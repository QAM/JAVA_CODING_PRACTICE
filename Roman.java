package laserfiche;

import java.util.LinkedHashMap;
import java.util.Map;

public class Roman {
	
	public static LinkedHashMap<String, Integer> roman = new LinkedHashMap<String, Integer>();
	
	public Roman(){
		initRomanHash();
	}
	
	public static void initRomanHash(){
		if(roman.size() > 0) return;
		roman.put("M", 1000);
		roman.put("CM", 900);
		roman.put("D", 500);
		roman.put("CD", 400);
		roman.put("C", 100);
		roman.put("XC", 90);
		roman.put("L", 50);
		roman.put("XL", 40);
		roman.put("X", 10);
		roman.put("IX", 9);
		roman.put("V", 5);
		roman.put("IV", 4);
		roman.put("I", 1);
	}
	
	private Integer getInt(String s, int start, int end){
		Integer v = null;
		if(start==end) v = roman.get(Character.toString(s.charAt(start)));
		else if(start < end) v = roman.get(s.substring(start, end+1));
		if(null == v) throw new IllegalArgumentException("Error: unknow vocabulary");
		return v;
	}
	
	public int Roman2Int(String s){
		int i=0;
		int r = 0;
		while(i<s.length()){
			Integer v1 = getInt(s,i,i);
			if((i+1) < s.length() && v1<getInt(s,i+1,i+1)){
				r+=getInt(s,i,i+1); i+=2;
			}else{
				r+=v1; i++;
			}
		}
		return r;
	}
	
	public String Int2Roman(int v){
		StringBuilder r = new StringBuilder();
		while(v != 0){
			for(Map.Entry<String, Integer> k : roman.entrySet()){
				if(k.getValue() <= v){
					r.append(k.getKey());
					v-=k.getValue();
					break;
				}
			}
		}
		return r.toString();
	}
	
	public static void main(String[] args){
		Roman r = new Roman();
		
		System.out.println(r.Roman2Int("MDCCC"));
		
		System.out.println(r.Int2Roman(78));
	}
}
