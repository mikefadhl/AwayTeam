package com.test.awayteam;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.Random;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Created by mfadh1 on 8/19/15.
 */

public class ShipTest {
    private final String shieldKey = "shield";
    private Ship ship;
    private Shield shield;

    @Before
    public void setUp() {
        ship = new Ship();
        shield = (Shield) ship.getSubsystems().get(shieldKey);
    }

    @Test
    public void shipHasSubsystem() {
        Ship ship = new Ship();
        assertFalse("Ship has a list of subsystems", ship.getSubsystems().isEmpty());
        assertEquals("The ship has three subsystems now", 3, ship.getSubsystems().size());
    }

    @Test
    public void shipHasThreeSubsystem() {
        Ship ship = new Ship();
        assertEquals("The ship has a Shield subsystem", Shield.class, ship.getSubsystems().get("shield").getClass());
        assertEquals("The ship has a Weapon subsystem", Weapon.class, ship.getSubsystems().get("weapon").getClass());
        assertEquals("The ship has a Engine subsystem", Engine.class, ship.getSubsystems().get("engine").getClass());
    }

    @Test
    public void shipHasSubsystemInOrder() {
        Ship ship = new Ship();
        Object[] values = ship.getSubsystems().values().toArray();
        assertEquals("The first subsystem is Shield", Shield.class, values[0].getClass());
        assertEquals("The second subsystem is Weapon", Weapon.class, values[1].getClass());
        assertEquals("The third subsystem is Engine", Engine.class, values[2].getClass());
    }

    @Test
    public void takeHitDepletesShields() {
        shield.raise();
        ship.takeHit(3999);
        assertEquals("Shields strength depleted by 3999 from default of 4000", 1, shield.getStrength());
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
    public void takeHitDepletesShieldsAndBringsDownAndDamagesRandomShieldSubsystem() {
        Random mockedRandom = Mockito.mock(Random.class);
        ship.setGenerator(mockedRandom);
        shield.raise();
        when(mockedRandom.nextInt(anyInt())).thenReturn(0);
        ship.takeHit(4001);
        assertEquals("Shields strength depleted by 4001 from default of 4000", 0, shield.getStrength());
        assertTrue("Shields are down", shield.isDown());
        assertTrue("Subsystem Weapon is damaged", ship.getSubsystems().get("shield").isDamaged());
    }

    @Test
    public void takeHitDepletesShieldsAndBringsDownAndDamagesRandomEngineSubsystem() {
        Random mockedRandom = Mockito.mock(Random.class);
        ship.setGenerator(mockedRandom);
        shield.raise();
        when(mockedRandom.nextInt(anyInt())).thenReturn(2);
        ship.takeHit(4001);
        assertEquals("Shields strength depleted by 4001 from default of 4000", 0, shield.getStrength());
        assertTrue("Shields are down", shield.isDown());
        assertTrue("Subsystem Weapon is damaged", ship.getSubsystems().get("engine").isDamaged());
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

    @Test
    public void testGiveEnergyToShieldMoreThanCurrentEnergyLevelInShip(){
        ship.giveEnergyToShield(4001);
        assertEquals(0, ship.getStrength());
        assertEquals(8000, shield.getStrength());
    }
}
