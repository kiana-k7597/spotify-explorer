package com.example.spotifyexplorer.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SpotifyAPIClient {

    @Value("${spotify.bearer-token}")
    private String bearerToken;

    private final RestTemplate restTemplate;

    public SpotifyAPIClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getNewAlbumRecommendations() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(bearerToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = this.restTemplate.exchange(
                "https://api.spotify.com/v1/browse/new-releases",
                HttpMethod.GET,
                entity,
                String.class);

        return response.getBody();
    }
}
