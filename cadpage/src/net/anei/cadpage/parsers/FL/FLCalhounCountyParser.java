package net.anei.cadpage.parsers.FL;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;

public class FLCalhounCountyParser extends FieldProgramParser {

  public FLCalhounCountyParser() {
    super("CALHOUN COUNTY", "FL", 
          "CALL:CALL! PLACE:PLACE! ADDR:ADDR! CITY:CITY! ID:ID! PRI:PRI! DATE:DATE! TIME:TIME! MAP:MAP! UNIT:UNIT! INFO:INFO!");
  }
  
  @Override
  public String getFilter() {
    return "woody@usa-software.com,calhouncad@gmail.com";
  }
  
  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    return parseFields(body.split("\n"), data);
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("ADDR")) return new MyAddressField();
    if (name.equals("TIME")) return new MyTimeField();
    return super.getField(name);
  }
  
  private class MyAddressField extends AddressField {
    @Override
    public void parse(String field, Data data) {
      if (field.length() == 0) {
        field = data.strPlace;
        data.strPlace = "";
      }
      super.parse(field, data);
    }
  }
  
  private static final Pattern TIME_PTN = Pattern.compile("(\\d\\d)(\\d\\d)(\\d\\d)");
  private class MyTimeField extends TimeField {
    @Override
    public void parse(String field, Data data) {
      Matcher match = TIME_PTN.matcher(field);
      if (!match.matches()) abort();
      data.strTime = match.group(1) + ':' + match.group(2) + ':' + match.group(3);
    }
  }
}
