package routeFinder;

import exceptions.IllegalSymbolException;
import exceptions.NoGoalPositionException;
import exceptions.NoStartPositionException;

/***
 * Интерфейс поиска маршрута
 */
public interface RouteFinder {
    /***
     * Поиск кратчайшего маршрута между двумя точками
     * @param map карта
     * @return карта с построенным маршрутом
     */
    char[][] findRoute(char[][] map) throws NoStartPositionException, NoGoalPositionException, IllegalSymbolException;
}
