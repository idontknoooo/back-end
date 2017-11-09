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

1. Use @Slf4j for log. @Slf4j is an annotation from lombok
2. RestTemplate: for HTTP request. FULLLPATH=name+path
3. Change Menu & Dish POST Method
