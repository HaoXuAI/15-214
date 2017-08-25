package edu.cmu.cs.cs214.rec13;

import edu.cmu.cs.cs214.rec13.util.TaskList;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * Concrete implementation of {@link AggregateService}
 */
public class AggregateServer implements AggregateService {
    // TODO declare an executor

    // Immutable, thus thread safe
    private final Map<ServerInfo, RemoteFileService> services;

    public AggregateServer(ServerInfo... servers) throws RemoteException, NotBoundException {
        services = new HashMap<>();
        for (ServerInfo serverInfo : servers) {
            Registry registry = LocateRegistry.getRegistry(serverInfo.getHostName(), serverInfo.getPort());
            RemoteFileService service = (RemoteFileService) registry.lookup(serverInfo.getServiceName());
            services.put(serverInfo, service);
        }
    }

    @Override
    public <T> T aggregate(TaskList taskList,
                           Function<FileInputStream, T> mapper,
                           BinaryOperator<T> reducer) throws IOException {
        // TODO implement me!
        return null;
    }



    public static void main(String[] args) throws IOException, NotBoundException, AlreadyBoundException {
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        int port = Integer.parseInt(args[0]);
        Registry registry = LocateRegistry.createRegistry(port);
        registry.bind("Aggregate",
                 UnicastRemoteObject.exportObject(
                         new AggregateServer(
                             new ServerInfo("127.0.0.1", "Peer1", 15215),
                             new ServerInfo("127.0.0.1", "Peer2", 15216)
                         ), port));
        System.out.println("aggregate service running");
    }

}
