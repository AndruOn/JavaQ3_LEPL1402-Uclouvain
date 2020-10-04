public class Tree {

    public Node root;

    public Tree(Node root){
        this.root = root;
    }

    public Tree combineWith(Tree o){
        if (o == null || o.root == null) { return this; }
        if (this.root == null) { return o; }

        int val = o.root.val + this.root.val;
        Tree leftTree = new Tree(this.root.left).combineWith(new Tree(o.root.left));
        Tree rightTree = new Tree(this.root.right).combineWith(new Tree(o.root.right));

        return new Tree(new Node(val, leftTree.root,rightTree.root));
    }
}