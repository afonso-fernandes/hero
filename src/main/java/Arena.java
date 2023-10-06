import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TerminalSize;


import java.io.IOException;

public class Arena {
    private int width;
    private int height;
    private Hero hero;



    public Arena(int width, int height){
        this.width = width;
        this.height = height;
        this.hero = new Hero(width / 2, height / 2);
    }


    public void processKey(KeyStroke key) throws IOException {
        KeyType keyType = key.getKeyType();
        switch (keyType) {
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case Character:
                char character = key.getCharacter();
                if (character == 'q') {
                    Game.screen.close();
                    System.exit(0);                                        // don't know why it doesn't work without this
                    return;
                }
                break;
            case EOF:
                return;
            default:
                break;
        }
    }

    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    public boolean canHeroMove(Position position){
        return position.getX() >= 0 && position.getX() < width && position.getY() >= 0 && position.getY() < height;
    }

    public void draw(TextGraphics textGraphics) {
        TextGraphics graphics = Game.screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#005599"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(textGraphics);


    }
}
