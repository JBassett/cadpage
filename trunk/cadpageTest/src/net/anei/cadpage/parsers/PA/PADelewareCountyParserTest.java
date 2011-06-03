package net.anei.cadpage.parsers.PA;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class PADelewareCountyParserTest extends BaseParserTest {
  
  public PADelewareCountyParserTest() {
    setParser(new PADelewareCountyParser(), "DELEWARE COUNTY", "PA");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "13:59*FIRE-NON - NON BLDG RESPONSE*643 CONCHESTER HWY*CHELSEA PKWY*610-859-8920,~ MULCH ON ISLAND NEAR STOP SIGN IN CORNER OF THE PARKING LOT",
        "CALL:FIRE-NON - NON BLDG RESPONSE",
        "ADDR:643 CONCHESTER HWY",
        "X:CHELSEA PKWY",
        "INFO:610-859-8920,~ MULCH ON ISLAND NEAR STOP SIGN IN CORNER OF THE PARKING LOT");

    doTest("T2",
        "13:42*ALARM -*785 CHERRY TREE RD B*HIDDEN VALLEY RD*OFFICE #610-494-8583,ESZ 406,APT B3 CMCST VOIP CALL,~ HALLWAY SMOKE DETECTOR,OFFICE #610-494-8583,ESZ 406,",
        "CALL:ALARM -",
        "ADDR:785 CHERRY TREE RD B",
        "X:HIDDEN VALLEY RD",
        "INFO:OFFICE #610-494-8583,ESZ 406,APT B3 CMCST VOIP CALL,~ HALLWAY SMOKE DETECTOR,OFFICE #610-494-8583,ESZ 406,");

    doTest("T3",
        "11:17*ALARM -*1702 CHICHESTER AV*LAUGHEAD AV*BUSINESS,484-490-5167,OWNER BEING NOTIFIED,ALARM CO NOW ADVISING THEY ARE GETTING A FALSE ALARM,UNSURE IF THEY HA",
        "CALL:ALARM -",
        "ADDR:1702 CHICHESTER AV",
        "X:LAUGHEAD AV",
        "INFO:BUSINESS,484-490-5167,OWNER BEING NOTIFIED,ALARM CO NOW ADVISING THEY ARE GETTING A FALSE ALARM,UNSURE IF THEY HA");

    doTest("T4",
        "14:19*FIRE-BLD - BUILDING RESPONSE*3386 CHICHESTER AV*THORNTON RD*610-485-8040 ESZ 405,~DEEP FRYER ON FIRE IN THE KITCHEN,SQ61, M100C, N13RIT,Location: 3386 C",
        "CALL:FIRE-BLD - BUILDING RESPONSE",
        "ADDR:3386 CHICHESTER AV",
        "X:THORNTON RD",
        "INFO:610-485-8040 ESZ 405,~DEEP FRYER ON FIRE IN THE KITCHEN,SQ61, M100C, N13RIT,Location: 3386 C");

    doTest("T5",
        "17:08*FIRE-HAZMAT - HAZMAT RESPONSE*OGDEN AV & NAAMANS CREEK RD*OGDEN AV*2 VEHS INVOLVED,PULLED OVER TO THE SIDE,2 VEHS,NAAMANS CREEK RD OGDEN AV, Cross Stree",
        "CALL:FIRE-HAZMAT - HAZMAT RESPONSE",
        "ADDR:OGDEN AV & NAAMANS CREEK RD",
        "X:OGDEN AV",
        "INFO:2 VEHS INVOLVED,PULLED OVER TO THE SIDE,2 VEHS,NAAMANS CREEK RD OGDEN AV, Cross Stree");

    doTest("T6",
        "10:22*ALARM -*1509 MEETINGHOUSE RD*2ND AV*610-497-2652,~ZONE 1,OBN, }",
        "CALL:ALARM -",
        "ADDR:1509 MEETINGHOUSE RD",
        "X:2ND AV",
        "INFO:610-497-2652,~ZONE 1,OBN, }");

    doTest("T7",
        "12:18*ALARM -*925 MEETINGHOUSE RD*LOCUST ST*610-485-6881,~GENERAL,OBN,",
        "CALL:ALARM -",
        "ADDR:925 MEETINGHOUSE RD",
        "X:LOCUST ST",
        "INFO:610-485-6881,~GENERAL,OBN,");

    doTest("T8",
        "13:42*ALARM -*785 CHERRY TREE RD B*HIDDEN VALLEY RD*OFFICE #610-494-8583,ESZ 406,APT B3 CMCST VOIP CALL,~ HALLWAY SMOKE DETECTOR,OFFICE #610-494-8583,ESZ 406,",
        "CALL:ALARM -",
        "ADDR:785 CHERRY TREE RD B",
        "X:HIDDEN VALLEY RD",
        "INFO:OFFICE #610-494-8583,ESZ 406,APT B3 CMCST VOIP CALL,~ HALLWAY SMOKE DETECTOR,OFFICE #610-494-8583,ESZ 406,");

    doTest("T9",
        "14:52*ALARM -*736 CARRIAGE CIR*CHERRY TREE RD*SCHATZ RES,~GEN OBN,OBN,610-494-1430,",
        "CALL:ALARM -",
        "ADDR:736 CARRIAGE CIR",
        "X:CHERRY TREE RD",
        "INFO:SCHATZ RES,~GEN OBN,OBN,610-494-1430,");

    doTest("T10",
        "11:17*ALARM -*1702 CHICHESTER AV*LAUGHEAD AV*BUSINESS,484-490-5167,OWNER BEING NOTIFIED,ALARM CO NOW ADVISING THEY ARE GETTING A FALSE ALARM,UNSURE IF THEY HA",
        "CALL:ALARM -",
        "ADDR:1702 CHICHESTER AV",
        "X:LAUGHEAD AV",
        "INFO:BUSINESS,484-490-5167,OWNER BEING NOTIFIED,ALARM CO NOW ADVISING THEY ARE GETTING A FALSE ALARM,UNSURE IF THEY HA");
  }
  
  public static void main(String[] args) {
    new PADelewareCountyParserTest().generateTests("T1");
  }
}
