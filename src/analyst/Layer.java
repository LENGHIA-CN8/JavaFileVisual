package analyst;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Layer extends JPanel implements ActionListener, MouseMotionListener, MouseListener {
    private List<Table> tables = new ArrayList<>();

    Timer t = new Timer(1, this);
    private Point mouseposition= new Point(0,0);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.LIGHT_GRAY);
        tables.forEach(table -> table.draw(g));
        t.start();
    }

    public void addTable(Table table) {
        tables.add(table);
    }




    @Override
    public void actionPerformed (ActionEvent e) {
        repaint();
    }

    @Override
    public void mouseClicked (MouseEvent e) {

    }

    @Override
    public void mousePressed (MouseEvent e) {
          mouseposition.setX(e.getX());
          mouseposition.setY(e.getY());
//          for(int i=0;i<tables.size();i++){
//              if(tables.get(i).containsMouse(e)){
//
//                  //break;
//              }
//          }
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
}
