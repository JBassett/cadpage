package net.anei.cadpage.parsers.AL;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;

/*
Lauderdale County, AL
Contact: Justin Gallien <jgfireemt@gmail.com>
Sender: 911paging@florenceal.org

PRI: KLEMS AD: 1688 COUNTY RD 31 CNTY  TIME: 21:12:32 EV: MD-BREATHING -:  CS1: DIXIELAND DR CS2: COUNTY RD 417 RE:  34YOM CHEST PAIN DIFFICULTY BREATHING
PRI: KLEMS AD: 101 HAYGOOD CIR CNTY  TIME: 20:30:29 EV: MD-FALL-MINOR -:  CS1: HWY 43 CS2: HWY 43 RE:  ALT# 931-242-1174 -087.544169 +34.975694 83 YOF FALL POSSIBLE HIP INJURY\
PRI: KLEMS AD: 1001 HWY 72 KILL: @MCDONALDS  TIME: 00:31:04 EV: MD-SEIZURE -:  CS1:  CS2:  RE:  ALT# 256-702-9165 -087.585777 +34.846252 20YOF ACTIVE SIEZURE
PRI: KLEMS TIME: 00:57:11 EV: MD-TRAUMA -:  CS1: HWY 72 CS2: COUNTY RD 29 RE:  6657301
PRI: GHVFD AD: 3479 COUNTY RD 25 CNTY  TIME: 04:22:42 EV: FIRE-STRUCTURE -: OTHER CS1: WEST FORK DR CS2: PRIVATE DRIVE RE:  ALT# 256-702-4551 -087.535736 +34.930687 BUILDING ON FIRE
PRI: KLVFD AD: J C MAULDIN HWY KILL  TIME: 16:28:30 EV: WRECK-INJURY -: BLOCKAGE CS1: HWY 72 CS2: BRUSH CREEK RD RE:  ALT# 256-810-6502 -087.556701 +34.856937 CALLER REPORTING WRECK UNKNOWN INJURIES
PRI: KLEMS AD: 830 LOCKE SIX RD KILL: @KILLEN PARK  TIME: 09:00:31 EV: MD-UNCONSCIOUS -:  CS1: PEDEN ST CS2: BRUSH CREEK RD RE:  ALT# 256-762-4010 -087.556701 +34.856937 7 YEAR OLD MALE HAS PASSED OUT IS BREATHING WIL BE AT THE FIELD IN THE HOLE
PRI: KLVFD AD: 1161 HWY 72 KILL: @FOODLAND  TIME: 14:37:33 EV: MD-TRAUMA -: SNAKEBIT CS1:  CS2:  RE:  ALT# 256-247-8813 -087.390038 +34.846100 CALLER ADVISED HER SON HAS BEEN BITTEN BY A COPPERHEAD SNAKE 38 YEAR OLD MALE HAS BEEN BITTEN ON THE HAND WILL BE DRIVING A GREEN PATHFINDER
PRI: EMA AD: LL(-87:39:45.5030,34:47:57.4231): @WEATHER  TIME: 01:55:09 EV: WEATHER -: TSTORM-WARNG CS1:  CS2:  RE:  SPECIAL ADDRESS COMMENT: no ad SEVERE THUNDERSTORM WARNING TIL 2:30AM

Contact: Tim Anerton <TAnerton@florenceal.org>
(IPS I/Page) PRI: FFR AD: 205 MARENGO ST FLOR: @ECM  TIME: 17:35:34 EV: FD-SPILL -: FUEL CS1: W ALABAMA ST CS2: W COLLEGE ST RE:  SPECIAL ADDRESS COMMENT:

Contact: Chris Phillips <cpffemt@aol.com>
Sender: 911Paging@florenceal.org
(IPS I/Page) PRI: KLEMS AD: 1650 HWY 72 KILL: @KILLEN POST OFFICE  TIME: 16:21:16 EV: WRECK-INJURY -: BLOCKAGE CS1: BRUSH CREEK RD CS2: POPLAR ST RE:  ALT#

 */


public class ALLauderdaleCountyParserTest extends BaseParserTest {
  
  public ALLauderdaleCountyParserTest() {
    setParser(new ALLauderdaleCountyParser(), "LAUDERDALE COUNTY", "AL");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "PRI: KLEMS AD: 1688 COUNTY RD 31 CNTY  TIME: 21:12:32 EV: MD-BREATHING -:  CS1: DIXIELAND DR CS2: COUNTY RD 417 RE:  34YOM CHEST PAIN DIFFICULTY BREATHING",
        "SRC:KLEMS",
        "ADDR:1688 COUNTY RD 31",
        "CALL:MD-BREATHING",
        "X:DIXIELAND DR & COUNTY RD 417",
        "INFO:34YOM CHEST PAIN DIFFICULTY BREATHING");

    doTest("T2",
        "PRI: KLEMS AD: 101 HAYGOOD CIR CNTY  TIME: 20:30:29 EV: MD-FALL-MINOR -:  CS1: HWY 43 CS2: HWY 43 RE:  ALT# 931-242-1174 -087.544169 +34.975694 83 YOF FALL POSSIBLE HIP INJURY\\",
        "SRC:KLEMS",
        "ADDR:101 HAYGOOD CIR",
        "CALL:MD-FALL-MINOR",
        "X:HWY 43 & HWY 43",
        "PHONE:931-242-1174",
        "GPS:-087.544169 +34.975694",
        "INFO:83 YOF FALL POSSIBLE HIP INJURY\\");

    doTest("T3",
        "PRI: KLEMS AD: 1001 HWY 72 KILL: @MCDONALDS  TIME: 00:31:04 EV: MD-SEIZURE -:  CS1:  CS2:  RE:  ALT# 256-702-9165 -087.585777 +34.846252 20YOF ACTIVE SIEZURE",
        "SRC:KLEMS",
        "ADDR:1001 HWY 72",
        "CITY:KILLEN",
        "PLACE:MCDONALDS",
        "CALL:MD-SEIZURE",
        "PHONE:256-702-9165",
        "GPS:-087.585777 +34.846252",
        "INFO:20YOF ACTIVE SIEZURE");

    doTest("T4",
        "PRI: KLEMS TIME: 00:57:11 EV: MD-TRAUMA -:  CS1: HWY 72 CS2: COUNTY RD 29 RE:  6657301",
        "SRC:KLEMS",
        "ADDR:HWY 72 & COUNTY RD 29",
        "CALL:MD-TRAUMA",
        "INFO:6657301");

    doTest("T5",
        "PRI: GHVFD AD: 3479 COUNTY RD 25 CNTY  TIME: 04:22:42 EV: FIRE-STRUCTURE -: OTHER CS1: WEST FORK DR CS2: PRIVATE DRIVE RE:  ALT# 256-702-4551 -087.535736 +34.930687 BUILDING ON FIRE",
        "SRC:GHVFD",
        "ADDR:3479 COUNTY RD 25",
        "CALL:FIRE-STRUCTURE - OTHER",
        "X:WEST FORK DR & PRIVATE DRIVE",
        "PHONE:256-702-4551",
        "GPS:-087.535736 +34.930687",
        "INFO:BUILDING ON FIRE");

    doTest("T6",
        "PRI: KLVFD AD: J C MAULDIN HWY KILL  TIME: 16:28:30 EV: WRECK-INJURY -: BLOCKAGE CS1: HWY 72 CS2: BRUSH CREEK RD RE:  ALT# 256-810-6502 -087.556701 +34.856937 CALLER REPORTING WRECK UNKNOWN INJURIES",
        "SRC:KLVFD",
        "ADDR:J C MAULDIN HWY",
        "MADDR:J C MAULDIN HWY & HWY 72",
        "CITY:KILLEN",
        "CALL:WRECK-INJURY - BLOCKAGE",
        "X:HWY 72 & BRUSH CREEK RD",
        "PHONE:256-810-6502",
        "GPS:-087.556701 +34.856937",
        "INFO:CALLER REPORTING WRECK UNKNOWN INJURIES");

    doTest("T7",
        "PRI: KLEMS AD: 830 LOCKE SIX RD KILL: @KILLEN PARK  TIME: 09:00:31 EV: MD-UNCONSCIOUS -:  CS1: PEDEN ST CS2: BRUSH CREEK RD RE:  ALT# 256-762-4010 -087.556701 +34.856937 7 YEAR OLD MALE HAS PASSED OUT IS BREATHING WIL BE AT THE FIELD IN THE HOLE",
        "SRC:KLEMS",
        "ADDR:830 LOCKE SIX RD",
        "CITY:KILLEN",
        "PLACE:KILLEN PARK",
        "CALL:MD-UNCONSCIOUS",
        "X:PEDEN ST & BRUSH CREEK RD",
        "PHONE:256-762-4010",
        "GPS:-087.556701 +34.856937",
        "INFO:7 YEAR OLD MALE HAS PASSED OUT IS BREATHING WIL BE AT THE FIELD IN THE HOLE");

    doTest("T8",
        "PRI: KLVFD AD: 1161 HWY 72 KILL: @FOODLAND  TIME: 14:37:33 EV: MD-TRAUMA -: SNAKEBIT CS1:  CS2:  RE:  ALT# 256-247-8813 -087.390038 +34.846100 CALLER ADVISED HER SON HAS BEEN BITTEN BY A COPPERHEAD SNAKE 38 YEAR OLD MALE HAS BEEN BITTEN ON THE HAND WILL BE DRIVING A GREEN PATHFINDER",
        "SRC:KLVFD",
        "ADDR:1161 HWY 72",
        "CITY:KILLEN",
        "PLACE:FOODLAND",
        "CALL:MD-TRAUMA - SNAKEBIT",
        "PHONE:256-247-8813",
        "GPS:-087.390038 +34.846100",
        "INFO:CALLER ADVISED HER SON HAS BEEN BITTEN BY A COPPERHEAD SNAKE 38 YEAR OLD MALE HAS BEEN BITTEN ON THE HAND WILL BE DRIVING A GREEN PATHFINDER");

    doTest("T9",
        "PRI: EMA AD: LL(-87:39:45.5030,34:47:57.4231): @WEATHER  TIME: 01:55:09 EV: WEATHER -: TSTORM-WARNG CS1:  CS2:  RE:  SPECIAL ADDRESS COMMENT: no ad SEVERE THUNDERSTORM WARNING TIL 2:30AM",
        "SRC:EMA",
        "ADDR:LL(-87:39:45.5030,34:47:57.4231)",
        "MADDR:34.79928419444444,-87.66263972222222",
        "PLACE:WEATHER",
        "CALL:WEATHER - TSTORM-WARNG",
        "INFO:SPECIAL ADDRESS COMMENT: no ad SEVERE THUNDERSTORM WARNING TIL 2:30AM");
        
  }
  
  @Test
  public void testFlorence() {

    doTest("T1",
        "(IPS I/Page) PRI: FFR AD: 205 MARENGO ST FLOR: @ECM  TIME: 17:35:34 EV: FD-SPILL -: FUEL CS1: W ALABAMA ST CS2: W COLLEGE ST RE:  SPECIAL ADDRESS COMMENT:",
        "SRC:FFR",
        "ADDR:205 MARENGO ST",
        "CITY:FLORENCE",
        "PLACE:ECM",
        "CALL:FD-SPILL - FUEL",
        "X:W ALABAMA ST & W COLLEGE ST",
        "INFO:SPECIAL ADDRESS COMMENT:");

  }
  
  @Test
  public void testChrisPhilips() {

    doTest("T1",
        "(IPS I/Page) PRI: KLEMS AD: 1650 HWY 72 KILL: @KILLEN POST OFFICE  TIME: 16:21:16 EV: WRECK-INJURY -: BLOCKAGE CS1: BRUSH CREEK RD CS2: POPLAR ST RE:  ALT#",
        "SRC:KLEMS",
        "ADDR:1650 HWY 72",
        "CITY:KILLEN",
        "PLACE:KILLEN POST OFFICE",
        "CALL:WRECK-INJURY - BLOCKAGE",
        "X:BRUSH CREEK RD & POPLAR ST",
        "INFO:ALT#");

  }
  
  public static void main(String[] args) {
    new ALLauderdaleCountyParserTest().generateTests("T1");
  }
}