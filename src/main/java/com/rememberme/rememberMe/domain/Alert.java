
package com.rememberme.rememberMe.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

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

    private String title;

    private String type_alert;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    private Task task;

    public Alert() {}

    public enum Values {
        PENDING(1L),
        OK(2L),
        TODAY(3L);

        Long id;

        Values(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
    }
}




