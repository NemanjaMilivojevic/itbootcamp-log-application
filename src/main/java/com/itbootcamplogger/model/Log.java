package com.itbootcamplogger.model;

import com.itbootcamplogger.LogTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Log")
@Setter
@Getter
@NoArgsConstructor
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String message;
    private LogTypeEnum logType;

}
