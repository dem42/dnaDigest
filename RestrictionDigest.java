import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestrictionDigest {

	static class KMP {

		// preprocess the word
		void prePro(String word, int[] Next)
		{
			int i, j;
			int m = word.length();

			i = 0;
			j = Next[0] = -1;
			while (i < m)
			{
				while (j > -1 && !widerEquals(word.charAt(j),word.charAt(i)))
					j = Next[j];
				i++;
				j++;
				if (i < m && widerEquals(word.charAt(i),word.charAt(j)))
					Next[i] = Next[j];
				else
					Next[i] = j;
			}
		}

		//a wildcard has to be wider than b
		private boolean widerEquals(char a, char b)
		{
			if(a == b)
				return true;
			
			if(a == 'M')
				return (b=='A')||(b=='C');
			
			if(a == 'R')
				return (b=='A')||(b=='G');
			
			if(a == 'W')
				return (b=='A')||(b=='T');
			
			if(a == 'S')
				return (b=='C')||(b=='G');
			
			if(a == 'Y')
				return (b=='C')||(b=='T');
			
			if(a == 'K')
				return (b=='G')||(b=='T');
			
			if(a == 'V')
				return (b=='A')||(b=='C')||(b=='G')||(b=='M')||(b=='R')||(b=='S');
			
			if(a == 'H')
				return (b=='A')||(b=='C')||(b=='T')||(b=='M')||(b=='W')||(b=='Y');
			
			if(a == 'D')
				return (b=='A')||(b=='G')||(b=='T')||(b=='R')||(b=='W')||(b=='K');
			
			if(a == 'B')
				return (b=='C')||(b=='G')||(b=='T')||(b=='S')||(b=='Y')||(b=='K');
			
			if(a == 'N' || a == 'X')
				return true;
			
			return false;
		}
		
		List<Integer> cuts(String word, String stringToSearch)
		{
			int i, j;
			//int pre=0;
			int[] kmpNext = new int[word.length() + 1];
			int m = word.length();
			int n = stringToSearch.length();
			List<Integer> cuts = new ArrayList<Integer>();

			prePro(word, kmpNext);
			debug(kmpNext);
			i = j = 0;
			while (j < n)
			{
				//debug(i,j);
				while (i > -1 && !widerEquals(word.charAt(i),stringToSearch.charAt(j)))
					i = kmpNext[i];
				i++;
				j++;
				if (i >= m)
				{
					i = kmpNext[i];
					//pre++;
					cuts.add(j-m);
				}
			}
			return cuts;
		}
	}
	
	public static void debug(Object... obs)
	{
		System.out.println(Arrays.deepToString(obs));
	}

	public String[] findEnzymeCocktail(String dna, String[] enzymes,
			String[] restrictions)
	{

		return null;
	}
	
	public static void main(String[] args)
	{
		KMP kmp = new KMP();
		//List<Integer> res = kmp.cuts("anmandanmanpan", "isawthisweirdanpanmaninananpanmansanmanpanmanpanandanmanpanmanpmanmanpan");
		//debug(res);
		//List<Integer> res2 = kmp.cuts("aaaa", "aaaaa");
		//debug(res2);
		List<Integer> r2 = kmp.cuts("AATTAMMAG", "AATTACCAGAAATAATTACCAATTAATTAATTACCAG");
		List<Integer> r = kmp.cuts("AATTAMMAG", "AATTAGGAATTACCAG");
		List<Integer> r3 = kmp.cuts("NNMM", "AATTAGGAATTACCAG");
		debug(r2);
		debug(r);
		debug(r3);
	}
}
