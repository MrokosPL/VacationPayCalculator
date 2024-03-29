package com.mrokos.VacationPay.dtos;

import lombok.Data;

import java.time.LocalDate;
@Data
public class VacationPayDto {
    private Double averageSalary;
    private String startDate;
    private String endDate;
}
