package practiceW09;

import java.io.*;
import java.security.*;
import java.util.Scanner;

public class Practice02_sign {
    public static void main(String[] args) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {

        Scanner sc = new Scanner(System.in);
        System.out.print("데이터 파일 이름: ");
        String fName = sc.nextLine();

        // 1. 데이터 파일 내용 읽어 들여서 byte[] 형태로 저장
        byte[] data;
        try (FileInputStream fileInputStream = new FileInputStream(fName)) {
            data = fileInputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 2. load a private key from a file
        System.out.print("개인키 파일 이름: ");
        String privateFName = sc.nextLine();
        System.out.println();

        PrivateKey privateKey;
        try (FileInputStream fileInputStream = new FileInputStream(privateFName)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

                Object obj = objectInputStream.readObject();
                privateKey = (PrivateKey) obj;

            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // 3. Calculate a signature, and save it into a binary file
        String signAlgorithm = "SHA256withRSA";

        Signature sig = Signature.getInstance(signAlgorithm);
        sig.initSign(privateKey);
        sig.update(data);
        byte[] signature = sig.sign();

        System.out.println("생성된 서명 정보: " + signature.length + " bytes");
        for (byte bytes : signature) {
            System.out.print(String.format("%02x", bytes) + "\t");
        }
        System.out.println("\n");

        System.out.print("서명을 저장할 파일 이름: ");
        String saveFName = sc.nextLine();
        try (FileOutputStream fileOutputStream = new FileOutputStream(saveFName)) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(signature);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("서명을 파일에 저장했습니다.");

    }
}
