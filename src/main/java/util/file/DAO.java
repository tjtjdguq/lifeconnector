package util.file;

import java.util.List;

public interface DAO {
    public List<?> getPaginatedData(Object...param);
    public int selectDataSize(Object...param);
}
