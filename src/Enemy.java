package sample;


import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Enemy extends Spawnable{
    // This is the Enemy class.
    // This class will create enemy objects so that user can dodge it.
    private Parent root;
    private Circle enemy;
    public Enemy(Location location, Size size, Movement movement, Image img, Parent root){
        super(location, movement, img, size);
        this.root = root;
    }

    // This method creates a new object with given variables.
    @Override
    public void render() {
        enemy = new Circle();
        enemy.setFill(new ImagePattern(this.img));

        enemy.setCenterX(loc.getX());
        enemy.setCenterY(loc.getY());
        enemy.setRadius(size.getRadius());

        ((Pane) root).getChildren().add(enemy);
    }

    // Enemies are coming down with this method.
    @Override
    public void move(){
        loc.setY(loc.getY() + movement.getSpeed());
        enemy.setCenterY(loc.getY());
    }

    // After enemy comes to the bottom of the screen, this destroys the point.
    @Override
    public void destroy(){
        enemy.setVisible(false);
    }
}
