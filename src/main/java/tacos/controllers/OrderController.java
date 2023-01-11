package tacos.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;
import tacos.domain.TacoOrder;
import tacos.domain.User;
import tacos.repository.OrderRepository;


@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
	private OrderRepository orderRepository;
	
	private OrderProps props;

	public OrderController(
			OrderRepository orderRepository,
			OrderProps props) {
		this.orderRepository = orderRepository;
		this.props = props;
	}

	@GetMapping("/current")
	public String orderForm(@AuthenticationPrincipal User user,
							@ModelAttribute TacoOrder tacoOrder) {
		if (tacoOrder.getDeliveryName() == null) {
			tacoOrder.setDeliveryName(user.getFullname());
		}
		if (tacoOrder.getDeliveryStreet() == null) {
			tacoOrder.setDeliveryStreet(user.getStreet());
		}
		if (tacoOrder.getDeliveryCity() == null) {
			tacoOrder.setDeliveryCity(user.getCity());
		}
		if (tacoOrder.getDeliveryState() == null) {
			tacoOrder.setDeliveryState(user.getState());
		}
		if (tacoOrder.getDeliveryZip() == null) {
			tacoOrder.setDeliveryZip(user.getZip());
		}

		return "orderForm";
	}
	
	@GetMapping
	public String ordersForUser(@AuthenticationPrincipal  User user, Model model) {
		Pageable pageable = PageRequest.of(0, props.getPageSize());
		model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));
		
		return "orderList";
	}

	@PostMapping
	public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
		if (errors.hasErrors()) {
			return "orderForm";
		}

		order.setUser(user);
		orderRepository.save(order);
		sessionStatus.setComplete(); // cleans up session attributes

		return "redirect:/";
	}
}
