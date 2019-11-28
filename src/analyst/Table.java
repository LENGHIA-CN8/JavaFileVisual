package analyst;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class Table {
    ObjectClasses objectclass;
    public Point position;
    public double width;
    public double height;
    Rectangle2D Rect_frame;
    Font font = new Font("TimesRoman", Font.BOLD | Font.ITALIC, 15);
    public Table(Point position,ObjectClasses objectclass){
        this.position=position;
        this.objectclass=objectclass;
    }

    private Double[] getRectSize(Graphics g, String s, Font f) {
        return new Double[]{Size.getWidth(g, objectclass.name, f), Size.getHeight(g, f)};
    }

    private Double[] getRectSize(Graphics g, List<String> properties , Font f) {
        double maxWidth = 0;
        double height = 0;
       // System.out.println(properties.size());
      for (String p:properties) {
              height += Size.getHeight(g, f);
              maxWidth = Math.max(maxWidth, Size.getWidth(g,p+"    ", f));
        }
      return new Double[]{maxWidth+20, height};
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //Font font = g.getFont().deriveFont(Font.BOLD);
        //Font font = new Font("TimesRoman", Font.BOLD | Font.ITALIC, 16);
        g.setFont(font);

        Double[] titleSize = getRectSize(g, objectclass.to_String(), font);
        Double[] fieldSize = getRectSize(g, objectclass.StringFields, font);
        Double[] methodSize = getRectSize(g, objectclass.StringMethods, font);
        double maxWidth = Math.max(titleSize[0], Math.max(fieldSize[0], methodSize[0]));

        Rectangle2D titleRect = new Rectangle2D.Double(position.getX(), position.getY(), maxWidth, titleSize[1]);
        Rectangle2D fieldRect = new Rectangle2D.Double(position.getX(), position.getY() + titleSize[1], maxWidth, fieldSize[1]);
        Rectangle2D methodRect = new Rectangle2D.Double(position.getX(), position.getY() + titleSize[1] + fieldSize[1], maxWidth, methodSize[1]);

        width = maxWidth;
        height = titleSize[1] + fieldSize[1] + methodSize[1];
        Rect_frame = new Rectangle2D.Double(position.getX(), position.getY(), width, height);

        g2.setColor(Color.DARK_GRAY);       //draw Rect
        g2.fill(Rect_frame);
        g2.setColor(Color.WHITE);
        g2.draw(titleRect);
        g2.draw(fieldRect);
        g2.draw(methodRect);
        g2.draw(Rect_frame);

        g.drawString(objectclass.name, (int) (position.getX() + (maxWidth - Size.getWidth(g, objectclass.name, font)) / 2), (int) position.getY() + 13);

        g.setFont(font.deriveFont(Font.PLAIN));  //set font for properties

        int i = 0, lineHeight = (int) Size.getHeight(g, font);
        for (String s : objectclass.StringFields) {
            g.drawString(s, (int) position.getX() + 3, (int) (position.getY() + 12 + titleSize[1] + i * lineHeight));
            i++;
        }
        i = 0;
        for (String s : objectclass.StringMethods) {
            g.drawString(s, (int) position.getX() + 3, (int) (position.getY() + 12 + titleSize[1] + fieldSize[1] + i * lineHeight));
            i++;
        }
    }
    public boolean containsMouse(MouseEvent mouseEvent) {
        return Rect_frame.contains(mouseEvent.getX() , mouseEvent.getY() );
    }
        public void move(int moveX, int moveY)  {
            double x = position.getX() + moveX;
            double y = position.getY() + moveY;
            if(x+width<Main.width && x>0) {
                position.setX(x);
            }
            if(y+height<Main.height && y>0) {
                position.setY(y);
            }
        }
        public void scale(MouseWheelEvent e){
            if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
                //System.out.println(font.getSize());
                float amount =  font.getSize()+e.getWheelRotation() * 5;
                //System.out.println(amount);
                font.deriveFont(amount);

            }
        }

    }











