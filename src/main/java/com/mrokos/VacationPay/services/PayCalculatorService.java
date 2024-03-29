package com.mrokos.VacationPay.services;
import com.mrokos.VacationPay.dtos.VacationPayDto;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
public class PayCalculatorService {
    private final DateService dateService;

    public PayCalculatorService(DateService dateService) {
        this.dateService = dateService;
    }

    public Double calculate(VacationPayDto vacationPay){
        return vacationPay.getAverageSalary()/ 29.3 * dateService.workingDayCheck(vacationPay);
    }
}
