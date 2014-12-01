package net.anei.cadpage.parsers.LA;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.SmartAddressParser;

/**
 * Beauregard Parish, LA
 */
public class LABeauregardParishParser extends SmartAddressParser {
  
  private static final Pattern MASTER = Pattern.compile("at: (\\d\\d?/\\d\\d?/\\d{4}) (\\d\\d?:\\d\\d:\\d\\d [AP]M)\\n\\n(.*)", Pattern.DOTALL); 
  private static final DateFormat TIME_FMT = new SimpleDateFormat("hh:mm:ss aa");
  
  private static final Pattern BETWEEN_AND_PTN = Pattern.compile("\\bBETWEEN +(\\d+) +AND +(\\d+) +");
  private static final Pattern LINE_BREAK_PTN = Pattern.compile(" *\\n+ *");
  
  public LABeauregardParishParser() {
    super("BEAUREGARD PARISH", "LA");
    setFieldList("SRC DATE TIME CALL ADDR APT INFO");
  }
  
  @Override
  public String getFilter() {
    return "CAD ALERTS";
  }
  
  @Override
  public boolean parseMsg(String subject, String body, Data data) {
    
    if (subject.length() == 0 || subject.contains(" ")) return false;
    data.strSource = subject;
    
    Matcher match = MASTER.matcher(body);
    if (!match.matches()) return false;
    data.strDate = match.group(1);
    setTime(TIME_FMT, match.group(2), data);
    body = match.group(3).trim();
    
    body = LINE_BREAK_PTN.matcher(body).replaceAll(" ");
    body = BETWEEN_AND_PTN.matcher(body).replaceAll("$1-$2 ");
    body = body.replace(" INTERSECTION OF ", " @ ");
    parseAddress(StartType.START_CALL, body, data);
    if (data.strAddress.length() == 0) return false;
    String info = getLeft();
    if (info.startsWith(",") || info.startsWith("-")) info = info.substring(1).trim();
    if (data.strCall.length() == 0) {
      data.strCall = info;
    }else {
      if (data.strCall.endsWith(",") || data.strCall.endsWith("-")) {
        data.strCall = data.strCall.substring(0,data.strCall.length()-1).trim();
      }
      data.strSupp = info;
    }
    
    return true;
  }
}
