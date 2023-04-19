package practiceW05;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Practice02_save {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        byte[] obj_bytes;

        Scanner sc = new Scanner(System.in);
        System.out.print("입력 파일 이름 : ");
        String read_fname = sc.nextLine();

        try (FileInputStream fis = new FileInputStream(read_fname)) {
            obj_bytes = fis.readAllBytes();

            // 해시값 계산
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(obj_bytes);
            byte [] messageDigestSHA_1 = messageDigest.digest();

            System.out.println("계산된 해시값");
            for (byte bytes : messageDigestSHA_1) {
                System.out.print(String.format("%02x", bytes) + "\t");
            }
            System.out.println();

            // 해시 결과 파일에 저장
            System.out.print("해시값을 저장할 파일 이름: ");
            String write_fname = sc.nextLine();

            try (PrintWriter out = new PrintWriter(new FileWriter(write_fname))) {
                for (byte bytes : messageDigestSHA_1) {
                    out.print(String.format("%02x", bytes) + "\t");
                }
                out.println();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
