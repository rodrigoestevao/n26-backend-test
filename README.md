# Code Challenge
We would like to have a restful API for our statistics. The main use case for our API is to calculate realtime statistic from the last 60 seconds. There will be two APIs, one of them is called every time a transaction is made. It is also the sole input of this rest API. The other one returns the statistic based of the transactions of the last 60 seconds.

## Transaction API
Every Time a new transaction happened, the following endpoint will be called.  

__Endpoint:__  
~~~~
POST /transactions
~~~~

__Body:__  
~~~~
{
  "amount": 12.3,
  "timestamp": 1478192204000
}
~~~~

__Returns:__  
Empty body with either 201 or 204.  
* `201` - in case of success  
* `204` - if transaction is older than 60 seconds  

__Where:__  
* `amount` - is a double specifying the transaction amount  
* `timestamp` - is a long specifying the transaction time in epoch (unix time format) in millis in UTC time zone (this is not current timestamp)  

## Statistic API
The folloing endpoint is the aim of this task. It will have to execute in constant time and memory `(O(1))` and must returns the statistic based on the transactions which happened in the last 60 seconds.  

__Endpoint:__  
~~~~
GET /statistics
~~~~

__Returns:__  
~~~~
{
  "sum": 1000,
  "avg": 100,
  "max": 200,
  "min": 50,
  "count": 10
}
~~~~

__Where:__  
* `sum` is a double specifying the total sum of transaction value in the last 60 seconds  
* `avg` is a double specifying the average amount of transaction value in the last 60 seconds  
* `max` is a double specifying single highest transaction value in the last 60 seconds  
* `min` is a double specifying single lowest transaction value in the last 60 seconds  
* `count` is a long specifying the total number of transactions happened in the last 60 seconds 

# Requirements
For the rest api, the biggest and maybe hardest requirement is to make the `GET /statistics` execute in constant time and space. The best solution would be `O(1)`. It is very recommended to tackle the `O(1)` requirement as the last thing to do as it is not the only thing which will be rated in the code challenge.