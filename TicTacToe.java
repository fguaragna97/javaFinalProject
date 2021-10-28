import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

  // we keep track of the inputs
  static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
  static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

  public static void main(String[] args) {
    // here we make the gameboard
    char[][] gameBoard = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' },
        { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' } };

    printGameBoard(gameBoard);

    // me make a while so it keeps listening for the player input // Game loop
    while (true) {
      // we make a scanner for the player to input where does he wanna play
      Scanner scan = new Scanner(System.in);
      System.out.println("Where do you wanna play? (1-9)");
      int playerPos = scan.nextInt();

      // we check that the input is valid
      while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
        System.out.println("Position invalid ");
        playerPos = scan.nextInt();
      }

      placePiece(gameBoard, playerPos, "player");

      String result = checkWinner();
      if (result.length() > 0) {
        System.out.println(result);
        break;
      }

      // we make a random number for the cpuPos
      Random rand = new Random();
      int cpuPos = rand.nextInt(9) + 1;

      // with these we fix that the cpu is overwritten our positions
      while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
        cpuPos = rand.nextInt(9) + 1;
      }

      // we make the cpu play
      placePiece(gameBoard, cpuPos, "cpu");

      // we print another time the gameboard with the changes
      printGameBoard(gameBoard);

      // we print the result if there is a winner or a tie
      result = checkWinner();
      if (result.length() > 0) {
        System.out.println(result);
        break;
      }
      System.out.println(result);
    }

  }

  // we extract the print game board and we save it as a method
  public static void printGameBoard(char[][] gameBoard) {
    for (char[] row : gameBoard) {
      for (char c : row) {
        System.out.print(c);

      }
      System.out.println();

    }
  }

  // we make the place Piece a method so we can reuse code
  public static void placePiece(char[][] gameBoard, int pos, String user) {

    // we declared that symbol is gonna be space for default
    char symbol = ' ';

    // with these we can change between user and cpu and we stored the positions
    if (user.equals("player")) {
      symbol = 'X';
      playerPositions.add(pos);
    } else if ((user.equals("cpu"))) {
      symbol = 'O';
      cpuPositions.add(pos);
    }

    // we make the switch case so when the user makes an input the character in the
    // arrays changes
    switch (pos) {
    case 1:
      gameBoard[0][0] = symbol;
      break;
    case 2:
      gameBoard[0][2] = symbol;
      break;
    case 3:
      gameBoard[0][4] = symbol;
      break;
    case 4:
      gameBoard[2][0] = symbol;
      break;
    case 5:
      gameBoard[2][2] = symbol;
      break;
    case 6:
      gameBoard[2][4] = symbol;
      break;
    case 7:
      gameBoard[4][0] = symbol;
      break;
    case 8:
      gameBoard[4][2] = symbol;
      break;
    case 9:
      gameBoard[4][4] = symbol;
      break;

    default:
      break;
    }
  }

  // Method to check if there is a winner
  public static String checkWinner() {

    // we make all the win conditions
    List topRow = Arrays.asList(1, 2, 3);
    List midRow = Arrays.asList(4, 5, 6);
    List botRow = Arrays.asList(7, 8, 9);

    List topCol = Arrays.asList(1, 4, 7);
    List midCol = Arrays.asList(2, 5, 8);
    List botCol = Arrays.asList(3, 6, 9);

    List cross1 = Arrays.asList(1, 5, 9);
    List cross2 = Arrays.asList(7, 5, 3);

    // we add all the win conditions to another list so we can loop through it
    List<List> winning = new ArrayList<List>();
    winning.add(topRow);
    winning.add(midRow);
    winning.add(botRow);
    winning.add(topCol);
    winning.add(midCol);
    winning.add(botCol);
    winning.add(cross1);
    winning.add(cross2);

    for (List l : winning) {
      if (playerPositions.containsAll(l)) {
        return "You won";
      } else if (cpuPositions.containsAll(l)) {
        return "Cpu won try again";
      } else if (playerPositions.size() + cpuPositions.size() == 9) {
        return "Tie";
      }
    }
    return "";
  }
}
