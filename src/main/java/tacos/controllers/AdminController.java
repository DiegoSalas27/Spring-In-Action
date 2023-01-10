package tacos.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.services.OrderAdminService;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final OrderAdminService adminService;

    public AdminController(OrderAdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String showAdminPage() {
        return "admin";
    }

    @PostMapping("/deleteOrders")
    public String deleteAllOrders() {
        log.info("Deleting: {} Orders", adminService.getAllOrders());
        adminService.deleteAllOrders();
        return "redirect:/admin";
    }
}
