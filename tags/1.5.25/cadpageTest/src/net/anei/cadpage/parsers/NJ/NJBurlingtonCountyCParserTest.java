package net.anei.cadpage.parsers.NJ;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class NJBurlingtonCountyCParserTest extends BaseParserTest {
  
  public NJBurlingtonCountyCParserTest() {
    setParser(new NJBurlingtonCountyCParser(), "BURLINGTON COUNTY", "NJ");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "S: Automatic R&amp;R Notification\n" +
        "  \n" +
        "Incident Number  : 2012-00000242                    ORI: E179          Station:  \n" +
        "Incident Type  . :  129 E AccInjry             Priority: 1  \n" +
        "Incident Location: RT206                                                Venue: Southamptn  \n" +
        "  Cross Street . : BUDDTOWN RD \n\n" +
        "Call Time- 18:55:19                                Date- 03/08/2012  \n" +
        "Dispatch - 18:56:59     En-route-              On-scene-              Depart 1-  \n" +
        "Arrive 2 -              Depart 2-              In-statn- \n\n" +
        "Area: 1700              Section :    1         Beat  . : 1790        Map . . :  \n" +
        "Grid:  M16              Quadrant: 1710         District: 1710 \n\n" +
        "Phone Number: (609) 731-1906                Call Source:  911 \n\n" +
        "Caller. . . : ALICE LISTON \n\n" +
        "Units sent  :  \n" +
        " 03171       F1710                03171       F1712  \n" +
        " E178        E1782                E179        E1791 \n\n" +
        "Nature of Call : 2 VEH MVA// ONE FEMALE WITH A HEAD INJURY//NO \n\n" +
        "Additional Info  \n" +
        "  WPH2:  \n" +
        "   OTHER HAZARDS \n\n" +
        "Additional Inc#s:  \n" +
        " E178        201200000257             03171       201200000084 \n\n" +
        "The Call Taker is MCCORMACK KENNETH J  \n" +
        "The Dispatcher is ENDRES ANTHONY G",

        "ID:2012-00000242",
        "CALL:129 E AccInjry",
        "PRI:1",
        "ADDR:RT206",
        "MADDR:RT 206 & BUDDTOWN RD",
        "CITY:Southampton",
        "X:BUDDTOWN RD",
        "TIME:18:55:19",
        "DATE:03/08/2012",
        "MAP:1710/1710/1/1700",
        "PHONE:(609) 731-1906",
        "NAME:ALICE LISTON",
        "UNIT:03171 F1710 03171 F1712 E178 E1782 E179 E1791",
        "INFO:2 VEH MVA// ONE FEMALE WITH A HEAD INJURY//NO");

    doTest("T2",
        "S: Automatic R&amp;R Notification\n" +
        "  \n" +
        "Incident Number  : 2012-00000242                    ORI: E179          Station:  \n" +
        "Incident Type  . :  EMS E EMS Call             Priority: 1  \n" +
        "Incident Location: RT206                                                Venue: Southamptn  \n" +
        "  Cross Street . : BUDDTOWN RD \n\n" +
        "Call Time- 18:55:19                                Date- 03/08/2012  \n" +
        "Dispatch -              En-route-              On-scene-              Depart 1-  \n" +
        "Arrive 2 -              Depart 2-              In-statn- \n\n" +
        "Area: 1700              Section :    1         Beat  . : 1790        Map . . :  \n" +
        "Grid:  M16              Quadrant: 1710         District: 1710 \n\n" +
        "Phone Number: (609) 731-1906                Call Source:  911 \n\n" +
        "Caller. . . : ALICE LISTON \n\n" +
        "Nature of Call : 2 VEH MVA// INJURIES \n\n" +
        "Additional Info  \n" +
        "  WPH2: \n\n" +
        "Additional Inc#s:  \n" +
        " 03171       201200000084 \n\n" +
        "The Call Taker is MCCORMACK KENNETH J  \n" +
        "The Dispatcher is ENDRES ANTHONY G",

        "ID:2012-00000242",
        "CALL:EMS E EMS Call",
        "PRI:1",
        "ADDR:RT206",
        "MADDR:RT 206 & BUDDTOWN RD",
        "CITY:Southampton",
        "X:BUDDTOWN RD",
        "TIME:18:55:19",
        "DATE:03/08/2012",
        "MAP:1710/1710/1/1700",
        "PHONE:(609) 731-1906",
        "NAME:ALICE LISTON",
        "INFO:2 VEH MVA// INJURIES");

    doTest("T3",
        "S: Automatic R&amp;R Notification\n" +
        "  \n" +
        "Incident Number  : 2012-00000083                    ORI: 03171         Station:  \n" +
        "Incident Type  . :   16 F Alarms               Priority: 1  \n" +
        "Incident Location: 511 MEADOWYCK LA                                     Venue: Southamptn  \n" +
        "Located Between  : CHURCH RD/EAYRESTOWN RD \n\n" +
        "Call Time- 12:53:11                                Date- 03/08/2012  \n" +
        "Dispatch - 12:53:24     En-route-              On-scene-              Depart 1-  \n" +
        "Arrive 2 -              Depart 2-              In-statn- \n\n" +
        "Area: 1700              Section :    1         Beat  . : 1790        Map . . :  \n" +
        "Grid:  K14              Quadrant: 1700         District: 1700 \n\n" +
        "Phone Number: (718) 494-6414                Call Source:  TEL \n\n" +
        "Caller. . . : STATE WIDE ALARMS  \n" +
        "Complaintant: OP 401 \n\n" +
        "Units sent  :  \n" +
        " 03171       F171                 03171       F1710  \n" +
        " 03172       F172 \n\n" +
        "Nature of Call : CO ALARM / 609-801-1503- \n\n" +
        "Additional Info  \n" +
        "  TWIN OAKS \n\n" +
        "Address Checks \n\n" +
        "Additional Inc#s:  \n" +
        " 03172       201200000056 \n\n" +
        "The Call Taker is KINNEY DAVID R  \n" +
        "The Dispatcher is KNOTT VINCENT M",

        "ID:2012-00000083",
        "CALL:16 F Alarms",
        "PRI:1",
        "ADDR:511 MEADOWYCK LA",
        "MADDR:511 MEADOWYCK LN",
        "CITY:Southampton",
        "X:CHURCH RD/EAYRESTOWN RD",
        "TIME:12:53:11",
        "DATE:03/08/2012",
        "MAP:1700/1700/1/1700",
        "PHONE:(718) 494-6414",
        "NAME:STATE WIDE ALARMS Complaintant: OP 401",
        "UNIT:03171 F171 03171 F1710 03172 F172",
        "INFO:CO ALARM / 609-801-1503-");

    doTest("T4",
        "S: Automatic R&amp;R Notification\n" +
        "  \n" +
        "Incident Number  : 2012-00000238                    ORI: E179          Station:  \n" +
        "Incident Type  . :  117 E Falls                Priority: 1  \n" +
        "Incident Location: 311 RED LION RD                                      Venue: Southamptn  \n" +
        "Located Between  : NEW FREEDOM RD/HILLIARDS BRIDGE RD \n\n" +
        "Call Time- 17:14:29                                Date- 03/07/2012  \n" +
        "Dispatch - 17:20:28     En-route-              On-scene- 17:20:28     Depart 1-  \n" +
        "Arrive 2 -              Depart 2-              In-statn- \n\n" +
        "Area: 1700              Section :    1         Beat  . : 1790        Map . . :  \n" +
        "Grid:  L16              Quadrant: 1712         District: 1712 \n\n" +
        "Phone Number: (856) 498-4915                Call Source:  911 \n\n" +
        "Caller. . . : RYAN SUSANNE \n\n" +
        "Units sent  :  \n" +
        " E159        E1593                03171       F171  \n" +
        " 03171       F1710                03171       F1712  \n" +
        " 03172       F172                 E178        E1782  \n" +
        " E179        E179 \n\n" +
        "Nature of Call : 16/F THROWN FROM HORSE BACK AND HEAD PAIN \n\n" +
        "Additional Info  \n" +
        "  WPH1:  RADIUS 01 MILES  \n" +
        "  HX BACK PROBLEM \n\n" +
        "Additional Inc#s:  \n" +
        " E159        201200004136             E178        201200000252  \n" +
        " 03171       201200000082             03172       201200000054 \n\n" +
        "The Call Taker is TAYL0R BONNIE J  \n" +
        "The Dispatcher is JOHNSON THOMAS",

        "ID:2012-00000238",
        "CALL:117 E Falls",
        "PRI:1",
        "ADDR:311 RED LION RD",
        "CITY:Southampton",
        "X:NEW FREEDOM RD/HILLIARDS BRIDGE RD",
        "TIME:17:14:29",
        "DATE:03/07/2012",
        "MAP:1712/1712/1/1700",
        "PHONE:(856) 498-4915",
        "NAME:RYAN SUSANNE",
        "UNIT:E159 E1593 03171 F171 03171 F1710 03171 F1712 03172 F172 E178 E1782 E179 E179",
        "INFO:16/F THROWN FROM HORSE BACK AND HEAD PAIN");

    doTest("T5",
        "S: Automatic R&amp;R Notification\n" +
        "  \n" +
        "Incident Number  : 2012-00000238                    ORI: E179          Station:  \n" +
        "Incident Type  . :  117 E Falls                Priority: 1  \n" +
        "Incident Location: 311 RED LION RD                                      Venue: Southamptn  \n" +
        "Located Between  : NEW FREEDOM RD/HILLIARDS BRIDGE RD \n\n" +
        "Call Time- 17:14:29                                Date- 03/07/2012  \n" +
        "Dispatch - 17:20:28     En-route-              On-scene- 17:20:28     Depart 1-  \n" +
        "Arrive 2 -              Depart 2-              In-statn- \n\n" +
        "Area: 1700              Section :    1         Beat  . : 1790        Map . . :  \n" +
        "Grid:  L16              Quadrant: 1712         District: 1712 \n\n" +
        "Phone Number: (856) 498-4915                Call Source:  911 \n\n" +
        "Caller. . . : RYAN SUSANNE \n\n" +
        "Units sent  :  \n" +
        " E159        E1593                03171       F171  \n" +
        " 03171       F1710                E178        E1782  \n" +
        " E179        E179 \n\n" +
        "Nature of Call : 16/F THROWN FROM HORSE BACK AND HEAD PAIN \n\n" +
        "Additional Info  \n" +
        "  WPH1:  RADIUS 01 MILES  \n" +
        "  HX BACK PROBLEM \n\n" +
        "Additional Inc#s:  \n" +
        " E159        201200004136             E178        201200000252  \n" +
        " 03171       201200000082 \n\n" +
        "The Call Taker is TAYL0R BONNIE J  \n" +
        "The Dispatcher is JOHNSON THOMAS",

        "ID:2012-00000238",
        "CALL:117 E Falls",
        "PRI:1",
        "ADDR:311 RED LION RD",
        "CITY:Southampton",
        "X:NEW FREEDOM RD/HILLIARDS BRIDGE RD",
        "TIME:17:14:29",
        "DATE:03/07/2012",
        "MAP:1712/1712/1/1700",
        "PHONE:(856) 498-4915",
        "NAME:RYAN SUSANNE",
        "UNIT:E159 E1593 03171 F171 03171 F1710 E178 E1782 E179 E179",
        "INFO:16/F THROWN FROM HORSE BACK AND HEAD PAIN");

    doTest("T6",
        "S: Automatic R&amp;R Notification\n" +
        "  \n" +
        "Incident Number  : 2012-00000234                    ORI: E179          Station:  \n" +
        "Incident Type  . : 129F E Mva/Fire             Priority: 1  \n" +
        "Incident Location: 2063 RT206                                           Venue: Southamptn  \n" +
        "Located Between  : RIDGE RD/RETREAT RD  \n" +
        "Common Name. . . : HAGERTHEY REPAIR SHOP \n\n" +
        "Call Time- 08:42:10                                Date- 03/07/2012  \n" +
        "Dispatch - 08:44:01     En-route-              On-scene-              Depart 1-  \n" +
        "Arrive 2 -              Depart 2-              In-statn- \n\n" +
        "Area: 1700              Section :    1         Beat  . : 1790        Map . . :  \n" +
        "Grid:  L16              Quadrant: 1712         District: 1712 \n\n" +
        "Phone Number: (609) 584-5000                Call Source:  TEL \n\n" +
        "Caller. . . : DISP 1614  \n" +
        "Complaintant: NJSP-RED LION \n\n" +
        "Units sent  :  \n" +
        " E179        E179                 03171       F171  \n" +
        " 03172       F172                 E178        E1781  \n" +
        " E179        E1791 \n\n" +
        "Nature of Call : MVA DRIVER WITH HEAD LAC//NJSP ON LOC \n\n" +
        "Additional Info  \n" +
        "  IAO \n\n" +
        "Address Checks \n\n" +
        "Additional Inc#s:  \n" +
        " E178        201200000247             03171       201200000081  \n" +
        " 03172       201200000053 \n\n" +
        "The Call Taker is MURRAY SHAKIRA  \n" +
        "The Dispatcher is YERKES WAYNE D",

        "ID:2012-00000234",
        "CALL:129F E Mva/Fire",
        "PRI:1",
        "ADDR:2063 RT206",
        "MADDR:2063 RT 206",
        "CITY:Southampton",
        "X:RIDGE RD/RETREAT RD",
        "PLACE:HAGERTHEY REPAIR SHOP",
        "TIME:08:42:10",
        "DATE:03/07/2012",
        "MAP:1712/1712/1/1700",
        "PHONE:(609) 584-5000",
        "NAME:DISP 1614 Complaintant: NJSP-RED LION",
        "UNIT:E179 E179 03171 F171 03172 F172 E178 E1781 E179 E1791",
        "INFO:MVA DRIVER WITH HEAD LAC//NJSP ON LOC");

    doTest("T7",
        "S: Automatic R&amp;R Notification\n" +
        "  \n" +
        "Incident Number  : 2012-00000083                    ORI: 03171         Station:  \n" +
        "Incident Type  . : FIRE F Fire Call            Priority: 1  \n" +
        "Incident Location: 511 MEADOWYCK LA                                     Venue: Southamptn  \n" +
        "Located Between  : CHURCH RD/EAYRESTOWN RD \n\n" +
        "Call Time- 12:53:11                                Date- 03/08/2012  \n" +
        "Dispatch -              En-route-              On-scene-              Depart 1-  \n" +
        "Arrive 2 -              Depart 2-              In-statn- \n\n" +
        "Area: 1700              Section :    1         Beat  . : 1790        Map . . :  \n" +
        "Grid:  K14              Quadrant: 1700         District: 1700 \n\n" +
        "Phone Number: (718) 494-6414                Call Source:  TEL \n\n" +
        "Caller. . . : STATE WIDE ALARMS  \n" +
        "Complaintant: OP 401 \n\n" +
        "Nature of Call : CO ALARM / 609-801-1503- \n\n" +
        "Additional Info  \n" +
        "  TWIN OAKS \n\n" +
        "Address Checks \n\n" +
        "Additional Inc#s: \n\n" +
        "The Call Taker is KINNEY DAVID R  \n" +
        "The Dispatcher is KNOTT VINCENT M",

        "ID:2012-00000083",
        "CALL:FIRE F Fire Call",
        "PRI:1",
        "ADDR:511 MEADOWYCK LA",
        "MADDR:511 MEADOWYCK LN",
        "CITY:Southampton",
        "X:CHURCH RD/EAYRESTOWN RD",
        "TIME:12:53:11",
        "DATE:03/08/2012",
        "MAP:1700/1700/1/1700",
        "PHONE:(718) 494-6414",
        "NAME:STATE WIDE ALARMS Complaintant: OP 401",
        "INFO:CO ALARM / 609-801-1503-");

    doTest("T8",
        "S: Automatic R&amp;R Notification\n" +
        "  \n" +
        "Incident Number  : 2012-00000234                    ORI: E179          Station:  \n" +
        "Incident Type  . :  EMS E EMS Call             Priority: 1  \n" +
        "Incident Location: 2063 RT206                                           Venue: Southamptn  \n" +
        "Located Between  : RIDGE RD/RETREAT RD  \n" +
        "Common Name. . . : HAGERTHEY REPAIR SHOP \n\n" +
        "Call Time- 08:42:10                                Date- 03/07/2012  \n" +
        "Dispatch -              En-route-              On-scene-              Depart 1-  \n" +
        "Arrive 2 -              Depart 2-              In-statn- \n\n" +
        "Area: 1700              Section :    1         Beat  . : 1790        Map . . :  \n" +
        "Grid:  L16              Quadrant: 1712         District: 1712 \n\n" +
        "Phone Number: (609) 584-5000                Call Source:  TEL \n\n" +
        "Caller. . . : DISP 1614  \n" +
        "Complaintant: NJSP-RED LION \n\n" +
        "Units sent  :  \n" +
        " E179        E179                 E178        E1781  \n" +
        " E179        E1791 \n\n" +
        "Nature of Call : MVA DRIVER WITH HEAD LAC//NJSP ON LOC \n\n" +
        "Additional Info  \n" +
        "  IAO \n\n" +
        "Address Checks \n\n" +
        "Additional Inc#s:  \n" +
        " E178        201200000247             03171       201200000081 \n\n" +
        "The Call Taker is MURRAY SHAKIRA  \n" +
        "The Dispatcher is YERKES WAYNE D",

        "ID:2012-00000234",
        "CALL:EMS E EMS Call",
        "PRI:1",
        "ADDR:2063 RT206",
        "MADDR:2063 RT 206",
        "CITY:Southampton",
        "X:RIDGE RD/RETREAT RD",
        "PLACE:HAGERTHEY REPAIR SHOP",
        "TIME:08:42:10",
        "DATE:03/07/2012",
        "MAP:1712/1712/1/1700",
        "PHONE:(609) 584-5000",
        "NAME:DISP 1614 Complaintant: NJSP-RED LION",
        "UNIT:E179 E179 E178 E1781 E179 E1791",
        "INFO:MVA DRIVER WITH HEAD LAC//NJSP ON LOC");
  }
  
  public static void main(String[] args) {
    new NJBurlingtonCountyCParserTest().generateTests("T1");
  }
}