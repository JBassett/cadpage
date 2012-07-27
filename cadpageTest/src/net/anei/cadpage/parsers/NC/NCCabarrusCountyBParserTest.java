package net.anei.cadpage.parsers.NC;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class NCCabarrusCountyBParserTest extends BaseParserTest {
  
  public NCCabarrusCountyBParserTest() {
    setParser(new NCCabarrusCountyBParser(), "CABARRUS COUNTY", "NC");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "29:1|ODOR OF SMOKE INSIDE|OPS3|920 CHURCH ST N|ARDSLEY AVE NE|CONCORD PKWY N|2|21KA|10/04/2011 10:24:12|NEMC MAIN BUILDING(CMH)|7047836595|4431|1106269",
        "DATE:10/04/2011",
        "TIME:10:24:12",
        "CALL:ODOR OF SMOKE INSIDE",
        "CH:OPS3",
        "ADDR:920 CHURCH ST N",
        "X:ARDSLEY AVE NE & CONCORD PKWY N",
        "PRI:2",
        "UNIT:21KA",
        "PLACE:NEMC MAIN BUILDING(CMH)",
        "PHONE:7047836595",
        "ID:1106269");

    doTest("T2",
        "30:FYI: |1|COMMERCIAL FIRE ALARM|2460 ROCK HILL CHURCH RD|CLOISTER CT NW|CAVALIER CT NW|3|371|10/04/2011 14:37:43|CLARE BRIDGE (DISCOVERY)|7047200990|1106276",
        "DATE:10/04/2011",
        "TIME:14:37:43",
        "CALL:COMMERCIAL FIRE ALARM",
        "ADDR:2460 ROCK HILL CHURCH RD",
        "X:CLOISTER CT NW & CAVALIER CT NW",
        "PRI:3",
        "UNIT:371",
        "PLACE:CLARE BRIDGE (DISCOVERY)",
        "PHONE:7047200990",
        "ID:1106276");

    doTest("T3",
        "34:10/05/2011 22:21:57|DWELLING FIRE|199 GOAR ST SW|FERN AVE SW|RONE AVE SW",
        "DATE:10/05/2011",
        "TIME:22:21:57",
        "CALL:DWELLING FIRE",
        "ADDR:199 GOAR ST SW",
        "X:FERN AVE SW & RONE AVE SW");

    doTest("T4",
        "37:10/07/2011 22:05:08|DWELLING FIRE|2873 SIGNAL CT SW|PULLMAN ST SW",
        "DATE:10/07/2011",
        "TIME:22:05:08",
        "CALL:DWELLING FIRE",
        "ADDR:2873 SIGNAL CT SW",
        "X:PULLMAN ST SW");

    doTest("T5",
        "77:FYI: |1|RESIDENTIAL FIRE ALARM|764 JUANITA DR SW|JOSEPHINE LN SW|KIM ST SW|(S)YATES MEADOW SD (N)YATES MEADOWS|5|583|10/23/2011 19:55:32|1106744",
        "DATE:10/23/2011",
        "TIME:19:55:32",
        "CALL:RESIDENTIAL FIRE ALARM",
        "ADDR:764 JUANITA DR SW",
        "X:JOSEPHINE LN SW & KIM ST SW",
        "INFO:(S)YATES MEADOW SD (N)YATES MEADOWS",
        "PRI:5",
        "UNIT:583",
        "ID:1106744");
   
  }
  
  @Test
  public void testParser2() {
    
    doTest("T1",
        "82:P|BOMB|2|273|90|3200 PATRICK HENRY DR NW|DAVIDSON HWY|10/26/2011 14:48:25|COVENANT CLASSICAL SCHOOL|7047921854|1106799",
        "DATE:10/26/2011",
        "TIME:14:48:25",
        "CALL:BOMB",
        "ADDR:3200 PATRICK HENRY DR NW",
        "X:DAVIDSON HWY",
        "PRI:2",
        "UNIT:273 90",
        "ID:1106799",
        "PLACE:COVENANT CLASSICAL SCHOOL",
        "PHONE:7047921854");

     doTest("T2",
         "89:FYI: |1|RESIDENTIAL FIRE ALARM|4155 KELLYBROOK DR SW|LITTLETON DR SW|LITTLETON DR SW|(S) (N)BRANDON RIDGE|8|843|10/27/2011 12:11:16|FRASER|7047869700|1106822",
         "DATE:10/27/2011",
         "TIME:12:11:16",
         "CALL:RESIDENTIAL FIRE ALARM",
         "ADDR:4155 KELLYBROOK DR SW",
         "X:LITTLETON DR SW & LITTLETON DR SW",
         "UNIT:843",
         "INFO:(S) (N)BRANDON RIDGE",
         "PRI:8",
         "ID:1106822",
         "PLACE:FRASER",
         "PHONE:7047869700");

     doTest("T3",
         "88:Update: |1|FIRE VEHICLE ACCIDENT RESPONSE|CENTRAL DR NW/MAPLE AVE NW|7047873400|2|237|10/27/2011 11:26:22|FVACC|7046922840",
         "DATE:10/27/2011",
         "TIME:11:26:22",
         "CALL:FIRE VEHICLE ACCIDENT RESPONSE",
         "ADDR:CENTRAL DR NW & MAPLE AVE NW",
         "UNIT:237",
         "INFO:7047873400",
         "PRI:2",
         "PLACE:FVACC",
         "PHONE:7046922840");

     doTest("T4",
         "163:FYI: |1|COMMERCIAL FIRE ALARM|3700 TAYLOR GLEN LN NW|PITTS SCHOOL RD NW|5|597|11/15/2011 20:43:47|TAYLOR GLEN RETIREMENT COM|7047886510|1107247",
         "DATE:11/15/2011",
         "TIME:20:43:47",
         "CALL:COMMERCIAL FIRE ALARM",
         "ADDR:3700 TAYLOR GLEN LN NW",
         "X:PITTS SCHOOL RD NW",
         "PRI:5",
         "UNIT:597",
         "ID:1107247",
         "PLACE:TAYLOR GLEN RETIREMENT COM",
         "PHONE:7047886510");

     doTest("T5",
         "170:Update: |1|COMMERCIAL FIRE ALARM|OPS2|4545 CONCORD PKWY S|CARPENTER CT NW|BRUTON SMITH BLVD|5|593|11/16/2011 13:56:51|BACKING UP CLASSICS|7047889500|ASG|8666668897|1107270",
         "DATE:11/16/2011",
         "TIME:13:56:51",
         "CALL:COMMERCIAL FIRE ALARM",
         "CH:OPS2",
         "ADDR:4545 CONCORD PKWY S",
         "X:CARPENTER CT NW & BRUTON SMITH BLVD",
         "PRI:5",
         "UNIT:593",
         "ID:1107270",
         "PLACE:BACKING UP CLASSICS / ASG",
         "PHONE:7047889500 / 8666668897");

     doTest("T6",
         "165:FYI: |1|WORKING FIRE UPGRADE|OPS3|1051 NC HWY 49 S|GLENWOOD DR SW|OLD CHARLOTTE RD SW|8|834|11/16/2011 12:41:58|WHITLEY TRK COMPANY|JJTC INC/ DBA WHITLEY TRUCK|7047862610|1107266",
         "DATE:11/16/2011",
         "TIME:12:41:58",
         "CALL:WORKING FIRE UPGRADE",
         "CH:OPS3",
         "ADDR:1051 NC HWY 49 S",
         "MADDR:1051 NC 49 S",
         "X:GLENWOOD DR SW & OLD CHARLOTTE RD SW",
         "PRI:8",
         "UNIT:834",
         "ID:1107266",
         "PLACE:WHITLEY TRK COMPANY / JJTC INC/ DBA WHITLEY TRUCK",
         "PHONE:7047862610");

     doTest("T7",
         "1021:FYI: |1|COMMERCIAL FIRE ALARM|250 BISHOP LN|CONCORD LAKE RD|2|21KA|07/23/2012 19:01:22|BRIAN CENTER|7047886400|1205076",
         "DATE:07/23/2012",
         "TIME:19:01:22",
         "CALL:COMMERCIAL FIRE ALARM",
         "ADDR:250 BISHOP LN",
         "X:CONCORD LAKE RD",
         "PRI:2",
         "UNIT:21KA",
         "PLACE:BRIAN CENTER",
         "PHONE:7047886400",
         "ID:1205076");

     doTest("T8",
         "1019:07/23/2012 16:08:00|HAZARDOUS MATERIALS|611 VEGA ST NW|SUTRO FOREST DR NW|FALLING LEAF DR NW",
         "DATE:07/23/2012",
         "TIME:16:08:00",
         "CALL:HAZARDOUS MATERIALS",
         "ADDR:611 VEGA ST NW",
         "X:SUTRO FOREST DR NW & FALLING LEAF DR NW");

     doTest("T9",
         "1018:FYI: |1|COMMERCIAL FIRE ALARM|5571 CONCORD PKWY S|SANDUSKY BLVD|EVERETTE DR|5|593A|07/23/2012 15:59:21|CMS NATIONWIDE CUP GARAGE|7044554381|1205066",
         "DATE:07/23/2012",
         "TIME:15:59:21",
         "CALL:COMMERCIAL FIRE ALARM",
         "ADDR:5571 CONCORD PKWY S",
         "X:SANDUSKY BLVD & EVERETTE DR",
         "PRI:5",
         "UNIT:593A",
         "PLACE:CMS NATIONWIDE CUP GARAGE",
         "PHONE:7044554381",
         "ID:1205066");

     doTest("T10",
         "1036:FYI: |1|RESIDENTIAL FIRE ALARM|506 CHANNING CIR NW|HANOVER DR NW|DEWITT CT NW|(S)ZEMOSA ACRES SD (N)ZEMOSA ACRES|7|732|07/27/2012 10:29:32|1205141",
         "DATE:07/27/2012",
         "TIME:10:29:32",
         "CALL:RESIDENTIAL FIRE ALARM",
         "ADDR:506 CHANNING CIR NW",
         "X:HANOVER DR NW & DEWITT CT NW",
         "PRI:7",
         "UNIT:732",
         "INFO:(S)ZEMOSA ACRES SD (N)ZEMOSA ACRES",
         "ID:1205141");

  }
  
  public static void main(String[] args) {
    new NCCabarrusCountyBParserTest().generateTests("T1");
  }
}
