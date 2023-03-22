package practiceW03;

public enum Score {
    WIN, LOSE, EQUAL, ERROR;

    public static String print(Score score) {
        if (score == WIN) {
            return "당신이 이겼습니다.";
        } else if (score == EQUAL) {
            return "비겼습니다.";
        } else if (score == LOSE) {
            return "컴퓨터가 이겼습니다.";
        }
        return "ERROR"; // 확인

    }
}
