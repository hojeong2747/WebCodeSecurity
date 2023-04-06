package practiceW05;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Practice01 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String plainTxt = "THIS is a Msg to be digested using SHA_1"; // SHA-1 -> SHA_1 위치 뒤 단어 글자 수정
        byte[] data = plainTxt.getBytes();

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.update(data);
        byte[] messageDigestMD5 = messageDigest.digest();

        System.out.println("Plain text : " + plainTxt);
        System.out.println("length of digest (bytes) : " + messageDigestMD5.length);
        System.out.println("digestedMD5 (hex) : ");
        for (byte bytes : messageDigestMD5) {
            System.out.print(String.format("%02x", bytes) + "\t");
        }
        System.out.println();
    }
}
