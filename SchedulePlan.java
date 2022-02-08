package prereqchecker;
import java.util.*;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * SchedulePlanInputFile name is passed through the command line as args[1]
 * Read from SchedulePlanInputFile with the format:
 * 1. One line containing a course ID
 * 2. c (int): number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * SchedulePlanOutputFile name is passed through the command line as args[2]
 * Output to SchedulePlanOutputFile with the format:
 * 1. One line containing an int c, the number of semesters required to take the course
 * 2. c lines, each with space separated course ID's
 */
public class SchedulePlan {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.SchedulePlan <adjacency list INput file> <schedule plan INput file> <schedule plan OUTput file>");
            return;
        }

	// WRITE YOUR CODE HERE
    HashMap<String, ArrayList<String>> courses = MapMakerLikeNami.worldMap(args[0]);

    StdIn.setFile(args[1]);
    StdOut.setFile(args[2]);
    String target = StdIn.readLine();
    int k = StdIn.readInt();
    StdIn.readLine();
    ArrayList<String> completed = new ArrayList<String>();

    
    for(int i = 0; i< k; i++){
        String e = StdIn.readLine();
        if(!completed.contains(e)){
            completed.add(e);
            MapMakerLikeNami.incursio(courses, e, completed);
        }
    }


    ArrayList<String> required = new ArrayList<String>();
    MapMakerLikeNami.incursio(courses, target, required);

    ArrayList<ArrayList<String>> schedule = new ArrayList<ArrayList<String>>();

   int p = completed.size();
   int c = 0;
    while(required.size() > p){
        ArrayList<String> eli = MapMakerLikeNami.eligibleAssist(courses.entrySet(), completed);
        ArrayList<String> a = new ArrayList<String>();

        for(String e : eli){
            if(required.contains(e)){
                required.remove(e);
                a.add(e);
                completed.add(e);
            }
        }
        schedule.add(a);
    if(c > required.size()){
        break;
    }
    c++;
    }

    StdOut.println(schedule.size());
    for(ArrayList<String> sem : schedule){
        for(String e: sem){
            StdOut.print(e +" ");
        }
        StdOut.print("\n");
        }

    }
}

// javac -d bin src/prereqchecker/*.java
// java -cp bin prereqchecker.SchedulePlan adjlist.in scheduleplan.in scheduleplan.out
