import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class RestApiTesting {

    @Test
    public void case5() throws IOException {

        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users");
        String jsonString = response.asString();

        //parse to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonString);

        String[] lines = jsonString.split("\\n");
        int emailRow = 5;
        for (int nameRow = 3; nameRow < lines.length; nameRow=nameRow + 23) {

            String username = lines[nameRow].substring(13, lines[nameRow].lastIndexOf("\""));
            String email = lines[emailRow].substring(14, lines[emailRow].lastIndexOf("\""));
            System.out.print(username + " | ");
            System.out.println(email);
            emailRow = emailRow + 23;
        }

        String firstEmail = lines[5].substring(14, lines[5].lastIndexOf("\""));
        assertTrue(firstEmail.contains("@"));

    //tried with json methods, no luck
    /*    ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonData);
        JsonNode idNode = rootNode.path("id");
        System.out.println("id = "+idNode.asInt());*/
    }
}
