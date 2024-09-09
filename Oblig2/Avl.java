import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Avl {
    Node rot;
    int teller = 0;
  public static void main(String[] args) throws FileNotFoundException, IOException {
        Avl avl = new Avl();
        avl.lesFrafil(args[0]);
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
        setHeight(forste);
        return balanse(forste);
    } 

    public Node remove(Node forste, int i){
        if(forste == null){
            return null;
        }
        if (i < forste.element){
            forste.v = remove(forste.v, i);
        }
        else if(i > forste.element){
            forste.h = remove(forste.h, i);
        }
        else if(forste.v == null){
            forste = forste.h;
        }
        else if(forste.h == null){
            forste = forste.v;
        }
        else {
            Node u = finnMinste(forste.h);
            forste.element = u.element;
            forste.h = remove(forste.h, u.element);
        }
        setHeight(forste);
        return balanse(forste);
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

    public int height(Node forste){
        if(forste == null){
            return -1;
        }
        return forste.hoyde;
    }

    public void setHeight(Node forste){
        if(forste != null){
            forste.hoyde = 1 + max(height(forste.v), height(forste.h));
        }
    }

    public int max(int a, int b){
        if(a > b){
            return a;
        } else {
            return b;
        }
    }

    public Node vRotasjon(Node forste){
        Node y = forste.h;
        Node T1 = y.v;

        y.v = forste;
        forste.h = T1;

        setHeight(forste);
        setHeight(y);

        return y;
    }

    public Node hRotasjon(Node forste){
        Node y = forste.v;
        Node T2 = y.h;

        y.h = forste;
        forste.v = T2;

        setHeight(forste);
        setHeight(y);

        return y;
    }

    public int balanseFaktor(Node forste){ // 0 = balansert, + -> venstretungt, - -> høyretungt 
        if(forste == null){
            return 0;
        }
        return (height(forste.v) - height(forste.h));
    }

    public Node balanse(Node forste){
        if(balanseFaktor(forste) < -1){
            if(balanseFaktor(forste.h) > 0){
                forste.h = hRotasjon(forste.h);
            }
            return vRotasjon(forste);
        }
        if(balanseFaktor(forste) > 1){
            if(balanseFaktor(forste.v) < 0){
                forste.v = vRotasjon(forste.v);
            }
            return hRotasjon(forste);
        }
        return forste;
    }

    class Node {
        Node h;
        Node v;
        int element;
        int hoyde;

        public Node(int i){
            element = i;
            hoyde = 0;
        }

    }
}