package net.anei.cadpage.parsers.PA;

import net.anei.cadpage.parsers.MsgInfo.Data;
import net.anei.cadpage.parsers.dispatch.DispatchA1Parser;

/*
Adams County, PA
Contact: Tim Tyler <tyler33fire@yahoo.com>

Subject:Alert: Traffic Accident - 1\nALRM LVL: 1\nLOC:\n274 HANOVER ST\nOXFORD TWP\nBTWN: COMMERCE ST & TROUT LN
Subject:Alert: Fire - Residential\nALRM LVL: 1\nLOC:\n80 HUNTER CIR\nHAMILTON TWP\nBTWN: FOX DEN LN & FOX MEADOW
Subject:Alert: Diabetic Emergency - 1\nALRM LVL: 1\nLOC:\nLINCOLNWAY W/TRACY AVE\nNEW OXFORD BORO\nBTWN: TRACY AVE & KOHLER MILL RD
Subject:Alert: Traffic Accident - 2\nALRM LVL: 1\nLOC:\nYORK RD/FLESHMAN MILL RD\nMOUNT PLEASANT TWP\nBTWN: FLESHMAN MILL RD
Subject:Alert: Fire - Residential\nALRM LVL: 1\nLOC:\n2315 HUNTERSTOWN-HAMPTON RD\nSTRABAN TWP\nBTWN: ALLEY & CONEWAGO RD
Subject:Alert: Traffic Accident - 2\nALRM LVL: 1\nLOC:\n760 NEW CHESTER RD\nSTRABAN TWP\nBTWN: SWIFT RUN RD & BRICKCRAFTERS RD

Contact: Active911
Agency name: Fairfield Fire and EMS Location: Fairfield, PA 
Sender: alert@adams911.com

(Alert: Sick Person - 3) ALRM LVL: 1\nLOC:\n20 RINGNECK TRL\nCARROLL VALLEY BORO\nBTWN: WINTER TRL & FINCH TRL\n\n\n\nCOM:\nHANG UP BEFORE ANSWER\n77-year-old, female, conscious, breathing.\nShe is completely alert (responding appropriately).\nShe is breathing normally.\nShe does not have any pain.\nShe is not bleeding (or vomiting blood).\nShe is unwell/ill.\nDeterminant: 26A10, Suffix: , Response: Class 3\n77-year-old, female, conscious, breathing.   Code: 26-A-10: Unwell/ill\nShe is completely alert (responding appropriately).\nShe is breathing normally.\nShe does not have any pain.\nShe is not bleeding (or vomiting blood).\nShe is unwell/ill.\n\nCT:\nNBCOOK at POS 04
(Alert: Sick Person - 3) ALRM LVL: 1\nLOC:\n24 DEEP POWDER TRL\nB\nCARROLL VALLEY BORO\nBTWN: SNOW BIRD TRL & SUNFISH TRL\n\nRCVD AS 911\n\nCOM:\n21-year-old, female, conscious, breathing.\nShe is completely alert (responding appropriately).\nShe is breathing normally.\nShe has other pain: MOUTH\nShe is not bleeding (or vomiting blood).\nNo priority symptoms (ALPHA conditions 2<128,147>11 not identified).\nNo priority symptoms (OMEGA conditions 2<128,147>28 not identified).\nPROQA Comment: MOUTH\nDeterminant: 26A01, Suffix: , Response: Class 3\n21-year-old, female, conscious, breathing.   Code: 26-A-1: No priority symptoms (complaint conditions 2€“11 not identified)\nShe is completely alert (responding appropriately).\nShe is breathing normally.\nShe has other pain: MOUTH\nShe is not bleeding (or vomiting blood).\nNo priority symptoms (ALPHA conditions 2<128,147>11 not identified).\nNo priority symptoms (OMEGA conditions 2<128,147>28 not identified).\nPROQA Comment: MOUTH\n\nCT:\nMDGROF at POS 03
(Alert: Breathing Problems - 1) ALRM LVL: 1\nLOC:\nDOLLAR GENERAL-FAIRFIELD\n4910 FAIRFIELD RD\nHAMILTONBAN TWP\nBTWN: IRON SPRINGS RD & MCGLAUGHLIN LN\n\n\n\nCOM:\nWHT LIMO\n47-year-old, male, conscious, breathing.\nHe is completely alert (responding appropriately).\nHe does not have any difficulty speaking between breaths.\nHe is not changing color.\nIt''s not known if he is clammy.\nHe does not have asthma.\nN/A\nDeterminant: 06C01, Suffix: , Response: Class 1\n47-year-old, male, conscious, breathing.   Code: 6-C-1: Abnormal breathing\nHe is completely alert (responding appropriately).\nHe does not have any difficulty speaking between breaths.\nHe is not changing color.\nIt''s not known if he is clammy.\nHe does not have asthma.\nN/A\n\nCT:\nPLPETR at POS 04
(Alert: Heart Problems - 1) ALRM LVL: 1\nLOC:\n7 HILLTOP TRL\nCARROLL VALLEY BORO\nBTWN: FAIRFIELD RD & CROSS TRL\n\n\n\nCOM:\n60-year-old, male, conscious, breathing.\nHe is not completely alert (not responding appropriately).\nHe is not breathing normally.\nHe has difficulty speaking between breaths.\nDeterminant: 19D01, Suffix: , Response: Class 1\nHe is not completely alert (not responding appropriately).\nHe is not breathing normally.\nHe has difficulty speaking between breaths.\nHe is changing color.\nHis color change is pale.\nHe is clammy.\nHe has a history of heart problems: STENTS CARDIAC\nHe does not have chest pain.\nHe took a prescribed medication in the past 12 hrs: NEW PILLS AND 1 NITRO\nThe caller was unable to reach the patient to check the pulse rate.\nThe caller tried but was unable to determine his pulse rate.\n60-year-old, male, conscious, breathing.   Code: 19-D-1: Not alert\nHe is not completely alert (not responding appropriately).\nHe is not breathing normally.\nHe has difficulty speaking between breaths.\nHe is changing color.\nHis color change is pale.\nHe is clammy.\nHe has a history of heart problems: STENTS CARDIAC\nHe does not have chest pain.\nHe took a prescribed medication in the past 12 hrs: NEW PILLS AND 1 NITRO\nThe caller was unable to reach the patient to check the pulse rate.\nThe caller tried but was unable to determine his pulse rate.\nPROQA Comment: STENTS CARDIAC\nPROQA Comment: NEW PILLS AND 1 NITRO\n\nCT:\nTATYLE at POS 04
(Alert: Diabetic Emergency - 3) ALRM LVL: 1\nLOC:\n62 WEIKERT RD\nHIGHLAND TWP\nBTWN: FAIRFIELD RD & STOOPS RD\n\n\n\nCOM:\n85-year-old, female, conscious, breathing.\nShe is completely alert (responding appropriately).\nShe is behaving normally now.\nShe is breathing normally.\nDeterminant: 13A01, Suffix: , Response: Class 3\n85-year-old, female, conscious, breathing.   Code: 13-A-1: Alert and behaving normally\nShe is completely alert (responding appropriately).\nShe is behaving normally now.\nShe is breathing normally.\n\nCT:\nKEWHIT at POS 04
(Alert: Traffic Accident - 1) ALRM LVL: 1\nLOC:\n1 MEADOW TRL\nCARROLL VALLEY BORO\nBTWN: VALLEY VIEW TRL & END\n\n\n\nCOM:\n\n\nCT:\nKEWHIT at POS 04
(Alert: Traumatic Injury - 3) ALRM LVL: 1\nLOC:\n13 NOVICE RUN TRL\nCARROLL VALLEY BORO\nBTWN: SNOW BIRD TRL & END\n\nRCVD AS Officer Initiated\n\nCOM:\n\n\nCT:\nTATYLE at POS 03
(Alert: Psych / Suicide - 2) ALRM LVL: 1\nLOC:\nHILLSIDE REST HOME\n1175 OLD WAYNESBORO RD\nHAMILTONBAN TWP\nBTWN: TUNNEL LN & LILAC LN\n\n\n\nCOM:\n61-year-old, male, conscious, breathing.\nHe is not violent.\nHe does not have a weapon.\nThe patient is inside the same structure.\nThis is not a suicide attempt.\nIt''s not known if he is thinking about committing suicide.\nHe is completely alert (responding appropriately).\nDeterminant: 25B06, Suffix: , Response: Class 2\n61-year-old, male, conscious, breathing.   Code: 25-B-6: Unknown status/Other codes not applicable\nHe is not violent.\nHe does not have a weapon.\nThe patient is inside the same structure.\nThis is not a suicide attempt.\nIt''s not known if he is thinking about committing suicide.\nHe is completely alert (responding appropriately).\n\nCT:\nJLSHEN at POS 04
(Alert: Hemorrhage / Laceration - 1) ALRM LVL: 1\nLOC:\n15 WATER ST\nFAIRFIELD BORO\nBTWN: WORTZ DR & POLLY DR\n\n\n\nCOM:\n21-year-old, female, conscious, breathing.\nThe cause of the bleeding is traumatic.\nThe bleeding is from a DANGEROUS area.\nShe is completely alert (responding appropriately).\nShe is breathing normally.\nThere is no blood spurting or pouring out.\nDeterminant: 21D03, Suffix: , Response: Class 1\nThe cause of the bleeding is traumatic.\nThe bleeding is from a DANGEROUS area.\nShe is completely alert (responding appropriately).\nShe is breathing normally.\nThere is no blood spurting or pouring out.\nShe has a bleeding disorder.\n21-year-old, female, conscious, breathing.   Code: 21-D-3: DANGEROUS hemorrhage\nThe cause of the bleeding is traumatic.\nThe bleeding is from a DANGEROUS area.\nShe is completely alert (responding appropriately).\nShe is breathing normally.\nThere is no blood spurting or pouring out.\nShe has a bleeding disorder.\n2A-Switch Status-15 WATER ST, FAIRFIELD BORO\n2A-Dispatched-15 WATER ST, FAIRFIELD BORO\n\nCT:\nJLSHEN at POS 04
(Alert: Psych / Suicide - 2) ALRM LVL: 1\nLOC:\nHILLSIDE REST HOME\n1175 OLD WAYNESBORO RD\nHAMILTONBAN TWP\nBTWN: TUNNEL LN & LILAC LN\n\n\n\nCOM:\n61-year-old, male, conscious, breathing.\nHe is not violent.\nHe does not have a weapon.\nThe patient is inside the same structure.\nThis is not a suicide attempt.\nIt''s not known if he is thinking about committing suicide.\nHe is completely alert (responding appropriately).\nDeterminant: 25B06, Suffix: , Response: Class 2\n61-year-old, male, conscious, breathing.   Code: 25-B-6: Unknown status/Other codes not applicable\nHe is not violent.\nHe does not have a weapon.\nThe patient is inside the same structure.\nThis is not a suicide attempt.\nIt''s not known if he is thinking about committing suicide.\nHe is completely alert (responding appropriately).\n2A-1-Switch Status-(HILLSIDE REST HOME) 1175 OLD WAYNESBORO RD, HAMILTONBAN TWP\n2A-1-Dispatched-(HILLSIDE REST HOME) 1175 OLD WAYNESBORO RD, HAMILTONBAN TWP\n\nCT:\nJLSHEN at POS 04
(Alert: Traffic Accident - 1) ALRM LVL: 1\nLOC:\n608 POPLAR SPRINGS RD\nFRANKLIN TWP\nBTWN: RAGGED EDGE RD & ORCHARD RD\n\n\n\nCOM:\n31-year-old, male, conscious, breathing.\nThe incident involves an all-terrain vehicle or snowmobile.\nDeterminant: 29D02, Suffix: k, Response: Class 1\n\nCT:\nMMSHAN at POS 01
(Alert: Traffic Accident - 1) ALRM LVL: 1\nLOC:\n608 POPLAR SPRINGS RD\nFRANKLIN TWP\nBTWN: RAGGED EDGE RD & ORCHARD RD\n\nRCVD AS 911\n\nCOM:\n31-year-old, male, conscious, breathing.\nThe incident involves an all-terrain vehicle or snowmobile.\nDeterminant: 29D02, Suffix: k, Response: Class 1\nThe incident involves an all-terrain vehicle or snowmobile.\nChemicals or other hazards are not involved.\nThere is no one pinned.\nEveryone appears to be completely awake (alert).\nHis injuries are described as other than to a NOT DANGEROUS area.\nThere is SERIOUS bleeding.\n31-year-old, male, conscious, breathing.   Code: 29-D-2-k: HIGH MECHANISM (k through s) (All-terrain/snowmobile)\nThe incident involves an all-terrain vehicle or snowmobile.\nChemicals or other hazards are not involved.\nThere is no one pinned.\nEveryone appears to be completely awake (alert).\nHis injuries are described as other than to a NOT DANGEROUS area.\nThere is SERIOUS bleeding.\nCH 4\nLIFE NET ON STANBY 10 MIN FLIGHT TIME\nASST 4 REQUESTED 2ND BLS\n\nCT:\nJLSHEN at POS 01
(Alert: Breathing Problems - 1) ALRM LVL: 1\nLOC:\n20 RINGNECK TRL\nCARROLL VALLEY BORO\nBTWN: WINTER TRL & FINCH TRL\n\n\n\nCOM:\n75-year-old, female, conscious, breathing.\nShe is not completely alert (not responding appropriately).\nShe does not have any difficulty speaking between breaths.\nDeterminant: 06D01, Suffix: , Response: Class 1\nShe is not completely alert (not responding appropriately).\nShe does not have any difficulty speaking between breaths.\nShe is not changing color.\nShe is not clammy.\nShe does not have asthma.\nShe has special equipment or instructions to treat this.\nThe special equipment or instructions have been used.\n75-year-old, female, conscious, breathing.   Code: 6-D-1: Not alert\nShe is not completely alert (not responding appropriately).\nShe does not have any difficulty speaking between breaths.\nShe is not changing color.\nShe is not clammy.\nShe does not have asthma.\nShe has special equipment or instructions to treat this.\nThe special equipment or instructions have been used.\n\nCT:\nLMBYER at POS 04
(Alert: Sick Person - 1) ALRM LVL: 1\nLOC:\n215 OLD MILL RD\nCUMBERLAND TWP\nBTWN: LAKE VIEW DR & PARK AVE\n\nRCVD AS 911\n\nCOM:\n64-year-old, female, conscious, breathing.\nShe is delirious.\nShe is breathing normally.\nShe has other pain: NECK AND SHOULDER\nShe is not bleeding (or vomiting blood).\nPROQA Comment: NECK AND SHOULDER\nDeterminant: 26C01, Suffix: , Response: Class 1\nShe is delirious.\nShe is breathing normally.\nShe has other pain: NECK AND SHOULDER\nShe is not bleeding (or vomiting blood).\nNo priority symptoms (ALPHA conditions 2<128,147>11 not identified).\nNo priority symptoms (OMEGA conditions 2<128,147>28 not identified).\n64-year-old, female, conscious, breathing.   Code: 26-C-1: ALTERED LEVEL OF CONSCIOUSNESS\nShe is delirious.\nShe is breathing normally.\nShe has other pain: NECK AND SHOULDER\nShe is not bleeding (or vomiting blood).\nNo priority symptoms (ALPHA conditions 2<128,147>11 not identified).\nNo priority symptoms (OMEGA conditions 2<128,147>28 not identified).\nPROQA Comment: NECK AND SHOULDER\n\nCT:\nGABRET at POS 04
(Alert: Unconscious - 1) ALRM LVL: 1\nLOC:\nACNB BANK - FAIRFIELD\n4910 FAIRFIELD RD\nA\nHAMILTONBAN TWP\nBTWN: IRON SPRINGS RD & MCGLAUGHLIN LN\n\n\n\nCOM:\n30-year-old, male, conscious, breathing.\nHis breathing is not completely normal.\nHe is not completely alert (not responding appropriately).\nHe is changing color.\nHis color change is purple.\nDeterminant: 31D03, Suffix: , Response: Class 1\nPROQA has reconfigured the response\n30-year-old, male, conscious, breathing.   Code: 31-D-4: CHANGING COLOR\nHis breathing is not completely normal.\nHe is not completely alert (not responding appropriately).\nHe is changing color.\nHis color change is purple.\nIt''s not known if he has a history of heart problems.\nReconfigured determinant: 31D04, Suffix: , Response: Class 1\n30-year-old, male, conscious, breathing.   Code: 31-D-4: CHANGING COLOR\nHis breathing is not completely normal.\nHe is not completely alert (not responding appropriately).\nHe is changing color.\nHis color change is purple.\nIt''s not known if he has a history of heart problems.\n\nCT:\nGABRET at POS 04
(Alert: Chest Pain - 1) ALRM LVL: 1\nLOC:\n2006 COLD SPRINGS RD\nHAMILTONBAN TWP\nBTWN: DUG LN & MORITZ RD\n\n\n\nCOM:\n14 YO MALE\n\nCT:\nWEHERR at POS 04
(Alert: Chest Pain - 1) ALRM LVL: 1\nLOC:\n2006 COLD SPRINGS RD\nHAMILTONBAN TWP\nBTWN: DUG LN & MORITZ RD\n\n\n\nCOM:\n14 YO MALE\nIS ALERT COMPLAINING OF CHEST PAIN AND A HIGH HEART RATE\nIS BREATHING  NORMALLY IS NOT CHANGING COLOR\nIS CLAMMY\nPROQA Abort Text: WOULD NOT COME UP ON ORGINAL CALL EMD BY CARD\n\nCT:\nWEHERR at POS 04
(Alert: Hemorrhage / Laceration - 2) ALRM LVL: 1\nLOC:\nHILLSIDE REST HOME\n1175 OLD WAYNESBORO RD\nHAMILTONBAN TWP\nBTWN: TUNNEL LN & LILAC LN\n\nRCVD AS 911\n\nCOM:\n83-year-old, male, conscious, breathing.\nThe cause of the bleeding is non-traumatic.\nThe bleeding is from a NOT DANGEROUS area.\nHe is completely alert (responding appropriately).\nHe is breathing normally.\nThere is SERIOUS bleeding.\nHe does not have a bleeding disorder or is taking blood thinners.\nDeterminant: 21B02, Suffix: , Response: Class 2\n83-year-old, male, conscious, breathing.   Code: 21-B-2: SERIOUS hemorrhage\nThe cause of the bleeding is non-traumatic.\nThe bleeding is from a NOT DANGEROUS area.\nHe is completely alert (responding appropriately).\nHe is breathing normally.\nThere is SERIOUS bleeding.\nHe does not have a bleeding disorder or is taking blood thinners.\n\nCT:\nREEIKE at POS 03
(Alert: Chest Pain - 1) ALRM LVL: 1\nLOC:\n3585 CHAMBERSBURG RD\nFRANKLIN TWP\nBTWN: FLOHRS CHURCH RD & HILLTOWN RD\n\n\n\nCOM:\n18-year-old, female, conscious, breathing.\nShe is completely alert (responding appropriately).\nShe is not breathing normally.\nShe does not have any difficulty speaking between breaths.\nShe is clammy.\nDeterminant: 10D04, Suffix: , Response: Class 1\nShe is completely alert (responding appropriately).\nShe is not breathing normally.\nShe does not have any difficulty speaking between breaths.\nShe is clammy.\nShe has not had a heart attack or angina (heart pains) before.\nShe took a prescribed medication in the past 12 hrs: NORMAL MEDS\n18-year-old, female, conscious, breathing.   Code: 10-D-4: Clammy\nShe is completely alert (responding appropriately).\nShe is not breathing normally.\nShe does not have any difficulty speaking between breaths.\nShe is clammy.\nShe has not had a heart attack or angina (heart pains) before.\nShe took a prescribed medication in the past 12 hrs: NORMAL MEDS\nPROQA Comment: NORMAL MEDS\n\nCT:\nMDGROF at POS 04
(Alert: Traffic Accident - 3) ALRM LVL: 1\nLOC:\nOLD ROUTE 30/ORRTANNA RD\nFRANKLIN TWP\nBTWN: ORRTANNA RD & HIGH ST\n\n\n\nCOM:\nVEHICLE ACCIDENT\n2 VEHICLES\nUNKNOWN INJURIES\nBOTH VEHICLES OFF THE ROADWAY\n\nCT:\nSNCROU at POS 01
(Alert: Traumatic Injury - 3) ALRM LVL: 1\nLOC:\nJACKS MOUNTAIN RD/WAYNESBORO PIKE\nHAMILTONBAN TWP\nBTWN: OLD WAYNESBORO RD & WAYNESBORO PIKE\n\n\n\nCOM:\n\n\nCT:\nSNCROU at POS 04
(Alert: Unconscious - 3) ALRM LVL: 1\nLOC:\n329 HIGH ST\nFRANKLIN TWP\nBTWN: HILLTOWN RD & OLD ROUTE 30\n\n\n\nCOM:\n10-year-old, female, conscious, breathing.\nHer breathing is completely normal.\nShe is completely alert (responding appropriately).\nShe is not changing color.\nShe has no history of heart problems.\nDeterminant: 31A03, Suffix: , Response: Class 3\n10-year-old, female, conscious, breathing.   Code: 31-A-3: Fainting episode(s) and alert < 35 (without cardiac history)\nHer breathing is completely normal.\nShe is completely alert (responding appropriately).\nShe is not changing color.\nShe has no history of heart problems.\n\nCT:\nCAHAGE at POS 04
(Alert: Heart Problems - 1) ALRM LVL: 1\nLOC:\nGRANITE HILL CAMPGROUND & ADVENTURE GOLF\n3340 FAIRFIELD RD\nHIGHLAND TWP\nBTWN: CARR HILL RD & COLD SPRINGS RD\n\nRCVD AS 911\n\nCOM:\n59-year-old, male, conscious, breathing.\nHe is completely alert (responding appropriately).\nHe is breathing normally.\nHe is not clammy.\nHe has a history of heart problems: 8 STINTS\nHe has chest pain.\nHe took a prescribed medication in the past 12 hrs: 3 NITRO PILLS TO RELIEVE CHEST PAINS\nPROQA Comment: 8 STINTS\nPROQA Comment: 3 NITRO PILLS TO RELIEVE CHEST PAINS\nDeterminant: 19C03, Suffix: , Response: Class 1\nHe is completely alert (responding appropriately).\nHe is breathing normally.\nHe is not clammy.\nHe has a history of heart problems: 8 STINTS\nHe has chest pain.\nHe took a prescribed medication in the past 12 hrs: 3 NITRO PILLS TO RELIEVE CHEST PAINS\nInstructions for taking a pulse have been given.\nHis pulse is between 50 and 129 beats per minute.\n\nCT:\nREEIKE at POS 04
(Alert: Falls - 2) ALRM LVL: 1\nLOC:\n47 SNOW BIRD TRL\nCARROLL VALLEY BORO\nBTWN: NOVICE RUN TRL & END\n\nRCVD AS 911\n\nCOM:\n\n\nCT:\nREEIKE at POS 04
(Alert: Falls - 2) ALRM LVL: 1\nLOC:\n205 CARROLLS TRACT RD\nHAMILTONBAN TWP\nBTWN: BULLFROG RD & SUGAR LOAF LN\n\nRCVD AS 911\n\nCOM:\nEMD CALL\n94-year-old, female, conscious, breathing.\nThis happened now (less than 6hrs ago).\nIt''s reported that she fell at ground level.\nThe fall was accidental.\nThere is no bleeding now.\nShe is completely alert (responding appropriately).\nThe injury is to the chest.\nShe is not having difficulty breathing.\nShe is no longer on the floor (ground).\nDeterminant: 17B01, Suffix: , Response: Class 2\n94-year-old, female, conscious, breathing.   Code: 17-B-1: POSSIBLY DANGEROUS body area\nThis happened now (less than 6hrs ago).\nIt''s reported that she fell at ground level.\nThe fall was accidental.\nThere is no bleeding now.\nShe is completely alert (responding appropriately).\nThe injury is to the chest.\nShe is not having difficulty breathing.\nShe is no longer on the floor (ground).\n\nCT:\nTATYLE at POS 04
(Alert: Traumatic Injury - 2) ALRM LVL: 1\nLOC:\n45 ORRTANNA RD\nFRANKLIN TWP\nBTWN: OLD ROUTE 30 & GREENFIELD LN\n\nRCVD AS 911\n\nCOM:\nLOWER BACK AND HEAD INJURY\n\nCT:\nLRSORD at POS 04
(Alert: Traffic Accident - 2) ALRM LVL: 1\nLOC:\nGRANITE HILL CAMPGROUND & ADVENTURE GOLF\n3340 FAIRFIELD RD\nHIGHLAND TWP\nBTWN: CARR HILL RD & COLD SPRINGS RD\n\n\n\nCOM:\n3 CARS ONE PERSON STILL IN THE VEHICLE\n\nCT:\nKEDEHO at POS 04
(Alert: Convulsions - 1) ALRM LVL: 1\nLOC:\n6475 FAIRFIELD RD\nCARROLL VALLEY BORO\nBTWN: STINE TRL & WAYNESBORO PIKE\n\n\n\nCOM:\n19-year-old, female, conscious, breathing.\nThis is apparently a generalized (grand mal) seizure.\nShe has not had more than one seizure in a row.\nShe is not pregnant.\nIt''s not known if she is diabetic.\nShe is not an epileptic and has not had seizures before.\nThe jerking (twitching) has not stopped.\nDeterminant: 12D02, Suffix: , Response: Class 1\n\nCT:\nMDGROF at POS 03
(Alert: Traffic Accident - 1) ALRM LVL: 1\nLOC:\nPNC BANK-ZORA\n815 WAYNESBORO PIKE\nCARROLL VALLEY BORO\nBTWN: FAIRFIELD RD & AUTUMN TRL\n\nRCVD AS 911\n\nCOM:\n\n\nCT:\nKNPARR at POS 04
(Alert: Traffic Accident - 1) ALRM LVL: 1\nLOC:\nCHAMBERSBURG RD/HIGH ST\nFRANKLIN TWP\nBTWN: HIGH ST & SHORT CUT RD\n\n\n\nCOM:\n\n\nCT:\nKNPARR at POS 04
(Alert: Alarm Fire - Residential) ALRM LVL: 1\nLOC:\n451 STOOPS RD\nHIGHLAND TWP\nBTWN: CAMP GETTYSBURG RD & WEIKERT RD\n\nRCVD AS 911\n\nCOM:\nGEN FIRE ALARM\n\nCT:\nMDGROF at POS 04
(Alert: Unconscious - 1) ALRM LVL: 1\nLOC:\n121 W MAIN ST\nSECOND FLOOR\nFAIRFIELD BORO\nBTWN: SIXTH AVE & SEVENTH ST\n\n\n\nCOM:\n50-year-old, female, not conscious, breathing.\nHer breathing is not completely normal.\nHer breathing is abnormal but effective.\nShe is still unconscious.\nPROQA Comment: Rate  = 10 breaths per minute\nPROQA Comment: Abnormal or Irregular*\nDeterminant: 31D02, Suffix: , Response: Class 1\n50-year-old, female, not conscious, breathing.   Code: 31-D-2: Unconscious €“ Effective breathing\nHer breathing is not completely normal.\nHer breathing is abnormal but effective.\nShe is still unconscious.\nPROQA Comment: Rate  = 10 breaths per minute\nPROQA Comment: Abnormal or Irregular*\nFOUND MOTHER IN BATHROOM UNCONSCIOUS\nBUT BREATHING\nUNKN HOW LONG SHE HAS BEEN UNCONSCIOUS\n\nCT:\nPAHAGE at POS 02
(Alert: Sick Person - 3) ALRM LVL: 1\nLOC:\n932 ORCHARD RD\nFRANKLIN TWP\nBTWN: RODNEY LEE DR & POPLAR SPRINGS RD\n\n\n\nCOM:\n75-year-old, female, conscious, breathing.\nShe is completely alert (responding appropriately).\nShe is breathing normally.\nShe does not have any pain.\nShe is not bleeding (or vomiting blood).\nHer primary problem is nausea.\nDeterminant: 26A06, Suffix: , Response: Class 3\n\nCT:\nPAHAGE at POS 04
(Alert: Hemorrhage / Laceration - 2) ALRM LVL: 1\nLOC:\n6 BLACK BASS TRL\nCARROLL VALLEY BORO\nBTWN: SKI RUN TRL & BLUE GILL TRL\n\n\n\nCOM:\n89-year-old, female, conscious, breathing.\nThe cause of the bleeding is non-traumatic.\nThe bleeding is from a NOT DANGEROUS area.\nShe is completely alert (responding appropriately).\nShe is breathing normally.\nThere is SERIOUS bleeding.\nShe takes blood thinners.\nDeterminant: 21B02, Suffix: , Response: Class 2\n89-year-old, female, conscious, breathing.   Code: 21-B-2: SERIOUS hemorrhage\nThe cause of the bleeding is non-traumatic.\nThe bleeding is from a NOT DANGEROUS area.\nShe is completely alert (responding appropriately).\nShe is breathing normally.\nThere is SERIOUS bleeding.\nShe takes blood thinners.\n\nCT:\nPAHAGE at POS 04
(Alert: Traumatic Injury - 2) ALRM LVL: 1\nLOC:\n4 MILE TRL\nCARROLL VALLEY BORO\nBTWN: RANCH TRL & PALOMINO TRL\n\n\n\nCOM:\n4-year-old, female, conscious, breathing.\nThis happened now (less than 6hrs ago).\nThere is SERIOUS bleeding.\nShe is completely alert (responding appropriately).\nThe injury is to a POSSIBLY DANGEROUS area.\nDeterminant: 30B01, Suffix: , Response: Class 2\n4-year-old, female, conscious, breathing.   Code: 30-B-1: POSSIBLY DANGEROUS body area\nThis happened now (less than 6hrs ago).\nThere is SERIOUS bleeding.\nShe is completely alert (responding appropriately).\nThe injury is to a POSSIBLY DANGEROUS area.\n\nCT:\nPAHAGE at POS 04
(Alert: Alarm Fire - Residential) ALRM LVL: 1\nLOC:\n451 STOOPS RD\nHIGHLAND TWP\nBTWN: CAMP GETTYSBURG RD & WEIKERT RD\n\nRCVD AS 911\n\nCOM:\n\n\nCT:\nLMBYER at POS 04
(Alert: Alarm Fire - Residential) ALRM LVL: 1\nLOC:\n451 STOOPS RD\nHIGHLAND TWP\nBTWN: CAMP GETTYSBURG RD & WEIKERT RD\n\nRCVD AS 911\n\nCOM:\nDISP CH 3\nGENERAL FIRE ALARM\nALARM CO ADV NO CONTACT\nSU2-Switch Status-451 STOOPS RD, HIGHLAND TWP\nSU2-Enroute-451 STOOPS RD, HIGHLAND TWP\n\nCT:\nLMBYER at POS 04
(Alert: Chest Pain - 1) ALRM LVL: 1\nLOC:\n11 PECAN TRL\nCARROLL VALLEY BORO\nBTWN: PEACH TREE TRL & END\n\n\n\nCOM:\n78-year-old, male, conscious, breathing.\nHe is completely alert (responding appropriately).\nHe is breathing normally.\nHe is changing color.\nHis color change is pale.\nHe is clammy.\nDeterminant: 10D04, Suffix: , Response: Class 1\nHe is completely alert (responding appropriately).\nHe is breathing normally.\nHe is changing color.\nHis color change is pale.\nHe is clammy.\nHe has had a heart attack before.\nHe took a prescribed medication in the past 12 hrs: REGULAR MEDS\n78-year-old, male, conscious, breathing.   Code: 10-D-4: Clammy\nHe is completely alert (responding appropriately).\nHe is breathing normally.\nHe is changing color.\nHis color change is pale.\nHe is clammy.\nHe has had a heart attack before.\nHe took a prescribed medication in the past 12 hrs: REGULAR MEDS\nPROQA Comment: REGULAR MEDS\n\nCT:\nKEDEHO at POS 04

*/

public class PAAdamsCountyParser extends DispatchA1Parser {
  
  public PAAdamsCountyParser() {
    super("ADAMS COUNTY", "PA");
  }

  @Override
  public String getFilter() {
    return"alert@adams911.com";
  }

  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    if (!super.parseMsg(subject, body, data)) return false;
    String city = data.strCity;
    if (city.toUpperCase().endsWith(" BORO")) {
      data.strCity = city.substring(0,city.length()-5).trim();
    }
    data.strSupp = data.strSupp.replace(" / ", "\n");
    return true;
  }
}
