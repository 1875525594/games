package Tank_Game;

import java.util.Vector;

public class Enemy extends Tank implements Runnable{
    Shot shot = null;

    public Enemy(int x, int y) {
        super(x, y);
    }
    /**子弹集合vector*/
    Vector<Shot> shots = new Vector<>();
    //
    Vector<Enemy> enemies = new Vector<>();

    //把TankPanel的enemies集合取到这里使用
    public void setEnemies(Vector<Enemy> enemies) {
        this.enemies = enemies;
    }

    //判断敌人是否重叠
    public boolean isTouchTank(){
        switch (this.getDirect()){

            case 0://当前坦克方向为上
                for (int i = 0; i < enemies.size(); i++) {
                    //取出一个对方坦克
                    Enemy enemy = enemies.get(i);
                    if (enemy!=this){//不和自己比较
                        //如果对方是上下移动
                        if (enemy.getDirect()==0||enemy.getDirect()==2){
                            //当前坦克左上角与对方
                            if (this.getX()>=enemy.getX()
                                    &&this.getX()<=enemy.getX()+40
                                    &&this.getY()>=enemy.getY()
                                    &&this.getY()<=enemy.getY()+60){
                                return true;}
                            ////当前坦克右上角与对方
                            if (this.getX()+40>=enemy.getX()
                                    &&this.getX()<=enemy.getX()
                                    &&this.getY()>=enemy.getY()
                                    &&this.getY()<=enemy.getY()+60){
                                return true;}
                        }

                        //如果对方是左右移动
                        if (enemy.getDirect()==1||enemy.getDirect()==3){
                            //当前坦克左上角与对方
                            if (    this.getX()>=enemy.getX()
                                    &&this.getX()<=enemy.getX()+40
                                    &&this.getY()>=enemy.getY()
                                    &&this.getY()<=enemy.getY()+60){
                                return true;}
                            ////当前坦克右上角与对方
                            if (    this.getX()+40>=enemy.getX()
                                    &&this.getX()+40<=enemy.getX()+60
                                    &&this.getY()>=enemy.getY()
                                    &&this.getY()<=enemy.getY()+40){
                                return true;}
                        }
                    }
                }
                break;
            case 1://当前坦克方向为右
                for (int i = 0; i < enemies.size(); i++) {
                    //取出一个对方坦克
                    Enemy enemy = enemies.get(i);
                    if (enemy!=this){//不和自己比较
                        //如果对方是上下移动
                        if (enemy.getDirect()==0||enemy.getDirect()==2){
                            //当前坦克右上角与对方
                            if (this.getX()+60>=enemy.getX()
                                    &&this.getX()+60<=enemy.getX()+40
                                    &&this.getY()>=enemy.getY()
                                    &&this.getY()<=enemy.getY()+60){
                                return true;}
                            ////当前坦克右下角与对方
                            if (this.getX()+60>=enemy.getX()
                                    &&this.getX()+60<=enemy.getX()+40
                                    &&this.getY()+40>=enemy.getY()
                                    &&this.getY()+40<=enemy.getY()+60){
                                return true;}
                        }

                        //如果对方是左右移动
                        if (enemy.getDirect()==1||enemy.getDirect()==3){
                            //当前坦克左上角与对方
                            if (    this.getX()+60>=enemy.getX()
                                    &&this.getX()<=enemy.getX()
                                    &&this.getY()>=enemy.getY()
                                    &&this.getY()<=enemy.getY()+40){
                                return true;}
                            ////当前坦克右下角与对方
                            if (    this.getX()+60>=enemy.getX()
                                    &&this.getX()<=enemy.getX()
                                    &&this.getY()+40>=enemy.getY()
                                    &&this.getY()<=enemy.getY()){
                                return true;}
                        }
                    }
                }
                break;
            case 2://当前坦克方向为上
                for (int i = 0; i < enemies.size(); i++) {
                    //取出一个对方坦克
                    Enemy enemy = enemies.get(i);
                    if (enemy!=this){//不和自己比较
                        //如果对方是上下移动
                        if (enemy.getDirect()==0||enemy.getDirect()==2){
                            //当前坦克左下角与对方
                            if (this.getX()>=enemy.getX()
                                    &&this.getX()<=enemy.getX()+40
                                    &&this.getY()+60>=enemy.getY()
                                    &&this.getY()<=enemy.getY()){
                                return true;}
                            ////当前坦克右下角与对方
                            if (this.getX()+40>=enemy.getX()
                                    &&this.getX()<=enemy.getX()
                                    &&this.getY()+60>=enemy.getY()
                                    &&this.getY()<=enemy.getY()){
                                return true;}
                        }

                        //如果对方是左右移动
                        if (enemy.getDirect()==1||enemy.getDirect()==3){
                            //当前坦克左下角与对方
                            if (    this.getX()>=enemy.getX()
                                    &&this.getX()<=enemy.getX()+60
                                    &&this.getY()+60>=enemy.getY()
                                    &&this.getY()+60<=enemy.getY()+40){
                                return true;}
                            ////当前坦克右上角与对方
                            if (    this.getX()+40>=enemy.getX()
                                    &&this.getX()+40<=enemy.getX()+60
                                    &&this.getY()+60>=enemy.getY()
                                    &&this.getY()+60<=enemy.getY()+40){
                                return true;}
                        }
                    }
                }
                break;
            case 3://当前坦克方向为左
                for (int i = 0; i < enemies.size(); i++) {
                    //取出一个对方坦克
                    Enemy enemy = enemies.get(i);
                    if (enemy!=this){//不和自己比较
                        //如果对方是上下移动
                        if (enemy.getDirect()==0||enemy.getDirect()==2){
                            //当前坦克左上角与对方
                            if (this.getX()>=enemy.getX()
                                    &&this.getX()<=enemy.getX()+40
                                    &&this.getY()>=enemy.getY()
                                    &&this.getY()<=enemy.getY()+60){
                                return true;}
                            ////当前坦克右上角与对方
                            if (this.getX()>=enemy.getX()
                                    &&this.getX()<=enemy.getX()+40
                                    &&this.getY()+40>=enemy.getY()
                                    &&this.getY()+40<=enemy.getY()+60){
                                return true;}
                        }

                        //如果对方是左右移动
                        if (enemy.getDirect()==1||enemy.getDirect()==3){
                            //当前坦克左上角与对方
                            if (    this.getX()>=enemy.getX()
                                    &&this.getX()<=enemy.getX()+60
                                    &&this.getY()>=enemy.getY()
                                    &&this.getY()<=enemy.getY()+40){
                                return true;}
                            ////当前坦克右上角与对方
                            if (    this.getX()>=enemy.getX()
                                    &&this.getX()<=enemy.getX()+60
                                    &&this.getY()+40>=enemy.getY()
                                    &&this.getY()<=enemy.getY()){
                                return true;}
                        }
                    }
                }
                break;
        }
        //没有重叠放回false
        return false;
    }

    //敌人移动线程
    @Override
    public void run() {

        while (true){
            //让敌人发射多颗子弹
            if (isLive&&shots.size()==0){
                Shot shot=null;
                switch (getDirect()){
                    case 0:
                        //每按下J，都创建了一个新的子弹对象，并且与hero绑定位置，之前的子弹就被回收了
                        shot=new Shot(getX()+20,getY(),0);//绑定hero方向
                        break;
                    case 1:
                        shot=new Shot(getX()+60,getY()+20,1);
                        break;
                    case 2:
                        shot=new Shot(getX()+20,getY()+60,getDirect());
                        break;
                    case 3:
                        shot=new Shot(getX(),getY()+20,getDirect());
                        break;
                }
                shots.add(shot);
                //启动
                new Thread(shot).start();
            }

        //敌人移动
        switch (getDirect()){
                case 0://上
                    for (int i = 0; i <30 ; i++) {
                        if (getY()>100&&!isTouchTank()) {//上壁
                            moveUp(5);
                        }
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                    }
                    break;
                case 1://右
                    for (int i = 0; i <30 ; i++) {
                        if (getX()+60<900&&!isTouchTank()) {
                            moveRight(5);
                        }

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                    break;
                case 2://下
                    for (int i = 0; i <30 ; i++) {
                        if (getY()+60<900&&!isTouchTank()){
                        moveDown(5);}
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3://左
                    for (int i = 0; i <30 ; i++) {
                        if (getX()>100&&!isTouchTank()){
                        moveLift(5); }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

            //设置随机数
            setDirect((int)(Math.random()*4));//返回(0~1)*4
            //
            if (!isLive){
                break;
            }
        }
    }
}
