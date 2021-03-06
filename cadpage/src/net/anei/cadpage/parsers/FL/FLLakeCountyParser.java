package net.anei.cadpage.parsers.FL;

import java.util.regex.Pattern;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;


public class FLLakeCountyParser extends FieldProgramParser {
  
  public FLLakeCountyParser() {
    super(CITY_LIST, "LAKE COUNTY", "FL",
        "CH? SRC CALL UNK? ADDR! MISC+? CITY");
  }
  
  @Override
  public boolean parseMsg(String body, Data data) {
    if (body.startsWith("/ ")) body = body.substring(2).trim();
    if (!body.startsWith("CAD:")) return false;
    body = body.substring(4).trim();
    if (body.endsWith("*")) body = body.substring(0,body.length()-1).trim();
    return parseFields(body.split("\\*"), data);
  }
  
  private static final Pattern CHANNEL_PTN = Pattern.compile("PS.*"); 
  private class MyChannelField extends ChannelField {
    public MyChannelField() {
      setPattern(CHANNEL_PTN);
    }
  }
  
  private static final Pattern UNKNOWN_PTN = Pattern.compile("UNKNOWN");
  private class UnknownField extends SkipField {
    public UnknownField() {
      setPattern(UNKNOWN_PTN);
    }
  }
  
  private class MiscField extends Field {

    @Override
    public void parse(String field, Data data) {
      if (field.startsWith("APT ")) {
        data.strApt = field.substring(4).trim();
      } else {
        data.strName = field;
      }
    }
    
    @Override
    public String getFieldNames() {
      return "NAME APT";
    }
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("CH")) return new MyChannelField();
    if (name.equals("UNK")) return new UnknownField();
    if (name.equals("MISC")) return new MiscField();
    return super.getField(name);
  }
  
  private static final String[] CITY_LIST = new String[] {
    "ASTATULA",
    "CLERMONT",
    "EUSTIS",
    "FRUITLAND PARK",
    "GROVELAND",
    "HOWEY-IN-THE-HILLS",
    "LADY LAKE",
    "LEESBURG",
    "MASCOTTE",
    "MINNEOLA",
    "MONTVERDE",
    "MOUNT DORA",
    "TAVARES",
    "UMATILLA",
    "ALTOONA",
    "ASTOR",
    "BASSVILLE PARK",
    "CITRUS RIDGE",
    "FERNDALE",
    "GRAND ISLAND",
    "LAKE KATHRYN",
    "LAKE MACK-FOREST HILLS",
    "LANIER",
    "LISBON",
    "MOUNT PLYMOUTH",
    "OKAHUMPKA",
    "ORANGE BEND",
    "PAISLEY",
    "PINE LAKES",
    "PITTMAN",
    "SILVER LAKE",
    "SORRENTO",
    "YALAHA"
  };
}
