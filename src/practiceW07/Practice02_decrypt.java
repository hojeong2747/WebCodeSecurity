package practiceW07;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class Practice02_decrypt {

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {

        // 1. prepare a secret key
        Scanner sc = new Scanner(System.in);
        System.out.print("개인키 파일: ");
        String privateFName = sc.nextLine();

        PrivateKey privateKey;
        try (FileInputStream fileInputStream = new FileInputStream(privateFName)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

                Object obj = objectInputStream.readObject();
                privateKey = (PrivateKey) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        //2. create a Cipher object
//        Cipher c2 = Cipher.getInstance("RSA");
        Cipher c2 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        c2.init(Cipher.DECRYPT_MODE, privateKey);


        // 3. create a CipherInputStream
        sc = new Scanner(System.in);
        System.out.print("암호데이터 파일: ");
        String encryptFName = sc.nextLine();


        try (FileInputStream fileInputStream = new FileInputStream(encryptFName);
             CipherInputStream cipherInputStream = new CipherInputStream(fileInputStream, c2);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(cipherInputStream))) {

            // 4. read data from cis
            StringBuffer decrypted = new StringBuffer();

            String line = bufferedReader.readLine();
            while (line != null) {
                decrypted.append(line + " ");
                line = bufferedReader.readLine();
            }

            // 5. decrypted output print
            System.out.println("복호화 결과: " + decrypted);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
