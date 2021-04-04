package code;

import files.payload;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

@Test
public class ComplexJsonParse {


    public void complexJson() {
        JsonPath js = new JsonPath(payload.coursePrice());
        int count = js.getInt("courses.size()");
        System.out.println(count);
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);
        for (int i = 0; i < count; i++) {
            String courseTitle = js.getString("courses["+i+"].title");
            int coursePrice =js.getInt("courses["+i+"].price");
            System.out.println("Cource Title is :- "+courseTitle + " And the price for this course is : "+coursePrice);

        }
    }

}
