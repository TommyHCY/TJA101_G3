package com.pixeltribe.shopsys.product.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductEditDTO {

	private Integer id;
    private String proName;
    private Integer proPrice;
    private String proStatus;
    private String proVersion;
    private LocalDate proDate;
    private byte[] proCover;
    private String proDetails;
    private String proInclude;
    private Integer mallTagNo;
    private Character proIsmarket;
	
}
