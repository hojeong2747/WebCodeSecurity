package practiceW03;

import java.util.Scanner;

public class Practice02 {
    static final Score[][] scoreBoard = {
            {Score.EQUAL, Score.LOSE, Score.WIN},
            {Score.WIN, Score.EQUAL, Score.LOSE},
            {Score.LOSE, Score.WIN, Score.EQUAL}
    }; // 위치 생각

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("컴퓨터의 입력 : ");
        String comInput = s.next();
        Game com = Game.encode(comInput);

        System.out.println("당신의 입력 : ");
        String yourInput = s.next();
        Game user = Game.encode(yourInput);

        Score rslt = whoswin(user, com);
        System.out.println(Score.print(rslt));

        s.close();
    }

    static Score whoswin(Game user, Game com) {
        return scoreBoard[user.getNum()][com.getNum()];
    }


}
