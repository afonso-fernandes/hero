import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{
    Monster(int x, int y){
        super(x, y);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#005599"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#800080"));
        graphics.putString(position.getX(), position.getY(), "M");
    }

    public Position move() {
        Random random = new Random();
        int newX = position.getX() + random.nextInt(3) - 1;;
        int newY = position.getY() + random.nextInt(3) - 1;;
        return new Position(newX, newY);
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
