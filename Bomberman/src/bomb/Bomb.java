package bomb;

import javax.swing.*;
import java.awt.*;

public class Bomb extends Entities {

    protected int amountBomb;
    protected int timeLine;
    public Bomb(int amountBomb){
        this.amountBomb = amountBomb;
    }
    public Bomb(int x, int y){
        this._x =x;
        this._y = y;
    }
    public Bomb(int x, int y, int amountBomb, int timeLine){
        this._x = x;
        this._y = y;
        this.amountBomb = amountBomb;
        this.timeLine = timeLine;
        _image = new ImageIcon(getClass().getResource("/Images/bomb.png")).getImage();
        this._width = _image.getWidth(null);
        this._height = _image.getHeight(null);
    }

    public void deadlineBomb(){
        timeLine--;
    }

    @Override
    public void Draw(Graphics2D graphics2D) {  
        graphics2D.drawImage(_image, _x, _y, null);
    }

    @Override
    public int impact(Entities e) {
        if(e instanceof Bomber){
            if(((Bomber) e)._cross == true) {
                return 0;
            }
            Rectangle rec1 = new Rectangle(_x, _y, 45, 45);
            Rectangle rec2 = new Rectangle(e.get_x(), e.get_y(), e.getWidth(), e.getHeight());
            return rec1.intersects(rec2)?1:0;
        }
        if( e instanceof Bomb){
            Rectangle rec1 = new Rectangle(_x, _y, 45, 45);
            Rectangle rec2 = new Rectangle(e._x, e._y, 45, 45);
            return rec1.intersects(rec2)?1:0;
        }

        if( e instanceof Monsters){
            if(((Monsters) e)._cross == true) {
                return 0;
            }
            Rectangle rec2 = new Rectangle(_x, _y, 45, 45);
            Rectangle rec3 = new Rectangle(e.get_x(), e.get_y(), e.getWidth(), e.getHeight());
            if(rec2.intersects(rec3)){
                if(e instanceof Monsters && ((Monsters) e).get_type() == 2){
                    return 2;
                }
                return 1;
            }
            return 0;
        }
        return 0;
    }
    //Kiểm tra khi bomber vừa đặt bomb thì có đi tiếp hay bị kẹt lại
    public boolean impactVsBomber(Bomber bomber){
        Rectangle rec2 = new Rectangle(_x, _y, 45, 45);
        Rectangle rec3 = new Rectangle(bomber.get_x(), bomber.get_y(), bomber.getWidth(), bomber.getHeight());
        return rec2.intersects(rec3);
    }

    //Kiểm tra 2 quả bom có xảy ra va chạm không? - (true nếu có)!
    public boolean impactVsBomb(Bomb bomb){
        Rectangle rec1 = new Rectangle(_x, _y, 45, 45);
        Rectangle rec2 = new Rectangle(bomb._x, bomb._y, 45, 45);
        return rec1.intersects(rec2);
    }

    //Kiểm tra Bomb có va chạm với nhân vật(enemy) hay không?
    public int impactvsActor(Actor actor){
        if(actor._cross == true) {
            return 0;
        }
        Rectangle rec2 = new Rectangle(_x, _y, 45, 45);
        Rectangle rec3 = new Rectangle(actor.get_x(), actor.get_y(), actor.getWidth(), actor.getHeight());
        if(rec2.intersects(rec3)){
            if(actor instanceof Monsters && ((Monsters) actor).get_type() == 2){
                return 2;
            }
            return 1;
        }
        return 0;
    }

    public int getAmountBomb() {
        return amountBomb;
    }

    public void setAmountBomb(int amountBomb) {
        this.amountBomb = amountBomb;
    }

    public int getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(int timeLine) {
        this.timeLine = timeLine;
    }
}
