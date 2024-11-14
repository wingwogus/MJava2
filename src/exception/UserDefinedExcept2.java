package exception;

import java.util.Scanner;

public class UserDefinedExcept2 {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            if (i < 0) {
                throw new MinusException();
            }
            System.out.println("읽어들인 점수는 " + i + "점");
        } catch (MinusException e) {
            System.out.println("양수를 입력하시오");
        }
    }
}

class MinusException extends Exception{

}