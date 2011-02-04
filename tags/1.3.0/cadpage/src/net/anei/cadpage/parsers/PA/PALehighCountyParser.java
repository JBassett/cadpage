package net.anei.cadpage.parsers.PA;

import java.util.Properties;

import net.anei.cadpage.parsers.dispatch.DispatchPrintrakParser;

/*
Lehigh County PA
Contact: "Don Smith" <asstjr03@ptd.net>  (911 employee)
Sender: 911@lehighcounty.org
System: PRINTRAK by Motorola

ST0100 TYP: RESP DIST-ABN BREATH APT: 8 AD: 4015 SCHOENECK RD CTY: LOWER MACUNGIE LOC: BIG O MHP CN: NURSE JULIE CMT1: **CHARLIE BREATHING PROBLEMS - ABNORMAL REATHING CMT2: 57 YEAR OLD MALE | 502 LBS | FULL OF FLUID | BLOOD IN STOOL | TIME: 10:11 UNTS: FD/30CHIEFS FD/ST01 XST2: MOUNTAIN RD
ST0100 TYP: NON-DWELL STRUCTURE AD: 8635 HENSINGERSVILLE RD CTY: 06 LONGSWAMP CMT1: **NON-DWELLING STRUCTURE FIRE (SHED, DETATCHED GARAGE) CMT2: FULLY INVOLVED STRUCTURE FIRE TIME: 01:37 UNTS: FD/ST01 XST: WALKER RD XST2: 8603 OAK HOLLOW LN
ST0100 TYP: APARTMENT BLDG FIRE AD: 2130 STATE ST CTY: 06 LONGSWAMP CN: BERKS CMT1: **APARTMENT BUILDING FIRE (MULTI-OCCUPANCY RESIDENTIAL) CMT2: REQ ST 01 WIT BERKS ST21 SMOKE FROM BASEMENT IN APT BUILDING TIME: 14:22 UNTS: FD/E111 XST: 175 SCHLOSSBURG ST XST2: 380 JAMES LN
ST0100 TYP: TRAFFIC CONTROL AD: CHURCH ST&THOMAS ST CTY: ALBURTIS LOC: ON THOMAS CN: RONALD CONRAD CMT1: **TRAFFIC CONTROL CMT2: ASST WITH WATER MAIN TIME: 08:
ST0100 TYP: MVA WITH RESCUE AD: S RT100&SCHANTZ RD CTY: UPPER MACUNGIE CN: TIM MARTIN CMT1: **BRAVO MVA - WITH INJURIES CMT2: PER PASSERBY AIRBAGS DEPLOYED TIM 14:06 UNTS: FD/ST25 FD/A6295 FD/E2511 FD/R141 FD/06R21
ST0100 TYP: RESCUE-SPECIFY TYPE AD: GAP RD&WALKER RD CTY: 06 LONGSWAMP CN: BERKS CMT1: **DELTA TRENCH COLLAPSE WITH RESCUE (ALERT RESCUE TONES) CMT2: ST 1 AND T66 WITH ATV RIGHT ON TO WALKER TO THE DEAD END BERK EMS AND FI TIME: 02:19 UNTS: FD/E112
ST3000 TYP: FD STANDBY AT OWN ST AD: ALLENTOWN FD CTY: ALLENTOWN LOC: ALLENTOWN FD CMT1: **FIRE STANDBY - AT OWN STATION CMT2: Original Location : ALLENTOWN FD TIME: 17:43 UNTS: FD/E3711 FD/E3012 FD/E1112 FD/E3112 FD/TK231 FD/E2711 FD/R1141&nbsp;
ST3000 TYP: EMS ASSIST AD: 3510 MACUNGIE RD CTY: LOWER MACUNGIE CN: JARRETT DENNIS CMT1: **ECHO CARDIAC ARREST - NOT BREATHING CMT2: 0010001030 WIFE WONT WAKE UP SNORING RESP 55 YEAR OLD FEMALE UNCONSCIO TIME: 10:07 UNTS: FD/30CHIEFS FD/ST30 XST: 3157 WATERMILL DR XST2: 5248 INDIAN CREEK RD
ST3000 TYP: SMOKE IN DWELLING AD: 2008 ELBOW LN CTY: LOWER MACUNGIE CN: LINDA STROHL CMT1: **SMOKE IN THE DWELLING CMT2: SMOKE COMING FROM THE EXHAUST PIPE OF HEATING SYSTEM FILLING BASEMENT WIT TIME: 06:58 UNTS: FD/30CHIEFS FD/ST30 FD/A6681 FD/ST66 FD/R3041 FD/E3012 FD/TK3031 FD/E3112 XST: 4486 REDWOOD LN XST2: HESS CIR
ST6651 TYP: NON-DWELL STRUCTURE AD: 8635 HENSINGERSVILLE RD CTY: 06 LONGSWAMP CMT1: **NON-DWELLING STRUCTURE FIRE (SHED, DETATCHED GARAGE) CMT2: FULLY INVOLVED STRUCTURE FIRE TIME: 01:37 UNTS: FD/6651 XST: WALKER RD XST2: 8603 OAK HOLLOW LN
ST3000 TYP: CHIMNEY FIRE AD: 6043 LOWER MACUNGIE RD CTY: LOWER MACUNGIE LOC: LOWER MACUNGIE ELEMENTARY CN: MR FELDMAN CMT1: **CHIMNEY FIRE CMT2: SMOKE COMING FROM THE CHIMNEY TIME: 22:09 UNTS: FD/30CHIEFS FD/ST30 FD/ST66 FD/A6683 FD/E3012 FD/TK3031 FD/R3041 FD/E2511 XST: S KROCKS RD XST2: SPRING CREEK RD
ST3000 TYP: UNCONSC-EFFECTIVE BR AD: 7547 CATALPA DR CTY: LOWER MACUNGIE CN: DAVE KOWALICK CMT1: **DELTA UNCONSCIOUS - EFFECTIVE BREATHING CMT2: 0110000513 UNCON 30 YEAR OLD MALE UNCONSCIOUS BREATHING STATUS UNKNOW TIME: 19:30 UNTS: FD/R3041 XST: WOODBINE RD XST2: RED OAK LN
ST3000 TYP: STRUCT/DWELL-UNK SIT AD: SPRINGHOUSE RD&amp;TREXLER BLVD CTY: SOUTH WHITEHALL LOC: . CN: CHRIS BENALE CMT1: **STRUCTURE OR&nbsp;DWELLING FIRE - UNKNOWN SITUATION CMT2: ABANDONED BARN WITH SMOKE COMING OUT OFROOF CAN SEE FLAMES ALSO TIME: 14:45 UNTS: FD/TK3031
ST3000 TYP: ALARM-FIRE-DWELLING AD: 4129 FAWN TRAIL RD CTY: SOUTH WHITEHALL LOC: CRON RES 610-530-0708 CN: OPER MANDI CMT1: **RESIDENTIAL FIRE ALARM - SINGLE-FAMILY OCCUPANCY CMT2: ZONE 6 SMOKE DETECTOR / ATTEMPTED KEYHOLDER TIME: 17:49 UNTS: FD/ST32 FD/E3212&nbsp;FD/TA3221 FD/SP3291 FD/E1111 FD/TK3031 FD/R1141 XST: 1382 BUCK TRAIL RD XST2: 1386 DOE TRAIL RD
ST3000 TYP: SPILL &lt;50 GAL UNCNTD AD: KROCKS RD&amp;HAMILTON BLVD CTY: LOWER MACUNGIE CN: JAMES FOREMAN CMT1: **BRAVO MVA - WITH INJURIES CMT2: TWO VEHICLES / ONE FEMALE 40'S POSS BROKEN NOSE TIME: 16:13 UNTS: FD/30CHIEFS FD/ST30
This one's on the PA Turnpike (I-476 at mile marker 57.4 NB).. You'll see how it's put in here with the mile marker:
ST3000 TYP: MVA-ROLLOVER AD: I476MM57 4NB CTY: SOUTH WHITEHALL LOC: I476MM57 4NB CMT1: **DELTA MVA WITH ROLLOVER CMT2: ONE VEHICLE&nbsp;ON THE TURNPIKE ANOTHER POSSIBLY FLIPPED OVER THE GUARDRAIL TIME: 11:57 UNTS: FD/30CHIEFS FD/ST30 FD/A6298 FD/ST62MN FD/R3041 FD/E3012 XST: TILGHMAN ST XST2: RT22
ST3000 TYP: MVA WITH RESCUE AD: WILD CHERRY LN&amp;VALLEY STREAM L CTY: LOWER MACUNGIE CN: DALE METZGER CMT1: **DELTA MVA WITH RESCUE (ALERT RESCUE TONES AS NECESSARY) CMT2: VEHICLE HIT HTE BRIDGE | MALE TRAPPED INTHE VEHICLE TIME: 07:04 UNTS: FD/30CHIEFS FD/ST30&nbsp;FD/A6682 FD/ST66 FD/E3012 FD/R3041

ST3000 TYP: MVA-AUTO-PEDESTRIAN AD: RT222&KROCKS RD CTY: LOWER MACUNGIE CN: JOHN TILGHMAN CMT1: **DELTA PEDESTRIAN STRUCK CMT2: PE Eff Body:ST3000 TYP: MVA-AUTO-PEDESTRIAN AD: RT222&KROCKS RD CTY: LOWER MACUNGIE CN: JOHN TILGHMAN CMT1: **DELTA PEDESTRIAN STRUCK CMT2: PE
D TIME: 21:51 UNTS: FD/E3012

ST3000 TYP: FALL-LONG FALL AD: 1525 SHASTA LN CTY: LOWER MACUNGIE CN: BROOK MYERS CMT1: **DELTA FALL VICTIM - LONG FALL (>6 FT) CM Eff Body:ST3000 TYP: FALL-LONG FALL AD: 1525 SHASTA LN CTY: LOWER MACUNGIE CN: BROOK MYERS CMT1: **DELTA FALL VICTIM - LONG FALL (>6 FT) CM
62499 ST RD13 GOSHENAmbulance CallCLIN,M1J01/16/2011 10:46:33 : pos7 : HARMOF   Geo Comment: 00120D  CLIN CEN  CLINTON BRICK MENNONITE CHURCH.....GO TO THE
SPRING CREEK RD

ST3000 TYP: DWEL/STRUCT W/ENTRAP AD: 2573 MILL HOUSE RD CTY: LOWER MACUNGIE CN: LORI BOWICR CMT1: **ECHO DWELLING OR STRUCTURE FIR
E WITH ENTRAPMENT CMT2: FIRE IN THE DRYER CAN NOT FIND HER DAUGHTER TIME: 10:25 UNTS: FD/ST30 FD/ST66 FD/E3012 FD/E3011 FD/E1512 F
D/E1511 FD/TK3031 FD/ST60RIT XST: 5753 FRESH MEADOW DR XST2: SAUERKRAUT LN
*/


public class PALehighCountyParser extends DispatchPrintrakParser {
  
  private static final Properties CITY_TABLE = buildCodeTable(new String[]{
      "06 LONGSWAMP", "LONGSWAMP TWP",
      "HANOVER", "HANOVER TWP",
      "HEIDELBERG","HEIDELBERG TWP",
      "LOWER MACUNGIE", "LOWER MACUNGIE TWP",
      "LOWER MILFORD", "LOWER MILFORD TWP",
      "LOWHILL", "LOWHILL TWP",
      "LYNN","LYNN TOWNSHIP",
      "NORTH WHITEHALL", "NORTH WHITEHALL TWP",
      "SALISBURY", "SALISBURY TWP",
      "SOUTH WHITEHALL", "SOUTH WHITEHALL TWP",
      "UPPER MACUNGIE", "UPPER MACUNGIE TWP",
      "UPPER MILFORD", "UPPER MILFORD TWP",
      "UPPER SAUCON", "UPPER SAUCON TWP",
      "WASHINGTON", "WASHINGTON TWP",
      "WEISENBERG", "WEISENBERG TWP",
      "WHITEHALL", "WHITEHALL TWP"
  });
  
  public PALehighCountyParser() {
    super(CITY_TABLE, "LEHIGH COUNTY", "PA");
  }
  
  @Override
  public String getFilter() {
    return "911@lehighcounty.org";
  }
}
