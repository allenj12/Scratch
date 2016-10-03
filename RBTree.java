
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

    public void insert(T value){
	RBNode current = this.root;
	if(current == null){
	    RBNode newRoot = new RBNode(null, null, null, value);
	    newRoot.insertCase1();	}
	else{
	    boolean inserted = false;
	    while(!inserted){
		if(value.compareTo(current.value) == 0){inserted = true;}
		if(value.compareTo(current.value) < 0){
		    RBNode left = current.left;
		    if(left == null){
			left = new RBNode(current, null, null, value);
			current.setLeft(left);
			left.insertCase1();
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
			current.setRight(right);
			right.insertCase1();
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
	    RBNode grandparent = parent.grandparent();
	    RBNode savedParent = node.parent;

	    if(grandparent == null){
		RBTree.this.root = this;
	    }
	    else{
		grandparent.right = node;
	    }
	    node.right = savedParent;
	    savedParent.left = null;
	}
    
	private void rotateLeft(RBNode node){
	    RBNode grandparent = node.grandparent();
	    RBNode savedParent = node.parent;

	    if(grandparent == null){
		RBTree.this.root = this;
	    }
	    else{
		grandparent.left = node;
	    }
	    
	    node.left = savedParent;
	    savedParent.right = null;
	}

	private void insertCase5(){
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

	private void insertCase4(){
	    RBNode grandparent = this.grandparent();
	    RBNode node = this;

	    if((node == node.parent.right) && (node.parent == grandparent.left)){
		rotateLeft(node);

		node = node.left;
	    }

	    else if((node == node.parent.left) && (node.parent == grandparent.right)){
		rotateRight(node);

		node = node.right;
	    }
	    else{
		node.insertCase5();
	    }
	
	}

	private void insertCase3(){
	    RBNode uncle = this.uncle();
	    RBNode grandparent = this.grandparent();

	    if(uncle != null && uncle.red == true){
		parent.red = false;
		uncle.red = false;
		grandparent.red = true;
		
		grandparent.insertCase1();
	    }
	    else{
		this.insertCase4();
	    }
	}

	private void insertCase2(){
	    if(this.parent.red == false){
		return;
	    }
	    else{
		this.insertCase3();
	    }
	}

	private void insertCase1(){
	    if(this.parent == null){
		this.red = false;
		RBTree.this.root = this;
	    }
	    else{
		this.insertCase2();
	    }
	}
	
	/**
	 * @return the parent
	 */
	public RBNode getParent() {
	    return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(RBNode parent) {
	    this.parent = parent;
	}

	/**
	 * @return the left
	 */
	public RBNode getLeft() {
	    return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(RBNode left) {
	    this.left = left;
	}

	/**
	 * @return the right
	 */
	public RBNode getRight() {
	    return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(RBNode right) {
	    this.right = right;
	}

	/**
	 * @return the value
	 */
	public T getValue() {
	    return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(T value) {
	    this.value = value;
	}

	/**
	 * @return the red
	 */
	public boolean isRed() {
	    return red;
	}

	/**
	 * @param red the red to set
	 */
	public void setRed(boolean red) {
	    this.red = red;
	}
    }
}
