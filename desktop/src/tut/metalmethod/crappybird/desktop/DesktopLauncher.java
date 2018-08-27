package tut.metalmethod.crappybird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import tut.metalmethod.crappybird.CrappyBirdGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Crappy Bird";
		config.width = 272;
		config.height = 408;
		new LwjglApplication(new CrappyBirdGame(), config);
	}
}
