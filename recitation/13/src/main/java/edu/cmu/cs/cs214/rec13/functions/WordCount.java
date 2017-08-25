package edu.cmu.cs.cs214.rec13.functions;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Scanner;
import java.util.function.Function;


public class WordCount implements Function<FileInputStream, Integer>, Serializable {
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
