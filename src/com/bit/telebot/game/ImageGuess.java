package com.bit.telebot.game;

import java.util.ArrayList;
import java.util.Random;

class ImageGuess{
    private String url;
    private String answer;
    public ImageGuess(String url, String answer){
        this.url = url;
        this.answer = answer;
    }

    public String getUrl() {
        return url;
    }

    public String getAnswer() {
        return answer;
    }
    private static ArrayList<ImageGuess> l = new ArrayList<>();
    private static void initList(){
        l.add(new ImageGuess("https://images.pexels.com/photos/247376/pexels-photo-247376.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", "zebra"));
        l.add(new ImageGuess("https://images.pexels.com/photos/236636/pexels-photo-236636.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", "horse"));
        l.add(new ImageGuess("https://images.pexels.com/photos/726478/pexels-photo-726478.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", "shark"));
        l.add(new ImageGuess("https://images.pexels.com/photos/39627/flamingo-valentine-heart-valentine-s-day-39627.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", "flamingo"));
        l.add(new ImageGuess("https://images.pexels.com/photos/145939/pexels-photo-145939.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", "tiger"));
        l.add(new ImageGuess("https://images.pexels.com/photos/326900/pexels-photo-326900.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", "bird"));
        l.add(new ImageGuess("https://images.pexels.com/photos/146083/pexels-photo-146083.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", "koala"));
        l.add(new ImageGuess("https://images.pexels.com/photos/8700/wall-animal-dog-pet.jpg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", "dog"));
        l.add(new ImageGuess("https://images.pexels.com/photos/45911/peacock-plumage-bird-peafowl-45911.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", "peacock"));
        l.add(new ImageGuess("https://images.pexels.com/photos/677974/pexels-photo-677974.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", "rhino"));
        l.add(new ImageGuess("https://images.pexels.com/photos/46540/hippo-hippopotamus-animal-look-46540.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", "hippo"));
        l.add(new ImageGuess("https://images.pexels.com/photos/69397/pig-sow-piglet-nursing-69397.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", "pig"));

        l.add(new ImageGuess("https://images.pexels.com/photos/39803/pexels-photo-39803.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", "apple"));
        l.add(new ImageGuess("https://images.pexels.com/photos/51312/kiwi-fruit-vitamins-healthy-eating-51312.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", "kiwi"));
        l.add(new ImageGuess("https://images.pexels.com/photos/59945/strawberry-fruit-delicious-red-59945.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", "strawberry"));
        l.add(new ImageGuess("https://images.pexels.com/photos/557659/pexels-photo-557659.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", "avocado"));
        l.add(new ImageGuess("https://images.pexels.com/photos/38283/bananas-fruit-carbohydrates-sweet-38283.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", "banana"));
        l.add(new ImageGuess("https://images.pexels.com/photos/416528/pexels-photo-416528.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", "water"));
        l.add(new ImageGuess("https://images.pexels.com/photos/39080/stop-shield-traffic-sign-road-sign-39080.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", "stop"));

        l.add(new ImageGuess("https://images.pexels.com/photos/955193/pexels-photo-955193.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", "book"));
        l.add(new ImageGuess("https://images.pexels.com/photos/978819/pexels-photo-978819.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", "cat"));
    }

    static ImageGuess random(){
        if(l.isEmpty())
            initList();
        return l.get(new Random().nextInt(l.size()));
    }
}
