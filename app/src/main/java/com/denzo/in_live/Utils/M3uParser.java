package com.denzo.in_live.Utils;

import com.denzo.in_live.Model.M3uChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class M3uParser {

    public static List<M3uChannel> parse(InputStream inputStream) throws IOException {
        List<M3uChannel> channels = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String currentName = "";
        String currentLogo = "";
        String currentTvgId = "";

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.startsWith("#EXTINF:")) {
                currentName = parseTag(line, "tvg-name");
                if (currentName.isEmpty()) {
                    // Fallback to name after comma
                    int commaIndex = line.lastIndexOf(",");
                    if (commaIndex != -1) {
                        currentName = line.substring(commaIndex + 1).trim();
                    }
                }
                currentLogo = parseTag(line, "tvg-logo");
                currentTvgId = parseTag(line, "tvg-id");
            } else if (!line.startsWith("#") && !line.isEmpty()) {
                channels.add(new M3uChannel(currentName, line, currentLogo, currentTvgId));
                currentName = "";
                currentLogo = "";
                currentTvgId = "";
            }
        }
        reader.close();
        return channels;
    }

    private static String parseTag(String line, String tagName) {
        Pattern pattern = Pattern.compile(tagName + "=\"(.*?)\"");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
}
