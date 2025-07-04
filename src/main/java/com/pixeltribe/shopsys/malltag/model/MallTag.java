package com.pixeltribe.shopsys.malltag.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pixeltribe.shopsys.product.model.Product;

@Getter
@Setter
@Entity
@Table(name = "mall_tag")
public class MallTag {
    @Id
    @Column(name = "MALL_TAG_NO", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 25)
    @NotNull
    @Column(name = "MALL_TAG_NAME", nullable = false, length = 25)
    private String mallTagName;

    @OneToMany(mappedBy = "mallTagNo")
    @JsonIgnore
    private Set<Product> products = new LinkedHashSet<>();
    
    public MallTag() {
    	
    }

}