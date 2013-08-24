package net.anei.cadpage.parsers.OR;

import net.anei.cadpage.parsers.BaseParserTest;
import net.anei.cadpage.parsers.OR.ORWashingtonCountyAParser;

import org.junit.Test;

/*
Washington County, OR
Contact: Phillip Duncan <phil860@gmail.com>
Sender:  930010001-0050

MVA-UKN INJURY SW TUALATIN VALLEY HW/SW331ST AV (33098 TUALATIN VALLEY/331ST) HIL MAP: 5377D UNIT: C2 E01 R8
ABDOMINAL PAIN 985 N DAVIS ST (DEAD END & N 10TH AV) CON MAP: 5375D UNIT METWA STA8
BREATHING PROB 39470 SW GEIGER RD (SW LAFOLLETT RD & FERN HILL RD) CON MAP: 5574B UNIT: METWA STA8
SICK PERSON/UNKO 1045 N ADAIR ST (N 10TH AV & N 10TH-11TH AL) CON MAP: 5375D UNIT: METWA STA8
RESIDENTIAL FIRE 822 N 28TH AV (N HOLLADAY DR & N 27TH AV) CON MAP: 5376D UNIT: METWA TIMERC CAS8 CFDUTY SIRN08 E813 E814 E815 E421 R8 T03 COCB1

Clackamas County, OR
Contact: Tyree Zander <tzander.depoebayfire@gmail.com>
Sender: 777109496789
    UNCON/FAINTING 37905 SE SERBAN RD (SE BLUFF RD & SE BAUMBACK RD) SAN MAP: 6013B UNIT: E74 M1
    
Contact: zak delair <zakdelair117@gmail.com>
Sender: 93001003
UNK PROB/MN DOWN 95 82ND DR (SAFEWAY (GLADSTONE)) GLA MAP: 6398D UNIT: R101 M1

Contact: Active911
[] PRE NOTIFICATION 59185 E CHALET PL (DEAD END & E EAST RD) SAN MAP: 6324A UNIT: SQ252 SQ251 M1 2509\r\n\r\n\n
[] BREATHING PROB. 59185 E CHALET PL (DEAD END & E EAST RD) SAN MAP: 6324A UNIT: SQ252\r\n\r\n\n
[] PRE NOTIFICATION 59454 E SLEEPY HOLLOW DR (E EAST RD & E BARLOW TRAIL RD) SAN MAP: 6324B UNIT: SQ252 SQ251 M1 2509\r\n\r\n\n
[] PRE NOTIFICATION 27160 E MARION RD (E ROAD 10 & E ROAD 20) RHO MAP: 6731A UNIT: SQ252 M1 3709\r\n\r\n\n
[] PRE NOTIFICATION 59454 E SLEEPY HOLLOW DR (E EAST RD & E BARLOW TRAIL RD) SAN MAP: 6324B UNIT: SQ252 SQ251 M1 2509\r\n\r\n\n
[] UNKNOWN TYP FIRE 31315 E MULTORPOR DR (SKI BOWL (EAST)) GOV MAP: 6939 UNIT: E251 E254 2509\r\n\r\n\n
[] FIRE ALARM, COMM 30521 E MELDRUM ST (E LIGE LN & E GOVERNMENT CAMP LP) GOV MAP: 6939 UNIT: E251 E254 WT251 2509\r\n\r\n\n

Contact: Active911
Agency name: Hillsboro Fire and Rescue
Location: Hillsboro, OR, United States
Sender: wccca@wccca.com

SEIZURES 7545 SE TUALATIN VALLEY HW (SE 75TH AV & SW CORNELIUS PASS RD) HIL UNIT: MW64 E02\r\n\r\n
SICK PERSON/UNKO 650 SE OAK ST #410 (SE 6TH AV & SE 7TH AV) HIL UNIT: METWA E01\r\n\r\n
MENTAL/PSYCH 537 SE 14TH AV #C (SE WALNUT ST & SE CEDAR ST) HIL UNIT: METWC E01\r\n\r\n
FALL C1 511 NE 37TH AV (NE LAURA ST & DEAD END) HIL UNIT: METWA E02\r\n\r\n
HEADACHE 1178 SE 13TH AV (SE SPRUCE ST & SE RIVER RD) HIL UNIT: METWA E01\r\n\r\n
CHEST PAIN/HEART 2501 NW 229TH AV (NE BUTLER ST & NW EVERGREEN PK) HIL UNIT: METWA E03\r\n\r\n
MVA-INJURY ACCID 7355-BLK SE TUALATIN VALLEY HW (SE 73RD AV & SE 75TH AV) HIL UNIT: METWA MED62 C1INFO\r\n\r\n
BACK PAIN C1 971 NE SHELLY CT (NE 9TH PL & DEAD END) HIL UNIT: E02 E05\r\n\r\n
BREATHING PROB 1241 SE WALNUT ST #F (SE 12TH AV & SE 13TH AV) HIL UNIT: METWA E01\r\n\r\n
SEIZURES 1255 NE 48TH AV (NE CORNELL RD & NE AIRPORT RD) HIL UNIT: METWA E03\r\n\r\n
FALL 10025 NW GLENCOE RD (NW HIGHLAND CT & NW PACIFIC ST) NOR UNIT: METWA E17\r\n\r\n
FALL 1221 NE 51ST AV #127 (NE WINDROW ST & NE FARMCREST ST) HIL UNIT: METWA E03\r\n\r\n
FALL 2000 SE 30TH AV (SE 32ND AV & SE TUALATIN VALLEY HW) HIL MAP: 5580B UNIT: METWA E02\r\n\r\n
FALL 1470 SE WALNUT ST #109 (SE 14TH AV & SE 15TH AV) HIL MAP: 5480A UNIT: METWA E01\r\n\r\n
BREATHING PROB 7136 NE RONLER WY #2718 (NE BUTLER ST & NE RONLER WY) HIL MAP: 5283C UNIT: METWA E06\r\n\r\n
SICK PERSON C1 3120 NW JOHN OLSEN AV #13102 (NW OVERLOOK DR & NW EVERGREEN PK) HIL MAP: 5284A UNIT: METWA E03\r\n\r\n
FIRE ALARM, COMM 720 SE WASHINGTON ST (SE 7TH AV & SE 8TH AV) HIL MAP: 5379D UNIT: E01\r\n\r\n
SICK PERSON/UNKO 772 NW AUTUMNCREEK WY #O105 (NW FIELDCREST WY & NW TRAILWALK DR) BTN MAP: 5383D UNIT: METWA E06\r\n\r\n
BREATHING PROB 351 SE 5TH AV (SE BASELINE ST & SE OAK ST) HIL MAP: 5479A UNIT: METWA E01\r\n\r\n
FALL C1 1377 SE DUKE DR (SE FIR GROVE LP & SE FLANDERS LN) HIL MAP: 5479D UNIT: METWA E02\r\n\r\n
BURN COMPLAINT 336 NW MERLE DR (NW 3RD AV & NW 4TH AV) HIL MAP: 5278D UNIT: E01\r\n\r\n
FALL C1 2731 SE LONNY CT (SE RIVER RD & DEAD END) HIL MAP: 5580B UNIT: METWC E02\r\n\r\n
ELECTRICAL FIRE 20820 SW REGAL CT (DEAD END & SW ANTHONY DR) BTN MAP: 5583B UNIT: E02\r\n\r\n
CHEST PAIN/HEART 617 SE 2ND AV (DEAD END & SE CEDAR-MAPLE AL) HIL MAP: 5479A UNIT: METWA E01\r\n\r\n
FALL 19500 SW MOUNTAIN HOME RD (SW HILLSBORO HW & SW SEIFFERT RD) SHE MAP: 6381D UNIT: E19 BR192\r\n\r\n
MVA-INJURY ACCID NW EVERGREEN PK/NW TOWN CENTE DR (18299 EVERGREEN/2551 TOWN CENTER) HIL MAP: 5285C UNIT: METWC E64 C1INFO\r\n\r\n
FALL 19500 SW MOUNTAIN HOME RD (SW HILLSBORO HW & SW SEIFFERT RD) SHE MAP: 6381D UNIT: METWA E19\r\n\r\n
BACK PAIN 1132 NE TURNER DR (NE GRANT ST & NE CORNELL RD) HIL MAP: 5379D UNIT: METWA E01\r\n\r\n
SICK PERSON C1 2223 SE TUALATIN VALLEY HW (SE MINTER BRIDGE RD & SE 24TH AV) HIL MAP: 5480D UNIT: METWA E02\r\n\r\n

*/

public class ORWashingtonCountyAParserTest extends BaseParserTest {
  
  public ORWashingtonCountyAParserTest() {
    setParser(new ORWashingtonCountyAParser(), "WASHINGTON COUNTY", "OR");
  }
  
  @Test
  public void testParser() {
    doTest("T1",
        "MVA-UKN INJURY SW TUALATIN VALLEY HW/SW331ST AV (33098 TUALATIN VALLEY/331ST) HIL MAP: 5377D UNIT: C2 E01 R8",
        "CALL:MVA-UKN INJURY",
        "ADDR:SW TUALATIN VALLEY HW & SW331ST AV",
        "MADDR:SW TUALATIN VALLEY HWY & SW331ST AVE",
        "X:33098 TUALATIN VALLEY/331ST",
        "CITY:HILLSBORO",
        "MAP:5377D",
        "UNIT:C2 E01 R8");
        
    doTest("T2",
        "ABDOMINAL PAIN 985 N DAVIS ST (DEAD END & N 10TH AV) CON MAP: 5375D UNIT: METWA STA8",
        "CALL:ABDOMINAL PAIN",
        "ADDR:985 N DAVIS ST",
        "X:DEAD END & N 10TH AV",
        "CITY:CORNELIUS",
        "MAP:5375D",
        "UNIT:METWA STA8");

    doTest("T3",
        "BREATHING PROB 39470 SW GEIGER RD (SW LAFOLLETT RD & FERN HILL RD) CON MAP: 5574B UNIT: METWA STA8",
        "CALL:BREATHING PROB",
        "ADDR:39470 SW GEIGER RD",
        "X:SW LAFOLLETT RD & FERN HILL RD",
        "CITY:CORNELIUS",
        "MAP:5574B",
        "UNIT:METWA STA8");

    doTest("T4",
        "SICK PERSON/UNKO 1045 N ADAIR ST (N 10TH AV & N 10TH-11TH AL) CON MAP: 5375D UNIT: METWA STA8",
        "CALL:SICK PERSON/UNKO",
        "ADDR:1045 N ADAIR ST",
        "X:N 10TH AV & N 10TH-11TH AL",
        "CITY:CORNELIUS",
        "MAP:5375D",
        "UNIT:METWA STA8");

    doTest("T5",
        "RESIDENTIAL FIRE 822 N 28TH AV (N HOLLADAY DR & N 27TH AV) CON MAP: 5376D UNIT: METWA TIMERC CAS8 CFDUTY SIRN08 E813 E814 E815 E421 R8 T03 COCB1",
        "CALL:RESIDENTIAL FIRE",
        "ADDR:822 N 28TH AV",
        "MADDR:822 N 28TH AVE",
        "X:N HOLLADAY DR & N 27TH AV",
        "CITY:CORNELIUS",
        "MAP:5376D",
        "UNIT:METWA TIMERC CAS8 CFDUTY SIRN08 E813 E814 E815 E421 R8 T03 COCB1");
  }
  
  @Test
  public void testClackamasCounty() {

    doTest("T1",
        "    UNCON/FAINTING 37905 SE SERBAN RD (SE BLUFF RD & SE BAUMBACK RD) SAN MAP: 6013B UNIT: E74 M1",
        "CALL:UNCON/FAINTING",
        "ADDR:37905 SE SERBAN RD",
        "X:SE BLUFF RD & SE BAUMBACK RD",
        "CITY:SANDY",
        "MAP:6013B",
        "UNIT:E74 M1");
  }
  
  @Test
  public void testZakDelair() {

    doTest("T1",
        "UNK PROB/MN DOWN 95 82ND DR (SAFEWAY (GLADSTONE)) GLA MAP: 6398D UNIT: R101 M1",
        "CALL:UNK PROB/MN DOWN",
        "ADDR:95 82ND DR",
        "X:SAFEWAY (GLADSTONE)",
        "CITY:GLADSTONE",
        "MAP:6398D",
        "UNIT:R101 M1");

  }
  
  @Test
  public void testActive911A() {

    doTest("T1",
        "[] PRE NOTIFICATION 59185 E CHALET PL (DEAD END & E EAST RD) SAN MAP: 6324A UNIT: SQ252 SQ251 M1 2509\r\n\r\n\n",
        "CALL:PRE NOTIFICATION",
        "ADDR:59185 E CHALET PL",
        "CITY:SANDY",
        "X:DEAD END & E EAST RD",
        "MAP:6324A",
        "UNIT:SQ252 SQ251 M1 2509");

    doTest("T2",
        "[] BREATHING PROB. 59185 E CHALET PL (DEAD END & E EAST RD) SAN MAP: 6324A UNIT: SQ252\r\n\r\n\n",
        "CALL:BREATHING PROB.",
        "ADDR:59185 E CHALET PL",
        "CITY:SANDY",
        "X:DEAD END & E EAST RD",
        "MAP:6324A",
        "UNIT:SQ252");

    doTest("T3",
        "[] PRE NOTIFICATION 59454 E SLEEPY HOLLOW DR (E EAST RD & E BARLOW TRAIL RD) SAN MAP: 6324B UNIT: SQ252 SQ251 M1 2509\r\n\r\n\n",
        "CALL:PRE NOTIFICATION",
        "ADDR:59454 E SLEEPY HOLLOW DR",
        "CITY:SANDY",
        "X:E EAST RD & E BARLOW TRAIL RD",
        "MAP:6324B",
        "UNIT:SQ252 SQ251 M1 2509");

    doTest("T4",
        "[] PRE NOTIFICATION 27160 E MARION RD (E ROAD 10 & E ROAD 20) RHO MAP: 6731A UNIT: SQ252 M1 3709\r\n\r\n\n",
        "CALL:PRE NOTIFICATION",
        "ADDR:27160 E MARION RD",
        "CITY:RHODODENDRON",
        "X:E ROAD 10 & E ROAD 20",
        "MAP:6731A",
        "UNIT:SQ252 M1 3709");

    doTest("T5",
        "[] PRE NOTIFICATION 59454 E SLEEPY HOLLOW DR (E EAST RD & E BARLOW TRAIL RD) SAN MAP: 6324B UNIT: SQ252 SQ251 M1 2509\r\n\r\n\n",
        "CALL:PRE NOTIFICATION",
        "ADDR:59454 E SLEEPY HOLLOW DR",
        "CITY:SANDY",
        "X:E EAST RD & E BARLOW TRAIL RD",
        "MAP:6324B",
        "UNIT:SQ252 SQ251 M1 2509");

    doTest("T6",
        "[] UNKNOWN TYP FIRE 31315 E MULTORPOR DR (SKI BOWL (EAST)) GOV MAP: 6939 UNIT: E251 E254 2509\r\n\r\n\n",
        "CALL:UNKNOWN TYP FIRE",
        "ADDR:31315 E MULTORPOR DR",
        "CITY:GOVERNMENT CAMP",
        "X:SKI BOWL (EAST)",
        "MAP:6939",
        "UNIT:E251 E254 2509");

    doTest("T7",
        "[] FIRE ALARM, COMM 30521 E MELDRUM ST (E LIGE LN & E GOVERNMENT CAMP LP) GOV MAP: 6939 UNIT: E251 E254 WT251 2509\r\n\r\n\n",
        "CALL:FIRE ALARM, COMM",
        "ADDR:30521 E MELDRUM ST",
        "CITY:GOVERNMENT CAMP",
        "X:E LIGE LN & E GOVERNMENT CAMP LP",
        "MAP:6939",
        "UNIT:E251 E254 WT251 2509");

  }
  
  @Test
  public void testHillsboro() {

    doTest("T1",
        "SEIZURES 7545 SE TUALATIN VALLEY HW (SE 75TH AV & SW CORNELIUS PASS RD) HIL UNIT: MW64 E02\r\n\r\n",
        "CALL:SEIZURES",
        "ADDR:7545 SE TUALATIN VALLEY HW",
        "MADDR:7545 SE TUALATIN VALLEY HWY",
        "CITY:HILLSBORO",
        "X:SE 75TH AV & SW CORNELIUS PASS RD",
        "UNIT:MW64 E02");

    doTest("T2",
        "SICK PERSON/UNKO 650 SE OAK ST #410 (SE 6TH AV & SE 7TH AV) HIL UNIT: METWA E01\r\n\r\n",
        "CALL:SICK PERSON/UNKO",
        "ADDR:650 SE OAK ST",
        "APT:410",
        "CITY:HILLSBORO",
        "X:SE 6TH AV & SE 7TH AV",
        "UNIT:METWA E01");

    doTest("T3",
        "MENTAL/PSYCH 537 SE 14TH AV #C (SE WALNUT ST & SE CEDAR ST) HIL UNIT: METWC E01\r\n\r\n",
        "CALL:MENTAL/PSYCH",
        "ADDR:537 SE 14TH AV",
        "MADDR:537 SE 14TH AVE",
        "APT:C",
        "CITY:HILLSBORO",
        "X:SE WALNUT ST & SE CEDAR ST",
        "UNIT:METWC E01");

    doTest("T4",
        "FALL C1 511 NE 37TH AV (NE LAURA ST & DEAD END) HIL UNIT: METWA E02\r\n\r\n",
        "CALL:FALL C1",
        "ADDR:511 NE 37TH AV",
        "MADDR:511 NE 37TH AVE",
        "CITY:HILLSBORO",
        "X:NE LAURA ST & DEAD END",
        "UNIT:METWA E02");

    doTest("T5",
        "HEADACHE 1178 SE 13TH AV (SE SPRUCE ST & SE RIVER RD) HIL UNIT: METWA E01\r\n\r\n",
        "CALL:HEADACHE",
        "ADDR:1178 SE 13TH AV",
        "MADDR:1178 SE 13TH AVE",
        "CITY:HILLSBORO",
        "X:SE SPRUCE ST & SE RIVER RD",
        "UNIT:METWA E01");

    doTest("T6",
        "CHEST PAIN/HEART 2501 NW 229TH AV (NE BUTLER ST & NW EVERGREEN PK) HIL UNIT: METWA E03\r\n\r\n",
        "CALL:CHEST PAIN/HEART",
        "ADDR:2501 NW 229TH AV",
        "MADDR:2501 NW 229TH AVE",
        "CITY:HILLSBORO",
        "X:NE BUTLER ST & NW EVERGREEN PK",
        "UNIT:METWA E03");

    doTest("T7",
        "MVA-INJURY ACCID 7355-BLK SE TUALATIN VALLEY HW (SE 73RD AV & SE 75TH AV) HIL UNIT: METWA MED62 C1INFO\r\n\r\n",
        "CALL:MVA-INJURY ACCID",
        "ADDR:7355-BLK SE TUALATIN VALLEY HW",
        "MADDR:7355 SE TUALATIN VALLEY HWY",
        "CITY:HILLSBORO",
        "X:SE 73RD AV & SE 75TH AV",
        "UNIT:METWA MED62 C1INFO");

    doTest("T8",
        "BACK PAIN C1 971 NE SHELLY CT (NE 9TH PL & DEAD END) HIL UNIT: E02 E05\r\n\r\n",
        "CALL:BACK PAIN C1",
        "ADDR:971 NE SHELLY CT",
        "CITY:HILLSBORO",
        "X:NE 9TH PL & DEAD END",
        "UNIT:E02 E05");

    doTest("T9",
        "BREATHING PROB 1241 SE WALNUT ST #F (SE 12TH AV & SE 13TH AV) HIL UNIT: METWA E01\r\n\r\n",
        "CALL:BREATHING PROB",
        "ADDR:1241 SE WALNUT ST",
        "APT:F",
        "CITY:HILLSBORO",
        "X:SE 12TH AV & SE 13TH AV",
        "UNIT:METWA E01");

    doTest("T10",
        "SEIZURES 1255 NE 48TH AV (NE CORNELL RD & NE AIRPORT RD) HIL UNIT: METWA E03\r\n\r\n",
        "CALL:SEIZURES",
        "ADDR:1255 NE 48TH AV",
        "MADDR:1255 NE 48TH AVE",
        "CITY:HILLSBORO",
        "X:NE CORNELL RD & NE AIRPORT RD",
        "UNIT:METWA E03");

    doTest("T11",
        "FALL 10025 NW GLENCOE RD (NW HIGHLAND CT & NW PACIFIC ST) NOR UNIT: METWA E17\r\n\r\n",
        "CALL:FALL",
        "ADDR:10025 NW GLENCOE RD",
        "CITY:NORTH PLAINS",
        "X:NW HIGHLAND CT & NW PACIFIC ST",
        "UNIT:METWA E17");

    doTest("T12",
        "FALL 1221 NE 51ST AV #127 (NE WINDROW ST & NE FARMCREST ST) HIL UNIT: METWA E03\r\n\r\n",
        "CALL:FALL",
        "ADDR:1221 NE 51ST AV",
        "MADDR:1221 NE 51ST AVE",
        "APT:127",
        "CITY:HILLSBORO",
        "X:NE WINDROW ST & NE FARMCREST ST",
        "UNIT:METWA E03");

    doTest("T13",
        "FALL 2000 SE 30TH AV (SE 32ND AV & SE TUALATIN VALLEY HW) HIL MAP: 5580B UNIT: METWA E02\r\n\r\n",
        "CALL:FALL",
        "ADDR:2000 SE 30TH AV",
        "MADDR:2000 SE 30TH AVE",
        "CITY:HILLSBORO",
        "X:SE 32ND AV & SE TUALATIN VALLEY HW",
        "MAP:5580B",
        "UNIT:METWA E02");

    doTest("T14",
        "FALL 1470 SE WALNUT ST #109 (SE 14TH AV & SE 15TH AV) HIL MAP: 5480A UNIT: METWA E01\r\n\r\n",
        "CALL:FALL",
        "ADDR:1470 SE WALNUT ST",
        "APT:109",
        "CITY:HILLSBORO",
        "X:SE 14TH AV & SE 15TH AV",
        "MAP:5480A",
        "UNIT:METWA E01");

    doTest("T15",
        "BREATHING PROB 7136 NE RONLER WY #2718 (NE BUTLER ST & NE RONLER WY) HIL MAP: 5283C UNIT: METWA E06\r\n\r\n",
        "CALL:BREATHING PROB",
        "ADDR:7136 NE RONLER WY",
        "MADDR:7136 NE RONLER WAY",
        "APT:2718",
        "CITY:HILLSBORO",
        "X:NE BUTLER ST & NE RONLER WY",
        "MAP:5283C",
        "UNIT:METWA E06");

    doTest("T16",
        "SICK PERSON C1 3120 NW JOHN OLSEN AV #13102 (NW OVERLOOK DR & NW EVERGREEN PK) HIL MAP: 5284A UNIT: METWA E03\r\n\r\n",
        "CALL:SICK PERSON C1",
        "ADDR:3120 NW JOHN OLSEN AV",
        "MADDR:3120 NW JOHN OLSEN AVE",
        "APT:13102",
        "CITY:HILLSBORO",
        "X:NW OVERLOOK DR & NW EVERGREEN PK",
        "MAP:5284A",
        "UNIT:METWA E03");

    doTest("T17",
        "FIRE ALARM, COMM 720 SE WASHINGTON ST (SE 7TH AV & SE 8TH AV) HIL MAP: 5379D UNIT: E01\r\n\r\n",
        "CALL:FIRE ALARM, COMM",
        "ADDR:720 SE WASHINGTON ST",
        "CITY:HILLSBORO",
        "X:SE 7TH AV & SE 8TH AV",
        "MAP:5379D",
        "UNIT:E01");

    doTest("T18",
        "SICK PERSON/UNKO 772 NW AUTUMNCREEK WY #O105 (NW FIELDCREST WY & NW TRAILWALK DR) BTN MAP: 5383D UNIT: METWA E06\r\n\r\n",
        "CALL:SICK PERSON/UNKO",
        "ADDR:772 NW AUTUMNCREEK WY",
        "MADDR:772 NW AUTUMNCREEK WAY",
        "APT:O105",
        "CITY:BEAVERTON",
        "X:NW FIELDCREST WY & NW TRAILWALK DR",
        "MAP:5383D",
        "UNIT:METWA E06");

    doTest("T19",
        "BREATHING PROB 351 SE 5TH AV (SE BASELINE ST & SE OAK ST) HIL MAP: 5479A UNIT: METWA E01\r\n\r\n",
        "CALL:BREATHING PROB",
        "ADDR:351 SE 5TH AV",
        "MADDR:351 SE 5TH AVE",
        "CITY:HILLSBORO",
        "X:SE BASELINE ST & SE OAK ST",
        "MAP:5479A",
        "UNIT:METWA E01");

    doTest("T20",
        "FALL C1 1377 SE DUKE DR (SE FIR GROVE LP & SE FLANDERS LN) HIL MAP: 5479D UNIT: METWA E02\r\n\r\n",
        "CALL:FALL C1",
        "ADDR:1377 SE DUKE DR",
        "CITY:HILLSBORO",
        "X:SE FIR GROVE LP & SE FLANDERS LN",
        "MAP:5479D",
        "UNIT:METWA E02");

    doTest("T21",
        "BURN COMPLAINT 336 NW MERLE DR (NW 3RD AV & NW 4TH AV) HIL MAP: 5278D UNIT: E01\r\n\r\n",
        "CALL:BURN COMPLAINT",
        "ADDR:336 NW MERLE DR",
        "CITY:HILLSBORO",
        "X:NW 3RD AV & NW 4TH AV",
        "MAP:5278D",
        "UNIT:E01");

    doTest("T22",
        "FALL C1 2731 SE LONNY CT (SE RIVER RD & DEAD END) HIL MAP: 5580B UNIT: METWC E02\r\n\r\n",
        "CALL:FALL C1",
        "ADDR:2731 SE LONNY CT",
        "CITY:HILLSBORO",
        "X:SE RIVER RD & DEAD END",
        "MAP:5580B",
        "UNIT:METWC E02");

    doTest("T23",
        "ELECTRICAL FIRE 20820 SW REGAL CT (DEAD END & SW ANTHONY DR) BTN MAP: 5583B UNIT: E02\r\n\r\n",
        "CALL:ELECTRICAL FIRE",
        "ADDR:20820 SW REGAL CT",
        "CITY:BEAVERTON",
        "X:DEAD END & SW ANTHONY DR",
        "MAP:5583B",
        "UNIT:E02");

    doTest("T24",
        "CHEST PAIN/HEART 617 SE 2ND AV (DEAD END & SE CEDAR-MAPLE AL) HIL MAP: 5479A UNIT: METWA E01\r\n\r\n",
        "CALL:CHEST PAIN/HEART",
        "ADDR:617 SE 2ND AV",
        "MADDR:617 SE 2ND AVE",
        "CITY:HILLSBORO",
        "X:DEAD END & SE CEDAR-MAPLE AL",
        "MAP:5479A",
        "UNIT:METWA E01");

    doTest("T25",
        "FALL 19500 SW MOUNTAIN HOME RD (SW HILLSBORO HW & SW SEIFFERT RD) SHE MAP: 6381D UNIT: E19 BR192\r\n\r\n",
        "CALL:FALL",
        "ADDR:19500 SW MOUNTAIN HOME RD",
        "CITY:SHERWOOD",
        "X:SW HILLSBORO HW & SW SEIFFERT RD",
        "MAP:6381D",
        "UNIT:E19 BR192");

    doTest("T26",
        "MVA-INJURY ACCID NW EVERGREEN PK/NW TOWN CENTE DR (18299 EVERGREEN/2551 TOWN CENTER) HIL MAP: 5285C UNIT: METWC E64 C1INFO\r\n\r\n",
        "CALL:MVA-INJURY ACCID",
        "ADDR:NW EVERGREEN PK & NW TOWN CENTE DR",
        "MADDR:NW EVERGREEN PARKWAY & NW TOWN CENTE DR",
        "CITY:HILLSBORO",
        "X:18299 EVERGREEN/2551 TOWN CENTER",
        "MAP:5285C",
        "UNIT:METWC E64 C1INFO");

    doTest("T27",
        "FALL 19500 SW MOUNTAIN HOME RD (SW HILLSBORO HW & SW SEIFFERT RD) SHE MAP: 6381D UNIT: METWA E19\r\n\r\n",
        "CALL:FALL",
        "ADDR:19500 SW MOUNTAIN HOME RD",
        "CITY:SHERWOOD",
        "X:SW HILLSBORO HW & SW SEIFFERT RD",
        "MAP:6381D",
        "UNIT:METWA E19");

    doTest("T28",
        "BACK PAIN 1132 NE TURNER DR (NE GRANT ST & NE CORNELL RD) HIL MAP: 5379D UNIT: METWA E01\r\n\r\n",
        "CALL:BACK PAIN",
        "ADDR:1132 NE TURNER DR",
        "CITY:HILLSBORO",
        "X:NE GRANT ST & NE CORNELL RD",
        "MAP:5379D",
        "UNIT:METWA E01");

    doTest("T29",
        "SICK PERSON C1 2223 SE TUALATIN VALLEY HW (SE MINTER BRIDGE RD & SE 24TH AV) HIL MAP: 5480D UNIT: METWA E02\r\n\r\n",
        "CALL:SICK PERSON C1",
        "ADDR:2223 SE TUALATIN VALLEY HW",
        "MADDR:2223 SE TUALATIN VALLEY HWY",
        "CITY:HILLSBORO",
        "X:SE MINTER BRIDGE RD & SE 24TH AV",
        "MAP:5480D",
        "UNIT:METWA E02");

    doTest("T30",
        "FALL 5605 NE ELAM YOUNG PK (NE ORENCO GARDENS DR & NE RAY CI) HIL MAP: 5382A UNIT: METWA E03\r\n\r\n",
        "CALL:FALL",
        "ADDR:5605 NE ELAM YOUNG PK",
        "MADDR:5605 NE ELAM YOUNG PARKWAY",
        "CITY:HILLSBORO",
        "X:NE ORENCO GARDENS DR & NE RAY CI",
        "MAP:5382A",
        "UNIT:METWA E03");

  }
  
  public static void main(String[] args) {
    new ORWashingtonCountyAParserTest().generateTests("T1");
  }
}
