package com.test.awayteam;

/**
 * Created by pkar1 on 8/18/15.
 */
public class Shield {

    private boolean isRaised = false;
    private boolean isDamaged = false;
    private int strength = 4000;

    public boolean isDown(){
        return !isRaised;
    }

    public void raise() {
        this.isRaised = true;
    }

    public void down() {
        this.isRaised = false;
    }

    public void transferEnergy(final int energy) {
        this.strength = this.strength + energy;
    }

    public int takeHit(int hitIntensity) {
        if (isRaised) {
            if (hitIntensity > strength) {
                down();
                hitIntensity -= strength;
                strength = 0;
            } else {
                strength -= hitIntensity;
                hitIntensity = 0;
            }
        }

        return hitIntensity;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public boolean isDamaged() {
        return isDamaged;
    }

    public void setIsDamaged(boolean isDamaged) {
        this.isDamaged = isDamaged;
    }
}
