package net.anei.cadpage.parsers.VA;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class VAIsleOfWightCountyParserTest extends BaseParserTest {
  
  public VAIsleOfWightCountyParserTest() {
    setParser(new VAIsleOfWightCountyParser(), "WIGHT COUNTY", "VA");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "11365 CENTRAL HILL RD;S CAROLINA AVE;COURTHOUSE HWY;FIRE ALARM;smoke detector zone 16 res last name babb 757-353-0490 [06/20/11 19:01:18 MPMARSH]",
        "ADDR:11365 CENTRAL HILL RD",
        "X:S CAROLINA AVE & COURTHOUSE HWY",
        "CALL:FIRE ALARM",
        "INFO:smoke detector zone 16 res last name babb 757-353-0490");

    doTest("T2",
        "YELLOW HAMMER RD/FIRE TOWER RD;1050;[LAW] {37} ONE FINGER AND JAW/ SEC CONCUSION [06/22/11 04:02:28 SHILL] {37} HAVE INJURIES [06/22/11 04:02:04 SHILL] VSP AD:",
        "ADDR:YELLOW HAMMER RD & FIRE TOWER RD",
        "CALL:1050",
        "INFO:{37} ONE FINGER AND JAW/ SEC CONCUSION / {37} HAVE INJURIES / VSP AD:");

    doTest("T3",
        "23352 COURTHOUSE HWY;CONSULATE HEALTH CARE WINDSOR;KENSINGTON CT;JOYNER TOWN LN;FIRE ALARM;[LAW] WILL TRY TO CONTACT KEYHOLDER [06/22/11 12:20:01 MDUNN] zone",
        "ADDR:23352 COURTHOUSE HWY",
        "PLACE:CONSULATE HEALTH CARE WINDSOR",
        "X:KENSINGTON CT & JOYNER TOWN LN",
        "CALL:FIRE ALARM",
        "INFO:WILL TRY TO CONTACT KEYHOLDER / zone");

    doTest("T4",
        "77 CASTLE ST;LIBERTY AVE;BELLMONT ST;FIRE OTHER NOT LISTED;microwave hot and not in use [06/23/11 09:59:22 JTEMPLETON].",
        "ADDR:77 CASTLE ST",
        "X:LIBERTY AVE & BELLMONT ST",
        "CALL:FIRE OTHER NOT LISTED",
        "INFO:microwave hot and not in use / .");

    doTest("T5",
        "W WINDSOR BLVD/N PRINCE BLVD;ACCIDENT WITH INJURIES;supreme prking lot. 2 veh. chryslter pt cruiser vs dodge ram piick up [06/23/11 13:13:34 JTEMPLETON]",
        "ADDR:W WINDSOR BLVD & N PRINCE BLVD",
        "CALL:ACCIDENT WITH INJURIES",
        "INFO:supreme prking lot. 2 veh. chryslter pt cruiser vs dodge ram piick up");
  }
  
  public static void main(String[] args) {
    new VAIsleOfWightCountyParserTest().generateTests("T1");
  }
}