package net.anei.cadpage.parsers.OH;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class OHKnoxCountyParserTest extends BaseParserTest {
  
  public OHKnoxCountyParserTest() {
    setParser(new OHKnoxCountyParser(), "KNOX COUNTY", "OH");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "Dispatch:[SD35]- NATURE: FIRE TRASH/DUMPSTER FIRE LOCATION: 21 JOHNSV ILLE RD CENTERBURG BETWEEN COLUMBUS RD / UPDIKE RD COMMENTS : MATRESS ON FIRE IN PARK ACROSS FROM SUNOCO STATION",
        "UNIT:SD35",
        "CALL:FIRE TRASH/DUMPSTER FIRE",
        "ADDR:21 JOHNSVILLE RD",
        "CITY:CENTERBURG",
        "X:COLUMBUS RD / UPDIKE RD",
        "INFO:MATRESS ON FIRE IN PARK ACROSS FROM SUNOCO STATION");

    doTest("T2",
        "Dispatch:[SD24]- NATURE: FIRESINGLE FAMILY RES STRUCTURE LOCATION: 42 20 ST RT 3 COMMENTS: MUTUAL AID BST&G STRUCTURE FIRE//HOUSE FILLED W/SMOKE////X ROADS OLD 3C//MEREDITH STATE RD",
        "UNIT:SD24",
        "CALL:FIRESINGLE FAMILY RES STRUCTURE",
        "ADDR:4220 ST RT 3",
        "MADDR:4220 ST 3",
        "INFO:MUTUAL AID BST&G STRUCTURE FIRE//HOUSE FILLED W/SMOKE////X ROADS OLD 3C//MEREDITH STATE RD");

    doTest("T3",
        "Dispatch:[SD35]- NATURE: CARDIAC OR RESPIRATORY ARREST/DEATH LOCATION : 212 FAIRVIEW AVE SUITE: BLDG CENTERBURG BETWEEN JONES AVE / LEONARD AVE COMMENTS: X Y 39 YOF TRACH AND VE NTILATOR PATIENT FULL CODE PROQA MEDICAL: NEW CASE NUMBER A SSIGNED CALL 11-1010849 BY SD35 (POS 02) AT 07:13:06 PROQA MEDICAL: ABORT CALL 11-1010849 BY SD35 (POS 02) AT 07:13:13 - DID NOT USE PROQA MEDICAL: STOPPED CALL 11-1010849 BY SD3 5 (POS 02) AT 07:13:13",
        "UNIT:SD35",
        "CALL:CARDIAC OR RESPIRATORY ARREST/DEATH",
        "ADDR:212 FAIRVIEW AVE",
        "APT:BLDG",
        "CITY:CENTERBURG",
        "X:JONES AVE / LEONARD AVE",
        "INFO:X Y 39 YOF TRACH AND VE NTILATOR PATIENT FULL CODE PROQA MEDICAL: NEW CASE NUMBER A SSIGNED CALL 11-1010849 BY SD35 (POS 02) AT 07:13:06 PROQA MEDICAL: ABORT CALL 11-1010849 BY SD35 (POS 02) AT 07:13:13 - DID NOT USE PROQA MEDICAL: STOPPED CALL 11-1010849 BY SD3 5 (POS 02) AT 07:13:13");

    doTest("T4",
        "Dispatch:[SD8]- NATURE: FALLS/BACK INJURY (TRAUMATIC) LOCATION: 164 W HOUCK ST SUITE: 109 CENTERBURG BETWEEN N PRESTON ST / WILL IS ST COMMENTS: FEMALE HAS FALLEN",
        "UNIT:SD8",
        "CALL:FALLS/BACK INJURY (TRAUMATIC)",
        "ADDR:164 WHOUCK ST",
        "APT:109",
        "CITY:CENTERBURG",
        "X:N PRESTON ST / WILL IS ST",
        "INFO:FEMALE HAS FALLEN");

    doTest("T5",
        "Dispatch:[SD24]- NATURE: UNCONCIOUS/PASSING OUT (NONTRAUMATI LOCATION : 164 W HOUCK ST SUITE: 112 CENTERBURG BETWEEN N PRESTON ST / WILLIS ST COMMENTS: PROQA MEDICAL: NEW CASE NUMBER ASSIGN ED CALL 11-1010741 BY SD24 (POS 01) AT 08:13:03 88 YOM UNRE SPONSIVE//BREATHING AT THIS TIME",
        "UNIT:SD24",
        "CALL:UNCONCIOUS/PASSING OUT (NONTRAUMATI",
        "ADDR:164 W HOUCK ST",
        "APT:112",
        "CITY:CENTERBURG",
        "X:N PRESTON ST / WILLIS ST",
        "INFO:PROQA MEDICAL: NEW CASE NUMBER ASSIGN ED CALL 11-1010741 BY SD24 (POS 01) AT 08:13:03 88 YOM UNRE SPONSIVE//BREATHING AT THIS TIME");
  }
  
  public static void main(String[] args) {
    new OHKnoxCountyParserTest().generateTests("T1");
  }
}