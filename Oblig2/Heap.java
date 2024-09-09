import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Heap{
    PriorityQueue<Integer> pQ = new PriorityQueue<Integer>();
    public static void main(String[] args) {
        PriorityQueue<Integer> pQ = new PriorityQueue<Integer>();

    }

    public void lesFrafil(String s){
        try{
            BufferedReader leser = new BufferedReader(new FileReader(s + ".txt"));
            String les;
            while((les = leser.readLine()) != null){
                //TODO
            }
            leser.close();
        } catch (IOException e){
            System.out.println("Noe gikk galt");
        }
    }
}