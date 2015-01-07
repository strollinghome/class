package db61b;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/** A single row of a database.
 *  @author Carlos A. Flores-Arellano.*/

class Row {
    /** A Row whose column values are DATA.  The array DATA must not be altered
     *  subsequently. */
    Row(String[] data) {
        _data = data;
    }

    /** Given M COLUMNS that were created from a sequence of Tables
     *  [t0,...,tn] as well as ROWS [r0,...,rn] that were drawn from those
     *  same tables [t0,...,tn], constructs a new Row containing M values,
     *  where the ith value of this new Row is taken from the location given
     *  by the ith COLUMN (for each i from 0 to M-1).
     *  The value for the ith column of this new row will come from the ith row
     *  in ROWS, using the ith entry of COLUMNS as an effective column index.
     *  More specifically, if _table is the Table number corresponding to
     *  COLUMN i, then the ith value of the newly created Row should come from
     *  ROWS[_table].
     *
     *  Even more specifically, the ith value of the newly created Row should
     *  be equal to ROWS[_table].get(_column), where _column is the column
     *  number in ROWS[_table] corresponding to COLUMN i.
     *
     *  There is a method in the Column class that you'll need to use, see
     *  {@link db61b.Column#getFrom}).  you're wondering why this looks like a
     *  potentially clickable link it is! Just not in source. You might
     *  consider converting this spec to HTML using the Javadoc command.
     */
    Row(List<Column> columns, Row... rows) {
        String columnName;
        ArrayList<String> data = new ArrayList<>();
        for (Column col : columns) {
            columnName = col.getFrom(rows);
            data.add(columnName);
        }
        _data = data.toArray(new String[data.size()]);
    }

    /** Return my number of columns. */
    int size() {
        return _data.length;
    }

    /** Return the value of my Kth column.  Requires that 0 <= K < size(). */
    String get(int k) {
        return _data[k];
    }

    @Override
    public String toString() {
        String rowString = "";
        int k = 0;
        if (_data.length > 0) {
            while (k < _data.length - 1) {
                rowString += _data[k] + ",";
                k = k + 1;
            }
            rowString += _data[k];
        }
        return rowString;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            Row rowObj = (Row) obj;
            int k = 0;
            while (k < size()) {
                if (size() != rowObj.size() || !get(k).equals(rowObj.get(k))) {
                    return false;
                }
                k = k + 1;
            }
        } catch (ClassCastException e) {
            throw new DBException(
                    "Input must be an instance of the class Row.");
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(_data);
    }

    /** Contents of this row. */
    private String[] _data;
}
