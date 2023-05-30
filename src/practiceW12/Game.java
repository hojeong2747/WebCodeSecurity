package practiceW12;

import java.util.Random;

public enum Game {
    ROCK(0), PAPER(1), SCISSORS(2);

    private int num;

    Game(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public static Game encode(String input) {
        if (input.equals("가위"))
            return Game.SCISSORS;
        else if (input.equals("바위"))
            return Game.ROCK;
        else if (input.equals("보"))
            return Game.PAPER;
        return null;
    }

    // enum 랜덤 값 생성 함수
    private static final Random PRNG = new Random();

    public static Game randomGame()  {
        Game[] directions = values();
        return directions[PRNG.nextInt(directions.length)];
    }
}
