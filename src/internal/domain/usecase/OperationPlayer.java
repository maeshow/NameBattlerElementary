package internal.domain.usecase;

public class OperationPlayer {
    private static String ID_PREFIX = "プレイヤー";

    public String getDisplayId(int index) {
        StringBuilder builder = new StringBuilder();
        builder.append(ID_PREFIX);
        builder.append(index);
        return builder.toString();
    }
}
