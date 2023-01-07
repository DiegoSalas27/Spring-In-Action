//package tacos;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import tacos.repository.IngredientRepository;
//import tacos.repository.OrderRepository;
//
//@WebMvcTest // <1>
//public class HomeControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc; // <2>
//
//	@MockBean
//	private IngredientRepository ingredientRepository;
//
//	@MockBean
//	private OrderRepository orderRepository;
//
//	@Test
//	public void testHomePage() throws Exception {
//		mockMvc.perform(get("/")) // <3>
//				.andExpect(status().isOk()) // <4>
//				.andExpect(view().name("home")) // <5>
//				.andExpect(content().string( // <6>
//						containsString("Welcome to...")));
//	}
//
//}