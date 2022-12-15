package subway;

import subway.constants.MainMenu;
import subway.domain.StationGraph;
import subway.view.InputView;
import subway.view.OutputView;

public class Controller {
    private final StationGraph stationGraph = new StationGraph();
    private final InputView inputView;
    private final OutputView outputView;
    private PathController pathController;
    private boolean isRunning = true;

    public Controller(InputView inputView, OutputView outputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void generate() {
        stationGraph.initiateStationGraph();
        pathController = new PathController(inputView, outputView, stationGraph);
        while(isRunning) {
            outputView.printMainMenu();
            MainMenu function = getFunction();
            executeFunction(function);
        }

    }

    public MainMenu getFunction() {
        try{
            String input = inputView.readFunction();
            return MainMenu.getFunctionBy(input);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
        return getFunction();
    }

    public void executeFunction(MainMenu function) {
        if(function.equals(MainMenu.LOOKUP)){
            pathController.generate();
        }
        if(function.equals(MainMenu.END)) {
            isRunning = false;
        }
    }

}
