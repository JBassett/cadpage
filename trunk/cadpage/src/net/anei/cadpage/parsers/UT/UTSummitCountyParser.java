package net.anei.cadpage.parsers.UT;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;



public class UTSummitCountyParser extends FieldProgramParser {
  
  public UTSummitCountyParser() {
    super("SUMMIT COUNTY", "UT",
           "SRC CALL ADDR! UNIT+? INFO+");
  }
  
  @Override
  public String getFilter() {
    return "scso911@summitcounty.org";
  }

  @Override
  protected boolean parseMsg(String body, Data data) {
    return parseFields(body.split("\n"), 4, data);
  }
  
  private static final Pattern BOUND_PTN = Pattern.compile("\\b(?:NORTH|EAST|SOUTH|WEST)BOUND\\b");
  private class MyAddressField extends AddressField {
    @Override
    public void parse(String field, Data data) {
      Parser p = new Parser(field);
      String city = convertCodes(p.getLastOptional(','), CITY_CODES);
      int pt = city.lastIndexOf(',');
      if (pt >= 0) {
        data.strState = city.substring(pt+1);
        city = city.substring(0,pt);
      }
      data.strCity = city;
      data.strCross = p.getLastOptional(';');
      String sAddr = p.get();
      if (sAddr.endsWith("@")) sAddr = sAddr.substring(0,sAddr.length()-1).trim();
      StringBuffer sb = new StringBuffer();
      Matcher match = BOUND_PTN.matcher(sAddr);
      while (match.find()) {
        match.appendReplacement(sb, "");
        sb.append(match.group().charAt(0));
        sb.append('B');
      }
      match.appendTail(sb);
      parseAddress(sb.toString(), data);
    }
    
    @Override
    public String getFieldNames() {
      return super.getFieldNames() + " X CITY ST";
    }
  }

  private class MyUnitField extends UnitField {
    public MyUnitField() {
      super("[A-Za-z]*[0-9]*");
    }
    
    @Override 
    public void parse(String field, Data data) {
      if (field.equals(data.strSource)) return;
      data.strUnit = append(data.strUnit, ",", field);
    }
  }
  
  private class MyInfoField extends InfoField {
    @Override
    public void parse(String field, Data data) {
      if (data.strCall.startsWith(field)) return;
      super.parse(field, data);
    }
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("SRC")) return new SourceField("[A-Z]{3,4}");
    if (name.equals("ADDR")) return new MyAddressField();
    if (name.equals("UNIT")) return new MyUnitField();
    if (name.equals("INFO")) return new MyInfoField();
    return super.getField(name);
  }
  
  private static final Properties CITY_CODES = buildCodeTable(new String[]{
      "COA",  "COALVILLE",
      "EVA",  "KAMAS",        // ?????
      "HEB",  "HEBER",        // Wasatch County
      "HOY",  "HOYTSVILLE",
      "KAM",  "KAMAS",
      "OAK",  "OAKLY",
      "PC",   "PARK CITY",
      "PCC",  "PARK CITY",
      "PEO",  "PEOA",
      "RKP",  "ROCKPORT"
  });
}
