package com.example.demo.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralAdvertisement {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String advertisement;

@UpdateTimestamp
@Temporal(TemporalType.TIMESTAMP)
private Date updatedAt;

@PreUpdate
protected void onUpdate() {
                updatedAt = new Date();
        }
}