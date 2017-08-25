package edu.cmu.cs.cs214.rec13;

import java.io.Serializable;

/**
 * A configuration class that describes the IP and port of a set of worker Servers. 
 *
 */
public class ServerInfo implements Serializable {
    private final String hostName, serviceName;
    private final int port;

    public ServerInfo(String hostName, String serviceName, int port) {
        this.hostName = hostName;
        this.serviceName = serviceName;
        this.port = port;
    }

    public String getHostName() {
        return hostName;
    }

    public int getPort() {
        return port;
    }

    public String getServiceName() {
        return serviceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServerInfo that = (ServerInfo) o;

        return port == that.port
                && (hostName != null ? hostName.equals(that.hostName) : that.hostName == null)
                && (serviceName != null ? serviceName.equals(that.serviceName) : that.serviceName == null);
    }

    @Override
    public int hashCode() {
        int result = hostName != null ? hostName.hashCode() : 0;
        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
        result = 31 * result + port;
        return result;
    }
}
