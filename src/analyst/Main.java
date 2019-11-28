package analyst;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    List<ObjectClasses> lisClasses= new ArrayList<>();
    public Main() {
        try {
            initGetClassInfo();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    private static Point random(){
        Point p= new Point(0,0);
        Random ran= new Random();
        p.setX(ran.nextInt(800));
        p.setY(ran.nextInt(600));
        return p;
    }
    private void initGetClassInfo() throws IOException {
        Parser parser=new Parser("");
        parser.handle();
        lisClasses=parser.listofObjectClasses;

    }
    public static void main(String[] args){
        Main m=new Main();
        Layer layer = new Layer();
        JFrame frame = new JFrame("FileVisual");

        for(ObjectClasses ob:m.lisClasses){
            Table table=new Table(random(),ob);
            layer.addTable(table);
        }


        frame.add(layer);
        frame.addMouseListener(layer);
        frame.addMouseMotionListener(layer);

        frame.setSize(1200, 1000);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
