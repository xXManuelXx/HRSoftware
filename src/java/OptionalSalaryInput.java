
import java.io.Serializable;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mfehrenbach
 */
@Named("optionalSalaryInput")
@ConversationScoped
public class OptionalSalaryInput implements Serializable{
    private double holidayMoney;
    private double bonus;
    private double companyCar1;
    private double companyCarWayToWork;
    private double hoursAtNight;
    private double hoursSunday;
    private double hoursOnHoliday;
    private double holidayExtraBonus;
    private double expensesRefund;
    private double rideMoney;
    private double bonusbaV;
    private double bav;
    private double sunHolidayNightMoney;

    public double getBav() {
        return bav;
    }

    public void setBav(double bav) {
        this.bav = bav;
    }

    public void setSunHolidayNightMoney(double sunHolidayNightMoney) {
        this.sunHolidayNightMoney = sunHolidayNightMoney;
    }
    
    
    

    public double getHolidayMoney() {
        holidayMoney = Math.floor(holidayMoney*100.0)/100.0;
        
        return holidayMoney;
    }

    public void setHolidayMoney(double holidayMoney) {
        this.holidayMoney = holidayMoney;
    }

    public double getBonus() {
        bonus = Math.floor(bonus*100.0)/100.0;
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getCompanyCar1() {
         companyCar1 = Math.floor(companyCar1*100.0)/100.0;
        return companyCar1;
    }

    public void setCompanyCar1(double companyCar1) {
        this.companyCar1 = companyCar1;
    }

    public double getCompanyCarWayToWork() {
        companyCarWayToWork = Math.floor(companyCarWayToWork*100.0)/100.0;
        return companyCarWayToWork;
    }

    public void setCompanyCarWayToWork(double companyCarWayToWork) {
        this.companyCarWayToWork = companyCarWayToWork;
    }

    public double getHoursAtNight() {
        hoursAtNight = Math.floor(hoursAtNight*100.0)/100.0;
        return hoursAtNight;
    }

    public void setHoursAtNight(double hoursAtNight) {
        this.hoursAtNight = hoursAtNight;
    }

    public double getHoursSunday() {
        hoursSunday = Math.floor(hoursSunday*100.0)/100.0;

        return hoursSunday;
    }

    public void setHoursSunday(double hoursSunday) {
        this.hoursSunday = hoursSunday;
    }

    public double getHoursOnHoliday() {
       hoursOnHoliday = Math.floor(hoursOnHoliday*100.0)/100.0;

        return hoursOnHoliday;
    }

    public void setHoursOnHoliday(double hoursOnHoliday) {
        this.hoursOnHoliday = hoursOnHoliday;
    }

    public double getHolidayExtraBonus() {
        holidayExtraBonus = Math.floor(holidayExtraBonus*100.0)/100.0;

        return holidayExtraBonus;
    }

    public void setHolidayExtraBonus(double holidayExtraBonus) {
        this.holidayExtraBonus = holidayExtraBonus;
    }

    public double getExpensesRefund() {
        expensesRefund = Math.floor(expensesRefund*100.0)/100.0;

        return expensesRefund;
    }

    public void setExpensesRefund(double expensesRefund) {
        this.expensesRefund = expensesRefund;
    }

    public double getRideMoney() {
        rideMoney = Math.floor(rideMoney*100.0)/100.0;

        return rideMoney;
    }

    public void setRideMoney(double rideMoney) {
        this.rideMoney = rideMoney;
    }

    public double getBonusbaV() {
        bonusbaV = Math.floor(bonusbaV*100.0)/100.0;

        return bonusbaV;
    }

    public void setBonusbaV(double bonusbaV) {
        this.bonusbaV = bonusbaV;
    }
    
    public double getSunHolidayNightMoney(){
        this.sunHolidayNightMoney = hoursAtNight+hoursOnHoliday+hoursSunday+hoursOnHoliday;
        sunHolidayNightMoney = Math.floor(sunHolidayNightMoney*100.0)/100.0;

        return this.sunHolidayNightMoney;
    }

    @Override
    public String toString(){
        return "Urlaubsgeld: " + this.holidayMoney + " bonus: " + this.bonus;
    }

}
