# Topic
A restaurant delivery application, which can browser restaurant and order food & make payment. Just like Yelp or any other meal ordering service.
# Architecture

## Microservices

### Restaurant   
Classes:  
- Restaurant: @Table(name = "MENU") @Document
  - restaurantId: String
  - restaurantName: String
  - menu: Menu (one-to-one)
  - description: String
  - address1: String
  - address2: String
  - city: String
  - state: String
  - zipCode: String

- Menu: @Table(name = "MENU")
  - menuId: String
  - dishes: List<Dish> (one-to-many | mapped by 'menu')
  - 

- Dish: @Table(name = "DISH") 
  - dishId: String (id is unique, and won't be same betweeen restaurant)
  - dishName: String
  - dishDescription: String
  - dishPrice: float
  - (menu: Menu (many-to-one | fetch = FetchType.LAZY))
  - 

Rest Controller:    

Service:  CURD operation
### Order
Classes:   
- Order:
  - orderId: String
  - orderTimestamp: Date
  - orderDescription: String
  - estimatedDeliveryTime: Date
  - paymentId: String
  - isCancellel: boolean
  - isPayed: boolean
  - totalBalance: double
  - orderDetail: List<Dish>

- Dish: 
  - dishId: String (id is unique, and won't be same betweeen restaurant)
  - dishName: String
  - dishDescription: String
  - dishPrice: double 
### Payment
Classes:  
- Payment:   
  - paymentId: String
  - orderId: String
  - paymentTimestamp: Date
  - isPaid: boolean
  - customerName: String
  - cardNumber: String
  - expirationMonth: String
  - expirationYear: String
  - securityCode: String
  - billingAddress1: String
  - billingAddress2: String
  - billingCity: String
  - billingState: String
  - billingZipCode: String
  - totalBalance: double

### NOTE
1. Domain Classes: Design of entities
2. Domain Interfaces(Repository): Inherited from PagingAndSortingRepository<> to geti Default CURD method of it
3. Service Interfaces: Customized CURB method headers, even Repository offers CRUD methods, we still implement new service method based on them. Good convention.
4. Service Classes(Interface Implementation): Implementations for interfaces
5. RestController: Using method from Service(Repository) to realize HTTP request mapping and other functionalities
6. In restController, there can be same url map to same type of HTTP requests. e.g. localhost:/8080/restaurant for GETBYID and GETBYNAME. Use restaurant/{id} instead to avoid conflict
7. RestTemplate is used when microserviceA wants to send microserviceB object and call Rest method in microserviceB. e.g. food-order-service wants to process order, which offers in order-payment-service.
8. Spring Cloud Stream is used when services want to communicate with MessageQueue. Service send object to MessageQ from 'source' to 'sink'. Source is 'publisher' and sink is 'consumer'.
Source:  
  - Dependencies: spring-cloud-stream and spring-cloud-stream-binder-rabbit
  - application.yml: cloud.stream.bindings.output: locations
  - @EnableBinding(Source.class)
  - private MessageChannel output
  - this.output.send(MessageBuilder.withPayload(positionInfo).build()) // BuildMessage and send
Sink:  
  - application.yml: Any port is fine, not like RestTemplate. cloud.stream.bindings.input: locations // binding on same queue as 'source'
  - @ServiceActivator(inputChannel = Sink.INPUT)
  - EnableBinding(Sink.class)
  - 

9. Payload: Actual message of transimitted data, without header or metadata.
{
    "data": {
        "message": "Hello, world!"
    }
}
In above, "Hello, world!" is the payload, all other is called protocol overhead.
10. MessageQueue: Uses to reduce load balancing, asynchronization and avoid blocking.

1. Use @Slf4j for log. @Slf4j is an annotation from lombok
2. RestTemplate: for HTTP request. FULLLPATH=name+path
3. Change Menu & Dish POST Method
4. Hystrix fallback to a assigned method when current method gone wrong.
