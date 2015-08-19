package com.test.awayteam;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by pkar1 on 8/18/15.
 */
public class ShieldTest {
    private Shield shield;

    @Before
    public void setUp() throws Exception {
        shield = new Shield();
    }

    @Test
    public void shieldsByDefaultDown(){
        Assert.assertTrue(shield.isDown());
    }

    @Test
    public void shieldsCanBeRaised(){
        shield.raise();
        Assert.assertFalse(shield.isDown());
    }

    @Test
    public void testShieldDown(){
        shield.down();
        Assert.assertTrue(shield.isDown());
    }

    @Test
    public void testIsDown() throws Exception {
    }

    @Test
    public void testRaise() throws Exception {}

    @Test
    public void testTransferEnergy(){
        shield.transferEnergy(1000);
        assertEquals(5000, shield.getStrength());
    }

    @Test
    public void takeHitDepletesShields() {
        shield.takeHit(500);
        assertEquals("Shields strength depleted by 500 from default of 4000", 3500, shield.getStrength());
    }

    @Test
    public void takeHitBucklesShields(){
        shield.takeHit(5000);
        shield.setIsDamaged(true);
        assertEquals("Shields have buckled", 0, shield.getStrength());
        assertEquals("Shields are damaged", true, shield.isDamaged());
    }
}