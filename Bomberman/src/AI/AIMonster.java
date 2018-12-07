package AI;

import bomb.*;

import java.util.ArrayList;
import java.util.Random;

public class AIMonster {
    Bomber bomber;
    Monsters monsters;
    BombBang bombBang;
    ArrayList<Bomb>arrBomb;
    ArrayList<Box>arrBox;
    Random random = new Random();
    public AIMonster(Bomber bomber, Monsters monsters , ArrayList<Bomb>arrBomb, ArrayList<Box>arrBox, BombBang bombBang){
        this.bomber = bomber;
        this.monsters = monsters;
        this.bombBang = bombBang;
        this.arrBomb = arrBomb;
        this.arrBox = arrBox;
    }


    //Todo: Đoán hướng theo cột
    protected int calculateRowDirection() {
        int xBomber = bomber.get_x()/45;
        int xMonster =  monsters.get_x()/45;
        if(xBomber < xMonster)
            return 1;
        else if(xBomber > xMonster)
            return 2;
        for(Bomb b : arrBomb){
            int xBomb = b.get_x()/45;
            int yBomn = b.get_y()/45;
            if(xMonster - xBomb/45 <= bombBang.getSizeBomBang()*45)
                return 2;
            if(xBomb/45 - xMonster <= bombBang.getSizeBomBang()*45)
                return 1;
        }
        return 0;
    }

    protected int calculateColDirection() {
        int yBomber = (bomber.get_y()-20)/45;
        int yMonster = (monsters.get_y()-23)/45;
        if(yBomber < yMonster)
            return 3;
        else if(yBomber > yMonster)
            return 4;
        return 0;
    }


    public int avoidBomb(int xBomb, int yBomb){
        //Todo: Check bom bên trên bomber
        int xEnemy = monsters.get_x()/45;
        int yEnemy = (monsters.get_y()-23)/45;
        if(xEnemy - xBomb/45 <= bombBang.getSizeBomBang()*45)
            return 2;
        if(xBomb/45 - xEnemy <= bombBang.getSizeBomBang()*45)
            return 1;
        if(yEnemy - yBomb/45 <= bombBang.getSizeBomBang()*45)

            return 4;
        if(yBomb/45 - yEnemy <= bombBang.getSizeBomBang()*45)
            return 3;
        return -1;
    }

    public int Direction(ArrayList<Bomb>arrBomb){
        if(bomber == null)
            return random.nextInt(4);
        for(Bomb b : arrBomb){
            if(avoidBomb(b.get_x(), b.get_y())!=-1)
                return avoidBomb(b.get_x(), b.get_y());
        }

        int vertical = random.nextInt(2);
        if(vertical == 1) {
            int v = calculateRowDirection();
            if(v != 0)
                return v;
            else
                return calculateColDirection();
        } else {
            int h = calculateColDirection();
            if(h != 0)
                return h;
            else
                return calculateRowDirection();
        }
    }
}
