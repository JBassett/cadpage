package net.anei.cadpage.parsers.OK;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.dispatch.DispatchA33Parser;


public class OKGarfieldCountyParser extends DispatchA33Parser {
  
  private static final Pattern INFO_DATE_TIME_PTN = Pattern.compile("^\\*\\*\\d\\d/\\d\\d/\\d{4} \\d\\d:\\d\\d:\\d\\d \\d\\*\\* *");
  
  public OKGarfieldCountyParser() {
    super("GARFIELD COUNTY", "OK", "Closed");
  }
  
  @Override
  public String getFilter() {
    return "911firedispatch@enid.org";
  }
  
  @Override
  public boolean parseMsg(String subject, String body, Data data) {
    if (!subject.equals("Call")) return false;
    int pt = body.indexOf("\nEvent No:");
    if (pt < 0) return false;
    body = body.substring(pt+1);
    if (!super.parseMsg(body, data)) return false;
    if (data.strCross.equals("OK")) data.strCross = "";
    Matcher match = INFO_DATE_TIME_PTN.matcher(data.strSupp);
    if (match.find()) data.strSupp = data.strSupp.substring(match.end());
    return true;
  }
  
  @Override
  public String adjustMapAddress(String address) {
    return HWY412_PTN.matcher(address).replaceAll("US 412");
  }
  private static final Pattern HWY412_PTN = Pattern.compile("\\bHWY *412\\b", Pattern.CASE_INSENSITIVE);
}
