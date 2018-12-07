package bomb;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BombBang extends Entities{
    protected int sizeBomBang, timeline;
    private Image img_left, img_right, img_up, img_down;
    public BombBang(){

    }
    public BombBang(int size){
        this.sizeBomBang = size;
    }
    public BombBang(int x, int y, int sizeBomBang, ArrayList<Box> arrBox) {
        this._x = x;
        this._y = y;
        this.sizeBomBang = sizeBomBang;
        this.timeline = 250;
        if(sizeBomBang == 1){
            img_left = new ImageIcon(getClass().getResource("/Images/bombbang_left_1.png")).getImage();
            img_right = new ImageIcon(getClass().getResource("/Images/bombbang_right_1.png")).getImage();
            img_up = new ImageIcon(getClass().getResource("/Images/bombbang_up_1.png")).getImage();
            img_down = new ImageIcon(getClass().getResource("/Images/bombbang_down_1.png")).getImage();
            for(Box b : arrBox) {
                if (isImpactBox(x - 45, y, 90, 45, b) && b.typeBox == true){
                    img_left = new ImageIcon(getClass().getResource("/Images/bombbang_left.png")).getImage();
                }
                if (isImpactBox(x, y, 90, 45, b) && b.typeBox == true){
                    img_right = new ImageIcon(getClass().getResource("/Images/bombbang_right.png")).getImage();
                }
                if (isImpactBox(x, y-45, 45, 90, b) && b.typeBox == true){
                    img_up = new ImageIcon(getClass().getResource("/Images/bombbang_up.png")).getImage();
                }
                if (isImpactBox(x , y, 45, 90, b) && b.typeBox == true){
                    img_down = new ImageIcon(getClass().getResource("/Images/bombbang_down.png")).getImage();
                }
            }
        }
        if(sizeBomBang == 2){
            img_left = new ImageIcon(getClass().getResource("/Images/bombbang_left_2.png")).getImage();
            img_right = new ImageIcon(getClass().getResource("/Images/bombbang_right_2.png")).getImage();
            img_up = new ImageIcon(getClass().getResource("/Images/bombbang_up_2.png")).getImage();
            img_down = new ImageIcon(getClass().getResource("/Images/bombbang_down_2.png")).getImage();
            for(Box b : arrBox) {
                //Todo: nếu chạm ở đầu tia lửa
                if (isImpactBox(x - 45*2, y, 135, 45, b)){
                    img_left = new ImageIcon(getClass().getResource("/Images/bombbang_left_1.png")).getImage();
                }
                if (isImpactBox(x, y, 135, 45, b)){
                    img_right = new ImageIcon(getClass().getResource("/Images/bombbang_right_1.png")).getImage();
                }
                if (isImpactBox(x, y-90, 45, 135, b)){
                    img_up = new ImageIcon(getClass().getResource("/Images/bombbang_up_1.png")).getImage();
                }
                if (isImpactBox(x , y, 45, 135, b)){
                    img_down = new ImageIcon(getClass().getResource("/Images/bombbang_down_1.png")).getImage();
                }
                //Todo: nếu chạm ở thân tia lửa
                if (isImpactBox(x - 45, y, 90, 45, b) && b.typeBox == true){
                    img_left = new ImageIcon(getClass().getResource("/Images/bombbang_left.png")).getImage();
                }
                if (isImpactBox(x, y, 90, 45, b) && b.typeBox == true){
                    img_right = new ImageIcon(getClass().getResource("/Images/bombbang_right.png")).getImage();
                }
                if (isImpactBox(x, y-45, 45, 90, b) && b.typeBox == true){
                    img_up = new ImageIcon(getClass().getResource("/Images/bombbang_up.png")).getImage();
                }
                if (isImpactBox(x , y, 45, 90, b) && b.typeBox == true){
                    img_down = new ImageIcon(getClass().getResource("/Images/bombbang_down.png")).getImage();
                }
            }
        }

    }

    public void deadlineBomb(){
        if(timeline>0){
            timeline--;
        }
    }

    public int getTimeLine() {
        return timeline;
    }
    //todo: Hàm để set up tia lửa
    private boolean isImpactBox(int x, int y, int width, int height, Box box){
        Rectangle rec1 = new Rectangle(x, y, width, height);
        Rectangle rec2 = new Rectangle(box.get_x(), box.get_y(), box.getWidth(), box.getHeight());
        return rec1.intersects(rec2);
    }


    @Override
    public void Draw(Graphics2D graphics2D) {
        graphics2D.drawImage(img_left, _x+45-img_left.getWidth(null), _y,null);
        graphics2D.drawImage(img_right, _x, _y,null);
        graphics2D.drawImage(img_up, _x, _y+45-img_up.getHeight(null),null);
        graphics2D.drawImage(img_down, _x, _y,null);
    }

    @Override
    public int impact(Entities e) {
        //Todo: Va chạm với nhân vật
        if(e instanceof Actor){
            Rectangle rec1 = new Rectangle(_x+45-img_left.getWidth(null)+5, _y+5, img_left.getWidth(null)-5, img_left.getHeight(null)-10);
            Rectangle rec2 = new Rectangle(_x, _y+5, img_right.getWidth(null)-5, img_right.getHeight(null)-10);
            Rectangle rec3 = new Rectangle(_x+5, _y+45-img_up.getHeight(null)+5, img_up.getWidth(null)-5, img_up.getHeight(null)-10);
            Rectangle rec4 = new Rectangle(_x+5, _y, img_down.getWidth(null)-10, img_down.getHeight(null)-5);
            Rectangle rec5 = new Rectangle(e.get_x(), e.get_y(), e.getWidth(), e.getHeight());
            if(rec1.intersects(rec5) || rec2.intersects(rec5) || rec3.intersects(rec5) || rec4.intersects(rec5)){
                return 1;
            }
            return 0;
        }
        //Todo: va chạm với Bomb khác
        if(e instanceof Bomb){
            Rectangle rec1 = new Rectangle(_x+45-img_left.getWidth(null), _y, img_left.getWidth(null), img_left.getHeight(null));
            Rectangle rec2 = new Rectangle(_x, _y, img_right.getWidth(null), img_right.getHeight(null));
            Rectangle rec3 = new Rectangle(_x, _y+45-img_up.getHeight(null), img_up.getWidth(null), img_up.getHeight(null));
            Rectangle rec4 = new Rectangle(_x, _y, img_down.getWidth(null), img_down.getHeight(null));
            Rectangle rec5 = new Rectangle(e.get_x(), e.get_y(), e.getWidth(), e.getHeight());
            if(rec1.intersects(rec5) || rec2.intersects(rec5) || rec3.intersects(rec5) || rec4.intersects(rec5)){
                return 1;
            }
            return 0;
        }
        //Todo : va chạm với Box
        if(e instanceof Box){
            if(((Box) e).typeBox == true){
                return 0;
            }
            Rectangle rec1 = new Rectangle(_x+45-img_left.getWidth(null), _y, img_left.getWidth(null), img_left.getHeight(null));
            Rectangle rec2 = new Rectangle(_x, _y, img_right.getWidth(null), img_right.getHeight(null));
            Rectangle rec3 = new Rectangle(_x, _y+45-img_up.getHeight(null), img_up.getWidth(null), img_up.getHeight(null));
            Rectangle rec4 = new Rectangle(_x, _y, img_down.getWidth(null), img_down.getHeight(null));
            Rectangle rec5 = new Rectangle(e.get_x(), e.get_y(), e.getWidth(), e.getHeight());
            if(rec1.intersects(rec5) || rec2.intersects(rec5) || rec3.intersects(rec5) || rec4.intersects(rec5)){
                return 1;
            }
            return 0;
        }
        //Todo : va chạm với Item
        if(e instanceof Item){
            Rectangle rec1 = new Rectangle(_x+45-img_left.getWidth(null), _y, img_left.getWidth(null), img_left.getHeight(null));
            Rectangle rec2 = new Rectangle(_x, _y, img_right.getWidth(null), img_right.getHeight(null));
            Rectangle rec3 = new Rectangle(_x, _y+45-img_up.getHeight(null), img_up.getWidth(null), img_up.getHeight(null));
            Rectangle rec4 = new Rectangle(_x, _y, img_down.getWidth(null), img_down.getHeight(null));
            Rectangle rec5 = new Rectangle(e.get_x(), e.get_y(), e.getWidth(), e.getHeight());
            if(rec1.intersects(rec5) || rec2.intersects(rec5) || rec3.intersects(rec5) || rec4.intersects(rec5)){
                if(((Item) e).getTimeline()>0){
                    ((Item) e).setTimeline(((Item) e).getTimeline()-1); //làm biến mất Item
                    return 0;
                }else{
                    return 1;
                }
            }
            return 0;
        }
        return 0;
    }

    public int getSizeBomBang() {
        return sizeBomBang;
    }

    public void setSizeBomBang(int sizeBomBang) {
        if(sizeBomBang >= 3)
            return;
        this.sizeBomBang = sizeBomBang;
    }

    public int getTimeline() {
        return timeline;
    }

    public void setTimeline(int timeline) {
        this.timeline = timeline;
    }
}
