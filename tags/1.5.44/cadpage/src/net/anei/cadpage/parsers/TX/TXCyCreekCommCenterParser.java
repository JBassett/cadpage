package net.anei.cadpage.parsers.TX;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.MsgParser;
import net.anei.cadpage.parsers.MsgInfo.Data;

/* Sample Harris Page

Contact: chris pivonka <cpivonka87@gmail.com>
Sender: CommCenter@ccems.com <From%3ACommCenter@ccems.com>

1of2:09/06 11:28 19707 WOOD WALK LN, ; Map:337U Sub:PINEHURST OF ATASCOCITA Nat:09E01-ARREST - NOT BREATHING Units:E-M19 E-M29 E-7900
1of2:09/05 08:56 19226 AQUATIC DR, ; Map:378A Sub:WALDEN ON LAKE HOUSTON Nat:52B01G-FIRE ALARM - RESIDENTIAL Units:ATFD E-E39 X-
1of2:09/04 19:45 17219 KOBUK VALLEY CIR, ; Map:377E Sub:EAGLE SPRINGS Nat:67B03U-OUTSIDE FIRE - INVESTIGA Units:E-E39 X-St:*** Dead
1of2:09/03 08:14 LILES LN/WOODLAND HILLS DR, ; Map:376H Sub:ATASCOCITA FOREST Nat:29-MOTOR VEHICLE INCIDENT Units:E-M19 E-E39 X-
11/15 11:28 19506 SWEETGUM FOREST DR, ; Map:337V Sub:PINEHURST OF ATASCOCITA Nat:28B01U-STROKE/CVA - UNKNOWN Units:E-E39 X-St:TWELFTH FAIRWAY *** Dead
11/13 13:12 6918 ATASCA CREEK DR, ; Map:377C Sub:ATASCA WOODS Nat:52B01G-FIRE ALARM - RESIDENTIAL Units:ATFD E-E39 X-St:LEENS LODGE LN FOUNTAIN L
11/13 02:18 E FM 1960/DINERO DR, ; Map:337Z Sub: Nat:29D02M-MVI - PEDESTRIAN Units:E-M19 E-E39 E-B39 X-St:DINERO DR E FM 1960
11/13 02:17 E FM 1960/DINERO DR, ; Map:337Z Sub: Nat:29-MOTOR VEHICLE INCIDENT Units:E-M19 E-E39 X-St:DINERO DR E FM 1960
11/12 18:24 Repage: 8922 PINE SHORES DR, ; Map:338S Sub:ATASCOCITA SHORES Nat:60B02-GAS LEAK - UNKNOWN Units:E-E19 X-St:SHOREVIEW LN SUNCOVE LN
11/12 18:08 Repage: ATASCOCITA RD/TIMBER FOREST D, ; Map:377A Sub: Nat:29-MOTOR VEHICLE INCIDENT Units:HUM2 E-E29 X-St:TIMBER FOREST DR ATASCOCI
11/12 17:31 E FM 1960/POSSUM PARK RD, ; Map:336T Sub: Nat:68C01-HEAVY SMOKE OUTSIDE Units:E-E39 E-B39 X-St:POSSUM PARK RD E FM 1960
11/19 17:00 OAK HOLLOW DR-HC/GRANT RD-HC, ; Map:369E Nat:67B01U-SMALL OUTSIDE FIRE Units:E21 E26 B22 X-St:GRANT RD WILLOW LN
11/28 12:49 6006 WOODMANCOTE DR-HC, ; Map:337X- Nat:27-STABBING / SHOOTING Units:E-M29 X-St:DANALYN CT CLOYANNA LN

1of2:09/06 11:56 W HILLSIDE DR/EASTEX FRWY, ; Map:414D Sub: Nat:MA-MUTUAL AID / ASSIST AGENCY Units:E91 T81 T73 E-L19 X-St:EASTEX
12/07 12:26 14231 CELLINI DR-HC, ; Map:368P- Nat:MA-MUTUAL AID / ASSIST AGENCY Units:MUTAID E24 X-St:TERREBONE DR BONAPARTE DR
12/08 17:53 13830 ROSETTA DR-HC, ; Map:368P- Nat:MA-MUTUAL AID / ASSIST AGENCY Units:MUTAID E22 X-St:BELLA DR LA BARRE DR

Contact: Bryan Perry <Bryan.Perry@ccvfd.com>
12/29 11:17 7575 N SAM HOUSTON PKWY W EB-, SAM HOUSTON RACE PARK; FL 1 Map:370T- Nat:71B01-VEHICLE FIRE Units:E24 NWE43 B23 X-St:FAIRBANKS N HOUSTON RD N

Contact: Patrick Boren <boren.patrick@gmail.com>
02/16 FM 2920-HC/RHODES RD-HC, ; Map:291N- Sub: Nat:29-MOTOR VEHICLE INCIDENT Units:M510 FO290 X-St:RHODES RD MARCIN DR 201107335

Contact: Nathan Mathews <nbmathews807@gmail.com>
03/14 10:10 HUFSMITH KOHRVILLE RD-HC/BOUD, ; Map:289X- Sub: Nat:59C02O-FUEL SPILL - UNCONTAINED Units:E306 HM1 X-St:BOUDREAUX RD
03/19 09:48 CROSBY TRAINING, ; Map:- Sub: Nat:HM3-HC HAZMAT TRAINING Units:HM1 X-St: 201112139

Contact: Preston Bartlett <fighter727@gmail.com>
  06/08 12:42 8314 CINNAMON RUN DR-HC, ; Map:250J- Sub: Nat:67-OUTSIDE FIRE Units:WDE107 E76 X-St:S ROLLING OAKS DR KUYKEND 201125394 TXT STOP to
  
Contact: joe marroni <joeman1115@gmail.com>
  06/08 16:04 12110 SARTI ST-HC, ; Map:371A- Sub:FOUNTAINHEAD Nat:31D02-UNCON - EFFECTIVE BREATHI Units:HCM93 D52 11M X-St:BROOKHEAD TRL 201125435 TXT STOP to opt-out

Contact: Preston Bartlett <fighter727@gmail.com>
  09/05 15:19 18300 ELLA BLVD-HC, ; Map:331G- Sub:CYPRESSWOOD Nat:67D03-LARGE OUTSIDE FIRE Units:E62 B78 E61 B76 E37 X-St:BLUE CYPRESS DR
  
Contact: Christopher Day <mrday010@gmail.com>   
  09/11 23:25 22539 SH 249 SB-HC, MATTRESS GIANT; Map:329N- Sub: Nat:69-LIGHT STRUCTURE INCIDENT Units:E23 E21 E33 E24 E36 TW23 L36 X-St:LAKEWOOD CROSSING DR 

Contact: Preston Bartlett <fighter727@gmail.com>
Sender: 2814750795
11/07 15:59 8304 WOODSONG DR-HC, ; 61 Map:250S- Sub:TIMBERCREST VILLAGE MHP Nat:26-MEDICAL CALL Units:M501 FO250W 76M X-St:SUGAR LEAF TRL LONDON TOW 201150082
11/09 12:21 8211 DEER TRAIL DR-HC, ; Map:250S- Sub:TIMBERCREST VILLAGE MHP Nat:10-CARDIAC Units:M54 FO250W 76M X-St:LONDON TOWN DR ELMFIELD D 201150359

Contact: David Wisofsky <dwisofsky@gmail.com>
12/09 13:45 SPRING CYPRESS RD-HC/NORTH FR,; Map292-P- Sub: Nat:29D202P-MVI - ROLLOVER Units:M56 FO291N E78 L71 X-St:NORTH FRWY SB NANNETTE LN 2

Contact: 8323221385@mms.mycricket.com
12/30 14:29 605 RILEY FUZZEL RD-HC, KATERING 2 KIDS; Map:292M- Sub: Nat:55B04-ELECTRICAL ODOR Units:E78 X-St:HARDY TOLL RD NB OLD RILE 20115823

Contact: Active911.com
Sender: <CommCenter@ccems.com>
02/17 17:42 DUNCAN RD-HC/W FM 1960-HC, ; Map:370C- Sub: Nat:29-MOTOR VEHICLE INCIDENT Units:M55 FO370 E12 X-St:W FM 1960 THEALL RD 201207247
02/17 21:32 5959 W FM 1960-HC, PROMENADE AT CHAMPIONS A; 1027 Map:370C- Sub: Nat:26-MEDICAL CALL Units:M57 11M X-St:PARADISE VALLEY DR DUNCAN 201207279
02/17 21:34 5959 W FM 1960-HC, PROMENADE AT CHAMPIONS A; 1027 Map:370C- Sub: Nat:26A11-SICK PERSON - VOMITING Units:M57 E12 X-St:PARADISE VALLEY DR DUNCAN 201207279
02/17 04:21 14222 WUNDERLICH DR-HC, COTTAGES OF CHAMPION FOR; 1901 Map:330Z- Sub: Nat:6-RESPIRATORY Units:M51 11M FO370 X-St:CORAL GABLES DR CASHEL OA 201207154
02/17 04:24 14222 WUNDERLICH DR-HC, COTTAGES OF CHAMPION FOR; 1901 Map:330Z- Sub: Nat:06C01-BREATHING PROBLEMS - ABNO Units:M51 11M E12 X-St:CORAL GABLES DR CASHEL OA 201207154
02/17 14:56 12811 GREENWOOD FOREST DR-HC, CHAMPIONS VILLAGE APTS; 2315 Map:370D- Sub:GREENWOOD FOREST Nat:06C01-BREATHING PROBLEMS - ABNO Units:M591 E12 X-St:OLD LODGE DR W FM 1960 201207218
02/17 15:02 12811 GREENWOOD FOREST DR-HC, CHAMPIONS VILLAGE APTS; 2315 Map:370D- Sub:GREENWOOD FOREST Nat:06C01-BREATHING PROBLEMS - ABNO Units:M591 E12 X-St:OLD LODGE DR W FM 1960 201207218
02/17 15:07 12811 GREENWOOD FOREST DR-HC, CHAMPIONS VILLAGE APTS; 2315 Map:370D- Sub:GREENWOOD FOREST Nat:06C01-BREATHING PROBLEMS - ABNO Units:M591 E12 D51 FO370 FOSOU 11M X-St:OLD LODGE DR W FM 1960 201207218

[Ponderosa FD] 03/08 08:38 FM 1960-HC/IMPERIAL VALLEY DR, ; Map:332L- Sub: Nat:29-MOTOR VEHICLE INCIDENT Units:M501 FO332E EQ63 X-St:IMPERIAL VALLEY DR CARLSW 201210400\n
[Ponderosa FD] 03/08 08:40 FM 1960-HC/IMPERIAL VALLEY DR, ; Map:332L- Sub: Nat:29D02L-MVI - BICYCLE / MOTORCYC Units:M501 EQ63 E64 X-St:IMPERIAL VALLEY DR CARLSW 201210400\n
[Ponderosa FD] 03/08 08:55 1103 MARNE LN-HC, ; Map:331M- Sub:OLD WESTADOR Nat:09B01A-OBVIOUS DEATH - COLD / S Units:M52 D51 FOSOU E63 FO332W X-St:NANES DR 201210402\n
[Ponderosa FD] 03/08 10:52 336 FM 1960-HC, ; Map:332L- Sub: Nat:52C03W-FIRE ALARM - COMMERCIAL Units:PVFD E63 X-St:GATEWICK NORTH FRWY NB 201210418\n
[Ponderosa FD] 03/08 10:54 336 FM 1960-HC, ; Map:332L- Sub: Nat:52C03W-FIRE ALARM - COMMERCIAL Units:E63 E64 X-St:GATEWICK NORTH FRWY NB 201210418\n
[Ponderosa FD] 03/08 10:54 Repage:336 FM 1960-HC, ; Map:332L- Sub: Nat:52C03W-FIRE ALARM - COMMERCIAL Units:E63 E64 X-St:GATEWICK NORTH FRWY NB 201210418\n
[Ponderosa FD] 03/08 12:10 NORTH FRWY MAINLANES SB-HC/W , ; Map:332K- Sub: Nat:12D02E-EPILEPTIC - CONTINUOUS / Units:M56 E64 X-St:W FM 1960 W RICHEY RD 201210432\n
[Ponderosa FD] 03/08 17:35 4401 SPRING CYPRESS RD-HC, ; Map:291X- Sub: Nat:69-LIGHT STRUCTURE INCIDENT Units:D71 E75 E62 T72 X-St:N PINE DR SLIPPERY ROCK L 201210488\n
[Ponderosa FD] 03/08 18:57 Repage:FM 1960-HC/IMPERIAL VAL, ; Map:332L- Sub: Nat:29-MOTOR VEHICLE INCIDENT Units:FO332E EQ63 M501 X-St:IMPERIAL VALLEY DR CARLSW 201210506\n
[Ponderosa FD] 03/09 02:32 300 N VISTA DR-HC, NORTH VISTA APTS; 204 Map:332U- Sub: Nat:31D02-UNCON - EFFECTIVE BREATHI Units:M501 FO332E FOEAST D51 E63 X-St:N MIST DR NORTH FRWY NB 201210555\n
[Ponderosa FD] 03/09 02:35 300 N VISTA DR-HC, NORTH VISTA APTS; 204 Map:332U- Sub: Nat:31D02-UNCON - EFFECTIVE BREATHI Units:M501 D51 E63 E64 X-St:N MIST DR NORTH FRWY NB 201210555\n
[Ponderosa FD] 03/09 18:47 NORTH FRWY NB-HC/FM 1960-HC, ; Map:332K- Sub: Nat:29-MOTOR VEHICLE INCIDENT Units:M501 FO332E EQ63 X-St:FM 1960 HIGHLAND CROSS DR 201210675\n
[Ponderosa FD] 03/09 18:48 NORTH FRWY NB-HC/FM 1960-HC, ; Map:332K- Sub: Nat:29-MOTOR VEHICLE INCIDENT Units:M501 FO332E EQ63 M51 E63 E61 X-St:FM 1960 HIGHLAND CROSS DR 201210675\n
[Ponderosa FD] 03/10 07:17 2302 WAGON GAP TRAIL-HC, ; Map:331Q- Sub: Nat:52B01G-FIRE ALARM - RESIDENTIAL Units:KVFD E61 X-St: 201210743\n
[Ponderosa FD] 03/10 2302 WAGON GAP TRAIL-HC, ; Map:331Q- Sub: Nat:52B01G-FIRE ALARM - RESIDENTIAL Units:E61 X-St: 201210743\n
[Ponderosa FD] 03/10 910 CYPRESS STATION DR-HC, PORTOFINO LANDING APTS; 1314 Map:332J- Sub: Nat:10D04-CHEST PAIN - CLAMMY Units:M501 61M FO332W X-St:RUSTYLEAF LN HOLLOW TREE 201210792\n
[Ponderosa FD] 03/10 15:00 16460 KUYKENDAHL RD-HC, EJS PLACE; 130 Map:331Q- Sub: Nat:26C02-SICK PERSON - ABNORMAL RE Units:61M FO331E M57 E64 EQ62 X-St:MICLIFF BLVD COLWELL RD 201210797\n
[Ponderosa FD] 03/10 15:47 905 CYPRESS STATION DR-HC, WYNDHAM PARK APTS; 202 Map:332J- Sub: Nat:69-LIGHT STRUCTURE INCIDENT Units:E64 X-St:RUSTYLEAF LN HOLLOW TREE 201210802\n
[Ponderosa FD] 03/10 19:51 420 LANTERN BEND DR-HC, PARK MANOR NURSING HOME; Map:332K- Sub: Nat:9-FULL ARREST Units:M501 FO332W FOSOU D51 E64 X-St:WESTFIELD PLACE DR GULF P 201210840\n
[Ponderosa FD] 03/10 21:02 NORTH FRWY NB-HC/FM 1960-HC, ; Map:332K- Sub: Nat:29-MOTOR VEHICLE INCIDENT Units:M56 FO332E EQ63 X-St:FM 1960 HIGHLAND CROSS DR 201210848\n
[Ponderosa FD] 03/10 21:02 Repage:NORTH FRWY NB-HC/FM 196, ; Map:332K- Sub: Nat:29B01M-MVI - INJURIES Units:M56 EQ63 X-St:FM 1960 HIGHLAND CROSS DR 201210848\n
[Ponderosa FD] 03/10 21:16 124 FM 1960-HC, TEXAS ROADHOUSE; Map:332L- Sub: Nat:29-MOTOR VEHICLE INCIDENT Units:E63 X-St:NORTH FRWY NB GATEWICK 201210849\n
[Ponderosa FD] 03/10 21:40 FM 1960-HC/NORTH FRWY NB-HC, ; Map:332L- Sub: Nat:29-MOTOR VEHICLE INCIDENT Units:M501 FO332E EQ63 X-St:NORTH FRWY NB GATEWICK 201210855\n
[Ponderosa FD] 03/10 21:56 NORTH FRWY SB-HC/SPRING CROSS, ; Map:292F- Sub: Nat:29D02P-MVI - ROLLOVER Units:E78 7002 E63 X-St:SPRING CROSSING DR SPRING 201210860\n
[Ponderosa FD] 03/11 12:03 324 FM 1960-HC, FM 1960 DIALYSIS CENTER ; 104 Map:332L- Sub: Nat:52C03S-FIRE ALARM - COMMERCIAL Units:PVFD E63 X-St:GATEWICK NORTH FRWY NB 201210930\n
[Ponderosa FD] 03/11 13:04 16503 N MIST DR-HC, ; Map:332U- Sub:NORTHVIEW Nat:69-LIGHT STRUCTURE INCIDENT Units:E63 X-St:N VISTA DR N WILLOW DR 201210940\n
[Ponderosa FD] 03/11 13:05 16503 N MIST DR-HC, ; Map:332U- Sub:NORTHVIEW Nat:69D06-RESIDENTIAL FIRE Units:E63 D60 E61 ALE31 L77 TW11 R7 X-St:N VISTA DR N WILLOW DR 201210940\n
[Ponderosa FD] 03/11 13:08 16503 N MIST DR-HC, ; Map:332U- Sub:NORTHVIEW Nat:69D06-RESIDENTIAL FIRE Units:E63 D60 E61 ALE31 L77 R71 EQ6 X-St:N VISTA DR N WILLOW DR 201210940\n
[Ponderosa FD] 03/11 13:15 16503 N MIST DR-HC, ; Map:332U- Sub:NORTHVIEW Nat:69D06-RESIDENTIAL FIRE Units:E63 D60 E61 ALE31 L77 R71 EQ6 X-St:N VISTA DR N WILLOW DR 201210940\n
[Ponderosa FD] 03/11 13:28 16503 N MIST DR-HC, ; Map:332U- Sub:NORTHVIEW Nat:69D06-RESIDENTIAL FIRE Units:E63 ALE31 L77 R71 M51 E12 E64 X-St:N VISTA DR N WILLOW DR 201210940\n
[Ponderosa FD] 03/11 14:25 NORTH FRWY NB-HC/N VISTA DR-H, ; Map:332U- Sub: Nat:29-MOTOR VEHICLE INCIDENT Units:M521 FO332E EQ63 X-St:N VISTA DR BAMMEL RD 201210952\n
[Ponderosa FD] 03/11 14:26 NORTH FRWY NB-HC/N VISTA DR-H, ; Map:332U- Sub: Nat:29-MOTOR VEHICLE INCIDENT Units:M521 FO332E EQ63 E63 X-St:N VISTA DR BAMMEL RD 201210952\n
[Ponderosa FD] 03/11 17:54 15725 BAMMEL VILLAGE DR-HC, NORTHGATE INN AND SUITES; 119 Map:331V- Sub: Nat:28-CVA Units:M55 61M FO331E E61 X-St:GILCREST DR BAMMELWOOD DR 201210973\n
[Ponderosa FD] 03/11 21:27 17911 MANTANA DR-HC, ; Map:331C- Sub:CYPRESSWOOD Nat:67A02-EXTINGUISHED FIRE Units:E62 X-St:FIR FOREST DR 201211002\n
[Ponderosa FD] 03/12 13:42 17030 NANES DR-HC, NANES PROFESSIONAL BUILD; Map:332N- Sub: Nat:31-UNCONSCIOUS PERSON Units:M52 61M FO332W E64 X-St:W FM 1960 TULIPTREE LN 201211113\n
[Ponderosa FD] 03/12 13:46 4646 DEER POINT DR-HC, ; Map:291F- Sub:FOX HOLLOW Nat:69D06-RESIDENTIAL FIRE Units:D71 E75 L72 L71 7006 7003 E62 X-St:FOX HOLLOW BLVD ROBINS NE 201211112\n
[Ponderosa FD] 03/12 14:31 NORTH FRWY MAINLANES SB-HC/W , ; Map:332K- Sub: Nat:71B01-VEHICLE FIRE Units:E64 L77 X-St:W FM 1960 W RICHEY RD 201211120\n
[Ponderosa FD] 03/12 16:11 W FM 1960-HC/SILVER RIDGE DR-, ; Map:331R- Sub: Nat:67-OUTSIDE FIRE Units:PVFD E64 X-St:SILVER RIDGE DR BUTTE CRE 201211131\n
[Ponderosa FD] 03/12 16:22 15411 SILVER RIDGE DR-HC, ; Map:331V- Sub: Nat:67-OUTSIDE FIRE Units:E64 X-St:WILLOW LEAF DR 201211132\n
[Ponderosa FD] 03/12 18:01 W FM 1960-HC/ROLLING CREEK DR, ; Map:331R- Sub: Nat:29-MOTOR VEHICLE INCIDENT Units:M51 E64 X-St:ROLLING CREEK DR BEAVER S 201211147\n
[Ponderosa FD] 03/13 00:45 1306 WOODYARD DR-HC, ; Map:332Z- Sub:PINE TRACE MHP Nat:MA-MUTUAL AID / ASSIST AGENCY Units:E63 X-St:NORTHBRIAR DR MCEANS DR 201211192\n
[Ponderosa FD] 03/13 00:45 Repage:1306 WOODYARD DR-HC, ; Map:332Z- Sub:PINE TRACE MHP Nat:MA-MUTUAL AID / ASSIST AGENCY Units:E63 D60 X-St:NORTHBRIAR DR MCEANS DR 201211192\n
[Ponderosa FD] 03/13 06:40 505 WELLS FARGO DR-HC, CARRIAGE PLACE APTS; 1012 Map:332P- Sub: Nat:06D02A-ASTHMA - DIFF SPEAKING Units:M57 E64 X-St:CYPRESS STATION DR HAFER 201211216\n
[Ponderosa FD] 03/13 07:34 4042 MARYWOOD DR-HC, ; Map:331B- Sub:CYPRESSDALE Nat:29-MOTOR VEHICLE INCIDENT Units:M53 FO331N E62 X-St:SORREL RIDGE DR LOST OAK 201211223\n
[Ponderosa FD] 03/13 10:18 17510 WILD OAK DR-HC, ; Map:332J- Sub:PINE OAK FOREST Nat:26C01-SICK PERSON - ALTERED LOC Units:M52 61M FO332W E61 X-St:HOLLOW TREE LN TIMBERDALE 201211239\n
[Ponderosa FD] 03/13 11:01 13555 KUYKENDAHL RD-HC, FAST TRACK GAS STATION; Map:372B-25 Sub: Nat:69-LIGHT STRUCTURE INCIDENT Units:SQ84 E83 E81 E63 L83 TW61 R81 X-St:ELLA BLVD LEAFDALE DR 201211246\n
[Ponderosa FD] 03/13 11:04 13555 KUYKENDAHL RD-HC, FAST TRACK GAS STATION; Map:372B-25 Sub: Nat:69D04-COMMERCIAL FIRE W/ HAZMAT Units:SQ84 E83 E81 L83 TW61 R81 M59 X-St:ELLA BLVD LEAFDALE DR 201211246\n
[Ponderosa FD] 03/13 15:54 19511 NORTH FRWY SB-HC, SUPER TARGET; Map:332B- Sub: Nat:52C03S-FIRE ALARM - COMMERCIAL Units:SVFD STA77 E62 X-St:HOLZWARTH RD CYPRESSWOOD 201211294\n

[] 03/28 10:28 8310 BUNKER BEND DR-HC, ; Map:338S- Sub:PINEHURST OF ATASCOCITA Nat:6-RESPIRATORY Units:E-M19 X-St:MAGNOLIA BEND DR LEGEND O 201213746\n
[] 03/28 12:24 5403 ENCHANTED TIMBERS DR-HC, ; Map:337S- Sub:OAKS OF ATASCOCITA Nat:17-INJURED PERSON FROM A FALL Units:E-M19 X-St:TIMBER FOREST DR OAK TIMB 201213772\n
[] 03/28 14:24 2914 RYAN CT-HC, ; Map:376F- Sub:ATASCOCITA ACRES Nat:24-O B CALL Units:E-M29 X-St:ATASCOCITA WAY 201213786\n
[] 03/28 17:22 E FM 1960-HC/TIMBER FOREST DR, ; Map:337W- Sub: Nat:29-MOTOR VEHICLE INCIDENT Units:E-M29 E-E39 X-St:TIMBER FOREST DR MOON TRA 201213805\n
[] 03/29 01:09 E FM 1960-HC/ATASCOCITA SHORE, ; Map:338S- Sub: Nat:6-RESPIRATORY Units:E-M19 X-St:ATASCOCITA SHORES DR PINE 201213861\n
[] 03/29 01:12 9718 FLEMING SPRINGS DR-HC, ; Map:376F- Sub: Nat:12-SEIZURES Units:E-M29 X-St:CASCADE HOUSE DR FLEMING 201213863\n

[] 06/12 21:05 16350 ELLA BLVD-HC, STONELEIGH AT ELLA CROSS; 225 Map:332S- Sub: Nat:69-LIGHT STRUCTURE INCIDENT Units:E64 D60 E63 SQ84 ALE31 L77 L3 X-St:SOUTHRIDGE RD NORTH FORES 201226204\n
[] 06/12 21:08 4600 W FM 1960-HC, ; Map:331W- Sub: Nat:52C04G-FIRE ALARM - APARTMENT(S Units:CESD E12 X-St:STUEBNER AIRLINE RD & VET 201226205\n
[] 06/12 21:08 Repage:4600 W FM 1960-HC, ; Map:331W- Sub: Nat:52C04G-FIRE ALARM - APARTMENT(S Units:CESD E12 X-St:STUEBNER AIRLINE RD & VET 201226205\n
[] 06/12 23:47 5927 HAVENWOODS DR-HC, ; Map:370H- Sub:GREENWOOD FOREST Nat:69-LIGHT STRUCTURE INCIDENT Units:E12 TW11 X-St:PARADISE VALLEY 201226242\n
[] 06/14 10:18 837 W FM 1960-HC, FM 1960 FAMILY PRACTICE; 105 Map:332N- Sub: Nat:33C06T-TRANSFER - EMERGENCY RES Units:M54 X-St:RED OAK DR ST EDWARDS 201226464\n
[] 06/14 16:06 6830 CHAMPIONS PLAZA DR-HC, CHAMPIONS WOODS APTS; 615 Map:370F- Sub: Nat:09E01-ARREST - NOT BREATHING Units:M57 FO370 FOSOU D52 11M X-St:CHAMPIONS PARK DR CUTTEN 201226501\n
[] 06/14 16:08 6830 CHAMPIONS PLAZA DR-HC, CHAMPIONS WOODS APTS; 615 Map:370F- Sub: Nat:09D01-ARREST - INEFFECTIVE BREA Units:M57 FO370 FOSOU D52 11M E12 X-St:CHAMPIONS PARK DR CUTTEN 201226501\n
[] 06/15 14:40 12801 CHAMPION FOREST DR-HC, CHAMPION FOREST APTS; 911 Map:370D- Sub:GREENWOOD FOREST Nat:17-INJURED PERSON FROM A FALL Units:M57 11M X-St:OLD LODGE DR W FM 1960 201226643\n
[] 06/15 14:42 12801 CHAMPION FOREST DR-HC, CHAMPION FOREST APTS; 911 Map:370D- Sub:GREENWOOD FOREST Nat:17A01-FALLS - NOT DANGEROUS ARE Units:M57 11M E12 X-St:OLD LODGE DR W FM 1960 201226643\n
[] 06/16 13:30 14106 CHAMPIONS VILLAGE DR-HC, ; Map:370B- Sub:CHAMPIONS PARK NORTH Nat:52B01G-FIRE ALARM - RESIDENTIAL Units:CESD E12 X-St:CHAMPIONS VILLAGE CT PRAI 201226801\n
[] 06/16 13:34 8318 ROCKFORD HALL LN-HC, ; Map:330P- Sub:CHAMPION FOREST Nat:67D01B-WILDLAND FIRE Units:E34 B11 X-St:CHAMPION FOREST DR MENTMO 201226805\n
[] 06/17 17:57 5411 HAVENWOODS DR-HC, ; Map:370H- Sub:GREENWOOD FOREST Nat:69-LIGHT STRUCTURE INCIDENT Units:E12 TW11 X-St:MISTY VALLEY DR GREENWOOD 201227003\n
[] 06/17 18:02 5411 HAVENWOODS DR-HC, ; Map:370H- Sub:GREENWOOD FOREST Nat:69D06-RESIDENTIAL FIRE Units:E12 TW11 B11 X-St:MISTY VALLEY DR GREENWOOD 201227003\n
[] 06/17 23:46 W FM 1960-HC/BRECK RD-HC, ; Map:331W- Sub: Nat:60B02O-ODOR OF GAS - UNKNOWN Units:E12 X-St:BRECK RD VETERANS MEMORIA 201227058\n
[] 06/19 09:57 CUTTEN RD-HC/HARGRAVE RD-HC, ; Map:370A- Sub: Nat:29-MOTOR VEHICLE INCIDENT Units:M53 FO370 E12 X-St:HARGRAVE RD & CHERRY HILL 201227270\n
[] 06/19 18:35 11600 BOURGEOIS FOREST DR-HC, ; Map:370M- Sub: Nat:29-MOTOR VEHICLE INCIDENT Units:M55 FO370 E12 X-St:BOURGEOIS RD 201227375\n
[] 06/19 21:13 5615 BOYCE SPRINGS DR-HC, ; Map:370D- Sub:GREENWOOD FOREST Nat:67A01U-TRANSFORMER FIRE - WIRE/ Units:E12 X-St:CHAMPION FOREST DR MISTY 201227405\n
[] 06/20 02:21 12801 CHAMPION FOREST DR-HC, CHAMPION FOREST APTS; Map:370D- Sub:GREENWOOD FOREST Nat:67B01-SMALL OUTSIDE FIRE Units:E12 X-St:OLD LODGE DR W FM 1960 201227440\n
[] 06/20 02:21 Repage:12801 CHAMPION FOREST D, CHAMPION FOREST APTS; Map:370D- Sub:GREENWOOD FOREST Nat:67B01-SMALL OUTSIDE FIRE Units:E12 X-St:OLD LODGE DR W FM 1960 201227440\n
[] 06/20 07:25 W FM 1960-HC/CUTTEN RD-HC, ; Map:370F- Sub: Nat:29-MOTOR VEHICLE INCIDENT Units:M55 FO370 E12 X-St:CUTTEN RD CHAMPIONS PARK 201227463\n
[] 06/20 07:25 Repage:W FM 1960-HC/CUTTEN RD-, ; Map:370F- Sub: Nat:29B04-MVI - UNKNOWN Units:M55 FO370 E12 X-St:CUTTEN RD CHAMPIONS PARK 201227463\n

*/

public class TXCyCreekCommCenterParser extends MsgParser {
  
  private static final Pattern MARKER = Pattern.compile("^(\\d\\d/\\d\\d) (?:(\\d\\d:\\d\\d) )?");
  private static final Pattern TRAILER = Pattern.compile(" +(\\d{8,}) *$");
  private static final Pattern VAL_PTN = Pattern.compile("\\bVAL\\b", Pattern.CASE_INSENSITIVE);
  
  public TXCyCreekCommCenterParser() {
    super("HARRIS COUNTY", "TX");
  }
  
  @Override
  public String getFilter() {
    return "CommCenter@ccems.com,93001,777";
  }

  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    
    // Strip message prefix
    
    data.strSource = subject;
    
    if (body.startsWith("/ ")) body = body.substring(2).trim();
    
    Matcher match = MARKER.matcher(body);
    if (!match.find()) return false;
    data.strDate = match.group(1);
    data.strTime = getOptGroup(match.group(2));
    body = body.substring(match.end()).trim();
    
    match = TRAILER.matcher(body);
    if (match.find()) {
      data.strCallId = match.group(1);
      body = body.substring(0,match.start());
    }
    
    if (body.startsWith("Repage:")) body = body.substring(7).trim();
    
    body = "Loc:" + body;
    
    Properties props = parseMessage(body, new String[]{"Loc", "Map", "Sub", "Nat", "Units", "X-St"});
    String sAddr = props.getProperty("Loc", "");
    Parser p = new Parser(sAddr);
    sAddr = p.get(',');
    sAddr = VAL_PTN.matcher(sAddr).replaceAll("VALLEY");
    parseAddressCity(sAddr, data);
    if (data.strCity.equals("HC")) data.strCity = "";
    data.strPlace = p.get(';');
    data.strApt = p.get();
    
    data.strMap = props.getProperty("Map", "");
    String sPlace = props.getProperty("Sub", "");
    if (sPlace.length() > 0) data.strPlace = sPlace;
    data.strCall = props.getProperty("Nat", "");
    data.strUnit = props.getProperty("Units", "");
    data.strCross = props.getProperty("X-St", "");
    
    if (data.strCall.contains("MA-MUTUAL AID")) data.strCity = "HOUSTON";
    return true;
  }
}
