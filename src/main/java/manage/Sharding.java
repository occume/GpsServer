package manage;

import java.util.concurrent.CopyOnWriteArrayList;

public class Sharding {

	private static Sharding sharding = new Sharding();
	
	private CopyOnWriteArrayList<String> tables = new CopyOnWriteArrayList<String>();
	
	private Sharding(){}
	
	public static Sharding instance(){
		return sharding;
	}
	
	public boolean exist(String tableName){
		return tables.contains(tableName);
	}
	
	public void put(String tableName){
		tables.add(tableName);
	}
	
}
