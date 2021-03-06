package net.anei.cadpage.parsers.MO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

import net.anei.cadpage.parsers.FieldProgramHtmlParser;
import net.anei.cadpage.parsers.MsgInfo.Data;


public class MOFranklinCountyParser extends FieldProgramHtmlParser {
  public MOFranklinCountyParser() {
    super(CITY_CODE,
            "FRANKLIN COUNTY",
            "MO",
            "ADDR CITY X PLACE APT CALL MAP DATE TIME UNIT SRC",
            LAYOUT);
  }
  
  @Override
  public String getProgram() {
    return "ADDR CITY X PLACE APT CALL MAP DATE TIME UNIT SRC";
  }
  
  private static final DateFormat MY_DATE_FMT
    = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");  
  @Override
  protected boolean parseHtmlMsg(String head, String body, Data data) {
    if (!getHtmlCleaner(body))
      return false;
    String location = getValue("LOCATION");
    parseAddress(StartType.START_ADDR, location, data);
    data.strCall = getValue("CALL");
    data.strMap = getValue("MAP");
    String dateTime = getValue("DATETIME");
    setDateTime(MY_DATE_FMT, dateTime, data);
    data.strUnit = getValue("UNIT");
    data.strSource = getValue("SOURCE");
  
    return true;
//    return data.strCall.length() > 0 && dateTime.length() > 0 && data.strUnit.length() > 0;
  }
  
  private static final String[] LAYOUT = {
    "LOCATION(ELEMENT=TD;LABEL=/Location:/;REMOVE_LABEL)",
    "CALL(ELEMENT=TD;LABEL=/Response Type:/;REMOVE_LABEL;REQUIRED)",
    "MAP(ELEMENT=TD;LABEL=/Zone Name:/;REMOVE_LABEL)",
    "DATETIME(ELEMENT=TD;LABEL=/Status Time:/;REMOVE_LABEL;REQUIRED)",
    "UNIT(ELEMENT=TD;LABEL=/Handling Unit:/;REMOVE_LABEL;REQUIRED)",
    "SOURCE(ELEMENT=TD;LABEL=/Agency Name:/;REMOVE_LABEL)"
  };
  
  private static final Properties CITY_CODE = buildCodeTable(new String[] {
    "UNION",        "UNION",
    "FRNKLN CNTY", "FRANKLIN COUNTY"
  });
 }