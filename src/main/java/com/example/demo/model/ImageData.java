package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private String name;
private String type;
@Column(length = 50000000)
private byte [] imageData;


}
