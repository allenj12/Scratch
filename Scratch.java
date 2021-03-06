
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class Scratch{

    //////////////////////////////////////////////////////////////////////////
    //given an array find the most frequent element
    //////////////////////////////////////////////////////////////////////////
    public static int mostFrequent(int[] a){
	//could use map to store all frequencies, but if im not allowed do this,

	if(a.length <= 2){
	    return a[0];
	}
	
	Arrays.sort(a);

	int previous = a[0];
	int largestCount = 0;
	int tempCount = 1;
	for(int i = 1; i < a.length; i++){
	    if(previous == a[i]){
		tempCount++;
		}
	    else{
		if(tempCount > largestCount){
		    largestCount = tempCount;
		    tempCount = 1;
		}
		previous = a[i];
	    }
	}

	if(tempCount > largestCount){
	    largestCount = tempCount;
	}
	
	return largestCount;
    }

    public static void runMostFrequent(){
	int[] a = {1, 3, 3, 3};
	System.out.println(mostFrequent(a));
    }

    //////////////////////////////////////////////////////////////////////////
    //Find pairs in an integer array whose sum is equal to 10 (bonus: O(n))
    //////////////////////////////////////////////////////////////////////////
    public static void pairs(int[] a){
	HashSet<Integer> lookup = new HashSet<Integer>();

	for(int i = 0; i < a.length; i++){
	    if(lookup.contains(10 - a[i])){
		System.out.println("PAIR " + i); //just enough to prove the concept
	    }
	    else{
		lookup.add(a[i]);
	    }
	}
    }

    public static void runPairs(){
	int[] a = {80, 1, 2, 8, 9, -70};
	pairs(a);
    }

    //////////////////////////////////////////////////////////////////////////
    //Given 2 integer arrays, determine of the 2nd array is a rotated version of the 1st array. 
    //////////////////////////////////////////////////////////////////////////
    public static int rotateGet(int rotatedBy, int index, int[] a){
	return a[(rotatedBy + index) % a.length];
    }
    
    public static boolean isRotated(int[] a, int[] b){
	if(a.length != b.length){
	    return false;
	}	

	for(int i = 0; i < a.length; i++){
	    boolean matched = true;
	    for(int j = 0; j < a.length; j++){
		if(!(a[j] == rotateGet(i , j, b))){
		    matched = false;
		    break;
		}
	    }
	    if(matched){
		return true;
	    }
	}
	return false;
    }

    public static void runIsRotated(){
	int[] a = {1, 2, 1, 3, 1};
	int[] b = {3, 1, 1, 2, 1};

	System.out.println(isRotated(b,a));
    }

    //////////////////////////////////////////////////////////////////////////
    //fib recursivly and interativly
    //////////////////////////////////////////////////////////////////////////    
    public static long fibonacciRecur(int n) { //use map from input to output to do it "dynamically"
        return (n <= 1) ?
	    n:
	    fibonacciRecur(n-1) + fibonacciRecur(n-2);
    }

    public static long fibonacciIter(int n){
	int x = 0;
	int y = 1;
	int z = 1;

	for(int i = 0; i < n; i++){
	    x = y;
	    y = z;
	    z = x + y;
	}
	return x;
    }

    public static void runBothFib(){
	System.out.println(fibonacciRecur(7));
	System.out.println(fibonacciIter(7));
    }
    
    //////////////////////////////////////////////////////////////////////////
    //Find the lonely element in an array 
    //////////////////////////////////////////////////////////////////////////
    public static int getSingleton(int[] a){
	int result = 0;
	for(int elem : a){
	    result ^= elem; //only works if there are only duplicats,
	                    //otherwise use hashtable, or fliter out the XORs with repats into another value
	}
	return result;
    }

    public static void runGetSingleton(){
	int[] a = {1, 2, 3, 2, 1}; 
	System.out.println(getSingleton(a));
    }

    //////////////////////////////////////////////////////////////////////////
    //get common elements between two arrays
    //////////////////////////////////////////////////////////////////////////    
    public static void getCommon(int[] a, int[] b){

	Arrays.sort(a);
	Arrays.sort(b);
	
	int aI = 0;
	int bI = 0;
	while(aI < a.length && bI < b.length){
	    System.out.print(a[aI] + " ");
	    System.out.println(b[bI]);
	    if(a[aI] == b[bI]){
		System.out.println(a[aI]);
	    }
	    if(a[aI] < b[bI]){
		aI++;
	    }
	    else{
		bI++;
	    }
	}
    }

    public static void runGetCommon(){
	int[] a = {1, 2, 7, 8};
	int[] b = {4, 2, 3, 8};

	getCommon(a, b);
    }

    //////////////////////////////////////////////////////////////////////////
    //BinSearch for rotated arrays (we will use the getRotate we made earlier
    //////////////////////////////////////////////////////////////////////////
    public static int binSearchRot(int rotBy, int[] a, int searchFor){
	int n = a.length / 2;
	int ub = a.length;
	int lb = 0;

	while(ub >= lb){
	    if(rotateGet(rotBy, n, a) == searchFor){return (rotBy + n) % a.length;}
	    if(rotateGet(rotBy, n, a) > searchFor){
		ub = n;
		n = n / 2;
	    }
	    else{
		lb = n;
		n = ub / 2;
	    }
	}
	return -1;
    }

    public static void runBinSearchRot(){
	int[] a = {6, 7, 8, 1, 2, 3, 4, 5};
	System.out.println(binSearchRot(3, a, 3));
    }

    //////////////////////////////////////////////////////////////////////////
    //implement parseInt "473" => (int) 473
    //////////////////////////////////////////////////////////////////////////
    public static int getInt(char c){ //im dumb could have used ascii table.... o well.
	int result = 0;
	switch(c){
	case '0': result = 0;
	    break;
	case '1': result = 1;
	    break;
	case '2': result = 2;
	    break;
	case '3': result = 3;
	    break;
	case '4': result = 4;
	    break;
	case '5': result = 5;
	    break;
	case '6': result = 6;
	    break;
	case '7': result = 7;
	    break;
	case '8': result = 8;
	    break;
	case '9': result = 9;
	    break;
	default: result = 0;
	    break;
		}
	return result;
    }
    public static int parseInt(String str){
	int result = 0;

	for(int i = 0; i < str.length(); i++){
	    result += getInt(str.charAt(i)) * Math.pow(10, str.length() - 1 - i);
	}

	return result;
    }

    public static void runParseInt(){
	System.out.println(parseInt("473"));
    }

    //////////////////////////////////////////////////////////////////////////
    //all unique chars in string, I stole this from SO, I liked it much better since I wouldnt have thought of it at all
    //////////////////////////////////////////////////////////////////////////
    //normaly it would be better to have an array of bools
    public static boolean isUniqueChars(String str) {
    // short circuit - supposed to imply that
    // there are no more than 256 different characters.
    // this is broken, because in Java, char's are Unicode,
    // and 2-byte values so there are 32768 values
    // (or so - technically not all 32768 are valid chars)
    if (str.length() > 256) {
        return false;
    }
    // checker is used as a bitmap to indicate which characters
    // have been seen already
    int checker = 0;
    for (int i = 0; i < str.length(); i++) {
        // set val to be the difference between the char at i and 'a'
        // unicode 'a' is 97
        // if you have an upper-case letter e.g. 'A' you will get a
        // negative 'val' which is illegal
        int val = str.charAt(i) - 'a';
        // if this lowercase letter has been seen before, then
        // the corresponding bit in checker will have been set and
        // we can exit immediately.
        if ((checker & (1 << val)) > 0) return false;
        // set the bit to indicate we have now seen the letter.
        checker |= (1 << val);
    }
    // none of the characters has been seen more than once.
    return true;
}
    public static void runIsUniqueChars(){
	String str = "abcdef";
	System.out.println(isUniqueChars(str));
    }

    //////////////////////////////////////////////////////////////////////////
    //Shortest Palindrome
    //////////////////////////////////////////////////////////////////////////
    public static boolean isPalindrome(String str){
	for(int i = 0; i < str.length() / 2; i++){
	    int j = str.length() - i - 1;

	    if(str.charAt(i) != str.charAt(j)){
		return false;
	    }
	}

	return true;
    }
    
    public static String shortestPalindrome(String str){ //come back to this later to make O(n)
	for(int i = 1; 0 < str.length() + 1; i++){
	    String appended = str + new StringBuilder(str.substring(0,i)).reverse().toString();
	    //System.out.println(appended);
	    if(isPalindrome(appended)){
		return appended;
	    }
	}
	
	return "NA";
    }

    public static void runShortestPalindrome(){
	System.out.println(shortestPalindrome("bannana"));
    }

    public static void minTestRBTrees(){
	RBTree<Integer> tree = new RBTree<Integer>();

	tree.insert(100);
	tree.insert(150);
	tree.insert(50);
	tree.insert(75);
	tree.insert(74);
	tree.insert(51);
	tree.insert(52);
	tree.printTree(tree.root);
	tree.insert(1);
	tree.insert(2);
	System.out.println(tree.root.getValue());
	tree.printTree(tree.root);
    }

    public static void pushh(int elem, Stack<Integer> left, Stack<Integer> right){
	right.push(elem);
    }

    public static int popp(Stack<Integer> left, Stack<Integer> right){
	if(!left.isEmpty()){
	    return left.pop();
	}
	else{
	    while(!right.isEmpty()){
		left.push(right.pop());
	    }
	    if(!left.isEmpty()){
		return left.pop();
	    }
	    else{
		return 0;
	    }
	}
    }

    public static void runTwoStackQueue(){
	Stack<Integer> l = new Stack<Integer>();
	Stack<Integer> r = new Stack<Integer>();

	pushh(1,l,r);
	pushh(2,l,r);
	pushh(3,l,r);
	System.out.println(r);
	System.out.println(l);
	
	System.out.println("-------------");

	int x = popp(l,r);
	System.out.println(r);
	System.out.println(l);
	System.out.println(x);

	System.out.println("-------------");
	
	pushh(4,l,r);
	pushh(5,l,r);
	pushh(6,l,r);
	System.out.println(r);
	System.out.println(l);

	System.out.println("-------------");

	x = popp(l,r);
	System.out.println(r);
	System.out.println(l);
	System.out.println(x);

	System.out.println("-------------");

	x = popp(l,r);
	System.out.println(r);
	System.out.println(l);
	System.out.println(x);

	System.out.println("-------------");

	x = popp(l,r);
	System.out.println(r);
	System.out.println(l);
	System.out.println(x);

	System.out.println("-------------");

	x = popp(l,r);
	System.out.println(r);
	System.out.println(l);
	System.out.println(x);

	System.out.println("-------------");

	x = popp(l,r);
	System.out.println(r);
	System.out.println(l);
	System.out.println(x);

	System.out.println("-------------");

	x = popp(l,r);
	System.out.println(r);
	System.out.println(l);
	System.out.println(x);
    }

    // Returns the length of the longest palindromic subsequence in seq
    static int lpSubsequence(String seq)
    {
       int n = seq.length();
       int i, j, cl;
       int L[][] = new int[n][n];  // Create a table to store results of subproblems
      
       // Strings of length 1 are palindrome of lentgh 1
       for (i = 0; i < n; i++)
           L[i][i] = 1;
              
        // Build the table. Note that the lower diagonal values of table are
        // useless and not filled in the process. The values are filled in a
        // manner similar to Matrix Chain Multiplication DP solution (See
        // http://www.geeksforgeeks.org/archives/15553). cl is length of
        // substring
        for (cl=2; cl<=n; cl++)
        {
            for (i=0; i<n-cl+1; i++)
            {
                j = i+cl-1;
                if (seq.charAt(i) == seq.charAt(j) && cl == 2)
                   L[i][j] = 2;
                else if (seq.charAt(i) == seq.charAt(j))
                   L[i][j] = L[i+1][j-1] + 2;
                else
                   L[i][j] = Math.max(L[i][j-1], L[i+1][j]);
            }
        }
              
        return L[0][n-1];
    }

    public static int lpSubstring(String str)
    {
	int n = str.length(); // get length of input string
 
	// table[i][j] will be false if substring str[i..j]
	// is not palindrome.
	// Else table[i][j] will be true
	boolean[][] table = new boolean[n][n];
 
	// All substrings of length 1 are palindromes
	int maxLength = 1;
	for (int i = 0; i < n; ++i)
	    table[i][i] = true;
 
	for (int i = 0; i < n-1; ++i)
	    {
		if (str.charAt(i) == str.charAt(i+1))
		    {
			table[i][i+1] = true;
			maxLength = 2;
		    }
	    }
 
	// Check for lengths greater than 2. k is length
	// of substring
	for (int k = 3; k <= n; ++k)
	    {
		// Fix the starting index
		for (int i = 0; i < n-k+1 ; ++i)
		    {
			// Get the ending index of substring from
			// starting index i and length k
			int j = i + k - 1;
 
			// checking for sub-string from ith index to
			// jth index iff str[i+1] to str[j-1] is a
			// palindrome
			if (table[i+1][j-1] && str.charAt(i) == str.charAt(j))
			    {
				table[i][j] = true;
 
				if (k > maxLength)
				    {
					maxLength = k;
				    }
			    }
		    }
	    }
	return maxLength;
    }

    public static void main(String[] args){
	//runTwoStackQueue();
	System.out.println(lpSubsequence("annxa"));
	System.out.println(lpSubstring("annxa"));
    }
}
