package net.anei.cadpage.parsers.PA;

import net.anei.cadpage.parsers.BaseParserTest;
import net.anei.cadpage.parsers.PA.PALehighCountyParser;

import org.junit.Test;


public class PALehighCountyParserTest extends BaseParserTest {
  
  public PALehighCountyParserTest() {
    setParser(new PALehighCountyParser(), "LEHIGH COUNTY", "PA");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "ST0100 TYP: RESP DIST-ABN BREATH APT: 8 AD: 4015 SCHOENECK RD CTY: LOWER MACUNGIE LOC: BIG O MHP CN: NURSE JULIE CMT1: **CHARLIE BREATHING PROBLEMS - ABNORMAL REATHING CMT2: 57 YEAR OLD MALE | 502 LBS | FULL OF FLUID | BLOOD IN STOOL | TIME: 10:11 UNTS: FD/30CHIEFS FD/ST01 XST2: MOUNTAIN RD",
        "SRC:ST0100",
        "CALL:RESP DIST-ABN BREATH",
        "APT:8",
        "ADDR:4015 SCHOENECK RD",
        "CITY:LOWER MACUNGIE TWP",
        "PLACE:BIG O MHP",
        "NAME:NURSE JULIE",
        "INFO:**CHARLIE BREATHING PROBLEMS - ABNORMAL REATHING / 57 YEAR OLD MALE | 502 LBS | FULL OF FLUID | BLOOD IN STOOL |",
        "UNIT:FD/30CHIEFS FD/ST01",
        "X:MOUNTAIN RD");
    
    doTest("T2",
        "ST0100 TYP: NON-DWELL STRUCTURE AD: 8635 HENSINGERSVILLE RD CTY: 06 LONGSWAMP CMT1: **NON-DWELLING STRUCTURE FIRE (SHED, DETATCHED GARAGE) CMT2: FULLY INVOLVED STRUCTURE FIRE TIME: 01:37 UNTS: FD/ST01 XST: WALKER RD XST2: 8603 OAK HOLLOW LN",
        "SRC:ST0100",
        "CALL:NON-DWELL STRUCTURE",
        "ADDR:8635 HENSINGERSVILLE RD",
        "CITY:LONGSWAMP TWP",
        "INFO:**NON-DWELLING STRUCTURE FIRE (SHED, DETATCHED GARAGE) / FULLY INVOLVED STRUCTURE FIRE",
        "UNIT:FD/ST01",
        "X:WALKER RD & 8603 OAK HOLLOW LN");
    
    doTest("T3",
        "ST0100 TYP: APARTMENT BLDG FIRE AD: 2130 STATE ST CTY: 06 LONGSWAMP CN: BERKS CMT1: **APARTMENT BUILDING FIRE (MULTI-OCCUPANCY RESIDENTIAL) CMT2: REQ ST 01 WIT BERKS ST21 SMOKE FROM BASEMENT IN APT BUILDING TIME: 14:22 UNTS: FD/E111 XST: 175 SCHLOSSBURG ST XST2: 380 JAMES LN",
        "SRC:ST0100",
        "CALL:APARTMENT BLDG FIRE",
        "ADDR:2130 STATE ST",
        "CITY:LONGSWAMP TWP",
        "NAME:BERKS",
        "INFO:**APARTMENT BUILDING FIRE (MULTI-OCCUPANCY RESIDENTIAL) / REQ ST 01 WIT BERKS ST21 SMOKE FROM BASEMENT IN APT BUILDING",
        "UNIT:FD/E111",
        "X:175 SCHLOSSBURG ST & 380 JAMES LN");
    
    doTest("T4",
        "ST0100 TYP: TRAFFIC CONTROL AD: CHURCH ST&THOMAS ST CTY: ALBURTIS LOC: ON THOMAS CN: RONALD CONRAD CMT1: **TRAFFIC CONTROL CMT2: ASST WITH WATER MAIN TIME: 08:",
        "SRC:ST0100",
        "CALL:TRAFFIC CONTROL",
        "ADDR:CHURCH ST & THOMAS ST",
        "CITY:ALBURTIS",
        "PLACE:ON THOMAS",
        "NAME:RONALD CONRAD",
        "INFO:**TRAFFIC CONTROL / ASST WITH WATER MAIN");

    doTest("T5",
        "ST3000 TYP: SMOKE IN DWELLING AD: 2008 ELBOW LN CTY: LOWER MACUNGIE CN: LINDA STROHL CMT1: **SMOKE IN THE DWELLING CMT2: SMOKE COMING FROM THE EXHAUST PIPE OF HEATING SYSTEM FILLING BASEMENT WIT TIME: 06:58 UNTS: FD/30CHIEFS FD/ST30 FD/A6681 FD/ST66 FD/R3041 FD/E3012 FD/TK3031 FD/E3112 XST: 4486 REDWOOD LN XST2: HESS CIR",
        "SRC:ST3000",
        "CALL:SMOKE IN DWELLING",
        "ADDR:2008 ELBOW LN",
        "CITY:LOWER MACUNGIE TWP",
        "NAME:LINDA STROHL",
        "INFO:**SMOKE IN THE DWELLING / SMOKE COMING FROM THE EXHAUST PIPE OF HEATING SYSTEM FILLING BASEMENT WIT",
        "UNIT:FD/30CHIEFS FD/ST30 FD/A6681 FD/ST66 FD/R3041 FD/E3012 FD/TK3031 FD/E3112",
        "X:4486 REDWOOD LN & HESS CIR");
  }
}