package com.retorno.review.configs.security.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "roles_jwt")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleJwt {

    @Id
    @GeneratedValue
    @Column(name = "role_jwtid")
    private Long roleJwtId;

    private String name;

    public enum Values {

        ADMIN(1L),
        BASIC(2L);

        long rolejwtId;

        Values(long rolejwtId) {
            this.rolejwtId = rolejwtId;
        }

        public long getRoleId() {
            return rolejwtId;
        }
    }
}
