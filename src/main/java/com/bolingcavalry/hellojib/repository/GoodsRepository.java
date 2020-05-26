package com.bolingcavalry.hellojib.repository;

import com.bolingcavalry.hellojib.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author liaohua1
 * @date 2020/5/26 11:37
 */
public interface GoodsRepository extends JpaRepository<Goods, Long> {

    List<Goods> findAllByGoodsIdBetween(Long goodsId1, Long goodsId2);

    List<Goods> findAllByGoodsIdIn(List<Long> goodsIds);

}
