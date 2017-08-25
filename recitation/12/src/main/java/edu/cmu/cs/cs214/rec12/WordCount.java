package edu.cmu.cs.cs214.rec12;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Created by tianyuli on 4/17/17.
 */
public class WordCount implements Function<FileInputStream, Integer> {
    @Override
    public Integer apply(FileInputStream fileInputStream) {
        int result = 0;
        Scanner scanner = new Scanner(fileInputStream);
        scanner.useDelimiter("\\W+");
        while (scanner.hasNext()) {
            scanner.next();
            result++;
        }
        return result;
    }
}
