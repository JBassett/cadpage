package net.anei.cadpage.parsers.OH;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class OHMedinaCountyParserTest extends BaseParserTest {
  
  public OHMedinaCountyParserTest() {
    setParser(new OHMedinaCountyParser(), "MEDINA COUNTY", "OH");
  }
  
  @Test
  public void testParsaer1() {

    doTest("T1",
        "(lCAD) [!] EMS//6387 CARSTEN RD MEDINA BETWEEN CHATHAM RD / COON CLUB RD//FEMALE FALL VIC TIM NEEDS LIFT ASSIST NO INJURY RECENTLY OUT OF THE HOSPITAL",
        "CALL:EMS",
        "ADDR:6387 CARSTEN RD",
        "CITY:MEDINA",
        "X:CHATHAM RD / COON CLUB RD",
        "INFO:FEMALE FALL VIC TIM NEEDS LIFT ASSIST NO INJURY RECENTLY OUT OF THE HOSPITAL");
  }
  
  @Test
  public void testParser2() {

    doTest("T1",
        "[gCAD]  [!] - NATURE: 2 //WB MEDINA RD / MEDINA L INE RD MEDINA//CALLBK=041.135838-081 .678843 1 VEH ON THE NORTH SIDE OF THE ROAD NO ONE AROUND IT",
        "CALL:- NATURE: 2",
        "ADDR:WB MEDINA RD & MEDINA L INE RD",
        "MADDR:MEDINA RD & MEDINA L INE RD",
        "CITY:MEDINA",
        "INFO:CALLBK=041.135838-081 .678843 1 VEH ON THE NORTH SIDE OF THE ROAD NO ONE AROUND IT");

    doTest("T2",
        "[gCAD]  [!] MVA WITH INJURIES//2389 MEDINA RD MEDINA BETWEEN BEACH RD / BONETA RD//CALLBK=041.136428-081.773643 WB CAR ROLL OVER CAR IS ON ITS TOP CALLER ADV SHE DONT SEE ANYONE IN THE VEH",
        "CALL:MVA WITH INJURIES",
        "ADDR:2389 MEDINA RD",
        "CITY:MEDINA",
        "X:BEACH RD / BONETA RD",
        "INFO:CALLBK=041.136428-081.773643 WB CAR ROLL OVER CAR IS ON ITS TOP CALLER ADV SHE DONT SEE ANYONE IN THE VEH");

    doTest("T3",
        "[gCAD]  [!] EMS//200 GRANGER RD MEDINA BETWEEN N MEDINA LINE RD / DRUERIE LN//APT 7 FEMAL E FALLEN OUT OF BED 87 YR FEMALE NOT INJURED",
        "CALL:EMS",
        "ADDR:200 GRANGER RD",
        "CITY:MEDINA",
        "X:N MEDINA LINE RD / DRUERIE LN",
        "INFO:APT 7 FEMAL E FALLEN OUT OF BED 87 YR FEMALE NOT INJURED");

    doTest("T4",
        "[gCAD]  [!] EMS//4519 RIDGE RD WADSWORTH BETWEEN GRANGER RD / MEDINA RD//87 YR OLD FEMALE FALL VICTIM BACK & SHOULDER INJURIES",
        "CALL:EMS",
        "ADDR:4519 RIDGE RD",
        "CITY:WADSWORTH",
        "X:GRANGER RD / MEDINA RD",
        "INFO:87 YR OLD FEMALE FALL VICTIM BACK & SHOULDER INJURIES");

    doTest("T5",
        "[gCAD]  [!] MVA WITH INJURIES//2900 STATE RD MEDINA B ETWEEN LEDGE RD / REMSEN RD//CALLBK=041.192775-081.710042 IN THE SB LANE PICK UP TRUCK ON ITS SIDE MALE OUTSIDE THE TRUCK",
        "CALL:MVA WITH INJURIES",
        "ADDR:2900 STATE RD",
        "CITY:MEDINA",
        "X:LEDGE RD / REMSEN RD",
        "INFO:CALLBK=041.192775-081.710042 IN THE SB LANE PICK UP TRUCK ON ITS SIDE MALE OUTSIDE THE TRUCK");

    doTest("T6",
        "[gCAD]  [!] MVA WITH INJURIES//3275 STATE RD MEDINA B ETWEEN REMSEN RD / WILBUR RD//+041.187100-081.71163 0 MVA MINOR INJ",
        "CALL:MVA WITH INJURIES",
        "ADDR:3275 STATE RD",
        "CITY:MEDINA",
        "X:REMSEN RD / WILBUR RD",
        "INFO:+041.187100-081.71163 0 MVA MINOR INJ");

    doTest("T7",
        "[gCAD]  [!] EMS//171 GRANGER RD // 1 47 MEDINA BETWEEN N MEDINA LINE RD / DRUERIE LN//+ 041.134625-081.703584 87 YR FEMALE CONFUSED, NOT FEELING WE LL",
        "CALL:EMS",
        "ADDR:171 GRANGER RD",
        "INFO:1 47 MEDINA BETWEEN N MEDINA LINE RD / DRUERIE LN / + 041.134625-081.703584 87 YR FEMALE CONFUSED, NOT FEELING WE LL");

    doTest("T8",
        "[gCAD]  [!] EMS//861 MEDINA RD MEDINA B ETWEEN STATE RD / BAMBECK RD//75-4 CALLED IN ADVISE D THEY RECXEIVED A CALL ON STATION FOR A TRANSPORT REF AN IN FECTION",
        "CALL:EMS",
        "ADDR:861 MEDINA RD",
        "CITY:MEDINA",
        "X:STATE RD / BAMBECK RD",
        "INFO:75-4 CALLED IN ADVISE D THEY RECXEIVED A CALL ON STATION FOR A TRANSPORT REF AN IN FECTION");
  }
  
  public static void main(String[] args) {
    new OHMedinaCountyParserTest().generateTests("T1");
  }
}