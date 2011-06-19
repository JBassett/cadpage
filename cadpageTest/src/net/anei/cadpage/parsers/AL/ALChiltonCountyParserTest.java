package net.anei.cadpage.parsers.AL;

import net.anei.cadpage.parsers.BaseParserTest;
import net.anei.cadpage.parsers.AL.ALChiltonCountyParserTest;

import org.junit.Test;


public class ALChiltonCountyParserTest extends BaseParserTest {
  
  public ALChiltonCountyParserTest() {
    setParser(new ALChiltonCountyParser(), "CHILTON COUNTY", "AL");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "120 COUNTY RD 377 BILLINGSLEY 2011040762 18:41:28 MENTAL PERSON/PATIENT CUTTING HIMSELF, ALTERED MENTAL STATUS",
        "ADDR:120 COUNTY RD 377",
        "CITY:BILLINGSLEY",
        "ID:2011040762",
        "INFO:MENTAL PERSON/PATIENT CUTTING HIMSELF, ALTERED MENTAL STATUS");

    doTest("T2",
        "COUNTY RD 57 \\ COUNTY RD 445 2011039172 06:20:45 WRECK-UNKNOWN INJURIES CALLER STATES ONE VEHICLE OVERTURNED.. ADV THAT THE SUBJECT IS OUT OF THE VEHICLE",
        "ADDR:COUNTY RD 57 & COUNTY RD 445",
        "ID:2011039172",
        "INFO:WRECK-UNKNOWN INJURIES CALLER STATES ONE VEHICLE OVERTURNED.. ADV THAT THE SUBJECT IS OUT OF THE VEHICLE");

    doTest("T3",
        "120 COUNTY RD 377 BILLINGSLEY 2011040762 18:41:28 MENTAL PERSON/PATIENT CUTTING HIMSELF, ALTERED MENTAL STATUS",
        "ADDR:120 COUNTY RD 377",
        "CITY:BILLINGSLEY",
        "ID:2011040762",
        "INFO:MENTAL PERSON/PATIENT CUTTING HIMSELF, ALTERED MENTAL STATUS");

    doTest("T4",
        "100 COUNTY RD 99 LOT 5 VERBENA 2011043298 18:37:55 SHORTNESS OF BREATH GRANDMOTHER ON CHEMO NOT DOING GOOD",
        "ADDR:100 COUNTY RD 99 LOT 5",
        "CITY:VERBENA",
        "ID:2011043298",
        "INFO:SHORTNESS OF BREATH GRANDMOTHER ON CHEMO NOT DOING GOOD");

    doTest("T5",
        "518 COUNTY RD 221 THORSBY 2011046746 06:20:05 DIABETIC RESIDENCE IS A BEIGE DOUBLE WIDE WITH MAROON SHUTTERS. RED TOYOTA AND BLACK DODGE IN THE BACK YARD",
        "ADDR:518 COUNTY RD 221",
        "CITY:THORSBY",
        "ID:2011046746",
        "INFO:DIABETIC RESIDENCE IS A BEIGE DOUBLE WIDE WITH MAROON SHUTTERS. RED TOYOTA AND BLACK DODGE IN THE BACK YARD");

    doTest("T6",
        "2645 COUNTY RD 37 THORSBY 2011050014 00:31:53 ASSIST OCCUPANT 91 YOM HAS FALLEN; LIFTING ASSISTANCE ONLY",
        "ADDR:2645 COUNTY RD 37",
        "CITY:THORSBY",
        "ID:2011050014",
        "INFO:ASSIST OCCUPANT 91 YOM HAS FALLEN; LIFTING ASSISTANCE ONLY");

    doTest("T7",
        "905 COUNTY RD 402 CLANTON 2011050250 05:43:58 GENERAL ILLNESS 57 YO FEMALE // NERVE PROBLEMS // PERMENANT NERVE DAMAGE //",
        "ADDR:905 COUNTY RD 402",
        "CITY:CLANTON",
        "ID:2011050250",
        "INFO:GENERAL ILLNESS 57 YO FEMALE // NERVE PROBLEMS // PERMENANT NERVE DAMAGE //");

  }
  
  public static void main(String[] args) {
    new ALChiltonCountyParserTest().generateTests("T8", "ADDR CITY ID CALL INFO");
  }
}