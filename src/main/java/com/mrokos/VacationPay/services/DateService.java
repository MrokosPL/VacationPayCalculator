package com.mrokos.VacationPay.services;

import com.mrokos.VacationPay.VacationPayApplication;
import com.mrokos.VacationPay.dtos.VacationPayDto;
import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;
import de.jollyday.ManagerParameter;
import de.jollyday.ManagerParameters;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class DateService {


    public LocalDate dateConverter(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }

    public Long workingDayCheck(VacationPayDto vacationPayDto) {
        long workingDays = ChronoUnit.DAYS.between(dateConverter(vacationPayDto.getStartDate()), dateConverter(vacationPayDto.getEndDate())) + 1; //Кол-во дней
        ManagerParameter parameters = ManagerParameters.create(HolidayCalendar.RUSSIA);
        HolidayManager manager = HolidayManager.getInstance(parameters);

        LocalDate startDate = dateConverter(vacationPayDto.getStartDate());
        LocalDate endDate = dateConverter(vacationPayDto.getEndDate());
//Проверка на выходной/праздник
        while (!startDate.isAfter(endDate)) {
            DayOfWeek dayOfWeek = startDate.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY || manager.isHoliday(startDate)) {
                workingDays--;
            }
            startDate = startDate.plusDays(1);
        }
        return workingDays;
    }
}
