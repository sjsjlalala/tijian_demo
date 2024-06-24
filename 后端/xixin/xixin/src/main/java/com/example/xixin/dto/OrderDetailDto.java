package com.example.xixin.dto;

import java.time.LocalDate;

/**
 * @projectName: xixin
 * @package: com.example.xixin.dto
 * @className: OrderDetailDto
 * @author: moki
 * @description: TODO
 * @date: 2024/6/17 15:42
 * @version: 1.0
 */
public class OrderDetailDto {
    private Integer smId;
    private Integer hpId;

    private LocalDate orderDate;

    private Integer orderId;

    public Integer getSmId() {
        return smId;
    }

    public void setSmId(Integer smId) {
        this.smId = smId;
    }

    public Integer getHpId() {
        return hpId;
    }

    public void setHpId(Integer hpId) {
        this.hpId = hpId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
