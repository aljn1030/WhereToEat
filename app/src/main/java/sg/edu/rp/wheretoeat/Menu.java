package sg.edu.rp.wheretoeat;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Menu implements Serializable{

    public List<String> list = new ArrayList<String>();

    @Override
    public String toString() {
        JSONArray jsonArray = new JSONArray();

        if(list != null){
            for(String s : list){
                jsonArray.put(s);
            }
        }

        return jsonArray.toString();
    }


    public static Menu jsonToMenu(String json){
        final Menu menu = new Menu();

        try {
            final List<String> list = new ArrayList<>();
            final JSONArray jsonArray = new JSONArray(json);
            for(int i=0; i < jsonArray.length(); i++){
                list.add(jsonArray.getString(i));
            }
            menu.list = list;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return menu;
    }

    static class Builder{

        private List<String> list;

        public Builder() {
            list = new ArrayList<String>();
        }

        public Builder append(String menuItem) {
            list.add(menuItem);
            return this;
        }

        public Menu build() {
            Menu menu = new Menu();
            menu.list = list;
            return menu;
        }
    }

}