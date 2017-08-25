package edu.cmu.cs.cs214.rec12;

import java.io.IOException;

/** A process that sends a mapper and reducer function over a wire */
public class Client {
    private static final int BUFFER_SIZE = 4096;
    private final ServerInfo server;

    public static void main(String[] args) throws IOException {
        Client client = new Client(new ServerInfo("localhost", 15214));
        client.downloadArtOfWar();
        client.countWordsInMetamorphosis();
    }

    /**
     * @param server {@link ServerInfo} representing the server this client will send work to.
     */
    public Client(ServerInfo server) {
        this.server = server;
    }

    /**
     * Attempts to download the art of war
     */
    public void downloadArtOfWar() {

        // TODO: Open a Socket connection to the Server.

        // TODO: Create an ObjectOutputStream on the Socket to write to the Server.

        // TODO: Send a request to download a copy of the file assets/art_of_war.txt
        // (hint: see ObjectOutputStream.writeObject(..)*/

        // TODO: Flush the ObjectOutputStream you've been writing.

        // TODO: Read in the response and write out to local/art_of_war.txt
    }

    /**
     * Attempts to count number of words in metamorphosis
     */
    public void countWordsInMetamorphosis() {
        // TODO: Open a Socket connection to the Server.

        // TODO: Create an ObjectOutputStream on the Socket to write to the Server.

        // TODO: Send a request to download a copy of the file
        // (hint: see ObjectOutputStream.writeObject(..)*/

        // TODO: Flush the ObjectOutputStream you've been writing.

        // TODO: Read in the response and print out to the command line

        // TODO: Create a PickledClass for the word count function to be executed.

        // TODO: Send a request to execute the function

        // TODO: Flush the ObjectOutputStream you've been writing.

        // TODO: Read in the response and print to command line
    }
}


