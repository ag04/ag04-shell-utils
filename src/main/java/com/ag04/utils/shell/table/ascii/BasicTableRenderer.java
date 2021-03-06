package com.ag04.utils.shell.table.ascii;

import com.ag04.utils.shell.table.TableDataSource;
import com.ag04.utils.shell.table.TableRenderer;
import de.vandermeer.asciitable.AT_ColumnWidthCalculator;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;

import java.util.List;

/**
 * @author mgulan on 03.08.18.
 */
public class BasicTableRenderer implements TableRenderer {
    private static final AT_ColumnWidthCalculator DEFAULT_CWC = new CWC_LongestLine();
    public static final int DEFAULT_TABLE_WIDTH=100;

    private AT_ColumnWidthCalculator cwc;
    private int width;

    public BasicTableRenderer() {
        this(DEFAULT_CWC, DEFAULT_TABLE_WIDTH);
    }

    public BasicTableRenderer(int tableWidth) {
        this(DEFAULT_CWC, tableWidth);
    }

    public BasicTableRenderer(AT_ColumnWidthCalculator cwc, int tableWidth) {
        this.cwc = (cwc != null ? cwc : DEFAULT_CWC);
        this.width = tableWidth;
    }

    @Override
    public String render(List<String> headers, TableDataSource tableDataSource) {
        AsciiTable at = new AsciiTable();
        at.getRenderer().setCWC(cwc);

        at.addRule();
        at.addRow(headers);
        at.addRule();
        for (int i = 0; i < tableDataSource.size(); i++) {
            at.addRow(tableDataSource.getRowElements(i));
            at.addRule();
        }
        return at.render(width);
    }


    // ----- get / set methods -------------------------------------------------------------

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setCwc(AT_ColumnWidthCalculator cwc) {
        this.cwc = cwc;
    }

    public AT_ColumnWidthCalculator getCwc() {
        return cwc;
    }
}
