package LibraryAPITest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class DynamicJson {
    @Test(dataProvider ="BooksData")
    public void AddBook(String isbn,String aisle){
        RestAssured.baseURI ="http://216.10.245.166";
        String response = given().log().all().header("Content-Type","application/json")
                .body(payload.AddBook(isbn,aisle)).
                when().post("/Library/Addbook.php").
        then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js=ReusableMethod.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);
    }

    @DataProvider(name="BooksData")
    public Object[][] getData()
    {
        return new Object[][] {{"abc","123"},{"xyz","456"},{"pqr","789"}};
    }

}
