package org.example.singleton;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class Dictionary {
    private static Dictionary INSTANCE;
    private Set<String> words;

    private Dictionary() {
        this.words = new HashSet<>();
        populate();
    }

    public synchronized static Dictionary getInstance() {
        System.out.println("Getting instance... " + INSTANCE);
        if (INSTANCE == null) {
            INSTANCE = new Dictionary();
        }
        return INSTANCE;
    }

    public boolean isValidWord(String wordToCheck) {
        return this.words.contains(wordToCheck.toLowerCase(Locale.ROOT).trim());
    }

    private void populate() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://localstorage.tools/game/word/dictionary/a.txt"))
                    .build();
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            this.words.addAll(Set.of(response.body().split(", ")).stream().map(str -> str.replaceAll("[^a-z]", "")).collect(Collectors.toSet()));
            System.out.println("Got my words! " + words.size());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
