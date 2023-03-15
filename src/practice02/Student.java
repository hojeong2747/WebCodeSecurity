package practice02;

public class Student implements Comparable<Student> {

    private String major;
    private int num;
    private String name;

    public Student(String major, int num, String name) {
        this.major = major;
        this.num = num;
        this.name = name;
    }

    @Override
    public String toString() {
        return major + "/" + num  + "/" + name;
    }

    public int compareTo(Student s) {
        return num - s.num; // 학번 정렬. 오름차순
    }

    public String getMajor() {
        return major;
    }

    public int getNum() {
        return num;
    }
}
