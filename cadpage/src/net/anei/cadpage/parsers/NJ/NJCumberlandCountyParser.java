package net.anei.cadpage.parsers.NJ;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.SmsMsgInfo.Data;
import net.anei.cadpage.parsers.FieldProgramParser;


/*
Cumberland County, NJ
Contact: Ryan Feaster <feaster.ryan@gmail.com>

E911:E22_INVEST_(VINELAND CITY)1763 CHEROKEE LA_2011-04-24 19:02:46_
E911:E2902_STRUCTURE_(GREENWICH TWP)176 BAYSIDE RD_2011-04-24 17:27:45_AREA OF THE BAY
E911:E22_COMM ALARM_(VINELAND CITY)CARNIVAL DISCOUNT LIQUORS / 4039 S MAIN RD_2011-04-24 12:27:19_
E911:29R-FA_BLEEDING_(DEERFIELD TWP)246 KENYON AV/SHINDLE RES_2011-05-02 10:25:55_
E911:29R-FA_BLEEDING_(DEERFIELD TWP)CUMBERLAND COUNTY TEC / 601 BRIDGETON AV_2011-05-02 09:27:47_NURSING OFFICE
E911:E21_VEHICLE_(VINELAND CITY)S DELSEA DR & W ELMER RD_2011-05-01 18:46:26_
E911:L2_COMM ALARM_(VINELAND CITY)COURTYARD SCHOOL INC / 1270 S EAST AVE_2011-05-01 17:03:36_
E911:B2_UNKNOWNF_(VINELAND CITY)1775 ROOSEVELT BLVD / BEHIND_2011-05-01 00:10:13_
E911:E2901_MVC_(DEERFIELD TWP)BIG OAK RD & MORTON AV_2011-04-30 00:06:19_

*/

public class NJCumberlandCountyParser extends FieldProgramParser {
  
  
  public NJCumberlandCountyParser() {
    super("CUMBERLAND COUNTY", "NJ",
           "CALL ADDR SKIP NAME");
  }
  
  @Override
  public boolean parseMsg(String subject, String body, Data data) {
    if (!body.startsWith("E911:")) return false;
    body = body.substring(5).trim();
    return parseFields(body.split("_"), data);
  }
  
  private static final Pattern ADDR_PTN = Pattern.compile("^\\((.*)\\)");
  private class MyAddressField extends AddressField {

    @Override
    public void parse(String field, Data data) {
      Matcher match = ADDR_PTN.matcher(field);
      if (match.find()) {
        data.strCity = match.group(1);
        field = field.substring(match.end()).trim();
      }
      super.parse(field, data);
    }
    
    @Override
    public String getFieldNames() {
      return "CITY ADDR"; 
    }
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("ADDR")) return new MyAddressField();
    return super.getField(name);
  }
}
