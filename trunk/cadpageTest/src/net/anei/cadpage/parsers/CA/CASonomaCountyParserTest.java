package net.anei.cadpage.parsers.CA;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class CASonomaCountyParserTest extends BaseParserTest {
  
  public CASonomaCountyParserTest() {
    setParser(new CASonomaCountyParser(), "SONOMA COUNTY", "CA");
  }
  
  @Test
  public void testParser1() {

    doTest("T1",
        "Loc: HWY1/VALLEY FORD ROAD VFR BOX: 3540 TYP:TC CN: CHP LOG # 632 C#: TYPE CODE: TC CALLER NAME: CHP LOG # 632 CALLER ADDR:  TIME: 11:07:31 COM: ** Case n",
        "ADDR:HWY1 & VALLEY FORD ROAD",
        "SRC:VFR",
        "BOX:3540",
        "CALL:TC",
        "NAME:CHP LOG # 632",
        "INFO:** Case n");

    doTest("T2",
        "Loc: 1320 BAY VIEW ST BBY:@ BODEGA UNION CHURCH BOX: 3433 B3 TYP: GAS-IN CN: BODEGA BAY UNION CHURCH C#: (707)875-3559 TYPE CODE: GAS-IN CALLER NAME: BODEGA",
        "ADDR:1320 BAY VIEW ST",
        "SRC:BBY",
        "PLACE:BODEGA UNION CHURCH",
        "BOX:3433 B3",
        "CALL:GAS-IN",
        "NAME:BODEGA",
        "PHONE:(707)875-3559");

    doTest("T3",
        "Loc: BBY:@HWY 1MM012.42 BOX:3332 B TYP: TC-EX CN: CHP LOG 344 C#: TYPE CODE: TC_EX CALLER NAME:CHP LOG 344 CALLER ADDR:  TIME: 03:44:34 COM: OVER TURN",
        "ADDR:HWY 1MM012.42",
        "SRC:BBY",
        "BOX:3332 B",
        "CALL:TC-EX",
        "NAME:CHP LOG 344",
        "INFO:OVER TURN");

    doTest("T4",
        "Loc: 1400 VALLEY FORD FREESTONE RD VFR BOX: 3439 B TYP: SER-PA CN: JOANNA C#: (707) 876-3288 TYPE CODE: SER-PA CALLER NAME: JOANNA CALLER ADDR:  TIME: 13:01",
        "ADDR:1400 VALLEY FORD FREESTONE RD",
        "SRC:VFR",
        "BOX:3439 B",
        "CALL:SER-PA",
        "NAME:JOANNA",
        "PHONE:(707) 876-3288");

    doTest("T5",
        "Loc: 2458 BIG OAK DR SR BOX: 2946 D4 TYP: STRUW CN: AT&T MOBILITY 80 635 6840  4 C#: (707) 327-7382 TYPE CODE: STRUW CALLER NAME: AT&T MOBILITY",
        "ADDR:2458 BIG OAK DR",
        "SRC:SR",
        "BOX:2946 D4",
        "CALL:STRUW",
        "NAME:AT&T MOBILITY",
        "PHONE:(707) 327-7382");

    doTest("T6",
        "Loc: CALISTOGA RD/CHALFANT RD RIN BOX: 2452 D TYP: TC-EX CN: CHP LOG#1987 C#:  TYPE CODE: TC-EX CALLER NAME: CHP LOG#1987 CALLER ADDR:  TIME: 19:32:46 COM:",
        "ADDR:CALISTOGA RD & CHALFANT RD",
        "SRC:RIN",
        "BOX:2452 D",
        "CALL:TC-EX",
        "NAME:CHP LOG#1987");

    doTest("T7",
        "Loc: 801 SANTA BARBARA DR SRO BOX: 3049 B4 TYP: GAS-IN CN: JOEY C#: (707) 391-8596 TYPE CODE: GAS-IN CALLER NAME: JOEY CALLER ADDR:  TIME: 18:14:37 COM:  Ev",
        "ADDR:801 SANTA BARBARA DR",
        "SRC:SRO",
        "BOX:3049 B4",
        "CALL:GAS-IN",
        "NAME:JOEY",
        "PHONE:(707) 391-8596",
        "INFO:Ev");
        
  }
  
  @Test
  public void testEmailParser() {

    doTest("T1",
        "Loc: VALLEY FORD RD/HWY 1 VFR BOX: 3540 TYP: TC CN: AT&T MOBILITY 800 635 6840  4 C#: (650) 455-7732 TYPE CODE: TC CALLER NAME: AT&T MOBILITY 800 635 6840  4 CALLER ADDR: 2885 BAY HILL RD BDGA TIME: 16:00:06 COM:  N -122.7320 T 38.40650 METERS 2758 MOTORCYCLE",
        "ADDR:VALLEY FORD RD & HWY 1",
        "SRC:VFR",
        "BOX:3540",
        "CALL:TC",
        "NAME:AT&T MOBILITY 800 635 6840  4",
        "PHONE:(650) 455-7732",
        "INFO:N -122.7320 T 38.40650 METERS 2758 MOTORCYCLE");

    doTest("T2",
        "Loc: 14460 SCHOOL ST VFR BOX: 3539 A2 TYP: MED CN: BEAL FRED C#: (707) 876-3232 TYPE CODE: MED CALLER NAME: BEAL FRED CALLER ADDR: 14460 SCHOOL TIME: 20:34:31 COM:  SCSO 911 HAD SURGERY IS SF A WEEK AGO LEG SWOLLEN FROM WHERE THE SURGERY WAS IN HIS LEG CONTROL 2 PT HAS A 102 TEMP DR TOLD HIM TO CALL 911 78 YOM",
        "ADDR:14460 SCHOOL ST",
        "SRC:VFR",
        "BOX:3539 A2",
        "CALL:MED",
        "NAME:BEAL FRED",
        "PHONE:(707) 876-3232",
        "INFO:SCSO 911 HAD SURGERY IS SF A WEEK AGO LEG SWOLLEN FROM WHERE THE SURGERY WAS IN HIS LEG CONTROL 2 PT HAS A 102 TEMP DR TOLD HIM TO CALL 911 78 YOM");

    doTest("T3",
        "Loc: 814 OWL CT BBY BOX: 3535 C1 TYP: STRU CN: CASTLE,STACEY C#: (707) 875-9870 TYPE CODE: STRU CALLER NAME: CASTLE,STACEY CALLER ADDR: 814 OWL CT TIME: 10:17:55 COM:  SCSO- 911 SMOKE COMING FROM AN ELECTICAL OUTELT",
        "ADDR:814 OWL CT",
        "SRC:BBY",
        "BOX:3535 C1",
        "CALL:STRU",
        "NAME:CASTLE,STACEY",
        "PHONE:(707) 875-9870",
        "INFO:SCSO- 911 SMOKE COMING FROM AN ELECTICAL OUTELT");

    doTest("T4",
        "Loc: BLOOMFIELD RD/SUTTON ST BLO BOX: 3542 B4 TYP: HC CN: 8540 C#:  TYPE CODE: HC CALLER NAME: 8540 CALLER ADDR:  TIME: 20:39:02 COM:  veh in ditch and water start 8580",
        "ADDR:BLOOMFIELD RD & SUTTON ST",
        "SRC:BLO",
        "BOX:3542 B4",
        "CALL:HC",
        "NAME:8540",
        "INFO:veh in ditch and water start 8580");

    doTest("T5",
        "BOX: 3437 TYP: VEG CN: VERIZON WIRELESS 800 451 5242 4 C#: (707) 321-9508 TYPE CODE: VEG CALLER NAME: VERIZON WIRELESS 800 451 5242 4 CALLER ADDR: 2885 BAY HILL RD BDGA BAY TIME: 12:33:30 COM:  N -122.9738 T 38.33546 METERS 41 RIGHT BEFORE THE BODEGA HWY TURN OFF SHE COULD NOT GIVE AN ADDRESS OR A MILE MARKER",
        "ADDR:2885 BAY HILL RD",
        "CITY:BODEGA BAY",
        "BOX:3437",
        "CALL:VEG",
        "NAME:VERIZON WIRELESS 800 451 5242 4",
        "PHONE:(707) 321-9508",
        "INFO:N -122.9738 T 38.33546 METERS 41 RIGHT BEFORE THE BODEGA HWY TURN OFF SHE COULD NOT GIVE AN ADDRESS OR A MILE MARKER");

    doTest("T6",
        "Loc: BBY: @HWY 1 MM012.42 BOX: 3332 B TYP: TC-EX CN: CHP LOG 344 C#:  TYPE CODE: TC-EX CALLER NAME: CHP LOG 344 CALLER ADDR:  TIME: 03:44:34 COM:  OVER TURNED VEH NEAR SALMON CREEK CONTROL 2",
        "ADDR:HWY 1 MM012.42",
        "SRC:BBY",
        "BOX:3332 B",
        "CALL:TC-EX",
        "NAME:CHP LOG 344",
        "INFO:OVER TURNED VEH NEAR SALMON CREEK CONTROL 2");

    doTest("T7",
        "Loc: HWY 1/BODEGA HW BOD BOX: 3436 TYP: TC-EX CN: CHP C#:  TYPE CODE: TC-EX CALLER NAME: CHP CALLER ADDR:  TIME: 20:46:20 COM:  OVERTURNED VEH ON HWY 1, BETWEEN VALLEY FORD AND BODEGA HWY BAD CONNECTION WITH RP, PER CHP",
        "ADDR:HWY 1 & BODEGA HWY",
        "SRC:BOD",
        "BOX:3436",
        "CALL:TC-EX",
        "NAME:CHP",
        "INFO:OVERTURNED VEH ON HWY 1, BETWEEN VALLEY FORD AND BODEGA HWY BAD CONNECTION WITH RP, PER CHP");
    
  }
  
  public static void main(String[] args) {
    new CASonomaCountyParserTest().generateTests("T6");
  }
}