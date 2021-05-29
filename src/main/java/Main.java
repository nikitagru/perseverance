public class Main {
    public static void main(String[] args) {
        char[][] map = new char[][] {
                {'.', '.' , '.' , '@', '.'},
                {'.', '#' , '#' , '#', '#'},
                {'.', '.' , '.' , '.', '.'},
                {'#', '#' , '#' , '.', '.'},
                {'.', '.' , '.' , '.', '.'},
                {'.', '.' , '.' , '.', '.'},
                {'.', '#' , '#' , '#', '#'},
                {'.', 'X' , '.' , '.', '.'}
        };

        RouteFinderImpl routeFinder = new RouteFinderImpl();
        routeFinder.findRoute(map);
    }
}
