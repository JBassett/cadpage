package net.anei.cadpage.parsers.IN;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class INMarionCountyParserTest extends BaseParserTest {
  
  public INMarionCountyParserTest() {
    setParser(new INMarionCountyParser(), "MARION COUNTY", "IN");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "2265 S MERIDIAN ST,IND S02300 W00001 GARAGE/WORKI LD13 MD29 TS18 SF06 C1205 C1823 IPAGE MEDIA I05320",
        "ADDR:2265 S MERIDIAN ST",
        "CITY:Indianapolis",
        "MAP:S02300 W00001",
        "CALL:GARAGE/WORKI",
        "UNIT:LD13 MD29 TS18 SF06 C1205 C1823 IPAGE MEDIA I05320");

    doTest("T2",
        "1702 E GIMBER ST,IND S02750 E01700 STRUCT/COLLAPSE CSTF TC01 LD01 TC23 EG23 SQ10 C1103 SF06 C1823 MD29 EG29 LD29 BC14 DST6 OP1 I05219",
        "ADDR:1702 E GIMBER ST",
        "CITY:Indianapolis",
        "MAP:S02750 E01700",
        "CALL:STRUCT/COLLAPSE",
        "INFO:CSTF TC01 LD01 TC23 EG23 SQ10 C1103 SF06 C1823 MD29 EG29 LD29 BC14 DST6 OP1 I05219");

    doTest("T3",
        "W 16TH ST/N AUBURN ST,SPD N01600 W05000 APARTMNT/WORKI LD30 TS18 TS83 SF08 WRKFIR IFDFYI I05193",
        "ADDR:W 16TH ST & N AUBURN ST",
        "CITY:Speedway",
        "MAP:N01600 W05000",
        "CALL:APARTMNT/WORKI",
        "UNIT:LD30 TS18 TS83 SF08 WRKFIR IFDFYI I05193");

    doTest("T4",
        "E WASHINGTON ST/S ORIENTAL ST,IND N00100 E01400 BUILDING/WORKI JUST FYI ON FD-OP05 (LP) I05087 @ FROM: IF40",
        "ADDR:E WASHINGTON ST & S ORIENTAL ST",
        "CITY:Indianapolis",
        "MAP:N00100 E01400",
        "CALL:BUILDING/WORKI",
        "INFO:JUST FYI ON FD-OP05 (LP) I05087 @ FROM: IF40");

    doTest("T5",
        "E WASHINGTON ST/S ORIENTAL ST,IND N00100 E01400 BUILDING/WORKI IFDFYI FIRE UNDER CONTROL ON OP5 BC14 COMMAND...ENC3 I05087 @ FROM: IF44",
        "ADDR:E WASHINGTON ST & S ORIENTAL ST",
        "CITY:Indianapolis",
        "MAP:N00100 E01400",
        "CALL:BUILDING/WORKI",
        "INFO:IFDFYI FIRE UNDER CONTROL ON OP5 BC14 COMMAND...ENC3 I05087 @ FROM: IF44");

    doTest("T6",
        "4512 E 21ST ST,IND N02100 E04500 RESIDENCE/WORKIN LD27 MD20 TS25 SF04 C1205 C1822 IPAGE MEDIA I05068",
        "ADDR:4512 E 21ST ST",
        "CITY:Indianapolis",
        "MAP:N02100 E04500",
        "CALL:RESIDENCE/WORKIN",
        "UNIT:LD27 MD20 TS25 SF04 C1205 C1822 IPAGE MEDIA I05068");

    doTest("T7",
        "8850 FALL CREEK RD,LWR N07000 E08900 CIV/FATALITY IFDFYI SINGLE FATALITY ON OP3...ENC I04762 @ FROM: IF42",
        "ADDR:8850 FALL CREEK RD",
        "CITY:Lawrence TWP",
        "MAP:N07000 E08900",
        "CALL:CIV/FATALITY",
        "INFO:IFDFYI SINGLE FATALITY ON OP3...ENC I04762 @ FROM: IF42");

    doTest("T8",
        "E 30TH ST/N GERMAN CHURCH RD,WAR N03200 E11000 FIELD IFDFYI...FYI ONLY, SEVERAL IFD APPARATUS, EG37 FROM LAWRENCE, AND HANCOCK CO GRASS TRUCK ON THIS :1of2",
        "ADDR:E 30TH ST & N GERMAN CHURCH RD",
        "CITY:Warren TWP",
        "MAP:N03200 E11000",
        "CALL:FIELD",
        "INFO:IFDFYI...FYI ONLY, SEVERAL IFD APPARATUS, EG37 FROM LAWRENCE, AND HANCOCK CO GRASS TRUCK ON THIS");
  }
  
  public static void main(String[] args) {
    new INMarionCountyParserTest().generateTests("T1", "ADDR CITY MAP CALL UNIT INFO");
  }
}