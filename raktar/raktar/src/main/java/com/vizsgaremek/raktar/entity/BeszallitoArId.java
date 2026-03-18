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

    public BeszallitoArId(Integer beszallitoId, Integer termekId) {
        this.beszallitoId = beszallitoId;
        this.termekId = termekId;
    }

    public BeszallitoArId() {

    }


    public Integer getBeszallitoId() {
        return beszallitoId;
    }

    public void setBeszallitoId(Integer beszallitoId) {
        this.beszallitoId = beszallitoId;
    }

    public Integer getTermekId() {
        return termekId;
    }

    public void setTermekId(Integer termekId) {
        this.termekId = termekId;
    }
}