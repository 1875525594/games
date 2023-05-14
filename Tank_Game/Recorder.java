package Tank_Game;

import java.io.*;
import java.util.Vector;

public  class Recorder {
    private static int allEnemyNum = 0;
    //定义IO文件路径"d:\\tank.txt"，（有时候因为权限问题无法访问），则可以定义在src下
    //private static String recordPath="d:\\tank.txt";
    //有bug？？？？？
    private static String recordPath="src\\tank.txt";
    static BufferedWriter  bufferedWriter=null;
    static Vector<Enemy> enemies = null;
    //定义历史集合
    static Vector<Node> nodes=new Vector<>();

    static BufferedReader bufferedReader=null;

    //读取文件获得存档记录,开始游戏是启动该方法
    public static Vector<Node> getNodes() {
        try {
            //获取文件信息
            bufferedReader = new BufferedReader(new FileReader(recordPath));
            //parseInt(bufferedReader.readLine())将字符串参数解析为带符号的十进制整数。
            allEnemyNum=Integer.parseInt(bufferedReader.readLine());

            String line="";//一行，25 5 0
            while ((line=bufferedReader.readLine())!=null){
               //split(" ")：将此字符串拆分为给定的regular expression的匹配。
               //"boo:and:foo"--->split(":")--->{ "boo", "and", "foo" }
                //"boo:and:foo"--->split("o")--->{ "b", "",":and:f" }
                String[] xyd = line.split(" ");//int x ,int y,int direct
                Node node = new Node(Integer.parseInt(xyd[0]),Integer.parseInt(xyd[1]),Integer.parseInt(xyd[2]));
                nodes.add(node);//把取出来的加入集合
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return nodes;
    }//getNodes()

    //返回存档文件路径
    public static String getRecordPath() {
        return recordPath;
    }

    //提供set方法获得TankPanel的Vector<Enemy>集合,去TankPanel传入enemies即可
    public static void setEnemies(Vector<Enemy> enemies) {
        Recorder.enemies = enemies;
    }

    //保存记录在"d\\tank.txt"
    public static void keepRecord(){
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(recordPath));
            bufferedWriter.write(allEnemyNum+"\r\n");
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                if (enemy.isLive){
                    String record =enemy.getX()+" "
                            +enemy.getY()+" "+enemy.getDirect();
                    //取到后写入文件，写完一个敌人信息，就换行
                    bufferedWriter.write(record+"\r\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter!=null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int getAllEnemyNum() {
        return allEnemyNum;
    }

    public static void setAllEnemyNum(int allEnemyNum) {
        Recorder.allEnemyNum  = allEnemyNum;
    }
    public static void addNum(){
        Recorder.allEnemyNum++;
    }
}
