package net.anei.cadpage.parsers.NJ;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class NJCamdenCountyAParserTest extends BaseParserTest {
  
  public NJCamdenCountyAParserTest() {
    setParser(new NJCamdenCountyAParser(), "CAMDEN COUNTY", "NJ");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "(Dispatch SD45) DWELLING\n312 9TH ST ,14\n#:\nX:CENTER/CHERRY\nZN:14A\nCP:  2011-03-31 10:44:12\nMI#:110073123\nRES#:SD45",
        "CALL:DWELLING",
        "ADDR:312 9TH ST",
        "CITY:Gloucester City",
        "X:CENTER/CHERRY",
        "MAP:14A",
        "ID:110073123",
        "UNIT:SD45");

    doTest("T2",
        "(Dispatch SD45) M.V.A\nROUTE 295 SB RAMP TO 76 SB ALJO CUR ,04\n#:\nX:/\nZN:04Q\nCP:ROUTE 295 SB RAMP T  2011-03-31 09:06:36\nMI#:110073062",
        "CALL:M.V.A",
        "ADDR:ROUTE 295 SB",
        "MADDR:RAMP TO 76 SB ALJO CUR ROUTE 295 SB RAMP T,ROUTE 295",
        "CITY:Bellmawr",
        "MAP:04Q",
        "PLACE:RAMP TO 76 SB ALJO CUR ROUTE 295 SB RAMP T",
        "ID:110073062",
        "UNIT:SD45");

    doTest("T3",
        "(Dispatch SD45) DIABETIC\n217 BLACK HORSE PK N ,25\n#:4\nX:CARLISLE/LAKEVIEW\nZN:25B\nCP:MAX GROUP PHILLY CO  2011-03-30 13:23:26\nMI#:11007",
        "CALL:DIABETIC",
        "ADDR:217 BLACK HORSE PK N",
        "MADDR:217 BLACK HORSE PIKE N",
        "APT:4",
        "CITY:Mount Ephraim",
        "X:CARLISLE/LAKEVIEW",
        "MAP:25B",
        "PLACE:MAX GROUP PHILLY CO",
        "ID:11007",
        "UNIT:SD45");

    doTest("T4",
        "(Dispatch SD45) DWELLING\nBURLINGTON ST S/MONMOUTH ST ,14\n#:\nX:/\nZN:14A\nCP:  2011-03-30 10:33:28\nMI#:110072287\nRES#:SD45",
        "CALL:DWELLING",
        "ADDR:BURLINGTON ST S & MONMOUTH ST",
        "CITY:Gloucester City",
        "MAP:14A",
        "ID:110072287",
        "UNIT:SD45");
  }
  
  @Test
  public void testSteveKane() {

    doTest("T1",
        "(Dispatch QT60) APARTMENT  \n" +
        "1800 LAUREL RD ,22   \n" +
        "#:114  \n" +
        "X:BLACKWOOD CLEMENTON/  \n" +
        "ZN:22B  \n" +
        "CP:STONINGTON COURT AP  2012-03-09 07:24:49  \n" +
        "MI#:",

        "CALL:APARTMENT",
        "ADDR:1800 LAUREL RD",
        "CITY:Lindenwold",
        "APT:114",
        "X:BLACKWOOD CLEMENTON/",
        "MAP:22B",
        "PLACE:STONINGTON COURT AP",
        "UNIT:QT60");

  }
 
  @Test
  public void testGuyDietrick() {

    doTest("T1",
        "Subject:Dispatch SD11\n" +
        "INCIDENTAL  \r\n" +
        "260 AUDUBON AV ,01   \r\n" +
        "#:  \r\n" +
        "X:BELOIT/WYOMING  \r\n" +
        "ZN:01A  \r\n" +
        "CP:  2012-03-25 07:29:59  \r\n" +
        "MI#:1200\r",

        "CALL:INCIDENTAL",
        "ADDR:260 AUDUBON AV",
        "MADDR:260 AUDUBON AVE",
        "CITY:Audubon",
        "X:BELOIT/WYOMING",
        "MAP:01A",
        "ID:1200",
        "UNIT:SD11");

  }
  public static void main(String[] args) {
    new NJCamdenCountyAParserTest().generateTests("T1");
  }
}