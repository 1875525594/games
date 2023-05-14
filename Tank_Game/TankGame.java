package Tank_Game;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class TankGame extends JFrame {
    TankPanel tankPanel=null;
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        /**创建TankGame对象
         * */
        TankGame tankGame = new TankGame();
    }

    //构造器
    public TankGame(){
        System.out.println("选择：1：继续上一局 \n 2：新游戏");
        String key=scanner.next();
        /**创建TanPanel对象，
         并且初始化* */
        tankPanel = new TankPanel(key);//初始化构造器
        //将tankPanel放入Tread，
        Thread thread = new Thread(tankPanel);
        thread.start();//启动子线程tankPanel下的run方法，
                        // 接着并发往下执行

        this.add(tankPanel);//把面板加入窗口
        this.setSize(1500,1200);//设置窗口大小

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);//允许显示
        this.addKeyListener(tankPanel);//键盘监听,JFrame监听tanKPanel

        //监听关闭窗口
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });

    }
}
