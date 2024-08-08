


package com.rememberme.rememberMe.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Class {@code domain/Folder} represent the entity of folder table.
 * <p>
 *  It contain entity`s attributes and your respective types.
 * </p>
 *
 * @author Eduardo Perucci
 * @version 1.0
 * @since 2024-07-22
 */

@Table(name = "tb_folder")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private List<byte[]> files;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    public boolean IsNameGraterThan50(String value) {
        return value.length() > 50;
    }
}


