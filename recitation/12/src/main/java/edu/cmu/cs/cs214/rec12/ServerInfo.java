package edu.cmu.cs.cs214.rec12;

/**
 * A configuration class that describes the IP and port of a set of worker Servers.
 *
 */
public class ServerInfo {
    private final String hostName;
    private final int port;

    public ServerInfo(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public String getHostName() {
        return hostName;
    }

    public int getPort() {
        return port;
    }
}

