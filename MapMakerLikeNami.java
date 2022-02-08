package prereqchecker;
import java.util.*;

public class MapMakerLikeNami {
    public static HashMap<String, ArrayList<String>> worldMap(String a){
        StdIn.setFile(a);
        int length = StdIn.readInt();

    StdIn.readLine();
    
    HashMap<String, ArrayList<String>> courses = new HashMap<String, ArrayList<String>>(length);
   
    for(int i = 0; i < length; i++){
        String code = StdIn.readLine();
        courses.put(code, new ArrayList<String>());
    }

    int p = StdIn.readInt();
    StdIn.readLine();
   for(int i = 0;i< p; i++){
    String f = StdIn.readLine();
    courses.get(f.substring(0,f.indexOf(" "))).add(f.substring(f.indexOf(" ")+1));
   }
        return courses;
    }

    public static ArrayList<String> incursio(HashMap<String, ArrayList<String>> p, String key, ArrayList<String> ongoing){
        for(String e: p.get(key)){
            if(ongoing.contains(e)){
    
            }else{
                ongoing.add(e);
            }
               incursio(p, e, ongoing);
           }
           return ongoing;
    }

    public static ArrayList<String> eligibleAssist( Set<HashMap.Entry<String, ArrayList<String>>> a, ArrayList<String> comp){
        ArrayList<String> eli = new ArrayList<String>();
        for(HashMap.Entry<String, ArrayList<String>> e : a){
             if(e.getValue().size() == 0){
                if(!comp.contains(e.getKey()))
                    eli.add(e.getKey());
            }else{
            for(int i = 0; i < e.getValue().size(); i++){
                if(!comp.contains(e.getValue().get(i)))
                    break;
                if(i == e.getValue().size()-1 && !comp.contains(e.getKey()))
                    eli.add(e.getKey());
            }
        }
        }
    
        return eli;
    }
}
