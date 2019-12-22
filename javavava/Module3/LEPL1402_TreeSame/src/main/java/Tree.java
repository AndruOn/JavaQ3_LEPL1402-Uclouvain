public class Tree {

    public Node root;

    public Tree(Node root){
        this.root = root;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){return true;}
        if (!(o instanceof Tree)){return false;}

        Tree otherTree = (Tree) o;
        Node otherRoot = otherTree.root;
        Node thisRoot = this.root;

        if (otherRoot == null && thisRoot == null){return true;}
        if ((otherRoot != null && thisRoot == null)||(otherRoot == null && thisRoot != null)){return false;}
        if (otherRoot.equals(thisRoot)){
            Tree ThisTreeRight = new Tree(thisRoot.right);
            Tree ThisTreeLeft = new Tree(thisRoot.left);
            return (ThisTreeLeft.equals(new Tree(otherRoot.left)) && ThisTreeRight.equals(new Tree(otherRoot.right)));
        }
        return false;
    }
}