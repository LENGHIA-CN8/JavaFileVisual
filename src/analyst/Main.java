package analyst;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends JFrame{
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
         ImageIcon openicon= new ImageIcon("/Users/user/Desktop/JavaFileVisual/openicon.png");
         ImageIcon saveicon= new ImageIcon("/Users/user/Desktop/JavaFileVisual/save-icon2.png");
         JButton open = new JButton(openicon);
         JButton save = new JButton(saveicon);
         //JToolBar buttonbar= new JToolBar(JToolBar.VERTICAL);
         JPanel buttonbar= new JPanel();

         open.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed (ActionEvent e) {
                 initGetClassPath();
                 //isopen=true;
                 try {
                     if(!path.equals("")) {
                         //parser = new Parser("/Users/user/Desktop/JavaFileVisual/Nghia.java");
                         parser = new Parser(path);
                         parser.handle();
                         lisClasses = parser.listofObjectClasses;
                     }
                     layer.removealltable();
                     layer.setlist(lisClasses);
                     if(!path.equals("")) {
                         for (ObjectClasses ob : lisClasses) {
                             if (ob.hasParents == false) {
                                 Table table = new Table(random(800, 0, 200), ob);
                                 layer.addTable(table);
                             } else {
                                 Table table = new Table(random(800, 300, 500), ob);
                                 layer.addTable(table);
                             }
                         }
                         path="";
                     }

                 } catch (IOException ex) {
                     ex.printStackTrace();
                 }
             }
         });
         save.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed (ActionEvent e) {
                 if(e.getSource()==save){
                     saveComponent(layer,"test.jpg");
                     System.out.println("saving");
                 }
             }
         });

            buttonbar.setLayout(new BoxLayout(buttonbar,BoxLayout.Y_AXIS));
            //buttonbar.setPreferredSize(new Dimension(60,100));
            layer.setPreferredSize(new Dimension(1200,700));

            buttonbar.add(open);
            buttonbar.add(save);

            frame.add(buttonbar,BorderLayout.WEST);
            frame.add(layer);
            frame.addMouseListener(layer);
            frame.addMouseMotionListener(layer);
            frame.setVisible(true);
            //frame.setBounds(100,100,500,500);


            frame.setSize(width, height);
            frame.setLocationRelativeTo(null);
            frame.setResizable(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
    public void saveComponent(Component c, String filename) {
        Dimension d = c.getPreferredSize();
        BufferedImage bi = new BufferedImage((int) d.getWidth(), (int) d.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = bi.createGraphics();
        //g2.setClip(0, 0, (int) d.getWidth(), (int) d.getHeight());
        c.paint(g2);
        try {
            ImageIO.write(bi,"jpg", new File(filename));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void initGetClassPath ()  {
        JFileChooser file_chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        file_chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnval = file_chooser.showOpenDialog(null);
        if (returnval == JFileChooser.APPROVE_OPTION) {
            String path1 = file_chooser.getSelectedFile().getAbsolutePath();

            if(path.equals("")||!path.equals(path1)){
                //System.out.println("yes");
                path=path1;
            } else {
                path="";
            }
            System.out.println(path1);
        }
    }




    public static void main (String[] args) {

        try {
            Main m= new Main();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Point random(int x,int y,int y2){
        Point p= new Point(0,0);
        Random ran= new Random();
        p.setX(ran.nextInt(x));
        p.setY(Math.random() * ((y2-y)+1)+y);
        return p;
    }

}
