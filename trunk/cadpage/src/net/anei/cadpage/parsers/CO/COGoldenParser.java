package net.anei.cadpage.parsers.CO;

import java.util.regex.Pattern;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;

/**
 * Golden, CO
 */
public class COGoldenParser extends FieldProgramParser {
  
  private static final Pattern ENDLINE = Pattern.compile("\\s*\n");
  private static final Pattern DELIM = Pattern.compile("\\*\n");
  
  public COGoldenParser() {
    super("GOLDEN", "CO",
           "Call#:ID! Time:TIME! Type_of_call:CALL! Location:ADDR! Cross_streets:X? Map_page:MAP? Units:UNIT? Narrative:INFO");
  }
  
  @Override
  public String getFilter() {
    return "messaging@iamresponding.com";
  }

  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    if (!subject.equals("Golden Fire")) return false;
    body = ENDLINE.matcher(body).replaceAll("\n");
    return parseFields(DELIM.split(body), 6, data);
  }
  
  private class MyAddressField extends AddressField {
    @Override
    public void parse(String field, Data data) {
      String[] parts = field.split(",");
      if (parts.length > 3) abort();
      int ndx = 0;
      if (parts.length == 3) data.strPlace = parts[ndx++].trim();
      parseAddress(parts[ndx++].trim(), data);
      if (ndx < parts.length) data.strCity = parts[ndx].trim();
    }
    
    @Override
    public String getFieldNames() {
      return "PLACE ADDR APT CITY";
    }
  }
  
  private class MyCrossField extends CrossField {
    @Override
    public void parse(String field, Data data) {
      if (field.equals("/")) return;
      super.parse(field, data);
    }
  }
  
  private class MyMapField extends MapField {
    @Override
    public void parse(String field, Data data) {
      if (field.startsWith("MAP ")) field = field.substring(4).trim();
      super.parse(field, data);
    }
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("ADDR")) return new MyAddressField();
    if (name.equals("X")) return new MyCrossField();
    if (name.equals("MAP")) return new MyMapField();
    return super.getField(name);
  }
}
