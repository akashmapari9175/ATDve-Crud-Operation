package com.crud;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testng.annotations.Test;

import com.crud.entity.Student;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testCRUDOperations() {
        // Create
        String createUrl = "http://localhost:" + port + "/api/students";
        Student student = new Student();
        student.setName("John Doe");
        student.setAttributes(Map.of("age", "25", "course", "Math"));

        Student createResponse = restTemplate.postForObject(createUrl, student, Student.class);
        Long studentId = createResponse.getId();
        assertEquals(createResponse.getName(), "John Doe");

        // Read
        String getUrl = "http://localhost:" + port + "/api/students/" + studentId;
        Student getResponse = restTemplate.getForObject(getUrl, Student.class);
        assertEquals(getResponse.getAttributes().get("age"), "25");

        // Update
        student.getAttributes().put("age", "26");
        restTemplate.put(getUrl, student);

        // Verify Update
        Student updatedResponse = restTemplate.getForObject(getUrl, Student.class);
        assertEquals(updatedResponse.getAttributes().get("age"), "26");

        // Delete
        restTemplate.delete(getUrl);

        // Verify Delete
        assertNull(restTemplate.getForObject(getUrl, Student.class));
    }
}
