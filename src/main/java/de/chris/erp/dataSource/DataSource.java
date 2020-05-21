package de.chris.erp.dataSource;

/**
 * Interface für alle Datenquellen.
 */
public interface DataSource
{
    public javax.sql.DataSource getDataSource();
}
