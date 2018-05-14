package sample;

import javafx.scene.image.Image;

public abstract class Spawnable {

        // common variables
        protected Location loc;
        protected Movement movement;
        protected Image img;
        protected Size size;

        Spawnable(Location loc, Movement movement, Image img, Size size){
            this.img=img;
            this.loc=loc;
            this.movement=movement;
            this.size=size;
        }

        Spawnable(){}

        // This method is for the enemy movement
        public double getYEnemy(){return loc.getY();}

        // These methods are used for game loop.
        public abstract void render();
        public abstract void move();
        public abstract void destroy();

        // This method calculates whether is a collision or not.
        boolean collision(Location loc,Size size){
            {//if the distance of two circle less or equal to their total radius then it is collision
                float distance = (float) this.loc.getDistance(this.loc, loc);
                distance = distance - size.getRadius() - this.size.getRadius();

                if(distance <= 0)
                    return true;
                else
                    return false;
            }
        }
}
