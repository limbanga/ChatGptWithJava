package org.example;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Main {
    public static void main(String[] args) throws Exception {
        String apiKey = "YOUR_API_KEY";
        String prompt = "Once upon a time";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost request = new HttpPost("https://api.openai.com/v1/completions");
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Authorization", "Bearer " + apiKey);

            String requestData = "{ \"model\": \"gpt-3.5-turbo\",\"prompt\": \"" + prompt + "\", \"max_tokens\": 50}";
            request.setEntity(new StringEntity(requestData));

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String responseString = EntityUtils.toString(entity);
                System.out.println(responseString);
            }
        } finally {
            httpClient.close();
        }
    }
}
