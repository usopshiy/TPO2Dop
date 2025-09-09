package usopshiy.tpo2dop;

import org.springframework.boot.SpringApplication;

public class TestTpo2DopApplication {

	public static void main(String[] args) {
		SpringApplication.from(Tpo2DopApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
