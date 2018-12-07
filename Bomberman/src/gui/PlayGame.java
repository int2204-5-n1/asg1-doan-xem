package gui;

import bomb.Bomber;
import bomb.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;



public class PlayGame extends JPanel implements Runnable{
    public static boolean IS_RUNNING=true;
    private MyContainer mContainer;
    private BitSet traceKey = new BitSet();
    private Manager mMagager = new Manager();
    private int count=0;
    private int deadlineBomb=0;
    private int move=0;
    private int timeDead=0;
    private int timeLose=0;
    private int timeNext=0;
    private JButton btn_Menu;

    public PlayGame(MyContainer mContainer) {
        this.mContainer = mContainer;
        setBackground(Color.WHITE);
        setLayout(null);
        setFocusable(true);
        addKeyListener(keyAdapter);
        Thread mytheard = new Thread(this);
        mytheard.start();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new java.awt.BasicStroke(2));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        mMagager.draWBackground(g2d);
        mMagager.drawAllItem(g2d);
        mMagager.drawAllBomb(g2d);
        mMagager.drawAllBox(g2d);
        mMagager.drawAllMonster(g2d);
        mMagager.getmBomber().Draw(g2d);
        //mMagager.drawInfo(g2d);
        //mMagager.drawBoss(g2d);
        if(mMagager.getStatus()==1){    //thua màn
            mMagager.drawDialog(g2d, 1);
        }
        if(mMagager.getStatus()==2){    //Qua màn
            mMagager.drawDialog(g2d, 2);
        }
        if(mMagager.getStatus()==3){    //Win
            mMagager.drawDialog(g2d, 3);
        }
    }

    private KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            traceKey.set(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            traceKey.clear(e.getKeyCode());
        }
    };

    @Override
    public void run() {
        while(IS_RUNNING){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(traceKey.get(KeyEvent.VK_LEFT) || traceKey.get(KeyEvent.VK_A)){
                mMagager.getmBomber().changeOrient(1);
                mMagager.getmBomber().move(count,mMagager.getmBomber().getSpeedBomber(), mMagager.getArrBomb(),mMagager.getArrBox());

            }
            if(traceKey.get(KeyEvent.VK_RIGHT) || traceKey.get(KeyEvent.VK_D)){
                mMagager.getmBomber().changeOrient(2);
                mMagager.getmBomber().move(count,mMagager.getmBomber().getSpeedBomber(), mMagager.getArrBomb(),mMagager.getArrBox());
            }
            if(traceKey.get(KeyEvent.VK_UP) || traceKey.get(KeyEvent.VK_W)){
                mMagager.getmBomber().changeOrient(3);
                mMagager.getmBomber().move(count,mMagager.getmBomber().getSpeedBomber(), mMagager.getArrBomb(),mMagager.getArrBox());
            }
            if(traceKey.get(KeyEvent.VK_DOWN) || traceKey.get(KeyEvent.VK_S)){
                mMagager.getmBomber().changeOrient(4);
                mMagager.getmBomber().move(count,mMagager.getmBomber().getSpeedBomber(), mMagager.getArrBomb(),mMagager.getArrBox());
            }
            if(traceKey.get(KeyEvent.VK_SPACE)){
                mMagager.innitBomb();
                mMagager.getmBomber().set_cross(true);
            }
            mMagager.setRunBomer();
            mMagager.deadLineAllBomb();
            mMagager.checkDead();
            mMagager.checkImpactItem();
            mMagager.checkWinAndLose();

            if(mMagager.getStatus()==1){    //Thua
                timeLose++;
                if(timeLose == 2000){
                    mMagager.innitManager();
                    mContainer.setShowMenu();
                    timeLose=0;
                }
            }

            if(mMagager.getStatus()==2){    //Qua màn
                timeNext++;
                if(timeNext==2000){
                    mMagager.innitManager();
                    timeNext=0;
                }
            }

            if(mMagager.getStatus()==3){    //Win
                timeNext++;
                if(timeNext==2000){
                    mMagager.innitManager();
                    mContainer.setShowMenu();
                    timeNext=0;
                }
            }

            if(mMagager.getmBomber().isALIVE() == false && mMagager.getmBomber().get_heart() != 0){ //(==0)
                timeDead++;
                if(timeDead==2000){
                    mMagager.setNewBomber();
                    timeDead=0;
                }
            }

            if(move==0){
                mMagager.changeOrientAll();
                move=2000;
            }
            if(move>0){
                move--;
            }
            mMagager.moveAllMonster(count);
            count++;
            repaint();
            if(count==10){
                count=0;
            }
        }

    }

}

