package net.anei.cadpage.parsers.TN;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;

/* 
Hamilton County, TN
Contact: Matt Randall <vaporjet@gmail.com>
Sender: <none>
System: Positron

RT:ACC1HW-HWY ACCIDENT WITH INJURIES  Loc:INTERSTATE 75 S, HAMILTON COUNTY (BRADLEY COUNTY/MILE MARKER 13.2)  #[1320-1560]
RT:CHILOC-CHILD LOCKED IN VEHICLE  Loc:10433 E BRAINERD RD, HAMILTON COUNTY (BENTWOOD COVE DR/STEPPING ROCK DR)  #[10416-10449] [10416-10449] [0-0]
RT:AFA NURSING HOME  Loc:4586 FORSYTHIA WAY, COLLEGEDALE  (ILENE CT/LEYLAND DR) #[4500-4599]
RT:AFA DORMITORY  Loc:4891 TAYLOR CIR, COLLEGEDALE  (UNIVERSITY DR/UNIVERSITY DR)  #[4800-4899]
RT:AFA RESIDENTIAL  Loc:4019 PATTON EDWARDS DR, EAST RIDGE  (S MISSION OAKS DR/EDWARDS TER)  #[4000-4099]
RT:ACC4-MOTOR VEHICLE ACCIDENT WITH ENTRAPMENT  Loc:INTERSTATE 75 N, HAMILTON COUNTY  (MILE MARKER 13.2/BRADLEY COUNTY)  #[1320-1560]
RT:SPILL-GASOLINE, FUEL, OR HAZARDOUS LIQUIDS SPILLED  Loc:8935 LEE HWY, CHATTANOOGA  (MOUNTAIN VIEW RD/INTERSTATE 75)  #[8900-8999]
RT:SMOKEINVES-INVESTIGATE SMOKE  Loc:LONDON LN, HAMILTON COUNTY  (SALEM RD/BILL JONES RD)  #[10932-10943]
RT:ACC1-MOTOR VEHICLE ACCIDENT WITH INJURIES  Loc:STANDIFER GAP RD, HAMILTON COUNTY  (CROSS GATE RD/OOLTEWAH RINGGOLD RD)  #[9243-9299]
RT:FTRASH-OUTSIDE TRASH FIRE  Loc:5205 ALABAMA RD, HAMILTON COUNTY  (BATES RD/BLAIR RD)  #[5000-5399]

Contact: Darren Randall <d.randall@tricommunityfire.com>
Sender: <user phone>
RT:AFA RESIDENTIAL  Loc:2706 NILE RD, HAMILTON COUNTY  (CLIPPER DR/DANUBE DR)  #
RT:FASEMS-FIRE DEPARTMENT ASSISTING EMS WITH MANPOWER  Loc:9407 CATHOWKEN DR, HA MILTON COUNTY  (FULLER RD/DEAD END)  #[9400-9499]
RT:FASCIT-FIRE DEPARTMENT ASSISTING A CITIZEN  Loc:8475 COMMUNITY PL, HAMILTON COUNTY  (PITTMAN LN/PATTENTOWN RD)  #[8400-8499]
RT:FMUAID-FIRE DEPARTMENT MUTUAL AID ALARM  Loc:281 CLARK RD, DOGWOOD RD THE CROSS CATOOSA CO
RT:ANSBT-ANIMAL OR SNAKE BITE  Loc:@WAFFLE HOUSE 8912 LEE HWY  (8912 LEE HWY, CHATTANOOGA)

Contact: "Danny" <robertdcooke@epbfi.com>
RT:STROKE-STROKE  Loc:10320 HAMBY RD, HAMILTON COUNTY  (SEQUOYAH ACCESS RD/PLES LN)  #[10206-10329]

Contact: Stuart Ball <smball09@gmail.com>
Sender: 4236671103 (user phone)
RT:FASCIT-FIRE DEPARTMENT ASSISTING A CITIZEN  Loc:8601 CAMP COLUMBUS RD, LAKESITE  (DEAD END/ST JOHNS RD)  #[8600-8604]

 */

public class TNHamiltonCountyParser extends FieldProgramParser {
  
  public TNHamiltonCountyParser() {
    super("HAMILTON COUNTY", "TN",
           "RT:CALL! Loc:ADDR!");
  }

  @Override
  protected boolean parseMsg(String body, Data data) {
    
    // There is always some trailing junk that needs to be stripped off.
    // But if we don't find it, assume more is coming
    
    int pt = body.indexOf(" #[");
    if (pt >= 0) body = body.substring(0,pt).trim();
    else data.expectMore = true;

    if (body.endsWith("#")) body = body.substring(0,body.length()-1).trim();

    return super.parseMsg(body, data);
  }
  
  private class MyAddressField extends AddressField {
    
    @Override
    public void parse(String field, Data data) {
      if (field.endsWith(")")) field = field.substring(0,field.length()-1).trim();
      int pt = field.indexOf('(');
      if (pt>0) {
        if (field.endsWith(")")) field = field.substring(0,field.length()-1).trim();
        else data.expectMore = true;
        String sCross = field.substring(pt+1, field.length()).trim();
        field = field.substring(0,pt).trim();
        pt = sCross.indexOf(',');
        if (pt >= 0) {
          data.strCity = sCross.substring(pt+1).trim();
          sCross = sCross.substring(0,pt).trim();
        }
        data.strCross = sCross;
      }
      
      pt = field.indexOf(',');
      if (pt >= 0) {
        String city = field.substring(pt+1).trim();
        field = field.substring(0,pt).trim();
        if (city.equals("HAMILTON COUNTY")) city = "";
        else if (city.endsWith(" CATOOSA CO")) {
          data.strCross = append(city.substring(0,city.length()-10).trim(), " & ", data.strCross);
          city = "CATOOSA COUNTY";
          data.strState = "GA";
        }
        data.strCity = city;
      }
      
      if (field.startsWith("@")) {
        parseAddress(StartType.START_PLACE, FLAG_START_FLD_REQ | FLAG_ANCHOR_END, field.substring(1).trim(), data);
      } else {
        parseAddress(field, data);
      }
    }
    
    @Override
    public String getFieldNames() {
      return "PLACE ADDR APT CITY ST X";
    }
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("ADDR")) return new MyAddressField();
    return super.getField(name);
  }
}
