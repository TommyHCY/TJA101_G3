package com.pixeltribe.shopsys.favoriteProduct.model;

import com.pixeltribe.membersys.vo.Member;
import com.pixeltribe.shopsys.product.model.Product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "favorite_product")
public class FavoriteProduct {
    @Id
    @Column(name = "FAV_PRO_NO", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MEM_NO", nullable = false)
    private Member memNo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRO_NO", nullable = false)
    private Product proNo;

}