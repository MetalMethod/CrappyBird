package tut.metalmethod.crappybird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import tut.metalmethod.crappybird.helpers.AssetLoader;
import tut.metalmethod.crappybird.screens.GameScreen;

public class CrappyBirdGame extends Game{

	@Override
	public void create() {
		Gdx.app.log("CrappyBirdGame", "created");
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose(){
		super.dispose();
		AssetLoader.dispose();
	}
}
