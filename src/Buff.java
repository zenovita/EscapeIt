package sample;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Buff extends Spawnable{

    private Parent root;
    private Circle buff;
    public Buff(Location location, Size size, Movement movement, Image image, Parent root){
        super(location,movement,image,size);
        this.root = root;
    }

    @Override
    public void render(){
        buff = new Circle();
        buff.setFill(new ImagePattern(this.img));

        buff.setCenterX(loc.getX());
        buff.setCenterY(loc.getY());
        buff.setRadius(size.getRadius());

        ((Pane) root).getChildren().add(buff);
    }
    @Override
    public void move(){
        loc.setY(loc.getY() + movement.getSpeed());
        buff.setCenterY(loc.getY());
    }

    @Override
    public void destroy(){
        buff.setVisible(false);
    }
}
