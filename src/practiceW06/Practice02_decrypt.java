package practiceW06;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Practice02_decrypt {

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {

        Scanner sc = new Scanner(System.in);
        System.out.print("비밀키 파일: ");
        String fName = sc.nextLine();

        // 1. prepare a secret key
        Key secretKey;
        try (FileInputStream fileInputStream = new FileInputStream(fName)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                Object obj = objectInputStream.readObject();
                secretKey = (Key) obj;

            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        //2. create a Cipher object
        Cipher c2 = Cipher.getInstance("AES");
        c2.init(Cipher.DECRYPT_MODE, secretKey);


        // 4. create a CipherInputStream
        sc = new Scanner(System.in);
        System.out.print("암호화 파일: ");
        String encryptFName = sc.nextLine();
        try (FileInputStream fileInputStream = new FileInputStream(encryptFName);
             CipherInputStream cipherInputStream = new CipherInputStream(fileInputStream, c2);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(cipherInputStream))) {

            // 5. read data from cis
            StringBuffer decrypted = new StringBuffer();

            String line = bufferedReader.readLine();
            while (line != null) {
                decrypted.append(line + " ");
                line = bufferedReader.readLine();
            }

            // 6. decrypted output print
            System.out.println("복호화 결과: " + decrypted);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
