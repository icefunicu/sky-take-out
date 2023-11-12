package com.sky.service;

import com.sky.vo.*;

import java.time.LocalDate;

public interface ReportService {
    /*
     * 统计指定时间区间在内的营业额数据
     * */

    TurnoverReportVO getTurnoverStatistics(LocalDate begin,LocalDate end);

    /*
     * 统计指定时间区间在内的用户数据
     * */
    UserReportVO getUserStatistics(LocalDate begin, LocalDate end);
    /*
     * 统计指定时间区间在内的订单数据
     * */
    OrderReportVO getOrdersStatistics(LocalDate begin, LocalDate end);
    /*
     * 统计指定时间区间在内的销售Top10商品数据
     * */
    SalesTop10ReportVO getTop10(LocalDate begin, LocalDate end);
}
