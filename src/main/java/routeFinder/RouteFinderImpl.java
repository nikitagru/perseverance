package routeFinder;

import exceptions.IllegalSymbolException;
import exceptions.NoGoalPositionException;
import exceptions.NoStartPositionException;
import org.javatuples.Pair;

import java.util.HashMap;

public class RouteFinderImpl implements RouteFinder {
    private HashMap<Pair<Integer, Integer>,Pair<Integer, Integer>> cameFrom;
    private HashMap<Pair<Integer, Integer>, Integer> costToPoint;

    public char[][] findRoute(char[][] map) throws NoStartPositionException, NoGoalPositionException, IllegalSymbolException {
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
                    int priority = newCost + marsMap.distanceToGoal(neighbor);
                    frontier.add(new Pair<>(neighbor, newCost + priority));
                    cameFrom.put(neighbor, current.getValue0());
                }
            }
        }

        return createRoute(marsMap, cameFrom);
    }


    private char[][] createRoute(Map map, HashMap<Pair<Integer, Integer>,Pair<Integer, Integer>> cameFrom) {
        char[][] finalRoute = new char[map.getMap().length][map.getMap()[0].length];
        Pair<Integer, Integer> goal = map.getGoalPosition();

        if (cameFrom.containsKey(goal)) {
            Pair<Integer, Integer> previous = cameFrom.get(goal);

            while (previous != null) {
                finalRoute[previous.getValue0()][previous.getValue1()] = '+';
                previous = cameFrom.get(previous);
            }

            for (int i = 0; i < finalRoute.length; i++) {
                for (int j = 0; j < finalRoute[0].length; j++) {
                    switch (map.getMap()[i][j]) {
                        case 0:
                            finalRoute[i][j] = 'X';
                            break;
                        case 1:
                            if (finalRoute[i][j] != '+') {
                                finalRoute[i][j] = '.';
                            }
                            break;
                        case 2:
                            finalRoute[i][j] = '@';
                            break;
                        case 3:
                            finalRoute[i][j] = '#';
                            break;
                    }
                }
            }

            return finalRoute;
        }

        return null;
    }
}
