package laserfiche;

import java.util.HashSet;

public class PageRange {
	HashSet<Integer> set;
	public PageRange(String s){
		if(null == s) throw new NullPointerException("Error: input string is null");
		if(s.length() <= 0) throw new IllegalArgumentException("Error: input string size shouldn't be 0");
		String[] ss = s.split(",");
		set = new HashSet<Integer>();
		for(int i=0; i<ss.length; ++i){
			boolean hasHyphen = false, hasColon=false;
			if(ss[i].length()<=0) throw new IllegalArgumentException("Error: no string before comma");
			if(ss[i].contains(Character.toString( ':' ))) hasHyphen=true;
			if(ss[i].contains(Character.toString( '-' ))) hasColon=true;
			if(hasHyphen && hasColon) throw new IllegalArgumentException("Error: can't exist Colon and Hyphen simutaneously");
			if(!hasHyphen && !hasColon){
				try{
					set.add(Integer.valueOf(ss[i]));
				}catch(NumberFormatException e){
					throw new IllegalArgumentException("Error: 1 string format is wrong");
				}
			}else{
				String[] sss = null;
				if(hasHyphen) sss = ss[i].split(":");
				if(hasColon) sss = ss[i].split("-");
				if(sss != null && sss.length != 2) throw new IllegalArgumentException("Error: no two values with separator");
				try{
					int start = Integer.valueOf(sss[0]);
					int end = Integer.valueOf(sss[1]);
					for(int j=start; j<=end; ++j) set.add(j);
				}catch(NumberFormatException e){
					throw new IllegalArgumentException("Error: 2 string format is wrong");
				}
			}
		}
		
		
	}
	
	public boolean isValid(int v){
		if(null == set) return false;
		return set.contains(v);
	}
	
	public static void main(String[] args){
		PageRange pr = new PageRange("5-1000,1,6,10,20");
		System.out.println(pr.isValid(10000));
	}
}
