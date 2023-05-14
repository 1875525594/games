package Tank_Game;

import java.util.Vector;

public class Hero extends Tank {

    //构造器
    public Hero(int x, int y) {
        super(x, y);
    }

    /**创建shot对象，只有一颗子弹*/
    Shot shot = null;

    /**可以发射多颗子弹*/
    Vector<Shot> shots =new Vector<>();

    //英雄射击
    public void shotEnemy(){
        //控制hero最多只能发射5颗,限定了集合容量为5，
        // 最多只能new5个，等待remove才能有空位
        if (shots.size()==5){
            return;
        }
        //子弹绑定hero
        switch (getDirect()){//hero.shotEnemy()----->getDirect()
            case 0:
                //每按下J，都创建了一个新的子弹对象，并且与hero绑定位置，之前的子弹就被回收了
                shot=new Shot(getX()+20,getY(),getDirect());//绑定hero方向
                break;
            case 1:
                shot=new Shot(getX()+60,getY()+20,getDirect());
                break;
            case 2:
                shot=new Shot(getX()+20,getY()+60,getDirect());
                break;
            case 3:
                shot=new Shot(getX(),getY()+20,getDirect());
                break;
        }

        //把新建的shot加入集合
        shots.add(shot);
        //启动shot线程
        new Thread(shot).start();
    }
}
