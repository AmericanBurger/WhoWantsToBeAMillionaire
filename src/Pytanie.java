import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pytanie {
    public int id;
    public int poziom;
    public String pytanie;
    public ArrayList<String> poprawne, bledne;
    public String temp;

    public Pytanie(int ID, int Level, String question, ArrayList<String> dobre, ArrayList<String> zle){
        id = ID;
        poziom = Level;
        pytanie = question;
        poprawne = dobre;
        bledne = zle;
    }
    public void wyswietlOdpowiedzi(Pytanie p){
        Random gen = new Random();
        int randomInt = gen.nextInt(p.poprawne.size());
        List<String> odpowiedzi = new ArrayList<>();
        odpowiedzi.add(p.poprawne.get(randomInt));

        List<Integer> byo = new ArrayList<>();
        boolean flaga = true;

        for(int i = 0; i < 3 && byo.size() != p.bledne.size() && odpowiedzi.size() <= 4; ++i) {
            while (flaga) {
                randomInt = gen.nextInt(p.bledne.size());
                if (!byo.contains(randomInt)) flaga = false;
            }
            byo.add(randomInt);
            odpowiedzi.add(p.bledne.get(randomInt));
            flaga = true;
        }
        byo.clear();
        char odp = 'A';
        for(int i = 0; i < 4 && byo.size() != p.bledne.size(); ++i, ++odp) {
            while (flaga) {
                randomInt = gen.nextInt(odpowiedzi.size());
                if (!byo.contains(randomInt)) flaga = false;
            }
            byo.add(randomInt);
            System.out.println(odp + ": " + odpowiedzi.get(randomInt));
            if(p.poprawne.contains(odpowiedzi.get(randomInt))) temp = Character.toString(odp);
            flaga = true;
        }
    }
}
