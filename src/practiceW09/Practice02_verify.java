package practiceW09;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.*;
import java.util.Scanner;

public class Practice02_verify {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

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

        // 2. load the matching public key from file
        System.out.print("공개키 파일 이름: ");
        String publicFName = sc.nextLine();
        System.out.println();

        PublicKey publicKey;
        try (FileInputStream fileInputStream = new FileInputStream(publicFName)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

                Object obj = objectInputStream.readObject();
                publicKey = (PublicKey) obj;

            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // 3. Open the saved signature file, and save the signature in byte[]
        System.out.print("전자서명 파일 이름: ");
        String signFName = sc.nextLine();
        System.out.println();

        byte[] signature;
        try (FileInputStream fileInputStream = new FileInputStream(signFName)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

                Object obj = objectInputStream.readObject();
                signature = (byte[]) obj;

            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("입력된 서명 정보: " + signature.length + " bytes");
        for (byte bytes : signature) {
            System.out.print(String.format("%02x", bytes) + "\t");
        }
        System.out.println("\n");


        // 4. Verify the signature, and print the verification result on console.
        String signAlgorithm = "SHA256withRSA";

        Signature sig = Signature.getInstance(signAlgorithm);
        sig.initVerify(publicKey);
        sig.update(data);
        boolean rslt = sig.verify(signature);

        System.out.println("서명 검증 결과 : " + rslt);

    }
}
