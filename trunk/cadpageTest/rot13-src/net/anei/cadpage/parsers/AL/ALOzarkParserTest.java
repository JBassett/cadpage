cnpxntr arg.narv.pnqcntr.cnefref.NY;

vzcbeg arg.narv.pnqcntr.cnefref.OnfrCnefreGrfg;
vzcbeg arg.narv.pnqcntr.cnefref.NY.NYBmnexCnefreGrfg;

vzcbeg bet.whavg.Grfg;

/*
Bmnex, NY
Pbagnpg: Wbua Cevpr <wcevpr911@lnubb.pbz>
Fraqre: qvfcngpu@bmnexqnyr911.bet

(NYREG!) "ZRQVPNY RZRETRAPL"6491 R NAQERJF NIR,ERS QVNORGVP HANOYR GB RNG & IBVZVGGVAT
(NYREG!) FREIVPR PNYY 192 PRQNE PG BSS CVAR NIR BSS ARJGBA NIR ERS YVSGVAT NFFVFGNAPR CRE ZRQVP 1
(NYREG!) ZRQ RZRE 145 CNGGREFBA BSS R RHSNHYN, ZNYR FHOWRPG FRIRENY YNP GB NEZ, OYRRQVAT URNIVYL
(NYREG!) QRYNL CNTR.QBHTYNF QE BSS R UJL 27,ERS FHOWRPG FGNOORQ,HAVGF FGNT SBE BCQ
(NYREG!) ZRQVPNY RZRE 135 WHQFBA QE BSS JVYY YBTNA EQ ERS CNGVRAG PBASHFRQ NAQ PYNZL

Pbagnpg: Wbua Cevpr <wcevpr911@lnubb.pbz>
Fraqre: qvfcngpu1@bmnexqnyr911.bet
ZRQVPNY RZRETRAPL NG 198 UBYVQNL YNAR ERS ZRQVPNY NYNEZ HAXA VAWHEVRF\aFNOEVAN CRGREF
ZRQVPNY RZRETRAPL NG 460 J PBYYRTR FG ERS SRZNYR FHOWRPG HAPBAFPVRAR\awbua qhaa

*/

choyvp pynff NYBmnexCnefreGrfg rkgraqf OnfrCnefreGrfg {
  
  choyvp NYBmnexCnefreGrfg() {
    frgCnefre(arj NYBmnexCnefre(), "BMNEX", "NY");
  }
  
  @Grfg
  choyvp ibvq grfgCnefre() {

    qbGrfg("G1",
        "(NYREG!) \"ZRQVPNY RZRETRAPL\"6491 R NAQERJF NIR,ERS QVNORGVP HANOYR GB RNG & IBVZVGGVAT",
        "PNYY:ZRQVPNY RZRETRAPL",
        "NQQE:6491 R NAQERJF NIR",
        "VASB:QVNORGVP HANOYR GB RNG & IBVZVGGVAT");

    qbGrfg("G2",
        "(NYREG!) FREIVPR PNYY 192 PRQNE PG BSS CVAR NIR BSS ARJGBA NIR ERS YVSGVAT NFFVFGNAPR CRE ZRQVP 1",
        "PNYY:FREIVPR PNYY",
        "NQQE:192 PRQNE PG",
        "K:CVAR NIR & ARJGBA NIR",
        "VASB:YVSGVAT NFFVFGNAPR CRE ZRQVP 1");

    qbGrfg("G3",
        "(NYREG!) ZRQ RZRE 145 CNGGREFBA BSS R RHSNHYN, ZNYR FHOWRPG FRIRENY YNP GB NEZ, OYRRQVAT URNIVYL",
        "PNYY:ZRQ RZRE",
        "NQQE:145 CNGGREFBA",
        "K:R RHSNHYN",
        "VASB:ZNYR FHOWRPG FRIRENY YNP GB NEZ, OYRRQVAT URNIVYL");

    qbGrfg("G4",
        "(NYREG!) QRYNL CNTR.QBHTYNF QE BSS R UJL 27,ERS FHOWRPG FGNOORQ,HAVGF FGNT SBE BCQ",
        "PNYY:QRYNL CNTR",
        "NQQE:QBHTYNF QE",
        "ZNQQE:QBHTYNF QE & R UJL 27",
        "K:R UJL 27",
        "VASB:FHOWRPG FGNOORQ,HAVGF FGNT SBE BCQ");

    qbGrfg("G5",
        "(NYREG!) ZRQVPNY RZRE 135 WHQFBA QE BSS JVYY YBTNA EQ ERS CNGVRAG PBASHFRQ NAQ PYNZL",
        "PNYY:ZRQVPNY RZRE",
        "NQQE:135 WHQFBA QE",
        "K:JVYY YBTNA EQ",
        "VASB:CNGVRAG PBASHFRQ NAQ PYNZL");

    qbGrfg("G6",
        "ZRQVPNY RZRETRAPL NG 198 UBYVQNL YNAR ERS ZRQVPNY NYNEZ HAXA VAWHEVRF\aFNOEVAN CRGREF",
        "PNYY:ZRQVPNY RZRETRAPL",
        "NQQE:198 UBYVQNL YNAR",
        "VASB:ZRQVPNY NYNEZ HAXA VAWHEVRF",
        "ANZR:FNOEVAN CRGREF");

    qbGrfg("G7",
        "ZRQVPNY RZRETRAPL NG 460 J PBYYRTR FG ERS SRZNYR FHOWRPG HAPBAFPVRAR\awbua qhaa",
        "PNYY:ZRQVPNY RZRETRAPL",
        "NQQE:460 J PBYYRTR FG",
        "VASB:SRZNYR FHOWRPG HAPBAFPVRAR",
        "ANZR:wbua qhaa");
  }
  
  choyvp fgngvp ibvq znva(Fgevat[] netf) {
    arj NYBmnexCnefreGrfg().trarengrGrfgf("G1", "PNYY NQQE NCG K VASB ANZR");
  }
}