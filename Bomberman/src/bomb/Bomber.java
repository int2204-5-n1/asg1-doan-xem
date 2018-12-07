package bomb;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Bomber extends Actor{
    protected int _point;
    protected int SpeedBomber;
    public Bomber(int x, int y, int speed, int orient) {
        super(x,y,"/Images/bomber_down.png", 3 ,orient);
        this.SpeedBomber = speed;
        this._point = 0;
        this._heart = 3;
        this._width = _image.getWidth(null);
        this._height = _image.getHeight(null)-20;
    }
    //Tọa lại Bomber mới
    public void setNew(int x,int y) {
        this._x = x;
        this._y = y;
        this.ALIVE = true;
        this._image = new ImageIcon(getClass().getResource("/Images/bomber_down.png")).getImage();

    }
    @Override
    public void Draw(Graphics2D graphics2D) {
        graphics2D.drawImage(_image, _x, _y-20, null);
    }

    @Override
    public int impact(Entities e) {
        if(ALIVE == false)
            return 0;
        if(e instanceof Monsters){
            Rectangle rec1 = new Rectangle(_x, _y, _width, _height);
            Rectangle rec2 = new Rectangle(e.get_x(), e.get_y(), e.getWidth(), e.getHeight());
            return rec1.intersects(rec2) ? 1 : 0;
        }
        return 0;
    }

    @Override
    public boolean move(int count, int speed, ArrayList<Bomb> arrBomb, ArrayList<Box> arrBox) {
        if(ALIVE == false)
            return false;
        return super.move(count, SpeedBomber, arrBomb, arrBox);
    }

    @Override
    public void changeOrient(int _orient) {
        if(ALIVE == false)
            return;
        super.changeOrient(_orient);
        switch (_orient){
            case LEFT:
                _image = new ImageIcon(getClass().getResource("/Images/bomber_left.png")).getImage();
                break;
            case RIGHT:
                _image = new ImageIcon(getClass().getResource("/Images/bomber_right.png")).getImage();
                break;
            case UP:
                _image = new ImageIcon(getClass().getResource("/Images/bomber_up.png")).getImage();
                break;
            case DOWN:
                _image = new ImageIcon(getClass().getResource("/Images/bomber_down.png")).getImage();
                break;
            default:
                break;

        }
    }

    public int get_point() {
        return _point;
    }

    public void set_point(int _point) {
        this._point = _point;
    }

    public boolean isALIVE() {
        return ALIVE;
    }

    public void setALIVE(boolean ALIVE) {
        this.ALIVE = ALIVE;
    }

    public int getSpeedBomber() {
        return SpeedBomber;
    }

    public void setSpeedBomber(int speedBomber) {
         //Todo: Không cho speed về 0 (Lỗi chia 0)
        if(speedBomber < 1)
            return;
        SpeedBomber = speedBomber;
    }
}
