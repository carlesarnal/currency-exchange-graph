import java.util.*;

public class BFSShortestPath {

	 public static ArrayList<CurrencyGraph.Currency> doBFSShortestPath(CurrencyGraph graph,
			 CurrencyGraph.Currency source, CurrencyGraph.Currency dest) {
		  ArrayList<CurrencyGraph.Currency> shortestPathList =
				  new ArrayList<CurrencyGraph.Currency>();
		  HashMap<CurrencyGraph.Currency, Boolean> visited =
				  new HashMap<CurrencyGraph.Currency, Boolean>();

		  if (source == dest)
				return null;
		  Queue<CurrencyGraph.Currency> queue = new LinkedList<CurrencyGraph.Currency>();
		  Stack<CurrencyGraph.Currency> pathStack = new Stack<CurrencyGraph.Currency>();

		  queue.add(source);
		  pathStack.add(source);
		  visited.put(source, true);

		  while (!queue.isEmpty()) {
				CurrencyGraph.Currency u = queue.poll();
				HashSet<CurrencyGraph.Currency> adjList = graph.getOutEdges(u);

				for (CurrencyGraph.Currency v : adjList) {
					 if (!visited.containsKey(v)) {
						  queue.add(v);
						  visited.put(v, true);
						  pathStack.add(v);
						  if (u == dest)
								break;
					 }
				}
		  }

		  //To find the path
		  CurrencyGraph.Currency node, currentSrc = dest;
		  shortestPathList.add(dest);
		  while (!pathStack.isEmpty()) {
				node = pathStack.pop();
				if (graph.isNeighbor(currentSrc, node)) {
					 shortestPathList.add(node);
					 currentSrc = node;
					 if (node == source)
						  break;
				}
		  }

		  return shortestPathList;
	 }

}
