package com.fundamentosplatzi.springboot.fundamentos.Configuration;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithPropertiesImplement;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.sql.DataSource;


//aqui van a ir configurasiones anotaciones etc...
@Configuration
//con propertysource traemos nuestros archivos properties en este caso estan en la carpeta resources
@PropertySource("classpath:connection.properties")
@EnableConfigurationProperties(UserPojo.class)// la clase user.pojo es la que se va a representar como unas propiedades dentro de nuestra aplicacion
public class GeneralConfiguration {
    @Value("${value.name}")
    private String name;
    @Value("${value.apellido}")
    private String apellido;
    @Value("${value.random}")
    private String random;


    //utilizamps  la anotacion @value para llamar los valores de nuestro properties
    @Value("${jdbc.url}")
    private String jdbcurl;
    // en estas variables tenemos el valor de driver del archivo propiedades
    @Value("${driver}")
    private String driver;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;


    @Bean
    public MyBeanWithProperties function(){
        return new MyBeanWithPropertiesImplement(name, apellido);
    }
    //construccion de un datasource o base de datos virtual


    @Bean
    @ExceptionHandler
    public DataSource dataSource(){
        // se crea un objeto de tipo DataSourceBuilder
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        // se accede al metodo y se le pasa el nombre correspondiente
        dataSourceBuilder.driverClassName(driver);
        //se accede al metodo de url y se le para la url
        dataSourceBuilder.url(jdbcurl);
        //se accede al metodo Username y se le pasa el nombre que deseamos
        dataSourceBuilder.username(username );
        //se accede al metodo password y creamos la contraseña
        dataSourceBuilder.password(password);

        //retornamos esta implementacion
        return dataSourceBuilder.build();
    }

}
