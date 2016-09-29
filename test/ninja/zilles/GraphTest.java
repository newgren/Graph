package ninja.zilles;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zilles on 9/27/16.
 */
public class GraphTest {
    public static final String TEST_INPUT_1 = "{\n" +
            "   \"nodes\": [\n" +
            "      {\"name\": \"Bloomington\", \"latitude\": 40.4842, \"longitude\": -88.9937},\n" +
            "      {\"name\": \"Champaign\", \"latitude\": 40.1164, \"longitude\": -88.2434},\n" +
            "      {\"name\": \"Chicago\", \"latitude\": 41.8781, \"longitude\": -87.6298},\n" +
            "      {\"name\": \"Decatur\", \"latitude\": 39.8403, \"longitude\": -88.9548},\n" +
            "      {\"name\": \"Danville\", \"latitude\": 40.1245, \"longitude\": -87.6300},\n" +
            "      {\"name\": \"Joliet\", \"latitude\": 41.5250, \"longitude\": -88.0817},\n" +
            "      {\"name\": \"Kankakee\", \"latitude\": 41.1200, \"longitude\": -87.8612},\n" +
            "      {\"name\": \"Lincoln\", \"latitude\": 40.1484, \"longitude\": -89.3648},\n" +
            "      {\"name\": \"Mattoon\", \"latitude\": 39.4831, \"longitude\": -88.3728},\n" +
            "      {\"name\": \"Peoria\", \"latitude\": 40.6936, \"longitude\": -89.5890},\n" +
            "      {\"name\": \"Springfield\", \"latitude\": 39.7817, \"longitude\": -89.6501}\n" +
            "   ],\n" +
            "   \"edges\": [\n" +
            "      {\"node1\": \"Bloomington\", \"node2\": \"Champaign\", \"weight\": 54}, \n" +
            "      {\"node1\": \"Decatur\", \"node2\": \"Champaign\", \"weight\": 54}, \n" +
            "      {\"node1\": \"Danville\", \"node2\": \"Champaign\", \"weight\": 35}, \n" +
            "      {\"node1\": \"Lincoln\", \"node2\": \"Springfield\", \"weight\": 37}, \n" +
            "      {\"node1\": \"Decatur\", \"node2\": \"Springfield\", \"weight\": 44}, \n" +
            "      {\"node1\": \"Decatur\", \"node2\": \"Mattoon\", \"weight\": 59}, \n" +
            "      {\"node1\": \"Mattoon\", \"node2\": \"Champaign\", \"weight\": 53}, \n" +
            "      {\"node1\": \"Lincoln\", \"node2\": \"Peoria\", \"weight\": 46}, \n" +
            "      {\"node1\": \"Lincoln\", \"node2\": \"Bloomington\", \"weight\": 36}, \n" +
            "      {\"node1\": \"Kankakee\", \"node2\": \"Champaign\", \"weight\": 75}, \n" +
            "      {\"node1\": \"Kankakee\", \"node2\": \"Chicago\", \"weight\": 62}, \n" +
            "      {\"node1\": \"Joliet\", \"node2\": \"Chicago\", \"weight\": 51}, \n" +
            "      {\"node1\": \"Joliet\", \"node2\": \"Kankakee\", \"weight\": 50}, \n" +
            "      {\"node1\": \"Joliet\", \"node2\": \"Bloomington\", \"weight\": 98}\n" +
            "   ]\n" +
            "}      ";
    private Graph graph;

    @Before
    public void setUp() throws Exception {
         graph = new Graph(TEST_INPUT_1);
    }

    @Test
    public void testConsistency() throws Exception {
        assertTrue(graph.isConnected());
    }
}