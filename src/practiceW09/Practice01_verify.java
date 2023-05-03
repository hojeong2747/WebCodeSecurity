package practiceW09;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.*;
import java.util.Scanner;

public class Practice01_verify {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        Scanner sc = new Scanner(System.in);

        String strData = "Software security of Dongduk University";
        System.out.println("서명에 사용할 데이터: " + strData);

        System.out.print("공개키 파일 이름: ");
        String publicFName = sc.nextLine();
        System.out.print("전자서명 파일 이름: ");
        String signFName = sc.nextLine();
        System.out.println();

        // Signing a document
        PublicKey publicKey;
        try (FileInputStream fileInputStream = new FileInputStream(publicFName)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

                Object obj = objectInputStream.readObject();
                publicKey = (PublicKey) obj;

            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

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

        String signAlgorithm = "SHA256withRSA";
        byte[] data = strData.getBytes();

        Signature sig = Signature.getInstance(signAlgorithm);
        sig.initVerify(publicKey);
        sig.update(data);
        boolean rslt = sig.verify(signature);

        System.out.println("서명 검증 결과 : " + rslt);


    }
}
