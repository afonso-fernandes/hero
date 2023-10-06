import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;

public class Hero  {
    private int x;
    private int y;
    public Hero(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {return x;}
    public int getY() {return y;}
    public void moveUp() {y--;}
    public void moveRight() {x++;}
    public void moveDown() {y++;}
    public void moveLeft() {x--;}

    public void draw(Screen screen) {
        screen.setCharacter(new TerminalPosition(x, y), TextCharacter.fromCharacter('A')[0]);
    }
}
