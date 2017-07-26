/**
 * Created by youzhang on 2017/7/27.
 * this is a typical question solved with segment tree. By using segment tree, we can provide O(log(n)) complexity on both update and search.
 */
public class RangeSumQueryMutable {
    SegmentTreeNode root;
    public RangeSumQueryMutable(int[] nums) {
        if(nums.length!=0){
            root = buildSegmentTree(0, nums.length-1, nums);
        }
    }

    public SegmentTreeNode buildSegmentTree(int left, int right, int[] nums){
        int middle = (left+right)/2;
        if(left<right){
            SegmentTreeNode leftChild = buildSegmentTree(left, middle, nums);
            SegmentTreeNode rightChild = buildSegmentTree(middle+1, right, nums);
            SegmentTreeNode root = new SegmentTreeNode(left, right, leftChild.sum+rightChild.sum);
            root.leftChild = leftChild;
            root.rightChild = rightChild;
            return root;
        }
        else{
            SegmentTreeNode root = new SegmentTreeNode(left, right, nums[left]);
            return root;
        }
    }

    public void update(int i, int val) {
        findAndUpdate(i, val, root);
    }

    public void findAndUpdate(int i, int val, SegmentTreeNode root){
        if(root.left == root.right){
            root.sum = val;
        }
        else{
            int middle = (root.left+root.right)/2;
            if(i<=middle){
                findAndUpdate(i, val, root.leftChild);
            }
            else{
                findAndUpdate(i, val, root.rightChild);
            }
            root.sum = root.leftChild.sum+root.rightChild.sum;
        }
    }

    public int sumRange(int i, int j) {
        if(i==0){
            return findAndSum(j, root);
        }
        else{
            return findAndSum(j, root) - findAndSum(i-1, root);
        }
    }

    public int findAndSum(int i, SegmentTreeNode root){
        if(root.right == i){
            return root.sum;
        }
        else{
            int middle = (root.left+root.right)/2;
            if(i<=middle){
                return findAndSum(i, root.leftChild);
            }
            else{
                return root.leftChild.sum+findAndSum(i, root.rightChild);
            }
        }
    }
}

class SegmentTreeNode {
    int left;
    int right;
    int sum;
    SegmentTreeNode leftChild;
    SegmentTreeNode rightChild;
    public SegmentTreeNode(int left, int right, int sum){
        this.left = left;
        this.right = right;
        this.sum = sum;
        leftChild = null;
        rightChild = null;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
