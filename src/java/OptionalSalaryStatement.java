
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
@Named("optionalSalaryStatement")
@ConversationScoped
public class OptionalSalaryStatement implements Serializable{
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

    public double getBav() {
        return bav;
    }

    public void setBav(double bav) {
        this.bav = bav;
    }
    
    
    

    public double getHolidayMoney() {
        return holidayMoney;
    }

    public void setHolidayMoney(double holidayMoney) {
        this.holidayMoney = holidayMoney;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getCompanyCar1() {
        return companyCar1;
    }

    public void setCompanyCar1(double companyCar1) {
        this.companyCar1 = companyCar1;
    }

    public double getCompanyCarWayToWork() {
        return companyCarWayToWork;
    }

    public void setCompanyCarWayToWork(double companyCarWayToWork) {
        this.companyCarWayToWork = companyCarWayToWork;
    }

    public double getHoursAtNight() {
        return hoursAtNight;
    }

    public void setHoursAtNight(double hoursAtNight) {
        this.hoursAtNight = hoursAtNight;
    }

    public double getHoursSunday() {
        return hoursSunday;
    }

    public void setHoursSunday(double hoursSunday) {
        this.hoursSunday = hoursSunday;
    }

    public double getHoursOnHoliday() {
        return hoursOnHoliday;
    }

    public void setHoursOnHoliday(double hoursOnHoliday) {
        this.hoursOnHoliday = hoursOnHoliday;
    }

    public double getHolidayExtraBonus() {
        return holidayExtraBonus;
    }

    public void setHolidayExtraBonus(double holidayExtraBonus) {
        this.holidayExtraBonus = holidayExtraBonus;
    }

    public double getExpensesRefund() {
        return expensesRefund;
    }

    public void setExpensesRefund(double expensesRefund) {
        this.expensesRefund = expensesRefund;
    }

    public double getRideMoney() {
        return rideMoney;
    }

    public void setRideMoney(double rideMoney) {
        this.rideMoney = rideMoney;
    }

    public double getBonusbaV() {
        return bonusbaV;
    }

    public void setBonusbaV(double bonusbaV) {
        this.bonusbaV = bonusbaV;
    }
    
    

    @Override
    public String toString(){
        return "Urlaubsgeld: " + this.holidayMoney + " bonus: " + this.bonus;
    }

}
