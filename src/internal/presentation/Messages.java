package internal.presentation;

public class Messages {
    public static final String WAITING_INPUT_NAME = "%sの名前を入力してください：";

    public static final String BATTLE_START = "=== バトル開始 ===";
    public static final String NEXT_TURN = "- 次のターン -";
    public static final String LINE = "---";

    public static final String ATTACK = "%sの攻撃！%n";
    public static final String DAMAGE = "%sに %d のダメージ！%n";
    public static final String NO_DAMAGE = "攻撃がミス";
    public static final String OUT_OF_POWER = "%sは力尽きた...%n";

    public static final String CURRENT_INFO = "%s：%s(HP %d)%n";
    public static final String WIN = "%sの勝利！！%n";

    public static final String INITIAL_INFO = "%s: HP(%d), STR(%d), DEF(%d), LUCK(%d)%n";

    public static void showWithNewLine(String message) {
        System.out.println(message);
    }

    public static void showWithoutNewLine(String message) {
        System.out.printf(message);
    }

    public static void showNewLine() {
        System.out.println();
    }

    public static void showFormattedMessage(String message, int a) {
        System.out.format(message, a);
    }

    public static void showFormattedMessage(String message, String a) {
        System.out.format(message, a);
    }

    public static void showFormattedMessage(String message, String a, int b) {
        System.out.format(message, a, b);
    }

    public static void showFormattedMessage(String message, String a, String b, int c) {
        System.out.format(message, a, b, c);
    }

    public static void showFormattedMessage(String message, String a, int b, int c, int d, int e) {
        System.out.format(message, a, b, c, d, e);
    }
}
