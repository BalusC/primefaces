/*
 * The MIT License
 *
 * Copyright (c) 2009-2025 PrimeTek Informatics
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.primefaces.component.column;

import org.primefaces.component.celleditor.CellEditor;
import org.primefaces.util.ComponentTraversalUtils;
import org.primefaces.util.LangUtils;

import java.io.IOException;
import java.util.List;

import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UINamingContainer;
import jakarta.faces.context.FacesContext;

public class Column extends ColumnBase {

    public static final String COMPONENT_TYPE = "org.primefaces.component.Column";

    @Override
    public CellEditor getCellEditor() {
        return ComponentTraversalUtils.firstChildRendered(CellEditor.class, this);
    }

    @Override
    public boolean isDynamic() {
        return false;
    }

    @Override
    public String getColumnKey() {
        return getClientId();
    }

    @Override
    public String getColumnKey(UIComponent parent, String rowIndex) {
        char separator = UINamingContainer.getSeparatorChar(getFacesContext());
        return getColumnKey().replace(parent.getId() + separator + rowIndex + separator, parent.getId() + separator);
    }

    @Override
    public List getElements() {
        return getChildren();
    }

    @Override
    public int getElementsCount() {
        return getChildCount();
    }

    @Override
    public void renderChildren(FacesContext context) throws IOException {
        int childCount = getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                UIComponent child = getChildren().get(i);
                child.encodeAll(context);
            }
        }
    }

    @Override
    public String getHeaderText() {
        String headerText = super.getHeaderText();
        if (headerText == null) {
            String field = getField();
            if (LangUtils.isNotBlank(field)) {
                headerText = LangUtils.toCapitalCase(field);
            }
        }
        return headerText;
    }
}
