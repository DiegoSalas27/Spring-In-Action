package tacos;

import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import tacos.controllers.DesignTacoController;
import tacos.domain.Ingredient;
import tacos.domain.Taco;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DesignTacoController.class)
public class DesignTacoControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	private List<Ingredient> ingredients;
	
	private Taco design;
}
