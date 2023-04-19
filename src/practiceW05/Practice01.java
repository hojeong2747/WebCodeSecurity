package practiceW05;

import java.security.MessageDigest; // hash function 지원하는 추상 클래스.
import java.security.NoSuchAlgorithmException;

public class Practice01 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String plainTxt = "THIS is a Msg to be digested using SHA_1"; // SHA-1 -> SHA_1 위치 뒤 단어 글자 수정
        byte[] data = plainTxt.getBytes();

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1"); // getInstance : 생성자 호출 안 하고 인스턴스 얻어옴. 인스턴스랑 객체 차이가 뭐지?
        messageDigest.update(data); // update : 해시 값을 구할 값들 넣음. 여러 번 호춣 가능하고, 뒤에 붙게 됨.
        byte[] messageDigestMD5 = messageDigest.digest(); // digest : 입력 값 입력 끝나면, 해시 결과 만듦

        System.out.println("Plain text : " + plainTxt);
        System.out.println("length of digest (bytes) : " + messageDigestMD5.length); // 해시 결과 길이 출력
        System.out.println("digestedMD5 (hex) : ");
        for (byte bytes : messageDigestMD5) { // 해시 결과 바이트 단위 출력
            System.out.print(String.format("%02x", bytes) + "\t");
        }
        System.out.println();
    }
}
