package net.anei.cadpage;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.res.Resources;

import net.anei.cadpage.parsers.GroupBestParser;
import net.anei.cadpage.parsers.SmsMsgParser;


/**
 * This class takes care of allocating the correct parser for a location code
 */
public class ManageParsers {
  
  // The name of the general alert parser code
  private static final String ALERT_PARSER = "GeneralAlert";
  
  // Table mapping known location codes to their corresponding parser
  private Map<String, SmsMsgParser> parserMap = new HashMap<String, SmsMsgParser>();
  
  private String curLocCode = null;
  private SmsMsgParser curParser = null;
  
  private Map<String,String> parserNames = null;
 
  // Private constructor, no body can build this except getInstance()
  private ManageParsers() {}
  

  /**
   * Get parser corresponding to location code
   * @param location requested location code or null to use current config setting
   * @return requested parser
   */
  public SmsMsgParser getParser(String location) {
    
    // First level cache.  If location code matches what we have stored for
    // the current location code, return the current parser
    String curLocation = null;
    if (location == null) curLocation = location = ManagePreferences.location();
    if (location.equals(curLocCode)) return curParser;
    
    // Second level cache, see if it is the our table of parsers by location
    SmsMsgParser parser = parserMap.get(location);
    if (parser == null) {
      
      // Otherwise we need to create a new parser
      // First see if there are multiple location parsers
      if (location.contains(",")) {
        
        // If there are, call ourselves recursively to allocate each
        // individual parser
        String[] locationList = location.split(",");
        SmsMsgParser[] parserList = new SmsMsgParser[locationList.length];
        for (int ii = 0; ii<locationList.length; ii++) {
          parserList[ii] = getParser(locationList[ii]);
        }
        parser = new GroupBestParser(parserList);
      }
      
      // Otherwise find the parser class and instantiate it
      else {
        String className = "net.anei.cadpage.parsers." + location + "Parser";
        try {
          parser = (SmsMsgParser)Class.forName(className).newInstance();
        } catch (Exception ex) {
          throw new RuntimeException(ex);
        }
      }
      
      // Then save the location and parser in our parser table 
      parserMap.put(location, parser);
    }
    
    // Before we return the parser we found, see if the requested location
    // matches the current location setting.  If it does, save the values
    // in the first level cache
    if (curLocation == null) curLocation = ManagePreferences.location();
    if (location.equals(curLocation)) {
      curLocCode = location;
      curParser = parser;
    }
    return parser;
  }
  
  /**
   * @return parser used to process general alerts (no parsing no address)
   */
  public SmsMsgParser getAlertParser() {
    return getParser(ALERT_PARSER);
  }

  /**
   * Prime the parser name map.  Calling this enables future calls to
   * getLocName()
   * @param ctx current context
   */
  public void setupNames(Context ctx) {
    parserNames = new HashMap<String,String>();
    Resources res = ctx.getResources();
    String[] locValues = res.getStringArray(R.array.pref_location_values);
    String[] locNames = res.getStringArray(R.array.pref_location_options);
    for (int ii = 0; ii<locValues.length; ii++) {
      parserNames.put(locValues[ii], locNames[ii]);
    }
  }
  
  /**
   * Return location name associated with location code
   * @param location location code
   * @return location name 
   */
  public String getLocName(String location) {
    if (parserNames == null) return "";
    String name = parserNames.get(location);
    return (name != null ? name : "");
  }
  
  private static ManageParsers instance = new ManageParsers();
  
  /**
   * @return singleton instance of ManageParsers
   */
  public static ManageParsers getInstance() {
    return instance;
  }

}
