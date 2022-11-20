package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.Usuario;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner{

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	private ComponentDependency componentDependency;
	private MyBean myBean;//AGREGACION DE INTERFACES
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;

	private UserService userService;


	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean mybean,
								  MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties,
								  UserPojo userPojo, UserRepository userRepository, UserService userService){//metodo constructor de la clase, recibe como parametro la dependencia para poderla inyectar

		this.componentDependency = componentDependency;
		this.myBean = mybean; //llamamos la propiedad "mybean"
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;//inyectamos esta clase como dependencia
		this.userRepository = userRepository;
		this.userService = userService;

	}
	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	//ejecuta en la aplicaciontodo lo que nosotros queramos, en este caso sera la implementacion de la dependencia
	public void run(String... args) {
		//ejemplosAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
		saveWhitErrorTransactional();
	}
	private void saveWhitErrorTransactional(){
		Usuario test1 = new Usuario("testTransactional1","TestTransactional1@test.com", LocalDate.now());
		Usuario test2 = new Usuario("testTransactional2","TestTransactional2@test.com", LocalDate.now());
		Usuario test3 = new Usuario("testTransactional3","TestTransactional3@test.com", LocalDate.now());
		Usuario test4 = new Usuario("testTransactional4","TestTransactional4@test.com", LocalDate.now());
		List<Usuario> usuarios = Arrays.asList(test1, test2, test3, test4);

		userService.saveTransactional(usuarios);

		userService.getAllUsers()
				.forEach(usuario -> LOGGER.info("usuario por metodo transactional: " + usuario));
	}
	private void getInformationJpqlFromUser(){
		/*LOGGER.info("el usuario pasado por metodo findbyuseremail es: " +
				userRepository.findByUserEmail("lau@test.com")
						.orElseThrow(()-> new RuntimeException("no se encontro el usuario")));
		//le pasamos user por lo que es una lista de nombres
		//con sort.by los escogemos por id
		//con descending() los ordenamos descendentemente
		userRepository.findAndSort("user", Sort.by("id").descending())
				.forEach(LOGGER::info);

		System.out.println("entrando al metodo findbyname");

		userRepository.findByName("john").forEach(LOGGER::info);

		System.out.println("paso el primero metodo findbyname");

		userRepository.findByName("John")
				.stream()
				.forEach(usuario -> LOGGER.info("el usuario es: "+ usuario));

		System.out.println("entrando al metodo email y nombre");

		LOGGER.info("el usuario encontrado por medio del metodo findbyNameAndEmail   " + userRepository.findByNameAndEmail("sergio","sergio@test.com")
				.orElseThrow(()-> new RuntimeException("Usuario no encontradoaaaaaa")));

		userRepository.findByNameLike("%u%")
				.forEach(usuario -> LOGGER.info("usuario por metodo namelike " + usuario));

		userRepository.findByNameOrEmail(null, "luis@test.com")
				.forEach(usuario -> LOGGER.info("usuario encontrado por metodo or " + usuario));

		userRepository.findByNameOrEmail("user1", null)
				.forEach(usuario -> LOGGER.info("usuario encontrado por el 2d0 metodo or " + usuario));

		userRepository.findByBirthDateBetween(LocalDate.of(2022, 3, 15),
						LocalDate.of(2023, 6, 10 ))
				.forEach(usuario -> LOGGER.info("usuario encontrado por between" + usuario));

		System.out.println("entrando al metodo containing");
		userRepository.findByNameContainingOrderByDesc("%user%")
				.forEach(usuario -> LOGGER.info("usuario encontrado con like y orderby" + usuario));

		LOGGER.info("el usuario encontrado por el metodo getAllbybirthandemail es: " + userRepository
				.getAllByBirthDateAndEmail(LocalDate.of(2021,8, 17),
					"juan@test.com")
						.orElseThrow(()->
							new RuntimeException("no se encontro el usuario apartir del metodo getAllbybirthandemail: ")));*/

	}

	private void saveUsersInDataBase(){
		Usuario user1 = new Usuario("John ","john@test.com", LocalDate.of(2022,3, 15));
		Usuario user2 = new Usuario("juan ","juan@test.com",LocalDate.of(2021,8, 17));
		Usuario user3 = new Usuario( "user1","lau@test.com", LocalDate.of(2020,7, 19));
		Usuario user4 = new Usuario("user2 ", "karen@test.com",LocalDate.of(2023, 6, 10));
		Usuario user5 = new Usuario( "user3 ","luis@test.com", LocalDate.of(2019, 4, 13));
		Usuario user6 = new Usuario("sergio ", "sergio@test.com", LocalDate.of(2018, 5, 15));

		List<Usuario> list = Arrays.asList(user1, user2, user3, user4, user5, user6 );
		//list.stream().forEach(userRepository::save);
		userRepository.saveAll(list);

	}
	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();//llamamos  a su implementacion y trae su metodo
		myBeanWithDependency.printWithDependency();//llamamos a la propiedad y a su implementacion
		System.out.println(myBeanWithProperties.function());//funcion de nombre concatenado con el apellido
		System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword() + "-" + userPojo.getAge());

		try{
			int value = 10/0;
			LOGGER.debug("el valor es: " + value);

		}catch (Exception e) {
			LOGGER.error("esto es un error del aplicativo" + Arrays.toString(e.getStackTrace()) + e.getLocalizedMessage());
		}
	}

}
