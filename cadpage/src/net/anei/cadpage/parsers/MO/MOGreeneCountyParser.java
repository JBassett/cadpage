package net.anei.cadpage.parsers.MO;

import java.util.regex.Pattern;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.dispatch.DispatchPrintrakParser;

/*
Greene County, MO
Contact: Daniel Brant <rdnck41@gmail.com>
Sender: grnpage@springfieldmo.gov <From%3Agrnpage@springfieldmo.gov>

TYP: RESIDENTIAL FIRE AD: 500 S VENTURA AVE CTY: REPUBLIC MAP: U-8 CN: JOE SNYDER CMT1: * * * CLONE STRUCTURE FIRES TO GF/FHOUSE-FBLDG/BF10/ CMT:AUTO_AID * * * TIME: 22:21 UNTS: RF/BRUSH1 XST: 598 S FOREST LN XST2: 600 W ONEAL RD
TYP: MEDICAL AD: 1013 E US60 EAST CTY: REPUBLIC MAP: T-9 LOC: PRICE CUTTER CMT1: * * * CLONE STRUCTURE FIRES TO GF/ FHOUSE-FBLDG/BF10/CMT:AUTO_AID * * * CMT2: PERSON FELL OFF BICYCLE -ELDERLY TIME: 19:12 UNTS: RF/ST1
TYP: Medical AD: 2356 E GOODMAN ST CTY: REPUBLIC MAP: U-10 CMT1: * * * CLONE STRUCTURE FIRES TO GF/FHOUSE-FBLDG/BF10/ CMT:AUTO_AID * * * CMT2:MOM IN LAW FELL, POSSIBLE HIP INJURY - 74 YEARS OLD TIME: 18:28 UNTS: RF/ST1 RF/CMD1 XST: 201 N CO
TYP: BUILDING ALARM APT:A AD: 2130 E HAMILTON ST CTY: REPUBLIC MAP: T-10 LOC: OAK COURT PLACE CN: HIDEMAN ALARM CO CMT1: * * * CLONE STRUCTURE FIRES TO GF/FHOUSE-FBLDG/BF10/ CMT:AUTO_AID * * * CMT2: GENERAL FIRE ALARM TIME: 18:28 UNTS: RF/S
TYP: Medical AD: 609 S COUNTRYSIDE AVE CTY: REPUBLIC MAP: V-8 CMT1: * * * CLONE STRUCTURE FIRES TO GF/FHOUSE-FBLDG/BF10/ CMT:AUTO_AID * * * CMT2:69 YR OLD MALE, PHEMONIA TROUBLE BREATHING, CONCIOUS AND ALERT TROUBLE BR TIME: 06:08 UNTS: RF/
TYP: Medical AD: 117 E PARK ST CTY: REPUBLIC MAP: U-9 CMT1: * * * CLONE STRUCTURE FIRES TO GF/FHOUSE-FBLDG/BF10/ CMT:AUTO_AID * * * CMT2:69 YR OLD MALE VOMMITING, CONCIOUS ALERT AND BREATHING. FACE ISSWOLLEN TIME: 00:37 UNTS: RF/RESCUE XST:

Contact: Dwayne <shinypiper@live.com>
TYP: MEDICAL AD: 11391 W SHBB CTY: GREENE COUNTY MAP: A-6 CN: VERIZON WIRELESS CMT1: CLR WITH LOW BLOOD SURGAR, HAVING TROUBLE SPEAKING TIME: 17:43 UNTS: GF/
TYP: FIRE OUTSIDE AD: N FR75&N SH123 CTY: GREENE COUNTY MAP: E-8 CN: PAULA CMT1: NEAR THE S CURVES.. SAYS A BRUSH FIRE. NEAR THE ROADWAY. BY SOME PROPERT CMT

Contact: Justin Crom <cromj87@gmail.com>
Sender: GRNPAGE@springfieldmo.gov
TYP: FIRE ALARM AD: 400 W WALNUT LN CTY: WILLARD MAP: I-11 LOC: WILLARD CARE CENTER/742-3594 CN: 914 CMT1: FIRE ALARM ZONE 1 CMT2: Original Location : WILLAR

Contact: Mark Hensley <marklrfire@gmail.com>
Sender: GRNPAGE@springfieldmo.gov
TYP: AUTO FIRE AD: E FR164&S SHJ CTY: GREENE COUNTY MAP: R-26 CN: VERIZON WIRELESS CMT1: ** STATION 1 ZONE ** CMT2: TRUCK ON FIRE TIME: 09:45 UNTS: GF/LRF

Webster County, MO
Contact: Mark Hensley <marklrfire@gmail.com>,Mh0356111@OTC.edu
Sender: GRNPAGE@springfieldmo.gov
TYP: FIRE OUTSIDE AD: POWER LINE RD&US60 CTY: ROGERSVILLE CN: LRF CMT1: ** STATION 3 ZONE ** CMT2: FIRE OUTSIDE TIME: 15:03 UNTS: GF/LRF
TYP: MEDICAL AD: 5337 S STONEHAVEN DR CTY: GREENE COUNTY MAP: U-23 CN: AT&T MOBILITY (TCS) CMT1: ** HIGHLAND SPRINGS NORTH GATE 5000 S HIGHLAND SPRINGS BL
TYP: MEDICAL AD: 245 W HELENA ST CTY: ROGERSVILLE CN: 4177666491 CMT1: ** STATION 5 ZONE ** CMT2: XFER FROM WEBSTER CO. MALE HAVING CHEST PAINS. CO

Contact: Active911
[] TYP: SMOKE IN AREA AD: 5266 E WILD HORSE DR CTY: GREENE COUNTY MAP: M-25 LOC: NORTH OF CN: GARTIN, PATRICK CMT1: CLRS SEES A LOT OF BLK SMOKE COMING OVER TREE LINE CMT2: Original Location : NORTH OF TIME: 18:54 UNTS: GF/LRFA XST: 1901 N CITATION AVE\r\n\n
[] TYP: MEDICAL AD: 141 CHESTNUT DR CTY: CHRISTIAN COUNT CN: JUSTIN CMT1: ** STATION 3 ZONE ** CMT2: REG LRF TO STAGE FOR ASSAULT AT LOC, F "BEAT UP", CHRISTIAN CO CALLING C TIME: 01:52 UNTS: GF/LRF XST:1271 SHU HWY\r\n\n
[] TYP: FIRE ALARM AD: 111 W MILL ST CTY: ROGERSVILLE LOC: ATT&T CN: GEORGE CMT1: ** STATION 5 ZONE ** CMT2: GENERAL FIRE TIME: 07:29 UNTS: GF/LRF XST: 140 S MAIN ST XST2: 198 S TURNER ST\r\n\n
[] TYP: COMM BLDG FIRE AD: 3601 W WESTWIND DR CN: WES CMT1: LRF AUTO AID WITH OZF, VEH FIRE IN GARAGE. US65 SB TO SHCC, EXIT WB TO F CMT2: REMONT, GO NB FREMONT TO WESTWIND DR, TURN LEFT TO APT COMPLEX. TIME: 14:52 UNTS: GF/LRF\r\n\n
[] TYP: MEDICAL APT: * AD: 2638 S SH125 CTY: GREENE COUNTY MAP: R-29 CN: HIGH,KEN & TERRI CMT1: ** STATION 1 ZONE ** CMT2: CLRS WIFE NEEDING LIFT ASSIST TIME: 15:57 UNTS: GF/LRF XST: 8099 E FR154 XST2: BNSFMEMRR RR\r\n\n
[] TYP: FIRE OUTSIDE AD: 10535 N FR33 CTY: GREENE COUNTY MAP: B-4 CN: VERIZON WIRELESS CMT1: FIELD BEHIND ON FIRE CMT2: CAN SEE WHT SMOKE TIME: 16:04 UNTS: GF/LRF XST: 13302 W FR28 XST2: 198 S KENNEDY ST\r\n\n
[] TYP: MEDICAL AD: 727 W LOGAN ST CTY: ROGERSVILLE MAP: T-31 CN: WCSO CMT1: ** STATION 5 ZONE ** CMT2: 46YOA F DIFFICULTY BREATHING. TIME: 01:20 UNTS: GF/LRF XST: 125 N HAWTHORN AVE XST2: 101 N FOXTROT AVE\r\n\n
[] TYP: MEDICAL AD: 1104 E TORREY PINES DR CN: KIM CMT1: REQUESTING MUTUAL AID FROM OZARK FIRE. MALE SUBJ HANGING WITH PLASTIC BAG CMT2: ON HEAD. CROSS STREETS NORTH 10 AVE AND NORTH 12 AVE TIME: 13:12 UNTS: GF/LRF\r\n\n
[] TYP: FIRE ALARM AD: 1915 S FR219 CTY: GREENE COUNTY MAP: Q-27 LOC: WATT/4178825153 CN: OUU CMT1: ** STATION 1 ZONE ** CMT2: FIRE ALARM. ZONE ONE SMOKE DETECTOR. KH ATTEMPTED, NOT REACHED. TIME: 18:49 UNTS: GF/LRF XST: 6597 E FR148 XST2: 2000 S FR221\r\n\n
[] TYP: MEDICAL APT: 61 AD: 7711 E US60 CTY: GREENE COUNTY MAP: T-28 LOC: SILVER BELL MHP CN: MICHEL,LEE & VIRGINI CMT1: ** STATION 1 ZONE ** CMT2: ELDERLY FEMALE IS ILL. CALLER DID NOT ELABORATE. 86 YOA. TIME: 10:51 UNTS: GF/LRF XST: 4992 S FR223 XST2: 4992 S FR229\r\n\n
[] TYP: VEHICLE ACCIDENT AD: E KELLY SPRING LN&S SHNN CTY: GREENE COUNTY MAP: U-25 LOC: SOUTH OF US60 CN: AT&T MOBILITY (TCS) CMT1: ** STATION 2 ZONE ** CMT2: BY THE S CURVE AT CEMETARY. ONE VEH OFF ROADWAY. 2 OCC CONF INJ. TIME: 13:11 UNTS: GF/LRF\r\n\n
[] TYP: VEHICLE ACCIDENT AD: S BLACKMAN RD&E FR156 CTY: GREENE COUNTY MAP: R-23 CN: AT&T MOBILITY (TCS) CMT1: ** STATION 2 ZONE ** CMT2: 2 VEHS. AN OLDER FEMALE WITH INJURIES. CLR NOT ON SCENE. STJ FIRE PD TIME: 14:49 UNTS: GF/LRF\r\n\n

 */


public class MOGreeneCountyParser extends DispatchPrintrakParser {
  
  private static final Pattern FR_PTN = Pattern.compile("\\bFR(?= *\\d)", Pattern.CASE_INSENSITIVE);
  
  public MOGreeneCountyParser() {
    super("GREENE COUNTY", "MO");
  }
  
  @Override
  public String getFilter() {
    return "grnpage@springfieldmo.gov";
  }
  
  @Override
  public boolean parseMsg(String body, Data data) {
    if (!super.parseMsg(body, data)) return false;
    if (data.strCity.equals("CHRISTIAN COUNT")) data.strCity = "CHRISTIAN COUNTY";
    return true;
  }

  @Override
  public String adjustMapAddress(String sAddress) {
    return FR_PTN.matcher(sAddress).replaceAll("FM");
  }
  
  
}
