package practiceW04;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static practiceW04.Student.readFromFile;
import static practiceW04.Student.readObject;

public class Practice02_read {
    public static void main(String[] args) throws FileNotFoundException {
        // 읽어오기
        Scanner sc = new Scanner(System.in);
        System.out.print("정보를 읽어올 파일 이름을 입력하세요 : ");
        String str = sc.nextLine();

        Student student = new Student(); // 여기 왜 회색이지? 같은 패키지랑 관련 있는 건가. 아 뭔가 알겠다.
        student = readObject(str);

        System.out.println("생성된 학생 객체의 정보입니다.");
        System.out.println(student);
    }
}
