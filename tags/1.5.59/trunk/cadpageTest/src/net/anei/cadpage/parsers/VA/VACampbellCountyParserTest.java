package net.anei.cadpage.parsers.VA;

import net.anei.cadpage.parsers.BaseParserTest;
import net.anei.cadpage.parsers.VA.VACampbellCountyParser;

import org.junit.Test;


public class VACampbellCountyParserTest extends BaseParserTest {
  
  public VACampbellCountyParserTest() {
    setParser(new VACampbellCountyParser(), "CAMPBELL COUNTY", "VA");
  }
  
  @Test
  public void testParser() {
    doTest("T1",
        "MAILBOX:SQ02 MVC/MOTOR VEHICLE CRASH/ACCIDE 002364 WATERLICK RD/LEESVILLE RD CFS# 2010-061119 REF TO A 3 CAR MVA. AT FIRST A REPORT OF NO PI. NO NEED FOR FIRE. VASI ADV THAT T" ,
        "SRC:SQ02",
        "CALL:MVC/MOTOR VEHICLE CRASH/ACCIDE",
        "ADDR:2364 WATERLICK RD & LEESVILLE RD",
        "MADDR:2364 WATERLICK RD",
        "ID:2010-061119",
        "INFO:REF TO A 3 CAR MVA. AT FIRST A REPORT OF NO PI. NO NEED FOR FIRE. VASI ADV THAT T"
        );
    doTest("T2",
        "MAILBOX:SQ02 (3)ACCIDENT 021400 TIMBERLAKE RD CFS# 2010-061105 2 CAR MVA. WITH ENTRAPMENT",
        "SRC:SQ02",
        "CALL:(3)ACCIDENT",
        "ADDR:21400 TIMBERLAKE RD",
        "ID:2010-061105",
        "INFO:2 CAR MVA. WITH ENTRAPMENT"
      );
    doTest("T3",
        "MAILBOX:SQ02 STABBING 005450 COLONIAL HWY EVI CFS# 2010-061047 STAGE IN THE AREA, DO NOT GO TO THE SCENE",
        "SRC:SQ02",
        "CALL:STABBING",
        "ADDR:5450 COLONIAL HWY",
        "CITY:EVINGTON",
        "ID:2010-061047",
        "INFO:STAGE IN THE AREA, DO NOT GO TO THE SCENE"
      );
    doTest("T4",
        "MAILBOX:SQ02 UNRESPONSIVE 000218 LAKE FOREST DR CFS# 2010-060825",
        "SRC:SQ02",
        "CALL:UNRESPONSIVE",
        "ADDR:218 LAKE FOREST DR",
        "ID:2010-060825"
      );
    doTest("T5",
        "MAILBOX:SQ02 HANGUP 911 000381 HORIZON DR CFS# 2010-060777 FEMALE ADVISED SOMETHING ABOUT THE RESCUE SQ. LINE DISCONNECTED",
        "SRC:SQ02",
        "CALL:HANGUP 911",
        "ADDR:381 HORIZON DR",
        "ID:2010-060777",
        "INFO:FEMALE ADVISED SOMETHING ABOUT THE RESCUE SQ. LINE DISCONNECTED");

    doTest("T6",
        "MAILBOX:CO12 BRUSH/FIELD FIRE 000175 WOODHAVEN DR CFS# 2011-024304",
        "SRC:CO12",
        "CALL:BRUSH/FIELD FIRE",
        "ADDR:175 WOODHAVEN DR",
        "ID:2011-024304");

    doTest("T7",
        "S: M:MAILBOX:CO13 POWER IN GENERATOR @FIRE STATION IS OUT. DOORS HAVE TO BE OPENED MANUALLY PER CHIEF 13\n",
        "SRC:CO13",
        "CALL:POWER IN GENERATOR",
        "ADDR:FIRE STATION IS OUT DOORS HAVE TO BE OPENED MANUALLY PER CHIEF 13");
  }
  
  @Test
  public void testMarkMoss() {

    doTest("T1",
        "MAILBOX:SQ01 HEADACHE 002243 BEDFORD HWY LYN CFS# 2012-018620\r",
        "SRC:SQ01",
        "CALL:HEADACHE",
        "CITY:LYNCH STATION",
        "ADDR:2243 BEDFORD HWY",
        "ID:2012-018620");

    doTest("T2",
        "MAILBOX:SQ01 PAIN 000149 BEALE RD LYN CFS# 2012-018617 76 YO F PAIN IN NECK AND SHOULDER HX HIGH BLOOD PRESSURE BLOCKAGE IN LEFT SIDE PRI 2\r",
        "SRC:SQ01",
        "CALL:PAIN",
        "CITY:LYNCH STATION",
        "ADDR:149 BEALE RD",
        "ID:2012-018617",
        "INFO:76 YO F PAIN IN NECK AND SHOULDER HX HIGH BLOOD PRESSURE BLOCKAGE IN LEFT SIDE PRI 2");

    doTest("T3",
        "MAILBOX:SQ01 MENTAL SUBJECT 001011 7TH ST ALT CFS# 2012-018608 ASSIST APD W/ A MENTAL SUBJECT PRIORITY 3\r",
        "SRC:SQ01",
        "CALL:MENTAL SUBJECT",
        "CITY:ALTAVISTA",
        "ADDR:1011 7TH ST",
        "ID:2012-018608",
        "INFO:ASSIST APD W/ A MENTAL SUBJECT PRIORITY 3");

    doTest("T4",
        "MAILBOX:SQ01 MUTUAL AID (OUTSIDE OF COUNTY 000901 N MAIN ST- GRETNA CFS# 2012-018626 MEADOWBROOK TRAILER PARK --LOT 4 33 Y/O F CHEST PAIN pri\r",
        "SRC:SQ01",
        "CALL:MUTUAL AID (OUTSIDE OF COUNTY",
        "ADDR:901 N MAIN ST",
        "CITY:GRETNA",
        "ID:2012-018626",
        "INFO:MEADOWBROOK TRAILER PARK --LOT 4 33 Y/O F CHEST PAIN pri");

    doTest("T5",
        "MAILBOX:SQ01 CHEST PAIN (NO CARDIAC HISTORY 001280 MAIN ST A ALT CFS# 2012-018645\r",
        "SRC:SQ01",
        "CALL:CHEST PAIN (NO CARDIAC HISTORY",
        "CITY:ALTAVISTA",
        "ADDR:1280 MAIN ST A",
        "ID:2012-018645");
  }
  
  @Test
  public void testDavidBishiop() {
 

    doTest("T1",
        "MAILBOX:SQ02 STROKE 001468 LYNBROOK RD RUS CFS# 2012-037665 45 YO FEMALE POSSIBLE STROKE LEFT SIDE PAIN IN SHOULDER AND BACK OF HEAD NUMBNESS Dispatcher:L267@08/0",
        "SRC:SQ02",
        "CALL:STROKE",
        "ADDR:1468 LYNBROOK RD",
        "CITY:RUSTBURG",
        "ID:2012-037665",
        "INFO:45 YO FEMALE POSSIBLE STROKE LEFT SIDE PAIN IN SHOULDER AND BACK OF HEAD NUMBNESS Dispatcher:L267@08/0");
 }
  
  public static void main(String[] args) {
    new VACampbellCountyParserTest().generateTests("T1", "SRC CALL CITY ADDR ID INFO");
  }
}
  