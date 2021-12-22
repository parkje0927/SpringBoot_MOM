package com.example.alarmandlogin.service.firebase;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Service
public class FirebaseService {

    private static final Logger logger = LoggerFactory.getLogger(FirebaseService.class);
    private static final String PROJECT_ID = "";
    private static final String FIREBASE_CONFIG_PATH = "";
    private static final String BASE_URL = "https://fcm.googleapis.com";
    private static final String FCM_SEND_ENDPOINT = "/v1/projects/" + PROJECT_ID + "/messages:send";
    private static final String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
    private static final String[] SCOPES = {MESSAGING_SCOPE};

    private static String getAccessToken() throws IOException {
        ClassPathResource resource = new ClassPathResource(FIREBASE_CONFIG_PATH);

        GoogleCredential googleCredential = GoogleCredential
                .fromStream(resource.getInputStream())
                .createScoped(Arrays.asList(SCOPES));
        googleCredential.refreshToken();
        return googleCredential.getAccessToken();
    }

    @PostConstruct
    public void initialize() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(FIREBASE_CONFIG_PATH).getInputStream())).build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                logger.info("Firebase application has been initialized");
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void sendMessage(JsonObject fcmMessage) throws IOException {
        HttpURLConnection connection = getConnection();
        connection.setDoOutput(true);
        String jsonData = fcmMessage.toString();

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonData.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
            os.flush();
            os.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        int responseCode = connection.getResponseCode();

        System.out.println("firebase test responseCode : " + responseCode);
    }

    private HttpURLConnection getConnection() throws IOException {
        // [START use_access_token]
        URL url = new URL(BASE_URL + FCM_SEND_ENDPOINT);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Authorization", "Bearer " + getAccessToken());
        httpURLConnection.setRequestProperty("Content-Type", "application/json; UTF-8");
        httpURLConnection.setRequestMethod("POST");
        return httpURLConnection;
        // [END use_access_token]
    }
}
