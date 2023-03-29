package practiceW04;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    private int sid;
    private String name;
    private String major;

    public Student() {
    }

    @Override
    public String toString() {
        return "Student[" + name + ", " + sid + ", " + major + ']';
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getSid() {
        return sid;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    // PracticeW04_01
    static Student readFromFile(String fname) throws FileNotFoundException {
        // 파일 fname에 저장된 정보를 이용해 객체 생성 후 반환, 객체 정보는 텍스트 형태

        int sid = 0;
        String name = null;
        String major = null;

        Student student = new Student();
        try (FileReader fr = new FileReader(fname)) {
            Scanner sc = new Scanner(new BufferedReader(fr));

            while (sc.hasNext()) {
                sid = Integer.parseInt(sc.nextLine());
                name = sc.nextLine();
                major = sc.nextLine();
            }
            student.setSid(sid);
            student.setName(name);
            student.setMajor(major);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return student;
    }

    boolean writeToFile(String fname) throws IOException {
        // 해당 객체를 주어진 파일에 텍스트 형태로 저장

        try (PrintWriter out = new PrintWriter(new FileWriter(fname))) {
            out.println(this.getSid());
            out.println(this.getName());
            out.println(this.getMajor());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    // PracticeW04_02
    // 직렬화
    static boolean writeObject(String fname, Student s) throws FileNotFoundException {
        // 매개변수로 주어진 객체를 직렬화하여 주어진 파일에 저장

        try (FileOutputStream fstream = new FileOutputStream(fname)) {
            try (ObjectOutputStream ostream = new ObjectOutputStream(fstream)) {
                ostream.writeObject((Student)s);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    static Student readObject(String fname) throws FileNotFoundException {
        // 주어진 파일에 저장된 정보를 이용하여 객체를 생성하고 반환
        // 객체 정보는 직렬화되어 파일에 저장되어 있다고 가정함

        Student student = new Student();
        try (FileInputStream fis = new FileInputStream(fname)) {
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                Object obj = ois.readObject();
                student = (Student) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return student;
    }
}
