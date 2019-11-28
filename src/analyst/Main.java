package analyst;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    static int width = 1200;
    static int height = 1000;
    List<ObjectClasses> lisClasses= new ArrayList<>();
    public Main() {
        try {
            initGetClassInfo();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    private static Point random(int x,int y,int y2){
        Point p= new Point(0,0);
        Random ran= new Random();
        p.setX(ran.nextInt(x));
        p.setY(Math.random() * ((y2-y)+1)+y);
        return p;
    }
    private void initGetClassInfo() throws IOException {
        Parser parser=new Parser("");
        parser.handle();
        lisClasses=parser.listofObjectClasses;

    }
    public static void main(String[] args){
        Main m=new Main();
        Layer layer = new Layer(m.lisClasses);
        JFrame frame = new JFrame("FileVisual");

        for(ObjectClasses ob:m.lisClasses){
            if(ob.hasParents==false) {
                Table table = new Table(random(800,0,200), ob);
                layer.addTable(table);
            } else {
                Table table = new Table(random(800,210,500),ob);
                layer.addTable(table);
            }
            //layer.addTable(table);
        }


        frame.add(layer);
        frame.addMouseListener(layer);
        frame.addMouseMotionListener(layer);
        frame.addMouseWheelListener(layer);

        frame.setSize(width, height);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
