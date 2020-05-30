package fr.biblioc.bibliocreservation.custom;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

/**
 * Classe Abstraite permettant de récuperer la data source
 */
public abstract class AbstractDao {

    @Inject
    private DataSource dataSource;

    protected DataSource getDataSource() {
        return dataSource;
    }
}