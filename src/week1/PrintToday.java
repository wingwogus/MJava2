package week1;

import java.time.LocalDate;

public class PrintToday {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.print("오늘은 " + today.getYear() +"년 ");
        System.out.print(today.getMonthValue() +"월 ");
        System.out.print(today.getDayOfMonth() +"일");
    }
}
