package routeFinder;

import org.javatuples.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PairPriorityQueue {

    private PriorityQueue<Pair<Pair<Integer, Integer>, Integer>> queue;

    public PairPriorityQueue() {
        this.queue = new PriorityQueue<>(Comparator.comparingInt(Pair::getValue1));
    }

    public void add(Pair<Pair<Integer, Integer>, Integer> value) {
        queue.add(value);
    }

    public Pair<Pair<Integer, Integer>, Integer> poll() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
