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
        //Pokemon
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=http%3A%2F%2Fwww.pokemonget.eu%2Fshop%2F979%2Fpikachu-birthday-event-pokemon.jpg&imgrefurl=http%3A%2F%2Fwww.pokemonget.eu%2Fshop%2Fen%2Fbirthday-events%2F111-pikachu-birthday-event-pokemon.html&docid=xu9Rh4WdpaG2VM&tbnid=R3gdwER6OYsmSM%3A&vet=10ahUKEwiuotPgvdHaAhWvVt8KHfLMABcQMwjLASgCMAI..i&w=1000&h=1000&bih=620&biw=612&q=Pikachu&ved=0ahUKEwiuotPgvdHaAhWvVt8KHfLMABcQMwjLASgCMAI&iact=mrc&uact=8","pikachu"));
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fcdn.bulbagarden.net%2Fupload%2Fthumb%2F0%2F00%2F359Absol.png%2F250px-359Absol.png&imgrefurl=https%3A%2F%2Fbulbapedia.bulbagarden.net%2Fwiki%2FAbsol_(Pok%25C3%25A9mon)&docid=3x4zECFPqJ5k3M&tbnid=q9qQMYxf0oJkNM%3A&vet=10ahUKEwjhzZPTvtHaAhXRV98KHSFWAC8QMwjMASgAMAA..i&w=250&h=250&bih=620&biw=612&q=Absol&ved=0ahUKEwjhzZPTvtHaAhXRV98KHSFWAC8QMwjMASgAMAA&iact=mrc&uact=8","absol"));
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fcdn.bulbagarden.net%2Fupload%2Fthumb%2F9%2F9b%2F778Mimikyu.png%2F1200px-778Mimikyu.png&imgrefurl=https%3A%2F%2Fbulbapedia.bulbagarden.net%2Fwiki%2FMimikyu_(Pok%25C3%25A9mon)&docid=qZAvA9xEjZfWQM&tbnid=f4g2qCG_O6tSNM%3A&vet=10ahUKEwivoYi-v9HaAhUCr1kKHSZsDZIQMwg5KAAwAA..i&w=1200&h=1200&bih=636&biw=1242&q=Mimikyu&ved=0ahUKEwivoYi-v9HaAhUCr1kKHSZsDZIQMwg5KAAwAA&iact=mrc&uact=8","mimikyu"));

        l.add(new ImageGuess("https://logorealm.com/nike-logo/", "nike"));

        //sport teams
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fs3.amazonaws.com%2Ffreebiesupply%2Flarge%2F2x%2Farizona-cardinals-logo-transparent.png&imgrefurl=https%3A%2F%2Ffreebiesupply.com%2Flogos%2Farizona-cardinals-logo%2F&docid=FcIQuol-ALKNtM&tbnid=nv0Mk64m21fmHM%3A&vet=10ahUKEwjGkLDHuNPaAhUKPN8KHc3sDzwQMwjRASgBMAE..i&w=2400&h=1981&bih=575&biw=637&q=cardinals%20logo&ved=0ahUKEwjGkLDHuNPaAhUKPN8KHc3sDzwQMwjRASgBMAE&iact=mrc&uact=8", "cardinals"));
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fs3.amazonaws.com%2Ffreebiesupply%2Flarge%2F2x%2Fatlanta-falcons-logo-transparent.png&imgrefurl=https%3A%2F%2Ffreebiesupply.com%2Flogos%2Fatlanta-falcons-logo%2F&docid=TZIeWf-i6zdGXM&tbnid=SLRhLbTF5-d7uM%3A&vet=10ahUKEwj1x_jpuNPaAhUPVd8KHYO6C8UQMwisASgAMAA..i&w=2400&h=2289&bih=575&biw=637&q=falcons%20logo&ved=0ahUKEwj1x_jpuNPaAhUPVd8KHYO6C8UQMwisASgAMAA&iact=mrc&uact=8","falcons"));
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fs3.amazonaws.com%2Ffreebiesupply%2Flarge%2F2x%2Fbaltimore-ravens-logo-transparent.png&imgrefurl=https%3A%2F%2Ffreebiesupply.com%2Flogos%2Fbaltimore-ravens-logo%2F&docid=SHxjqa0GUhXVxM&tbnid=G5FT505s9DrzIM%3A&vet=10ahUKEwjc5p20udPaAhXvmuAKHXNRCNcQMwiBAigAMAA..i&w=2400&h=1480&bih=575&biw=637&q=ravens%20logo&ved=0ahUKEwjc5p20udPaAhXvmuAKHXNRCNcQMwiBAigAMAA&iact=mrc&uact=8", "ravens"));
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fs3.amazonaws.com%2Ffreebiesupply%2Flarge%2F2x%2Fbuffalo-bills-logo-transparent.png&imgrefurl=https%3A%2F%2Ffreebiesupply.com%2Flogos%2Fbuffalo-bills-logo%2F&docid=yDIKHl5RRffYiM&tbnid=Ut3lghJVtL4QtM%3A&vet=10ahUKEwi4o7mJutPaAhXKm-AKHUueA9kQMwjFASgAMAA..i&w=2400&h=1700&bih=575&biw=637&q=bills%20logo&ved=0ahUKEwi4o7mJutPaAhXKm-AKHUueA9kQMwjFASgAMAA&iact=mrc&uact=8", "bills"));
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fi.ytimg.com%2Fvi%2FMK16FBfuQxk%2Fmaxresdefault.jpg&imgrefurl=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3DMK16FBfuQxk&docid=N7-jngDjuF8tnM&tbnid=YfEonzTWzTuxgM%3A&vet=10ahUKEwjr2amrutPaAhUxhOAKHQKhC3YQMwiqASgAMAA..i&w=1280&h=720&bih=575&biw=637&q=panthers%20logo&ved=0ahUKEwjr2amrutPaAhUxhOAKHQKhC3YQMwiqASgAMAA&iact=mrc&uact=8","panthers"));




    }

    static ImageGuess random(){
        if(l.isEmpty())
            initList();
        return l.get(l.size()-1);
        //return l.get(new Random().nextInt(l.size()));
    }
}

