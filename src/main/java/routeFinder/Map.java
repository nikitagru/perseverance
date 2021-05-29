package routeFinder;

import exceptions.IllegalSymbolException;
import exceptions.NoGoalPositionException;
import exceptions.NoStartPositionException;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/***
 * Объект карты
 */
public class Map {
    // Карта, представленная в виде чисел(возможно расширение, если добавить на карту покрытия разной степени проходимости)
    private final int[][] map;
    private Pair<Integer, Integer> startPosition;
    private Pair<Integer, Integer> goalPosition;


    public Map(char[][] map) throws NoStartPositionException, NoGoalPositionException, IllegalSymbolException {
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
                    default:
                        throw new IllegalSymbolException(map[i][j]);
                }
            }
        }

        if (startPosition == null) {
            throw new NoStartPositionException();
        }

        if (goalPosition == null) {
            throw new NoGoalPositionException();
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

    /***
     * Получение соседей точки
     * @param point точка, у которой нужно найти соседей
     * @return массив всех соседей
     */
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

    /***
     * Расстояние до финальной точки(Манхэттеновское расстояние)
     * @param point точка, откуда вычисляется расстояние до цели
     * @return расстояние до цели
     */
    public int distanceToGoal(Pair<Integer, Integer> point) {
        return (abs(goalPosition.getValue0() - point.getValue0()) + abs(goalPosition.getValue1() - point.getValue1()));
    }
}
