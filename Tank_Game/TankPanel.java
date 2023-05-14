package Tank_Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;
@SuppressWarnings("all")
public class TankPanel extends JPanel implements KeyListener ,Runnable {

    //初始化我方
    Hero hero = null;
    Enemy enemy=null;
    /**敌方集合vector*/
    Vector<Enemy> enemies = new Vector<>();
    int enemySize = 13;

    //定义一个Node的集合
    Vector<Node> nodes =new Vector<>();

    /**爆炸特效,且定义3张图片
     * */
    Vector<boomEffect> booms = new Vector<>();
    Image image1=null;
    Image image2=null;
    Image image3=null;

    //构造器
    public TankPanel(String key) {
        //恢复存档,如果没有存档文件（或者文件被删除）会报错。
        File file = new File(Recorder.getRecordPath());
        if (file.exists()){
        nodes = Recorder.getNodes();
        }else {
            System.out.println("存档不存在，重新开始");
            key="2";
        }
        /**，创建英雄，
         * 且初始化我方位置*/
        hero = new Hero(500, 500);
        //
        Recorder.setEnemies(enemies);
        //
        switch (key){
            case "1" ://返回上一局
                //初始化敌人
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);

                    /**创建敌人，
                     * 且恢复敌方位置**/
                    Enemy enemy = new Enemy (node.getX(), node.getY());

                    //传递集合给Enemy，以便调用Enemy的方法
                    enemy.setEnemies(enemies);

                    //设置方向
                    enemy.setDirect(node.getDirect());
                    //启动敌人的run线程
                    new Thread(enemy).start();

                    /**创建一颗新子弹的对象，
                     * 而且使得这颗新子弹与敌人绑定*/
                    Shot shot1 = new Shot(enemy.getX() + 20, enemy.getY() + 60, enemy.getDirect());
                    //把设置好的子弹加入 子弹集合
                    enemy.shots.add(shot1);

                    //启动shot对象，使得shot下的run方法动起来
                    Thread t1 = new Thread(shot1);
                    t1.start();
                    //等价与：new Thread(shot1).start();

                    //把设置好的坦克加入 敌人集合
                    enemies.add(enemy);

                }
                break;
            case "2"://新游戏
                Recorder.setAllEnemyNum(0);
                //初始化敌人
                for (int i = 0; i < enemySize; i++) {
                    /**创建敌人，
                     * 且初始化我方位置**/
                    Enemy enemy = new Enemy(((100 + i)*i + 100), 200+(100 + i)*i);
                    //传递集合给Enemy
                    enemy.setEnemies(enemies);
                    //设置方向
                    enemy.setDirect(2);
                    //启动敌人的run线程
                    new Thread(enemy).start();
                    /**创建一颗新子弹的对象，
                     * 而且使得这颗新子弹与敌人绑定*/
                    Shot shot1 = new Shot(enemy.getX() + 20, enemy.getY() + 60, enemy.getDirect());
                    //把设置好的子弹加入 子弹集合
                    enemy.shots.add(shot1);

                    //启动shot对象，使得shot下的run方法动起来
                    Thread t1 = new Thread(shot1);
                    t1.start();
                    //等价与：new Thread(shot1).start();

                    //把设置好的坦克加入 敌人集合
                    enemies.add(enemy);

                }
                break;
            default:
                System.out.println("你的输入有无");
        }
        //初始化图片
        image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/t1.png"));
        image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/t2.png"));
        image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/t3.png"));
        //加入音乐??


    }//构造器TankPanel()

    //显示击毁得分
    public void showInfo(Graphics g){
        //设置颜色
        g.setColor(Color.black);
        //字类型，粗细，大小
        Font font = new Font("黑体",Font.BOLD,50);
        setFont(font);
        g.drawString("击毁数：",950,200);
        drawTank(1000,250,g,0,1);
        g.setColor(Color.gray);
        //Recorder.getAllEnemyNum()+"",int---->String
        g.drawString(": "+Recorder.getAllEnemyNum(),1050,300);

    }

    //画画方法
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        showInfo(g);
        //绘画背景
        g.fillRect(100, 100, 800, 800);
        //画hero方法
        if (hero.isLive){
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);}
        //drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
        //画敌人
        for (int i = 0; i < enemies.size(); i++) {
            //从集合取出一个敌人
            Enemy enemy = enemies.get(i);
            if (enemy.isLive){
                //绘画敌人
                drawTank(enemy.getX(), enemy.getY(), g, enemy.getDirect(), 1);

            for (int j = 0; j < enemy.shots.size(); j++) {
                //从集合取出一个子弹，敌人子弹
                Shot shot = enemy.shots.get(j);
                //画敌人子弹
                if (shot.isLive == true) {
                    g.draw3DRect(shot.x, shot.y, 5, 5, false);
                } else {//移除从Vector子弹
                    enemy.shots.remove(shot);
                }
            }
        }
        }
        /**单颗hero子弹*/
        //绘画hero子弹
        //g.fill3DRect(hero.shot.x,hero.shot.y,5,5,false);
        //缺少hero.shot!=null的判断会报错
        //if (hero.shot != null && hero.shot.isLive == true) {
        //    //System.out.println("子弹被绘制。。。");
        //    g.fill3DRect(hero.shot.x, hero.shot.y, 5, 5, false);
        //}

        /**多颗hero子弹*/
        //遍历hero子弹，然后依次绘制
        for (int i = 0; i <hero.shots.size() ; i++) {
            //从集合中取出子弹
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.isLive == true) {
                //System.out.println("子弹被绘制。。。");
                g.setColor(Color.CYAN);
                g.fill3DRect(shot.x, shot.y, 5, 5, false);

            }else {//销毁无效子弹
                hero.shots.remove(shot);
            }

        }
        
        //画爆炸
        for (int i = 0; i <booms.size() ; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //取出炸弹
          boomEffect boom  =booms.get(i);
          if (boom.life>9){
              g.drawImage(image1,boom.x,boom.y,50,50,this);
          }else if(boom.life>6){
              g.drawImage(image2,boom.x,boom.y,50,50,this);
          }
          else if(boom.life>3){
                  g.drawImage(image2,boom.x,boom.y,50,50,this);
            }else {
              g.drawImage(image3,boom.x,boom.y,50,50,this);
          }
          boom.lifeDown();
          //从集合中删除
            if (boom.life==0){
                booms.remove(boom);
            }
        }

    }//paint()

    /**5颗子弹都能打死敌人*/
    public void hitEnemy(Shot s,Enemy enemy){//hitTank(hero.shot, enemy);
        for (int i = 0; i <hero.shots.size() ; i++) {
            Shot shot = hero.shots.get(i);
              s=shot;
                switch (enemy.getDirect()){
                    case 2://坦克下方位
                    case 0://坦克上方位
                        if (s.x>enemy.getX()&&s.x<enemy.getX()+40&&
                                s.y>enemy.getY()&&s.y<enemy.getY()+60){
                            s.isLive=false;
                            enemy.isLive=false;
                            //并且把敌人移出集合
                            enemies.remove(enemy);

                            if (enemy instanceof Enemy){
                                Recorder.addNum();
                            }
                            //创建boomEffect对象，加入到boomEffect集合
                            boomEffect boomEffect = new boomEffect(enemy.getX(),enemy.getY());
                            booms.add(boomEffect);
                        }
                        break;
                    case 3:
                    case 1://坦克右方位
                        if (s.x>enemy.getX()&&s.x<enemy.getX()+60&&
                                s.y>enemy.getY()&&s.y<enemy.getY()+40){
                            s.isLive=false;
                            enemy.isLive=false;
                            //并且把敌人移出集合
                            enemies.remove(enemy);

                            if (enemy instanceof Enemy){
                                Recorder.addNum();
                            }
                            //创建boomEffect对象，加入到boomEffect集合
                            boomEffect boomEffect = new boomEffect(enemy.getX(),enemy.getY());
                            booms.add(boomEffect);
                        }
                        break;
                }
        }
    }

    //判断是否击中tank
    //攻击tank后，坦克与子弹消失
    public void hitTank(Shot s,Tank enemy){
        //根据敌人不同方位，形状不同
        switch (enemy.getDirect()){
            case 0:
            case 2://坦克上方位
                if (s.x>enemy.getX()&&s.x<enemy.getX()+40&&
                        s.y>enemy.getY()&&s.y<enemy.getY()+60){
                    s.isLive=false;
                    enemy.isLive=false;
                    //并且把敌人移出集合
                    enemies.remove(enemy);
                    //记录击毁数
                    if (enemy instanceof Enemy){
                        Recorder.addNum();
                    }
                    //创建boomEffect对象，加入到boomEffect集合
                    boomEffect boomEffect = new boomEffect(enemy.getX(),enemy.getY());
                    booms.add(boomEffect);
                }
                break;
            case 1:
            case 3://坦克右方位
                if (s.x>enemy.getX()&&s.x<enemy.getX()+60&&
                        s.y>enemy.getY()&&s.y<enemy.getY()+40){
                    s.isLive=false;
                    enemy.isLive=false;
                    //并且把敌人移出集合
                    enemies.remove(enemy);
                    //记录击毁数
                    if (enemy instanceof Enemy){
                        Recorder.addNum();
                    }
                    //创建boomEffect对象，加入到boomEffect集合
                    boomEffect boomEffect = new boomEffect(enemy.getX(),enemy.getY());
                    booms.add(boomEffect);
                }
                break;
        }
    }

    /*
     *int x,坦克运动位置x
     * int y,坦克运动位置
     * Graphics g,
     * int direct,坦克运动方位
     * int type，坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        switch (type) {
            case 0://我方tank
                g.setColor(Color.blue);
                break;
            case 1://敌方tank
                g.setColor(Color.red);
                break;
        }

        /*画不同方位不同的tank
         0：上
         1：右
         2：下
         3：左
         */
        switch (direct) {
            case 0://向上
                g.fill3DRect(x, y, 10, 60, false);//绘画左轮
                g.fill3DRect(x + 30, y, 10, 60, false);//绘画右轮
                g.fill3DRect(x + 10, y + 20, 20, 20, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1://向右
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2://向下
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3://向左
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            default:
                System.out.println("-----------");
        }
    }

    //键盘触发
    //工作hero的攻击，运动方位
    //但是并没用控制敌人的的攻击，运动方位,速度
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirect(0);//上
            //hero.setDirect(hero.getY()-1);
            if (hero.getY()>100){
            hero.moveUp(15);}
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);//右
            if (hero.getX()<900){
            hero.moveRight(15);}
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);//下
            if (hero.getY()<900){
            hero.moveDown(15);}
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);//左
            if (hero.getX()>100){
            hero.moveLift(15);}
        }
        //用户输入J
        else if (e.getKeyCode() == KeyEvent.VK_J) {
            System.out.println("用户按下了 J ");

            //要求子弹销毁，才能进行下次攻击
            //1，hero.shot==null保证了可以触发hero.shotEnemy();
            //2，ero.shot.isLive==false销毁子弹
            //if(hero.shot==null||hero.shot.isLive==false){
            //hero.shotEnemy();}
            //发射多颗子弹
            hero.shotEnemy();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    //重绘线程（Tankpanel的）
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /**一堆子弹也能攻击敌人,不行！**/
            ////一堆子弹也能攻击敌人
            //        for (int i = 0; i < hero.shots.size(); i++) {
            //            //从集合中取出一颗子弹
            //            Shot shot = hero.shots.get(i);
            //            if (shot!=null&&shot.isLive) {
            //            for (int j = 0; j < enemies.size(); j++) {
            //                //将这颗子弹传入判断所有tank是否击中
            //                Enemy enemy = enemies.get(j);
            //                hitTank(hero.shot, enemy);
            //            }
            //        }}

            if (hero.shot!=null&&hero.shot.isLive) {
                    for (int j = 0; j < enemies.size(); j++) {
                        //将这颗子弹传入判断所有tank是否击中
                          Enemy enemy = enemies.get(j);
                          hitEnemy(hero.shot, enemy);
                    }
            }

            //看看hero是否存活（优化）
            //攻击hero
                for (int j = 0; j < enemies.size(); j++) {
                    Enemy enemy = enemies.get(j);
                    for (int i = 0; i <enemy.shots.size() ; i++) {
                        Shot shot = enemy.shots.get(i);
                        if (hero.isLive&&shot.isLive) {
                        hitTank(shot, hero);
                        }
                    }
                }


            this.repaint();//启动线程paint();
        }
    }//run
}
