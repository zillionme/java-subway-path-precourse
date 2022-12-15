package subway.domain;

import java.util.List;

public enum Section {
//    2호선: 교대역 - ( 2km / 3분 ) - 강남역 - ( 2km / 3분 ) - 역삼역
//   - 3호선: 교대역 - ( 3km / 2분 ) - 남부터미널역 - ( 6km / 5분 ) - 양재역 - ( 1km / 1분 ) - 매봉역
//   - 신분당선: 강남역 - ( 2km / 8분 ) - 양재역 - ( 10km / 3분 ) - 양재시민의숲역

    SECTION1(List.of("교대역","강남역"),2,3),
    SECTION2(List.of("강남역","역삼역"),2,3),
    SECTION3(List.of("교대역","남부터미널역"),3,2),
    SECTION4(List.of("남부터미널역","양재역"),6,5),
    SECTION5(List.of("양재역","매봉역"),1,1),
    SECTION6(List.of("강남역", "양재역"),2,8),
    SECTION7(List.of("양재역","양재시민의숲역"),10,3);

    private final List<String> stationsInSection;
    private final int distance;
    private final int time;

    Section(List<String> stationsInSection, int distance, int time) {
        this.stationsInSection = stationsInSection;
        this.distance = distance;
        this.time = time;
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }

    public List<String> getStationsInSection() {
        return stationsInSection;
    }
}
