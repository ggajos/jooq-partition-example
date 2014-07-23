package ie.uws.example;

import ie.uws.example.db.h2.tables.Author_1;
import ie.uws.example.db.h2.tables.Author_2;
import org.jooq.InsertQuery;
import org.junit.Assert;
import org.junit.Test;

import static ie.uws.example.Partitioner.*;
import static ie.uws.example.Partitioner.forPartition;
import static ie.uws.example.db.h2.tables.Author.AUTHOR;

public class PartitionTest extends AbstractDatabaseAwareTest {

    @Test
    public void testTruncateAddAndSelectWithoutPartitionerHelper() throws Exception {
        // truncate
        dsl.delete(Author_1.AUTHOR_1).execute();
        dsl.delete(Author_2.AUTHOR_2).execute();
        // add
        InsertQuery query1 = dsl.insertQuery(Author_1.AUTHOR_1);
        query1.addValue(Author_1.AUTHOR_1.ID, 1);
        query1.addValue(Author_1.AUTHOR_1.LAST_NAME, "Nowak");
        query1.execute();
        InsertQuery query2 = dsl.insertQuery(Author_2.AUTHOR_2);
        query2.addValue(Author_2.AUTHOR_2.ID, 1);
        query2.addValue(Author_2.AUTHOR_2.LAST_NAME, "Nowak");
        query2.execute();
        // select
        Assert.assertTrue(dsl.selectFrom(Author_1.AUTHOR_1)
                .where(Author_1.AUTHOR_1.LAST_NAME.eq("Nowak"))
                .fetch().size() == 1);
        Assert.assertTrue(dsl.selectFrom(Author_2.AUTHOR_2)
                .where(Author_2.AUTHOR_2.LAST_NAME.eq("Nowak"))
                .fetch().size() == 1);
    }

    @Test
    public void testTruncateAddAndSelectWithPartitionerHelper() throws Exception {
        // truncate
        for(int i=1; i<=2; i++) {
            dsl.delete(forPartition(i).table(AUTHOR)).execute();
        }
        // add
        for(int i=1; i<=2; i++) {
            Builder partitioner = forPartition(i);
            InsertQuery query = dsl.insertQuery(partitioner.table(AUTHOR));
            query.addValue(partitioner.field(AUTHOR.ID), 1);
            query.addValue(partitioner.field(AUTHOR.LAST_NAME), "Nowak");
            query.execute();
        }
        // select
        for(int i=1; i<=2; i++) {
            Builder partitioner = forPartition(i);
            Assert.assertTrue(dsl.selectFrom(partitioner.table(AUTHOR))
                    .where(partitioner.field(AUTHOR.LAST_NAME).eq("Nowak"))
                    .fetch()
                    .size() == 1);
        }
    }
}
