public class Node {

    public int val;
    public Node left;
    public Node right;

    public Node(int val){
        this.val = val;
    }
    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf(){
        return this.left == null && this.right == null;
    }

    @Override
    public boolean equals(Object o){
        return o.equals(this.val);
    }
}