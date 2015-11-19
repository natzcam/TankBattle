package nac.tankbattle.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import nac.tankbattle.TankGame;

import javax.swing.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		String h = "localhost";
		Object[] options = {"Yes",
				"No"};
		int n = JOptionPane.showOptionDialog(null, "Run as server", "Tanke Tanke",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		if (n == 1) {
			h = JOptionPane.showInputDialog("Host: ", "localhost");
		}
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Tanke Tanke";
		cfg.width = TankGame.WIDTH;
		cfg.height = TankGame.HEIGHT;
		cfg.addIcon("data/icon.png", Files.FileType.Internal);
		new LwjglApplication(new TankGame(n == 0, h), cfg);
	}
}
