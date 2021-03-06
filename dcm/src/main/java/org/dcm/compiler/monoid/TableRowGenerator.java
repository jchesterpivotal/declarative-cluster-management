/*
 * Copyright © 2018-2020 VMware, Inc. All Rights Reserved.
 *
 * SPDX-License-Identifier: BSD-2
 */

package org.dcm.compiler.monoid;

import org.dcm.IRColumn;
import org.dcm.IRPrimaryKey;
import org.dcm.IRTable;

import java.util.Optional;

public final class TableRowGenerator extends Qualifier {
    private final IRTable table;

    public TableRowGenerator(final IRTable table) {
        this.table = table;
    }

    public IRTable getTable() {
        return table;
    }

    public Optional<IRColumn> getUniquePrimaryKeyColumn() {
        return table.getPrimaryKey().map(IRPrimaryKey::getPrimaryKeyFields)
                .filter(l -> l.size() == 1)
                .map(l -> l.get(0));
    }

    @Override
    public String toString() {
        return "TableRowGenerator{" +
                "table=" + table.getName() +
                '}';
    }

    @Override
    <T, C> T acceptVisitor(final MonoidVisitor<T, C> visitor, final C context) {
        return visitor.visitTableRowGenerator(this, context);
    }
}