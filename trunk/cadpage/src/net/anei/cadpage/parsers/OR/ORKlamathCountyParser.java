package net.anei.cadpage.parsers.OR;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.SmsMsgInfo.Data;

import net.anei.cadpage.parsers.FieldProgramParser;

/*
Klamath County, OR
Contact: David Ager <davidagerdc@gmail.com>
Sender: kc-911@kc911.us
System: EIS Cad

[NEW INCIDENT] 0/26/2011 1109\nEVENT # 1109260054 36\n2- ALS MEDICAL CALL\nPRIORITY 1\nLOCATION 3520 HOLBROOK ST\nCITY KF\nAPT\nPREMISE\nCOMMENT LYNN STILE RES\n541-8
[NEW INCIDENT] 9/26/2011 0809\nEVENT # 1109260025 36\nTONE - TONE REQUEST\nPRIORITY 3\nLOCATION 14800 PUCKETT RD\nCITY KF\nAPT\nPREMISE KENO FIRE SEPT. STATION 1\nCOMMENT
[NEW INCIDENT] 09/25/2011 0909\nEVENT # 1109250150 36\n3- MVA W/INJURIES\nPRIORITY 1\nLOCATION MP27 DEAD INDIAN \nMEMORIAL RD\nCITY KF\nAPT\nPREMISE MP27 DEAD INDIAN\nMEMOR
[NEW INCIDENT] 9/25/2011 0909\nEVENT # 1109250070 36\n2- ALS MEDICAL CALL\nPRIORITY 1\nLOCATION 12790 KENO WORDEN RD\nCITY KF\nAPT\nPREMISE\nCOMMENT 80YOM\nFELL FROM STA
[NEW INCIDENT] 9/24/2011 0209\nEVENT # 1109240085 36\n2- ALS MEDICAL CALL\nPRIORITY 1\nLOCATION 3502 HOLBROOK ST\nCITY KF\nAPT\nPREMISE\nCOMMENT MRDICAL ALARM\nADV AT TH
[NEW INCIDENT] 9/23/2011 0909\nEVENT # 1109230187 36\n2 - ALS MEDICAL CALL\nPRIORITY 1\nLOCATION 5309 BLUE MOUNTAIN DR\nCITY KF\nAPT\nPREMISE\nCOMMENT 49 YO MALE\nDIABETI
[NEW INCIDENT] 9/23/2011 1109\nEVENT # 1109230067 36\n10 - SMOKE REPORT / OUTDO...\nPRIORITY 1\nLOCATION PUCKETT LN/STAGECOACH RD\nCITY KF\nAPT\nPREMISE\nCOMMENT TURN LF
[NEW INCIDENT] 9/22/2011 0709\nEVENT # 1109220146 36\n2 - ALS MEDICAL CALL\nPRIORITY 1\nLOCATION 17904 PONDEROSA LN\nCITY KF\nAPT\nCOMMENT MALE PATIENT\n77 YOA\nD
[NEW INCIDENT] 09/22/2011 0209\nEVENT # 1109220011 36\n2 - ALS MEDICAL CALL\nPRIORITY 1\nLOCATION 5711 BLUE MOUNTAIN DR\nCITY KF\nAPT\nPREMISE \nCOMMENT LOW BP 86/66\nSHE I
[NEW INCIDENT] 9/20/2011 0809\nEVENT # 1109200147 36\n2 - ALS MEDICAL CALL\nPRIORITY 1\nLOCATION 16445 FINLEY BUTTE RD\nCITY KF\nAPT\nPREMISE\nCOMMENT FEMALE PATIENT \n41
[NEW INCIDENT] 9/18/2011 0209\nEVENT # 1109180080 36\n2 - ALS MEDICAL CALL\nPRIORITY 1\nLOCATION 9889 OLD WAGON RD\nCITY KF\nAPT\nPREMISE\nCOMMENT 83 YO FEMALE\nDIFFICULT
[NEW INCIDENT] 9/17/2011 1109\nEVENT # 1109170179 36\n2 - ALS MEDICAL CALL\nPRIORITY 1\nLOCATION 8404 MCLAUGHLIN LN\nCITY KF\nAPT\nPREMISE\nCOMMENT 6 DAYS OLD \nMALE DIFF
[NEW INCIDENT] 9/15/2011 0209\nEVENT #1109150134 36\n2 - ALS MEDIAL CALL\nPRIORITY 1\nLOCATION 5513 BLUE MOUNTAIN DR\nCITY KF\nAPT\nPREMISE\nCOMMENT 70YOF \nALERT\nBOK\nC/
[NEW INCIDENT] 09/13/2011 0109\nEVENT # 1109130070 36\n10 - SMOKE REPORT / OUTDO...\nPRIORITY 1\nLOCATION BEAR VALLEY DRIVE/\nKANN SPRINGS DR\nCITY KENO\nAPT\nPREMISE\nCOMMENT
[NEW INCIDENT] 9/12/2011 0909\nEVENT #1109120184 36\n2 - ALS MEDICAL CALL\nPRIORITY 1\nLOCATION 2160 ROUND LAKE RD\nCITY KF\nAPT \nPREMISE\nCOMMENT 20 YO FEMALE\nPASSED O
[NEW INCIDENT] 9/12/2011 1009\nEVENT # 1109120051 36\n2 - ALS MEDICAL CALL\nPRIORITY 1\nLOCATION 11747 WHISPERING\nPINES DR\nCITY KF\nAPT\nPREMISE\nCOMMENT MEDICAL ALARM
[NEW INCIDENT] 9/10/2011 0809\nEVENT # 1109100147 36\n2 - ALS MEDICAL CALL\nPRIORITY 1\nLOCATION 19777 HIGHWAY 97 S\nCITY KF\nAPT\nPREMISE WARDEN TRUCK STOP\nCOMMENT 36YO 
[NEW INCIDENT] 9/9/2011 0709\nEVENT # 1109090030 36\n2 - ALS MEDICAL CALL\nPRIORITY 1\nLOCATION 14307 RAVENWOOD DR\nCITY KF\nAPT\nPREMISE\nCOMMENT PRESSURE IN CHEST IN C
[NEW INCIDENT] 9/7/2011 0709\nEVENT # 1109070128 36\n2 - SUICIDE\nPRIORITY 3\nLOCATION 12774 CHRISTOPHER DR\nCITY KENO\nAPT\nPREMISE\nCOMMENT MN/ROBERT HAROW\nSTATED HE\n

*/

public class ORKlamathCountyParser extends FieldProgramParser {
  
  private static Pattern KEYWORD_PTN = Pattern.compile("(?<=\n(?:EVENT #|PRIORITY|LOCATION|CITY|APT|PREMISE|COMMENT))");

  private static Properties CITY_CODES = buildCodeTable(new String[]{
      "KF", "KLAMATH FALLS"
  });
  
  public ORKlamathCountyParser() {
    super(CITY_CODES, "KLAMATH COUNTY", "OR",
           "DATEID! EVENT_#:IDUNIT! CALL! PRIORITY:PRI! LOCATION:ADDR! CITY:CITY! APT:APT! PREMISE:PLACE? COMMENT:INFO+");
  }
  
  @Override
  public String getFilter() {
    return "kc-911@kc911.us";
  }

  @Override
  protected boolean parseMsg(String body, Data data) {
    body = KEYWORD_PTN.matcher(body).replaceAll(":");
    return parseFields(body.split("\n"), data);
  }
  
  private class MyDateIdField extends SkipField {
    
    public MyDateIdField() {
      setPattern(Pattern.compile("\\d{1,2}/\\d{1,2}/\\d{4} \\d+"), true);
    }
  }
  
  private static final Pattern ID_UNIT_PTN = Pattern.compile("(\\d{10}) (.*)");
  private class MyIdUnitField extends Field {
    
    @Override
    public void parse(String field, Data data) {
      Matcher match = ID_UNIT_PTN.matcher(field);
      if (!match.matches()) abort();
      data.strCallId = match.group(1).trim();
      data.strUnit =  match.group(2).trim();
    }
    
    @Override
    public String getFieldNames() {
      return "ID UNIT";
    }
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("DATEID")) return new MyDateIdField();
    if (name.equals("IDUNIT")) return new MyIdUnitField();
    return super.getField(name);
  }
}
