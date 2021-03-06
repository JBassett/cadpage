package net.anei.cadpage.parsers.VA;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.CodeSet;
import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.SmartAddressParser;



public class VARoanokeCountyParser extends SmartAddressParser {
  
  private static final Pattern MASTER_PTN1 = Pattern.compile("(.*?)  (\\d{4}) (.*)(Roanoke County|Floyd County|Town of Vinton) ([ A-Z]+) (\\d{4} \\d{8})");
  private static final Pattern NOT_DISPATCH_PTN = Pattern.compile("\\b(?:ADV|TRAINING)\\b");
  private static final Pattern DATE_TIME_PTN1 = Pattern.compile(" (\\d{1,2}/\\d{1,2}/\\d{4}) +(\\d{1,2}:\\d{1,2}:\\d{1,2} [AP]M)$");
  private static final Pattern DATE_TIME_PTN2 = Pattern.compile(" (\\d\\d?-[A-Z]{3}-\\d{4}) +(\\d{1,2}:\\d{1,2}:\\d{1,2})$", Pattern.CASE_INSENSITIVE);
  private static final DateFormat TIME_FMT1 = new SimpleDateFormat("hh:mm:ss aa");
  private static final DateFormat DATE_FMT2 = new SimpleDateFormat("dd-MMM-yyyy");
  private static final Pattern XST_PTN = Pattern.compile("[- ]+X ?ST(?:REETS?)?\\b *");
  private static final Pattern X_APT_PTN = Pattern.compile("(?:APT|RM|(FL|LOT)) *([^ ]+)\\b *(.*)", Pattern.CASE_INSENSITIVE);
  private static final Pattern X_NO_CROSS_PTN = Pattern.compile("(.*?) *\\bNo Cross Streets Found\\b *(.*)");
  private static final Pattern UNIT_PTN = Pattern.compile("^((?:[A-Z]+\\d+|SALEMEMS|SALEMF|RES(?:CUE)?[- ]\\d+)(?:\\$[A-Z]+\\d+)? +)+");
  private static final Pattern X_PHONE_PTN = Pattern.compile("((?:\\(?\\d{3}\\)? ?)?\\d{3}[- ]\\d{4})\\b *(.*)");
  private static final Pattern APT_PHONE_PTN = Pattern.compile("\\b(?:\\(?\\d{3}\\)? ?)?\\d{3}[- ]\\d{4}\\b");
  private static final Pattern APT_PTN = Pattern.compile("([A-Z]{2}|[A-Z]?\\d+[A-Z]?)\\b *(.*)");
    
  
  public VARoanokeCountyParser() {
    super("ROANOKE COUNTY", "VA");
    setupCallList(CALL_LIST);
    setupMultiWordStreets(
        "AUTUMN PARK",
        "AMERICAN TIRE",
        "BACK CREEK ORCHARD",
        "BENT MOUNTAIN",
        "BROAD HILL",
        "CARDINAL PARK",
        "CARRIAGE HILLS",
        "CHARING CROSS",
        "CIRCLE BROOK",
        "CLEARBROOK VILLAGE",
        "CLOVER HILL",
        "COTTON HILL",
        "COUNTRY HOMES",
        "DEER RIDGE",
        "DREWRYS HILL",
        "EAST VIRGINIA",
        "ELM VIEW",
        "FAIRWAY FOREST",
        "FEATHER GARDEN",
        "FORTUNE RIDGE",
        "GLEN HEATHER",
        "GREEN MEADOW",
        "GREEN VALLEY",
        "HIDDEN HILL",
        "HIDDEN VALLEY",
        "HYDE PARK",
        "INDIAN GRAVE",
        "IVY RIDGE",
        "LOCH HAVEN",
        "LOST MOUNTAIN",
        "LOST VIEW",
        "LOUISE WELLS",
        "LYNNN HAVEN",
        "MANSARD SQUARE",
        "MARTINS CREEK",
        "MCVITTY FOREST",
        "MEWS HILL",
        "MOCKINGBIRD HILL",
        "MOUNTAIN VIEW",
        "MT CHESTNUT",
        "NORTHSIDE HIGH SCHOOL",
        "OLD CAVE SPRING",
        "OLD MILL PLANTATION",
        "PENN FOREST",
        "PETERS CREEK",
        "PLEASANT HILL",
        "POAGE VALLEY RD",
        "POPLAR SPRINGS",
        "RAMSEY BLVD",
        "RAN LYNN",
        "RED LANE",
        "RIDGELEA ESTATES",
        "SOUTH PACIFIC",
        "SOUTH PEAK",
        "SOUTH ROSELAWN",
        "SPRING GROVE",
        "SPRING MEADOW",
        "SUNNY SIDE",
        "TANGLEWOOD WEST ENTRANCE",
        "TWELVE OCLOCK KNOB",
        "VALE HILL",
        "VALLEY FORGE",
        "WEST RIVER",
        "WEST RURITAN",
        "WEST VIRGINIA",
        "YELLOW MOUNTAIN"
    );
  }
  
  @Override
  public String adjustMapAddress(String address) {
    address = GRANDIN_ROAD_EXT.matcher(address).replaceAll("$1 EXD");
    return super.adjustMapAddress(address);
  }
  private static final Pattern GRANDIN_ROAD_EXT = Pattern.compile("\\b(GRANDIN ROAD) EXT\\b", Pattern.CASE_INSENSITIVE);

  @Override
  protected boolean parseMsg(String body, Data data) {
    
    // There seem to be two different formats, possibly separated chronologically
    // This one can be identified by a pattern match
    Matcher match = MASTER_PTN1.matcher(body);
    if (match.matches()) {
      setFieldList("UNIT BOX ADDR APT CITY CALL ID");
      data.strUnit = match.group(1).trim();
      data.strBox = match.group(2);
      parseAddress(match.group(3).trim(), data);
      String city = match.group(4);
      if (city.startsWith("Town of ")) city = city.substring(8).trim();
      data.strCity = city;
      data.strCall = match.group(5).trim();
      data.strCallId = match.group(6);
      return true;
    }
    
    // Second format tends to catch a lot of things that really are not
    // dispatch pages.  We will look for some keywords that indicate this
    // is not a dispatch alert
    if (NOT_DISPATCH_PTN.matcher(body).find()) return false;
    
    setFieldList("UNIT CALL PLACE ADDR APT PHONE INFO X DATE TIME");
    boolean good = false;
    match = DATE_TIME_PTN1.matcher(body);
    if (match.find()) {
      data.strDate = match.group(1);
      setTime(TIME_FMT1, match.group(2), data);
      body = body.substring(0,match.start()).trim();
      good = true;
    }
    else if ((match = DATE_TIME_PTN2.matcher(body)).find()) {
      setDate(DATE_FMT2, match.group(1), data);
      data.strTime = match.group(2);
      body = body.substring(0,match.start()).trim();
      good = true;
    }
    
    String bodyUpsh = body.toUpperCase();
    match = XST_PTN.matcher(bodyUpsh);
    if (match.find()) {
      String cross  = body.substring(match.end());
      body = body.substring(0,match.start());
      good = true;
      
      match = X_APT_PTN.matcher(cross);
      if (match.matches()) {
        data.strApt = append(getOptGroup(match.group(1)), " ", match.group(2));
        cross = match.group(3);
      }
      match = X_PHONE_PTN.matcher(cross);
      if (match.matches()) {
        data.strPhone = match.group(1);
        cross = match.group(2);
      }
      match = X_NO_CROSS_PTN.matcher(cross);
      if (match.matches()) {
        data.strApt = append(data.strApt, "-", match.group(1));
        data.strPlace = append(data.strPlace, " - ", match.group(2));
      } else { 
        cross = cross.replaceAll("  +", " / ");
        parseAddress(StartType.START_ADDR, FLAG_ONLY_CROSS | FLAG_IMPLIED_INTERSECT, cross, data);
        String left = getLeft();
        left = stripFieldStart(left, "-");
        if (left.startsWith("/")) {
          left = left.substring(1).trim();
          String saveCross = data.strCross;
          data.strCross = "";
          parseAddress(StartType.START_ADDR, FLAG_ONLY_CROSS, left, data);
          data.strCross = append(saveCross, " / ", data.strCross);
          data.strPlace = append(data.strPlace, " - ", getLeft());
        } else if (isValidAddress(left)) {
            data.strCross = append(data.strCross, " / ", left);
        } else  { 
          data.strPlace = append(data.strPlace, " - ", left);
        }
      }
    }
    
    match = UNIT_PTN.matcher(body);
    if (match.find()) {
      data.strUnit = match.group().trim();
      body = body.substring(match.end()).trim();
      good = true;
    }
    
    parseAddress(StartType.START_CALL_PLACE, FLAG_START_FLD_REQ | FLAG_IGNORE_AT | FLAG_START_FLD_NO_DELIM, body.toUpperCase(), data);
    if (data.strAddress.length() == 0) {
      if (data.strPlace.length() == 0) return false;
      parseAddress(data.strPlace, data);
      data.strPlace = "";
    }
    if (!good && CALL_LIST.getCode(data.strCall, true) == null) return false;
    data.strPlace = stripFieldStart(data.strPlace, "(");
    data.strPlace = stripFieldStart(data.strPlace, ",");
    data.strPlace = stripFieldEnd(data.strPlace, "-");
    String left = getLeft();
    left = stripFieldStart(left, "-");
    if ((match = APT_PHONE_PTN.matcher(left)).find()) {
      data.strApt = append(data.strApt, "-", left.substring(0,match.start()).trim());
      data.strPhone = match.group(0);
      data.strSupp = left.substring(match.end()).trim();
    }
    else if ((match = APT_PTN.matcher(left)).matches()) {
      data.strApt = append(data.strApt, "-", match.group(1));
      data.strSupp = match.group(2);
    } else {
      data.strSupp = left;
    }
    
    // See if we should split a place name from the call description
    if (data.strPlace.length() == 0) {
      int pt = data.strCall.indexOf('(');
      if (pt >= 0) {
        data.strPlace = data.strCall.substring(pt+1).trim();
        if (data.strPlace.endsWith(")")) data.strPlace = data.strPlace.substring(0,data.strPlace.length()-1).trim(); 
        data.strCall = data.strCall.substring(0,pt).trim();
      }
    }
    return true;
  }
  
  private static final CodeSet CALL_LIST = new CodeSet(
      "ACC  INJ",
      "ACC INJ",
      "ACCIDENT",
      "ACCIDENT MINOR INJURY",
      "ACCIDENT MINOR INJURY PD ONSCENE",
      "ACCIDENT PERSONAL INJURY",
      "ALARMC",
      "ALARMR",
      "ALS",
      "ALS UPGRADED ALSC",
      "ALS-CHEST PAINS",
      "ALSC",
      "ALS CRITICAL",
      "ASSISTF",
      "ASSISTR",
      "BLS",
      "BRUSH",
      "BRUSH FIRE",
      "CARBON",
      "CARBON MONOXIDE LEAK",
      "CHIMNEY FIRE",
      "CLSC",
      "CODE BLUE",
      "COMMERCIAL FIRE ALARM",
      "COMMERCIAL GAS LEAK",
      "COMMERCIAL STRUCTURE FIRE",
      "DUMPSTER",
      "ELEVATOR",
      "EMS SERVICE CALL",
      "ESERVICE",
      "FIRE ALARM",
      "FIRE SERVICE CALL",
      "FSERVICE",
      "GASC",
      "GASR",
      "HAZMAT",
      "HAZMAT RESPONSE",
      "HIT & RUN PERSONAL INJURY",
      "ILLEGAL BURN",
      "ILLEGALBURN",
      "RESIDENTIAL FIRE ALARM",
      "RESIDENTIAL GAS LEAK",
      "RESIDENTIAL STRUCTURE FIRE",
      "SECOND EMERGENCY CALL ALS",
      "SERVICE CALL",
      "SMOKE REPORT",
      "STANDBY",
      "STRUCTC",
      "STRUCTR",
      "TEST CALL",
      "TEST STATION 2",
      "TEST STATION 3",
      "THEFT",
      "TREE ON A LINE",
      "UPGRADED ALSC",
      "VEHICLE",
      "VEHICLE FIRE",
      "WIRE DOWN",
      "WIREDOWN"
  );
}
