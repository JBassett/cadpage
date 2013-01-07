package net.anei.cadpage.parsers.GA;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.SmartAddressParser;
import net.anei.cadpage.parsers.MsgInfo.Data;


public class GAWhitfieldCountyParser extends SmartAddressParser {

  private static final Pattern MARKER = Pattern.compile("^WHITFIELD CO\\. 911:|WHITFIELD_CO_911:");
  private static final Pattern PHONE_PTN = Pattern.compile("\\b\\d{10}\\b");
  
  public GAWhitfieldCountyParser() {
    super("WHITFIELD COUNTY", "GA");
  }
  
  @Override
  public String getFilter() {
    return "777";
  }
  
  @Override
  public boolean parseMsg(String body, Data data) {
    Matcher match = MARKER.matcher(body);
    if (!match.find()) return false;
    body = body.substring(match.end()).trim();
    
    data.strCall = "";
    for (String call : CALL_LIST) {
      if (body.startsWith(call)) {
        data.strCall = call.trim();
        body = body.substring(call.length());
        break;
      }
    }
    match = PHONE_PTN.matcher(body);
    if (match.find()) {
      data.strPhone = match.group();
      String sAddr = body.substring(0,match.start()).trim();
      sAddr = sAddr.replace('@', '&');
      if (data.strCall.length() != 0) {
        parseAddress(sAddr, data);
      } else {
        parseAddress(StartType.START_CALL, FLAG_ANCHOR_END, sAddr, data);
        data.strPhone = match.group();
      }
      data.strSupp = body.substring(match.end()).trim();
    } else {
      StartType startType = data.strCall.length() == 0 ? StartType.START_ADDR : StartType.START_CALL;
      parseAddress(startType, body, data);
      data.strSupp = getLeft();
    }
    
    return true;
  }
  
  private static final String[] CALL_LIST = new String[]{
    "50I M.V. ACCIDENT W/INJURIES ",
    "F12 SF-COMMERCIAL/INDUSTRIAL/SCHOO ",
    "F15 FIRE ALARM ",
    "F21 VEHICLE FIRE - CMV "
  };

}
