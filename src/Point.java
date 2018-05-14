package sample;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Point extends Spawnable{
    // This is the Point class.
    // This class will create points so that user can earn points while playing

    // Variables
    private Parent root;
    private Circle point;

    // Constructor
    public Point(Location location, Size size, Movement movement, Image img, Parent root){
        super(location, movement, img, size);
        this.root = root;
    }

    // This method creates a new object with given variables.
    @Override
    public void render() {
        point = new Circle();
        point.setFill(new ImagePattern(this.img));

        point.setCenterX(loc.getX());
        point.setCenterY(loc.getY());
        point.setRadius(size.getRadius());

        ((Pane) root).getChildren().add(point);
    }
    // Points not moving so it is not implemented.
    @Override
    public void move(){}

    // After collision happens, this destroys the point.
    @Override
    public void destroy(){
        point.setVisible(false);
    }
}
