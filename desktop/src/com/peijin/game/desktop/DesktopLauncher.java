package com.peijin.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.peijin.game.MarineBattle;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Submarine Battle";
		cfg.height = 480;
		cfg.width = 800;

		new LwjglApplication(new MarineBattle(), cfg);
	}
}
