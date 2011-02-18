package net.anei.cadpage.parsers.FL;

import net.anei.cadpage.parsers.BaseParserTest;
import net.anei.cadpage.parsers.FL.FLLeeCountyParser;

import org.junit.Test;


public class FLLeeCountyParserTest extends BaseParserTest {
  
  public FLLeeCountyParserTest() {
    setParser(new FLLeeCountyParser(), "LEE COUNTY", "FL");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "10116004231 TYP: SEIZURES AD: 1051 FIFTH ST CTY: FT MYERS BEACH LOC: LIGHTHOUSE RESORT TIKI BAR CN: VERIZON WIRELESS CMT1: CONVULSIONS / ZURES CMT2: Original Location : LIGHTHOUSE RESORT TIKI BAR TIME: 16:34 FBFBA32 FBFBE31 XST: 1 MATANZAS BRIDGE XST2: 1067 FOURTH ST",
        "ID:10116004231",
        "CALL:SEIZURES",
        "ADDR:1051 FIFTH ST",
        "CITY:FT MYERS BEACH",
        "PLACE:LIGHTHOUSE RESORT TIKI BAR",
        "NAME:VERIZON WIRELESS",
        "INFO:CONVULSIONS / ZURES / Original Location : LIGHTHOUSE RESORT TIKI BAR",
        "X:1 MATANZAS BRIDGE & 1067 FOURTH ST");

    doTest("T2",
        "10116004116 TYP: BREATHING PROBLEM AD: 11610 DOGWOOD LN CTY: FT MYERS BEACH LOC: BAYSIDE ESTATES CN: KESTLER, BARBARA CMT1: BREATHING PRO MS CMT2: Original Location : BAYSIDE ESTATES TIME: 08:35 FBFBA32 FBFBE33XST: 17681 EGLANTINE LN",
        "ID:10116004116",
        "CALL:BREATHING PROBLEM",
        "ADDR:11610 DOGWOOD LN",
        "CITY:FT MYERS BEACH",
        "PLACE:BAYSIDE ESTATES",
        "NAME:KESTLER, BARBARA",
        "INFO:BREATHING PRO MS / Original Location : BAYSIDE ESTATES");

    doTest("T3",
        "10118004821 TYP: CHEST PAINS AD: 885 ARAPAHO TRL CTY: FORT MYERS LOC: INDIAN CREEK CN: HANKS, ROBIN & DEAN CMT1: CHEST PAIN (NON TRAUMATI CMT2: Original Location : INDIAN CREEK TIME: 18:29 FBFBA32 FBFBE33 XST: 1205 SHASTA TRL XST2: 1211 SHASTA TRL",
        "ID:10118004821",
        "CALL:CHEST PAINS",
        "ADDR:885 ARAPAHO TRL",
        "CITY:FORT MYERS",
        "PLACE:INDIAN CREEK",
        "NAME:HANKS, ROBIN & DEAN",
        "INFO:CHEST PAIN (NON TRAUMATI / Original Location : INDIAN CREEK",
        "X:1205 SHASTA TRL & 1211 SHASTA TRL");

    doTest("T4",
        "NC: FCW110118004875 TYP: GENERAL ILLNESS APT: 114 AD: 17761 SAN CARLOS BLVD CTY: FT MYERS BEACH LOC: TAHITIAN INN CN: LCSO - CHELSEA CMT1: SICK PERSON (SPECIFIC DIAGNOSIS) CMT2: Original Location : TAHITIAN INN TIME: 22:56 FBFBA32 FBFBE33 XST: 17605 BROADWAY AVE XST2: 11891 IS",
        "ID:FCW110118004875",
        "CALL:GENERAL ILLNESS",
        "APT:114",
        "ADDR:17761 SAN CARLOS BLVD",
        "CITY:FT MYERS BEACH",
        "PLACE:TAHITIAN INN",
        "NAME:LCSO - CHELSEA",
        "INFO:SICK PERSON (SPECIFIC DIAGNOSIS) / Original Location : TAHITIAN INN",
        "X:17605 BROADWAY AVE & 11891 IS");

    doTest("T5",
        "10119005036 TYP: FALL AD: 1131 FIRST ST CTY: FT MYERS BEACH LOC: NERVOUS NELLIE'S WATERFRONT CN: NERVOUS NELLIE'S WAT CMT1: 1) MPDS TYPE",
        "ID:10119005036",
        "CALL:FALL",
        "ADDR:1131 FIRST ST",
        "CITY:FT MYERS BEACH",
        "PLACE:NERVOUS NELLIE'S WATERFRONT",
        "NAME:NERVOUS NELLIE'S WAT",
        "INFO:1) MPDS TYPE");

    doTest("T6",
        "10119005092 TYP: OD / POISONING AD: 71 AVENUE C CTY: FT MYERS BEACH LOC: PKG LOT CN: AT&T MOBILITY CMT1: OVERDOSE / POISONING (INGESTION) CMT2: Original Location : PKG LOT TIME: 17:27 FBFBA33 FBFBE31 XST2: 1511 ESTERO BLVD",
        "ID:10119005092",
        "CALL:OD / POISONING",
        "ADDR:71 AVENUE C",
        "CITY:FT MYERS BEACH",
        "PLACE:PKG LOT",
        "NAME:AT&T MOBILITY",
        "INFO:OVERDOSE / POISONING (INGESTION) / Original Location : PKG LOT",
        "X:1511 ESTERO BLVD");

    doTest("T7",
        "10119005122 TYP: HEMORRHAGE APT: 535 AD: 8350 ESTERO BLVD CTY: FT MYERS BEACH LOC: CARLOS POINT CONDOS CN: VERIZON WIRELESS CMT1: HEMORRH / LACERATIONS CMT2: Original Location : CARLOS POINT CONDOS TIME: 20:38FBFBA33 FBFBTK33 XST: 99 TARPON RD XST2: 111 ESTRELLITA DR",
        "ID:10119005122",
        "CALL:HEMORRHAGE",
        "APT:535",
        "ADDR:8350 ESTERO BLVD",
        "CITY:FT MYERS BEACH",
        "PLACE:CARLOS POINT CONDOS",
        "NAME:VERIZON WIRELESS",
        "INFO:HEMORRH / LACERATIONS / Original Location : CARLOS POINT CONDOS",
        "X:99 TARPON RD & 111 ESTRELLITA DR");

    doTest("T8",
        "10119005132 TYP: FAINTING APT: 4 AD: 2370 ESTERO BLVD CTY: FT MYERS BEACH CN: METRO PCS CMT1: 1) MPDS TYPE (2?) CMT2: 38 YEAR OLD FEMALE SCIOUS BREATHING UNCONSCIOUS / FAINTING (NEAR) TIME: 21:51 FBFBA32 FBFBE31 XST: 111 MANGO ST XST2: 81 MANGO ST",
        "ID:10119005132",
        "CALL:FAINTING",
        "APT:4",
        "ADDR:2370 ESTERO BLVD",
        "CITY:FT MYERS BEACH",
        "NAME:METRO PCS",
        "INFO:1) MPDS TYPE (2?) / 38 YEAR OLD FEMALE SCIOUS BREATHING UNCONSCIOUS / FAINTING (NEAR)",
        "X:111 MANGO ST & 81 MANGO ST");

    doTest("T9",
        "10120005323 TYP: FALL AD: 3001 ESTERO BLVD CTY: FT MYERS BEACH LOC: RED COCONUT RV PARK CN: VERIZON WIRELESS CMT1: FALLS CMT2: Original L RED COCONUT RV PARK TIME: 15:54 FBFBA33 FBFBE31 XST: 1 GULF VIEW COLONY XST2: 930 SAND DOLLAR DR",
        "ID:10120005323",
        "CALL:FALL",
        "ADDR:3001 ESTERO BLVD",
        "CITY:FT MYERS BEACH",
        "PLACE:RED COCONUT RV PARK",
        "NAME:VERIZON WIRELESS",
        "INFO:FALLS / Original L RED COCONUT RV PARK",
        "X:1 GULF VIEW COLONY & 930 SAND DOLLAR DR");

    doTest("T10",
        "10120005404 TYP: CHEST PAINS AD: 17861 PEPPARD DR CTY: FT MYERS BEACH CN: AT&T MOBILITY CMT1: CHEST PAIN (NON TRAUMATIC) CMT2: 70 YEAR OL EMALE CONSCIOUS BREATHING CHEST PAIN (NON-TRAUMATIC) TIME: 20:33 FBFBA32FBFBE33 XST: 11211 AZALEA LN",
        "ID:10120005404",
        "CALL:CHEST PAINS",
        "ADDR:17861 PEPPARD DR",
        "CITY:FT MYERS BEACH",
        "NAME:AT&T MOBILITY",
        "INFO:CHEST PAIN (NON TRAUMATIC) / 70 YEAR OL EMALE CONSCIOUS BREATHING CHEST PAIN (NON-TRAUMATIC)",
        "X:11211 AZALEA LN");
  }
  
  public static void main(String[] args) {
    new FLLeeCountyParserTest().generateTests("T1");
  }
}
  