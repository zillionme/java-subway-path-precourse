package subway.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;
    private static final String MESSAGE_FUNCTION = "\n## 원하는 기능을 선택하세요.";

    private static final String MESSAGE_STATION = "\n## %s역을 입력하세요.";

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readFunction() {
        System.out.println(MESSAGE_FUNCTION);
        return scanner.nextLine();
    }

    public String readStation(String msg) {
        System.out.printf((MESSAGE_STATION) + "%n", msg);
        return scanner.nextLine();
    }
}
