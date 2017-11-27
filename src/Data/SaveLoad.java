package Data;

import java.util.HashMap;

public class SaveLoad {

    public void saveXML(HashMap<String, String> mapToSave) {
        System.out.println("saved");
    }

    public HashMap<String, String> loadXML() {
        HashMap<String, String> map = new HashMap<>();
        map.put("HEJ", "med dig");
        return map;
    }

}
