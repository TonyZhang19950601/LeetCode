/**
 * Created by youzhang on 2017/8/15.
 */
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Hashtable;
/*
* here we use a bucket array to implement a hashset.
* to implement a hash set with bucket array is trivial. the main focus of this question is how to get random.
* here for every insert, we give a number card to the bucket where the new node is inserted.
* for every remove, we get back a number card from the bucket where the node is removed, and give this card to the bucket who has the number card with largest number, and destroy the number card with largest number.
* Thus, for a bucket who has n nodes, it will has n cards and the probability if this bucket to be chosed is n/total number of nodes.
* Then we can pick a node from the chosen bucket randomly.
* */
public class InsertDeleteGetRandomO1 {
    LinkedList[] setArray;
    int count;
    ArrayList<Integer> cardArray;
    Hashtable<Integer, LinkedList<Integer>> indexCards;
    /** Initialize your data structure here. */
    public InsertDeleteGetRandomO1() {
        setArray = new LinkedList[100];
        count = 0;
        cardArray = new ArrayList<Integer>();
        indexCards = new Hashtable<Integer, LinkedList<Integer>>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        int index = Integer.hashCode(Math.abs(val))%100;
        if(setArray[index]==null){
            setArray[index] = new LinkedList<Integer>();
            setArray[index].add(val);
        }
        else{
            if(setArray[index].contains(val)) return false;
            setArray[index].add(val);
        }
        if(count==cardArray.size()){
            cardArray.add(index);
        }
        else{
            cardArray.set(count, index);
        }
        LinkedList<Integer> cards = indexCards.get(index);
        if(cards==null){
            cards = new LinkedList<Integer>();
            cards.add(count);
            indexCards.put(index, cards);
        }
        else{
            cards.add(count);
        }
        count++;
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        int index = Integer.hashCode(Math.abs(val))%100;
        if(setArray[index]!=null&&setArray[index].remove(Integer.valueOf(val))){
            int firstCard = indexCards.get(index).removeFirst();
            int lastIndex = cardArray.get(count-1);
            cardArray.set(firstCard, lastIndex);
            LinkedList<Integer> lastIndexCards = indexCards.get(lastIndex);
            lastIndexCards.remove(Integer.valueOf(count-1));
            lastIndexCards.add(firstCard);
            count--;
            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int randomNumber = (int)(Math.random()*count);
        int index = cardArray.get(randomNumber);
        LinkedList<Integer> buckets = setArray[index];
        int subRandom = (int)(Math.random()*buckets.size());
        return buckets.get(subRandom);
    }
}
