import java.util.*;

public class BFSShortestPath {

	 public static ArrayList<Integer> doBFSShortestPath(CurrencyGraph graph, int source, int dest) {

		  System.out.println("Testing...");
		  ArrayList<Integer> shortestPathList = new ArrayList<Integer>();
		  HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();

		  if (source == dest)
				return null;
		  Queue<Integer> queue = new LinkedList<Integer>();
		  Stack<Integer> pathStack = new Stack<Integer>();

		  queue.add(source);
		  pathStack.add(source);
		  visited.put(source, true);

		  while (!queue.isEmpty()) {
				int u = queue.poll();
				ArrayList<Integer> adjList = graph.getOutEdges(u);

				for (int v : adjList) {
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
		  int node, currentSrc = dest;
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
