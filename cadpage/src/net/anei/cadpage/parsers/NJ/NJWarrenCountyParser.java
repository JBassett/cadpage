package net.anei.cadpage.parsers.NJ;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.SmartAddressParser;


/**
 * Warren County, NJ
 */
public class NJWarrenCountyParser extends SmartAddressParser {
  
  private static final Pattern MASTER = Pattern.compile("([A-Z0-9]+) ALERT: ([^\\(\\)]+?) \\(SENT (\\d\\d/\\d\\d) (\\d\\d:\\d\\d)\\)");
  private static final Pattern DELIM = Pattern.compile(" *, +");
  private static final Pattern CITY_TRIM_PTN = Pattern.compile(" (?:BORO|TOWN)$");
  
  public NJWarrenCountyParser() {
    super(CITY_LIST, "WARREN COUNTY", "NJ");
  }
  
  @Override
  public String getFilter() {
    return "WC911@co.warren.nj.us";
  }
  
  @Override
  public boolean parseMsg(String subject, String body, Data data) {
    String[] lines = body.split("\n");
    Matcher match = MASTER.matcher(lines[0]);
    if (!match.matches()) return false;
    data.strUnit = match.group(1);
    String sAddr = match.group(2);
    data.strDate = match.group(3);
    data.strTime = match.group(4);
    
    // Sometimes there are nice comma delimiters
    String[] flds = DELIM.split(sAddr);
    if (flds.length == 3) {
      data.strCall = flds[0];
      data.strCity = flds[2];
      
      String addr = flds[1];
      String[] parts = addr.split(" / ");
      if (parts.length == 2 && checkAddress(parts[0]) == 0) {
        data.strPlace = parts[0].trim();
        addr = parts[1].trim();
      }
      parseAddress(addr, data);
    }
    
    // and sometimes there are not
    else {
      parseAddress(StartType.START_CALL, FLAG_START_FLD_REQ, sAddr, data);
    }
    
    // Clean up odd things in call and city fields
    if (data.strCall.endsWith("/")) data.strCall = data.strCall.substring(0,data.strCall.length()-1).trim();
    match = CITY_TRIM_PTN.matcher(data.strCity);
    if (match.find()) data.strCity = data.strCity.substring(0,match.start());
    
    if (lines.length > 1) {
      String cross = lines[1];
      if (cross.startsWith("Cross Streets-")) {
        data.strCross = cross.substring(14).trim();
      }
    }
    return true;
  }
  
  private static String[] CITY_LIST = new String[]{
    "ALPHA BORO",
    "PHILLIPSBURG TOWN",
    "POHATCONG TWP"
  };
}
