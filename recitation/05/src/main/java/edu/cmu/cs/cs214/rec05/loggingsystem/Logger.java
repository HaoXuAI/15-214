package edu.cmu.cs.cs214.rec05.loggingsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Supports writing of debug and error messages (with different prefixes) to
 * either the console or an output file. Configurable via constant fields within
 * the class.
 * 
 * TODO Rewrite the code such that different loggers can be configured and
 * added.
 */
public class Logger {

	// Change this to false to disable logging to the console
	private static final boolean CONSOLE_ENABLED = true;

	// Change this to false to disable logging to the file
	private static final boolean LOGFILE_ENABLED = true;
	private static final String LOGFILE_NAME = "log.txt";

	// Strings
	private static final String DEBUG_PREFIX = "[Debug]";
	private static final String ERROR_PREFIX = "[Error]";
	private static final String LOGGER_STARTED_UP = "Logger started up.";
	private static final String LOGGER_SHUTTING_DOWN = "Logger shutting down.";

	private PrintWriter fileOut = null;

	public Logger() {
		if (LOGFILE_ENABLED) {
			try {
				fileOut = new PrintWriter(new File(LOGFILE_NAME));
				fileOut.println(LOGGER_STARTED_UP);
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		if (CONSOLE_ENABLED) {
			System.out.println(LOGGER_STARTED_UP);
		}
	}

	/**
	 * Writes a debug message to the logging system.
	 * 
	 * @param message
	 *            The debug message to write.
	 */
	public void writeDebug(String message) {
		String logLine = DEBUG_PREFIX + " " + message;
		if (CONSOLE_ENABLED) {
			System.out.println(logLine);
		}
		if (LOGFILE_ENABLED) {
			fileOut.println(logLine);
		}
	}

	/**
	 * Writes an error message to the logging system.
	 * 
	 * @param error
	 *            The error message to write.
	 */
	public void writeError(String error) {
		String logLine = ERROR_PREFIX + " " + error;
		if (CONSOLE_ENABLED) {
			System.out.println(logLine);
		}
		if (LOGFILE_ENABLED) {
			fileOut.println(logLine);
		}
	}

	/**
	 * Shuts down the logging system. After this method is called, the logger
	 * should not be used.
	 */
	public void close() {
		if (CONSOLE_ENABLED) {
			System.out.println(LOGGER_SHUTTING_DOWN);
		}
		if (LOGFILE_ENABLED) {
			fileOut.println(LOGGER_SHUTTING_DOWN);
			fileOut.close();
		}
	}

}
