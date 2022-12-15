package subway;


import java.util.List;
import subway.constants.SubMenu;
import subway.domain.StationGraph;
import subway.util.Validator;
import subway.view.InputView;
import subway.view.OutputView;

public class PathController {

    private final InputView inputView;
    private final OutputView outputView;
    private final StationGraph stationGraph;

    public PathController(InputView inputView, OutputView outputView, StationGraph stationGraph) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.stationGraph = stationGraph;
    }

    public void generate() {
        try {
            outputView.printOptionMenu();
            SubMenu function = getFunction();
            executeFunction(function);

        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            generate();
        }

    }

    public SubMenu getFunction() {
        try {
            String input = inputView.readFunction();
            return SubMenu.getFunctionBy(input);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
        return getFunction();
    }

    public void executeFunction(SubMenu function) {
        if (function.equals(SubMenu.MINIMUM_DISTANCE)) {
            getShortestPathByDistance();
        }
        if (function.equals(SubMenu.MINIMUM_TIME)) {
            getShortestPathByTime();
        }
    }

    public void getShortestPathByDistance() {
        List<String> stations = getStationToStartAndArrive();
        List<String> pathOfShortestDistance = stationGraph.getPathOfShortestDistance(stations);
        // 연결되어있는지 확인하기
        Validator.validateSections(pathOfShortestDistance);
        List<Double> distanceAndTimeOfShortestDistance =
                stationGraph.getTotalDistanceAndTimeOfShortestDistance(stations);

        outputView.printResult(distanceAndTimeOfShortestDistance, pathOfShortestDistance);
    }


    public void getShortestPathByTime() {
        List<String> stations = getStationToStartAndArrive();
        List<String> pathOfShortestTime = stationGraph.getPathOfShortestTime(stations);
        // 연결되어있는지 확인하기
        Validator.validateSections(pathOfShortestTime);
        List<Double> distanceAndTimeOfShortestTime =
                stationGraph.getTotalDistanceAndTimeOfShortestTime(stations);

        outputView.printResult(distanceAndTimeOfShortestTime, pathOfShortestTime);
    }

    public List<String> getStationToStartAndArrive() {
        String start = getStation("시작");
        String arrive = getStation("도착");

        return List.of(start, arrive);
    }

    public String getStation(String msg) {
        try {
            String input = inputView.readStation(msg);
            Validator.validateStation(input); //-역으로 끝나는지
            return input;
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
        return getStation(msg);

    }


}
