package Tank_Game;

public class Tank {
    boolean isLive=true;
    private int x;
    private int y;
    private int direct=0;
    private int speed=5;//移动速度
    public int getDirect() {
        return direct;
    }
    public void setDirect(int direct) {
        this.direct = direct;
    }

    //构造器
    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveUp(int speed){

        y-=speed;
    }
    public void moveRight(int speed){

        x+=speed;
    }
    public void moveDown(int speed){

        y+=speed;
    }
    public void moveLift(int speed){

        x-=speed;
    }
}
