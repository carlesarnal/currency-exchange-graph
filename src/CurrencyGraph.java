import java.util.*;

public class CurrencyGraph {


	 public static HashMap<Currency, HashSet<Currency>> adjacencyMap = null;

	 public CurrencyGraph(List<Currency> currencies) {
		  adjacencyMap = new HashMap<>();
		  for (Currency currency : currencies)
				adjacencyMap.put(currency, new HashSet<>());
	 }

	 public void addEdge(Edge edge) {
		  adjacencyMap.get(edge.getOrigin()).add(edge.getDestination());
	 }

	 /**
	  * Method to verify whether u and v are neighbors
	  *
	  * @param u
	  * @param v
	  * @return
	  */
	 public boolean isNeighbor(Currency u, Currency v) {
		  return adjacencyMap.get(u).contains(v);

	 }

	 /**
	  * Method to return the size of the graph
	  *
	  * @return
	  */
	 public int size() {
		  return adjacencyMap.size();
	 }

	 /**
	  * @param currency
	  * @return To return the outgoing edges for the given currency
	  */
	 public HashSet<Currency> getOutEdges(Currency currency) {
		  return adjacencyMap.get(currency);
	 }

	 /**
	  * Method to return the adjacency list
	  *
	  * @return
	  */
	 public HashMap getAdjacencyMap() {
		  return adjacencyMap;
	 }

	 public void printGraph() {
		  HashSet<Currency> edgeList;
		  for (Currency currency : adjacencyMap.keySet()) {
				edgeList = adjacencyMap.get(currency);
				if (edgeList != null) {
					 for (Currency v : edgeList)
						  System.out.println(
								  " u : " + currency + " " + " v : " + v);
				}
		  }
	 }



	 public enum Currency {
		  USD(1),
		  CAD(2),
		  EUR(3);

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
						  return USD;
					 case 2:
						  return CAD;
					 case 3:
						  return EUR;
					 default:
						  throw new NoSuchElementException();
				}
		  }
	 }


	 private static class Edge {
		  private final Currency origin;
		  private final Currency destination;

		  public Edge(double conversionRate, Currency origin, Currency destination) {
				this.origin = origin;
				this.destination = destination;
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

		  CurrencyGraph graph = new CurrencyGraph(Arrays.asList(Currency.values()));

		  graph.addEdge(new Edge(3.45, Currency.EUR, Currency.USD));
		  graph.addEdge(new Edge(1.98, Currency.CAD, Currency.EUR));
		  graph.addEdge(new Edge(0.58, Currency.USD, Currency.EUR));
		  graph.addEdge(new Edge(0.28, Currency.EUR, Currency.CAD));

		  graph.printGraph();

		  ArrayList<Currency> shortestPathList =
				  BFSShortestPath.doBFSShortestPath(graph, Currency.USD, Currency.CAD);

		  Collections.reverse(shortestPathList);

		  System.out.print("[ ");
		  for (Currency node : shortestPathList) {
				System.out.print(node + " ");
		  }
		  System.out.print("]");

	 }
}
