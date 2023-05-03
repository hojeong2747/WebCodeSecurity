package practiceW09;

import java.io.*;
import java.security.*;
import java.util.Scanner;

public class Practice01_sign {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        Scanner sc = new Scanner(System.in);

        String strData = "Software security of Dongduk University";
        System.out.println("서명에 사용할 데이터: " + strData);

        System.out.print("개인키 파일 이름: ");
        String fName = sc.nextLine();
        System.out.println();

        // Signing a document
        PrivateKey privateKey;
        try (FileInputStream fileInputStream = new FileInputStream(fName)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

                Object obj = objectInputStream.readObject();
                privateKey = (PrivateKey) obj;

            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String keyAlgorithm = "RSA";
        String signAlgorithm = "SHA256withRSA";
        byte[] data = strData.getBytes();

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
