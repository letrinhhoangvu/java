/*
 * $Id: GraphComponent.java,v 1.1.34.1 2006/04/12 19:31:02 ofung Exp $
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

package components.components;


import components.model.Graph;
import components.model.Node;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 * Component wrapping a {@link Graph} object that is pointed at by the
 * a value binding reference expression.  This component supports
 * the processing of a {@link ActionEvent} that will toggle the expanded
 * state of the specified {@link Node} in the {@link Graph}.
 */

public class GraphComponent extends UICommand {

    private static Log log = LogFactory.getLog(GraphComponent.class);


    public GraphComponent() {

        // set a default actionListener to expand or collapse a node
        // when a node is clicked.
        Class signature[] = {ActionEvent.class};
        setActionListener(FacesContext.getCurrentInstance().getApplication()
                          .createMethodBinding(
                              "#{GraphBean.processGraphEvent}",
                              signature));

    }


    /**
     * <p>Return the component family for this component.</p>
     */
    public String getFamily() {

        return ("Graph");

    }

}
