package net.anei.cadpage.parsers.WA;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.SmartAddressParser;



public class WAYakimaCountyParser extends SmartAddressParser {
  
  private static final Pattern MASTER = 
    Pattern.compile("(\\d\\d\\.\\d\\d\\.\\d\\d) (\\d\\d/\\d\\d/\\d\\d) (.*?) ([A-Z]{2}FD|AMR|ALS)((?: +(?:[A-Z]+\\d+[A-Z]?|AOA))+)(?: +(.*))?");
  private static final Pattern APT_MARK_PTN = Pattern.compile(" +(?:APT|ROOM) +", Pattern.CASE_INSENSITIVE);
  
  public WAYakimaCountyParser() {
    super("YAKIMA COUNTY", "WA");
    setup();
    setFieldList("TIME DATE CALL ADDR PLACE SRC UNIT INFO");
  }
  
  @Override
  public String getFilter() {
    return "wwantla@ci.yakima.wa.us";
  }

  @Override
  protected boolean parseMsg(String body, Data data) {
    
    Matcher match = MASTER.matcher(body);
    if (!match.matches()) return false;
    data.strTime = match.group(1).replace('.', ':');
    data.strDate = match.group(2);
    String sAddr = match.group(3).trim();
    data.strSource = match.group(4);
    data.strUnit = match.group(5).trim();
    data.strSupp = getOptGroup(match.group(6));
    
    // Address section consists of a call, address, and possible semicolon separated place and/or apt
    Parser p = new Parser(sAddr);
    parseAddress(StartType.START_CALL, FLAG_START_FLD_REQ | FLAG_ANCHOR_END, p.get(';'), data);
    String place = p.get(';');
    match = APT_MARK_PTN.matcher(place);
    if (match.find()) {
      data.strApt = append(data.strApt, "-", place.substring(match.end()));
      data.strPlace = place.substring(0,match.start());
    }
    else if (data.strApt.length() == 0 && place.length() <= 4) {
      data.strApt = append(data.strApt, "-", place);
    } else {
      data.strPlace = place;
    }
    data.strApt = append(data.strApt, "-", p.get());
    return true;
  }

  private void setup() {
    setupCallList(
      "ACCIDENT HITRUN",
      "ACCIDENT INJURY",
      "ACCIDENT NO INJ",
      "CITIZEN ASSIST",
      "EMR ALARM MED",
      "EMR AMB",
      "EMR IFT",
      "EMR MEDIC",
      "EMR NURSE",
      "EMR RED",
      "EMR YELLOW",
      "FIRE O2QLTY",
      "FIRE AIR HEAVY",
      "FIRE AIR LIGHT",
      "FIRE AIR STANDB",
      "FIRE ALARM RES",
      "FIRE ALARM 2",
      "FIRE AUTO ALARM",
      "FIRE AUTO ALM 2",
      "FIRE BRUSH GRAS",
      "FIRE CHIMNEY",
      "FIRE EWR",
      "FIRE FW",
      "FIRE HAYSTACK",
      "FIRE HAZMAT",
      "FIRE INVEST",
      "FIRE OTHER",
      "FIRE POWER PROB",
      "FIRE RESCUE",
      "FIRE SRVC CALL",
      "FIRE STRUC COMM",
      "FIRE STRUCTURE",
      "FIRE TRASH GARB",
      "FIRE VEHICLE",
      "PAGED"
    );
  }
}
