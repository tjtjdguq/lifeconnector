package util.file.excelFactory;

import java.io.File;

public abstract class ExcelFileFactory {

    public abstract File createExcelFile(Object... params);
}
