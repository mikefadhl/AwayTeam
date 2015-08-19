package com.test.awayteam;

import java.util.*;

/**
 * Created by mfadh1 on 8/19/15.
 */
public class Ship {
    private Map<String, Subsystem> subsystems = new HashMap<String, Subsystem>();

    public Ship() {
        Shield shield = new Shield();
        subsystems.put("shield", shield);
        Weapon weapon = new Weapon();
        subsystems.put("weapon", weapon);
        Engine engine = new Engine();
        subsystems.put("engine", engine);
    }

    public Map<String, Subsystem> getSubsystems() {
        return subsystems;
    }

    public void takeHit(int hitIntensity) {
        Shield shield = (Shield) subsystems.get("shield");
        hitIntensity = shield.takeHit(hitIntensity);
        if (hitIntensity > 0){
            subsystems.get("shield").damage();
        }
    }
}
