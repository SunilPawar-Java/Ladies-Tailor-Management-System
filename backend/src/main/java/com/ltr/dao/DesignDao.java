package com.ltr.dao;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DesignDao {
    private Long id;
    private String clothMaterial;
    private String clothColor;
    private String frontNeck;
    private String backNeck;
    private String fitting;
    private String sleeve;
    private String openingClosing;
}
