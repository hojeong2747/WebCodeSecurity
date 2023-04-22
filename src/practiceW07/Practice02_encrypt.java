package practiceW07;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Scanner;

public class Practice02_encrypt {

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {

        // 1. prepare a secret key
        Scanner sc = new Scanner(System.in);
        System.out.print("공개키 파일: ");
        String publicFName = sc.nextLine();

        PublicKey publicKey;
        try (FileInputStream fileInputStream = new FileInputStream(publicFName)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                Object obj = objectInputStream.readObject();
                publicKey = (PublicKey) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        // 2. prepare data
        System.out.print("데이터 파일 이름: ");
        String txtFName = sc.nextLine();

        String plainTxt = "";
        try (FileReader fileReader = new FileReader(txtFName)) {
            sc = new Scanner(new BufferedReader(fileReader));

            while (sc.hasNext()) {
                plainTxt += sc.nextLine() + "\n";
            }
//            System.out.println(plainTxt);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        byte[] input = plainTxt.getBytes();


        // 3. create a Cipher object
//        Cipher c1 = Cipher.getInstance("RSA");
        Cipher c1 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        c1.init(Cipher.ENCRYPT_MODE, publicKey);

        // 4. create a CipherOutputStream
        sc = new Scanner(System.in);
        System.out.print("암호데이터 출력 파일: ");
        String encryptFName = sc.nextLine();
        try (FileOutputStream fileOutputStream = new FileOutputStream(encryptFName);
             CipherOutputStream cipherOutputStream = new CipherOutputStream(fileOutputStream, c1)) {

            // 5. write data to cos
            cipherOutputStream.write(input);
            cipherOutputStream.flush();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
