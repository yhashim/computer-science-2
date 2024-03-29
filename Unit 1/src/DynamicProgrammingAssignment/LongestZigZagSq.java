package DynamicProgrammingAssignment;

import java.util.Arrays;

public class LongestZigZagSq {

	static int[] diffs;
	static int solution;
	static int length;
	
	public static void main(String[] args) {
		int[] sequence = {10, 21, 31, 2, 413, 2};
		diffs = new int[sequence.length-1];
		for (int i = 0; i<diffs.length; i++) {
			diffs[i] = sequence[i+1]-sequence[i];
		}
		if (sequence.length==1) {
			System.out.println(sequence.length);
		} else if (sequence.length==2) {
			if (diffs[0]!=0) {
				System.out.println(2);
			} else {
				System.out.println(1);
			}
		} else {
			System.out.println(longestZigZagSq(sequence)+2);
		}
	}

	public static int longestZigZagSq(int[] array) {
		int i;
		for (i = 0; i+1<diffs.length; i++) {
			if (diffs[i]!=0 && (Math.signum(diffs[i+1]*-1) == Math.signum(diffs[i]))) {
				length++;
			} else {
				break;
			}
		}
		if (i+1 == diffs.length-1) {
			return length;
		} else {
			if (i+1>diffs.length) {
				return 0;
			} else {
				diffs = Arrays.copyOfRange(diffs, i+1, diffs.length);
				solution = Math.max(length, longestZigZagSq(diffs));
				return solution;
			}
		}
	} 

}
