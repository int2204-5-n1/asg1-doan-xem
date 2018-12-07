package bomb;

import javax.swing.*;
import java.awt.*;

public class Item extends Entities {
    protected int timeline;
    protected int typeItem;
    public static final int Item_BombAmount = 1;
    public static final int Item_BombSize = 2;
    public static final int Item_Shoe = 3;

    public Item(int x, int y, int typeItem, String image){
        _x = x;
        _y = y;
        this._image = new ImageIcon(getClass().getResource(image)).getImage();
        _width = _image.getWidth(null);
        _height = _image.getHeight(null);
        this.typeItem = typeItem;
        this.timeline = 250;
    }

    @Override
    public void Draw(Graphics2D graphics2D) {
        graphics2D.drawImage(_image, _x, _y, null);
    }

    @Override
    public int impact(Entities e) {
        if(e instanceof Bomber) {
            Rectangle rec1 = new Rectangle(_x, _y, _width, _height);
            Rectangle rec2 = new Rectangle(e.get_x(), e.get_y(), e.getWidth(), e.getHeight());
            return rec1.intersects(rec2) ? 1 : 0;
        }
        return 0;
    }

    public int getTimeline() {
        return timeline;
    }

    public void setTimeline(int timeline) {
        this.timeline = timeline;
    }

    public int getTypeItem() {
        return typeItem;
    }

    public void setTypeItem(int typeItem) {
        this.typeItem = typeItem;
    }
}
