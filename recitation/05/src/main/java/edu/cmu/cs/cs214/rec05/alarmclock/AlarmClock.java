package edu.cmu.cs.cs214.rec05.alarmclock;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * An alarm clock that supports listeners that are triggered when the alarm goes
 * off.
 * 
 * The alarm clock has an alarm time and a current time. The alarm time is when
 * the alarm should go off. The current time is the time now that the system
 * tells it.
 */
public class AlarmClock {
  private final List<AlarmListener> alarmListeners; // The set of listeners
  private final Date alarmTime; // The time the alarm should go off

  /** The thread that is run to update the time. */
  private Thread updateTimeThread;

  public AlarmClock(Date alarmTime) {
    this.alarmListeners = new ArrayList<AlarmListener>();
    this.alarmTime = alarmTime;
    updateTimeThread = new Thread(new Runnable() {
      @Override
      public void run() {
        Date time;
        for (time = new Date(); time.before(alarmTime); time = new Date()) {
          // Wait 100 ms before checking again.
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            System.out.println("Alarm Clock was interrupted.");
          }
        }

        // Alarm has gone off, notify our listeners.
        for (AlarmListener al : alarmListeners) {
          al.onAlarmEvent(AlarmClock.this, time);
        }
      }
    });
  }

  /**
   * Starts updating the AlarmClock's time in a new thread.
   */
  public void start() {
    updateTimeThread.start();
  }

  /**
   * Adds a listener which triggers some action when the alarm goes off.
   * 
   * @param lis
   *          The listener to add.
   */
  public void addAlarmListener(AlarmListener lis) {
    alarmListeners.add(lis);
  }

  /**
   * Notify all listeners that the alarm has gone off.
   * 
   * @param time
   *          The current time.
   */
  private void publishAlarmEvent(Date time) {
    for (AlarmListener al : alarmListeners) {
      al.onAlarmEvent(this, time);
    }
  }
}
