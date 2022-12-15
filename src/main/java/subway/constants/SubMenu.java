package subway.constants;

import static subway.util.ErrorCode.NOT_VALID_MENU;

import java.util.Arrays;

public enum SubMenu {
    MINIMUM_DISTANCE("1"),
    MINIMUM_TIME("2"),
    BACK_TO_MAIN("B");

    private final String symbol;

    SubMenu(String symbol) {
        this.symbol = symbol;
    }

    public static SubMenu getFunctionBy(String input) {
        return Arrays.stream(values())
                .filter(function -> function.symbol.equals(input))
                .findFirst()
                .orElseThrow(NOT_VALID_MENU::throwError);
    }
}
