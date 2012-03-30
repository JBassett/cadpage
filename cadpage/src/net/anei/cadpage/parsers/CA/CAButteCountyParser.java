package net.anei.cadpage.parsers.CA;

import java.util.Properties;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;

/*
Butte County, CA
Contact: "McLean, Scott" <Scott.McLean@fire.ca.gov>
Sender: btucad@fire.ca.gov

(CAD Page) MEDICAL; 1759 12TH ST ,THERMALITO ; ; 1200 BLK TEHAMA AVE; Map: 6683; Inc# 002759; Date-Time: 21-Mar-2012/20:55:59; CARDIAC;
(CAD Page) MEDICAL; 14897 DEL ORO DR ,MGLA ; ;  DEAD-END; Map: 5633; Inc# 002916; Date-Time: 26-Mar-2012/09:00:57; CARDIAC;
(CAD Page) OTH,REFERRAL; 7540 OROVILLE BANGOR HWY / 2 BANGOR PARK RD ,BNGR ; ; ; Map: 6985; Inc# ; Date-Time: 28-Mar-2012/09:29:18; LOOSE HORSE ON BANGOR PARK RD

 */


public class CAButteCountyParser extends FieldProgramParser {
  
  public CAButteCountyParser() {
    super(CITY_CODES, "BUTTE COUNTY", "CA",
           "CALL ADDRCITY! X/Z+ Map:MAP Inc:ID! Date-Time:DATETIME! CALL!");
  }
  
  @Override
  public String getFilter() {
    return "btucad@fire.ca.gov";
  }

  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    
    if (!subject.equals("CAD Page")) return false;
    body = body.replace(" Inc#", " Inc:");
    return parseFields(body.split(";"), 6, data);
  }
  
  private class MyDateTimeField extends Field {
    @Override
    public void parse(String field, Data data) {
      int pt = field.indexOf('/');
      if (pt < 0) abort();
      data.strDate = field.substring(0,pt).trim();
      data.strTime = field.substring(pt+1).trim();
    }
    
    @Override
    public String getFieldNames() {
      return "DATE TIME";
    }
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("DATETIME")) return new MyDateTimeField();
    return super.getField(name);
  }
  
  private static final Properties CITY_CODES = buildCodeTable(new String[]{
      "BNGR", "BANGOR",
      "MGLA", "MAGALIA"
  });
}
