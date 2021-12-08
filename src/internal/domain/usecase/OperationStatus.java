package internal.domain.usecase;

import java.util.Random;

import internal.domain.entity.Player;
import internal.domain.entity.Status;

public class OperationStatus {
    private static final Random RANDOM = new Random();
    private static final int MAX_DAMAGE = 3;

    public void removeHitPoint(Player player, int damage) {
        Status status = player.getStatus();
        int newHitPoint = status.getHitPoint() - damage;
        status.setHitPoint(newHitPoint);
    }

    public int getAttackDamage() {
        return RANDOM.nextInt(MAX_DAMAGE) + 1;
    }
}
