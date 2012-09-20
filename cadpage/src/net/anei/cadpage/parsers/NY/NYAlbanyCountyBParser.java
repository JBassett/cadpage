package net.anei.cadpage.parsers.NY;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;

/**
 * Albany County, NY
 */
public class NYAlbanyCountyBParser extends FieldProgramParser {
  
  private static final Pattern SUBJECT_PTN = Pattern.compile("(\\w+?)_cad (\\w+?)-(\\d\\d:\\d\\d)");
  private static final DateFormat DATE_FMT = new SimpleDateFormat("MMMdd");

  public NYAlbanyCountyBParser() {
    super("ALBANY COUNTY", "NY",
           "TYPE:CALL! LOC:ADDR! BTWN:X? NATURE:INFO!");
  }
  
  @Override
  public String getFilter() {
    return "dispatch@edispatches.com";
  }

  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    Matcher match = SUBJECT_PTN.matcher(subject);
    if (!match.matches()) return false;
    data.strSource = match.group(1);
    setDate(DATE_FMT, match.group(2), data);
    data.strTime = match.group(3);
    
    return parseFields(body.split("\n"), data);
  }
  
  @Override
  public String getProgram() {
    return "SRC DATE TIME " + super.getProgram();
  }
  
  // Info field may contain a cross street
  private class MyInfoField extends InfoField {
    @Override
    public void parse(String field, Data data) {
      if (checkAddress(field) > 0) {
        data.strCross = field;
      } else {
        data.strSupp = field;
      }
    }
  }
  
  @Override
  public Field getField(String name) {
    if (name.equals("INFO")) return new MyInfoField();
    return super.getField(name);
  }
}
	