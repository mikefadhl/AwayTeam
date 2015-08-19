package com.test.awayteam;

/**
 * Created by pkar1 on 8/18/15.
 */
public class Shield extends Subsystem {

    private boolean isRaised = false;
    private boolean isDamaged = false;
    private int strength = 4000;
    private int maxEnergy = 10000;
    private int minEnergy = 0;

    public boolean isDown() {
        return !isRaised;
    }

    public void raise() {
        this.isRaised = true;
    }

    public void down() {
        this.isRaised = false;
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

    public int transferInEnergy(final int energy) {
        int excessEnergy = 0;

        if (energy <= 0) {
            return excessEnergy;
        }
        this.strength = this.strength + energy;

        if (this.strength > this.maxEnergy) {
            excessEnergy = this.strength - this.maxEnergy;
            this.strength = this.strength - excessEnergy;
        }
        return excessEnergy;
    }

    public int transferOutEnergy(final int energy) {
        int returnEnergy = 0;

        if (energy <= 0) {
            return returnEnergy;
        }

        if (energy >= this.strength) {
            returnEnergy = this.strength;
            this.strength = 0;
        } else {
            returnEnergy = energy;
            this.strength = this.strength - energy;
        }
        return returnEnergy;
    }
}
