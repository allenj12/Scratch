
public class RBTree<T extends Comparable<T>>{
    public RBNode<T> root;

    RBTree(){
	this.root = null;
    }

    public boolean search(T value){
	RBNode<T> current = this.root;
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
	RBNode<T> current = this.root;
	if(current == null){
	    RBNode<T> newRoot = new RBNode<T>(null, null, null, value);
	    newRoot.insertCase1();
	    this.root = newRoot;
	}
	else{
	    boolean inserted = false;
	    while(!inserted){
		if(value.compareTo(current.value) == 0){inserted = true;}
		if(value.compareTo(current.value) < 0){
		    RBNode<T> left = current.left;
		    if(left == null){
			left = new RBNode<T>(current, null, null, value);
			current.setLeft(left);
			left.insertCase1();
			inserted = true;
		    }
		    else{
			current = current.left;
		    }
		}
		else if(value.compareTo(current.value) > 0){
		    RBNode<T> right = current.right;
		    if(right == null){
			right = new RBNode<T>(current, null, null, value);
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
    
    public class RBNode<Y extends Comparable<Y>>{

	private RBNode<Y> parent;
	private RBNode<Y> left; 
	private RBNode<Y> right;
	private Y value;
	private boolean red;

	RBNode(RBNode<Y> parent, RBNode<Y> left, RBNode<Y> right, Y value){
	    this.parent = parent;
	    this.left = left;
	    this.right = right;
	    this.value = value;
	    this.red = true;
	}

	RBNode(Y value){
	    this.value = value;
	}

	public RBNode<Y> grandparent(){
	    RBNode<Y> parent = this.parent;
	    RBNode<Y> grandparent = parent.parent;
	    if(parent != null && grandparent != null){
		return grandparent;
	    }
	    else{
		return null;
	    }
	}

	public RBNode<Y> uncle(){
	    RBNode<Y> grandparent = this.grandparent();
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

	private void rotateRight(RBNode<Y> node){
	    RBNode<Y> parent = node.parent;
	    RBNode<Y> grandparent = parent.grandparent();
	    RBNode<Y> savedParent = grandparent.right;
	    RBNode<Y> savedRightN = node.right;

	    grandparent.right = node;
	    node.right = savedParent;
	    savedParent.right = savedRightN;
	}
    
	private void rotateLeft(RBNode<Y> node){
	    RBNode<Y> parent = node.parent;
	    RBNode<Y> grandparent = parent.grandparent();
	    RBNode<Y> savedParent = grandparent.left;
	    RBNode<Y> savedLeftN = node.left;

	    grandparent.left = node;
	    node.left = savedParent;
	    savedParent.right = savedLeftN;
	}

	private void insertCase5(){
	    RBNode<Y> grandparent = this.grandparent();

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
	    RBNode<Y> grandparent = this.grandparent();
	    RBNode<Y> node = this;

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
	    RBNode<Y> uncle = this.uncle();
	    RBNode<Y> grandparent = this.grandparent();

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
	    }
	    else{
		this.insertCase2();
	    }
	}
	
	/**
	 * @return the parent
	 */
	public RBNode<Y> getParent() {
	    return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(RBNode<Y> parent) {
	    this.parent = parent;
	}

	/**
	 * @return the left
	 */
	public RBNode<Y> getLeft() {
	    return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(RBNode<Y> left) {
	    this.left = left;
	}

	/**
	 * @return the right
	 */
	public RBNode<Y> getRight() {
	    return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(RBNode<Y> right) {
	    this.right = right;
	}

	/**
	 * @return the value
	 */
	public Y getValue() {
	    return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Y value) {
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
