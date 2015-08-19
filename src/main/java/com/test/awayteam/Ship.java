package com.test.awayteam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mfadh1 on 8/19/15.
 */
public class Ship {
    private List<Subsystem> subsystems = new ArrayList<Subsystem>();

    public Ship() {
        Shield shield = new Shield();
        subsystems.add(shield);
    }

    public List<Subsystem> getSubsystems() {
        return subsystems;
    }

    public void takeHit(int hitIntensity) {
        Shield shield = (Shield) subsystems.get(0);
        hitIntensity = shield.takeHit(hitIntensity);
        if (hitIntensity > 0){
            subsystems.get(0).damageASubsystem(0);
        }
    }
}
