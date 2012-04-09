package net.anei.cadpage.parsers.GA;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class GADoolyCountyParserTest extends BaseParserTest {
  
  public GADoolyCountyParserTest() {
    setParser(new GADoolyCountyParser(), "DOOLY COUNTY", "GA");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "VFD Type:WEATHER SUBTYPE:? LOC:TORNADO WATCH UNTIL 2 AM C:11/22/2011 18:25:41 VFDE1 : DSPTCH & primary unit.",
        "CALL:WEATHER",
        "ADDR:TORNADO WATCH UNTIL 2 AM",
        "DATE:11/22/2011",
        "TIME:18:25:41",
        "UNIT:VFDE1");

    doTest("T2",
        "VFD Type:50I SUBTYPE:? LOC:HWY 41 & MT PLEASANT CHURCH RD C:11/23/2011 13:01:24 18 WHEELER VS ANOTHER VEH 11/23/2011 13:01:26 DCEM1 : DSPTCH & primary uni",
        "CALL:50I",
        "ADDR:HWY 41 & MT PLEASANT CHURCH RD",
        "DATE:11/23/2011",
        "TIME:13:01:24",
        "UNIT:DCEM1",
        "INFO:18 WHEELER VS ANOTHER VEH");

    doTest("T3",
        "VFD Type:SMOKE SUBTYPE:? LOC:513 CLOVER ST C:11/24/2011 03:31:23 513 CLOVER ST: cross streets: W WOODWARD ST & PECAN ST 11/24/2011 03:32:59 10-85 CALLED T",
        "CALL:SMOKE",
        "ADDR:513 CLOVER ST",
        "DATE:11/24/2011",
        "TIME:03:31:23",
        "X:W WOODWARD ST & PECAN ST",
        "INFO:10-85 CALLED T");

    doTest("T4",
        "VFD Type:70 SUBTYPE:? LOC:N 5TH ST & E PINE ST C:11/25/2011 05:09:23 10-85 CALLED T REPORT A HOUSE ON FIRE ON N 5TH ST NEAR PINE ST",
        "CALL:70",
        "ADDR:N 5TH ST & E PINE ST",
        "DATE:11/25/2011",
        "TIME:05:09:23",
        "INFO:10-85 CALLED T REPORT A HOUSE ON FIRE ON N 5TH ST NEAR PINE ST");

    doTest("T5",
        "VFD Type:MEETING SUBTYPE:? LOC:DRILL TONIGHT @1900 HRS AT THE STATION. ALL MEMBERS ARE ASKED TO ATTEND C:",
        "CALL:MEETING",
        "ADDR:DRILL TONIGHT @1900 HRS AT THE STATION ALL MEMBERS ARE ASKED TO ATTEND");

    doTest("T6",
        "VFD Type:90F SUBTYPE:? LOC:208 CHURCH ST C:12/6/2011 10:58:06 208 CHURCH ST: cross streets:       N 5TH ST & N 4TH ST 12/6/2011 10:59:18 VFDE2 : DSPTCH & primar",
        "CALL:90F",
        "ADDR:208 CHURCH ST",
        "DATE:12/6/2011",
        "TIME:10:58:06",
        "X:N 5TH ST & N 4TH ST",
        "UNIT:VFDE2");

    doTest("T7",
        "VFD Type:CANCELCALL SUBTYPE:? LOC:208 CHURCH ST C:12/6/2011 10:58:06 208 CHURCH ST: cross streets: N 5TH ST & N 4TH ST 12/6/2011 10:59:18 VFDE2 : DSPTCH &",
        "CALL:CANCELCALL",
        "ADDR:208 CHURCH ST",
        "DATE:12/6/2011",
        "TIME:10:58:06",
        "X:N 5TH ST & N 4TH ST",
        "UNIT:VFDE2");

    doTest("T8",
        "VFD Type:90F SUBTYPE:? LOC:3113 HWY 90 C:12/6/2011 19:07:08 OPT 29 JOHNSON ELEC 12/6/2011 19:07:11 3113 HWY 90: cross streets: SLOSHEYE TRAIL RD 12/6/2011",
        "CALL:90F",
        "ADDR:3113 HWY 90",
        "DATE:12/6/2011",
        "TIME:19:07:08",
        "X:SLOSHEYE TRAIL RD",
        "INFO:OPT 29 JOHNSON ELEC");

    doTest("T9",
        "VFD Type:CANCELCALL SUBTYPE:? LOC:3113 HWY 90 C:12/6/2011 19:07:08 OPT 29 JOHNSON ELEC 12/6/2011 19:07:11 3113 HWY 90: cross streets: SLOSHEYE TRAIL RD 12/",
        "CALL:CANCELCALL",
        "ADDR:3113 HWY 90",
        "DATE:12/6/2011",
        "TIME:19:07:08",
        "X:SLOSHEYE TRAIL RD 12",
        "INFO:OPT 29 JOHNSON ELEC");

    doTest("T10",
        "VFD Type:70 SUBTYPE:? LOC:514 FOUNDRY ST C:12/7/2011 19:27:29 514 FOUNDRY ST: cross streets: W WOODWARD ST & PECAN ST 12/7/2011 19:27:50 ADV SMOKE IS COMI",
        "CALL:70",
        "ADDR:514 FOUNDRY ST",
        "DATE:12/7/2011",
        "TIME:19:27:29",
        "X:W WOODWARD ST & PECAN ST",
        "INFO:ADV SMOKE IS COMI");

    doTest("T11",
        "VFD Type:90M SUBTYPE:? LOC:21490 HWY 27 C:12/13/2011 20:59:47 21490 HWY 27: cross streets: RIVER RD & OTTO RD 12/13/2011 21:01:13 56 YOA FEMALE HAS PRESSE",
        "CALL:90M",
        "ADDR:21490 HWY 27",
        "DATE:12/13/2011",
        "TIME:20:59:47",
        "X:RIVER RD & OTTO RD",
        "INFO:56 YOA FEMALE HAS PRESSE");

    doTest("T12",
        "VFD Type:70 LRG VEHICLE SUBTYPE:? LOC:TIPPETTVILLE RD & COPPEDGE RD C:12/14/2011 13:24:04 ADV THAT ONE OF THE TRACTORS ARE ON FIRE 12/14/2011 13:24:23 ADV",
        "CALL:70 LRG VEHICLE",
        "ADDR:TIPPETTVILLE RD & COPPEDGE RD",
        "DATE:12/14/2011",
        "TIME:13:24:04",
        "INFO:ADV THAT ONE OF THE TRACTORS ARE ON FIRE / ADV");

    doTest("T13",
        "VFD Type:70 DUMPSTER SUBTYPE:? LOC:PECAN ST & CLOVER ST C:12/15/2011 18:14:10 FIRE IN TRASH CAN BY A HOUSE ON PECAN JUST OFF OF CLOVER ST 12/15/2011 18:14:",
        "CALL:70 DUMPSTER",
        "ADDR:PECAN ST & CLOVER ST",
        "DATE:12/15/2011",
        "TIME:18:14:10",
        "INFO:FIRE IN TRASH CAN BY A HOUSE ON PECAN JUST OFF OF CLOVER ST");

    doTest("T14",
        "VFD Type:CANCELCALL SUBTYPE:? LOC:PECAN ST & CLOVER ST C:12/15/2011 18:14:10 FIRE IN TRASH CAN BY A HOUSE ON PECAN JUST OFF OF CLOVER ST 12/15/2011 18:14:4",
        "CALL:CANCELCALL",
        "ADDR:PECAN ST & CLOVER ST",
        "DATE:12/15/2011",
        "TIME:18:14:10",
        "INFO:FIRE IN TRASH CAN BY A HOUSE ON PECAN JUST OFF OF CLOVER ST");

    doTest("T15",
        "VFD Type:WIRES SUBTYPE:? LOC:S 5TH ST & E UNION ST C:12/16/2011 07:46:24 ADV THE POWER LINES ARE SPARKING FIRE AT THIS LOCATION 12/16/2011 07:46:51 VFDE2",
        "CALL:WIRES",
        "ADDR:S 5TH ST & E UNION ST",
        "DATE:12/16/2011",
        "TIME:07:46:24",
        "INFO:ADV THE POWER LINES ARE SPARKING FIRE AT THIS LOCATION / VFDE2");

    doTest("T16",
        "VFD Type:50I SUBTYPE:? LOC:109 I 75 N [@I75N EXIT 109[ : C:12/17/2011 19:48:39 VPD6 : Field Incident 12/17/2011 19:48:39 VPD6 : DSPTCH & primary unit. 12/1",
        "CALL:50I",
        "ADDR:109 I 75 N",
        "PLACE:I75N EXIT 109",
        "DATE:12/17/2011",
        "TIME:19:48:39",
        "UNIT:VPD6",
        "INFO:VPD6 : Field Incident");

    doTest("T17",
        "VFD Type:70W SUBTYPE:? LOC:107 I 75 N [@I75N MM 107 C:12/18/2011 12:53:33 VFDE2 : DSPTCH & primary unit.",
        "CALL:70W",
        "ADDR:107 I 75 N",
        "PLACE:I75N MM 107",
        "DATE:12/18/2011",
        "TIME:12:53:33",
        "UNIT:VFDE2");

    doTest("T18",
        "VFD Type:CANCELCALL SUBTYPE:? LOC:107 I 75 N [@I75N MM 107 C:12/18/2011 12:53:33 VFDE2 : DSPTCH & primary unit. 12/18/2011 12:53:53 ADV FIELD ON FIRE AT TH",
        "CALL:CANCELCALL",
        "ADDR:107 I 75 N",
        "PLACE:I75N MM 107",
        "DATE:12/18/2011",
        "TIME:12:53:33",
        "UNIT:VFDE2",
        "INFO:ADV FIELD ON FIRE AT TH");
   
  }
  
  public static void main(String[] args) {
    new GADoolyCountyParserTest().generateTests("T1");
  }
}
