import exceptions.IllegalSymbolException;
import exceptions.NoGoalPositionException;
import exceptions.NoStartPositionException;
import routeFinder.RouteFinderImpl;

public class Main {
    public static void main(String[] args) throws NoStartPositionException, NoGoalPositionException, IllegalSymbolException {
        char[][] map = new char[][] {
                {'.', '.' , '.' , '@', '.'},
                {'#', '.' , '#' , '#', '#'},
                {'.', '.' , '.' , '.', '.'},
                {'.', '.' , '.' , '.', 'X'},
                {'.', '.' , '.' , '.', '.'}
        };

        RouteFinderImpl routeFinder = new RouteFinderImpl();
        char[][] finalRoute = routeFinder.findRoute(map);
    }
}
