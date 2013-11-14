package net.anei.cadpage.parsers.PA;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;

/**
 * Mercer County, PA
 */
public class PAMercerCountyParser extends FieldProgramParser {
  
  public PAMercerCountyParser() {
    super(CITY_CODES, "MERCER COUNTY", "PA",
           "Location:ADDR/S? EID:ID! TYPE_CODE:CALL! CALLER_NAME:NAME CALLER_ADDR:ADDR/S TIME:TIME");
  }
  
  @Override
  public String getFilter() {
    return "@mcc.co.mercer.pa.us";
  }

  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    if (!subject.equals("IPS I/Page Notification")) return false;
    return super.parseMsg(body, data);
  }
  
  private static final Pattern PLACE_MARKER = Pattern.compile(" *:? *@ *| *: *alias *");
  private class MyAddressField extends AddressField {
    @Override
    public void parse(String field, Data data) {
      if (data.strAddress.length() > 0) return;
      
      String sPlace = "";
      Matcher match = PLACE_MARKER.matcher(field);
      if (match.find()) {
        sPlace = field.substring(match.end()).trim();
        field = field.substring(0,match.start()).trim();
      }
      super.parse(field, data);
      
      int pt = sPlace.lastIndexOf(' ');
      if (pt >= 0) {
        String city = CITY_CODES.getProperty(sPlace.substring(pt+1));
        if (city != null) {
          sPlace = sPlace.substring(0,pt).trim();
          if (data.strCity.length() == 0) data.strCity = city;
        }
      }
      data.strPlace = sPlace;
    }
    
    @Override
    public String getFieldNames() {
      return super.getFieldNames() + " PLACE";
    }
  }
  
  private class MyCallField extends CallField {
    @Override
    public void parse(String field, Data data) {
      if (field.length() >= 2 && field.charAt(1) == '-') {
        data.strPriority = field.substring(0,1).trim();
        field = field.substring(2).trim();
      }
      data.strCall = convertCodes(field, CALL_CODES);
    }
    
    @Override
    public String getFieldNames() {
      return "PRI CALL";
    }
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("ADDR")) return new MyAddressField();
    if (name.equals("CALL")) return new MyCallField();
    return super.getField(name);
  }
  
  private static final Properties CITY_CODES = buildCodeTable(new String[]{
      "CLRK",  "CLARK",
      "COOL",  "COOLSPRING TWP",
      "DEER",  "DEER CREEK",
      "DELA",  "DELAWARE",
      "ELAC",  "EAST LACKAWANNOCK",
      "FAIR",  "FAIRVIEW",
      "FARL",  "FARRELL",
      "FIND",  "FINDLEY",
      "FRED",  "FREDONIA",
      "FREN",  "FRENCH CREEK",
      "GRNE",  "GREENE",
      "GRVL",  "GREENVILLE",
      "GROV",  "GROVE CITY",
      "HEMP",  "HEMPFIELD",
      "HERM",  "HERMITAGE",
      "JACK",  "JACKSON TWP",
      "JCEN",  "JACKSON CENTER",
      "JAME",  "JAMESTOWN",
      "JEFF",  "JEFFERSON",
      "LACK",  "LACKAWANNOCK",
      "LAKE",  "LAKE",
      "LIBR",  "LIBERTY TWP",
      "MERC",  "MERCER",
      "MILL",  "MILL CREEK",
      "NLEB",  "NEW LEBANON",
      "NVER",  "NEW VERNON",
      "OTTR",  "OTTER CREEK",
      "PERR",  "PERRY",
      "PINE",  "PINE",
      "PYMA",  "PYMATUNING",
      "SALM",  "SALEM",
      "SCRK",  "SANDY CREEK",
      "SLAK",  "SANDY LAKE",
      "SLTP", "SANDY LAKE TWP",
      "SHAR",  "SHARON",
      "SHRP",  "SHARPSVILLE",
      "SKVL",  "SHEAKLEYVILLE",
      "SHEN",  "SHENANGO",
      "SPYM",  "SOUTH PYMATUNING",
      "SPRG",  "SPRINGFIELD",
      "STON",  "STONEBORO",
      "SUGR",  "SUGAR GROVE",
      "WMID",  "WEST MIDDLESEX",
      "WSAL",  "WEST SALEM",
      "WHEA",  "WHEATLAND",
      "WILM",  "WILMINGTON",
      "WOLF",  "WOLF CREEK",
      "WRTH",  "WORTH",
      "SLTP",  "SANDY LAKE TWP"
  });
  
  private static final Properties CALL_CODES = buildCodeTable(new String[]{
      "AF",   "Fire-Admin Activity",
      "ANML", "Animal Rescue",
      "BACK", "Back Pains",
      "BARN", "Barn Fire",
      "BRU",  "Brush Fire",
      "CHIM", "Chimney Fire",
      "CMD",  "Carbon Monoxide Detector",
      "CMDS", "Carbon Monoxide detector w/ symptoms",
      "COM",  "Commercial Fire",
      "ELI",  "Electric Fire",
      "ELO",  "Electric Lines down",
      "EXP",  "Explosion",
      "FALM", "Fire alarm",
      "GARC", "Comercial Garage Fire",
      "GARF", "Garbage Fire",
      "GAS",  "Inside Gas Leak",
      "HAZ",  "Hazmat",
      "HF",   "House fire",
      "KITF", "Kitchen Fire",
      "MHF",  "Mobile Home Fire",
      "MUT",  "Mutual Aid",
      "MVA",  "Motor Vehicle Crash w. no injuries",
      "MVE",  "Motor Vehicle Crash w. trap",
      "MVI",  "Motor Vehicle Crash w/ injuries",
      "MVU",  "Motor Vehicle Crash w/ unknown injuries",
      "RES",  "Rescue Call",
      "ROAD", "Notification of Road Conditions",
      "QRS",  "EMS Call",
      "SDET", "Smoke Detector Activation",
      "SERV", "Service Call",
      "SMOI", "Smoke in Structure",
      "STR",  "Structure Fire",
      "TREE", "Tree down",
      "UNKF", "Unknown Type Fire",
      "UTL",  "Utility Lines Down",
  });
}
