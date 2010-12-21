package net.anei.cadpage.parsers;

import org.junit.Test;


public class INVigoCountyParserTest extends BaseParserTest {
  
  public INVigoCountyParserTest() {
    setParser(new INVigoCountyParser(), "VIGO COUNTY", "IN");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "Ct:FALL Loc:3301 ST MARY'S RD Apt: XSt:BLOOMTOWN RD US HWY 150 Grid:112D Units:E-42 TC-1 Rmk:92 YOLD FML; FELL; FACE DOWN ON FLOOR -",
        "CALL:FALL",
        "ADDR:3301 ST MARY'S RD",
        "X:BLOOMTOWN RD US HWY 150",
        "MAP:112D",
        "UNIT:E-42 TC-1",
        "INFO:92 YOLD FML; FELL; FACE DOWN ON FLOOR -");
    
    doTest("T2",
        "Ct:FALL Loc:309 N CHURCH ST Apt: XSt:PARIS AV Grid:0WTH Units:E-41 TC-2 Rmk:65 Years F ;fall",
        "CALL:FALL",
        "ADDR:309 N CHURCH ST",
        "X:PARIS AV",
        "MAP:0WTH",
        "UNIT:E-41 TC-2",
        "INFO:65 Years F ;fall");
    
    doTest("T3",
        "Ct:CHEST PAIN Loc:500 W PARIS AV Apt: XSt:6TH ST WTH 4TH ST WTH Grid:0WTH Units:E-41 TC-1 Rmk:",
        "CALL:CHEST PAIN",
        "ADDR:500 W PARIS AV",
        "X:6TH ST WTH 4TH ST WTH",
        "MAP:0WTH",
        "UNIT:E-41 TC-1");
    
    doTest("T4",
        "Ct:CHOKING Loc:2925 N MAPLE PL Apt: XSt:LOCUST AV Grid:0126 Units:E-42 TC-1 Rmk:18 Months F ;CHOKING",
        "CALL:CHOKING",
        "ADDR:2925 N MAPLE PL",
        "X:LOCUST AV",
        "MAP:0126",
        "UNIT:E-42 TC-1",
        "INFO:18 Months F ;CHOKING");
    
    doTest("T5",
        "Ct:MVA-UNKNOWN-INJ Loc:US HWY 40/INTERSTATE 70 Apt: XSt: Grid:0169 Units:E-41 Rmk:SEMI AND PASSENGER CAR//UNKNOWN Injuries",
        "CALL:MVA-UNKNOWN-INJ",
        "ADDR:US HWY 40 & INTERSTATE 70",
        "MAP:0169",
        "UNIT:E-41",
        "INFO:SEMI AND PASSENGER CAR//UNKNOWN Injuries");
    
  }
}