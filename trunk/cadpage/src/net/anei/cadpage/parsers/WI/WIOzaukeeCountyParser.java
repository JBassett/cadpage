package net.anei.cadpage.parsers.WI;

import java.text.SimpleDateFormat;
import java.util.Properties;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;



public class WIOzaukeeCountyParser extends FieldProgramParser {  
  public WIOzaukeeCountyParser() {
    super("OZAUKEE COUNTY", "WI",
          "Juris:SRC! CFS:CALL! Request#:ID! Report_Date/Time:DATETIME! Reporting_Period:SKIP! Location:ADDR! Notify_Type:SKIP Call#:SKIP Login_User:SKIP");
  }
  
  @Override
  public String getFilter() {
    return "Police@ci.mequon.wi.us";
  }
  
  @Override
  public int getMapFlags() {
    return MAP_FLG_SUPPR_LA;
  }
  
  @Override
  protected boolean parseMsg(String body, Data data) {
    body = body.replace("\\t", "");
    return parseFields(body.split("\\n"), data);
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("ID")) return new IdField("\\d{2}\\-\\d{6}", true);
    if (name.equals("DATETIME")) return new MyDateTimeField();
    if (name.equals("ADDR")) return new MyAddressField();
    return super.getField(name);
  }
  
  private static final SimpleDateFormat MY_DATE_TIME_FMT
    = new SimpleDateFormat("mm/dd/yyyy hh:mm:ss aa");
  private class MyDateTimeField extends DateTimeField {
    @Override
    public void parse(String field, Data data) {
      setDateTime(MY_DATE_TIME_FMT, field, data);
    }
  }
  
  private class MyAddressField extends AddressField {
    @Override
    public void parse(String field, Data data) {
      Parser p = new Parser(field);
      data.strCity = convertCodes(p.getLastOptional(';'), CITY_CODE);
      String apt = p.getLastOptional(','); 
      super.parse(p.get(), data);
      data.strApt = append(data.strApt, "-", apt);
      return;
    }
  }
  
  private static final Properties CITY_CODE = buildCodeTable (new String[]{
    "ME", "MEQUON"
  });
}
