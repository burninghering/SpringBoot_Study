import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.Car;
import dto.User;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String args[]) throws JsonProcessingException {
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

        List<Car> carList = Arrays.asList(car1, car2);
        //[Car{name='K5', carNumber='11GA 1111', type='sedan'}, Car{name='Q5', carNumber='22GA 2222', type='SUV'}]

        user.setCars(carList);

        //오브젝트 맵퍼로 json을 바꿔보자
        String json = objectMapper.writeValueAsString(user); //string으로 바꾸는 기능
        System.out.println(json);


        //파싱해보자~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();

        System.out.println("name : " + _name);
        System.out.println("name : " + _age);
        //위와 같은 부분들은 KEY-VALUE값인데,

        //cars는 json의 새로운 노드, 즉 배열이기 때문에 아래처럼 못 가져온다
//        String _list = jsonNode.get("cars").asText();
//        System.out.println(_list);

        JsonNode cars = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode) cars; //cars는 배열의 노드기 때문에
        //object mapper의 클래스인 ArrayNode를 사용, (ArrayNode)cars -> 타입 변환해서 강제 파싱

        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {
        });
        //covertValue ->  여러가지 object를 json이 아닌 우리가 원하는 클래스로 바꿀 수 있음
        // TypeReference에는 우리가 원하는 제네릭 타입 <List<Car>> 넣어주기
        //arrayNode라는 오브젝트를 받아서, 우리가 원하는 타입 (List의 car)라는 List형태로 변환

        System.out.println(_cars);

        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name", "Steve");
        objectNode.put("age", 23);

        System.out.println(objectNode.toPrettyString());
        //toPrettyString는 json을 예쁘게 출력함!
    }
}
