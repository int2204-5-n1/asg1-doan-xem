package AI;

import bomb.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class superAI {
    Bomber bomber;
    Monsters monsters;
    BombBang bombBang;
    Bomb bomb;


    public int width = 22, height = 22;
    public String [] map = new String[22];
    public ArrayList<Integer>pathEnemy = new ArrayList<>();
    public int[][] node = new int[(width)*(height)][5];
    public int number = width*height;
    public int[][] matrix = new int[width][height];
    public Random random = new Random();
//    public Manager manager = new Manager();
    public superAI(){}

    public superAI(Bomber b, Monsters monsters, BombBang bang){
        this.bomber = b;
        this.monsters = monsters;
        this.bombBang = bang;
//        this. bomb = bomb;
    }

    public void init(){
        map[0] =    "######################\0";
        map[1] =    "#                    #\0";
        map[2] =    "# @@@@@@@@@ @@@@@    #\0";
        map[3] =    "# @  @         @     #\0";
        map[4] =    "# @   @        @     #\0";
        map[5] =    "# @@   @      @      #\0";
        map[6] =    "# @ @   @    @   @@@ #\0";
        map[7] =    "# @  @   @  @   @  @ #\0";
        map[8] =    "# @       @@   @   @ #\0";
        map[9] =    "#     @   @@  @    @ #\0";
        map[10] =   "#       @@  @@     @ #\0";
        map[11] =   "#       @@  @@       #\0";
        map[12] =   "# @    @  @   @    @ #\0";
        map[13] =   "# @   @   @@   @     #\0";
        map[14] =   "# @  @   @  @   @  @ #\0";
        map[15] =   "# @@@   @        @ @ #\0";
        map[16] =   "#      @      @   @@ #\0";
        map[17] =   "#     @   @    @   @ #\0";
        map[18] =   "#     @         @  @ #\0";
        map[19] =   "#     @@@@@ @@@@@@@@ #\0";
        map[20] =   "#                    #\0";
        map[21] =   "######################\0";
    }

    public void processMap(){
        int index = 1;
        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (map[i].charAt(j) == '#') {
                    matrix[i][j] = 0;
                } else if (map[i].charAt(j) == '@') {
                    matrix[i][j] = (-1) * index;
                    index++;
                } else {
                    matrix[i][j] = index;
                    index++;
                }
            }
        }
    }

    public void updateTheMap(){

    }

    public void changeMap(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(matrix[i][j] > 0){
                    //Trái
                    if(matrix[i-1][j] > 0){     //nếu bên tay trái có thể đi được
                        node[matrix[i][j]][0] = matrix[i-1][j];  //đánh dấu có thể đi được (sang bên trái)
                    }
                    else node[matrix[i][j]][0] = 0;

                    //Phải
                    if(matrix[i+1][j] > 0){     //nếu bên tay phải có thể đi được
                        node[matrix[i][j]][1] = matrix[i+1][j];  //đánh dấu có thể đi được (sang bên phải)
                    }
                    else node[matrix[i][j]][1] = 0;

                    //Trên
                    if(matrix[i][j-1] > 0){     //nếu bên trên có thể đi được
                        node[matrix[i][j]][2] = matrix[i][j-1];  //đánh dấu có thể đi được (lên trên)
                    }
                    else node[matrix[i][j]][2] = 0;

                    //Dưới
                    if(matrix[i][j+1] > 0){     //nếu bên dưới có thể đi được
                        node[matrix[i][j]][3] = matrix[i][j+1];  //đánh dấu có thể đi được (xuống dưới)
                    }
                    else node[matrix[i][j]][3] = 0;
                }
            }
        }
    }

    public void checkBomb(int xBomb, int yBomb){
        int radius = bombBang.getSizeBomBang();

        int xEnemy = monsters.get_x()/45;
        int yEnemy = (monsters.get_y()-23) /45 ;

        int xBomber = bomber.get_x()/45;
        int yBomber = (bomber.get_y()-20)/45;
        int xB = xBomb/45;
        int yB = yBomb/45;
        matrix[xB][yB] *= (-1);
        //Xét tia lửa nếu enemy ở bên trái bomb
        for (int i = 0; i < radius; i++) {
            if (matrix[xB-i][yB] > 0 && xB - xEnemy >= i){
                matrix[xB-i][yB] *= (-1);
            }
        }
        //Xét tia lửa nếu enemy ở bên phải bomb
        for (int i = 0; i < radius; i++) {
            if (matrix[xB+i][yB] > 0 && xEnemy - xB >= i){
                matrix[xB+i][yB] *= (-1);
            }
        }
        //Xét tia lửa nếu enemy ở bên trên bomb
        for (int i = 0; i < radius; i++) {
            if (matrix[xB][yB-i] > 0 && yB - yEnemy >= i){
                matrix[xB][yB-i] *= (-1);
            }
        }
        //Xét tia lửa nếu enemy ở bên dưới bomb
        for (int i = 0; i < radius; i++) {
            if (matrix[xB][yB+i] > 0 && yEnemy - yB >= i){
                matrix[xB][yB+i] *= (-1);
            }
        }
    }

    int useBFS(int start, int end){
        Queue<Integer> Node = new LinkedList<>();

        int [] parent = new int[number+1];
        boolean [] visted = new boolean[number+1];
        visted[start] = false;
        parent[start] = -1; // sét mặc định đỉnh cha
        parent[end] = -1;


        Node.add(start);
        while (!Node.isEmpty()){
            int current = Node.poll();
            for(int i = 0; i < 4; i++){
                if ( visted[node[current][i]] == false && node[current][i] != 0 ) {

                    visted[node[current][i]]= true;

                    parent[node[current][i]] = current;

                    Node.add(node[current][i]);
                }
            }
        }

        int p = parent[end];
        // thêm node cuối
        if (p != -1){
            pathEnemy.add(end);
            pathEnemy.add(p);
            while ( p!=start){
                p = parent[p];
                pathEnemy.add(p);
            }
            // trả về vị trí thứ 2 tức làm đỉnh kế tiếp start
            return pathEnemy.get(pathEnemy.size()-2);
        }
        return  -1;
    }

    public int Direction(){
        init();
        processMap();
        //checkBomb(bomb.get_x()/45, bomb.get_y()/45);
        changeMap();
        int start = matrix[monsters.get_x()/45][(monsters.get_y()-23)/45];
        int end   = matrix[bomber.get_x()/45][(bomber.get_y()-20)/45];
        int move = useBFS(start,end);
        if(move == -1)
            return random.nextInt(4)+ 1;
        return -1;
    }
}
