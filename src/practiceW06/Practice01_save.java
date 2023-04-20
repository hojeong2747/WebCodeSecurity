package practiceW06;

import javax.crypto.KeyGenerator;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Practice01_save {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        // 1. get data for secret key
        Scanner sc = new Scanner(System.in);
        System.out.print("암호화 알고리즘: ");
        String eName = sc.nextLine();

        // 2. prepare a secret key
        KeyGenerator keyGenerator = KeyGenerator.getInstance(eName);
        keyGenerator.init(128);
        Key secretKey = keyGenerator.generateKey();

        // 3. convert to byte[]
        byte[] secretKeyBytes = secretKey.getEncoded();
        System.out.println("키의 길이 (bytes): " + secretKeyBytes.length);

        for (byte bytes : secretKeyBytes) {
            System.out.print(String.format("%02x", bytes) + "\t");
        }
        System.out.println();

        // 4. save to file
        System.out.print("비밀키를 저장할 파일 이름: ");
        String fName = sc.nextLine();

        try (FileOutputStream fileOutputStream = new FileOutputStream(fName)) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(secretKey);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
