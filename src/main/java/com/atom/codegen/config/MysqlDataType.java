package com.atom.codegen.config;

/**
 * @author lime
 */
public enum MysqlDataType {
    /**
     * mysql 数据类型
     */
    BIT("Boolean", "java.lang.Boolean", 1),
    TINYINT("Boolean", "java.lang.Boolean", 2),
    BOOL("Boolean", "java.lang.Boolean", 3),
    BOOLEAN("Boolean", "java.lang.Boolean", 4),
    SMALLINT("Integer", "java.lang.Integer", 5),
    MEDIUMINT("Integer", "java.lang.Integer", 6),
    INT("Integer", "java.lang.Integer", 7),
    INTEGER("Integer", "java.lang.Integer", 8),
    BIGINT("Long", "java.lang.Long", 9),
    FLOAT("Float", "java.lang.Float", 10),
    DOUBLE("Double", "java.lang.Double", 11),
    DECIMAL("BigDecimal", "java.math.BigDecimal", 12),
    DATE("Date", "java.sql.Date", 13),
    DATETIME("Timestamp", "java.sql.Timestamp", 14),
    TIMESTAMP("Timestamp", "java.sql.Timestamp", 15),
    TIME("Time", "java.sql.Time", 16),
    YEAR("String", "java.lang.String", 17),
    CHAR("String", "java.lang.String", 18),
    VARCHAR("String", "java.lang.String", 19),
    BINARY("Byte[]", "java.lang.Byte[]", 20),
    VARBINARY("Byte[]", "java.lang.Byte[]", 21),
    TINYBLOB("Byte[]", "java.lang.Byte[]", 22),
    TINYTEXT("String", "java.lang.String", 23),
    BLOB("Byte[]", "java.lang.Byte[]", 24),
    TEXT("String", "java.lang.String", 25),
    MEDIUMBLOB("Byte[]", "java.lang.Byte[]", 26),
    MEDIUMTEXT("String", "java.lang.String", 27),
    LONGBLOB("Byte[]", "java.lang.Byte[]", 28),
    LONGTEXT("String", "java.lang.String", 29),
    ENUM("String", "java.lang.String", 30),
    SET("String", "java.lang.String", 31);

    private String javaType;
    private String packageName;

    private int index;

    MysqlDataType(String javaType, String packageName, int index) {
        this.javaType = javaType;
        this.packageName = packageName;
        this.index = index;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getJavaType() {
        return javaType;
    }


    public int getIndex() {
        return index;
    }

}
