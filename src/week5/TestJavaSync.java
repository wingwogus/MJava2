package week5;

import java.util.Scanner;

public class TestJavaSync {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("JAVA 교수 이름을 입력하세요");
        Professor professor = new Professor(sc.next());

        System.out.println("JAVA 시험을 볼 학생들의 수를 입력하세요");
        int num = sc.nextInt();
        Student[] students = new Student[num];

        for (int i = 0; i < num; i++) {
            System.out.println((i + 1) + "번째 학생의 이름과 학번을 입력하세요");
            students[i] = new Student(sc.next(), sc.nextInt(), professor);
        }

        sc.close();
        
        for (Student student : students) {
            student.start();
        }
    }

    private static class Professor {
        private String name;

        public Professor(String name) {
            this.name = name;
        }

        synchronized public void checkTestPaper(Student s) {
            s.end = System.currentTimeMillis();
            double totalTime = s.end - s.start;
            System.out.println(s.id + "번 " + s.name + " 시험 종료, 시험 본 시간은 " + totalTime + "ms");
            if (totalTime > 5000) {
                System.out.println(s.id + "번 " + s.name + "의 시험지는 Time Over");
            } else {
                System.out.println(name + " 교수가 " + s.id + "번 " + s.name + "의 시험지를 확인하고 접수했다");
            }
        }
    }

    private static class Student extends Thread {
        String name;
        int id;
        double start;
        double end;
        Professor professor;

        public Student(String name, int id, Professor professor) {
            this.name = name;
            this.id = id;
            this.professor = professor;
        }

        @Override
        public void run() {
            start = System.currentTimeMillis();
            System.out.println(id + "번 " + name + "이(가) JAVA 시험을 보고 있다");
            try {
                Thread.sleep((int) (Math.random() * 10000));
            } catch (InterruptedException e) {
            }
            professor.checkTestPaper(this);
            System.out.println(name + "은(는) 집으로 돌아간다");
        }
    }
}
