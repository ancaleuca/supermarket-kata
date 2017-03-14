## Implementation of supermarket kata found [here] (http://codekata.com/kata/kata01-supermarket-pricing/)

### Prerequisites

* Java 8
* Maven

### Running the tests

`mvn clean test`

### Approach

Please note this is just part of the model of a real supermarket. My application
assumes there are `Products` (with infinite stock) and several types of `Promotions`. I've also added a stubbed 
`Basket` that only calculates its total and discounted prices.

This kata is based on Uncle Bob's clean architecture. I've implemented inputs to the system
as simple `Transactions` that emulate use cases.

The real challenge has been implementing flexible promotions. At the moment the system supports:

* single-buy promotions, e.g. "Buy one and pay £4 less"
* multi-buy promotions where the discount is calculated over a set of products - e.g., "Buy 3 get 20% off"

I've used the strategy pattern to make the promotions flexible in terms of:
* applicability - i.e. how many products are needed for the promotion to apply
* discount - i.e. different types of discounts (like percent or value)

### REST endpoints 

For example for products:

Add a product: `POST /supermarket/products`
Remove a product: `DELETE /supermarket/products/{sku}`
Update a product: `PUT /supermarket/products`
Get a product: `GET /supermarket/products/{sku}`



