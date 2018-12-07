package bomb;

import javax.swing.*;
import java.awt.*;

public class Box extends Entities {
    protected boolean typeBox;  //true là không phá được, false là phá được

    public Box(int x , int y, boolean type, String image){
        _x = x;
        _y = y;
        this.typeBox = type;
        _image = new ImageIcon(getClass().getResource(image)).getImage();
        _width = _image.getWidth(null);
        _height = _image.getHeight(null);
    }

    @Override
    public void Draw(Graphics2D graphics2D) {
        graphics2D.drawImage(_image, _x, _y, null);
    }

    @Override
    public int impact(Entities e) {
        if(e instanceof Monsters){
            if(((Monsters) e).get_type() == 2)
                return 0;
        }
        if(e instanceof Actor){
            Rectangle rec1 = new Rectangle(_x, _y, _width, _height);
            Rectangle rec2 = new Rectangle(e.get_x(), e.get_y(), 45, 45);
            Rectangle rec3 = new Rectangle();

            if(rec1.intersects(rec2)){  //nếu rec1 giao nhau với rec2
                rec1.intersect(rec1,rec2,rec3);
                if(rec3.getHeight()==1 && (((Actor) e)._orient == Actor.UP || ((Actor) e)._orient == Actor.DOWN)){
                    if(e.get_x()==rec3.getX()){
                        return (int)rec3.getWidth();
                    }else{
                        return (int)-rec3.getWidth();
                    }
                }else{
                    if(e.get_y()==rec3.getY()){
                        return (int)rec3.getHeight();
                    }else{
                        return (int)-rec3.getHeight();
                    }
                }
            }
        }
        return 0;
    }

    public boolean isTypeBox() {
        return typeBox;
    }

    public void setTypeBox(boolean typeBox) {
        this.typeBox = typeBox;
    }
}
