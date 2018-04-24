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

import java.util.List;

public class LanguageDetection {

    public static void detect(String str) {
        try {
            //load all languages:
            List<LanguageProfile> languageProfiles = new LanguageProfileReader().readAllBuiltIn();

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
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        // should say es, bc this is spanish, and i know spanish so i can agree
        detect("Este software no funciona tan bien cuando el texto de entrada para analizar es corto o sucio. Por ejemplo tweets.\n" +
                "\n" +
                "Cuando un texto está escrito en varios idiomas, el algoritmo predeterminado de este software no es apropiado. Puede intentar dividir el texto (por frase o párrafo) y detectar las partes individuales. Ejecutar el adivinador del lenguaje en todo el texto simplemente le dirá el idioma que es más dominante, en el mejor de los casos.\n" +
                "\n" +
                "Este software no puede manejarlo bien cuando el texto de entrada no está en ninguno de los idiomas esperados (y compatibles). Por ejemplo, si solo carga los perfiles de idioma de inglés y alemán, pero el texto está escrito en francés, el programa puede elegir el más probable o decir que no sabe. (Una mejora sería detectar claramente que es poco probable que sea uno de los idiomas admitidos).");
    }
}
