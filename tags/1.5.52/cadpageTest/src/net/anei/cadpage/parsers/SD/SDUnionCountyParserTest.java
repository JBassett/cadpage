package net.anei.cadpage.parsers.SD;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class SDUnionCountyParserTest extends BaseParserTest {
  
  public SDUnionCountyParserTest() {
    setParser(new SDUnionCountyParser(), "UNION COUNTY", "SD");
  }
  
  @Test
  public void testActive911A() {

    doTest("T1",
        "(J:SICK PERSON/212 E SAWGRASS TRL DAKOTA DUNES/DAKOTA DUNES BLVD & PEBBLE BEACH DR/) SICK PERSON/212 E SAWGRASS TRL DAKOTA DUNES/DAKOTA DUNES BLVD & PEBBLE BEACH DR/DDUNES/ 72EMS9/CANCER PATIENT--58YR FEMALE",
        "CALL:SICK PERSON",
        "ADDR:212 E SAWGRASS TRL",
        "CITY:DAKOTA DUNES",
        "X:DAKOTA DUNES BLVD & PEBBLE BEACH DR",
        "SRC:DDUNES",
        "UNIT:72EMS9",
        "INFO:CANCER PATIENT--58YR FEMALE");

    doTest("T2",
        "(J:FIRE ALARM/663 W SAWGRASS TRL DAKOTA DUNES/FIRETHORN TRL & SPYGLASS CIR/DDUNES/) FIRE ALARM/663 W SAWGRASS TRL DAKOTA DUNES/FIRETHORN TRL & SPYGLASS CIR/DDUNES/ 72E5/HOUSE SMOKE DETECTORS---HESSE RES",
        "CALL:FIRE ALARM",
        "ADDR:663 W SAWGRASS TRL",
        "CITY:DAKOTA DUNES",
        "X:FIRETHORN TRL & SPYGLASS CIR",
        "SRC:DDUNES",
        "UNIT:72E5",
        "INFO:HOUSE SMOKE DETECTORS---HESSE RES");

    doTest("T3",
        "(J:GRASS FIRE/1 UNKNOWN ST NORTH SIOUX UNKNS/NSIOUX/ 72B1 72EMS9 72E4 72E5 72F7 72Q) GRASS FIRE/1 UNKNOWN ST NORTH SIOUX UNKNS/NSIOUX/ 72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8/COUNTY RD 23  IN 2 DIFF SPOTS FIRE S THAT ARE 200SQ FT",
        "CALL:GRASS FIRE",
        "ADDR:1 UNKNOWN ST", // Not mapping, no surprise
        "CITY:NORTH SIOUX",
        "PLACE:UNKNS",
        "SRC:NSIOUX",
        "UNIT:72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8",
        "INFO:COUNTY RD 23  IN 2 DIFF SPOTS FIRE S THAT ARE 200SQ FT");

    doTest("T4",
        "(J:CHEST PAIN/23 COTTS DR NORTH SIOUX/RIVER DR &/NSIOUX/ 72EMS9/CHEST PAINS SINCE A) CHEST PAIN/23 COTTS DR NORTH SIOUX/RIVER DR &/NSIOUX/ 72EMS9/CHEST PAINS SINCE ABOUT 0630  OPEN HEART SURG IN 1999",
        "CALL:CHEST PAIN",
        "ADDR:23 COTTS DR",
        "CITY:NORTH SIOUX",
        "X:RIVER DR",
        "SRC:NSIOUX",
        "UNIT:72EMS9",
        "INFO:CHEST PAINS SINCE ABOUT 0630  OPEN HEART SURG IN 1999");

    doTest("T5",
        "(J:CHEST PAIN/575 SIOUX POINT RD DAKOTA DUNES CNOS/DAKOTA DUNES BLVD & W ANCHOR DR/) CHEST PAIN/575 SIOUX POINT RD DAKOTA DUNES CNOS/DAKOTA DUNES BLVD & W ANCHOR DR/DDUNES/ 72EMS9/USE BACK DOOR",
        "CALL:CHEST PAIN",
        "ADDR:575 SIOUX POINT RD",
        "CITY:DAKOTA DUNES",
        "PLACE:CNOS",
        "X:DAKOTA DUNES BLVD & W ANCHOR DR",
        "SRC:DDUNES",
        "UNIT:72EMS9",
        "INFO:USE BACK DOOR");

    doTest("T6",
        "(J:FIRE ALARM/573 MONTEREY TRL DAKOTA DUNES/W PINEHURST TRL & DEAD END ST/DDUNES/ 7) FIRE ALARM/573 MONTEREY TRL DAKOTA DUNES/W PINEHURST TRL & DEAD END ST/DDUNES/ 72F7 72E5/HEATHER & TODD JOHNSON RES, COVERS POOL EQUIPMENT ROOM",
        "CALL:FIRE ALARM",
        "ADDR:573 MONTEREY TRL",
        "CITY:DAKOTA DUNES",
        "X:W PINEHURST TRL & DEAD END ST",
        "SRC:DDUNES",
        "UNIT:72F7 72E5",
        "INFO:HEATHER & TODD JOHNSON RES, COVERS POOL EQUIPMENT ROOM");

    doTest("T7",
        "(J:FIRE ALARM/705 SIOUX POINT RD DAKOTA DUNES PRODIGY LEARNING CENTER/W ANCHOR DR &) FIRE ALARM/705 SIOUX POINT RD DAKOTA DUNES PRODIGY LEARNING CENTER/W ANCHOR DR & W FLURIE RD/DDUNES/ 72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8/GENERAL FIRE ALARM",
        "CALL:FIRE ALARM",
        "ADDR:705 SIOUX POINT RD",
        "CITY:DAKOTA DUNES",
        "PLACE:PRODIGY LEARNING CENTER",
        "X:W ANCHOR DR & W FLURIE RD",
        "SRC:DDUNES",
        "UNIT:72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8",
        "INFO:GENERAL FIRE ALARM");

    doTest("T8",
        "(J:FIRE ALARM/915 WILLOW CIR DAKOTA DUNES/DDUNES/ 72B1 72EMS9 72E4 72E5 72F7 72Q6 7) FIRE ALARM/915 WILLOW CIR DAKOTA DUNES/DDUNES/ 72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8/NORTH SIOUX CITY    DR ROBERT ZIMMERMAN RES  BASEMENT SMOKE",
        "CALL:FIRE ALARM",
        "ADDR:915 WILLOW CIR",
        "CITY:DAKOTA DUNES",
        "SRC:DDUNES",
        "UNIT:72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8",
        "INFO:NORTH SIOUX CITY    DR ROBERT ZIMMERMAN RES  BASEMENT SMOKE");

    doTest("T9",
        "(J:FIRE ALARM/509 PRAIRIE PASS DAKOTA DUNES/SUNSET DR & DEAD END/DDUNES/ 72B1 72EMS) FIRE ALARM/509 PRAIRIE PASS DAKOTA DUNES/SUNSET DR & DEAD END/DDUNES/ 72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8/2ND FLOOR SMOKE     CHUCK LONG RES",
        "CALL:FIRE ALARM",
        "ADDR:509 PRAIRIE PASS",
        "CITY:DAKOTA DUNES",
        "X:SUNSET DR & DEAD END",
        "SRC:DDUNES",
        "UNIT:72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8",
        "INFO:2ND FLOOR SMOKE     CHUCK LONG RES");

    doTest("T10",
        "(J:CHEST PAIN/1 DEVILSFOOD DR NORTH SIOUX INTERBAKE FOODS INC/N DERBY LN & MILITARY) CHEST PAIN/1 DEVILSFOOD DR NORTH SIOUX INTERBAKE FOODS INC/N DERBY LN & MILITARY RD/NSIOUX/ 72EMS9/Using ProQA Medical",
        "CALL:CHEST PAIN",
        "ADDR:1 DEVILSFOOD DR",
        "CITY:NORTH SIOUX",
        "PLACE:INTERBAKE FOODS INC",
        "X:N DERBY LN & MILITARY RD",
        "SRC:NSIOUX",
        "UNIT:72EMS9",
        "INFO:Using ProQA Medical");

    doTest("T11",
        "(J:INJURED/FALL/67 NORTHSHORE DR NORTH SIOUX/PENROSE DR & WESTWOOD LN/NSIOUX/ 72EMS) INJURED/FALL/67 NORTHSHORE DR NORTH SIOUX/PENROSE DR & WESTWOOD LN/NSIOUX/ 72EMS9/82YR FEMALE UNABLE TO GET UP FROM THE RECLINER--PAST FALL",
        "CALL:INJURED/FALL",
        "ADDR:67 NORTHSHORE DR",
        "CITY:NORTH SIOUX",
        "X:PENROSE DR & WESTWOOD LN",
        "SRC:NSIOUX",
        "UNIT:72EMS9",
        "INFO:82YR FEMALE UNABLE TO GET UP FROM THE RECLINER--PAST FALL");

    doTest("T12",
        "(J:CONVULSIONS/SEIZURES/367 LAKESHORE DR NORTH SIOUX/WYCOFF DR & ALCOMA DR/NSIOUX/) CONVULSIONS/SEIZURES/367 LAKESHORE DR NORTH SIOUX/WYCOFF DR & ALCOMA DR/NSIOUX/ 72EMS9/10MO CHILD",
        "CALL:CONVULSIONS/SEIZURES",
        "ADDR:367 LAKESHORE DR",
        "CITY:NORTH SIOUX",
        "X:WYCOFF DR & ALCOMA DR",
        "SRC:NSIOUX",
        "UNIT:72EMS9",
        "INFO:10MO CHILD");

    doTest("T13",
        "(J:CHEST PAIN/3 UNKNOWN ST NORTH SIOUX UNK72/NSIOUX/ 72EMS9/N OF WATER TOWER M HAVI) CHEST PAIN/3 UNKNOWN ST NORTH SIOUX UNK72/NSIOUX/ 72EMS9/N OF WATER TOWER M HAVING HEART ATTACK 65YO M NOT CONS",
        "CALL:CHEST PAIN",
        "ADDR:3 UNKNOWN ST",
        "CITY:NORTH SIOUX",
        "PLACE:UNK72",
        "SRC:NSIOUX",
        "UNIT:72EMS9",
        "INFO:N OF WATER TOWER M HAVING HEART ATTACK 65YO M NOT CONS");

    doTest("T14",
        "(J:FIRE SM VEHICLE/4 I29 .2 NORTH SIOUX EXIT 4 MCCOOK LAKE/NORTHSHORE DR &/NSIOUX/) FIRE SM VEHICLE/4 I29 .2 NORTH SIOUX EXIT 4 MCCOOK LAKE/NORTHSHORE DR &/NSIOUX/ 72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8/SMOKE FROM ENGINE.",
        "CALL:FIRE SM VEHICLE",
        "ADDR:4 I29 .2",
        "MADDR:4 I 29 .2",
        "CITY:NORTH SIOUX",
        "PLACE:EXIT 4 MCCOOK LAKE",
        "X:NORTHSHORE DR",
        "SRC:NSIOUX",
        "UNIT:72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8",
        "INFO:SMOKE FROM ENGINE.");

    doTest("T15",
        "(J:INJURED/FALL/1 DEVILSFOOD DR NORTH SIOUX INTERBAKE FOODS INC/N DERBY LN & MILITA) INJURED/FALL/1 DEVILSFOOD DR NORTH SIOUX INTERBAKE FOODS INC/N DERBY LN & MILITARY RD/NSIOUX/ 72EMS9/Using ProQA Medical",
        "CALL:INJURED/FALL",
        "ADDR:1 DEVILSFOOD DR",
        "CITY:NORTH SIOUX",
        "PLACE:INTERBAKE FOODS INC",
        "X:N DERBY LN & MILITARY RD",
        "SRC:NSIOUX",
        "UNIT:72EMS9",
        "INFO:Using ProQA Medical");

    doTest("T16",
        "(J:HEAT/COLD EXPOSURE/80 NORTHSHORE DR NORTH SIOUX DAKOTA VALLEY ELEMENTARY/WESTWOO) HEAT/COLD EXPOSURE/80 NORTHSHORE DR NORTH SIOUX DAKOTA VALLEY ELEMENTARY/WESTWOOD LN & SUNCOAST DR/NSIOUX/ 72EMS9/Using ProQA Medical",
        "CALL:HEAT/COLD EXPOSURE",
        "ADDR:80 NORTHSHORE DR",
        "CITY:NORTH SIOUX",
        "PLACE:DAKOTA VALLEY ELEMENTARY",
        "X:WESTWOOD LN & SUNCOAST DR",
        "SRC:NSIOUX",
        "UNIT:72EMS9",
        "INFO:Using ProQA Medical");

    doTest("T17",
        "(J:FIRE OTHER/300 N MERRILL AVE NORTH SIOUX & MALLARD DR/NSIOUX/ 72B1 72EMS9 72E4 7) FIRE OTHER/300 N MERRILL AVE NORTH SIOUX & MALLARD DR/NSIOUX/ 72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8/IN THE BACK BY THE DUMPSTER IS ON FIRE",
        "CALL:FIRE OTHER",
        "ADDR:300 N MERRILL AVE",
        "CITY:NORTH SIOUX",
        "PLACE:NORTH SIOUX & MALLARD DR",
        "SRC:NSIOUX",
        "UNIT:72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8",
        "INFO:IN THE BACK BY THE DUMPSTER IS ON FIRE");

    doTest("T18",
        "(J:GRASS FIRE/3 UNKNOWN ST NORTH SIOUX UNK72/NSIOUX/ 72B1 72EMS9 72E4 72E5 72F7 72Q) GRASS FIRE/3 UNKNOWN ST NORTH SIOUX UNK72/NSIOUX/ 72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8/312 W PELLA DAKOTA DUNES GRASS FIRE. SPREADING QUICKLY.",
        "CALL:GRASS FIRE",
        "ADDR:3 UNKNOWN ST",
        "CITY:NORTH SIOUX",
        "PLACE:UNK72",
        "SRC:NSIOUX",
        "UNIT:72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8",
        "INFO:312 W PELLA DAKOTA DUNES GRASS FIRE. SPREADING QUICKLY.");

    doTest("T19",
        "(J:GRASS FIRE/100 DORAL LN DAKOTA DUNES/DDUNES/ 72B1 72F7/GRASS FIRE IN THE DITCH) GRASS FIRE/100 DORAL LN DAKOTA DUNES/DDUNES/ 72B1 72F7/GRASS FIRE IN THE DITCH",
        "CALL:GRASS FIRE",
        "ADDR:100 DORAL LN", // Does not map
        "CITY:DAKOTA DUNES",
        "SRC:DDUNES",
        "UNIT:72B1 72F7",
        "INFO:GRASS FIRE IN THE DITCH");

    doTest("T20",
        "(J:GRASS FIRE/851 E SAWGRASS TRL DAKOTA DUNES/DAKOTA DUNES BLVD & PEBBLE BEACH DR/D) GRASS FIRE/851 E SAWGRASS TRL DAKOTA DUNES/DAKOTA DUNES BLVD & PEBBLE BEACH DR/DDUNES/ 72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8/FIRE IN TREE",
        "CALL:GRASS FIRE",
        "ADDR:851 E SAWGRASS TRL",
        "CITY:DAKOTA DUNES",
        "X:DAKOTA DUNES BLVD & PEBBLE BEACH DR",
        "SRC:DDUNES",
        "UNIT:72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8",
        "INFO:FIRE IN TREE");

    doTest("T21",
        "(J:GRASS FIRE/3 UNKNOWN ST NORTH SIOUX UNK72/NSIOUX/ 72E5/BY STARS AND STRIPES FIRE) GRASS FIRE/3 UNKNOWN ST NORTH SIOUX UNK72/NSIOUX/ 72E5/BY STARS AND STRIPES FIREWORKS GRASS ON FIRE",
        "CALL:GRASS FIRE",
        "ADDR:3 UNKNOWN ST",
        "CITY:NORTH SIOUX",
        "PLACE:UNK72",
        "SRC:NSIOUX",
        "UNIT:72E5",
        "INFO:BY STARS AND STRIPES FIREWORKS GRASS ON FIRE");

    doTest("T22",
        "(J:GRASS FIRE/1150 NORTHSHORE DR NORTH SIOUX DAKOTA VALLEY HIGH SCHOOL/SUNCOAST DR) GRASS FIRE/1150 NORTHSHORE DR NORTH SIOUX DAKOTA VALLEY HIGH SCHOOL/SUNCOAST DR & WESTSHORE DR/NSIOUX/ 72B1/BY DEER RUN   GRASS FIRE   JUST WEST OF THE HIGH SCHOOL",
        "CALL:GRASS FIRE",
        "ADDR:1150 NORTHSHORE DR",
        "CITY:NORTH SIOUX",
        "PLACE:DAKOTA VALLEY HIGH SCHOOL",
        "X:SUNCOAST DR & WESTSHORE DR",
        "SRC:NSIOUX",
        "UNIT:72B1",
        "INFO:BY DEER RUN   GRASS FIRE   JUST WEST OF THE HIGH SCHOOL");

    doTest("T23",
        "(J:GRASS FIRE/1 NORTHSHORE DR NORTH SIOUX & HIGHWAY 105/NSIOUX/ 72B1/BY LANTIS AGAI) GRASS FIRE/1 NORTHSHORE DR NORTH SIOUX & HIGHWAY 105/NSIOUX/ 72B1/BY LANTIS AGAIN NORTHSHORE AND HWY 105",
        "CALL:GRASS FIRE",
        "ADDR:1 NORTHSHORE DR",
        "CITY:NORTH SIOUX",
        "PLACE:& HIGHWAY 105",
        "SRC:NSIOUX",
        "UNIT:72B1",
        "INFO:BY LANTIS AGAIN NORTHSHORE AND HWY 105");

    doTest("T24",
        "(J:FIRE/463 PRAIRIE PASS DAKOTA DUNES/SUNSET DR & DEAD END/DDUNES/ 72B1 72EMS9 72E4) FIRE/463 PRAIRIE PASS DAKOTA DUNES/SUNSET DR & DEAD END/DDUNES/ 72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8/ROOF IS ON FIRE THEY HAVE WOOD SHINGLES",
        "CALL:FIRE",
        "ADDR:463 PRAIRIE PASS",  // Found in Big Sioux
        "CITY:DAKOTA DUNES",
        "X:SUNSET DR & DEAD END",
        "SRC:DDUNES",
        "UNIT:72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8",
        "INFO:ROOF IS ON FIRE THEY HAVE WOOD SHINGLES");

    doTest("T25",
        "(J:GRASS FIRE/3 UNKNOWN ST NORTH SIOUX UNK72/NSIOUX/ 72B1 72EMS9 72E4 72E5 72F7 72Q) GRASS FIRE/3 UNKNOWN ST NORTH SIOUX UNK72/NSIOUX/ 72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8/BY THE MCCOOK EXIT ... FIREWORKS STAND .. HUGE FIRE HERE",
        "CALL:GRASS FIRE",
        "ADDR:3 UNKNOWN ST",
        "CITY:NORTH SIOUX",
        "PLACE:UNK72",
        "SRC:NSIOUX",
        "UNIT:72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8",
        "INFO:BY THE MCCOOK EXIT ... FIREWORKS STAND .. HUGE FIRE HERE");

    doTest("T26",
        "(J:FIRE/1120 WASHINGTON ST NORTH SIOUX/PEARL ST & MILITARY RD/NSIOUX/ 72B1 72EMS9 7) FIRE/1120 WASHINGTON ST NORTH SIOUX/PEARL ST & MILITARY RD/NSIOUX/ 72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8/REKINDLING, RES IS SMOKING",
        "CALL:FIRE",
        "ADDR:1120 WASHINGTON ST",
        "CITY:NORTH SIOUX",
        "X:PEARL ST & MILITARY RD",
        "SRC:NSIOUX",
        "UNIT:72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8",
        "INFO:REKINDLING, RES IS SMOKING");

    doTest("T27",
        "(J:FIRE OTHER/105 S DERBY LN NORTH SIOUX PRONTO EXPRESS/RIVER DR &/NSIOUX/ 72B1 72E) FIRE OTHER/105 S DERBY LN NORTH SIOUX PRONTO EXPRESS/RIVER DR &/NSIOUX/ 72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8/NEED ASSISTANCE",
        "CALL:FIRE OTHER",
        "ADDR:105 S DERBY LN",
        "CITY:NORTH SIOUX",
        "PLACE:PRONTO EXPRESS",
        "X:RIVER DR",
        "SRC:NSIOUX",
        "UNIT:72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8",
        "INFO:NEED ASSISTANCE");

    doTest("T28",
        "(J:INJURED/FALL/400 RIVER DR NORTH SIOUX & LLOYD AVE/NSIOUX/ 72EMS9/Using ProQA Med) INJURED/FALL/400 RIVER DR NORTH SIOUX & LLOYD AVE/NSIOUX/ 72EMS9/Using ProQA Medical",
        "CALL:INJURED/FALL",
        "ADDR:400 RIVER DR",
        "CITY:NORTH SIOUX",
        "PLACE:NORTH SIOUX & LLOYD AVE",
        "SRC:NSIOUX",
        "UNIT:72EMS9",
        "INFO:Using ProQA Medical");

    doTest("T29",
        "(J:INJURED/FALL/1 DEVILSFOOD DR NORTH SIOUX INTERBAKE FOODS INC/N DERBY LN & MILITA) INJURED/FALL/1 DEVILSFOOD DR NORTH SIOUX INTERBAKE FOODS INC/N DERBY LN & MILITARY RD/NSIOUX/ 72EMS9/62 YO FEMALE",
        "CALL:INJURED/FALL",
        "ADDR:1 DEVILSFOOD DR",
        "CITY:NORTH SIOUX",
        "PLACE:INTERBAKE FOODS INC",
        "X:N DERBY LN & MILITARY RD",
        "SRC:NSIOUX",
        "UNIT:72EMS9",
        "INFO:62 YO FEMALE");

    doTest("T30",
        "(J:CONVULSIONS/SEIZURES/367 LAKESHORE DR NORTH SIOUX/WYCOFF DR & ALCOMA DR/NSIOUX/) CONVULSIONS/SEIZURES/367 LAKESHORE DR NORTH SIOUX/WYCOFF DR & ALCOMA DR/NSIOUX/ 72EMS9/Using ProQA Medical",
        "CALL:CONVULSIONS/SEIZURES",
        "ADDR:367 LAKESHORE DR",
        "CITY:NORTH SIOUX",
        "X:WYCOFF DR & ALCOMA DR",
        "SRC:NSIOUX",
        "UNIT:72EMS9",
        "INFO:Using ProQA Medical");

    doTest("T31",
        "(J:LIFELINE/79 NORTHSHORE DR NORTH SIOUX/WESTWOOD LN & SUNCOAST DR/NSIOUX/ 72EMS9/S) LIFELINE/79 NORTHSHORE DR NORTH SIOUX/WESTWOOD LN & SUNCOAST DR/NSIOUX/ 72EMS9/SPOKE TO ELEANOR CANTRELL, ALOT OF PAIN IN LEFT ARM, DIZZY",
        "CALL:LIFELINE",
        "ADDR:79 NORTHSHORE DR",
        "CITY:NORTH SIOUX",
        "X:WESTWOOD LN & SUNCOAST DR",
        "SRC:NSIOUX",
        "UNIT:72EMS9",
        "INFO:SPOKE TO ELEANOR CANTRELL, ALOT OF PAIN IN LEFT ARM, DIZZY");

    doTest("T32",
        "(J:HEART PROBLEMS/120 MERRILL AVE NORTH SIOUX/RIVER DR & CAMPBELL ST/NSIOUX/ 72EMS9) HEART PROBLEMS/120 MERRILL AVE NORTH SIOUX/RIVER DR & CAMPBELL ST/NSIOUX/ 72EMS9/Using ProQA Medical",
        "CALL:HEART PROBLEMS",
        "ADDR:120 MERRILL AVE",
        "CITY:NORTH SIOUX",
        "X:RIVER DR & CAMPBELL ST",
        "SRC:NSIOUX",
        "UNIT:72EMS9",
        "INFO:Using ProQA Medical");

    doTest("T33",
        "(J:LIFT ASSISTANCE/301 DAKOTA DUNES BLVD APT24 DAKOTA DUNES STONEYBROOK SUITES/SIOU) LIFT ASSISTANCE/301 DAKOTA DUNES BLVD APT24 DAKOTA DUNES STONEYBROOK SUITES/SIOUX POINT RD & COURTYARD DR/DDUNES/ 72EMS9/REFUSING MEDICAL",
        "CALL:LIFT ASSISTANCE",
        "ADDR:301 DAKOTA DUNES BLVD",
        "APT:24",
        "CITY:DAKOTA DUNES",
        "PLACE:STONEYBROOK SUITES",
        "X:SIOUX POINT RD & COURTYARD DR",
        "SRC:DDUNES",
        "UNIT:72EMS9",
        "INFO:REFUSING MEDICAL");

    doTest("T34",
        "(J:CHEST PAIN/1 DEVILSFOOD DR NORTH SIOUX INTERBAKE FOODS INC/N DERBY LN & MILITARY) CHEST PAIN/1 DEVILSFOOD DR NORTH SIOUX INTERBAKE FOODS INC/N DERBY LN & MILITARY RD/NSIOUX/ 72EMS9/STABBIGN PAIN IN L SHOULDER ARM",
        "CALL:CHEST PAIN",
        "ADDR:1 DEVILSFOOD DR",
        "CITY:NORTH SIOUX",
        "PLACE:INTERBAKE FOODS INC",
        "X:N DERBY LN & MILITARY RD",
        "SRC:NSIOUX",
        "UNIT:72EMS9",
        "INFO:STABBIGN PAIN IN L SHOULDER ARM");

    doTest("T35",
        "(J:FIRE ALARM/575 SIOUX POINT RD DAKOTA DUNES CNOS/DAKOTA DUNES BLVD & W ANCHOR DR/) FIRE ALARM/575 SIOUX POINT RD DAKOTA DUNES CNOS/DAKOTA DUNES BLVD & W ANCHOR DR/DDUNES/ 72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8/575 NORTH SIOUX POINT RD.COVERING GENERAL.NO CONTACT",
        "CALL:FIRE ALARM",
        "ADDR:575 SIOUX POINT RD",
        "CITY:DAKOTA DUNES",
        "PLACE:CNOS",
        "X:DAKOTA DUNES BLVD & W ANCHOR DR",
        "SRC:DDUNES",
        "UNIT:72B1 72EMS9 72E4 72E5 72F7 72Q6 72R8",
        "INFO:575 NORTH SIOUX POINT RD.COVERING GENERAL.NO CONTACT");

    doTest("T36",
        "(J:INJURED/FALL/220 S DERBY LN LOT57 NORTH SIOUX COTTONWOOD TRAILER COURT/NSIOUX/ 7) INJURED/FALL/220 S DERBY LN LOT57 NORTH SIOUX COTTONWOOD TRAILER COURT/NSIOUX/ 72EMS9/Using ProQA Medical",
        "CALL:INJURED/FALL",
        "ADDR:220 S DERBY LN",
        "CITY:NORTH SIOUX",
        "APT:57",
        "PLACE:NORTH SIOUX COTTONWOOD TRAILER COURT",
        "SRC:NSIOUX",
        "UNIT:72EMS9",
        "INFO:Using ProQA Medical");

    doTest("T37",
        "(J:UNCONSCIOUS/FAINTING/675 STREETER DR NORTH SIOUX KOA CAMPGROUND/NSIOUX/ 72EMS9/F) UNCONSCIOUS/FAINTING/675 STREETER DR NORTH SIOUX KOA CAMPGROUND/NSIOUX/ 72EMS9/F FEEL OFF BIKE ON BIKE PATH. UNCON & UNK BREATHIN",
        "CALL:UNCONSCIOUS/FAINTING",
        "ADDR:675 STREETER DR",
        "CITY:NORTH SIOUX",
        "PLACE:KOA CAMPGROUND",
        "SRC:NSIOUX",
        "UNIT:72EMS9",
        "INFO:F FEEL OFF BIKE ON BIKE PATH. UNCON & UNK BREATHIN");

    doTest("T38",
        "(J:SICK PERSON/1300 RIVER DR NORTH SIOUX SUPER 8 MOTEL/STREETER DR & SODRAC DR/NSIO) SICK PERSON/1300 RIVER DR NORTH SIOUX SUPER 8 MOTEL/STREETER DR & SODRAC DR/NSIOUX/ 72EMS9/JUV FEMALE OUT OF CONTROL & PD WANTS HER CK D OUT",
        "CALL:SICK PERSON",
        "ADDR:1300 RIVER DR",
        "CITY:NORTH SIOUX",
        "PLACE:SUPER 8 MOTEL",
        "X:STREETER DR & SODRAC DR",
        "SRC:NSIOUX",
        "UNIT:72EMS9",
        "INFO:JUV FEMALE OUT OF CONTROL & PD WANTS HER CK D OUT");

  }
  
  public static void main(String[] args) {
    new SDUnionCountyParserTest().generateTests("T1");
  }
}