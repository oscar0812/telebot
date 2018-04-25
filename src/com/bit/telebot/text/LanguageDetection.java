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
import java.util.ArrayList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class LanguageDetection {
    private String key,value;

    public LanguageDetection(String key, String value){
    this.key = key;
    this.value = value;
}

    public String getKey() { return key; }

    public String getValue() { return value; }

    private static ArrayList <LanguageDetection> lang = new ArrayList<>();

    public static void initList(){
        lang.add(new LanguageDetection("af", "Afrikaans"));
        lang.add(new LanguageDetection("an", "Aragonese"));
        lang.add(new LanguageDetection("ar", "Arabic"));
        lang.add(new LanguageDetection("ast", "Asturian"));
        lang.add(new LanguageDetection("be", "Belarusian"));
        lang.add(new LanguageDetection("br", "Breton"));
        lang.add(new LanguageDetection("ca", "Catalan"));
        lang.add(new LanguageDetection("bg", "Bulgarian"));
        lang.add(new LanguageDetection("bn","Bengali"));
        lang.add(new LanguageDetection("cs", "Czech"));
        lang.add(new LanguageDetection("cy", "Welsh"));
        lang.add(new LanguageDetection("da", "Danish"));
        lang.add(new LanguageDetection("de", "German"));
        lang.add(new LanguageDetection("el", "Greek"));
        lang.add(new LanguageDetection("en", "English"));
        lang.add(new LanguageDetection("es", "Spanish"));
        lang.add(new LanguageDetection("et", "Estonian"));
        lang.add(new LanguageDetection("eu", "Basque"));
        lang.add(new LanguageDetection("fa", "Persian"));
        lang.add(new LanguageDetection("fi", "Finnish"));
        lang.add(new LanguageDetection("fr", "French"));
        lang.add(new LanguageDetection("ga", "Irish"));
        lang.add(new LanguageDetection("gl", "Galician"));
        lang.add(new LanguageDetection("gu", "Gujarti"));
        lang.add(new LanguageDetection("he", "Hebrew"));
        lang.add(new LanguageDetection("hi", "Hindi"));
        lang.add(new LanguageDetection("hr", "Croatian"));
        lang.add(new LanguageDetection("ht", "Haitian"));
        lang.add(new LanguageDetection("hu", "Hungarian"));
        lang.add(new LanguageDetection("id", "Indonesian"));
        lang.add(new LanguageDetection("is", "Icelandic"));
        lang.add(new LanguageDetection("it", "Italian"));
        lang.add(new LanguageDetection("ja", "Japanese"));
        lang.add(new LanguageDetection("km", "Khmer"));
        lang.add(new LanguageDetection("kn", "Kannada"));
        lang.add(new LanguageDetection("ko", "Korean"));
        lang.add(new LanguageDetection("lt", "Lithuanian"));
        lang.add(new LanguageDetection("lv", "Latvian"));
        lang.add(new LanguageDetection("mk", "Macedonian"));
        lang.add(new LanguageDetection("ml", "Malayalam"));
        lang.add(new LanguageDetection("mr", "Marathi"));
        lang.add(new LanguageDetection("ms", "Malay"));
        lang.add(new LanguageDetection("mt", "Maltese"));
        lang.add(new LanguageDetection("ne", "Nepali"));
        lang.add(new LanguageDetection("nl", "Dutch"));
        lang.add(new LanguageDetection("no", "Norwegian"));
        lang.add(new LanguageDetection("oc", "Occitan"));
        lang.add(new LanguageDetection("pa", "Punjabi"));
        lang.add(new LanguageDetection("pl", "Polish"));
        lang.add(new LanguageDetection("pt", "Portuguese"));
        lang.add(new LanguageDetection("ro", "Romanian"));
        lang.add(new LanguageDetection("ru", "Russian"));
        lang.add(new LanguageDetection("sk", "Slovak"));
        lang.add(new LanguageDetection("sl", "Slovene"));
        lang.add(new LanguageDetection("so", "Somali"));
        lang.add(new LanguageDetection("sq", "Albanian"));
        lang.add(new LanguageDetection("sr", "Serbian"));
        lang.add(new LanguageDetection("sv","Swedish"));
        lang.add(new LanguageDetection("sw", "Swahili"));
        lang.add(new LanguageDetection("ta", "Tamil"));
        lang.add(new LanguageDetection("te", "Telugu"));
        lang.add(new LanguageDetection("th", "Thai"));
        lang.add(new LanguageDetection("tl", "Tagalog"));
        lang.add(new LanguageDetection("tr", "Turkish"));
        lang.add(new LanguageDetection("uk", "Ukrainian"));
        lang.add(new LanguageDetection("ur", "Urdu"));
        lang.add(new LanguageDetection("vi", "Vietnamese"));
        lang.add(new LanguageDetection("wa", "Walloon"));
        lang.add(new LanguageDetection("yi", "Yiddish"));
        lang.add(new LanguageDetection("zh-cn", "Simplified Chinese"));
        lang.add(new LanguageDetection("zh-tw", "Traditional Chinese"));





    }

    public static void detect(String str) {
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
        Optional<LdLocale> lang = languageDetector.detect(textObject);

        System.out.println(lang);
    }

}
