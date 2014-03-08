package com.eduardtarassov.canyonbunny;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.eduardtarassov.canyonbunny.util.WorldController;
import com.eduardtarassov.canyonbunny.util.WorldRenderer;

public class CanyonBunnyMain implements ApplicationListener {

    private static final String TAG = CanyonBunnyMain.class.getName();

    private WorldController worldController;
    private WorldRenderer worldRenderer;

    private boolean paused;

    @Override
    public void create() {
        // Set libGDX log level to DEBUG (to print everything to the console that might be logged during runtime)
        // Before publishing game log level must be changed to something like LOG_NONE or LOG_INFO!!!
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        // Initialize controller and renderer
        worldController = new WorldController();
        worldRenderer = new WorldRenderer(worldController);
        // Game world is active on start
        paused = false; //but in Zombie Bird game we used enum GameState, which is more efficient?
    }

    @Override
    public void render() {
        // DO not update game world when paused
        if (!paused) {
            // Update game world by the time that has passed since last rendered frame
            worldController.update(Gdx.graphics.getDeltaTime());
        }
        // Sets the clear screen color to: Cornflower Blue
        Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f, 0xff / 255.0f); //The same in decimal notation Gdx.gl.glClearColor(  100/255.0f, 149/255.0f, 237/255.0f, 255/255.0f);
        // Clears the screen
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        // Render game world to screen
        worldRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
        worldRenderer.resize(width, height);
    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
        paused = false;
    }

    @Override
    public void dispose() {
        worldRenderer.dispose();
    }

}
