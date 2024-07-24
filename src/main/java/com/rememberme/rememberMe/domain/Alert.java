package com.rememberme.rememberMe.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Class {@code domain/Alert} represent the entity of alert table.
 * <p>
 *  It contain entity`s attributes and your respective types.
 * </p>
 *
 * @author Eduardo Perucci
 * @version 1.0
 * @since 2024-07-22
 */

@Table(name = "tb_alert")
@Entity
@Getter
@Setter
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type_alert;

    public Long getAlertId() {
        return id;
    }

    public void setAlertId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type_alert;
    }

    public void setTypeAlert(String type_alert) {
        this.type_alert = type_alert;
    }

    public enum Values {
        PENDING(1L),
        OK(2L);

        Long id;

        Values(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
    }
}
