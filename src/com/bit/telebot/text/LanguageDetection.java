package com.bit.telebot.text;

import com.google.common.base.Optional;
import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.i18n.LdLocale;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import com.optimaize.langdetect.text.CommonTextObjectFactories;
import com.optimaize.langdetect.text.TextObject;
import com.optimaize.langdetect.text.TextObjectFactory;

import java.util.*;

import java.io.IOException;
import java.util.ArrayList;

public class LanguageDetection {
    private static String language;

    private static HashMap<String, String> lang = new HashMap();

    public static void initList() {
        lang.put("af", "Afrikaans");
        lang.put("an", "Aragonese");
        lang.put("ar", "Arabic");
        lang.put("ast", "Asturian");
        lang.put("be", "Belarusian");
        lang.put("br", "Breton");
        lang.put("ca", "Catalan");
        lang.put("bg", "Bulgarian");
        lang.put("bn", "Bengali");
        lang.put("cs", "Czech");
        lang.put("cy", "Welsh");
        lang.put("da", "Danish");
        lang.put("de", "German");
        lang.put("el", "Greek");
        lang.put("en", "English");
        lang.put("es", "Spanish");
        lang.put("et", "Estonian");
        lang.put("eu", "Basque");
        lang.put("fa", "Persian");
        lang.put("fi", "Finnish");
        lang.put("fr", "French");
        lang.put("ga", "Irish");
        lang.put("gl", "Galician");
        lang.put("gu", "Gujarti");
        lang.put("he", "Hebrew");
        lang.put("hi", "Hindi");
        lang.put("hr", "Croatian");
        lang.put("ht", "Haitian");
        lang.put("hu", "Hungarian");
        lang.put("id", "Indonesian");
        lang.put("is", "Icelandic");
        lang.put("it", "Italian");
        lang.put("ja", "Japanese");
        lang.put("km", "Khmer");
        lang.put("kn", "Kannada");
        lang.put("ko", "Korean");
        lang.put("lt", "Lithuanian");
        lang.put("lv", "Latvian");
        lang.put("mk", "Macedonian");
        lang.put("ml", "Malayalam");
        lang.put("mr", "Marathi");
        lang.put("ms", "Malay");
        lang.put("mt", "Maltese");
        lang.put("ne", "Nepali");
        lang.put("nl", "Dutch");
        lang.put("no", "Norwegian");
        lang.put("oc", "Occitan");
        lang.put("pa", "Punjabi");
        lang.put("pl", "Polish");
        lang.put("pt", "Portuguese");
        lang.put("ro", "Romanian");
        lang.put("ru", "Russian");
        lang.put("sk", "Slovak");
        lang.put("sl", "Slovene");
        lang.put("so", "Somali");
        lang.put("sq", "Albanian");
        lang.put("sr", "Serbian");
        lang.put("sv", "Swedish");
        lang.put("sw", "Swahili");
        lang.put("ta", "Tamil");
        lang.put("te", "Telugu");
        lang.put("th", "Thai");
        lang.put("tl", "Tagalog");
        lang.put("tr", "Turkish");
        lang.put("uk", "Ukrainian");
        lang.put("ur", "Urdu");
        lang.put("vi", "Vietnamese");
        lang.put("wa", "Walloon");
        lang.put("yi", "Yiddish");
        lang.put("zh-cn", "Simplified Chinese");
        lang.put("zh-tw", "Traditional Chinese");


    }

    public static String detect(String str) {
        initList();
        List<LanguageProfile> languageProfiles = null;
        try {
            languageProfiles = new LanguageProfileReader().readAllBuiltIn();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //build language detector:
        LanguageDetector languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
                .withProfiles(languageProfiles)
                .build();

        //create a text object factory
        TextObjectFactory textObjectFactory = CommonTextObjectFactories.forDetectingOnLargeText();

        //query:
        TextObject textObject = textObjectFactory.forText(str);
        Optional<LdLocale> langs = languageDetector.detect(textObject);

        language = langs.toString().split("Optional.of\\(")[1].split("\\)")[0];
        if(language.equals(null)){
            lang.
        }
        //System.out.println(lang.get(language));

        return lang.get(language);

    }



}