package gui;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import sound.GameSound;

public class GUI extends JFrame{
    public static final int WIDTHJF = 1005;  //Thiết lập chiều rộng của cửa sổ game
    public static final int HEIGHTJF = 1030; //Thiết lập chiều dài của cửa sổ game
    private MyContainer mContainer;         //gồm các thanh menu

    public GUI() {
        setSize(WIDTHJF, HEIGHTJF);
        setLayout(new CardLayout());
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mContainer = new MyContainer(this);
        add(mContainer);
        addWindowListener(mwindow); //Tạo sự kiện khi tắt chương trình
    }

    private WindowAdapter mwindow = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            GameSound.getIstance().stop();
            PlayGame.IS_RUNNING = false;
        }
    };
}
