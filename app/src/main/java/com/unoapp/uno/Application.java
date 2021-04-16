package com.unoapp.uno;

import com.unoapp.uno.ui.Game;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class Application {
	public static void main(String[] args) {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("assets/gilmer_heavy.otf")));

		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		Game game;
		try {
			game = new Game();
			game.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
