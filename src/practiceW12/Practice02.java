package practiceW12;

import practiceW12.Game;
import practiceW12.Score;

import java.util.Scanner;

public class Practice02 {

    public static void main(String[] args) throws InvalidInputException {
        Scanner s = new Scanner(System.in);

        Game comInput = Game.randomGame();
        String com = decode(comInput);
        System.out.print("컴퓨터의 생성 : " + com);
        System.out.println();

        String yourInput = getInput(s);
        Game user = encode(yourInput);
        // 3. getInput() 메소드의 반환 값에 대한 post-condition 으로 체크
        assert(user == Game.ROCK || user == Game.PAPER || user == Game.SCISSORS) : "잘못된 입력입니다.";

        Score rslt = whoswin(user, comInput);
        if (rslt == Score.WIN) {
            System.out.println("당신이 이겼습니다.");
        } else if (rslt == Score.EQUAL) {
            System.out.println("비겼습니다.");
        } else if (rslt == Score.LOSE) {
            System.out.println("컴퓨터가 이겼습니다.");
        }

        s.close();
    }

    private static String getInput(Scanner s) throws InvalidInputException {
        System.out.print("당신의 입력 : ");
        String yourInput = s.next();
        Game user = encode(yourInput);

        // 2. getInput() 메소드에서 반환 직전에 post-condition 으로 체크
//        assert(user == Game.ROCK || user == Game.PAPER || user == Game.SCISSORS) : "잘못된 입력입니다.";
        return yourInput;
    }

    static String decode (Game game) {
        if (game == Game.ROCK) {
            return "바위";
        } else if (game == Game.PAPER) {
            return "보";
        } else if (game == Game.SCISSORS) {
            return "가위";
        }
        return null;
    }

    static Game encode (String str) {
        if (str.equals("가위"))
            return Game.SCISSORS;
        else if (str.equals("바위"))
            return Game.ROCK;
        else if (str.equals("보"))
            return Game.PAPER;
        return null;
    }

    private static Score whoswin(Game user, Game com) {
        // 1. pre-condition 으로 체크
//        assert(user == Game.ROCK || user == Game.PAPER || user == Game.SCISSORS) : "잘못된 입력입니다.";

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

    private static class InvalidInputException extends Exception {
        public InvalidInputException() {
        }

        public InvalidInputException(String message) {
            super(message);
        }
    }
}
