/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuliy.example;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author yilun
 */
public class AI {

    private Rectangle bounds;
    private Vector2 velocity;
    private TextureRegion frame = Assets.stand;
    private float time = 0;
    private int AImovedecider;
    private int AImovetimer;
    private int health;

    public AI(float x, float y) {
        bounds = new Rectangle(x, y, Assets.stand.getRegionWidth(), Assets.stand.getRegionHeight() / 4);
        velocity = new Vector2(0, 0);
        health = 100;
    }

    public void update(float deltaTime) {
        bounds.x += velocity.x * deltaTime;
        bounds.y += velocity.y * deltaTime;
        if (velocity.x != 0 || velocity.y != 0) {
            time += deltaTime;
        } else {
            time = 0;
        }
    }

    public void draw(SpriteBatch batch, String type) {
        if (type == "PGreen") {
            if (velocity.x > 0 && velocity.y == 0) {
                frame = Assets.runRight
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 90, true);
            } else if (velocity.x < 0 && velocity.y == 0) {
                frame = Assets.runRight
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 270, true);
            } else if (velocity.x == 0 && velocity.y > 0) {
                frame = Assets.runRight
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 180, true);
            } else if (velocity.x > 0 && velocity.y > 0 && velocity.x == velocity.y) {
                frame = Assets.runRight
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 135, true);
            } else if (velocity.x < 0 && velocity.y > 0 && -velocity.x == velocity.y) {
                frame = Assets.runRight
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 225, true);
            } else if (velocity.x < 0 && velocity.y < 0 && velocity.x == velocity.y) {
                frame = Assets.runRight
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 315, true);
            } else if (velocity.x == 0 && velocity.y < 0) {
                frame = Assets.runRight
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 0, true);
            } else if (velocity.x > 0 && velocity.y < 0 && velocity.x == -velocity.y) {
                frame = Assets.runRight
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 45, true);
            } else {
                frame = Assets.stand;
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 135, true);
            }
        }
    }

    public void move(int AIspeed) {
        if (AImovetimer <= 0) {
            AImovetimer = (int) (Math.random() * 100);
            AImovedecider = (int) (Math.random() * 5);
        }

        if (AImovetimer >= 0) {
            if (AImovedecider == 0) {
                velocity.x = AIspeed;
            } else if (AImovedecider == 1) {
                velocity.x = -AIspeed;
            } else if (AImovedecider == 2) {
                velocity.y = AIspeed;
            } else if (AImovedecider == 3) {
                velocity.y = -AIspeed;
            } else if (AImovedecider == 4) {
                velocity.x = 0;
                velocity.y = 0;
            }
            AImovetimer--;
        }
    }

    public void scared(Player p, int AIspeed) {
        if (p.getX() > bounds.x) {
            velocity.x = -AIspeed;
        } else if (p.getX() < bounds.x) {
            velocity.x = AIspeed;
        }

        if (p.getY() > bounds.y) {
            velocity.y = -AIspeed;
        } else if (p.getY() < bounds.y) {
            velocity.y = AIspeed;
        }
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public float getX() {
        return bounds.x;
    }

    public float getY() {
        return bounds.y;
    }

    public float getHeight() {
        return bounds.height;
    }

    public float getWidth() {
        return bounds.width;
    }

    public void setVelocity(float dx, float dy) {
        velocity.x = dx;
        velocity.y = dy;
    }

    public void setX(float x) {
        bounds.x = x;
    }

    public void setY(float y) {
        bounds.y = y;
    }

    public void handleCollision(Rectangle c) {
        float x = Math.max(bounds.x, c.x);
        float y = Math.max(bounds.y, c.y);
        float width = Math.min(bounds.x + bounds.width, c.x + c.width) - x;
        float height = Math.min(bounds.y + bounds.height, c.y + c.height) - y;
        if (width < height) {
            if (bounds.x < c.x) {
                bounds.x = bounds.x - width;
            } else {
                bounds.x = bounds.x + width;
            }
        } else {
            if (bounds.y > c.y) {
                bounds.y = bounds.y + height;
            } else {
                bounds.y = bounds.y - height;
            }
        }
    }
}
