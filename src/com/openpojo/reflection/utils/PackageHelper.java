package com.openpojo.reflection.utils;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import com.openpojo.reflection.exception.ReflectionException;

/**
 * This is a utility class to help enumerate and generate PojoClasses for specific pacakge.
 * 
 * @author oshoukry
 */
public class PackageHelper {

    /**
     * Get a list of all classes in the package.
     * @return
     *      List of all classes in the package.
     */
    public static List<Class<?>> getClasses(String packagename) {
        List<Class<?>> classes = new LinkedList<Class<?>>();

        // Get a File object for the package
        File directory;
        directory = getPackageAsDirectory(packagename);

        // Get the list of the files contained in the package
        String[] files = directory.list();

        for (int i = 0; i < files.length; i++) {
            if (isClass(files[i])) {
                try {
                    classes.add(Class.forName(getFQClassName(packagename, files[i])));
                } catch (ClassNotFoundException e) {
                    // this should never happen since we get the entry from directory listing.
                    throw new ReflectionException(e);
                }
            }
        }

        return classes;
    }

    /**
     * Turn a java package into a directory listing.
     * 
     * @return
     *         Return a file representation of the directory.
     */
    private static File getPackageAsDirectory(String packagename) {

        ClassLoader cld = Thread.currentThread().getContextClassLoader();
        if (cld == null) {
            throw new ReflectionException("Can't get class loader.");
        }

        String path = packagename.replace('.', '/');
        URL resource = cld.getResource(path);
        if (resource == null) {
            throw new ReflectionException("No resource for " + path);
        }

        File directory = null;
        directory = new File(resource.getFile());
        if (!directory.exists()) {
            throw new ReflectionException(packagename + " does not appear to be a valid package");
        }

        return directory;
    }

    private static final String CLASS_SUFFIX = ".class";

    /**
     * Returns true if the string refers to a class entry (i.e. ends with .class).
     * @param entry
     *          The class name.
     * @return
     *          True if the className ends with CLASS_SUFFIX.
     */
    private static boolean isClass(String entry) {
        if (entry.endsWith(CLASS_SUFFIX)) {
            return true;
        }
        return false;
    }

    /**
     * Return a fully qualified class name given the fileEntry for the classname, and package this helper represents.
     * @param fileEntry
     *          The classname to qualify.
     * @return
     *          The fully qualifed package name and classname.
     */
    private static String getFQClassName(String packagename, String fileEntry) {
        String className = packagename + '.' + fileEntry.substring(0, fileEntry.length() - CLASS_SUFFIX.length());
        return className;
    }
}
