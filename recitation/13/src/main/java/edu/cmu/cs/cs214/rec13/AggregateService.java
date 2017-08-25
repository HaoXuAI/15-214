package edu.cmu.cs.cs214.rec13;

import edu.cmu.cs.cs214.rec13.util.TaskList;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Remote;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * Created by tianyuli on 4/24/17.
 */
public interface AggregateService extends Remote {
    <T> T aggregate(TaskList taskList,
                    Function<FileInputStream, T> mapper,
                    BinaryOperator<T> reducer) throws IOException;
}
