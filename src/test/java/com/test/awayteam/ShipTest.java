package com.test.awayteam;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mfadh1 on 8/19/15.
 */
public class ShipTest {
    private Ship ship;
    private Shield shield;

    @Before
    public void setUp() throws Exception {
        ship = new Ship();
        shield = (Shield) ship.getSubsystems().get(0);
    }

    @Test
    public void shipHasSubsystem() {
        Ship ship = new Ship();
        assertFalse("Ship has a list of subsystems", ship.getSubsystems().isEmpty());
        assertEquals("The ship has only one subsystem now", 1, ship.getSubsystems().size());
        assertEquals("The only subsystem is shields", Shield.class, ship.getSubsystems().get(0).getClass());
    }


    @Test
    public void takeHitDepletesShields() {
        shield.raise();
        ship.takeHit(500);
//        int unabsorbedIntensity = shield.takeHit(500);
        assertEquals("Shields strength depleted by 500 from default of 4000", 3500, shield.getStrength());
//        assertEquals("Shields return 0 as unabsorbed", 0, unabsorbedIntensity);
        assertFalse("Shields is still up", shield.isDown());
    }
}
