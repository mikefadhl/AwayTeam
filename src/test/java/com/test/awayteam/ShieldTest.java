package com.test.awayteam;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by pkar1 on 8/18/15.
 */
public class ShieldTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shieldsByDefaultDown(){
        Shield shield = new Shield();
        Assert.assertTrue(shield.isDown());
    }

    @Test
    public void shieldsCanBeRaised(){
        Shield shield = new Shield();
        shield.raise();
        Assert.assertFalse(shield.isDown());
    }

    @Test
    public void testShieldDown(){
        Shield shield = new Shield();
        shield.down();
        Assert.assertTrue(shield.isDown());
    }

    @Test
    public void testIsDown() throws Exception {
    }

    @Test
    public void testRaise() throws Exception {}

    public void testTransferEnergy(){
        Shield shield = new Shield();
        shield.transferEnergy(1000);
        assertEquals(5000, shield.getStrength());
    }

    @Test
    public void takeHitDepletesShields() {
        Shield shield = new Shield();

        shield.takeHit(500);
        assertEquals("Shields strength depletes by 500 from default of 4000", 3500, shield.getStrength());
    }
}