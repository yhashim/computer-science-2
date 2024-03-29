package NomenclatureFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class Driver {
	
	// WINK SHEET!
			// oxyanions (back of table alters)
				// per ___ ate
				// ___ate
				// ___ite
				// hypo___ite
			// ionic hydrates (solid sols w/ H2O trapped)
				// IUPAC -> anhydrous name-#-water
				// Common -> anhydrous name prefixhydrate
					// prefixes: mono, di, tri, tetra, penta, hexa, hepta, octa, nona, deca
			// organic compounds
				// prefixes: meth, eth, prop, but, pent, hex, hept, oct, non, dec
				// hydrocarbons (only C and H)
					// ane -> CnH2n+2
					// ene -> CnH2n
					// yne -> CnH2n-2
				// alcohols
					// CnH2n+1OH
					// name -> alkane name+"anol"
			// acids
				// H balanced w/ anion
				// naming:
					// IUPAC -> aqueous hydrogen anion
					// classic naming:
						/*
						 * ide -> hyro___ic acid
						 * ate -> ___ic acid
						 * ite -> ___ous acid
						 */

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static boolean done;

	public static void main(String[] args) throws IOException {
		String[][] oxyanions = Permutations.getOxyanions();
		String[][] specials = Permutations.getSpecials();	
		String[][] organicCompounds = Permutations.getOrgComps();
		String[][] acidsIUPAC = Permutations.getAcidsIUPAC();
		String[][] acidsCLASSIC = Permutations.getAcidsClassic();
//		String[][] ionicHydratesIUPAC = Permutations.getIHI();
//		String[][] ionicHydratesCOMM = Permutations.getIHC();
		done = false;
		print("Welcome to nomenclature 101!\nIf you find an error please tell me!"
				+ "\nFor reference, subscripts are represented by normal numbers."
				+ "\nSuperscripts are preceeded by \"^\" signs.\n");
		print("Let's begin!\n");
		while (!done) {
			int random = (int)(Math.random()*5);
			if (random==0) {
				// oxyanions
				question(oxyanions);
			} else if (random==1) {
				// sugars + molecules + etc.
				question(specials);
			} else if (random==2) {
				// organic compounds
				question(organicCompounds);
			} else if (random==3) {
				// acid
				print("IUPAC: ");
				question(acidsIUPAC);
			} else if (random==4) {
				// acid
				print("Classic naming: ");
				question(acidsCLASSIC);
			}
//			} else if (random==5) {
//				// ionicHydratesIUPAC
//				question(ionicHydratesIUPAC);
//			}
//			} else if (random==6) {
//			// ionicHydratesCOMM
//			question(ionicHydratesCOMM);
//		}
			print("Next...\n");
		}
	}

	private static void question(String[][] array) {
		int random = (int)(Math.random()*array.length);
		int formulaOrName = (int) Math.round(Math.random());
		if (formulaOrName==0) {
			// formula given
			print(array[random][0]+"\n");
			if(prompt().equals(array[random][1])) {
				print("Good job!\n");
			} else {
				print("Correction: " + array[random][1]+"\n");
			}
		} else if (formulaOrName==1){
				// name given
				print(array[random][1]+"\n");
				if(prompt().equals(array[random][0])) {
					print("Good job!\n");
				} else {
					print("Correction: " + array[random][0]+"\n");
				}
		}
	}

	static void print(String temp) {
		int lapse = 15;
		for (int i = 0; i < temp.length(); i++) {
			try {
				TimeUnit.MILLISECONDS.sleep(lapse);
				System.out.print(temp.charAt(i));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static String prompt() {
		try {
			String input = readLine();
			return input;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			st = new StringTokenizer(br.readLine().trim());
		}
		return st.nextToken();
	}

	static String readLine() throws IOException {
		return br.readLine().trim();
	}

}
