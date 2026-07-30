package com.ltr.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mainCategory; // upper wear , bottom wear
    private String locationCategory;  //
    private String color; // (Base color name used for grouping, e.g., "White")
    private String occasion; // (Target event tags, e.g., ["Casual", "Brunch", "Party Wear"])
    private String season; // (Temporal suitability tags, e.g., ["Summer", "Spring"])
    private Integer stock; // (Global inventory counter for availability checks)
    private Double discountPercentage; // (Auto calculated reduction tag value)
    private String fabric; // (Dominant material, e.g., "Polyester", "Cotton", "Linen")
    private String neckline; // (e.g., "Square Neck", "Sweetheart", "V-Neck")
    private String sleeveLength; //  (e.g., "Long Sleeve", "Short Sleeve", "Sleeveless")
    private String sleeveType; // (e.g., "Balloon", "Puff", "Regular", "Bell")
    private String lengthType; // (e.g., "Crop", "Regular", "Longline")
    private String fitType; //  (Overall silhouette, e.g., "Corset Fit", "Slim Fit", "Oversized")
    private String hemline; //  (Bottom edge cut, e.g., "Hanky Hem", "Straight", "Asymmetric")
    private String closureType; // (Fastening mechanism, e.g., "Back Zipper", "Pull-On", "Buttons")
    @Column(nullable = false)
    private String itemName; // name
    @Column(nullable = false)
    private String itemType; // type
    @Column(nullable = false)
    private Double price; // price
    private String description; // more details
    private String imageName;
    private String imageType;
    @Lob
    @Column(nullable = false)
    private byte[] image;
}
