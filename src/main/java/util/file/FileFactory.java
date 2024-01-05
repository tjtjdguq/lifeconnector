package util.file;

import java.io.File;

public abstract class FileFactory {
    public abstract File createFile(Object...param) throws Exception;
}
