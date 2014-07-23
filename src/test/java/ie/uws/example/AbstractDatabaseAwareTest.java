package ie.uws.example;

import ie.uws.example.Resources;
import org.jooq.DSLContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import static org.jooq.impl.DSL.using;

public abstract class AbstractDatabaseAwareTest {
    static Connection connection;
    static DSLContext dsl;

    @BeforeClass
    public static void start() throws Exception {
        Properties p = new Properties();
        p.load(Resources.class.getResourceAsStream("config.properties"));

        connection = DriverManager.getConnection(p.getProperty("db.url"), p.getProperty("db.username"), p.getProperty("db.password"));
        dsl = using(connection);
    }

    @AfterClass
    public static void end() throws Exception {
        connection.close();
    }

}
