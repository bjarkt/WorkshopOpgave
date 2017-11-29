package Data;

import Acq.IData;

import java.util.HashMap;
import java.util.Map;

public class DataFacade implements IData {
    private SaveLoad saveLoad;
    public DataFacade() {
        this.saveLoad = new SaveLoad("log.xml");
    }

    public void save(HashMap<String, String> mapToSave) {
        saveLoad.saveXML(mapToSave);
    }

    public Map<String, String> load() {
        Map<String, String> map = saveLoad.loadXML();
        return map;
    }
}
