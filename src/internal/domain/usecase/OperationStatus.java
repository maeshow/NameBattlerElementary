package internal.domain.usecase;

import java.util.Random;

import internal.domain.entity.Player;
import internal.domain.entity.Status;

public class OperationStatus {
    private static final Random RANDOM = new Random();

    private static int PREVENT_ZERO = 1;

    public void removeHitPoint(Player player, int damage) {
        Status status = player.getStatus();
        int newHitPoint = status.getHitPoint() - damage;
        status.setHitPoint(newHitPoint);
    }

    public int getAttackDamage(int strength) {
        return RANDOM.nextInt(strength) + PREVENT_ZERO;
    }
}
