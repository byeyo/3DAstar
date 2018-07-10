package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import alg.Astar;
import alg.AstarMethod;
import alg.GridNode;


/**
 * Servlet implementation class TransmitData
 */
@WebServlet("/TransmitData")
public class TransmitData extends HttpServlet {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder("[");
		LinkedList<GridNode> list = new LinkedList<>();
		list.add(new GridNode(0, 0, 0));
		list.add(new GridNode(3, 6, 8));
    	for(GridNode node : list) {
    		int z = node .getZ();
    		int y = node .getY();
    		int x = node .getX();
    		sb.append("{");
    		sb.append("'x':"+x+",");
    		sb.append("'y':"+y+",");
    		sb.append("'z':"+z+"}");
    		sb.append(",");
    	}
    	sb.replace(sb.length() - 1, sb.length(), "]");
    	System.out.println(sb.toString());
		JSONArray ja = (JSONArray) JSONValue.parse(sb.toString());
	
	}
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransmitData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GridNode start = new GridNode(0, 0, 0);
		GridNode dst = new GridNode(9, 9, 9);
		Astar as = new Astar(start, dst);
		AstarMethod asm = new AstarMethod(as, start, dst, 10);
	    String repStr = listToJSONstr(AstarMethod .obsList);
		//JSONObject json = JSONObject.toJSONString(map)
		// TODO Auto-generated method stub
	    request.setAttribute("obsJsonStr", repStr);
	    request.getRequestDispatcher("three.js-dev/three.js-dev/examples/mySpace/display.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
    public String listToJSONstr(LinkedList<GridNode> list) {
    	
    	StringBuilder sb = new StringBuilder("[");
    	for(GridNode node : list) {
    		int z = node .getZ();
    		int y = node .getY();
    		int x = node .getX();
    		sb.append("{");
    		sb.append("\'x\':"+x+",");
    		sb.append("\'y\':"+y+",");
    		sb.append("\'z\':"+z+"}");
    		sb.append(",");
    	}
    	sb.replace(sb.length() - 1, sb.length(), "]");
		return sb.toString();
    }
}
