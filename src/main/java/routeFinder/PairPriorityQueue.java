package routeFinder;

import org.javatuples.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;

/***
 * Класс обертка над стандартной очередью с приоритетом
 */
public class PairPriorityQueue {

    // Сама очередь, где хранится вершина и ее приоритет
    // Чем меньше число приоритета, тем наиболее приоритетной считается точка
    private PriorityQueue<Pair<Pair<Integer, Integer>, Integer>> queue;

    public PairPriorityQueue() {
        this.queue = new PriorityQueue<>(Comparator.comparingInt(Pair::getValue1));
    }

    /***
     * Добавление элемента в очередь
     * @param value точка и ее приоритет
     */
    public void add(Pair<Pair<Integer, Integer>, Integer> value) {
        queue.add(value);
    }

    /***
     * Извлечение элемента из очереди
     * @return элемент с наименьшей стоимостью
     */
    public Pair<Pair<Integer, Integer>, Integer> poll() {
        return queue.poll();
    }

    /***
     * Является ли очередь пустой
     * @return булево значение
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
