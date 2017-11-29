package Acq;

import java.util.HashMap;
import java.util.Map;

public interface IData {
    void save(HashMap<String, String> mapToSave);
    Map<String, String> load();
}
