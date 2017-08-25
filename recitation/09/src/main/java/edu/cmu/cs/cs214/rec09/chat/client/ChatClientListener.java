package edu.cmu.cs.cs214.rec09.chat.client;

/**
 * Callback for the ChatClient
 */
public interface ChatClientListener {
	/**
	 * handle a message received and processed by the ChatClient
	 * @param name
	 *            the sender's name
	 * @param message
	 *            message text
	 */
	public void onIncomingMsg(String name, String message);
}
