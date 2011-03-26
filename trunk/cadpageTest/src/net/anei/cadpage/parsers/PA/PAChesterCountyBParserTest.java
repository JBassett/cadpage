package net.anei.cadpage.parsers.PA;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Before;
import org.junit.Test;


public class PAChesterCountyBParserTest extends BaseParserTest {
  
  public PAChesterCountyBParserTest() {
    setParser(new PAChesterCountyBParser(), "CHESTER COUNTY", "PA");
  }
  
  @Before
  public void setup() {
    setParser(new PAChesterCountyParser());
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "OVERDOSE - ALS *\n300 CEDAR SPRINGS RD - LIBERTY KNOLL APTS\nW BALTIMORE PK & CEDAR WOODS CI\nAPT 205\nNGARDN\n3RD HAND INFO/RP NOT ON LOC/UNK AGE MALE SAID HE OD'S ON ON PILLS/RP PUT ME ON HOLD\n02/21/2011\n15:09\n610-388-7400",
        "CALL:OVERDOSE - ALS *",
        "ADDR:300 CEDAR SPRINGS RD",
        "APT:205",
        "INFO:W BALTIMORE PK & CEDAR WOODS CI / 3RD HAND INFO/RP NOT ON LOC/UNK AGE MALE SAID HE OD'S ON ON PILLS/RP PUT ME ON HOLD",
        "CITY:NEW GARDEN TWP",
        "NAME:LIBERTY KNOLL APTS",
        "PHONE:610-388-7400");

    doTest("T2",
        "UNKNOWN TYPE FIRE *\n10 ALTEMUS DR\nWATSON MILL RD & DEAD END\nBROAD RUN KNOLL\nACROSS FROM ABV 1/4 MILE\nNGARDN\nSEES FIRE UNSURE IF ITS A BUILDING OR TRASH\n02/21/2011\n08:27\nRIOFSKI, LINDA P\n610-274-0906",
        "CALL:UNKNOWN TYPE FIRE *",
        "ADDR:10 ALTEMUS DR",
        "INFO:WATSON MILL RD & DEAD END / BROAD RUN KNOLL / ACROSS FROM ABV 1/4 MILE / SEES FIRE UNSURE IF ITS A BUILDING OR TRASH",
        "CITY:NEW GARDEN TWP",
        "NAME:RIOFSKI, LINDA P",
        "PHONE:610-274-0906");

    doTest("T3",
        "HOUSE FIRE *\n109 GARDEN STATION RD\nE AVONDALE RD & WHITESTONE RD\nLGROVE\nSTOVE INSIDE BASMENT ON FIRE\n02/20/2011\n17:50\nA/F\n610-322-0944/C",
        "CALL:HOUSE FIRE *",
        "ADDR:109 GARDEN STATION RD",
        "X:E AVONDALE RD & WHITESTONE RD",
        "INFO:STOVE INSIDE BASMENT ON FIRE",
        "CITY:LONDON GROVE TWP",
        "NAME:A/F",
        "PHONE:610-322-0944/C");

    doTest("T4",
        "ALARM - FIRE *\n8822 GAP NEWPORT PK\nCROSSAN LA & PENN GREEN RD\nRESD - LAFFERTY 610-268-2861\nNGARDN\nSMOKE DETECTOR IN KITCHEN - ENTER THRU BACK DO OR\n02/12/2011\n19:57\nLIFE ALERT-645\n800-638-8222",
        "CALL:ALARM - FIRE *",
        "ADDR:8822 GAP NEWPORT PK",
        "INFO:CROSSAN LA & PENN GREEN RD / RESD - LAFFERTY 610-268-2861 / SMOKE DETECTOR IN KITCHEN - ENTER THRU BACK DO OR",
        "CITY:NEW GARDEN TWP",
        "NAME:LIFE ALERT-645",
        "PHONE:800-638-8222");

    doTest("T5",
        "[Update]\nSMOKE / ODOR INVEST (OUTSIDE)\nRT 41 / RT 841\nLGROVE\nHEAVY SMOKE CONDITION IN THE AREA - UNK WHAT'S IT'S ACTUALLY COMING FROM\n02/16/2011\n02:18\nROCHESTER, RANDOLF\n717-468-8174",
        "CALL:SMOKE / ODOR INVEST (OUTSIDE)",
        "ADDR:RT 41 & RT 841",
        "INFO:HEAVY SMOKE CONDITION IN THE AREA - UNK WHAT'S IT'S ACTUALLY COMING FROM",
        "CITY:LONDON GROVE TWP",
        "NAME:ROCHESTER, RANDOLF",
        "PHONE:717-468-8174");

    doTest("T6",
        "SMOKE / ODOR INVEST (OUTSIDE)\nRT 41 / RT 841\nLGROVE\nHEAVY SMOKE CONDITION IN THE AREA - UNK WHAT'S IT'S ACTUALLY COMING FROM\n02/16/2011\n02:18\nROCHESTER, RANDOLF\n717-468-8174",
        "CALL:SMOKE / ODOR INVEST (OUTSIDE)",
        "ADDR:RT 41 & RT 841",
        "INFO:HEAVY SMOKE CONDITION IN THE AREA - UNK WHAT'S IT'S ACTUALLY COMING FROM",
        "CITY:LONDON GROVE TWP",
        "NAME:ROCHESTER, RANDOLF",
        "PHONE:717-468-8174");
  }
  
  public static void main(String[] args) {
    new PAChesterCountyBParserTest().generateTests("T1");
  }
}
