package net.anei.cadpage.parsers.CA;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class CANapaCountyParserTest extends BaseParserTest {
  
  public CANapaCountyParserTest() {
    setParser(new CANapaCountyParser(), "NAPA COUNTY", "CA");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "(CAD Page) PA, OTHER; 413 BURGUNDY S ,CALISTOGA_CITY ; Inc# 000483; 70YOM GL FALL NON INJ; RA: CAL; X: -122 34.2255  Y: 38 34.7732; E19 AMR19; Cmd: NAPA CO FIRE; CB#: 7073226460",
        "CALL:PA, OTHER",
        "ADDR:413 BURGUNDY S",
        "CITY:CALISTOGA",
        "ID:000483",
        "INFO:70YOM GL FALL NON INJ",
        "UNIT:E19 AMR19",
        "GPS:X: -122 34.2255  Y: 38 34.7732",
        "SRC:NAPA CO FIRE");

    doTest("T2",
        "(CAD Page) FIRE, COMMERCIAL ALARM; CALISTOGA GARDEN @ 1715 WASHINGTON ST ,CALISTOGA_CITY ; Inc# 000412; GENERAL FIRE ALARM; RA: CAL; X: -122 35.0390  Y: 38 34.86 CB#: 7073226460",
        "CALL:FIRE, COMMERCIAL ALARM",
        "PLACE:CALISTOGA GARDEN",
        "ADDR:1715 WASHINGTON ST",
        "CITY:CALISTOGA",
        "ID:000412",
        "INFO:GENERAL FIRE ALARM",
        "UNIT:CAL",
        "GPS:X: -122 35.0390  Y: 38 34.86");

    doTest("T3",
        "(CAD Page) MEDICAL; DUFFY'S @ 3076 MYRTLEDALE RD ,CALISTOGA ; Inc# 000459; CHEST PAIN; RA: L55; X: -122 35.9647  Y: 38 35.7255; E26 E19; Cmd: NAPA CO FIRE; Tac: CB#: 7073226460",
        "CALL:MEDICAL",
        "PLACE:DUFFY'S",
        "ADDR:3076 MYRTLEDALE RD",
        "CITY:CALISTOGA",
        "ID:000459",
        "INFO:CHEST PAIN",
        "UNIT:E26 E19",
        "GPS:X: -122 35.9647  Y: 38 35.7255",
        "SRC:NAPA CO FIRE");

    doTest("T4",
        "(CAD Page) FIRE, RESIDENTIAL ALARM; 1670 DIAMOND MOUNTAIN RD ,CALISTOGA ; Inc# 000277; LOWER LEVEL SMOKE DETECTOR; RA: S33; X: -122 34.7655  Y: 38 33.7344; B1412 CB#: 7073226460",
        "CALL:FIRE, RESIDENTIAL ALARM",
        "ADDR:1670 DIAMOND MOUNTAIN RD",
        "CITY:CALISTOGA",
        "ID:000277",
        "INFO:LOWER LEVEL SMOKE DETECTOR",
        "UNIT:B1412",
        "GPS:X: -122 34.7655  Y: 38 33.7344");

    doTest("T5",
        "(CAD Page) FIRE, SMOKE CHECK; 4300 HWY 29 / 3898 OLD LAWLEY TOLL RD ,CALISTOGA ; Inc# 000406; POSS CTRL - WHITE; RA: S37; X: -122 35.7937  Y: 38 38.4289; E26 E19 CB#: 7073226460",
        "CALL:FIRE, SMOKE CHECK",
        "ADDR:4300 HWY 29 & 3898 OLD LAWLEY TOLL RD",
        "MADDR:4300 HWY 29",
        "CITY:CALISTOGA",
        "ID:000406",
        "INFO:POSS CTRL - WHITE",
        "UNIT:E26 E19",
        "GPS:X: -122 35.7937  Y: 38 38.4289");

    doTest("T6",
        "(CAD Page) FIRE, DEBRIS; 4750 SILVERADO TRL ,CALISTOGA ; Inc# 000185; COMPOST FIRE; RA: L54; X: -122 33.324  Y: 38 34.7315; E26 E19 B1414; Cmd: NAPA CO FIRE; Tac CB#: 7073226460",
        "CALL:FIRE, DEBRIS",
        "ADDR:4750 SILVERADO TRL",
        "CITY:CALISTOGA",
        "ID:000185",
        "INFO:COMPOST FIRE",
        "UNIT:E26 E19 B1414",
        "GPS:X: -122 33.324  Y: 38 34.7315",
        "SRC:NAPA CO FIRE");

    doTest("T7",
        "(CAD Page) FIRE, VEH PASSENGER; RLS @ =L(38.654873,-122.596711) ,MIDDLETOWN ; Inc# 000149; NEAR RLS. 60 PICK FLAMES SEEN FROM ENGINE COMPARTMENT; RA: M5; X: -122 CB#: 7073226460",
        "CALL:FIRE, VEH PASSENGER",
        "PLACE:RLS",
        "ADDR:=L(38.654873,-122.596711)",
        "CITY:MIDDLETOWN",
        "ID:000149",
        "INFO:NEAR RLS. 60 PICK FLAMES SEEN FROM ENGINE COMPARTMENT",
        "UNIT:M5",
        "GPS:X: -122");
  }
  
  public static void main(String[] args) {
    new CANapaCountyParserTest().generateTests("T1");
  }
}