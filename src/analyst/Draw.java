package analyst;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Draw extends JPanel implements ActionListener, MouseMotionListener, MouseListener,MouseWheelListener {
    private List<Table> tables = new ArrayList<>();
    JButton button= new JButton("zoom1");

    private List<ObjectClasses> classes= new ArrayList<>();


    Draw(List<ObjectClasses> _classes){
        this.classes=_classes;

    }
    public void setlist(List<ObjectClasses> _classes){
        this.classes=_classes;
    }
    public void removealltable(){
        tables.removeAll(tables);
    }
    Timer t = new Timer(1, this);
    private Point mouseposition= new Point(0,0);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        add(button);
        setBackground(Color.LIGHT_GRAY);
        if(tables.size()>0) {
            tables.forEach(table -> table.draw(g));
            Graphics2D g2 = (Graphics2D) g;
            for (Table table : tables) {
                if (table.objectclass.hasParents == true) {
                    for (Table j : tables) {
                        for (int i = 0; i < table.objectclass.parent.size(); i++) {
                            //if(!table.objectclass.parent.isEmpty()){
                            if (table.objectclass.parent.get(i).equals(j.objectclass.name)) {

                                if (j.objectclass.othr.equals("interface")) {
                                    float[] dash3 = {4f, 0f, 2f};
                                    BasicStroke bs3 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                                            BasicStroke.JOIN_ROUND, 1.0f, dash3, 2f);
                                    g2.setStroke(bs3);
                                } else {
                                    BasicStroke bs1 = new BasicStroke(2, BasicStroke.CAP_BUTT,
                                            BasicStroke.JOIN_BEVEL);
                                    g2.setStroke(bs1);
                                }
                                int x=(int) (table.position.getX() + table.width / 2);
                                int y=(int) table.position.getY();
                                int _x=(int) (j.position.getX() + j.width / 2);
                                int _y=(int) (j.position.getY() + j.height + 10);

                                g2.drawLine(_x, y + (_y - y)/2, _x, _y);
                                g2.drawLine(x, y + (_y - y)/2, _x, y + (_y - y)/2);
                                g2.drawLine(x, y, x, y + (_y - y)/2);

                                Path2D mypath = new Path2D.Double();
                                mypath.moveTo((int) (j.position.getX() + j.width / 2), (int) (j.position.getY() + j.height));
                                mypath.lineTo((int) (j.position.getX() + j.width / 2 + 5), (int) (j.position.getY() + j.height + 10));
                                mypath.lineTo((int) (j.position.getX() + j.width / 2 - 5), (int) (j.position.getY() + j.height + 10));
                                mypath.closePath();
                                g2.setPaint(Color.WHITE);
                                g2.fill(mypath);
                            }
                        }
                    }
                }

            }
        }
        t.start();
    }

    public void addTable(Table table) {
        tables.add(table);
    }



    @Override
    public void actionPerformed (ActionEvent e) {
        if(e.getSource()==button){
            for(Table t:tables){
                t.iszoom=true;
            }
        } else {

        }
       // System.out.println("is zooming");
        repaint();
    }


    @Override
    public void mouseClicked (MouseEvent e) {

    }

    @Override
    public void mousePressed (MouseEvent e) {
          mouseposition.setX(e.getX());
          mouseposition.setY(e.getY());
    }
    @Override
    public void mouseDragged (MouseEvent e) {
          for(int i=0;i<tables.size();i++){
              if(tables.get(i).containsMouse(e)){
                  tables.get(i).move((int)(e.getX() - mouseposition.getX()),(int)( e.getY() - mouseposition.getY()));
                  mouseposition.setX(e.getX());
                  mouseposition.setY(e.getY());
                 // break;
              }
          }
    }

    @Override
    public void mouseReleased (MouseEvent e) {
          mouseposition.setX(0);
          mouseposition.setY(0);
    }

    @Override
    public void mouseEntered (MouseEvent e) {

    }

    @Override
    public void mouseExited (MouseEvent e) {

    }


   @Override
    public void mouseMoved (MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved (MouseWheelEvent e) {
        for(int i=0;i<tables.size();i++){
            if(tables.get(i).containsMouse(e)){
                tables.get(i).scale(e);
                //repaint();
            }
        }
    }
}
