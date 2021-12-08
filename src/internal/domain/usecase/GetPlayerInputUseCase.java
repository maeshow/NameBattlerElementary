package internal.domain.usecase;

import java.util.Scanner;

public class GetPlayerInputUseCase {
    private static final Scanner STDIN = new Scanner(System.in);

    public String invoke() {
        return STDIN.next();
    }
}
