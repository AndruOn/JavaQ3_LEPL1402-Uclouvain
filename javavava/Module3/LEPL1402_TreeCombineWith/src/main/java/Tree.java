public class Tree {

    public Node root;
    public Tree(Node root){
        this.root = root;
    }

    public Tree combineWith(Tree o){
        if (o == null){return this;}
        if (o.root == null && this.root == null){return null;}
        if (o.root == null){return this;}
        if (this.root == null){return o;}
        Node newNode = combineWithNode(o.root, this.root);
        Tree newTree = new Tree(newNode);
        return newTree;
    }

    public Node combineWithNode(Node n1, Node n2){
        if (n1 == null && n2 == null){return null;}

        Node result = new Node(0);
        if (n1 == null){
            result.val = n2.val;
            result.left = combineWithNode(null, n2.left);
            result.right = combineWithNode(null, n2.right);
        }
        else if (n2 == null){
            result.val = n1.val;
            result.left = combineWithNode(n1.left, null);
            result.right = combineWithNode(n1.right, null);
        }
        else {
            result.val = n1.val + n2.val;
            result.left = combineWithNode(n1.left, n2.left);
            result.right = combineWithNode(n1.right, n2.right);
        }

        return result;
    }
}