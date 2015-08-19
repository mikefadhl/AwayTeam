package com.test.awayteam;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by pkar1 on 8/18/15.
 */
public class ShieldTest {
    private Ship ship;
    private Shield shield;

    @Before
    public void setUp() throws Exception {
        ship = new Ship();
        shield = (Shield) ship.getSubsystems().get(0);
    }

    @Test
    public void shieldsByDefaultDown() {
        Assert.assertTrue(shield.isDown());
    }

    @Test
    public void shieldsCanBeRaised() {
        shield.raise();
        Assert.assertFalse(shield.isDown());
    }

    @Test
    public void testShieldDown() {
        shield.raise();
        shield.down();
        Assert.assertTrue(shield.isDown());
    }

    @Test
    public void testIsDown() throws Exception {
    }

    @Test
    public void testRaise() throws Exception {
    }

    @Test
    public void takeHitDepletesShields() {
        shield.raise();
        int unabsorbedIntensity = shield.takeHit(500);
        assertEquals("Shields strength depleted by 500 from default of 4000", 3500, shield.getStrength());
        assertEquals("Shields return 0 as unabsorbed", 0, unabsorbedIntensity);
        assertFalse("Shields is still up", shield.isDown());
    }

    @Test
    public void takeHitDoesNotDepleteShieldsIfDown() {
        int unabsorbedIntensity = shield.takeHit(500);
        assertEquals("Shields strength depleted by 0 from default of 4000", 4000, shield.getStrength());
        assertEquals("Shields return the whole strength as unabsorbed", 500, unabsorbedIntensity);
        assertTrue("Shields is still down", shield.isDown());
    }

    @Test
    public void takeHitBucklesShields() {
        shield.raise();
        int unabsorbedIntensity = shield.takeHit(5000);
        assertEquals("Shields have buckled", 0, shield.getStrength());
        assertEquals("Shields return 1000 as unabsorbed", 1000, unabsorbedIntensity);
        assertTrue("Shields is down", shield.isDown());
    }

    @Ignore
    @Test
    public void remainingHitDamagesSubSystem() {
        Subsystem subsystem = new Subsystem();
        shield.raise();
        shield.takeHit(5000);
        assertTrue(shield.isDamaged());
    }

    @Test
    public void testTransferInEnergyInvalidMaximumValue() {
        Assert.assertEquals(1, shield.transferInEnergy(6001));
        Assert.assertEquals(10000, shield.getStrength());
    }

    @Test
    public void testTransferInEnergyInvalidMinimumValue() {
        Assert.assertEquals(0, shield.transferInEnergy(-1));
    }

    @Test
    public void testTransferOutEnergy() {
        Assert.assertEquals(1000, shield.transferOutEnergy(1000));
        Assert.assertEquals(3000, shield.getStrength());
    }

    @Test
    public void testTransferOutEnergyMoreThanShieldsCurrentEnergyLimit() {
        Assert.assertEquals(4000, shield.transferOutEnergy(5000));
        Assert.assertEquals(0, shield.getStrength());
    }

    @Test
    public void testTransferOutEnergyNegativeValueTest() {
        Assert.assertEquals(0, shield.transferOutEnergy(-5000));
        Assert.assertEquals(4000, shield.getStrength());
    }
}