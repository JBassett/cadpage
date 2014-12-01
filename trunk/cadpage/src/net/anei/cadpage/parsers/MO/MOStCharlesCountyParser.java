package net.anei.cadpage.parsers.MO;


import java.util.Properties;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;



public class MOStCharlesCountyParser extends FieldProgramParser {
  
  private static final Pattern ID_PTN = Pattern.compile("\\d{6}-\\d{5}");
 
  public MOStCharlesCountyParser() {
    super("ST CHARLES COUNTY", "MO",
          "ADDR! APT:APT! BUS:PLACE! FD:CITY! CHL:CH! Units:UNIT!");
  }
  
  @Override
  public String getFilter() {
    return "dispatch@sccda.org,dispatch@sccmo.org";
  }
  
  @Override
  public boolean parseMsg(String body, Data data) {
    
    // Dispatch sends a fixed length field format and a variable lenght field format.
    // Try the variable format first
    if (super.parseMsg(body, data)) return true;
    
    // Otherwise reset things and try the fixed field format
    data.initialize(this);
    setFieldList("ID INFO CALL ADDR PLACE APT X MAP CH SRC UNIT");
    
    FParser p = new FParser(body);
    
    // Skip optional ID: label
    p.check("ID:");
    
    data.strCallId = p.get(12);
    if (! ID_PTN.matcher(data.strCallId).matches()) return false;

    data.strSupp = p.get(8);
    data.strCall = p.get(25);;
    parseAddress(p.get(50), data);
    data.strPlace = p.get(25);

    p.setOptional();
    if (!p.check("APT:")) return false;
    data.strApt = p.getOptional("X ST:", 5, 16);
    if (data.strApt == null) return false;
    data.strCross = p.get(30);
    
    if (!p.check("MAP:")) return false;
    String map = p.getOptional("CHL:", 10);
    if (map == null) map = p.getOptional("CH:", 10);
    if (map != null) {
      data.strMap = map;
      data.strChannel = p.get(5);
    } else {
      data.strMap = p.get(15);
    }
    data.strSource = p.get(30);
    
    p.check("Units:");
    data.strUnit = p.get();
    
    return true;
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("ADDR")) return new MyAddressField();
    return super.getField(name);
  }
  
  private class MyAddressField extends AddressField {
    @Override
    public void parse(String field, Data data) {
      if (field.startsWith("Comment:")) {
        int pt = field.indexOf(',', 8);
        if (pt < 0) abort();
        data.strSupp = field.substring(8, pt).trim();
        field = field.substring(pt+1).trim();
      }
      int pt = field.indexOf(" AT ");
      if (pt < 0) abort();
      data.strCall = field.substring(0,pt).trim();
      super.parse(field.substring(pt+4).trim(), data);
    }
    
    @Override
    public String getFieldNames() {
      return "INFO CALL " + super.getFieldNames();
    }
  }
  
  @Override
  public String adjustMapCity(String city) {
    String tmp = MAP_CITY_TABLE.getProperty(city.toUpperCase());
    if (tmp != null) city = tmp;
    return super.adjustMapCity(city);
  }
  private static final Properties MAP_CITY_TABLE = buildCodeTable(new String[]{
      "CENTRAL COUNTY",     "St Peters",
      "DISPATCH.",          "",
      "RIVERS POINTE",      ""
  });
}