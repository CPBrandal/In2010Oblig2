import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BalSokTre {
    public static void main(String[] args) throws IOException {
        //BalSokTre balansertSokTre = new BalSokTre();
        BufferedReader leser = new BufferedReader(new FileReader(args[0] + ".txt"));
        String les;
        ArrayList<Integer> liste = new ArrayList<>(); 
        while((les = leser.readLine()) != null){
            liste.add(Integer.parseInt(les));
        }
        leser.close();

        int[] helListe = new int[liste.size()];
        for(int x = 0; x < liste.size(); x++){
            helListe[x] = liste.get(x);
        }

        int midten = helListe.length/2;

        int[] listeLav = new int[midten];
        for(int i = 0; i < midten; i++){
            listeLav[i] = helListe[i];
        }

        int[] listeHoy = new int[helListe.length-midten-1];
        for(int i = midten; i < helListe.length-1; i++){
            listeHoy[i-midten] = helListe[i+1];
        }

/* 
        for(int i : listeHoy){
            System.out.println(i);
        }

        for(int i : listeLav){
            System.out.println(i);
        } */

        int[] ferdig = new int[liste.size()];
        ferdig[0] = helListe[midten];
        ferdig[1] = listeHoy[listeHoy.length/2];
        int teller1 = 2;
        for(int x = 0; x < listeHoy.length; x++){
            if(listeHoy[listeHoy.length-1-x] != listeHoy[listeHoy.length/2]){
                ferdig[x+teller1] = listeHoy[listeHoy.length-1-x];
            } 
            else {
                teller1--;
            }
        }

        ferdig[listeHoy.length+1] = listeLav[listeLav.length/2];

        int teller2=2;
        for(int y = 0; y < listeLav.length; y++){
            if(listeLav[listeLav.length-1-y] != ferdig[listeHoy.length+1]){
                ferdig[y+listeHoy.length+teller2] = listeLav[listeLav.length-1-y];
            } else{
                teller2--;
            }
        }

        try {
            FileWriter myWriter = new FileWriter(args[0] + "result.txt");
            for(int i : ferdig){
                myWriter.write(String.valueOf(i));
                myWriter.write("\n");
            }
            myWriter.close();
        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void algoritme(int[] tListe){
        int lengde = tListe.length;
        if(lengde <= 1){
            return;
        }
        int midten = (lengde/2);

        int LV[] = new int[midten]; 
        int LH[] = new int[lengde - midten]; 

        for(int x = 0; x < midten; x++){
            LV[x] = tListe[x];
        }
        for(int y = midten; y < lengde; y++){
            LH[y-midten] = tListe[y];
        }
    }
}
