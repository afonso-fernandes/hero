import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

class Game {
    private Screen screen;
    private Hero hero;

    public Game() throws IOException {

        TerminalSize terminalSize = new TerminalSize(60, 60);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary

        hero = new Hero(10, 10);
    }
    private void draw() throws IOException {

        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }

    private void moveHero(Position position) {
        hero.setPosition(position);
    }
    public void run() throws IOException {
        while (true) {
            draw();  // Draw the game screen

            KeyStroke key = screen.readInput();  // Read user input

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
                        screen.close();
                        return;
                    }
                    break;
                case EOF:
                    return;
                default:
                    break;
            }
        }
    }
        /*if (key.getKeyType() == KeyType.ArrowUp) System.out.println("equal");
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'ç') System.out.println("equal char");*/





    private void processKey(KeyStroke key){

        System.out.println();
    }
}

