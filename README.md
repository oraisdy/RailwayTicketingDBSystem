# RailwayTicketingDBSystem
MySQL database design for railway ticketing system.

Install
------------
Database structures and initial data in 12306.sql

Usage
------------
#### Query tickets:
```java
crud.queryTickets("徐州东","南京南","2016-11-11");
```
Result:

![](https://github.com/oraisdy/RailwayTicketingDBSystem/blob/master/screenshot1.png)
![](https://github.com/oraisdy/RailwayTicketingDBSystem/blob/master/screenshot2.png)

#### Order a seat
```java
crud.buyTicket(1, "G101", "北京南", "上海虹桥", "二等座");
```
Result:

![](https://github.com/oraisdy/RailwayTicketingDBSystem/blob/master/screenshot3.png)


