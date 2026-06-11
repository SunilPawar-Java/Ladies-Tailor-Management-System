package com.ltr.entity.orders;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ltr.entity.products.Products;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProduct extends BodyMeasurement {

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "product_id")
    private Products product;
}
