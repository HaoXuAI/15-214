package edu.cmu.cs.cs214.rec13;

import edu.cmu.cs.cs214.rec13.functions.Plus;
import edu.cmu.cs.cs214.rec13.functions.WordCount;
import edu.cmu.cs.cs214.rec13.util.TaskList;

import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;


/** A process that sends a mapper and reducer function over a wire */
public class Client {
    private static final ServerInfo aggregate = new ServerInfo("127.0.0.1", "Aggregate", 15214);
    private static final ServerInfo peer1 = new ServerInfo("127.0.0.1", "Peer1", 15215);
    private static final ServerInfo peer2 = new ServerInfo("127.0.0.1", "Peer2", 15216);

    public static void main(String[] args) throws IOException, NotBoundException {
        TaskList taskList = new TaskList.Builder()
                .forServer(peer1)
                .addTask("asset1/art_of_war.txt")
                .addTask("asset1/the_yellow_wallpaper.txt")
                .forServer(peer2)
                .addTask("asset2/metamorphosis.txt")
                .build();
        AggregateService service = (AggregateService) LocateRegistry
                .getRegistry(aggregate.getHostName(), aggregate.getPort())
                .lookup(aggregate.getServiceName());
        System.out.println(service.aggregate(taskList, new WordCount(), new Plus()));
    }
}


