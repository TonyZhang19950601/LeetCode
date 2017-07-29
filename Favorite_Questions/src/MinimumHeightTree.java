/**
 * Created by youzhang on 2017/7/29.
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
public class MinimumHeightTree {
    /*
    this is my own solution. first we do DFS start from any node in the graph. for each node, we store the max length of the neibor vertices
    of the current node's subtree. Thus, After the DFS, every node have the max length of all it's degree except the one from it's parent node in the DFS.
    then we do a DFS(BFS is also OK)again on this graph. for every node, we compare the current max length and the length from it's parents. if the length from the parents
    is larger than the current max length, we update the max length for that node.
    No we get the max height for every node. we only need to find out the nodes with minimum maxHeight and return them.
    this is O(n) time complexity, However, it's time complexity is not optimized. Although the space complexity is O(n),
    it need several stacks and arrays.
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        Hashtable<Integer, ArrayList<Integer>> edgeTable = new Hashtable<Integer, ArrayList<Integer>>();
        boolean[] visitFlag = new boolean[n];
        int[][] maxLength = new int[n][2];
        if(n==1){
            resultList.add(0);
            return resultList;
        }
        for(int i=0;i<edges.length;i++){
            ArrayList<Integer> list0 = edgeTable.get(edges[i][0]);
            if(list0!=null){
                list0.add(edges[i][1]);
            }else{
                list0 = new ArrayList<Integer>();
                list0.add(edges[i][1]);
                edgeTable.put(edges[i][0], list0);
            }
            ArrayList<Integer> list1 = edgeTable.get(edges[i][1]);
            if(list1!=null){
                list1.add(edges[i][0]);
            }else{
                list1 = new ArrayList<Integer>();
                list1.add(edges[i][0]);
                edgeTable.put(edges[i][1], list1);
            }
            visitFlag[i] = false;
        }
        int start = 0;
        //firs time DFS
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(start);
        visitFlag[start] = true;
        while(!stack.isEmpty()){
            int node = stack.pop();
            ArrayList<Integer> neibors = edgeTable.get(node);
            boolean allVisited = true;
            for(int neibor:neibors){
                if(!visitFlag[neibor]){
                    stack.push(node);
                    stack.push(neibor);
                    visitFlag[neibor] = true;
                    allVisited = false;
                    break;
                }
            }
            if(allVisited){
                int maxDepth = 0;
                int nextMaxDepth = 0;
                for(int neibor:neibors){
                    if(maxLength[neibor][0]>maxDepth){
                        nextMaxDepth = maxDepth;
                        maxDepth = maxLength[neibor][0];
                    }else if(maxLength[neibor][0]>nextMaxDepth){
                        nextMaxDepth = maxLength[neibor][0];
                    }
                }
                maxLength[node][0] = maxDepth+1;
                maxLength[node][1] = nextMaxDepth+1;
            }
        }
        //second time BFS
        stack.push(start);
        visitFlag[start] = false;
        Stack<Integer> lastNodeStack = new Stack<Integer>();
        lastNodeStack.push(-1);
        while(!stack.isEmpty()){
            int node = stack.pop();
            int lastNode = lastNodeStack.pop();
            if(lastNode>=0){
                int lastMax = maxLength[lastNode][0];
                int lastNextMax = maxLength[lastNode][1];
                if(maxLength[node][0]<lastMax-1){
                    maxLength[node][1] = maxLength[node][0];
                    maxLength[node][0] = lastMax+1;
                }else if(maxLength[node][0] == lastMax-1){
                    if(maxLength[node][0]<=lastNextMax){
                        maxLength[node][1] = maxLength[node][0];
                        maxLength[node][0] = lastNextMax+1;
                    }else if(maxLength[node][1] <= lastNextMax){
                        maxLength[node][1] = lastNextMax+1;
                    }
                }
            }
            ArrayList<Integer> neibors = edgeTable.get(node);
            for(int neibor:neibors){
                if(visitFlag[neibor]){
                    stack.push(neibor);
                    lastNodeStack.push(node);
                    visitFlag[neibor] = false;
                }
            }
        }


        int min = Integer.MAX_VALUE;
        for(int i = 0;i<n;i++){
            min = Math.min(min, maxLength[i][0]);
        }
        for(int i=0;i<n;i++){
            if(maxLength[i][0]==min){
                resultList.add(i);
            }
        }
        return resultList;
    }

    /*
    * this is a solution on leetcode, which use BFS.
    * the code is more clear and simple.
    * We start from every end, by end we mean vertex of degree 1 (aka leaves). We let the pointers move the same speed. When two pointers meet, we keep only one of them, until the last two pointers meet or one step away we then find the roots.
      It is easy to see that the last two pointers are from the two ends of the longest path in the graph.
      The actual implementation is similar to the BFS topological sort. Remove the leaves, update the degrees of inner vertexes. Then remove the new leaves. Doing so level by level until there are 2 or 1 nodes left. What's left is our answer!
      The time complexity and space complexity are both O(n).
      Note that for a tree we always have V = n, E = n-1.
    * */

    public List<Integer> findMinHeightTreesOnLeetCode(int n, int[][] edges) {
        if (n == 1) return Collections.singletonList(0);

        List<Set<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) adj.add(new HashSet<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; ++i)
            if (adj.get(i).size() == 1) leaves.add(i);

        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for (int i : leaves) {
                int j = adj.get(i).iterator().next();
                adj.get(j).remove(i);
                if (adj.get(j).size() == 1) newLeaves.add(j);
            }
            leaves = newLeaves;
        }
        return leaves;
    }

}
