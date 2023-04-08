//******************************************************************************
// FractalTree.java         Author: Ali Berk Karaarslan       Date:09.04.2023
//
// Fractal Tree with adjustable settings
//******************************************************************************

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

//Setting the visuals
class Frame extends JFrame{

    Tree tree;
    public Frame(){

        //Setting the frame
        setSize(1400,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Fractal Tree By Ali Berk Karaarslan");

        tree = new Tree();
        add(tree,BorderLayout.CENTER);

        //Panel that have sliders
        JPanel sliders = new JPanel();
        sliders.setLayout(new GridLayout(2,2));

        //With this slider you can change the number of trees
        JLabel treeCountLabel = new JLabel("  Count");
        JSlider treeCountSlider= new JSlider(SwingConstants.VERTICAL,1,8,1);
        treeCountSlider.setPaintTicks(true);
        treeCountSlider.setPaintLabels(true);
        treeCountSlider.setMajorTickSpacing(1);
        treeCountSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tree.treeCount = treeCountSlider.getValue();
                tree.repaint();
            }
        });
        sliders.add(treeCountLabel);
        sliders.add(treeCountSlider);

        //With this slider you can change the Angle  of trees
        JLabel angleLabel = new JLabel("  Angle");
        JSlider angleSlider = new JSlider(SwingConstants.VERTICAL,0,360,120);
        angleSlider.setPaintTicks(true);
        angleSlider.setPaintLabels(true);
        angleSlider.setMajorTickSpacing(15);
        angleSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tree.deltaAngle = angleSlider.getValue();
                tree.repaint();
            }
        });
        sliders.add(angleLabel);
        sliders.add(angleSlider);

        add(sliders,BorderLayout.WEST);
        setVisible(true);
    }
}

//Drawing the fractal tree
class Tree extends JPanel {

    int initialHeight = 200;

    //Angle which we want to grow the tree
    double deltaAngle = 30;
    int treeCount =1;

    public Tree() {
        setBackground(Color.BLACK);
        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);

        //Calculating the angle between trees
        int angleDifference = 360/treeCount;
        int startAngle = -90;

        //Drawing all the trees
        for(int i =0;i<treeCount;i++){
            drawTree(g,getWidth()/2,getHeight()/2,startAngle+(i*angleDifference),initialHeight);
        }
    }

    //Recursive Part
    public void drawTree(Graphics g ,int startX,int startY, double angle , double height){

        //Base Case
        if(height >1){
            double newHeight = height*0.67;
            int endX = startX + (int) ((Math.cos(Math.toRadians(angle))) * newHeight);
            int endY = startY + (int) ((Math.sin(Math.toRadians(angle))) * newHeight);

            g.drawLine(startX,startY,endX,endY);
            drawTree(g,endX,endY,angle+deltaAngle,newHeight);
            drawTree(g,endX,endY,angle-deltaAngle,newHeight);
        }
    }
}

//Starting the program
public class FractalTree {
    public static void main(String[] args) {
        new Frame();
    }
}