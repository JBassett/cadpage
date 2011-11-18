package net.anei.cadpage.parsers.PA;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class PADelawareCountyDParserTest extends BaseParserTest {
  
  public PADelawareCountyDParserTest() {
    setParser(new PADelawareCountyDParser(), "DELAWARE COUNTY", "PA");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "09:06\nE BENEDICT AV & W DARBY RD\nSTREET CLOSED\nE BENEDICT AV\nW DARBY RD\n08/16/2011\nSTREET\nSTA38\nF11046756\nE BENEDICT BETWEEN DARB",
        "TIME:09:06",
        "ADDR:E BENEDICT AV & W DARBY RD",
        "X:STREET CLOSED & E BENEDICT AV & W DARBY RD",
        "DATE:08/16/2011",
        "CALL:STREET",
        "UNIT:STA38",
        "ID:F11046756",
        "INFO:E BENEDICT BETWEEN DARB");

    doTest("T2",
        "11:42\n616 VALLEY VIEW RD\nAUTOMATIC FIRE ALARM\nHAVERFORD RD\nWESTFIELD RD\n10/17/2011\nALARM\nSTA38\nF11060706\n~GENERAL,~SEVERAL SIGNAL",
        "TIME:11:42",
        "ADDR:616 VALLEY VIEW RD",
        "X:AUTOMATIC FIRE ALARM & HAVERFORD RD & WESTFIELD RD",
        "DATE:10/17/2011",
        "CALL:ALARM",
        "UNIT:STA38",
        "ID:F11060706",
        "INFO:GENERAL, SEVERAL SIGNAL");

    doTest("T3",
        "09:29\n5030 BRITTANY LN\nACCIDENTAL TRIP OF FIRE ALARM\nBRENNAN DR\nSPROUL RD\n10/17/2011\nALARM\nENG38\nF11060680\n~TRIPPED /BY STEAM,610",
        "TIME:09:29",
        "ADDR:5030 BRITTANY LN",
        "X:ACCIDENTAL TRIP OF FIRE ALARM & BRENNAN DR & SPROUL RD",
        "DATE:10/17/2011",
        "CALL:ALARM",
        "UNIT:ENG38",
        "ID:F11060680",
        "INFO:TRIPPED /BY STEAM,610");

    doTest("T4",
        "18:25\n300 E EAGLE RD\nAUTOMATIC FIRE ALARM\nSAINT DENIS LN\nSAINT DENIS LN\n10/13/2011\nALARM\nENG38\nF11059917\n610-446-4608,ESZ 3815,~G",
        "TIME:18:25",
        "ADDR:300 E EAGLE RD",
        "X:AUTOMATIC FIRE ALARM & SAINT DENIS LN & SAINT DENIS LN",
        "DATE:10/13/2011",
        "CALL:ALARM",
        "UNIT:ENG38",
        "ID:F11059917",
        "INFO:610-446-4608,ESZ 3815, G");

    doTest("T5",
        "17:54\n601 OVERHILL RD\nAUTOMATIC FIRE ALARM\nHAVERFORD RD\nWESTFIELD RD\n10/12/2011\nALARM\nENG38\nF11059705\n~ZONE 014,RES SEIMEN,610-65",
        "TIME:17:54",
        "ADDR:601 OVERHILL RD",
        "X:AUTOMATIC FIRE ALARM & HAVERFORD RD & WESTFIELD RD",
        "DATE:10/12/2011",
        "CALL:ALARM",
        "UNIT:ENG38",
        "ID:F11059705",
        "INFO:ZONE 014,RES SEIMEN,610-65");

    doTest("T6",
        "12:57\n421 BROOKLINE BLVD\nAUTOMATIC FIRE ALARM\nWEXFORD RD\nEARLINGTON RD\n10/12/2011\nALARM\nLA38/ENG56/RE56/FO35\nF11059648\n610-446-84",
        "TIME:12:57",
        "ADDR:421 BROOKLINE BLVD",
        "X:AUTOMATIC FIRE ALARM & WEXFORD RD & EARLINGTON RD",
        "DATE:10/12/2011",
        "CALL:ALARM",
        "UNIT:LA38/ENG56/RE56/FO35",
        "ID:F11059648",
        "INFO:610-446-84");

    doTest("T7",
        "09:29\n5030 BRITTANY LN\nACCIDENTAL TRIP OF FIRE ALARM\nBRENNAN DR\nSPROUL RD\n10/17/2011\nALARM\nENG38\nF11060680\n~TRIPPED /BY STEAM,610",
        "TIME:09:29",
        "ADDR:5030 BRITTANY LN",
        "X:ACCIDENTAL TRIP OF FIRE ALARM & BRENNAN DR & SPROUL RD",
        "DATE:10/17/2011",
        "CALL:ALARM",
        "UNIT:ENG38",
        "ID:F11060680",
        "INFO:TRIPPED /BY STEAM,610");

    doTest("T8",
        "13:36\n838 AUBREY AV\nAUTOMATIC FIRE ALARM\nSAINT MARYS RD\nCOUNTY LINE RD\n10/14/2011\nALARM\nENG38\nF11060114\n~ HALLWAY SMOKE DET,1068 ",
        "TIME:13:36",
        "ADDR:838 AUBREY AV",
        "X:AUTOMATIC FIRE ALARM & SAINT MARYS RD & COUNTY LINE RD",
        "DATE:10/14/2011",
        "CALL:ALARM",
        "UNIT:ENG38",
        "ID:F11060114",
        "INFO:HALLWAY SMOKE DET,1068");
  }
  
  public static void main(String[] args) {
    new PADelawareCountyDParserTest().generateTests("T1");
  }
}
