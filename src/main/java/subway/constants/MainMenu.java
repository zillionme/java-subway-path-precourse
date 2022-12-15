package subway.constants;

import static subway.util.ErrorCode.NOT_VALID_MENU;

import java.util.Arrays;

public enum MainMenu {
    LOOKUP("1"),
    END("Q");

    private final String symbol;

    MainMenu(String symbol) {
        this.symbol = symbol;
    }

    public static MainMenu getFunctionBy(String input) {
        return Arrays.stream(values())
                .filter(function -> function.symbol.equals(input))
                .findFirst()
                .orElseThrow(NOT_VALID_MENU::throwError);
    }
}
