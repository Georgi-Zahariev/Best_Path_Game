import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

/**
 * GUI class
 * a program that is letting the user choose what to do
 */
class GUI {
    /**
     * field object
     */
    private Field<Block> field;
    /**
     * ArrayList with Block elements
     */
    private ArrayList<Block> list;
    /**
     * JPanel
     */
    private JPanel panelTable;
    /**
     * label with info
     */
    private JLabel info2;
    /**
     * label with info
     */
    private JLabel pathLbl;

    /**
     * Constructor
     * The whole thing is happening here
     * Creating the user friendly part of our project
     */
    GUI() {
        JFrame frame = new JFrame("CS 211");
        //set frames size
        frame.setSize(1000,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lbl1 = new JLabel("File name");
        JTextField txt1 = new JTextField(20);

        //button and its events
        JButton btn1 = new JButton("Generate");
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    //if nothing there
                    if(txt1.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Enter file name or choose the Random Generate matrix!");
                    }else{//if there is something...
                        //loading the data from the file, if not it is generating random
                        field = FieldGenerator.loadDataFromFile(txt1.getText());
                        panelTable = new JPanel(new GridLayout(field.getHeight(), field.getWidth()));

                        ArrayList<Block> arr = Game.bestRoute(field, Game.bestStartingPoint(field).startingColumn);
                        for(int i = 0 ; i < field.getHeight(); i++){
                            for(int j = 0 ; j < field.getWidth(); j++){
                                JLabel a;
                                //if current element is in the best path
                                //I am adding a * so I can colour it later easily
                                if(arr.contains(field.getElement(i,j))) {
                                    a = new JLabel(field.getElement(i, j).toString() + "*");
                                    a.setPreferredSize(new Dimension(30, 30));
                                    a.setHorizontalAlignment(JLabel.CENTER);
                                }else{
                                    a = new JLabel(field.getElement(i, j).toString());
                                    a.setPreferredSize(new Dimension(30, 30));
                                    a.setHorizontalAlignment(JLabel.CENTER);
                                }
                                panelTable.add(a);
                            }
                        }
                        frame.add(panelTable, BorderLayout.SOUTH);
                        panelTable.revalidate();
                    }
                }
            }
        );

        //button
        JButton btn2 = new JButton("Random Generate matrix");
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //random generating field
                field = FieldGenerator.randomIntegers(10, 10, 1, 9, 15);
                panelTable = new JPanel(new GridLayout(10, 10));
                ArrayList<Block> arr = Game.bestRoute(field, Game.bestStartingPoint(field).startingColumn);
                for (int i = 0; i < field.getHeight(); i++) {
                    for (int j = 0; j < field.getWidth(); j++) {
                        JLabel a;
                        //if current element is in the best path
                        //I am adding a * so I can colour it later easily
                        if (arr.contains(field.getElement(i, j))) {
                            a = new JLabel(field.getElement(i, j).toString() + "*");
                            a.setPreferredSize(new Dimension(30, 30));
                            a.setHorizontalAlignment(JLabel.CENTER);
                        } else {
                            a = new JLabel(field.getElement(i, j).toString());
                            a.setPreferredSize(new Dimension(30, 30));
                            a.setHorizontalAlignment(JLabel.CENTER);
                        }
                        panelTable.add(a);
                    }
                }
                frame.add(panelTable, BorderLayout.SOUTH);
                panelTable.revalidate();
            }
        }
        );
        //button
        JButton btn = new JButton("Refresh");

        //adding the objects
        frame.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(lbl1);
        topPanel.add(txt1);
        topPanel.add(btn1);
        topPanel.add(btn2);
        topPanel.add(btn);

        //the middle part of our view
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel,BoxLayout.Y_AXIS));

        JPanel infoStart = new JPanel();
        JLabel l = new JLabel("If the file is not found it will automatically generate random matrix!");
        infoStart.setLayout(new BoxLayout(infoStart, BoxLayout.X_AXIS));
        infoStart.add(l);

        JPanel info = new JPanel();
        JLabel lbl2 = new JLabel("FIRST YOU NEED TO GENERATE THE MATRIX BASED ON THE TWO OPTIONS ABOVE AND THEN YOU CAN CONTINUE WITH THE BUTTONS BELOW");
        info.setLayout(new BoxLayout(info, BoxLayout.X_AXIS));
        info.add(lbl2);



        JPanel btn3Panel = new JPanel();
        btn3Panel.setLayout(new BoxLayout(btn3Panel, BoxLayout.X_AXIS));
        JButton btn3 = new JButton("Iterate throw all elements");

        JLabel lbl3 = new JLabel("show here");
        //button, it is doing the normal iteration
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = "";
                Iterator<Block> it = field.iterator();
                while (it.hasNext())
                    s += it.next() + " ";
                lbl3.setText(s);
            }
        }
        );
        btn3Panel.add(btn3);
        btn3Panel.add(lbl3);
        lbl3.setMaximumSize(new Dimension(Integer.MAX_VALUE, lbl3.getPreferredSize().height));


        JPanel btn4Panel = new JPanel();
        btn4Panel.setLayout(new BoxLayout(btn4Panel, BoxLayout.X_AXIS));
        JButton btn4 = new JButton("Iterate through Passages only");
        JLabel lbl4 = new JLabel("show here");
        //button, it is doing the Passage iteration
        btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = "";
                Iterator<Block> it = field.iterator("Passage");
                while (it.hasNext())
                    s += it.next() + " ";
                lbl4.setText(s);
            }
        }
        );
        btn4Panel.add(btn4);
        btn4Panel.add(lbl4);
        lbl4.setMaximumSize(new Dimension(Integer.MAX_VALUE, lbl4.getPreferredSize().height));

        JPanel btn5Panel = new JPanel();
        btn5Panel.setLayout(new BoxLayout(btn5Panel, BoxLayout.X_AXIS));
        JButton btn5 = new JButton("Iterate through Obstacles only");
        JLabel lbl5 = new JLabel("show here");
        //button, it is doing the Obstacle iteration
        btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = "";
                Iterator<Block> it = field.iterator("Obstacle");
                while (it.hasNext())
                    s += it.next() + " ";
                lbl5.setText(s);
            }
        }
        );
        btn5Panel.add(btn5);
        btn5Panel.add(lbl5);
        lbl5.setMaximumSize(new Dimension(Integer.MAX_VALUE, lbl5.getPreferredSize().height));

        JPanel btn6Panel = new JPanel();
        btn6Panel.setLayout(new BoxLayout(btn6Panel, BoxLayout.X_AXIS));
        JButton btn6 = new JButton("Show the best path info");
        JLabel lbl6 = new JLabel("show here");
        //button - colouring the path, showing the path and calculation of the score
        btn6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //best route
                ArrayList<Block> arr = Game.bestRoute(field, Game.bestStartingPoint(field).startingColumn);
                //total points from the route
                int s = Game.bestStartingPoint(field).totalPoints;
                String path = "";
                //loop to make a nice string for users
                for (int i = 0; i < arr.size(); i++) {
                    if (i < arr.size() - 1) {
                        path += arr.get(i).toString() + " -> ";
                    } else {
                        path += arr.get(i).toString();
                    }
                }
                pathLbl.setText(path);
                lbl6.setText("Collected points: " + s);
                info2.setText("    See the path below colored in green!");

                //going over all elements and color the one that are from the path
                for (int i = 0; i < field.getHeight(); i++) {
                    for (int j = 0; j < field.getWidth(); j++) {
                        JLabel label = (JLabel) panelTable.getComponent(i * field.getWidth() + j);
                        if (label.getText().contains("*") && label.getText().length() > 1) {
                            label.setBackground(Color.GREEN); // set color to green
                            label.setOpaque(true);
                        }
                    }
                }
            }
        }
        );

        lbl6.setMaximumSize(new Dimension(200, lbl6.getPreferredSize().height));

        btn6Panel.add(btn6);
        btn6Panel.add(lbl6);
        pathLbl = new JLabel();
        btn6Panel.add(pathLbl);
        info2 = new JLabel();
        btn6Panel.add(info2);
        //button, cleaning stuff
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelTable.removeAll();
                lbl3.setText("show here");
                lbl4.setText("show here");
                lbl5.setText("show here");
                lbl6.setText("show here");
                info2.setText("");
                pathLbl.setText("");
                panelTable.revalidate();
            }
        }
        );


        middlePanel.add(infoStart);
        middlePanel.add(info);
        middlePanel.add(btn3Panel);
        middlePanel.add(btn4Panel);
        middlePanel.add(btn5Panel);
        middlePanel.add(btn6Panel);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(middlePanel,BorderLayout.CENTER);

        frame.setVisible(true);

    }

    /**
     * Main method, runs the whole program
     * @param args
     */
    public static void main(String[] args) {
        GUI gui = new GUI();
        System.out.println("main thread continues");
    }

}