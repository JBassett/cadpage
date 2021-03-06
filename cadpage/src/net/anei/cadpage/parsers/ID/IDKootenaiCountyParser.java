package net.anei.cadpage.parsers.ID;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;


public class IDKootenaiCountyParser extends FieldProgramParser {
  
  public IDKootenaiCountyParser() {
    super(CITY_CODES, "KOOTENAI COUNTY", "ID",
          "SRC CALL ADDR UNIT+? CH! INFO+");
  }
  
  @Override
  protected boolean parseMsg(String body, Data data) {
    if (body.startsWith("KOOTENAI COUNTY SHERIFF ")) {
      body = body.substring(24).trim();
    }
    int pt = body.indexOf("\nSent by CLI");
    if (pt >= 0) body = body.substring(0,pt).trim();
    
    return parseFields(body.split("\n"), 5, data);
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("ADDR")) return new  MyAddressField();
    if (name.equals("UNIT")) return new MyUnitField();
    if (name.equals("CH")) return new ChannelField("|OPS.*");
    if (name.equals("INFO")) return new MyInfoField();
    return super.getField(name);
  }
  
  private class MyAddressField extends AddressField {
    @Override
    public void parse(String field, Data data) {
      Parser p = new Parser(field);
      data.strCity = convertCodes(p.getLastOptional(','), CITY_CODES);
      parseAddress(p.get(';'), data);
      String place = p.get();
      if (place.startsWith("#")) {
        data.strApt = append(data.strApt, "-", place.substring(1).trim());
      } else {
        data.strPlace = place;
      }
    }
    
    @Override
    public String getFieldNames() {
      return "ADDR APT PLACE CITY";
    }
  }
  
  private class MyUnitField extends UnitField {
    @Override
    public void parse(String field, Data data) {
      data.strUnit = append(data.strUnit, " ", field);
    }
  }
  
  // INFO field skips anything starting with a date/time
  private static Pattern DATE_TIME_PTN = Pattern.compile("^\\d\\d:\\d\\d:\\d\\d: \\d\\d/\\d\\d/\\d\\d\\d\\d\\b");
  private static Pattern GPS_PTN = Pattern.compile("CALLBACK=([-0-9\\(\\)]+) LAT=([-+]?\\d+\\.\\d{4,}) LON=([-+]?\\d+\\.\\d{4,}) UNC=\\d+");
  private class MyInfoField extends InfoField {
    @Override
    public void parse(String field, Data data) {
      if (DATE_TIME_PTN.matcher(field).matches()) return;
      
      Matcher match = GPS_PTN.matcher(field);
      if (match.matches()) {
        data.strPhone = match.group(1);
        setGPSLoc(match.group(2)+','+match.group(3), data);
        return;
      }
      
      super.parse(field, data);
    }
    
    @Override
    public String getFieldNames() {
      return "PHONE GPS INFO";
    }
  }
  
  private static final Properties CITY_CODES = buildCodeTable(new String[]{
      "ATH", "ATHOL",
      "BA",  "BAYVIEW",
      "BLA", "BLANCHARD",
      "CDA", "COEUR D'ALENE",
      "DG",  "DALTON GARDENS",
      "FL",  "FERNAN LAKE",
      "HA",  "HAYDEN",
      "HAR", "HARRISON",
      "HAU", "HAUSER LAKE",
      "HL",  "HAYDEN LAKE",
      "KEL", "KELLOGG", 
      "MOS", "MOSCOW",
      "PF",  "POST FALLS",
      "RA",  "RATHDRUM",
      "RL",  "ROSE LAKE",
      "SL",  "SPIRIT LAKE"
  });
}
