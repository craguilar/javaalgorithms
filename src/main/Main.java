package main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		long[] dic = new long[26];
		long n = in.nextInt();

		for (int i = 0; i < s.length(); i++) {
			dic[s.charAt(i) - 'a']++;
		}
		/*
		 * for(int i=0;i<26;i++){ if(dic[i]>0) System.out.println(Character.toString
		 * ((char) (i+'a'))+"("+dic[i]+")>"+dic[i]*(i+1)); }
		 */
		for (int a0 = 0; a0 < n; a0++) {
			int x = in.nextInt();
			boolean b = false;
			for (int i = 0; i < 26; i++) {
				if (dic[i] > 0 && x >= (i + 1) && x <= (dic[i] * (i + 1)) && ((x % (i + 1) == 0) || (i + 1) == 1)) {
					b = true;
					break;
				}
			}
			System.out.println(b ? "Yes" : "No");
		}
		System.out.println("End");
	}
	/*
	 * public static void main(String[] args) throws UnsupportedEncodingException,
	 * FileNotFoundException, IOException { // Time parameters long startTime;
	 * long stopTime; long elapsedTime; // Test string String a="hola";
	 * MyStringBuffer s= new MyStringBuffer(a);
	 * System.out.println(MyStringBuffer.checkIfPalindrome("oelseesleon"));
	 * 
	 * //String[] al={"CARLOS","RAQUEL","ALAINA"}; //for(int
	 * i=0;i<8192*32;i++){a=a+"a";} //MyStringBuffer sb= new MyStringBuffer(a);
	 * 
	 * // Test array of ints int b[]= new int[100000]; for(int
	 * i=0;i<b.length;i++){b[i]=b.length-i;}
	 * 
	 * //Test trees //int preOrder[]={'F','B', 'A', 'D', 'C', 'E', 'G', 'I', 'H'};
	 * //int inOrder[] ={'A','B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'}; //Tree t=
	 * new Tree(preOrder,inOrder);
	 * 
	 * //startTime = System.currentTimeMillis(); // Put your algorithm here...
	 * (new ShellSort()).runAlgorithm(true,b);
	 * 
	 * // End of your algorithm //stopTime = System.currentTimeMillis();
	 * //elapsedTime = stopTime - startTime;
	 * //System.out.println("\nElapsed time=" + elapsedTime + "[ms]");
	 * 
	 * 
	 * }
	 */

}