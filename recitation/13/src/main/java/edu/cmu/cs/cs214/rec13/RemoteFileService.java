package edu.cmu.cs.cs214.rec13;

import com.healthmarketscience.rmiio.RemoteOutputStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Remote;
import java.util.function.Function;

/**
 * Created by tianyuli on 4/24/17.
 */
public interface RemoteFileService extends Remote {

    RemoteOutputStream fetch(String filename) throws IOException;

    <T> T exec(Function<FileInputStream, T> reducer, String filename) throws IOException;
}
