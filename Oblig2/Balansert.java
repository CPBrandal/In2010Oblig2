import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class Balansert {
    static Node rot;
    static int[] helListe;
    static int[] outputListe;
    static PriorityQueue<Integer> pQ = new PriorityQueue<Integer>();
    

    public static void main(String[] args) throws IOException{
        Balansert b = new Balansert();
        BufferedReader leser = new BufferedReader(new FileReader(args[0]+ ".txt"));
        String les;
        ArrayList<Integer> liste = new ArrayList<>(); 
        while((les = leser.readLine()) != null){
            liste.add(Integer.parseInt(les));
        }
        leser.close();

        helListe = new int[liste.size()];
        for(int x = 0; x < liste.size(); x++){
            helListe[x] = liste.get(x);
            pQ.add(liste.get(x));
        }
        outputListe = new int[helListe.length];

        rot = b.balanserArray(helListe, helListe.length-helListe.length, helListe.length-1);

        b.itererNoder(rot);
    }

    public Node balanserArray(int[] liste, int start, int slutt){
        if(start > slutt){
            return null;
        }

        int mid = (start + slutt)/2;
        Node n = new Node(liste[mid]);

        n.h = balanserArray(liste, mid+1, slutt);

        n.v = balanserArray(liste, start, mid-1);

        return n;
    }   


    public void itererNoder(Node n){
        //int teller = 0;
        if(n == null){
            return;
        }
        System.out.println(n.element);
/*         outputListe[teller] = n.element;
        teller++; */
        itererNoder(n.h);
        itererNoder(n.v);
    }

    public class Node{
        Node v;
        Node h;
        int element;

        public Node(int i){
            element = i;
        }
    } 
}
