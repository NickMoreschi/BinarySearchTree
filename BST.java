public class BST<T extends Comparable> implements Tree<T> {

    public class Node implements Tree.Node<T> {

        Node left;
        Node right;
        Node parent;
        T value;

        public Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public T getValue() {
            return this.value;
        }

        public Node getLeft() {
            return this.left;
        }

        public Node getRight() {
            return this.right;
        }

        public Node getParent() {
            return this.parent;
        }

    }

    private Node _root;
    private int _size;

    public BST() {
        this._root = null;
        this._size = 0;
    }

    public Node getRoot() {
        return this._root;
    }

    public boolean add(T value) {
        boolean added = true;

        if ( _root == null ) {
            _root = new Node(value);
            _size++;
        } else {
            added = addNode(_root, value);
            _size++;
            return added;
        }
        return added;

    }

    @SuppressWarnings("unchecked")
    private boolean addNode(Node parent, T value) {
        boolean added = true;
        int cmp = value.compareTo( parent.value );

        if ( cmp < 0 ) {
            if ( parent.left != null ) {
                added = addNode(parent.left, value);
            } else {
                parent.left = new Node(value);
                parent.left.parent = parent;
            }
        } else if ( cmp == 0 ) {
            added = false;
        } else {
            if ( parent.right != null ) {
                added = addNode(parent.right, value);
            } else {
                parent.right = new Node(value);
                parent.right.parent = parent;
            }
        }
        return added;
    }

    public String toString() {
        return "";
    }

    public void clear() {
        _root.right.parent = null;
        _root.left.parent = null;
        _root.right = null;
        _root.left = null;
        _root = null;
        _size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(Object o) {
        T found = null;
        Node cur = _root;
        while ( cur != null && found == null ) {
            int cmp = cur.value.compareTo( o );
            if ( cmp == 0 ) {
                found = cur.value;
            } else if ( cmp > 0 ) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return found;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        Node cur = _root;
        while ( cur != null) {
            int cmp = cur.value.compareTo( o );
            if ( cmp == 0 ) {
                return true;
            } else if ( cmp > 0 ) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return _size == 0;
    }

    private void addToFarRight( Node n, Node subtree ) {

        while (n.right != null) {
            n = n.right;
        }
        n.right = subtree;
        subtree.parent = n;

    }

    private void addToFarLeft( Node n, Node subtree ) {

        while (n.left != null) {
            n = n.left;
        }
        n.left = subtree;
        subtree.parent = n;

    }

    private void removeRoot() {
        Node r = _root;
        if (r.left != null && r.right != null) {
            addToFarRight(r.left, r.right);
            r.left.parent = null;
            _root = r.left;

        } else if (r.left == null && r.right != null) {
            r.right.parent = null;
            _root = r.right;

        } else if (r.left != null && r.right == null) {
            r.left.parent = null;
            _root = r.left;

        } else {
            _root = null;
        }

    }

    private void removeLeft( Node parent, Node n ) {
        if (n.left != null && n.right != null) {
            addToFarRight(n.left, n.right);
            parent.left = n.left;
            n.left.parent = parent;

        } else if (n.left == null && n.right != null) {
            parent.left = n.right;
            n.right.parent = parent;

        } else if (n.left != null && n.right == null) {
            parent.left = n.left;
            n.left.parent = parent;

        } else {
            parent.left = null;
        }

    }

    private void removeRight( Node parent, Node n ) {
        if (n.left != null && n.right != null) {
            addToFarLeft(n.right, n.left);
            parent.right = n.right;
            n.right.parent = parent;

        } else if (n.left == null && n.right != null) {
            parent.right = n.right;
            n.right.parent = parent;

        } else if (n.left != null && n.right == null) {
            parent.right = n.left;
            n.left.parent = parent;
        } else {
            parent.right = null;
        }

    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        Node n = _root;
        while (n != null) {
            if (n.value.compareTo(o) > 0) {
                n = n.left;
            } else if (n.value.compareTo(o) < 0) {
                n = n.right;
            } else {
                break;
            }
        }
        if (n == _root) {
            removeRoot();
            _size--;
            return true;
        } else if (n.equals(n.parent.left)) {
            removeLeft(n.parent, n);
            _size--;
            return true;
        } else if (n.equals(n.parent.right)) {
            removeRight(n.parent, n);
            _size--;
            return true;
        }
        return false;
    }

    public int size() {
        return _size;
    }

    public static void main(String[] args) {
        BST<String> bst = new BST<>();
        bst.add("test");
        bst.add("hello");
        bst.add("nick");
        bst.add("john");
        bst.add("zack");
        System.out.println(bst.size());
    }

}
