package interview.binaryTree;

import java.util.LinkedList;
import java.util.Scanner;

public class Pep_JavaIP_10BinaryTrees_344NextRight {
	private static class Node {
		int data;
		Node left;
		Node right;

		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	public static void display(Node node) {
		if (node == null) {
			return;
		}

		String str = "";

		str += node.left == null ? "." : node.left.data;
		str += " <= " + node.data + " => ";
		str += node.right == null ? "." : node.right.data;

		System.out.println(str);

		display(node.left);
		display(node.right);
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int[] arr = new int[scn.nextInt()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = scn.nextInt();
		}

		// creation using the level order, seeing it like a heap
		// -1 says there is no node
		Node[] nodes = new Node[arr.length];
		for (int i = 0; i < nodes.length; i++) {
			if (arr[i] != -1) {
				nodes[i] = new Node(arr[i], null, null);

				if (i > 0) {
					int pi = (i - 1) / 2;

					if (i == 2 * pi + 1) {
						nodes[pi].left = nodes[i];
					} else {
						nodes[pi].right = nodes[i];
					}
				}
			}
		}
		Node root = nodes[0];
		
		int data = scn.nextInt();
//		display(root);

		solve(root, data);
	}

	private static void solve(Node root, int data){
		LinkedList<Node> queue = new LinkedList<>();

		// level order keeping track of levels
		queue.add(root);
		while(queue.size() > 0){
			// a new level starts, has count elements
			int count = queue.size();
			
			// finishing the level
			while(count-- > 0){
				Node rem = queue.removeFirst();

				// data found
				if(rem.data == data){
					if(count > 0){ // more left in same level, pick the next as next right
						System.out.println(queue.getFirst().data);
					} else {
						System.out.println("Not Found");
					}
					return;
				}
				
				if(rem.left != null){
					queue.addLast(rem.left);
				}
				
				if(rem.right != null){
					queue.addLast(rem.right);
				}
			}
		}
	}
	
}
/* Test case 1 
12 
1 2 3 4 5 6 7 8 9 10 11 12 
5
6 
*/

/* Test case 2 
12 
1 2 3 -1 4 5 -1 -1 -1 6 7 8
8
Not Found
*/

/* Test case 3 
12 
1 2 3 4 -1 -1 5 6 7 -1 -1 -1
4
5
*/

/* Test case 4
12 
1 2 3 4 -1 -1 5 6 7 -1 -1 -1
5
Not Found
*/