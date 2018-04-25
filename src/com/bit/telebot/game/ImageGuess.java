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
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Fthumb%2F5%2F5c%2FChicago_Bears_logo.svg%2F2000px-Chicago_Bears_logo.svg.png&imgrefurl=https%3A%2F%2Fcommons.wikimedia.org%2Fwiki%2FFile%3AChicago_Bears_logo.svg&docid=hYll5to0COIzuM&tbnid=JkSoKqmsogiEyM%3A&vet=10ahUKEwiTv5mZvNPaAhXJneAKHUZQDlEQMwi7ASgBMAE..i&w=2000&h=1337&bih=575&biw=637&q=bears%20logo&ved=0ahUKEwiTv5mZvNPaAhXJneAKHUZQDlEQMwi7ASgBMAE&iact=mrc&uact=8", "bears"));
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Fthumb%2F8%2F81%2FCincinnati_Bengals_logo.svg%2F2000px-Cincinnati_Bengals_logo.svg.png&imgrefurl=https%3A%2F%2Fcommons.wikimedia.org%2Fwiki%2FFile%3ACincinnati_Bengals_logo.svg&docid=-JQvfbu7a0UGFM&tbnid=AuK3MGJb1rsAMM%3A&vet=10ahUKEwjFkOiiwNPaAhVGn-AKHcPvDXYQMwi_ASgAMAA..i&w=2000&h=1404&bih=575&biw=637&q=benfalslogo&ved=0ahUKEwjFkOiiwNPaAhVGn-AKHcPvDXYQMwi_ASgAMAA&iact=mrc&uact=8", "bengals"));
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fusatftw.files.wordpress.com%2F2015%2F02%2Fhelmet_top_center1.png%3Fw%3D1000&imgrefurl=https%3A%2F%2Fftw.usatoday.com%2F2015%2F02%2Fcleveland-browns-new-logo&docid=1TSRYc7Xsh85nM&tbnid=WvfM3bCJk7i0GM%3A&vet=10ahUKEwj9uZ7twNPaAhXnmOAKHfhfD0sQMwibASgAMAA..i&w=660&h=509&bih=575&biw=637&q=browns%20logo&ved=0ahUKEwj9uZ7twNPaAhXnmOAKHfhfD0sQMwibASgAMAA&iact=mrc&uact=8", "browns"));
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fi.pinimg.com%2Foriginals%2F9b%2Fd7%2F4f%2F9bd74fc0e9547d6da161c2c9c6e485e3.jpg&imgrefurl=https%3A%2F%2Fwww.pinterest.com%2Fpin%2F212584044888909413%2F&docid=rxlOfcoX_r7UzM&tbnid=w4-n73_PbQ93oM%3A&vet=10ahUKEwjW-8mm1dPaAhVNm-AKHZ9hDXAQMwjlASgAMAA..i&w=605&h=570&bih=575&biw=637&q=cowboys%20logo&ved=0ahUKEwjW-8mm1dPaAhVNm-AKHZ9hDXAQMwjlASgAMAA&iact=mrc&uact=8", "cowboys"));
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fs3.amazonaws.com%2Ffreebiesupply%2Flarge%2F2x%2Fdenver-broncos-logo-transparent.png&imgrefurl=https%3A%2F%2Ffreebiesupply.com%2Flogos%2Fdenver-broncos-logo%2F&docid=Oa5F0ch_e2zObM&tbnid=lhncnp8c0E8E1M%3A&vet=10ahUKEwiKiLb91dPaAhXSTd8KHavCB1AQMwifAigAMAA..i&w=2400&h=1600&bih=575&biw=637&q=brroncos%20logo&ved=0ahUKEwiKiLb91dPaAhXSTd8KHavCB1AQMwifAigAMAA&iact=mrc&uact=8", "broncos"));
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fs3.amazonaws.com%2Ffreebiesupply%2Flarge%2F2x%2Fdetroit-lions-logo-transparent.png&imgrefurl=https%3A%2F%2Ffreebiesupply.com%2Flogos%2Fdetroit-lions-logo%2F&docid=KWumKeKelaL5fM&tbnid=EkHd9sy6zqXMqM%3A&vet=10ahUKEwjcoOnC19PaAhUDMt8KHSzgDRIQMwjgASgBMAE..i&w=2400&h=2000&bih=575&biw=637&q=lions%20logo&ved=0ahUKEwjcoOnC19PaAhUDMt8KHSzgDRIQMwjgASgBMAE&iact=mrc&uact=8", "lions"));
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Fthumb%2F5%2F50%2FGreen_Bay_Packers_logo.svg%2F2000px-Green_Bay_Packers_logo.svg.png&imgrefurl=https%3A%2F%2Fcommons.wikimedia.org%2Fwiki%2FFile%3AGreen_Bay_Packers_logo.svg&docid=J5Rlw8JVWu7b7M&tbnid=rdLUmuuv75h8fM%3A&vet=10ahUKEwj28deSiNTaAhXvTN8KHZCpDUEQMwjkASgAMAA..i&w=2000&h=1318&bih=592&biw=1242&q=packers%20logo&ved=0ahUKEwj28deSiNTaAhXvTN8KHZCpDUEQMwjkASgAMAA&iact=mrc&uact=8","packers"));
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=http%3A%2F%2Fwww.stickpng.com%2Fassets%2Fimages%2F580b585b2edbce24c47b2b29.png&imgrefurl=http%3A%2F%2Fwww.stickpng.com%2Fimg%2Fsports%2Fnfl-football%2Fhouston-texans%2Fhouston-texans-logo&docid=KV5IA20jaEllNM&tbnid=_SYfCPn3kp2IRM%3A&vet=10ahUKEwiziuOBidTaAhWwY98KHZV2BwwQMwj0ASgAMAA..i&w=657&h=600&bih=592&biw=1242&q=texans%20logo&ved=0ahUKEwiziuOBidTaAhWwY98KHZV2BwwQMwj0ASgAMAA&iact=mrc&uact=8", "texans"));
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Fthumb%2F0%2F00%2FIndianapolis_Colts_logo.svg%2F2000px-Indianapolis_Colts_logo.svg.png&imgrefurl=https%3A%2F%2Fcommons.wikimedia.org%2Fwiki%2FFile%3AIndianapolis_Colts_logo.svg&docid=RoW_jhNhpuhNgM&tbnid=ev9xkGGoSkohjM%3A&vet=10ahUKEwj215znidTaAhUvUt8KHdyIAN0QMwiKAigAMAA..i&w=2000&h=2104&bih=592&biw=1242&q=colts%20logo&ved=0ahUKEwj215znidTaAhUvUt8KHdyIAN0QMwiKAigAMAA&iact=mrc&uact=8", "colts"));
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fs3.amazonaws.com%2Ffreebiesupply%2Flarge%2F2x%2Fjacksonville-jaguars-logo-transparent.png&imgrefurl=https%3A%2F%2Ffreebiesupply.com%2Flogos%2Fjacksonville-jaguars-logo%2F&docid=5isOfVFYl_gv9M&tbnid=hFP4ie-ZguEQoM%3A&vet=10ahUKEwiJup2eitTaAhUMd98KHeHkChQQMwiOAigAMAA..i&w=2400&h=1900&bih=592&biw=1242&q=logo%20jaguars&ved=0ahUKEwiJup2eitTaAhUMd98KHeHkChQQMwiOAigAMAA&iact=mrc&uact=8", "jaguars"));
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fs3.amazonaws.com%2Ffreebiesupply%2Flarge%2F2x%2Fkansas-city-chiefs-logo-transparent.png&imgrefurl=https%3A%2F%2Ffreebiesupply.com%2Flogos%2Fkansas-city-chiefs-logo%2F&docid=5Hk-grG04POKvM&tbnid=ztIHSjTgwBM8aM%3A&vet=10ahUKEwjM4aDSitTaAhVCPN8KHWF1CMIQMwjuASgAMAA..i&w=2400&h=1600&bih=592&biw=1242&q=chiefs%20logo&ved=0ahUKEwjM4aDSitTaAhVCPN8KHWF1CMIQMwjuASgAMAA&iact=mrc&uact=8", "chiefs"));
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fs3.amazonaws.com%2Ffreebiesupply%2Flarge%2F2x%2Flos-angeles-chargers-logo-transparent.png&imgrefurl=https%3A%2F%2Ffreebiesupply.com%2Flogos%2Flos-angeles-chargers-logo%2F&docid=koaXtdT0Ak_SiM&tbnid=dmLf2LOi8rLpqM%3A&vet=10ahUKEwiR6rGDi9TaAhUjiOAKHTzZBesQMwiCAigBMAE..i&w=2400&h=1400&bih=592&biw=1242&q=chargers%20logo&ved=0ahUKEwiR6rGDi9TaAhUjiOAKHTzZBesQMwiCAigBMAE&iact=mrc&uact=8", "chargers"));
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fs3.amazonaws.com%2Ffreebiesupply%2Flarge%2F2x%2Flos-angeles-rams-logo-transparent.png&imgrefurl=https%3A%2F%2Ffreebiesupply.com%2Flogos%2Flos-angeles-rams-logo%2F&docid=JsGs6XsHamuaRM&tbnid=1L-fvZ7rg_tXEM%3A&vet=10ahUKEwjU_MjOi9TaAhUxh-AKHSfCDZEQMwimASgAMAA..i&w=2400&h=1800&bih=592&biw=1242&q=rams%20logo&ved=0ahUKEwjU_MjOi9TaAhUxh-AKHSfCDZEQMwimASgAMAA&iact=mrc&uact=8", "rams"));
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fi.pinimg.com%2Foriginals%2Fda%2F4b%2Fb1%2Fda4bb1eaa26bcd05da4eede92e15b449.jpg&imgrefurl=https%3A%2F%2Fwww.pinterest.com%2Fpin%2F573294227537180755%2F&docid=B7-MV6oH_ULdrM&tbnid=yphcSCcoPRSN7M%3A&vet=10ahUKEwiSrf_ii9TaAhUyc98KHcV_ChEQMwjTASgAMAA..i&w=512&h=512&bih=592&biw=1242&q=dolphins%20logo&ved=0ahUKEwiSrf_ii9TaAhUyc98KHcV_ChEQMwjTASgAMAA&iact=mrc&uact=8", "dolphins"));
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fs3.amazonaws.com%2Ffreebiesupply%2Flarge%2F2x%2Fminnesota-vikings-logo-transparent.png&imgrefurl=https%3A%2F%2Ffreebiesupply.com%2Flogos%2Fminnesota-vikings-logo%2F&docid=x59TzrRJL1UOTM&tbnid=ld88_Z1PMJcF-M%3A&vet=10ahUKEwiDkqKljNTaAhUBMd8KHUb4DBMQMwivASgBMAE..i&w=2400&h=3000&bih=592&biw=1242&q=vikings%20logo&ved=0ahUKEwiDkqKljNTaAhUBMd8KHUb4DBMQMwivASgBMAE&iact=mrc&uact=8", "vikings"));
        l.add(new ImageGuess("https://www.google.com/imgres?imgurl=https%3A%2F%2Fimages-na.ssl-images-amazon.com%2Fimages%2FI%2F519GwdDFgGL._SL1000_.jpg&imgrefurl=https%3A%2F%2Fwww.amazon.com%2FNew-England-Patriots-Logo-Magnet%2Fdp%2FB000Q74DPA&docid=rqRAIog5PKRbGM&tbnid=4ILTA7iRhPgO6M%3A&vet=10ahUKEwiEgIf7jNTaAhXSUt8KHeDoD7UQMwjRASgBMAE..i&w=1000&h=1000&bih=592&biw=1242&q=patriots%20logo&ved=0ahUKEwiEgIf7jNTaAhXSUt8KHeDoD7UQMwjRASgBMAE&iact=mrc&uact=8", "patriots"));


    }

    static ImageGuess random(){
        if(l.isEmpty())
            initList();
        //return l.get(l.size()-1);
        return l.get(new Random().nextInt(l.size()));
    }
}

