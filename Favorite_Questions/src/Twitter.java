/**
 * Created by youzhang on 2017/8/5.
 */
import java.util.HashSet;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;
/*
* the following solution's runtime is 147ms, better than 90% of the submissions on leetcode.
* I use two hashtable, one to store the following relation, one to store the tweets.
* the time complexity of follow(), unfollow, postTweet are all O(1);
* when gets new feed for a user, I implement a maxHeap based on PriorityQueue. which sort the tweets of all the followees of current user based on post time.
* we first put the last post of every followee into the heap.
* everytime we poll the tweet on top of the heap. then we put the tweet which is in front of the polled tweets in the tweetList into the heap.
* the time complexity is O(n*log(n)) (here n is the number of users the current user follows)
* */
public class Twitter {
    Hashtable<Integer, HashSet<Integer>> followTable;
    Hashtable<Integer, ArrayList<Post>> tweetsTable;
    int timeCount;
    /** Initialize your data structure here. */
    public Twitter() {
        followTable = new Hashtable<Integer, HashSet<Integer>>();
        tweetsTable = new Hashtable<Integer, ArrayList<Post>>();
        timeCount = 0;
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        Post newPost = new Post(tweetId, timeCount++);
        if(followTable.get(userId)==null){
            HashSet<Integer> followees = new HashSet<Integer>();
            followees.add(userId);
            followTable.put(userId, followees);
        }
        if(tweetsTable.get(userId)==null){
            ArrayList<Post> posts = new ArrayList<Post>();
            posts.add(newPost);
            tweetsTable.put(userId, posts);
        }
        else{
            tweetsTable.get(userId).add(newPost);
        }

    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        HashSet<Integer> followees = followTable.get(userId);
        if(followees==null){
            return resultList;
        }
        Integer[] followeeList = new Integer[followees.size()];
        followees.toArray(followeeList);
        PriorityQueue<Post> tweetsHeap = new PriorityQueue<Post>(followees.size()+1, new Comparator<Post>(){
            public int compare(Post a, Post b){
                return b.timeStamp-a.timeStamp;
            }
        });
        Hashtable<Post,int[]> indexTable = new Hashtable<Post, int[]>();
        for(int followeeId:followeeList){
            ArrayList<Post> tweetsList = tweetsTable.get(followeeId);
            if(tweetsList!=null){
                tweetsHeap.add(tweetsList.get(tweetsList.size()-1));
                int[] temp = new int[2];
                temp[0] = followeeId;
                temp[1] = 0;
                indexTable.put(tweetsList.get(tweetsList.size()-1), temp);
            }
        }
        while(indexTable.size()>0&&resultList.size()<10){
            Post current = tweetsHeap.poll();
            resultList.add(current.tweetId);
            int[] temp = indexTable.get(current);
            indexTable.remove(current);
            ArrayList<Post> currentList = tweetsTable.get(temp[0]);
            if(++temp[1]<currentList.size()){
                Post currentTweet = currentList.get(currentList.size()-1-temp[1]);
                tweetsHeap.add(currentTweet);
                indexTable.put(currentTweet, temp);
            }
        }
        return resultList;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(followTable.get(followerId)==null){
            HashSet<Integer> followees = new HashSet<Integer>();
            followees.add(followerId);
            followTable.put(followerId, followees);
        }
        if(followTable.get(followeeId)==null){
            HashSet<Integer> followees = new HashSet<Integer>();
            followees.add(followeeId);
            followTable.put(followeeId, followees);
        }
        followTable.get(followerId).add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(followTable.get(followerId)==null||followTable.get(followeeId)==null){
            return;
        }
        if(followeeId!=followerId){
            followTable.get(followerId).remove(followeeId);
        }
    }
}

class Post{
    int tweetId;
    int timeStamp;
    public Post(int tweetId, int currentTime){
        this.tweetId = tweetId;
        timeStamp = currentTime;
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
