package edu.cmu.cs.cs214.rec05.alarmclock;

import java.util.Date;

import javax.swing.SwingUtilities;

/**
 * Creates an alarm that goes off in 3 seconds.
 */
public class Main {

  /** Number of milliseconds in one second */
  private static final int SECOND = 1000;

  /**
   * Creates an alarm clock set to go off in three seconds, with three
   * listeners. Each listener has a different behavior.
   * 
   * @param args
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new Main().createAndShowAlarmClockGUI();
      }
    });
  }

  public void createAndShowAlarmClockGUI() {
    // Launches an alarm window when the alarm goes off
    AlarmListener alarmWindowListener = new AlarmWindow();

    // Makes a beeping sound when the alarm goes off
    AlarmListener beeperListener = new Beeper();

    // Checks processes when alarm goes off
    AlarmListener watchDogListener = new ReliabilityWatchDog();

    // Calculate the time 3 seconds from now
    Date now = new Date();
    Date alarmTime = new Date(now.getTime() + 3 * SECOND);
    System.out.println("Time now is " + now.toString());
    System.out.println("Alarm set to go off at " + alarmTime.toString());

    // Create a new alarm clock and set the alarm
    AlarmClock alarmClock = new AlarmClock(alarmTime);

    // Add all the listeners to the alarm clock
    alarmClock.addAlarmListener(alarmWindowListener);
    alarmClock.addAlarmListener(beeperListener);
    alarmClock.addAlarmListener(watchDogListener);

    // Start the alarm clock
    alarmClock.start();
  }
}
