package net.anei.cadpage.parsers.CT;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class CTSimsburyParserTest extends BaseParserTest {
  
  public CTSimsburyParserTest() {
    setParser(new CTSimsburyParser(), "SIMSBURY", "CT");
  }
  
  @Test
  public void testAndrewGaines() {

    doTest("T1",
        "8 ROSWELL RD Suite: 1 TYPE:SF new oven making noises smells like burning rubber",
        "ADDR:8 ROSWELL RD",
        "APT:1",
        "CODE:SF",
        "CALL:Structure Fire",
        "INFO:new oven making noises smells like burning rubber");

    doTest("T2",
        "530 BUSHY HILL RD Suite: 03 TYPE:FA",
        "ADDR:530 BUSHY HILL RD",
        "APT:03",
        "CODE:FA",
        "CALL:Fire Alarm");

    doTest("T3",
        "235 FARMS VILLAGE RD TYPE:VA 2 CAR MVA WITH FLUIDS",
        "ADDR:235 FARMS VILLAGE RD",
        "CODE:VA",
        "CALL:Vehicle Accident",
        "INFO:2 CAR MVA WITH FLUIDS");

    doTest("T4",
        "BUSHY HILL RD&OVERLOOK TER TYPE:VR. REPORTED PARTY ENTRAPT",
        "ADDR:BUSHY HILL RD & OVERLOOK TER",
        "CODE:VR",
        "CALL:Vehicle Rescue",
        "INFO:REPORTED PARTY ENTRAPT");

    doTest("T5",
        "57 WEST MOUNTAIN RD TYPE:VA CAR OFF ROAD INTO TREE. POLICE ON SCENE REQ FD",
        "ADDR:57 WEST MOUNTAIN RD",
        "CODE:VA",
        "CALL:Vehicle Accident",
        "INFO:CAR OFF ROAD INTO TREE. POLICE ON SCENE REQ FD");

    doTest("T6",
        "7 WHITNEY LA TYPE:SA smell of smoke in area - can see \"film\" in air",
        "ADDR:7 WHITNEY LA",
        "MADDR:7 WHITNEY LN",
        "CODE:SA",
        "CALL:Smoke Alarm",
        "INFO:smell of smoke in area - can see \"film\" in air");

    doTest("T7",
        "HOPMEADOW ST&WOODLAND ST TYPE:PA Truck hit telephone pole south of Woodland Street across from DB breaking the guide wire. Police re quest FD check pole.",
        "ADDR:HOPMEADOW ST & WOODLAND ST",
        "CODE:PA",
        "CALL:Police Assist",
        "INFO:Truck hit telephone pole south of Woodland Street across from DB breaking the guide wire. Police re quest FD check pole.");

    doTest("T8",
        "4 BROOK RIDGE TYPE:FA CALLED IN BY ALARM COMPANY",
        "ADDR:4 BROOK RIDGE",
        "CODE:FA",
        "CALL:Fire Alarm",
        "INFO:CALLED IN BY ALARM COMPANY");

    doTest("T9",
        "12 CRANE PL TYPE:LS life star, end of cul de sac",
        "ADDR:12 CRANE PL",
        "CODE:LS",
        "CALL:Lift Star",
        "INFO:life star, end of cul de sac");

    doTest("T10",
        "BUSHY HILL RD&ALBANY TNPK TYPE:VR mva rollover w/extrication",
        "ADDR:BUSHY HILL RD & ALBANY TNPK",
        "CODE:VR",
        "CALL:Vehicle Rescue",
        "INFO:mva rollover w/extrication");

  }
  
  public static void main(String[] args) {
    new CTSimsburyParserTest().generateTests("T1");
  }
}