package internal.domain.usecase;

import internal.domain.entity.Player;

public class UpdatePlayerHitPointUseCase {
    public void invoke(Player player, int damage) {
        int newHitPoint = player.getHitPoint() - damage;
        player.setHitPoint(newHitPoint);
    }
}
