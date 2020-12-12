public class Node {

    public int val;
    public Node left;
    public Node right;

    public Node(int val){
        this.val = val;
    }

    public boolean isLeaf(){
        return this.left == null && this.right == null;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof  Node){
            Node node = (Node) o;
            return node.val == this.val;
        }
        return false;
    }
}