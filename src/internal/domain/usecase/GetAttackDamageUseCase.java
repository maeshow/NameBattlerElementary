package internal.domain.usecase;

import java.util.Random;

public class GetAttackDamageUseCase {
    private static final Random RANDOM = new Random();
    private static final int MAX_DAMAGE = 3;

    public int invoke() {
        return RANDOM.nextInt(MAX_DAMAGE) + 1;
    }
}
