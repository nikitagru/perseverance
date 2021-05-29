import org.javatuples.Pair;

import java.util.HashMap;

import static java.lang.Math.abs;

public class RouteFinderImpl implements RouteFinder {
    private HashMap<Pair<Integer, Integer>,Pair<Integer, Integer>> cameFrom;
    private HashMap<Pair<Integer, Integer>, Integer> costToPoint;

    public char[][] findRoute(char[][] map) {
        Map marsMap = new Map(map);
        cameFrom = new HashMap<>();
        costToPoint = new HashMap<>();

        PairPriorityQueue frontier = new PairPriorityQueue();
        frontier.add(new Pair<>(marsMap.getStartPosition(), 0));
        cameFrom.put(marsMap.getStartPosition(), null);
        costToPoint.put(marsMap.getStartPosition(), 0);

        while(!frontier.isEmpty()) {
            Pair<Pair<Integer, Integer>, Integer> current = frontier.poll();

            if (current.getValue0() == marsMap.getGoalPosition()) {
                break;
            }

            for (Pair<Integer, Integer> neighbor : marsMap.getNeighbors(current.getValue0())) {
                int newCost = costToPoint.get(current.getValue0())
                        + marsMap.getMap()[neighbor.getValue0()][neighbor.getValue1()];

                if (!costToPoint.containsKey(neighbor) || newCost < costToPoint.get(neighbor)) {
                    costToPoint.put(neighbor, newCost);
                    int priority = newCost + distanceToGoal(marsMap.getGoalPosition(), neighbor);
                    frontier.add(new Pair<>(neighbor, newCost + priority));
                    cameFrom.put(neighbor, current.getValue0());
                }
            }
        }

        System.out.println();

        return null;
    }

    private int distanceToGoal(Pair<Integer, Integer> goal, Pair<Integer, Integer> nextPoint) {
        return (abs(goal.getValue0() - nextPoint.getValue0()) + abs(goal.getValue1() - nextPoint.getValue1()));
    }
}
