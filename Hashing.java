
import java.util.*;
import java.io.*;

public class Hashing implements Set<String> {

    private final static int table_size = 10000000;

    String[] arrSet;

    Hashing(){
        arrSet = new String[table_size];
        for(int i = 0; i < arrSet.length; i++)
            arrSet[i] = null;
    }

    Hashing(int size){
        arrSet = new String[size];
        for(int i = 0; i <arrSet.length; i++)
            arrSet[i] = null;
    }

    private int hashingCode(String s) {
        int code = 0;
        for (int i = 0; i < s.length(); i++) {
            code += s.charAt(i) * Math.pow(139, s.length() - i - 1);
        }
        if(code < 0)
            code*=-1;

        return code;
    }

    @Override
    public int size() {
        int count = 0;
        for(int i = 0; i < arrSet.length; i++){
            if(arrSet[i] != null)
                count++;
        }

        return count;
    }

    @Override
    public boolean isEmpty() {
        for(int i = 0; i < arrSet.length; i++){
            if(arrSet[i] != null)
                return false;
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        String s = o.toString();
        String lowString = s.toLowerCase();
        boolean loop = true;

        int index = hashingCode(lowString) % arrSet.length;

        while(index <= arrSet.length - 2){
            if (arrSet[index] != null && arrSet[index].equals(lowString)) {
                loop = false;
                return true;
            }
            else {
                index = (index + 1) % arrSet.length;
            }
        }
        return false;
    }

    @Override
    public Iterator<String> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(String s) {
        String lowString = s.toLowerCase();
        int index = hashingCode(lowString) % arrSet.length;

        while(true){
            if(arrSet[index] != null && arrSet[index].equals(lowString)){
                return false;
            }

            else if(arrSet[index] != null){
                index = (index + 1) % arrSet.length;
            }

            else{
                arrSet[index] = lowString;
                return true;
            }
        }
    }

    @Override
    public boolean remove(Object o) {
        String s = o.toString();
        String lowString = s.toLowerCase();
        boolean loop = true;

        int index = hashingCode(lowString) % arrSet.length;

        if(contains(lowString) == true) {
            while (index <= arrSet.length - 2) {
                if (arrSet[index] != null && arrSet[index].equals(lowString)) {
                    arrSet[index] = null;
                    return true;
                }
                else{
                    index = (index + 1) % arrSet.length;
                }
            }
        }
        return false;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    public static void main(String[] args) throws IOException{
        Hashing arr = new Hashing();

        try {
            Scanner scanner = new Scanner(new FileReader("words"));
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
                arr.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner input = new Scanner(System.in);

        String word = input.nextLine();

        if(arr.contains(word) == true)
            System.out.println("ok");

        if(arr.contains(word) == false)
            System.out.println("not found");

    }

}


