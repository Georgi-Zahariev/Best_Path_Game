import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class Field, generic
 * implementing FlexibleIterable interface
 * @param <T>
 */
public class Field<T> implements FlexibleIterable<T>{
    /**
     * field - generic 2D array - matrix
     */
    private T[][] matrix;

    /**
     * Constructor for Field objects
     * initializing the matrix
     * @param height
     * @param width
     */
    @SuppressWarnings("unchecked")
    public Field(int height, int width){
        //ask question
        matrix = (T[][]) new Object[height][width];
    }

    /**
     * getter of an element from the matrix by its coordinates
     * @param row
     * @param col
     * @return T - generic
     */
    public T getElement(int row, int col){
        return matrix[row][col];
    }

    /**
     * Setter of an element from the matrix by its coordinates and value
     * @param row
     * @param col
     * @param el
     */
    public void setElement(int row, int col, T el){
        matrix[row][col] = el;
    }

    /**
     * matrix rows count
     * @return int height
     */
    public int getHeight(){
        return matrix.length;
    }
    /**
     * matrix columns count
     * @return int width
     */
    public int getWidth(){
        return matrix[0].length;
    }

    /**
     * toString method - user friendly output
     * @return String val
     */
    @Override
    public String toString(){ //no leading spaces
        String output = "";
        for(int i = 0; i<matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                output += matrix[i][j] + " ";
            }
            //no leading spaces
            output.trim();
            //new line after every row
            output += "\n";
        }
        return output;
    }

    /**
     * iterator method, iterating over all Blocks in the Field
     * @return Iterator
     */
    @Override
    public Iterator<T> iterator(){
        // An anonymous class
        return new Iterator<T>(){
            int row = 0;
            int col = 0;

            //if it is not already after the last row, it has next
            public boolean hasNext(){
                return row < getHeight();
            }

            //next block
            public T next(){
                //test
                if (!hasNext()){
                    //are we throwing this exception
                    throw new NoSuchElementException();
                }
                T element = getElement(row, col);
                col++;
                //making the adjustments
                if (col == getWidth()){
                    row++;
                    col = 0;
                }
                return element;
            }

            public void remove(){
                throw new UnsupportedOperationException();
            }
        };

    }

    /**
     * More advanced iterator, that iterate over specific types of blocks
     * @param iterableObjectName
     * @return Iterator
     */
    @Override
    public Iterator<T> iterator(String iterableObjectName){
        return new FieldIterator<T>(this, iterableObjectName);
    }

}
