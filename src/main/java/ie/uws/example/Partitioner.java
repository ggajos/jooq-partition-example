package ie.uws.example;

import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.TableField;

import static org.jooq.impl.DSL.fieldByName;
import static org.jooq.impl.DSL.tableByName;

public class Partitioner {

    public static Builder forPartition(int partitionId) {
        return new Builder(partitionId);
    }

    public static class Builder {
        private final int partitionId;

        public Builder(int partitionId) {
            this.partitionId = partitionId;
        }

        public Table<Record> table(Table<?> table) {
            return tableByName(table.getName() + "_" + partitionId);
        }

        public <T> Field<T> field(TableField<? extends Record, T> field) {
            return fieldByName(field.getType(), field.getName());
        }

    }

}

