package util.file.excel;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import util.file.dao.DAO;
import util.file.exception.InvalidDirectoryException;
import util.file.exception.MissingImplementException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RspExcelFile extends ExcelFile implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private List<DAO> daos = new ArrayList<>();
    private Map<String, ? extends List<?extends CharSequence>> headers;
    private static final int paginationSize = 1000;
    private static final int maxFileSize = 1024 * 1024 * 100;
    private boolean autoMapEnabled;

    /**
     * @throws IOException
     * @throws InvalidDirectoryException creates blank excel file at /ccbss/excel/
     */
    public RspExcelFile() throws IOException, InvalidDirectoryException {
        super();
    }

    /**
     * @param path
     * @throws IOException
     * @throws InvalidDirectoryException creates blank excel file at /ccbss/excel/{path}
     */
    public RspExcelFile(String path) throws IOException, InvalidDirectoryException {
        super(path);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public RspExcelFile dao(Class claz) throws MissingImplementException {
        boolean isDaoImplemented=false;
        for (Class itf : claz.getInterfaces()) {
            if (itf.equals(DAO.class)) {
                this.daos.add((DAO) applicationContext.getBean(claz));
                isDaoImplemented=true;
            }
        }
        if(!isDaoImplemented){
            throw new MissingImplementException();
        }
        return this;
    }

    public RspExcelFile daos(List<Class> clazz) throws MissingImplementException {
        for (Class claz : clazz) {
            boolean isDaoImplemented=false;
            for (Class itf : claz.getInterfaces()) {
                if (itf.equals(DAO.class)) {
                    this.daos.add((DAO) applicationContext.getBean(claz));
                    isDaoImplemented=true;
                }
            }
            if(!isDaoImplemented){
                throw new MissingImplementException();
            }
        }
        return this;
    }

    /**
     * @param headers set headers for each sheet
     *                key - sheetName
     *                value - a header consisting a list of columns
     */
    public RspExcelFile Headers(Map<String, ?extends List<String>> headers) {
        this.headers = headers;
        return this;
    }

    /**
     * @param autoMapEnabled set Auto Mapping between Excel header and Data
     */
    public void setAutoMapEnabled(boolean autoMapEnabled) {
        this.autoMapEnabled = autoMapEnabled;
    }
}
