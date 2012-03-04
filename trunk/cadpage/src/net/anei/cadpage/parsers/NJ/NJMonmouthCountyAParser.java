package net.anei.cadpage.parsers.NJ;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;


/*
Monmouth County, NJ
Contact: support@active911.com
Sender: @MCSONJ.ORG

(<mcsoza7@MCSONJ.ORG>) S: Automatic R&R Notification\n** ** ** ** ** ** ** ** ** ** ** ** FINAL REPORT ** ** ** ** ** ** ** ** ** ** ** **  \nIncident Number  : 2012-00000042                    ORI: 26-2          Station: STA 26-2  \nIncident Type  . : FIRS F FIRE STRUC           Priority: 1  \nIncident Location: 90 OAKLAND MILLS RD                                  Venue: MANALAPAN \n\nCall Time- 17:25:27                                Date- 01/19/2012  \nDispatch - 17:25:50     En-route- 17:29:37     On-scene- 17:31:16     Depart 1-  \nArrive 2 -              Depart 2-              In-statn-              Cleared - 18:18:23 \n\nArea: 26A4              Section :   33         Beat  . : 26A4  \nGrid:                   Quadrant: 262S         District: E262 \n\nPhone Number: (732) 308-4591                Call Source:  911 \n\nCaller. . . : ROB YUHAS \n\nUnits sent  :  \n 12-1        F12A                 26-2        F262A  \n 32-1        F32A                 12-1        1266  \n 12-1        1269                 12-1        1274  \n 12-1        1275                 26-2        26286  \n 32-1        3266                 32-1        3290  \n 32-1        3296                 26-2        26266  \n 26-2        26267                26-2        26290 \n\nNature of Call : CHIMNEY FIRE/ ADVISED TO EVACUATE \n\nAdditional Info  \n  RESD: \n\nNarrative  \n Information on the units assigned to the call follows.  \n     Unit#: F12A   Radio#:        Ofcr 1:             Ofcr 2:  \n        DSP: 01/19/12 17:25   ENR:            :    ARV:            :  \n        DPT:            :     AR2:            :    DP2:            :  \n        QTR:            :     CLR: 01/19/12 17:30  \n     Unit#: 1266   Radio#:        Ofcr 1:             Ofcr 2:  \n        DSP: 01/19/12 17:29   ENR: 01/19/12 17:29  ARV: 01/19/12 17:36  \n        DPT:            :     AR2:            :    DP2:            :  \n        QTR:            :     CLR: 01/19/12 18:18  \n     Unit#: 1274   Radio#:        Ofcr 1:             Ofcr 2:  \n        DSP: 01/19/12 17:33   ENR: 01/19/12 17:33  ARV: 01/19/12 17:54  \n        DPT:            :     AR2:            :    DP2:            :  \n        QTR:            :     CLR: 01/19/12 18:18  \n     Unit#: 1275   Radio#:        Ofcr 1:             Ofcr 2:  \n        DSP: 01/19/12 17:34   ENR: 01/19/12 17:34  ARV: 01/19/12 17:40  \n        DPT:            :     AR2:            :    DP2:            :  \n        QTR:            :     CLR: 01/19/12 18:18  \n     Unit#: 1269   Radio#:        Ofcr 1:             Ofcr 2:  \n        DSP: 01/19/12 17:34   ENR: 01/19/12 17:34  ARV: 01/19/12 17:39  \n        DPT:            :     AR2:            :    DP2:            :  \n        QTR:            :     CLR: 01/19/12 18:18  \n     Unit#: F262A  Radio#:        Ofcr 1:             Ofcr 2:  \n        DSP: 01/19/12 17:25   ENR:            :    ARV:            :  \n        DPT:            :     AR2:            :    DP2:            :  \n        QTR:            :     CLR: 01/19/12 17:30  \n     Unit#: 26286  Radio#:        Ofcr 1:             Ofcr 2:  \n        DSP: 01/19/12 17:29   ENR: 01/19/12 17:29  ARV: 01/19/12 17:54  \n        DPT:            :     AR2:            :    DP2:            :  \n        QTR:            :     CLR: 01/19/12 18:18  \n     Unit#: 26266  Radio#:        Ofcr 1:             Ofcr 2:  \n        DSP: 01/19/12 17:29   ENR: 01/19/12 17:29  ARV: 01/19/12 17:31  \n        DPT:            :     AR2:            :    DP2:            :  \n        QTR:            :     CLR: 01/19/12 18:18  \n     Unit#: 26267  Radio#:        Ofcr 1:             Ofcr 2:  \n        DSP: 01/19/12 17:29   ENR: 01/19/12 17:29  ARV: 01/19/12 17:34  \n        DPT:            :     AR2:            :    DP2:            :  \n        QTR:            :     CLR: 01/19/12 18:18  \n     Unit#: 26290  Radio#:        Ofcr 1:             Ofcr 2:  \n        DSP: 01/19/12 17:33   ENR: 01/19/12 17:33  ARV: 01/19/12 17:36  \n        DPT:            :     AR2:            :    DP2:            :  \n        QTR:            :     CLR: 01/19/12 18:18  \n     Unit#: F32A   Radio#:        Ofcr 1:             Ofcr 2:  \n        DSP: 01/19/12 17:25   ENR:            :    ARV:            :  \n        DPT:            :     AR2:            :    DP2:            :  \n        QTR:            :     CLR: 01/19/12 17:30  \n     Unit#: 3290   Radio#:        Ofcr 1:             Ofcr 2:  \n        DSP: 01/19/12 17:29   ENR: 01/19/12 17:29  ARV: 01/19/12 17:36  \n        DPT:            :     AR2:            :    DP2:            :  \n        QTR:            :     CLR: 01/19/12 18:08  \n     Unit#: 3266   Radio#:        Ofcr 1:             Ofcr 2:  \n        DSP: 01/19/12 17:37   ENR: 01/19/12 17:37  ARV: 01/19/12 17:45  \n        DPT:            :     AR2:            :    DP2:            :  \n        QTR:            :     CLR: 01/19/12 18:08  \n     Unit#: 3296   Radio#:        Ofcr 1:             Ofcr 2:  \n        DSP:            :     ENR:            :    ARV: 01/19/12 17:40  \n        DPT:            :     AR2:            :    DP2:            :  \n        QTR:            :     CLR: 01/19/12 18:08  \n PD 26 NTFD  \n CODE 2  \n 26 21

(<mcsoz6@MCSONJ.ORG>) S:Automatic R&R Notification\n\nIncident Number  : 2012-00000040                    ORI: 26-2          Station: STA 26-2\nIncident Type  . : CRBA F CARB MONOX           Priority: 1\nIncident Location: 686 ST ANDREWS PL                                    Venue: MANALAPAN\nLocated Between  : SAWGRASS DR/\n\nCall Time- 21:28:10                                Date- 01/18/2012\nDispatch -              En-route-              On-scene-              Depart 1-\nArrive 2 -              Depart 2-              In-statn-\n\nArea: 26A4              Section :   29         Beat  . : 26A4        Map . . :\nGrid:                   Quadrant: F262         District: E262\n\nPhone Number: (908) 770-0906                Call Source:  911\n\nCaller. . . : JESSICA SEAGULL\n\nNature of Call : CO ALARM SOUNDING/HAS GAS FIREPLACE ON EARLIE\n\nAdditional Info\n  WPH1:  RADIUS 01 MILES\n  R PD 26 NTFD\n\nAdditional Inc#s:\n\nThe Call Taker is FRUEH DOUGLAS\nThe Dispatcher is MCCORMICK KIEL
(<mcsoz6@MCSONJ.ORG>) S:Automatic R&R Notification\nIncident Number : 2012-00000039 ORI: 26-2\nStation: STA 26-2\nIncident Type . : FIRA F FIRE ALARM Priority: 1\nIncident Location: 103 PENSION RD\nVenue: MANALAPAN\nLocated Between : GORDONS CORNER RD/PINE BROOK RD\nCommon Name. . . : WESTERN MONMOUTH UTILITIES\n\nCall Time- 20:50:27 Date- 01/17/2012\nDispatch - 20:51:05 En-route- On-scene- Depart\n1-\nArrive 2 - Depart 2- In-statn-\n\nArea: 26A3 Section : 20 Beat . : 26A3 Map . .:\nGrid: Quadrant: 1226 District: E262\n\nPhone Number: (800) 434-4000 Call Source: TEL\n\nCaller. . . : COMPLETE SEC MJ01\n\nUnits sent :\n12-1 F12A 26-2 F262A\n\nNature of Call : WESTERN MONMOUTH UTILITIES ZONE//FILTRATION\n\nAdditional Info\nREF#3231017\nBLDG PREM//732-614-1586\n\nNarrative\nPD NTFD CMCCARTH 20:50:41\nALARM CO ATT TO NTFY KH CMCCARTH 20:50:45\n\nAdditional Inc#s:\n12-1 201200000035\n\nThe Call Taker is MCCARTHY CHRISTINE\nThe Dispatcher is KNIGHTON JUSTIN
(<MCSOZD2@MCSONJ.ORG>) S:Automatic R&R Notification\nIncident Number  : 2012-00000042                    ORI: 26-2          Station: STA 26-2  \nIncident Type  . : FIRS F FIRE STRUC           Priority: 1  \nIncident Location: 90 OAKLAND MILLS RD                                  Venue: MANALAPAN  \nLocated Between  : MILL RD/ \n\nCall Time- 17:25:27                                Date- 01/19/2012  \nDispatch -              En-route-              On-scene-              Depart 1-  \nArrive 2 -              Depart 2-              In-statn- \n\nArea: 26A4              Section :   33         Beat  . : 26A4        Map . . :  \nGrid:                   Quadrant: 262S         District: E262 \n\nPhone Number: (732) 308-4591                Call Source:  911 \n\nCaller. . . : ROB YUHAS \n\nNature of Call : CHIMNEY FIRE/ ADVISED TO EVACUATE \n\nAdditional Info  \n  RESD: \n\nAdditional Inc#s: \n\nThe Call Taker is DALY EVAN  \nThe Dispatcher is BRUNO CHRISTOPHER
(<MCSOZD2@MCSONJ.ORG>) S: Automatic R&R Notification\nIncident Number  : 2012-00000043                    ORI: 26-2          Station: STA 26-2  \nIncident Type  . : CRBA F CARB MONOX           Priority: 1  \nIncident Location: 41 YATES RD                                          Venue: MANALAPAN  \nLocated Between  : SALTER CT/CRAWFORD RD \n\nCall Time- 22:31:12                                Date- 01/19/2012  \nDispatch - 22:31:47     En-route-              On-scene-              Depart 1-  \nArrive 2 -              Depart 2-              In-statn- \n\nArea: 26A4              Section :   34         Beat  . : 26A4        Map . . :  \nGrid:                   Quadrant: F262         District: E262 \n++\nPhone Number: (732) 462-4033                Call Source:  TEL \n\nCaller. . . : NICHOLAS VULTIS \n\nUnits sent  :  \n 26-2        F262A \n\nNature of Call : CO ALARM SOUNDING/RESIDENTS EVACUATING/NO \n\nAdditional Info  \n  SYMPTOMS - JUST GOT HOME FROM VACATION \n\nNarrative  \n CODE 3                                            RMCDONAL   22:31:16  \n MCRR NOTIFIED FOR FIRE DEPT                       RMCDONAL   22:31:31 \n\nAdditional Inc#s: \n\nThe Call Taker is MCDONALD ROBIN  \nThe Dispatcher is BRUNO CHRISTOPHER
(<mcson6@MCSONJ.ORG>) S: Automatic R&R Notification\nIncident Number  : 2012-00000044                    ORI: 26-2          Station: STA 26-2  \nIncident Type  . : FIRA F FIRE ALARM           Priority: 1  \nIncident Location: 327 ST HWY 33                                        Venue: MANALAPAN  \nLocated Between  : WOODWARD RD/IRON ORE RD  \nCommon Name. . . : MARRIANE MANOR LLC \n\nCall Time- 23:21:19                                Date- 01/19/2012  \nDispatch - 23:22:20     En-route-              On-scene-              Depart 1-  \nArrive 2 -              Depart 2-              In-statn- \n\nArea: 26A4              Section :   30         Beat  . : 26A4        Map . . :    E  \nGrid:                   Quadrant: F262         District: E262 \n\nPhone Number: (000) 000-0000                Call Source:  TEL \n\nUnits sent  :  \n 26-2        F262A \n\nNature of Call : FIRE ALARM/ASSOC FIRE PROTECTION 8009323822 \n\nAdditional Info  \n  SRN #75548 \n\nNarrative  \n PD 26 CALLED IN                                   DMELLACI   23:22:20 \n\nAdditional Inc#s: \n\nThe Call Taker is NIGRO-CIOFFI MARIA  \nThe Dispatcher is HORN EDWARD
(<mcsozb8@MCSONJ.ORG>) S: Automatic R&R Notification\nIncident Number  : 2012-00000046                    ORI: 26-2          Station: STA 26-2  \nIncident Type  . : CRBA F CARB MONOX           Priority: 1  \nIncident Location: 4 SUMMIT AV                                          Venue: MANALAPAN  \nLocated Between  : SWEETMANS LN/ \n\nCall Time- 14:52:48                                Date- 01/22/2012  \nDispatch -              En-route-              On-scene-              Depart 1-  \nArrive 2 -              Depart 2-              In-statn- \n\nArea: 26A4              Section :   32         Beat  . : 26A4        Map . . :  \nGrid:                   Quadrant: 26SS         District: E262 \n\nPhone Number: (732) 845-4737                Call Source:  911 \n\nCaller. . . : DAWN HIGGINS \n\nNature of Call : NO SYMPTOMS. PD NTFD \n\nAdditional Info  \n  RESD: \n\nAdditional Inc#s: \n\nThe Call Taker is MAGNIN KARIN  \nThe Dispatcher is WARD JOSEPH
(<mcson6@MCSONJ.ORG>) S: Automatic R&R Notification\nIncident Number  : 2012-00000104                    ORI: 16-1          Station: STA 16-1  \nIncident Type  . : FIRS F FIRE STRUC           Priority: 1  \nIncident Location: 103 SILOAM RD                                        Venue: FRHLD TWP  \nLocated Between  : HENDRICKSON RD/TURKEY SWAMP RD \n\nCall Time- 02:08:05                                Date- 01/24/2012  \nDispatch - 02:21:29     En-route- 02:21:29     On-scene-              Depart 1-  \nArrive 2 -              Depart 2-              In-statn- \n\nArea: 1600              Section : 1796         Beat  . : FT03        Map . . :  \nGrid:                   Quadrant: 1611         District: E151 \n\nPhone Number: (908) 770-5342                Call Source:  911 \n\nCaller. . . : VERIZON WIRELESS \n\nUnits sent  :  \n 32-1        F32A                 16-1        F161B  \n 32-1        3266                 32-1        3275  \n 32-1        3296                 16-1        16166  \n 16-1        16167                16-1        16168  \n 16-1        16169                16-1        16170  \n 16-1        16171                16-1        16177  \n 16-1        16179                16-1        16190  \n 16-1        16196                26-2        26266  \n D19         19266                D19         19296 \n\nNature of Call : HOUSE ON FIRE//SEES FLAMES NEAR #95 \n\nAdditional Info  \n  WPH1:  RADIUS 01 MILES \n\nNarrative  \n PASSERBY//CHECKING TO SEE IF ANYONE IS HOME       LCIVELLO    2:08:29  \n NO CARS IN DRIVE//MAY BE ABANDOONED               LCIVELLO    2:08:40  \n ON SIDE OF HOUSE BY CHIMNEY                       LCIVELLO    2:08:49  \n THERES AN OIL TANK THERE ALSO                     LCIVELLO    2:08:55  \n TOTALLY DARK//BANGED ON WINDOWS                   LCIVELLO    2:09:11  \n NO ONE IS ANSWERING                               LCIVELLO    2:09:23  \n HOUSE #103                                        LCIVELLO    2:09:35  \n MIKE KILKENNY 908-770-5342                        LCIVELLO    2:10:10  \n CALLER HAS TO GO TO WORK AND IS LEAVING           LCIVELLO    2:10:28  \n REQ 32-1                                          EHORN       2:10:58  \n NJNG BEING NTFD                                   CMCCARTH    2:11:33  \n NTFY JCP&L                                        NMURACO     2:12:10  \n NJNG COMING FROM WALL                             CMCCARTH    2:12:23  \n 19-2 FOR RIT AS PER 1-67                          EHORN       2:12:25  \n JCP&L NTFD                                        NMURACO     2:13:39  \n 1 HOUR #325382367                                 NMURACO     2:15:43  \n HOUSE FULLY INVOLVED PER PTLS                     JFERA       2:15:59  \n PTLS EVACA SURROUNDING HOUSES                     JFERA       2:16:47  \n SILOAM RD S AT RT 537                             JFERA       2:17:16  \n TO BE CLOSED BY PD 26                             JFERA       2:17:44  \n 16-1-66 REQ 26-2 FOR TANKER                       EHORN       2:17:52  \n FIRE DEPT MEMBER ON LOC PER PTLS                  JFERA       2:17:55  \n FIRE TRUCK 10-9 PER PTLS                          JFERA       2:18:07  \n 32-1 2ND ALERT                                    EHORN       2:18:56  \n 19-2 FOR THE TANKER                               NMURACO     2:19:09  \n 19-1 FOR TANKER                                   EHORN       2:19:25  \n 19-2 FOR TANKER                                   EHORN       2:19:59  \n 2658 ON LOC FOR STAND BY                          JFERA       2:21:09 \n\nAdditional Inc#s:  \n D19         201200000023             26-2        201200000048  \n 32-1        201200000047 \n\nThe Call Taker is CIVELLO LORI MARIE  \nThe Dispatcher is HORN EDWARD
(<MCSOZC2@MCSONJ.ORG>) S: Automatic R&R Notification\nIncident Number  : 2012-00000057                    ORI: 26-2          Station: STA 26-2  \nIncident Type  . : FIRS F FIRE STRUC           Priority: 1  \nIncident Location: AVALON LA                                            Venue: MANALAPAN  \nLocated Between  : SPRINGHOUSE CI/ \n\nCall Time- 18:59:46                                Date- 01/27/2012  \nDispatch -              En-route-              On-scene-              Depart 1-  \nArrive 2 -              Depart 2-              In-statn- \n\nArea: 26A4              Section :   31         Beat  . : 26A4        Map . . :  \nGrid:                   Quadrant: 1226         District: E262 \n\nPhone Number: (000) 000-0000                Call Source:  TEL \n\nNature of Call : ACROSS FROM 13 NO HOUSE NUMBER//SEES FLAMES \n\nAdditional Info  \n  SRN \n\nNarrative  \n CODE 2                                            DERNDL     19:00:16  \n PD 26 CALLED IN                                   MKATZ      19:00:43 \n\nAdditional Inc#s: \n\nThe Call Taker is ERNDL DOREEN  \nThe Dispatcher is DALY EVAN
(<mcsoo8@MCSONJ.ORG>) S: Automatic R&R Notification\nIncident Number  : 2012-00000055                    ORI: 26-2          Station: STA 26-2  \nIncident Type  . : FIRS F FIRE STRUC           Priority: 1  \nIncident Location: 15 APPLE BLOSSOM LN BLOSSUM                          Venue: MANALAPAN  \nLocated Between  : THOMPSON GROVE RD/ \n\nCall Time- 21:22:19                                Date- 01/26/2012  \nDispatch - 21:22:35     En-route-              On-scene-              Depart 1-  \nArrive 2 -              Depart 2-              In-statn- \n\nArea: 26A4              Section :   35         Beat  . : 26A4        Map . . :  \nGrid:                   Quadrant: F262         District: E262 \n\nPhone Number: (732) 744-4549                Call Source:  911 \n\nCaller. . . : VALENTINA SEAMAN \n\nUnits sent  :  \n 26-2        F262A \n\nNature of Call : FIRE IN GARAGE \n\nAdditional Info  \n  WPH1:  RADIUS 01 MILES \n\nAdditional Inc#s:  \n 12-1        201200000046 \n\nThe Call Taker is SMITH BRUCE  \nThe Dispatcher is DONATELLI ROBERT
(<MCSOZ3@MCSONJ.ORG>) S: Automatic R&R Notification\nIncident Number  : 2012-00000051                    ORI: 26-2          Station: STA 26-2  \nIncident Type  . : FIRA F FIRE ALARM           Priority: 1  \nIncident Location: 124 SHINNECOCK DR                                    Venue: MANALAPAN  \nLocated Between  : MUIRFIELD DR/ \n\nCall Time- 15:31:14                                Date- 01/26/2012  \nDispatch -              En-route-              On-scene-              Depart 1-  \nArrive 2 -              Depart 2-              In-statn- \n\nArea: 26A4              Section :   29         Beat  . : 26A4        Map . . :  \nGrid:                   Quadrant: 1226         District: E262 \n\nPhone Number: (732) 792-1073                Call Source:  911 \n\nCaller. . . : GERARD LAINO \n\nNature of Call : LEFT POT ON STOVE//HOUSE FILLED W/SMOKE \n\nAdditional Info  \n  RESD: \n\nNarrative  \n PD NTFD                                           SPRINCIN   15:31:35 \n\nAdditional Inc#s: \n\nThe Call Taker is PRINCING SHARON  \nThe Dispatcher is SPANO MICHAEL
(<MCSOZ3@MCSONJ.ORG>) S: Automatic R&R Notification\nIncident Number  : 2012-00000066                    ORI: 16-2          Station: STA 16-2  \nIncident Type  . : FIRA F FIRE ALARM           Priority: 1  \nIncident Location: 4189 N RT 9                                          Venue: FRHLD TWP  \nLocated Between  : RT 522/SCHIBANOFF RD  \nCommon Name. . . : BROCK FARMS \n\nCall Time- 15:30:16                                Date- 01/26/2012  \nDispatch - 15:34:22     En-route- 15:34:22     On-scene-              Depart 1-  \nArrive 2 -              Depart 2-              In-statn- \n\nArea: 1600              Section : 1797         Beat  . : FT01        Map . . :    N  \nGrid:                   Quadrant: 1626         District: E151 \n\nPhone Number: (908) 810-8833                Call Source:  TEL \n\nCaller. . . : OPER MAGGIE \n\nUnits sent  :  \n 16-2        F162A                26-2        26266 \n\nNature of Call : BUSN: BROCK FARMS // ZONE: GENERAL \n\nAdditional Info  \n  SUPREME SECURITY  \n  PREMIS: 732-462-2700 \n\nNarrative  \n P16180 ADVISES NO SMOKE VISIBLE FROM OUTSIDE      LCIVELLO   15:31:26 \n\nAdditional Inc#s:  \n 26-2        201200000052 \n\nThe Call Taker is HAMMEL THOMAS C  \nThe Dispatcher is SPANO MICHAEL
(<MCSOZ3@MCSONJ.ORG>) S: Automatic R&R Notification\nIncident Number  : 2012-00000055                    ORI: 32-1          Station: STA 32-1  \nIncident Type  . : FIRS F FIRE STRUC           Priority: 1  \nIncident Location: CHARLESTON SPRING RD                                 Venue: MILLSTONE  \n  Cross Street . : CARRIAGE WY \n\nCall Time- 16:26:27                                Date- 01/26/2012  \nDispatch - 16:32:31     En-route-              On-scene-              Depart 1-  \nArrive 2 -              Depart 2-              In-statn- \n\nArea: 3200              Section : 1851         Beat  . :   32        Map . . :  \nGrid:                   Quadrant: 3215         District: E321 \n\nPhone Number: (609) 396-8852                Call Source:  911 \n\nCaller. . . : ELIZABETH GOLDMAN \n\nUnits sent  :  \n 26-2        F262A                32-1        F32A  \n 16-1        F161A                32-1        3275 \n\nNature of Call : REPORTING A HOUSE FIRE \n\nAdditional Info  \n  WPH2:  RADIUS 01 MILES  \n  SAW SMOKE FROM WINDOW 2ND FLR \n\nNarrative  \n CALLER DROVE PAST ON SCHOOL BUS                   MHEATON    16:26:56  \n IS ATTEMPTING TO DRIVE BACK                       MHEATON    16:27:01  \n NJSP NTFD                                         MHEATON    16:27:43  \n 44 CHARLESTOWN SPRINGS                            MHEATON    16:32:27 \n\nAdditional Inc#s:  \n 16-1        201200000112             26-2        201200000053 \n\nThe Call Taker is HEATON MICHAEL  \nThe Dispatcher is SPANO MICHAEL
(<mcsop7@MCSONJ.ORG>) S: Automatic R&R Notification\nIncident Number  : 2012-00000054                    ORI: 26-2          Station: STA 26-2  \nIncident Type  . : AIDF F AID-F                Priority: 1  \nIncident Location: 5 SWEETMANS LN                                       Venue: MANALAPAN  \nLocated Between  : BORDER-MILLSTONE TWP/ST HWY 33 W  \nCommon Name. . . : STATION 26-2 \n\nCall Time- 16:34:08                                Date- 01/26/2012  \nDispatch - 16:34:38     En-route-              On-scene-              Depart 1-  \nArrive 2 -              Depart 2-              In-statn- \n\nArea: 26A4              Section :   32         Beat  . : 26A4        Map . . :  \nGrid:                   Quadrant: F262         District: E262 \n\nPhone Number: (000) 000-0000                Call Source:  TEL \n\nCaller. . . : 3275 \n\nUnits sent  :  \n 26-2        F262A \n\nNature of Call : IN MILLSTONE CHARLSTON SPRING STRUCTURE \n\nAdditional Info  \n  SAME ABOVE \n\nAdditional Inc#s: \n\nThe Call Taker is KNIGHTON JUSTIN  \nThe Dispatcher is KNIGHTON JUSTIN

*/

public class NJMonmouthCountyAParser extends FieldProgramParser {
  
  private static final Pattern KEYWORD_TRAIL_PTN = Pattern.compile("[ \\.]+:");
  private static final Pattern CALL_TIME_DATE_PTN = Pattern.compile("\\bCall Time- ([0-9:]+) +Date- ([0-9/]+) *\n.*?(?=\nArea:)", Pattern.DOTALL);
  
  public NJMonmouthCountyAParser() {
    super("MONMOUTH COUNTY", "NJ",
           "Incident_Number:ID! ORI:SKIP! Station:SRC! " +
           "Incident_Type:CALL! Priority:PRI! " +
           "Incident_Location:ADDR! Venue:CITY! " +
           "Located_Between:X? Cross_Street:X? Common_Name:PLACE? " +
           "Call_Time:TIME! Call_Date:DATE! " +
           "Area:MAP! Section:MAP! Beat:SKIP! Map:SKIP! " +
           "Grid:SKIP! Quadrant:MAP! District:MAP! " +
           "Phone_Number:PHONE! Call_Source:SKIP! " +
           "Caller:NAME? " +
           "Units_sent:UNIT? " +
           "Nature_of_Call:INFO ");
  }
  
  @Override
  public String getFilter() {
    return "@MCSONJ.ORG";
  }
  
  @Override
  public boolean parseMsg(String subject, String body, Data data) {
    if (!subject.equals("Automatic R&R Notification")) return false;
    if (body.contains("** FINAL REPORT **")) return false;
    
    int pt = body.indexOf("\nAdditional Info");
    if (pt < 0) return false;
    body = body.substring(0,pt).trim();
    
    body = KEYWORD_TRAIL_PTN.matcher(body).replaceAll(":");
    Matcher match = CALL_TIME_DATE_PTN.matcher(body);
    if (!match.find()) return false;
    body = match.replaceFirst("Call Time:$1 Call Date:$2 ");
    
    body = body.replace('\n', ' ');
    body = body.replaceAll("  +", " ");
    if (!super.parseMsg(body, data)) return false;
    return true;
  }
  
  private class MyCrossField extends CrossField {
    @Override
    public void parse(String field, Data data) {
      if (field.endsWith("/")) field = field.substring(0,field.length()-1).trim();
      super.parse(field, data);
    }
  }
  
  private static final Pattern MAP_TRAIL_PTN = Pattern.compile("[ +]+$");
  private class MyMapField extends MapField {
    @Override
    public void parse(String field, Data data) {
      field = MAP_TRAIL_PTN.matcher(field).replaceFirst("");
      data.strMap = append(field, "/", data.strMap);
    }
  }
  
  private class MyPhoneField extends PhoneField {
    @Override
    public void parse(String field, Data data) {
      if (field.equals("(000) 000-0000")) return;
      super.parse(field, data);
    }
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("X")) return new MyCrossField();
    if (name.equals("MAP")) return new MyMapField();
    if (name.equals("PHONE")) return new MyPhoneField();
    return super.getField(name);
  }
}
