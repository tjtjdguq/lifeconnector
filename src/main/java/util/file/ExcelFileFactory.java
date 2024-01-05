package util.file;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ExcelFileFactory extends FileFactory implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    /**
     * @param param = Class with ibatis Mapper annotation and inherits util.file.DAO
     * @return ExcelFile
     * @throws InappropriateParamException
     */
    public File createFile(Object... param) throws Exception {
        ExcelFile excel = new ExcelFile("/");
        excel.setApplicationContext(applicationContext);
        for (Class itf : param.getClass().getInterfaces()) {
            if (itf.equals(DAO.class)) {
                excel.Dao(param.getClass());
                return excel;
            }
        }
        throw new InappropriateParamException();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
