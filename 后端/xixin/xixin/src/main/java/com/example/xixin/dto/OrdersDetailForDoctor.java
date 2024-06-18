package com.example.xixin.dto;

import com.example.xixin.entity.Cidetailedreport;
import com.example.xixin.entity.Overallresult;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @projectName: xixin
 * @package: com.example.xixin.dto
 * @className: OrdersDetailForDoctor
 * @author: moki
 * @description: TODO
 * @date: 2024/6/18 11:34
 * @version: 1.0
 */
public class OrdersDetailForDoctor {
    private String orderId;
    private LocalDate orderDate;

    private Map<String, List<Cidetailedreport>> map;

    private List<Overallresult> overallresults;

    public List<Overallresult> getOverallresults() {
        return overallresults;
    }

    public void setOverallresults(List<Overallresult> overallresults) {
        this.overallresults = overallresults;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Map<String, List<Cidetailedreport>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<Cidetailedreport>> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "OrdersDetailForDoctor{" +
                "orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", map=" + map +
                '}';
    }
}
