package week5;

import java.util.Scanner;

public class Vote_Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("투표소 이름을 입력하세요. =>");
        VotePlace votePlace = new VotePlace(sc.next());

        System.out.println("투표할 주민들의 수를 입력하세요. =>");
        int num = sc.nextInt();
        People[] people = new People[num];

        for (int i = 0; i < num; i++) {
            System.out.println((i + 1) + "번째 주민의 주민번호와 이름을 입력하세요");
            String id = sc.next();
            String name = sc.next();
            people[i] = new People(id, name, votePlace);
        }

        for (People person : people) {
            person.start();
        }

    }

    private static class VotePlace {
        private String name;

        public VotePlace(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private static class People extends Thread{
        VotePlace votePlace;
        private String id;
        private String name;

        public People(String id, String name, VotePlace votePlace) {
            this.id = id;
            this.name = name;
            this.votePlace = votePlace;
        }

        @Override
        public void run() {
            System.out.println(votePlace.getName() + "에서 주민번호: " + id + " 이름: " + name + " 신분확인하다");
            System.out.println(votePlace.getName() + "에서 주민번호: " + id + " 이름: " + name + "이(가) 투표한다");
            System.out.println(votePlace.getName() + "에서 주민번호: " + id + " 이름: " + name + "이(가) 투표지를 투표함에 넣는다");
        }
    }
}
