package com.mygdx.spacechoppers.gamestates

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.spacechoppers.AssetManager
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.SpaceChoppersGame
import com.mygdx.spacechoppers.controller.AsteroidController
import com.mygdx.spacechoppers.controller.BackgroundController
import com.mygdx.spacechoppers.controller.ChopperController
import com.mygdx.spacechoppers.controller.LaserController
import com.mygdx.spacechoppers.controller.LiveScoresController
import com.mygdx.spacechoppers.factories.AsteroidFactory
import com.mygdx.spacechoppers.model.AsteroidTextures
import com.mygdx.spacechoppers.model.Explosion
import com.mygdx.spacechoppers.model.Joystick
import kotlin.random.Random


class PlayState(gsm: GameStateManager) : GameState(gsm) {
    private val stage = Stage(FitViewport(cam.viewportWidth, cam.viewportHeight), sb)
    private val joystick = Joystick(cam.viewportWidth)
    // health bar
    private val blank : Texture = AssetManager.manager.get("blank.png", Texture::class.java);

    // Chopper
    private val chopperController = ChopperController(joystick.touchpad)

    // Laser
    private val lasersController = LaserController();

    // Scores
    private val liveScoresController = LiveScoresController();

    // Asteroids
    private val asteroidFactory = AsteroidFactory(sb, AsteroidTextures())
    private val asteroids = ArrayList<AsteroidController>()

    // Explosions
    private val explosions = ArrayList<Explosion>()

    // Background
    private val background = BackgroundController(stage);



    init {
        Gdx.input.inputProcessor = stage
        stage.addActor(joystick.touchpad)

        createAsteroids(3)

        explosions.add(Explosion(100f, 100f, 0f))
        explosions.add(Explosion(100f, 800f, 0f))
    }

    private fun createAsteroids(num: Int) {
        for (i in 0..num) {
            asteroids.add(asteroidFactory.create())
        }
    }

    override fun update(delta: Float) {
        // Get chopper movement
        chopperController.moveChopper(delta)

        cam.position.set(chopperController.model.location)
        lasersController.fireLasers(delta, chopperController.model.location, chopperController.model.currentAngle)

        // Check if asteroids are out of bounds
        if (asteroids.all { a: AsteroidController -> a.model.isOutOfBounds }) {
            asteroids.clear()
            createAsteroids(Random.nextInt(5)) // TODO: Dynamic number
        }

        asteroids.forEach { asteroidController: AsteroidController -> asteroidController.moveAsteroid() }

        explosions.forEach { exp : Explosion -> exp.update(delta) }

    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        cam.update()
        sb.projectionMatrix = cam.combined

        sb.begin()
        background.draw(sb)

        chopperController.draw(sb)
        lasersController.draw(sb)
        liveScoresController.renderScores(sb)

        liveScoresController.renderScores(sb)
        asteroids.forEach{ asteroidController: AsteroidController -> asteroidController.draw() }
        explosions.forEach { explosion: Explosion -> explosion.draw(sb) }

        // Health Bar
        if (chopperController.model.hitPoints > 75){
            sb.setColor(Color.GREEN);
        }
        else if(chopperController.model.hitPoints > 25){
            sb.setColor(Color.ORANGE);
        } else {
            sb.setColor(Color.RED);
        }

        sb.draw(blank, 0F, 0F, SpaceChoppersGame.width * (chopperController.model.hitPoints / 100f), 10F)
        //sb.draw(blank, chopperController.model.location.x - 200, chopperController.model.location.y - 200, 500 * (chopperController.model.hitPoints / 100f), 10F)
        sb.setColor(Color.WHITE);

        sb.end()

        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
        
    }

    override fun dispose() {
        stage.dispose()
        joystick.dispose()
    }
}