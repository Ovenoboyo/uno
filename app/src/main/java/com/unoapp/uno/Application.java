package com.unoapp.uno;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;

import com.unoapp.uno.ui.screens.Game;
import com.unoapp.uno.ui.screens.PlayerSelection;
import com.unoapp.uno.ui.screens.TitleScreen;
import com.unoapp.uno.utils.Constants;
import com.unoapp.uno.utils.Constants.Screens;
import com.unoapp.uno.utils.Database.DBNotInitializedException;

public class Application {
	public static void main(String[] args) {
		try {
			Constants.dbConnection.connect();
			Constants.dbConnection.migrate();

			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						registerFont();
						changeScreen(null, Screens.TITLE_SCREEN);
					} catch (FontFormatException | IOException e) {
						e.printStackTrace();
					}
				}
			});

		} catch (IOException | SQLException | DBNotInitializedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Registers required fonts
	 * @throws FontFormatException
	 * @throws IOException
	 */
	private static void registerFont() throws FontFormatException, IOException {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		File gilmer = new File(Constants.getAsset("gilmer_heavy.otf"));
		File proxima = new File(Constants.getAsset("ProximaNova-Bold.otf"));
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, gilmer));
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, proxima));
	}

	public static void changeScreen(JFrame currentScreen, Constants.Screens screen) {
		if (currentScreen != null)
			currentScreen.dispose();
		switch (screen) {
			case GAME:
				new Game().setVisible(true);
				break;
			case TITLE_SCREEN:
				new TitleScreen().setVisible(true);
				break;
			case PLAYER_SELECT:
				new PlayerSelection().setVisible(true);
				break;
			case EXIT:
				System.exit(0);
		}
	}
}
