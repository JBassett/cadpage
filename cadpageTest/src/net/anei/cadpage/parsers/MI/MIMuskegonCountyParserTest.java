package net.anei.cadpage.parsers.MI;

import net.anei.cadpage.parsers.BaseParserTest;
import net.anei.cadpage.parsers.MI.MIMuskegonCountyParser;

import org.junit.Test;

/*
Muskegon County, MI 
Contact: john@duffhouse.net
sender: cad@mcd911.net,9300xxxx
CAD:FYI: ;7610 EASY ST;BENSTON RD;WHITEHALL RD;MED1
CAD:FYI: ;322 E MUSKEGON AV;S LIVINGSTON ST;S BALDWIN ST;WIRED
CAD:FYI: ;1387 W HOLTON WHITEHALL RD;AUTOMOBILE RD;HYDE PARK RD;MED1
CAD:FYI: ;823 S LIVINGSTON ST;E MUSKEGON AV;E LEWIS ST;MED1
CAD:FYI: ;2715 N WEBER RD;DULEY DR;W MCMILLAN RD;FGRAS
CAD:FYI: ;4252 W BARD RD;SIMONELLI RD;ORSHAL RD;FAR
CAD:FYI: ;N WEBER RD/W MICHILLINDA RD;PI1
CAD:FYI: ;ADMIRAL PETROLEUM CO;1007 E COLBY ST;S HALL ST;PETERSON RD;PI1
CAD:FYI: ;180 W ASHLAND ST;AUBURN RD;CENTRAL RD;MED1

Contact: Active911
Agency name: Muskegon Central Dispatch 9-1-1
Location: Muskegon, MI, United States

CAD:FYI: ;STRUCTURE FIRE;2302 MARLETTE ST;AMERICAN AV;RICHARDSON AV;MCTW;POSSIBLE FIRE IN FURNACE .. [01/23/13 16:53:37 MCDDKH1]
CAD:FYI: ;MEDICAL EMERGENCY-PRIORITY 1;PINEGROVE MANOR APARTMENTS;1764-109 E APPLE AV;SHONAT ST;SAUTER ST;MCTW;fem 85 yrs.. c/a/b.. abd pain and back [01/23/13 15:02:26 MCDDKH1] WAS AT ER YESTERDAY .. [01/23/13 15:02:59 MCDDKH1]
CAD:FYI: ;MEDICAL EMERGENCY-PRIORITY 1;PINEGROVE MANOR APARTMENTS;1764-109 E APPLE AV;SHONAT ST;SAUTER ST;MCTW;fem 85 yrs.. c/a/b.. abd pain and back [01/23/13 15:02:26 MCDDKH1]
CAD:FYI: ;MEDICAL EMERGENCY-PRIORITY 1;71 DOGWOOD CT;SYCAMORE DR;WALNUT DR;MCTW;71 YR OLD FEMALE WAS HOSPITALIZED FOR PNEUMONIA LAST WEEK, NOW GOING DOWN HILL.............DIFF BREATH [01/23/13 08:26:14 MCDJFK1]
CAD:FYI: ;MEDICAL EMERGENCY-PRIORITY 1;1588 DEBAKER ST;VIOLET AV;MCTW;MALE DIFF BREATHER [01/23/13 05:17:49 MCDRLJ1]
CAD:FYI: ;STRUCTURE FIRE;2200-BLK CROZIER AV;LEON ST;LEBOEUF ST;MUCT
CAD:MTFD - WORKING FIRE;1782 S WOLF LAKE RD; EGTW;STRUCTURE FIRE
CAD:FYI: ;STRUCTURE FIRE;1782 S WOLF LAKE RD;MISSYS DR;EVANSTON AV;EGTW;PROPANE HEATER IN SHED ON FIRE...CAN HEAR IT HISSING...FLAMES SHOWING [01/22/13 22:31:55 MCDSLP1] SHED IS 8X8...NOT ENDANGERING....30 FT FROM HOUSE...PUMP HOUSE SHED [0
CAD:FYI: ;MEDICAL EMERGENCY-PRIORITY 1;1490 W ADDISION WY;E ADDISION WY;LTTW;88 YO WIFE.....BREATH NORM....DEC LOC.... [01/22/13 19:35:22 MCDAKB1]
CAD:FYI: ;MEDICAL EMERGENCY-PRIORITY 1;40 S DENSMORE ST;N DENSMORE ST;HALL RD;MCTW;78 yo fem... dizzy...BREATH NORM....CONS....HISTORY OF STROKE....ALERT....NOW LABORED BREATH....UNABLE TO WALK ON HER OWN ATT.... [01/22/13 15:06:42 MCDAKB1]
CAD:FYI: ;MEDICAL EMERGENCY-PRIORITY 1;2070 RUSSELL RD;BUEL AV;E RIVER RD;MCTW;MALE CHEST PAIN [01/22/13 13:55:30 MCDMLV1]
CAD:FYI: ;STRUCTURE FIRE;246 E LAKETON AV;HOYT ST;TERRACE ST;MUCT;bathroom on fire...HOME OWNER CAME OVER...SMOKE COMING OUT OF THE WALLS... [01/22/13 09:44:29 MCDRRB1]
CAD:FYI: ;STRUCTURE FIRE;246 MCLAUGHLIN;bathroom on fire...HOME OWNER CAME OVER...SMOKE COMING OUT OF THE WALLS... [01/22/13 09:44:29 MCDRRB1]
CAD:FYI: ;MEDICAL EMERGENCY-PRIORITY 1;1865 WHITEHALL RD;W GILES RD;VICTOR AV;LTTW;COM IS CALLING FOR HER DAD WHO IS HOME ALONE POSS CVA [01/22/13 09:00:12 MCDDMO1]
CAD:MTFD - WORKING FIRE;1247 WHITEHALL RD; MCTW;COMMERICAL FIRE
CAD:FYI: ;1247 WHITEHALL RD;CURVECREST DR;WITHAM RD;SCOTTS AUTOMOTIVE;COMMERICAL FIRE
CAD:FYI: ;MEDICAL EMERGENCY-PRIORITY 1;US31 NB/MM 118;MCTW;Event spawned from VEHICLE ACCIDENT. [01/21/2013 18:01:24 MCDDMO1] BURG CAR SPUN OUT INTO MEDIAN AND HIT TREE [01/21/13 17:43:24 MCDDKH1] [FIRE] NB JUST S/OF HOLTON [01/21/13 17:44:
CAD:FYI: ;1103 N CRESTON RD;E GILES RD;ICE RESCUE
CAD:FYI: ;1103 N CRESTON RD;E GILES RD;ICE RESCUE
CAD:FYI: ;STRUCTURE FIRE;995 W SOUTHERN AV;HUDSON ST;FRANKLIN ST;MUCT;SMELL OF SMOKE IN RES... SLIIGHT HAZE [01/18/13 00:58:52 MCDDER1]
CAD:FYI: ;STRUCTURE FIRE;1851 HUDSON ST;W LAKETON AV;WINDSOR AV;MUCT;2 HOUSES DOWN TWD WINDSOR...............HOUSE IS FILLED WITH SMOKE [01/17/13 08:55:52 MCDJFK1]
CAD:FYI: ;STRUCTURE FIRE;1943 VALLEY ST;E HOLBROOK AV;E KEATING AV;MUCT;SEE SMOKE NO FLAMES CABLE MODEM IN THE OFFICE [01/16/13 13:19:07 MCDDMO1]
CAD:FYI: ;STRUCTURE FIRE;1654 MANZ ST;E FOREST AV;E DALE AV;MUCT;SMOKE FROM BASEMENT WINDOW [01/16/13 10:46:34 MCDAJJ1]
CAD:FYI: ;STRUCTURE FIRE;479 WHITE AV;WILLIAMS ST;SCOTT ST;MUCT;STOVE FIRE [01/15/13 20:30:06 MCDAJJ1]
CAD:FYI: ;STRUCTURE FIRE;1257 EVANSTON AV;GARDEN ST;BURTON ST;MUCT;HOUSE ON FIRE.... SMOKE AND FLAMES SEEN.... [01/15/13 19:42:51 MCDMRS1]
CAD:FYI: ;STRUCTURE FIRE;393 S BROTON RD;HALL RD;E APPLE AV;EGTW;FIRE IN THE CHIMNEY [01/15/13 18:54:26 MCDAJJ1]
CAD:FYI: ;STRUCTURE FIRE;1432 JIROCH ST;IRWIN AV;E GRAND AV;MUCT;OVEN ON FIRE...FLAMES SHOWING...... [01/13/13 14:17:07 MCDAKB1]
CAD:FYI: ;STRUCTURE FIRE;1827 REIGLER RD;BECKER RD;KINGS CT;MCTW;CHIMNEY FIRE IN THE BASEMENT [01/13/13 12:08:07 MCDAKB1]
CAD:FYI: ;STRUCTURE FIRE;3830 E LAKETON AV;S MILL IRON RD;LILAC ST;MCTW;WALL BTWN GARAGE / CHIMNEY / HOUSE .. SMOKE PRETTY GOOD [01/10/13 12:04:41 MCDDKH1]
CAD:Update: ;2258-3 HENRY ST;W HACKLEY AV;W BARNEY AV;COMMERICAL FIRE
CAD:Update: ;STRUCTURE FIRE;2258-3 HENRY ST;W HACKLEY AV;W BARNEY AV;MUCT;FIRE IN THE BATHROOM..... FLAMES AND SMOKE SEEN IN THE BATHROOM [01/10/13 01:46:09 MCDMRS1]
CAD:FYI: ;STRUCTURE FIRE;2258 HENRY ST;W HACKLEY AV;W BARNEY AV;MUCT;FIRE IN THE BATHROOM..... FLAMES AND SMOKE SEEN IN THE BATHROOM [01/10/13 01:46:09 MCDMRS1]
CAD:FYI: ;STRUCTURE FIRE;5983 E APPLE AV;DELORA ST;CHATTERSON RD;EGTW;DVD PLAYER WAS ON FIRE.... [01/09/13 18:59:54 MCDMRS1] FURNACE IS ALSO ACTING... [01/09/13 19:00:07 MCDMRS1] NO SMOKE AND FLAMES SEEN ATT [01/09/13 19:00:17 MCDMRS1] POSS
CAD:FYI: ;STRUCTURE FIRE;1338 JAMES AV;SCHOOL ST;CRESTON ST;MUCT;something fell down chimney and is smoking [01/07/13 14:41:51 MCDMP01]
CAD:FYI: ;STRUCTURE FIRE;43 N MILL IRON RD;S MILL IRON RD;MACARTHUR RD;MCTW;BROTHER SET THE CURTAINS ON FIRE [01/06/13 10:34:23 MCDBMK1]
CAD:FYI: ;STRUCTURE FIRE;4206 SUNDROP CR N;SUNDROP CR W;SUNDROP CR E;EGTW;SMOKE ALARMS WERE GOING OFF/SMELL OF SMOKE INSIDE HOME/POWER GOING OFF AND ON/ [01/06/13 00:37:01 MCDCJE1] CMP WAS ADV TO EVACUATE [01/06/13 00:37:08 MCDCJE1]
CAD:FYI: ;STRUCTURE FIRE;1355 DUDLEY AV;SCHOOL ST;CRESTON ST;MUCT;SMELLS SOMETHING BURNING IN THE HOUSE..... [01/05/13 23:52:44 MCDMRS1]
CAD:FYI: ;3015 E APPLE AV;DEBAKER ST;S WOODLAND ST;46 BAR;COMMERICAL FIRE
CAD:FYI: ;5506 HARVEY ST;E STERNBERG RD;E MOUNT GARFIELD RD;FAMILY CHRISTIAN STORE;COMMERICAL FIRE
CAD:FYI: ;460 E TYLER RD;RUSSELL RD;STRAND RD;SHILOH TABERNACLE;COMMERICAL FIRE
CAD:MTFD - WORKING FIRE;7321-117 WHITE RD; EGTW;STRUCTURE FIRE
CAD:FYI: ;STRUCTURE FIRE;MAPLE ISLAND ESTATES TRLR PK;7321-117 WHITE RD;N MAPLE ISLAND RD;EGTW;NEXT DOOR TO CMP... HOUSE ON FIRE [01/04/13 23:23:39 MCDMRS1] [FIRE] SMOKE AND FLAMES SEEN... FROM THE BACK OF THE TRAILER... [01/04/13 23:24:01 
CAD:FYI: ;STRUCTURE FIRE;362 MONROE AV;5TH ST;6TH ST;MUCT;SMOKE IN THE BASEMENT [01/04/13 15:46:50 MCDAJJ1]
CAD:FYI: ;STRUCTURE FIRE;7479 EVANSTON AV;S MAPLE ISLAND RD;BARNES RD;EGTW;CHIMNEY FIRE [01/03/13 19:41:50 MCDJIP1]

 */

public class MIMuskegonCountyParserTest extends BaseParserTest {
  
  public MIMuskegonCountyParserTest() {
	  setParser(new MIMuskegonCountyParser(), "MUSKEGON COUNTY", "MI");
  }
  
  @Test
  public void testParser() {
    
    doTest("T1",
        "CAD:FYI: ;7610 EASY ST;BENSTON RD;WHITEHALL RD;MED1",
        "ADDR:7610 EASY ST",
        "X:BENSTON RD & WHITEHALL RD",
        "CALL:MED1");

    doTest("T2",
        "CAD:FYI: ;322 E MUSKEGON AV;S LIVINGSTON ST;S BALDWIN ST;WIRED",
        "ADDR:322 E MUSKEGON AV",
        "MADDR:322 E MUSKEGON AVE",
        "X:S LIVINGSTON ST & S BALDWIN ST",
        "CALL:WIRED");
    
    doTest("T3",
        "CAD:FYI: ;1387 W HOLTON WHITEHALL RD;AUTOMOBILE RD;HYDE PARK RD;MED1",
        "CALL:Unkown",
        "ADDR:1387 W HOLTON WHITEHALL RD",
        "X:AUTOMOBILE RD & HYDE PARK RD",
        "CALL:MED1");
        
    doTest("T4",
        "CAD:FYI: ;823 S LIVINGSTON ST;E MUSKEGON AV;E LEWIS ST;MED1",
        "ADDR:823 S LIVINGSTON ST",
        "X:E MUSKEGON AV & E LEWIS ST",
        "CALL:MED1"
        );
        
    doTest("T5",
        "CAD:FYI: ;2715 N WEBER RD;DULEY DR;W MCMILLAN RD;FGRAS",
        "ADDR:2715 N WEBER RD",
        "X:DULEY DR & W MCMILLAN RD",
        "CALL:FGRAS"
        );
    doTest("T6",
        "CAD:FYI: ;N WEBER RD/W MICHILLINDA RD;PI1",
        "ADDR:N WEBER RD & W MICHILLINDA RD",
        "CALL:PI1");
    doTest("T7",
        "CAD:FYI: ;ADMIRAL PETROLEUM CO;1007 E COLBY ST;S HALL ST;PETERSON RD;PI1",
        "PLACE:ADMIRAL PETROLEUM CO",
        "ADDR:1007 E COLBY ST",
        "X:S HALL ST & PETERSON RD",
        "CALL:PI1");

    doTest("T8",
        "CAD:FYI: ;180 W ASHLAND ST;AUBURN RD;CENTRAL RD;MED1",
        "ADDR:180 W ASHLAND ST",
        "X:AUBURN RD & CENTRAL RD",
        "CALL:MED1");
  }
  
  @Test
  public void testMuskegonCentralDispatch() {

    doTest("T1",
        "CAD:FYI: ;STRUCTURE FIRE;2302 MARLETTE ST;AMERICAN AV;RICHARDSON AV;MCTW;POSSIBLE FIRE IN FURNACE .. [01/23/13 16:53:37 MCDDKH1]",
        "CALL:STRUCTURE FIRE",
        "ADDR:2302 MARLETTE ST",
        "X:AMERICAN AV & RICHARDSON AV",
        "CITY:MUSKEGON CHARTER TWP",
        "INFO:POSSIBLE FIRE IN FURNACE ..",
        "DATE:01/23/13",
        "TIME:16:53:37");

    doTest("T2",
        "CAD:FYI: ;MEDICAL EMERGENCY-PRIORITY 1;PINEGROVE MANOR APARTMENTS;1764-109 E APPLE AV;SHONAT ST;SAUTER ST;MCTW;fem 85 yrs.. c/a/b.. abd pain and back [01/23/13 15:02:26 MCDDKH1] WAS AT ER YESTERDAY .. [01/23/13 15:02:59 MCDDKH1]",
        "CALL:MEDICAL EMERGENCY-PRIORITY 1",
        "ADDR:1764-109 E APPLE AV",
        "MADDR:1764 E APPLE AVE",
        "PLACE:PINEGROVE MANOR APARTMENTS",
        "X:SHONAT ST & SAUTER ST",
        "CITY:MUSKEGON CHARTER TWP",
        "INFO:fem 85 yrs.. c/a/b.. abd pain and back / WAS AT ER YESTERDAY ..",
        "DATE:01/23/13",
        "TIME:15:02:26");

    doTest("T3",
        "CAD:FYI: ;MEDICAL EMERGENCY-PRIORITY 1;PINEGROVE MANOR APARTMENTS;1764-109 E APPLE AV;SHONAT ST;SAUTER ST;MCTW;fem 85 yrs.. c/a/b.. abd pain and back [01/23/13 15:02:26 MCDDKH1]",
        "CALL:MEDICAL EMERGENCY-PRIORITY 1",
        "ADDR:1764-109 E APPLE AV",
        "MADDR:1764 E APPLE AVE",
        "PLACE:PINEGROVE MANOR APARTMENTS",
        "X:SHONAT ST & SAUTER ST",
        "CITY:MUSKEGON CHARTER TWP",
        "INFO:fem 85 yrs.. c/a/b.. abd pain and back",
        "DATE:01/23/13",
        "TIME:15:02:26");

    doTest("T4",
        "CAD:FYI: ;MEDICAL EMERGENCY-PRIORITY 1;71 DOGWOOD CT;SYCAMORE DR;WALNUT DR;MCTW;71 YR OLD FEMALE WAS HOSPITALIZED FOR PNEUMONIA LAST WEEK, NOW GOING DOWN HILL.............DIFF BREATH [01/23/13 08:26:14 MCDJFK1]",
        "CALL:MEDICAL EMERGENCY-PRIORITY 1",
        "ADDR:71 DOGWOOD CT",
        "X:SYCAMORE DR & WALNUT DR",
        "CITY:MUSKEGON CHARTER TWP",
        "INFO:71 YR OLD FEMALE WAS HOSPITALIZED FOR PNEUMONIA LAST WEEK, NOW GOING DOWN HILL.............DIFF BREATH",
        "DATE:01/23/13",
        "TIME:08:26:14");

    doTest("T5",
        "CAD:FYI: ;MEDICAL EMERGENCY-PRIORITY 1;1588 DEBAKER ST;VIOLET AV;MCTW;MALE DIFF BREATHER [01/23/13 05:17:49 MCDRLJ1]",
        "CALL:MEDICAL EMERGENCY-PRIORITY 1",
        "ADDR:1588 DEBAKER ST",
        "X:VIOLET AV",
        "CITY:MUSKEGON CHARTER TWP",
        "INFO:MALE DIFF BREATHER",
        "DATE:01/23/13",
        "TIME:05:17:49");

    doTest("T6",
        "CAD:FYI: ;STRUCTURE FIRE;2200-BLK CROZIER AV;LEON ST;LEBOEUF ST;MUCT",
        "CALL:STRUCTURE FIRE",
        "ADDR:2200-BLK CROZIER AV",
        "MADDR:2200 CROZIER AVE",
        "X:LEON ST & LEBOEUF ST",
        "CITY:MUSKEGON");

    doTest("T7",
        "CAD:MTFD - WORKING FIRE;1782 S WOLF LAKE RD; EGTW;STRUCTURE FIRE",
        "CALL:MTFD - WORKING FIRE",
        "ADDR:1782 S WOLF LAKE RD",
        "CITY:EGELSTON TWP",
        "INFO:STRUCTURE FIRE");

    doTest("T8",
        "CAD:FYI: ;STRUCTURE FIRE;1782 S WOLF LAKE RD;MISSYS DR;EVANSTON AV;EGTW;PROPANE HEATER IN SHED ON FIRE...CAN HEAR IT HISSING...FLAMES SHOWING [01/22/13 22:31:55 MCDSLP1] SHED IS 8X8...NOT ENDANGERING....30 FT FROM HOUSE...PUMP HOUSE SHED [0",
        "CALL:STRUCTURE FIRE",
        "ADDR:1782 S WOLF LAKE RD",
        "X:MISSYS DR & EVANSTON AV",
        "CITY:EGELSTON TWP",
        "INFO:PROPANE HEATER IN SHED ON FIRE...CAN HEAR IT HISSING...FLAMES SHOWING / SHED IS 8X8...NOT ENDANGERING....30 FT FROM HOUSE...PUMP HOUSE SHED",
        "DATE:01/22/13",
        "TIME:22:31:55");

    doTest("T9",
        "CAD:FYI: ;MEDICAL EMERGENCY-PRIORITY 1;1490 W ADDISION WY;E ADDISION WY;LTTW;88 YO WIFE.....BREATH NORM....DEC LOC.... [01/22/13 19:35:22 MCDAKB1]",
        "CALL:MEDICAL EMERGENCY-PRIORITY 1",
        "ADDR:1490 W ADDISION WY",
        "X:E ADDISION WY",
        "CITY:LAKETON TWP",
        "INFO:88 YO WIFE.....BREATH NORM....DEC LOC....",
        "DATE:01/22/13",
        "TIME:19:35:22");

    doTest("T10",
        "CAD:FYI: ;MEDICAL EMERGENCY-PRIORITY 1;40 S DENSMORE ST;N DENSMORE ST;HALL RD;MCTW;78 yo fem... dizzy...BREATH NORM....CONS....HISTORY OF STROKE....ALERT....NOW LABORED BREATH....UNABLE TO WALK ON HER OWN ATT.... [01/22/13 15:06:42 MCDAKB1]",
        "CALL:MEDICAL EMERGENCY-PRIORITY 1",
        "ADDR:40 S DENSMORE ST",
        "X:N DENSMORE ST & HALL RD",
        "CITY:MUSKEGON CHARTER TWP",
        "INFO:78 yo fem... dizzy...BREATH NORM....CONS....HISTORY OF STROKE....ALERT....NOW LABORED BREATH....UNABLE TO WALK ON HER OWN ATT....",
        "DATE:01/22/13",
        "TIME:15:06:42");

    doTest("T11",
        "CAD:FYI: ;MEDICAL EMERGENCY-PRIORITY 1;2070 RUSSELL RD;BUEL AV;E RIVER RD;MCTW;MALE CHEST PAIN [01/22/13 13:55:30 MCDMLV1]",
        "CALL:MEDICAL EMERGENCY-PRIORITY 1",
        "ADDR:2070 RUSSELL RD",
        "X:BUEL AV & E RIVER RD",
        "CITY:MUSKEGON CHARTER TWP",
        "INFO:MALE CHEST PAIN",
        "DATE:01/22/13",
        "TIME:13:55:30");

    doTest("T12",
        "CAD:FYI: ;STRUCTURE FIRE;246 E LAKETON AV;HOYT ST;TERRACE ST;MUCT;bathroom on fire...HOME OWNER CAME OVER...SMOKE COMING OUT OF THE WALLS... [01/22/13 09:44:29 MCDRRB1]",
        "CALL:STRUCTURE FIRE",
        "ADDR:246 E LAKETON AV",
        "MADDR:246 E LAKETON AVE",
        "X:HOYT ST & TERRACE ST",
        "CITY:MUSKEGON",
        "INFO:bathroom on fire...HOME OWNER CAME OVER...SMOKE COMING OUT OF THE WALLS...",
        "DATE:01/22/13",
        "TIME:09:44:29");

    doTest("T13",
        "CAD:FYI: ;STRUCTURE FIRE;246 MCLAUGHLIN;bathroom on fire...HOME OWNER CAME OVER...SMOKE COMING OUT OF THE WALLS... [01/22/13 09:44:29 MCDRRB1]",
        "CALL:STRUCTURE FIRE",
        "ADDR:246 MCLAUGHLIN",
        "INFO:bathroom on fire...HOME OWNER CAME OVER...SMOKE COMING OUT OF THE WALLS...",
        "DATE:01/22/13",
        "TIME:09:44:29");

    doTest("T14",
        "CAD:FYI: ;MEDICAL EMERGENCY-PRIORITY 1;1865 WHITEHALL RD;W GILES RD;VICTOR AV;LTTW;COM IS CALLING FOR HER DAD WHO IS HOME ALONE POSS CVA [01/22/13 09:00:12 MCDDMO1]",
        "CALL:MEDICAL EMERGENCY-PRIORITY 1",
        "ADDR:1865 WHITEHALL RD",
        "X:W GILES RD & VICTOR AV",
        "CITY:LAKETON TWP",
        "INFO:COM IS CALLING FOR HER DAD WHO IS HOME ALONE POSS CVA",
        "DATE:01/22/13",
        "TIME:09:00:12");

    doTest("T15",
        "CAD:MTFD - WORKING FIRE;1247 WHITEHALL RD; MCTW;COMMERICAL FIRE",
        "CALL:MTFD - WORKING FIRE",
        "ADDR:1247 WHITEHALL RD",
        "CITY:MUSKEGON CHARTER TWP",
        "INFO:COMMERICAL FIRE");

    doTest("T16",
        "CAD:FYI: ;1247 WHITEHALL RD;CURVECREST DR;WITHAM RD;SCOTTS AUTOMOTIVE;COMMERICAL FIRE",
        "ADDR:1247 WHITEHALL RD",
        "X:CURVECREST DR & WITHAM RD",
        "PLACE:SCOTTS AUTOMOTIVE",
        "CALL:COMMERICAL FIRE");

    doTest("T17",
        "CAD:FYI: ;MEDICAL EMERGENCY-PRIORITY 1;US31 NB/MM 118;MCTW;Event spawned from VEHICLE ACCIDENT. [01/21/2013 18:01:24 MCDDMO1] BURG CAR SPUN OUT INTO MEDIAN AND HIT TREE [01/21/13 17:43:24 MCDDKH1] [FIRE] NB JUST S/OF HOLTON [01/21/13 17:44:",
        "CALL:MEDICAL EMERGENCY-PRIORITY 1",
        "ADDR:US31 NB & MM 118",
        "MADDR:US 31 & MM 118",
        "CITY:MUSKEGON CHARTER TWP",
        "INFO:Event spawned from VEHICLE ACCIDENT. / BURG CAR SPUN OUT INTO MEDIAN AND HIT TREE / NB JUST S/OF HOLTON",
        "DATE:01/21/2013",
        "TIME:18:01:24");

    doTest("T18",
        "CAD:FYI: ;1103 N CRESTON RD;E GILES RD;ICE RESCUE",
        "CALL:ICE RESCUE",
        "ADDR:1103 N CRESTON RD",
        "X:E GILES RD");

    doTest("T19",
        "CAD:FYI: ;1103 N CRESTON RD;E GILES RD;ICE RESCUE",
        "CALL:ICE RESCUE",
        "ADDR:1103 N CRESTON RD",
        "X:E GILES RD");

    doTest("T20",
        "CAD:FYI: ;STRUCTURE FIRE;995 W SOUTHERN AV;HUDSON ST;FRANKLIN ST;MUCT;SMELL OF SMOKE IN RES... SLIIGHT HAZE [01/18/13 00:58:52 MCDDER1]",
        "CALL:STRUCTURE FIRE",
        "ADDR:995 W SOUTHERN AV",
        "MADDR:995 W SOUTHERN AVE",
        "X:HUDSON ST & FRANKLIN ST",
        "CITY:MUSKEGON",
        "INFO:SMELL OF SMOKE IN RES... SLIIGHT HAZE",
        "DATE:01/18/13",
        "TIME:00:58:52");

    doTest("T21",
        "CAD:FYI: ;STRUCTURE FIRE;1851 HUDSON ST;W LAKETON AV;WINDSOR AV;MUCT;2 HOUSES DOWN TWD WINDSOR...............HOUSE IS FILLED WITH SMOKE [01/17/13 08:55:52 MCDJFK1]",
        "CALL:STRUCTURE FIRE",
        "ADDR:1851 HUDSON ST",
        "X:W LAKETON AV & WINDSOR AV",
        "CITY:MUSKEGON",
        "INFO:2 HOUSES DOWN TWD WINDSOR...............HOUSE IS FILLED WITH SMOKE",
        "DATE:01/17/13",
        "TIME:08:55:52");

    doTest("T22",
        "CAD:FYI: ;STRUCTURE FIRE;1943 VALLEY ST;E HOLBROOK AV;E KEATING AV;MUCT;SEE SMOKE NO FLAMES CABLE MODEM IN THE OFFICE [01/16/13 13:19:07 MCDDMO1]",
        "CALL:STRUCTURE FIRE",
        "ADDR:1943 VALLEY ST",
        "X:E HOLBROOK AV & E KEATING AV",
        "CITY:MUSKEGON",
        "INFO:SEE SMOKE NO FLAMES CABLE MODEM IN THE OFFICE",
        "DATE:01/16/13",
        "TIME:13:19:07");

    doTest("T23",
        "CAD:FYI: ;STRUCTURE FIRE;1654 MANZ ST;E FOREST AV;E DALE AV;MUCT;SMOKE FROM BASEMENT WINDOW [01/16/13 10:46:34 MCDAJJ1]",
        "CALL:STRUCTURE FIRE",
        "ADDR:1654 MANZ ST",
        "X:E FOREST AV & E DALE AV",
        "CITY:MUSKEGON",
        "INFO:SMOKE FROM BASEMENT WINDOW",
        "DATE:01/16/13",
        "TIME:10:46:34");

    doTest("T24",
        "CAD:FYI: ;STRUCTURE FIRE;479 WHITE AV;WILLIAMS ST;SCOTT ST;MUCT;STOVE FIRE [01/15/13 20:30:06 MCDAJJ1]",
        "CALL:STRUCTURE FIRE",
        "ADDR:479 WHITE AV",
        "MADDR:479 WHITE AVE",
        "X:WILLIAMS ST & SCOTT ST",
        "CITY:MUSKEGON",
        "INFO:STOVE FIRE",
        "DATE:01/15/13",
        "TIME:20:30:06");

    doTest("T25",
        "CAD:FYI: ;STRUCTURE FIRE;1257 EVANSTON AV;GARDEN ST;BURTON ST;MUCT;HOUSE ON FIRE.... SMOKE AND FLAMES SEEN.... [01/15/13 19:42:51 MCDMRS1]",
        "CALL:STRUCTURE FIRE",
        "ADDR:1257 EVANSTON AV",
        "MADDR:1257 EVANSTON AVE",
        "X:GARDEN ST & BURTON ST",
        "CITY:MUSKEGON",
        "INFO:HOUSE ON FIRE.... SMOKE AND FLAMES SEEN....",
        "DATE:01/15/13",
        "TIME:19:42:51");

    doTest("T26",
        "CAD:FYI: ;STRUCTURE FIRE;393 S BROTON RD;HALL RD;E APPLE AV;EGTW;FIRE IN THE CHIMNEY [01/15/13 18:54:26 MCDAJJ1]",
        "CALL:STRUCTURE FIRE",
        "ADDR:393 S BROTON RD",
        "X:HALL RD & E APPLE AV",
        "CITY:EGELSTON TWP",
        "INFO:FIRE IN THE CHIMNEY",
        "DATE:01/15/13",
        "TIME:18:54:26");

    doTest("T27",
        "CAD:FYI: ;STRUCTURE FIRE;1432 JIROCH ST;IRWIN AV;E GRAND AV;MUCT;OVEN ON FIRE...FLAMES SHOWING...... [01/13/13 14:17:07 MCDAKB1]",
        "CALL:STRUCTURE FIRE",
        "ADDR:1432 JIROCH ST",
        "X:IRWIN AV & E GRAND AV",
        "CITY:MUSKEGON",
        "INFO:OVEN ON FIRE...FLAMES SHOWING......",
        "DATE:01/13/13",
        "TIME:14:17:07");

    doTest("T28",
        "CAD:FYI: ;STRUCTURE FIRE;1827 REIGLER RD;BECKER RD;KINGS CT;MCTW;CHIMNEY FIRE IN THE BASEMENT [01/13/13 12:08:07 MCDAKB1]",
        "CALL:STRUCTURE FIRE",
        "ADDR:1827 REIGLER RD",
        "X:BECKER RD & KINGS CT",
        "CITY:MUSKEGON CHARTER TWP",
        "INFO:CHIMNEY FIRE IN THE BASEMENT",
        "DATE:01/13/13",
        "TIME:12:08:07");

    doTest("T29",
        "CAD:FYI: ;STRUCTURE FIRE;3830 E LAKETON AV;S MILL IRON RD;LILAC ST;MCTW;WALL BTWN GARAGE / CHIMNEY / HOUSE .. SMOKE PRETTY GOOD [01/10/13 12:04:41 MCDDKH1]",
        "CALL:STRUCTURE FIRE",
        "ADDR:3830 E LAKETON AV",
        "MADDR:3830 E LAKETON AVE",
        "X:S MILL IRON RD & LILAC ST",
        "CITY:MUSKEGON CHARTER TWP",
        "INFO:WALL BTWN GARAGE / CHIMNEY / HOUSE .. SMOKE PRETTY GOOD",
        "DATE:01/10/13",
        "TIME:12:04:41");

    doTest("T30",
        "CAD:Update: ;2258-3 HENRY ST;W HACKLEY AV;W BARNEY AV;COMMERICAL FIRE",
        "CALL:COMMERICAL FIRE",
        "ADDR:2258-3 HENRY ST",
        "MADDR:2258 HENRY ST",
        "X:W HACKLEY AV & W BARNEY AV");

    doTest("T31",
        "CAD:Update: ;STRUCTURE FIRE;2258-3 HENRY ST;W HACKLEY AV;W BARNEY AV;MUCT;FIRE IN THE BATHROOM..... FLAMES AND SMOKE SEEN IN THE BATHROOM [01/10/13 01:46:09 MCDMRS1]",
        "CALL:STRUCTURE FIRE",
        "ADDR:2258-3 HENRY ST",
        "MADDR:2258 HENRY ST",
        "X:W HACKLEY AV & W BARNEY AV",
        "CITY:MUSKEGON",
        "INFO:FIRE IN THE BATHROOM..... FLAMES AND SMOKE SEEN IN THE BATHROOM",
        "DATE:01/10/13",
        "TIME:01:46:09");

    doTest("T32",
        "CAD:FYI: ;STRUCTURE FIRE;2258 HENRY ST;W HACKLEY AV;W BARNEY AV;MUCT;FIRE IN THE BATHROOM..... FLAMES AND SMOKE SEEN IN THE BATHROOM [01/10/13 01:46:09 MCDMRS1]",
        "CALL:STRUCTURE FIRE",
        "ADDR:2258 HENRY ST",
        "X:W HACKLEY AV & W BARNEY AV",
        "CITY:MUSKEGON",
        "INFO:FIRE IN THE BATHROOM..... FLAMES AND SMOKE SEEN IN THE BATHROOM",
        "DATE:01/10/13",
        "TIME:01:46:09");

    doTest("T33",
        "CAD:FYI: ;STRUCTURE FIRE;5983 E APPLE AV;DELORA ST;CHATTERSON RD;EGTW;DVD PLAYER WAS ON FIRE.... [01/09/13 18:59:54 MCDMRS1] FURNACE IS ALSO ACTING... [01/09/13 19:00:07 MCDMRS1] NO SMOKE AND FLAMES SEEN ATT [01/09/13 19:00:17 MCDMRS1] POSS",
        "CALL:STRUCTURE FIRE",
        "ADDR:5983 E APPLE AV",
        "MADDR:5983 E APPLE AVE",
        "X:DELORA ST & CHATTERSON RD",
        "CITY:EGELSTON TWP",
        "INFO:DVD PLAYER WAS ON FIRE.... / FURNACE IS ALSO ACTING... / NO SMOKE AND FLAMES SEEN ATT / POSS",
        "DATE:01/09/13",
        "TIME:18:59:54");

    doTest("T34",
        "CAD:FYI: ;STRUCTURE FIRE;1338 JAMES AV;SCHOOL ST;CRESTON ST;MUCT;something fell down chimney and is smoking [01/07/13 14:41:51 MCDMP01]",
        "CALL:STRUCTURE FIRE",
        "ADDR:1338 JAMES AV",
        "MADDR:1338 JAMES AVE",
        "X:SCHOOL ST & CRESTON ST",
        "CITY:MUSKEGON",
        "INFO:something fell down chimney and is smoking",
        "DATE:01/07/13",
        "TIME:14:41:51");

    doTest("T35",
        "CAD:FYI: ;STRUCTURE FIRE;43 N MILL IRON RD;S MILL IRON RD;MACARTHUR RD;MCTW;BROTHER SET THE CURTAINS ON FIRE [01/06/13 10:34:23 MCDBMK1]",
        "CALL:STRUCTURE FIRE",
        "ADDR:43 N MILL IRON RD",
        "X:S MILL IRON RD & MACARTHUR RD",
        "CITY:MUSKEGON CHARTER TWP",
        "INFO:BROTHER SET THE CURTAINS ON FIRE",
        "DATE:01/06/13",
        "TIME:10:34:23");

    doTest("T36",
        "CAD:FYI: ;STRUCTURE FIRE;4206 SUNDROP CR N;SUNDROP CR W;SUNDROP CR E;EGTW;SMOKE ALARMS WERE GOING OFF/SMELL OF SMOKE INSIDE HOME/POWER GOING OFF AND ON/ [01/06/13 00:37:01 MCDCJE1] CMP WAS ADV TO EVACUATE [01/06/13 00:37:08 MCDCJE1]",
        "CALL:STRUCTURE FIRE",
        "ADDR:4206 SUNDROP CR N",
        "MADDR:4206 SUNDROP CIR N",
        "X:SUNDROP CR W & SUNDROP CR E",
        "CITY:EGELSTON TWP",
        "INFO:SMOKE ALARMS WERE GOING OFF/SMELL OF SMOKE INSIDE HOME/POWER GOING OFF AND ON/ / CMP WAS ADV TO EVACUATE",
        "DATE:01/06/13",
        "TIME:00:37:01");

    doTest("T37",
        "CAD:FYI: ;STRUCTURE FIRE;1355 DUDLEY AV;SCHOOL ST;CRESTON ST;MUCT;SMELLS SOMETHING BURNING IN THE HOUSE..... [01/05/13 23:52:44 MCDMRS1]",
        "CALL:STRUCTURE FIRE",
        "ADDR:1355 DUDLEY AV",
        "MADDR:1355 DUDLEY AVE",
        "X:SCHOOL ST & CRESTON ST",
        "CITY:MUSKEGON",
        "INFO:SMELLS SOMETHING BURNING IN THE HOUSE.....",
        "DATE:01/05/13",
        "TIME:23:52:44");

    doTest("T38",
        "CAD:FYI: ;3015 E APPLE AV;DEBAKER ST;S WOODLAND ST;46 BAR;COMMERICAL FIRE",
        "ADDR:3015 E APPLE AV",
        "MADDR:3015 E APPLE AVE",
        "X:DEBAKER ST & S WOODLAND ST",
        "PLACE:46 BAR",
        "CALL:COMMERICAL FIRE");

    doTest("T39",
        "CAD:FYI: ;5506 HARVEY ST;E STERNBERG RD;E MOUNT GARFIELD RD;FAMILY CHRISTIAN STORE;COMMERICAL FIRE",
        "ADDR:5506 HARVEY ST",
        "X:E STERNBERG RD & E MOUNT GARFIELD RD",
        "PLACE:FAMILY CHRISTIAN STORE",
        "CALL:COMMERICAL FIRE");

    doTest("T40",
        "CAD:FYI: ;460 E TYLER RD;RUSSELL RD;STRAND RD;SHILOH TABERNACLE;COMMERICAL FIRE",
        "ADDR:460 E TYLER RD",
        "X:RUSSELL RD & STRAND RD",
        "PLACE:SHILOH TABERNACLE",
        "CALL:COMMERICAL FIRE");

    doTest("T41",
        "CAD:MTFD - WORKING FIRE;7321-117 WHITE RD; EGTW;STRUCTURE FIRE",
        "CALL:MTFD - WORKING FIRE",
        "ADDR:7321-117 WHITE RD",
        "MADDR:7321 WHITE RD",
        "CITY:EGELSTON TWP",
        "INFO:STRUCTURE FIRE");

    doTest("T42",
        "CAD:FYI: ;STRUCTURE FIRE;MAPLE ISLAND ESTATES TRLR PK;7321-117 WHITE RD;N MAPLE ISLAND RD;EGTW;NEXT DOOR TO CMP... HOUSE ON FIRE [01/04/13 23:23:39 MCDMRS1] [FIRE] SMOKE AND FLAMES SEEN... FROM THE BACK OF THE TRAILER... [01/04/13 23:24:01 ",
        "CALL:STRUCTURE FIRE",
        "ADDR:7321-117 WHITE RD",
        "MADDR:7321 WHITE RD",
        "PLACE:MAPLE ISLAND ESTATES TRLR PK",
        "X:N MAPLE ISLAND RD",
        "CITY:EGELSTON TWP",
        "INFO:NEXT DOOR TO CMP... HOUSE ON FIRE / SMOKE AND FLAMES SEEN... FROM THE BACK OF THE TRAILER...",
        "DATE:01/04/13",
        "TIME:23:23:39");

    doTest("T43",
        "CAD:FYI: ;STRUCTURE FIRE;362 MONROE AV;5TH ST;6TH ST;MUCT;SMOKE IN THE BASEMENT [01/04/13 15:46:50 MCDAJJ1]",
        "CALL:STRUCTURE FIRE",
        "ADDR:362 MONROE AV",
        "MADDR:362 MONROE AVE",
        "X:5TH ST & 6TH ST",
        "CITY:MUSKEGON",
        "INFO:SMOKE IN THE BASEMENT",
        "DATE:01/04/13",
        "TIME:15:46:50");

    doTest("T44",
        "CAD:FYI: ;STRUCTURE FIRE;7479 EVANSTON AV;S MAPLE ISLAND RD;BARNES RD;EGTW;CHIMNEY FIRE [01/03/13 19:41:50 MCDJIP1]",
        "CALL:STRUCTURE FIRE",
        "ADDR:7479 EVANSTON AV",
        "MADDR:7479 EVANSTON AVE",
        "X:S MAPLE ISLAND RD & BARNES RD",
        "CITY:EGELSTON TWP",
        "INFO:CHIMNEY FIRE",
        "DATE:01/03/13",
        "TIME:19:41:50");

  }
  
  public static void main(String[] args) {
    new MIMuskegonCountyParserTest().generateTests("T1");
  }
}
