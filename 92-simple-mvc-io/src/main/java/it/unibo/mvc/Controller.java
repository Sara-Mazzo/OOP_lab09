package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private static final String HOME = System.getProperty("user.home");
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final String DEFAULT_FILE = "output.txt";

    private final File defaultFile = new File(HOME + FILE_SEPARATOR + DEFAULT_FILE);
    private File currentFile;

    /**
     * sets the default file as current.
     */
    public Controller() {
        this.currentFile = defaultFile;
    }

    /**
     * @param file the file to set as the new destination.
     */
    public void setFile(final File file) {
        Objects.requireNonNull(file);
        final File parent = file.getParentFile();
        if (parent != null && parent.exists()) {
            this.currentFile = file;
        } else {
            throw new IllegalArgumentException("Destination directory does not exist");
        }
    }

    /**
     * @return the current file.
     */
    public File getFile() {
        return this.currentFile;
    }

    /**
     * @return the current file path.
     */
    public String getPath() {
        return this.currentFile.getPath();
    }

    /**
     * @param string what to write in the file
     * @throws IOException if an I/O error occurs while writing
     */
    public void writeOnFile(final String string) throws IOException {
        try (PrintStream ps = new PrintStream(currentFile.getPath(), StandardCharsets.UTF_8)) {
            ps.print(string);
        }
    }
}
