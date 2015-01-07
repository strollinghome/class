package db61b;

import java.util.Hashtable;

/** A collection of Tables, indexed by name.
 *  @author Carlos A. Flores-Arellano.*/

class Database {
    /** An empty database. */
    public Database() {
        _contents = new Hashtable<String, Table>();
    }

    /** Return the Table whose name is NAME stored in this database, or null
     *  if there is no such table. */
    public Table get(String name) {
        return _contents.get(name);
    }

    /** Set or replace the table named NAME in THIS to TABLE.  TABLE and
     *  NAME must not be null, and NAME must be a valid name for a table. */
    public void put(String name, Table table) {
        if (name == null || table == null) {
            throw new IllegalArgumentException("null argument");
        }
        _contents.put(name, table);
    }

    /** Contains the tables added to this instance of database, key is the NAME
     * and the value is the TABLE.*/
    private Hashtable<String, Table> _contents;
}
