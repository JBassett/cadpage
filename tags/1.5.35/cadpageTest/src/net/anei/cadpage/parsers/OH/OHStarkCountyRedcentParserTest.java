package net.anei.cadpage.parsers.OH;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class OHStarkCountyRedcentParserTest extends BaseParserTest {
  
  public OHStarkCountyRedcentParserTest() {
    setParser(new OHStarkCountyRedcenterParser(), "STARK COUNTY", "OH");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "RED:[02]- NATURE: EMERGENCY MEDICAL CALL LOCATION: 5830 MANCHEST ER AV NW LAWRENCE TWP BETWEEN PATTERSON / GADDIS ACCESS BY NORTH LAWRENCE FIRE COMMENTS: MENTAL HEALTH EVAL ON STATIO N",
        "UNIT:02",
        "CALL:EMERGENCY MEDICAL CALL",
        "ADDR:5830 MANCHESTER AV NW",
        "MADDR:5830 MANCHESTER AVE NW",
        "CITY:LAWRENCE TWP",
        "X:PATTERSON / GADDIS ACCESS BY NORTH LAWRENCE FIRE",
        "INFO:MENTAL HEALTH EVAL ON STATIO N");

    doTest("T2",
        "RED:[43]- NATURE: EMERGENCY MEDICAL CALL LOCATION: 80 TfHOMAS BL NW LAWRENCE TWP BETWEEN NICHOLAS / CARMELLA ACCESS BY 40 C ORNERS TRAILER PARK COMMENTS: COMPLICATIONS WITH PANCREATITI S",
        "UNIT:43",
        "CALL:EMERGENCY MEDICAL CALL",
        "ADDR:80 TfHOMAS BL NW",
        "CITY:LAWRENCE TWP",
        "X:NICHOLAS / CARMELLA ACCESS BY 40 C ORNERS TRAILER PARK",
        "INFO:COMPLICATIONS WITH PANCREATITI S");

    doTest("T3",
        "RED:[02]- NATURE: PUBLIC SERVICE-SQUAD LOCATION: 8757 SPRING GRO VE AV NW LAWRENCE TWP BETWEEN GLENCOE / GENEVA COMMENTS: LI FT ASSIST GARAGE ENTRANCE",
        "UNIT:02",
        "CALL:PUBLIC SERVICE-SQUAD",
        "ADDR:8757 SPRING GROVE AV NW",
        "MADDR:8757 SPRING GROVE AVE NW",
        "CITY:LAWRENCE TWP",
        "X:GLENCOE / GENEVA",
        "INFO:LI FT ASSIST GARAGE ENTRANCE");

    doTest("T4",
        "RED:[02]- NATURE: EMERGENCY MEDICAL CALL LOCATION: 14602 ARCADIA ST NW LAWRENCE TWP BETWEEN HAMMOCK / ALABAMA COMMENTS: FEM ALE ILLNESS",
        "UNIT:02",
        "CALL:EMERGENCY MEDICAL CALL",
        "ADDR:14602 ARCADIA ST NW",
        "CITY:LAWRENCE TWP",
        "X:HAMMOCK / ALABAMA",
        "INFO:FEM ALE ILLNESS");

    doTest("T5",
        "RED:[02]- NATURE: ACCIDENT - INJURY LOCATION: 5950 DEERFIELD AV NW LAWRENCE TWP BETWEEN ORRVILLE / ARCADIA ACCESS BY NORTH LAWRENCE FIRE COMMENTS: ROLL OVER",
        "UNIT:02",
        "CALL:ACCIDENT - INJURY",
        "ADDR:5950 DEERFIELD AV NW",
        "MADDR:5950 DEERFIELD AVE NW",
        "CITY:LAWRENCE TWP",
        "X:ORRVILLE / ARCADIA ACCESS BY NORTH LAWRENCE FIRE",
        "INFO:ROLL OVER");

    doTest("T6",
        "RED:[RC68]- NATURE: EMERGENCY MEDICAL CALL LOCATION: 10751 JOHNS TON ST NW LAWRENCE TWP BETWEEN AKRON / HIGHMILL COMMENTS: N URSE ON SCENE DRIVEWAY IS A FORK- STAY TO THE LEFT ALTERED LOC",
        "UNIT:RC68",
        "CALL:EMERGENCY MEDICAL CALL",
        "ADDR:10751 JOHNSTON ST NW",
        "CITY:LAWRENCE TWP",
        "X:AKRON / HIGHMILL",
        "INFO:N URSE ON SCENE DRIVEWAY IS A FORK- STAY TO THE LEFT ALTERED LOC");

    doTest("T7",
        "RED:[02]- NATURE: EMERGENCY MEDICAL CALL LOCATION: 5622 CUTTEN A V NW LAWRENCE TWP BETWEEN SOUSA / WEYGANDT ACCESS BY NORTH LAWRENCE FIRE COMMENTS: FEMALE HIP PAIN",
        "UNIT:02",
        "CALL:EMERGENCY MEDICAL CALL",
        "ADDR:5622 CUTTEN AV NW",
        "MADDR:5622 CUTTEN AVE NW",
        "CITY:LAWRENCE TWP",
        "X:SOUSA / WEYGANDT ACCESS BY NORTH LAWRENCE FIRE",
        "INFO:FEMALE HIP PAIN");

    doTest("T8",
        "RED:[07]- NATURE: ACCIDENT - INJURY LOCATION: 3971 MANCHESTER AV NW LAWRENCE TWP BETWEEN YOUTH / ORRVILLE ACCESS BY NORTH LAWRENCE FIRE COMMENTS: CAR OVER GUARDRAIL",
        "UNIT:07",
        "CALL:ACCIDENT - INJURY",
        "ADDR:3971 MANCHESTER AV NW",
        "MADDR:3971 MANCHESTER AVE NW",
        "CITY:LAWRENCE TWP",
        "X:YOUTH / ORRVILLE ACCESS BY NORTH LAWRENCE FIRE",
        "INFO:CAR OVER GUARDRAIL");

    doTest("T9",
        "RED:[11]- NATURE: STRUCTURE FIRE LOCATION: 3283 YOUNG AV NW LAWR ENCE TWP BETWEEN ORRVILLE / NORTH TO END ACCESS BY NORTH L AWRENCE FIRE COMMENTS: SMOKE IN THE BASEMENT TONED 1339",
        "UNIT:11",
        "CALL:STRUCTURE FIRE",
        "ADDR:3283 YOUNG AV NW",
        "MADDR:3283 YOUNG AVE NW",
        "CITY:LAWRENCE TWP",
        "X:ORRVILLE / NORTH TO END ACCESS BY NORTH L AWRENCE FIRE",
        "INFO:SMOKE IN THE BASEMENT TONED 1339");

    doTest("T10",
        "RED:[11]- NATURE: PUBLIC SERVICE-FIRE LOCATION: 1641 ARAPAHOE AV SE MASSILLON BETWEEN SHAWNEE RD / 16TH ST COMMENTS: RESIDE NTIAL LOCKOUT",
        "UNIT:11",
        "CALL:PUBLIC SERVICE-FIRE",
        "ADDR:1641 ARAPAHOE AV SE",
        "MADDR:1641 ARAPAHOE AVE SE",
        "CITY:MASSILLON",
        "X:SHAWNEE RD / 16TH ST",
        "INFO:RESIDE NTIAL LOCKOUT");

    doTest("T11",
        "RED:[11]- NATURE: STRUCTURE FIRE LOCATION: 3283 YOUNG AV NW LAWR ENCE TWP BETWEEN ORRVILLE / NORTH TO END ACCESS BY NORTH L AWRENCE FIRE COMMENTS: SMOKE IN THE BASEMENT TONED 1339",
        "UNIT:11",
        "CALL:STRUCTURE FIRE",
        "ADDR:3283 YOUNG AV NW",
        "MADDR:3283 YOUNG AVE NW",
        "CITY:LAWRENCE TWP",
        "X:ORRVILLE / NORTH TO END ACCESS BY NORTH L AWRENCE FIRE",
        "INFO:SMOKE IN THE BASEMENT TONED 1339");

    doTest("T12",
        "RED:[07]- NATURE: AUTO ALARM LOCATION: 10405 STRAUSSER ST NW LAW RENCE TWP BETWEEN MC TAGGART / AKRON COMMENTS: 1ST FLR SMOK ES 854 1004",
        "UNIT:07",
        "CALL:AUTO ALARM",
        "ADDR:10405 STRAUSSER ST NW",
        "CITY:LAWRENCE TWP",
        "X:MC TAGGART / AKRON",
        "INFO:1ST FLR SMOK ES 854 1004");

    doTest("T13",
        "RED:[02]- NATURE: EMERGENCY MEDICAL CALL LOCATION: 5830 MANCHEST ER AV NW LAWRENCE TWP BETWEEN PATTERSON / GADDIS ACCESS BY NORTH LAWRENCE FIRE COMMENTS: MENTAL HEALTH EVAL ON STATIO N",
        "UNIT:02",
        "CALL:EMERGENCY MEDICAL CALL",
        "ADDR:5830 MANCHESTER AV NW",
        "MADDR:5830 MANCHESTER AVE NW",
        "CITY:LAWRENCE TWP",
        "X:PATTERSON / GADDIS ACCESS BY NORTH LAWRENCE FIRE",
        "INFO:MENTAL HEALTH EVAL ON STATIO N");

    doTest("T14",
        "RED:[43]- NATURE: EMERGENCY MEDICAL CALL LOCATION: 80 THOMAS BL NW LAWRENCE TWP BETWEEN NICHOLAS / CARMELLA ACCESS BY 40 C ORNERS TRAILER PARK COMMENTS: COMPLICATIONS WITH PANCREATITI S",
        "UNIT:43",
        "CALL:EMERGENCY MEDICAL CALL",
        "ADDR:80 THOMAS BL NW",
        "CITY:LAWRENCE TWP",
        "X:NICHOLAS / CARMELLA ACCESS BY 40 C ORNERS TRAILER PARK",
        "INFO:COMPLICATIONS WITH PANCREATITI S");

    doTest("T15",
        "RED:[RC63]- NATURE: EMERGENCY MEDICAL CALL LOCATION: 10388 STONE ACRE ST NW LAWRENCE TWP BETWEEN ARTHUR / MC TAGGART COMMENT S: UNKNOWN ILLNESS",
        "UNIT:RC63",
        "CALL:EMERGENCY MEDICAL CALL",
        "ADDR:10388 STONEACRE ST NW",
        "CITY:LAWRENCE TWP",
        "X:ARTHUR / MC TAGGART COMMENT S: UNKNOWN ILLNESS");

    doTest("T16",
        "RED:[32]- NATURE: EMERGENCY MEDICAL CALL LOCATION: 8 MICHAEL CIR LAWRENCE TWP BETWEEN THOMAS BLVD / DEAD END CIRCLE ACCESS BY NORTH LAWRENCE FIRE COMMENTS: MOTHER IN LAW IS SHAKY & N OT FEELING WELL",
        "UNIT:32",
        "CALL:EMERGENCY MEDICAL CALL",
        "ADDR:8 MICHAEL CIR",
        "CITY:LAWRENCE TWP",
        "X:THOMAS BLVD / DEAD END CIRCLE ACCESS BY NORTH LAWRENCE FIRE",
        "INFO:MOTHER IN LAW IS SHAKY & N OT FEELING WELL");

    doTest("T17",
        "RED:[13]- NATURE: EMERGENCY MEDICAL CALL LOCATION: 8871 MANCHEST ER AV NW LAWRENCE TWP BETWEEN BAPST / COUNTY LINE COMMENTS: 85 YOF FALL VICTIM HIP INJURY",
        "UNIT:13",
        "CALL:EMERGENCY MEDICAL CALL",
        "ADDR:8871 MANCHESTER AV NW",
        "MADDR:8871 MANCHESTER AVE NW",
        "CITY:LAWRENCE TWP",
        "X:BAPST / COUNTY LINE",
        "INFO:85 YOF FALL VICTIM HIP INJURY");

    doTest("T18",
        "RED:[28]- NATURE: EMERGENCY MEDICAL CALL LOCATION: 10438 MAPLEVI EW ST NW LAWRENCE TWP BETWEEN AKRON / ELMVIEW COMMENTS: BEH IND THE CALLER, LAWN TRACTOR TIPPED OVER ON HIM CALLER WILL FLAG YOU DOWN AT THIS ADDRESS",
        "UNIT:28",
        "CALL:EMERGENCY MEDICAL CALL",
        "ADDR:10438 MAPLEVIEW ST NW",
        "CITY:LAWRENCE TWP",
        "X:AKRON / ELMVIEW",
        "INFO:BEH IND THE CALLER, LAWN TRACTOR TIPPED OVER ON HIM CALLER WILL FLAG YOU DOWN AT THIS ADDRESS");

    doTest("T19",
        "RED:[02]- NATURE: PUBLIC SERVICE-SQUAD LOCATION: 8757 SPRING GRO VE AV NW LAWRENCE TWP BETWEEN GLENCOE / GENEVA COMMENTS: LI FT ASSIST GARAGE ENTRANCE",
        "UNIT:02",
        "CALL:PUBLIC SERVICE-SQUAD",
        "ADDR:8757 SPRING GROVE AV NW",
        "MADDR:8757 SPRING GROVE AVE NW",
        "CITY:LAWRENCE TWP",
        "X:GLENCOE / GENEVA",
        "INFO:LI FT ASSIST GARAGE ENTRANCE");

    doTest("T20",
        "RED:[02]- NATURE: EMERGENCY MEDICAL CALL LOCATION: 10382 STRAUSS ER ST NW LAWRENCE TWP BETWEEN ARTHUR / MC TAGGART COMMENTS: NOSE BLEED",
        "UNIT:02",
        "CALL:EMERGENCY MEDICAL CALL",
        "ADDR:10382 STRAUSSER ST NW",
        "CITY:LAWRENCE TWP",
        "X:ARTHUR / MC TAGGART",
        "INFO:NOSE BLEED");
  }
  
  public static void main(String[] args) {
    new OHStarkCountyRedcentParserTest().generateTests("T1");
  }
}