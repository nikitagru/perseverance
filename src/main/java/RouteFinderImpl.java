import java.util.PriorityQueue;

public class RouteFinderImpl implements RouteFinder {
    public char[][] findRoute(char[][] map) {
        Map marsMap = new Map(map);

        PairPriorityQueue frontier = new PairPriorityQueue();
        frontier.add(marsMap.getStartPosition());

        return null;
    }
}
