package net.anei.cadpage.parsers;

import java.util.HashMap;
import java.util.Map;

import net.anei.cadpage.parsers.MsgInfo.Data;

/**
 * Base class for the two CadpageParser classes
 */
public class CadpageParserBase  extends FieldProgramParser{
  
  private Map<String,Field> fieldMap = new HashMap<String,Field>();
  
  public CadpageParserBase() {
    this("", "", CountryCode.US);
  }
  
  public CadpageParserBase(String defCity, String defState, CountryCode country) {
    // Pass empty strings to subclass constructor, we never really try to run a 
    // field program or use the default city/state values
    super(defCity, defState, country, "");
    initMap();
  }
  
  private void initMap() {
    setMap("PRI");
    setMap("DATE");
    setMap("TIME");
    setMap("CALL");
    setMap("PLACE", "PL");
    setMap("ADDR");
    setMap("CITY");
    setMap("ST");
    setMap("APT");
    setMap("X");
    setMap("BOX");
    setMap("MAP");
    setMap("CH");
    setMap("UNIT");
    setMap("INFO");
    setMap("NAME");
    setMap("PHONE", "PH");
    setMap("CODE");
    setMap("GPS");
    setMap("ID");
    setMap("SRC");
    setMap("DCITY");
    setMap("DST");
    setMap("MADDR");
    setMap("MCITY");
    setMap("URL");
    setMap("CO");
    setMap("REC_GPS");
    setMap("PARSER");
    setMap("DATETIME");
    setMap("TIMEDATE");
  }

  /**
   * Set up key -> Field process map
   * @param keys list of key used to access this field.  The first key will
   * be used to look up the field that all of them will refer to.
   */
  private void setMap(String ... keys) {
    Field field = getField(keys[0]);
    for (String key : keys) fieldMap.put(key, field);
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("DCITY")) return new DefCityField();
    if (name.equals("DST")) return new DefStateField();
    if (name.equals("MADDR")) return new MapAddressField();
    if (name.equals("MCITY")) return new MapCityField();
    if (name.equals("CO")) return new CountryField();
    if (name.equals("REC_GPS")) return new PreferGPSField();
    if (name.equals("PARSER")) return new ParserField();
    return super.getField(name);
  }

  // And something to save the default city and state
  private class DefCityField extends SkipField {
    @Override 
    public void parse(String field, Data data) {
      data.defCity = field;
    }
  }
  
  private class DefStateField extends SkipField {
    @Override 
    public void parse(String field, Data data) {
      data.defState = field;
    }
  }
  
  private class CountryField extends SkipField {
    @Override
    public void parse(String field, Data data) {
      try {
        // We used the wrong code for United Kingdom.  Don't want to change it
        // now, but we will accept the correct code.
        if (field.equals("GB")) field = "UK";
        data.countryCode = CountryCode.valueOf(field);
      } catch (Exception ex) {}
    }
  }
  
  private class PreferGPSField extends SkipField {
    @Override 
    public void parse(String field, Data data) {
      data.preferGPSLoc = (field.length() > 0 && "YES".startsWith(field));
    }
  }
  
  private class MapAddressField extends SkipField {
    @Override
    public void parse(String field, Data data) {
      data.strBaseMapAddress = field;
    }
  }
  
  private class MapCityField extends SkipField {
    @Override
    public void parse(String field, Data data) {
      data.strMapCity = field;
    }
  }
  
  private class ParserField extends SkipField {
    @Override
    public void parse(String field, Data data) {
      try {
        data.parser = ManageParsers.getInstance().getParser(field);
      } catch (Exception ex) {}
    }
  }
  
  /**
   * Get Field object that will really be used to process items
   * @param name field name
   * @return Field object if found or null otherwise
   */
  protected Field getMapField(String name) {
    return fieldMap.get(name);
  }
}
