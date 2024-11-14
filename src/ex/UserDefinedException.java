package ex;

import java.util.InputMismatchException;
import java.util.Scanner;

import static ex.UserDefinedException.checkNo1;
import static ex.UserDefinedException.checkNo2;

public class UserDefinedException {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("학번과 이름을 입력하세요");
            int no = sc.nextInt();
            String name = sc.next();

            new Check1(no).start();
            new Check2(no).start();

            welcome(no, name);
        } catch (InputMismatchException e) {
            System.out.println("학번은 정수로 입력하시오");
        }
    }

    public static void checkNo1(int no) throws ShortException{
        String noStr = String.valueOf(no);

        if (noStr.length() != 10) {
            throw new ShortException();
        }
    }

    public static void checkNo2(int no) throws NoPositiveE{
        if (no < 0) {
            throw new NoPositiveE();
        }
    }


    public static void welcome(int no, String name) {
        int g = no / 1000000;
        String grade = "재학생";

        if (g == 2024) {
            grade = "신입생";
        }

        System.out.println(no + "번 " + name + " " + grade + " 환영합니다");
    }
}
class NoPositiveE extends Exception {
}

class ShortException extends Throwable {
}

class Check1 extends Thread {
    int no;

    public Check1(int no) {
        this.no = no;
    }

    @Override
    public void run() {
        try {
            checkNo1(no);
        } catch (ShortException e) {
            System.out.println("길게");
        }
    }
}
class Check2 extends Thread {
    int no;

    public Check2(int no) {
        this.no = no;
    }

    @Override
    public void run() {
        try {
            checkNo2(no);
        } catch (NoPositiveE e) {
            System.out.println("양수입력");
        }
    }
}