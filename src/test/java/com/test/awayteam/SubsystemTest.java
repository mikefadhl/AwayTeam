package com.test.awayteam;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Created by mfadh1 on 8/19/15.
 */
public class SubsystemTest {
    @Test
    public void newSubsystemStartsOutNotDamaged() {
        Subsystem subsystem = new Subsystem();
        assertFalse(subsystem.isDamaged());
    }
}
