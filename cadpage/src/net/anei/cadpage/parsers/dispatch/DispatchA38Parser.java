package net.anei.cadpage.parsers.dispatch;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;



public class DispatchA38Parser extends FieldProgramParser {
  
  public DispatchA38Parser(String defCity, String defState) {
    super(defCity, defState,
          "CFS#:ID! CallType:CALL! Address:ADDR+ Units:UNIT/N+ Details:INFO/CS+");
  }

  @Override
  protected boolean parseMsg(String body, Data data) {
    String[] flds = body.split("\n");
    if (flds.length >= 3) return parseFields(flds, data);
    return super.parseMsg(body, data);
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("ID")) return new IdField("\\d{4}-\\d{5}|\\d{8}|\\d{2}[A-Z]{3}\\d{6}", true);
    if (name.equals("ADDR")) return new MyAddressField();
    return super.getField(name);
  }
  
  private class MyAddressField extends AddressField {
    @Override
    public void parse(String field, Data data) {
      Parser p = new Parser(field.replace(" apt:", " Apt:"));
      String city = p.getLastOptional(',');
      if (city.length() == 2 || city.length() == 0) {
        data.strState = city;
        city = p.getLastOptional(',');
      }
      data.strCity = city;
      String apt = p.getLastOptional("Apt:");
      String addr = p.get();
      addr = addr.replace('@', '&');
      parseAddress(addr, data);
      if (!apt.equals(data.strApt)  && !apt.equals(addr)) {
        data.strApt = append(data.strApt, "-", apt);
      }
    }
    
    @Override
    public String getFieldNames() {
      return "ADDR APT CITY ST";
    }
  }
}
