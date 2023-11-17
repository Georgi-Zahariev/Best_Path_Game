
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.util.Iterator;
import java.util.ArrayList;
public class Game
{
    /**
     static nested class serving as a vehicle to return two values from a method
     */
    public static class TwoValues
    {
        public int startingColumn;
        public int totalPoints;
    }

    public static void main(String args[]) {
//
//        /**
//         command line arguments validation
//         */
//        if (args.length != 1)
//        {
//            System.err.println("Usage: java " + Game.class.getName() + " <filename>");
//            return;
//        }
//
//        /**
//         example of loading data from a file
//         */
//        Field<Block> field = FieldGenerator.loadDataFromFile(args[0]);
//
//        /**
//         example of generating a random Field
//         */
//        //Field<Block> field = FieldGenerator.randomIntegers(8,11,0,9,10);
//        //Field<Block> field = FieldGenerator.loadDataFromFile("info.txt");
//        //Field<Block> field = FieldGenerator.randomIntegers(8,11,0,9,10);
//        //Field<Block> field = FieldGenerator.randomIntegers(4,4,0,9,4);
//
//        /**
//         print the whole field
//         */
//        System.out.println(field);
//
//        /**
//         Example of running a foreach loop
//         This will invoke the default iterator (the one using the anonymous inner class)
//         */
//        for(Block b:field)
//            System.out.println(b);
//
//        /**
//         Example of running a while loop
//         This will invoke the overloaded iterator (the one using the FieldIterator class)
//         */
//        //Iterator<Block> it = field.iterator("Passage");
//      Iterator<Block> it = field.iterator("Obstacle"); // same thing for Obstacle objects
//        while(it.hasNext())
//            System.out.println(it.next());
//
//        /**
//         find the best starting point and print the results
//         */
//        TwoValues br = bestStartingPoint(field);
//        System.out.println("Best starting point is in column " + br.startingColumn + " and the total points collected from this route is " + br.totalPoints);
//
//        /**
//         find the best route and print it
//         */
//        ArrayList<Block> al = bestRoute(field, br.startingColumn);
//        for (Block b : al)
//            System.out.println(b);
    }


    /**
     * The method is showing where is the best starting point and what is the points collected from the best path
     * @param board
     * @return TwoValues object
     */
    public static TwoValues bestStartingPoint(Field<Block> board){
        TwoValues val = new TwoValues();
        int maxVal = Integer.MIN_VALUE;
        int index = -1;

        //iterating over all elements from the first row
        for(int i = 0; i < board.getWidth(); i++){
            //using the help method to determine if the best path from the current point is max
            if(maxVal < calcBestRoad(board,0,i) && board.getElement(0, i).getClass().getName() != "Obstacle"){
                //set as max
                maxVal = calcBestRoad(board,0,i);
                //save the column
                index = i;
            }
        }

        val.startingColumn= index;
        val.totalPoints = maxVal;
        return val;

    }

    /**
     * Method that is finding the best row in the field
     * using recursion
     * @param board
     * @param row
     * @param col
     * @return int val - the best path in the field
     */
    private static int calcBestRoad(Field<Block> board, int row,int col){
        //base case, if row is after the last row, done
        if (row == board.getHeight() - 1) {
            return board.getElement(row, col).getValue();
        }

        // Recursive cases
        int left = Integer.MIN_VALUE;
        int down = Integer.MIN_VALUE;
        int right = Integer.MIN_VALUE;
        if (col > 0 && board.getElement(row+1, col-1).getClass().getName() != "Obstacle") { // check if left cell is in bounds
            //path of go down, left
            left = calcBestRoad(board, row + 1, col - 1);
        }
        if (col < board.getWidth() - 1 && board.getElement(row+1, col+1).getClass().getName() != "Obstacle") { // check if right cell is in bounds
            //path of go down, right
            right = calcBestRoad(board, row + 1, col + 1);
        }
        if(board.getElement(row+1, col).getClass().getName() != "Obstacle")
            //path of go down
            down = calcBestRoad(board, row + 1, col);

        //return the max value
        return board.getElement(row, col).getValue() + Math.max(left, Math.max(down, right));
    }

    /**
     * Tracing the best route and returning ArrayList with the route
     * @param board
     * @param col
     * @return ArrayList with Block elements
     */
    public static ArrayList<Block> bestRoute(Field<Block> board, int col){
        //if wrong input for the method
        if(col < 0 || col > board.getWidth()){
            throw new IllegalArgumentException();
        }
        ArrayList<Block> list = new ArrayList<>();
        //the list with the path is made in the help method
        list = trace(board,list,0,col);
        //but it is reversed, so we need to correct the order
        ArrayList<Block> list2 = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            list2.add(0, list.get(i));
        }
        return list2;

    }

    /**
     * Using recursion
     * @param board
     * @param arr
     * @param row
     * @param col
     * @return ArrayList with specific path having the start coordinates
     */
    private static ArrayList<Block> trace(Field<Block> board,ArrayList<Block> arr,  int row,int col){

        if (row == board.getHeight() - 1 ) {
            arr.add(0,board.getElement(row, col));
            return arr;
        }

        // Recursive cases
        int left = Integer.MIN_VALUE;
        int down = Integer.MIN_VALUE;
        int right = Integer.MIN_VALUE;
        if (col > 0 && board.getElement(row+1, col-1).getClass().getName() != "Obstacle") { // check if left cell is in bounds
            //path of go down, left
            left = calcBestRoad(board, row + 1, col - 1);
        }
        if (col < board.getWidth() - 1 && board.getElement(row+1, col+1).getClass().getName() != "Obstacle") { // check if right cell is in bounds
            //path of go down, right
            right = calcBestRoad(board, row + 1, col + 1);
        }
        if(board.getElement(row+1, col).getClass().getName() != "Obstacle")
            //path of go down
            down = calcBestRoad(board, row + 1, col); // always go down


        arr.add(0,board.getElement(row, col));
        //passing arr as parameter so recursion will append the elements from the path there
        //based on which path is bigger we call recursively with different coordinates
        if(Math.max(left, Math.max(down, right)) == left){
            return trace(board,arr,row +  1,col-1);
        }else if(Math.max(left, Math.max(down, right)) == down){
            return trace(board,arr,row + 1,col);
        }else{
            return trace(board,arr,row + 1,col+1);
        }

    }


}


