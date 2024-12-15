package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class AddBookIntegrationTest {

    private final String BASE_URL = "http://localhost:5000/api/media";

    @Test
    public void testAddNewBookSuccessfully() throws Exception {
        // Create a valid payload
        String jsonPayload = "{"
            + "\"title\": \"Test Book\","
            + "\"author\": \"Test Author\","
            + "\"isbn\": \"1234567890\","
            + "\"category_id\": \"4bbfd4e7-ad5c-11ef-8422-a0afbd3a6924\","
            + "\"publication_date\": \"2024-12-25\","
            + "\"publisher\": \"Test Publisher\","
            + "\"item_description\": \"Test description\""
    + "}";


        // Send POST request
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(BASE_URL + "/add"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
            .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Assert that the response status is 201 (Created)
        assertEquals(201, response.statusCode());
    }

    @Test
    public void testAddBookWithMissingFields() throws Exception {
        // Create an invalid payload with missing fields
        String jsonPayload = "{"
            + "\"author\": \"Test Author\","
            + "\"isbn\": \"1234567890\","
            + "\"category_id\": \"4bbfd4e7-ad5c-11ef-8422-a0afbd3a6924\","
            + "\"publication_date\": \"2024-12-25\","
            + "\"publisher\": \"Test Publisher\","
            + "\"item_description\": \"Test description\""
    + "}";


        // Send POST request
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(BASE_URL + "/add"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
            .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Assert that the response status is 400 (Bad Request)
        assertEquals(400, response.statusCode());
    }
}

