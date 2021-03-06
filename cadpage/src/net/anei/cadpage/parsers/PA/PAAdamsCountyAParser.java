package net.anei.cadpage.parsers.PA;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.dispatch.DispatchA1Parser;

/**
 * Adams County, PA
 */
public class PAAdamsCountyAParser extends DispatchA1Parser {
  
  private static final Pattern IAMR_PREFIX1 = Pattern.compile("^(?:Alert: +)?(.*?)[ \n](?=ALRM LVL:|: +BOX )");
  private static final Pattern IAMR_BOX_PTN = Pattern.compile("[, ] +BOX ");
  private static final Pattern IAMR_COMMA_PTN = Pattern.compile("[ ,]*\n[ ,]*");
  
  public PAAdamsCountyAParser() {
    super("ADAMS COUNTY", "PA");
  }

  @Override
  public String getFilter() {
    return "adams911@adamscounty.us,messaging@iamresponding.com,777";
  }

  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    
    // Check for garbled prefix associated with IamResponding
    Matcher match = IAMR_PREFIX1.matcher(body);
    if (match.lookingAt()) {
      data.strSource = subject;
      subject = "Alert: " + match.group(1).trim();
      body = body.substring(match.end()).trim();
      if (body.startsWith(":")) {
        body = "RUN CARD:" + body.substring(1);
      } else {
        body = IAMR_BOX_PTN.matcher(body).replaceFirst(", RUN CARD: BOX ");
      }
      body = IAMR_COMMA_PTN.matcher(body).replaceAll("\n");
      body = body.replaceAll(" , ", " ");
    }
    
    if (!super.parseMsg(subject, body, data)) return false;
    String city = data.strCity;
    if (city.toUpperCase().endsWith(" BORO")) {
      data.strCity = city.substring(0,city.length()-5).trim();
    } else {
      if (city.endsWith(" CO")) data.strCity = city = city + "UNTY";
      if (city.equals("WASHINGTON COUNTY") || 
          city.equals("CARROLL COUNTY") ||
          city.equals("FREDERICK COUNTY")) data.strState = "MD";
    }
    data.strSupp = data.strSupp.replace(" / ", "\n");
    return true;
  }
  
  @Override
  public String getProgram() {
    return "SRC " + super.getProgram().replace("CITY", "CITY ST");
  }
}
