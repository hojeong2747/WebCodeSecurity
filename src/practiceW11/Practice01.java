package practiceW11;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Practice01 {
    public static void main(String[] args) throws CloneNotSupportedException {

        Date[] list = new Date[3];

        list[0] = new Date(2019, 5, 13);
        list[1] = new Date(2000, 1, 1);
        list[2] = new Date(1988, 12, 25);

        Diary diary = new Diary(list);
        System.out.println("Original Array: ");
        System.out.println(diary);

        Diary clonedDiary = (Diary) diary.clone();
        System.out.println("Cloned Array: ");
        System.out.println(clonedDiary);

        Scanner sc = new Scanner(System.in);
        System.out.print("\n변경하고 싶은 배열을 선택하세요 (1-original/2-cloned): ");
        int num = sc.nextInt();

        System.out.print("변경하고 싶은 날짜의 인덱스를 입력하세요: ");
        int idx = sc.nextInt();

        System.out.print("년도: ");
        int year = sc.nextInt();
        System.out.print("월: ");
        int month = sc.nextInt();
        System.out.print("일 ");
        int day = sc.nextInt();

        if (num == 1) {
            Date[] listOfDate = diary.getListOfDates();
            listOfDate[idx].setYear(year);
            listOfDate[idx].setMonth(month);
            listOfDate[idx].setDay(day);
        } else if (num == 2) {
            Date[] listOfDate = clonedDiary.getListOfDates();
            listOfDate[idx].setYear(year);
            listOfDate[idx].setMonth(month);
            listOfDate[idx].setDay(day);
        }

        System.out.println("\nOriginal Array: ");
        System.out.println(diary);

        System.out.println("Cloned Array: ");
        System.out.println(clonedDiary);

    }
}
