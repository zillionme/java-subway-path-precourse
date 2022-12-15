package subway;

import java.util.Scanner;
import subway.view.InputView;
import subway.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller(new InputView(scanner), new OutputView());
        controller.generate();
    }
}
