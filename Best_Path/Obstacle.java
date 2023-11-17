/**
 * Obstacle class, extending Block
 * It will represent block where we are not allowed to enter
 */
public class Obstacle extends Block{
    /**
     * field - String label
     */
    private String label; //not in the description

    /**
     * Constructor for Obstacle objects
     * @param label
     */
    public Obstacle(String label){
        super();
        if(label == null || label.equals("")){
            throw new IllegalArgumentException();
        }
        this.label = label;
    }

    /**
     * toString method for Obstacle
     * @return String label
     */
    @Override
    public String toString(){
        return label;
    }

    /**
     * Method from parent class, implementation is provided here
     * @return int value
     */
    @Override
    public int getValue() {
        return 0;
    }
}
