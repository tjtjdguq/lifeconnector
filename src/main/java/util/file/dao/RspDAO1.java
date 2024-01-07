package util.file.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

@Mapper
public interface RspDAO1 extends DAO {
    @Override
    public <String> List<T> getPaginatedData(String condition);
}
