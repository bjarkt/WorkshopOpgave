package Acq;

import java.util.HashMap;

public interface IData {
    void save(HashMap<String, String> mapToSave);
    HashMap<String, String> load();
}
