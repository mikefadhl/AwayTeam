package com.test.awayteam;

/**
 * Created by mfadh1 on 8/19/15.
 */
public abstract class Subsystem {

    private boolean isDamaged = false;

    public boolean isDamaged() {
        return isDamaged;
    }

    public void damage(){
        isDamaged = true;
    }
}
