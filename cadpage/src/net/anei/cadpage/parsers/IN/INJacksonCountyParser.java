package net.anei.cadpage.parsers.IN;

import net.anei.cadpage.parsers.SmartAddressParser;
import net.anei.cadpage.parsers.MsgInfo.Data;


public class INJacksonCountyParser extends SmartAddressParser {
  
  public INJacksonCountyParser() {
    super("JACKSON COUNTY", "IN");
  }
  
  @Override
  public String getFilter() {
    return "@injacso.com";
  }
  
  @Override
  public boolean parseMsg(String subject, String body, Data data) {
    do {
      if (subject.endsWith("FD")) {
        data.strSource = subject;
        break;
      }
      if (subject.contains("medical")) break;
    } while (false);
    
    parseAddress(StartType.START_CALL, body, data);
    String sExtra = getLeft();
    if (data.strCall.length() == 0) {
      data.strCall = sExtra;
    } else {
      data.strSupp = sExtra;
    }
    return true;
  }
}
