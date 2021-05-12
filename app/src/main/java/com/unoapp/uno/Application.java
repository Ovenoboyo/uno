package com.unoapp.uno;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import com.unoapp.uno.utils.Constants;
import com.unoapp.uno.utils.Database;
import com.unoapp.uno.utils.Database.DBNotInitializedException;
import com.unoapp.uno.models.PlayerInfo;
import com.unoapp.uno.ui.screens.Game;
import com.unoapp.uno.ui.screens.PlayerSelection;

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
				new PlayerSelection().setVisible(true);
				// try {
				// 	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				// 	System.out.println(new File(".").getCanonicalPath());
				// 	File file = new File("assets/gilmer_heavy.otf");
				// 	if (!file.exists()) {
				// 		file = new File("runtime/bin/assets/gilmer_heavy.otf");
				// 	}

				// 	String filePath = "assets/bg.png";
				// 	File bg = new File(filePath);
				// 	if (!bg.exists()) {
				// 		filePath = "runtime/bin/assets/bg.png";
				// 	}
				// 	ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, file));
				// 	new Game(filePath).setVisible(true);

				// } catch (IOException | FontFormatException e) {
				// 	e.printStackTrace();
				// }
			}
		});
	}
}
