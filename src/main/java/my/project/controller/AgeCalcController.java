package my.project.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@Controller
public class AgeCalcController {

    // Default birth date and today's date
    private static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.of(2003, 8, 24);
    private static final LocalDate DEFAULT_TODAY_DATE = LocalDate.now();

    @GetMapping("/")
    public String showCalculator(Model model) {
        model.addAttribute("birthDate", DEFAULT_BIRTH_DATE);  // Default birthdate
        model.addAttribute("todayDate", DEFAULT_TODAY_DATE);  // Default current date
        return "calculate";
    }

    @PostMapping("/calculate")
    public String calculateAge(
            @RequestParam(value = "birthDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate,
            @RequestParam(value = "todayDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate todayDate,
            Model model) {

        // If birthDate or todayDate is not provided, use default values
        if (birthDate == null) birthDate = DEFAULT_BIRTH_DATE;
        if (todayDate == null) todayDate = DEFAULT_TODAY_DATE;

        // Validation to check if birthDate is greater than todayDate
        if (birthDate.isAfter(todayDate)) {
            model.addAttribute("errorMessage", "Birth date should not be greater than today's date.");
            model.addAttribute("birthDate", birthDate);
            model.addAttribute("todayDate", todayDate);
            return "calculate";
        }

        // Calculate age
        Period age = Period.between(birthDate, todayDate);
        model.addAttribute("years", age.getYears());
        model.addAttribute("months", age.getMonths());
        model.addAttribute("days", age.getDays());

        // Calculate next birthday
        LocalDate nextBirthday = birthDate.plusYears(age.getYears() + 1);
        Period timeToNextBirthday = Period.between(todayDate, nextBirthday);
        model.addAttribute("nextBirthdayDay", nextBirthday.getDayOfWeek());
        model.addAttribute("monthsToBirthday", timeToNextBirthday.getMonths());
        model.addAttribute("daysToBirthday", timeToNextBirthday.getDays());

        // Summary details
        long totalDays = ChronoUnit.DAYS.between(birthDate, todayDate);
        long totalWeeks = totalDays / 7;
        long totalHours = totalDays * 24;
        long totalMinutes = totalHours * 60;

        model.addAttribute("totalDays", totalDays);
        model.addAttribute("totalWeeks", totalWeeks);
        model.addAttribute("totalHours", totalHours);
        model.addAttribute("totalMinutes", totalMinutes);

        // Pass dates back to form, ensuring that any changes made by the user are reflected
        model.addAttribute("birthDate", birthDate);
        model.addAttribute("todayDate", todayDate);

        return "calculate";
    }
}
