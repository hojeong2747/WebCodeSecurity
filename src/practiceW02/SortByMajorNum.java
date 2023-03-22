package practiceW02;

import java.util.Comparator;

public class SortByMajorNum implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        if (o1.getMajor().equals(o2.getMajor())) {
            return o1.getNum() - o2.getNum(); // 오름차순
        } else
            return o1.getMajor().charAt(0) - o2.getMajor().charAt(0); // 첫 알파벳 순
    }
}
