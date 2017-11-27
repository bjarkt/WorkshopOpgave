package Data;

import Acq.IData;

import java.util.HashMap;

public class DataFacade implements IData {
    private SaveLoad saveLoad;
    public DataFacade() {
        this.saveLoad = new SaveLoad();
    }

    public void save(HashMap<String, String> mapToSave) {
        saveLoad.saveXML(mapToSave);
    }

    public HashMap<String, String> load() {
        HashMap<String, String> map = new HashMap<>();
        map = saveLoad.loadXML();
        return map;
    }
}
