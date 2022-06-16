# Triggers :
## before_update_order :

	if new state = ordered => check user balance
	if new state = delivering => check messenger id NOT NULL

## after_update_order :

	if new state = ordered => update user balance
	if new state = delivering => update messenger status
	if new state = delivered => update messenger status

## before_insert_order_line :

	set price

## after_insert_order_line : 

	update order price

## after_update_order_line :

	set price
	update order price

## after_delete_order_line : 

	update order price

## after_update_messenger :

	if new vehicle id != old vehicle id
		if new vehicle id is not NULL => update new vehicle state
		if old vehicle id is not NULL => update new old vehicle state


# View : 

	available_messenger
	available_vehicles
	pizza_price
	menu
	customer_expanses

		CREATE VIEW customer_expanses as
		SELECT u.username, SUM(o.price) as "Expanses" 
		FROM order o 
		JOIN user u on u.id = o.user_id 
		GROUP BY user_id

	best_customer
	
		CREATE VIEW best_customer as 
		SELECT *
		FROM customer_expanses
		WHERE expenses = Max(expenses)

	revenue

		CREATE VIEW revenue as
		SELECT SUM(price)
		FROM order
		WHERE state_id > 0


# Use cases :

## A. Staff : 
**CRUD :**
- messenger
- ingredient
- pizza
- order

## B. Customer :
- see balance
- add balance
- create order
- browse pizza menu
- choose size
- choose quantity
- add pizza to order
- see order
- click Order !
- see order status