import exceptions.IllegalSymbolException;
import exceptions.NoGoalPositionException;
import exceptions.NoStartPositionException;
import routeFinder.RouteFinderImpl;

public class Main {
    public static void main(String[] args) throws NoStartPositionException, NoGoalPositionException, IllegalSymbolException {
        char[][] map = new char[][] {
                {'.', '.' , '1' , '@', '.'},
                {'#', '.' , '#' , '#', '#'},
                {'.', '.' , '.' , '.', '.'},
                {'.', '.' , '.' , '.', 'X'},
                {'.', '.' , '.' , '.', '.'}
        };

        RouteFinderImpl routeFinder = new RouteFinderImpl();
        routeFinder.findRoute(map);
    }
}
