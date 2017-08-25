package edu.cmu.cs.cs214.rec13.util;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * A class loader that translates byte arrays in class-file format into actual classes.
 */
public class ByteArrayClassLoader extends URLClassLoader {
    // These nasty fields are used to pass info from getClassFromBytes to findClass
    private String fullClassName;
    private byte[] classBytes;

    public ByteArrayClassLoader() {
        super(new URL[0]);
    }

    /**
     * Loads a class for the specified name and "class file bytes" (which must match),
     * and returns its class object.
     *
     * @param name the fully qualified name of the class
     * @param classBytes the contents of the class file for the class
     * @throws ClassNotFoundException if name doesn't match classBytes
     */
    public Class<?> getClassFromBytes(String name, byte[] classBytes) throws ClassNotFoundException {
        this.fullClassName = name;
        this.classBytes = classBytes;
        try {
            return findClass(name);
        } finally {
            this.fullClassName = null;
            this.classBytes = null;
        }
    }

    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        return name.equals(fullClassName) ?
                defineClass(name, classBytes, 0, classBytes.length) : super.findClass(name);
    }
}
