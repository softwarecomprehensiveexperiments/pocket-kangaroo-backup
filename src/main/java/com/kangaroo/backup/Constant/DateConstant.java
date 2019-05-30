package com.kangaroo.backup.Constant;

import java.util.Date;

public interface DateConstant {

    /**
     * 统一未设定时的日期
     */
    Date NULL_DATE = new Date(0);

    /**
     * 统一无穷大日期
     */
    Date INFINITE_DATE = new Date(Long.MAX_VALUE);
}

