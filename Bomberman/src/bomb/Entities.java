package bomb;

import java.awt.*;

public abstract class Entities {
    protected int _x, _y;
    protected int _width, _height;
    protected Image _image ;

    public abstract void Draw(Graphics2D graphics2D);
    public abstract int impact(Entities e);


    public int get_x() {
        return _x;
    }

    public void set_x(int _x) {
        this._x = _x;
    }

    public int get_y() {
        return _y;
    }

    public void set_y(int _y) {
        this._y = _y;
    }

    public int getWidth() {
        return _width;
    }

    public void setWidth(int width) {
        this._width = width;
    }

    public int getHeight() {
        return _height;
    }

    public void setHeight(int height) {
        this._height = height;
    }

    public Image getImage() {
        return _image;
    }

    public void setImage(Image image) {
        this._image = image;
    }
}
