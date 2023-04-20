package practiceW06;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Practice02_encrypt {
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


        // 2. prepare data (read file, convert to byte[])
        System.out.print("데이터 파일 이름: ");
        String txtFName = sc.nextLine();

        String plainTxt = "";
        try (FileReader fileReader = new FileReader(txtFName)) {
            sc = new Scanner(new BufferedReader(fileReader));

            while (sc.hasNext()) {
                plainTxt += sc.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        byte[] input = plainTxt.getBytes();
//        System.out.println("plain text: " + plainTxt + "(length " + input.length + ")");


        // 3. create a Cipher object
        Cipher c1 = Cipher.getInstance("AES");
        c1.init(Cipher.ENCRYPT_MODE, secretKey);


        // 4. create a CipherOutputStream
        sc = new Scanner(System.in);
        System.out.print("암호화 출력 파일: ");
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
