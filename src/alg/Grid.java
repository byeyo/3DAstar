package alg;

import java.util.Iterator;
import java.util.LinkedList;

public class Grid {
	private boolean[][][] gridMap;
	private int sizeX;
	private int sizeY;
	private int sizeZ;
	public Grid(boolean[][][] grideMap){
		this.sizeZ = grideMap.length - 1;
		this.sizeY = grideMap[0].length - 1;
		this.sizeX = grideMap[0][0].length - 1;
		/*System.out.println(grideMap[0][1][0]);
		System.out.println(grideMap[0][2][0]);*/
		gridMap = grideMap;
	}
	public Iterator<GridNode> adj(GridNode grideNode) {
		LinkedList<GridNode> list = new LinkedList<>();
		for(int x = getFloor(grideNode.getX()); x <= getTop(grideNode.getX(), 'x'); x ++) {
			for(int y = getFloor(grideNode.getY()); y <= getTop(grideNode.getY(), 'y');y ++) {
				for(int z = getFloor(grideNode.getZ()); z <= getTop(grideNode.getZ(), 'z'); z ++)
					if(!gridMap[z][y][x]&&!grideNode.getName().equals(z+","+y+","+x)) {
						list.add(new GridNode(z, y, x));
					}
			}
		}	
		/*if(getTop(grideNode.getZ(), 'z') != grideNode.getZ()) {
			if(!gridMap[grideNode.getZ() +1 ][grideNode.getY()][grideNode.getX()])
			list.add(new GrideNode(grideNode.getX(),grideNode.getY(),grideNode.getZ() + 1));
		}
		if(getFloor(grideNode.getZ()) != grideNode.getZ()) {
			if(!gridMap[grideNode.getZ() - 1][grideNode.getY()][grideNode.getX()] )
			list.add(new GrideNode(grideNode.getX(),grideNode.getY(),grideNode.getZ() - 1));
		}*/
		return list.iterator();
	}
	public boolean isObtc(GridNode node) {
		int x = node.getX();
		int y = node.getY();
		int z = node .getZ();
		//超出边界
		if(z>sizeZ||y>sizeY||x>sizeX) {
			return true;
		}
		//障碍
		return gridMap[z][y][x];
	}
	private int getTop(int val,char dimName) {
		if(val+1 > getSize(dimName)) {
			return val;
		}
		return val + 1;
	}
	//根据x是否处于地图边界返回不同的值
	private int getFloor(int val) {
		if(val - 1 < 0) {
			return 0;
		}
		return val - 1;
	}
	
	private int getSize(char dimName) {
 		switch(dimName) {
			case 'x':return sizeX;
			case 'y':return sizeY;
			case 'z':return sizeZ;
		}
		System.err.println("错误的读取地图格式");
		return -1;
	}
}
