package code;

import files.payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class ComplexJsonParse {


    public void complexJson() {
        int cumulativeCostForAllCourses=0;
        JsonPath js = new JsonPath(payload.coursePrice());
        int count = js.getInt("courses.size()");
//        System.out.println(count);
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
//        System.out.println(purchaseAmount);
        for (int i = 0; i < count; i++) {
            String courseTitle = js.getString("courses["+i+"].title");
            int coursePrice =js.getInt("courses["+i+"].price");
            int courseCopies=js.getInt("courses["+i+"].copies");
            int totalAmountForCourse=coursePrice*courseCopies;
            System.out.println("Cource Title is :- "+courseTitle + " And the price per course is : "+coursePrice+" And Copies sold is " +courseCopies+
                    " And Total price of Course is " +totalAmountForCourse);
             cumulativeCostForAllCourses=cumulativeCostForAllCourses+totalAmountForCourse;

        }
        System.out.println("Cumulative Sum of all the courses : " +cumulativeCostForAllCourses);
        Assert.assertEquals(cumulativeCostForAllCourses,purchaseAmount);
    }

}
