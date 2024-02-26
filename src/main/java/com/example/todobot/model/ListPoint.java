package com.example.todobot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Data
@NoArgsConstructor
@SQLDelete(sql = "UPDATE list_points SET is_deleted=true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "list_points")
public class ListPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String pointMessage;
    @Column(nullable = false)
    private boolean isDone = false;
    @Column(nullable = false)
    private boolean isDeleted = false;
    @Column(nullable = false)
    private Long pointNumber;
    private LocalDateTime dateAdded;
    private LocalDateTime dateDone;
}
