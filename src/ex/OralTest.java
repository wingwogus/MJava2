package ex;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class OralTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("구두시험 볼 java 교수와 db 교수 이름을 입력하세요");

        String javaName = sc.next();
        String dbName = sc.next();
        Professor javaProf = new Professor(javaName, "java");
        Professor dbProf = new Professor(dbName, "DB");

        System.out.println("수업을 듣고 있는 2학년 학생들의 수를 입력하세요: ");
        int count = sc.nextInt();

        Student[] students = new Student[count];

        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "번쨰 학생의 학번과 이름을 입력하세요: ");
            String no = sc.next();
            String name = sc.next();
            students[i] = new Student(no, name, javaProf, dbProf);
        }

        for (int i = 0; i < count; i++) {
            students[i].start();
        }
    }

    private static class Professor {
        private String name;
        private String subject;

        public Professor(String name, String subject) {
            this.name = name;
            this.subject = subject;
        }

        public synchronized void test(Student s) {
            System.out.println(s.no + " " + s.name + " 학생이 " + name + "교수 앞에서 " + subject + " 구두 시험을 본다");
        }
    }

    private static class Student extends Thread{
        String no;
        String name;
        Professor javaProf;
        Professor dbProf;

        public Student(String no, String name, Professor javaProf, Professor dbProf) {
            this.no = no;
            this.name = name;
            this.javaProf = javaProf;
            this.dbProf = dbProf;
        }

        @Override
        public void run() {
            javaProf.test(this);
            dbProf.test(this);
            System.out.println(name + " 학생이 시험을 마치고 집으로 돌아간다");
        }
    }
}
