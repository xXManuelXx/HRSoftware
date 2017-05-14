/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mfehrenbach
 */
import com.hrsoftware.jpa.Gehaltsabrechnungvariableeingaben;
import com.hrsoftware.jpa.Mitarbeiter;
import com.hrsoftware.jpa.Stammdaten;
import com.hrsoftware.jpacontroller.StammdatenFacade;
import java.io.Serializable;
import static java.lang.Math.max;
import static java.lang.Math.min;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
 
@Named("gehaltsabrechnungsrechner")
@ConversationScoped
public class Gehaltsabrechnungsrechner implements Serializable {
   
    //Stammdaten
    private Stammdaten baseData;
    private OptionalSalaryStatement optionalSalaryStatement;
    private Mitarbeiter employee;
    private double employeeSalaryYear;
    //Lohnsteuer
   private double lohnst_alter1;
   private double lohnst_zkf;
   private double lohnst_lzz;
   private double lohnst_krv;
   private double lohnst_zre4j;
   private double lohnst_stkl;
   private double lohnst_jlfreib;
   private double lohnst_jlhinzu;
   private double lohnst_rvbemes;
   private double lohnst_pkv;
   private double lohnst_pv;
   private double lohnst_faktorF;
   
   //Einmalzahlungen
   private double einmalzahlung;
   
   //laufende zahlungen
   private double laufendezahlungen;
   
   //einmaliger geltw. Vorteil
   private double einmaligerGeltwVorteil;
   
   //laufender geltw.Vorteil
   private double laufenderGeltwVorteil;
   
   private double rvPflichtigerBeitrag;
   
   private int miniJobFlatrate = 0;
   
   //MRE4ALTE
   private double mre4alte_tab4;
   private double mre4alte_tab5;
   private double mre4alte_alteanteil;
   private double mre4alte_alte;
   private double mre4alte_zre4;
   private double mre4alte_zre4vp;
   
   //MRE4
   private double mre4_zre4;
   private double mre4_zre4vp;
   
   //MZTABFB
   private double mztabfb_kztab;
   private double mztabfb_anp = 1000;
   private double mztabfb_efa;
   private double mztabfb_sap;
   private double mztabfb_kfb;
   private double mztabfb_ztabfb;
   
   //UPEVP
   private double upevp_zre4vp;
   private double upevp_vsp1;
   private double upevp_vhb;
   private double upevp_vsp2;
   private double upevp_zukvPv;
   private double upevp_kv;
   private double upevp_kvVhb;
   private double upevp_vspn;
   
   //UMVSP
   private double umvsp_zve;
   private double umvsp_zzx;
   
   //UPTAB07
   private double uptab07_st;
   
   //MST5-6
   private double mst56_x;
   private double mst56_st1;
   private double mst56_x1;
   private double mst56_st2;
   private double mst56_diff;
   private double mst56_mist;
   private double mst56_st;
   private double mst56_st3;
   private double mst56_vergl;
   private double mst56_st4;
   private double mst56_st5;
   private double mst56_reichst;
   private double mst56_lstjahr;
   private double mst56_jw;
   
   //MLSTJAHR
   private double mlstjahr_lstlzzSum;
   private double mlstjahr_ztabfb;
   private double mlstjahr_zve;
   private double mlstjahr_zveX;
   private double mlstjahr_st;
   private double mlstjahr_jbmg;
   
   //MSOLZ
   private double msolz_solzfrei;
   private double msolz_solzj;
   private double msolz_solzmin;
   private double msolz_solzj1;
   private double  msolz_jw;
   
   //SOLZ
   private double solz_anteil1;
   private double solz_solzSum;
   private double solz_jw;
   
   //BK
   private double bk_anteil1;
   private double bk_bk;
   private double  bk_kistSum;
   
   //Sozialversicherung
   private double solzvers_kvBemes;
   private double solzvers_rvBemes;
   private double solzvers_rvAn;
   private double solzvers_avAn;
   private double solzvers_kvAn;
   private double solzvers_kvZusatz;
   private double solzvers_pvAn;
   private double solzvers_rvAg;
   private double solzvers_avAg;
   private double solzvers_kvAg;
   private double solzvers_pvAg;
   
   //SozV Gleitzone
   private double sozgleit_sozVEntgelt;
   private double sozgleit_rvAn;
   private double sozgleit_avAn;
   private double sozgleit_kvAn;
   private double sozgleit_kvZusatz;
   private double sozgleit_pvAn;
   
   
   //Gleitzone 400-450
   private double gleitzone_sozVEntgelt;
   private double gleitzone_rvAn;
   private double gleitzone_avAn;
   private double gleitzone_kvAn;
   private double gleitzone_kvZusatz;
   private double gleitzone_pvAn;
   
   //Ãœbertragswerte
    private double uebertragw_rvAn;
    private double uebertragw_avAn;
    private double uebertragw_kvAn;
    private double uebertragw_kvZusatz;
    private double uebertragw_pvAn;
    
    //jahreslohn+abger.EinmZ
    private double jahreslohn_alter1;
    private double jahreslohn_zkf;
    private double jahreslohn_llz;
    private double jahreslohn_krv;
    private double jahreslohn_zre4j;
    private double jahreslohn_stkl;
    private double jahreslohn_jlfreib;
    private double jahreslohn_jlhinzu;
    private double jahreslohn_rvBemes;
    private double jahreslohn_pkv;
    private double jahreslohn_pv;
    private double jahreslohn_faktorF;
    
    //MRE4ALTE
    private double mre4alte1_tab4;
    private double mre4alte1_tab5;
    private double mre4alte1_alteanteil;
    private double mre4alte1_alte;
    private double mre4alte1_zre4;
    private double mre4alte1_zre4vp;
    
    //MRE41
    private double mre41_zre4;
    private double mre41_zre4vp;
    
    
    //MZTABFB
   private double mztabfb1_kztab;
   private double mztabfb1_anp;
   private double mztabfb1_efa;
   private double mztabfb1_sap;
   private double mztabfb1_kfb;
   private double mztabfb1_ztabfb;
   
   //UPEVP
   private double upevp1_zre4vp;
   private double upevp1_vsp1;
   private double upevp1_vhb;
   private double upevp1_vsp2;
   private double upevp1_zukvPv;
   private double upevp1_kv;
   private double upevp1_kvVhb;
   private double upevp1_vspn;
   
   //UMVSP
   private double umvsp1_zve;
   private double umvsp1_zzx;
   
   //UPTAB07
   private double uptab071_st;
   
   //MST5-6
   private double mst561_x;
   private double mst561_st1;
   private double mst561_x1;
   private double mst561_st2;
   private double mst561_diff;
   private double mst561_mist;
   private double mst561_st;
   private double mst561_st3;
   private double mst561_vergl;
   private double mst561_st4;
   private double mst561_st5;
   private double mst561_reichst;
   private double mst561_lstjahr;
   private double mst561_jw;
   
   //MLSTJAHR
   private double mlstjahr1_lstlzzSum;
   private double mlstjahr1_ztabfb;
   private double mlstjahr1_zve;
   private double mlstjahr1_zveX;
   private double mlstjahr1_st;
   private double mlstjahr1_jbmg;
   
   //MSOLZ
   private double msolz1_solzfrei;
   private double msolz1_solzj;
   private double msolz1_solzmin;
   
   //BK
   private double bk1_anteil1;
   private double bk1_bk;
   private double bk1_bkeinmal;
   private double bk1_kist;
    
   
   
   //jahreslohn+alle .EinmZ
    private double jahreslohn1_alter1;
    private double jahreslohn1_zkf;
    private double jahreslohn1_llz;
    private double jahreslohn1_krv;
    private double jahreslohn1_zre4j;
    private double jahreslohn1_stkl;
    private double jahreslohn1_jlfreib;
    private double jahreslohn1_jlhinzu;
    private double jahreslohn1_rvBemes;
    private double jahreslohn1_pkv;
    private double jahreslohn1_pv;
    private double jahreslohn1_faktorF;
    
    //MRE4ALTE
    private double mre4alte2_tab4;
    private double mre4alte2_tab5;
    private double mre4alte2_alteanteil;
    private double mre4alte2_alte;
    private double mre4alte2_zre4;
    private double mre4alte2_zre4vp;
    
    //MRE41
    private double mre42_zre4;
    private double mre42_zre4vp;
    
    
    //MZTABFB
   private double mztabfb2_kztab;
   private double mztabfb2_anp;
   private double mztabfb2_efa;
   private double mztabfb2_sap;
   private double mztabfb2_kfb;
   private double mztabfb2_ztabfb;
   
   //UPEVP
   private double upevp2_zre4vp;
   private double upevp2_vsp1;
   private double upevp2_vhb;
   private double upevp2_vsp2;
   private double upevp2_zukvPv;
   private double upevp2_kv;
   private double upevp2_kvVhb;
   private double upevp2_vspn;
  
   //UMVSP
   private double umvsp2_zve;
   private double umvsp2_zzx;
   
   //UPTAB07
   private double uptab072_st;
   
   //MST5-6
   private double mst562_x;
   private double mst562_st1;
   private double mst562_x1;
   private double mst562_st2;
   private double mst562_diff;
   private double mst562_mist;
   private double mst562_st;
   private double mst562_st3;
   private double mst562_vergl;
   private double mst562_st4;
   private double mst562_st5;
   private double mst562_reichst;
   private double mst562_lstjahr;
   private double mst562_jw;
   
   //MLSTJAHR
   private double mlstjahr2_lstlzzSum;
   private double mlstjahr2_ztabfb;
   private double mlstjahr2_zve;
   private double mlstjahr2_zveX;
   private double mlstjahr2_st;
   private double mlstjahr2_jbmg;
   
   //MSOLZ
   private double msolz2_solzfrei;
   private double msolz2_solzj;

   
   //BK
   private double bk2_anteil1;
   private double bk2_bk;
   
   //sozialversichungspflichtiger Anteil des baV-Beitrags
   private double solzAnteilBaV_bavBeitrag;
   private double solzAnteilBaV_sozVPflicht;
   private double solzAnteilBaV_sozVpflichtBrut;
    
   
   //RV-pflichtiger Beitrag
   private double kvPflichtigerBeitrag;
   
   
   //Umlagen
   private double u1;
   private double u2;
   private double insolvenz;
   
   //UmschlÃ¤ge Minijob/Gleitzone
   private double mini_u1;
   private double mini_u2;
   private double mini_insolvenz;
   
   //Ãœbergabewerte
   private double uebWerte_u1;
   private double uebWerte_u2;
   private double uebWerte_insolvenz;
   
   //U1 und U2 bei Minijob
   private double minijob_u1;
   private double minijob_u2;
   
   //MiniJob % Pauschale
   private double minijobpausch_miniJob;
   private double minijobpausch_kv;
   private double minijobpausch_lst;
   private double minijobpausch_berueck;
   
   //Pflege
   private double pflege_pflegeversicherung;
   private double pflege_pflegeSachsen;
   private double pflege_pflegeArbeitgeber;

    public double getEmployeeSalaryYear() {
        return employeeSalaryYear;
    }

    public void setEmployeeSalaryYear(double employeeSalaryYear) {
        this.employeeSalaryYear = employeeSalaryYear;
    }
   
   
   

    public OptionalSalaryStatement getOptionalSalaryStatement() {
        return optionalSalaryStatement;
    }

    public void setOptionalSalaryStatement(OptionalSalaryStatement optionalSalaryStatement) {
        this.optionalSalaryStatement = optionalSalaryStatement;
    }

   
   
   
   public void setStammdaten(Stammdaten baseData){
       this.baseData = baseData;
   }

    public Mitarbeiter getEmployee() {
        return employee;
    }

    public void setEmployee(Mitarbeiter employee) {
        this.employee = employee;
    }
   
   
   
    public double getLohnst_alter1() {
        return lohnst_alter1;
    }

    public void setLohnst_alter1(double lohnst_alter1) {
        this.lohnst_alter1 = lohnst_alter1;
    }

    public double getLohnst_zkf() {
        return lohnst_zkf;
    }

    public void setLohnst_zkf(double lohnst_zkf) {
        this.lohnst_zkf = lohnst_zkf;
    }

    public double getLohnst_lzz() {
        return lohnst_lzz;
    }

    public void setLohnst_lzz(double lohnst_lzz) {
        this.lohnst_lzz = lohnst_lzz;
    }

    public double getLohnst_krv() {
        return lohnst_krv;
    }

    public void setLohnst_krv(double lohnst_krv) {
        this.lohnst_krv = lohnst_krv;
    }

    public double getLohnst_zre4j() {
        return lohnst_zre4j;
    }

    public void setLohnst_zre4j(double lohnst_zre4j) {
        this.lohnst_zre4j = lohnst_zre4j;
    }

    public double getLohnst_stkl() {
        return lohnst_stkl;
    }

    public void setLohnst_stkl(double lohnst_stkl) {
        this.lohnst_stkl = lohnst_stkl;
    }

    public double getLohnst_jlfreib() {
        return lohnst_jlfreib;
    }

    public void setLohnst_jlfreib(double lohnst_jlfreib) {
        this.lohnst_jlfreib = lohnst_jlfreib;
    }

    public double getLohnst_jlhinzu() {
        return lohnst_jlhinzu;
    }

    public void setLohnst_jlhinzu(double lohnst_jlhinzu) {
        this.lohnst_jlhinzu = lohnst_jlhinzu;
    }

    public double getLohnst_rvbemes() {
        return lohnst_rvbemes;
    }

    public void setLohnst_rvbemes(double lohnst_rvbemes) {
        this.lohnst_rvbemes = lohnst_rvbemes;
    }

    public double getLohnst_pkv() {
        return lohnst_pkv;
    }

    public void setLohnst_pkv(double lohnst_pkv) {
        this.lohnst_pkv = lohnst_pkv;
    }

    public double getLohnst_pv() {
        return lohnst_pv;
    }

    public void setLohnst_pv(double lohnst_pv) {
        this.lohnst_pv = lohnst_pv;
    }

    public double getLohnst_faktorF() {
        return lohnst_faktorF;
    }

    public void setLohnst_faktorF(double lohnst_faktorF) {
        this.lohnst_faktorF = lohnst_faktorF;
    }

    public double getEinmalzahlung() {
        return einmalzahlung;
    }

    public void setEinmalzahlung(double einmalzahlung) {
        this.einmalzahlung = einmalzahlung;
    }

    public double getLaufendezahlungen() {
        return laufendezahlungen;
    }

    public void setLaufendezahlungen(double laufendezahlungen) {
        this.laufendezahlungen = laufendezahlungen;
    }

    public double getEinmaligerGeltwVorteil() {
        return einmaligerGeltwVorteil;
    }

    public void setEinmaligerGeltwVorteil(double einmaligerGeltwVorteil) {
        this.einmaligerGeltwVorteil = einmaligerGeltwVorteil;
    }

    public double getLaufenderGeltwVorteil() {
        return laufenderGeltwVorteil;
    }

    public void setLaufenderGeltwVorteil(double laufenderGeltwVorteil) {
        this.laufenderGeltwVorteil = laufenderGeltwVorteil;
    }

    public double getMre4alte_tab4() {
        return mre4alte_tab4;
    }

    public void setMre4alte_tab4(double mre4alte_tab4) {
        this.mre4alte_tab4 = mre4alte_tab4;
    }

    public double getMre4alte_tab5() {
        return mre4alte_tab5;
    }

    public void setMre4alte_tab5(double mre4alte_tab5) {
        this.mre4alte_tab5 = mre4alte_tab5;
    }

    public double getMre4alte_alteanteil() {
        return mre4alte_alteanteil;
    }

    public void setMre4alte_alteanteil(double mre4alte_alteanteil) {
        this.mre4alte_alteanteil = mre4alte_alteanteil;
    }

    public double getMre4alte_alte() {
        return mre4alte_alte;
    }

    public void setMre4alte_alte(double mre4alte_alte) {
        this.mre4alte_alte = mre4alte_alte;
    }

    public double getMre4alte_zre4() {
        return mre4alte_zre4;
    }

    public void setMre4alte_zre4(double mre4alte_zre4) {
        this.mre4alte_zre4 = mre4alte_zre4;
    }

    public double getMre4alte_zre4vp() {
        return mre4alte_zre4vp;
    }

    public void setMre4alte_zre4vp(double mre4alte_zre4vp) {
        this.mre4alte_zre4vp = mre4alte_zre4vp;
    }

    public double getMre4_zre4() {
        return mre4_zre4;
    }

    public void setMre4_zre4(double mre4_zre4) {
        this.mre4_zre4 = mre4_zre4;
    }

    public double getMre4_zre4vp() {
        return mre4_zre4vp;
    }

    public void setMre4_zre4vp(double mre4_zre4vp) {
        this.mre4_zre4vp = mre4_zre4vp;
    }

    public double getMztabfb_kztab() {
        return mztabfb_kztab;
    }

    public void setMztabfb_kztab(double mztabfb_kztab) {
        this.mztabfb_kztab = mztabfb_kztab;
    }

    public double getMztabfb_anp() {
        return mztabfb_anp;
    }

    public void setMztabfb_anp(double mztabfb_anp) {
        this.mztabfb_anp = mztabfb_anp;
    }

    public double getMztabfb_efa() {
        return mztabfb_efa;
    }

    public void setMztabfb_efa(double mztabfb_efa) {
        this.mztabfb_efa = mztabfb_efa;
    }

    public double getMztabfb_sap() {
        return mztabfb_sap;
    }

    public void setMztabfb_sap(double mztabfb_sap) {
        this.mztabfb_sap = mztabfb_sap;
    }

    public double getMztabfb_kfb() {
        return mztabfb_kfb;
    }

    public void setMztabfb_kfb(double mztabfb_kfb) {
        this.mztabfb_kfb = mztabfb_kfb;
    }

    public double getMztabfb_ztabfb() {
        return mztabfb_ztabfb;
    }

    public void setMztabfb_ztabfb(double mztabfb_ztabfb) {
        this.mztabfb_ztabfb = mztabfb_ztabfb;
    }

    public double getUpevp_zre4vp() {
        return upevp_zre4vp;
    }

    public void setUpevp_zre4vp(double upevp_zre4vp) {
        this.upevp_zre4vp = upevp_zre4vp;
    }

    public double getUpevp_vsp1() {
        return upevp_vsp1;
    }

    public void setUpevp_vsp1(double upevp_vsp1) {
        this.upevp_vsp1 = upevp_vsp1;
    }

    public double getUpevp_vhb() {
        return upevp_vhb;
    }

    public void setUpevp_vhb(double upevp_vhb) {
        this.upevp_vhb = upevp_vhb;
    }

    public double getUpevp_vsp2() {
        return upevp_vsp2;
    }

    public void setUpevp_vsp2(double upevp_vsp2) {
        this.upevp_vsp2 = upevp_vsp2;
    }

    public double getUpevp_zukvPv() {
        return upevp_zukvPv;
    }

    public void setUpevp_zukvPv(double upevp_zukvPv) {
        this.upevp_zukvPv = upevp_zukvPv;
    }

    public double getUpevp_kv() {
        return upevp_kv;
    }

    public void setUpevp_kv(double upevp_kv) {
        this.upevp_kv = upevp_kv;
    }

    public double getUpevp_kvVhb() {
        return upevp_kvVhb;
    }

    public void setUpevp_kvVhb(double upevp_kvVhb) {
        this.upevp_kvVhb = upevp_kvVhb;
    }

    public double getUpevp_vspn() {
        return upevp_vspn;
    }

    public void setUpevp_vspn(double upevp_vspn) {
        this.upevp_vspn = upevp_vspn;
    }

    public double getUmvsp_zve() {
        return umvsp_zve;
    }

    public void setUmvsp_zve(double umvsp_zve) {
        this.umvsp_zve = umvsp_zve;
    }

    public double getUmvsp_zzx() {
        return umvsp_zzx;
    }

    public void setUmvsp_zzx(double umvsp_zzx) {
        this.umvsp_zzx = umvsp_zzx;
    }

    public double getUptab07_st() {
        return uptab07_st;
    }

    public void setUptab07_st(double uptab07_st) {
        this.uptab07_st = uptab07_st;
    }

    public double getMst56_x() {
        return mst56_x;
    }

    public void setMst56_x(double mst56_x) {
        this.mst56_x = mst56_x;
    }

    public double getMst56_st1() {
        return mst56_st1;
    }

    public void setMst56_st1(double mst56_st1) {
        this.mst56_st1 = mst56_st1;
    }

    public double getMst56_x1() {
        return mst56_x1;
    }

    public void setMst56_x1(double mst56_x1) {
        this.mst56_x1 = mst56_x1;
    }

    public double getMst56_st2() {
        return mst56_st2;
    }

    public void setMst56_st2(double mst56_st2) {
        this.mst56_st2 = mst56_st2;
    }

    public double getMst56_diff() {
        return mst56_diff;
    }

    public void setMst56_diff(double mst56_diff) {
        this.mst56_diff = mst56_diff;
    }

    public double getMst56_mist() {
        return mst56_mist;
    }

    public void setMst56_mist(double mst56_mist) {
        this.mst56_mist = mst56_mist;
    }

    public double getMst56_st() {
        return mst56_st;
    }

    public void setMst56_st(double mst56_st) {
        this.mst56_st = mst56_st;
    }

    public double getMst56_st3() {
        return mst56_st3;
    }

    public void setMst56_st3(double mst56_st3) {
        this.mst56_st3 = mst56_st3;
    }

    public double getMst56_vergl() {
        return mst56_vergl;
    }

    public void setMst56_vergl(double mst56_vergl) {
        this.mst56_vergl = mst56_vergl;
    }

    public double getMst56_st4() {
        return mst56_st4;
    }

    public void setMst56_st4(double mst56_st4) {
        this.mst56_st4 = mst56_st4;
    }

    public double getMst56_st5() {
        return mst56_st5;
    }

    public void setMst56_st5(double mst56_st5) {
        this.mst56_st5 = mst56_st5;
    }

    public double getMst56_reichst() {
        return mst56_reichst;
    }

    public void setMst56_reichst(double mst56_reichst) {
        this.mst56_reichst = mst56_reichst;
    }

    public double getMst56_lstjahr() {
        return mst56_lstjahr;
    }

    public void setMst56_lstjahr(double mst56_lstjahr) {
        this.mst56_lstjahr = mst56_lstjahr;
    }

    public double getMst56_jw() {
        return mst56_jw;
    }

    public void setMst56_jw(double mst56_jw) {
        this.mst56_jw = mst56_jw;
    }

    public double getMlstjahr_lstlzzSum() {
        return mlstjahr_lstlzzSum;
    }

    public void setMlstjahr_lstlzzSum(double mlstjahr_lstlzzSum) {
        this.mlstjahr_lstlzzSum = mlstjahr_lstlzzSum;
    }

    public double getMlstjahr_ztabfb() {
        return mlstjahr_ztabfb;
    }

    public void setMlstjahr_ztabfb(double mlstjahr_ztabfb) {
        this.mlstjahr_ztabfb = mlstjahr_ztabfb;
    }

    public double getMlstjahr_zve() {
        return mlstjahr_zve;
    }

    public void setMlstjahr_zve(double mlstjahr_zve) {
        this.mlstjahr_zve = mlstjahr_zve;
    }

    public double getMlstjahr_zveX() {
        return mlstjahr_zveX;
    }

    public void setMlstjahr_zveX(double mlstjahr_zveX) {
        this.mlstjahr_zveX = mlstjahr_zveX;
    }

    public double getMlstjahr_st() {
        return mlstjahr_st;
    }

    public void setMlstjahr_st(double mlstjahr_st) {
        this.mlstjahr_st = mlstjahr_st;
    }

    public double getMlstjahr_jbmg() {
        return mlstjahr_jbmg;
    }

    public void setMlstjahr_jbmg(double mlstjahr_jbmg) {
        this.mlstjahr_jbmg = mlstjahr_jbmg;
    }

    public double getMsolz_solzfrei() {
        return msolz_solzfrei;
    }

    public void setMsolz_solzfrei(double msolz_solzfrei) {
        this.msolz_solzfrei = msolz_solzfrei;
    }

    public double getMsolz_solzj() {
        return msolz_solzj;
    }

    public void setMsolz_solzj(double msolz_solzj) {
        this.msolz_solzj = msolz_solzj;
    }

    public double getMsolz_solzmin() {
        return msolz_solzmin;
    }

    public void setMsolz_solzmin(double msolz_solzmin) {
        this.msolz_solzmin = msolz_solzmin;
    }

    public double getMsolz_solzj1() {
        return msolz_solzj1;
    }

    public void setMsolz_solzj1(double msolz_solzj1) {
        this.msolz_solzj1 = msolz_solzj1;
    }

    public double getMsolz_jw() {
        return msolz_jw;
    }

    public void setMsolz_jw(double msolz_jw) {
        this.msolz_jw = msolz_jw;
    }

    public double getSolz_anteil1() {
        return solz_anteil1;
    }

    public void setSolz_anteil1(double solz_anteil1) {
        this.solz_anteil1 = solz_anteil1;
    }

    public double getSolz_solzSum() {
        return solz_solzSum;
    }

    public void setSolz_solzSum(double solz_solzSum) {
        this.solz_solzSum = solz_solzSum;
    }

    public double getSolz_jw() {
        return solz_jw;
    }

    public void setSolz_jw(double solz_jw) {
        this.solz_jw = solz_jw;
    }

    public double getBk_anteil1() {
        return bk_anteil1;
    }

    public void setBk_anteil1(double bk_anteil1) {
        this.bk_anteil1 = bk_anteil1;
    }

    public double getBk_bk() {
        return bk_bk;
    }

    public void setBk_bk(double bk_bk) {
        this.bk_bk = bk_bk;
    }

    public double getBk_kistSum() {
        return bk_kistSum;
    }

    public void setBk_kistSum(double bk_kistSum) {
        this.bk_kistSum = bk_kistSum;
    }

    public double getSolzvers_kvBemes() {
        return solzvers_kvBemes;
    }

    public void setSolzvers_kvBemes(double solzvers_kvBemes) {
        this.solzvers_kvBemes = solzvers_kvBemes;
    }

    public double getSolzvers_rvBemes() {
        return solzvers_rvBemes;
    }

    public void setSolzvers_rvBemes(double solzvers_rvBemes) {
        this.solzvers_rvBemes = solzvers_rvBemes;
    }

    public double getSolzvers_rvAn() {
        return solzvers_rvAn;
    }

    public void setSolzvers_rvAn(double solzvers_rvAn) {
        this.solzvers_rvAn = solzvers_rvAn;
    }

    public double getSolzvers_avAn() {
        return solzvers_avAn;
    }

    public void setSolzvers_avAn(double solzvers_avAn) {
        this.solzvers_avAn = solzvers_avAn;
    }

    public double getSolzvers_kvAn() {
        return solzvers_kvAn;
    }

    public void setSolzvers_kvAn(double solzvers_kvAn) {
        this.solzvers_kvAn = solzvers_kvAn;
    }

    public double getSolzvers_kvZusatz() {
        return solzvers_kvZusatz;
    }

    public void setSolzvers_kvZusatz(double solzvers_kvZusatz) {
        this.solzvers_kvZusatz = solzvers_kvZusatz;
    }

    public double getSolzvers_pvAn() {
        return solzvers_pvAn;
    }

    public void setSolzvers_pvAn(double solzvers_pvAn) {
        this.solzvers_pvAn = solzvers_pvAn;
    }

    public double getSolzvers_rvAg() {
        return solzvers_rvAg;
    }

    public void setSolzvers_rvAg(double solzvers_rvAg) {
        this.solzvers_rvAg = solzvers_rvAg;
    }

    public double getSolzvers_avAg() {
        return solzvers_avAg;
    }

    public void setSolzvers_avAg(double solzvers_avAg) {
        this.solzvers_avAg = solzvers_avAg;
    }

    public double getSolzvers_kvAg() {
        return solzvers_kvAg;
    }

    public void setSolzvers_kvAg(double solzvers_kvAg) {
        this.solzvers_kvAg = solzvers_kvAg;
    }

    public double getSolzvers_pvAg() {
        return solzvers_pvAg;
    }

    public void setSolzvers_pvAg(double solzvers_pvAg) {
        this.solzvers_pvAg = solzvers_pvAg;
    }

    public double getSozgleit_sozVEntgelt() {
        return sozgleit_sozVEntgelt;
    }

    public void setSozgleit_sozVEntgelt(double sozgleit_sozVEntgelt) {
        this.sozgleit_sozVEntgelt = sozgleit_sozVEntgelt;
    }

    public double getSozgleit_rvAn() {
        return sozgleit_rvAn;
    }

    public void setSozgleit_rvAn(double sozgleit_rvAn) {
        this.sozgleit_rvAn = sozgleit_rvAn;
    }

    public double getSozgleit_avAn() {
        return sozgleit_avAn;
    }

    public void setSozgleit_avAn(double sozgleit_avAn) {
        this.sozgleit_avAn = sozgleit_avAn;
    }

    public double getSozgleit_kvAn() {
        return sozgleit_kvAn;
    }

    public void setSozgleit_kvAn(double sozgleit_kvAn) {
        this.sozgleit_kvAn = sozgleit_kvAn;
    }

    public double getSozgleit_kvZusatz() {
        return sozgleit_kvZusatz;
    }

    public void setSozgleit_kvZusatz(double sozgleit_kvZusatz) {
        this.sozgleit_kvZusatz = sozgleit_kvZusatz;
    }

    public double getSozgleit_pvAn() {
        return sozgleit_pvAn;
    }

    public void setSozgleit_pvAn(double sozgleit_pvAn) {
        this.sozgleit_pvAn = sozgleit_pvAn;
    }

    public double getGleitzone_sozVEntgelt() {
        return gleitzone_sozVEntgelt;
    }

    public void setGleitzone_sozVEntgelt(double gleitzone_sozVEntgelt) {
        this.gleitzone_sozVEntgelt = gleitzone_sozVEntgelt;
    }

    public double getGleitzone_rvAn() {
        return gleitzone_rvAn;
    }

    public void setGleitzone_rvAn(double gleitzone_rvAn) {
        this.gleitzone_rvAn = gleitzone_rvAn;
    }

    public double getGleitzone_avAn() {
        return gleitzone_avAn;
    }

    public void setGleitzone_avAn(double gleitzone_avAn) {
        this.gleitzone_avAn = gleitzone_avAn;
    }

    public double getGleitzone_kvAn() {
        return gleitzone_kvAn;
    }

    public void setGleitzone_kvAn(double gleitzone_kvAn) {
        this.gleitzone_kvAn = gleitzone_kvAn;
    }

    public double getGleitzone_kvZusatz() {
        return gleitzone_kvZusatz;
    }

    public void setGleitzone_kvZusatz(double gleitzone_kvZusatz) {
        this.gleitzone_kvZusatz = gleitzone_kvZusatz;
    }

    public double getGleitzone_pvAn() {
        return gleitzone_pvAn;
    }

    public void setGleitzone_pvAn(double gleitzone_pvAn) {
        this.gleitzone_pvAn = gleitzone_pvAn;
    }

    public double getUebertragw_rvAn() {
        return uebertragw_rvAn;
    }

    public void setUebertragw_rvAn(double uebertragw_rvAn) {
        this.uebertragw_rvAn = uebertragw_rvAn;
    }

    public double getUebertragw_avAn() {
        return uebertragw_avAn;
    }

    public void setUebertragw_avAn(double uebertragw_avAn) {
        this.uebertragw_avAn = uebertragw_avAn;
    }

    public double getUebertragw_kvAn() {
        return uebertragw_kvAn;
    }

    public void setUebertragw_kvAn(double uebertragw_kvAn) {
        this.uebertragw_kvAn = uebertragw_kvAn;
    }

    public double getUebertragw_kvZusatz() {
        return uebertragw_kvZusatz;
    }

    public void setUebertragw_kvZusatz(double uebertragw_kvZusatz) {
        this.uebertragw_kvZusatz = uebertragw_kvZusatz;
    }

    public double getUebertragw_pvAn() {
        return uebertragw_pvAn;
    }

    public void setUebertragw_pvAn(double uebertragw_pvAn) {
        this.uebertragw_pvAn = uebertragw_pvAn;
    }

    public double getJahreslohn_alter1() {
        return jahreslohn_alter1;
    }

    public void setJahreslohn_alter1(double jahreslohn_alter1) {
        this.jahreslohn_alter1 = jahreslohn_alter1;
    }

    public double getJahreslohn_zkf() {
        return jahreslohn_zkf;
    }

    public void setJahreslohn_zkf(double jahreslohn_zkf) {
        this.jahreslohn_zkf = jahreslohn_zkf;
    }

    public double getJahreslohn_llz() {
        return jahreslohn_llz;
    }

    public void setJahreslohn_llz(double jahreslohn_llz) {
        this.jahreslohn_llz = jahreslohn_llz;
    }

    public double getJahreslohn_krv() {
        return jahreslohn_krv;
    }

    public void setJahreslohn_krv(double jahreslohn_krv) {
        this.jahreslohn_krv = jahreslohn_krv;
    }

    public double getJahreslohn_zre4j() {
        return jahreslohn_zre4j;
    }

    public void setJahreslohn_zre4j(double jahreslohn_zre4j) {
        this.jahreslohn_zre4j = jahreslohn_zre4j;
    }

    public double getJahreslohn_stkl() {
        return jahreslohn_stkl;
    }

    public void setJahreslohn_stkl(double jahreslohn_stkl) {
        this.jahreslohn_stkl = jahreslohn_stkl;
    }

    public double getJahreslohn_jlfreib() {
        return jahreslohn_jlfreib;
    }

    public void setJahreslohn_jlfreib(double jahreslohn_jlfreib) {
        this.jahreslohn_jlfreib = jahreslohn_jlfreib;
    }

    public double getJahreslohn_jlhinzu() {
        return jahreslohn_jlhinzu;
    }

    public void setJahreslohn_jlhinzu(double jahreslohn_jlhinzu) {
        this.jahreslohn_jlhinzu = jahreslohn_jlhinzu;
    }

    public double getJahreslohn_rvBemes() {
        return jahreslohn_rvBemes;
    }

    public void setJahreslohn_rvBemes(double jahreslohn_rvBemes) {
        this.jahreslohn_rvBemes = jahreslohn_rvBemes;
    }

    public double getJahreslohn_pkv() {
        return jahreslohn_pkv;
    }

    public void setJahreslohn_pkv(double jahreslohn_pkv) {
        this.jahreslohn_pkv = jahreslohn_pkv;
    }

    public double getJahreslohn_pv() {
        return jahreslohn_pv;
    }

    public void setJahreslohn_pv(double jahreslohn_pv) {
        this.jahreslohn_pv = jahreslohn_pv;
    }

    public double getJahreslohn_faktorF() {
        return jahreslohn_faktorF;
    }

    public void setJahreslohn_faktorF(double jahreslohn_faktorF) {
        this.jahreslohn_faktorF = jahreslohn_faktorF;
    }

    public double getMre4alte1_tab4() {
        return mre4alte1_tab4;
    }

    public void setMre4alte1_tab4(double mre4alte1_tab4) {
        this.mre4alte1_tab4 = mre4alte1_tab4;
    }

    public double getMre4alte1_tab5() {
        return mre4alte1_tab5;
    }

    public void setMre4alte1_tab5(double mre4alte1_tab5) {
        this.mre4alte1_tab5 = mre4alte1_tab5;
    }

    public double getMre4alte1_alteanteil() {
        return mre4alte1_alteanteil;
    }

    public void setMre4alte1_alteanteil(double mre4alte1_alteanteil) {
        this.mre4alte1_alteanteil = mre4alte1_alteanteil;
    }

    public double getMre4alte1_alte() {
        return mre4alte1_alte;
    }

    public void setMre4alte1_alte(double mre4alte1_alte) {
        this.mre4alte1_alte = mre4alte1_alte;
    }

    public double getMre4alte1_zre4() {
        return mre4alte1_zre4;
    }

    public void setMre4alte1_zre4(double mre4alte1_zre4) {
        this.mre4alte1_zre4 = mre4alte1_zre4;
    }

    public double getMre4alte1_zre4vp() {
        return mre4alte1_zre4vp;
    }

    public void setMre4alte1_zre4vp(double mre4alte1_zre4vp) {
        this.mre4alte1_zre4vp = mre4alte1_zre4vp;
    }

    public double getMre41_zre4() {
        return mre41_zre4;
    }

    public void setMre41_zre4(double mre41_zre4) {
        this.mre41_zre4 = mre41_zre4;
    }

    public double getMre41_zre4vp() {
        return mre41_zre4vp;
    }

    public void setMre41_zre4vp(double mre41_zre4vp) {
        this.mre41_zre4vp = mre41_zre4vp;
    }

    public double getMztabfb1_kztab() {
        return mztabfb1_kztab;
    }

    public void setMztabfb1_kztab(double mztabfb1_kztab) {
        this.mztabfb1_kztab = mztabfb1_kztab;
    }

    public double getMztabfb1_anp() {
        return mztabfb1_anp;
    }

    public void setMztabfb1_anp(double mztabfb1_anp) {
        this.mztabfb1_anp = mztabfb1_anp;
    }

    public double getMztabfb1_efa() {
        return mztabfb1_efa;
    }

    public void setMztabfb1_efa(double mztabfb1_efa) {
        this.mztabfb1_efa = mztabfb1_efa;
    }

    public double getMztabfb1_sap() {
        return mztabfb1_sap;
    }

    public void setMztabfb1_sap(double mztabfb1_sap) {
        this.mztabfb1_sap = mztabfb1_sap;
    }

    public double getMztabfb1_kfb() {
        return mztabfb1_kfb;
    }

    public void setMztabfb1_kfb(double mztabfb1_kfb) {
        this.mztabfb1_kfb = mztabfb1_kfb;
    }

    public double getMztabfb1_ztabfb() {
        return mztabfb1_ztabfb;
    }

    public void setMztabfb1_ztabfb(double mztabfb1_ztabfb) {
        this.mztabfb1_ztabfb = mztabfb1_ztabfb;
    }

    public double getUpevp1_zre4vp() {
        return upevp1_zre4vp;
    }

    public void setUpevp1_zre4vp(double upevp1_zre4vp) {
        this.upevp1_zre4vp = upevp1_zre4vp;
    }

    public double getUpevp1_vsp1() {
        return upevp1_vsp1;
    }

    public void setUpevp1_vsp1(double upevp1_vsp1) {
        this.upevp1_vsp1 = upevp1_vsp1;
    }

    public double getUpevp1_vhb() {
        return upevp1_vhb;
    }

    public void setUpevp1_vhb(double upevp1_vhb) {
        this.upevp1_vhb = upevp1_vhb;
    }

    public double getUpevp1_vsp2() {
        return upevp1_vsp2;
    }

    public void setUpevp1_vsp2(double upevp1_vsp2) {
        this.upevp1_vsp2 = upevp1_vsp2;
    }

    public double getUpevp1_zukvPv() {
        return upevp1_zukvPv;
    }

    public void setUpevp1_zukvPv(double upevp1_zukvPv) {
        this.upevp1_zukvPv = upevp1_zukvPv;
    }

    public double getUpevp1_kv() {
        return upevp1_kv;
    }

    public void setUpevp1_kv(double upevp1_kv) {
        this.upevp1_kv = upevp1_kv;
    }

    public double getUpevp1_kvVhb() {
        return upevp1_kvVhb;
    }

    public void setUpevp1_kvVhb(double upevp1_kvVhb) {
        this.upevp1_kvVhb = upevp1_kvVhb;
    }

    public double getUpevp1_vspn() {
        return upevp1_vspn;
    }

    public void setUpevp1_vspn(double upevp1_vspn) {
        this.upevp1_vspn = upevp1_vspn;
    }

    public double getUmvsp1_zve() {
        return umvsp1_zve;
    }

    public void setUmvsp1_zve(double umvsp1_zve) {
        this.umvsp1_zve = umvsp1_zve;
    }

    public double getUmvsp1_zzx() {
        return umvsp1_zzx;
    }

    public void setUmvsp1_zzx(double umvsp1_zzx) {
        this.umvsp1_zzx = umvsp1_zzx;
    }

    public double getUptab071_st() {
        return uptab071_st;
    }

    public void setUptab071_st(double uptab071_st) {
        this.uptab071_st = uptab071_st;
    }

    public double getMst561_x() {
        return mst561_x;
    }

    public void setMst561_x(double mst561_x) {
        this.mst561_x = mst561_x;
    }

    public double getMst561_st1() {
        return mst561_st1;
    }

    public void setMst561_st1(double mst561_st1) {
        this.mst561_st1 = mst561_st1;
    }

    public double getMst561_x1() {
        return mst561_x1;
    }

    public void setMst561_x1(double mst561_x1) {
        this.mst561_x1 = mst561_x1;
    }

    public double getMst561_st2() {
        return mst561_st2;
    }

    public void setMst561_st2(double mst561_st2) {
        this.mst561_st2 = mst561_st2;
    }

    public double getMst561_diff() {
        return mst561_diff;
    }

    public void setMst561_diff(double mst561_diff) {
        this.mst561_diff = mst561_diff;
    }

    public double getMst561_mist() {
        return mst561_mist;
    }

    public void setMst561_mist(double mst561_mist) {
        this.mst561_mist = mst561_mist;
    }

    public double getMst561_st() {
        return mst561_st;
    }

    public void setMst561_st(double mst561_st) {
        this.mst561_st = mst561_st;
    }

    public double getMst561_st3() {
        return mst561_st3;
    }

    public void setMst561_st3(double mst561_st3) {
        this.mst561_st3 = mst561_st3;
    }

    public double getMst561_vergl() {
        return mst561_vergl;
    }

    public void setMst561_vergl(double mst561_vergl) {
        this.mst561_vergl = mst561_vergl;
    }

    public double getMst561_st4() {
        return mst561_st4;
    }

    public void setMst561_st4(double mst561_st4) {
        this.mst561_st4 = mst561_st4;
    }

    public double getMst561_st5() {
        return mst561_st5;
    }

    public void setMst561_st5(double mst561_st5) {
        this.mst561_st5 = mst561_st5;
    }

    public double getMst561_reichst() {
        return mst561_reichst;
    }

    public void setMst561_reichst(double mst561_reichst) {
        this.mst561_reichst = mst561_reichst;
    }

    public double getMst561_lstjahr() {
        return mst561_lstjahr;
    }

    public void setMst561_lstjahr(double mst561_lstjahr) {
        this.mst561_lstjahr = mst561_lstjahr;
    }

    public double getMst561_jw() {
        return mst561_jw;
    }

    public void setMst561_jw(double mst561_jw) {
        this.mst561_jw = mst561_jw;
    }

    public double getMlstjahr1_lstlzzSum() {
        return mlstjahr1_lstlzzSum;
    }

    public void setMlstjahr1_lstlzzSum(double mlstjahr1_lstlzzSum) {
        this.mlstjahr1_lstlzzSum = mlstjahr1_lstlzzSum;
    }

    public double getMlstjahr1_ztabfb() {
        return mlstjahr1_ztabfb;
    }

    public void setMlstjahr1_ztabfb(double mlstjahr1_ztabfb) {
        this.mlstjahr1_ztabfb = mlstjahr1_ztabfb;
    }

    public double getMlstjahr1_zve() {
        return mlstjahr1_zve;
    }

    public void setMlstjahr1_zve(double mlstjahr1_zve) {
        this.mlstjahr1_zve = mlstjahr1_zve;
    }

    public double getMlstjahr1_zveX() {
        return mlstjahr1_zveX;
    }

    public void setMlstjahr1_zveX(double mlstjahr1_zveX) {
        this.mlstjahr1_zveX = mlstjahr1_zveX;
    }

    public double getMlstjahr1_st() {
        return mlstjahr1_st;
    }

    public void setMlstjahr1_st(double mlstjahr1_st) {
        this.mlstjahr1_st = mlstjahr1_st;
    }

    public double getMlstjahr1_jbmg() {
        return mlstjahr1_jbmg;
    }

    public void setMlstjahr1_jbmg(double mlstjahr1_jbmg) {
        this.mlstjahr1_jbmg = mlstjahr1_jbmg;
    }

    public double getMsolz1_solzfrei() {
        return msolz1_solzfrei;
    }

    public void setMsolz1_solzfrei(double msolz1_solzfrei) {
        this.msolz1_solzfrei = msolz1_solzfrei;
    }

    public double getMsolz1_solzj() {
        return msolz1_solzj;
    }

    public void setMsolz1_solzj(double msolz1_solzj) {
        this.msolz1_solzj = msolz1_solzj;
    }

    public double getMsolz1_solzmin() {
        return msolz1_solzmin;
    }

    public void setMsolz1_solzmin(double msolz1_solzmin) {
        this.msolz1_solzmin = msolz1_solzmin;
    }

    public double getBk1_anteil1() {
        return bk1_anteil1;
    }

    public void setBk1_anteil1(double bk1_anteil1) {
        this.bk1_anteil1 = bk1_anteil1;
    }

    public double getBk1_bk() {
        return bk1_bk;
    }

    public void setBk1_bk(double bk1_bk) {
        this.bk1_bk = bk1_bk;
    }

    public double getBk1_bkeinmal() {
        return bk1_bkeinmal;
    }

    public void setBk1_bkeinmal(double bk1_bkeinmal) {
        this.bk1_bkeinmal = bk1_bkeinmal;
    }

    public double getBk1_kist() {
        return bk1_kist;
    }

    public void setBk1_kist(double bk1_kist) {
        this.bk1_kist = bk1_kist;
    }

    public double getJahreslohn1_alter1() {
        return jahreslohn1_alter1;
    }

    public void setJahreslohn1_alter1(double jahreslohn1_alter1) {
        this.jahreslohn1_alter1 = jahreslohn1_alter1;
    }

    public double getJahreslohn1_zkf() {
        return jahreslohn1_zkf;
    }

    public void setJahreslohn1_zkf(double jahreslohn1_zkf) {
        this.jahreslohn1_zkf = jahreslohn1_zkf;
    }

    public double getJahreslohn1_llz() {
        return jahreslohn1_llz;
    }

    public void setJahreslohn1_llz(double jahreslohn1_llz) {
        this.jahreslohn1_llz = jahreslohn1_llz;
    }

    public double getJahreslohn1_krv() {
        return jahreslohn1_krv;
    }

    public void setJahreslohn1_krv(double jahreslohn1_krv) {
        this.jahreslohn1_krv = jahreslohn1_krv;
    }

    public double getJahreslohn1_zre4j() {
        return jahreslohn1_zre4j;
    }

    public void setJahreslohn1_zre4j(double jahreslohn1_zre4j) {
        this.jahreslohn1_zre4j = jahreslohn1_zre4j;
    }

    public double getJahreslohn1_stkl() {
        return jahreslohn1_stkl;
    }

    public void setJahreslohn1_stkl(double jahreslohn1_stkl) {
        this.jahreslohn1_stkl = jahreslohn1_stkl;
    }

    public double getJahreslohn1_jlfreib() {
        return jahreslohn1_jlfreib;
    }

    public void setJahreslohn1_jlfreib(double jahreslohn1_jlfreib) {
        this.jahreslohn1_jlfreib = jahreslohn1_jlfreib;
    }

    public double getJahreslohn1_jlhinzu() {
        return jahreslohn1_jlhinzu;
    }

    public void setJahreslohn1_jlhinzu(double jahreslohn1_jlhinzu) {
        this.jahreslohn1_jlhinzu = jahreslohn1_jlhinzu;
    }

    public double getJahreslohn1_rvBemes() {
        return jahreslohn1_rvBemes;
    }

    public void setJahreslohn1_rvBemes(double jahreslohn1_rvBemes) {
        this.jahreslohn1_rvBemes = jahreslohn1_rvBemes;
    }

    public double getJahreslohn1_pkv() {
        return jahreslohn1_pkv;
    }

    public void setJahreslohn1_pkv(double jahreslohn1_pkv) {
        this.jahreslohn1_pkv = jahreslohn1_pkv;
    }

    public double getJahreslohn1_pv() {
        return jahreslohn1_pv;
    }

    public void setJahreslohn1_pv(double jahreslohn1_pv) {
        this.jahreslohn1_pv = jahreslohn1_pv;
    }

    public double getJahreslohn1_faktorF() {
        return jahreslohn1_faktorF;
    }

    public void setJahreslohn1_faktorF(double jahreslohn1_faktorF) {
        this.jahreslohn1_faktorF = jahreslohn1_faktorF;
    }

    public double getMre4alte2_tab4() {
        return mre4alte2_tab4;
    }

    public void setMre4alte2_tab4(double mre4alte2_tab4) {
        this.mre4alte2_tab4 = mre4alte2_tab4;
    }

    public double getMre4alte2_tab5() {
        return mre4alte2_tab5;
    }

    public void setMre4alte2_tab5(double mre4alte2_tab5) {
        this.mre4alte2_tab5 = mre4alte2_tab5;
    }

    public double getMre4alte2_alteanteil() {
        return mre4alte2_alteanteil;
    }

    public void setMre4alte2_alteanteil(double mre4alte2_alteanteil) {
        this.mre4alte2_alteanteil = mre4alte2_alteanteil;
    }

    public double getMre4alte2_alte() {
        return mre4alte2_alte;
    }

    public void setMre4alte2_alte(double mre4alte2_alte) {
        this.mre4alte2_alte = mre4alte2_alte;
    }

    public double getMre4alte2_zre4() {
        return mre4alte2_zre4;
    }

    public void setMre4alte2_zre4(double mre4alte2_zre4) {
        this.mre4alte2_zre4 = mre4alte2_zre4;
    }

    public double getMre4alte2_zre4vp() {
        return mre4alte2_zre4vp;
    }

    public void setMre4alte2_zre4vp(double mre4alte2_zre4vp) {
        this.mre4alte2_zre4vp = mre4alte2_zre4vp;
    }

    public double getMre42_zre4() {
        return mre42_zre4;
    }

    public void setMre42_zre4(double mre42_zre4) {
        this.mre42_zre4 = mre42_zre4;
    }

    public double getMre42_zre4vp() {
        return mre42_zre4vp;
    }

    public void setMre42_zre4vp(double mre42_zre4vp) {
        this.mre42_zre4vp = mre42_zre4vp;
    }

    public double getMztabfb2_kztab() {
        return mztabfb2_kztab;
    }

    public void setMztabfb2_kztab(double mztabfb2_kztab) {
        this.mztabfb2_kztab = mztabfb2_kztab;
    }

    public double getMztabfb2_anp() {
        return mztabfb2_anp;
    }

    public void setMztabfb2_anp(double mztabfb2_anp) {
        this.mztabfb2_anp = mztabfb2_anp;
    }

    public double getMztabfb2_efa() {
        return mztabfb2_efa;
    }

    public void setMztabfb2_efa(double mztabfb2_efa) {
        this.mztabfb2_efa = mztabfb2_efa;
    }

    public double getMztabfb2_sap() {
        return mztabfb2_sap;
    }

    public void setMztabfb2_sap(double mztabfb2_sap) {
        this.mztabfb2_sap = mztabfb2_sap;
    }

    public double getMztabfb2_kfb() {
        return mztabfb2_kfb;
    }

    public void setMztabfb2_kfb(double mztabfb2_kfb) {
        this.mztabfb2_kfb = mztabfb2_kfb;
    }

    public double getMztabfb2_ztabfb() {
        return mztabfb2_ztabfb;
    }

    public void setMztabfb2_ztabfb(double mztabfb2_ztabfb) {
        this.mztabfb2_ztabfb = mztabfb2_ztabfb;
    }

    public double getUpevp2_zre4vp() {
        return upevp2_zre4vp;
    }

    public void setUpevp2_zre4vp(double upevp2_zre4vp) {
        this.upevp2_zre4vp = upevp2_zre4vp;
    }

    public double getUpevp2_vsp1() {
        return upevp2_vsp1;
    }

    public void setUpevp2_vsp1(double upevp2_vsp1) {
        this.upevp2_vsp1 = upevp2_vsp1;
    }

    public double getUpevp2_vhb() {
        return upevp2_vhb;
    }

    public void setUpevp2_vhb(double upevp2_vhb) {
        this.upevp2_vhb = upevp2_vhb;
    }

    public double getUpevp2_vsp2() {
        return upevp2_vsp2;
    }

    public void setUpevp2_vsp2(double upevp2_vsp2) {
        this.upevp2_vsp2 = upevp2_vsp2;
    }

    public double getUpevp2_zukvPv() {
        return upevp2_zukvPv;
    }

    public void setUpevp2_zukvPv(double upevp2_zukvPv) {
        this.upevp2_zukvPv = upevp2_zukvPv;
    }

    public double getUpevp2_kv() {
        return upevp2_kv;
    }

    public void setUpevp2_kv(double upevp2_kv) {
        this.upevp2_kv = upevp2_kv;
    }

    public double getUpevp2_kvVhb() {
        return upevp2_kvVhb;
    }

    public void setUpevp2_kvVhb(double upevp2_kvVhb) {
        this.upevp2_kvVhb = upevp2_kvVhb;
    }

    public double getUpevp2_vspn() {
        return upevp2_vspn;
    }

    public void setUpevp2_vspn(double upevp2_vspn) {
        this.upevp2_vspn = upevp2_vspn;
    }

    public double getUmvsp2_zve() {
        return umvsp2_zve;
    }

    public void setUmvsp2_zve(double umvsp2_zve) {
        this.umvsp2_zve = umvsp2_zve;
    }

    public double getUmvsp2_zzx() {
        return umvsp2_zzx;
    }

    public void setUmvsp2_zzx(double umvsp2_zzx) {
        this.umvsp2_zzx = umvsp2_zzx;
    }

    public double getUptab072_st() {
        return uptab072_st;
    }

    public void setUptab072_st(double uptab072_st) {
        this.uptab072_st = uptab072_st;
    }

    public double getMst562_x() {
        return mst562_x;
    }

    public void setMst562_x(double mst562_x) {
        this.mst562_x = mst562_x;
    }

    public double getMst562_st1() {
        return mst562_st1;
    }

    public void setMst562_st1(double mst562_st1) {
        this.mst562_st1 = mst562_st1;
    }

    public double getMst562_x1() {
        return mst562_x1;
    }

    public void setMst562_x1(double mst562_x1) {
        this.mst562_x1 = mst562_x1;
    }

    public double getMst562_st2() {
        return mst562_st2;
    }

    public void setMst562_st2(double mst562_st2) {
        this.mst562_st2 = mst562_st2;
    }

    public double getMst562_diff() {
        return mst562_diff;
    }

    public void setMst562_diff(double mst562_diff) {
        this.mst562_diff = mst562_diff;
    }

    public double getMst562_mist() {
        return mst562_mist;
    }

    public void setMst562_mist(double mst562_mist) {
        this.mst562_mist = mst562_mist;
    }

    public double getMst562_st() {
        return mst562_st;
    }

    public void setMst562_st(double mst562_st) {
        this.mst562_st = mst562_st;
    }

    public double getMst562_st3() {
        return mst562_st3;
    }

    public void setMst562_st3(double mst562_st3) {
        this.mst562_st3 = mst562_st3;
    }

    public double getMst562_vergl() {
        return mst562_vergl;
    }

    public void setMst562_vergl(double mst562_vergl) {
        this.mst562_vergl = mst562_vergl;
    }

    public double getMst562_st4() {
        return mst562_st4;
    }

    public void setMst562_st4(double mst562_st4) {
        this.mst562_st4 = mst562_st4;
    }

    public double getMst562_st5() {
        return mst562_st5;
    }

    public void setMst562_st5(double mst562_st5) {
        this.mst562_st5 = mst562_st5;
    }

    public double getMst562_reichst() {
        return mst562_reichst;
    }

    public void setMst562_reichst(double mst562_reichst) {
        this.mst562_reichst = mst562_reichst;
    }

    public double getMst562_lstjahr() {
        return mst562_lstjahr;
    }

    public void setMst562_lstjahr(double mst562_lstjahr) {
        this.mst562_lstjahr = mst562_lstjahr;
    }

    public double getMst562_jw() {
        return mst562_jw;
    }

    public void setMst562_jw(double mst562_jw) {
        this.mst562_jw = mst562_jw;
    }

    public double getMlstjahr2_lstlzzSum() {
        return mlstjahr2_lstlzzSum;
    }

    public void setMlstjahr2_lstlzzSum(double mlstjahr2_lstlzzSum) {
        this.mlstjahr2_lstlzzSum = mlstjahr2_lstlzzSum;
    }

    public double getMlstjahr2_ztabfb() {
        return mlstjahr2_ztabfb;
    }

    public void setMlstjahr2_ztabfb(double mlstjahr2_ztabfb) {
        this.mlstjahr2_ztabfb = mlstjahr2_ztabfb;
    }

    public double getMlstjahr2_zve() {
        return mlstjahr2_zve;
    }

    public void setMlstjahr2_zve(double mlstjahr2_zve) {
        this.mlstjahr2_zve = mlstjahr2_zve;
    }

    public double getMlstjahr2_zveX() {
        return mlstjahr2_zveX;
    }

    public void setMlstjahr2_zveX(double mlstjahr2_zveX) {
        this.mlstjahr2_zveX = mlstjahr2_zveX;
    }

    public double getMlstjahr2_st() {
        return mlstjahr2_st;
    }

    public void setMlstjahr2_st(double mlstjahr2_st) {
        this.mlstjahr2_st = mlstjahr2_st;
    }

    public double getMlstjahr2_jbmg() {
        return mlstjahr2_jbmg;
    }

    public void setMlstjahr2_jbmg(double mlstjahr2_jbmg) {
        this.mlstjahr2_jbmg = mlstjahr2_jbmg;
    }

    public double getMsolz2_solzfrei() {
        return msolz2_solzfrei;
    }

    public void setMsolz2_solzfrei(double msolz2_solzfrei) {
        this.msolz2_solzfrei = msolz2_solzfrei;
    }

    public double getMsolz2_solzj() {
        return msolz2_solzj;
    }

    public void setMsolz2_solzj(double msolz2_solzj) {
        this.msolz2_solzj = msolz2_solzj;
    }

    public double getBk2_anteil1() {
        return bk2_anteil1;
    }

    public void setBk2_anteil1(double bk2_anteil1) {
        this.bk2_anteil1 = bk2_anteil1;
    }

    public double getBk2_bk() {
        return bk2_bk;
    }

    public void setBk2_bk(double bk2_bk) {
        this.bk2_bk = bk2_bk;
    }

    public double getSolzAnteilBaV_bavBeitrag() {
        return solzAnteilBaV_bavBeitrag;
    }

    public void setSolzAnteilBaV_bavBeitrag(double solzAnteilBaV_bavBeitrag) {
        this.solzAnteilBaV_bavBeitrag = solzAnteilBaV_bavBeitrag;
    }

    public double getSolzAnteilBaV_sozVPflicht() {
        return solzAnteilBaV_sozVPflicht;
    }

    public void setSolzAnteilBaV_sozVPflicht(double solzAnteilBaV_sozVPflicht) {
        this.solzAnteilBaV_sozVPflicht = solzAnteilBaV_sozVPflicht;
    }

    public double getSolzAnteilBaV_sozVpflichtBrut() {
        return solzAnteilBaV_sozVpflichtBrut;
    }

    public void setSolzAnteilBaV_sozVpflichtBrut(double solzAnteilBaV_sozVpflichtBrut) {
        this.solzAnteilBaV_sozVpflichtBrut = solzAnteilBaV_sozVpflichtBrut;
    }

    public double getRvPflichtigerBeitrag() {
        return rvPflichtigerBeitrag;
    }

    public void setRvPflichtigerBeitrag(double rvPflichtigerBeitrag) {
        this.rvPflichtigerBeitrag = rvPflichtigerBeitrag;
    }

    public double getKvPflichtigerBeitrag() {
        return kvPflichtigerBeitrag;
    }

    public void setKvPflichtigerBeitrag(double kvPflichtigerBeitrag) {
        this.kvPflichtigerBeitrag = kvPflichtigerBeitrag;
    }

    public double getU1() {
        return u1;
    }

    public void setU1(double u1) {
        this.u1 = u1;
    }

    public double getU2() {
        return u2;
    }

    public void setU2(double u2) {
        this.u2 = u2;
    }

    public double getInsolvenz() {
        return insolvenz;
    }

    public void setInsolvenz(double insolvenz) {
        this.insolvenz = insolvenz;
    }

    public double getMini_u1() {
        return mini_u1;
    }

    public void setMini_u1(double mini_u1) {
        this.mini_u1 = mini_u1;
    }

    public double getMini_u2() {
        return mini_u2;
    }

    public void setMini_u2(double mini_u2) {
        this.mini_u2 = mini_u2;
    }

    public double getMini_insolvenz() {
        return mini_insolvenz;
    }

    public void setMini_insolvenz(double mini_insolvenz) {
        this.mini_insolvenz = mini_insolvenz;
    }

    public double getUebWerte_u1() {
        return uebWerte_u1;
    }

    public void setUebWerte_u1(double uebWerte_u1) {
        this.uebWerte_u1 = uebWerte_u1;
    }

    public double getUebWerte_u2() {
        return uebWerte_u2;
    }

    public void setUebWerte_u2(double uebWerte_u2) {
        this.uebWerte_u2 = uebWerte_u2;
    }

    public double getUebWerte_insolvenz() {
        return uebWerte_insolvenz;
    }

    public void setUebWerte_insolvenz(double uebWerte_insolvenz) {
        this.uebWerte_insolvenz = uebWerte_insolvenz;
    }

    public double getMinijob_u1() {
        return minijob_u1;
    }

    public void setMinijob_u1(double minijob_u1) {
        this.minijob_u1 = minijob_u1;
    }

    public double getMinijob_u2() {
        return minijob_u2;
    }

    public void setMinijob_u2(double minijob_u2) {
        this.minijob_u2 = minijob_u2;
    }

    public double getMinijobpausch_miniJob() {
        return minijobpausch_miniJob;
    }

    public void setMinijobpausch_miniJob(double minijobpausch_miniJob) {
        this.minijobpausch_miniJob = minijobpausch_miniJob;
    }

    public double getMinijobpausch_kv() {
        return minijobpausch_kv;
    }

    public void setMinijobpausch_kv(double minijobpausch_kv) {
        this.minijobpausch_kv = minijobpausch_kv;
    }

    public double getMinijobpausch_lst() {
        return minijobpausch_lst;
    }

    public void setMinijobpausch_lst(double minijobpausch_lst) {
        this.minijobpausch_lst = minijobpausch_lst;
    }

    public double getMinijobpausch_berueck() {
        return minijobpausch_berueck;
    }

    public void setMinijobpausch_berueck(double minijobpausch_berueck) {
        this.minijobpausch_berueck = minijobpausch_berueck;
    }

    public double getPflege_pflegeversicherung() {
        return pflege_pflegeversicherung;
    }

    public void setPflege_pflegeversicherung(double pflege_pflegeversicherung) {
        this.pflege_pflegeversicherung = pflege_pflegeversicherung;
    }

    public double getPflege_pflegeSachsen() {
        return pflege_pflegeSachsen;
    }

    public void setPflege_pflegeSachsen(double pflege_pflegeSachsen) {
        this.pflege_pflegeSachsen = pflege_pflegeSachsen;
    }

    public double getPflege_pflegeArbeitgeber() {
        return pflege_pflegeArbeitgeber;
    }

    public void setPflege_pflegeArbeitgeber(double pflege_pflegeArbeitgeber) {
        this.pflege_pflegeArbeitgeber = pflege_pflegeArbeitgeber;
    }
   
    public void calcLohnst_alter1(){
        try {
            String inputDateOld = "1940-12-31";
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            Date dateOld = dateFormat.parse(inputDateOld);
            
            String inputDateMittleOld = "1952-12-31";
            DateFormat dateFormatMittle = new SimpleDateFormat("yyyy-mm-dd");
            Date dateMittleOld = dateFormat.parse(inputDateMittleOld);
            
            if(employee.getGeburtsdatum().before(dateOld)){
                lohnst_alter1 = 1;
            }else if(employee.getGeburtsdatum().before(dateMittleOld)){
                lohnst_alter1 = -1;
            }else{
                int diffYears = (getYearFromDate(employee.getGeburtsdatum())-1940) +1;
                lohnst_alter1 = diffYears;
            }
        } catch (ParseException ex) {
            Logger.getLogger(Gehaltsabrechnungsrechner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fillLohnst_zkf(){
        if(baseData.getSteuerklasse() >4){
            lohnst_zkf = 0;
        }else{
            lohnst_zkf = baseData.getSteuerklasse();
        }
    }
    
    public void fillLohnst_lzz(){
        lohnst_lzz = 2;
    }
    
    public void fillLohnst_krv(){
        calcLohnst_alter1();
        if(lohnst_alter1 == 1){
            lohnst_krv =1;
        }else if(baseData.getRentenversichert() == 1){
            lohnst_krv = 0; 
        }else{
            lohnst_krv = 1;
        }
    }
    
    public void fillLohnst_zre4j(){
        clacOneTimePayment();
        lohnst_zre4j =( calcBruttoTaxes()*100-einmalzahlung)*12;
    }
    
    public void fillLohnst_stkl(){
        if(miniJobFlatrate == 0 && baseData.getSteuerklasse() == 6){
            lohnst_stkl = 0;
        } else{
            lohnst_stkl = baseData.getSteuerklasse();
        }
    }
    
    public void fillLohnst_jlfreib(){
        lohnst_jlfreib = baseData.getLohnsteuerfreibetrag()*100;
    }
    
    public void fillLohnst_jlhinzu(){
        fillLohnst_stkl();
        lohnst_jlhinzu = baseData.getHinzurechnungsbetrag()*100;
    }
    
    public void fillLohnst_rvbemes(){
        if(baseData.getStelleImOsten() == 0){
            lohnst_rvbemes = 74400;
        }else{
            lohnst_rvbemes = 64800;
        }
    }
    
    public void fillLohnst_pkv(){
        fillMztabfb_kztab();
        fillUpevp_vhb();
        
        if(baseData.getKrankenversicherung() > 20){
            if(upevp_vhb >(baseData.getKvzuschlag()*12)){
                if(baseData.getArbeitgeberzuschussKvPv()>0){
                    if(baseData.getStelleImOsten() == 1){
                        lohnst_pkv = upevp_vhb - (0.07675 * min(lohnst_zre4j/100,50850));
                    }else{
                        lohnst_pkv = upevp_vhb - (0.08175 * min(lohnst_zre4j/100,50850));
                    }
                }else{
                    lohnst_pkv = upevp_vhb -0;
                }
            }else{
                 if(baseData.getArbeitgeberzuschussKvPv()>0){
                    if(baseData.getStelleImOsten() == 1){
                        lohnst_pkv = (baseData.getKvzuschlag()*12) - (0.07675 * min(lohnst_zre4j/100,50850));
                    }else{
                        lohnst_pkv = (baseData.getKvzuschlag()*12)  - (0.08175 * min(lohnst_zre4j/100,50850));
                    }
                }else{
                    lohnst_pkv = (baseData.getKvzuschlag()*12)  -0;
                }
            }
        }else{
            lohnst_pkv = 0;
        }
    }
    
    public void fillLohnst_pv(){
        double wert1;
        double wert2;
        
        if(baseData.getStelleInSachsen() == 0){
            wert1 = 0.01175;
        }else{
            wert1 = 0.01675;
        }
        
        if(baseData.getKinderlos() == 1){
            wert2 = 0.0025;
        }else{
            wert2 = 0;
        }
        
        lohnst_pv = wert1 + wert2;
    }
    
    public void fillLohnst_faktorf(){
        if( (baseData.getEhegattenfaktor() == 0 || baseData.getEhegattenfaktor() > 1 ) || baseData.getSteuerklasse() != 4){
            lohnst_faktorF = 1;
        }else{
            lohnst_faktorF = baseData.getEhegattenfaktor();
        }
    }
    public void fillUpevp_vhb(){
        if(mztabfb_kztab == 1){
            upevp_vhb = 1900;
        }else{
            upevp_vhb = 3000;
        }
    }
    
    public void fillMre4Alte_tab5(){
        
        double wert1 = 0;
        
        if(lohnst_alter1 == 9){
             wert1 = 129200;
        }else if(lohnst_alter1 == 10){
             wert1 = 121600;
        }else if(lohnst_alter1 == 11){
             wert1 = 114000;
        }else if(lohnst_alter1 == 12){
             wert1 = 106400;
        }
        
        if(lohnst_alter1 == 1){
            mre4alte_tab5 = 190000 + wert1;
        }else if(lohnst_alter1 == 2){
             mre4alte_tab5 = 182400 + wert1;
        }else if(lohnst_alter1 == 3){
             mre4alte_tab5 = 174200 + wert1;
        }else if(lohnst_alter1 == 4){
             mre4alte_tab5 = 167200 + wert1;
        }else if(lohnst_alter1 == 5){
             mre4alte_tab5 = 159600 + wert1;
        }else if(lohnst_alter1 == 6){
             mre4alte_tab5 = 152000 + wert1;
        }else if(lohnst_alter1 == 7){
             mre4alte_tab5 = 144400 + wert1;
        }else if(lohnst_alter1 == 8){
             mre4alte_tab5 = 136800 + wert1;
        }
    }
    
    public void fillMre4alte_tab4(){
           double wert1 = 0;
        
        if(lohnst_alter1 == 9){
             wert1 = 0.272;
        }else if(lohnst_alter1 == 10){
             wert1 = 0.256;
        }else if(lohnst_alter1 == 11){
             wert1 = 0.24;
        }else if(lohnst_alter1 == 12){
             wert1 = 0.224;
        }
        
        if(lohnst_alter1 == 1){
            mre4alte_tab4 = 0.4 + wert1;
        }else if(lohnst_alter1 == 2){
             mre4alte_tab4 = 0.384 + wert1;
        }else if(lohnst_alter1 == 3){
             mre4alte_tab4 = 0.368 + wert1;
        }else if(lohnst_alter1 == 4){
             mre4alte_tab4 = 0.352 + wert1;
        }else if(lohnst_alter1 == 5){
             mre4alte_tab4 = 0.336 + wert1;
        }else if(lohnst_alter1 == 6){
             mre4alte_tab4 = 0.32 + wert1;
        }else if(lohnst_alter1 == 7){
             mre4alte_tab4 = 0.304 + wert1;
        }else if(lohnst_alter1 == 8){
             mre4alte_tab4 = 0.288 + wert1;
        }
    }
    
    public void fillMztabfb_kztab(){
        fillLohnst_stkl();
        if(lohnst_stkl == 3){
            mztabfb_kztab = 2;
        }else{
            mztabfb_kztab = 1;
        }
        
    }
    
    
    public void fillMre4alte_alteanteil(){
        fillMre4Alte_tab5();
        mre4alte_alteanteil = mre4alte_tab5;
    }
    
    public void fillMre4alte_alte(){
        fillLohnst_zre4j();
        fillMre4alte_alteanteil();
        if(lohnst_alter1 == 0){
            mre4alte_alte = 0;
        }else if((lohnst_zre4j*mre4alte_tab4)>mre4alte_alteanteil){
            mre4alte_alte = mre4alte_alteanteil;
        }else{
            mre4alte_alte = (lohnst_zre4j*mre4alte_tab4);
        }
    }
    
    public void fillMre4alte_zre4(){
        fillLohnst_zre4j();
        fillLohnst_jlfreib();
        fillLohnst_jlhinzu();
        fillMre4alte_alte();
        mre4alte_zre4 = lohnst_zre4j - (lohnst_jlfreib + lohnst_jlhinzu) - mre4alte_alte;
    }
    
    public void fillMre4alte_zre4vp(){
        fillLohnst_zre4j();
        mre4alte_zre4vp = lohnst_zre4j;
    }
    
    public void fillMre4_zre4(){
        fillMre4alte_zre4();
        mre4_zre4 = mre4alte_zre4/100;
    }
    
     public void fillMre4_zre4vp(){
        fillMre4alte_zre4vp();
        mre4_zre4vp = mre4alte_zre4vp/100;
    }
     
     public void fillMztabfb_efa(){
         fillLohnst_stkl();
         if(lohnst_stkl == 2){
             mztabfb_efa = 1908;
         }else{
             mztabfb_efa = 0;
         }
     }
     
     public void fillMztabfb_sap(){
         fillLohnst_stkl();
         if(lohnst_stkl > 5){
             mztabfb_sap = 0;
         }else{
             mztabfb_sap = 36;
         }
     }
     
     public void fillMztabfb_kfb(){
        fillLohnst_stkl();
        fillLohnst_zkf();
        
        if(lohnst_stkl<4){
            mztabfb_kfb = lohnst_zkf*7248;
        }else if(lohnst_stkl == 4){
             mztabfb_kfb = lohnst_zkf*3624;
        }else{
             mztabfb_kfb = 0;
        }
     }
     
     public void fillMztabfb_ztabfb(){
        fillLohnst_stkl();
        fillMztabfb_efa();
        fillMztabfb_sap();

         if(lohnst_stkl == 6){
             mztabfb_ztabfb = 0;
         }else{
             mztabfb_ztabfb = mztabfb_efa + mztabfb_sap + mztabfb_anp;
         }
     }
     
     public void fillUpevp_zre4vp(){
         fillLohnst_rvbemes();
         fillMre4_zre4();
         upevp_zre4vp = min(lohnst_rvbemes,mre4_zre4);
     }
     
     public void fillUpevp_vsp1(){
         fillUpevp_zre4vp();
         fillLohnst_krv();
         
         if(lohnst_krv == 1){
             upevp_vsp1 = 0;
         }else{
             upevp_vsp1 = 0.64*upevp_zre4vp*0.0935;
         }
     }
     
     public void fillUpevp_vsp2(){
         fillUpevp_zre4vp();
         fillUpevp_vhb();
         upevp_vsp2 = min(upevp_vhb,(upevp_zre4vp*0.12));
     }
     
     public void fillUpevp_zukvpv(){
         fillLohnst_pv();
         if(baseData.getKrankenversicherung()== 0){
             upevp_zukvPv = 0;
         }else{
             upevp_zukvPv = 0.07+baseData.getKvzuschlag()/100+lohnst_pv;
         }
     }
     
     public void fillUpevp_kv(){
         fillLohnst_pkv();
         fillLohnst_stkl();
         fillMre4_zre4vp();
         fillUpevp_zukvpv();
         if(lohnst_pkv > 0){
            if(lohnst_stkl == 6){
                upevp_kv = 0;
            }else{
                upevp_kv = lohnst_pkv;
            }
         }else {
            upevp_kv = (min(mre4_zre4vp,50850) * upevp_zukvPv*100)/100;  
         }
     }
     
     public void fillUpevp_kvvhb(){
         fillUpevp_kv();
         fillUpevp_vhb();
         fillUpevp_vsp2();
         if(upevp_kv > upevp_vhb){
             upevp_kvVhb = upevp_kv;
         }else{
             upevp_kvVhb = upevp_vsp2;
         }
     }
     
     public void fillUpevp_vspn(){
         fillUpevp_vsp1();
         fillUpevp_kvvhb();
         upevp_vspn = upevp_vsp1 + upevp_kvVhb;
     }
     
     public void fillUmvsp_zve(){
         fillMre4_zre4();
         fillMztabfb_ztabfb();
         fillUpevp_vspn();
         umvsp_zve = mre4_zre4 -mztabfb_ztabfb - upevp_vspn;
     }
     
     public void fillUmvsp_zzx(){
         fillUmvsp_zve();
         fillMztabfb_kztab();
         umvsp1_zzx = max(0,umvsp_zve/mztabfb_kztab);
     }
     
     public void fillUptab07_st(){
         fillUmvsp_zzx();
        
         //uptab07_st;
         if(umvsp_zzx<= 8652){
             uptab07_st = 0 * mztabfb_anp;
         }else if(umvsp_zzx <= 13669){
             uptab07_st = ((993.62*(umvsp_zzx-8652)/10000+1400)*(umvsp_zzx-8652)/10000)* mztabfb_anp;
         }else if(umvsp_zzx <= 53665){
             uptab07_st = ((225.4*(umvsp_zzx-13669)/10000+2397)*(umvsp_zzx-13669)/10000+952.48)* mztabfb_anp;
         }else if(umvsp_zzx <= 254446){
             uptab07_st = ((umvsp_zzx * 0.42-8394.14)* mztabfb_anp);
         }else{
             uptab07_st = ((umvsp_zzx * 0.45-16027.52)* mztabfb_anp);
         }
     }
     
     public void fillMst56_x(){
         fillUmvsp_zzx();
         mst56_x = min(26832,umvsp_zzx)*1.25;
     }
     
     public void fillMst56_st(){
         //mst56_st1;
         
          fillMst56_x();
        
         if(mst56_x<= 8652){
             mst56_st = 0 * mztabfb_anp;
         }else if(mst56_x <= 13669){
             mst56_st = ((993.62*(mst56_x-8652)/10000+1400)*(mst56_x-8652)/10000)* mztabfb_anp;
         }else if(mst56_x <= 53665){
             mst56_st = ((225.4*(mst56_x-13669)/10000+2397)*(mst56_x-13669)/10000+952.48)* mztabfb_anp;
         }else if(mst56_x <= 254446){
             mst56_st = ((mst56_x * 0.42-8394.14)* mztabfb_anp);
         }else{
             mst56_st = ((mst56_x * 0.45-16027.52)* mztabfb_anp);
         }
     }
     
      public void fillMst56_x1(){
         fillUmvsp_zzx();
         mst56_x1 = min(26832,umvsp_zzx)*0.75;
     }
      
      
      
     public void fillMst56_st1(){
         //mst56_st1;
         
          fillMst56_x1();
        
         if(mst56_x1<= 8652){
             mst56_st1 = 0 * mztabfb_anp;
         }else if(mst56_x1 <= 13669){
             mst56_st2 = ((993.62*(mst56_x1-8652)/10000+1400)*(mst56_x1-8652)/10000)* mztabfb_anp;
         }else if(mst56_x1 <= 53665){
             mst56_st1 = ((225.4*(mst56_x1-13669)/10000+2397)*(mst56_x1-13669)/10000+952.48)* mztabfb_anp;
         }else if(mst56_x1 <= 254446){
             mst56_st1 = ((mst56_x1 * 0.42-8394.14)* mztabfb_anp);
         }else{
             mst56_st1 = ((mst56_x1 * 0.45-16027.52)* mztabfb_anp);
         }
     }
     
     public void fillMst56_diff(){
         fillMst56_st1();
         fillMst56_st2();
         mst56_diff = mst56_st-mst56_st1;
     }
     
     public void fillMst56_mist(){
        fillUmvsp_zzx();
        
        mst56_mist = min(umvsp_zzx,26832)*0.14;
     }
     
     public void fillMst56_st2(){
         fillMst56_mist();
         fillMst56_diff();
         mst56_st2 = max(mst56_diff,mst56_mist);
     }
     
     public void fillMst56_st3(){
         //mst56_st1;
        fillMst56_st2();
        fillUmvsp_zzx();

         if(umvsp_zzx >203557){
            mst56_st3 = (203557-26832)*0.42+mst56_st2;
         }else{
            mst56_st3 = max((umvsp_zzx-26832),0)*0.42+mst56_st2;
         }
         
     }
     
     public void fillMst56_vergl(){
        fillUmvsp_zzx();
        fillMst56_st2();
        if(umvsp_zzx>10070 && umvsp_zzx <= 26832){
            mst56_vergl = mst56_st3;
        }else{
            mst56_vergl = 0;
        }
     }
     
     public void fillMst56_st4(){
         mst56_st4 = 10070*0.14;
     }
     
     public void fillMst56_st5(){
        fillUmvsp_zzx();
        fillMst56_st3();
        fillMst56_st4();
         mst56_st5 = min(max(umvsp_zzx-10070,0)*0.42+mst56_st4,mst56_st3);
     }
     
     public void fillMst56_reichst(){
        fillUmvsp_zzx();
        fillMst56_st5();
        mst56_reichst = max(umvsp_zzx-203557,0)*0.45+mst56_st5;
     }
     
     public void fillMst56_lstjahr(){
         fillLohnst_stkl();
         fillLohnst_faktorf();
         fillUptab07_st();
         fillMst56_reichst();
         if(lohnst_stkl<5){
             mst56_lstjahr = uptab07_st*lohnst_faktorF;
         }else{
             mst56_lstjahr = mst56_reichst*lohnst_faktorF;

         }
     }
     
     public void fillMst56_jw(){
         fillMst56_lstjahr();
         mst56_jw = mst56_lstjahr*100;
     }
     
     public void fillMlstjahr_lstlzzsum(){
         fillMst56_jw();
         fillMlstjahr1_lstlzzsum();
         fillMlstjahr2_lstlzzsum();
         
         mlstjahr_lstlzzSum = (mst56_jw/12)+mlstjahr1_lstlzzSum-mlstjahr2_lstlzzSum;
     }
     
     public void fillMlstjahr_ztabfb(){
         fillMztabfb_kfb();
         fillMztabfb_ztabfb();
         
         mlstjahr_ztabfb = mztabfb_kfb + mztabfb_ztabfb;
     }
     
     public void fillMlstjahr_zve(){
         fillMre4_zre4();
         fillUpevp_vspn();
         fillMlstjahr_ztabfb();
         mlstjahr_zve = mre4_zre4-upevp_vspn-mlstjahr_ztabfb; 
     }
     
     public void fillMlstjahr_zvex(){
         fillMlstjahr_zve();
         fillMztabfb_kztab();
         
         if(mlstjahr_zve<36){
             mlstjahr_zveX = 0;
         }else{
             mlstjahr_zveX = mlstjahr_zve/mztabfb_kztab;
         }
     }
     public void fillMlstjahr_st(){
          fillMlstjahr_zvex();
        
         if(mlstjahr_zveX<= 8652){
             mlstjahr_st = 0 * mztabfb_anp;
         }else if(mlstjahr_zveX <= 13669){
             mlstjahr_st = ((993.62*(mlstjahr_zveX-8652)/10000+1400)*(mlstjahr_zveX-8652)/10000)* mztabfb_anp;
         }else if(mlstjahr_zveX <= 53665){
             mlstjahr_st = ((225.4*(mlstjahr_zveX-13669)/10000+2397)*(mlstjahr_zveX-13669)/10000+952.48)* mztabfb_anp;
         }else if(mlstjahr_zveX <= 254446){
             mlstjahr_st = ((mlstjahr_zveX * 0.42-8394.14)* mztabfb_anp);
         }else{
             mlstjahr_st = ((mlstjahr_zveX * 0.45-16027.52)* mztabfb_anp);
         }
     }
     
     public void fillMlstjahr_jbmg(){
         fillLohnst_zkf();
         fillLohnst_faktorf();
         fillMst56_lstjahr();
         fillMlstjahr_st();
         
         if(lohnst_zkf > 0){
            mlstjahr_jbmg = mlstjahr_st*lohnst_faktorF;
         }else{
             mlstjahr_jbmg = mst56_lstjahr;
         }
     }
     
     public void fillMsolz_solzfrei(){
         fillMztabfb_kztab();
         msolz_solzfrei = 972*mztabfb_kztab;
     }
     
     public void fillMsolz_solzj(){
         fillMlstjahr_st();
         msolz_solzj =(mlstjahr_st*5.5)/100;
     }
     
     public void fillMsolz_solzmin(){
         fillMlstjahr_jbmg();
         fillMsolz_solzfrei();
         msolz_solzmin = ((mlstjahr_jbmg-msolz_solzfrei)*20)/100;
     }
     
    /*
    public void calcRVPflichtigerBeitrag(){
        
    }
    */
    public double calcBruttoTaxes(){
        double salaryMonth = employeeSalaryYear/12;
        double bavDiscount = 0.0d;
        
        if(optionalSalaryStatement.getBav() < 398){
            bavDiscount = optionalSalaryStatement.getBav();
        }else{
            bavDiscount = 398;
        }
        
        double taxBrutto = salaryMonth -(optionalSalaryStatement.getHolidayMoney() 
                + optionalSalaryStatement.getBonus()
                + optionalSalaryStatement.getCompanyCar1()
                + optionalSalaryStatement.getCompanyCarWayToWork() + bavDiscount);
        return taxBrutto;
    }
    
    public void clacOneTimePayment(){
        einmalzahlung = optionalSalaryStatement.getHolidayMoney()
                + optionalSalaryStatement.getBonus()
                + optionalSalaryStatement.getCompanyCar1()
                + optionalSalaryStatement.getCompanyCarWayToWork();
    }
    
    public int getYearFromDate(Date date) throws ParseException{
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            return year;
    }
    
   

}
