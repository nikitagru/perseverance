package exceptions;

/***
 * Ошибка отсутсвия финальной точки
 */
public class NoGoalPositionException extends Exception{
    public NoGoalPositionException() {
        super("There is no goal position in map");
    }
}
