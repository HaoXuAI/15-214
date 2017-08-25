package edu.cmu.cs.cs214.rec12.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This class represents a class file that is ready to be serialized and sent
 * through the network, or "pickled", so to speak. A pickled class can then be
 * revived into a class object that can be used for reflections.
 */
public class PickledClass implements Serializable {
    /** The fully qualified class name (e.g. "java.lang.String") */
    private final String name;
    
    /** The bytes from the class file for this class */
    private final byte[] classFile;

    /**
     * Instantiates a new pickled class from a class object.
     *
     * @param classObj the class object of the class to be pickled.
     */
    public PickledClass(Class<?> classObj) {
        name = classObj.getName();
        InputStream is = classObj.getResourceAsStream(classObj.getSimpleName() + ".class");
        try {
            classFile = inputStreamToByteArray(is);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Instantiates a new pickled class from a class file.
     *
     * @param fullClassName the fully qualified class name
     * @param path          the relative or absolute path to the classfile
     * @throws IllegalArgumentException if the class file is invalid. Note 
     *     that you will *NOT* get this exception if the classfile and
     *     the name don't match, but the revive method will fail when applied
     *     to such a (broken) pickled class.
     */
    public PickledClass(String fullClassName, Path path) {
        try {
            this.name = fullClassName;
            this.classFile = Files.readAllBytes(path);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Load the class onto the current vm and return the class object for it.
     *
     * @return the loaded class object
     * @throws IllegalStateException if the class file bytes do not match the
     *         class name.
     */
    public Class<?> revive() {
    	ByteArrayClassLoader loader = new ByteArrayClassLoader();
        try {
            return loader.getClassFromBytes(name, classFile);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    /** Returns a byte array containing all of the data read from the given InputStream */
    private static byte[] inputStreamToByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
        byte[] buffer = new byte[4096];
        int bytesRead;
        while((bytesRead = is.read(buffer)) > 0) {
            out.write(buffer, 0, bytesRead);
        }
        return out.toByteArray();
    }
    
    // Appease the serialization gods
    private static final long serialVersionUID = 42L;   
}
