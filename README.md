REST API using Hibernate/Spring/Spring-Boot without frontend.

The task is:

Build a voting system for deciding where to have lunch.

2 types of users: admin and regular users
Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which restaurant they want to have lunch at
Only one vote counted per user
If user votes again the same day:
If it is before 11:00 we asume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

-----------------------------------------------------------------------------------

Get all users :
 
  curl localhost:8080/admin/users -u admin@gmail.com:admin

Update user:

  curl -X PUT localhost:8080/users/profile -H "Content-type:application/json" -d '{\"email\":\"test@gmail.com\",\"password\":\"trd4\"}' -u user@mail.ru:qwe

Add user:

 curl -s -X POST localhost:8080/users/profile/register -H "Content-type:application/json" -d '{\"email\":\"poncha@mail.ru\",\"password\":\"sdfjhj\"}'

Delete user:

 curl -X DELETE localhost:8080/admin/users/100002 -u admin@gmail.com:admin


Get restaraunt :

  curl localhost:8080/restaurants -u admin@gmail.com:admin

Get restaurantMeals in restaraunt : 

  curl localhost:8080/restaurants/restaurantMeals/100000 -u admin@gmail.com:admin

Create restaraunt whith meals:

  curl -X POST localhost:8080/admin/restaurants -H "Content-type:application/json" -d '{\"name\":\"ABC\",\"restaurantMeals\":[{\"name\":\"Buter\",\"price\":\"89\"},{\"name\":\"Colla\",\"price\":\"39\"}]}' -u admin@gmail.com:admin

Vote (for 11:00): 

  curl localhost:8080/restaurants/vote/100001 -u user@mail.ru:qwe

Update name restaraunt:

  curl -X PUT localhost:8080/admin/restaurants/100005 -H "Content-type:application/json" -d '{"name":"Картошка"}' -u admin@gmail.com:admin

Delete Restoraunt: 

  curl -X DELETE localhost:8080/admin/restaurants/100007 -u admin@gmail.com:admin
  
Get all meal:

  curl localhost:8080/meal -u admin@gmail.com:admin
  
 
 
 