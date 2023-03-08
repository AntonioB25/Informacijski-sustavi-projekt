package com.is.projektbackend.projekt.application.util;

import java.util.ArrayList;
import java.util.List;

public class BlackListWords {

    public static List<String> getBlackListedWords(){
        List<String> blacklistedWords = new ArrayList<>();
        blacklistedWords.add("from");
        blacklistedWords.add("select");
        return blacklistedWords;
    }

}
