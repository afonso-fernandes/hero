import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TerminalSize;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;
    private List <Monster> monsters;

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
        this.hero = new Hero(width / 2, height / 2);
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
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
        retrieveCoins();
    }




    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return coins;
    }
    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            monsters.add(new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return monsters;
    }

    private void retrieveCoins(){
        List<Coin> coinsToRemove = new ArrayList<>();
        Position heroPosition = hero.getPosition();

        for(Coin coin : coins){
            if (coin.getPosition().equals(heroPosition)) coinsToRemove.add(coin);
        }
        coins.removeAll(coinsToRemove);
    }
    public void moveHero(Position position) {
        if (canHeroMove(position))
            for (Wall wall : walls) {
                if (wall.getPosition().equals(position)) {
                    return;
                }
            }
        hero.setPosition(position);
    }

    public void moveMonsters(Position position) {
        for (Monster monster : monsters) {
            Position newPosition = monster.move();
            if (canMonsterMove(newPosition)) {
                boolean canMove = true;
                for (Wall wall : walls) {
                    if (wall.getPosition().equals(newPosition)) {
                        canMove = false;
                        break; // Stop checking other walls
                    }
                }
                if (canMove) {
                    monster.setPosition(newPosition);
                }
            }
        }
    }

    public boolean canHeroMove(Position position){
        return position.getX() >= 0 && position.getX() < width && position.getY() >= 0 && position.getY() < height;
    }
    public boolean canMonsterMove(Position position){
        return position.getX() >= 1 && position.getX() < width-1 && position.getY() >= 1&& position.getY() < height-1;
    }
    public void draw(TextGraphics textGraphics) {
        TextGraphics graphics = Game.screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#005599"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(textGraphics);
        for (Wall wall : walls) {
            wall.draw(graphics);
        }
        for(Coin coin : coins){
            coin.draw(textGraphics);
        }
        for (Monster monster : monsters){
            monster.draw(graphics);
        }
    }

    public void processMonster() {
        for (Monster monster : monsters) {
            Position newPosition = monster.move();
            if (canMonsterMove(newPosition)) {
                monster.setPosition(newPosition);
            }
        }
    }

    public void verifyMonsterCollisions() throws IOException {
        Position heroPosition = hero.getPosition();

        for(Monster monster : monsters)
            if(monster.getPosition().equals(heroPosition)){
                System.out.println("GAME OVER");
                Game.screen.close();
                System.exit(0);
            }
    }
}
