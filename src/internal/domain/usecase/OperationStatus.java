package internal.domain.usecase;

import java.util.Random;

import internal.domain.entity.Player;
import internal.domain.entity.Status;

public class OperationStatus {
    private static final Random RANDOM = new Random();

    private static int DEFAULT_BOUND = 100;
    private static int CRITICAL_RANGE_START = 0;
    private static int CRITICAL_RANGE_END = 10;

    public void removeHitPoint(Player player, int damage) {
        Status status = player.getStatus();
        int newHitPoint = status.getHitPoint() - damage;
        status.setHitPoint(newHitPoint);
    }

    public int getAttackDamage(int strength, int defense, int luck) {
        int result = strength - defense;
        if (isCritical(luck)) {
            result = strength;
        }
        return Math.max(result, 0);
    }

    public boolean isCritical(int luck) {
        int bound = DEFAULT_BOUND - luck;
        int result = RANDOM.nextInt(Math.max(bound, 0));
        if (CRITICAL_RANGE_START <= result && result < CRITICAL_RANGE_END) {
            return true;
        }
        return false;
    }
}
