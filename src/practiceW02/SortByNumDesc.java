package practiceW02;

import java.util.Comparator;

public class SortByNumDesc implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o2.getNum() - o1.getNum(); // 내림차순
    }
}
