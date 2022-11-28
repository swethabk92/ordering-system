package com.looped.foodordering.vo;

import java.math.BigDecimal;

/**

 * User: looped
 * DateTime: 2022/11/28 16:56
 */
public class OrderDetailVo {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Long num;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }
}
