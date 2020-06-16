package com.main.app.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Setter
public abstract class AbstractEntity<I> implements Serializable {


    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    protected Long id;

    private static final long serialVersionUID = 1L;



    @LastModifiedDate
    private Instant dateUpdated;

    private Instant dateDeleted;

    private boolean deleted;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant dateCreated;
}
