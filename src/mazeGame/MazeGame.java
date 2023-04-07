package mazeGame;

import java.util.*;

public class MazeGame {
	private static final int WIDTH = 25;
    private static final int HEIGHT = 25;
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int[][] maze = new int[HEIGHT][WIDTH];
    private static int[] player = new int[2];
    
    public static void main(String[] args) {
        generateMaze();
        player[0] = 0;
        player[1] = 0;
        playGame();
    }
    
    private static void generateMaze() {
    	Stack<int[]> stack = new Stack<>();
        Random random = new Random();
        int[] start = {random.nextInt(HEIGHT), random.nextInt(WIDTH)};
        maze[start[0]][start[1]] = 1;
        stack.push(start);
        
        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            for (int[] direction : DIRECTIONS) {
                int[] neighbor = {current[0] + direction[0], current[1] + direction[1]};
                if (isInsideMaze(neighbor) && maze[neighbor[0]][neighbor[1]] == 0) {
                    maze[neighbor[0]][neighbor[1]] = 1;
                    stack.push(neighbor);
                }
            }
        }
    }
    
    private static void playGame() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!isAtEnd()) {
            printMaze();
            System.out.print("Utiliza (A/S/W/D) o Q para finalizar: ");
            input = scanner.nextLine().toLowerCase();
            int[] direction = null;
            switch (input) {
                case "a":
                    direction = DIRECTIONS[3];
                    break;
                case "s":
                    direction = DIRECTIONS[1];
                    break;
                case "d":
                    direction = DIRECTIONS[0];
                    break;
                case "w":
                    direction = DIRECTIONS[2];
                    break;
                case "q": 
                	System.exit(0);
                	break;
                default:
                    System.out.println("Opción inválida!");
                    continue;
            }
            int[] nextCell = {player[0] + direction[0], player[1] + direction[1]};
            if (isInsideMaze(nextCell) && maze[nextCell[0]][nextCell[1]] == 1) {
                player[0] = nextCell[0];
                player[1] = nextCell[1];
            } else {
                System.out.println("Por aquí no puedes seguir");
            }
        }
        System.out.println("Llegaste al final del laberinto!");
    }
    
    private static boolean isInsideMaze(int[] cell) {
        int row = cell[0];
        int col = cell[1];
        return row >= 0 && row < HEIGHT && col >= 0 && col < WIDTH;
    }
    
    private static boolean isAtEnd() {
        return player[0] == HEIGHT - 1 && player[1] == WIDTH - 1;
    }
    
    private static void printMaze() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (i == player[0] && j == player[1]) {
                    System.out.print("D ");
                } else if (maze[i][j] == 1) {
                    System.out.print("  ");
                } 
            }
            System.out.println();
        }
    }
}