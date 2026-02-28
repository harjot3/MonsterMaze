package MonsterMaze;
import java.util.Scanner;

/**
 * HW-03 -- FileNewPatent
 * <p>
 * This program takes in input from System.in
 * and performs ten different instructions regarding 
 * string manipulation. After all of these actions are performed
 * something is printed back to the command line. 
 *
 * @author Harjot Singh, 10841
 * @version Feb 2, 2026
 */
public class MonsterMaze {

    // Constants for Prompts
    public static final String WELCOME_MESSAGE = "Welcome to Monster Maze!";
    public static final String INPUT_SIZE = "Enter grid rows and columns:";
    public static final String INPUT_START = "Enter player start row and column:";
    public static final String INPUT_GOAL = "Enter goal row and column:";
    public static final String INPUT_MONSTERS = "Enter number of monsters and their locations (row col):";
    public static final String MOVE = "Enter move (W/A/S/D):";
    public static final String INVALID_MOVE = "Invalid move! You hit a wall, but the monsters keep moving...";
    public static final String GAME_OVER = "A monster caught you! Game Over.";
    public static final String REACH_GOAL = "Congratulations! You reached the goal!";
    public static final String THANKS = "Thank you for playing!";
    
    private int gridRows;
    private int gridCols;
    private int playerRow;
    private int playerCol;  
    private int goalRow;
    private int goalCol;  
    private int[][] monstersArr; 
    
    public MonsterMaze() {}

    public void setGridValues(String gridSize) {
        String[] grid = gridSize.split(",");
        this.gridRows = Integer.parseInt(grid[0]) - 1;
        this.gridCols = Integer.parseInt(grid[1]) - 1;
    }

    public void setPlayerValues(String playerValues) {
        String[] grid = playerValues.split(",");
        this.playerRow = Integer.parseInt(grid[0]);
        this.playerCol = Integer.parseInt(grid[1]);
    }

    public void setGoalValues(String goalValues) {
        String[] grid = goalValues.split(",");
        this.goalRow = Integer.parseInt(grid[0]);
        this.goalCol = Integer.parseInt(grid[1]);
    }
    
    public void setMonsters(int numMonsters) {
        this.monstersArr = new int[numMonsters][2];
    }
    
    public void setMonstersArr(int index, int row, int col) {
        this.monstersArr[index][0] = row; 
        this.monstersArr[index][1] = col; 
    }
    
    public boolean checkIfMonsterCaughtPlayer() {
        for (int i = 0; i < this.monstersArr.length; i++) {
            if ((this.monstersArr[i][0] == this.playerRow) && (this.monstersArr[i][1] == this.playerCol)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkIfPlayerReachedGoal() {
        return ((this.playerRow == this.goalRow) && (this.playerCol == this.goalCol));
    }
    
    public void movePlayer(String move) {
        switch (move.toLowerCase()) {
            case "w":
                int row = this.playerRow - 1;
                if (row >= 0) {
                    this.playerRow = row;
                } else {
                    System.out.println(INVALID_MOVE);
                }
                break;
            case "s":
                row = this.playerRow + 1;
                if (row <= this.gridRows) {
                    this.playerRow = row;
                } else {
                    System.out.println(INVALID_MOVE);
                }
                break;
            case "a":
                int col = this.playerCol - 1;
                if (col >= 0) {
                    this.playerCol = col;
                } else {
                    System.out.println(INVALID_MOVE);
                }
                break;
            case "d":
                col = this.playerCol + 1;
                if (col <= this.gridCols) {
                    this.playerCol = col;
                } else {
                    System.out.println(INVALID_MOVE);
                }
                break;
        }
        // System.out.printf("Player Position: [%d, %d]\n", this.playerRow, this.playerCol);
        
    }
    
    public void moveMonsters() {
        for (int i = 0; i < this.monstersArr.length; i++) {
            int row = this.monstersArr[i][0] - 1;
            if (row < 0) {
                this.monstersArr[i][0] = this.gridRows;
            } else {
                this.monstersArr[i][0] = row;   
            }
            // System.out.printf("Monster Position: [%d, %d]\n", this.monstersArr[i][0], this.monstersArr[i][1]);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        MonsterMaze monsterMaze = new MonsterMaze();
        System.out.println(WELCOME_MESSAGE);
        
        System.out.println(INPUT_SIZE); 
        String gridSize = scan.nextLine();
        monsterMaze.setGridValues(gridSize);
        
        
        System.out.println(INPUT_START);
        String startingPosition = scan.nextLine();
        monsterMaze.setPlayerValues(startingPosition);

        System.out.println(INPUT_GOAL);
        String goalPosition = scan.nextLine();
        monsterMaze.setGoalValues(goalPosition);
        
        System.out.println(INPUT_MONSTERS);
        int numMonsters = scan.nextInt();
        monsterMaze.setMonsters(numMonsters);
        
        scan.nextLine();
        for (int i = 0; i < numMonsters; i++ ) {
            String monsterPosition = scan.nextLine();
            String[] monsterPositionArr = monsterPosition.split(",");
            int row  = Integer.parseInt(monsterPositionArr[0]);
            int col  = Integer.parseInt(monsterPositionArr[1]);
            monsterMaze.setMonstersArr(i, row, col);
        }
        
        boolean monsterCaughtPlayer = monsterMaze.checkIfMonsterCaughtPlayer();
        boolean playerReachedGoal = monsterMaze.checkIfPlayerReachedGoal();
        String move = "";
        while (!monsterCaughtPlayer && !playerReachedGoal) {
            System.out.println(MOVE);
            move = scan.nextLine();
            monsterMaze.movePlayer(move);
            monsterMaze.moveMonsters();
            monsterCaughtPlayer = monsterMaze.checkIfMonsterCaughtPlayer();
            // System.out.printf("Monster caught player: %b\n", monsterCaughtPlayer);
            playerReachedGoal = monsterMaze.checkIfPlayerReachedGoal();
        }
        
        if (monsterCaughtPlayer) {
            System.out.println(GAME_OVER);
        } else {
            System.out.println(REACH_GOAL);
        }
        
        System.out.println(THANKS);
    }
}