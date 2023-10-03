import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

class Game {
    private Screen screen;


    Game(Screen screen) {
        this.screen = screen;
    }

    public Game() {

    }

    public static void main(String[] args) throws IOException {
        TerminalSize terminalSize = new TerminalSize(100, 60);
        DefaultTerminalFactory terminalFactory = new
                DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary


        screen.clear();
        screen.setCharacter(20, 20, TextCharacter.fromCharacter('A')
                [0]);
        screen.refresh();
    }
    private void draw() throws IOException {

        screen.clear();
        screen.setCharacter(20, 20, TextCharacter.fromCharacter('A')
                [0]);
        screen.refresh();
    }
    public void run() throws IOException {
        draw();
    }
}

