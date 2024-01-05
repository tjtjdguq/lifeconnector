package util.file;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.File;
import java.util.List;

public class ExcelFile extends File implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private DAO dao;
    private List<String> headers;
    private static final int paginationSize=1000;
    private static final int maxFileSize=1024*1024*100;

    /**
     * @param autoMapEnabled
     * set Auto Mapping between Excel header and Data
     */
    public void setAutoMapEnabled(boolean autoMapEnabled) {
        this.autoMapEnabled = autoMapEnabled;
    }

    private boolean autoMapEnabled;
    /**
     * @param pathname absolute path where file is to be created
     * by default it will create an blank File
     */
    public ExcelFile(String pathname) {
        super(pathname);
    }
    public ExcelFile Dao(Class claz)  {
        for(Class itf:claz.getInterfaces()){
            if(itf.equals(DAO.class)){
                if(claz.getAnnotation(Mapper.class)!=null){
                    this.dao= (DAO) applicationContext.getBean(claz);
                    return this;
                }
            }
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
