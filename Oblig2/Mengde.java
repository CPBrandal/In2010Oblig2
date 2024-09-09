import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Mengde {
    Node rot;
    int teller = 0;
  public static void main(String[] args) throws FileNotFoundException, IOException {
        Mengde m = new Mengde();
        m.lesFrafil(args[0]);
    }

    public void lesFrafil(String filnavn) throws FileNotFoundException, IOException{
        BufferedReader leser = new BufferedReader(new FileReader(filnavn + ".txt"));
        String les;
        while((les = leser.readLine()) != null){
            String[] liste = les.split(" ");
            if(liste[0].equals("insert")){
                if(this.contains(rot, Integer.parseInt(liste[1])) == null){
                    rot = insert(rot, Integer.parseInt(liste[1]));
                    teller++;
                }
            }
            else if(liste[0].equals("contains")){
                if(this.contains(rot, Integer.parseInt(liste[1])) == null){
                    System.out.println("false");
                } else {
                    System.out.println("true");
                }
            }
            else if(liste[0].equals("remove")){
                if(this.contains(rot, Integer.parseInt(liste[1])) != null){
                    rot = this.remove(rot, Integer.parseInt(liste[1]));
                    teller--;
                }
            }
            else if(liste[0].equals("size")){
                this.size();
            }
        } 
        leser.close();
    } 

    public Node insert(Node forste, int i){
        if(forste == null){
            forste = new Node(i);
        }
        else if(i < forste.element){
            forste.v = insert(forste.v, i);
        }
        else if(i > forste.element){
            forste.h = insert(forste.h, i);
        }
        return forste;
    } 

    public Node remove(Node forste, int i){
        if(forste == null){
            return null;
        }
        if (i < forste.element){
            forste.v = remove(forste.v, i);
            return forste;
        }
        if(i > forste.element){
            forste.h = remove(forste.h, i);
            return forste;
        }
        if(forste.v == null){
            return forste.h;
        }
        if(forste.h == null){
            return forste.v;
        }
        Node u = finnMinste(forste.h);
        forste.element = u.element;
        forste.h = remove(forste.h, u.element);
        return forste;
    }

     public Node finnMinste(Node n){
        if(n.v == null){
            return n;
        }
        if(n.v != null){
            return finnMinste(n.v);
        } else {
            return null;
        }
    }

    public Node contains(Node forste, int i){
        if(forste == null){
            return null;
        }
        if(forste.element == i){
            return forste;
        }
        if(i < forste.element){
            return contains(forste.v, i);
        } 
        if(i > forste.element){
            return contains(forste.h, i);
        }
        return forste;
    }

    public void size(){
        System.out.println(teller);
    }

    class Node {
        Node h;
        Node v;
        int element;

        public Node(int i){
            element = i;
        }
    }
}