package internal.domain.usecase;

import java.util.List;

import internal.domain.entity.Player;

public class IsEndGameUseCase {
    private static int NOTHING_HP = 0;

    public boolean invoke(List<Player> players) {
        boolean isOutOfPower = false;
        for (Player player : players) {
            isOutOfPower = isOutOfPower || player.getHitPoint() <= NOTHING_HP;
        }
        return isOutOfPower;
    }
}
