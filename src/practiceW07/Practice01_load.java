package practiceW07;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class Practice01_load {

    public static void main(String[] args) {

        // 1. load file
        Scanner sc = new Scanner(System.in);
        System.out.print("공개키를 저장한 파일 이름: ");
        String publicFName = sc.nextLine();

        // 2. get data for secret key
        System.out.print("암호화 알고리즘: ");
        String publicEName = sc.nextLine();

        // 3. read from file
        PublicKey publicKey;
        try (FileInputStream fileInputStream = new FileInputStream(publicFName)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

                Object obj = objectInputStream.readObject();
                publicKey = (PublicKey) obj;
                byte[] publicKeyBytes = publicKey.getEncoded();

                System.out.println("키의 길이 (bytes): " + publicKeyBytes.length);
                for (byte bytes : publicKeyBytes) {
                    System.out.print(String.format("%02x", bytes) + "\t");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n");

        //
        // 1. load file
        System.out.print("개인키를 저장한 파일 이름: ");
        String privateFName = sc.nextLine();

        // 2. get data for secret key
        System.out.print("암호화 알고리즘: ");
        String privateEName = sc.nextLine();

        // 3. read from file
        PrivateKey privateKey;
        try (FileInputStream fileInputStream = new FileInputStream(privateFName)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

                Object obj = objectInputStream.readObject();
                privateKey = (PrivateKey) obj;
                byte[] privateKeyBytes = privateKey.getEncoded();

                System.out.println("키의 길이 (bytes): " + privateKeyBytes.length);
                for (byte bytes : privateKeyBytes) {
                    System.out.print(String.format("%02x", bytes) + "\t");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
