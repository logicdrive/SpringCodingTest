package com.toy.submission.components.services;

import org.springframework.stereotype.Service;

@Service
public class StringService {
    public String padLeft(String inputString, int padLength, char padChar) {
        if (inputString.length() >= padLength)
            return inputString;

        StringBuilder sb = new StringBuilder();
        while (sb.length() < padLength - inputString.length())
            sb.append(padChar);
        sb.append(inputString);
        return sb.toString();
    }
}
