package subway.domain;

import java.util.Arrays;
import java.util.List;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class StationGraph {

    private WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph =
            new WeightedMultigraph(DefaultWeightedEdge.class);
    private WeightedMultigraph<String, DefaultWeightedEdge> timeGraph =
            new WeightedMultigraph(DefaultWeightedEdge.class);
    private DijkstraShortestPath dijkstraDistancePath;
    private DijkstraShortestPath dijkstraTimePath;

    //초기화
    public void initiateStationGraph() {
        for (Section section : Section.values()) {
            List<String> stationsInSection = section.getStationsInSection();
            String stationA = stationsInSection.get(0);
            String stationB = stationsInSection.get(1);

            int distance = section.getDistance();
            int time = section.getTime();

            addSectionToDistanceGraph(stationA, stationB, distance);
            addSectionToTimeGraph(stationA, stationB, time);
        }
        dijkstraDistancePath = new DijkstraShortestPath(distanceGraph);
        dijkstraTimePath = new DijkstraShortestPath(timeGraph);
    }

    public void addSectionToDistanceGraph(String stationA, String stationB, int distance) {
        distanceGraph.setEdgeWeight(distanceGraph.addEdge(stationA, stationB), distance);
    }

    public void addSectionToTimeGraph(String stationA, String stationB, int time) {
        timeGraph.setEdgeWeight(timeGraph.addEdge(stationA, stationB), time);
    }

    public GraphPath getShortestDistance(String start, String end) {
        return dijkstraDistancePath.getPath(start, end);
    }

    public List<String> getPathOfShortestDistance(String start, String end) {
        return dijkstraDistancePath.getPath(start, end).getVertexList();
    }

    //GraphPath path를 컨트롤러에서하기.
    public List<Double> getTotalDistanceAndTimeOfShortestDistance(String start, String end) {
        GraphPath path = getShortestDistance(start, end);
        List<String> pathByDistance = path.getVertexList();

        //최단 거리 - 총거리
        double totalDistance = path.getWeight();
        //최단거리 - 총시간
        double totalTime = 0;
        for(int i =0; i<pathByDistance.size()-1; i++) {
            DefaultWeightedEdge edge = timeGraph.getEdge(pathByDistance.get(i), pathByDistance.get(i + 1));
            totalTime += timeGraph.getEdgeWeight(edge);
        }

        return List.of(totalDistance, totalTime);
    }

    //최단시간
    public GraphPath getShortestTime(String start, String end) {
        return dijkstraTimePath.getPath(start, end);
    }

    public List<String> getPathOfShortestTime(String start, String end) {
        return dijkstraTimePath.getPath(start, end).getVertexList();
    }

    //GraphPath path를 컨트롤러에서하기.
    public List<Double> getTotalDistanceAndTimeOfShortestTime(String start, String end) {
        GraphPath path = getShortestTime(start, end);
        List<String> pathByTime = path.getVertexList();

        //최소 시간 - 총 시간
        double totalTime = path.getWeight();
        //최소시간- 총 거리
        double totalDistance = 0;
        for(int i =0; i<pathByTime.size()-1; i++) {
            DefaultWeightedEdge edge = distanceGraph.getEdge(pathByTime.get(i), pathByTime.get(i + 1));
            totalTime += distanceGraph.getEdgeWeight(edge);
        }

        return List.of(totalDistance, totalTime);
    }
}
