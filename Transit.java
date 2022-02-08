import java.util.ArrayList;

/**
 * This class contains methods which perform various operations on a layered linked
 * list to simulate transit
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class Transit {
	/**
	 * Makes a layered linked list representing the given arrays of train stations, bus
	 * stops, and walking locations. Each layer begins with a location of 0, even though
	 * the arrays don't contain the value 0.
	 * 
	 * @param trainStations Int array listing all the train stations
	 * @param busStops Int array listing all the bus stops
	 * @param locations Int array listing all the walking locations (always increments by 1)
	 * @return The zero node in the train layer of the final layered linked list
	 */
	public static TNode makeList(int[] trainStations, int[] busStops, int[] locations) {
		// WRITE YOUR CODE HERE	
		TNode walkZero = makeListAssist(locations, null);
		TNode busZero = makeListAssist(busStops, walkZero );
		return makeListAssist(trainStations, busZero);
	}

	private static TNode makeListAssist(int[] a, TNode connectedSectionZero){
		TNode temp = null;
		for(int i = a.length-1; i >-1; i--){
			TNode p = new TNode(a[i], temp,m(a[i],connectedSectionZero));
			temp = p;
		}
		TNode zero = new TNode(0, temp, connectedSectionZero);
		return zero;
	}

	private static TNode m(int n, TNode z){
		if(z == null){
			return null;
		}
		while(z.location != n){
			z = z.next;
		}
		return z;
	}
	/**
	 * Modifies the given layered list to remove the given train station but NOT its associated
	 * bus stop or walking location. Do nothing if the train station doesn't exist
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param station The location of the train station to remove
	 */
	public static void removeTrainStation(TNode trainZero, int station) {
		// WRITE YOUR CODE HERE
		while(trainZero.next != null && trainZero.next.location != station){
			trainZero = trainZero.next;
		}
		if(trainZero.next == null){

		}else
			trainZero.next = trainZero.next.next;
	}

	/**
	 * Modifies the given layered list to add a new bus stop at the specified location. Do nothing
	 * if there is no corresponding walking location.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param busStop The location of the bus stop to add
	 */
	public static void addBusStop(TNode trainZero, int busStop) {
		// WRITE YOUR CODE HERE
		TNode busZero = trainZero.down;
		while(busZero.next != null){
			if(busZero.location < busStop && busZero.next.location > busStop){
				TNode l = busZero.next;
				busZero.next = new TNode(busStop, l, m(busStop, trainZero.down.down));
			}
			busZero = busZero.next;
		}
		
	}
	
	/**
	 * Determines the optimal path to get to a given destination in the walking layer, and 
	 * collects all the nodes which are visited in this path into an arraylist. 
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param destination An int representing the destination
	 * @return
	 */
	public static ArrayList<TNode> bestPath(TNode trainZero, int destination) {
		// WRITE YOUR CODE HERE
		ArrayList<TNode> path = new ArrayList<TNode>();
		while(trainZero.next != null){
			if(trainZero.next.location > destination){
			path.add(trainZero);
			break;
			}else{
				path.add(trainZero);
				trainZero = trainZero.next;
			}
		}
		if(trainZero.location <= destination && trainZero.next == null){
			path.add(trainZero);
		}
		TNode pointer = trainZero.down;
		while(pointer.next != null){
			if(pointer.next.location > destination){
				path.add(pointer);
				break;
			}else{
			path.add(pointer);
			pointer = pointer.next;
			}
		}
		if(pointer.location<= destination && pointer.next == null){
			path.add(pointer);
		}
		pointer = pointer.down;
		while(pointer.location <= destination){
			path.add(pointer);
			if(pointer.next == null)
				break;
			pointer = pointer.next;
		}
		return path;
	}

	/**
	 * Returns a deep copy of the given layered list, which contains exactly the same
	 * locations and connections, but every node is a NEW node.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @return
	 */
	public static TNode duplicate(TNode trainZero) {
		// WRITE YOUR CODE HERE
		TNode bus = trainZero.down;
		TNode walk = bus.down;
		int[] t = dupiAssist(length(trainZero), trainZero.next);
		int[] b = dupiAssist(length(bus), bus.next);
		int[] w = dupiAssist(length(walk), walk.next);
		return makeList(t,b,w);
	}

	private static int[] dupiAssist(int l, TNode o){
		int[] t = new int[l-1];
		int c = 0;
		while(o != null){
			t[c] = o.location;
			o = o.next;
			c++;
		}
		return t;
	}
	private static int length(TNode n){
		int c = 0;
		while(n != null){
			n = n.next;
			c++;
		}
		return c;
	}
	/**
	 * Modifies the given layered list to add a scooter layer in between the bus and
	 * walking layer.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param scooterStops An int array representing where the scooter stops are located
	 */
	public static void addScooter(TNode trainZero, int[] scooterStops) {
		// WRITE YOUR CODE HERE
		TNode bus = trainZero.down;
		TNode scooterZero = makeListAssist(scooterStops, trainZero.down.down);
		while(bus != null){
			bus.down = m(bus.location, scooterZero);
			bus = bus.next;
		}
	}
}