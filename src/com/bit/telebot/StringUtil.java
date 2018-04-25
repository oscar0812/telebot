package com.bit.telebot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class StringUtil {
    static String charToStr(char[] arr) {

        StringBuilder all = new StringBuilder();
        for (char anArr : arr) {
            all.append(anArr);
        }

        return all.toString();
    }

    public static String removeCall(String lower) {
        if (lower.contains("@"+ Constants.BOT_USERNAME)) {
            int indexBefore = lower.indexOf("@"+Constants.BOT_USERNAME) - 1;

            if (indexBefore >= 0) {
                if (lower.charAt(indexBefore) != ' ') {
                    lower = lower.replaceAll("@"+Constants.BOT_USERNAME, "");
                }
            }

        }
        return lower;
    }

    public static String fromInternet(String url) {
        try {
            System.setProperty("http.agent", "Chrome");

            URL u = new URL(url);
            StringBuilder stringBuilder;
            try (BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(u.openStream()))) {
                stringBuilder = new StringBuilder();
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    stringBuilder.append(inputLine);
                    stringBuilder.append(System.lineSeparator());
                }
            }
            return stringBuilder.toString().trim();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String[] splitString(String str, int maxChars) {
        int rows = (str.length() / maxChars) + 1;
        String[] arr = new String[str.length()];
        if (rows == 1) {
            return (new String[]{str});
        } else {
            int x = 0;
            while (!str.trim().isEmpty()) {
                if (str.trim().length() <= maxChars) {
                    arr[x++] = str;
                    str = "";
                } else {
                    String current = str.substring(0, maxChars);
                    if (str.charAt(maxChars) == ' ') {
                        str = str.substring(maxChars);
                    } else {
                        int space_ind = current.lastIndexOf(' ');
                        if (space_ind > 0) {
                            current = str.substring(0, space_ind);
                            str = str.substring(space_ind);
                        }
                    }
                    arr[x++] = current;
                }
            }

            String[] temp = new String[x];
            for (int y = 0; y < temp.length; y++) {
                temp[y] = arr[y].trim();
            }

            arr = temp;
        }
        return arr;
    }
}
