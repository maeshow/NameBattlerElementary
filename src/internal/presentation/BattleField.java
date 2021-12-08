package internal.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import internal.domain.entity.Player;
import internal.domain.entity.Status;
import internal.domain.usecase.GenerateHashDigest;
import internal.domain.usecase.OperationPlayer;
import internal.domain.usecase.OperationStatus;

public class BattleField {
    private static final Scanner STDIN = new Scanner(System.in);

    private static int FIRST_PLAYER_INDEX = 1;
    private static int PLAYER_NUMBER = 2;
    private static int MAX_HP = 9;
    private static int MAX_STR = 5;
    private static int HP_INDEX = 0;
    private static int STR_INDEX = 1;
    private static int NOTHING_HP = 0;
    private static int PREVENT_ZERO = 1;

    private final OperationPlayer operationPlayer;
    private final OperationStatus operationStatus;
    private final GenerateHashDigest generateHashDigest;

    public BattleField() {
        this.operationPlayer = new OperationPlayer();
        this.operationStatus = new OperationStatus();
        this.generateHashDigest = new GenerateHashDigest();
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
        String id = operationPlayer.getDisplayId(index);
        Messages.showFormattedMessage(Messages.WAITING_INPUT_NAME, id);
        String name = STDIN.next();
        int hitPoint = generateHashDigest.generateNumber(name, HP_INDEX) % MAX_HP + PREVENT_ZERO;
        int strength = generateHashDigest.generateNumber(name, STR_INDEX) % MAX_STR + PREVENT_ZERO;
        return new Player(index, name, new Status(hitPoint, strength));
    }

    private void startTurn(List<Player> players) {
        Messages.showNewLine();
        Messages.showWithNewLine(Messages.BATTLE_START);
        while (!isEndGame(players)) {
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
        int attackDamage = operationStatus.getAttackDamage(myself.getStatus().getStrength());
        Messages.showFormattedMessage(Messages.DAMAGE, enemy.getName(), attackDamage);
        operationStatus.removeHitPoint(enemy, attackDamage);
    }

    private boolean isOutOfPower(Player player) {
        return player.getStatus().getHitPoint() <= NOTHING_HP;
    }

    private boolean isEndGame(List<Player> players) {
        boolean isOutOfPower = false;
        for (Player player : players) {
            isOutOfPower = isOutOfPower || player.getStatus().getHitPoint() <= NOTHING_HP;
        }
        return isOutOfPower;
    }

    private void showCurrentInfo(List<Player> players) {
        Messages.showWithNewLine(Messages.NEXT_TURN);
        for (Player player : players) {
            String id = operationPlayer.getDisplayId(player.getId());
            Messages.showFormattedMessage(Messages.CURRENT_INFO, id, player.getName(),
                    player.getStatus().getHitPoint());
        }
        Messages.showNewLine();
        Messages.showWithNewLine(Messages.LINE);
        Messages.showNewLine();
    }
}
