package com.thriteen.bgd.usersync;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// MP接口扫描
@MapperScan("com.thriteen.bgd.usersync.dao")
public class UsersyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersyncApplication.class, args);
	}

}
