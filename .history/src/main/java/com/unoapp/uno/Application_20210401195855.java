package com.unoapp.uno;

import com.unoapp.uno.ui.Screen;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class).headless(false)
				.run(args);
		Screen mScreen = context.getBean(Screen.class);
		// mScreen.setVisible(true);
	}

}
