package com.test.awayteam;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mfadh1 on 8/19/15.
 */
public class ShipTest {
    private Ship ship;
    private Shield shield;

    private final String shieldKey = "shield";

    @Before
    public void setUp() throws Exception {
        ship = new Ship();
        shield = (Shield) ship.getSubsystems().get(shieldKey);
    }

    @Test
    public void shipHasSubsystem() {
        Ship ship = new Ship();
        assertFalse("Ship has a list of subsystems", ship.getSubsystems().isEmpty());
        assertEquals("The ship has three subsystems now", 3, ship.getSubsystems().size());
        assertEquals("The first subsystem is shields", Shield.class, ship.getSubsystems().get("shield").getClass());
    }

    @Test
    public void takeHitDepletesShields() {
        shield.raise();
        ship.takeHit(3999);
        assertEquals("Shields strength depleted by 500 from default of 4000", 1, shield.getStrength());
        assertFalse("Shields is still up", shield.isDown());
    }

    @Test
    public void takeHitDepletesShieldsAndBringsDown() {
        shield.raise();
        ship.takeHit(4000);
        assertEquals("Shields strength depleted by 4000 from default of 4000", 0, shield.getStrength());
        assertTrue("Shields are down", shield.isDown());
    }

    @Test
    public void takeHitDepletesShieldsAndBringsDownAndDamagesSubsystem() {
        shield.raise();
        ship.takeHit(4001);
        assertEquals("Shields strength depleted by 4001 from default of 4000", 0, shield.getStrength());
        assertTrue("Shields are down", shield.isDown());
        assertTrue("Subsystem is damaged", ship.getSubsystems().get("shield").isDamaged());
    }

    @Test
    public void testGetEnergyFromShield(){
        ship.getEnergyFromShield(1000);
        assertEquals(5000, ship.getStrength());
        assertEquals(3000, shield.getStrength());
    }

    @Test
    public void testGetEnergyFromShieldExceedCurrentEnergyLevel(){
        ship.getEnergyFromShield(4001);
        assertEquals(8000, ship.getStrength());
        assertEquals(0,shield.getStrength());
    }

    @Test
    public void testGetEnergyFromShieldNegetiveEnergyLevel(){
        ship.getEnergyFromShield(-1);
        assertEquals(4000, ship.getStrength());
        assertEquals(4000,shield.getStrength());
    }

    // give
    @Test
    public void testGiveEnergyToShield(){
        ship.giveEnergyToShield(1000);
        assertEquals(3000, ship.getStrength());
        assertEquals(5000, shield.getStrength());
    }

    @Test
    public void testGiveEnergyToShieldExceedCurrentEnergyLevel(){
        Ship shipLocal = new Ship(10000);
        shipLocal.giveEnergyToShield(6001);
        Shield shieldLocal = (Shield) shipLocal.getSubsystems().get(shieldKey);
        assertEquals(4000, shipLocal.getStrength());
        assertEquals(10000,shieldLocal.getStrength());
    }

    @Test
    public void testGiveEnergyToShieldNegetiveCurrentEnergyLevel(){
        ship.giveEnergyToShield(-1);
        assertEquals(4000, ship.getStrength());
        assertEquals(4000,shield.getStrength());
    }
}
