package com.bolingcavalry.hellojib.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author liaohua1
 * @date 2020/5/26 11:36
 */
@Data
@Entity
@Table(name = "goods")
public class Goods {

    @Id
    private Long goodsId;

    private String goodsName;

    private Long goodsType;

}
