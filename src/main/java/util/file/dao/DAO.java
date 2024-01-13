package util.file.dao;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public interface DAO {

    public <K> List<T> getPaginatedData(K condition);
}
