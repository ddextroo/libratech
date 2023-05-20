/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libratech.design;

/**
 *
 * @author Admin
 */
public class SelectedDate {
    
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public SelectedDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public SelectedDate() {
    }

    private int day;
    private int month;
    private int year;
    
}
