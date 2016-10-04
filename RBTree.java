
public class RBTree<T extends Comparable<T>>{
    public RBNode root;

    RBTree(){
	this.root = null;
    }

    public boolean search(T value){
	RBNode current = this.root;
	while(current != null){
	    if(value.compareTo(current.value) == 0){
		return true;
	    }
	    else if(value.compareTo(current.value) < 0){
		current = current.left;
	    }
	    else if(value.compareTo(current.value) > 0){
		current = current.right;
	    }
	}
	return false;
    }

    public void printTree(RBNode node){
	if(node != null){
	    System.out.println(node.value +
			       ": " + (node.red ? "RED" : "BLACK") +
			       ": " + (node.parent == null ? "null" : node.parent.value) +
			       ": " + (node.left == null ? "null" : node.left.value) +
			       ": " + (node.right == null ? "null" : node.right.value));

	    printTree(node.left);
	    printTree(node.right);
	}
    }

    public void insert(T value){
	RBNode current = this.root;
	if(current == null){
	    RBNode newRoot = new RBNode(null, null, null, value);
	    newRoot.insertedRoot();	}
	else{
	    boolean inserted = false;
	    while(!inserted){
		if(value.compareTo(current.value) == 0){inserted = true;}
		if(value.compareTo(current.value) < 0){
		    RBNode left = current.left;
		    if(left == null){
			left = new RBNode(current, null, null, value);
			current.left = left;
			left.insertedRoot();
			inserted = true;
		    }
		    else{
			current = current.left;
		    }
		}
		else if(value.compareTo(current.value) > 0){
		    RBNode right = current.right;
		    if(right == null){
			right = new RBNode(current, null, null, value);
			current.right = right;
			right.insertedRoot();
			inserted = true;
		    }
		    else{
			current = current.right;
		    }
		}
	    }
	}
    }
    
    public class RBNode{

	private RBNode parent;
	private RBNode left; 
	private RBNode right;
	private T value;
	private boolean red;

	RBNode(RBNode parent, RBNode left, RBNode right, T value){
	    this.parent = parent;
	    this.left = left;
	    this.right = right;
	    this.value = value;
	    this.red = true;
	}

	RBNode(T value){
	    this.value = value;
	}

	public RBNode grandparent(){
	    RBNode parent = this.parent;
	    RBNode grandparent = parent.parent;
	    if(parent != null && grandparent != null){
		return grandparent;
	    }
	    else{
		return null;
	    }
	}

	public RBNode uncle(){
	    RBNode grandparent = this.grandparent();
	    if(grandparent == null){
		return null; 
	    }
	    else{
		if(this.parent == grandparent.left){
		    return grandparent.right;
		}
		else{
		    return grandparent.left;
		}
	    }
	}

	private void rotateRight(RBNode node){
	    RBNode grandparent = node.grandparent();
	    RBNode savedParent = node.parent;

	    if(grandparent == null){
		RBTree.this.root = this;
	    }
	    else{
		grandparent.right = node;
		node.parent = grandparent;
	    }
	    node.right = savedParent;
	    savedParent.left = null;
	    savedParent.parent = node;
	}
    
	private void rotateLeft(RBNode node){
	    RBNode grandparent = node.grandparent();
	    RBNode savedParent = node.parent;

	    if(grandparent == null){
		RBTree.this.root = this;
	    }
	    else{
		grandparent.left = node;
		node.parent = grandparent;
	    }
	    
	    node.left = savedParent;
	    savedParent.right = null;
	    savedParent.parent = node;
	}

	private void insertedStraight(){
	    System.out.println("CASE 5");
	    RBNode grandparent = this.grandparent();

	    this.parent.red = false;
	    grandparent.red = true;
	    if(this == this.parent.left){
		rotateRight(this.parent);
	    }
	    if(this == this.parent.right){
		rotateLeft(this.parent);
	    }
	}

	private void insertedZigZag(){
	    RBNode grandparent = this.grandparent();
	    RBNode node = this;

	    if((node == node.parent.right) && (node.parent == grandparent.left)){
		rotateLeft(node);
		node = node.left;
		System.out.println("CASE 4");
	    }

	    else if((node == node.parent.left) && (node.parent == grandparent.right)){
		rotateRight(node);
		    node = node.right;
		System.out.println("CASE 4");
	    }
	    node.insertedStraight();
	}

	private void insertedParentUncleRed(){
	    RBNode uncle = this.uncle();
	    RBNode grandparent = this.grandparent();

	    if(uncle != null && uncle.red == true){
		parent.red = false;
		uncle.red = false;
		grandparent.red = true;

		System.out.println("CASE 3");
		
		grandparent.insertedRoot();
	    }
	    else{
		this.insertedZigZag();
	    }
	}

	private void insertedParentBlack(){
	    if(this.parent.red == true){
		this.insertedParentUncleRed();
	    }
	    else{
		System.out.println("CASE 2");
	    }
	}

	private void insertedRoot(){
	    if(this.parent == null){
		this.red = false;
		RBTree.this.root = this;
		System.out.println("CASE 1");
	    }
	    else{
		this.insertedParentBlack();
	    }
	}
	
	/**
	 * @return the parent
	 */
	public RBNode getParent() {
	    return parent;
	}
	
	/**
	 * @return the left
	 */
	public RBNode getLeft() {
	    return left;
	}

	/**
	 * @return the right
	 */
	public RBNode getRight() {
	    return right;
	}

	/**
	 * @return the value
	 */
	public T getValue() {
	    return value;
	}

	/**
	 * @return the red
	 */
	public boolean isRed() {
	    return red;
	}
    }
}
