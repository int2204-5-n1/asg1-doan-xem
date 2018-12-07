package bomb;

import javax.swing.*;
import java.awt.*;

public class Monsters extends Actor {
    private  int _type; // type = 1 là monster, type = 2 là boss;
    private  int SpeedMonster;
    public Monsters(){}
    public Monsters(int x, int y, int type, int orient, int speed, int heart, String image) {
        this._x = x;
        this._y = y;
        this.SpeedMonster = speed;
        this._type = type;
        this._heart = heart;
        this._orient = orient;
        this._cross = false;
        this._image = new ImageIcon(getClass().getResource(image)).getImage();
        _width = _image.getWidth(null);
        if(type == 1){
            _height = _image.getHeight(null)-23;
        }else{
            _height = _image.getHeight(null)-38;
        }

    }

    @Override
    public void Draw(Graphics2D graphics2D) {
        if(_type == 1)
            graphics2D.drawImage(_image, _x, _y-23, null);

        else if (_type == 2) {
            graphics2D.drawImage(_image, _x, _y - 38, null);
            graphics2D.setColor(Color.WHITE);
            graphics2D.drawRect(_x + 13, _y - 54, _width - 26, 12);
            Image imgHeartBoss = new ImageIcon(getClass().getResource("/Images/heart_boss.png")).getImage();
            int a = 0;
            for (int i = 0; i < (_heart - 1) / 250 + 1; i++) {
                graphics2D.drawImage(imgHeartBoss, _x + 18 + a, _y - 52, null);
                a = a + 10;
            }
        }
    }

    @Override
    public void changeOrient(int _orient) {
        super.changeOrient(_orient);
        if(_type == 1){
            switch (_orient) {
                case LEFT:
                    _image= new ImageIcon(getClass().getResource("/Images/monster_left.png")).getImage();
                    break;
                case RIGHT:
                    _image = new ImageIcon(getClass().getResource("/Images/monster_right.png")).getImage();
                    break;
                case UP:
                    _image = new ImageIcon(getClass().getResource("/Images/monster_up.png")).getImage();
                    break;
                case DOWN:
                    _image = new ImageIcon(getClass().getResource("/Images/monster_down.png")).getImage();
                    break;
                default:
                    break;
            }
        }else{
            switch (_orient) {
                case LEFT:
                    _image = new ImageIcon(getClass().getResource("/Images/boss_left.png")).getImage();
                    break;
                case RIGHT:
                    _image = new ImageIcon(getClass().getResource("/Images/boss_right.png")).getImage();
                    break;
                case UP:
                    _image = new ImageIcon(getClass().getResource("/Images/boss_up.png")).getImage();
                    break;
                case DOWN:
                    _image = new ImageIcon(getClass().getResource("/Images/boss_down.png")).getImage();
                    break;
                default:
                    break;
            }
        }
    }

    public int get_type() {
        return _type;
    }

    public void set_type(int _type) {
        this._type = _type;
    }

    public int getSpeedMonster() {

        return SpeedMonster;
    }

    public void setSpeedMonster(int speedMonster) {
        SpeedMonster = speedMonster;
    }
}
