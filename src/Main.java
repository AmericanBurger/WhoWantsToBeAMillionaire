import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.*;

public class Main {
    private static boolean wyswietl(List<Pytanie> p, boolean f, List<Integer> b){
        Random gen = new Random();
        int randomInt = 0;
        for (int i = 0; i <= p.size() && b.size() != p.size(); ++i) {
            while (f) {
                randomInt = gen.nextInt(p.size());
                if (!b.contains(randomInt)) f = false;
            }
            b.add(randomInt);
            System.out.println(p.get(randomInt).pytanie);
            p.get(randomInt).wyswietlOdpowiedzi(p.get(randomInt));

            System.out.println("Podaj odpowiedz:");
            Scanner input = new Scanner(System.in);
            String odp = input.nextLine();

            if(!odp.toUpperCase().equals(p.get(randomInt).temp)) return false;
            System.out.println("Brawo! Odpowiedz prawidlowa.");
            f = true;
        }
        return true;
    }
    public static void main(String[] args) throws NullPointerException{
        List<Pytanie> pytania = new ArrayList<>();
        List<Integer> byo = new ArrayList<>();
        boolean flaga = true;

        try
        {
            JsonReader skaner = new JsonReader(new FileReader("test.json"));
            pytania = Arrays.asList(new Gson().fromJson(skaner, Pytanie[].class));
        }
        catch (Exception ex)
        {
            System.out.println("Press X to Jason!");
        }

        List<Pytanie> easy = new ArrayList<>();
        List<Pytanie> medium = new ArrayList<>();
        List<Pytanie> hard = new ArrayList<>();

        for(Pytanie el:pytania){
            if(el.poziom == 1) easy.add(el);
            else if(el.poziom == 2) medium.add(el);
            else hard.add(el);
        }
        List<List<Pytanie>> lista = new ArrayList<>();
        lista.add(easy);
        lista.add(medium);
        lista.add(hard);

        for(List<Pytanie> el:lista) {
            if(!wyswietl(el, flaga, byo)) {
                System.out.println("Podana przez Ciebie odpowiedz jest bledna. Bardzo sie starales, ale z gry wyleciales ;P");
                break;
            }
            flaga = true;
            byo.clear();
        }
    }
}
