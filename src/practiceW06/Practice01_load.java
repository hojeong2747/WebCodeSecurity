package practiceW06;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.Key;
import java.util.Scanner;

public class Practice01_load {
    public static void main(String[] args) {

        // 1. load file
        Scanner sc = new Scanner(System.in);
        System.out.print("비밀키를 저장한 파일 이름: ");
        String fName = sc.nextLine();

        // 2. get data for secret key
        System.out.print("암호화 알고리즘: ");
        String eName = sc.nextLine();

        // 3. read from file
        Key secretKey;
        try (FileInputStream fileInputStream = new FileInputStream(fName)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                Object obj = objectInputStream.readObject();
                secretKey = (Key) obj;

            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // 4. convert to byte[]
        byte[] secretKeyBytes = secretKey.getEncoded();
        System.out.println("키의 길이 (bytes): " + secretKeyBytes.length);

        for (byte bytes : secretKeyBytes) {
            System.out.print(String.format("%02x", bytes) + "\t");
        }
        System.out.println();

    }
}
