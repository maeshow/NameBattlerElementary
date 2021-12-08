package internal.presentation;

import java.util.ArrayList;
import java.util.List;

import internal.domain.entity.Player;
import internal.domain.usecase.CreatePlayerUseCase;
import internal.domain.usecase.GetAttackDamageUseCase;
import internal.domain.usecase.GetIdForDisplayUseCase;
import internal.domain.usecase.GetPlayerInputUseCase;
import internal.domain.usecase.IsEndGameUseCase;
import internal.domain.usecase.UpdatePlayerHitPointUseCase;

public class NameBattlerController {
    private static int FIRST_PLAYER_INDEX = 1;
    private static int PLAYER_NUMBER = 2;
    private static int HP = 10;
    private static int NOTHING_HP = 0;

    private final GetPlayerInputUseCase getPlayerInputUseCase;
    private final GetIdForDisplayUseCase getIdForDisplayUseCase;
    private final CreatePlayerUseCase createPlayerUseCase;
    private final IsEndGameUseCase isEndGameUseCase;
    private final GetAttackDamageUseCase getAttackDamageUseCase;
    private final UpdatePlayerHitPointUseCase updatePlayerHitPointUseCase;

    public NameBattlerController() {
        this.getPlayerInputUseCase = new GetPlayerInputUseCase();
        this.getIdForDisplayUseCase = new GetIdForDisplayUseCase();
        this.createPlayerUseCase = new CreatePlayerUseCase();
        this.isEndGameUseCase = new IsEndGameUseCase();
        this.getAttackDamageUseCase = new GetAttackDamageUseCase();
        this.updatePlayerHitPointUseCase = new UpdatePlayerHitPointUseCase();
    }

    public void startGame() {
        List<Player> players = registerPlayers();
        startTurn(players);
    }

    private List<Player> registerPlayers() {
        List<Player> players = new ArrayList<>();
        for (int i = FIRST_PLAYER_INDEX; i <= PLAYER_NUMBER; i++) {
            players.add(generatePlayer(i));
        }
        return players;
    }

    private Player generatePlayer(int index) {
        String id = getIdForDisplayUseCase.invoke(index);
        Messages.showFormattedMessage(Messages.WAITING_INPUT_NAME, id);
        String name = getPlayerInputUseCase.invoke();
        return createPlayerUseCase.invoke(index, name, HP);
    }

    private void startTurn(List<Player> players) {
        Messages.showNewLine();
        Messages.showWithNewLine(Messages.BATTLE_START);
        while (!isEndGameUseCase.invoke(players)) {
            for (int i = 0; i < PLAYER_NUMBER; i++) {
                Player firstPlayer = players.get(i);
                Player secondPlayer;
                if (i == PLAYER_NUMBER - 1) {
                    secondPlayer = players.get(0);
                } else {
                    secondPlayer = players.get(i + 1);
                }
                attackEnemy(firstPlayer, secondPlayer);
                if (isOutOfPower(secondPlayer)) {
                    Messages.showFormattedMessage(Messages.OUT_OF_POWER, secondPlayer.getName());
                    Messages.showFormattedMessage(Messages.WIN, firstPlayer.getName());
                    return;
                }
            }
            Messages.showNewLine();
            showCurrentInfo(players);
        }
    }

    private void attackEnemy(Player myself, Player enemy) {
        Messages.showFormattedMessage(Messages.ATTACK, myself.getName());
        int attackDamage = getAttackDamageUseCase.invoke();
        Messages.showFormattedMessage(Messages.DAMAGE, enemy.getName(), attackDamage);
        updatePlayerHitPointUseCase.invoke(enemy, attackDamage);
    }

    private boolean isOutOfPower(Player player) {
        return player.getHitPoint() <= NOTHING_HP;
    }

    private void showCurrentInfo(List<Player> players) {
        Messages.showWithNewLine(Messages.NEXT_TURN);
        for (Player player : players) {
            String id = getIdForDisplayUseCase.invoke(player.getId());
            Messages.showFormattedMessage(Messages.CURRENT_INFO, id, player.getName(), player.getHitPoint());
        }
        Messages.showNewLine();
        Messages.showWithNewLine(Messages.LINE);
        Messages.showNewLine();
    }
}
