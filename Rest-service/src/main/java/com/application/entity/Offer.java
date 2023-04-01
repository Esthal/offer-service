package com.application.entity;

import com.application.entity.Enum.Answer;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="offers")
public class Offer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String message;
    private int amount;
    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean alreadyAnswered;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Answer answer;

    @ManyToOne()
    @JsonManagedReference()
    private User user;

    private LocalDate dateOfAnswer;

}
