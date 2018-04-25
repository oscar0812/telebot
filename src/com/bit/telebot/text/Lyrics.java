package com.bit.telebot.text;

import com.bit.telebot.StringUtil;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lyrics {

    private static final String BASE_URL = "http://api.chartlyrics.com/apiv1.asmx/SearchLyricDirect";
    private static final Pattern PATTERN_LYRICS = Pattern.compile("<Lyric>(.*)</Lyric>", Pattern.MULTILINE | Pattern.DOTALL);
    private static final Pattern PATTERN_SONG = Pattern.compile("<LyricSong>(.*)</LyricSong>", Pattern.MULTILINE | Pattern.DOTALL);
    private static final Pattern PATTERN_ARTIST = Pattern.compile("<LyricArtist>(.*)</LyricArtist>", Pattern.MULTILINE | Pattern.DOTALL);

    public static LyricsResponse getSongLyrics(String artist, String title) throws IOException {
        String lyricsXml = StringUtil.fromInternet(BASE_URL + "?artist=" + URLEncoder.encode(artist, "UTF-8") + "&song=" + URLEncoder.encode(title, "UTF-8"));
        Matcher matcher_song = PATTERN_SONG.matcher(lyricsXml);
        Matcher matcher_artist = PATTERN_ARTIST.matcher(lyricsXml);
        Matcher matcher_lyrics = PATTERN_LYRICS.matcher(lyricsXml);
        LyricsResponse response = new LyricsResponse();


        if (matcher_lyrics.find()) {
            response.lyrics = matcher_lyrics.group(1);
        }

        if (matcher_artist.find()) {
            response.artist = matcher_artist.group(1);
        }

        if (matcher_song.find()) {
            response.title = matcher_song.group(1);
        }
        return response;
    }

    public static class LyricsResponse {
        public String lyrics;
        public String artist;
        public String title;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Lyrics.getSongLyrics("Eminem", "Superman").lyrics);
    }
}