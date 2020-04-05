
import com.sun.deploy.security.WSeedGenerator;

import java.lang.reflect.Array;
import java.util.*;
import java.lang.String;
public class Main {

    static ArrayList<Integer> playerPos = new ArrayList<Integer>();
    static ArrayList<Integer> comPos = new ArrayList<Integer>();

   public static char[][] map =new char[][]{{'1','|','2','|','3'},
            {'-','+','-','+','-'},
            {'4','|','5','|','6'},
            {'-','+','-','+','-'},
            {'7','|','8','|','9'},
    };

   public static char computer = 'O', human = 'X';
   

    public static void printer(char[][] map){
        int row,col;
        for(row=0;row<map.length;row++){
            for(col=0;col<map[row].length;col++){
                System.out.print(map[row][col]);
            }
            System.out.println();
        }
        System.out.println();

    }
    
    public static void placer(int position, char playerType){

       if (playerType == computer){
            comPos.add(position);
       }
       else if(playerType == human){
           playerPos.add(position);
       }

        switch (position){
            case 1:
                map[0][0] = playerType;
                break;
            case 2:
                map[0][2] = playerType;
                break;
            case 3:
                map[0][4] = playerType;
                break;
            case 4:
                map[2][0] = playerType;
                break;
            case 5:
                map[2][2] = playerType;
                break;
            case 6:
                map[2][4] = playerType;
                break;
            case 7:
                map[4][0] = playerType;
                break;
            case 8:
                map[4][2] = playerType;
                break;
            case 9:
                map[4][4] = playerType;
                break;
            default:
                break;
           
        }
        printer(map);


    }

    public static String winner(){
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List bottomRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List diagonal1 = Arrays.asList(1,5,9);
        List diagonal2 = Arrays.asList(3,5,7);

        List<List> winwin = new ArrayList<List>();
        winwin.add(topRow);
        winwin.add(midRow);
        winwin.add(bottomRow);
        winwin.add(leftCol);
        winwin.add(rightCol);
        winwin.add(midCol);
        winwin.add(diagonal1);
        winwin.add(diagonal2);

        for(List l: winwin){
            if(playerPos.containsAll(l))
                return "You win!";
                else if(comPos.containsAll(l))
                    return "Sorry! You lose";
                        else if(playerPos.size() + comPos.size() == 9)
                            return "Its a draw";

        }

        return "";
    }

    public static void main(String[] args) {


        System.out.println("Welcome to tic tac toe!");
        printer(map);


        while (true) {
            System.out.println("Choose a position from 1-9 where you want to place X");
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();

            while (playerPos.contains(choice) || comPos.contains((choice))) {
                System.out.println("Space Already Filled");
                System.out.println("Choose a position from 1-9 where you want to place X");
                choice = scan.nextInt();
            }
            placer(choice, human);

            String result = winner();
            if(result.length()>0) {
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int num = rand.nextInt(9) + (1);
            while (playerPos.contains(num) || comPos.contains((num))) {
                num = rand.nextInt(9) + (1);
            }

            placer(num, computer);
                result = winner();
                if(result.length()>0) {
                    System.out.println(result);
                    break;
                }
            }
        }
    }

