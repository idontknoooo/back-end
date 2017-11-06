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

### Payment
