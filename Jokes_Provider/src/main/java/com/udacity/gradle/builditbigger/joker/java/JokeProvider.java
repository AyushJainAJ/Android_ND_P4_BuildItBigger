package com.udacity.gradle.builditbigger.joker.java;

import java.util.Random;

public class JokeProvider {

    private static String jokes[] = {"Q. Why was 6 afraid of 7?\nA. Because 7, 8, 9.",
            "Q: What did the mushroom say to the fungus?\nA. You are a fun guy!",
            "Q: Why did the boy bring a ladder to school?\nA. He wanted to go to high school.",
            "Today a man knocked on my door and asked for a small donation towards the local swimming pool. I gave him a glass of water"};

    public static String getAJoke() {

        return jokes[new Random().nextInt(jokes.length)];
    }
}