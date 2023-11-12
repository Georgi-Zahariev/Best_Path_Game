import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Class FieldGenerator
 */
public class FieldGenerator {
    /**
     * Loading data from file
     * @param filename
     * @return Field
     */
    public static Field<Block> loadDataFromFile(String filename) {
        try {
            //rows count
            Scanner cs = new Scanner(new File(filename));
            int r = 0;
            while (cs.hasNextLine()) {
                r++;
                cs.nextLine();
            }
            //columns count
            cs = new Scanner(new File(filename));
            int c = 0;
            if (cs.hasNextLine()) {
                c = cs.nextLine().split("\\s+").length;
            }

            cs = new Scanner(new File(filename));

            //creating the field based on the file
            Field<Block> field = new Field<>(r, c);

            //filling the matrix
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    String val = cs.next();
                    Block el;
                    //if exception is raised
                    //val is not number
                    //create the needed type of Block
                    try{
                        el = new Passage(Integer.parseInt(val));
                    }catch (Exception e){
                        el = new Obstacle(val);
                    }

//                    if (val.equals("-")) {
//                        el = new Obstacle(val);
//                    } else {
//                        el = new Passage(Integer.parseInt(val));
//                    }
                    //using the setter to set elements in the field
                    field.setElement(i, j, el);
                }
            }
            return field;
        }catch(FileNotFoundException e){
            //if file not found, just generate this random matrix
            return randomIntegers(7,7,0,9,11);
        }
    }


    /**
     * Creating a Field by selected size and range of number values, and count of obstacles
     * random values
     * @param h
     * @param w
     * @param l
     * @param m
     * @param n
     * @return Field
     */
    public static Field<Block> randomIntegers(int h, int w, int l, int m, int n){
        //if wrong values are used in the method
        if(h<1 || w<1 || m<l || n < 0){
            throw new IllegalArgumentException();
        }
        //creating the field
        Field<Block> f = new Field<>(h,w);

        Random rand = new Random();
        //adding obstacles at random places
        for(int i = 0 ; i < n; i++){
            boolean check = false;
            //the checker is needed, because it will generate new address if we already have obstacle at the generated
            while(check == false) {
                //random coordinates
                int ranH = rand.nextInt(h);
                int ranW = rand.nextInt(w);
                // if this address haven't been generated before
                //we create Obstacle there
                if(f.getElement(ranH,ranW) == null){
                    //I am using "-" for label for Obstacle
                    Obstacle o = new Obstacle("-");
                    f.setElement(ranH,ranW,o);
                    //make check true and while will stop, because we set obstacle successfully
                    check = true;
                }

            }
        }
        //setting random Passages
        for(int i = 0 ; i < h; i++){
            for(int j = 0 ; j < w; j++){
                //if at that address there is obstacle, continue
                if(f.getElement(i,j) instanceof Obstacle){
                    continue;
                }else{
                    //rand value in range [l , m]
                    int val = rand.nextInt((m - l) + 1) + l;
                    Passage p = new Passage(val);
                    f.setElement(i,j,p);
                }
            }
        }
        return f;

    }

}
