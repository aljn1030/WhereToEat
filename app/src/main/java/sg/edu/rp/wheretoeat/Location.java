package sg.edu.rp.wheretoeat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;


public class Location implements Serializable {

    public double lat;
    public double lng;

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("lat",lat);
            jsonObject.put("lng",lng);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    public static Location jsonToLocation(String json){
        final Location location = new Location();

        try {
            JSONObject jsonObject = new JSONObject(json);
            location.lat = jsonObject.getDouble("lat");
            location.lng = jsonObject.getDouble("lng");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return location;
    }

    static class Builder{

        private Location location;

        public Builder(){
            location = new Location();
        }

        public Builder lat(double lat){
            location.lat = lat;
            return this;
        }

        public Builder lng(double lng){
            location.lng = lng;
            return this;
        }

        public Location build(){
            return location;
        }
    }

}
