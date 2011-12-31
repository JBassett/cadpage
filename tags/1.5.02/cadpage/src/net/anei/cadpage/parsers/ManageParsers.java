package net.anei.cadpage.parsers;

import java.util.HashMap;
import java.util.Map;



/**
 * This class takes care of allocating the correct parser for a location code
 */
public class ManageParsers {
  
  // The name of the general alert parser code
  private static final String ALERT_PARSER = "GeneralAlert";
  
  // Table mapping known location codes to their corresponding parser
  private Map<String, MsgParser> parserMap = new HashMap<String, MsgParser>();
  
  private String curLocCode = null;
  private MsgParser curParser = null;
 
  // Private constructor, no body can build this except getInstance()
  private ManageParsers() {}
  
  /**
   * Get parser corresponding to location code
   * @param location requested location code or null to use current config setting
   * @return requested parser
   */
  public MsgParser getParser(String location) {
    
    // First level cache.  If location code matches what we have stored for
    // the current location code, return the current parser
    String curLocation = null;
    if (location.equals(curLocCode)) return curParser;
    
    // Second level cache, see if it is the our table of parsers by location
    MsgParser parser = parserMap.get(location);
    if (parser == null) {
      
      // Otherwise we need to create a new parser
      // First see if there are multiple location parsers
      if (location.contains(",")) {
        
        // If there are, call ourselves recursively to allocate each
        // individual parser
        String[] locationList = location.split(",");
        MsgParser[] parserList = new MsgParser[locationList.length];
        for (int ii = 0; ii<locationList.length; ii++) {
          parserList[ii] = getParser(locationList[ii]);
        }
        parser = new GroupBestParser(parserList);
      }
      
      // Otherwise find the parser class and instantiate it
      else {
        String className = getParserClassname(location);
        try {
          parser = (MsgParser)Class.forName(className).newInstance();
        } catch (Exception ex) {
          throw new RuntimeException(ex.getMessage(), ex);
        }
      }
      
      // Then save the location and parser in our parser table 
      parserMap.put(location, parser);
    }
    
    // Before we return the parser we found, see if the requested location
    // matches the current location setting.  If it does, save the values
    // in the first level cache
    if (location.equals(curLocation)) {
      curLocCode = location;
      curParser = parser;
    }
    return parser;
  }
  
  /**
   * Get fully qualified parser class name associated with location
   * @param location requested location
   * @return parser class name
   */
  private String getParserClassname(String location) {
    
    String pkg = null;
    if (Character.isUpperCase(location.charAt(1))) {
      pkg = location.substring(0,2);
    } else if (location.startsWith("Dispatch")) {
      pkg = "dispatch";
    } else if (location.startsWith("General")) {
      pkg = "general";
    }
    StringBuffer sb = new StringBuffer();
    sb.append(this.getClass().getPackage().getName());
    sb.append('.');
    if (pkg != null) {
      sb.append(pkg);
      sb.append('.');
    }
    sb.append(location);
    sb.append("Parser");
    return sb.toString();

  }
  
  /**
   * @return parser used to process general alerts (no parsing no address)
   */
  public MsgParser getAlertParser() {
    return getParser(ALERT_PARSER);
  }
  
  /**
   * Return location name associated with location code
   * @param location location code
   * @return location name 
   */
  public String getLocName(String location) {
    return getParser(location).getLocName();
  }
  
  private static ManageParsers instance = new ManageParsers();
  
  /**
   * @return singleton instance of ManageParsers
   */
  public static ManageParsers getInstance() {
    return instance;
  }

}
