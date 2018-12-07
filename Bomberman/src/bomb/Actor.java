package bomb;

import gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Actor extends Entities {

    protected static final int LEFT = 1;
    protected static final int RIGHT = 2;
    protected static final int UP = 3;
    protected static final int DOWN = 4;
    protected boolean ALIVE;
    protected boolean _cross;   //có cho đi qua hay không
    protected int _heart, _orient;
    public Actor(int x, int y, String image, int heart, int orient){
        this._x = x;
        this._y = y;
        this._heart = heart;
        this._orient = orient;
        this. ALIVE =true;
        this._cross = false;
        this._image = new ImageIcon(getClass().getResource(image)).getImage();
    }

    public Actor() {
    }

    @Override
    public void Draw(Graphics2D graphics2D) {}

    public int impact(Entities e) {return 0;}

    public boolean move(int count, int speed, ArrayList<Bomb> arrBomb, ArrayList<Box> arrBox){
        if(count % speed != 0)
            return true;
        switch (_orient){
            //Todo: Đi trái
            case LEFT:
                if(_x <= 0)
                    return false;
                _x--;
                for(Bomb bomb : arrBomb){
                    if(bomb.impact(this) == 1){  //Nêu di chuyển vào Bom thì lùi lại (x-- ==> x++)
                        _x++;
                        return false;
                    }
                }
                for(Box box : arrBox){
                    int possition = box.impact(this);   //Va chạm vào Box
                    if(possition != 0) {
                        if (possition >= -20 && possition <= 20) {
                            if (possition > 0)
                                _y++;
                            else
                                _y--;
                        }
                        _x++;
                        return false;
                    }
                }
                break;
             //Todo: Đi phải
            case RIGHT:
                if(_x +_width >= 945)
                    return false;
                _x++;
                for(Bomb bomb : arrBomb){
                    if(bomb.impact(this) == 1){
                        _x--;
                        return false;
                    }
                }
                for(Box box : arrBox){
                    int possition = box.impact(this);
                    if(possition != 0){
                        if (possition >= -20 && possition <= 20) {
                            if (possition > 0)
                                _y++;
                            else
                                _y--;
                        }
                        _x--;
                        return false;
                    }
                }
                break;
            //Todo: Đi lên
            case UP:
                if(_y <= 0){
                    return false;
                }
                _y--;
                for(Bomb bomb : arrBomb){
                    if(bomb.impact(this)==1){
                        _y++;
                        return false;
                    }
                }
                for(Box box : arrBox){
                    int kq = box.impact(this);
                    if(kq!=0){
                        if(kq>=-20 && kq<=20){
                            if(kq>0){
                                _x++;
                            }else{
                                _x--;
                            }
                        }
                        _y++;
                        return false;
                    }
                }
                break;
            //Todo : Đi xuống
            case DOWN:
                if(_y + _height>=(GUI.HEIGHTJF-45)){
                    return false;
                }
                _y++;
                for(Bomb bomb : arrBomb){
                    if(bomb.impact(this)==1){
                        _y--;
                        return false;
                    }
                }
                for(Box box : arrBox){
                    int kq = box.impact(this);
                    if(kq!=0){
                        if(kq>=-20 && kq<=20){
                            if(kq>0){
                                _x++;
                            }else{
                                _x--;
                            }
                        }
                        _y--;
                        return false;
                    }
                }
                break;
            default:
                break;

        }
        return true;
    }

    public void changeOrient(int _orient){
        this._orient = _orient;
    }

    public boolean is_cross() {
        return _cross;
    }

    public void set_cross(boolean _cross) {
        this._cross = _cross;
    }

    public int get_heart() {
        return _heart;
    }

    public void set_heart(int _heart) {
        this._heart = _heart;
    }


}
