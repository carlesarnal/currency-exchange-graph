import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class CurrencyGraph {

	 public static ArrayList<Integer>[] adjacencyList = null;
	 public int noOfVertices;

	 public CurrencyGraph(int noOfVertices) {

		  System.out.println("testing...");
		  adjacencyList = (ArrayList<Integer>[]) new ArrayList[noOfVertices + 1];
		  this.noOfVertices = noOfVertices;
		  for (int i = 0; i < (noOfVertices + 1); i++)
				adjacencyList[i] = new ArrayList<Integer>();
	 }

	 /**
	  * @param u
	  * @param v To add edges to the adjacency list in graph
	  */
	 public void addEdge(int u, int v) {
		  if (adjacencyList[u] == null)
				adjacencyList[u] = new ArrayList<Integer>();
		  adjacencyList[u].add(v);
	 }

	 /**
	  * @param u
	  * @param v To remove the edge from the graph
	  */
	 public void removeEdge(int u, int v) {
		  int indexToBeRemoved = -1;
		  ArrayList<Integer> edgeList = adjacencyList[u];
		  for (int i = 0; i < adjacencyList[u].size(); i++) {
				int val = edgeList.get(i);
				if (val == v) {
					 indexToBeRemoved = i;
				}
		  }
		  edgeList.remove(indexToBeRemoved);
	 }

	 /**
	  * Method to verify whether u and v are neighbors
	  *
	  * @param u
	  * @param v
	  * @return
	  */
	 public boolean isNeighbor(int u, int v) {
		  if (adjacencyList[u] == null)
				return false;
		  return adjacencyList[u].contains(v);

	 }

	 /**
	  * Method to return the size of the graph
	  *
	  * @return
	  */
	 public int size() {
		  return adjacencyList.length;
	 }

	 /**
	  * @param u
	  * @return To return the outgoing edges for the given source
	  */
	 public ArrayList<Integer> getOutEdges(int u) {
		  return adjacencyList[u];
	 }

	 /**
	  * Method to return the adjacency list
	  *
	  * @return
	  */
	 public ArrayList<Integer>[] getAdjacencyList() {
		  return adjacencyList;
	 }

	 public void printGraph() {
		  ArrayList<Integer> edgeList;
		  for (int i = 1; i <= noOfVertices; i++) {
				edgeList = adjacencyList[i];
				if (edgeList != null) {
					 for (int v : edgeList)
						  System.out.println("u : " + i + " v : " + v);
				}
		  }
	 }



	 public enum Currency {
		  EUR(1), CAD(2), USD(3);

		  Currency(int id) {
				this.id = id;
		  }

		  private int id;

		  public int getId() {
				return id;
		  }

		  public static Currency fromId(int id) {
				switch (id) {
					 case 1:
						  return EUR;
					 case 2:
						  return CAD;
					 case 3:
						  return USD;
					 default:
						  throw new NoSuchElementException();
				}
		  }
	 }


	 private static class Edge {
		  private final double conversionRate;
		  private final Currency origin;
		  private final Currency destination;

		  public Edge(double conversionRate, Currency origin, Currency destination) {
				this.conversionRate = conversionRate;
				this.origin = origin;
				this.destination = destination;
		  }

		  public double getConversionRate() {
				return conversionRate;
		  }

		  public Currency getOrigin() {
				return origin;
		  }

		  public Currency getDestination() {
				return destination;
		  }
	 }

	 /**
	  * @param args Main function to test the graph
	  */
	 public static void main(String[] args) {

		  CurrencyGraph graph = new CurrencyGraph(Currency.values().length);

		  graph.addEdge(Currency.EUR.getId(), Currency.USD.getId());
		  graph.addEdge(Currency.CAD.getId(), Currency.EUR.getId());
		  graph.addEdge(Currency.USD.getId(), Currency.EUR.getId());
		  graph.addEdge(Currency.EUR.getId(), Currency.CAD.getId());

		  graph.printGraph();

		  ArrayList<Integer> shortestPathList =
				  BFSShortestPath.doBFSShortestPath(graph, Currency.EUR.getId(), Currency.CAD.getId());
		  if (shortestPathList != null) {
				Collections.reverse(shortestPathList);

				System.out.print("[ ");
				for (int node : shortestPathList) {
					 System.out.print(Currency.fromId(node) + " " + node + " ");
				}
				System.out.print("]");
		  }

	 }
}
