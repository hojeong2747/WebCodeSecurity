package practiceW03;

import java.util.Scanner;

public class Practice01 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("컴퓨터의 입력 : ");
        String comInput = s.next();
        Game com = encode(comInput);

        System.out.print("당신의 입력 : ");
        String yourInput = s.next();
        Game user = encode(yourInput);

        Score rslt = whoswin(user, com);
        if (rslt == Score.WIN) {
            System.out.println("당신이 이겼습니다.");
        } else if (rslt == Score.EQUAL) {
            System.out.println("비겼습니다.");
        } else if (rslt == Score.LOSE) {
            System.out.println("컴퓨터가 이겼습니다.");
        }

        s.close();
    }

    static Game encode (String str) {
        if (str.equals("가위"))
            return Game.SCISSORS;
        else if (str.equals("바위"))
            return Game.ROCK;
        else if (str.equals("보"))
            return Game.PAPER;
        return Game.ERROR;
    }

    static Score whoswin(Game user, Game com) {
        if (user == Game.ROCK) {
            if (com == Game.SCISSORS) {
                return Score.WIN;
            } else if (com == Game.ROCK) {
                return Score.EQUAL;
            } else if (com == Game.PAPER) {
                return Score.LOSE;
            }
        } else if (user == Game.PAPER) {
            if (com == Game.SCISSORS) {
                return Score.LOSE;
            } else if (com == Game.ROCK) {
                return Score.WIN;
            } else if (com == Game.PAPER) {
                return Score.EQUAL;
            }
        } else if (user == Game.SCISSORS) {
            if (com == Game.SCISSORS) {
                return Score.EQUAL;
            } else if (com == Game.ROCK) {
                return Score.LOSE;
            } else if (com == Game.PAPER) {
                return Score.WIN;
            }
        }
        return Score.ERROR;

    }


}
