package net.anei.cadpage.parsers.NC;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;

/* 
Surry County, NC
Contact: Active911

[] SC911::Call #120627-0079 [Address] 112 SOLO LN [X St] PEACE HAVEN CIR TO DEADEND [Type] ASST PATIENT\n
[] SC911::Call #120627-0206 [Address] 587 N FRANKLIN RD [X St] TO [Type] CARDIAC\n
[] SC911::Call #120627-0221 [Address] 297 MAPLE HOLLOW RD [X St] MARK ST TO LYNN ST [Type] DIABETIC D\n
[] SC911::Call #120627-0235 [Address] 1872 W PINE ST [X St] TRANQUIL LN TO CEDAR KNOLL DR [Type] ASST PATIENT\n
[] SC911::Call #120627-0263 [Address] MAPLE DR // OLD TOAST RD [X St] OLD TOAST RD TO DEADEND [Type] UNKNOWN PROB\n
[] SC911::Call #120628-0145 [Address] 1766 S ANDY GRIFFITH PKWY [X St] CITY LIMITS TO OLD BUCK SHOALS RD [Type] FIRE ALARM\n
[] SC911::Call #120628-0294 [Address] 1161 W LEBANON ST [X St] PAIGE ST TO HADLEY RD [Type] COMM SERVICE\n

Agency name: Franklin VFD - Medical Location: Toast, NC 
Sender: SC911:@co.surry.nc.us
  
SC911::Call #120707-0414 [Address] 367 CEDAR RIDGE RD [X St] PIPERS GAP RD TO DEADEND [Type] ASSAULT D
SC911::Call #120709-0218 [Address] 192 LOWER PARK LN [X St] MID LN TO DEADEND [Type] CVA D
SC911::Call #120710-0221 [Address] 426 N FRANKLIN RD [X St] TO [Type] RESP DIS D
SC911::Call #120712-0107 [Address] 1872 PIPERS GAP RD [X St] MILLER RD TO PHANTOM LN [Type] ACCIDENT PI
SC911::Call #120712-0347 [Address] 223 BLUEMONT RD [X St] HIGHLAND DR TO S FRANKLIN RD [Type] FALL
SC911::Call #120713-0124 [Address] 201 HUNTINGTON CREEK LN [X St] IMOGENE CH RD TO DEADEND [Type] UNRESPONSIVE
SC911::Call #120713-0140 [Address] 227 AIRVIEW DR [X St] SUMMIT DR TO DEADEND [Type] ASSIST AGNCY
SC911::Call #120713-0165 [Address] 227 AIRVIEW DR [X St] SUMMIT DR TO DEADEND [Type] ASSIST AGNCY
SC911::Call #120713-0208 [Address] 142 BOURBON TR [X St] CAROLINA RD/DUKE RD TO DEADEND [Type] CHOKE
SC911::Call #120713-0222 [Address] 242 DUDLEY AVE [X St] DUDLEY AVE TO DEADEND [Type] FALL D
SC911::Call #120713-0222 [Address] 242 DUDLEY AVE [X St] DUDLEY AVE TO DEADEND [Type] FALL D
SC911::Call #120714-0040 [Address] 149 LINWOOD DR [X St] S FRANKLIN RD TO GREENWAY DR [Type] SUICIDE
SC911::Call #120714-0113 [Address] 150 ELK COURT DR [X St] N BRIDGE ST TO HWY 268 BY PASS [Type] LIFELINE
SC911::Call #120714-0116 [Address] 108 DOGWOOD DR [X St] ROSEWOOD DR TO LONG ST [Type] SICK D
SC911::Call #120715-0211 [Address] 194 OAK RIDGE DR [X St] MAPLE HOLLOW RD TO DEADEND [Type] CARDIAC D
SC911::Call #120715-0237 [Address] 481 WHITE PINES COUNTRY CLUB RD [X St] N ANDY GRIFFITH PKWY TO VA STATE LINE [Type] ASST PATIENT
SC911::Call #120715-0325 [Address] 338 S FRANKLIN RD [X St] CALVARY DR TO LONG ST [Type] HEMRGE D
SC911::Call #120716-0179 [Address] 227 AIRVIEW DR [X St] SUMMIT DR TO DEADEND [Type] TRANSPORT
SC911::Call #120716-0280 [Address] 110 DUTCHMANS CT [X St] JOHNSON RIDGE RD TO DEADEND [Type] TRANSPORT
SC911::Call #120716-0299 [Address] 481 WHITE PINES COUNTRY CLUB RD [X St] N ANDY GRIFFITH PKWY TO VA STATE LINE [Type] UNRSPVE D
SC911::Call #120716-0529 [Address] 2013 SPARGER RD [X St] BEAMER RD TO GOINS RD [Type] ACCIDENT PI
SC911::Call #120718-0172 [Address] 112 SOLO LN [X St] PEACE HAVEN CIR TO DEADEND [Type] CARDIAC D
SC911::Call #120718-0206 [Address] 1872 W PINE ST [X St] TRANQUIL LN TO CEDAR KNOLL DR [Type] ASST PATIENT

Contact: Active911
Agency name: Four Way VFD-Medical Location: Mount Airy, NC
Sender: SC911:@co.surry.nc.us 

SC911::=121008-0404 [Address] 100 MCBRIDE RD [X St] E PINE ST TO INTRIGUE LN [Type] TEST
SC911::=121008-0534 [Address] 182 ELEANOR AVE [X St] GAYLON ST TO DEADEND [Type] RESP DIS C
SC911::=121011-0174 [Address] 2692 WARDS GAP RD [X St] GREENHILL RD TO BEYOND CROSSINGHAM RD [Type] ABD PAIN C
SC911::=121011-0416 [Address] 104 SUMMER LN [X St] AUTUMN LN TO DEADEND [Type] ABD PAIN C
SC911::=121012-0031 [Address] 761 MCBRIDE RD [X St] MELISSA DR TO LYNNEWOOD DR [Type] ANAPHYLACTIC
SC911::=121012-0424 [Address] 116 MITTMAN LN [X St] GREENTOWN RD TO DEADEND [Type] CVA
SC911::=121012-0435 [Address] 119 NOBLE LN [X St] PAYNETOWN RD TO DEADEND [Type] UNRESPONSIVE
SC911::=121012-0646 [Address] 473 SLATE RD [X St] MCBRIDE RD TO LINVILLE RD [Type] MENTAL SUBJ
SC911::=121013-0272 [Address] 366 MCBRIDE RD [X St] INTRIGUE LN TO TRIPLE R DR [Type] ACCIDENT PI
SC911::=121015-0341 [Location] 542 ALLRED MILL RD MOUNT AIRY , NC [X St] W LEBANON ST TO W LEBANON ST [Type] TRANSPORT
SC911::=121015-0345 [Location] 278 GAYLON ST MOUNT AIRY , NC [X St] NE PINE ST TO DEADEND [Type] CARDIAC D

Contact: Active911
Agency name: Mount Airy Fire Department
Location: Mount Airy, NC, United States
Sender: SC911@co.surry.nc.us

SC911:=121017-0448 [Location] 2170 RIVERSIDE DR MOUNT AIRY , NC [X St] BENNETT ST TO MOUNT VIEW DR [Type] FIRE ALARM\r\n
SC911:=121017-0379 [Location] 508 N RENFRO ST MOUNT AIRY , NC [X St] INDEPENDENCE BL TO N MAIN ST [Type] ACCIDENT PI\r\n
SC911:=121016-0498 [Location] 145 KINGSWOOD LN MOUNT AIRY , NC [X St] W VIRGINIA ST TO DEADEND [Type] FIRE ALARM\r\n
SC911::=121015-0175 [Location] 161 ORCHARD ST MOUNT AIRY , NC [X St] TAYLOR ST TO WILLOW ST [Type] ACCIDENT PI\r\n
SC911::=121014-0158 [Address] 838 S MAIN ST [X St] WORTH ST TO WELCH ST [Type] RESP DIS D\r\n
SC911::=121013-0243 [Address] 147 W POPLAR ST [X St] N MAIN ST TO LEBANON ST [Type] VEH FIRE\r\n
SC911::=121013-0144 [Address] 402 W PINE ST [X St] MARSHALL ST TO S SOUTH ST [Type] TRANSFMR FIR\r\n
SC911::=121012-0532 [Address] 1401 BOGGS DR [X St] WESTLAKE DR TO DEADEND [Type] GRASS FIRE\r\n
SC911::=121011-0563 [Address] 613 WORTH ST [X St] BANNER ST TO DAVIS ST [Type] SUICIDE\r\n
SC911::=121011-0521 [Address] FOR STA 10 [X St] TO [Type] PUBLIC SVC\r\n
SC911:=121021-0050 [Location] ARCH ST MOUNT AIRY , NC [X St] ELM ST TO JUNCTION ST [Type] WX TREE\r\n
SC911:=121020-0199 [Location] W LEBANON ST // FLOWER CHARM LN SURRY COUNTY , NC [X St] TO [Type] ACCIDENT PI\r\n
SC911:=121019-0316 [Location] 2133 ROCKFORD ST MOUNT AIRY , NC [X St] STEWART DR TO EDGEWOOD DR [Type] CARDIAC D\r\n
SC911:=121018-0284 [Location] 114 E LEBANON ST MOUNT AIRY , NC [X St] N MAIN ST TO NORTH ST [Type] ACCIDENT PI\r\n
SC911:=121017-0448 [Location] 2170 RIVERSIDE DR MOUNT AIRY , NC [X St] BENNETT ST TO MOUNT VIEW DR [Type] FIRE ALARM\r\n
SC911:=121017-0379 [Location] 508 N RENFRO ST MOUNT AIRY , NC [X St] INDEPENDENCE BL TO N MAIN ST [Type] ACCIDENT PI\r\n
SC911:=121016-0498 [Location] 145 KINGSWOOD LN MOUNT AIRY , NC [X St] W VIRGINIA ST TO DEADEND [Type] FIRE ALARM\r\n
SC911::=121015-0175 [Location] 161 ORCHARD ST MOUNT AIRY , NC [X St] TAYLOR ST TO WILLOW ST [Type] ACCIDENT PI\r\n
SC911::=121014-0158 [Address] 838 S MAIN ST [X St] WORTH ST TO WELCH ST [Type] RESP DIS D\r\n
SC911::=121013-0243 [Address] 147 W POPLAR ST [X St] N MAIN ST TO LEBANON ST [Type] VEH FIRE\r\n

*/

public class NCSurryCountyParserTest extends BaseParserTest {
  
  public NCSurryCountyParserTest() {
    setParser(new NCSurryCountyParser(), "SURRY COUNTY", "NC");
  }
  
  @Test
  public void testActive911A() {

    doTest("T1",
        "[] SC911::Call #120627-0079 [Address] 112 SOLO LN [X St] PEACE HAVEN CIR TO DEADEND [Type] ASST PATIENT\n",
        "ID:120627-0079",
        "ADDR:112 SOLO LN",
        "X:PEACE HAVEN CIR & DEADEND",
        "CALL:ASST PATIENT");

    doTest("T2",
        "[] SC911::Call #120627-0206 [Address] 587 N FRANKLIN RD [X St] TO [Type] CARDIAC\n",
        "ID:120627-0206",
        "ADDR:587 N FRANKLIN RD",
        "CALL:CARDIAC");

    doTest("T3",
        "[] SC911::Call #120627-0221 [Address] 297 MAPLE HOLLOW RD [X St] MARK ST TO LYNN ST [Type] DIABETIC D\n",
        "ID:120627-0221",
        "ADDR:297 MAPLE HOLLOW RD",
        "X:MARK ST & LYNN ST",
        "CALL:DIABETIC D");

    doTest("T4",
        "[] SC911::Call #120627-0235 [Address] 1872 W PINE ST [X St] TRANQUIL LN TO CEDAR KNOLL DR [Type] ASST PATIENT\n",
        "ID:120627-0235",
        "ADDR:1872 W PINE ST",
        "X:TRANQUIL LN & CEDAR KNOLL DR",
        "CALL:ASST PATIENT");

    doTest("T5",
        "[] SC911::Call #120627-0263 [Address] MAPLE DR // OLD TOAST RD [X St] OLD TOAST RD TO DEADEND [Type] UNKNOWN PROB\n",
        "ID:120627-0263",
        "ADDR:MAPLE DR & OLD TOAST RD",
        "X:OLD TOAST RD & DEADEND",
        "CALL:UNKNOWN PROB");

    doTest("T6",
        "[] SC911::Call #120628-0145 [Address] 1766 S ANDY GRIFFITH PKWY [X St] CITY LIMITS TO OLD BUCK SHOALS RD [Type] FIRE ALARM\n",
        "ID:120628-0145",
        "ADDR:1766 S ANDY GRIFFITH PKWY",
        "X:CITY LIMITS & OLD BUCK SHOALS RD",
        "CALL:FIRE ALARM");

    doTest("T7",
        "[] SC911::Call #120628-0294 [Address] 1161 W LEBANON ST [X St] PAIGE ST TO HADLEY RD [Type] COMM SERVICE\n",
        "ID:120628-0294",
        "ADDR:1161 W LEBANON ST",
        "X:PAIGE ST & HADLEY RD",
        "CALL:COMM SERVICE");
  }
  
  @Test
  public void testActive911B() {

    doTest("T1",
        "SC911::Call #120707-0414 [Address] 367 CEDAR RIDGE RD [X St] PIPERS GAP RD TO DEADEND [Type] ASSAULT D",
        "ID:120707-0414",
        "ADDR:367 CEDAR RIDGE RD",
        "X:PIPERS GAP RD & DEADEND",
        "CALL:ASSAULT D");

    doTest("T2",
        "SC911::Call #120709-0218 [Address] 192 LOWER PARK LN [X St] MID LN TO DEADEND [Type] CVA D",
        "ID:120709-0218",
        "ADDR:192 LOWER PARK LN",
        "X:MID LN & DEADEND",
        "CALL:CVA D");

    doTest("T3",
        "SC911::Call #120710-0221 [Address] 426 N FRANKLIN RD [X St] TO [Type] RESP DIS D",
        "ID:120710-0221",
        "ADDR:426 N FRANKLIN RD",
        "CALL:RESP DIS D");

    doTest("T4",
        "SC911::Call #120712-0107 [Address] 1872 PIPERS GAP RD [X St] MILLER RD TO PHANTOM LN [Type] ACCIDENT PI",
        "ID:120712-0107",
        "ADDR:1872 PIPERS GAP RD",
        "X:MILLER RD & PHANTOM LN",
        "CALL:ACCIDENT PI");

    doTest("T5",
        "SC911::Call #120712-0347 [Address] 223 BLUEMONT RD [X St] HIGHLAND DR TO S FRANKLIN RD [Type] FALL",
        "ID:120712-0347",
        "ADDR:223 BLUEMONT RD",
        "X:HIGHLAND DR & S FRANKLIN RD",
        "CALL:FALL");

    doTest("T6",
        "SC911::Call #120713-0124 [Address] 201 HUNTINGTON CREEK LN [X St] IMOGENE CH RD TO DEADEND [Type] UNRESPONSIVE",
        "ID:120713-0124",
        "ADDR:201 HUNTINGTON CREEK LN",
        "X:IMOGENE CH RD & DEADEND",
        "CALL:UNRESPONSIVE");

    doTest("T7",
        "SC911::Call #120713-0140 [Address] 227 AIRVIEW DR [X St] SUMMIT DR TO DEADEND [Type] ASSIST AGNCY",
        "ID:120713-0140",
        "ADDR:227 AIRVIEW DR",
        "X:SUMMIT DR & DEADEND",
        "CALL:ASSIST AGNCY");

    doTest("T8",
        "SC911::Call #120713-0165 [Address] 227 AIRVIEW DR [X St] SUMMIT DR TO DEADEND [Type] ASSIST AGNCY",
        "ID:120713-0165",
        "ADDR:227 AIRVIEW DR",
        "X:SUMMIT DR & DEADEND",
        "CALL:ASSIST AGNCY");

    doTest("T9",
        "SC911::Call #120713-0208 [Address] 142 BOURBON TR [X St] CAROLINA RD/DUKE RD TO DEADEND [Type] CHOKE",
        "ID:120713-0208",
        "ADDR:142 BOURBON TR",
        "X:CAROLINA RD/DUKE RD & DEADEND",
        "CALL:CHOKE");

    doTest("T10",
        "SC911::Call #120713-0222 [Address] 242 DUDLEY AVE [X St] DUDLEY AVE TO DEADEND [Type] FALL D",
        "ID:120713-0222",
        "ADDR:242 DUDLEY AVE",
        "X:DUDLEY AVE & DEADEND",
        "CALL:FALL D");

    doTest("T11",
        "SC911::Call #120713-0222 [Address] 242 DUDLEY AVE [X St] DUDLEY AVE TO DEADEND [Type] FALL D",
        "ID:120713-0222",
        "ADDR:242 DUDLEY AVE",
        "X:DUDLEY AVE & DEADEND",
        "CALL:FALL D");

    doTest("T12",
        "SC911::Call #120714-0040 [Address] 149 LINWOOD DR [X St] S FRANKLIN RD TO GREENWAY DR [Type] SUICIDE",
        "ID:120714-0040",
        "ADDR:149 LINWOOD DR",
        "X:S FRANKLIN RD & GREENWAY DR",
        "CALL:SUICIDE");

    doTest("T13",
        "SC911::Call #120714-0113 [Address] 150 ELK COURT DR [X St] N BRIDGE ST TO HWY 268 BY PASS [Type] LIFELINE",
        "ID:120714-0113",
        "ADDR:150 ELK COURT DR",
        "X:N BRIDGE ST & HWY 268 BY PASS",
        "CALL:LIFELINE");

    doTest("T14",
        "SC911::Call #120714-0116 [Address] 108 DOGWOOD DR [X St] ROSEWOOD DR TO LONG ST [Type] SICK D",
        "ID:120714-0116",
        "ADDR:108 DOGWOOD DR",
        "X:ROSEWOOD DR & LONG ST",
        "CALL:SICK D");

    doTest("T15",
        "SC911::Call #120715-0211 [Address] 194 OAK RIDGE DR [X St] MAPLE HOLLOW RD TO DEADEND [Type] CARDIAC D",
        "ID:120715-0211",
        "ADDR:194 OAK RIDGE DR",
        "X:MAPLE HOLLOW RD & DEADEND",
        "CALL:CARDIAC D");

    doTest("T16",
        "SC911::Call #120715-0237 [Address] 481 WHITE PINES COUNTRY CLUB RD [X St] N ANDY GRIFFITH PKWY TO VA STATE LINE [Type] ASST PATIENT",
        "ID:120715-0237",
        "ADDR:481 WHITE PINES COUNTRY CLUB RD",
        "X:N ANDY GRIFFITH PKWY & VA STATE LINE",
        "CALL:ASST PATIENT");

    doTest("T17",
        "SC911::Call #120715-0325 [Address] 338 S FRANKLIN RD [X St] CALVARY DR TO LONG ST [Type] HEMRGE D",
        "ID:120715-0325",
        "ADDR:338 S FRANKLIN RD",
        "X:CALVARY DR & LONG ST",
        "CALL:HEMRGE D");

    doTest("T18",
        "SC911::Call #120716-0179 [Address] 227 AIRVIEW DR [X St] SUMMIT DR TO DEADEND [Type] TRANSPORT",
        "ID:120716-0179",
        "ADDR:227 AIRVIEW DR",
        "X:SUMMIT DR & DEADEND",
        "CALL:TRANSPORT");

    doTest("T19",
        "SC911::Call #120716-0280 [Address] 110 DUTCHMANS CT [X St] JOHNSON RIDGE RD TO DEADEND [Type] TRANSPORT",
        "ID:120716-0280",
        "ADDR:110 DUTCHMANS CT",
        "X:JOHNSON RIDGE RD & DEADEND",
        "CALL:TRANSPORT");

    doTest("T20",
        "SC911::Call #120716-0299 [Address] 481 WHITE PINES COUNTRY CLUB RD [X St] N ANDY GRIFFITH PKWY TO VA STATE LINE [Type] UNRSPVE D",
        "ID:120716-0299",
        "ADDR:481 WHITE PINES COUNTRY CLUB RD",
        "X:N ANDY GRIFFITH PKWY & VA STATE LINE",
        "CALL:UNRSPVE D");

    doTest("T21",
        "SC911::Call #120716-0529 [Address] 2013 SPARGER RD [X St] BEAMER RD TO GOINS RD [Type] ACCIDENT PI",
        "ID:120716-0529",
        "ADDR:2013 SPARGER RD",
        "X:BEAMER RD & GOINS RD",
        "CALL:ACCIDENT PI");

    doTest("T22",
        "SC911::Call #120718-0172 [Address] 112 SOLO LN [X St] PEACE HAVEN CIR TO DEADEND [Type] CARDIAC D",
        "ID:120718-0172",
        "ADDR:112 SOLO LN",
        "X:PEACE HAVEN CIR & DEADEND",
        "CALL:CARDIAC D");

    doTest("T23",
        "SC911::Call #120718-0206 [Address] 1872 W PINE ST [X St] TRANQUIL LN TO CEDAR KNOLL DR [Type] ASST PATIENT",
        "ID:120718-0206",
        "ADDR:1872 W PINE ST",
        "X:TRANQUIL LN & CEDAR KNOLL DR",
        "CALL:ASST PATIENT");
  }
  
  @Test
  public void testFourWay() {

    doTest("T1",
        "SC911::=121008-0404 [Address] 100 MCBRIDE RD [X St] E PINE ST TO INTRIGUE LN [Type] TEST",
        "ID:121008-0404",
        "ADDR:100 MCBRIDE RD",
        "X:E PINE ST & INTRIGUE LN",
        "CALL:TEST");

    doTest("T2",
        "SC911::=121008-0534 [Address] 182 ELEANOR AVE [X St] GAYLON ST TO DEADEND [Type] RESP DIS C",
        "ID:121008-0534",
        "ADDR:182 ELEANOR AVE",
        "X:GAYLON ST & DEADEND",
        "CALL:RESP DIS C");

    doTest("T3",
        "SC911::=121011-0174 [Address] 2692 WARDS GAP RD [X St] GREENHILL RD TO BEYOND CROSSINGHAM RD [Type] ABD PAIN C",
        "ID:121011-0174",
        "ADDR:2692 WARDS GAP RD",
        "X:GREENHILL RD & BEYOND CROSSINGHAM RD",
        "CALL:ABD PAIN C");

    doTest("T4",
        "SC911::=121011-0416 [Address] 104 SUMMER LN [X St] AUTUMN LN TO DEADEND [Type] ABD PAIN C",
        "ID:121011-0416",
        "ADDR:104 SUMMER LN",
        "X:AUTUMN LN & DEADEND",
        "CALL:ABD PAIN C");

    doTest("T5",
        "SC911::=121012-0031 [Address] 761 MCBRIDE RD [X St] MELISSA DR TO LYNNEWOOD DR [Type] ANAPHYLACTIC",
        "ID:121012-0031",
        "ADDR:761 MCBRIDE RD",
        "X:MELISSA DR & LYNNEWOOD DR",
        "CALL:ANAPHYLACTIC");

    doTest("T6",
        "SC911::=121012-0424 [Address] 116 MITTMAN LN [X St] GREENTOWN RD TO DEADEND [Type] CVA",
        "ID:121012-0424",
        "ADDR:116 MITTMAN LN",  // Is this CLINT MITTMAN LN??
        "X:GREENTOWN RD & DEADEND",
        "CALL:CVA");

    doTest("T7",
        "SC911::=121012-0435 [Address] 119 NOBLE LN [X St] PAYNETOWN RD TO DEADEND [Type] UNRESPONSIVE",
        "ID:121012-0435",
        "ADDR:119 NOBLE LN",
        "X:PAYNETOWN RD & DEADEND",
        "CALL:UNRESPONSIVE");

    doTest("T8",
        "SC911::=121012-0646 [Address] 473 SLATE RD [X St] MCBRIDE RD TO LINVILLE RD [Type] MENTAL SUBJ",
        "ID:121012-0646",
        "ADDR:473 SLATE RD",
        "X:MCBRIDE RD & LINVILLE RD",
        "CALL:MENTAL SUBJ");

    doTest("T9",
        "SC911::=121013-0272 [Address] 366 MCBRIDE RD [X St] INTRIGUE LN TO TRIPLE R DR [Type] ACCIDENT PI",
        "ID:121013-0272",
        "ADDR:366 MCBRIDE RD",
        "X:INTRIGUE LN & TRIPLE R DR",
        "CALL:ACCIDENT PI");

    doTest("T10",
        "SC911::=121015-0341 [Location] 542 ALLRED MILL RD MOUNT AIRY , NC [X St] W LEBANON ST TO W LEBANON ST [Type] TRANSPORT",
        "ID:121015-0341",
        "ADDR:542 ALLRED MILL RD",
        "CITY:MOUNT AIRY",
        "X:W LEBANON ST & W LEBANON ST",
        "CALL:TRANSPORT");

    doTest("T11",
        "SC911::=121015-0345 [Location] 278 GAYLON ST MOUNT AIRY , NC [X St] NE PINE ST TO DEADEND [Type] CARDIAC D",
        "ID:121015-0345",
        "ADDR:278 GAYLON ST",
        "CITY:MOUNT AIRY",
        "X:NE PINE ST & DEADEND",
        "CALL:CARDIAC D");

  }
  
  @Test
  public void testMtAireyFire() {

    doTest("T1",
        "SC911:=121017-0448 [Location] 2170 RIVERSIDE DR MOUNT AIRY , NC [X St] BENNETT ST TO MOUNT VIEW DR [Type] FIRE ALARM\r\n",
        "ID:121017-0448",
        "ADDR:2170 RIVERSIDE DR",
        "CITY:MOUNT AIRY",
        "X:BENNETT ST & MOUNT VIEW DR",
        "CALL:FIRE ALARM");

    doTest("T2",
        "SC911:=121017-0379 [Location] 508 N RENFRO ST MOUNT AIRY , NC [X St] INDEPENDENCE BL TO N MAIN ST [Type] ACCIDENT PI\r\n",
        "ID:121017-0379",
        "ADDR:508 N RENFRO ST",
        "CITY:MOUNT AIRY",
        "X:INDEPENDENCE BL & N MAIN ST",
        "CALL:ACCIDENT PI");

    doTest("T3",
        "SC911:=121016-0498 [Location] 145 KINGSWOOD LN MOUNT AIRY , NC [X St] W VIRGINIA ST TO DEADEND [Type] FIRE ALARM\r\n",
        "ID:121016-0498",
        "ADDR:145 KINGSWOOD LN",
        "CITY:MOUNT AIRY",
        "X:W VIRGINIA ST & DEADEND",
        "CALL:FIRE ALARM");

    doTest("T4",
        "SC911::=121015-0175 [Location] 161 ORCHARD ST MOUNT AIRY , NC [X St] TAYLOR ST TO WILLOW ST [Type] ACCIDENT PI\r\n",
        "ID:121015-0175",
        "ADDR:161 ORCHARD ST",
        "CITY:MOUNT AIRY",
        "X:TAYLOR ST & WILLOW ST",
        "CALL:ACCIDENT PI");

    doTest("T5",
        "SC911::=121014-0158 [Address] 838 S MAIN ST [X St] WORTH ST TO WELCH ST [Type] RESP DIS D\r\n",
        "ID:121014-0158",
        "ADDR:838 S MAIN ST",
        "X:WORTH ST & WELCH ST",
        "CALL:RESP DIS D");

    doTest("T6",
        "SC911::=121013-0243 [Address] 147 W POPLAR ST [X St] N MAIN ST TO LEBANON ST [Type] VEH FIRE\r\n",
        "ID:121013-0243",
        "ADDR:147 W POPLAR ST",
        "X:N MAIN ST & LEBANON ST",
        "CALL:VEH FIRE");

    doTest("T7",
        "SC911::=121013-0144 [Address] 402 W PINE ST [X St] MARSHALL ST TO S SOUTH ST [Type] TRANSFMR FIR\r\n",
        "ID:121013-0144",
        "ADDR:402 W PINE ST",
        "X:MARSHALL ST & S SOUTH ST",
        "CALL:TRANSFMR FIR");

    doTest("T8",
        "SC911::=121012-0532 [Address] 1401 BOGGS DR [X St] WESTLAKE DR TO DEADEND [Type] GRASS FIRE\r\n",
        "ID:121012-0532",
        "ADDR:1401 BOGGS DR",
        "X:WESTLAKE DR & DEADEND",
        "CALL:GRASS FIRE");

    doTest("T9",
        "SC911::=121011-0563 [Address] 613 WORTH ST [X St] BANNER ST TO DAVIS ST [Type] SUICIDE\r\n",
        "ID:121011-0563",
        "ADDR:613 WORTH ST",
        "X:BANNER ST & DAVIS ST",
        "CALL:SUICIDE");

    doTest("T10",
        "SC911::=121011-0521 [Address] FOR STA 10 [X St] TO [Type] PUBLIC SVC\r\n",
        "ID:121011-0521",
        "ADDR:FOR STA 10",
        "CALL:PUBLIC SVC");

    doTest("T11",
        "SC911:=121021-0050 [Location] ARCH ST MOUNT AIRY , NC [X St] ELM ST TO JUNCTION ST [Type] WX TREE\r\n",
        "ID:121021-0050",
        "ADDR:ARCH ST",
        "MADDR:ARCH ST & ELM ST",
        "CITY:MOUNT AIRY",
        "X:ELM ST & JUNCTION ST",
        "CALL:WX TREE");

    doTest("T12",
        "SC911:=121020-0199 [Location] W LEBANON ST // FLOWER CHARM LN SURRY COUNTY , NC [X St] TO [Type] ACCIDENT PI\r\n",
        "ID:121020-0199",
        "ADDR:W LEBANON ST & FLOWER CHARM LN",
        "CITY:SURRY COUNTY",
        "CALL:ACCIDENT PI");

    doTest("T13",
        "SC911:=121019-0316 [Location] 2133 ROCKFORD ST MOUNT AIRY , NC [X St] STEWART DR TO EDGEWOOD DR [Type] CARDIAC D\r\n",
        "ID:121019-0316",
        "ADDR:2133 ROCKFORD ST",
        "CITY:MOUNT AIRY",
        "X:STEWART DR & EDGEWOOD DR",
        "CALL:CARDIAC D");

    doTest("T14",
        "SC911:=121018-0284 [Location] 114 E LEBANON ST MOUNT AIRY , NC [X St] N MAIN ST TO NORTH ST [Type] ACCIDENT PI\r\n",
        "ID:121018-0284",
        "ADDR:114 E LEBANON ST",
        "CITY:MOUNT AIRY",
        "X:N MAIN ST & NORTH ST",
        "CALL:ACCIDENT PI");

    doTest("T15",
        "SC911:=121017-0448 [Location] 2170 RIVERSIDE DR MOUNT AIRY , NC [X St] BENNETT ST TO MOUNT VIEW DR [Type] FIRE ALARM\r\n",
        "ID:121017-0448",
        "ADDR:2170 RIVERSIDE DR",
        "CITY:MOUNT AIRY",
        "X:BENNETT ST & MOUNT VIEW DR",
        "CALL:FIRE ALARM");

    doTest("T16",
        "SC911:=121017-0379 [Location] 508 N RENFRO ST MOUNT AIRY , NC [X St] INDEPENDENCE BL TO N MAIN ST [Type] ACCIDENT PI\r\n",
        "ID:121017-0379",
        "ADDR:508 N RENFRO ST",
        "CITY:MOUNT AIRY",
        "X:INDEPENDENCE BL & N MAIN ST",
        "CALL:ACCIDENT PI");

    doTest("T17",
        "SC911:=121016-0498 [Location] 145 KINGSWOOD LN MOUNT AIRY , NC [X St] W VIRGINIA ST TO DEADEND [Type] FIRE ALARM\r\n",
        "ID:121016-0498",
        "ADDR:145 KINGSWOOD LN",
        "CITY:MOUNT AIRY",
        "X:W VIRGINIA ST & DEADEND",
        "CALL:FIRE ALARM");

    doTest("T18",
        "SC911::=121015-0175 [Location] 161 ORCHARD ST MOUNT AIRY , NC [X St] TAYLOR ST TO WILLOW ST [Type] ACCIDENT PI\r\n",
        "ID:121015-0175",
        "ADDR:161 ORCHARD ST",
        "CITY:MOUNT AIRY",
        "X:TAYLOR ST & WILLOW ST",
        "CALL:ACCIDENT PI");

    doTest("T19",
        "SC911::=121014-0158 [Address] 838 S MAIN ST [X St] WORTH ST TO WELCH ST [Type] RESP DIS D\r\n",
        "ID:121014-0158",
        "ADDR:838 S MAIN ST",
        "X:WORTH ST & WELCH ST",
        "CALL:RESP DIS D");

    doTest("T20",
        "SC911::=121013-0243 [Address] 147 W POPLAR ST [X St] N MAIN ST TO LEBANON ST [Type] VEH FIRE\r\n",
        "ID:121013-0243",
        "ADDR:147 W POPLAR ST",
        "X:N MAIN ST & LEBANON ST",
        "CALL:VEH FIRE");

  }

  public static void main(String[] args) {
    new NCSurryCountyParserTest().generateTests("T1");
  }
}
