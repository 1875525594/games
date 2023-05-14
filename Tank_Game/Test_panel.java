package Tank_Game;
import javax.swing.*;
import java.awt.*;

public class Test_panel extends JFrame {
    //定义一个面板,并且初始化
    private MyPanel_ myPanel_=null;
    //main
    public static void main(String[] args) {
        new Test_panel();
        System.out.println("1111111111111");
    }
    //构造器
    public Test_panel(){
        myPanel_= new MyPanel_();
        this.add(myPanel_);//把面板加入窗口
        this.setSize(700,700);//设置窗口大小
        this.setVisible(true);//允许显示
        //this.paint(Graphics g);

    }
}

class MyPanel_ extends JPanel {
    //
    @Override
    public  void paint(Graphics g) {
        super.paint(g);//调用父类完成初始化
        System.out.println("ssssssssssss");
        //画圆方法
       // g.drawOval(10,10,200,200);
        //画直线
        //g.drawLine(1,1,6,6);
        //画矩形边框
        //g.drawRect(1,1,50,50);
        //填充边框(圆，矩形...)
        //g.setColor(Color.blue);
        //g.fillOval(1,1,60,60);
        //图片
        //1，获取图片
        Image image =Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/t2.gif"));
        g.drawImage(image,10,50,60,60,this);
        //画字符串
        //1,画笔颜色
        //2,字体（类型，粗细，大小）
        //3,内容（内容，x，y位置）
        g.setColor(Color.red);
        g.setFont(new Font("黑体",Font.BOLD,50));
        g.drawString("啦啦啦",50,50);
        //
    }
}
