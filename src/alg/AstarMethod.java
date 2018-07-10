package alg;

import java.util.Iterator;
import java.util.LinkedList;


public class AstarMethod {
	public static void main(String[] args) {
		//int[][][] map2 = new int[3][2][1];
		boolean[][][] map1 = {{{false,true,false},{false,true,true},{false,true,false}}
						,{{false,true,false},{true,true,true},{false,true,false}},
						{{false,true,false},{true,true,false},{false,false,false}}};
		//map2[0] = map1;
		Grid map = new Grid(map1);
		GridNode start = new GridNode(0,0,0);
		GridNode dst = new GridNode(2,2,2);
		Astar as = new Astar(start, dst);
		AstarMethod am = new AstarMethod(as, start, dst, 6);
		
		Object result = am.path(dst);
		if(result != null) {
			System.out.println("path");
			System.out.println(am.printPath());
		}else {
			System.out.println("璺緞涓嶅瓨鍦�");
		}
        
	}
	private Astar astar;
	private GridNode start;
	private GridNode dst;
	private Grid map;
	public static LinkedList<GridNode> obsList = new LinkedList<>();
	private LinkedList<GridNode> result = new LinkedList<>();
	
	public AstarMethod(Astar astar, GridNode start, GridNode dst, Grid map) {
		this.astar = astar;
		this.dst = dst;
		this.start = start;
		this.map = map;
		this.astar.addToOpen(start);
	}
	public AstarMethod(Astar astar, GridNode start, GridNode dst, int size) {
		this.astar = astar;
		this.dst = dst;
		this.start = start;
		this.map = createMap(size);
		this.astar.addToOpen(start);
	}
	private Grid createMap(int size ) {
		boolean[][][] map = new boolean[size][size][size];
		for(int i = 0 ; i < size ; i ++) {
			for(int j = 0 ; j < size ; j ++) {
				for(int m = 0 ; m < size ; m ++) {
					if(start.getZ() == i && start.getY() == j && start.getX() == m) {
						continue;
					}
					if(dst.getZ() == i && dst.getY() == j && dst.getX() == m) {
						continue;
					}
					if(Math.random()> 0.8) {
						map[i][j][m] = true;
						obsList.add(new GridNode(i, j, m));
					}
				}
			}
		}
		map[start.getZ()][start.getY()][start.getZ()] = false;
		map[dst.getZ()][dst.getY()][dst.getZ()] = false;
		return new Grid(map);
	}
	public void step(GridNode start) {
		//寰楀埌涓巗tart鐩搁偦鐨勭偣
		Iterator<GridNode> it = map.adj(start);
		while(it.hasNext()) {
			GridNode node = it.next();
			//濡傛灉璇ョ偣鍙互鍔犲叆open琛�
			if(canAddToOpen(node)) {
				setG(node);
				setH(node);
				/*if(node.getName().equals("0,2,2")) {
					System.out.println("debug");
				}*/
				node.setFather(start);				
				astar.addToOpen(node);
			}
			else if(inOpen(node)) {
				if(node.getG() > start.getG() + 1) {
					node.setFather(start);
					if(node.getName().equals("0,2,2")) {
						System.out.println("debug");
					}
					node.setG(start.getG() + 1);
				}
			}
		}
	}
	public boolean inOpen(GridNode node){
		return astar.inOpen(node);
	}
	public boolean inClose(GridNode node) {
		return astar.inClose(node);
	}
	public LinkedList<GridNode> path(GridNode dst) {	
		boolean hasResult = false;
		while(true) {
			GridNode node = astar.mvOpenMinToClose();
			if(node == null) {
				if(astar.isEnd()) {
					break;
				}
				continue;
			}
			if(node.equals(dst)) {
				dst = node;
				hasResult = true;
				break;
			}
			step(node);
			
		}
		if(!hasResult) {
			return null;
		}
		for(GridNode no = dst; no != null ; no = no.father()) {
			result.addFirst(no);
		}
		return result;
	}

	private String printPath() {
		StringBuilder sb = new StringBuilder();
		Iterator<GridNode> it = result.iterator();
		while(it.hasNext()){
			GridNode node = it.next();
			sb.append(node.getName()+"\n");
		}
		return sb.toString();
	}
	private boolean canAddToOpen(GridNode node) {
		return !(inOpen(node)||inClose(node)|| map.isObtc(node)) ;
	}
	private void setG(GridNode node) {	
		node.setG(1);
	}
	//鏂滅嚎鐨勮窛绂诲姞涓婄洿绾跨殑璺濈,xy鏂瑰悜鍙互瀵硅绾匡紝Z鏂瑰悜涓嶈兘瀵硅绾�
	private void setH(GridNode node) {
		int dX = Math.abs(node.getX()- dst.getX());
		int dY = Math.abs(node.getY()- dst.getY());
		int dZ = Math.abs(node.getZ() - dst.getZ());
		node.setH(dX+dY+dZ);
	}
	
}
