package fr.pag.pay.bluetooth;

import java.util.Timer;
import java.util.TimerTask;

public class SingleTimer extends Timer {

    private boolean cancelled;

    @Override
    public void schedule(TimerTask task, long delay, long period) {
        super.schedule(task, delay, period);
        cancelled = false;
    }

    @Override
    public void cancel() {
        super.cancel();
        cancelled = true;
    }

    public boolean isCancelled() {
        return cancelled;
    }

}
