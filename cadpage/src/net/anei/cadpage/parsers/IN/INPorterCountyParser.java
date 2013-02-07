package net.anei.cadpage.parsers.IN;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;

public class INPorterCountyParser extends FieldProgramParser {
  
  private static final Pattern MISSING_LF_PTN = Pattern.compile("(?<=[^\n])(?=GRP:)");
  
  public INPorterCountyParser() {
    super("PORTER COUNTY", "IN",
           "ID? UNIT ( ADDR1/Z ADDR2 | ADDR3! ) CROSS:X? GRP:SRC? PRI:PRI comment:INFO");
  }
  
  @Override
  public int getMapFlags() {
    return MAP_FLG_ADD_DEFAULT_CNTY; 
  }

  @Override
  public boolean parseMsg(String subject, String body, Data data) {
    do {
      if (subject.equals("CAD Page")) break;
      if (subject.endsWith("FS")) {
        data.strSource = subject;
        break;
      }
      return false;
    } while (false);
    
    body = MISSING_LF_PTN.matcher(body).replaceAll("\n");
    if (!parseFields(body.split("\n"), 4, data)) return false;
    String sAddr = data.strAddress;
    data.strAddress = "";
    parseAddress(sAddr, data);
    return true;
  }
  
  @Override
  public String getProgram() {
    return ("SRC " + super.getProgram());
  }
  
  private static final Pattern ID_PTN = Pattern.compile("#(\\d{9}) -");
  private class MyIdField extends IdField {
    
    @Override
    public boolean canFail() {
      return true;
    }
    
    @Override
    public boolean checkParse(String field, Data data) {
      Matcher match = ID_PTN.matcher(field);
      if (!match.matches()) return false;
      super.parse(match.group(1), data);
      return true;
    }
    
    @Override
    public void parse(String field, Data data) {
      if (!checkParse(field, data)) abort();
    }
  }
  
  private class MyUnitField extends UnitField {
    @Override
    public void parse(String field, Data data) {
      if (field.endsWith(":")) field = field.substring(0,field.length()-1).trim();
      super.parse(field, data);
    }
  }
  
  /*
   * Address field comes in three flavors, all of which can determine their state
   * type 1 = call @ place,city
   * type 2 = - at address,city
   * type 3 = call @ address, city
   */
  private class MyAddressField extends AddressField {
    
    private int type;
    
    public MyAddressField(int type) {
      this.type = type;
    }
    
    @Override
    public boolean checkParse(String field, Data data) {
      if (type == 2) {
        if (!field.startsWith("- at ")) return false; 
        field = field.substring(5).trim();
      }
      
      else {
        int pt1 = field.lastIndexOf('@');
        if (pt1 < 0) return false;
        data.strCall = field.substring(0,pt1).trim();
        field = field.substring(pt1+1).trim();
      }
      
      int pt = field.lastIndexOf(',');
      if (pt >= 0) {
        String sCity = field.substring(pt+1).trim();
        field = field.substring(0,pt).trim();
        if (data.strCity.length() == 0) {
          pt = sCity.indexOf('-');
          if (pt >= 0) sCity = sCity.substring(0,pt).trim();
          if (sCity.equals("WNT")) data.defCity = "LAKE COUNTY";
          data.strCity = convertCodes(sCity, CITY_CODES);
        }
      }
      
      if (type == 1) {
        data.strPlace = field;
      }
      
      else {
        super.parse(field, data);
      }
      return true;
    }
    
    @Override
    public void parse(String field, Data data) {
      if (!checkParse(field, data)) abort();
    }
    
    @Override 
    public String getFieldNames() {
      switch (type) {
      case 1:
        return "CALL PLACE CITY";
      case 2:
        return super.getFieldNames() + " CITY";
      case 3:
        return "CALL " + super.getFieldNames() + " CITY";
      default:
        return null;
      }
    }
  }
  
  private class MyCrossField extends CrossField {
    @Override
    public void parse(String field, Data data) {
      if (field.startsWith("btwn ")) field = field.substring(5).trim();
      super.parse(field, data);
    }
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("ID")) return new MyIdField();
    if (name.equals("UNIT")) return new MyUnitField();
    if (name.equals("ADDR1")) return new MyAddressField(1);
    if (name.equals("ADDR2")) return new MyAddressField(2);
    if (name.equals("ADDR3")) return new MyAddressField(3);
    if (name.equals("X")) return new MyCrossField();
    return super.getField(name);
  }
  
  private static final Properties CITY_CODES = buildCodeTable(new String[]{
      "BHB", "Burns Harbor",
      "BSH", "Beverly Shores",
      "CHE", "Chesterton",
      "DAC", "Dune Acres",
      "HEB", "Hebron",
      "KTS", "Kouts",
      "OGD", "Ogden Dunes",
      "PTG", "Portage",
      "PTR", "Porter",
      "VAL", "Valparaiso",
              
      "BNT", "Boone Twp",
      "CCT", "Center Twp",
      "CTT", "Center Twp",
      "ECT", "Eagle Creek Twp",
      "JKT", "Jackson Twp",
      "LBT", "Liberty Twp",
      "MGT", "Morgan Twp",
      "PGT", "Portage Twp",
      "PLT", "Pleasant Twp",
      "PNT", "Pine Twp",
      "POT", "Porter Twp",
      "UNT", "Union Twp",
      "WCT", "Westchester Twp",
      "WGT", "Washington Twp",
      "WNT", "Winfield Twp",  // Lake County
  });
}
