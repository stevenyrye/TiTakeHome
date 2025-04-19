package com.ti.takehome.config;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.Dialect;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.sql.ast.SqlAstTranslatorFactory;
import org.hibernate.sql.ast.spi.StandardSqlAstTranslatorFactory;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.descriptor.jdbc.spi.JdbcTypeRegistry;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        super(DatabaseVersion.make(3, 42));
     }

    @Override
    public SqlAstTranslatorFactory getSqlAstTranslatorFactory() {
        return new StandardSqlAstTranslatorFactory();
    }
    
    @Override
    public void contributeTypes(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        JdbcTypeRegistry jdbcTypeRegistry = typeContributions.getTypeConfiguration().getJdbcTypeRegistry();

        jdbcTypeRegistry.addDescriptor(SqlTypes.INTEGER, jdbcTypeRegistry.getDescriptor(SqlTypes.INTEGER));
        jdbcTypeRegistry.addDescriptor(SqlTypes.VARCHAR, jdbcTypeRegistry.getDescriptor(SqlTypes.VARCHAR));
        jdbcTypeRegistry.addDescriptor(SqlTypes.BOOLEAN, jdbcTypeRegistry.getDescriptor(SqlTypes.BOOLEAN));
    }
    
}