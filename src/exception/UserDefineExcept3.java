package exception;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserDefineExcept3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("점수를 입력해주세요");
            int i = sc.nextInt();
            if (i < 0) {
                throw new MinusE();
            } else if (i > 100) {
                throw new OverflowE();
            }
            System.out.println("읽어들인 점수는 " + i + "점");
        } catch (MinusE | OverflowE e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("정수를 입력하세요");
        }
    }

    static class MinusE extends Exception {
        public MinusE() {
            super("양수를 입력하세요");
        }
    }

    static class OverflowE extends Exception {
        public OverflowE() {
            super("점수는 100점 이하여야 합니다");
        }
    }
}
