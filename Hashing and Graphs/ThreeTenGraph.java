import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedGraph;

import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.graph.util.EdgeType;

import org.apache.commons.collections15.Factory;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collection;

//You may use your hash map and hash set if you'd like
//or you may import the java.util versions.
//The interface  is the same for both, so the code you
//write here (in ThreeTenGraph) should be the same for
//either one!

//Uncomment the following lines if you want to use the java.util version
import java.util.HashMap; //or use ThreeTenHashMap!
import java.util.HashSet; //or use ThreeTenHashSet!
	
import java.util.NoSuchElementException;
/**
 * This class repsents a graph with some functionality. 
 * @param <V> generic type. 
 * @param <E> generic type. 
 */
class ThreeTenGraph<V extends ThreeTenGraphComponent, E extends ThreeTenGraphComponent>
	implements Graph<V, E>, UndirectedGraph<V, E> {
	
	//********************************************************************************
	//   YOUR CODE GOES IN THIS SECTION
	//********************************************************************************
	
	//**************** IMPORTANT WARNING ****************
	//Due to Java complexities with bounded genics that it would be difficult to explain here,
	//if you want an array of V[] or E[], the following format ___SHOULD NOT___ be used:
	//         V[] items = (V[]) new Object[10];
	//instead, use this format:
	// private static class Pair<V, E> {
	// 	private V vertex;
	// 	private E edge;
	// 	private Pair next;

	// }
	/**
	 * This represents graph storage. 
	 */
	private HashMap<V, HashMap<V, E>> graph;


	/**
	 * Creates a new graph. Initializing all appropriate instance variables.
	 */
	@SuppressWarnings("unchecked")
	public ThreeTenGraph() {
		this.graph = new HashMap<V, HashMap<V, E>> ();	
	}
	
	/**
	 * Returns a view of all edges in this graph. In general, this
	 * obeys the Collection contract, and therefore makes no guarantees 
	 * about the ordering of the vertices within the set.
	 * @return a Collection view of all edges in this graph
	 */
	public Collection<E> getEdges() {
		Collection<E> ret = new HashSet<>();
		for (V vertice: graph.keySet()){
			for (E edge: graph.get(vertice).values()){
				ret.add(edge);
			}
		}
		return ret;
	}
	
	/**
	 * Returns a view of all vertices in this graph. In general, this
	 * obeys the Collection contract, and therefore makes no guarantees 
	 * about the ordering of the vertices within the set.
	 * @return a Collection view of all vertices in this graph
	 */
	public Collection<V> getVertices() {
		return graph.keySet();
	}
	
	/**
	 * Returns the number of edges in this graph.
	 * @return the number of edges in this graph
	 */
	public int getEdgeCount() {
		return getEdges().size();
	}
	
	/**
	 * Returns the number of vertices in this graph.
	 * @return the number of vertices in this graph
	 */
	public int getVertexCount() {
		return getVertices().size();
	}
	
	/**
	 * Returns the collection of vertices in this graph which are connected to edge.
	 * Note that for some graph types there are guarantees about the size of this collection
	 * (i.e., some graphs contain edges that have exactly two endpoints, which may or may 
	 * not be distinct).  Implementations for those graph types may provide alternate methods 
	 * that provide more convenient access to the vertices.
	 * 
	 * @param edge the edge whose incident vertices are to be returned
	 * @return  the collection of vertices which are connected to edge, or null if edge is not present
	 */
	public Collection<V> getIncidentVertices(E edge) {
		Collection<V> ret = new HashSet<>();
		for (V ver: graph.keySet()){
			HashMap<V, E> temp = graph.get(ver);
			for (V v: temp.keySet()){
				if (temp.get(v).equals(edge)){
					ret.add(v);
					ret.add(ver);
				}
			}
		}
		return ret;
	}

	/**
	 * Returns the collection of vertices which are connected to vertex
	 * via any edges in this graph.
	 * If vertex is connected to itself with a self-loop, then 
	 * it will be included in the collection returned.
	 * 
	 * @param vertex the vertex whose neighbors are to be returned
	 * @return  the collection of vertices which are connected to vertex, or null if vertex is not present.
	 */
	public Collection<V> getNeighbors(V vertex) {
		if (!getVertices().contains(vertex)){
			return null;
		}
		return graph.get(vertex).keySet();
	}
	
	/**
	 * Returns the collection of edges in this graph which are connected to vertex.
	 * 
	 * @param vertex the vertex whose incident edges are to be returned
	 * @return  the collection of edges which are connected to vertex, or null if vertex is not present
	 */
	public Collection<E> getIncidentEdges(V vertex) {
		if (!getVertices().contains(vertex)){
			return null;
		}
		return graph.get(vertex).values();
	}
	
	/**
	 * Returns an edge that connects v1 to v2.
	 * If this edge is not uniquely
	 * defined (that is, if the graph contains more than one edge connecting 
	 * v1 to v2), any of these edges 
	 * may be returned.  findEdgeSet(v1, v2) may be 
	 * used to return all such edges.
	 * Returns null if either of the following is true:
	 * v1 is not connected to v2
	 * either v1 or v2 are not present in this graph
	 * For purposes of this method, v1 is only considered to be connected to
	 * v2 via a given <i>directed</i> edge e if
	 * v1 == e.getSource() && v2 == e.getDest() evaluates to true.
	 * (v1 and v2 are connected by an undirected edge u if 
	 * u is incident to both v1 and v2.)
	 * @param v1 is a vertex. 
	 * @param v2 is a vertex.
	 * @return  an edge that connects v1 to v2, or null if no such edge exists (or either vertex is not present)
	 * @see Hypergraph#findEdgeSet(Object, Object) 
	 */
	public E findEdge(V v1, V v2) {
		HashMap<V, E> map = graph.get(v1);
		E ret = null;
		for(V ver: map.keySet()){
			if (ver.equals(v2)){
				ret = map.get(ver);
				break;
			}
		}
		return ret;
	}

	/**
	 * Adds edge e to this graph such that it connects 
	 * vertex v1 to v2. 
	 * If this graph does not contain v1, v2, 
	 * or both, implementations may choose to either silently add 
	 * the vertices to the graph or throw an IllegalArgumentException.
	 * If this graph assigns edge types to its edges, the edge type of
	 * e will be the default for this graph.
	 * See Hypergraph.addEdge() for a listing of possible reasons
	 * for failure. In addition, this should fail if the vertices or edge
	 * violates any given restrictions (such as invalid IDs).
	 * Equivalent to addEdge(e, new Pair(v1, v2)).
	 * @param e the edge to be added
	 * @param v1 the first vertex to be connected
	 * @param v2 the second vertex to be connected
	 * @return true if the add is successful, false otherwise
	 * @see Hypergraph#addEdge(Object, Collection)
	 * @see #addEdge(Object, Object, Object, EdgeType)
	 */
	public boolean addEdge(E e, V v1, V v2) {
		if (graph.keySet().contains(v1) && graph.keySet().contains(v2)){
			if (getNeighbors(v1).contains(v2)){
				return false;
			}
			graph.get(v1).put(v2, e);	
			graph.get(v2).put(v1, e);
			return true;
		}
		else{
			throw new  IllegalArgumentException();
		}
	}
	
	/**
	 * Adds vertex to this graph.
	 * Fails if vertex is null or already in the graph.
	 * Also fails if the vertex violates and constraints given in
	 * the project (such as ID restrictions).
	 * 
	 * @param vertex	the vertex to add
	 * @return true if the add is successful, and false otherwise
	 * @throws IllegalArgumentException if vertex is null
	 */
	public boolean addVertex(V vertex) {
		if (graph.keySet().contains(vertex) || vertex == null){
			return false;
		}
		HashMap<V, E> temp = new HashMap<>();
		graph.put(vertex, temp);
		return true;
	}

	/**
	 * Removes edge from this graph.
	 * Fails if edge is null, or is otherwise not an element of this graph.
	 * 
	 * @param edge the edge to remove
	 * @return true if the removal is successful, false otherwise
	 */
	public boolean removeEdge(E edge) {
		if (edge == null){
			return false;
		}
	
		for (V ver: graph.keySet()){
			HashMap<V,E> map = graph.get(ver);
			Object[] key = map.keySet().toArray();
			for (Object v: key){
				if (map.get(v).equals(edge)){
					map.remove(v);
				}
			}
		}
		return true;
	}
	
	/**
	 * Removes vertex from this graph.
	 * As a side effect, removes any edges e incident to vertex if the 
	 * removal of vertex would cause e to be incident to an illegal
	 * number of vertices.  (Thus, for example, incident hyperedges are not removed, but 
	 * incident edges--which must be connected to a vertex at both endpoints--are removed.) 
	 * Fails under the following circumstances:
	 * vertex is not an element of this graph or vertex is null
	 * @param vertex the vertex to remove
	 * @return true if the removal is successful, false otherwise
	 */
	public boolean removeVertex(V vertex) {
		if (!graph.keySet().contains(vertex) || vertex == null){
			return false;
		}
		
		for (V ver: graph.keySet()){
			HashMap<V,E> map = graph.get(ver);
			Object[] key = map.keySet().toArray();
			for (Object v: key){
				if (map.keySet().contains(vertex)){
					map.remove(v);
				}
			}
		}
		graph.remove(vertex);
		return true;
	}
	
	//--------------------------------------------------------
	// testing code goes here... edit this as much as you want!
	//--------------------------------------------------------
	/**
	 * Testing purpose only. 
	 * @return a string. 
	 */
	public String toString() {
		String str = "";
		for (V ver: graph.keySet()){
			str += ver.toString();
			str += " ->> ";
			HashMap<V, E> map = graph.get(ver);
			for (V v: map.keySet()){
				str += v.toString();
				str= str +"V : E";
				str += map.get(v).toString();
				str += " -->> ";
			}
			str += "\n";
		}
		return str;
	}
	/**
	 * Testing purpose only. 
	 * @param args testing. 
	 */
	public static void main(String[] args) {
		/**
		 * Testing purpose only. 
		 */
		class Person extends ThreeTenGraphComponent {
			public Person(int id) { super(id); }
		}
		/**
		 * Testing purpose only. 
		 */
		class Cat extends ThreeTenGraphComponent {
			public Cat(int id) { super(id); }
		}
		/**
		 * Testing purpose only. 
		 */
		class IntComponent extends ThreeTenGraphComponent {
			public IntComponent(int id) { super(id); }
		}
		
		
		ThreeTenGraph<Person,Cat> graph1 = new ThreeTenGraph<>();

		ThreeTenGraph<Person,Cat> g1 = new ThreeTenGraph<>();
		for(int i = 0; i < 3; i++) {
			g1.addVertex(new Person(i));
		}
		for(Person p : g1.getVertices()) {
			g1.addEdge(new Cat((int)(Math.random()*100)), p, p);
		}
		System.out.println(g1.toString());
		for(int i = 0; i < 3; i++) {
			graph1.addVertex(new Person(i));
		}
		Person np = new Person(1);
		System.out.println(g1.getIncidentEdges(np));
		System.out.println(g1.getNeighbors(np));
		System.out.println(g1.getEdges());
		System.out.println(g1.getVertices());
		System.out.println(g1.getEdgeCount());
		System.out.println(g1.getVertexCount());
		System.out.println(g1.removeEdge(g1.findEdge(np, np)));
		System.out.println(g1.toString());

		for(Person p : graph1.getVertices()) {
			graph1.addEdge(new Cat((int)(Math.random()*100)), p, p);
		}
		//System.out.println(graph1.getIncidentEdges());
		if(graph1.getVertexCount() == 3 && graph1.getEdgeCount() == 3) {
			System.out.println("Yay 1");
		}
		
		//create a set of nodes and edges to test with
		IntComponent[] nodes = {
			new IntComponent(1), 
			new IntComponent(26), 
			new IntComponent(2), 
			new IntComponent(25), 
			new IntComponent(3), 
			new IntComponent(24), 
			new IntComponent(4), 
			new IntComponent(23), 
			new IntComponent(5), 
			new IntComponent(22)
		};
		
		IntComponent[] edges = {
			new IntComponent(10000000), 
			new IntComponent(4), 
			new IntComponent(Integer.MAX_VALUE),
			new IntComponent(Integer.MIN_VALUE), 
			new IntComponent(500), 
			new IntComponent(600000)
		};
		
		//constructs a graph
		ThreeTenGraph<IntComponent,IntComponent> graph2 = new ThreeTenGraph<>();
		for(IntComponent n : nodes) {
			graph2.addVertex(n);
		}
		graph2.addEdge(edges[0],nodes[0],nodes[1]);
		graph2.addEdge(edges[1],nodes[2],nodes[2]);
		graph2.addEdge(edges[2],nodes[3],nodes[4]);
		graph2.addEdge(edges[3],nodes[6],nodes[7]);
		graph2.addEdge(edges[4],nodes[8],nodes[9]);
		graph2.addEdge(edges[5],nodes[9],nodes[0]);
		
		
		if(graph2.containsVertex(new IntComponent(1)) && graph2.containsEdge(new IntComponent(10000000))) {
			System.out.println("Yay 2");
		}
		
		//lot more testing here...
	}
	
	//********************************************************************************
	//   YOU MAY, BUT DON'T NEED TO EDIT THINGS IN THIS SECTION
	// This is a good place to look for "optimizing" your code to be faster.
	//********************************************************************************
	
	/**
	 * Returns true if this graph's vertex collection contains vertex.
	 * Equivalent to getVertices().contains(vertex).
	 * @param vertex the vertex whose presence is being queried
	 * @return true iff this graph contains a vertex vertex
	 */
	public boolean containsVertex(V vertex) {
		return getVertices().contains(vertex);
	}
	
	/**
	 * Returns true if this graph's edge collection contains edge.
	 * Equivalent to getEdges().contains(edge).
	 * @param edge the edge whose presence is being queried
	 * @return true iff this graph contains an edge edge
	 */
	public boolean containsEdge(E edge) {
		return getEdges().contains(edge);
	}

	/**
	 * Returns true if vertex and edge 
	 * are incident to each other.
	 * Equivalent to getIncidentEdges(vertex).contains(edge) and to
	 * getIncidentVertices(edge).contains(vertex).
	 * @param vertex is a vertex. 
	 * @param edge is an edge. 
	 * @return true if vertex and edge are incident to each other
	 */
	public boolean isIncident(V vertex, E edge) {
		return getIncidentEdges(vertex).contains(edge);
	}

	/**
	 * Returns true if v1 and v2 share an incident edge.
	 * Equivalent to getNeighbors(v1).contains(v2).
	 * 
	 * @param v1 the first vertex to test
	 * @param v2 the second vertex to test
	 * @return true if v1 and v2 share an incident edge
	 */
	public boolean isNeighbor(V v1, V v2) {
		return getNeighbors(v1).contains(v2);
	}
	
	/**
	 * Returns true if v1 is a predecessor of v2 in this graph.
	 * Equivalent to v1.getPredecessors().contains(v2).
	 * @param v1 the first vertex to be queried
	 * @param v2 the second vertex to be queried
	 * @return true if v1 is a predecessor of v2, and false otherwise.
	 */
	public boolean isPredecessor(V v1, V v2) {
		return getPredecessors(v1).contains(v2);
	}
	
	/**
	 * Returns true if v1 is a successor of v2 in this graph.
	 * Equivalent to v1.getSuccessors().contains(v2).
	 * @param v1 the first vertex to be queried
	 * @param v2 the second vertex to be queried
	 * @return true if v1 is a successor of v2, and false otherwise.
	 */
	public boolean isSuccessor(V v1, V v2) {
		return getSuccessors(v1).contains(v2);
	}
	
	/**
	 * Returns the number of edges incident to vertex.  
	 * Special cases of interest:
	 * Incident self-loops are counted once.
	 * If there is only one edge that connects this vertex to
	 * each of its neighbors (and vice versa), then the value returned 
	 * will also be equal to the number of neighbors that this vertex has
	 * (that is, the output of getNeighborCount).
	 * If the graph is directed, then the value returned will be 
	 * the sum of this vertex's indegree (the number of edges whose 
	 * destination is this vertex) and its outdegree (the number
	 * of edges whose source is this vertex), minus the number of
	 * incident self-loops (to avoid double-counting).
	 * Equivalent to getIncidentEdges(vertex).size().
	 * 
	 * @param vertex the vertex whose degree is to be returned
	 * @return the degree of this node
	 * @see Hypergraph#getNeighborCount(Object)
	 */
	public int degree(V vertex) {
		return getIncidentEdges(vertex).size();
	}

	/**
	 * Returns the number of vertices that are adjacent to vertex
	 * (that is, the number of vertices that are incident to edges in vertex's
	 * incident edge set).
	 * 
	 * <p>Equivalent to getNeighbors(vertex).size().
	 * @param vertex the vertex whose neighbor count is to be returned
	 * @return the number of neighboring vertices
	 */
	public int getNeighborCount(V vertex) {
		return getNeighbors(vertex).size();
	}
	
	/**
	 * Returns the number of incoming edges incident to vertex.
	 * Equivalent to getInEdges(vertex).size().
	 * @param vertex	the vertex whose indegree is to be calculated
	 * @return  the number of incoming edges incident to vertex
	 */
	public int inDegree(V vertex) {
		return getInEdges(vertex).size();
	}
	
	/**
	 * Returns the number of outgoing edges incident to vertex.
	 * Equivalent to getOutEdges(vertex).size().
	 * @param vertex	the vertex whose outdegree is to be calculated
	 * @return  the number of outgoing edges incident to vertex
	 */
	public int outDegree(V vertex) {
		return getOutEdges(vertex).size();
	}

	/**
	 * Returns the number of predecessors that vertex has in this graph.
	 * Equivalent to vertex.getPredecessors().size().
	 * @param vertex the vertex whose predecessor count is to be returned
	 * @return  the number of predecessors that vertex has in this graph
	 */
	public int getPredecessorCount(V vertex) {
		return getPredecessors(vertex).size();
	}
	
	/**
	 * Returns the number of successors that vertex has in this graph.
	 * Equivalent to vertex.getSuccessors().size().
	 * @param vertex the vertex whose successor count is to be returned
	 * @return  the number of successors that vertex has in this graph
	 */
	public int getSuccessorCount(V vertex) {
		return getSuccessors(vertex).size();
	}
	
	/**
	 * Returns the vertex at the other end of edge from vertex.
	 * (That is, returns the vertex incident to edge which is not vertex.)
	 * @param vertex the vertex to be queried
	 * @param edge the edge to be queried
	 * @return the vertex at the other end of edge from vertex
	 */
	public V getOpposite(V vertex, E edge) {
		Pair<V> p = getEndpoints(edge);
		if(p.getFirst().equals(vertex)) {
			return p.getSecond();
		}
		else {
			return p.getFirst();
		}
	}
	
	/**
	 * Returns all edges that connects v1 to v2.
	 * If this edge is not uniquely
	 * defined (that is, if the graph contains more than one edge connecting 
	 * v1 to v2), any of these edges 
	 * may be returned.  findEdgeSet(v1, v2) may be 
	 * used to return all such edges.
	 * Returns null if v1 is not connected to v2.
	 * <br/>Returns an empty collection if either v1 or v2 are not present in this graph.
	 *  
	 * <p><b>Note</b>: for purposes of this method, v1 is only considered to be connected to
	 * v2 via a given <i>directed</i> edge d if
	 * v1 == d.getSource() && v2 == d.getDest() evaluates to true.
	 * (v1 and v2 are connected by an undirected edge u if 
	 * u is incident to both v1 and v2.)
	 * @param v1 is a vertex. 
	 * @param v2 is a vertex. 
	 * @return  a collection containing all edges that connect v1 to v2, or null if either vertex is not present
	 * @see Hypergraph#findEdge(Object, Object) 
	 */
	public Collection<E> findEdgeSet(V v1, V v2) {
		E edge = findEdge(v1, v2);
		if(edge == null) {
			return null;
		}
		
		ArrayList<E> ret = new ArrayList<>();
		ret.add(edge);
		return ret;
		
	}
	
	/**
	 * Returns true if vertex is the source of edge.
	 * Equivalent to getSource(edge).equals(vertex).
	 * @param vertex the vertex to be queried
	 * @param edge the edge to be queried
	 * @return true iff vertex is the source of edge
	 */
	public boolean isSource(V vertex, E edge) {
		return getSource(edge).equals(vertex);
	}
	
	/**
	 * Returns true if vertex is the destination of edge.
	 * Equivalent to getDest(edge).equals(vertex).
	 * @param vertex the vertex to be queried
	 * @param edge the edge to be queried
	 * @return true iff vertex is the destination of edge
	 */
	public boolean isDest(V vertex, E edge) {
		return getDest(edge).equals(vertex);
	}
	
	/**
	 * Adds edge e to this graph such that it connects 
	 * vertex v1 to v2.
	 * Equivalent to addEdge(e, new Pair of generic containing(v1, v2)).
	 * If this graph does not contain v1, v2, 
	 * or both, implementations may choose to either silently add 
	 * the vertices to the graph or throw an IllegalArgumentException.
	 * If edgeType is not legal for this graph, this method will
	 * throw IllegalArgumentException.
	 * See Hypergraph.addEdge() for a listing of possible reasons
	 * for failure.
	 * @param e the edge to be added
	 * @param v1 the first vertex to be connected
	 * @param v2 the second vertex to be connected
	 * @param edgeType the type to be assigned to the edge
	 * @return true if the add is successful, false otherwise
	 * @see Hypergraph#addEdge(Object, Collection)
	 * @see #addEdge(Object, Object, Object)
	 */
	public boolean addEdge(E e, V v1, V v2, EdgeType edgeType) {
		//NOTE: Only undirected edges allowed
		
		if(edgeType == EdgeType.DIRECTED) {
			throw new IllegalArgumentException();
		}
		
		return addEdge(e, v1, v2);
	}
	
	/**
	 * Adds edge to this graph.
	 * Fails under the following circumstances:
	 * edge is already an element of the graph 
	 * either edge or vertices is null
	 * vertices has the wrong number of vertices for the graph type
	 * vertices are already connected by another edge in this graph,
	 * and this graph does not accept parallel edges
	 * 
	 * @param edge is new edge to be added to graph. 
	 * @param vertices is vertice to be added to graph. 
	 * @return true if the add is successful, and false otherwise
	 * @throws IllegalArgumentException if edge or vertices is null.
	 */
	@SuppressWarnings("unchecked")
	public boolean addEdge(E edge, Collection<? extends V> vertices) {
		if(edge == null || vertices == null || vertices.size() != 2) {
			return false;
		}
		
		V[] vs = (V[])vertices.toArray();
		return addEdge(edge, vs[0], vs[1]);
	}

	/**
	 * Adds edge to this graph with type edge_type.
	 * Fails under the following circumstances:
	 * <ul>
	 * <li/>edge is already an element of the graph 
	 * <li/>either edge or vertices is null
	 * <li/>vertices has the wrong number of vertices for the graph type
	 * <li/>vertices are already connected by another edge in this graph,
	 * and this graph does not accept parallel edges
	 * <li/>edge_type is not legal for this graph
	 * </ul>
	 * 
	 * @param edge is an edge.
	 * @param edgetype type an an edge. 
	 * @param vertices collection of vertex. 
	 * @return true if the add is successful, and false otherwise
	 * @throws IllegalArgumentException if edge or vertices is null, or if a different vertex set in this graph is already connected by edge, or if vertices are not a legal vertex set for edge 
	 */
	@SuppressWarnings("unchecked")
	public boolean addEdge(E edge, Collection<? extends V> vertices, EdgeType edgetype) {
		if(edge == null || vertices == null || vertices.size() != 2) {
			return false;
		}
		
		V[] vs = (V[])vertices.toArray();
		return addEdge(edge, vs[0], vs[1], edgetype);
	}
	
	/**
	 * Returns the number of edges of type edge_type in this graph.
	 * @param edgetype the type of edge for which the count is to be returned
	 * @return the number of edges of type edge_type in this graph
	 */
	public int getEdgeCount(EdgeType edgetype) {
		if(edgetype == EdgeType.UNDIRECTED) {
			return getEdgeCount();
		}
		return 0;
	}
	
	/**
	 * Returns the collection of edges in this graph which are of type edge_type.
	 * @param edgetype the type of edges to be returned
	 * @return the collection of edges which are of type edge_type, or null if the graph does not accept edges of this type
	 * @see EdgeType
	 */
	public Collection<E> getEdges(EdgeType edgetype) {
		if(edgetype == EdgeType.UNDIRECTED) {
			return getEdges();
		}
		return null;
	}
	
	/**
	 * Returns the number of vertices that are incident to edge.
	 * For hyperedges, this can be any nonnegative integer; for edges this
	 * must be 2 (or 1 if self-loops are permitted). 
	 * 
	 * <p>Equivalent to getIncidentVertices(edge).size().
	 * @param edge the edge whose incident vertex count is to be returned
	 * @return the number of vertices that are incident to edge.
	 */
	public int getIncidentCount(E edge) {
		return getIncidentVertices(edge).size();
	}
	
	/**
	 * If directed_edge is a directed edge in this graph, returns the source; 
	 * otherwise returns null. 
	 * The source of a directed edge d is defined to be the vertex for which  
	 * d is an outgoing edge.
	 * directed_edge is guaranteed to be a directed edge if 
	 * its EdgeType is DIRECTED. 
	 * @param directededge direction of edge.
	 * @return  the source of directed_edge if it is a directed edge in this graph, or null otherwise
	 */
	public V getSource(E directededge) {
		return null;
	}

	/**
	 * If directed_edge is a directed edge in this graph, returns the destination; 
	 * otherwise returns null. 
	 * The destination of a directed edge d is defined to be the vertex 
	 * incident to d for which  
	 * d is an incoming edge.
	 * directed_edge is guaranteed to be a directed edge if 
	 * its EdgeType is DIRECTED. 
	 * @param directededge direction of an edge. 
	 * @return  the destination of directed_edge if it is a directed edge in this graph, or null otherwise
	 */
	public V getDest(E directededge) {
		return null;
	}

	/**
	 * Returns a Collection view of the predecessors of vertex 
	 * in this graph.  A predecessor of vertex is defined as a vertex v 
	 * which is connected to 
	 * vertex by an edge e, where e is an outgoing edge of 
	 * v and an incoming edge of vertex.
	 * @param vertex	the vertex whose predecessors are to be returned
	 * @return  a Collection view of the predecessors of vertex in this graph
	 */
	public Collection<V> getPredecessors(V vertex) {
		return getNeighbors(vertex);
	}
	
	/**
	 * Returns a Collection view of the successors of vertex 
	 * in this graph.  A successor of vertex is defined as a vertex v 
	 * which is connected to 
	 * vertex by an edge e, where e is an incoming edge of 
	 * v and an outgoing edge of vertex.
	 * @param vertex	the vertex whose predecessors are to be returned
	 * @return  a Collection view of the successors of  vertex in this graph
	 */
	public Collection<V> getSuccessors(V vertex) {
		return getNeighbors(vertex);
	}
	
	/**
	 * Returns a Collection view of the incoming edges incident to vertex
	 * in this graph.
	 * @param vertex	the vertex whose incoming edges are to be returned
	 * @return  a Collection view of the incoming edges incident to vertex in this graph
	 */
	public Collection<E> getInEdges(V vertex) {
		return getIncidentEdges(vertex);
	}
	
	/**
	 * Returns a Collection view of the outgoing edges incident to vertex
	 * in this graph.
	 * @param vertex	the vertex whose outgoing edges are to be returned
	 * @return  a Collection view of the outgoing edges incident  to vertex in this graph
	 */
	public Collection<E> getOutEdges(V vertex) {
		return getIncidentEdges(vertex);
	}

	/**
	 * Returns the endpoints of edge as a Pair.
	 * @param edge the edge whose endpoints are to be returned
	 * @return the endpoints (incident vertices) of edge
	 */
	@SuppressWarnings("unchecked")
	public Pair<V> getEndpoints(E edge) {
		Object[] ends = getIncidentVertices(edge).toArray();
		return new Pair<>((V)ends[0],(V)ends[1]);
	}
	
	//********************************************************************************
	//   DO NOT EDIT ANYTHING BELOW THIS LINE (except to edit/fix the JavaDocs)
	//********************************************************************************
	
	/**
	 * Returns a {@code Factory} that creates an instance of this graph type.
	 * @param <V> the vertex type for the graph factory
	 * @param <E> the edge type for the graph factory
	 * @return a new graph.
	 */
	public static <V extends ThreeTenGraphComponent, E extends ThreeTenGraphComponent> Factory<Graph<V,E>> getFactory() { 
		return new Factory<Graph<V,E>> () {
			public Graph<V,E> create() {
				return new ThreeTenGraph<>();
			}
		};
	}
	/**
	 * Returns a {@code Factory} that creates an instance of this graph type.
	 * @param <V> the vertex type for the graph factory
	 * @param <E> the edge type for the graph factory
	 * @return a new graph. 
	 */
	public static <V extends ThreeTenGraphComponent, E extends ThreeTenGraphComponent> Factory<UndirectedGraph<V,E>> getUndirectedFactory() { 
		return new Factory<UndirectedGraph<V,E>> () {
			public UndirectedGraph<V,E> create() {
				return new ThreeTenGraph<>();
			}
		};
	}
	
	/**
	 * Returns the edge type of edge in this graph.
	 * @param edge is a parameter. 
	 * @return the EdgeType of edge, or null if edge has no defined type
	 */
	public EdgeType getEdgeType(E edge) {
		return EdgeType.UNDIRECTED;
	}
	
	/**
	 * Returns the default edge type for this graph.
	 * 
	 * @return the default edge type for this graph
	 */
	public EdgeType getDefaultEdgeType() {
		return EdgeType.UNDIRECTED;
	}
}
