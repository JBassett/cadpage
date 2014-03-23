package net.anei.cadpage.parsers.FL;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;


public class FLManateeCountyParser extends FieldProgramParser {
  
  public FLManateeCountyParser() {
    super(CITY_CODES, "MANATEE COUNTY", "FL",
        "Location:ADDR/S? Estimated_Address:PLACE? TYPE_CODE:CALL! INSIDE_LOCATION:LOC? SUB_TYPE:INFO TIME:TIME% MAP_GRID:MAP");
  }

  @Override
  public String getFilter() {
    return "93001,777,888";
  }
  
  @Override
  public boolean parseMsg(String body, Data data) {
    body = body.replaceAll("\\s+", " ").replace("SUB TYPE:", " SUB TYPE:");
    body = body.replace("Estimated Address ", "Estimated Address:");
    if (!super.parseMsg(body, data)) return false;
    if (data.strAddress.length() == 0) return false;
    return true;
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("ADDR")) return new MyAddressField();
    if (name.equals("PLACE")) return new MyPlaceField();
    if (name.equals("LOC")) return new MyLocField();
    return super.getField(name);
  }
  
  private class MyAddressField extends AddressField {
    @Override
    public void parse(String field, Data data) {
      
      Parser p = new Parser(field);
      data.strPlace = p.getLastOptional(": @");
      if (data.strPlace.length() == 0) data.strPlace = p.getLastOptional(':');
      String apt = p.getLastOptional(',');
      super.parse(p.get(), data);
      data.strApt = append(data.strApt, "-", apt);
    }
    
    @Override
    public String getFieldNames() {
      return super.getFieldNames() + " PLACE";
    }
  }
  
  private class MyPlaceField extends PlaceField {
    @Override
    public void parse(String field, Data data) {
      
      // Remove leading EST
      if (field.startsWith("EST ")) field = field.substring(4).trim();
      if (field.equals("EST")) return;
      if (field.startsWith("@")) field = field.substring(1).trim();
      
      // If there was no location field, use this
      if (data.strAddress.length() == 0) {
        parseAddress(StartType.START_ADDR, FLAG_ANCHOR_END, field, data);
      }
      
      // Otherwise this is a normal place field
      else {
        super.parse(field, data);
      }
    }
  }

  private static final Pattern LOC_APT_PTN = Pattern.compile("(?:APT|RM|SUITE|LOT|#) *([^ ]*)|UNIT.*", Pattern.CASE_INSENSITIVE);
  private static final Pattern LOC_PLACE_PTN = Pattern.compile("\\*+ *(.*)");
  private class MyLocField extends Field {
    @Override
    public void parse(String field, Data data) {
      if (field.length() == 0) return;
      String place = null;
      String apt = null;
      do {
        Matcher match = LOC_APT_PTN.matcher(field);
        if (match.matches()) {
          apt = match.group(1);
          if (apt == null) apt = field;
          break;
        }
        
        match = LOC_PLACE_PTN.matcher(field);
        if (match.matches()) {
          place = match.group(1);
          break;
        }
        
        int chk = NUMERIC.matcher(field).find() ? 20 : 2;
        if (field.length() <= chk) {
          apt = field;
        } else {
          place = field;
        }
      } while (false);
      
      if (apt != null) {
        data.strApt = apt;
      } else {
        data.strPlace = append(data.strPlace, " - ", place);
      }
    }
    
    @Override
    public String getFieldNames() {
      return "APT PLACE";
    }
  }
  
  private static final Properties CITY_CODES = buildCodeTable(new String[]{
      "AM",   "Anna Maria",
      "BB",   "Bradenton Beach",
      "BR",   "Bradenton",
      "CZ",   "Cortez",
      "DE",   "Desoto County",
      "EL",   "Ellenton",
      "HA",   "Hardee County",
      "HB",   "Holmes Beach",
      "HC",   "Hillsborough County",
      "LBK",  "Longboat Key",
      "LK",   "Longboat Key",
      "MY",   "Myakka City",
      "PA",   "Palmetto",
      "PI",   "Pinellas County",
      "PR",   "Parrish",
      "SARA", "Sarasota County",
      "SM",   "Sarasota",
      "UB",   "Bradenton",
      "UP",   "Palmetto",
      "WM",   "Wimauma"
  });
}
