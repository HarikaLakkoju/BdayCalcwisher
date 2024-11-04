package my.project.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller

public class FormController {
    @GetMapping("/form")
    public String showForm(Model model) {
        return "submissionForm"; // Thymeleaf template name without extension
    }

    @PostMapping("/submissionForm")
    public String handleSubmit(
            @RequestParam("email") String email,
            @RequestParam("time") String time,
            @RequestParam("content") String content,
            @RequestParam("files") List<MultipartFile> files,
            RedirectAttributes redirectAttributes) {

        // Process the form data here
        System.out.println("Email: " + email);
        System.out.println("Time: " + time);
        System.out.println("Content: " + content);
        System.out.println("Number of files uploaded: " + files.size());

        // Optionally add messages to be displayed to the user
        redirectAttributes.addFlashAttribute("message", "Form submitted successfully!");

        return "redirect:/form"; // Redirect back to the form or another page
    }
}
