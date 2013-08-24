package net.anei.cadpage.parsers.IA;

import net.anei.cadpage.parsers.BaseParserTest;
import net.anei.cadpage.parsers.IA.IABlackHawkCountyParser;

import org.junit.Test;

/*
Waterloo, IA (or Black Hawk County)
Contact: Ryan Mahood <331medic@gmail.com>
Sender: Swmail@bhcso.org,Xmail@connectingyou.com

10-127299 MVA ROLLOVER/20 HWY SERGEANT RD WATERLOO
10-124880 MVA ROLLOVER 1941 W 6TH ST EUREKA/ MITCHELL/WATERLOO

Black Hawk County, IA
Contact: Active911
[] (Dispatch) 12-092660 ALLERGIES/HIVES/STING/MEDREACT \r\nReported: 09/03/2012 14:41:50 \r\n6615 LAFAYETTE RD \r\n4TH ST / 3 RD ST (RA/YMOND RD \r\nJOE THOME TRUCKING RAYMO\r\n
[] (Dispatch) 12-087578 BURNING COMPLAINT\r\nReported: 08/21/2012 17:21:39\r\n504 S 3RD ST\r\nDUBUQUE / S S CT/\r\nMARSHALL,DAVID M3407  LID 6661 RAYMOND\r\n921\r\n

Contact: Active911
Agency name: Evansdale Fire Rescue
Location: Evansdale, IA, United States
Sender: messaging@iamresponding.com

(EFR) 13-059366 STROKE Reported: 06/12/2013 16:48:47 210-218 S EVANS RD LAFAYETTE / MORRELL EVANS VILLAGE  EVANSDALE 238 
(EFR) 13-059264 SUICIDE ATTEMPT/PSYCHIATRIC Reported: 06/12/2013 10:18:57 239 FELDT AV LAWRENCE / S ROOSEVELT EVANSDALE 238 
(EFR) 13-059235 ALLERGIES/HIVES/STING/MEDREACT Reported: 06/12/2013 08:00:57 239 FELDT AV LAWRENCE / S ROOSEVELT EVANSDALE 238 
(EFR) 13-058779 BREATHING PROBLEMS Reported: 06/11/2013 01:59:27 1110 SCHONS ST N FRM 600 ARBUTUS / GILBERT EVANSDALE 238 2G3 
(EFR) 13-058405 SICK PERSON Reported: 06/10/2013 08:57:51 3701 LAFAYETTE RD NATURE TRAIL / CLARK ST EVANSDALE FAMILY PRACTICE  EVANSDALE 238 
(EFR) 13-058169 BACK PAIN NON-TRAUMATIC Reported: 06/09/2013 15:20:08 715-313 CENTRAL AV EVANS RD / BOELING EVANSDALE 238 
(EFR) 13-058024 ASSAULT/AMBULANCE REQUESTED Reported: 06/09/2013 02:57:00 628 SUNNYSIDE DR 'EVPD N FRM 900 BROOKSIDE / JOY DR EVANSDALE 238 2G3 2K2 
(EFR) 13-057914 BREATHING PROBLEMS Reported: 06/08/2013 23:01:50 715-105 CENTRAL AV EVANS RD / BOELING EVANSDALE 238 
(EFR) 13-057796 HEADACHE Reported: 06/08/2013 16:34:26 207 GRAND BLVD MORRELL / EAST END EN EVANSDALE 238 
(EFR) 13-056932 LIFELINE REQUEST Reported: 06/06/2013 13:48:51 2107 PARKVIEW DR N FRM 20TH & 5TH / PATRICIA DR GILBERTVILLE 238 
(EFR) 13-056919 APPLIANCE FIRE NO STRUCTURE Reported: 06/06/2013 12:55:30 1756 ENID ST E FRM 700 2ND AVE / JULE EVANSDALE 201 
(EFR) 13-056324 CHEST PAIN Reported: 06/04/2013 16:53:06 1038 RIVER FOREST RD TRAIL /  GILBERT CASEYS GENERAL STORE-EDALE-RVR FRST  EVANSDALE 238 2G9 
(EFR) 13-056206 SICK PERSON Reported: 06/04/2013 07:53:05 229 LANDMARK DR ANN ST / GREENBUSH RAYMOND 238 9FST 
(EFR) 13-055905 BREATHING PROBLEMS Reported: 06/03/2013 12:33:01 302 RIVER FOREST RD MORRELL / EAST END EN EVANSDALE 238 
(EFR) 13-055629 ABDOMINAL PAIN/PROBLEMS Reported: 06/02/2013 16:42:49 313-35 CENTRAL AV JONES RD / FOX AVE WELLINGTON PARK APARTMENTS (E'DALE)  EVANSDALE 238 2G17 
(EFR) 13-055184 SICK PERSON Reported: 06/01/2013 14:58:58 528-5 RIVER FOREST RD LEONARD / CENTRAL EVANSDALE 238 2A1 
(EFR) 13-055099 SICK PERSON Reported: 06/01/2013 09:32:37 545 S EVANS RD EAST END EN / MORRIS EVANSDALE 238 2A1 
(EFR) 13-054743 MVA INJURYReported: 05/31/2013 17:20:24380 FRWY/ RIVER FOREST RDEVANSDALE238LAST TEXT WAS A TEST ONLY 
(EFR) 13-054743 MVA INJURY Reported: 05/31/2013 17:20:24 380 FRWY/ RIVER FOREST RD EVANSDALE 238 
(EFR) 13-054210 COMMERCIAL AUTOMATIC ALARM Reported: 05/30/2013 11:51:06 1138 CENTRAL AV LAWRENCE / S ROOSEVELT POYNER ELEMENTRY SCHOOL (NEW)  EVANSDALE 201 202 224 238 
(EFR) 13-053504 SEIZURE / CONVULSIONS CARD 12 Reported: 05/28/2013 21:01:59 204 MINER DR N FRM 4000 LAFAYETTE / DEAD END EN EVANSDALE 238 2G15 
(EFR) 13-053352 OVERDOSE/INGESTION/POISON Reported: 05/28/2013 13:53:53 819 18TH AV E FM 8TH ST / DEAD END / CITY LIM GILBERTVILLE 238 4FST 7G51 7G67 7P52 7S68 
(EFR) 13-053309 SICK PERSON Reported: 05/28/2013 11:09:38 816 COLLEEN AV ELLIOTT / DOYLE EVANSDALE 238 
(EFR) 13-053157 BREATHING PROBLEMS Reported: 05/27/2013 22:25:29 1660 MICHIGAN DR E FRM 800 BURR OAK / 2ND AVE EVANSDALE 238 
(EFR) 13-052710 FALLS/BACK INJURIES-TRAUMATIC Reported: 05/26/2013 16:43:25 313 CENTRAL AV JONES RD / FOX AVE WELLINGTON PARK APARTMENTS (E'DALE)  EVANSDALE 238 
(EFR) 13-052146 ASSAULT/AMBULANCE REQUESTED Reported: 05/25/2013 01:03:50 3480 LAFAYETTE RD RIVER FOREST / BROWN ST LOFTYS LOUNGE   #1013  EVANSDALE 238 
(EFR) 13-052006 STROKE Reported: 05/24/2013 20:07:19 1115 LAKE AV S FRM GILBERT DR / EVANS RD EVANSDALE 238 
(EFR) 13-051910 SICK PERSON Reported: 05/24/2013 15:51:37 130 MARION ST E FRM 400 LAWRENCE / DEAD END EN EVANSDALE 238 2G8 
(EFR) 13-051425 CHEST PAIN Reported: 05/23/2013 15:27:34 210-106 S EVANS RD LAFAYETTE / MORRELL EVANS VILLAGE  EVANSDALE 238 
(EFR) 13-051005 DEATH Reported: 05/22/2013 13:35:37 6925 FENTON RD 22ND AVE / DEAD END EN GILBERTVILLE 238 7N49 
(EFR) 13-050744 BREATHING PROBLEMS Reported: 05/21/2013 20:38:20 130 BROVAN BLVD E FRM 100 RIVR FORST / SIPPLE AVE EVANSDALE 238 
(EFR) 13-050732 SEIZURE / CONVULSIONS CARD 12 Reported: 05/21/2013 20:09:19 3452 LAFAYETTE RD RIVER FOREST / BROWN ST PRONTO MARKET (E'DALE)   #1025  EVANSDALE 238 
(EFR) 13-050489 UNCONSCIOUS/FAINTING NON TRUMA Reported: 05/21/2013 05:27:23 237 TRAIL AV JONES / FOX EVANSDALE 238 
(EFR) 13-050272 UNCONSCIOUS/FAINTING NON TRUMA Reported: 05/20/2013 16:36:38 1038 RIVER FOREST RD TRAIL / GILBERT CASEYS GENERAL STORE-EDALE-RVR FRST  EVANSDALE 238 
(EFR) 13-050235 OVERDOSE/INGESTION/POISON Reported: 05/20/2013 14:54:48 110 MARY DR N FRM 300 NORMA NO / DEAD END EN EVANSDALE 238 
(EFR) 13-050194 SUICIDE ATTEMPT/PSYCHIATRIC Reported: 05/20/2013 12:34:31 313-35 CENTRAL AV JONES RD / FOX AVE WELLINGTON PARK APARTMENTS (E'DALE)  EVANSDALE 238 
(EFR) 13-049966 MVA INJURY Reported: 05/19/2013 19:35:59 4213 LAFAYETTE RD ROOSEVELT / ARTHUR EVANSDALE 238 2G8 
(EFR) 13-048801 ASSAULT/AMBULANCE REQUESTED Reported: 05/16/2013 20:42:32 100 PARK RD RIVERFOREST /  CEDAR RIVER DEERWOOD BALL DIAMONDS  EVANSDALE 238 
(EFR) 13-048649 FALLS/BACK INJURIES-TRAUMATIC Reported: 05/16/2013 15:01:47 1918 5TH ST 19TH AVE / 20TH AVE GILBERTVILLE 238 4FST 
(EFR) 13-048438 HEMORRHAGE/LACERATIONS Reported: 05/16/2013 00:46:39 750-25 RIVER FOREST RD CENTRAL / PHILLIPS EVANSDALE 238 
(EFR) 13-048101 HEMORRHAGE/LACERATIONS Reported: 05/15/2013 07:01:42 616 12TH AV E FRM 1100 6TH ST / 7TH ST ST GILBERTVILLE 238 4FST 
(EFR) 13-047751 PASSENGER VEHICLE FIRE Reported: 05/14/2013 11:02:39 4800 LAFAYETTE RD ELDENE /  MCCOY BUNGER PARK  EVANSDALE 201 
(EFR) 13-047578 BREATHING PROBLEMS Reported: 05/13/2013 22:23:33 237 TRAIL AV JONES / FOX EVANSDALE 238 2G3 

 */

public class IABlackHawkCountyParserTest extends BaseParserTest {
  
  public IABlackHawkCountyParserTest() {
    setParser(new IABlackHawkCountyParser(), "BLACK HAWK COUNTY", "IA");
  }
  
  @Test
  public void testParser() {
    
    doTest("T1",
        "10-127299 MVA ROLLOVER/20 HWY SERGEANT RD WATERLOO",
        "ID:10-127299",
        "CALL:MVA ROLLOVER/",
        "ADDR:20 HWY",
        "MADDR:HWY 20 & SERGEANT RD",
        "X:SERGEANT RD",
        "CITY:WATERLOO");
    
    doTest("T2",
        "10-124880 MVA ROLLOVER 1941 W 6TH ST EUREKA/ MITCHELL/WATERLOO",
        "ID:10-124880",
        "CALL:MVA ROLLOVER",
        "ADDR:1941 W 6TH ST",
        "X:EUREKA / MITCHELL",
        "CITY:WATERLOO");
   
  }
  
  @Test
  public void testActive911A() {

    doTest("T1",
        "[] (Dispatch) 12-092660 ALLERGIES/HIVES/STING/MEDREACT \r\n" +
        "Reported: 09/03/2012 14:41:50 \r\n" +
        "6615 LAFAYETTE RD \r\n" +
        "4TH ST / 3 RD ST (RA/YMOND RD \r\n" +
        "JOE THOME TRUCKING RAYMO\r\n",

        "ID:12-092660",
        "CALL:ALLERGIES/HIVES/STING/MEDREACT",
        "DATE:09/03/2012",
        "TIME:14:41:50",
        "ADDR:6615 LAFAYETTE RD",
        "X:4TH ST / 3 RD ST (RA/YMOND RD",
        "PLACE:JOE THOME TRUCKING",
        "CITY:RAYMOND");

    doTest("T2",
        "[] (Dispatch) 12-087578 BURNING COMPLAINT\r\n" +
        "Reported: 08/21/2012 17:21:39\r\n" +
        "504 S 3RD ST\r\n" +
        "DUBUQUE / S S CT/\r\n" +
        "MARSHALL,DAVID M3407  LID 6661 RAYMOND\r\n" +
        "921\r\n",

        "ID:12-087578",
        "CALL:BURNING COMPLAINT",
        "DATE:08/21/2012",
        "TIME:17:21:39",
        "ADDR:504 S 3RD ST",
        "X:DUBUQUE / S S CT",
        "PLACE:MARSHALL,DAVID M3407  LID 6661",
        "CITY:RAYMOND",
        "UNIT:921");

  }
  
  @Test
  public void testEvansFire() {

    doTest("T1",
        "(EFR) 13-059366 STROKE Reported: 06/12/2013 16:48:47 210-218 S EVANS RD LAFAYETTE / MORRELL EVANS VILLAGE  EVANSDALE 238 ",
        "ID:13-059366",
        "CALL:STROKE",
        "ADDR:210-218 S EVANS RD",
        "MADDR:210 S EVANS RD",
        "X:LAFAYETTE / MORRELL EVANS VILLAGE",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T2",
        "(EFR) 13-059264 SUICIDE ATTEMPT/PSYCHIATRIC Reported: 06/12/2013 10:18:57 239 FELDT AV LAWRENCE / S ROOSEVELT EVANSDALE 238 ",
        "ID:13-059264",
        "CALL:SUICIDE ATTEMPT/PSYCHIATRIC",
        "ADDR:239 FELDT AV",
        "MADDR:239 FELDT AVE",
        "X:LAWRENCE / S ROOSEVELT",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T3",
        "(EFR) 13-059235 ALLERGIES/HIVES/STING/MEDREACT Reported: 06/12/2013 08:00:57 239 FELDT AV LAWRENCE / S ROOSEVELT EVANSDALE 238 ",
        "ID:13-059235",
        "CALL:ALLERGIES/HIVES/STING/MEDREACT",
        "ADDR:239 FELDT AV",
        "MADDR:239 FELDT AVE",
        "X:LAWRENCE / S ROOSEVELT",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T4",
        "(EFR) 13-058779 BREATHING PROBLEMS Reported: 06/11/2013 01:59:27 1110 SCHONS ST N FRM 600 ARBUTUS / GILBERT EVANSDALE 238 2G3 ",
        "ID:13-058779",
        "CALL:BREATHING PROBLEMS",
        "ADDR:1110 SCHONS ST",
        "X:N FRM 600 ARBUTUS / GILBERT",
        "CITY:EVANSDALE",
        "UNIT:238 2G3");

    doTest("T5",
        "(EFR) 13-058405 SICK PERSON Reported: 06/10/2013 08:57:51 3701 LAFAYETTE RD NATURE TRAIL / CLARK ST EVANSDALE FAMILY PRACTICE  EVANSDALE 238 ",
        "ID:13-058405",
        "CALL:SICK PERSON",
        "ADDR:3701 LAFAYETTE RD",
        "X:NATURE TRAIL / CLARK ST",
        "PLACE:EVANSDALE FAMILY PRACTICE",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T6",
        "(EFR) 13-058169 BACK PAIN NON-TRAUMATIC Reported: 06/09/2013 15:20:08 715-313 CENTRAL AV EVANS RD / BOELING EVANSDALE 238 ",
        "ID:13-058169",
        "CALL:BACK PAIN NON-TRAUMATIC",
        "ADDR:715-313 CENTRAL AV",
        "MADDR:715 CENTRAL AVE",
        "X:EVANS RD / BOELING",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T7",
        "(EFR) 13-058024 ASSAULT/AMBULANCE REQUESTED Reported: 06/09/2013 02:57:00 628 SUNNYSIDE DR 'EVPD N FRM 900 BROOKSIDE / JOY DR EVANSDALE 238 2G3 2K2 ",
        "ID:13-058024",
        "CALL:ASSAULT/AMBULANCE REQUESTED",
        "ADDR:628 SUNNYSIDE DR",
        "X:'EVPD N FRM 900 BROOKSIDE / JOY DR",
        "CITY:EVANSDALE",
        "UNIT:238 2G3 2K2");

    doTest("T8",
        "(EFR) 13-057914 BREATHING PROBLEMS Reported: 06/08/2013 23:01:50 715-105 CENTRAL AV EVANS RD / BOELING EVANSDALE 238 ",
        "ID:13-057914",
        "CALL:BREATHING PROBLEMS",
        "ADDR:715-105 CENTRAL AV",
        "MADDR:715 CENTRAL AVE",
        "X:EVANS RD / BOELING",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T9",
        "(EFR) 13-057796 HEADACHE Reported: 06/08/2013 16:34:26 207 GRAND BLVD MORRELL / EAST END EN EVANSDALE 238 ",
        "ID:13-057796",
        "CALL:HEADACHE",
        "ADDR:207 GRAND BLVD",
        "X:MORRELL / EAST END EN",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T10",
        "(EFR) 13-056932 LIFELINE REQUEST Reported: 06/06/2013 13:48:51 2107 PARKVIEW DR N FRM 20TH & 5TH / PATRICIA DR GILBERTVILLE 238 ",
        "ID:13-056932",
        "CALL:LIFELINE REQUEST",
        "ADDR:2107 PARKVIEW DR",
        "X:N FRM 20TH & 5TH / PATRICIA DR",
        "CITY:GILBERTVILLE",
        "UNIT:238");

    doTest("T11",
        "(EFR) 13-056919 APPLIANCE FIRE NO STRUCTURE Reported: 06/06/2013 12:55:30 1756 ENID ST E FRM 700 2ND AVE / JULE EVANSDALE 201 ",
        "ID:13-056919",
        "CALL:APPLIANCE FIRE NO STRUCTURE",
        "ADDR:1756 ENID ST",
        "X:E FRM 700 2ND AVE / JULE",
        "CITY:EVANSDALE",
        "UNIT:201");

    doTest("T12",
        "(EFR) 13-056324 CHEST PAIN Reported: 06/04/2013 16:53:06 1038 RIVER FOREST RD TRAIL /  GILBERT CASEYS GENERAL STORE-EDALE-RVR FRST  EVANSDALE 238 2G9 ",
        "ID:13-056324",
        "CALL:CHEST PAIN",
        "ADDR:1038 RIVER FOREST RD",
        "X:TRAIL / GILBERT CASEYS GENERAL STORE-EDALE-RVR FRST",
        "CITY:EVANSDALE",
        "UNIT:238 2G9");

    doTest("T13",
        "(EFR) 13-056206 SICK PERSON Reported: 06/04/2013 07:53:05 229 LANDMARK DR ANN ST / GREENBUSH RAYMOND 238 9FST ",
        "ID:13-056206",
        "CALL:SICK PERSON",
        "ADDR:229 LANDMARK DR",
        "X:ANN ST / GREENBUSH",
        "CITY:RAYMOND",
        "UNIT:238 9FST");

    doTest("T14",
        "(EFR) 13-055905 BREATHING PROBLEMS Reported: 06/03/2013 12:33:01 302 RIVER FOREST RD MORRELL / EAST END EN EVANSDALE 238 ",
        "ID:13-055905",
        "CALL:BREATHING PROBLEMS",
        "ADDR:302 RIVER FOREST RD",
        "X:MORRELL / EAST END EN",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T15",
        "(EFR) 13-055629 ABDOMINAL PAIN/PROBLEMS Reported: 06/02/2013 16:42:49 313-35 CENTRAL AV JONES RD / FOX AVE WELLINGTON PARK APARTMENTS (E'DALE)  EVANSDALE 238 2G17 ",
        "ID:13-055629",
        "CALL:ABDOMINAL PAIN/PROBLEMS",
        "ADDR:313-35 CENTRAL AV",
        "MADDR:313 CENTRAL AVE",
        "X:JONES RD / FOX AVE",
        "PLACE:WELLINGTON PARK APARTMENTS (E'DALE)",
        "CITY:EVANSDALE",
        "UNIT:238 2G17");

    doTest("T16",
        "(EFR) 13-055184 SICK PERSON Reported: 06/01/2013 14:58:58 528-5 RIVER FOREST RD LEONARD / CENTRAL EVANSDALE 238 2A1 ",
        "ID:13-055184",
        "CALL:SICK PERSON",
        "ADDR:528-5 RIVER FOREST RD",
        "MADDR:528 RIVER FOREST RD",
        "X:LEONARD / CENTRAL",
        "CITY:EVANSDALE",
        "UNIT:238 2A1");

    doTest("T17",
        "(EFR) 13-055099 SICK PERSON Reported: 06/01/2013 09:32:37 545 S EVANS RD EAST END EN / MORRIS EVANSDALE 238 2A1 ",
        "ID:13-055099",
        "CALL:SICK PERSON",
        "ADDR:545 S EVANS RD",
        "X:EAST END EN / MORRIS",
        "CITY:EVANSDALE",
        "UNIT:238 2A1");

    doTest("T19",
        "(EFR) 13-054743 MVA INJURY Reported: 05/31/2013 17:20:24 380 FRWY/ RIVER FOREST RD EVANSDALE 238 ",
        "ID:13-054743",
        "CALL:MVA INJURY",
        "ADDR:380 FRWY & RIVER FOREST RD",
        "MADDR:380 FRWY",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T20",
        "(EFR) 13-054210 COMMERCIAL AUTOMATIC ALARM Reported: 05/30/2013 11:51:06 1138 CENTRAL AV LAWRENCE / S ROOSEVELT POYNER ELEMENTRY SCHOOL (NEW)  EVANSDALE 201 202 224 238 ",
        "ID:13-054210",
        "CALL:COMMERCIAL AUTOMATIC ALARM",
        "ADDR:1138 CENTRAL AV",
        "MADDR:1138 CENTRAL AVE",
        "X:LAWRENCE / S ROOSEVELT POYNER ELEMENTRY SCHOOL",
        "PLACE:(NEW)",
        "CITY:EVANSDALE",
        "UNIT:201 202 224 238");

    doTest("T21",
        "(EFR) 13-053504 SEIZURE / CONVULSIONS CARD 12 Reported: 05/28/2013 21:01:59 204 MINER DR N FRM 4000 LAFAYETTE / DEAD END EN EVANSDALE 238 2G15 ",
        "ID:13-053504",
        "CALL:SEIZURE / CONVULSIONS CARD 12",
        "ADDR:204 MINER DR",
        "X:N FRM 4000 LAFAYETTE / DEAD END",
        "CITY:EVANSDALE",
        "UNIT:238 2G15");

    doTest("T22",
        "(EFR) 13-053352 OVERDOSE/INGESTION/POISON Reported: 05/28/2013 13:53:53 819 18TH AV E FM 8TH ST / DEAD END / CITY LIM GILBERTVILLE 238 4FST 7G51 7G67 7P52 7S68 ",
        "ID:13-053352",
        "CALL:OVERDOSE/INGESTION/POISON",
        "ADDR:819 18TH AV",
        "MADDR:819 18TH AVE",
        "X:E FM 8TH ST / DEAD END / CITY LIM",
        "CITY:GILBERTVILLE",
        "UNIT:238 4FST 7G51 7G67 7P52 7S68");

    doTest("T23",
        "(EFR) 13-053309 SICK PERSON Reported: 05/28/2013 11:09:38 816 COLLEEN AV ELLIOTT / DOYLE EVANSDALE 238 ",
        "ID:13-053309",
        "CALL:SICK PERSON",
        "ADDR:816 COLLEEN AV",
        "MADDR:816 COLLEEN AVE",
        "X:ELLIOTT / DOYLE",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T24",
        "(EFR) 13-053157 BREATHING PROBLEMS Reported: 05/27/2013 22:25:29 1660 MICHIGAN DR E FRM 800 BURR OAK / 2ND AVE EVANSDALE 238 ",
        "ID:13-053157",
        "CALL:BREATHING PROBLEMS",
        "ADDR:1660 MICHIGAN DR",
        "X:E FRM 800 BURR OAK / 2ND AVE",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T25",
        "(EFR) 13-052710 FALLS/BACK INJURIES-TRAUMATIC Reported: 05/26/2013 16:43:25 313 CENTRAL AV JONES RD / FOX AVE WELLINGTON PARK APARTMENTS (E'DALE)  EVANSDALE 238 ",
        "ID:13-052710",
        "CALL:FALLS/BACK INJURIES-TRAUMATIC",
        "ADDR:313 CENTRAL AV",
        "MADDR:313 CENTRAL AVE",
        "X:JONES RD / FOX AVE",
        "PLACE:WELLINGTON PARK APARTMENTS (E'DALE)",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T26",
        "(EFR) 13-052146 ASSAULT/AMBULANCE REQUESTED Reported: 05/25/2013 01:03:50 3480 LAFAYETTE RD RIVER FOREST / BROWN ST LOFTYS LOUNGE   #1013  EVANSDALE 238 ",
        "ID:13-052146",
        "CALL:ASSAULT/AMBULANCE REQUESTED",
        "ADDR:3480 LAFAYETTE RD",
        "X:RIVER FOREST / BROWN ST",
        "PLACE:LOFTYS LOUNGE",
        "APT:1013",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T27",
        "(EFR) 13-052006 STROKE Reported: 05/24/2013 20:07:19 1115 LAKE AV S FRM GILBERT DR / EVANS RD EVANSDALE 238 ",
        "ID:13-052006",
        "CALL:STROKE",
        "ADDR:1115 LAKE AV",
        "MADDR:1115 LAKE AVE",
        "X:S FRM GILBERT DR / EVANS RD",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T28",
        "(EFR) 13-051910 SICK PERSON Reported: 05/24/2013 15:51:37 130 MARION ST E FRM 400 LAWRENCE / DEAD END EN EVANSDALE 238 2G8 ",
        "ID:13-051910",
        "CALL:SICK PERSON",
        "ADDR:130 MARION ST",
        "X:E FRM 400 LAWRENCE / DEAD END",
        "CITY:EVANSDALE",
        "UNIT:238 2G8");

    doTest("T29",
        "(EFR) 13-051425 CHEST PAIN Reported: 05/23/2013 15:27:34 210-106 S EVANS RD LAFAYETTE / MORRELL EVANS VILLAGE  EVANSDALE 238 ",
        "ID:13-051425",
        "CALL:CHEST PAIN",
        "ADDR:210-106 S EVANS RD",
        "MADDR:210 S EVANS RD",
        "X:LAFAYETTE / MORRELL EVANS VILLAGE",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T30",
        "(EFR) 13-051005 DEATH Reported: 05/22/2013 13:35:37 6925 FENTON RD 22ND AVE / DEAD END EN GILBERTVILLE 238 7N49 ",
        "ID:13-051005",
        "CALL:DEATH",
        "ADDR:6925 FENTON RD",
        "X:22ND AVE / DEAD END",
        "CITY:GILBERTVILLE",
        "UNIT:238 7N49");

    doTest("T31",
        "(EFR) 13-050744 BREATHING PROBLEMS Reported: 05/21/2013 20:38:20 130 BROVAN BLVD E FRM 100 RIVR FORST / SIPPLE AVE EVANSDALE 238 ",
        "ID:13-050744",
        "CALL:BREATHING PROBLEMS",
        "ADDR:130 BROVAN BLVD",
        "X:E FRM 100 RIVR FORST / SIPPLE AVE",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T32",
        "(EFR) 13-050732 SEIZURE / CONVULSIONS CARD 12 Reported: 05/21/2013 20:09:19 3452 LAFAYETTE RD RIVER FOREST / BROWN ST PRONTO MARKET (E'DALE)   #1025  EVANSDALE 238 ",
        "ID:13-050732",
        "CALL:SEIZURE / CONVULSIONS CARD 12",
        "ADDR:3452 LAFAYETTE RD",
        "X:RIVER FOREST / BROWN ST",
        "PLACE:PRONTO MARKET (E'DALE)",
        "APT:1025",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T33",
        "(EFR) 13-050489 UNCONSCIOUS/FAINTING NON TRUMA Reported: 05/21/2013 05:27:23 237 TRAIL AV JONES / FOX EVANSDALE 238 ",
        "ID:13-050489",
        "CALL:UNCONSCIOUS/FAINTING NON TRUMA",
        "ADDR:237 TRAIL AV",
        "MADDR:237 TRAIL AVE",
        "X:JONES / FOX",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T34",
        "(EFR) 13-050272 UNCONSCIOUS/FAINTING NON TRUMA Reported: 05/20/2013 16:36:38 1038 RIVER FOREST RD TRAIL / GILBERT CASEYS GENERAL STORE-EDALE-RVR FRST  EVANSDALE 238 ",
        "ID:13-050272",
        "CALL:UNCONSCIOUS/FAINTING NON TRUMA",
        "ADDR:1038 RIVER FOREST RD",
        "X:TRAIL / GILBERT CASEYS GENERAL STORE-EDALE-RVR FRST",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T35",
        "(EFR) 13-050235 OVERDOSE/INGESTION/POISON Reported: 05/20/2013 14:54:48 110 MARY DR N FRM 300 NORMA NO / DEAD END EN EVANSDALE 238 ",
        "ID:13-050235",
        "CALL:OVERDOSE/INGESTION/POISON",
        "ADDR:110 MARY DR",
        "X:N FRM 300 NORMA NO / DEAD END",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T36",
        "(EFR) 13-050194 SUICIDE ATTEMPT/PSYCHIATRIC Reported: 05/20/2013 12:34:31 313-35 CENTRAL AV JONES RD / FOX AVE WELLINGTON PARK APARTMENTS (E'DALE)  EVANSDALE 238 ",
        "ID:13-050194",
        "CALL:SUICIDE ATTEMPT/PSYCHIATRIC",
        "ADDR:313-35 CENTRAL AV",
        "MADDR:313 CENTRAL AVE",
        "X:JONES RD / FOX AVE",
        "PLACE:WELLINGTON PARK APARTMENTS (E'DALE)",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T37",
        "(EFR) 13-049966 MVA INJURY Reported: 05/19/2013 19:35:59 4213 LAFAYETTE RD ROOSEVELT / ARTHUR EVANSDALE 238 2G8 ",
        "ID:13-049966",
        "CALL:MVA INJURY",
        "ADDR:4213 LAFAYETTE RD",
        "X:ROOSEVELT / ARTHUR",
        "CITY:EVANSDALE",
        "UNIT:238 2G8");

    doTest("T38",
        "(EFR) 13-048801 ASSAULT/AMBULANCE REQUESTED Reported: 05/16/2013 20:42:32 100 PARK RD RIVERFOREST /  CEDAR RIVER DEERWOOD BALL DIAMONDS  EVANSDALE 238 ",
        "ID:13-048801",
        "CALL:ASSAULT/AMBULANCE REQUESTED",
        "ADDR:100 PARK RD",
        "X:RIVERFOREST / CEDAR RIVER DEERWOOD BALL DIAMONDS",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T39",
        "(EFR) 13-048649 FALLS/BACK INJURIES-TRAUMATIC Reported: 05/16/2013 15:01:47 1918 5TH ST 19TH AVE / 20TH AVE GILBERTVILLE 238 4FST ",
        "ID:13-048649",
        "CALL:FALLS/BACK INJURIES-TRAUMATIC",
        "ADDR:1918 5TH ST",
        "X:19TH AVE / 20TH AVE",
        "CITY:GILBERTVILLE",
        "UNIT:238 4FST");

    doTest("T40",
        "(EFR) 13-048438 HEMORRHAGE/LACERATIONS Reported: 05/16/2013 00:46:39 750-25 RIVER FOREST RD CENTRAL / PHILLIPS EVANSDALE 238 ",
        "ID:13-048438",
        "CALL:HEMORRHAGE/LACERATIONS",
        "ADDR:750-25 RIVER FOREST RD",
        "MADDR:750 RIVER FOREST RD",
        "X:CENTRAL / PHILLIPS",
        "CITY:EVANSDALE",
        "UNIT:238");

    doTest("T41",
        "(EFR) 13-048101 HEMORRHAGE/LACERATIONS Reported: 05/15/2013 07:01:42 616 12TH AV E FRM 1100 6TH ST / 7TH ST ST GILBERTVILLE 238 4FST ",
        "ID:13-048101",
        "CALL:HEMORRHAGE/LACERATIONS",
        "ADDR:616 12TH AV",
        "MADDR:616 12TH AVE",
        "X:E FRM 1100 6TH ST / 7TH ST",
        "CITY:GILBERTVILLE",
        "UNIT:238 4FST");

    doTest("T42",
        "(EFR) 13-047751 PASSENGER VEHICLE FIRE Reported: 05/14/2013 11:02:39 4800 LAFAYETTE RD ELDENE /  MCCOY BUNGER PARK  EVANSDALE 201 ",
        "ID:13-047751",
        "CALL:PASSENGER VEHICLE FIRE",
        "ADDR:4800 LAFAYETTE RD",
        "X:ELDENE / MCCOY BUNGER PARK",
        "CITY:EVANSDALE",
        "UNIT:201");

    doTest("T43",
        "(EFR) 13-047578 BREATHING PROBLEMS Reported: 05/13/2013 22:23:33 237 TRAIL AV JONES / FOX EVANSDALE 238 2G3 ",
        "ID:13-047578",
        "CALL:BREATHING PROBLEMS",
        "ADDR:237 TRAIL AV",
        "MADDR:237 TRAIL AVE",
        "X:JONES / FOX",
        "CITY:EVANSDALE",
        "UNIT:238 2G3");

  }
  
  public static void main(String[] args) {
    new IABlackHawkCountyParserTest().generateTests("T1");
  }
}