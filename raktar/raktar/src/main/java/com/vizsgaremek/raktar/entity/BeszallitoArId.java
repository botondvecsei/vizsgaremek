package com.vizsgaremek.raktar.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class BeszallitoArId implements Serializable {

    private Integer beszallitoId;
    private Integer termekId;


}