package graph;

import java.util.Hashtable;

/* See restrictions in Graph.java. */

/** A partial implementation of ShortestPaths that contains the weights of
 *  the vertices and the predecessor edges.   The client needs to
 *  supply only the two-argument getWeight method.
 *  @author Carlos A. Flores-Arellano.
 */
public abstract class SimpleShortestPaths extends ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public SimpleShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public SimpleShortestPaths(Graph G, int source, int dest) {
        super(G, source, dest);
        _weights = new Hashtable<>();
        _predecessors = new Hashtable<>();
    }

    @Override
    public double getWeight(int v) {
        if (_weights.containsKey(v)) {
            return _weights.get(v);
        }
        return Double.POSITIVE_INFINITY;
    }

    @Override
    protected void setWeight(int v, double w) {
        _weights.put(v, w);
    }

    @Override
    public int getPredecessor(int v) {
        if (_predecessors.containsKey(v) && _predecessors.get(v) != null) {
            return _predecessors.get(v);
        }
        return 0;
    }

    @Override
    protected void setPredecessor(int v, int u) {
        _predecessors.put(v, u);
    }

    /** Keeps track of the weigth in each node. */
    private Hashtable<Integer, Double> _weights;

    /** Keeps track of predecessors. */
    private Hashtable<Integer, Integer> _predecessors;

}
