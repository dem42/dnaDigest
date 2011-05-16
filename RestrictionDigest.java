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
				while (j > -1 && !wildEquals(word.charAt(i),word.charAt(j)))
					j = Next[j];
				i++;
				j++;
				if (i < m && wildEquals(word.charAt(i),word.charAt(j)))
					Next[i] = Next[j];
				else
					Next[i] = j;
			}
		}

		private boolean wildEquals(char a, char b)
		{
			if(a == 'M' || b == 'M')
			{
				return (a=='A')||(a=='C')||(b=='A')||(b=='C')||( (a=='M')&&(b=='M') );
			}
			if(a == 'R' || b == 'R')
			{
				return (a=='A')||(a=='G')||(b=='A')||(b=='G');
			}
			if(a == 'X' || b == 'X' || a == 'N' || b == 'N')
			{
				return true;
			}
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
				while (i > -1 && !wildEquals(word.charAt(i),stringToSearch.charAt(j)))
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
		List<Integer> r2 = kmp.cuts("AATTAMMAG", "AATTACCAG");
		List<Integer> r = kmp.cuts("AATTAMMAG", "AATTAGGAATTACCAG");
		debug(r2);
		debug(r);
	}
}
