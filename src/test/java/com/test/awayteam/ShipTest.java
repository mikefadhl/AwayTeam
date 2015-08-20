package com.test.awayteam;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import java.util.Random;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mfadh1 on 8/19/15.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Random.class, Ship.class })
public class ShipTest {
    private Ship ship;
    private Shield shield;
    Random mockedRandom;

    @Before
    public void setUp() throws Exception {
        ship = new Ship();
        shield = (Shield) ship.getSubsystems().get("shield");
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
    public void takeHitDepletesShieldsAndBringsDownAndDamagesRandomSubsystem() {
        mockedRandom = PowerMockito.mock(Random.class);
        try {
            PowerMockito.whenNew(Random.class).withNoArguments().thenReturn(mockedRandom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PowerMockito.doReturn(1).when(mockedRandom).nextInt(ship.getSubsystems().size());
        shield.raise();
        ship.takeHit(4001);
        assertEquals("Shields strength depleted by 4001 from default of 4000", 0, shield.getStrength());
        assertTrue("Shields are down", shield.isDown());
        assertTrue("Subsystem is damaged", ship.getSubsystems().get("weapon").isDamaged());
    }
}
