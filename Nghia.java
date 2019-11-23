import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Layer extends JPanel {
    private List<Shape> shapes = new ArrayList<>();

    public void addShape(Shape newShape) {
        shapes.add(newShape);
    }

    public void removeCircles() {
        if (shapes == null) return;
        for ( int i = 0 ; i < shapes.size(); i++){
            if ( shapes.get(i) instanceof Circle ){
                shapes.remove(i);
            }
        }
    }

    public void removeDuplicates() {
        if (shapes == null) return;
        for (int i = 0 ; i < shapes.size(); i++) {
            if (shapes.get(i) instanceof Circle) {
                for (int j = i + 1 ; j < shapes.size(); j++) {
                    if (shapes.get(i).hashCode() == shapes.get(j).hashCode()) {
                        shapes.remove(j);
                    }
                }
            }
            if (shapes.get(i) instanceof Rectangle) {
                for (int j = i + 1 ; j < shapes.size(); j++) {
                    if (shapes.get(i).hashCode() == shapes.get(j).hashCode()) {
                        shapes.remove(j);
                    }
                }
            }
        }
    }

    public String getInfo() {
        String res = "Layer of crazy shapes: \n";
        for (Shape shape : shapes) res += shape.toString() + "\n";
        return res;
    }
}