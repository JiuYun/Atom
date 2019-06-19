package com.atom.codegen.start;

import com.atom.codegen.config.DataSourceConfig;
import com.atom.codegen.config.PackageConfig;

public interface Start {

    /***
     * 配置包信息
     *
     * @return
     */
    public PackageConfig packageConfig();

    /***
     * 配置数据源信息
     *
     * @return
     */
    public DataSourceConfig dataSourceConfig();







}
