package com.example.shortener;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.identity.IdentityColumnSupport;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;
import org.hibernate.type.StandardBasicTypes;

import java.sql.Types;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        registerColumnType(Types.BIT, "integer");
        registerColumnType(Types.TINYINT, "tinyint");
        registerColumnType(Types.SMALLINT, "smallint");
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.BIGINT, "bigint");
        registerColumnType(Types.FLOAT, "float");
        registerColumnType(Types.REAL, "real");
        registerColumnType(Types.DOUBLE, "double");
        registerColumnType(Types.NUMERIC, "numeric");
        registerColumnType(Types.DECIMAL, "decimal");
        registerColumnType(Types.CHAR, "char");
        registerColumnType(Types.VARCHAR, "varchar");
        registerColumnType(Types.LONGVARCHAR, "longvarchar");
        registerColumnType(Types.DATE, "date");
        registerColumnType(Types.TIME, "time");
        registerColumnType(Types.TIMESTAMP, "timestamp");
        registerColumnType(Types.BINARY, "blob");
        registerColumnType(Types.VARBINARY, "blob");
        registerColumnType(Types.LONGVARBINARY, "blob");
        registerColumnType(Types.BLOB, "blob");
        registerColumnType(Types.CLOB, "clob");
        registerColumnType(Types.BOOLEAN, "integer");

        registerFunction("concat", new StandardSQLFunction("concat", StandardBasicTypes.STRING));
        registerFunction("lower", new StandardSQLFunction("lower", StandardBasicTypes.STRING));
        registerFunction("upper", new StandardSQLFunction("upper", StandardBasicTypes.STRING));
        registerFunction("substring", new StandardSQLFunction("substr", StandardBasicTypes.STRING));
        registerFunction("length", new StandardSQLFunction("length", StandardBasicTypes.LONG));
        registerFunction("trim", new StandardSQLFunction("trim", StandardBasicTypes.STRING));
    }

    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new SQLiteIdentityColumnSupport();
    }

    public static class SQLiteIdentityColumnSupport extends IdentityColumnSupportImpl {
        @Override
        public boolean supportsIdentityColumns() {
            return true;
        }

        @Override
        public String getIdentitySelectString(String table, String column, int type) {
            return "select last_insert_rowid()";
        }

        @Override
        public String getIdentityColumnString(int type) {
            return "integer primary key autoincrement";
        }
    }
}