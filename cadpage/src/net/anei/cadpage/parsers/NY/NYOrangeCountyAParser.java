package net.anei.cadpage.parsers.NY;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;



public class NYOrangeCountyAParser extends FieldProgramParser {
  
  private static final Pattern KEYWORD_PTN = Pattern.compile("(?<! ) [A-Z]+[0-9]*:");
  
  public NYOrangeCountyAParser() {
    super(CITY_LIST, "ORANGE COUNTY", "NY",
           "ID? CALL ADDR CITY? INFO+ LOCATION:PLACE? TIME:TIME% XST:X XST2:X");
  }
  
  @Override
  public String getFilter() {
    return "messaging@iamresponding.com,777";
  }
  
  @Override
  public boolean parseMsg(String subject, String body, Data data) {
    if (subject.length() == 0) return false;
    data.strSource = subject;
    
    int pt = body.indexOf('\n');
    if (pt >= 0) body = body.substring(0,pt).trim();
    
    body = KEYWORD_PTN.matcher(body).replaceAll(" $0");
    String[] flds = body.split("  +");
    if (!parseFields(flds, 3, data)) return false;
    return data.strCity.length() > 0 || data.strTime.length() > 0;
  }
  
  @Override
  public String getProgram() {
    return "SRC " + super.getProgram();
  }
  
  private class MyInfoField extends InfoField {
    @Override
    public void parse(String field, Data data) {
      if (field.startsWith("INCIDENT CLONED FROM")) return;
      if (field.startsWith("PARENT:")) return;
      super.parse(field, data);
    }
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("ID")) return new IdField("F\\d{9,}|\\d{5,}");
    if (name.equals("INFO")) return new MyInfoField();
    return super.getField(name);
  }
  
  private static final String[] CITY_LIST = new String[]{
      "MIDDLETOWN",
      "NEWBURGH",
      "PORT JERVIS",

      "CHESTER",
      "CORNWALL ON HUDSON",
      "FLORIDA",
      "GOSHEN",
      "GREENWOOD LAKE",
      "HARRIMAN",
      "HIGHLAND FALLS",
      "KIRYAS JOEL",
      "MAYBROOK",
      "MONROE",
      "MONTGOMERY",
      "OTISVILLE",
      "SOUTH BLOOMING GROVE",
      "TUXEDO PARK",
      "UNIONVILLE",
      "WALDEN",
      "WARWICK",
      "WASHINGTONVILLE",
      "WOODBURY",

      "BLOOMING GROVE",
      "CHESTER",
      "CORNWALL",
      "CRAWFORD",
      "DEERPARK",
      "GOSHEN",
      "GREENVILLE",
      "HAMPTONBURGH",
      "HIGHLANDS",
      "MINISINK",
      "MONROE",
      "MONTGOMERY",
      "MOUNT HOPE",
      "NEW WINDSOR",
      "NEWBURGH",
      "TUXEDO",
      "WALLKILL",
      "WARWICK",
      "WAWAYANDA",
      "WOODBURY",
  };
}
