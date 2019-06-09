package com.peijin.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class MarineBattle extends ApplicationAdapter {

	// define class level variable: why we do this??

	private Sound dropSound;
	private Music rainMusic;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Bucket bucket;
	private Array<Droplet> raindrops;
	private Submaine submaine;
	private Sky sky;
	private Sea sea;
	private long lastDropTime;

	private final int WINDOW_WIDTH = 800;
	private final int WINDOW_HEIGHT = 480;

	private ShapeRenderer shapeRenderer;

	@Override
	public void create () {

		// load the images for the droplet and the bucket, 64x64 pixels each
//		bucketImage = new Texture(Gdx.files.internal("bucket.png"));


		// load the drop sound effect and the rain background "music"
		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

		// start the playback of the background music immediately
		rainMusic.setLooping(true);
		rainMusic.play();

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, WINDOW_WIDTH, WINDOW_HEIGHT);

		bucket = new Bucket(WINDOW_WIDTH);

		raindrops = new Array<Droplet>();
		spawnRaindrop();

		int skyHeight = 120;
		int seaHeight = WINDOW_HEIGHT - skyHeight;
		sea = new Sea(WINDOW_WIDTH, seaHeight,0,0);
		submaine = new Submaine(sea);
		sky = new Sky(WINDOW_WIDTH, skyHeight, 360);

	}

	@Override
	public void render () {
		// the render will be called several times in a sec.
		// clear the screen with a dark blue color. The
		// arguments to glClearColor are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// tell the camera to update its matrices.
		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);

		drawEnvironment();

		// begin a new batch and draw the bucket and
		// all drops
		batch.begin();
		batch.draw(Bucket.image(), bucket.x, bucket.y); //bucket.image() is a static reference
		for(Droplet droplet : raindrops) {
			batch.draw(Droplet.image(), droplet.x, droplet.y);
		}
		batch.draw(Submaine.image(), submaine.x, submaine.y, submaine.width, submaine.height);
		batch.end();

		// process user input
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucket.x = touchPos.x - 64 / 2;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.moveLeft(Gdx.graphics.getDeltaTime());
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.moveRight(Gdx.graphics.getDeltaTime());
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) bucket.moveUp(Gdx.graphics.getDeltaTime());
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) bucket.moveDown(Gdx.graphics.getDeltaTime());
		// make sure the bucket stays within the screen bounds
		bucket.ensureInScreen();

		// check if we need to create a new raindrop
		if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

		// move the raindrops, remove any that are beneath the bottom edge of
		// the screen or that hit the bucket. In the latter case we play back
		// a sound effect as well.
		for (Iterator<Droplet> iter = raindrops.iterator(); iter.hasNext(); ) {
			Droplet raindrop = iter.next();
			raindrop.moveDown(Gdx.graphics.getDeltaTime());

			if(raindrop.isOutOfScreen()) iter.remove();
			if(raindrop.overlaps(bucket)) {
				dropSound.play();
				iter.remove();
			}

			if(raindrop.overlaps(submaine)){
				dropSound.play();
			}
		}

		// move submaine(s)
		submaine.moveLeft(Gdx.graphics.getDeltaTime());
		submaine.loopBack();
	}

	private void drawEnvironment() {
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		// draw sea
		shapeRenderer.setColor(Color.BLUE);
		shapeRenderer.rect(this.sea.x, this.sea.y, this.sea.width, this.sea.height);
		// draw sky
		shapeRenderer.setColor(Color.SKY);
		shapeRenderer.rect(this.sky.x, this.sky.y, this.sky.width, this.sky.height);

		shapeRenderer.end();
	}

	private void spawnRaindrop() {
		Droplet droplet = new Droplet(WINDOW_WIDTH,WINDOW_HEIGHT);
		raindrops.add(droplet); //add a raindrop to raindrops list.
		lastDropTime = TimeUtils.nanoTime();
	}

	@Override
	public void dispose () {
		this.batch.dispose(); //this refer to the instance
		Droplet.image().dispose();
		Bucket.image().dispose(); // dispose a static image, only once
		this.dropSound.dispose();
		this.rainMusic.dispose();
	}
}
