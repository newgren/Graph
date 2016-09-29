package ninja.zilles;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by zilles on 9/27/16.
 *
 * A graph representation using adjacency matrix where the edge weight is stored in the matrix
 *           https://en.wikipedia.org/wiki/Adjacency_matrix
 *
 * Sadly this graph is not as generic as I'd like, since we have to include the latitude/longitude
 * for computing the heuristic distance.
 */
public class Graph {
    private int [][] edgeWeight;
    private JsonCity [] nodes;
    private Map<String, Integer> nameToNodeIndexMap;

    Graph(String jsonString) {
        // parse the JSON
        Gson gson = new Gson();
        JsonGraph jsonGraph = gson.fromJson(jsonString, JsonGraph.class);

        // create a map from the name of the node to its index
        nodes = jsonGraph.getNodes();
        nameToNodeIndexMap = new HashMap<String, Integer>();
        for (int i = 0; i < nodes.length; i++) {
            nameToNodeIndexMap.put(nodes[i].getName(), i);
        }

        // create and populate the adjacency matrix
        int numNodes = nodes.length;
        edgeWeight = new int[numNodes][numNodes];       // default values are zeroes
        for (JsonEdge edge :
                jsonGraph.getEdges()) {
            int node1 = nameToNodeIndexMap.get(edge.getNode1());
            int node2 = nameToNodeIndexMap.get(edge.getNode2());
            edgeWeight[node1][node2] = edge.getWeight();
            edgeWeight[node2][node1] = edge.getWeight();
        }
    }

    /**
     * @param rowNumber  the index of the desired node
     * @return array of values corresponding to distance to another node (0 means not a neighbor)
     */
    private int [] getRow(int rowNumber) {
        return edgeWeight[rowNumber];
    }

    /**
     * Recursively visit all of the nodes reachable from this node (transitive closure)
     * @param rowNumber     node to visit
     * @param visited       set of nodes that have been visited
     */
    private void visitAdjacent(int rowNumber, Set<Integer> visited) {
        if (visited.contains(rowNumber)) {
            return;
        }

        visited.add(rowNumber);
        int [] row = getRow(rowNumber);
        for (int i = 0; i < row.length; i++) {
            if (row[i] != 0) {
                visitAdjacent(i, visited);
            }
        }
    }

    /**
     * @return  Is the graph a single connected component
     */
    public boolean isConnected() {
        // find all of the nodes reachable from node 0
        Set<Integer> visited = new HashSet<Integer>();
        visitAdjacent(0, visited);

        // graph is fully connected if we manage to visit every node
        return visited.size() == edgeWeight.length;
    }



    public class JsonGraph {
        JsonCity [] nodes;
        JsonEdge [] edges;

        public JsonCity[] getNodes() {
            return nodes;
        }

        public JsonEdge[] getEdges() {
            return edges;
        }
    }

    public class JsonCity {
        private String name;
        private double latitude;
        private double longitude;

        public String getName() {
            return name;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public double distance(JsonCity otherCity) {
            double xDistance = Math.abs(this.longitude - otherCity.longitude);
            double yDistance = Math.abs(this.latitude - otherCity.latitude);
            return Math.sqrt(xDistance*xDistance + yDistance*yDistance);
        }
    }

    public class JsonEdge {
        private String node1;
        private String node2;
        private int weight;

        public String getNode1() {
            return node1;
        }

        public String getNode2() {
            return node2;
        }

        public int getWeight() {
            return weight;
        }
    }
}
