import org.javatuples.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;

import static java.lang.Math.abs;

public class PairPriorityQueue extends PriorityQueue {

    public static Comparator<Pair<Integer, Integer>> pointComparator = new Comparator<Pair<Integer, Integer>>() {
        public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
            return (abs(o1.getValue0() - o2.getValue0()) + abs(o1.getValue1() - o2.getValue1()));
        }
    };

    public PairPriorityQueue() {
        super(pointComparator);
    }

}
