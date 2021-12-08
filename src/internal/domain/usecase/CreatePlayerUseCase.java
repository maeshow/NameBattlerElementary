package internal.domain.usecase;

import internal.domain.entity.Player;

public class CreatePlayerUseCase {
    public Player invoke(int id, String name, int hitPoint) {
        return new Player(id, name, hitPoint);
    }
}
