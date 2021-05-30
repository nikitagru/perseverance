import exceptions.IllegalSymbolException;
import exceptions.NoGoalPositionException;
import exceptions.NoStartPositionException;
import org.junit.Test;
import routeFinder.RouteFinderImpl;

import static org.junit.jupiter.api.Assertions.*;

public class RoutesTests {
    private RouteFinderImpl finder = new RouteFinderImpl();

    @Test
    public void correctFirst() throws NoStartPositionException, IllegalSymbolException, NoGoalPositionException {
        char[][] map = new char[][] {
                {'.', '.' , '.' , '@', '.'},
                {'.', '#' , '#' , '#', '#'},
                {'.', '.' , '.' , '.', '.'},
                {'#', '#' , '#' , '#', '.'},
                {'.', 'X' , '.' , '.', '.'}
        };

        char[][] routedMap = finder.findRoute(map);
        assertNotNull(routedMap);
    }

    @Test
    public void correctSecond() throws NoStartPositionException, IllegalSymbolException, NoGoalPositionException {
        char[][] map = new char[][] {
                {'.', '.' , '.' , '.', '@'},
                {'#', '.' , '#' , '#', '#'},
                {'.', '.' , '.' , '.', '.'},
                {'.', '.' , '.' , '.', 'X'},
                {'.', '.' , '.' , '.', '.'}
        };

        char[][] routedMap = finder.findRoute(map);
        assertNotNull(routedMap);
    }

    @Test
    public void unreachableGoal() throws NoStartPositionException, IllegalSymbolException, NoGoalPositionException {
        char[][] map = new char[][] {
                {'.', '.' , '.' , 'X', '.'},
                {'#', '#' , '#' , '#', '#'},
                {'.', '.' , '.' , '.', '.'},
                {'.', '@' , '.' , '.', '.'},
                {'.', '.' , '.' , '.', '.'}
        };

        char[][] routedMap = finder.findRoute(map);
        assertNull(routedMap);
    }

    @Test
    public void illegalSymbol() {
        char[][] map = new char[][] {
                {'.', '.' , '1' , '@', '.'},
                {'#', '.' , '#' , '#', '#'},
                {'.', '.' , '.' , '.', '.'},
                {'.', '.' , '.' , '.', 'X'},
                {'.', '.' , '.' , '.', '.'}
        };

        assertThrows(IllegalSymbolException.class, () -> finder.findRoute(map));
    }

    @Test
    public void noGoalPosition() {
        char[][] map = new char[][] {
                {'.', '.' , '.' , '@', '.'},
                {'#', '.' , '#' , '#', '#'},
                {'.', '.' , '.' , '.', '.'},
                {'.', '.' , '.' , '.', '.'},
                {'.', '.' , '.' , '.', '.'}
        };

        assertThrows(NoGoalPositionException.class, () -> finder.findRoute(map));
    }

    @Test
    public void noStartPosition() {
        char[][] map = new char[][] {
                {'.', '.' , '.' , '.', '.'},
                {'#', '.' , '#' , '#', '#'},
                {'.', '.' , '.' , '.', '.'},
                {'.', '.' , '.' , '.', 'X'},
                {'.', '.' , '.' , '.', '.'}
        };

        assertThrows(NoStartPositionException.class, () -> finder.findRoute(map));
    }

    @Test
    public void bigCorrectMap() throws NoStartPositionException, IllegalSymbolException, NoGoalPositionException {
        char[][] map = new char[][] {
                {'.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '@', '.', '.' , '.' , '.', '.'},
                {'.', '#' , '#' , '#', '#', '#', '#' , '#' , '#', '#', '#', '#' , '#' , '#', '#', '#', '#' , '#' , '#', '.'},
                {'.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.'},
                {'.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.'},
                {'.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.'},
                {'.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '#', '.'},
                {'.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '#', '.'},
                {'.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '#', '.'},
                {'.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '#', '.'},
                {'.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '#', '.'},
                {'.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '#', '.'},
                {'.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '#', '.'},
                {'.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '#', '.'},
                {'.', '.' , '.' , '.', '#', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '#', '.'},
                {'.', '.' , '.' , '.', '#', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '#', '.'},
                {'.', '.' , '.' , '.', '#', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '#', '.'},
                {'.', '.' , '.' , '.', '#', '#', '#' , '#' , '#', '#', '#', '#' , '#' , '#', '#', '#', '#' , '#' , '#', '.'},
                {'.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.'},
                {'.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.'},
                {'.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', '.', '.', '.' , '.' , '.', 'X', '.', '.' , '.' , '.', '.'}
        };

        char[][] routedMap = finder.findRoute(map);

        assertNotNull(routedMap);
    }

    @Test
    public void smallMap() throws NoStartPositionException, IllegalSymbolException, NoGoalPositionException {
        char[][] map = new char[][] {
                {'@'},
                {'X'}
        };
        char[][] routedMap = finder.findRoute(map);

        assertNotNull(routedMap);

    }
}
