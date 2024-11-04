/*
package my.project.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Controller
public class AgeCalculatorController {

    @GetMapping("/")
    public String showCalculator(Model model) {
        // Default birthdate and today's date
        model.addAttribute("birthDate", "2003-08-24");  // default birthdate
        model.addAttribute("todayDate", LocalDate.now().toString());  // default to current date
        return "calculate";
    }

    @PostMapping("/calculate")
    public String calculateAge(
            @RequestParam("birthDate") String birthDateStr,
            @RequestParam("todayDate") String todayDateStr,
            Model model
    ) {
        LocalDate birthDate = LocalDate.parse(birthDateStr);
        LocalDate todayDate = LocalDate.parse(todayDateStr);

        // Calculate age
        Period age = Period.between(birthDate, todayDate);
        int years = age.getYears();
        int months = age.getMonths();
        int days = age.getDays();

        // Calculate time until next birthday
        LocalDate nextBirthday = birthDate.plusYears(years + 1);
        Period untilNextBirthday = Period.between(todayDate, nextBirthday);

        // Additional details for summary
        int totalDays = (int) Period.between(birthDate, todayDate).toTotalMonths() * 30 + days; // approx. calculation
        long hours = totalDays * 24L;
        long minutes = hours * 60L;
        long weeks = totalDays / 7;

        // Add data to the model
        model.addAttribute("birthDate", birthDateStr);
        model.addAttribute("todayDate", todayDateStr);
        model.addAttribute("years", years);
        model.addAttribute("months", months);
        model.addAttribute("days", days);
        model.addAttribute("nextBirthdayDay", nextBirthday.getDayOfWeek());
        model.addAttribute("monthsUntilNext", untilNextBirthday.getMonths());
        model.addAttribute("daysUntilNext", untilNextBirthday.getDays());
        model.addAttribute("totalDays", totalDays);
        model.addAttribute("totalWeeks", weeks);
        model.addAttribute("totalHours", hours);
        model.addAttribute("totalMinutes", minutes);

        return "calculator";
    }


}

*/
