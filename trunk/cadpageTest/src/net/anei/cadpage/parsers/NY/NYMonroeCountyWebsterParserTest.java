package net.anei.cadpage.parsers.NY;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class NYMonroeCountyWebsterParserTest extends BaseParserTest {
  
  public NYMonroeCountyWebsterParserTest() {
    setParser(new NYMonroeCountyWebsterParser(), "MONROE COUNTY", "NY");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "L: 701 AUDLEY END\nT: STRUCTURE FIRE\nO: \nB: \nC1: FINCHINGFIELD LA\nC2: WOODBRIDGE LA\nX: PLASTIC CONTAINER CAUGHT FIRE ON STOVE..COMP SAYS THAT CONTAINER IS SMOLD",
        "ADDR:701 AUDLEY END",
        "CALL:STRUCTURE FIRE",
        "X:FINCHINGFIELD LA & WOODBRIDGE LA",
        "INFO:PLASTIC CONTAINER CAUGHT FIRE ON STOVE..COMP SAYS THAT CONTAINER IS SMOLD");

    doTest("T2",
        "L: 286 PHILLIPS RD\nT: AUTOMATIC FIRE ALARM\nO: SMELTER RES - 265451\nB:\nC1: N CHIGWELL LA\nC2: MEADOW BREEZE LA\nX: FIRE ALRM ,GEN, C/C - ONLY CORNER STREET ALRM",
        "ADDR:286 PHILLIPS RD",
        "CALL:AUTOMATIC FIRE ALARM",
        "NAME:SMELTER RES - 265451",
        "X:N CHIGWELL LA & MEADOW BREEZE LA",
        "INFO:FIRE ALRM ,GEN, C/C - ONLY CORNER STREET ALRM");

    doTest("T3",
        "L: 736 BLUE CREEK DR\nT: AUTOMATIC FIRE ALARM\nO: FRANCO RESIDENCE\nB:\nC1: JOHN GLENN BL\nC2: LITHUANICA LA\nX: FIRE ALRM--SMOKE DETECTOR--UNK WHERE----C/C-----PH#",
        "ADDR:736 BLUE CREEK DR",
        "CALL:AUTOMATIC FIRE ALARM",
        "NAME:FRANCO RESIDENCE",
        "X:JOHN GLENN BL & LITHUANICA LA",
        "INFO:FIRE ALRM--SMOKE DETECTOR--UNK WHERE----C/C-----PH#");

    doTest("T4",
        "L: 1271 FAIRPRT NINE MILE PT RD\nT: MVAIA - W/INJURIES\nO:\nB:\nC1: BAINBRIDGE LA\nC2: PLANK RD\nX: PRECAUTIONARY FOR CHEST PAIN DUE TO MVA",
        "ADDR:1271 FAIRPRT NINE MILE PT RD",
        "CALL:MVAIA - W/INJURIES",
        "X:BAINBRIDGE LA & PLANK RD",
        "INFO:PRECAUTIONARY FOR CHEST PAIN DUE TO MVA");
  }
  
  @Test
  public void testParser2() {

    doTest("T1",
        "A: FAIF BOX: 0176\nL: 2 LEWIS ST\nT: CARDIAC/RESP-NOT BREATHING\nO: \nB: \nC1: POTTER PL\nC2: FILKINS ST\nX: 46 YO/M NOT BREATHING -- COLD TO THE TOUCH",
        "BOX:0176",
        "ADDR:2 LEWIS ST",
        "CALL:CARDIAC/RESP-NOT BREATHING",
        "X:POTTER PL & FILKINS ST",
        "INFO:46 YO/M NOT BREATHING -- COLD TO THE TOUCH");

  }
  
  @Test
  public void testParser3() {

    doTest("T1",
        "L: 50 COTTAGE GROVE CI BOX: 0622\nT: CHIE - 6D2 : TRB BREATHING- DIFF SPEAKING            \nB: ROCH PRESB  PH:\nC1: BUFFALO RD\nC2: DEAD END\nX: 43 YO F PROB BREAT",
        "ADDR:50 COTTAGE GROVE CI",
        "MADDR:50 COTTAGE GROVE CIR",
        "BOX:0622",
        "CALL:CHIE - 6D2 : TRB BREATHING- DIFF SPEAKING",
        "PLACE:ROCH PRESB",
        "X:BUFFALO RD & DEAD END",
        "INFO:43 YO F PROB BREAT");
    
  }
  
  @Test
  public void testActive911() {

    doTest("T1",
        "(Scottsville Amb.) SCOE  B:0310 1  L:19 CHILI AV ,SCO - GROUP HOME T:6D4    X: MALE 50 TROUBLE BREATHING--CLAMMY AND FEVERISH--",
        "SRC:SCOE",
        "BOX:0310",
        "PRI:1",
        "ADDR:19 CHILI AV",
        "MADDR:19 CHILI AVE",
        "CITY:Scottsville",
        "PLACE:GROUP HOME",
        "CALL:6D4",
        "INFO:MALE 50 TROUBLE BREATHING--CLAMMY AND FEVERISH--");

    doTest("T2",
        "(Scottsville Amb.) SCOE  B:0320 2  L:521 NORTH RD ,SCO - KAYLEIGH STEELLMAN RES T:23C1   X: XFER FROM 211/CLR REQ AMB FOR 19 YO F INTOX, HX OF SUICIDAL TENDENCIES, V IOLENT, NOT ALERT, SOUNDED LIKE SHE WAS BREATHING ABNORMALLY PER 211, F H/U ON 211 ",
        "SRC:SCOE",
        "BOX:0320",
        "PRI:2",
        "ADDR:521 NORTH RD",
        "CITY:Scottsville",
        "PLACE:KAYLEIGH STEELLMAN RES",
        "CALL:23C1",
        "INFO:XFER FROM 211/CLR REQ AMB FOR 19 YO F INTOX, HX OF SUICIDAL TENDENCIES, V IOLENT, NOT ALERT, SOUNDED LIKE SHE WAS BREATHING ABNORMALLY PER 211, F H/U ON 211");

    doTest("T3",
        "SCOE  B:0450 1P L:851 SCOTTSVILLE MUMFORD RD ,WHE T:17D3   X: 71 YO HUSBAND HAS FALLEN / NOT ALERT",
        "SRC:SCOE",
        "BOX:0450",
        "PRI:1P",
        "ADDR:851 SCOTTSVILLE MUMFORD RD",
        "CITY:Wheatland",
        "CALL:17D3",
        "INFO:71 YO HUSBAND HAS FALLEN / NOT ALERT");

    doTest("T4",
        "SCOE B:0450 1P L:851 SCOTTSVILLE MUMFORD RD ,WHE T:17D3 X: 71 YO HUSBAND HAS FALLEN / NOT ALERT --",
        "SRC:SCOE",
        "BOX:0450",
        "PRI:1P",
        "ADDR:851 SCOTTSVILLE MUMFORD RD",
        "CITY:Wheatland",
        "CALL:17D3",
        "INFO:71 YO HUSBAND HAS FALLEN / NOT ALERT --");

    doTest("T5",
        "SCOE B:0330 4 L:2 RACE ST ,SCO T:26A1 X: 89 YO F/BLADDER INFECTION /PAIN IN URINATION AND BACK PAIN",
        "SRC:SCOE",
        "BOX:0330",
        "PRI:4",
        "ADDR:2 RACE ST",
        "CITY:Scottsville",
        "CALL:26A1",
        "INFO:89 YO F/BLADDER INFECTION /PAIN IN URINATION AND BACK PAIN");

    doTest("T6",
        "SCOE B:0330 1 L:385 SCOTTSVILLE MUMFORD RD ,SCO -- SCOF T:TEST X: TESTING SCOE'S TONES, PLEASE DISREGARD AND HAVE A GOOD NIGHT! --",
        "SRC:SCOE",
        "BOX:0330",
        "PRI:1",
        "ADDR:385 SCOTTSVILLE MUMFORD RD",
        "CITY:Scottsville",
        "PLACE:SCOF",
        "CALL:TEST",
        "INFO:TESTING SCOE'S TONES, PLEASE DISREGARD AND HAVE A GOOD NIGHT! --");

    doTest("T7",
        "SCOE B:0310 3 L:724 NORTH RD ,SCO - ROOM 6 T:RBCST X: ,NEED A DRIVER OR A MEDIC TO COMPLETE THE CREW W/ 4M16 - GATES A4569 TAKING THE ALS PORTION --",
        "SRC:SCOE",
        "BOX:0310",
        "PRI:3",
        "ADDR:724 NORTH RD",
        "CITY:Scottsville",
        "PLACE:ROOM 6",
        "CALL:RBCST",
        "INFO:,NEED A DRIVER OR A MEDIC TO COMPLETE THE CREW W/ 4M16 - GATES A4569 TAKING THE ALS PORTION --");

    doTest("T8",
        "SCOE B:0310 1 L:724 NORTH RD ,SCO - ROOM 6 T:6D4 X: 48 YO M - TRBL BREATHING - IS CLAMMY - ALERT --",
        "SRC:SCOE",
        "BOX:0310",
        "PRI:1",
        "ADDR:724 NORTH RD",
        "CITY:Scottsville",
        "PLACE:ROOM 6",
        "CALL:6D4",
        "INFO:48 YO M - TRBL BREATHING - IS CLAMMY - ALERT --");

    doTest("T9",
        "SCOE B:0330 1 L:MAIN ST/RT 251 ,SCO T:29D2N X: CAR TOOK OFF OUT OF COUNTY PARK -- EJECTED ONE OF THE PPL OUT OF THE CAR -- NOW TRYING TO LOAD HER INTO CAR AND LEAVE LOOK TO BE INTOX -- BLU CAR POSS SUBU IMPREZA 4D COMPACT",
        "SRC:SCOE",
        "BOX:0330",
        "PRI:1",
        "ADDR:MAIN ST & RT 251",
        "CITY:Scottsville",
        "CALL:29D2N",
        "INFO:CAR TOOK OFF OUT OF COUNTY PARK -- EJECTED ONE OF THE PPL OUT OF THE CAR -- NOW TRYING TO LOAD HER INTO CAR AND LEAVE LOOK TO BE INTOX -- BLU CAR POSS SUBU IMPREZA 4D COMPACT");

    doTest("T10",
        "SCOE B:0320 2 L:NORTH RD/ROCHESTER ST ,SCO - MOBILE GAS STATION T:EMSA X: ODOR OF NATURAL GAS INSIDE THE BLDG - M IN BACK COOKING --",
        "SRC:SCOE",
        "BOX:0320",
        "PRI:2",
        "ADDR:NORTH RD & ROCHESTER ST",
        "CITY:Scottsville",
        "PLACE:MOBILE GAS STATION",
        "CALL:EMSA",
        "INFO:ODOR OF NATURAL GAS INSIDE THE BLDG - M IN BACK COOKING --");

    doTest("T11",
        "SCOE B:0320 4 L:8 GENESEE ST ,SCO - JOHN HOLTZ RES T:26A2 X: 68 YO MALE - SHAKING - POSS HIGH FEVER - COMPS ETA 15 MINS TO LOC --",
        "SRC:SCOE",
        "BOX:0320",
        "PRI:4",
        "ADDR:8 GENESEE ST",
        "CITY:Scottsville",
        "PLACE:JOHN HOLTZ RES",
        "CALL:26A2",
        "INFO:68 YO MALE - SHAKING - POSS HIGH FEVER - COMPS ETA 15 MINS TO LOC --");

    doTest("T12",
        "SCOE B:0320 4 L:498 NORTH RD ,SCO T:7A1 X: 67 YO MALE RADIATOR SPLASHED ANTI FREEZE ON HIM /BURNING ON HIS CHEST AND FACE --",
        "SRC:SCOE",
        "BOX:0320",
        "PRI:4",
        "ADDR:498 NORTH RD",
        "CITY:Scottsville",
        "CALL:7A1",
        "INFO:67 YO MALE RADIATOR SPLASHED ANTI FREEZE ON HIM /BURNING ON HIS CHEST AND FACE --");

  }
  
  public static void main(String[] args) {
    new NYMonroeCountyWebsterParserTest().generateTests("T1");
  }
}