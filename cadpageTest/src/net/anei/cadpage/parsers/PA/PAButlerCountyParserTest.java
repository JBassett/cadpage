package net.anei.cadpage.parsers.PA;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;

/*
Butler County, PA
Contact: Jeff Gooch <goochff21@gmail.com>
Sender: "."@co.butler.pa.us
"."@butlerco.911 :ALAF >ALARM/FIRE 20436 ROUTE 19 CRANBERRY TWP GUARDIAN Map: Grids:00000,000 Cad: 2011-0000075272
"."@butlerco.911 :CO3 >CO DETECTOR / FIRE RESPONSE 437 SETTLERS VILLAGE CIR CRANBERRY TWP ALEJANDRO, GABRIEL Map: Grids:00000,000 Cad: 2011-0000074937
"."@butlerco.911 :SERV >SERVICE CALL 143 FOX RUN RD CRANBERRY TWP CHEETHAM WILLIAM Map: Grids:00000,000 Cad: 2011-0000075245
"."@butlerco.911 :FIRST >FIRE - STRUCTURE 20036 ROUTE 19 CRANBERRY TWP CANDLEWOOD EXTENDED STAY Map: Grids:00000,000 Cad: 2011-0000074503
"."@co.butler.pa.us :ALAF >ALARM/FIRE 20620 ROUTE 19 CRANBERRY TWP RAMPART Map: Grids:00000,000 Cad: 2012-0000006337
:VAINJ >VA / INJURIES ROUTE 19 CRANBERRY TWP PORTNEY PATTERSON Cad: 2012-0000056854
:VAINJ >VA / INJURIES ROUTE 19 CRANBERRY TWP TYLER VARGO Cad: 2012-0000063053

Contact: Kenneth Chiacchia <chiacchiakb@gmail.com>
Sender: 6245
"."@co.butler.pa.us :ALAF >ALARM/FIRE 804 PROSPECT RD CONNOQ TWP GAURDIAN Map: Grids:00000,000 Cad: 2012-0000035901

Contact: PJ Pesanka <pjp@pjpesanka.com>
Sender: "."@co.butler.pa.us
:FLODB >FLOODING / BASEMENT 337 CLAY AVE MARS BORO KEN SCOTT Map: Grids:00000,000 Cad: 2012-0000053246

*/

public class PAButlerCountyParserTest extends BaseParserTest {
  
  public PAButlerCountyParserTest() {
    setParser(new PAButlerCountyParser(), "BUTLER COUNTY", "PA");
  }
  
  @Test
  public void testJeffGooch() {

    doTest("T1",
        "\".\"@butlerco.911 :ALAF >ALARM/FIRE 20436 ROUTE 19 CRANBERRY TWP GUARDIAN Map: Grids:00000,000 Cad: 2011-0000075272",
        "CALL:ALARM/FIRE",
        "ADDR:20436 ROUTE 19",
        "CITY:CRANBERRY TWP",
        "NAME:GUARDIAN",
        "ID:2011-0000075272");

    doTest("T2",
        "\".\"@butlerco.911 :CO3 >CO DETECTOR / FIRE RESPONSE 437 SETTLERS VILLAGE CIR CRANBERRY TWP ALEJANDRO, GABRIEL Map: Grids:00000,000 Cad: 2011-0000074937",
        "CALL:CO DETECTOR / FIRE RESPONSE",
        "ADDR:437 SETTLERS VILLAGE CIR",
        "CITY:CRANBERRY TWP",
        "NAME:ALEJANDRO, GABRIEL",
        "ID:2011-0000074937");

    doTest("T3",
        "\".\"@butlerco.911 :SERV >SERVICE CALL 143 FOX RUN RD CRANBERRY TWP CHEETHAM WILLIAM Map: Grids:00000,000 Cad: 2011-0000075245",
        "CALL:SERVICE CALL",
        "ADDR:143 FOX RUN RD",
        "CITY:CRANBERRY TWP",
        "NAME:CHEETHAM WILLIAM",
        "ID:2011-0000075245");

    doTest("T4",
        "\".\"@butlerco.911 :FIRST >FIRE - STRUCTURE 20036 ROUTE 19 CRANBERRY TWP CANDLEWOOD EXTENDED STAY Map: Grids:00000,000 Cad: 2011-0000074503",
        "CALL:FIRE - STRUCTURE",
        "ADDR:20036 ROUTE 19",
        "CITY:CRANBERRY TWP",
        "NAME:CANDLEWOOD EXTENDED STAY",
        "ID:2011-0000074503");

    doTest("T5",
        "\".\"@co.butler.pa.us :ALAF >ALARM/FIRE 20620 ROUTE 19 CRANBERRY TWP RAMPART Map: Grids:00000,000 Cad: 2012-0000006337",
        "CALL:ALARM/FIRE",
        "ADDR:20620 ROUTE 19",
        "CITY:CRANBERRY TWP",
        "NAME:RAMPART",
        "ID:2012-0000006337");

    doTest("T6",
        "\".\"@co.butler.pa.us :ALAF >ALARM/FIRE 804 PROSPECT RD CONNOQ TWP GAURDIAN Map: Grids:00000,000 Cad: 2012-0000035901",
        "CALL:ALARM/FIRE",
        "ADDR:804 PROSPECT RD",
        "CITY:CONNOQUENESSING TWP",
        "NAME:GAURDIAN",
        "ID:2012-0000035901");

    doTest("T7",
        ":VAINJ >VA / INJURIES ROUTE 19 CRANBERRY TWP PORTNEY PATTERSON Cad: 2012-0000056854",
        "CALL:VA / INJURIES",
        "ADDR:ROUTE 19",
        "CITY:CRANBERRY TWP",
        "NAME:PORTNEY PATTERSON",
        "ID:2012-0000056854");

    doTest("T8",
        ":VAINJ >VA / INJURIES ROUTE 19 CRANBERRY TWP TYLER VARGO Cad: 2012-0000063053",
        "CALL:VA / INJURIES",
        "ADDR:ROUTE 19",
        "CITY:CRANBERRY TWP",
        "NAME:TYLER VARGO",
        "ID:2012-0000063053");
  }
  
  @Test
  public void testKennethChiacchia() {

    doTest("T1",
        "\".\"@co.butler.pa.us :ALAF >ALARM/FIRE 804 PROSPECT RD CONNOQ TWP GAURDIAN Map: Grids:00000,000 Cad: 2012-0000035901",
        "CALL:ALARM/FIRE",
        "ADDR:804 PROSPECT RD",
        "CITY:CONNOQUENESSING TWP",
        "NAME:GAURDIAN",
        "ID:2012-0000035901");
  
  }
  
  @Test
  public void testPJPensanka() {

    doTest("T1",
        ":FLODB >FLOODING / BASEMENT 337 CLAY AVE MARS BORO KEN SCOTT Map: Grids:00000,000 Cad: 2012-0000053246",
        "CALL:FLOODING / BASEMENT",
        "ADDR:337 CLAY AVE",
        "CITY:MARS",
        "NAME:KEN SCOTT",
        "ID:2012-0000053246");

  }
  
  public static void main(String[] args) {
    new PAButlerCountyParserTest().generateTests("T1");
  }
}
