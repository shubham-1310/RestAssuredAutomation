package files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {

    public static JsonPath rawToJson(String abc){
        JsonPath js= new JsonPath(abc);
        return js;

    }
}
