package net.anei.cadpage.parsers.VA;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;

/*
Prince Edward County, VA
Contact: Active911
Agency name: Farmville Fire Department
Location: Farmville, VA, United States
Sender: MAILBOX@farmvilleva.com

MAILBOX:12 FIRE (UNKNOWN) CFS# 2013-007545
MAILBOX:17 FIRE ALARM 1001 FOURTH AV EXT FAR CFS# 2013-007466
MAILBOX:01 FIRE ALARM 800 OAK ST FAR CFS# 2013-007444
MAILBOX:26 UNCONSCIOUS 414 SPRING ST B FAR CFS# 2013-007402
MAILBOX:56 FIRE (UNKNOWN) FUQUA DR FAR CFS# 2013-007315
MAILBOX:52 FIRE ALARM 000300 DOMINION DR FAR CFS# 2013-006920
MAILBOX:41 FIRE ALARM 202 HIGH ST FAR CFS# 2013-006768
MAILBOX:12 BRUSH / FOREST FIRE ANDREWS DR FAR CFS# 2013-006675 CROSS: REED ST/PARKVIEW DR
MAILBOX:14 ACCIDENT WITH INJURIES PRINCE EDWARD HWY FAR CFS# 2013-006650
MAILBOX:23 ACCIDENT NO INJURY FAIRGROUNDS RD FAR CFS# 2013-006612
MAILBOX:40 FIRE ALARM 1205 FOURTH AV EXT FAR CFS# 2013-006597
MAILBOX:30 STRUCTURE FIRE 300 POPLAR FOREST RD FAR CFS# 2013-006156
MAILBOX:13 FIRE ALARM 9 MAHAN RD FAR CFS# 2013-006133
MAILBOX:28 FIRE ALARM 800 OAK ST FAR CFS# 2013-006066
MAILBOX:32 DISABLED VEHICLE PUTNEY ST FAR CFS# 2013-006007
MAILBOX:22 FIRE ALARM 800 OAK ST FAR CFS# 2013-005990
MAILBOX:27 BRUSH / FOREST FIRE 000175 CRESCENT RD FAR CFS# 2013-005986
MAILBOX:07 ACCIDENT WITH INJURIES 306 S MAIN ST FAR CFS# 2013-005939
MAILBOX:03 FALL 318 N MAIN ST FAR CFS# 2013-005901
MAILBOX:12 TREE DOWN 2300 OLD RIDGE RD FAR CFS# 2013-005775
MAILBOX:49 FIRE ALARM 1403 S MAIN ST FAR CFS# 2013-005721
MAILBOX:04 PUBLIC ASSISTANCE REQUEST CFS# 2013-005703
MAILBOX:00 HAZMAT/SPILL 2102 S MAIN ST A FAR CFS# 2013-005700
MAILBOX:05 FIRE ALARM 1575 SCOTT DR FAR CFS# 2013-005342
MAILBOX:46 COMMERCIAL STRUCTURE FIRE 1835 ZION HILL RD FAR CFS# 2013-005288 CROSS: OLD RIDGE RD/FARMVILLE RD
MAILBOX:15 ACCIDENT WITH INJURIES W THIRD ST FAR CFS# 2013-005284
MAILBOX:09 FIRE ALARM 1575 SCOTT DR FAR CFS# 2013-005224
MAILBOX:17 ACCIDENT NO INJURY S MAIN ST FAR CFS# 2013-005121
MAILBOX:18 SHOOTING 100 HEALY ST FAR CFS# 2013-005048
MAILBOX:47 HAZMAT/SPILL 502 LANDON ST 26 FAR CFS# 2013-005047
MAILBOX:17 STRUCTURE FIRE 1506 S MAIN ST A FAR CFS# 2013-004851
MAILBOX:49 VEHICLE/EQUIPMENT FIRE 1913 E THIRD ST FAR CFS# 2013-004767
MAILBOX:36 BRUSH / FOREST FIRE FAIRGROUNDS RD FAR CFS# 2013-004651
MAILBOX:29 STRUCTURE FIRE VAUGHAN ST FAR CFS# 2013-004528
MAILBOX:26 ACCIDENT WITH INJURIES MILNWOOD RD FAR CFS# 2013-004476
MAILBOX:07 ACCIDENT NO INJURY BUSH RIVER DR FAR CFS# 2013-004470
MAILBOX:27 ACCIDENT NO INJURY 1218 SHEPPARDS RD FAR CFS# 2013-004386
MAILBOX:29 STRUCTURE FIRE 94 PINE KNOLL DR RIC CFS# 2013-004236
MAILBOX:00 STRUCTURE FIRE 94 PINE KNOLL DR RIC CFS# 2013-004231
MAILBOX:47 FIRE ALARM 202 HIGH ST FAR CFS# 2013-004201
MAILBOX:42 COMMERCIAL STRUCTURE FIRE 3991 W THIRD ST FAR CFS# 2013-004191
MAILBOX:10 FIRE ALARM 1303 W THIRD ST FAR CFS# 2013-004152
MAILBOX:24 COMMERCIAL STRUCTURE FIRE HSC / CFS# 2013-004116
MAILBOX:15 FIRE (UNKNOWN) 3082 W THIRD ST FAR CFS# 2013-004090
MAILBOX:01 ACCIDENT NO INJURY PRINCE EDWARD HWY FAR CFS# 2013-004054
MAILBOX:10 SEIZURES 1096 FAIRGROUNDS RD FAR CFS# 2013-004053
MAILBOX:51 FIRE ALARM 1801 E THIRD ST FAR CFS# 2013-003975
MAILBOX:12 BRUSH / FOREST FIRE 4285 S JAMES MADISON HWY CFS# 2013-003954
MAILBOX:53 FIRE ALARM 100 FIRST ST FAR CFS# 2013-003872
MAILBOX:00 FIRE (UNKNOWN) 222 CALLAWAY LN FAR CFS# 2013-003754
MAILBOX:46 ACCIDENT NO INJURY 14940 PRINCE EDWARD HWY FAR CFS# 2013-003666
MAILBOX:35 FIRE (UNKNOWN) 1649 COMMERCE RD FAR CFS# 2013-003641
MAILBOX:18 COMMERCIAL STRUCTURE FIRE 811 POPLAR FOREST RD FAR CFS# 2013-003436
MAILBOX:12 FIRE (UNKNOWN) 108 GEORGIA ST FAR CFS# 2013-003373
MAILBOX:20 FIRE ALARM 500 PLANK RD FAR CFS# 2013-003315
MAILBOX:31 FIRE ALARM 210 FOURTH ST FAR CFS# 2013-003195
MAILBOX:32 ACCIDENT NO INJURY 301 W THIRD ST FAR CFS# 2013-002971
MAILBOX:40 FIRE ALARM 202 HIGH ST FAR CFS# 2013-002954
MAILBOX:05 COMMERCIAL STRUCTURE FIRE 2104 S MAIN ST FAR CFS# 2013-002937
MAILBOX:17 ODOR INVESTIGATION 226 CALLAWAY LN FAR CFS# 2013-002933
MAILBOX:23 FIRE ALARM 202 HIGH ST FAR CFS# 2013-002930
MAILBOX:19 PUBLIC ASSISTANCE REQUEST 364 W THIRD ST FAR CFS# 2013-002910
MAILBOX:30 FIRE ALARM 508 WATER WORKS RD FAR CFS# 2013-002884
MAILBOX:26 FIRE (UNKNOWN) 1537 CUMBERLAND RD FAR CFS# 2013-002819
MAILBOX:54 STRUCTURE FIRE 2104 S MAIN ST FAR CFS# 2013-002800
MAILBOX:43 PUBLIC ASSISTANCE (RESCUE) 001101 SIXTH AVE FAR CFS# 2013-002739, CHIEF 1 VEHICLE RESPONDING FOR A LIFT ASSIST
MAILBOX:42 PUBLIC ASSISTANCE (RESCUE) 1101 SIXTH AVE FAR CFS# 2013-002739
MAILBOX:47 STRUCTURE FIRE 1371 SIMPSON RD PRO CFS# 2013-002679
MAILBOX:49 FIRE ALARM 605 FUQUA DR FAR CFS# 2013-002668
MAILBOX:26 FIRE ALARM 900 W THIRD ST FAR CFS# 2013-002588
MAILBOX:58 PUBLIC ASSISTANCE REQUEST 800 OAK ST FAR CFS# 2013-002567
MAILBOX:57 FIRE ALARM 000202 HIGH ST FAR CFS# 2013-002546 NEED CREW TO RESPOND THIRD TONE
MAILBOX:46 FIRE ALARM 202 HIGH ST FAR CFS# 2013-002546
MAILBOX:41 VEHICLE/EQUIPMENT FIRE 11802 PRINCE EDWARD HWY PRO CFS# 2013-002530
MAILBOX:13 STRUCTURE FIRE 132 LIGONTOWN RD CFS# 2013-002463
MAILBOX:26 ACCIDENT NO INJURY PRINCE EDWARD HWY FAR CFS# 2013-002440
MAILBOX:14 STRUCTURE FIRE 418 SPRUCE ST FAR CFS# 2013-002433
MAILBOX:05 SMOKE INVESTIGATION (OUTSIDE) 813 LONGWOOD AVE .. FAR CFS# 2013-002431
MAILBOX:28 FIRE ALARM 9 MAHAN RD FAR CFS# 2013-002427
MAILBOX:40 FIRE ALARM 202 HIGH ST FAR CFS# 2013-002356
MAILBOX:57 STRUCTURE FIRE 383 RIVER RD FAR CFS# 2013-002322
MAILBOX:36 ACCIDENT NO INJURY N MAIN ST FAR CFS# 2013-002287
MAILBOX:07 FIRE ALARM 202 HIGH ST FAR CFS# 2013-002227
MAILBOX:34 TRASH FIRE (RUBBISH) CFS# 2013-002126
MAILBOX:21 ACCIDENT WITH INJURIES CUMBERLAND RD FAR CFS# 2013-002106
MAILBOX:35 FIRE ALARM 406 DOSWELL ST 2 FAR CFS# 2013-001963
MAILBOX:48 FIRE ALARM 406 DOSWELL ST FAR CFS# 2013-001959
MAILBOX:54 FIRE ALARM 800 OAK ST FAR CFS# 2013-001949
MAILBOX:32 FIRE ALARM 1575 SCOTT DR FAR CFS# 2013-001869
MAILBOX:57 FIRE ALARM 1506 S MAIN ST FAR CFS# 2013-001821
MAILBOX:43 FIRE ALARM 202 HIGH ST FAR CFS# 2013-001818
MAILBOX:13 SMOKE INVESTIGATION (OUTSIDE) 820 LONGWOOD AVE FAR CFS# 2013-001807
MAILBOX:23 FIRE ALARM 200 FIRST ST CFS# 2013-001689
MAILBOX:05 FIRE ALARM 508 WATER WORKS RD FAR CFS# 2013-001662
MAILBOX:02 FIRE ALARM 711 SECOND AVE FAR CFS# 2013-001659
MAILBOX:30 FIRE ALARM 1800 PEERY DR FAR CFS# 2013-001634
MAILBOX:22 STROKE 1575 SCOTT DR FAR CFS# 2013-001600
MAILBOX:42 FIRE ALARM 202 HIGH ST FAR CFS# 2013-001597

Contact: Active911
Agency name: Prince Edward Volunteer Rescue Squad
Location: Farmville, VA, United States
Sender: MAILBOX@farmvilleva.com 

MAILBOX:59 SICK 001575 SCOTT DR FAR CFS# 2013-007067 NEED FULL CREW TO RESPOND TRINITY MISSION. 87 Y/O F HAVING CONVULSIONS.
MAILBOX:29 DIFFICULTY BREATHING 005908 HEIGHTS SCHOOL RD PAM CFS# 2013-007022 NEED ALS
MAILBOX:36 MEDICAL ALARM 001501 GILLIAM DR FAR CFS# 2013-006970 PER OFFICERS ON SCENE, DISREGARD
MAILBOX:24 RESCUE CALL 002280 LOCKETT RD RIC CFS# 2013-006963
MAILBOX:25 ABDOMINAL/BACK PAIN 002450 CUMBERLAND RD FAR CFS# 2013-006922 DAVIS WILL HANDLE
MAILBOX:30 ACCIDENT WITH INJURIES 000710 N MAIN ST FAR CFS# 2013-006760 R12 ADVISING TO CANCEL ON 2ND AMBULANCE, THEY WILL HANDLE
MAILBOX:27 ACCIDENT WITH INJURIES 000710 N MAIN ST FAR CFS# 2013-006760 NEED FULL CREW FOR 2ND AMBULANCE TO RESPOND PER R12
MAILBOX:00 RESCUE CALL 002003 COBB ST FAR CFS# 2013-006721NEED FULL DUTY CREW
MAILBOX:54 DIFFICULTY BREATHING 000031 BRIERY WAY RD FAR CFS# 2013-006666 REQUESTING ALS FOR A 78 Y/O F DIFFICULTY BREATHING.
MAILBOX:44 FULL CARDIAC ARREST 001575 SCOTT DR FAR CFS# 2013-006618 STILL NEED EMT
MAILBOX:33 SICK 000485 WATSON BVD MEH CFS# 2013-006617 FULL CREW NEEDED
MAILBOX:54 CHEST PAINS 000235 MAHAN RD FAR CFS# 2013-006426, DISREGARD CALL, DAVIS AMBULANCE WILL HANDLE
MAILBOX:30 SEIZURES 000035 MCALLISTER LN FAR CFS# 2013-006403
MAILBOX:46 DIABETIC 000305 RIVER RD FAR CFS# 2013-006326 DIABETIC MALE WITH LOW BLOOD SUGAR. NEED FULL CREW
MAILBOX:55 RESCUE CALL 000199 SUZANNE DR RIC CFS# 2013-006264
MAILBOX:03 RESCUE CALL 002003 COBB ST FAR CFS# 2013-006243, NEED EMT TO COMPLETE CREW, POSSIBLE HIP DISLOCATION
MAILBOX:59 RESCUE CALL 002003 COBB ST FAR CFS# 2013-006243, NEED FULL CREW, POSSIBLE DISLOCATED HIP, LEE 147
MAILBOX:18 RESCUE CALL 008106 ABILENE RD FAR CFS# 2013-006197 CROSS: FARMVILLE RD/(RT 15)
MAILBOX:14 CHEST PAINS 001878 OLIVER RD PRO CFS# 2013-006115 DRIVER OR EMT

*/


public class VAPrinceEdwardCountyParserTest extends BaseParserTest {
   
  public VAPrinceEdwardCountyParserTest() {
    setParser(new VAPrinceEdwardCountyParser(), "PRINCE EDWARD COUNTY", "VA");
  }
  
  @Test
  public void testfarmvillefiredept() {
    
    doTest("T1",
        "MAILBOX:12 FIRE (UNKNOWN) CFS# 2013-007545",
        "SRC:12",
        "CALL:FIRE (UNKNOWN)",
        "ID:2013-007545");

    doTest("T2",
        "MAILBOX:17 FIRE ALARM 1001 FOURTH AV EXT FAR CFS# 2013-007466",
        "SRC:17",
        "CALL:FIRE ALARM",
        "ADDR:1001 FOURTH AV EXT",
        "MADDR:1001 FOURTH AVE",
        "CITY:FARMVILLE",
        "ID:2013-007466");

    doTest("T3",
        "MAILBOX:01 FIRE ALARM 800 OAK ST FAR CFS# 2013-007444",
        "SRC:01",
        "CALL:FIRE ALARM",
        "ADDR:800 OAK ST",
        "CITY:FARMVILLE",
        "ID:2013-007444");

    doTest("T4",
        "MAILBOX:26 UNCONSCIOUS 414 SPRING ST B FAR CFS# 2013-007402",
        "SRC:26",
        "CALL:UNCONSCIOUS",
        "ADDR:414 SPRING ST B",
        "CITY:FARMVILLE",
        "ID:2013-007402");

    doTest("T5",
        "MAILBOX:56 FIRE (UNKNOWN) FUQUA DR FAR CFS# 2013-007315",
        "SRC:56",
        "CALL:FIRE (UNKNOWN)",
        "ADDR:FUQUA DR",
        "CITY:FARMVILLE",
        "ID:2013-007315");

    doTest("T6",
        "MAILBOX:52 FIRE ALARM 000300 DOMINION DR FAR CFS# 2013-006920",
        "SRC:52",
        "CALL:FIRE ALARM",
        "ADDR:300 DOMINION DR", //finds dominion, not #300
        "CITY:FARMVILLE",
        "ID:2013-006920");

    doTest("T7",
        "MAILBOX:41 FIRE ALARM 202 HIGH ST FAR CFS# 2013-006768",
        "SRC:41",
        "CALL:FIRE ALARM",
        "ADDR:202 HIGH ST",
        "CITY:FARMVILLE",
        "ID:2013-006768");

    doTest("T8",
        "MAILBOX:12 BRUSH / FOREST FIRE ANDREWS DR FAR CFS# 2013-006675 CROSS: REED ST/PARKVIEW DR",
        "SRC:12",
        "CALL:BRUSH / FOREST FIRE",
        "ADDR:ANDREWS DR",
        "MADDR:ANDREWS DR & REED ST",
        "CITY:FARMVILLE",
        "ID:2013-006675",
        "X:REED ST/PARKVIEW DR");

    doTest("T9",
        "MAILBOX:14 ACCIDENT WITH INJURIES PRINCE EDWARD HWY FAR CFS# 2013-006650",
        "SRC:14",
        "CALL:ACCIDENT WITH INJURIES PRINCE",
        "ADDR:EDWARD HWY",  // not enough info
        "CITY:FARMVILLE",
        "ID:2013-006650");

    doTest("T10",
        "MAILBOX:23 ACCIDENT NO INJURY FAIRGROUNDS RD FAR CFS# 2013-006612",
        "SRC:23",
        "CALL:ACCIDENT NO INJURY",
        "ADDR:FAIRGROUNDS RD",
        "CITY:FARMVILLE",
        "ID:2013-006612");

    doTest("T11",
        "MAILBOX:40 FIRE ALARM 1205 FOURTH AV EXT FAR CFS# 2013-006597",
        "SRC:40",
        "CALL:FIRE ALARM",
        "ADDR:1205 FOURTH AV EXT",
        "MADDR:1205 FOURTH AVE",
        "CITY:FARMVILLE",
        "ID:2013-006597");

    doTest("T12",
        "MAILBOX:30 STRUCTURE FIRE 300 POPLAR FOREST RD FAR CFS# 2013-006156",
        "SRC:30",
        "CALL:STRUCTURE FIRE",
        "ADDR:300 POPLAR FOREST RD",
        "CITY:FARMVILLE",
        "ID:2013-006156");

    doTest("T13",
        "MAILBOX:13 FIRE ALARM 9 MAHAN RD FAR CFS# 2013-006133",
        "SRC:13",
        "CALL:FIRE ALARM",
        "ADDR:9 MAHAN RD", //they really like not adding numbers for addresses
        "CITY:FARMVILLE",
        "ID:2013-006133");

    doTest("T14",
        "MAILBOX:28 FIRE ALARM 800 OAK ST FAR CFS# 2013-006066",
        "SRC:28",
        "CALL:FIRE ALARM",
        "ADDR:800 OAK ST",
        "CITY:FARMVILLE",
        "ID:2013-006066");

    doTest("T15",
        "MAILBOX:32 DISABLED VEHICLE PUTNEY ST FAR CFS# 2013-006007",
        "SRC:32",
        "CALL:DISABLED VEHICLE",
        "ADDR:PUTNEY ST",
        "CITY:FARMVILLE",
        "ID:2013-006007");

    doTest("T16",
        "MAILBOX:22 FIRE ALARM 800 OAK ST FAR CFS# 2013-005990",
        "SRC:22",
        "CALL:FIRE ALARM",
        "ADDR:800 OAK ST",
        "CITY:FARMVILLE",
        "ID:2013-005990");

    doTest("T17",
        "MAILBOX:27 BRUSH / FOREST FIRE 000175 CRESCENT RD FAR CFS# 2013-005986",
        "SRC:27",
        "CALL:BRUSH / FOREST FIRE",
        "ADDR:175 CRESCENT RD",
        "CITY:FARMVILLE",
        "ID:2013-005986");

    doTest("T18",
        "MAILBOX:07 ACCIDENT WITH INJURIES 306 S MAIN ST FAR CFS# 2013-005939",
        "SRC:07",
        "CALL:ACCIDENT WITH INJURIES",
        "ADDR:306 S MAIN ST",
        "CITY:FARMVILLE",
        "ID:2013-005939");

    doTest("T19",
        "MAILBOX:03 FALL 318 N MAIN ST FAR CFS# 2013-005901",
        "SRC:03",
        "CALL:FALL",
        "ADDR:318 N MAIN ST",
        "CITY:FARMVILLE",
        "ID:2013-005901");

    doTest("T20",
        "MAILBOX:12 TREE DOWN 2300 OLD RIDGE RD FAR CFS# 2013-005775",
        "SRC:12",
        "CALL:TREE DOWN",
        "ADDR:2300 OLD RIDGE RD",
        "CITY:FARMVILLE",
        "ID:2013-005775");

    doTest("T21",
        "MAILBOX:49 FIRE ALARM 1403 S MAIN ST FAR CFS# 2013-005721",
        "SRC:49",
        "CALL:FIRE ALARM",
        "ADDR:1403 S MAIN ST",
        "CITY:FARMVILLE",
        "ID:2013-005721");

    doTest("T22",
        "MAILBOX:04 PUBLIC ASSISTANCE REQUEST CFS# 2013-005703",
        "SRC:04",
        "CALL:PUBLIC ASSISTANCE REQUEST",
        "ID:2013-005703");

    doTest("T23",
        "MAILBOX:00 HAZMAT/SPILL 2102 S MAIN ST A FAR CFS# 2013-005700",
        "SRC:00",
        "CALL:HAZMAT/SPILL",
        "ADDR:2102 S MAIN ST A",
        "CITY:FARMVILLE",
        "ID:2013-005700");

    doTest("T24",
        "MAILBOX:05 FIRE ALARM 1575 SCOTT DR FAR CFS# 2013-005342",
        "SRC:05",
        "CALL:FIRE ALARM",
        "ADDR:1575 SCOTT DR",
        "CITY:FARMVILLE",
        "ID:2013-005342");

    doTest("T25",
        "MAILBOX:46 COMMERCIAL STRUCTURE FIRE 1835 ZION HILL RD FAR CFS# 2013-005288 CROSS: OLD RIDGE RD/FARMVILLE RD",
        "SRC:46",
        "CALL:COMMERCIAL STRUCTURE FIRE",
        "ADDR:1835 ZION HILL RD",
        "CITY:FARMVILLE",
        "ID:2013-005288",
        "X:OLD RIDGE RD/FARMVILLE RD");

    doTest("T26",
        "MAILBOX:15 ACCIDENT WITH INJURIES W THIRD ST FAR CFS# 2013-005284",
        "SRC:15",
        "CALL:ACCIDENT WITH INJURIES",
        "ADDR:W THIRD ST",
        "CITY:FARMVILLE",
        "ID:2013-005284");

    doTest("T27",
        "MAILBOX:09 FIRE ALARM 1575 SCOTT DR FAR CFS# 2013-005224",
        "SRC:09",
        "CALL:FIRE ALARM",
        "ADDR:1575 SCOTT DR",
        "CITY:FARMVILLE",
        "ID:2013-005224");

    doTest("T28",
        "MAILBOX:17 ACCIDENT NO INJURY S MAIN ST FAR CFS# 2013-005121",
        "SRC:17",
        "CALL:ACCIDENT NO INJURY",
        "ADDR:S MAIN ST",
        "CITY:FARMVILLE",
        "ID:2013-005121");

    doTest("T29",
        "MAILBOX:18 SHOOTING 100 HEALY ST FAR CFS# 2013-005048",
        "SRC:18",
        "CALL:SHOOTING",
        "ADDR:100 HEALY ST",
        "CITY:FARMVILLE",
        "ID:2013-005048");

    doTest("T30",
        "MAILBOX:47 HAZMAT/SPILL 502 LANDON ST 26 FAR CFS# 2013-005047",
        "SRC:47",
        "CALL:HAZMAT/SPILL",
        "ADDR:502 LANDON ST 26",
        "CITY:FARMVILLE",
        "ID:2013-005047");

    doTest("T31",
        "MAILBOX:17 STRUCTURE FIRE 1506 S MAIN ST A FAR CFS# 2013-004851",
        "SRC:17",
        "CALL:STRUCTURE FIRE",
        "ADDR:1506 S MAIN ST A",
        "CITY:FARMVILLE",
        "ID:2013-004851");

    doTest("T32",
        "MAILBOX:49 VEHICLE/EQUIPMENT FIRE 1913 E THIRD ST FAR CFS# 2013-004767",
        "SRC:49",
        "CALL:VEHICLE/EQUIPMENT FIRE",
        "ADDR:1913 E THIRD ST",
        "CITY:FARMVILLE",
        "ID:2013-004767");

    doTest("T33",
        "MAILBOX:36 BRUSH / FOREST FIRE FAIRGROUNDS RD FAR CFS# 2013-004651",
        "SRC:36",
        "CALL:BRUSH / FOREST FIRE",
        "ADDR:FAIRGROUNDS RD",
        "CITY:FARMVILLE",
        "ID:2013-004651");

    doTest("T34",
        "MAILBOX:29 STRUCTURE FIRE VAUGHAN ST FAR CFS# 2013-004528",
        "SRC:29",
        "CALL:STRUCTURE FIRE",
        "ADDR:VAUGHAN ST",
        "CITY:FARMVILLE",
        "ID:2013-004528");

    doTest("T35",
        "MAILBOX:26 ACCIDENT WITH INJURIES MILNWOOD RD FAR CFS# 2013-004476",
        "SRC:26",
        "CALL:ACCIDENT WITH INJURIES",
        "ADDR:MILNWOOD RD",
        "CITY:FARMVILLE",
        "ID:2013-004476");

    doTest("T36",
        "MAILBOX:07 ACCIDENT NO INJURY BUSH RIVER DR FAR CFS# 2013-004470",
        "SRC:07",
        "CALL:ACCIDENT NO INJURY BUSH",
        "ADDR:RIVER DR",
        "CITY:FARMVILLE",
        "ID:2013-004470");

    doTest("T37",
        "MAILBOX:27 ACCIDENT NO INJURY 1218 SHEPPARDS RD FAR CFS# 2013-004386",
        "SRC:27",
        "CALL:ACCIDENT NO INJURY",
        "ADDR:1218 SHEPPARDS RD",
        "CITY:FARMVILLE",
        "ID:2013-004386");

    doTest("T38",
        "MAILBOX:29 STRUCTURE FIRE 94 PINE KNOLL DR RIC CFS# 2013-004236",
        "SRC:29",
        "CALL:STRUCTURE FIRE",
        "ADDR:94 PINE KNOLL DR",
        "CITY:RICE",
        "ID:2013-004236");

    doTest("T39",
        "MAILBOX:00 STRUCTURE FIRE 94 PINE KNOLL DR RIC CFS# 2013-004231",
        "SRC:00",
        "CALL:STRUCTURE FIRE",
        "ADDR:94 PINE KNOLL DR",
        "CITY:RICE",
        "ID:2013-004231");

    doTest("T40",
        "MAILBOX:47 FIRE ALARM 202 HIGH ST FAR CFS# 2013-004201",
        "SRC:47",
        "CALL:FIRE ALARM",
        "ADDR:202 HIGH ST",
        "CITY:FARMVILLE",
        "ID:2013-004201");

    doTest("T41",
        "MAILBOX:42 COMMERCIAL STRUCTURE FIRE 3991 W THIRD ST FAR CFS# 2013-004191",
        "SRC:42",
        "CALL:COMMERCIAL STRUCTURE FIRE",
        "ADDR:3991 W THIRD ST",
        "CITY:FARMVILLE",
        "ID:2013-004191");

    doTest("T42",
        "MAILBOX:10 FIRE ALARM 1303 W THIRD ST FAR CFS# 2013-004152",
        "SRC:10",
        "CALL:FIRE ALARM",
        "ADDR:1303 W THIRD ST",
        "CITY:FARMVILLE",
        "ID:2013-004152");

    doTest("T43",
        "MAILBOX:24 COMMERCIAL STRUCTURE FIRE HSC / CFS# 2013-004116",
        "SRC:24",
        "CALL:COMMERCIAL STRUCTURE FIRE HSC /",
        "ID:2013-004116");

    doTest("T44",
        "MAILBOX:15 FIRE (UNKNOWN) 3082 W THIRD ST FAR CFS# 2013-004090",
        "SRC:15",
        "CALL:FIRE (UNKNOWN)",
        "ADDR:3082 W THIRD ST",
        "CITY:FARMVILLE",
        "ID:2013-004090");

    doTest("T45",
        "MAILBOX:01 ACCIDENT NO INJURY PRINCE EDWARD HWY FAR CFS# 2013-004054",
        "SRC:01",
        "CALL:ACCIDENT NO INJURY PRINCE",
        "ADDR:EDWARD HWY",
        "CITY:FARMVILLE",
        "ID:2013-004054");

    doTest("T46",
        "MAILBOX:10 SEIZURES 1096 FAIRGROUNDS RD FAR CFS# 2013-004053",
        "SRC:10",
        "CALL:SEIZURES",
        "ADDR:1096 FAIRGROUNDS RD",
        "CITY:FARMVILLE",
        "ID:2013-004053");

    doTest("T47",
        "MAILBOX:51 FIRE ALARM 1801 E THIRD ST FAR CFS# 2013-003975",
        "SRC:51",
        "CALL:FIRE ALARM",
        "ADDR:1801 E THIRD ST",
        "CITY:FARMVILLE",
        "ID:2013-003975");

    doTest("T48",
        "MAILBOX:12 BRUSH / FOREST FIRE 4285 S JAMES MADISON HWY CFS# 2013-003954",
        "SRC:12",
        "CALL:BRUSH / FOREST FIRE",
        "ADDR:4285 S JAMES MADISON HWY",
        "ID:2013-003954");

    doTest("T49",
        "MAILBOX:53 FIRE ALARM 100 FIRST ST FAR CFS# 2013-003872",
        "SRC:53",
        "CALL:FIRE ALARM",
        "ADDR:100 FIRST ST",
        "CITY:FARMVILLE",
        "ID:2013-003872");

    doTest("T50",
        "MAILBOX:00 FIRE (UNKNOWN) 222 CALLAWAY LN FAR CFS# 2013-003754",
        "SRC:00",
        "CALL:FIRE (UNKNOWN)",
        "ADDR:222 CALLAWAY LN",
        "CITY:FARMVILLE",
        "ID:2013-003754");

    doTest("T51",
        "MAILBOX:46 ACCIDENT NO INJURY 14940 PRINCE EDWARD HWY FAR CFS# 2013-003666",
        "SRC:46",
        "CALL:ACCIDENT NO INJURY",
        "ADDR:14940 PRINCE EDWARD HWY",
        "CITY:FARMVILLE",
        "ID:2013-003666");

    doTest("T52",
        "MAILBOX:35 FIRE (UNKNOWN) 1649 COMMERCE RD FAR CFS# 2013-003641",
        "SRC:35",
        "CALL:FIRE (UNKNOWN)",
        "ADDR:1649 COMMERCE RD",
        "CITY:FARMVILLE",
        "ID:2013-003641");

    doTest("T53",
        "MAILBOX:18 COMMERCIAL STRUCTURE FIRE 811 POPLAR FOREST RD FAR CFS# 2013-003436",
        "SRC:18",
        "CALL:COMMERCIAL STRUCTURE FIRE",
        "ADDR:811 POPLAR FOREST RD",
        "CITY:FARMVILLE",
        "ID:2013-003436");

    doTest("T54",
        "MAILBOX:12 FIRE (UNKNOWN) 108 GEORGIA ST FAR CFS# 2013-003373",
        "SRC:12",
        "CALL:FIRE (UNKNOWN)",
        "ADDR:108 GEORGIA ST",
        "CITY:FARMVILLE",
        "ID:2013-003373");

    doTest("T55",
        "MAILBOX:20 FIRE ALARM 500 PLANK RD FAR CFS# 2013-003315",
        "SRC:20",
        "CALL:FIRE ALARM",
        "ADDR:500 PLANK RD",
        "CITY:FARMVILLE",
        "ID:2013-003315");

    doTest("T56",
        "MAILBOX:31 FIRE ALARM 210 FOURTH ST FAR CFS# 2013-003195",
        "SRC:31",
        "CALL:FIRE ALARM",
        "ADDR:210 FOURTH ST",
        "CITY:FARMVILLE",
        "ID:2013-003195");

    doTest("T57",
        "MAILBOX:32 ACCIDENT NO INJURY 301 W THIRD ST FAR CFS# 2013-002971",
        "SRC:32",
        "CALL:ACCIDENT NO INJURY",
        "ADDR:301 W THIRD ST",
        "CITY:FARMVILLE",
        "ID:2013-002971");

    doTest("T58",
        "MAILBOX:40 FIRE ALARM 202 HIGH ST FAR CFS# 2013-002954",
        "SRC:40",
        "CALL:FIRE ALARM",
        "ADDR:202 HIGH ST",
        "CITY:FARMVILLE",
        "ID:2013-002954");

    doTest("T59",
        "MAILBOX:05 COMMERCIAL STRUCTURE FIRE 2104 S MAIN ST FAR CFS# 2013-002937",
        "SRC:05",
        "CALL:COMMERCIAL STRUCTURE FIRE",
        "ADDR:2104 S MAIN ST",
        "CITY:FARMVILLE",
        "ID:2013-002937");

    doTest("T60",
        "MAILBOX:17 ODOR INVESTIGATION 226 CALLAWAY LN FAR CFS# 2013-002933",
        "SRC:17",
        "CALL:ODOR INVESTIGATION",
        "ADDR:226 CALLAWAY LN",
        "CITY:FARMVILLE",
        "ID:2013-002933");

    doTest("T61",
        "MAILBOX:23 FIRE ALARM 202 HIGH ST FAR CFS# 2013-002930",
        "SRC:23",
        "CALL:FIRE ALARM",
        "ADDR:202 HIGH ST",
        "CITY:FARMVILLE",
        "ID:2013-002930");

    doTest("T62",
        "MAILBOX:19 PUBLIC ASSISTANCE REQUEST 364 W THIRD ST FAR CFS# 2013-002910",
        "SRC:19",
        "CALL:PUBLIC ASSISTANCE REQUEST",
        "ADDR:364 W THIRD ST",
        "CITY:FARMVILLE",
        "ID:2013-002910");

    doTest("T63",
        "MAILBOX:30 FIRE ALARM 508 WATER WORKS RD FAR CFS# 2013-002884",
        "SRC:30",
        "CALL:FIRE ALARM",
        "ADDR:508 WATER WORKS RD",
        "CITY:FARMVILLE",
        "ID:2013-002884");

    doTest("T64",
        "MAILBOX:26 FIRE (UNKNOWN) 1537 CUMBERLAND RD FAR CFS# 2013-002819",
        "SRC:26",
        "CALL:FIRE (UNKNOWN)",
        "ADDR:1537 CUMBERLAND RD",
        "CITY:FARMVILLE",
        "ID:2013-002819");

    doTest("T65",
        "MAILBOX:54 STRUCTURE FIRE 2104 S MAIN ST FAR CFS# 2013-002800",
        "SRC:54",
        "CALL:STRUCTURE FIRE",
        "ADDR:2104 S MAIN ST",
        "CITY:FARMVILLE",
        "ID:2013-002800");

    doTest("T66",
        "MAILBOX:43 PUBLIC ASSISTANCE (RESCUE) 001101 SIXTH AVE FAR CFS# 2013-002739, CHIEF 1 VEHICLE RESPONDING FOR A LIFT ASSIST",
        "SRC:43",
        "CALL:PUBLIC ASSISTANCE (RESCUE)",
        "ADDR:1101 SIXTH AVE",
        "CITY:FARMVILLE",
        "ID:2013-002739, CHIEF 1 VEHICLE RESPONDING FOR A LIFT ASSIST");

    doTest("T67",
        "MAILBOX:42 PUBLIC ASSISTANCE (RESCUE) 1101 SIXTH AVE FAR CFS# 2013-002739",
        "SRC:42",
        "CALL:PUBLIC ASSISTANCE (RESCUE)",
        "ADDR:1101 SIXTH AVE",
        "CITY:FARMVILLE",
        "ID:2013-002739");

    doTest("T68",
        "MAILBOX:47 STRUCTURE FIRE 1371 SIMPSON RD PRO CFS# 2013-002679",
        "SRC:47",
        "CALL:STRUCTURE FIRE",
        "ADDR:1371 SIMPSON RD",
        "CITY:PROSPECT",
        "ID:2013-002679");

    doTest("T69",
        "MAILBOX:49 FIRE ALARM 605 FUQUA DR FAR CFS# 2013-002668",
        "SRC:49",
        "CALL:FIRE ALARM",
        "ADDR:605 FUQUA DR",
        "CITY:FARMVILLE",
        "ID:2013-002668");

    doTest("T70",
        "MAILBOX:26 FIRE ALARM 900 W THIRD ST FAR CFS# 2013-002588",
        "SRC:26",
        "CALL:FIRE ALARM",
        "ADDR:900 W THIRD ST",
        "CITY:FARMVILLE",
        "ID:2013-002588");

    doTest("T71",
        "MAILBOX:58 PUBLIC ASSISTANCE REQUEST 800 OAK ST FAR CFS# 2013-002567",
        "SRC:58",
        "CALL:PUBLIC ASSISTANCE REQUEST",
        "ADDR:800 OAK ST",
        "CITY:FARMVILLE",
        "ID:2013-002567");

    doTest("T72",
        "MAILBOX:57 FIRE ALARM 000202 HIGH ST FAR CFS# 2013-002546 NEED CREW TO RESPOND THIRD TONE",
        "SRC:57",
        "CALL:FIRE ALARM",
        "ADDR:202 HIGH ST",
        "CITY:FARMVILLE",
        "ID:2013-002546 NEED CREW TO RESPOND THIRD TONE");

    doTest("T73",
        "MAILBOX:46 FIRE ALARM 202 HIGH ST FAR CFS# 2013-002546",
        "SRC:46",
        "CALL:FIRE ALARM",
        "ADDR:202 HIGH ST",
        "CITY:FARMVILLE",
        "ID:2013-002546");

    doTest("T74",
        "MAILBOX:41 VEHICLE/EQUIPMENT FIRE 11802 PRINCE EDWARD HWY PRO CFS# 2013-002530",
        "SRC:41",
        "CALL:VEHICLE/EQUIPMENT FIRE",
        "ADDR:11802 PRINCE EDWARD HWY",
        "CITY:PROSPECT",
        "ID:2013-002530");

    doTest("T75",
        "MAILBOX:13 STRUCTURE FIRE 132 LIGONTOWN RD CFS# 2013-002463",
        "SRC:13",
        "CALL:STRUCTURE FIRE",
        "ADDR:132 LIGONTOWN RD",
        "ID:2013-002463");

    doTest("T76",
        "MAILBOX:26 ACCIDENT NO INJURY PRINCE EDWARD HWY FAR CFS# 2013-002440",
        "SRC:26",
        "CALL:ACCIDENT NO INJURY PRINCE",
        "ADDR:EDWARD HWY",
        "CITY:FARMVILLE",
        "ID:2013-002440");

    doTest("T77",
        "MAILBOX:14 STRUCTURE FIRE 418 SPRUCE ST FAR CFS# 2013-002433",
        "SRC:14",
        "CALL:STRUCTURE FIRE",
        "ADDR:418 SPRUCE ST",
        "CITY:FARMVILLE",
        "ID:2013-002433");

    doTest("T78",
        "MAILBOX:05 SMOKE INVESTIGATION (OUTSIDE) 813 LONGWOOD AVE .. FAR CFS# 2013-002431",
        "SRC:05",
        "CALL:SMOKE INVESTIGATION",
        "ADDR:(OUTSIDE) 813 LONGWOOD AVE",
        "MADDR:813 LONGWOOD AVE",
        "CITY:FARMVILLE",
        "ID:2013-002431");

    doTest("T79",
        "MAILBOX:28 FIRE ALARM 9 MAHAN RD FAR CFS# 2013-002427",
        "SRC:28",
        "CALL:FIRE ALARM",
        "ADDR:9 MAHAN RD",
        "CITY:FARMVILLE",
        "ID:2013-002427");

    doTest("T80",
        "MAILBOX:40 FIRE ALARM 202 HIGH ST FAR CFS# 2013-002356",
        "SRC:40",
        "CALL:FIRE ALARM",
        "ADDR:202 HIGH ST",
        "CITY:FARMVILLE",
        "ID:2013-002356");

    doTest("T81",
        "MAILBOX:57 STRUCTURE FIRE 383 RIVER RD FAR CFS# 2013-002322",
        "SRC:57",
        "CALL:STRUCTURE FIRE",
        "ADDR:383 RIVER RD",
        "CITY:FARMVILLE",
        "ID:2013-002322");

    doTest("T82",
        "MAILBOX:36 ACCIDENT NO INJURY N MAIN ST FAR CFS# 2013-002287",
        "SRC:36",
        "CALL:ACCIDENT NO INJURY",
        "ADDR:N MAIN ST",
        "CITY:FARMVILLE",
        "ID:2013-002287");

    doTest("T83",
        "MAILBOX:07 FIRE ALARM 202 HIGH ST FAR CFS# 2013-002227",
        "SRC:07",
        "CALL:FIRE ALARM",
        "ADDR:202 HIGH ST",
        "CITY:FARMVILLE",
        "ID:2013-002227");

    doTest("T84",
        "MAILBOX:34 TRASH FIRE (RUBBISH) CFS# 2013-002126",
        "SRC:34",
        "CALL:TRASH FIRE",
        "ADDR:(RUBBISH)",
        "ID:2013-002126");

    doTest("T85",
        "MAILBOX:21 ACCIDENT WITH INJURIES CUMBERLAND RD FAR CFS# 2013-002106",
        "SRC:21",
        "CALL:ACCIDENT WITH INJURIES",
        "ADDR:CUMBERLAND RD",
        "CITY:FARMVILLE",
        "ID:2013-002106");

    doTest("T86",
        "MAILBOX:35 FIRE ALARM 406 DOSWELL ST 2 FAR CFS# 2013-001963",
        "SRC:35",
        "CALL:FIRE ALARM",
        "ADDR:406 DOSWELL ST 2",
        "CITY:FARMVILLE",
        "ID:2013-001963");

    doTest("T87",
        "MAILBOX:48 FIRE ALARM 406 DOSWELL ST FAR CFS# 2013-001959",
        "SRC:48",
        "CALL:FIRE ALARM",
        "ADDR:406 DOSWELL ST",
        "CITY:FARMVILLE",
        "ID:2013-001959");

    doTest("T88",
        "MAILBOX:54 FIRE ALARM 800 OAK ST FAR CFS# 2013-001949",
        "SRC:54",
        "CALL:FIRE ALARM",
        "ADDR:800 OAK ST",
        "CITY:FARMVILLE",
        "ID:2013-001949");

    doTest("T89",
        "MAILBOX:32 FIRE ALARM 1575 SCOTT DR FAR CFS# 2013-001869",
        "SRC:32",
        "CALL:FIRE ALARM",
        "ADDR:1575 SCOTT DR",
        "CITY:FARMVILLE",
        "ID:2013-001869");

    doTest("T90",
        "MAILBOX:57 FIRE ALARM 1506 S MAIN ST FAR CFS# 2013-001821",
        "SRC:57",
        "CALL:FIRE ALARM",
        "ADDR:1506 S MAIN ST",
        "CITY:FARMVILLE",
        "ID:2013-001821");

    doTest("T91",
        "MAILBOX:43 FIRE ALARM 202 HIGH ST FAR CFS# 2013-001818",
        "SRC:43",
        "CALL:FIRE ALARM",
        "ADDR:202 HIGH ST",
        "CITY:FARMVILLE",
        "ID:2013-001818");

    doTest("T92",
        "MAILBOX:13 SMOKE INVESTIGATION (OUTSIDE) 820 LONGWOOD AVE FAR CFS# 2013-001807",
        "SRC:13",
        "CALL:SMOKE INVESTIGATION",
        "ADDR:(OUTSIDE) 820 LONGWOOD AVE",
        "MADDR:820 LONGWOOD AVE",
        "CITY:FARMVILLE",
        "ID:2013-001807");

    doTest("T93",
        "MAILBOX:23 FIRE ALARM 200 FIRST ST CFS# 2013-001689",
        "SRC:23",
        "CALL:FIRE ALARM",
        "ADDR:200 FIRST ST",
        "ID:2013-001689");

    doTest("T94",
        "MAILBOX:05 FIRE ALARM 508 WATER WORKS RD FAR CFS# 2013-001662",
        "SRC:05",
        "CALL:FIRE ALARM",
        "ADDR:508 WATER WORKS RD", // google says it is "waterworks"
        "CITY:FARMVILLE", 
        "ID:2013-001662");

    doTest("T95",
        "MAILBOX:02 FIRE ALARM 711 SECOND AVE FAR CFS# 2013-001659",
        "SRC:02",
        "CALL:FIRE ALARM",
        "ADDR:711 SECOND AVE",
        "CITY:FARMVILLE",
        "ID:2013-001659");

    doTest("T96",
        "MAILBOX:30 FIRE ALARM 1800 PEERY DR FAR CFS# 2013-001634",
        "SRC:30",
        "CALL:FIRE ALARM",
        "ADDR:1800 PEERY DR",
        "CITY:FARMVILLE",
        "ID:2013-001634");

    doTest("T97",
        "MAILBOX:22 STROKE 1575 SCOTT DR FAR CFS# 2013-001600",
        "SRC:22",
        "CALL:STROKE",
        "ADDR:1575 SCOTT DR",
        "CITY:FARMVILLE",
        "ID:2013-001600");

    doTest("T98",
        "MAILBOX:42 FIRE ALARM 202 HIGH ST FAR CFS# 2013-001597",
        "SRC:42",
        "CALL:FIRE ALARM",
        "ADDR:202 HIGH ST",
        "CITY:FARMVILLE",
        "ID:2013-001597");
    
  }
  
  @Test
  public void testvolunteerdept() {

    doTest("T1",
        "MAILBOX:59 SICK 001575 SCOTT DR FAR CFS# 2013-007067 NEED FULL CREW TO RESPOND TRINITY MISSION. 87 Y/O F HAVING CONVULSIONS.",
        "SRC:59",
        "CALL:SICK",
        "ADDR:1575 SCOTT DR",
        "CITY:FARMVILLE",
        "ID:2013-007067 NEED FULL CREW TO RESPOND TRINITY MISSION. 87 Y/O F HAVING CONVULSIONS.");

    doTest("T2",
        "MAILBOX:29 DIFFICULTY BREATHING 005908 HEIGHTS SCHOOL RD PAM CFS# 2013-007022 NEED ALS",
        "SRC:29",
        "CALL:DIFFICULTY BREATHING",
        "ADDR:5908 HEIGHTS SCHOOL RD", //address not found
        "CITY:PAMPLIN CITY",
        "ID:2013-007022 NEED ALS");

    doTest("T3",
        "MAILBOX:36 MEDICAL ALARM 001501 GILLIAM DR FAR CFS# 2013-006970 PER OFFICERS ON SCENE, DISREGARD",
        "SRC:36",
        "CALL:MEDICAL ALARM",
        "ADDR:1501 GILLIAM DR",
        "CITY:FARMVILLE",
        "ID:2013-006970 PER OFFICERS ON SCENE, DISREGARD");

    doTest("T4",
        "MAILBOX:24 RESCUE CALL 002280 LOCKETT RD RIC CFS# 2013-006963",
        "SRC:24",
        "CALL:RESCUE CALL",
        "ADDR:2280 LOCKETT RD", // address not found
        "CITY:RICE",
        "ID:2013-006963");

    doTest("T5",
        "MAILBOX:25 ABDOMINAL/BACK PAIN 002450 CUMBERLAND RD FAR CFS# 2013-006922 DAVIS WILL HANDLE",
        "SRC:25",
        "CALL:ABDOMINAL/BACK PAIN",
        "ADDR:2450 CUMBERLAND RD", 
        "CITY:FARMVILLE", //maps to the city cumberland
        "ID:2013-006922 DAVIS WILL HANDLE");

    doTest("T6",
        "MAILBOX:30 ACCIDENT WITH INJURIES 000710 N MAIN ST FAR CFS# 2013-006760 R12 ADVISING TO CANCEL ON 2ND AMBULANCE, THEY WILL HANDLE",
        "SRC:30",
        "CALL:ACCIDENT WITH INJURIES",
        "ADDR:710 N MAIN ST",
        "CITY:FARMVILLE",
        "ID:2013-006760 R12 ADVISING TO CANCEL ON 2ND AMBULANCE, THEY WILL HANDLE");

    doTest("T7",
        "MAILBOX:27 ACCIDENT WITH INJURIES 000710 N MAIN ST FAR CFS# 2013-006760 NEED FULL CREW FOR 2ND AMBULANCE TO RESPOND PER R12",
        "SRC:27",
        "CALL:ACCIDENT WITH INJURIES",
        "ADDR:710 N MAIN ST",
        "CITY:FARMVILLE",
        "ID:2013-006760 NEED FULL CREW FOR 2ND AMBULANCE TO RESPOND PER R12");

    doTest("T8",
        "MAILBOX:00 RESCUE CALL 002003 COBB ST FAR CFS# 2013-006721NEED FULL DUTY CREW",
        "SRC:00",
        "CALL:RESCUE CALL",
        "ADDR:2003 COBB ST",
        "CITY:FARMVILLE",
        "ID:2013-006721NEED FULL DUTY CREW");

    doTest("T9",
        "MAILBOX:54 DIFFICULTY BREATHING 000031 BRIERY WAY RD FAR CFS# 2013-006666 REQUESTING ALS FOR A 78 Y/O F DIFFICULTY BREATHING.",
        "SRC:54",
        "CALL:DIFFICULTY BREATHING",
        "ADDR:31 BRIERY WAY RD", //address not found
        "CITY:FARMVILLE",
        "ID:2013-006666 REQUESTING ALS FOR A 78 Y/O F DIFFICULTY BREATHING.");

    doTest("T10",
        "MAILBOX:44 FULL CARDIAC ARREST 001575 SCOTT DR FAR CFS# 2013-006618 STILL NEED EMT",
        "SRC:44",
        "CALL:FULL CARDIAC ARREST",
        "ADDR:1575 SCOTT DR",
        "CITY:FARMVILLE",
        "ID:2013-006618 STILL NEED EMT");

    doTest("T11",
        "MAILBOX:33 SICK 000485 WATSON BVD MEH CFS# 2013-006617 FULL CREW NEEDED",
        "SRC:33",
        "CALL:SICK",
        "ADDR:485 WATSON BVD", //maps to watson st, farmville
        "CITY:MEHERRIN",
        "ID:2013-006617 FULL CREW NEEDED");

    doTest("T12",
        "MAILBOX:54 CHEST PAINS 000235 MAHAN RD FAR CFS# 2013-006426, DISREGARD CALL, DAVIS AMBULANCE WILL HANDLE",
        "SRC:54",
        "CALL:CHEST PAINS",
        "ADDR:235 MAHAN RD",
        "CITY:FARMVILLE",
        "ID:2013-006426, DISREGARD CALL, DAVIS AMBULANCE WILL HANDLE");

    doTest("T13",
        "MAILBOX:30 SEIZURES 000035 MCALLISTER LN FAR CFS# 2013-006403",
        "SRC:30",
        "CALL:SEIZURES",
        "ADDR:35 MCALLISTER LN",
        "CITY:FARMVILLE",
        "ID:2013-006403");

    doTest("T14",
        "MAILBOX:46 DIABETIC 000305 RIVER RD FAR CFS# 2013-006326 DIABETIC MALE WITH LOW BLOOD SUGAR. NEED FULL CREW",
        "SRC:46",
        "CALL:DIABETIC",
        "ADDR:305 RIVER RD",
        "CITY:FARMVILLE",
        "ID:2013-006326 DIABETIC MALE WITH LOW BLOOD SUGAR. NEED FULL CREW");

    doTest("T15",
        "MAILBOX:55 RESCUE CALL 000199 SUZANNE DR RIC CFS# 2013-006264",
        "SRC:55",
        "CALL:RESCUE CALL",
        "ADDR:199 SUZANNE DR", //google lists city as 201??
        "CITY:RICE",
        "ID:2013-006264");

    doTest("T16",
        "MAILBOX:03 RESCUE CALL 002003 COBB ST FAR CFS# 2013-006243, NEED EMT TO COMPLETE CREW, POSSIBLE HIP DISLOCATION",
        "SRC:03",
        "CALL:RESCUE CALL",
        "ADDR:2003 COBB ST",
        "CITY:FARMVILLE",
        "ID:2013-006243, NEED EMT TO COMPLETE CREW, POSSIBLE HIP DISLOCATION");

    doTest("T17",
        "MAILBOX:59 RESCUE CALL 002003 COBB ST FAR CFS# 2013-006243, NEED FULL CREW, POSSIBLE DISLOCATED HIP, LEE 147",
        "SRC:59",
        "CALL:RESCUE CALL",
        "ADDR:2003 COBB ST",
        "CITY:FARMVILLE",
        "ID:2013-006243, NEED FULL CREW, POSSIBLE DISLOCATED HIP, LEE 147");

    doTest("T18",
        "MAILBOX:18 RESCUE CALL 008106 ABILENE RD FAR CFS# 2013-006197 CROSS: FARMVILLE RD/(RT 15)",
        "SRC:18",
        "CALL:RESCUE CALL",
        "ADDR:8106 ABILENE RD",
        "CITY:FARMVILLE",
        "ID:2013-006197",
        "X:FARMVILLE RD/(RT 15)");

    doTest("T19",
        "MAILBOX:14 CHEST PAINS 001878 OLIVER RD PRO CFS# 2013-006115 DRIVER OR EMT",
        "SRC:14",
        "CALL:CHEST PAINS",
        "ADDR:1878 OLIVER RD", //cannot find address
        "CITY:PROSPECT",
        "ID:2013-006115 DRIVER OR EMT");

    
  }
  
  public static void main(String[] args) {
    new VAPrinceEdwardCountyParserTest().generateTests("T1");
  }
}