import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Map {
    private final int[][] map;
    private Pair<Integer, Integer> startPosition;
    private Pair<Integer, Integer> goalPosition;

    public Map(char[][] map) {
        this.map = new int[map.length][map[0].length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (map[i][j]) {
                    case '.':
                        this.map[i][j] = 1;
                        break;
                    case '@':
                        this.map[i][j] = 2;
                        this.startPosition = new Pair(i, j);
                        break;
                    case '#':
                        this.map[i][j] = 3;
                        break;
                    case 'X':
                        this.map[i][j] = 0;
                        this.goalPosition = new Pair<>(i, j);
                        break;
                }
            }
        }
    }

    public int[][] getMap() {
        return map;
    }

    public Pair<Integer, Integer> getStartPosition() {
        return startPosition;
    }

    public Pair<Integer, Integer> getGoalPosition() {
        return goalPosition;
    }

    public List<Pair<Integer, Integer>> getNeighbors(Pair<Integer, Integer> point) {
        List<Pair<Integer, Integer>> neighbors = new ArrayList<>();

        if (point.getValue0() + 1 <= map.length - 1 && map[point.getValue0() + 1][point.getValue1()] != 3) {
            neighbors.add(new Pair(point.getValue0() + 1, point.getValue1()));
        }

        if (point.getValue1() + 1 <= map[0].length - 1 && map[point.getValue0()][point.getValue1() + 1] != 3) {
            neighbors.add(new Pair(point.getValue0(), point.getValue1() + 1));
        }

        if (point.getValue0() - 1 >= 0 && map[point.getValue0() - 1][point.getValue1()] != 3) {
            neighbors.add(new Pair(point.getValue0() - 1, point.getValue1()));
        }

        if (point.getValue1() - 1 >= 0 && map[point.getValue0()][point.getValue1() - 1] != 3) {
            neighbors.add(new Pair(point.getValue0(), point.getValue1() - 1));
        }

        return neighbors;
    }

    public int distanceToGoal(Pair<Integer, Integer> nextPoint) {
        return (abs(goalPosition.getValue0() - nextPoint.getValue0()) + abs(goalPosition.getValue1() - nextPoint.getValue1()));
    }
}
