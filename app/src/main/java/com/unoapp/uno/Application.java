package com.unoapp.uno;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import com.unoapp.uno.ui.screens.Game;

public class Application {
	public static void main(String[] args) {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			System.out.println(new File(".").getCanonicalPath());
			File file = new File("assets/gilmer_heavy.otf");
			if (!file.exists()) {
				file = new File("runtime/bin/assets/gilmer_heavy.otf");
			}
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, file));

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
