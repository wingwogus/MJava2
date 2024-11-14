package to;


// 스레드 : 응시자들 , 공유자원: 실기시험장 , 동기화 메소드 : 보컬,드럼,기타 시험본다, 신호값의 개수 : 2가지(신호 1: 응시자 수만큼, 신호 2: 3가지)


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MT {
    public static void main(String[] args) {

        System.out.println("학과명을 입력하세요. =>");

        Scanner s = new Scanner(System.in);
        String dept = s.next();

        System.out.println(dept + "응시자들의 수를 입력하세요. =>");
        int cnt = s.nextInt();

        Thread[] Students = new Thread[cnt];

        ExamRoom er = new ExamRoom(dept);

        for (int i = 0; i < cnt; i++) {
            if (i%3 == 0){
                Students[i] = new VocalStudents(i+1,er);
            }else if(i%3 == 1){
                Students[i] = new DrumStudents(i+1,er);
            }else{
                Students[i] = new GuitarStudents(i+1,er);
            }
        }

        for (int i = 0; i < cnt; i++) {
            Students[i].start();
        }
    }
}

class VocalStudents extends Thread {
    ExamRoom er;
    int cnt;

    public VocalStudents(int cnt, ExamRoom er) {
        this.cnt = cnt;
        this.er = er;
    }

    @Override
    public void run() {
        er.VocalExam(this);
    }
}

class DrumStudents extends Thread {
    ExamRoom er;
    int cnt;

    public DrumStudents(int cnt, ExamRoom er) {
        this.cnt = cnt;
        this.er = er;
    }

    @Override
    public void run() {
        er.DrumExam();
    }
}

class GuitarStudents extends Thread {
    ExamRoom er;
    int cnt;

    public GuitarStudents(int cnt, ExamRoom er) {
        this.cnt = cnt;
        this.er = er;
    }
    @Override
    public void run() {
        er.GuitarExam();
    }
}

class ExamRoom {
    int cnt = 1;
    String dept;
    int signal = 1;


    public ExamRoom(String dept) {
        this.dept = dept;
    }

    synchronized void VocalExam(VocalStudents thread) {
        while (signal != 1 ) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(dept + cnt + "번 응시자가 보컬시험 본다");
        System.out.println("실제로는 " + thread.cnt + "번이 보는 중");
        signal = 2;
        cnt++;
        notifyAll();
    }

    synchronized void DrumExam() {
        while (signal != 2) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(dept + cnt + "번 응시자가 드럼시험 본다");
        signal = 3;
        cnt++;
        notifyAll();
    }

    synchronized void GuitarExam() {
        while (signal != 3 ) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(dept + cnt + "번 응시자가 기타시험 본다");
        signal = 1;
        cnt++;
        notifyAll();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh-mm-dd");
        System.out.println(LocalDateTime.now().format(dateTimeFormatter));
    }


}
