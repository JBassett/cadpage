package net.anei.cadpage.parsers.GA;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.SmartAddressParser;


public class GAWalkerCountyParser extends SmartAddressParser {
  
  private static final Pattern MASTER = Pattern.compile("([A-Z]{3,4})  +\\1 +(.*?) +(\\d{4}-\\d{8})");
  
  private static final Pattern DATE_TIME_PTN = Pattern.compile(" +(\\d\\d/\\d\\d/\\d\\d) (\\d\\d:\\d\\d)$");
  private static final Pattern PART_DATE_TIME_PTN = Pattern.compile(" +\\d\\d/[\\d/]*(?: [\\d:]*)?$");
  private static final Pattern DISPATCHED_PTN = Pattern.compile("^Dispatch received by unit ([A-Z0-9]+)\\b *");
  
  public GAWalkerCountyParser() {
    super(CITY_LIST, "WALKER COUNTY", "GA");
  }
  
  @Override
  public String getFilter() {
    return "dispatch@walkerga.org";
  }
  
  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    
    if (!subject.equals("!")) return false;
    
    // There are two formats we are handling.  Check for the newer one first
    Matcher match = MASTER.matcher(body);
    if (match.matches()) {
      setFieldList("SRC ADDR APT CITY CALL ID");
      data.strSource = match.group(1);
      String addr = match.group(2);
      data.strCallId = match.group(3);
      
      parseAddress(StartType.START_ADDR, addr, data);
      data.strCall = getLeft();
      return true;
    }
    
    // Otherwise process the old format
    setFieldList("CALL ADDR APT X CITY UNIT INFO DATE TIME");

    int pt = body.indexOf("   ");
    if (pt < 0) return false;
    String addr = body.substring(0,pt);
    String desc = body.substring(pt+3).trim();
    
    parseAddress(StartType.START_CALL, FLAG_START_FLD_REQ | FLAG_PAD_FIELD | FLAG_ANCHOR_END, addr, data);
    data.strCross = getPadField();
    
    // Strip date/time from end of description area
    match = DATE_TIME_PTN.matcher(desc);
    if (match.find()) {
      data.strDate = match.group(1);
      data.strTime = match.group(2);
      desc = desc.substring(0, match.start());
    }
    
    // No go, see if we should strip off a partial date/time
    else {
      match = PART_DATE_TIME_PTN.matcher(desc);
      if (match.find()) desc = desc.substring(0,match.start());
    }
    
    // Strip off leading unit specs
    while (true) {
      match = DISPATCHED_PTN.matcher(desc);
      if (!match.find()) break;
      data.strUnit = append(data.strUnit, " ", match.group(1));
      desc = desc.substring(match.end());
    }
    
    data.strSupp = desc;
    return true;
  }
  
  private static final String[] CITY_LIST = new String[]{
    "CHICKAMAUGA",
    "LAFAYETTE",
    "LOOKOUT MOUNTAIN",
    "ROSSVILLE",
    "CHATTANOOGA VALLEY",
    "NOBLE",
    "FAIRVIEW",
    "FLINTSTONE",
    "KENSINGTON",
    "NAOMI",
    "DRY CREEK",
    "ROCK SPRING",
    "VILLANOW",
    "HIGH POINT"
  };
}
