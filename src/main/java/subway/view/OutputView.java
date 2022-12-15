package subway.view;

import java.util.List;

public class OutputView {
    private static final String MAIN_MENU = "## 메인 화면\n" +
            "1. 경로 조회\n" +
            "Q. 종료";
    private static final String OPTION_MENU = "\n## 경로 기준\n" +
            "1. 최단 거리\n" +
            "2. 최소 시간\n" +
            "B. 돌아가기";
    private static final String BEGIN = "[INFO] ";
    private static final String DELIMITER = "[INFO] ---";
    private static final String TOTAL_DISTANCE = "[INFO] 총 거리: %skm";
    private static final String TOTAL_TIME = "[INFO] 총 소요 시간: %s분";



    public void printMainMenu() {
        System.out.println(MAIN_MENU);
    }

    public void printOptionMenu() {
        System.out.println(OPTION_MENU);
    }

    public void printError(String msg) {
        System.out.println(msg);
    }

    public void printResult(List<Double> shortestDistanceAndTime, List<String> pathOfShortestDistance) {
        System.out.println(DELIMITER);
        System.out.printf((TOTAL_DISTANCE)+"\n", shortestDistanceAndTime.get(0));
        System.out.printf((TOTAL_TIME)+"\n", shortestDistanceAndTime.get(1));
        printStations(pathOfShortestDistance);

    }
    public void printStations(List<String> pathOfShortestDistance) {
        System.out.println(DELIMITER);
        for(String path : pathOfShortestDistance) {
            System.out.println(BEGIN+path);
        }
    }
}
