package practiceW07;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.*;
import java.util.Scanner;

public class Practice01_save {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        // 1. get data for secret key
        Scanner sc = new Scanner(System.in);
        System.out.print("암호화 알고리즘: ");
        String eName = sc.nextLine();

        // 2. prepare a secret key
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(eName);
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
//        System.out.println(publicKey + "\n\n" + privateKey);
        byte[] publicKeyBytes = publicKey.getEncoded();
        byte[] privateKeyBytes = privateKey.getEncoded();

        // 3. test for secret key (byte[]로 가져와서 길이 확인 후 몇 개 찍어보기)
        System.out.println("생성된 공개키 정보: ");
        System.out.println("키의 길이 (bytes): " + publicKeyBytes.length);
        for (byte bytes : publicKeyBytes) {
            System.out.print(String.format("%02x", bytes) + "\t");
        }

        System.out.println("\n생성된 개인키 정보: ");
        System.out.println("키의 길이 (bytes): " + privateKeyBytes.length);
        for (byte bytes : privateKeyBytes) {
            System.out.print(String.format("%02x", bytes) + "\t");
        }
        System.out.println("\n");

        // 4. save to file
        System.out.print("공개키를 저장할 파일 이름: ");
        String publicFName = sc.nextLine();

        try (FileOutputStream fileOutputStream = new FileOutputStream(publicFName)) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(publicKey);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.print("개인키를 저장할 파일 이름: ");
        String privateFName = sc.nextLine();

        try (FileOutputStream fileOutputStream = new FileOutputStream(privateFName)) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(privateKey);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
