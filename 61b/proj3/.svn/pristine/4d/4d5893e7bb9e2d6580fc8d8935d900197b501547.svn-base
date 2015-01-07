package graph;

import java.util.ArrayList;

/* See restrictions in Graph.java. */

/** Represents a general unlabeled directed graph whose vertices are denoted by
 *  positive integers. Graphs may have self edges.
 *
 *  @author Carlos A. Flores-Arellano.
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int inDegree(int v) {
        if (super.contains(v)) {
            int inDegree;
            inDegree = 0;
            Iteration<int[]> allEdges = super.edges();
            for (int[] edge : allEdges) {
                if (edge[1] == v) {
                    inDegree = inDegree + 1;
                }
            }
            return inDegree;
        }
        return 0;
    }

    @Override
    public int predecessor(int v, int k) {
        Iteration<int[]> allEdges = super.edges();
        ArrayList<Integer> predecessors = new ArrayList<Integer>();
        for (int[] edge : allEdges) {
            if (edge[1] == v) {
                predecessors.add(edge[0]);
            }
        }
        if (k < predecessors.size()) {
            return predecessors.get(k);
        }
        return 0;
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        Iteration<int[]> allEdges = super.edges();
        ArrayList<Integer> predecessors = new ArrayList<Integer>();
        for (int[] edge : allEdges) {
            if (edge[1] == v) {
                predecessors.add(edge[0]);
            }
        }
        return Iteration.iteration(predecessors);
    }

}
