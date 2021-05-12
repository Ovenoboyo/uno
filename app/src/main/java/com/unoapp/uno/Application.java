package com.unoapp.uno;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import com.unoapp.uno.ui.screens.PlayerSelection;
import com.unoapp.uno.utils.Constants;
import com.unoapp.uno.utils.Database.DBNotInitializedException;

public class Application {
	public static void main(String[] args) {
		try {
			Constants.dbConnection.connect();
			Constants.dbConnection.migrate();

		} catch (IOException | SQLException | DBNotInitializedException e) {
			e.printStackTrace();
			return;
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerFont();
					new PlayerSelection().setVisible(true);

				} catch (FontFormatException | IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void registerFont() throws FontFormatException, IOException {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		File gilmer = new File(Constants.getAsset("gilmer_heavy.otf"));
		File proxima = new File(Constants.getAsset("ProximaNova-Bold.otf"));
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, gilmer));
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, proxima));
	}
}
