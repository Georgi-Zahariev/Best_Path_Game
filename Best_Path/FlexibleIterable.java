import java.util.Iterator;

/**
 * generic interface
 * @param <T>
 */
interface FlexibleIterable <T> extends Iterable<T> {
    /**
     * abstract method that will be implemented in the classes that implement this interface
     * @param iterableObjectName
     * @return Iterator
     */
    public Iterator<T> iterator(String iterableObjectName);

}
