package net.anei.cadpage.parsers.GA;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;

/*

Bulloch County, GA
Contact: Active911
Agency name: Bulloch County Fire Department
Location: Statesboro, GA, United States
Sender:  "Bulloch 911" <bullochga@ez911mail.com>

(Case # 2013013379) \rFrom : btapley User Id: 22
(Case # 2013013347) \rFrom : btapley User Id: 22
(Disp SFD Case # 2013013532 Call # 3530) 1/22/2013 7:38:13 AM /1070B /BURKHALTER RD /BURKHALTER RD /LEFT OFF OF HWY 80, 1 MILE DOWN ON RIGHT http://maps.google.com/maps?q=32.36275136,+-81.78721680\rFrom : rfail User Id: 8
(Disp GF-B Case # 2013013513 Call # 3511) 1/21/2013 21:56:21 /1070W /HIGHWAY 46 /HIGHWAY 46 /WOODS ON FIRE/RIGHT HAND SIDE OF ROAD BEFORE BULLOCH FERTILIZER\rFrom : tbolton User Id: 10
(Disp 4400 Case # 2013013513 Call # 3511) 1/21/2013 21:56:21 / /HIGHWAY 46 /HIGHWAY 46 /WOODS ON FIRE/RIGHT HAND SIDE OF ROAD BEFORE BULLOCH FERTILIZER\rFrom : tbolton User Id: 10
(Disp SFD Case # 2013013486 Call # 3484) 1/21/2013 12:51:33 PM /1070B /2050 PARRISH RD /2050 /PARRISH RD /BACK YARD http://maps.google.com/maps?q=32.42420336,+-81.86493334\rFrom : rfail User Id: 8
(Disp 4400 Case # 2013013478 Call # 3476) 1/21/2013 11:36:32 AM /1070B /HIGHWAY 46 /NEVILS GROVELAND RD /11600 /HIGHWAY 46\rFrom : rfail User Id: 8
(Disp SFD Case # 2013013468 Call # 3466) 1/21/2013 5:39:41 /1070A /CAMPUS CROSSING /133 /304 /LANIER DR /FIRE ALARM http://maps.google.com/maps?q=32.41461754,+-81.78285187\rFrom : tmcdonald User Id: 16
(Disp 9900 Case # 2013013452 Call # 3450) 1/20/2013 8:32:29 PM /1070I /S HIGHWAY 301 /S HIGHWAY 301 /OVERTURNED 18 WHEELER http://maps.google.com/maps?q=32.37280332,+-81.84724030\rFrom : dbrown User Id: 18
(Disp 7700 Case # 2013013452 Call # 3450) 1/20/2013 8:32:29 PM /1070I /S HIGHWAY 301 /S HIGHWAY 301 /OVERTURNED 18 WHEELER http://maps.google.com/maps?q=32.37280332,+-81.84724030\rFrom : dbrown User Id: 18
(Disp 4400 Case # 2013013452 Call # 3450) 1/20/2013 8:32:29 PM / /HIGHWAY 301\rFrom : dbrown User Id: 18
(Disp 3300 Case # 2013013452 Call # 3450) 1/20/2013 8:32:29 PM / /HIGHWAY 301\rFrom : dbrown User Id: 18
(Disp 3300 Case # 2013013379 Call # 3377) 1/20/2013 3:15:35 AM /1070S /EAGLE COURT CONDOS /VETERANS MEMORIAL PKWY /222 /211 /LANIER DR /APARTMENT ON FIRE FROM CANDLE http://maps.google.com/maps?q=32.40799655,+-81.77965394\rFrom : cperkins User Id: 17
(Disp SFD Case # 2013013379 Call # 3377) 1/20/2013 3:15:35 AM /1070S /EAGLE COURT CONDOS /VETERANS MEMORIAL PKWY /222 /211 /LANIER DR /APARTMENT ON FIRE FROM CANDLE http://maps.google.com/maps?q=32.40799655,+-81.77965394\rFrom : cperkins User Id: 17
(Disp SFD Case # 2013013378 Call # 3376) 1/20/2013 2:44:56 AM / /THE EXCHANGE OF STATESBORO /2000 /STAMBUK LN\rFrom : cperkins User Id: 17
(Disp 2200 Case # 2013013347 Call # 3345) 1/19/2013 8:27:08 PM /1070B /BANKS CREEK CHURCH RD /BANKS CREEK CHURCH RD /BRUSH FIRE\rFrom : tmcdonald User Id: 16
(Disp 2200 Case # 2013013325 Call # 3323) 1/19/2013 16:27:04 / /N OLD RIVER RD /10002 /3 /N OLD RIVER RD /TRAILER FIRE\rFrom : kborgman User Id: 14
(Disp 8800 Case # 2013013325 Call # 3323) 1/19/2013 16:27:04 / /N OLD RIVER RD /10002 /3 /N OLD RIVER RD /TRAILER FIRE\rFrom : kborgman User Id: 14
(Disp 9900 Case # 2013013325 Call # 3323) 1/19/2013 16:27:04 / /N OLD RIVER RD /10002 /3 /N OLD RIVER RD /TRAILER FIRE\rFrom : kborgman User Id: 14
(Disp SFD Case # 2013013298 Call # 3296) 1/19/2013 11:04:19 /1070B /SIMONS RD /246 /SIMONS RD /FIELD FIRE http://maps.google.com/maps?q=32.48075932,+-81.81980600\rFrom : kborgman User Id: 14
(Disp SFD Case # 2013013294 Call # 3292) 1/19/2013 10:53:23 /1070S /322 CONLEY ST /322 /CONLEY ST\rFrom : kborgman User Id: 14
(Disp 9900 Case # 2013013294 Call # 3292) 1/19/2013 10:53:23 /1070S /322 CONLEY ST /322 /CONLEY ST\rFrom : kborgman User Id: 14
(Disp 3300 Case # 2013013294 Call # 3292) 1/19/2013 10:53:23 /1070S /322 CONLEY ST /322 /CONLEY ST\rFrom : kborgman User Id: 14
(Disp 2200 Case # 2013013294 Call # 3292) 1/19/2013 10:53:23 /1070S /322 CONLEY ST\rFrom : kborgman User Id: 14
(Disp SFD Case # 2013013210 Call # 3208) 1/18/2013 12:39:35 PM /1070B /210 RAYMOND ST /210 /RAYMOND ST /TREE ON FIRE\rFrom : jsmith User Id: 13
(Disp SFD Case # 2013013183 Call # 3181) 1/17/2013 7:37:37 PM /1070 /THE COVE AT SOUTHERN /OLYMPIC BLVD /1818 /92 /CHANDLER RD /SAMLL KITCHEN FIRE, FIRE IS OUT BUT ALOT OF SMOKE http://maps.google.com/maps?q=32.41389315,+-81.78724638\rFrom : cblackburn User Id: 12
(Disp SFD Case # 2013013099 Call # 3097) 1/16/2013 5:56:48 PM /1070A /15 PARADISE CV /SAWGRASS TR /15 /PARADISE CV /FALSE ALARM NO RESPONSE NEEDED PER HOME OWNER http://maps.google.com/maps?q=32.38677126,+-81.74812788\rFrom : rfail User Id: 8
(Disp SFD Case # 2013013011 Call # 3009) 1/15/2013 12:30:18 /1070A /PREFORMING ARTS /847 /PLANT DR\rFrom : mrich User Id: 15
(Disp 7700 Case # 2013012958 Call # 2956) 1/14/2013 16:10:13 /1070 /ARCOLA RD /ARCOLA RD /DUMPSTER SMOKING\rFrom : mrich User Id: 15
(Disp 3300 Case # 2013012913 Call # 2911) 1/14/2013 4:19:54 /1070V /I-16E @ MM 115 /I-16E @ MM 115 /18 WHEELER TRAILER ON FIRE, SEES FLAMES\rFrom : tbolton User Id: 10
(Disp SFD Case # 2013012901 Call # 2899) 1/13/2013 11:08:05 PM /1070S /500 S KEVIN CT /500 /S KEVIN CT /SMELLS LIKE WIRES BURNING IN HOUSE\rFrom : cblackburn User Id: 12
(Disp 2200 Case # 2013012901 Call # 2899) 1/13/2013 11:08:05 PM /1070S /500 S KEVIN CT /500 /S KEVIN CT /SMELLS LIKE WIRES BURNING IN HOUSE\rFrom : cblackburn User Id: 12
(Disp 8800 Case # 2013012901 Call # 2899) 1/13/2013 11:08:05 PM /1070S /500 S KEVIN CT /500 /S KEVIN CT /SMELLS LIKE WIRES BURNING IN HOUSE\rFrom : cblackburn User Id: 12
(Disp 9900 Case # 2013012901 Call # 2899) 1/13/2013 11:08:05 PM /1070S /500 S KEVIN CT /500 /S KEVIN CT /SMELLS LIKE WIRES BURNING IN HOUSE\rFrom : cblackburn User Id: 12
(Disp SFD Case # 2013012891 Call # 2889) 1/13/2013 20:53:08 /1070A /EAGLE VILLAGE DORM /ONEAL DR /410 /GEORGIA AV /BUILDING 1/COMMUNU http://maps.google.com/maps?q=32.42016064,+-81.77785273\rFrom : tbolton User Id: 10
(Disp 3300 Case # 2013012885 Call # 2883) 1/13/2013 7:51:25 PM / /CYPRESS LAKE MHP /CYPRESS LAKE TRLR PK http://maps.google.com/maps?q=32.39288986,+-81.86280882\rFrom : cblackburn User Id: 12
(Disp 2200 Case # 2013012864 Call # 2862) 1/13/2013 4:08:14 PM /1070B /PLEASANT LN /303 /PLEASANT LN /FIRE IN FIELD CATCHING GRASS http://maps.google.com/maps?q=32.56542991,+-81.95798339\rFrom : rfail User Id: 8
(Disp 9900 Case # 2013012807 Call # 2805) 1/12/2013 9:45:18 PM /1070S /217 SHUMAN DR /JONATHAN RD /217 /SHUMAN DR /GREASE FIRE, SPREADING TO UPPER CABNIETS http://maps.google.com/maps?q=32.47583486,+-81.79343024\rFrom : cblackburn User Id: 12
(Disp SFD Case # 2013012807 Call # 2805) 1/12/2013 9:45:18 PM /1070S /217 SHUMAN DR /JONATHAN RD /217 /SHUMAN DR /GREASE FIRE, SPREADING TO UPPER CABNIETS http://maps.google.com/maps?q=32.47583486,+-81.79343024\rFrom : cblackburn User Id: 12
(Disp 9900 Case # 2013012790 Call # 2788) 1/12/2013 7:03:45 PM /1070 /BLACKBIRD DR /BLACKBIRD DR /FIELD ON FIRE\rFrom : cblackburn User Id: 12
(Disp RESCU Case # 2013012785 Call # 2783) 1/12/2013 18:27:15 /1050I /I-16E @ MM 132 /I-16E @ MM 132 /2 HURT/\rFrom : jsmith User Id: 13
(Disp 5500 Case # 2013012787 Call # 2785) 1/12/2013 6:29:48 PM /1070R /I-16E @ MM 132 /I-16E @ MM 132 /SINGLE VEH ROLLOVER\rFrom : rfail User Id: 8
(Disp 7700 Case # 2013012760 Call # 2758) 1/12/2013 1:11:45 PM /1070B /3040 E HIGHWAY 80 /M & M LN /3040 /E HIGHWAY 80 http://maps.google.com/maps?q=32.25840414,+-81.52713342\rFrom : rfail User Id: 8
(Disp GF-B Case # 2013012760 Call # 2758) 1/12/2013 13:11:45 /1070B /3040 E HIGHWAY 80 /M & M LN /3040 /E HIGHWAY 80 http://maps.google.com/maps?q=32.25840414,+-81.52713342\rFrom : kpuchala User Id: 7
(Disp 6600 Case # 2013012760 Call # 2758) 1/12/2013 1:11:45 PM /1070B /3040 E HIGHWAY 80 /M & M LN /3040 /E HIGHWAY 80 http://maps.google.com/maps?q=32.25840414,+-81.52713342\rFrom : rfail User Id: 8
(Disp SFD Case # 2013012757 Call # 2755) 1/12/2013 13:01:03 /1070 /445 S WALNUT /W WALNUT ST /SMELLS GAS OUTSIDE\rFrom : kpuchala User Id: 7
(Disp 6600 Case # 2013012693 Call # 2691) 1/11/2013 18:50:54 /1070B /1031 OLD THORN POND RD /HYNKO RD /1031 /OLD THORN POND RD /BRUSH FIRE NEAR THE FIELD NEAR THE HOUSE http://maps.google.com/maps?q=32.31097424,+-81.56851845\rFrom : kpuchala User Id: 7
(Disp 2200 Case # 2013012689 Call # 2687) 1/11/2013 18:21:35 /1070A /5219 WILLIAMS RD /ISAAC AKINS RD /5219 /WILLIAMS RD /CARBON MONOXIDE  DETECTOR GOING OFF http://maps.google.com/maps?q=32.50269479,+-81.85355975\rFrom : kpuchala User Id: 7
(Disp SFD Case # 2013012642 Call # 2640) 1/11/2013 2:11:21 AM /1070A /CAMDEN PLACE /LINDA DR /115 /DODD CIR /SOUTH END HALLWAY http://maps.google.com/maps?q=32.47125773,+-81.75935423\rFrom : dbrown User Id: 18
(Disp SFD Case # 2013012640 Call # 2638) 1/11/2013 1:44:23 AM /1010 /COLLEGE WALK APTS /ROBIN HOOD TR /210 /8 /LANIER DR http://maps.google.com/maps?q=32.40952575,+-81.78068092\rFrom : dbrown User Id: 18
(Disp SFD Case # 2013012616 Call # 2614) 1/10/2013 19:48:56 /1070 /BLAKEWOOD APARTMENTS /620 /27 /E OLLIFF ST /SMELL OF GAS http://maps.google.com/maps?q=32.45395560,+-81.77233773\rFrom : tmcdonald User Id: 16
(Disp SFD Case # 2013012610 Call # 2608) 1/10/2013 18:27:17 /1070A /BULLOCH COUNTY ANIMAL SHELTER /81 /MILL CREEK RD /GENERAL FIRE 764-4521\rFrom : kborgman User Id: 14
(Disp 9900 Case # 2013012582 Call # 2580) 1/10/2013 13:05:51 /1070B /N OLD RIVER RD /301 NORTH /N OLD RIVER RD\rFrom : kborgman User Id: 14
(Disp SFD Case # 2013012541 Call # 2539) 1/9/2013 10:22:01 PM /1070 /E MAIN ST /225 /E MAIN ST /ALARM GOING  OFF http://maps.google.com/maps?q=32.44894410,+-81.77665364\rFrom : dbrown User Id: 18
(Disp 9900 Case # 2013012536 Call # 2534) 1/9/2013 20:51:13 /1070S /ISABELLA'S /199 /E NORTHSIDE DR /SMELL OF ELECTRICAL FIRE AND SMOKE IN KITCHEN AREA\rFrom : tmcdonald User Id: 16
(Disp SFD Case # 2013012536 Call # 2534) 1/9/2013 20:51:13 / /ISABELLA'S /199 /E NORTHSIDE DR /SMELL OF ELECTRICAL FIRE AND SMOKE IN KITCHEN AREA\rFrom : tmcdonald User Id: 16
(Disp 7700 Case # 2013012528 Call # 2526) 1/9/2013 18:35:34 / /2040 GRIMSHAW LN /2040 /A /GRIMSHAW LN /GOLF CART NEXT TO DECK ON FIRE http://maps.google.com/maps?q=32.40162046,+-81.70882885\rFrom : mrich User Id: 15
(Disp SFD Case # 2013012528 Call # 2526) 1/9/2013 18:35:34 / /2040 GRIMSHAW LN /2040 /GRIMSHAW LN /GOLDCART NEXT TO DECK ON FIRE\rFrom : mrich User Id: 15
(Disp 2200 Case # 2013012505 Call # 2503) 1/9/2013 13:50:26 / /HIGHWAY 25 /COUNTY LINE\rFrom : kborgman User Id: 14
(Disp SFD Case # 2013012484 Call # 2482) 1/9/2013 09:41:31 /E911 /EAST GEORGIA WOMEN'S CENTER /BRAMPTON AV /1012 /BERMUDA RUN RD /SMOKE FROM EARLIER http://maps.google.com/maps?q=32.41450426,+-81.77292143\rFrom : kborgman User Id: 14
(Disp 9900 Case # 2013012481 Call # 2479) 1/9/2013 09:16:48 /E911 /N HIGHWAY 301 /21793 /N HIGHWAY 301 /SMELL LIKE SOMETHING BURNING\rFrom : kborgman User Id: 14
(Disp 9900 Case # 2013012364 Call # 2362) 1/7/2013 12:03:48 PM /1070 /CLITO RD /RR TRACKS /CLITO RD\rFrom : rfail User Id: 8
(Disp 7700 Case # 2013012314 Call # 2312) 1/6/2013 13:50:41 /1070A /12381 E HIGHWAY 80 /MYRTIS MAYS/  KEY PAD/  842-9137\rFrom : kborgman User Id: 14
(Disp SFD Case # 2013012304 Call # 2302) 1/6/2013 10:35:08 /1070 /FAIR RD /FAIR RD /PROPANE POURING OUT TENNIS COURTS NEXT TO RECREATION DEPARTMENT LINE SEVERED\rFrom : mrich User Id: 15
(Disp SFD Case # 2013012301 Call # 2299) 1/6/2013 10:23:55 AM /1070 /NORTHSIDE APTS # 101 /61 /101 /PACKINGHOUSE RD /STOVE FIRE FULL OF SMOKE http://maps.google.com/maps?q=32.46117401,+-81.76816754\rFrom : jsmith User Id: 13
(Disp SFD Case # 2013012239 Call # 2237) 1/5/2013 13:49:09 /1070A /BROWN'S HEALTH AND REHABILITATION CENTER /226 /S COLLEGE ST /GENERAL FIRE 764-9631/FALSE CALL NOT DISPATCHED http://maps.google.com/maps?q=32.44292618,+-81.78601654\rFrom : kborgman User Id: 14
(Disp SFD Case # 2013012144 Call # 2142) 1/4/2013 12:01:57 PM / /DEAL HALL /1582 /SOUTHERN DR /AC SMOKING http://maps.google.com/maps?q=32.42591312,+-81.78315790\rFrom : jsmith User Id: 13
(Disp SFD Case # 2013012138 Call # 2136) 1/4/2013 9:17:46 /1070A /ROLLINGRIDGE CT /201 /ROLLINGRIDGE CT /general fire 764-5913 CHARLES WEBB FALSE ALARM NOT DISPATCHED\rFrom : mrich User Id: 15
(Disp SFD Case # 2013012117 Call # 2115) 1/3/2013 7:50:11 PM /1070 /COUNTRY CLUB RD /COUNTRY CLUB RD /GAS LEAK FROM VEHICLE ACCIDENT\rFrom : cblackburn User Id: 12
(Disp SFD Case # 2013012085 Call # 2083) 1/3/2013 11:32:17 /1070A /FIRST BAPTIST CHURCH OF STATESBORO /108 /N MAIN ST /FALSE ALARM NO RESPONSE NEEDED\rFrom : kpuchala User Id: 7
(Disp 7700 Case # 2013012079 Call # 2077) 1/3/2013 8:45:42 AM / /EMIT GROVE BAPTIST CHURCH /1567 /EMIT GROVE RD /COTTON PICKER ON FIRE http://maps.google.com/maps?q=32.34353644,+-81.73318767\rFrom : rfail User Id: 8
(Disp 4400 Case # 2013012079 Call # 2077) 1/3/2013 8:45:42 AM / /EMIT GROVE BAPTIST CHURCH /1567 /EMIT GROVE RD /COTTON PICKER ON FIRE http://maps.google.com/maps?q=32.34353644,+-81.73318767\rFrom : rfail User Id: 8
(Disp 3300 Case # 2013012037 Call # 2035) 1/2/2013 16:27:38 / /88 ALLISON LN /88 /ALLISON LN /THINKS THERE IS SMOKE COMING FROM THE VENTS\rFrom : mmiller User Id: 9
(Disp 2200 Case # 2013012010 Call # 2008) 1/2/2013 08:38:28 / /26779 W HIGHWAY 80 /26779 /W HIGHWAY 80 /SMELLS LIKE SOMETHING IS BURNING\rFrom : kpuchala User Id: 7
(Disp SFD Case # 2013012014 Call # 2012) 1/2/2013 9:17:36 AM /1070A /WILLOW POND /4344 /COUNTRY CLUB RD http://maps.google.com/maps?q=32.43448075,+-81.81772454\rFrom : rfail User Id: 8
(Disp SFD Case # 2013011977 Call # 1975) 1/1/2013 18:04:26 /1070S /BROOMSTRAW TRC /62 /BROOMSTRAW TRC /ACROSS FROM 62 BROOMSTRAW PORCH ON FIRE\rFrom : kborgman User Id: 14
(Disp 7700 Case # 2013011977 Call # 1975) 1/1/2013 18:04:26 /1070S /BROOMSTRAW TRC /62 /BROOMSTRAW TRC /ACROSS FROM 62 BROOMSTRAW PORCH ON FIRE\rFrom : kborgman User Id: 14
(Disp 5500 Case # 2013011977 Call # 1975) 1/1/2013 18:04:26 /1070S /BROOMSTRAW TRC /62 /BROOMSTRAW TRC /ACROSS FROM 62 BROOMSTRAW PORCH ON FIRE\rFrom : kborgman User Id: 14
(Disp 6600 Case # 2013011977 Call # 1975) 1/1/2013 18:04:26 /1070S /BROOMSTRAW TRC /62 /BROOMSTRAW TRC /ACROSS FROM 62 BROOMSTRAW PORCH ON FIRE\rFrom : kborgman User Id: 14
(Disp SFD Case # 2013011950 Call # 1948) 1/1/2013 8:06:48 AM / /136 KENT ST /136 /KENT ST /SMELL OF GAS http://maps.google.com/maps?q=32.45909983,+-81.79287797\rFrom : kpuchala User Id: 7
(Disp 9900 Case # 2012121906 Call # 1904) 12/31/2012 9:18:08 PM /1070S /HOUNDSBARK CT /1006 /HOUNDSBARK CT /SPARKS COMING FROM FIREPLACE/OUT CHIMNEY\rFrom : dbrown User Id: 18
(Disp SFD Case # 2012121906 Call # 1904) 12/31/2012 9:18:08 PM /1070S /HOUNDSBARK CT /1006 /HOUNDSBARK CT /SPARKS COMING FROM FIREPLACE/OUT CHIMNEY\rFrom : dbrown User Id: 18
(Disp SFD Case # 2012121818 Call # 1816) 12/30/2012 8:11:54 PM / /FIRE IS OUT SMOKE IN THE HOUSE GREASE FIRE  SMOKE /333\rFrom : lwalters User Id: 11
(Disp 3300 Case # 2012121781 Call # 1779) 12/30/2012 10:49:23 /1070 /HIGHWAY 46 /HIGHWAY 46 /REF SETTING UP A LANDING ZONE FOR HELICOPTER\rFrom : kpuchala User Id: 7
(Disp 4400 Case # 2012121763 Call # 1761) 12/30/2012 3:24:28 AM /1070S /CEDARWOOD ACRES MHP /1 /CEDARWOOD ACRES TRLR PK /MOBILE HOME ON FIRE\rFrom : lwalters User Id: 11
(Disp 2200 Case # 2012121763 Call # 1761) 12/30/2012 3:24:28 AM /1070S /CEDARWOOD ACRES MHP /1 /CEDARWOOD ACRES TRLR PK /MOBILE HOME ON FIRE\rFrom : lwalters User Id: 11
(Disp 3300 Case # 2012121763 Call # 1761) 12/30/2012 3:24:28 AM /1070S /CEDARWOOD ACRES MHP /1 /CEDARWOOD ACRES TRLR PK /MOBILE HOME ON FIRE\rFrom : lwalters User Id: 11
(Disp SFD Case # 2012121763 Call # 1761) 12/30/2012 3:24:28 AM /1070S /CEDARWOOD ACRES MHP /1 /CEDARWOOD ACRES TRLR PK /MOBILE HOME ON FIRE\rFrom : lwalters User Id: 11
(Disp SFD Case # 2012121732 Call # 1730) 12/29/2012 7:12:41 PM /1070A /STATESBORO MALL /718 /E NORTHSIDE DR /SUITE 33-ZONE 3 MANUAL PULL/HALL AND REAR STORES\rFrom : cblackburn User Id: 12
(Disp 5500 Case # 2012121684 Call # 1682) 12/29/2012 4:45:45 / /I-16W @ MM 135 /I-16W @ MM 135\rFrom : tbolton User Id: 10
(Disp RESCU Case # 2012121683 Call # 1681) 12/29/2012 4:45:11 AM /1052E /I-16W @ MM 135 /I-16W @ MM 135 /MALE TRAPPED IN VEHICLE\rFrom : cblackburn User Id: 12
(Disp SFD Case # 2012121658 Call # 1656) 12/28/2012 7:18:56 PM /1070A /1201 PLANTATION CIR /1201 /PLANTATION CIR /SMOKE DETECTOR http://maps.google.com/maps?q=32.44082647,+-81.85108947\rFrom : lwalters User Id: 11
(Disp SFD Case # 2012121647 Call # 1645) 12/28/2012 5:54:52 PM /1070 /ZETTEROWER AV /ZETTEROWER AV /POSIBLE GAS LEAK\rFrom : mrich User Id: 15
(Disp GF-B Case # 2012121646 Call # 1644) 12/28/2012 17:23:42 /1070B /I-16E @ MM 126 /I-16E @ MM 126 /SMALL BRUSH FIRE\rFrom : mmiller User Id: 9
(Disp 4400 Case # 2012121646 Call # 1644) 12/28/2012 17:23:42 /1070B /I-16E @ MM 126 /I-16E @ MM 126\rFrom : mmiller User Id: 9
(Disp SFD Case # 2012121603 Call # 1601) 12/28/2012 7:25:09 /1070A /OGEECHEE AREA HOSPICE /200 /DONEHOO ST /GENERAL FIRE ALARM http://maps.google.com/maps?q=32.44202371,+-81.77268419\rFrom : mmiller User Id: 9
(Disp 3300 Case # 2012121534 Call # 1532) 12/27/2012 14:12:10 /1070 /PULASKI RD /PULASKI RD /FIRE ON THE SIDE OF THE ROADWAY\rFrom : kborgman User Id: 14
(Disp 3300 Case # 2012121507 Call # 1505) 12/27/2012 7:21:55 /1070A /WALMART DISTRIBUTION CENTER /299 /A J RIGGS RD /waterflow 49 ZONE 58/871-8404/FALSE CALL NOT DISPATCHED http://maps.google.com/maps?q=32.39316679,+-81.83586391\rFrom : mrich User Id: 15
(Disp SFD Case # 2012121502 Call # 1500) 12/26/2012 11:10:16 PM /1070\rFrom : dbrown User Id: 18
(Disp SFD Case # 2012121493 Call # 1491) 12/26/2012 19:31:09 /1070 /W HIGHWAY 80 /W HIGHWAY 80 /18 WHEELER ON SIDE OF ROAD LEAKING FUEL\rFrom : tmcdonald User Id: 16

*/

public class GABullochCountyBParserTest extends BaseParserTest {
  
  public GABullochCountyBParserTest() {
    setParser(new DummyParser(), "BULLOCH COUNTY", "GA");
  }
  
  @Test
  public void testParser() {

    
  }
  
  public static void main(String[] args) {
    new GABullochCountyBParserTest().generateTests("T1");
  }
}
