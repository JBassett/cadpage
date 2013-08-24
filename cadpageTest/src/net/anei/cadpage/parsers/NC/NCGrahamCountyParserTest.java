package net.anei.cadpage.parsers.NC;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;

/*
Graham County, NC
Contact: bob keber <bobkeber3d@gmail.com>
Sender:  dispatch@911email.net

(E911) FALL\nJERRY JUMPER 8284792451\n784 CORNSILK BRANCH RD, ROBBINSVILLE
(E911) UNKNOWN EMS\nTRI CO.TEC. - PHILLIPS BUILDING \n70 MOOSE BRANCH RD, ROBBINSVILLE
(E911) LIFELINE ALARM\n\n301 MOOSE BRANCH RD\nAPT # 5C
(E911) UNRESPONSIVE\nGRAHAM HEALTHCARE AND REHAB 8284798421\n811 SNOWBIRD RD, ROBBINSVILLE
(E911) SICK PERSON\nMILLSAPS,VAN 8287351549\n325 ELLER BRANCH RD, ROBBINSVILLE\nNC 28771
(E911) BREATHING DIFFICULTY\nERNEST HOLLIFIELD 8284793398\n494 ELLER BRANCH RD, ROBBINSVILLE
(E911) THIRD CALL EMS\nEMERGENCY SERVICES 8284797985\n70 W FORT HILL, ROBBINSVILLE
(E911) FRACTURE\nHEROLD CARRINGER 8284796832\n115 NATHAN GARLAND RD, ROBBINSVILLE

 */

public class NCGrahamCountyParserTest extends BaseParserTest {
  
  public NCGrahamCountyParserTest() {
    setParser(new NCGrahamCountyParser(), "GRAHAM COUNTY", "NC");
  }
  
  @Test
  public void test1Parser() {

    doTest("T1",
        "(E911) FALL\nJERRY JUMPER 8284792451\n784 CORNSILK BRANCH RD, ROBBINSVILLE",
        "CALL:FALL",
        "PHONE:8284792451",
        "NAME:JERRY JUMPER",
        "ADDR:784 CORNSILK BRANCH RD",
        "CITY:ROBBINSVILLE");

    doTest("T2",
        "(E911) UNKNOWN EMS\nTRI CO.TEC. - PHILLIPS BUILDING \n70 MOOSE BRANCH RD, ROBBINSVILLE",
        "CALL:UNKNOWN EMS",
        "PLACE:TRI CO.TEC. - PHILLIPS BUILDING",
        "ADDR:70 MOOSE BRANCH RD",
        "CITY:ROBBINSVILLE");

    doTest("T3",
        "(E911) LIFELINE ALARM\n\n301 MOOSE BRANCH RD\nAPT # 5C",
        "CALL:LIFELINE ALARM",
        "ADDR:301 MOOSE BRANCH RD",
        "APT:# 5C");

    doTest("T4",
        "(E911) UNRESPONSIVE\nGRAHAM HEALTHCARE AND REHAB 8284798421\n811 SNOWBIRD RD, ROBBINSVILLE",
        "CALL:UNRESPONSIVE",
        "PHONE:8284798421",
        "PLACE:GRAHAM HEALTHCARE AND REHAB",
        "ADDR:811 SNOWBIRD RD",
        "CITY:ROBBINSVILLE");

    doTest("T5",
        "(E911) SICK PERSON\nMILLSAPS,VAN 8287351549\n325 ELLER BRANCH RD, ROBBINSVILLE\nNC 28771",
        "CALL:SICK PERSON",
        "PHONE:8287351549",
        "NAME:MILLSAPS,VAN",
        "ADDR:325 ELLER BRANCH RD",
        "CITY:ROBBINSVILLE",
        "INFO:NC 28771");

    doTest("T6",
        "(E911) BREATHING DIFFICULTY\nERNEST HOLLIFIELD 8284793398\n494 ELLER BRANCH RD, ROBBINSVILLE",
        "CALL:BREATHING DIFFICULTY",
        "PHONE:8284793398",
        "NAME:ERNEST HOLLIFIELD",
        "ADDR:494 ELLER BRANCH RD",
        "CITY:ROBBINSVILLE");

    doTest("T7",
        "(E911) THIRD CALL EMS\nEMERGENCY SERVICES 8284797985\n70 W FORT HILL, ROBBINSVILLE",
        "CALL:THIRD CALL EMS",
        "PHONE:8284797985",
        "NAME:EMERGENCY SERVICES",
        "ADDR:70 W FORT HILL",
        "CITY:ROBBINSVILLE");

    doTest("T8",
        "(E911) FRACTURE\nHEROLD CARRINGER 8284796832\n115 NATHAN GARLAND RD, ROBBINSVILLE",
        "CALL:FRACTURE",
        "PHONE:8284796832",
        "NAME:HEROLD CARRINGER",
        "ADDR:115 NATHAN GARLAND RD",  // Not maping
        "CITY:ROBBINSVILLE");
   
  }
  

  public static void main(String[] args) {
    new NCGrahamCountyParserTest().generateTests("T1");
  }
}
