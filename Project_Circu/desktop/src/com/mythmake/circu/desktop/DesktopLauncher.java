package com.mythmake.circu.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mythmake.circu.Circu;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Circu!";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new Circu(), config);
	}
}
