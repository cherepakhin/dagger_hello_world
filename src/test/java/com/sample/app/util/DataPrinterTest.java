package com.sample.app.util;

import com.sample.app.interfaces.DataSource;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class DataPrinterTest extends TestCase {

    @Test
    public void testGetDataFromSQL() {
        DataSource sourceFromSql = new DataSource() {
            @Override
            public String read() {
                return "fromSql";
            }
        };

        DataPrinter dp = new DataPrinter(sourceFromSql, null);

        try {
            assertEquals("fromSql", dp.getDataFromSQL());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testGetDataFromFile() {
        DataSource sourceFromFile = new DataSource() {
            @Override
            public String read() {
                return "fromFile";
            }
        };

        DataPrinter dp = new DataPrinter(null, sourceFromFile);

        try {
            assertEquals("fromFile", dp.getDataFromFile());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testGetDataFrom_NULL_SQL() {
        DataPrinter dp = new DataPrinter(null, null);
        Exception exception = assertThrows(Exception.class, () -> {
            dp.getDataFromSQL();
        });

        assertEquals("sqlSource is null", exception.getMessage());
    }

    @Test
    public void testGetDataFrom_NULL_File() {
        DataPrinter dp = new DataPrinter(null, null);
        Exception exception = assertThrows(Exception.class, () -> {
            dp.getDataFromFile();
        });

        assertEquals("fileSource is null", exception.getMessage());
    }

}