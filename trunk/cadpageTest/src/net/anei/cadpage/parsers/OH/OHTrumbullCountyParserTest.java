package net.anei.cadpage.parsers.OH;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class OHTrumbullCountyParserTest extends BaseParserTest {
  
  public OHTrumbullCountyParserTest() {
    setParser(new OHTrumbullCountyParser(), "TRUMBULL COUNTY", "OH");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "FRM:911no@co.trumbull.oh.us\n" +
        "MSG:*2350 NILES CORTLAND RD SE, HOWLAND TWP, OH***FIRE INFORMATION***STA30***//**ALARM SYSTEM OFF LINE TILL  0900 TOMORROW",

        "ADDR:2350 NILES CORTLAND RD SE",
        "CITY:HOWLAND TWP",
        "CALL:FIRE INFORMATION",
        "SRC:STA30",
        "INFO:ALARM SYSTEM OFF LINE TILL  0900 TOMORROW");

    doTest("T2",
        "FRM:911no@co.trumbull.oh.us\n" +
        "MSG:*8164 CHERRY HILL DR NE, HOWLAND TWP, OH***GENERAL ILLNESS***STA30***ELMWOOD AVE NE//CRANBROOK AVE\n" +
        "(Con't) 2 of 2\n" +
        "NE**PAIN IN SIDE AND HEADACHE//  63 YOF(End)",

        "ADDR:8164 CHERRY HILL DR NE",
        "CITY:HOWLAND TWP",
        "CALL:GENERAL ILLNESS",
        "SRC:STA30",
        "X:ELMWOOD AVE NE/CRANBROOK AVE NE",
        "INFO:PAIN IN SIDE AND HEADACHE//  63 YOF");

    doTest("T3",
        "1 of 3\n" +
        "FRM:911no@co.trumbull.oh.us\n" +
        "MSG:*8235 FAIRHILL DR NE, HOWLAND TWP, OH***EMS UNKNOWN PROBLEM/UNCLASSIFIED***STA30***LONGVIEW DR\n" +
        "(Con't) 2 of 3\n" +
        "NE//TIMBERLANE ST NE**80 YOF THAT HAS DEMENTIA HAS BEEN BATTLING CALLER ALL NITE.DOES NOT HAVE ANY MEDICATIONS TO CALM HER  PER DR  REQ\n" +
        "(Con't) 3 of 3\n" +
        "TRANSPORTED(End)",

        "ADDR:8235 FAIRHILL DR NE",
        "CITY:HOWLAND TWP",
        "CALL:EMS UNKNOWN PROBLEM/UNCLASSIFIED",
        "SRC:STA30",
        "X:LONGVIEW DR NE/TIMBERLANE ST NE",
        "INFO:80 YOF THAT HAS DEMENTIA HAS BEEN BATTLING CALLER ALL NITE.DOES NOT HAVE ANY MEDICATIONS TO CALM HER  PER DR  REQ TRANSPORTED");

  }
  
  @Test
  public void testActive911A() {

    doTest("T1",
        "[] *902 S IRVINE AVE SE, BROOKFIELD TWP, OH***ELECTRICAL FIRE***STA18***ROBERTS ST SE//S IRVINE AVE SE**CALLER ADV LIGHTNING JUST STRUCK THE TELEPHONE POLE NEAR THE CIRCLE K ON 62 AND THE TRANSFORMER EXPLODED // UNK IF ON FIRE\r\n" +
        "\r\n" +
        "\r\n",

        "ADDR:902 S IRVINE AVE SE",
        "CITY:BROOKFIELD TWP",
        "CALL:ELECTRICAL FIRE",
        "SRC:STA18",
        "X:ROBERTS ST SE/S IRVINE AVE SE",
        "INFO:CALLER ADV LIGHTNING JUST STRUCK THE TELEPHONE POLE NEAR THE CIRCLE K ON 62 AND THE TRANSFORMER EXPLODED // UNK IF ON FIRE");

    doTest("T2",
        "[] *YOUNGSTOWN CONNEAUT RD/KING GRAVES RD NE, YANKEE LAKE***TRAFFIC REGULATION COMPLAINT***STA18***//**KINGS GRAVES W ST RTE 7 FOR BRANCH IN RDWAY\r\n" +
        "\r\n" +
        "\r\n",

        "ADDR:YOUNGSTOWN CONNEAUT RD & KING GRAVES RD NE",
        "CITY:YANKEE LAKE",
        "CALL:TRAFFIC REGULATION COMPLAINT",
        "SRC:STA18",
        "INFO:KINGS GRAVES W ST RTE 7 FOR BRANCH IN RDWAY");

    doTest("T3",
        "[] *6567 KING GRAVES RD NE, HARTFORD TWP, OH***FALL***STA18***NORTH ALBRIGHT MCKAY RD NE//**89YOM FELL IN THE BATHROOM AN HOUR AGO AND IS STILL DOWN// SAYS BACK HURTS\r\n" +
        "\r\n" +
        "\r\n",

        "ADDR:6567 KING GRAVES RD NE",
        "CITY:HARTFORD TWP",
        "CALL:FALL",
        "SRC:STA18",
        "X:NORTH ALBRIGHT MCKAY RD NE",
        "INFO:89YOM FELL IN THE BATHROOM AN HOUR AGO AND IS STILL DOWN// SAYS BACK HURTS");

    doTest("T4",
        "[] *6034 MERWIN CHASE RD NE, BROOKFIELD TWP, OH***EMS UNKNOWN PROBLEM/UNCLASSIFIED***STA18***WARNER RD NE//NORTH ALBRIGHT MCKAY RD NE**79 YOF WITH SHARP IN RIGHT   SIDE..\r\n" +
        "\r\n" +
        "\r\n",

        "ADDR:6034 MERWIN CHASE RD NE",
        "CITY:BROOKFIELD TWP",
        "CALL:EMS UNKNOWN PROBLEM/UNCLASSIFIED",
        "SRC:STA18",
        "X:WARNER RD NE/NORTH ALBRIGHT MCKAY RD NE",
        "INFO:79 YOF WITH SHARP IN RIGHT   SIDE..");

    doTest("T5",
        "[] *6812 WARREN SHARON RD, BROOKFIELD TWP, OH***DIABETIC***STA18***SEABORN ST SE//ST RTE 7 SE**46 YOM DIABETIC PROBLEM\r\n\r\n\r\n",
        "ADDR:6812 WARREN SHARON RD",
        "CITY:BROOKFIELD TWP",
        "CALL:DIABETIC",
        "SRC:STA18",
        "X:SEABORN ST SE/ST RTE 7 SE",
        "INFO:46 YOM DIABETIC PROBLEM");

    doTest("T6",
        "[] *8055 ADDISON RD SE, BROOKFIELD TWP, OH***EMERGENCY MEDICAL ASSISTANCE***STA18***ST RTE 82//HILLTOP GARDENS APARTMENTS**\r\n\r\n\r\n",
        "ADDR:8055 ADDISON RD SE",
        "CITY:BROOKFIELD TWP",
        "CALL:EMERGENCY MEDICAL ASSISTANCE",
        "SRC:STA18",
        "X:ST RTE 82/HILLTOP GARDENS APARTMENTS");

  }
  
  public static void main(String[] args) {
    new OHTrumbullCountyParserTest().generateTests("T1");
  }
}