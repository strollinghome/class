package make;

import graph.LabeledGraph;
import graph.DirectedGraph;

/** A directed, labeled subtype of Graph that describes dependencies between
 *  targets in a Makefile.
 *  @author Carlos A. Flores-Arellano.
 */
class Depends extends LabeledGraph<Rule, Void> {

    /** An empty dependency graph. */
    Depends() {
        super(new DirectedGraph());
    }
}
