package manage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Sharding {

	private static Sharding sharding = new Sharding();
	
	private CopyOnWriteArrayList<String> tables = new CopyOnWriteArrayList<String>();
	
	private Sharding(){}
	
	public static Sharding instance(){
		return sharding;
	}
	
	public void initTables(List<String> tracks){
		tables.addAll(tracks);
	}
	
	public boolean exist(String tableName){
		for(String tb: tables){
			if(tb.contains(tableName)){
				return true;
			}
		}
		return false;
	}
	
	public void put(String tableName){
		tables.add(tableName);
	}
	
}
