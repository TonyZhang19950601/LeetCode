/**
 * Created by youzhang on 2017/8/5.
 */
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
public class FlattenNestedListInteger implements Iterator<Integer>{
    /*
    * This is a tree dfs question. every list can be considered as a node in a tree. every integer is a leaf node in the tree.
    * Thus, I use stack to do the DFS on this tree.
    * */
    Stack<List<NestedInteger>> stack;
    Stack<Integer> indexStack;
    public FlattenNestedListInteger(List<NestedInteger> nestedList) {
        stack = new Stack<List<NestedInteger>>();
        stack.push(nestedList);
        indexStack = new Stack<Integer>();
        indexStack.push(-1);
        while(!stack.isEmpty()){
            List<NestedInteger> currentList = stack.pop();
            int currentIndex = indexStack.pop()+1;
            if(currentIndex<currentList.size()){
                if(currentList.get(currentIndex).isInteger()){
                    stack.push(currentList);
                    indexStack.push(currentIndex);
                    break;
                }
                else{
                    stack.push(currentList);
                    indexStack.push(currentIndex);
                    List<NestedInteger> tempList = currentList.get(currentIndex).getList();
                    if(tempList.size()>0){
                        stack.push(tempList);
                        indexStack.push(-1);
                    }
                }
            }
        }
    }

    @Override
    public Integer next() {
        int result = stack.peek().get(indexStack.peek()).getInteger();
        while(!stack.isEmpty()){
            List<NestedInteger> currentList = stack.pop();
            int currentIndex = indexStack.pop()+1;
            if(currentIndex<currentList.size()){
                if(currentList.get(currentIndex).isInteger()){
                    stack.push(currentList);
                    indexStack.push(currentIndex);
                    break;
                }
                else{
                    stack.push(currentList);
                    indexStack.push(currentIndex);
                    List<NestedInteger> tempList = currentList.get(currentIndex).getList();
                    if(tempList.size()>0){
                        stack.push(tempList);
                        indexStack.push(-1);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

}

interface NestedInteger {

              // @return true if this NestedInteger holds a single integer, rather than a nested list.
              public boolean isInteger();

              // @return the single integer that this NestedInteger holds, if it holds a single integer
              // Return null if this NestedInteger holds a nested list
              public Integer getInteger();

              // @return the nested list that this NestedInteger holds, if it holds a nested list
              // Return null if this NestedInteger holds a single integer
              public List<NestedInteger> getList();
 }
