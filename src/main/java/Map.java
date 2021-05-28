import org.javatuples.Pair;

public class Map {
    private final int[][] map;
    private Pair<Integer, Integer> startPosition;

    public Map(char[][] map) {
        this.map = new int[map.length][map[0].length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (map[i][j]) {
                    case '.':
                        this.map[i][j] = 0;
                        break;
                    case '@':
                        this.map[i][j] = 1;
                        this.startPosition = new Pair(i, j);
                        break;
                    case '#':
                        this.map[i][j] = 2;
                        break;
                    case 'X':
                        this.map[i][j] = 3;
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
}
