package net.anei.cadpage.parsers.NJ;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;

public class NJAtlanticCountyCParser extends FieldProgramParser {

  public NJAtlanticCountyCParser() {
    super("ATLANTIC COUNTY", "NJ", 
          "Call:CALL! Address:ADDR! Apt:APT! City:CITY! Cross:X! Place:PLACE! GPS Narrative:INFO+");
  }
  
  @Override
  public String getFilter() {
    return "noreply@gtpd.org";
  }

  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    if (!subject.equals("!")) return false;

    return super.parseFields(body.split(";"), data);
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("ADDR")) return new MyAddressField();
    return super.getField(name);
  }
  
  private class MyAddressField extends AddressField {
    @Override
    public void parse(String field, Data data) {
      field = stripFieldEnd(field, "&");
      super.parse(field, data);
    }
  }
}
