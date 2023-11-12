import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * FieldIterator class
 * Generic
 * implements Iterator
 * @param <T>
 */
public class FieldIterator<T> implements Iterator<T> {
    /**
     * fields - field, row, col, iterableObjectName
     */
    private Field<T> field;
    private String iterableObjectName;
    private int row = 0;
    private int col = 0;

    /**
     * Constructor for FieldIterator
     * @param field
     * @param iterableObjectName
     */
    public FieldIterator(Field<T> field, String iterableObjectName) {
        this.field = field;
        this.iterableObjectName = iterableObjectName;
    }

    /**
     * hasNext element
     * @return boolean true/false
     */
    public boolean hasNext() {
        //until it reaches the end of the matrix
        while (row < field.getHeight() && col < field.getWidth()) {
            T element = field.getElement(row, col);
            //if not empty and is from the needed iterableObjectName
            if (element != null && element.getClass().getName().equals(iterableObjectName)) {
                return true;
            }
            //making the adjustments
            col++;
            //when it go passed the last element on the row
            //move to the next row and first column
            if (col == field.getWidth()) {
                col = 0;
                row++;
            }
        }
        return false;
    }

    /**
     * next - returning the next element
     * @return T
     */
    public T next() {
        if (!hasNext()) {
            //throwing this exception
            throw new NoSuchElementException();
        }
        //it will change to true once we reach next element
        boolean check = false;
        T element = null;
        //while I don't reach the valid one
        while(check == false) {
            element = field.getElement(row, col);
            //if this one is valid -> check = true and the while will stop after that
            if(element.getClass().getName().equals(iterableObjectName)){
                check =true;
            }
            //make updates
            col++;
            //if the end of the row, we are going to the next
            if (col == field.getWidth()) {
                col = 0;
                row++;
            }
        }
        return element;
    }

    /**
     * remove, throwing exception
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }
}