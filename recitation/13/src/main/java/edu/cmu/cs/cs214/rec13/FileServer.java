package edu.cmu.cs.cs214.rec13;

import com.healthmarketscience.rmiio.RemoteOutputStream;
import com.healthmarketscience.rmiio.RemoteOutputStreamServer;
import com.healthmarketscience.rmiio.SimpleRemoteOutputStream;
import edu.cmu.cs.cs214.rec13.util.PickledClass;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;


/**
 * Concrete implementation for {@link RemoteFileService}
 */
public class FileServer implements RemoteFileService {
    private static final Set<Integer> WHITE_LIST = new HashSet<>();
    static {
        WHITE_LIST.add(1858596841);
        WHITE_LIST.add(1574945135);
    }
    private final Path root;

    public FileServer(String root) throws IOException {
        this.root = Paths.get(root).toRealPath();
    }

    @Override
    public RemoteOutputStream fetch(String filename) throws IOException {
        assertLegalFile(filename);
        RemoteOutputStreamServer out = new SimpleRemoteOutputStream(new FileOutputStream(filename));
        return out.export();
    }

    @Override
    public <T> T exec(Function<FileInputStream, T> reducer, String filename) throws IOException {
        assertLegalFile(filename);
        assertLegalCode(reducer);
        return reducer.apply(new FileInputStream(filename));
    }

    private void assertLegalFile(String filename) throws IOException {
        if (!Paths.get(filename).toRealPath().startsWith(root)) {
            throw new IllegalArgumentException("Access denied for " + filename);
        }
    }

    private void assertLegalCode(Object o) {
        if (!WHITE_LIST.contains(new PickledClass(o.getClass()).hashCode())) {
            throw new IllegalArgumentException("Unrecognized code execution" + o.getClass().getSimpleName());
        }
    }

    // Command line argument: <Service name> <port number> <root directory>
    public static void main(String[] args) throws IOException, AlreadyBoundException {
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        int port = Integer.parseInt(args[1]);
        Registry registry = LocateRegistry.createRegistry(port);
        registry.bind(args[0],
                UnicastRemoteObject.exportObject(new FileServer(args[2]), port));
        System.out.println("File Service running");
    }

}
