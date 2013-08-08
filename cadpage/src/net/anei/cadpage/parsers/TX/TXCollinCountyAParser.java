package net.anei.cadpage.parsers.TX;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.CodeSet;
import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;

/**
 * Collin County, TX
 */
public class TXCollinCountyAParser extends FieldProgramParser {

  private static final Pattern TRAIL_JUNK_PTN = Pattern.compile("(?:\\[[^\\[\\]]*)?\\{[^\\{]*\\}?$");
  private static final Pattern CFS_ID_PTN = Pattern.compile(" CFS (\\d{8})\\b");
  private static final Pattern LEAD_DASH_PTN = Pattern.compile("^[ -]+");
  private static final Pattern DIST_GRID_PTN = Pattern.compile("\\[([A-Z]+) .*?GRID: ([A-Z]*\\d*) *\\]");
  
  private static final String[] DOUBLE_CITY_LIST = new String[] {
    "ANNA",
    "AUBREY",
    "BLUE RIDGE",
    "CELINA",
    "COLLIN COUNTY",
    "COLONY",
    "DENTON",
    "FRISCO",
    "LEWISVILLE",
    "LITTLE ELM",
    "LOWRY CROSSING",
    "MC KINNEY",
    "MELISSA",
    "PILOT POINT",
    "PRINCETON",
    "ROYSE CITY",
    "ST PAUL",
    "VAN ALSTYNE"
  };
  
  public TXCollinCountyAParser() {
    this("COLLIN COUNTY", "TX");
  }
  
  @Override
  public int getMapFlags() {
    return MAP_FLG_SUPPR_LA;
  }

  protected TXCollinCountyAParser(String defCity, String defState) {
    super(defCity, defState,
          "MASH UNITS:UNIT ST_RMK:INFO CFS_RMK:INFO");
    setupCallList(CALL_LIST);
  }
  
  @Override
  public String getFilter() {
    return "ccsodispatch@co.collin.tx.us,DispatchSMS@coppelltx.gov";
  }

  @Override
  protected boolean parseMsg(String body, Data data) {
    
    String alert = null;
    if (body.startsWith("Message From Dispatch ")) {
      body = body.substring(22).trim();
      if (body.startsWith("MUTUAL AID")) {
        Matcher match  = CFS_ID_PTN.matcher(body);
        if (match.find()) {
          data.strCallId = match.group(1);
          parseAddress(StartType.START_CALL, FLAG_START_FLD_REQ, body.substring(0,match.start()).trim(), data);
          String sInfo = body.substring(match.end());
          match = LEAD_DASH_PTN.matcher(sInfo);
          if (match.find()) sInfo = sInfo.substring(match.end());
          data.strSupp = sInfo;
          return true;
        }
        
        parseAddress(StartType.START_CALL, FLAG_START_FLD_REQ, body, data);
        return true;
      }
      
      alert = body;
    }

    // Remove trailing ID
    Matcher match = TRAIL_JUNK_PTN.matcher(body);
    if (match.find()) body = body.substring(0,match.start()).trim();
    
    // Some variants include the source and map code in square brackets
    // If this is one of those, extract that information and remove the
    // square bracket construct
    match = DIST_GRID_PTN.matcher(body);
    if (match.find()) {
      data.strSource = match.group(1).trim();
      data.strMap = match.group(2).trim();
      body = body.substring(0,match.start()) + body.substring(match.end());
    }
    
    int pt = body.indexOf("CFS No: ");
    if (pt >= 0) {
      data.strSupp = body.substring(0,pt).trim();
      body = body.substring(pt+8).trim();
    }
    
    // It seems that the original dispatch message uses double blanks as field
    // delimiters, but that some helpful? forwarding services are eliminating
    // the redundant blanks.  If this text message has the original double
    // blank delimiters, we can call parseFields to finsh things off
    body = body.replace("CFS RMK ", "CFS RMK: ");
    body = body.replaceAll(" +/ +", " / ");
    if (super.parseMsg(body, data)) {
      
      if (data.strCity.equalsIgnoreCase("COLLIN COUNTY")) data.strCity = "";
      if (data.strAddress.length() > 0) return true;
    }
    
    if (alert != null) return data.parseGeneralAlert(this, alert);
    return false;
  }
  
  @Override
  public String getProgram() {
    return "ID CALL ADDR CITY PLACE X SRC MAP UNIT INFO TIME";
  }
  
  // Parse a mashup of ID, CALL, ADDR, CITY, and Cross streets all of which might
  // or might not be separated by double blank delimiters
  private static final Pattern ID_PTN = Pattern.compile("^(\\d{8}) +");
  private static final Pattern BRACKET_PTN = Pattern.compile(" +\\{(.*?)\\} *");
  private static final Pattern STANDBY_PTN = Pattern.compile("^STANDBY(?: AT THIS TIME)?  +");
  private static final Pattern IN_PTN = Pattern.compile(" +IN +", Pattern.CASE_INSENSITIVE);
  private class MashField extends Field {
    
    @Override
    public void parse(String field, Data data) {
      
      // Start with easy stuff.  ID is always the first token
      Matcher match = ID_PTN.matcher(field);
      if (!match.find()) abort();
      data.strCallId = match.group(1);
      field = field.substring(match.end());
      
      // A field in {} is considered a place name
      match = BRACKET_PTN.matcher(field);
      if (match.find()) {
        data.strPlace = match.group(1);
        field = field.substring(0,match.start()) + "  " + field.substring(match.end());
      }
      
      // If first phrase is a standby request, combine it with second term
      field = STANDBY_PTN.matcher(field).replaceFirst("STANDBY ");
      
      // Break up what is left by any double blank delimiters and see what we have to work with
      String[] flds = field.split("  +");
      switch (flds.length) {
      
      case 1:
        
        parseAddr(CALL | CROSS, flds[0], data);
        return;
        
      case 2:
        
        // Two fields is ambiguous, we don't know if if the break is call/address and cross
        // or call and address/cross.  We'll check both for an IN keyword, which
        // would mark the address
        if (parseAddr(OPTIONAL | CROSS, flds[1], data)) {
          data.strCall = flds[0];
          return;
        }
        
        if (parseAddr(OPTIONAL | CALL, flds[0], data)) {
          data.strCross = flds[1];
          return;
        }
        
        break;
        
      case 3:
        
        // Three fields breaks into call, address/city and cross
        data.strCall = flds[0];
        parseAddr(0, flds[1], data);
        data.strCross = flds[2];
        return;
        
      // More than 3 fields, we haven't a clue what to do.  
      default:
        abort();
      }
    }
    
    private static final int OPTIONAL = 1;
    private static final int CALL = 2;
    private static final int CROSS = 4;
    private boolean parseAddr(int flags, String sAddress, Data data) {
      
      // Break out flag options
      boolean optional = (flags & OPTIONAL) != 0;
      boolean call = (flags & CALL) != 0;
      boolean cross = (flags & CROSS) != 0;
      
      int parseFlags = 0;
      StartType st = StartType.START_ADDR;
      if (call) {
        st = StartType.START_CALL;
        parseFlags |= FLAG_START_FLD_REQ; 
      }
      
      // Next, see if address contains an IN keyword separated call/address from city/cross
      Matcher match = IN_PTN.matcher(sAddress);
      if (match.find()) {
        
        // Check, use smart parser to split call and address
        parseAddress(st, parseFlags | FLAG_ANCHOR_END, sAddress.substring(0,match.start()), data);
        
        // Now lets look at the right side of the IN keyword
        // If we aren't handling cross streets, it is all city
        String tail = sAddress.substring(match.end());
        if (!cross) {
          data.strCity = tail;
          return true;
        }
        
        // Otherwise, see if it starts with a two word city, if it does
        // use that city to break tail into city and cross streets
        String tail2 = tail.toUpperCase();
        for (String tc : DOUBLE_CITY_LIST) {
          if (tail2.startsWith(tc)) {
            data.strCity = tail.substring(0,tc.length());
            data.strCross = tail.substring(tc.length()).trim();
            return true;
          }
        }
        
        // Otherwise first word of tail is city, rest is cross
        Parser p = new Parser(tail);
        data.strCity = p.get(' ');
        data.strCross = p.get();
        return true;
      }
      
      // No IN keyword, if this was an optional parse, return failure
      if (optional) return false;
      
      // no IN keyword, which we assume means no city
      // Use smart address parser to separate call, adddress, and cross
      if (!cross) parseFlags |= FLAG_ANCHOR_END;
      parseAddress(st, parseFlags, sAddress, data);
      if (cross) data.strCross = getLeft();
      return true;
    }
    
    @Override
    public String getFieldNames() {
      return "ID CALL ADDR APT CITY X";
    }
  }
  
  private static final Pattern INFO_TIME_PTN = Pattern.compile("^(\\d\\d:\\d\\d)\\b");
  private static final Pattern INFO_NONE_PTN = Pattern.compile(" *<NO(?:NE|.* COMMENTS|.* REMARKS)> *");
  private class MyInfoField extends InfoField {
    @Override
    public void parse(String field, Data data) {
      Matcher match = INFO_TIME_PTN.matcher(field);
      if (match.find()) {
        data.strTime = match.group(1);
        field = field.substring(match.end()).trim();
      }
      field = INFO_NONE_PTN.matcher(field).replaceAll(" ").trim();
      super.parse(field, data);
    }
    
    @Override
    public String getFieldNames() {
      return "TIME INFO";
    }
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("MASH")) return new MashField();
    if (name.equals("INFO")) return new MyInfoField();
    return super.getField(name);
  }
  
  private static final CodeSet CALL_LIST = new CodeSet(
      "COMERCIAL FIRE ALARM",
      "DUMPSTER FIRE",
      "DRIVING WHILE INTOXICATED",
      "EMERGENCY MEDICAL CALL",
      "FIRE ALARM",
      "FIRE PUBLIC ASSIST",
      "FIRST RESPONDERS",
      "GRASS FIRE",
      "INJURED PERSON",
      "INVESTIGATION-UNKNOWN SIT.",
      "MAJOR ACCIDENT 10/50",
      "MINOR ACCIDENT 10/50",
      "MEDICAL EMERGENCY",
      "MEDICATION OVERDOSE",
      "MUTUAL AID GRASS FIRE",
      "MUTUAL AID SFIRE",
      "MUTUAL AID STRUCTURE FIRE",
      "PROPERTY PUBLIC ASSIST",
      "RESCUE-TRAPED PERSON(S)",
      "RESIDENTIAL FIRE ALARM",
      "STANDBY ELECTRICAL FIRE",
      "STRUCTURE FIRE",
      "TEST CALL",
      "TRAFIC HAZARD",
      "TRASH FIRE",
      "UNAUTHORZED BURN",
      "UNKNOWN FIRE",
      "VEHICLE FIRE",
      "WELFARE CHECK"
  );
}
