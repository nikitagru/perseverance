package routeFinder;

import exceptions.IllegalSymbolException;
import exceptions.NoGoalPositionException;
import exceptions.NoStartPositionException;
import org.javatuples.Pair;

import java.util.HashMap;

/***
 * Реализация поиска маршрута
 */
public class RouteFinderImpl implements RouteFinder {
    // Массив предшественников
    private HashMap<Pair<Integer, Integer>,Pair<Integer, Integer>> cameFrom;
    // Стоимость перемещения на точку(возможно расширение до разного типа покрытия)
    private HashMap<Pair<Integer, Integer>, Integer> costToPoint;

    /***
     * Поиск кратчайшего пути (алгоритм А*)
     * @param map исходная карта
     * @return карту с проложенным маршрутом
     * @throws NoStartPositionException
     * @throws NoGoalPositionException
     * @throws IllegalSymbolException
     */
    public char[][] findRoute(char[][] map) throws NoStartPositionException, NoGoalPositionException, IllegalSymbolException {
        // Объект карты
        Map marsMap = new Map(map);
        cameFrom = new HashMap<>();
        costToPoint = new HashMap<>();

        // Очередь границ(соседей) клетки
        // Обертка над стандартной очередью с приоритетами
        PairPriorityQueue frontier = new PairPriorityQueue();
        // Добавление стартовой вершины в очередь
        frontier.add(new Pair<>(marsMap.getStartPosition(), 0));
        // Задание предыдущей вершины для стартовой
        cameFrom.put(marsMap.getStartPosition(), null);
        // Задание стоимости перемещения к стартовой позиции
        costToPoint.put(marsMap.getStartPosition(), 0);

        while(!frontier.isEmpty()) {
            // Извлечение из очереди наиболее предпочтительного элемента
            Pair<Pair<Integer, Integer>, Integer> current = frontier.poll();

            if (current.getValue0() == marsMap.getGoalPosition()) {
                break;
            }

            // Цикл по всем соседям извлеченной вершины
            for (Pair<Integer, Integer> neighbor : marsMap.getNeighbors(current.getValue0())) {
                // Вычисление стоимости перемещение к соседу извлеченной точки
                int newCost = costToPoint.get(current.getValue0())
                        + marsMap.getMap()[neighbor.getValue0()][neighbor.getValue1()];

                if (!costToPoint.containsKey(neighbor) || newCost < costToPoint.get(neighbor)) {
                    costToPoint.put(neighbor, newCost);
                    // Вычисление приоритета точки(стоимость перемещения + расстояние до финальной цели)
                    int priority = newCost + marsMap.distanceToGoal(neighbor);
                    frontier.add(new Pair<>(neighbor, newCost + priority));
                    cameFrom.put(neighbor, current.getValue0());
                }
            }
        }

        return createRoute(marsMap, cameFrom);
    }

    /***
     * Создание маршрута
     * @param map объект исходной карты
     * @param cameFrom словарь предшественников
     * @return карту с проложенным маршрутом, если маршрут существует
     */
    private char[][] createRoute(Map map, HashMap<Pair<Integer, Integer>,Pair<Integer, Integer>> cameFrom) {
        char[][] finalRoute = new char[map.getMap().length][map.getMap()[0].length];
        Pair<Integer, Integer> goal = map.getGoalPosition();

        // Если в словаре предшественников есть точка финальной цели
        if (cameFrom.containsKey(goal)) {
            Pair<Integer, Integer> previous = cameFrom.get(goal);

            // Каждую итерацию происходит отрисовка маршрута на текущей точке
            // и переопределение предыдущей точки
            while (previous != null) {
                finalRoute[previous.getValue0()][previous.getValue1()] = '+';
                previous = cameFrom.get(previous);
            }

            // Заполнение карты всеми остальными символами
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
