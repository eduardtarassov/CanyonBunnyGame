package com.eduardtarassov.canyonbunny.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Eduard on 3/9/14.
 */
public class CameraHelper {
    private static final String TAG = CameraHelper.class.getName();
    private final float MAX_ZOOM_IN = 0.25f;
    private final float MAX_ZOOM_OUT = 10.0f;
    private Vector2 position;
    private float zoom;
    private Sprite target;

    public CameraHelper() {
        position = new Vector2();
        zoom = 1.0f;
    }

    /*
    * Updates camera (if needed) position every main update cycle.
    */
    public void update(float deltaTime) {
        if (!hasTarget()) return;
        position.x = target.getX() + target.getOriginX();
        position.y = target.getY() + target.getOriginY();
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void addZoom(float amount) {
        setZoom(zoom + amount);
    }

    public void setZoom(float zoom) {
        this.zoom = MathUtils.clamp(zoom, MAX_ZOOM_IN, MAX_ZOOM_OUT);
    }

    public float getZoom() {
        return zoom;
    }

    /*
    * This method allows camera to follow one game object. It can be also set to null to stop tracking anything.
    */
    public void setTarget(Sprite target) {
        this.target = target;
    }

    /*
    * Allows to get out what is the last set target.
     */
    public Sprite getTarget() {
        return target;
    }

    /*
    * Checks if even camera has a target.
    */
    public boolean hasTarget() {
        return target != null;
    }

    public boolean hasTarget(Sprite target) {
        return hasTarget() && this.target.equals(target);
    }

    /*
    * Takes care of updating the camera's attributes.
    * Hence it must be always called from the beginning of the rendering of a new frame.
    */
    public void applyTo(OrthographicCamera camera) {
        camera.position.x = position.x;
        camera.position.y = position.y;
        camera.zoom = zoom;
        camera.update();
    }
}
