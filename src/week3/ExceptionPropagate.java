package week3;

import java.util.InputMismatchException;
import java.util.Scanner;

class OperatorException extends Exception {}

class Calculator {
    private int num1, num2;
    private String opr;

    public void inputExpression() throws OperatorException {
        Scanner sc = new Scanner(System.in);
        num1 = sc.nextInt();
        opr = sc.next();
        if(!(opr.equals("+") || opr.equals("-") || opr.equalsIgnoreCase("X") || opr.equals("/"))) {
            throw new OperatorException();
        }
        num2 = sc.nextInt();
        sc.close();
    }

    public void calculate() {
        int result= 0;
        if(opr.equals("+")) {
           result  = num1 + num2;
        } else if (opr.equals("-")) {
            result  = num1 - num2;
        } else if (opr.equalsIgnoreCase("X")) {
            result = num1 * num2;
        } else if (opr.equals("/")) {
            result = num1 / num2;
        }
        System.out.println(num1 + " " + opr + " " + num2 + " = " + result);
    }
}
public class ExceptionPropagate {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        while (true) {
            try {
                calculator.inputExpression();
                calculator.calculate();
                break;
            } catch (OperatorException e) {
                System.out.println("연산은 사칙 연산만 가능합니다");
            } catch (InputMismatchException e) {
                System.out.println("피연산자는 정수만 들어올 수 있습니다");
            }
        }

    }
}
