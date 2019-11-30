package analyst;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends JFrame {
    static int width = 1200;
    static int height = 1000;
    //private static String path;
    List<ObjectClasses> lisClasses = new ArrayList<>();
    String path = "";

    Parser parser;
    boolean isopen=false;

    private Draw layer=new Draw(lisClasses);


    public Main () throws IOException {

         JFrame frame=new JFrame("FileVisual");
         JButton open = new JButton("Open");
         open.setBounds(50,50,100,100);
         open.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed (ActionEvent e) {
                 initGetClassInfo();
                 isopen=true;
                 try {
                     parser = new Parser(path);
                     parser.handle();
                     lisClasses = parser.listofObjectClasses;
                     layer.setlist(lisClasses);
                     for (ObjectClasses ob : lisClasses) {
                         if (ob.hasParents == false) {
                             Table table = new Table(random(800, 0, 200), ob);
                             layer.addTable(table);
                         } else {
                             Table table = new Table(random(800, 300, 500), ob);
                             layer.addTable(table);
                         }
                     }

                 } catch (IOException ex) {
                     ex.printStackTrace();
                 }
             }
         });
            layer.add(open);

            frame.add(layer);
            //frame.add(open);
            frame.addMouseListener(layer);
            frame.addMouseMotionListener(layer);
            frame.setVisible(true);

            frame.setSize(width, height);
            frame.setResizable(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


//            layer = new Draw(lisClasses);
//            frame = new JFrame("FileVisual");
//
////            for(ObjectClasses ob:lisClasses){
////                if(ob.hasParents==false) {
////                    Table table = new Table(random(800,0,200), ob);
////                    layer.addTable(table);
////                } else {
////                    Table table = new Table(random(800,300,500),ob);
////                    layer.addTable(table);
////                }
////            }
////            open.addActionListener(new ActionListener() {
////                @Override
////                public void actionPerformed (ActionEvent e) {
////                    if(e.getSource()==open){
////                        JFileChooser file_chooser=new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
////                        file_chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
////                        int returnval=file_chooser.showOpenDialog(null);
////                        if(returnval==JFileChooser.APPROVE_OPTION){
////                            choser=file_chooser.getSelectedFile().getAbsolutePath();
////                            System.out.println(choser);
////                            try {
////                                parser=new Parser(choser);
////                                parser.handle();
////                                lisClasses=parser.listofObjectClasses;
////                                layer = new Draw(lisClasses);
////                                for(ObjectClasses ob:lisClasses){
////                                    if(ob.hasParents==false) {
////                                        Table table = new Table(random(800,0,200), ob);
////                                        layer.addTable(table);
////                                    } else {
////                                        Table table = new Table(random(800,300,500),ob);
////                                        layer.addTable(table);
////                                    }
////                                }
////                                layer.add(open);
////                                frame.setSize(width, height);
////                                frame.add(layer);
////                                frame.addMouseListener(layer);
////                                frame.addMouseMotionListener(layer);
////                                frame.addMouseWheelListener(layer);
////
////                            } catch (IOException ex) {
////                                ex.printStackTrace();
////                            }
////                        }
////                    } else {
////                        choser="";
////                    }
////                }
////            });
//            layer.add(open);
//
//            frame.add(layer);
//            frame.addMouseListener(layer);
//            frame.addMouseMotionListener(layer);
//            frame.addMouseWheelListener(layer);
//
//            frame.setSize(width, height);
//            frame.setResizable(true);
//            frame.setVisible(true);
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initGetClassInfo ()  {
        JFileChooser file_chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        file_chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnval = file_chooser.showOpenDialog(null);
        if (returnval == JFileChooser.APPROVE_OPTION) {
            path = file_chooser.getSelectedFile().getAbsolutePath();
        }
    }



    public static void main (String[] args) {

        try {
            Main m= new Main();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



//        Draw layer = new Draw(m.lisClasses);
//        JFrame frame = new JFrame("FileVisual");
//
//        for(ObjectClasses ob:m.lisClasses){
//            if(ob.hasParents==false) {
//                Table table = new Table(random(800,0,200), ob);
//                layer.addTable(table);
//            } else {
//                Table table = new Table(random(800,300,500),ob);
//                layer.addTable(table);
//            }
//        }
//
//        frame.setSize(width, height);
//        frame.add(layer);
//        frame.addMouseListener(layer);
//        frame.addMouseMotionListener(layer);
//        frame.addMouseWheelListener(layer);
//        //frame.add(button);
//
//        frame.setResizable(true);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    private static Point random(int x,int y,int y2){
        Point p= new Point(0,0);
        Random ran= new Random();
        p.setX(ran.nextInt(x));
        p.setY(Math.random() * ((y2-y)+1)+y);
        return p;
    }

}
