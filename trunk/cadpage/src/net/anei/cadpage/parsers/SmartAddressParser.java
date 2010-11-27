package net.anei.cadpage.parsers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.anei.cadpage.SmsMsgInfo;

/**
 * Abstract message parser class that adds support for "smart" address parsing.
 * That is parsing logic that can be used for text formats that do not clearly 
 * deliminate where the the address portion of the message can be found. 
 *
 */
public abstract class SmartAddressParser extends SmsMsgParser {
  

  /**
   * Type code indicating what kind of information might preceed the address
   * portion of this message
   */
  public  enum StartType {START_ADDR, START_CALL, START_PLACE, START_SKIP};
  
  /**
   * Flag indicating that the start field (either CALL, PLACE, or SKIP) must
   * contain at least one token
   */
  public static final int FLAG_START_FLD_REQ = 0x0001;
  
  // Main dictionary maps words to a bitmap indicating what is important about that word
  private final Map<String, Integer> dictionary = new HashMap<String, Integer>();
  
  // Bitmask indicating dictionary word is a road suffix
  private static final int ID_ROAD_SFX = 1;
  
  // Bitmask bit indicating dictionary word is a route number prefix
  private static final int ID_ROUTE_PFX = 2;
  
  // Bitmask bit indicating dictionary word is a direction modifier
  private static final int ID_DIRECTION = 4;
  
  // Bitmask bit indicating dictionary work is an intersection connector
  private static final int ID_CONNECTOR = 8;
  
  // Bitmask bit indicating dictionary word is a cross street selector
  private static final int ID_CROSS_STREET = 0x10;
  
  // Bitmask bit indicating dictionary word is a city name/code
  private static final int ID_CITY = 0x100;
  
  // Bitmask bit indicating numeric token
  private static final int ID_NUMBER = 0x200;
  
  // Bitmask bit indicating start of multiword token
  private static final int ID_MULTIWORD = 0x400;
  
  // Bitmask bit indicating a start address marker
  private static final int ID_START_MARKER = 0x800;
  
  // Bitmask bit indicating token had a preceding @ character
  private static final int ID_INCL_START_MARKER = 0x1000;
  
  // Bitmask bit indicating token is an appartment selector
  private static final int ID_APPT = 0x2000;
  
  // List of multiple word cities that need to be converted to and from single tokens
  List<String[]> mWordCities = null;
  
  public SmartAddressParser(String[] cities, String state) {
    this();
    setupCities(cities);
    setupDictionary(ID_ROUTE_PFX, new String[]{state});
  }
  
  public SmartAddressParser(Properties cityTable, String state) {
    this();
    setupCities(getKeywords(cityTable));
    setupDictionary(ID_ROUTE_PFX, new String[]{state});
  }
  
  public SmartAddressParser(String[] cities) {
    this();
    setupCities(cities);
  }
  
  public SmartAddressParser(Properties cityTable) {
    this();
    setupCities(getKeywords(cityTable));
  }
  
  public SmartAddressParser(String state) {
    this();
    setupDictionary(ID_ROUTE_PFX, new String[]{state});
  }
  
  // Default constructor
  public SmartAddressParser() {
    setupDictionary(ID_ROAD_SFX, 
        "AVENUE", "AV", "AVE", 
        "STREET", "ST", 
        "PLACE", "PL", 
        "ROAD", "RD", 
        "LANE", "LN", "LA",
        "DRIVE", "DR",
        "SQUARE", "SQ",
        "BLVD",
        "WAY", "PKWY", "PK", "FWY", "WY", "HW",
        "CIRCLE", "CIR",
        "TRAIL", "TRL",
        "PATH",
        "PIKE",
        "COURT", "CT",
        "TER", "TERR",
        "HWY",
        "MALL");
    setupDictionary(ID_ROUTE_PFX, "RT", "ST", "SRT", "US", "FS", "INTERSTATE", "I", "HW", "HWY", "STHWY", "CO", "CR");
    setupDictionary(ID_DIRECTION, "N", "NE", "E", "SE", "S", "SW", "W", "NW");
    setupDictionary(ID_CONNECTOR, "AND", "/", "&");
    setupDictionary(ID_START_MARKER, "AT", "@");
    
    // C/S should be in this list, but it gets changed before we parse stuff
    setupDictionary(ID_CROSS_STREET, "XS:", "X:");
    setupDictionary(ID_APPT, "APT:", "APT", "#");
  }
  
  private String[] getKeywords(Properties table) {
    String[] result = new String[table.size()];
    int ndx = 0;
    for (Enumeration<?> e = table.propertyNames(); e.hasMoreElements(); ) {
      result[ndx++] = (String)e.nextElement();
    }
    return result;
  }
  
  private void setupCities(String[] cities) {

    // Run thorough the city list
    for (String city : cities) {
      
      // If city name contains a blank, things get complicated
      if (city.contains(" ")) {
        
        // Break up city name into token list and add it to mWordCities
        if (mWordCities == null) mWordCities = new ArrayList<String[]>();
        String[] tokenList = city.split(" +");
        mWordCities.add(tokenList);
        
        // And add the first token to the dictionary as an incomplete city
        setupDictionary(ID_CITY | ID_MULTIWORD, tokenList[0]);
      }
      
      // Otherwise, we just add this to dictionary as a normal city
      setupDictionary(ID_CITY, city);
    }
  }
  
  private void setupDictionary(int bitMask, String ... args) {
    for (String arg : args) {
      setupDictioanry(bitMask, arg);
    }
  }

  private void setupDictioanry(int bitMask, String arg) {
    int newMask = bitMask;
    Integer oldMask = dictionary.get(arg);
    if (oldMask != null) newMask |= oldMask;
    dictionary.put(arg, newMask);
  }
  
  
  // Parser working variables
  private int flags;
  private String[] tokens;
  private int[] tokenType;
  private int lastCity = -1;
  private int stAddress = -1;
  private int startAddress = -1; 
  private int stApt = -1;
  private int startApt = -1;
  private int startCross = -1;
  private int startCity = -1;
  private int endAll = -1;
  
  /**
   * Determine if a string looks like a valid address
   * @param address Address string to be checked
   * @return zero if string is not recognized as valid address, otherwise a
   * numeric value in which higher values indicate better addresses
   */
  protected int checkAddress(String address) {
    return checkAddress(address, 0);
  }
  
  /**
   * Determine if a string looks like a valid address
   * @param address Address string to be checked
   * @param extra number of extra tokens (presumably city names) that can
   * be ignored at the end of the line
   * @return zero if string is not recognized as valid address, otherwise a
   * numeric value in which higher values indicate better addresses
   */
  protected int checkAddress(String address, int extra) {
    int result = parseAddress(StartType.START_ADDR, 0, address);
    if (tokens.length-endAll > extra) result = 0;
    return result;
  }

  /**
   * Parse address line
   * @param sType indicates what we now about start of address field
   *         START_ADDR - field starts with the address
   *         START_CALL - field starts with call description followed by address
   *         START_SKIP - field starts with something we aren't interested in,
   *                      followed by address
   * @param address address field to be parsed
   * @param data data object to be filled with information from parsed address field.
   */
  protected void parseAddress(StartType sType, String address, SmsMsgInfo.Data data) {
    parseAddress(sType, 0, address, data);
  }

  /**
   * Parse address line
   * @param sType indicates what we now about start of address field
   *         START_ADDR - field starts with the address
   *         START_CALL - field starts with call description followed by address
   *         START_SKIP - field starts with something we aren't interested in,
   *                      followed by address
   * @param flags - Special processing flags                     
   * @param address address field to be parsed
   * @param data data object to be filled with information from parsed address field.
   */
  protected void parseAddress(StartType sType, int flags, String address, SmsMsgInfo.Data data) {
    parseAddress(sType, flags, address);
    fillInData(sType, data);
  }
  
  /**
   * Internal method that does the real parsing
   * @param sType indicates what we now about start of address field
   *         START_ADDR - field starts with the address
   *         START_CALL - field starts with call description followed by address
   *         START_SKIP - field starts with something we aren't interested in,
   *                      followed by address
   * @param flags - Special processing flags                     
   * @param address address field to be parsed
   * @return integer indicating how good this address is, higher number mean better fit
   */
  private int parseAddress(StartType sType, int flags, String address) {

    this.flags = flags;
    lastCity = -1;
    stAddress = -1;
    startAddress = -1;
    stApt = -1;
    startApt = -1;
    startCross = -1;
    startCity = -1;
    endAll = -1;

    // Check for null string
    if (address.length() == 0) return 0;

    // Make sure any / or & character will parse by itself
    // Before we do that we have to protect the C/S cross street indicator
    address = address.replaceAll(" C/S ", " XS: ").replaceAll("/", " / ").replaceAll("&", " & ");
    
    // Periods used with abbreviations also cause trouble.  Just get rid of all periods
    address = address.replaceAll("\\.", "");
    
    // Parse line into tokens and categorize each token
    // While we are doing this, identify the index of the last city
    // And see if we have a keyword flagging the start of the address
    tokens = address.split("\\s+");
    tokenType = new int[tokens.length];
    
    stAddress = startAddress = (sType == StartType.START_ADDR? 0 : -1);
    boolean setStart = (startAddress < 0);
    for (int ndx = 0; ndx < tokens.length; ndx++) {
      setType(ndx);
      if (setStart) {
        if (isType(ndx, ID_START_MARKER)) {
          stAddress = ndx;
          startAddress = ndx + 1;
        } else if (isType(ndx, ID_INCL_START_MARKER)) {
          stAddress = startAddress = ndx;
        }
      }
      if (isType(ndx, ID_CITY)) lastCity = ndx;
    }
    
    // Now comes the hard part.
    
    // We have a number of basic patters that we will recognize
    // Try each one until we find one that works
    if (parseTrivialAddress()) return 4;
    if (parseSimpleAddress()) return 3;
    if (parseIntersection()) return 2;
    if (parseNakedRoad()) return 1;
    
    // Total failure, assign the entire field to either the call description
    // or the address
    endAll = tokens.length;
    if (startAddress < 0 ) {
      if (sType == StartType.START_SKIP) {
        stAddress = startAddress = 0;
      } else {
        stAddress = endAll;
      }
    }
    if (sType != StartType.START_CALL && sType != StartType.START_PLACE) startAddress = 0;
    return 0;
  }

  /**
   * Called after address has been parsed
   * @return the part of the line after the address
   */
  public String getLeft() {
    if (endAll < 0) return "";
    return buildData(endAll, tokens.length, false);
  }
  
  /**
   * We handle the dead simple case where the address starts at the 
   * beginning of the text and we have found a city to mark the end
   * of the address (would that life were always this simple
   */
  private boolean parseTrivialAddress() {
    
    // OK, we have to have at least 2 items before the city
    if (startAddress < 0) return false;
    return parseToCity(startAddress+2);
  }

  /**
   * Look for the basic address looking like
   *     <number> <street name> <street suffix>
   */
  private boolean parseSimpleAddress() {
    
    // Look for a numeric field which we assume is the house number
    // If field starts with address this has to be the first token
    // Exception, numeric fields that follow a route prefix are part
    // of a road name and cannot be a house number.
    // Exception to exception, CO is a valid route prefix, but it could also
    // be an abbreviation for COMPANY in a prefix name.  So we won't count it
    // as a route prefix
    int sAddr = startAddress >= 0 ? startAddress : isFlagSet(FLAG_START_FLD_REQ) ? 1 : 0;
    int sEnd;
    while (true) {
      while (true) {
        
        if (sAddr >= tokens.length) return false;
        if (isType(sAddr, ID_NUMBER)) {
          if (sAddr > 0 && isType(sAddr-1, ID_ROUTE_PFX) && 
              !tokens[sAddr-1].equalsIgnoreCase("CO")) return false;
          break;
        }
        if (startAddress >= 0) return false;
        sAddr++;
      }
      
      // If we found a city beyond this start point, just use that as the terminator
      if (parseToCity(sAddr)) return true;
      
      // Otherwise, see if we can find a road starting from the next token
      sEnd = findRoadEnd(sAddr+1);
      if (sEnd > 0) break;
      
      // This isn't what we are looking for
      // Increment the search index and look for something else
      sAddr++;
    }
    
    // We have found what we need to have found and we are going
    // to be successful
    if (startAddress < 0) stAddress = startAddress = sAddr;
    endAll = sEnd;
    
    // But there might be some additional cross street info we can parse
    findCrossStreet();
    return true;
  }

  /**
   * Look for intersection adddress with the basic form of
   *    <roadname> <road sfx> <connector> <roadname> <road sfx>
   * @return true if successful
   */
  private boolean parseIntersection() {
    
    // First lets figure out where the address starts
    int sAddr;
    int ndx;

    // If address starts at beginning of field, find end of address and
    // confirm that it starts with a road followed by a connector
    if (startAddress >= 0) {
      sAddr = startAddress;
      ndx = findRoadEnd(startAddress);
      if (ndx < 0) return false;
      if (! isType(ndx, ID_CONNECTOR)) return false;
    }
    
    // Otherwise, scan forward looking for a <road-sfx> <connector> combination
    // Then back up 2 places assuming the road consists of one token.
    // If the previous token is a direction, back up one more to include that.
    else {
      int start = isFlagSet(FLAG_START_FLD_REQ) ? 1 : 0;
      ndx = start + 1;
      boolean dirFound = false;
      while (true) {
        ndx++;
        if (ndx >= tokens.length) return false; 
        if (isType(ndx, ID_CONNECTOR)) {
          sAddr = ndx-1;
          dirFound = isType(sAddr, ID_DIRECTION);
          if (dirFound) sAddr--;
          
          if (isRoadToken(sAddr)) break;
          
          if (sAddr > start) { 
            sAddr--;
            if (isType(sAddr+1, ID_ROAD_SFX)) break;
            if (isType(sAddr, ID_ROUTE_PFX) & ndx>2 & isType(sAddr+1, ID_NUMBER)) break;
          }
        }
      }
      
      // If road is preceded by a direction, include that
      if (!dirFound) sAddr = stretchRoadPrefix(start, sAddr);
    }
    
    // When we get here, 
    // saddr points to beginning of address
    // ndx points to the first connector after the first road name.

    // If there is a city terminating the address, just parse up to it
    if (parseToCity(ndx)) {
      stAddress = startAddress = sAddr;
      return true;
    }
    
    // Otherwise find end of second road
    ndx = findRoadEnd(ndx+1);
    if (ndx < 0) return false;
    
    // If we found that, we have a successful intersection parse
    if (startAddress < 0) stAddress = startAddress = sAddr;
    endAll = ndx;
    
    // But there might be some additional cross street info we can parse
    findCrossStreet();
    return true;
  }
  
  /**
   * Look for simple road without a house number or intersection
   *    <roadname> <roadsfx>
   * @return true if found 
   */
  private boolean parseNakedRoad() {

    int ndx;

    // If address starts at beginning of field, find end of address and
    // Don't have to look for city because we wouldn't be here if both startAddr
    // and city was found
    if (startAddress >= 0) {
      ndx = findRoadEnd(startAddress);
      if (ndx < 0) return false;
    }
    
    // Otherwise, scan forward looking for a <road-sfx>
    //            that isn't the start of a <route-pfx> <number> combination
    // or number preceded by a <route-pfx>
    else {
      int start = isFlagSet(FLAG_START_FLD_REQ) ? 1 : 0;
      ndx = start;
      int sAddr;
      while (true) {
        ndx++;
        sAddr = ndx - 1;
        if (ndx >= tokens.length) return false; 
        if (isType(ndx, ID_ROAD_SFX)) {
          if (!isType(ndx, ID_ROUTE_PFX) || !isType(ndx+1, ID_NUMBER)) break;
        }
        if (ndx > start && isType(ndx, ID_NUMBER) && isType(ndx-1, ID_ROUTE_PFX)) break;
        if (isRoadToken(ndx)) {
          sAddr = ndx;
          break;
        }
      }
      // If the previous token is a direction, back up one more to include that.
      int saveSaddr = sAddr;
      sAddr = stretchRoadPrefix(start, sAddr);
      boolean dirFound = (sAddr != saveSaddr);
      
      // increment end pointer past the road terminator
      // If the following token is a direction, increment end pointer past that too
      ndx++;
      if (!dirFound && isType(ndx, ID_DIRECTION)) ndx++;
      stAddress = startAddress = sAddr;
    }
    
    // When we get here, 
    // startAddress points to beginning of address
    // ndx points past the end of the road
    
    // We have a naked road parse
    endAll = ndx;
    
    // See if we can parse out to a city
    if (parseToCity(startAddress)) return true;
    
    // Nope, see if we can find some cross street info
    findCrossStreet();
    return true;
  }
  
  /**
   * Called when we found the end of a road name and are guessing at where
   * it might start
   * @param start Hard limit on where address can start
   * @param sAddr index of current start of tentative road name
   * @return index of presumed road name
   */
  private int stretchRoadPrefix(int start, int sAddr) {
    
    // Look up to 3 tokensn back to see if we find a direction token
    // Stop search if we encounther a /, lest we confuse a W/INJURY
    // as part of a road name
    for (int j = 1; j<= 3; j++) {
      int ndx = sAddr - j;
      if (ndx < start) break;
      if (tokens[ndx].equals("/")) break;
      if (isType(ndx, ID_DIRECTION)) return sAddr-j; 
    }
    return sAddr;
  }
  
  /**
   * See if we can parse an address from a known starting point to a city
   * @param stNdx known start of address
   * @return true if successful
   */
  private boolean parseToCity(int stNdx) {
    
    if (lastCity <= stNdx) return false;
    
    if (startAddress < 0) stAddress = startAddress = stNdx;
    for (int ndx = stNdx; ndx < tokens.length; ndx++) {
      if (isType(ndx, ID_APPT) && startCross < 0) {
        stApt = ndx;
        startApt = ndx + 1;
      }
      if (isType(ndx, ID_CROSS_STREET)) startCross = ndx + 1;
      int endCity = findEndCity(ndx);
      if (endCity >= 0) {
        startCity = ndx;
        endAll = endCity;
        return true;
      }
    }
    return false;
  }
  
  /**
   * Find the end of a city that starts at the current index
   * @param ndx current index
   * @return one past the last token in city if city was found,
   * -1 if this is not a city.
   */
  private int findEndCity(int ndx) {
    
    // If this isn't a city or city start the answer is no
    if (! isType(ndx, ID_CITY)) return -1;
    
    // If this is a complete one word city, the answer is yes
    int endNdx = -1;
    if (! isType(ndx, ID_MULTIWORD)) {
      endNdx = ndx + 1;
    }
    
    // Otherwise this might be the start of a multi-word city, we will have to
    // compare each possible multi-word city against the current token
    // list to see if we have a match
    else {
      for (String[] tokenList : mWordCities) {
        boolean match = true;
        for (int j = 0; j< tokenList.length; j++) {
          if (ndx+j >= tokens.length ||
              ! tokenList[j].equalsIgnoreCase(tokens[ndx+j])) {
            match = false;
            break;
          }
        }
        if (match) {
          endNdx = ndx + tokenList.length;
          break;
        }
      }
    }
    
    // If we didn't find a city, return -1
    if (endNdx < 0) return -1;
    
    // If we did find a city, check to make sure it isn't followed by
    // a road suffix before we return it's end
    if (isType(endNdx, ID_ROAD_SFX)) return -1;
    return endNdx;
  }

  /**
   * See if we can find some additional cross street information after
   * the end of a successfully parsed address
   */
  private void findCrossStreet() {
    
    if (endAll >= tokens.length) return;
    
    // First lets look for an apartment
    if (isType(endAll, ID_APPT)) {
      stApt = endAll++;
      startApt = endAll;
    }
    
    else if (tokens[endAll].length() <= 4) {
      char chr = tokens[endAll].charAt(0);
      if (chr == '#' || Character.isDigit(chr)) {
        stApt = startApt = endAll++;
      }
    }
    
    // Now see if we are at a cross street indicator.  If not, nothing to find
    if (! isType(endAll, ID_CROSS_STREET)) return;
    
    // If it was, skip past it 
    int sCross = endAll + 1;
    int sEnd = sCross;
    
    while (true) {
      
      // And try to find another road
      sEnd = findRoadEnd(sEnd);
      if (sEnd < 0) break;
      
      // Success, save the cross street location
      startCross = sCross;
      endAll = sEnd;
      
      // If this road was terminated by a connector
      // Loop back and see if we can find another cross street
      if (!isType(sEnd, ID_CONNECTOR)) break;
      sEnd++;
    }
  }

  /**
   * See if we can identify a road name starting at a given index
   * @param start starting index
   * @return index of token past end of road name if successful, -1 otherwise
   */
  private int findRoadEnd(int start) {
    
    // If this starts with a street direction, skip over it
    boolean dirFound = isType(start, ID_DIRECTION); 
    if (dirFound) start++;
    
    // A stand alone road token can terminate the road search, but it must
    // be the first thing in the search sequence
    int end = start+1;
    if (! isRoadToken(start)) {
      
      // Now start looking for a street suffix (or cross street indicator
      // If we have to pass more than two tokens before finding, give up
      
      while (true) {
        
        if (isType(end, ID_ROAD_SFX)) {end++; break; }
        if (isType(end, ID_CROSS_STREET)) break;
        
        // A number preceded by a route prefix counts as a road
        if (isType(end, ID_NUMBER) && end > 0 && isType(end-1, ID_ROUTE_PFX)) {end++; break;}
        
        // If potential road gets too long, give up
        if (++end - start > 3) return -1;
      }
    }
    
    // If road is followed by a direction, include that
    if (! dirFound && isType(end, ID_DIRECTION)) end++;
    return end;
  }

  /**
   * Fill data object with information from parsed line
   */
  private void fillInData(StartType startType, SmsMsgInfo.Data data) {
    
    // If prefix ends with some variation of "REPORTED AT" drop the 
    // REPORTED (AT has already been dropped)
    if (stAddress > 0 && isType(stAddress, ID_START_MARKER) &&
        tokens[stAddress-1].equalsIgnoreCase("REPORTED")) stAddress--;
    
    int end = endAll;
    if (startCity >= 0) {
      data.strCity = buildData(startCity, end, false);
      end = startCity;
    }
    
    if (startCross >= 0) {
      data.strCross = buildData(startCross, end, false);
      end = startCross - 1;
    }
    
    if (startApt >= 0) {
      data.strApt = buildData(startApt, end, false);
      end = stApt;
    }
    
    if (startAddress >= 0) {
      data.strAddress = buildData(startAddress, end, true);
      end = startAddress;
    }
    
    switch (startType) {
    case START_CALL:
      data.strCall = buildData(0, stAddress, false).replaceAll(" / ", "/");
      break;
    case START_PLACE:
      data.strPlace = buildData(0, stAddress, false).replaceAll(" / ", "/");
      break;
    }
  }

  /**
   * Construct data field from the token sequence from given start and end position
   * @param start starting token index
   * @param end ending token index
   * @param addr true if we are processing the address field
   * @return Constructed data field.
   */
  private String buildData(int start, int end, boolean addr) {
    
    StringBuilder sb = new StringBuilder();
    for (int ndx = start; ndx < end; ndx++) {
      if (ndx != start) sb.append(' ');
      String token = tokens[ndx];
      if (addr && isType(ndx, ID_CONNECTOR)) token = "&";
      sb.append(token);
    }
    return sb.toString();
  }

  // Identify token type
  private void setType(int ndx) {
    String token = tokens[ndx];
    
    // If token is longer than 1 char and starts with an @
    // Strip off the @ and add the ID_INCL_START_MARK flag
    int mask = 0;
   if (token.length() > 1 && token.charAt(0) == '@') {
     tokens[ndx] = token = token.substring(1);
     mask = ID_INCL_START_MARKER;
   }
    
    // If token is in dictionary, return the associated type code
    Integer iType = dictionary.get(token.toUpperCase());
    if (iType != null) {
      tokenType[ndx] = (mask | iType);
    }
    
    else if (NUMERIC.matcher(token).matches()) {
      tokenType[ndx] = (mask | ID_NUMBER);
    } else {
      tokenType[ndx] =  mask;
    }
  }
  
  private boolean isType(int ndx, int mask) {
    if (ndx >= tokenType.length) return false; 
    return (tokenType[ndx] & mask) != 0;
  }
  
  private boolean isFlagSet(int mask) {
    return (flags & mask) != 0;
  }
  
  // Determine if token is a single standalone road token
  // such as I-234, or US50, or RT250NB :(
  private boolean isRoadToken(int ndx) {
    
    // Get the string token
    if (ndx >= tokenType.length) return false; 
    String token = tokens[ndx];
    
    // See how many letters there are at start of token
    int pt = 0;
    while (pt < token.length() && Character.isLetter(token.charAt(pt))) pt++;
    
    // If zero, or if entire token is letters, the answer is no
    if (pt == 0 || pt == token.length()) return false;
    
    // If next character is a dash, skip over it
    int pta = pt;
    
    int ptBreak = pt;
    boolean dashFound = (token.charAt(pt) == '-');
    if (dashFound) {
      pt++;
      ptBreak = -1;
    }
    
    // If next character is not a digit, answer is no
    if (pt >= token.length() || ! Character.isDigit(token.charAt(pt))) return false;
    
    // Shift the alpha portion upper case and see if what is left is a route prefix
    String pfx = token.substring(0, pta).toUpperCase();
    Integer mask = dictionary.get(pfx);
    if (mask == null || (mask&ID_ROUTE_PFX) == 0) return false;
    
    // Looks promising.  Now scan over any digits and see what we have left
    do pt++; while (pt < token.length() && Character.isDigit(token.charAt(pt)));
    
    // If we are at end of string, answer is yes
    // Otherwise, the answer depends on if the remaining suffix looks like some
    // kind of highway qualifier (NB = north bound for example)
    if (pt != token.length()) {
      String sfx = token.substring(pt).toUpperCase();
      
      do {
        if (sfx.length() == 2 && sfx.charAt(1) == 'B' &&
            "NESW".indexOf(sfx.charAt(0)) >= 0) break;
        
        // Nothing recognized, return false
        return false;
        
      } while (false);
      
      // OK, this looks valid.
      // But Google maps won't recognize the highway qualifier, so we have to get rid of it
      tokens[ndx] = token.substring(0, pt);
    }
    
    // OK, we've concluded this is a valid single word road name.  Now we
    // need to clean up some things Google can't handle
    
    // Google doesn't handle some prefixes with a dash separator, so just to
    // be on the safe side, we always insert one if it wasn't there initially
    // Unless this is a RT prefix, in which cas the dash just doesn't work at all
    if (ptBreak >= 0 && !pfx.equals("RT")) {
      tokens[ndx] = tokens[ndx].substring(0, ptBreak) + "-" + tokens[ndx].substring(ptBreak);
    }
    
    // And Google doesn't like the SRT prefix, change it it to ST
    if (pfx.equals("SRT")) {
      tokens[ndx] = "ST" + tokens[ndx].substring(3);
    }
    return true;
  }
}
