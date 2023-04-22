package practiceW05;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Practice02_compare02 {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        byte[] read_hash;
        byte [] messageDigestSHA_1;

        String read_modified_SHA = "";
        String saved_hash = "";

        boolean rslt; // 초기값 자동 false 됨. 중복 정의라고 함.

        Scanner sc = new Scanner(System.in);
        System.out.print("입력 파일 이름 : ");
        String read_FText = sc.nextLine();

        try (FileInputStream fis = new FileInputStream(read_FText)) {
            read_hash = fis.readAllBytes();

            // 해시값 계산
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(read_hash);
            messageDigestSHA_1 = messageDigest.digest();

            System.out.println("계산된 해시값");
            for (byte bytes : messageDigestSHA_1) {
                read_modified_SHA += String.format("%02x", bytes) + "\t";
            }
            System.out.println(read_modified_SHA);
            System.out.println();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.print("저장된 해시값 파일 이름 : ");
        String read_fhash = sc.nextLine();
        try (FileReader fr = new FileReader(read_fhash)) {
            sc = new Scanner(new BufferedReader(fr));
            saved_hash = sc.nextLine();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        rslt = read_modified_SHA.equals(saved_hash);
        System.out.println("비교 결과 : " + rslt);

    }

}
