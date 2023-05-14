package Tank_Game;

import javax.swing.*;

public class Shot extends JPanel implements Runnable{
    int x;
    int y;
    int speed=2;//子弹速度
    int direct=0;
    boolean isLive=true;

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    //对象Shot的线程
    @Override
    public void run() {
        while (true){
            //休眠50sm
            try {
                Thread.sleep(10);//控制子弹速度
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //子弹攻击方向
            switch (direct){//传进来：direct=enemy.getDirect()
                case 0:
                    y-=speed;
                    break;
                case 1:
                    x+=speed;
                    break;
                case 2:
                    y+=speed;
                    break;
                case 3:
                    x-=speed;
                    break;
            }

            //监控子弹
           // System.out.println("子弹踪迹：x= "+x+"y= "+y);
            //子弹销毁
            //1,碰到边界
            //2，碰到敌人
            if (!(x>100&&x<=900&&y>100&&y<=900&&isLive)){
                isLive=false;
                break;
            }

        }
    }

}
