package internal.domain.usecase;

public class GetIdForDisplayUseCase {
    private static String ID_PREFIX = "プレイヤー";

    public String invoke(int index) {
        StringBuilder builder = new StringBuilder();
        builder.append(ID_PREFIX);
        builder.append(index);
        return builder.toString();
    }
}
