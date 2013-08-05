package net.anei.cadpage.parsers;

import net.anei.cadpage.parsers.MsgInfo.Data;

public class Cadpage2Parser extends CadpageParserBase {
  
  private String delim;
  private boolean active911;
  
  public Cadpage2Parser() {
    this("\n", "", "", false);
  }
  
  Cadpage2Parser(String delim, String defCity, String defState) {
    this(delim, defCity, defState, false);
  }

  
  Cadpage2Parser(String delim, String defCity, String defState, boolean active911) {
    super(defCity, defState);
    this.delim = delim;
    this.active911 = active911;
  }
  
  @Override
  public String getLocName() {
    return "Standard Cadpage Format B";
  }
  
  @Override
  protected boolean parseMsg(String body, Data data) {
    
    // Only valid if positive ID established
    if (!isPositiveId()) return false;
    
    // Otherwise, process fields broken by newlines and ignore
    // anything that doesn't start with a valid keyword
    // Except for lines following an INFO: keyword, which we
    // assume result from a long INFO string that contains line breaks
    boolean info = false;
    for (String line : body.split(delim)) {
      int pt = line.indexOf(':');
      if (pt >= 0) {
        String key = line.substring(0,pt).trim();
        String value = line.substring(pt+1).trim();
        Field field = getMapField(key);
        
        // Active911 format treats unknown (to them) keywords as info strings
        // The net result is that info strings may be encountered after known
        // keywords found after an INFO field.  So once the info flag is set
        // it stays set forever
        if (field != null) {
          if (active911) {
            if (key.equals("INFO")) info = true;
          } else {
            info = key.equals("INFO");
          }
          field.parse(value, data);
          continue;
        }
      }
      if (info) {
        data.strSupp = append(data.strSupp, "\n", line);
      }
    }
    return true;
  }

}
