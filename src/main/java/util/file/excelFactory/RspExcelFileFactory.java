package util.file.excelFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.file.dao.RspDAO1;
import util.file.dao.RspDAO2;
import util.file.excel.RspExcelFile;
import util.file.exception.InappropriateParamException;
import util.file.exception.InvalidDirectoryException;
import util.file.exception.MissingImplementException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class RspExcelFileFactory extends ExcelFileFactory {
    private RspDAO1 rspDao1;
    private RspDAO2 rspDao2;

    @Autowired
    public RspExcelFileFactory(RspDAO1 rspDao1, RspDAO2 rspDao2) {
        this.rspDao1 = rspDao1;
        this.rspDao2 = rspDao2;
    }

    /**
     * @param params = Classes with ibatis Mapper annotation and inherits util.file.dao.DAO
     * @return ExcelFile
     * @throws InappropriateParamException
     */
    @Override
    public File createExcelFile(Object... params) {
        Map<String, Class> headers = new HashMap();
        headers.put("sheet1", RspDAO1.class);
        headers.put("sheet2", RspDAO2.class);
        RspExcelFile excel = null;
        try {
            excel = new RspExcelFile()
                    .dao(RspDAO1.class)
                    .dao(RspDAO2.class);
//                    .Headers(headers);
        } catch (MissingImplementException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidDirectoryException e) {
            throw new RuntimeException(e);
        }
        //make rsp specific informant file

        return excel.getExcelFile();
    }

}
