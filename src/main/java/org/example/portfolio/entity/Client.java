package org.example.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Подвох №1: ФИО в одной строке
    private String fio;

    // Подвох №2: Регион в одной строке (не вынесен в справочник)
    private String region;

    // Подвох №3: Телефон в свободном формате
    private String phone;

    private String inn;
    private String snils;

    // Статус AML-проверки (null — еще не проверен)
    @Column(name = "aml_status")
    private Boolean amlStatus;
}