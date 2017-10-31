import java.util.*;

/**
 * Created by youzhang on 2017/10/16.
 */

class UndirectedGraphNode {
      int label;
      List<UndirectedGraphNode> neighbors;
      UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
  }
public class CloneGraph {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node==null) return null;
        Hashtable<Integer, UndirectedGraphNode> table = new Hashtable<Integer, UndirectedGraphNode>();
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        queue.add(node);
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        table.put(node.label, newNode);
        HashSet<Integer> finishSet = new HashSet<Integer>();
        finishSet.add(node.label);
        while(!queue.isEmpty()) {
            UndirectedGraphNode current = queue.poll();
            UndirectedGraphNode currentNew = table.get(current.label);
            for(UndirectedGraphNode neighbor:current.neighbors) {
                UndirectedGraphNode newNeighbor = table.get(neighbor.label);
                if(newNeighbor==null) {
                    newNeighbor = new UndirectedGraphNode(neighbor.label);
                    table.put(neighbor.label, newNeighbor);
                }
                currentNew.neighbors.add(newNeighbor);
                if(!finishSet.contains(neighbor.label)) {
                    queue.add(neighbor);
                    finishSet.add(neighbor.label);
                }
            }
        }
        return newNode;
    }
}
