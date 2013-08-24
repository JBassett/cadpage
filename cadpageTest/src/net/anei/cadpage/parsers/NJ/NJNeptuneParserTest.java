package net.anei.cadpage.parsers.NJ;

import net.anei.cadpage.parsers.BaseParserTest;

import org.junit.Test;

/*
Neptune, NJ
Contact: Ross Leibowitz <rossl32100@gmail.com>
Sender: iar@neptunetownship.org

Subject:NEPTUNE PD COMMS CENTER\nhamilton fire/sr fire and hamilton first aid 716 shell place carbon monoxide alarm
Subject:NEPTUNE PD COMMS CENTER\nhamilton first aid 716 shell pl carbon monoxide alarm with symptoms﻿
Subject:NEPTUNE PD COMMS CENTER\nHFA-CANCEL CALL 716 SHELL PL REFUSAL AS PER PATROL﻿
Subject:NEPTUNE PD COMMS CENTER\nall neptune ems all call ocean grove 98.5 lawrence ave female feeling weak and dizzy
Subject:NEPTUNE PD COMMS CENTER\nEMS ALL CALL 98.5 LAWRENCE AV FOR ALERTERD MENTAL STATUS

 */

public class NJNeptuneParserTest extends BaseParserTest {
  
  public NJNeptuneParserTest() {
    setParser(new NJNeptuneParser(), "NEPTUNE", "NJ");
  }
  
  @Test
  public void testParser() {

    doTest("T1",
        "Subject:NEPTUNE PD COMMS CENTER\nhamilton fire/sr fire and hamilton first aid 716 shell place carbon monoxide alarm",
        "CALL:hamilton fire/sr fire and hamilton first aid",
        "ADDR:716 shell place",
        "INFO:carbon monoxide alarm");

    doTest("T2",
        "Subject:NEPTUNE PD COMMS CENTER\nhamilton first aid 716 shell pl carbon monoxide alarm with symptoms﻿",
        "CALL:hamilton first aid",
        "ADDR:716 shell pl",
        "INFO:carbon monoxide alarm with symptoms﻿");

    doTest("T3",
        "Subject:NEPTUNE PD COMMS CENTER\nHFA-CANCEL CALL 716 SHELL PL REFUSAL AS PER PATROL﻿",
        "CALL:HFA-CANCEL CALL",
        "ADDR:716 SHELL PL",
        "INFO:REFUSAL AS PER PATROL﻿");

    doTest("T4",
        "Subject:NEPTUNE PD COMMS CENTER\nall neptune ems all call ocean grove 98.5 lawrence ave female feeling weak and dizzy",
        "CALL:all neptune ems all call ocean grove",
        "ADDR:98.5 lawrence ave",
        "INFO:female feeling weak and dizzy");

    doTest("T5",
        "Subject:NEPTUNE PD COMMS CENTER\nEMS ALL CALL 98.5 LAWRENCE AV FOR ALERTERD MENTAL STATUS",
        "CALL:EMS ALL CALL",
        "ADDR:98.5 LAWRENCE AV",
        "MADDR:98.5 LAWRENCE AVE",
        "INFO:FOR ALERTERD MENTAL STATUS");
  }
  
  public static void main(String[] args) {
    new NJNeptuneParserTest().generateTests("T1");
  }
}