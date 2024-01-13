package util.file.excel;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExcelFile {
    private File file;

    public ExcelFile() throws IOException {
        File directory=null;
        if (System.getProperty("os.name").contains("linux")) {
            directory = new File("/ccbss/excel/");
        } else {
            directory = new File("C:\\ccbss\\excel");
        }
        file= new File(directory,LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        SXSSFWorkbook workbook=new SXSSFWorkbook();
        try(FileOutputStream outputStream=new FileOutputStream(file)){
            workbook.write(outputStream);
        }
    }

    public ExcelFile(String pathname) {
        file = new File(pathname);
    }

    public File getFile() {
        return file;
    }
}
