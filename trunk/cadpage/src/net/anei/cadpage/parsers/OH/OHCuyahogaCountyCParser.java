package net.anei.cadpage.parsers.OH;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;

public class OHCuyahogaCountyCParser extends FieldProgramParser {

  public OHCuyahogaCountyCParser() {
    super(OHCuyahogaCountyParser.CITY_CODES, "CUYAHOGA COUNTY", "OH", 
          "CODE_CALL ADDR/SXa PLACE? CITY INFO+");
  }

  private static Pattern FROM_SRC = Pattern.compile("From: +(.*)");

  @Override
  protected boolean parseMsg(String subject, String body, Data data) {
    //Extract source from subject
    Matcher fsMat = FROM_SRC.matcher(subject);
    if (!fsMat.matches()) return false;
    data.strSource = fsMat.group(1);

    return super.parseFields(body.split(","), data);
  }

  @Override
  public Field getField(String name) {
    if (name.equals("ADDR")) return new AddressField("(?:1 +)?(.*?)(?: +0)?");
    if (name.equals("CODE_CALL")) return new MyCodeCallField();
    return super.getField(name);
  }

  private static Pattern CODE_CALL = Pattern.compile("(\\d{3})-(.*?)");

  private class MyCodeCallField extends Field {

    @Override
    public void parse(String field, Data data) {
      Matcher mat = CODE_CALL.matcher(field);
      if (!mat.matches()) abort();
      data.strCode = mat.group(1);
      data.strCall = mat.group(2);
    }

    @Override
    public String getFieldNames() {
      return "CODE CALL";
    }
  }

  @Override
  public String getProgram() {
    return "SRC " + super.getProgram();
  }
}
