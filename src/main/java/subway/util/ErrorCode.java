package subway.util;

public enum ErrorCode {
    NOT_VALID_MENU("해당하는 기능이 없습니다"),
    NOT_VALID_STATION("역을 입력하지 않았습니다."),
    NOT_VALID_SECTION("출발역과 도착역이 동일합니다."),
    NOT_CONNECTED_SECTION("역이 연결되어있지 않습니다");

    private static final String ERROR_BEGIN = "[ERROR] ";
    private final String errorMessage;

    ErrorCode(String errorMessage) {
        this.errorMessage = ERROR_BEGIN+errorMessage;
    }

    public IllegalArgumentException throwError() {
        return new IllegalArgumentException(this.errorMessage);
    }
}
