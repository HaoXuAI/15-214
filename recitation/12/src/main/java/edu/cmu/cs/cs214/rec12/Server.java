package edu.cmu.cs.cs214.rec12;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** A process that iterates over words in file, mapping and reducing and finally prints the result. */
public class Server {
    private final int port;
    private static final int BUFFER_SIZE = 4096;

    /**
     * @param port Server port on which we listen for client connections.
     */
    public Server(int port) {
        this.port = port;
    }

    /**
     * Helper class to process a single request from a client.
     *
     * This could be implemented more cleanly as an anonymous Runnable above, but
     * we implemented it as a private static inner class to more-cleanly show the
     * separation between the network server code above and the handling of each
     * client connection below.
     */
    private static class ServerCommandHandler implements Runnable {
        private final Socket socket;

        /**
         * @param socket Socket we are connected to the Client with.
         */
        public ServerCommandHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // TODO: Create a new ObjectInputStream on the accepted Socket

                // TODO: Read in the message type of the request and case on the result

                // TODO: if the request is a fetch, read in a string representing the file name,
                // you may ignore the case where the file does not exist, or the message is malformed

                // TODO: Read from the file with a buffer and forward its contents to the client until done

                // TODO: if the request is an exec, read in the PickledClass representing the task

                // TODO: Get the Class of the function from the PickledClass.
                // (hint: see PickledClass.revive()).

                // TODO: Create a new instance of the function.
                // (hint: see Class.newInstance()).

                // TODO: Apply the function

                // TODO: Write back the result and flush

            } catch (Exception e) {
                e.printStackTrace(System.err);  // We just print stack trace and exit this thread if we get an exception.
            }

            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Warning:  Got exception " + e + " while closing client connection.");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Socket sock;
        ExecutorService executorService = Executors.newCachedThreadPool();
        try (ServerSocket servsock = new ServerSocket(Integer.parseInt(args[0]))) {
            while (true) {
                System.err.println("Waiting...");
                sock = servsock.accept();
                System.err.println("Accepted connection : " + sock);
                executorService.submit(new ServerCommandHandler(sock));
            }
        }
    }
}
