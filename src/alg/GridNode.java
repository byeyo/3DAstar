package alg;

public class GridNode implements Comparable<GridNode>{
	private double G;
	private double F;
	private double H;
	private int x;
	private int y;
	private int z;
	private String name;
	private GridNode father;
	public GridNode(int z,int y, int x) {
		this.z = z;
		this.y = y;
		this.x = x;
		name = z + "," + y + "," + x;
	}
	public double getG(){
		return G;
	}
	public void setG(double g) {
		F = F -G +g;
		G = g;
	}
	public double getF() {
		return F;
	}
	public void setF(double f) {
		F = f;
	}
	public double getH() {
		return H;
	}
	public void setH(double h) {
		F = F - H + h;
		H = h;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getZ() {
		return z;
	}
	public GridNode father() {
		return father;
	}
	public void setFather(GridNode father) {
		this.father = father;
	}
	@Override
	public int compareTo(GridNode o) {
		if(F > o.getF()){
			return -1;
		}else if(F < o.getF()){
			return 1;
		}
		
		return 0;
	}
	@Override
	public String toString() {
		return name;
	}
	@Override 
	public int hashCode() {
		return name.hashCode();
	}
	@Override
	public boolean equals(Object o) {
		if(!o.getClass().getName().equals(this.getClass().getName())) {
			return false;
		}
		GridNode obj = (GridNode) o ;
		return this.name.equals(obj.getName());
	}
	
}
