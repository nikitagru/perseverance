import org.javatuples.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;

import static java.lang.Math.*;

public class PairPriorityQueue {

    private PriorityQueue<Pair<Pair<Integer, Integer>, Integer>> queue;

    public PairPriorityQueue() {
        this.queue = new PriorityQueue<>(new Comparator<Pair<Pair<Integer, Integer>, Integer>>() {
            @Override
            public int compare(Pair<Pair<Integer, Integer>, Integer> o1, Pair<Pair<Integer, Integer>, Integer> o2) {
                return o1.getValue1() - o2.getValue1();
            }
        });
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
