package com.trevis.startup.example;

// import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// import com.trevis.startup.example.model.UserRole;
import com.trevis.startup.example.services.DepartmentService;
import com.trevis.startup.example.services.UserService;

@SpringBootTest
public class UserTest {
    
    @Autowired
    UserService userService;
    
    @Autowired
    DepartmentService departmentService;

    @Test
	void userTest() {
		// UserRole role = new UserRole();
		// role.setId(2l);
		// role.setRole("Manager");
		// userService.create("Lore", departmentService.getAll().get(0), role);// criando user

		// userService.updatePassword(Long.valueOf(0), "Senha");// colocando a senha para o user
		// assertEquals(userService.get("Lore").getDepartment(), departmentService.getAll().get(0));
		// assertEquals(userService.get("Lore").getLogin(), "Lore");
		// assertEquals(userService.get("Lore").getPassword(), "Senha");// verificando se as informações batem
	}

}