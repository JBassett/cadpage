package net.anei.cadpage.parsers.IN;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.dispatch.DispatchA6Parser;

/*
St Joseph County, IN
Contact: Eric Yoder <pennfire38@sbcglobal.net>
Sender: rc.425@c-msg.net

Subject:CAD\n[!] ^ 11425 MCKINLEY HW PE :(55231) BELAIR STGAS LEAK FROM PUTING IN A POST 1626,006NIPSCO 1627,014PEN4A\n
Subject:CAD\n[!] ^ 17 RIVERCREST DR OS :( 600) N BEECH RDFROM MEDICAL ALARM COMP UNK MED 1617,006CALL BACK # TO ALARM COMP 800-325-17\n

Contact: Tyler Heckaman <ty.heckaman@gmail.com>
Subject:CAD\n[!] CRASH021 ^ 2 BYPASS HW PE :(60000) ELM RD2 VEH UNK INJ 0613,010ME10 ,PENN1 
Subject:CAD\n[!] COMAL021 ^ 55693 ASH RD PE :(10000) ELSIE HWDUNGARVIN - COMMERCIAL FIRE ALARM - MANUAL 1233,014NOVI 800-877-3624 PRE 
Subject:CAD\n[!] RESAL021 ^ 54895 MONTPELLIER DR PE :(11500) BOISE RUN STRESIDENTIAL FIRE ALARM - HALLWAY SMOKE DETECTOR 0813,014PENN 
Subject:CAD\n[!] ^ 56375 CURRANT RD PE :(13402) ESTHER AVM. HEAD INJ FROM ASSULT 2147,014ME12 ,PENN2 

Contact: "Brian Kazmierzak" <brian@firefighterclosecalls.com>
[!] MEDIC021 ^ 11035 MC*KINLEY HW PE :(55500) BEECH RDFEMALE FELL 0958,006PENN4 
[!] MEDIC021 ^ 11311 BIRCHWAY DR PE :(55730) BIRCH RDF PASSED OUT 0332,014ME12 ,PENN4 5/10/2012 18:19 
[!] 1 MEDIC021 ^ 60101 BODNAR ST PE :(14100) JACKSON RDIN LOT M FALL 1752,006ME10 ,PENN1VERIFY NAME OF THE BUSINESS 10/29/09 NJL 5/10/2012 15:55 
[!] 9 MEDIC021 ^ 16086 DRAGOON TR PE :(59200) SR 331 HWM FIRST PARTY PASSING OUT 1520,006ME10 ,PENN1 
[!] 4 MEDIC ^ 410 LINCOLWAY :( ) OBTAIN MORE LOCATION INFOM. PASSED OUT 1121,014OSCEO,PENN ,ME11 ,1660 
[!] MEDIC021 ^ 60205 BODNAR ST PE :(14100) JACKSON RDM 84 IN A-FIB 0622,010ME12 ,PENN1VERIFY NAME OF THE BUSINESS 10/29/09 NJL 
[!] 8 MEDIC021 ^ 13000 JEFFERSON BL PE :(56200) BOHLSEN STMAN DOWN UNKNOWN MEDICAL- FORM MISHAWAKA 1846,010ME12 
[!] MEDIC021 ^ 56200 MILLER AV PE :(13342) LA SALLE AVF COLLAPSED 0729,006ME12 ,PENN2 
[!] 9 MEDIC021 ^ 12810 JEFFERSON BL PE :(12642) JEFFERSON RDM. POSS INJ FROM ASSULT- STAGE FOR PD 1811,006NO ANSWER ON CALLBACK 2952 1813,006ME10 ,1660 ,PENN2 
[!] 9 CRASH021 ^ 55017 JEFFERY DR PE :(11948) DAY TR1 CAR VS POLE 1414,006ME12 ,PENN4 
[!] 3 MEDIC021 ^ 11285 MCKINLEY HW PE :(55371) BIRCH RDF. LIFT ASSIST 1050,014PENN4 
[!] MEDIC021 ^ 14000 JEFFERSON BL PE :( ) CAPITAL RDSERIOUS NOSE BLEED 0927,006ME12 ,PENN2 
[!] 2 29D02 ^ 60000 SR 331 HW PE :(16000) JACKSON RDMOTORCYCLE ACCIDENT -M. BROKEN LEG 1848,006[M. BROKEN LEG] YOU ARE RESPONDING TO A PATIENT INJURED IN ATRAFFIC INCIDENT. THE PATIENT IS A 28-YEAR-OLD MALE, WHO ISCONSCIOUS AND BREATHING. HIGH 
[!] MEDIC021 ^ 56415 ASH RD PE :(10200) LEHMAN ST16M NECK PX ASSULT 1135,014ME12 ,PENN4 
[!] MEDIC021 ^ 55831 DOGWOOD RD PE :(12900) ERIE STM. COUCHING UP BLOOD 1055,006ME12 ,PENN2 
[!] MEDIC021 ^ 12052 PENN RD PE :(56100) HARMAN DR82 Y/O F. DIZZY 0901,006LIFE LINE - 888 289 2018 PREMISE 574 255 7542 0901,006ME12 ,PENN4 
[!] 5 MEDIC012 ^ 400 N LAMPORT ST OS :( 600) PIERCE STPER 1202 ON SCENE M BLEEDING FROM HEAD FROM BYCYCLE ACC
[!] 3 MEDIC012 ^ 830 GRIFFITH ST OS :( 500) E BARNES STM FELL DOWN BASEMENT STAIRS 2001,0061202 RESPONDING 2002,010[M FELL DOWN BASEMENT STAIRS] YOU ARE RESPONDING TO A PATIENTINVOLVED IN A FALL. THE PATIENT IS A 87-YEAR-OLD MALE, WHOOSCEO,PENN ,
[!] 1 09E01 ^ 10991 IRELAND RD PE :(59500) APPLE RDM. 10-0 1856,006[M 10-0] YOU ARE RESPONDING TO A PATIENT IN APPARENT CARDIAC(RESPIRATORY) ARREST. THE PATIENT IS A 74-YEAR-OLD MALE, WHOIS UNCONSCIOUS AND NOT BREATHING. NOT BREATHING AT ALL. 1856,00 
[!] 7 CRASH021 ^ 14000 KERN RD PE :(60000) ELM RDF. NECK PX-SJCPD ON SCENE 1648,014ME10 ,PENN1 
[!] 5 CRASH021 ^ 56401 ASH RD PE :(10200) LEHMAN ST1 PT NEEDING EVAL STANDARD 1602,0061660 ,PENN4 
[!] 3 FIELD021 ^ 57830 APPLETREE LN PE :(57354) APPLE RDNEIGHBORS FENCE ON FIRE 1512,006OSCEO,PENN3 
[!] 2 MEDIC021 ^ 17850 IRELAND RD PE :(59500) IROWNWOOD RDF SEIXING 1442,014ME10 ,PENN1 
[!] 0 COMAL021 ^ 13366 MCKINLEY HW PE :(55800) FRANCIS AVHOWARDS AUTO SALES 1409,0148772387730 KEYPAD 1410,014PENN RPT # = 12-21000471

Contact: support@active911.com
Sender: rc.588@c-msg.net
(NC)[!] INVES 03/23/12 POPPY LN :( ) OBTAIN MORE LOCATION INFOBETWEEN HICKORY AND HOLLY - UNKNOWN TYPE FIRE
(NC)[!] CRASH009 G-453 03/23/12 30750 SR 2 HW NC :(56000) LARRISON DRPI
(NC)[!] COMAL009 G-428 03/23/12 31869 CHICAGO TR NC :(51452) HAMILTON TRGENERAL FIRE-SIMPLEX SECURITY

*/


public class INStJosephCountyParser extends DispatchA6Parser {
  
  // Pattern we use to try to find the missing space between the cross street and info
  private static Pattern CROSS_BREAK = Pattern.compile("\\)[ A-Z0-9]+ (?:(?:RD|HW)(?! )|(?:ST|AV|TR|DR)(?![AEIOU ]))");
  private static Pattern UNIT_PTN = Pattern.compile(" +([A-Z]-\\d+)");
  
  public INStJosephCountyParser() {
    super(CITY_CODES, "ST JOSEPH COUNTY", "IN");
  }
  
  @Override
  public String getFilter() {
    return "@c-msg.net";
  }
  
  @Override
  protected boolean parseMsg(String body, Data data) {
    
    // These guys don't put a space between the cross street an info field.
    // So to find where it should be and restore it
    Matcher match = CROSS_BREAK.matcher(body);
    if (match.find()) {
      int pt = match.end();
      body = body.substring(0,pt) + " " + body.substring(pt);
    }
    if (! super.parseMsg(body, data)) return false;
    data.strAddress = data.strAddress.replace("*", "");
    match = UNIT_PTN.matcher(data.strCall);
    if (match.find()) {
      data.strUnit = match.group(1);
      data.strCall = data.strCall.substring(0,match.start());
    }
    return true;
  }
  
  private static final Properties CITY_CODES = buildCodeTable(new String[] {
      "CE", "CENTRE TWP",
      "CL", "CLAY TWP",
      "GE", "GERMAN TWP",
      "GR", "GREEN TWP",
      "HA", "HARRIS TWP",
      "MA", "MADISON TWP",
      "MI", "MISHAWAKA",
      "NC", "NEW CARLISLE",
      "ND", "NOTRE DAME",
      "OS", "OSCEOLA",
      "PE", "PENN TWP",
      "PO", "PORTAGE TWP",
      "SB", "SOUTH BEND",
      "SO", "SW CENTRAL",
      "WA", "WARREN TWP",

  });
}
