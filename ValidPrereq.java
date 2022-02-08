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
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */
public class ValidPrereq {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
            return;
        }
	// WRITE YOUR CODE HERE

    String answer;
    StdOut.setFile(args[2]);
    HashMap<String, ArrayList<String>> courses = MapMakerLikeNami.worldMap(args[0]);
    StdIn.setFile(args[1]);
    String adv = StdIn.readLine();
    String preReqAdv = StdIn.readLine();

    ArrayList<String> will = new ArrayList<String>();
    will.add(preReqAdv);

   MapMakerLikeNami.incursio(courses,preReqAdv, will);
   answer = "YES";
    for(String e : will){
        if(adv.equals(e)){
            answer = "NO";
            break;
        }
    }

    StdOut.print(answer);

    }
}

// javac -d bin src/prereqchecker/*.java
//  java -cp bin prereqchecker.ValidPrereq adjlist.in validprereq.in validprereq.out