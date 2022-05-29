import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Car;
import dto.User;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String args[]){
        System.out.println("main");

        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();
        user.setName("김혜린");
        user.setAge(10);

        Car car1 = new Car();
        car1.setName("K5");
        car1.setCarNumber("11가 1111");
        car1.setType("sedan");

        Car car2 = new Car();
        car2.setName("Q5");
        car2.setCarNumber("22가 2222");
        car2.setType("SUV");

        List<Car> carList= Arrays.asList(car1,car2);
        //[Car{name='K5', carNumber='11GA 1111', type='sedan'}, Car{name='Q5', carNumber='22GA 2222', type='SUV'}]

        System.out.println(user);
    }
}
