package com.test.awayteam;

/**
 * Created by pkar1 on 8/18/15.
 */
public class Shield {

    private boolean isRaised = false;
    private boolean isDamaged = false;
    private int strength = 4000;
    private int maxEnergy = 10000;

    public boolean isDown(){
        return !isRaised;
    }

    public void raise() {
        this.isRaised = true;
    }

    public void down() {
        this.isRaised = false;
    }


    public void takeHit(int hitIntensity) {
        if (isRaised) {
            strength -= hitIntensity;
            if (strength < 0){
                strength = 0;
                down();
            }
        }
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

    public int transferEnergy(final int energy) {
        int excessEnergy = 0;
        this.strength = this.strength + energy;

        if(this.strength > this.maxEnergy){
            excessEnergy =  this.strength - this.maxEnergy;
            this.strength = this.strength - excessEnergy;
        }
        return excessEnergy;
    }
}
