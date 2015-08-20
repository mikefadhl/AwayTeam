package com.test.awayteam;

import java.util.*;

/**
 * Created by mfadh1 on 8/19/15.
 */
public class Ship {
    private Map<String, Subsystem> subsystems = new HashMap<String, Subsystem>();
    private int strength = 4000;
    private final String shieldKey = "shield";
    private final String weaponKey = "weapon";
    private final String engineyKey = "engine";

    public Ship() {
        Shield shield = new Shield();
        subsystems.put("shield", shield);
        subsystems.put(shieldKey, shield);
        Weapon weapon = new Weapon();
        subsystems.put("weapon", weapon);
        subsystems.put(weaponKey, weapon);
        Engine engine = new Engine();
        subsystems.put("engine", engine);
        subsystems.put(engineyKey, engine);
    }

    public Ship(final int strength) {
        this();
        this.strength = strength;

    }

    public Map<String, Subsystem> getSubsystems() {
        return subsystems;
    }
<<<<<<< HEAD
    
    protected void takeHit(int hitIntensity) {
        Shield shield = (Shield) subsystems.get("shield");
        Random generator = new Random();
        hitIntensity = shield.takeHit(hitIntensity);
        if (hitIntensity > 0) {
            Object[] values = subsystems.values().toArray();
            Subsystem randomSubsystem = (Subsystem) values[generator.nextInt(values.length)];
            randomSubsystem.damage();

    public int getStrength() {
        return strength;
    }

    public void takeHit(int hitIntensity) {
        Shield shield = (Shield) subsystems.get(shieldKey);
        hitIntensity = shield.takeHit(hitIntensity);
        if (hitIntensity > 0){
            subsystems.get(shieldKey).damage();
        }
    }

    public void getEnergyFromShield(final int energy) {
        Shield shield = (Shield) subsystems.get(shieldKey);
        this.strength = this.strength + shield.transferOutEnergy(energy);
    }

    public void giveEnergyToShield(final int energy) {
        if(energy < 0) return;
        Shield shield = (Shield) subsystems.get(shieldKey);
        int excessEnergy = shield.transferInEnergy(energy);
        this.strength = this.strength - energy + excessEnergy;
    }
}
