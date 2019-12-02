import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Layer extends JPanel implements ActionListener, KeyListener {
    private List<Shape> shapes = new ArrayList<Shape>();
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    Timer t = new Timer(10, this);

    static int x = 20, y = 70;
    static int width = Main.width - 60;
    static int height = Main.height - 130;
    static int botCorner = y + height;
    static int rightCorner = x + width;

    public void removeCircles() {
        shapes.removeIf(shape -> shape instanceof Circle);
    }

    public String getInfo() {
        String info = "Layer of crazy shapes: \n";
        for (Shape shape : shapes) {
            info += shape.toString() + "\n";
        }
        return info;
    }

    public void removeDuplicates() {
        for (int i = 0; i < shapes.size(); i++) {
            for (int j = i+1; j < shapes.size(); j++) {
                if (shapes.get(i).equals(shapes.get(j)))
                    shapes.remove(j--);
            }
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);;
        setBackground(Color.WHITE);
        g.drawRect(x, y, width, height);
        g.drawString("Commands:", x,y - 50);
        g.drawString("  C: add a circle", x,y - 35);
        g.drawString("  R: add a rectangle", x, y - 20);
        g.drawString("  S: add a square", x, y -  5);
        g.drawString("  1: remove circles", x + 120,y - 35);
        g.drawString("  2: remove duplicates", x + 120,y - 20);
        g.drawString("  -: slow down", x + 120*2,y - 35);
        g.drawString("  +: speed up", x + 120*2,y - 20);

        shapes.forEach(shape -> shape.draw(g));
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        shapes.forEach(shape -> shape.move());//shapes.forEach(Shape::move);
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        System.out.println(keyEvent.getKeyChar());
        switch (keyEvent.getKeyChar()) {
            case 'c':
                addShape(new Circle(new Point(100, 100),25, "BLACK", true));
                break;
            case 'r':
                addShape(new Rectangle(new Point(10, 80),55, 25, "BLACK", true));
                break;
            case 's':
                addShape(new Square(new Point(10, 80),35, "BLACK", true));
                break;
            case '1':
                removeCircles();
                break;
            case '2':
                removeDuplicates();
                break;
            case '-':
                shapes.forEach(shape -> shape.slowDown());
                break;
            case '+':
                shapes.forEach(Shape::speedUp);
                break;
            default:
                break;
        }
        //keyEvent.consume();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {/* not used */}
    @Override
    public void keyReleased(KeyEvent keyEvent) {/* not used */}
}
