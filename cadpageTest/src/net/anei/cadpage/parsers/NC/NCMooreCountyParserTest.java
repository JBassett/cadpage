package net.anei.cadpage.parsers.NC;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class NCMooreCountyParserTest extends BaseParserTest {
  
  public NCMooreCountyParserTest() {
    setParser(new NCMooreCountyParser(), "MOORE COUNTY", "NC");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "[[911 NOTIFICATION] ]  505 PINEHURST ST ABERDEEN 2011024766 19:52:16 F69 STRUCTURE FIRE LOOKS LIKE BACK DECK ON FIRE",
        "ADDR:505 PINEHURST ST",
        "CITY:ABERDEEN",
        "ID:2011024766",
        "TIME:19:52:16",
        "UNIT:F69",
        "INFO:STRUCTURE FIRE LOOKS LIKE BACK DECK ON FIRE");

    doTest("T2",
        "[[911 NOTIFICATION] ]  1 S. VINELAND SOUTH CELL TOWER 2011025029 09:31:20 M25 PSYCHIATRIC/SUICIDE ATTEMPT",
        "ADDR:1 S VINELAND SOUTH CELL TOWER",
        "ID:2011025029",
        "TIME:09:31:20",
        "UNIT:M25",
        "CALL:PSYCHIATRIC/SUICIDE ATTEMPT");

    doTest("T3",
        "[[911 NOTIFICATION] ]  126 ROBIN HOOD LN ABERDEEN 2011025065 13:37:27 F52 ALARM-FIRE ACTIVATION AC 800 932-3822 OPER SP8",
        "ADDR:126 ROBIN HOOD LN",
        "CITY:ABERDEEN",
        "ID:2011025065",
        "TIME:13:37:27",
        "UNIT:F52",
        "INFO:ALARM-FIRE ACTIVATION AC 800 932-3822 OPER SP8");

    doTest("T4",
        "[[911 NOTIFICATION] ]  632 SAND PIT RD ABERDEEN MDL 07A01 2011025721 11:06:58 M7 BURNS/EXPLOSIONS",
        "ADDR:632 SAND PIT RD",
        "CITY:ABERDEEN",
        "CODE:07A01",
        "ID:2011025721",
        "TIME:11:06:58",
        "UNIT:M7",
        "CALL:BURNS/EXPLOSIONS");

    doTest("T5",
        "[[911 NOTIFICATION] ]  218 BERRY ST PINE BLUFF 2011026135 14:36:24 F67 OUTSIDE FIRE/WOODS/BRUSH TREE ON FIRE",
        "ADDR:218 BERRY ST",
        "CITY:PINE BLUFF",
        "ID:2011026135",
        "TIME:14:36:24",
        "UNIT:F67",
        "INFO:OUTSIDE FIRE/WOODS/BRUSH TREE ON FIRE");

    doTest("T6",
        "[[911 NOTIFICATION] ]  1 E NEW ENGLAND /S PEAR 2011025862 07:31:07 F67 OUTSIDE FIRE/WOODS/BRUSH",
        "ADDR:E NEW ENGLAND & S PEAR",
        "ID:2011025862",
        "TIME:07:31:07",
        "UNIT:F67",
        "CALL:OUTSIDE FIRE/WOODS/BRUSH");

    doTest("T7",
        "6 PAR DR WHISPERING PINES 2011045777 11:36:50 M10 CHEST PAIN CHEST PAIN",
        "ADDR:6 PAR DR",
        "PLACE:WHISPERING PINES",
        "ID:2011045777",
        "TIME:11:36:50",
        "UNIT:M10",
        "CALL:CHEST PAIN CHEST PAIN");

    doTest("T8",
        "232 ALLEN LN ABERDEEN MDL 30A01 2012028950 15:18:54 M30 TRAUMATIC INJURIES\r",
        "ADDR:232 ALLEN LN",
        "CITY:ABERDEEN",
        "CODE:30A01",
        "ID:2012028950",
        "TIME:15:18:54",
        "UNIT:M30",
        "CALL:TRAUMATIC INJURIES");
  }
  
  @Test
  public void testNewParser() {

    doTest("T1",
        "358 R SANDS RD ABERDEEN, MDL 31D02, 2012031517, 15:45:55, M31 UNCONSCIOUSNESS/FAINTING (NEAR),\r",
        "ADDR:358 R SANDS RD",
        "CITY:ABERDEEN",
        "CODE:31D02",
        "ID:2012031517",
        "TIME:15:45:55",
        "UNIT:M31",
        "CALL:UNCONSCIOUSNESS/FAINTING",
        "INFO:(NEAR)");

    doTest("T2",
        "232 ALLEN LN ABERDEEN MDL 30A01 2012028950 15:18:54 M30 TRAUMATIC INJURIES\r",
        "ADDR:232 ALLEN LN",
        "CITY:ABERDEEN",
        "CODE:30A01",
        "ID:2012028950",
        "TIME:15:18:54",
        "UNIT:M30",
        "CALL:TRAUMATIC INJURIES");

    doTest("T3",
        "3216 CALLAWAY RD, 2012031795, 05:27:06, F65 MUTUAL AID, COMMERCIAL STRUCTURE CROSS STREET MONTROSE",
        "ADDR:3216 CALLAWAY RD",
        "ID:2012031795",
        "TIME:05:27:06",
        "UNIT:F65",
        "CALL:MUTUAL AID",
        "INFO:COMMERCIAL STRUCTURE CROSS STREET MONTROSE");

    doTest("T4",
        "S: M:1885 ADDOR RD ABERDEEN, 2012031801, 07:04:25, F18 STORM DAMAGE, ",
        "ADDR:1885 ADDOR RD",
        "CITY:ABERDEEN",
        "ID:2012031801",
        "TIME:07:04:25",
        "UNIT:F18",
        "CALL:STORM DAMAGE");

    doTest("T5",
        "S: M:675 WHITE ROCK RD VASS, 2012031934, 02:47:52, M29 TRAFFIC ACCIDENT,",
        "ADDR:675 WHITE ROCK RD",
        "CITY:VASS",
        "ID:2012031934",
        "TIME:02:47:52",
        "UNIT:M29",
        "CALL:TRAFFIC ACCIDENT");

    doTest("T6",
        "S: M:128 JOHNS WAY VASS, MDL 31D03, 11:05:29, M31 UNCONSCIOUSNESS/FAINTING (NEAR),",
        "ADDR:128 JOHNS WAY",
        "CITY:VASS",
        "CODE:31D03",
        "TIME:11:05:29",
        "UNIT:M31",
        "CALL:UNCONSCIOUSNESS/FAINTING",
        "INFO:(NEAR)");

    doTest("T7",
        "144 SHADY WOOD CT WEST END, FDL 69D06, 08:46:34, F69 STRUCTURE FIRE, WOKE UP AND HER STOVE WOULDNT OPEN.\r",
        "ADDR:144 SHADY WOOD CT",
        "CITY:WEST END",
        "CODE:69D06",
        "TIME:08:46:34",
        "UNIT:F69",
        "CALL:STRUCTURE FIRE",
        "INFO:WOKE UP AND HER STOVE WOULDNT OPEN.");
  }
  
  @Test
  public void testActive911A() {

    doTest("T1",
        "3000 LAKEBAY RD VASS, 06:43:02, F52 ALARM-FIRE ACTIVATION, ZONE: 10 HOUSE SMOKE CALLBACK: 877 350 5292 OP: 24121",
        "ADDR:3000 LAKEBAY RD",
        "CITY:VASS",
        "TIME:06:43:02",
        "UNIT:F52",
        "CALL:ALARM-FIRE ACTIVATION",
        "INFO:ZONE: 10 HOUSE SMOKE CALLBACK: 877 350 5292 OP: 24121");

    doTest("T2",
        "981 RING RD CARTHAGE, 11:38:58, M10 CHEST PAIN,",
        "ADDR:981 RING RD",
        "CITY:CARTHAGE",
        "TIME:11:38:58",
        "UNIT:M10",
        "CALL:CHEST PAIN");

    doTest("T3",
        "120 PINE DR VASS, MDL 26C01, 14:22:44, M26 SICK PERSON,",
        "ADDR:120 PINE DR",
        "CITY:VASS",
        "CODE:26C01",
        "TIME:14:22:44",
        "UNIT:M26",
        "CALL:SICK PERSON");

    doTest("T4",
        "628 BOYS CAMP RD VASS, MDL 10D02, 19:15:14, M10 CHEST PAIN,",
        "ADDR:628 BOYS CAMP RD",
        "CITY:VASS",
        "CODE:10D02",
        "TIME:19:15:14",
        "UNIT:M10",
        "CALL:CHEST PAIN");

    doTest("T5",
        "888 HARNETT RD CAMERON, MDL 06D02-A, 23:28:45, M6 BREATHING PROBLEMS,",
        "ADDR:888 HARNETT RD",
        "CITY:CAMERON",
        "CODE:06D02-A",
        "TIME:23:28:45",
        "UNIT:M6",
        "CALL:BREATHING PROBLEMS");

    doTest("T6",
        "465 EDMONDS RD CAMERON, 01:47:54, F18 STORM DAMAGE, OFF OF CRANES CREEK RD TREE IN ROAD WAY",
        "ADDR:465 EDMONDS RD",
        "CITY:CAMERON",
        "TIME:01:47:54",
        "UNIT:F18",
        "CALL:STORM DAMAGE",
        "INFO:OFF OF CRANES CREEK RD TREE IN ROAD WAY");

    doTest("T7",
        "262 HERON RD CAMERON, FDL 69D06-O, 11:13:48, F69 STRUCTURE FIRE,",
        "ADDR:262 HERON RD",  // Found 262 LAKE HERON DR???
        "CITY:CAMERON",
        "CODE:69D06-O",
        "TIME:11:13:48",
        "UNIT:F69",
        "CALL:STRUCTURE FIRE");

    doTest("T8",
        "1 LAMMS GROVE RD @ ROBERTS RD, 03:26:22, M29 TRAFFIC ACCIDENT, VEH OVERTURNED IN THE FIELD",
        "ADDR:LAMMS GROVE RD & ROBERTS RD",
        "TIME:03:26:22",
        "UNIT:M29",
        "CALL:TRAFFIC ACCIDENT",
        "INFO:VEH OVERTURNED IN THE FIELD");

    doTest("T9",
        "418 MEYER FARM DR SOUTHERN PINES, FDL 69D06, 18:48:58, F69 STRUCTURE FIRE,",
        "ADDR:418 MEYER FARM DR",
        "CITY:SOUTHERN PINES",
        "CODE:69D06",
        "TIME:18:48:58",
        "UNIT:F69",
        "CALL:STRUCTURE FIRE");

  }   
  

  public static void main(String[] args) {
    new NCMooreCountyParserTest().generateTests("T1", "ADDR CITY PLACE CODE ID TIME UNIT CALL INFO");
  }
}
