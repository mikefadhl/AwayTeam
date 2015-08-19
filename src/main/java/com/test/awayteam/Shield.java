package com.test.awayteam;

/**
 * Created by pkar1 on 8/18/15.
 */
public class Shield {

    private boolean isRaised = false;
    private boolean isDisabled = false;
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

    public void takeHit(int hitIntensity) {
        strength -= hitIntensity;
        if (strength - hitIntensity  < 0) strength = 0 ;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public boolean disabled() {
        return isDisabled;
    }

    public void setIsDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
    }
}
