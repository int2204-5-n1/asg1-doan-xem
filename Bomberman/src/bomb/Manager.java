package bomb;

import AI.AIMonster;
import AI.superAI;
import sound.GameSound;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Manager {
    private Bomber bomber;
    private Bomb bomb = new Bomb(1);
    private BombBang bombBang = new BombBang(1) ;
    private ArrayList<Box>arrBox;
    private ArrayList<Bomb>arrBomb;
    private ArrayList<Item>arrItem;
    private ArrayList<Monsters>arrMonster;
    private ArrayList<BombBang> arrBombBang;
    private String Background;
    private int round = 3;
    private int nextRound = 0;
    private int status = 0; // (1==thua) (2==nextRound) (3==Win)
    private Random random = new Random();
    private AIMonster aiMonster;
    private superAI superAI;
    public Manager(){
        innitManager();
    }


    public void innitManager() {
        switch (round) {
            case 1:
                bomber = new Bomber(45, 900, 1 , Actor.DOWN);
                innit("src/Map1/BOX.txt", "src/Map1/MONSTER.txt", "src/Map1/ITEM.txt");
                nextRound = 0;
                status = 0;
                break;
            case 2:
                bomber = new Bomber(315, 270, 3 , Actor.DOWN);
                innit("src/Map2/BOX.txt", "src/Map2/MONSTER.txt", "src/Map2/ITEM.txt");
                aiMonster = new AIMonster(bomber, arrMonster.get(0), arrBomb, arrBox, bombBang);
                nextRound = 0;
                status = 0;
                break;
            case 3:
                bomber = new Bomber(315, 495, 3 , Actor.DOWN);
                innit("src/Map3/BOX.txt", "src/Map3/MONSTER.txt", "src/Map3/ITEM.txt");
                aiMonster = new AIMonster(bomber, arrMonster.get(0), arrBomb, arrBox, bombBang);
                nextRound = 0;
                status = 0;
                break;

            default:
                break;
        }

    }
    //-----------------------------------------------------------------------------------------------------//
    /**
     *      PHẦN KHỞI TẠO BẢN ĐỒ
     */
    //-----------------------------------------------------------------------------------------------------//
    //Todo: Hàm khởi tạo map từ file đọc vào
    public void innit(String pathBox, String pathMonster, String pathItem) {
        arrBox = new ArrayList<>();
        arrBomb = new ArrayList<>();
        arrBombBang = new ArrayList<>();
        arrMonster = new ArrayList<>();
        arrItem = new ArrayList<>();

        innitArrBox(pathBox);
        initArrMonster(pathMonster);
        innitArrItem(pathItem);
    }
    //Todo: Đọc các Item
    public void innitArrItem(String path) {
        try {
            FileReader file = new FileReader(path);
            BufferedReader input = new BufferedReader(file);
            String line;
            while ((line = input.readLine()) != null) {
                String str[] = line.split(":");
                int x = Integer.parseInt(str[0]);   // số đầu tiên của tệp là tọa độ x
                int y = Integer.parseInt(str[1]);   // số thứ hai là tọa độ y
                int type = Integer.parseInt(str[2]);    //số thứ 3 là loại Item
                String images = str[3]; //String là tên của image
                Item item = new Item(x, y, type, images);
                arrItem.add(item);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Todo: Đọc các Box
    public void innitArrBox(String pathBox) {
        try {
            FileReader file = new FileReader(pathBox);
            BufferedReader input = new BufferedReader(file);
            Background = input.readLine();
            String line;
            while ((line = input.readLine()) != null) {
                String str[] = line.split(":");
                int x = Integer.parseInt(str[0]);
                int y = Integer.parseInt(str[1]);
                int type = Integer.parseInt(str[2]);
                String images = str[3];
                Box box = new Box(x, y, type==1?true:false, images);
                arrBox.add(box);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Todo: Đọc các vị trí Monster từ tệp
    public void initArrMonster(String path) {
        try {
            FileReader file = new FileReader(path);
            BufferedReader input = new BufferedReader(file);
            String line;
            while ((line = input.readLine()) != null) {
                String str[] = line.split(":");
                int x = Integer.parseInt(str[0]);
                int y = Integer.parseInt(str[1]);
                int type = Integer.parseInt(str[2]);
                int orient = Integer.parseInt(str[3]);
                int speed = Integer.parseInt(str[4])-3;
                int heart = Integer.parseInt(str[5]);
                String images = str[6];
                Monsters monster = new Monsters(x, y, type,orient, speed, heart, images);
                arrMonster.add(monster);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Todo: Đặt Bomb nếu được
    public void innitBomb() {
        if (bomber.ALIVE == false) {
            return;
        }
        int x = (bomber.get_x()/45)*45;   //để đặt bom đúng vị trí cần đặt
        int y = (bomber.get_y()/45)*45;
        for (int i = 0; i < arrBomb.size(); i++) {
            if (arrBomb.get(i).impactVsBomb(new Bomb(x,y))) {
                return;
            }
        }
        if (arrBomb.size() >= bomb.amountBomb) {
            return;
        }
        GameSound.getIstance().getAudio(GameSound.BOMB).play();
        Bomb mBomb = new Bomb(x, y, bomb.amountBomb, 1500);
        arrBomb.add(mBomb);
    }
    //--------------------------------------------------------------------------------------------------//
    /**
     *      PHẦN IN RA CÁC THỨ CẦN TRÊN BẢN ĐỒ
     */
    //--------------------------------------------------------------------------------------------------//
    //Todo: In background
    public void draWBackground(Graphics2D graphics2D) {
        Image imgBackground = new ImageIcon(getClass().getResource(Background)).getImage();
        graphics2D.drawImage(imgBackground, 0, 0, null);
    }
    //Todo: In thông tin (số lượt chơi, điểm)
//    public void drawInfo(Graphics2D graphics2D) {
//        Image imgInfor = new ImageIcon(getClass().getResource(
//                "/Images/background_Info.png")).getImage();
//        graphics2D.setFont(new Font("Arial", Font.BOLD, 20));
//        graphics2D.setColor(Color.RED);
//        graphics2D.drawImage(imgInfor, 1350, 0, null);
//        graphics2D.drawString("HEART", 755, 100);
//        Image heart = new ImageIcon(getClass().getResource(
//                "/Images/heart_1.png")).getImage();
//        if (bomber._heart == 3) {
//            graphics2D.drawImage(heart, 750, 120, null);
//            graphics2D.drawImage(heart, 775, 120, null);
//            graphics2D.drawImage(heart, 800, 120, null);
//        }
//        if (bomber._heart == 2) {
//            graphics2D.drawImage(heart, 760, 120, null);
//            graphics2D.drawImage(heart, 790, 120, null);
//        }
//        if (bomber._heart == 1) {
//            graphics2D.drawImage(heart, 775, 120, null);
//        }
//
//        graphics2D.drawString("SCORE : " + bomber._point, 740, 200);
//    }
    //Todo: In các Item
    public void drawAllItem(Graphics2D graphics2D) {
        for (int i = 0; i < arrItem.size(); i++) {
            arrItem.get(i).Draw(graphics2D);
        }
    }
    //Todo: In các Box
    public void drawAllBox(Graphics2D graphics2D) {
        for (int i = 0; i < arrBox.size(); i++) {
            arrBox.get(i).Draw(graphics2D);
        }
    }
    //Todo: In các vị trí có Monster
    public void drawAllMonster(Graphics2D graphics2D) {
        for (int i = 0; i < arrMonster.size(); i++) {
            arrMonster.get(i).Draw(graphics2D);
        }
    }
    //Todo: In các vị trí có bom
    public void drawAllBomb(Graphics2D graphics2D) {
        for (int i = 0; i < arrBomb.size(); i++) {
            arrBomb.get(i).Draw(graphics2D);
        }
        for (int i = 0; i < arrBombBang.size(); i++) {
            arrBombBang.get(i).Draw(graphics2D);
        }
    }
    //Todo: In Thông báo
    public void drawDialog(Graphics2D graphics2D, int type) {
        graphics2D.setFont(new Font("Arial", Font.BOLD, 70));
        graphics2D.setColor(Color.RED);
        if(type==1){
            graphics2D.drawString("You Lose !!!", 200, 250);
        }else if(type==2){
            graphics2D.drawString("Round "+round, 200, 250);
        }else{
            graphics2D.drawString("You Win !!!", 200, 250);
        }
    }

    //-------------------------------------------------------------------------------------//
    /**
     *      PHẦN CHECK
     */
    //-------------------------------------------------------------------------------------//
    //Todo: Check ăn Item
    public void checkImpactItem() {
        for (int i = 0; i < arrItem.size(); i++) {
            if (arrItem.get(i).impact(bomber) == 1) {
                GameSound.instance.getAudio(GameSound.ITEM).play();
                if (arrItem.get(i).getTypeItem() == Item.Item_BombAmount) {
                    bomb.setAmountBomb(bomb.getAmountBomb()+1);
                    arrItem.remove(i);
                    break;
                }
                if (arrItem.get(i).getTypeItem() == Item.Item_BombSize) {
                    bombBang.setSizeBomBang(bombBang.getSizeBomBang() + 1);
                    arrItem.remove(i);
                    break;
                }
                if (arrItem.get(i).getTypeItem() == Item.Item_Shoe) {
                    bomber.setSpeedBomber(bomber.getSpeedBomber() - 1);
                    arrItem.remove(i);
                    break;
                }
            }
        }
    }
    //Todo: Check Die
    public void checkDead() {
        for (int i = 0; i < arrBombBang.size(); i++) {
            if (arrBombBang.get(i).impact(bomber) == 1  && bomber.ALIVE) {
                Image icon = new ImageIcon(getClass().getResource(
                        "/Images/ghost.png")).getImage();
                bomber.setImage(icon);
                if (!bomber.ALIVE) {
                    return;
                }
                bomber.set_heart(bomber.get_heart() - 1);
                bomber.ALIVE = false;
                GameSound.instance.getAudio(GameSound.BOMBER_DIE).play();
            }
        }
        for (int i = 0; i < arrMonster.size(); i++) {
            if (bomber.impact(arrMonster.get(i)) == 1) {
                Image icon = new ImageIcon(getClass().getResource(
                        "/Images/ghost.png")).getImage();
                bomber.setImage(icon);
                if (!bomber.ALIVE) {
                    return;
                }
                bomber.set_heart(bomber.get_heart() - 1);
                bomber.ALIVE = false;
                if(bomber.get_heart() != 0)
                    GameSound.instance.getAudio(GameSound.BOMBER_DIE).play();
            }
        }
    }
    //Todo: Check thắng or thua
    public void checkWinAndLose() {
        if (bomber.get_heart() == 0 && nextRound == 0) {
            round = 1;
            status = 1;
            nextRound++;
            GameSound.getIstance().getAudio(GameSound.PLAYGAME).stop();
            GameSound.getIstance().getAudio(GameSound.LOSE).play();
        }
        if (arrMonster.size() == 0 && nextRound == 0) {
            if (round == 3) {
                status = 3;
                nextRound++;
                GameSound.getIstance().getAudio(GameSound.PLAYGAME).stop();
                GameSound.getIstance().getAudio(GameSound.WIN).play();
                round = 1;
                return;
            }
            round++;
            nextRound++;
            status = 2;
        }
    }
    //--------------------------------------------------------------------------------------------//
    /**
     *      XỬ lÍ BOM NỔ
     */

    //--------------------------------------------------------------------------------------------//
    //Todo: Xử lí bom
    public void deadLineAllBomb() {
        for (int i = 0; i < arrBomb.size(); i++) {
            arrBomb.get(i).deadlineBomb();
            if (arrBomb.get(i).getTimeLine() == 250) {
                BombBang bomBang = new BombBang(arrBomb.get(i).get_x(), arrBomb
                        .get(i).get_y(), bombBang.getSizeBomBang(), arrBox);
                GameSound.getIstance().getAudio(GameSound.BONG_BANG).play();
                arrBombBang.add(bomBang);
                arrBomb.remove(i);
            }
        }
        for(int j=0;j<arrMonster.size();j++){
            for(int i=0;i<arrBomb.size();i++){
                if(arrBomb.get(i).impactvsActor(arrMonster.get(j))==1){
                    BombBang bomBang = new BombBang(arrBomb.get(i).get_x(), arrBomb
                            .get(i).get_y(), bombBang.getSizeBomBang(), arrBox);
                    GameSound.getIstance().getAudio(GameSound.BONG_BANG).play();
                    arrBombBang.add(bomBang);
                    arrBomb.remove(i);
                }
            }
        }

        for (int i = 0; i < arrBombBang.size(); i++) {
            for (int j = 0; j < arrBomb.size(); j++) {
                if (arrBombBang.get(i).impact(arrBomb.get(j)) == 1) {
                    BombBang bomBang = new BombBang(arrBomb.get(j).get_x(),
                            arrBomb.get(j).get_y(), bombBang.getSizeBomBang(),
                            arrBox);
                    arrBombBang.add(bomBang);
                    arrBomb.remove(j);
                }
            }
        }
        //TODO : XỬ LÍ KHI TIA LỬA CHẠM VÀO MONTERS HOẶC VÀO BOSS
        for (int k = 0; k < arrBombBang.size(); k++) {
            arrBombBang.get(k).deadlineBomb();
            for (int j = 0; j < arrMonster.size(); j++) {
                if (arrBombBang.get(k).impact(arrMonster.get(j)) == 1) {
                    //TODO: BOM NỔ TRÚNG THÌ TRỪ MÁU CỦA MONSTERS HOẶC BOSS
                    if(arrMonster.get(j).get_heart()>1){
                        arrMonster.get(j).set_heart(arrMonster.get(j).get_heart()-1);
                    }else{
                        //TODO: NẾU TIA LỬA CHẠM VÀO BOSS THÌ CỘNG 10 POINT
                        if(arrMonster.get(j).get_type()== 2){
                            bomber.set_point(bomber.get_point() + 10);
                            //TODO: NẾU TIA LỬA CHẠM VÀO BOSS THÌ CỘNG 1 POINT
                        }else{
                            bomber.set_point(bomber.get_point() + 1);
                        }
                        GameSound.getIstance().getAudio(GameSound.MONSTER_DIE).play();
                        arrMonster.remove(j);
                    }
                }
            }
            if (arrBombBang.get(k).getTimeLine() == 0) {
                arrBombBang.remove(k);
            }
        }
        //TODO: XỬ LÍ KHI TIA LỬA CHẠM VÀO BOX THÌ XÓA BOX
        for (int i = 0; i < arrBombBang.size(); i++) {
            for (int j = 0; j < arrBox.size(); j++) {
                if (arrBombBang.get(i).impact(arrBox.get(j)) == 1) {
                    arrBox.remove(j);
                }
            }
        }
        //TODO: XỬ LÍ KHI TIA LỬA CHẠM VÀO ITEM THÌ XÓA LUÔN ITEM
        for (int i = 0; i < arrBombBang.size(); i++) {
            for (int j = 0; j < arrItem.size(); j++) {
                if (arrBombBang.get(i).impact(arrItem.get(j)) == 1) {
                    arrItem.remove(j);
                }
            }
        }
    }
    //--------------------------------------------------------------------------------------------//
    /**
     *      XỬ lÍ TẠO BOMBER MỚI
     */
    //--------------------------------------------------------------------------------------------//
    //Kiểm tra Bomber khi đặt bom
    public void setRunBomer() {
        if (arrBomb.size() > 0) {
            if (arrBomb.get(arrBomb.size() - 1).impactVsBomber(bomber)== false) {
                bomber.set_cross(false);
            }
        }
    }
    //Đặt bom mới
    public void setNewBomber() {
        switch (round) {
            case 1:
                bomber.setNew(45, 900);
                break;
            case 2:
                bomber.setNew(315, 270);
                break;
            case 3:
                bomber.setNew(315, 495);
                break;

            default:
                break;
        }
    }
    //--------------------------------------------------------------------------------------------//
    /**
     *      XỬ lÍ AI CHO ENEMY
     */
    //--------------------------------------------------------------------------------------------//
    //Cài hướng (AI) cho các enemy
    public void changeOrientAll() {
        for (int i = 0; i < arrMonster.size(); i++) {
            int orient = random.nextInt(4) + 1;
//            aiMonster = new AIMonster(bomber, arrMonster.get(0), arrBomb, arrBox, bombBang);
//            int orient = aiMonster.Direction(arrBomb);
            arrMonster.get(i).changeOrient(orient);
//            superAI = new superAI(bomber, arrMonster.get(0), bombBang);
//            int orient = superAI.Direction();
//            if(orient != -1)
//                arrMonster.get(i).changeOrient(orient);
//            else
//                arrMonster.get(i).changeOrient(random.nextInt(4)+1);
        }
    }
    //Di chuyển tất cả các enemy
    public void moveAllMonster(int count) {
        for (int i = 0; i < arrMonster.size(); i++) {
            int speed = arrMonster.get(i).getSpeedMonster()-3;
            if (arrMonster.get(i).move(count,speed, arrBomb, arrBox) == false) {
                int orient = random.nextInt(4) + 1;
//                aiMonster = new AIMonster(bomber, arrMonster.get(0), arrBomb, arrBox, bombBang);
//                int orient = aiMonster.Direction(arrBomb);
                arrMonster.get(i).changeOrient(orient);
//                superAI = new superAI(bomber, arrMonster.get(0), bombBang);
//                int orient = superAI.Direction();
//                if(orient != -1)
//                    arrMonster.get(i).changeOrient(orient);
//                else
//                    arrMonster.get(i).changeOrient(random.nextInt(4)+1);
//            }
            }
        }
    }
    public ArrayList<Box> getArrBox() {
        return arrBox;
    }

    public ArrayList<Bomb> getArrBomb() {
        return arrBomb;
    }

    public Bomber getmBomber() {
        return bomber;
    }

    public int getStatus() {
        return status;
    }

    public void setRound(int round) {
        this.round = round;
    }
}
