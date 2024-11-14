package week6;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class EntranceExam {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("신입사원 채용을 진행할 회사명과 지원자 수를 입력하세요 -> ");
        String companyName = sc.next();
        int count = sc.nextInt();
        Applicant[] a = new Applicant[count];

        InterviewRoom interviewRoom = new InterviewRoom(companyName);

        for (int i = 0; i < count; i++) {
            a[i] = new Applicant(i + 1, interviewRoom);
            a[i].start();
        }
    }


}

class InterviewRoom {
    String name;
    int nowOrder = 1;

    public InterviewRoom(String name) {
        this.name = name;
        System.out.println(this.name + "의 신입사원 채용을 시작합니다");
    }

    public synchronized void interview(int order) {
        while (!(nowOrder == order)) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(order + "번째 지원자가 면접 본다");
        nowOrder++;
        notifyAll();
    }
}

class Applicant extends Thread {
    InterviewRoom room;
    int order;
    LocalDateTime now;

    public Applicant(int order, InterviewRoom room) {
        this.room = room;
        this.order = order;
    }

    @Override
    public void run() {
        writtenText();
        room.interview(order);
    }

    void writtenText() {
        now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("hh시 mm분 ss초"));
        System.out.println(time + "에 " + order + "번 지원자가 필기시험 응시");
    }
}