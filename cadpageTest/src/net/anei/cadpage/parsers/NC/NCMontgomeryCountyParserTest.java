package net.anei.cadpage.parsers.NC;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;

/* 
Montgomery County, NC
Contact: Kyle Morris <wildlandfire09@gmail.com>
Sender: @montgomerycountync.com

CAD:DAYS INN 531 EAST MAIN ST 105 BISCOE 14:14:20 ASSAULT - SEXUAL ASSAULT man an woman was fighting and then fm turned on caller.
CAD:1344 NC HWY 109 S MT GILEAD MDL 09E01 19:26:03 CARDIAC OR RESPIRATORY ARREST - DEATH
CAD:1344 NC HWY 109 S MT GILEAD 19:21:52 UNCONSCIOUS - FAINTING (NEAR) E
CAD:348 AUMAN RD BISCOE 17:25:35 ASSAULT - SEXUAL ASSAULT ASSAULT CALLERS SON GAVE FM BLACK EYE

Contact: mike barrington <michael.barrington509@gmail.com>
Sender: CAD@montgomerycountync.com
CAD:217 BRUTONVILLE CHURCH ST CANDOR 20:52:18 SMOKE INVESTIGATION (OUTSIDE) ADV THERE IS SMOKE AND IT IS MAKING HER SICK FROM FIRE EARLIER TODAY
 
Contact: "Robert" <robert.george@montgomerycountync.com>
Sender: CAD@montgomerycountync.com
CAD:232 HARPER ST STAR 15:48:25 COMMUNICATING THREATS CALE HARRIS EX HUSBAND JUST PULLED GUN :1of2


*/

public class NCMontgomeryCountyParserTest extends BaseParserTest {
  
  public NCMontgomeryCountyParserTest() {
    setParser(new NCMontgomeryCountyParser(), "MONTGOMERY COUNTY", "NC");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "CAD:DAYS INN 531 EAST MAIN ST 105 BISCOE 14:14:20 ASSAULT - SEXUAL ASSAULT man an woman was fighting and then fm turned on caller.",
        "PLACE:DAYS INN",
        "ADDR:531 EAST MAIN ST 105",
        "CITY:BISCOE",
        "TIME:14:14:20",
        "CALL:ASSAULT",
        "INFO:SEXUAL ASSAULT man an woman was fighting and then fm turned on caller.");

    doTest("T2",
        "CAD:1344 NC HWY 109 S MT GILEAD MDL 09E01 19:26:03 CARDIAC OR RESPIRATORY ARREST - DEATH",
        "ADDR:1344 NC HWY 109 S",
        "MADDR:1344 STATE 109 S",
        "CITY:MT GILEAD",
        "CODE:09E01",
        "TIME:19:26:03",
        "CALL:CARDIAC OR RESPIRATORY ARREST",
        "INFO:DEATH");

    doTest("T3",
        "CAD:1344 NC HWY 109 S MT GILEAD 19:21:52 UNCONSCIOUS - FAINTING (NEAR) E",
        "ADDR:1344 NC HWY 109 S",
        "MADDR:1344 STATE 109 S",
        "CITY:MT GILEAD",
        "TIME:19:21:52",
        "CALL:UNCONSCIOUS",
        "INFO:FAINTING (NEAR) E");

    doTest("T4",
        "CAD:348 AUMAN RD BISCOE 17:25:35 ASSAULT - SEXUAL ASSAULT ASSAULT CALLERS SON GAVE FM BLACK EYE",
        "ADDR:348 AUMAN RD",
        "CITY:BISCOE",
        "TIME:17:25:35",
        "CALL:ASSAULT",
        "INFO:SEXUAL ASSAULT ASSAULT CALLERS SON GAVE FM BLACK EYE");
  }
  
  @Test
  public void testMikeBarrington() {

    doTest("T1",
        "CAD:217 BRUTONVILLE CHURCH ST CANDOR 20:52:18 SMOKE INVESTIGATION (OUTSIDE) ADV THERE IS SMOKE AND IT IS MAKING HER SICK FROM FIRE EARLIER TODAY",
        "ADDR:217 BRUTONVILLE CHURCH ST",
        "CITY:CANDOR",
        "TIME:20:52:18",
        "CALL:SMOKE INVESTIGATION (OUTSIDE) ADV THERE IS SMOKE AND IT IS MAKING HER SICK FROM FIRE EARLIER TODAY");
  }
  
  @Test
  public void testRobertGeorge() {

    doTest("T1",
        "CAD:232 HARPER ST STAR 15:48:25 COMMUNICATING THREATS CALE HARRIS EX HUSBAND JUST PULLED GUN :1of2",
        "ADDR:232 HARPER ST",
        "CITY:STAR",
        "TIME:15:48:25",
        "CALL:COMMUNICATING THREATS CALE HARRIS EX HUSBAND JUST PULLED GUN");

  }
  

  public static void main(String[] args) {
    new NCMontgomeryCountyParserTest().generateTests("T1");
  }
}
