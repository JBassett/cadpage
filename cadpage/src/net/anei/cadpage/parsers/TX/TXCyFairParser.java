package net.anei.cadpage.parsers.TX;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;

/**
 * Cy Creek Comm Center
 */
public class TXCyFairParser extends FieldProgramParser {
  
  private static final Pattern RUN_REPORT_MARKER = Pattern.compile(" [A-Z]{4,5}\\d{10} ");
  
  public TXCyFairParser() {
    super("HARRIS", "TX",
           "UNIT CALL ADDR/SXx! X2 PLACE SUB:CITY! MAP:MAP UNITS_ASSIGNED:UNIT");
  }
  
  @Override
  public String getFilter() {
    return "CAD@CYFAIRVFD.ORG";
  }

  @Override
  public String getLocName() {
    return "Cypress-Harris, TX";
  }
  
  @Override
  public int getMapFlags() {
    return MAP_FLG_SUPPR_LA;
  }

  @Override
  protected boolean parseMsg(String body, Data data) {
    
    if (RUN_REPORT_MARKER.matcher(body).find()) {
      data.strCall = "RUN REPORT";
      data.strPlace = body;
      return true;
    }
    if (!parseFields(body.split("/"), data)) return false;
    
    String city = CITY_PLACE_TABLE.getProperty(data.strCity.toUpperCase());
    if (city != null) {
      data.strPlace = append(data.strCity, " - ", data.strPlace);
      data.strCity = city;
    }
    return true;
  }
  
  private static final Pattern CODE_PTN = Pattern.compile("^(\\d{1,2}[A-Z]\\d{2}[A-Z]?) +");
  private class MyCallField extends CallField {
    @Override
    public void parse(String field, Data data) {
      Matcher match = CODE_PTN.matcher(field);
      if (match.find()) {
        data.strCode = match.group(1);
        field = field.substring(match.end());
      }
      super.parse(field, data);
    }
    
    @Override
    public String getFieldNames() {
      return "CODE CALL";
    }
  }
  
  private static final String NO_X_FOUND = "No X Street Found";
  private class MyAddressField extends AddressField {
    @Override
    public void parse(String field, Data data) {
      
      field = field.replace("SANDHILLGLEN", "SAND HILL GLEN DR");
      if (field.endsWith(NO_X_FOUND)) {
        parseAddress(field.substring(0,field.length()-NO_X_FOUND.length()).trim(), data);
        return;
      }
      parseAddress(StartType.START_ADDR, FLAG_CROSS_FOLLOWS, field, data);
      String cross = getLeft();
      if (cross.startsWith("&")) cross = cross.substring(1).trim();
      data.strCross = cross;
      if (data.strCross.length() > 0) return;
      
    }
    
    @Override
    public String getFieldNames() {
      return "ADDR X";
    }
  }
  
  private class MyCross2Field extends CrossField {
    @Override
    public void parse(String field, Data data) {
      if (field.equals(NO_X_FOUND)) return;
      String saveCross = data.strCross;
      data.strCross = "";
      parseAddress(StartType.START_ADDR, FLAG_ONLY_CROSS, field, data);
      data.strCross = append(saveCross, " & ", data.strCross);
      data.strApt = getLeft();
    }
    
    @Override
    public String getFieldNames() {
      return "X APT";
    }
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("CALL")) return new MyCallField();
    if (name.equals("ADDR")) return new MyAddressField();
    if (name.equals("X2")) return new MyCross2Field();
    return super.getField(name);
  }
  
  private static final Properties CITY_PLACE_TABLE = buildCodeTable(new String[]{
      "FAIRFIELD",         "",
      "MUELLER CEMETERY",  ""
  });
}
