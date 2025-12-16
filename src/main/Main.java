package main;

import Student.*;

public class Main {

    public static void main(String[] args) {

        StudentList list = new StudentList();

        System.out.println("=== 전체 학생 목록 ===");
        list.printAll();

        Student s = list.getStudent(1); // 두 번째 학생
        if (s == null) return;

        ScoreService service = new ScoreService();
        service.print(s);
    }
}
