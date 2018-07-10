package alg;

import java.util.HashSet;
import java.util.Iterator;


public class Astar {
	public static void main(String[] args){
		MaxPQ<Num> m = new MaxPQ<>();
		m.insert(new Num(1));
		m.insert(new Num(0));
		m.insert(new Num(-1));
		System.out.println(m.max().num);
	}
	private GridNode start;
	private GridNode dst;
	HashSet<GridNode> close = new HashSet<>();
	MaxPQ<GridNode> open = new MaxPQ<>();
	HashSet<GridNode> openHashSet = new HashSet<>();
	public Astar(GridNode start,GridNode dst) {
		this.start = start;
		this.dst = dst;
	}
	
	
	public void addToOpen(GridNode x){
		System.out.println("加入了"+x.getName());
		open.insert(x);
		openHashSet.add(x);
	}
	public void addToClose(GridNode x){
		close.add(x);
	}
	public double G(){
		return 0;
	}
	public double H() {
		return 0;
	}
	public double F() {
		return G()+H();
	}
	public boolean isEnd() {
		return openHashSet.isEmpty();
	}
	public boolean inClose(GridNode node) {
		return close.contains(node);
	}
	public boolean inOpen(GridNode node) {
		return openHashSet.contains(node);
	}
	public GridNode mvOpenMinToClose() {
		GridNode node = null;
		try {
			node = open.delMax();
		} catch (Exception e) {
			return null;
		}
		
		System.out.println("得到最小点："+node.getName());
		if(node.getName().equals("2,2,1")) {
			System.out.println("debug");
		}
		Iterator<GridNode> it  = open.iterator();
		while(it.hasNext()) {
			GridNode node1 = it.next();
			System.out.println(node1.getName());
		}
		openHashSet.remove(node);
	    addToClose(node);
		return node;
	}
}
class Num implements Comparable<Num>{
	int num;
	public Num(int num){
		this.num = num;
	}
	
	@Override
	public int compareTo(Num o) {
		if(num > o.num){
			return -1;
		}
		if(num < o.num){
			return 1;
		}
		return 0;
	}
}
