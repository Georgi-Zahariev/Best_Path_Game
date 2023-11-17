/**
 * Passage class, extending Block
 * It will represent block where we are allowed to enter
 */
public class Passage extends Block{
    /**
     * field - String label
     */
    private int value; //not in description
    /**
     * Constructor for Obstacle objects
     * @param value
     */
    public Passage(int value){
        super();
        this.value = value;
    }
    /**
     * toString method for Obstacle
     * @return String value
     */
    @Override
    public String toString(){
        return Integer.toString(value);
    }
    /**
     * Method from parent class, implementation is provided here
     * @return int value
     */
    @Override
    public int getValue() {
        return this.value;
    }
}
