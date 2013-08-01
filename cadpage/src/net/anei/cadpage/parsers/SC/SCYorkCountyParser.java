package net.anei.cadpage.parsers.SC;

import net.anei.cadpage.parsers.FieldProgramParser;
import net.anei.cadpage.parsers.MsgInfo.Data;



public class SCYorkCountyParser extends FieldProgramParser {

  public SCYorkCountyParser() {
    super("YORK COUNTY", "SC",
          "UNIT P:PRI! LOC:ADDR! X:X! NAR:INFO INC#:ID");
  }
  
  private class MyCrossField extends CrossField {
    @Override
    public void parse(String field, Data data) {
      
      Parser p = new Parser(field);
      data.strCross = p.get('*');
      data.strPlace = p.get('*');
      data.strCity = p.get('*');
      data.strPlace = append(data.strPlace, " - ", p.get('*'));
      data.strCall = p.get('*') + " - " + p.get('*');

      if (data.strPlace.length() > 0) {
        String tmp = new Parser(data.strPlace).get(' ');
        int ipt = data.strCity.indexOf(" " + tmp);
        if (ipt >= 0) data.strCity = data.strCity.substring(0,ipt).trim();
      }
    }
    
    @Override
    public String getFieldNames() {
      return "X PLACE CITY CALL";
    }
  }

  @Override
  public Field getField(String name) {
    if (name.equals("X")) return new MyCrossField();
    return super.getField(name);
  }
}
