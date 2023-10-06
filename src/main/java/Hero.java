import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero  {
    private Position position;
    public Hero(int x, int y) {
        this.position = new Position(x, y);
    }
    public int getX() {return position.getX();}
    public int getY() {return position.getY();}
    public Position moveUp() {return new Position(position.getX(), position.getY() - 1);}
    public Position moveRight() {return new Position(position.getX() + 1, position.getY());}
    public Position moveDown() {return new Position(position.getX(), position.getY() + 1);}
    public Position moveLeft() {return new Position(position.getX() - 1, position.getY());    }

    public void draw(Screen screen) {
        screen.setCharacter(new TerminalPosition(position.getX(), position.getY()), TextCharacter.fromCharacter('A')[0]);
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
