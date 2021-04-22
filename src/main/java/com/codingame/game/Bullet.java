package com.codingame.game;

import com.codingame.game.entities.Robot;

import java.util.*;

/**
 * 
 */
public class Bullet extends CircularHitBox {
    private boolean hasExplode;
    public static Set<Bullet> bulletSet = new HashSet<>();
    private boolean isInstanced = false;

    public Bullet(Robot shooter, Robot target, boolean willHit, double damage) {
        super(shooter, 0, Constants.BULLET_SPEED);
        this.target = target;
        if (willHit) {
            end = new Point(target);
        } else {
            end = getDeviation(target);
        }

        this.willHit = willHit;
        this.damage = damage;
        owner = shooter.getOwner();
    }

    private final Player owner;
    private final Point end;
    private final Robot target;
    private static final Random random = new Random();
    private final boolean willHit;

    private Point getDeviation(CircularHitBox target) {
        int sign;
        if(random.nextBoolean()){
            sign = 1;
        } else {
            sign = -1;
        }
        Point newTarget = target.addPoint(getDirection(target).orthogonal().normalize().multiply(
                sign * target.getSize() * (1.01 + Constants.MAX_BULLET_DEVIATION * random.nextDouble())));
        return getDirection(newTarget);
    }

    private final double damage;


    public boolean updatePos(ViewManager viewManager) {
        //Referee.debug(String.format("bullet fired at %f, %f ",getX(),getY()));
        if (!isInstanced) {
            viewManager.instantiateBullet(this);
            isInstanced = true;
        }
        if (willHit) {
            if (getDist(end) < Constants.DELTA_TIME * Constants.BULLET_SPEED + target.getSize()) {
                target.takeDamage(damage,owner);
                hasExplode = true;
                setXY(target);
                return true;
            } else {
                setXY(this.addPoint(end.addPoint(this.multiply(-1)).normalize().
                        multiply(Constants.DELTA_TIME * Constants.BULLET_SPEED)));
            }
        } else {
            move((end).multiply(Constants.DELTA_TIME * Constants.BULLET_SPEED));
            if (!isInsideMap()) {
                setXY(clampToMap(this));
                hasExplode = true;
                return true;
                //Referee.debug(String.format("ball hit at : %f, %f", getX(), getY()));
            }
        }
        return false;
    }
    public boolean isActive() {
        return !hasExplode;
    }

    public Player getOwner() {
        return owner;
    }



}