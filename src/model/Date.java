package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

public class Date {
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private LocalDate localDate;
    private String date;
    public Date(String date) {
        this.localDate = LocalDate.parse(date, dtf);
        this.date = date;
    }
    public String getDate() {
        return this.date;
    }
    public LocalDate getLocalDate(){return this.localDate;}
    public static Integer getDaysBetween(Date inputDate1, Date inputDate2) {
        long daysBetween = DAYS.between(inputDate1.localDate, inputDate2.localDate);
        int result = (int) (daysBetween);
        return result;

    }

    public static Date getNDaysAfter(Date inputDate1, int n) {
        String newDate = dtf.format(inputDate1.localDate.plusDays(n));
        return new Date(newDate);
    }


}
