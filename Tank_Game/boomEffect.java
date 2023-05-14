package Tank_Game;

public class boomEffect {
    int x;
    int y;
    int life=12;
    boolean isLive=true;

    public boomEffect(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //减少寿命
    public void lifeDown(){
        if (life>0){
            life--;
        }
        else {
            isLive=false;
        }
    }
}
