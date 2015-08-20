package com.test.awayteam;

import java.util.*;

/**
 * Created by mfadh1 on 8/19/15.
 */
public class Ship {
    private final String shieldKey = "shield";
    private final String weaponKey = "weapon";
    private final String engineyKey = "engine";
    private Map<String, Subsystem> subsystems = new HashMap<String, Subsystem>();
    private int strength = 4000;

    public Ship() {
        Shield shield = new Shield();
        subsystems.put(shieldKey, shield);
        Weapon weapon = new Weapon();
        subsystems.put(weaponKey, weapon);
        Engine engine = new Engine();
        subsystems.put(engineyKey, engine);
    }

    public Ship(final int strength) {
        this();
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public Map<String, Subsystem> getSubsystems() {
        return subsystems;
    }
    
    protected void takeHit(int hitIntensity) {
        Shield shield = (Shield) subsystems.get(shieldKey);
        Random generator = new Random();
        hitIntensity = shield.takeHit(hitIntensity);
        if (hitIntensity > 0) {
            Object[] values = subsystems.values().toArray();
            Subsystem randomSubsystem = (Subsystem) values[generator.nextInt(values.length)];
            randomSubsystem.damage();
        }
    }

    public void getEnergyFromShield(final int energy) {
        Shield shield = (Shield) subsystems.get(shieldKey);
        this.strength = this.strength + shield.transferOutEnergy(energy);
    }

    public void giveEnergyToShield(int energy) {

        if(energy < 0){
            return;
        }

        if(energy > this.strength){
            energy = this.strength;
        }
        Shield shield = (Shield) subsystems.get(shieldKey);
        int excessEnergy = shield.transferInEnergy(energy);
        this.strength = this.strength - energy + excessEnergy;
    }
}
