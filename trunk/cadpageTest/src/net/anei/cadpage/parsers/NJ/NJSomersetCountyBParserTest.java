package net.anei.cadpage.parsers.NJ;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class NJSomersetCountyBParserTest extends BaseParserTest {
  
  public NJSomersetCountyBParserTest() {
    setParser(new NJSomersetCountyBParser(), "SOMERSET COUNTY", "NJ");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "[fCAD] TYPE - HHaz-Health Hazard LOC - 204~STRATFORD~PL~ XST - UNKNOWN / THOMPSON AVE NAR - 07/28/12 16:50:02~CALLER STATED A POTATO EXPLODED IN HER MICROWAVE AND SHE IS CONCERNED THAT THERE MAY BE FIRE IN THE WALLS.",
        "CODE:HHaz",
        "CALL:Health Hazard",
        "ADDR:204 STRATFORD PL",
        "X:UNKNOWN / THOMPSON AVE",
        "TIME:16:50:02",
        "DATE:07/28/12",
        "INFO:CALLER STATED A POTATO EXPLODED IN HER MICROWAVE AND SHE IS CONCERNED THAT THERE MAY BE FIRE IN THE WALLS.");

    doTest("T2",
        "[fCAD] TYPE - AF - Fire Alarm LOC - 9~DARTMOUTH~AVE~APT 4B XST - COLUMBIA DR / DEAD END NAR - 08/02/12 23:27:55~FFD DISPATCHED FOR AUDIBLE FIRE ALARM CALLED IN BY RESIDENT",
        "CODE:AF",
        "CALL:Fire Alarm",
        "ADDR:9 DARTMOUTH AVE",
        "APT:4B",
        "X:COLUMBIA DR / DEAD END",
        "TIME:23:27:55",
        "DATE:08/02/12",
        "INFO:FFD DISPATCHED FOR AUDIBLE FIRE ALARM CALLED IN BY RESIDENT");

    doTest("T3",
        "[fCAD] TYPE - 39 - Notify LOC - POLHEMUS LANE~ XST - ~ NAR - 08/06/12 11:02:10~FYI ONLY-DO NOT RESPOND. POLHEMUS LANE CLOSED AT RR CROSSING 8/9 TO 8/17. DETOUR VIA TEMPORARY ROAD.",
        "CODE:39",
        "CALL:Notify",
        "ADDR:POLHEMUS LANE",
        "TIME:11:02:10",
        "DATE:08/06/12",
        "INFO:FYI ONLY-DO NOT RESPOND. POLHEMUS LANE CLOSED AT RR CROSSING 8/9 TO 8/17. DETOUR VIA TEMPORARY ROAD.");

    doTest("T4",
        "[fCAD] TYPE - 38G - Gas Leak LOC - 7~COLUMBIA~DR~APT 3A XST - MORGAN LN / DARTMOUTH AVE NAR - 07/13/12 19:18:20~FFD DISPATCHED FOR A STRONG SMELL OF GAS IN THE RESIDENCE FOR THE PAST 48 HOURS, NEG ILLNESS",
        "CODE:38G",
        "CALL:Gas Leak",
        "ADDR:7 COLUMBIA DR",
        "APT:3A",
        "X:MORGAN LN / DARTMOUTH AVE",
        "TIME:19:18:20",
        "DATE:07/13/12",
        "INFO:FFD DISPATCHED FOR A STRONG SMELL OF GAS IN THE RESIDENCE FOR THE PAST 48 HOURS, NEG ILLNESS");

    doTest("T5",
        "[fCAD] TYPE - 38S - Smoke Condition LOC - 1~PATRIOTS~PARK~TD BANK PARK RED LOT XST - DRIVEWAY / DRIVEWAY NAR -",
        "CODE:38S",
        "CALL:Smoke Condition",
        "ADDR:1 PATRIOTS PARK TD BANK PARK RED LOT",
        "X:DRIVEWAY / DRIVEWAY");

    doTest("T6",
        "[fCAD] TYPE - 43 - MV Stop LOC - 300~COMMONS~WAY~BEST BUY XST - RAMP / 5TH AVE NAR - 07/26/12 15:01:31~BLK NISSAN MAX",
        "CODE:43",
        "CALL:MV Stop",
        "ADDR:300 COMMONS WAY BEST BUY",
        "X:RAMP / 5TH AVE",
        "TIME:15:01:31",
        "DATE:07/26/12",
        "INFO:BLK NISSAN MAX");

    doTest("T7",
        "[fCAD] TYPE - AF - Fire Alarm LOC - 190~FINDERNE~AVE~ADVOSERVE XST - ENGLISH CT / FOOTHILL RD NAR - 07/22/12 00:20:21~FFD DISPATCHED FOR FIRE ALARM ACTIVATION, 1 ST FLOOR OFFICE PULL STATION",
        "CODE:AF",
        "CALL:Fire Alarm",
        "ADDR:190 FINDERNE AVE ADVOSERVE",
        "X:ENGLISH CT / FOOTHILL RD",
        "TIME:00:20:21",
        "DATE:07/22/12",
        "INFO:FFD DISPATCHED FOR FIRE ALARM ACTIVATION, 1 ST FLOOR OFFICE PULL STATION");

    doTest("T8",
        "[fCAD] TYPE - 38S - Smoke Condition LOC - 1~PATRIOTS~PARK~TD BANK PARK RED LOT XST - DRIVEWAY / DRIVEWAY NAR - 07/26/12 19:30:40~BLS UNIT 806 REPORTED A LARGE BLACK CLOUD OF SMOKE",
        "CODE:38S",
        "CALL:Smoke Condition",
        "ADDR:1 PATRIOTS PARK TD BANK PARK RED LOT",
        "X:DRIVEWAY / DRIVEWAY",
        "TIME:19:30:40",
        "DATE:07/26/12",
        "INFO:BLS UNIT 806 REPORTED A LARGE BLACK CLOUD OF SMOKE");

    doTest("T9",
        "[fCAD] TYPE - 43 - MV Stop LOC - 300~COMMONS~WAY~BEST BUY XST - RAMP / 5TH AVE NAR - 07/26/12 15:01:31~BLK NISSAN MAX",
        "CODE:43",
        "CALL:MV Stop",
        "ADDR:300 COMMONS WAY BEST BUY",
        "X:RAMP / 5TH AVE",
        "TIME:15:01:31",
        "DATE:07/26/12",
        "INFO:BLK NISSAN MAX");

    doTest("T10",
        "[fCAD] TYPE - AF - Fire Alarm LOC - 120~FINDERNE~AVE~PEOPLE CARE CENTER XST - LOCUST ST N / UNION AVE NAR - 07/31/12 18:03:20~AREA OF ACTIVATION - 2ND FLOOR SMOKE, 208",
        "CODE:AF",
        "CALL:Fire Alarm",
        "ADDR:120 FINDERNE AVE PEOPLE CARE CENTER",
        "X:LOCUST ST N / UNION AVE",
        "TIME:18:03:20",
        "DATE:07/31/12",
        "INFO:AREA OF ACTIVATION - 2ND FLOOR SMOKE, 208");

    doTest("T11",
        "[fCAD] TYPE - AA - Audible Alarm LOC - 705~KLINE~PL~ XST - MORTON ST / PEARL ST NAR - 07/09/12 11:19:47~ALARM COMING FROM INSIDE SOME HOUSE IN THE AREA - CALLER UNABLE TO PINPOINT SOURCE",
        "CODE:AA",
        "CALL:Audible Alarm",
        "ADDR:705 KLINE PL",
        "X:MORTON ST / PEARL ST",
        "TIME:11:19:47",
        "DATE:07/09/12",
        "INFO:ALARM COMING FROM INSIDE SOME HOUSE IN THE AREA - CALLER UNABLE TO PINPOINT SOURCE");

    doTest("T12",
        "[fCAD] TYPE - 38S - Smoke Condition LOC - 511~UNION~AVE~ XST - YORKTOWN RD / PEARL ST NAR - 07/09/12 11:03:03~SMOKE CONDITION IN THE AREA. FFD/SMC801 DISPATCHED",
        "CODE:38S",
        "CALL:Smoke Condition",
        "ADDR:511 UNION AVE",
        "X:YORKTOWN RD / PEARL ST",
        "TIME:11:03:03",
        "DATE:07/09/12",
        "INFO:SMOKE CONDITION IN THE AREA. FFD/SMC801 DISPATCHED");

    doTest("T13",
        "[fCAD] TYPE - AF - Fire Alarm LOC - 100~MONROE~ST~ARBOR GLEN XST - GEORGE ST / JEFFERSON CT NAR - 07/18/12 00:45:49~HEALTH CARE BUIDLING",
        "CODE:AF",
        "CALL:Fire Alarm",
        "ADDR:100 MONROE ST ARBOR GLEN",
        "X:GEORGE ST / JEFFERSON CT",
        "TIME:00:45:49",
        "DATE:07/18/12",
        "INFO:HEALTH CARE BUIDLING");

    doTest("T14",
        "[fCAD] TYPE - 13 - Assist LOC - 311~MOUNTAIN~AVE~ XST - SOMERSET ST / STHY 28 NAR - 07/18/12 23:16:59~FFD DISPATCHED BY SCC FOR FAST TEAM INTO BOUND BROOK",
        "CODE:13",
        "CALL:Assist",
        "ADDR:311 MOUNTAIN AVE",
        "X:SOMERSET ST / STHY 28",
        "TIME:23:16:59",
        "DATE:07/18/12",
        "INFO:FFD DISPATCHED BY SCC FOR FAST TEAM INTO BOUND BROOK");

  }
  
  
  public static void main(String[] args) {
    new NJSomersetCountyBParserTest().generateTests("T1", "CODE CALL ADDR APT X TIME DATE INFO");
  }
}