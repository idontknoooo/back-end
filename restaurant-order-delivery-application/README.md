# INTRO 
You can browse menu, make order and payment using this application.

# HOW TO PERFORM WORKFLOW

## Start MicroServices
In `restaurant-order-delivery-application` directory
1. run `docker-compose up`
2. run `mvn clean install`
3. run `java -jar platform/eureka/target/*.jar`
4. run `java -jar platfrom/hystrix-dashboard/target/*.jar`
5. run `java -jar restaurant/target/*.jar`
6. run `java -jar order/target/*.jar`
7. run `java -jar payment/target/*.jar`

## Monitoring using Eureka, RabbitMQ and Hystrix-Dashboard
1. Eureka Server: 
    - In Chrome, open `localhost:8761`
2. RabbitMQ:
    - In Chrome, open `localhost:15672`
3. Hystrix-Dashboard:
    - In Chrome, open `localhost:7979`
    - Type `localhost:8081/hystrix.stream` and click `monitor stream`, since only `Order` use Hystrix (its port is 8081)
    
## HTTP requests
Use POSTMAN chrome plugin
1. Post Restaurant Information:
    - In `localhost:8080`, select method `POST`
    - Copy & Paste `restaurant.json` file content to `body` tab in POSTMAN
    - click `SEND`
    - **NOTE**: 
        - We are expected to see 201 response. 
        - `restaurant.json` is in `restaurant` module.
        - Switch method to `GET` to see restaurant information.
        
2. Post Order Information(process order):
    - In `localhost:8081`, select method `POST`
    - Copy & Paste `order.json` file content to `body` tab in POSTMAN
    - click `SEND`
    - **NOTE**: 
        - We are expected to see 201 response. 
        - `order.json` is in `order` module.
        - Switch method to `GET` to see order information
        - Before you pay the order, you cannot see the `estimatedDeliveryTime` attribute in Order.
        
3. Post Payment Information(Pay order):
    - In `localhost:8082`, select method `POST`
    - Copy & Paste `payment.json` file content to `body` tab in POSTMAN
    - click `SEND`
    - **NOTE**: 
        - We are expected to see 201 response. 
        - `payment.json` is in `payment` module.
        - Switch method to `GET` to see order information
        - Once your payment is passed, you can go back to `localhost:8081` to see `estimatedDeliveryTime`
        - Also, order will be marked as `isPaid:true`, when payment passed
