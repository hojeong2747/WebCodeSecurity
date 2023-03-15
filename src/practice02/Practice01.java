package practice02;

public class Practice01 {

    public static void main(String[] args) {
        calcSum(10,20);
        calcSum(10,20,30);
        calcSum(10,20,30,40,50);
    }

    public static void calcSum(Integer... integers) { // 타입 주의
        int sum = 0;
        for (Integer i : integers) {
            sum += i;
        }
        System.out.println(sum);
    }

    // 배열인 것처럼 짜는 건 맞는데 그 이름의 배열을 만들어주는 것은 아니다. 인덱스 i 사용할 수 없는 컬렉션이라 생각하기. 인덱스로 돌리는 for 문 거의 사용 안 하고 아마 forEach 쓰게 될 것이다.

}


