package com.application.entity;

import com.application.entity.Enum.Answer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean alreadyAnswered;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Answer answer;

    @JsonIgnore
    @ManyToOne()
    private User user;

    private LocalDate dateOfAnswer;

}
