package practiceW04;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Practice02_write {
    public static void main(String[] args) throws FileNotFoundException {
        // 내보내기
        Scanner sc = new Scanner(System.in);
        System.out.print("학생 정보를 입력하세요 : ");
        String str = sc.nextLine();
        StringTokenizer st = new StringTokenizer(str);

        int sid;
        String name;
        String major;
        Student student = new Student();
        while (st.hasMoreTokens()) {
            sid = Integer.parseInt(st.nextToken());
            name = st.nextToken();
            major = st.nextToken();
            student.setSid(sid);
            student.setName(name);
            student.setMajor(major);
        }

        System.out.println("생성된 학생 객체의 정보입니다.");
        System.out.println(student);

        System.out.print("정보를 저장할 파일 이름을 입력하세요: ");
        String fname = sc.nextLine();
        student.writeObject(fname, student);
        System.out.println("프로그램을 종료합니다.");
    }
}
