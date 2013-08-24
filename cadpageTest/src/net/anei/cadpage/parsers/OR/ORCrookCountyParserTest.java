package net.anei.cadpage.parsers.OR;

import net.anei.cadpage.parsers.BaseParserTest;
import net.anei.cadpage.parsers.OR.ORCrookCountyParser;

import org.junit.Test;

/*
Prineville, OR
Contact: 5414603655@vzwpix.com
Sender:dispatch@prinevillepd.org

(NEW INCIDENT) 3/10/2011 1003\nEVENT # 1103100020 PFD\nMABD - ABDOMINAL PAIN\nPRIORITY 1 \nLOCATION 2250 SE KYLE RD\nCITY PRINEVILLE\nAPT \nPREMISE: \nCOMMENT: HA
(NEW INCIDENT) 3/11/2011 1003\nEVENT # 1103110010 PFD\nBURN - BURN INFO ONLY\nPRIORITY 1 \nLOCATION 6282 NW GREEN VALLEY RD\nCITY PRINEVILLE\nAPT \nPREMISE: \nCOM
(NEW INCIDENT) 3/11/2011 0903\nEVENT # 1103110009 PFD\nFALARM - FIRE ALARM\nPRIORITY 1 \nLOCATION 210 NE 3RD ST\nCITY PRINEVILLE\nAPT \nPREMISE: \nCOMMENT: BACK R\n
(NEW INCIDENT) 3/10/2011 0803\nEVENT # 1103100051 PFD\nMUNK - UNKNOWN MEDICAL\nPRIORITY 1 \nLOCATION 587 SE KNIGHT ST\nCITY PRINEVILLE\nAPT \nPREMISE: \nCOMMENT:
(NEW INCIDENT) 3/10/2011 0803\nEVENT # 1103100050 PFD\nALARM - ALARM\nPRIORITY 1 \nLOCATION 705 NW 10TH\nCITY PRINEVILLE\nAPT 27\nPREMISE: \nCOMMENT: 90 YOF FALLE
(NEW INCIDENT) 3/10/2011 0703\nEVENT # 1103100047 PFD\nMVA - 1201: 1273 / 1225\nPRIORITY 1 \nLOCATION HWY 27 MP 2\nCITY PRINEVILLE\nAPT \nPREMISE: \nCOMMENT: SUV

([PRNV_911] NEW INCIDENT) 5/3/2011 0805\nEVENT # 1105030076 PFD\nMINJ - INJURIES\nPRIORITY 1 \nLOCATION 1265 NW LAMBERT RD\nCITY PRINEVILL
([PRNV_911] NEW INCIDENT) 5/3/2011 0705\nEVENT # 1105030067 PFD\nMFALL - FALL\nPRIORITY 1 \nLOCATION 210 SE 5TH ST\nCITY PRINEVILLE\nAPT 13
([PRNV_911] NEW INCIDENT) 5/3/2011 0605\nEVENT # 1105030061 PFD\nMFALL - FALL\nPRIORITY 1 \nLOCATION 14493 SE LEE WAY\nCITY PRINEVILLE\nAPT

Contact: James Shannon <firemedic2484@gmail.com>
S:NEW INCIDENT M:6/2/2011 1006\nEVENT # 1106020016 PFD\nALARM - 1201:1225/1221/1222/...\nPRIORITY 1\nLOCATION 1100 SE LYNN\nCITY PRINEVILLE\nAPT\nPREMISE:\nCOMMENT: SMOKE ALARM//

Contact: Jason Dad Hupp <hupp375@gmail.com>
Sender: fire_sms-bounces@listserv.cityofprineville.net
([PRNV_911] NEW INCIDENT) 7/21/2011 1107\nEVENT # 1107210080 PFD\nMUNK - UNKNOWN MEDICAL\nPRIORITY 1 \nLOCATION 3813 SE TILLAMOOK LOOP\nCI

Contact: CodeMessaging.net
1/12/2013 0301<br>EVENT # 1301120044 PFD<br>MTRANS - MEDICAL TRANSFER<br>PRIORITY 1 <br>LOCATION ER TO 472 NE 2ND ST<br>CITY PRINEVILLE<br>APT <br>PREMISE: <br>COMMENT: BLS BACK HOME<br><br>

*/

public class ORCrookCountyParserTest extends BaseParserTest {
  
  public ORCrookCountyParserTest() {
    setParser(new ORCrookCountyParser(), "CROOK COUNTY", "OR");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "(NEW INCIDENT) 3/10/2011 1003\nEVENT # 1103100020 PFD\nMABD - ABDOMINAL PAIN\nPRIORITY 1 \nLOCATION 2250 SE KYLE RD\nCITY PRINEVILLE\nAPT \nPREMISE: \nCOMMENT: HA",
        "DATE:3/10/2011",
        "TIME:10:03",
        "ID:1103100020",
        "SRC:PFD",
        "CALL:MABD - ABDOMINAL PAIN",
        "PRI:1",
        "ADDR:2250 SE KYLE RD",
        "CITY:PRINEVILLE",
        "INFO:HA");

    doTest("T2",
        "(NEW INCIDENT) 3/11/2011 1003\nEVENT # 1103110010 PFD\nBURN - BURN INFO ONLY\nPRIORITY 1 \nLOCATION 6282 NW GREEN VALLEY RD\nCITY PRINEVILLE\nAPT \nPREMISE: \nCOM",
        "DATE:3/11/2011",
        "TIME:10:03",
        "ID:1103110010",
        "SRC:PFD",
        "CALL:BURN - BURN INFO ONLY",
        "PRI:1",
        "ADDR:6282 NW GREEN VALLEY RD",
        "CITY:PRINEVILLE");

    doTest("T3",
        "(NEW INCIDENT) 3/11/2011 0903\nEVENT # 1103110009 PFD\nFALARM - FIRE ALARM\nPRIORITY 1 \nLOCATION 210 NE 3RD ST\nCITY PRINEVILLE\nAPT \nPREMISE: \nCOMMENT: BACK R\n",
        "DATE:3/11/2011",
        "TIME:09:03",
        "ID:1103110009",
        "SRC:PFD",
        "CALL:FALARM - FIRE ALARM",
        "PRI:1",
        "ADDR:210 NE 3RD ST",
        "CITY:PRINEVILLE",
        "INFO:BACK R");

    doTest("T4",
        "(NEW INCIDENT) 3/10/2011 0803\nEVENT # 1103100051 PFD\nMUNK - UNKNOWN MEDICAL\nPRIORITY 1 \nLOCATION 587 SE KNIGHT ST\nCITY PRINEVILLE\nAPT \nPREMISE: \nCOMMENT:",
        "DATE:3/10/2011",
        "TIME:08:03",
        "ID:1103100051",
        "SRC:PFD",
        "CALL:MUNK - UNKNOWN MEDICAL",
        "PRI:1",
        "ADDR:587 SE KNIGHT ST",
        "CITY:PRINEVILLE");

    doTest("T5",
        "(NEW INCIDENT) 3/10/2011 0803\nEVENT # 1103100050 PFD\nALARM - ALARM\nPRIORITY 1 \nLOCATION 705 NW 10TH\nCITY PRINEVILLE\nAPT 27\nPREMISE: \nCOMMENT: 90 YOF FALLE",
        "DATE:3/10/2011",
        "TIME:08:03",
        "ID:1103100050",
        "SRC:PFD",
        "CALL:ALARM - ALARM",
        "PRI:1",
        "ADDR:705 NW 10TH",
        "CITY:PRINEVILLE",
        "APT:27",
        "INFO:90 YOF FALLE");

    doTest("T6",
        "(NEW INCIDENT) 3/10/2011 0703\nEVENT # 1103100047 PFD\nMVA - 1201: 1273 / 1225\nPRIORITY 1 \nLOCATION HWY 27 MP 2\nCITY PRINEVILLE\nAPT \nPREMISE: \nCOMMENT: SUV",
        "DATE:3/10/2011",
        "TIME:07:03",
        "ID:1103100047",
        "SRC:PFD",
        "CALL:MVA - 1201: 1273 / 1225",
        "PRI:1",
        "ADDR:HWY 27 MP 2",
        "CITY:PRINEVILLE",
        "INFO:SUV");

    doTest("T7",
        "([PRNV_911] NEW INCIDENT) 5/3/2011 0805\nEVENT # 1105030076 PFD\nMINJ - INJURIES\nPRIORITY 1 \nLOCATION 1265 NW LAMBERT RD\nCITY PRINEVILL",
        "DATE:5/3/2011",
        "TIME:08:05",
        "ID:1105030076",
        "SRC:PFD",
        "CALL:MINJ - INJURIES",
        "PRI:1",
        "ADDR:1265 NW LAMBERT RD",
        "CITY:PRINEVILL");

    doTest("T8",
        "([PRNV_911] NEW INCIDENT) 5/3/2011 0705\nEVENT # 1105030067 PFD\nMFALL - FALL\nPRIORITY 1 \nLOCATION 210 SE 5TH ST\nCITY PRINEVILLE\nAPT 13",
        "DATE:5/3/2011",
        "TIME:07:05",
        "ID:1105030067",
        "SRC:PFD",
        "CALL:MFALL - FALL",
        "PRI:1",
        "ADDR:210 SE 5TH ST",
        "CITY:PRINEVILLE",
        "APT:13");

    doTest("T9",
        "([PRNV_911] NEW INCIDENT) 5/3/2011 0605\nEVENT # 1105030061 PFD\nMFALL - FALL\nPRIORITY 1 \nLOCATION 14493 SE LEE WAY\nCITY PRINEVILLE\nAPT",
        "DATE:5/3/2011",
        "TIME:06:05",
        "ID:1105030061",
        "SRC:PFD",
        "CALL:MFALL - FALL",
        "PRI:1",
        "ADDR:14493 SE LEE WAY",
        "CITY:PRINEVILLE");

    doTest("T10",
        "S:NEW INCIDENT M:6/2/2011 1006\nEVENT # 1106020016 PFD\nALARM - 1201:1225/1221/1222/...\nPRIORITY 1\nLOCATION 1100 SE LYNN\nCITY PRINEVILLE\nAPT\nPREMISE:\nCOMMENT: SMOKE ALARM//",
        "DATE:6/2/2011",
        "TIME:10:06",
        "ID:1106020016",
        "SRC:PFD",
        "CALL:ALARM - 1201:1225/1221/1222/...",
        "PRI:1",
        "ADDR:1100 SE LYNN",
        "CITY:PRINEVILLE",
        "INFO:SMOKE ALARM//");

    doTest("T11",
        "([PRNV_911] NEW INCIDENT) 7/21/2011 1107\nEVENT # 1107210080 PFD\nMUNK - UNKNOWN MEDICAL\nPRIORITY 1 \nLOCATION 3813 SE TILLAMOOK LOOP\nCI",
        "DATE:7/21/2011",
        "TIME:11:07",
        "ID:1107210080",
        "SRC:PFD",
        "CALL:MUNK - UNKNOWN MEDICAL",
        "PRI:1",
        "ADDR:3813 SE TILLAMOOK LOOP");
  }
  
  @Test
  public void testCodeMessaging() {

    doTest("T1",
        "1/12/2013 0301<br>EVENT # 1301120044 PFD<br>MTRANS - MEDICAL TRANSFER<br>PRIORITY 1 <br>LOCATION ER TO 472 NE 2ND ST<br>CITY PRINEVILLE<br>APT <br>PREMISE: <br>COMMENT: BLS BACK HOME<br><br>",
        "DATE:1/12/2013",
        "TIME:03:01",
        "ID:1301120044",
        "SRC:PFD",
        "CALL:MTRANS - MEDICAL TRANSFER",
        "PRI:1",
        "ADDR:ER TO 472 NE 2ND ST",
        "CITY:PRINEVILLE",
        "INFO:BLS BACK HOME");

  }
  
  public static void main(String[] args) {
    new ORCrookCountyParserTest().generateTests("T1");
  }
}