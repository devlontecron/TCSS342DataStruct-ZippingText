/*
 * TCSS 342 - Spring 2016
 * Assignment 5 - MazeGenerator
 */

/**
 * Main driver Class.
 * 
 * @author Samantha Ong - sjfoh and Devin Durham - d1duram
 * @version 3 May 2016
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodingTree {
	
	List<Node> myChars = new ArrayList<Node>();
	Map<Character, String> codes = new HashMap<Character, String>();
	final StringBuilder sb = new StringBuilder();
	String bits;
	StringBuilder sbCodes = new StringBuilder();
	
	private class Node{
		
		char Item;
		int freq;
		Node left;
		Node Right;
		
		public Node (char theItem, int theFreq){
			Item = theItem;
			freq = theFreq;
		}
		public Node (int theFreq){	
			freq = theFreq;
		}
		
		public Node(){	
		}

	}
	
	
	void CodingTree(String message){
		readCharFreqs(message);
		makeTree();
		readTree(myChars.get(0));
		toBin(message);
	}
	
	
	public void toBin(String message) {
		StringBuilder outBin = new StringBuilder();
		for(int x = 0; x<message.length(); x++){
			outBin.append(codes.get(message.charAt(x)));			
		}
		bits = outBin.toString();	
	}
	
	
	private void readTree(Node root){
		
		if(root.left!= null){
			sb.append("0");
			readTree(root.left);
		} if(root.Right != null){
			sb.append("1");
			readTree(root.Right);
		} if (root.left == null && root.Right == null){
			//System.out.println(root.Item + " " + sb.toString());
			codes.put(root.Item, sb.toString());
			sbCodes.append(root.Item + "=" + sb.toString() + " \n");
		} if(sb.length() != 0){
			sb.deleteCharAt(sb.length()-1);
		}
	}

	
	
	private void readCharFreqs(String theString){
		for(int x = 0; x<theString.length(); x++){
			char curr = theString.charAt(x);
			Boolean isMatch = false;
			
			for(int p =0; p<myChars.size();p++){
				if(myChars.get(p).Item == curr){
					myChars.get(p).freq++;
					isMatch =true;
					}
				}
			
			if(isMatch ==false){
				Node newNode = new Node(curr,1);
			    myChars.add(newNode);
			}
		}
	}
	
	private void makeTree() {
		while (myChars.size() - 1 > 0) {

			Node min1 = new Node();
			Node min2 = new Node();

			min1 = myChars.get(0);
			for (int y = 0; y < myChars.size(); y++) {
				if (min1.freq > myChars.get(y).freq) {
					min1 = myChars.get(y);
				}
			}
			myChars.remove(min1);
			
			if (myChars.size() > 0) {
				min2 = myChars.get(0);
				for (int y = 0; y < myChars.size(); y++) {
					if (min2.freq > myChars.get(y).freq) {
						min2 = myChars.get(y);
					}
				}
				myChars.remove(min2);
			}

			int par = min1.freq + min2.freq;
			//System.out.println(min1.Item);
			//System.out.println(min2.Item);
			//System.out.println(par);
			Node parNode = new Node(par);
			parNode.left = min1;
			parNode.Right = min2;

			myChars.add(parNode);

		}
	}
	
	

}
