/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuliy.example;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author yilun
 */
public class Police extends AI {

    private TextureRegion frame = Assets.stand;
    private float time = 0;
    private int health;
    private int AIspeed = 120;
    private int shotNum;
    private boolean isDead = false;

    public Police(float x, float y) {
        super(x, y);
        health = 200;
    }

    public void chase(Player p) {
        if (p.getX() > bounds.x) {
            velocity.x = AIspeed;
        } else if (p.getX() < bounds.x) {
            velocity.x = -AIspeed;
        }

        if (p.getY() > bounds.y) {
            velocity.y = AIspeed;
        } else if (p.getY() < bounds.y) {
            velocity.y = -AIspeed;
        }
    }
    
    public void stop(){
        velocity.x = 0;
        velocity.y = 0;
    }

    public boolean isDead() {
        if (health <= 0) {
            isDead = true;
        }
        return isDead;
    }

    public void punched() {
        health -= 2;
    }

    public void shot() {
        health -= 25;
    }

    public void shootPlayer(Player p) {
        shotNum = (int) (Math.random() * 51);

        if (shotNum == 1) {
            p.getShot();
        }
    }
    
    public void setLastRot(Player p){
        lastRot = (int) (Math.toDegrees(Math.atan2(Math.abs(bounds.y - p.getY()), Math.abs(bounds.x - p.getX()))));
        if (bounds.y > p.getY() && bounds.x > p.getX()) {
            lastRot += 270;
        } else if (bounds.y > p.getY() && bounds.x < p.getX()) {
            lastRot = 90 - lastRot;
        } else if (bounds.y < p.getY() && bounds.x < p.getX()) {
            lastRot += 90;
        } else if (bounds.y < p.getY() && bounds.x > p.getX()) {
            lastRot = 270 - lastRot;
        }
    }

    public void draw(SpriteBatch batch, Player p, float deltaTime) {
        if (velocity.x != 0 || velocity.y != 0) {
            time += deltaTime;
        } else {
            time = 0;
        }
        frame = Assets.standCop;
        if (p.getWantedLvl() < 100) {
            if (velocity.x > 0 && velocity.y == 0) {
                frame = Assets.runCop
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 90, true);
                lastRot = 90;
            } else if (velocity.x < 0 && velocity.y == 0) {
                frame = Assets.runCop
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 270, true);
                lastRot = 270;
            } else if (velocity.x == 0 && velocity.y > 0) {
                frame = Assets.runCop
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 180, true);
                lastRot = 180;
            } else if (velocity.x > 0 && velocity.y > 0 && velocity.x == velocity.y) {
                frame = Assets.runCop
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 135, true);
                lastRot = 135;
            } else if (velocity.x < 0 && velocity.y > 0 && -velocity.x == velocity.y) {
                frame = Assets.runCop
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 225, true);
                lastRot = 225;
            } else if (velocity.x < 0 && velocity.y < 0 && velocity.x == velocity.y) {
                frame = Assets.runCop
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 315, true);
                lastRot = 315;
            } else if (velocity.x == 0 && velocity.y < 0) {
                frame = Assets.runCop
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 0, true);
                lastRot = 0;
            } else if (velocity.x > 0 && velocity.y < 0 && velocity.x == -velocity.y) {
                frame = Assets.runCop
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 45, true);
                lastRot = 45;
            } else {
                frame = Assets.standCop;
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 135, true);
                lastRot = 135;
            }
        } else {
            if (velocity.x > 0 && velocity.y == 0) {
                frame = Assets.policeM4
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 0.55f, 2.2f, lastRot, true);
                lastRot = 90;
            } else if (velocity.x < 0 && velocity.y == 0) {
                frame = Assets.policeM4
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 0.55f, 2.2f, lastRot, true);
                lastRot = 270;
            } else if (velocity.x == 0 && velocity.y > 0) {
                frame = Assets.policeM4
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 0.55f, 2.2f, lastRot, true);
                lastRot = 180;
            } else if (velocity.x > 0 && velocity.y > 0 && velocity.x == velocity.y) {
                frame = Assets.policeM4
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 0.55f, 2.2f, lastRot, true);
                lastRot = 135;
            } else if (velocity.x < 0 && velocity.y > 0 && -velocity.x == velocity.y) {
                frame = Assets.policeM4
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(),0.55f, 2.2f, lastRot, true);
                lastRot = 225;
            } else if (velocity.x < 0 && velocity.y < 0 && velocity.x == velocity.y) {
                frame = Assets.policeM4
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 0.55f, 2.2f, lastRot, true);
                lastRot = 315;
            } else if (velocity.x == 0 && velocity.y < 0) {
                frame = Assets.policeM4
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(),0.55f, 2.2f, lastRot, true);
                lastRot = 0;
            } else if (velocity.x > 0 && velocity.y < 0 && velocity.x == -velocity.y) {
                frame = Assets.policeM4
                        .getKeyFrame(time, true);
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 0.55f, 2.2f, lastRot, true);
                lastRot = 45;
            } else {
                frame = Assets.standPolice;
                batch.draw(frame, bounds.x, bounds.y, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(), 0.55f, 2.2f, lastRot, true);
            }
        }
    }
}
