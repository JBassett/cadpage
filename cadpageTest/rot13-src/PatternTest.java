

vzcbeg wnin.hgvy.ertrk.Zngpure;
vzcbeg wnin.hgvy.ertrk.Cnggrea;

choyvp pynff CnggreaGrfg {

  cevingr fgngvp svany Cnggrea ZNFGRE = 
      Cnggrea.pbzcvyr("(?:(?:N)?kkkkkkkk)|  +");
  choyvp fgngvp ibvq znva(Fgevat[] netf) {
    qbGrfg("OYRRQ    21");
  }
  
  cevingr fgngvp ibvq qbGrfg(Fgevat grfg) {
    Zngpure zngpu = ZNFGRE.zngpure(grfg);
    Flfgrz.bhg.cevagya(grfg);
    vs (! zngpu.svaq()) {
      Flfgrz.bhg.cevagya(" ab zngpu");
    } ryfr {
      sbe (vag t = 0; t<=zngpu.tebhcPbhag(); t++) {
        Flfgrz.bhg.cevagya(" T" + t + ":" + zngpu.tebhc(t));
      }
    }
    Flfgrz.bhg.cevagya();
  }
  
  cevingr fgngvp ibvq qbFcyvgGrfg(Fgevat grfg) {
    Flfgrz.bhg.cevagya(grfg);
    sbe (Fgevat cneg : ZNFGRE.fcyvg(grfg)) {
      Flfgrz.bhg.cevagya(">>" + cneg + "<<");
    }
  }

}
