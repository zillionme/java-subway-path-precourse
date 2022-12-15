package subway.util;

import static subway.util.ErrorCode.NOT_CONNECTED_SECTION;
import static subway.util.ErrorCode.NOT_VALID_SECTION;
import static subway.util.ErrorCode.NOT_VALID_STATION;

import java.util.List;
import subway.domain.LineRepository;

public class Validator {

    public static void validateStation(String input) {
        if(!input.endsWith("역")) {
            throw NOT_VALID_STATION.throwError();
        }
    }

    public static void validateStationToStartAndArrive(List<String> stations) {
        Validator.validateSections(stations); //일치하는지
        Validator.validateConnectedSections(stations); // 연결되어 있는지

    }
    public static void validateConnectedSections(List<String> pathOfShortestDistance) {
        if(pathOfShortestDistance.size()==0) {
            throw NOT_CONNECTED_SECTION.throwError();
        }
    }

    public static void validateSections(List<String> stations) { //
        if(stations.get(0).equals(stations.get(1))) {
            throw NOT_VALID_SECTION.throwError();
        }
    }
}
