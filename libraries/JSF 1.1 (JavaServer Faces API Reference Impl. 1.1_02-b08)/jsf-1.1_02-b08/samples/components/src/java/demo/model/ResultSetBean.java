/*
 * $Id: ResultSetBean.java,v 1.1.38.1 2006/04/12 19:31:13 ofung Exp $
 */

/*
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the License at
 * https://javaserverfaces.dev.java.net/CDDL.html or
 * legal/CDDLv1.0.txt. 
 * See the License for the specific language governing
 * permission and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL
 * Header Notice in each file and include the License file
 * at legal/CDDLv1.0.txt.    
 * If applicable, add the following below the CDDL Header,
 * with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * [Name of File] [ver.__] [Date]
 * 
 * Copyright 2006 Sun Microsystems Inc. All Rights Reserved
 */

package demo.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Backing file bean for <code>ResultSet</code> demo.</p>
 */

public class ResultSetBean {

    private static Log log = LogFactory.getLog(ResultSetBean.class);

    private List list = null;


    public ResultSetBean() {
    }


    public List getList() {
        // Construct a preconfigured customer list lazily.
        if (list == null) {
            list = new ArrayList();
            for (int i = 0; i < 1000; i++) {
                list.add(new CustomerBean(Integer.toString(i),
                                          "name_" + Integer.toString(i),
                                          "symbol_" + Integer.toString(i), i));
            }
        }
        return list;
    }


    public void setList(List newlist) {
        this.list = newlist;
    }

    // -------------------------------------------------------- Bound Components

    /**
     * <p>The <code>UIData</code> component representing the entire table.</p>
     */
    private UIData data = null;


    public UIData getData() {
        return data;
    }


    public void setData(UIData data) {
        this.data = data;
    }


    // ---------------------------------------------------------- Action Methods


    /**
     * <p>Scroll directly to the first page.</p>
     */
    public String first() {
        scroll(0);
        return (null);

    }


    /**
     * <p>Scroll directly to the last page.</p>
     */
    public String last() {
        scroll(data.getRowCount() - 1);
        return (null);

    }


    /**
     * <p>Scroll forwards to the next page.</p>
     */
    public String next() {
        int first = data.getFirst();
        scroll(first + data.getRows());
        return (null);

    }


    /**
     * <p>Scroll backwards to the previous page.</p>
     */
    public String previous() {
        int first = data.getFirst();
        scroll(first - data.getRows());
        return (null);

    }


    /**
     * <p>Scroll to the page that contains the specified row number.</p>
     *
     * @param row Desired row number
     */
    public void scroll(int row) {

        int rows = data.getRows();
        if (rows < 1) {
            return; // Showing entire table already
        }
        if (row < 0) {
            data.setFirst(0);
        } else if (row >= data.getRowCount()) {
            data.setFirst(data.getRowCount() - 1);
        } else {
            data.setFirst(row - (row % rows));
        }

    }


    /**
     * Handles the ActionEvent generated as a result of clicking on a
     * link that points a particular page in the result-set.
     */
    public void processScrollEvent(ActionEvent event) {
        int currentRow = 1;
        if (log.isTraceEnabled()) {
            log.trace("TRACE: ResultSetBean.processScrollEvent ");
        }
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent component = event.getComponent();
        Integer curRow = (Integer) component.getAttributes().get("currentRow");
        if (curRow != null) {
            currentRow = curRow.intValue();
        }
        // scroll to the appropriate page in the ResultSet.
        scroll(currentRow);
    }


}
