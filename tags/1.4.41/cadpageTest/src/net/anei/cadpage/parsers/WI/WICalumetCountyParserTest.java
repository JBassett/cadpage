package net.anei.cadpage.parsers.WI;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;


public class WICalumetCountyParserTest extends BaseParserTest {
  
  public WICalumetCountyParserTest() {
    setParser(new WICalumetCountyParser(), "CALUMET COUNTY", "WI");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "HAFR       PRI: 1 INC: FHA110114000008 TYP: RESCUE ADVANCED LIFE AD: N8016 PIGEON RD CTY: CMSV CN: BRUSE CMT1: 11 MONTH OLD INFANT WITH SOMETHING STUCK I",
        "SRC:HAFR",
        "PRI:1",
        "ID:FHA110114000008",
        "CALL:RESCUE ADVANCED LIFE",
        "ADDR:N 8016 PIGEON RD",
        "CITY:SHERWOOD",
        "NAME:BRUSE",
        "INFO:11 MONTH OLD INFANT WITH SOMETHING STUCK I");

    doTest("T2",
        "BUFD       PRI: 1 INC: FBU110115000010 TYP: ACCIDENT - C AD: CTY TK CE&OUTAGAMIE RD CTY: BUCT CN: KARNER,BEN CMT1: ***IF 2191 OR 2192 IS RESPONDING ALSO",
        "SRC:BUFD",
        "PRI:1",
        "ID:FBU110115000010",
        "CALL:ACCIDENT - C",
        "ADDR:COUNTY RD CE & OUTAGAMIE RD",
        "CITY:BUCHANAN",
        "NAME:KARNER,BEN",
        "INFO:***IF 2191 OR 2192 IS RESPONDING ALSO");

    doTest("T3",
        "HAFR       PRI: 1 INC: FHA110116000010 TYP: RESCUE ADVANCED LIFE AD: W5872 EASTER LILY DR CTY: CMTH CN: SWEERE, SHANNON CMT1: 13YOA FEMALE BAD",
        "SRC:HAFR",
        "PRI:1",
        "ID:FHA110116000010",
        "CALL:RESCUE ADVANCED LIFE",
        "ADDR:W 5872 EASTER LILY DR",
        "CITY:HARRISON",
        "NAME:SWEERE, SHANNON",
        "INFO:13YOA FEMALE BAD");

    doTest("T4",
        "HAFR       PRI: 1 INC: FHA110123000017 TYP: VEHICLE ACCIDENT AD: W7170 HWY 114 CTY: CMTH CN: VANDEHEY, FRANCIS CMT1: **POINT TO POINT TO OUSO TO DISPATCH",
        "SRC:HAFR",
        "PRI:1",
        "ID:FHA110123000017",
        "CALL:VEHICLE ACCIDENT",
        "ADDR:W 7170 HWY 114",
        "CITY:HARRISON",
        "NAME:VANDEHEY, FRANCIS",
        "INFO:**POINT TO POINT TO OUSO TO DISPATCH");

    doTest("T5",
        "HAFR       PRI: 1 INC: FHA110123000016 TYP: RESCUE ADVANCED LIFE AD: N9664 EMERALD LN CTY: CMTH CN: HELLINGSON KATIE CMT1: 60YOA FEMALE DIFFICULTY BREATH",
        "SRC:HAFR",
        "PRI:1",
        "ID:FHA110123000016",
        "CALL:RESCUE ADVANCED LIFE",
        "ADDR:N 9664 EMERALD LN",
        "CITY:HARRISON",
        "NAME:HELLINGSON KATIE",
        "INFO:60YOA FEMALE DIFFICULTY BREATH");

    doTest("T6",
        "HAFR       PRI: 1 INC: FHA110125000019 TYP: RESCUE ADVANCED LIFE AD: N9085 N COOP RD CTY: CMTH LOC: WOODLAND SCHOOL CN: WOODLAND SCHOOL CMT1: Original Lo",
        "SRC:HAFR",
        "PRI:1",
        "ID:FHA110125000019",
        "CALL:RESCUE ADVANCED LIFE",
        "ADDR:N 9085 N COOP RD",
        "CITY:HARRISON",
        "PLACE:WOODLAND SCHOOL",
        "NAME:WOODLAND SCHOOL",
        "INFO:Original Lo");

    doTest("T7",
        "HAFR       PRI: 1 INC: FHA110126000021 TYP: RESCUE ADVANCED LIFE AD: N9114 NOE RD CTY: CMTH CN: COLE, KATHY CMT1: HAND INJURY TIME: 19:22 GAGCA1 HAM321 X",
        "SRC:HAFR",
        "PRI:1",
        "ID:FHA110126000021",
        "CALL:RESCUE ADVANCED LIFE",
        "ADDR:N 9114 NOE RD",
        "CITY:HARRISON",
        "NAME:COLE, KATHY",
        "INFO:HAND INJURY");

    doTest("T8",
        "HAFR       PRI: 1 INC: FHA110117000011 TYP: RESCUE ADVANCED LIFE AD: CTY TK KK&NOE RD CTY: CMTH CN: TAUS,ANNETTE CMT1: 44 YR OLD FEMALE WITH CHEST PAINS",
        "SRC:HAFR",
        "PRI:1",
        "ID:FHA110117000011",
        "CALL:RESCUE ADVANCED LIFE",
        "ADDR:COUNTY RD KK & NOE RD",
        "CITY:HARRISON",
        "NAME:TAUS,ANNETTE",
        "INFO:44 YR OLD FEMALE WITH CHEST PAINS");

    doTest("T9",
        "HAFR       PRI: 1 INC: FHA110127000023 TYP: STRUCTURE FIRE AD: W2825 EMMONS CTY: BUCT CN: BUCHANAN FIRE CMT1: **POINT TO POINT TO OUSO TO DISPATCH BUCHAN",
        "SRC:HAFR",
        "PRI:1",
        "ID:FHA110127000023",
        "CALL:STRUCTURE FIRE",
        "ADDR:W 2825 EMMONS",
        "CITY:BUCHANAN",
        "NAME:BUCHANAN FIRE",
        "INFO:**POINT TO POINT TO OUSO TO DISPATCH BUCHAN");

    doTest("T10",
        "HAFR       PRI: 1 INC: FHA110130000024 TYP: RESCUE ADVANCED LIFE AD: W5095 GOLF COURSE RD CTY: CMSV LOC: HIGH CLIFF SUPPER CL CMT1: Original Location : H",
        "SRC:HAFR",
        "PRI:1",
        "ID:FHA110130000024",
        "CALL:RESCUE ADVANCED LIFE",
        "ADDR:W 5095 GOLF COURSE RD",
        "CITY:SHERWOOD",
        "PLACE:HIGH CLIFF SUPPER CL",
        "INFO:Original Location : H");

    doTest("T11",
        "HAFR       PRI: 1 INC: FHA110224000047 TYP: RESCUE ADVANCED LIFE AD: W6013 BLAZING STAR DR CTY: CMTH CN: WILLIAMS-SERVANT GRE CMT1: 35 FEMALE SUBJECT VAG",
        "SRC:HAFR",
        "PRI:1",
        "ID:FHA110224000047",
        "CALL:RESCUE ADVANCED LIFE",
        "ADDR:W 6013 BLAZING STAR DR",
        "CITY:HARRISON",
        "NAME:WILLIAMS-SERVANT GRE",
        "INFO:35 FEMALE SUBJECT VAG");
        
  }
  
  @Test
  public void testBrownCountyParser() {

    doTest("T1",
        "HOFD PRI: 1 INC: FHO110429000014 TYP: VEGETATION FIRE AD: 7571 CTH D CTY: HOLL MAP: 9BD CN: ED CARROL CMT1: WITHIN 10 OF BUILDING, CHANGE TO STRUCTURE CMT2: C",
        "SRC:HOFD",
        "PRI:1",
        "ID:FHO110429000014",
        "CALL:VEGETATION FIRE",
        "ADDR:7571 CTH D",
        "CITY:HOLLAND",
        "MAP:9BD",
        "NAME:ED CARROL",
        "INFO:WITHIN 10 OF BUILDING, CHANGE TO STRUCTURE / C");

    doTest("T2",
        "HBFD PRI: 1 INC: FHB110509000117 TYP: FIRE ALARM AD: 710 BOW BELLS RD CTY: HOBA MAP: 10Y CN: CEC, 2964 CMT1: RESIDENTIAL FIRE ALARM...UNK WHERE COMING FROM... CMT2: ATTEMTING KEYHOLDERS.. TIME: 15:18 HBHBFD XST: 661 SILVER CREEK DR XS :30 T2: 3169 FERNDELL ACRES DR 675",
        "SRC:HBFD",
        "PRI:1",
        "ID:FHB110509000117",
        "CALL:FIRE ALARM",
        "ADDR:710 BOW BELLS RD",
        "CITY:HOBART",
        "MAP:10Y",
        "NAME:CEC, 2964",
        "INFO:RESIDENTIAL FIRE ALARM...UNK WHERE COMING FROM... / ATTEMTING KEYHOLDERS..",
        "X:661 SILVER CREEK DR XS :30 T2: 3169 FERNDELL ACRES DR 675");

    doTest("T3",
        "HBFD PRI: 1 INC: FHB110407000084 TYP: EXTRICATION RESCUE AD: STH 29/32&SUNLITE DR CTY: HOBA MAP: 12Q CN: SPRINT CMT1: INCIDENT CLONED FROM PARENT: LBR110407013154 CMT2: Original Date/Time for Comment: 11/04/07 07:51 TIME: 08:16 HBHBFD <:1012",
        "SRC:HBFD",
        "PRI:1",
        "ID:FHB110407000084",
        "CALL:EXTRICATION RESCUE",
        "ADDR:STH 29 & 32 & SUNLITE DR",
        "CITY:HOBART",
        "MAP:12Q",
        "NAME:SPRINT",
        "INFO:INCIDENT CLONED FROM PARENT: LBR110407013154 / Original Date/Time for Comment: 11/04/07 07:51");

    doTest("T4",
        "HBFD PRI: 1 INC: FHB110406000083 TYP: STRUCTURE FIRE AD: 4580 CHOCTAW TR CTY: HOBA MAP: 14R CN: CELLCOM CMT1: HEARD AND EXPLOSION AND NOW HOUSE IS ON FIRE - FULLY INVOLVED CMT2: Child Inc LHL110406001545 UPDATE PriUnt to HL/HL440 TIME <5= : 18:01 HBHBFD CRAM6021 HBHB1R XST: 4550 CROW CT XST2: 4599 CHOCTAW CT & NAVAJO T 349",
        "SRC:HBFD",
        "PRI:1",
        "ID:FHB110406000083",
        "CALL:STRUCTURE FIRE",
        "ADDR:4580 CHOCTAW TR",
        "CITY:HOBART",
        "MAP:14R",
        "NAME:CELLCOM",
        "INFO:HEARD AND EXPLOSION AND NOW HOUSE IS ON FIRE - FULLY INVOLVED / Child Inc LHL110406001545 UPDATE PriUnt to HL/HL440 TIME <5= : 18:01 HBHBFD CRAM6021 HBHB1R",
        "X:4550 CROW CT & 4599 CHOCTAW CT & NAVAJO T 349");

    doTest("T5",
        "HBFD PRI: 1 INC: FHB110407000084 TYP: EXTRICATION RESCUE AD: STH 29/32&SUNLITE DR CTY: HOBA MAP: 12Q CN: SPRINT CMT1: INCIDENT CLONED FROM PARENT: LBR110407013154 CMT2: Original Date/Time for Comment: 11/04/07 07:51 TIME: 08:16 HBHBFD <:1012",
        "SRC:HBFD",
        "PRI:1",
        "ID:FHB110407000084",
        "CALL:EXTRICATION RESCUE",
        "ADDR:STH 29 & 32 & SUNLITE DR",
        "CITY:HOBART",
        "MAP:12Q",
        "NAME:SPRINT",
        "INFO:INCIDENT CLONED FROM PARENT: LBR110407013154 / Original Date/Time for Comment: 11/04/07 07:51");

    doTest("T6",
        "HBFD PRI: 1 INC: FHB110515000121 TYP: VEGETATION FIRE AD: FERNANDO DR&PACKERLAND DR CTY: ASHW MAP: 14AE CN: JODY CMT1: WITHIN 10 OF BUILDING, CHANGE TO STRUCTURE CMT2: INCIDENT CLONED FROM PARENT: FAF110515000686 TIME: 01:27 HBEN1711 :74 HBEN1712 21:",
        "SRC:HBFD",
        "PRI:1",
        "ID:FHB110515000121",
        "CALL:VEGETATION FIRE",
        "ADDR:FERNANDO DR & PACKERLAND DR",
        "CITY:ASHWAUBENON",
        "MAP:14AE",
        "NAME:JODY",
        "INFO:WITHIN 10 OF BUILDING, CHANGE TO STRUCTURE / INCIDENT CLONED FROM PARENT: FAF110515000686");
    
  }
  
  public static void main(String[] args) {
    new WICalumetCountyParserTest().generateTests("T6");
  }
}