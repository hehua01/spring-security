package com.example.springsecurityjwt.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Date 2022/3/2 15:46
 * @Description
 */
@Entity
@Table(name = "sys_user")
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    //逗号分隔的角色
    private String roles;
}
