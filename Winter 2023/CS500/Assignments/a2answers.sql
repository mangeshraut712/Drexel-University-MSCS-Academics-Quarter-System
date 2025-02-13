Q1 – For each category id, find the number of products in the category. List by category id ascending.

SELECT CategoryID, count(ProductID)
FROM products
GROUP BY CategoryID
ORDER BY CategoryID ASC;

categoryid | count
------------+-------
         1 |    12
         2 |    12
         3 |    13
         4 |    10
         5 |     7
         6 |     6
         7 |     5
         8 |    12
(8 rows)

Q2 – For each category name, find the number of products in the category. List the results alphabetically by category name.

SELECT C.CategoryName, count(ProductID)
FROM products P, categories C
WHERE P.CategoryID = C.CategoryID
GROUP BY CategoryName
ORDER BY CategoryName;

 categoryname  | count
----------------+-------
Beverages      |    12
Condiments     |    12
Confections    |    13
Dairy Products |    10
Grains/Cereals |     7
Meat/Poultry   |     6
Produce        |     5
Seafood        |    12
(8 rows)

Q3 – Find the number of distinct suppliers for products.

SELECT COUNT(DISTINCT SupplierID) AS "Number of distinct suppliers"
FROM products;

Number of distinct suppliers
------------------------------
                          29
(1 row)

Q4 – Find the total number of distinct customers.

SELECT COUNT(DISTINCT CustomerID) as "Number of distinct customers"
FROM customers;

Number of distinct customers
------------------------------
                          91
(1 row)

Q5 – Find the company name that placed 10 or more orders in 1997. Order the result by the order count.

SELECT C.CompanyName
FROM customers C, orders O
WHERE C.CustomerID = O.CustomerID
AND O.OrderDate > '1996-12-31' AND O.OrderDate < '1998-01-01'
GROUP BY C.CompanyName
HAVING Count(O.OrderID)>9
ORDER BY COUNT(O.OrderID);

companyname
------------------------------
Mère Paillarde
Hungry Owl All-Night Grocers
HILARION-Abastos
Wartian Herkku
Berglunds snabbköp
QUICK-Stop
Ernst Handel
Save-a-lot Markets
(8 rows)

Q6 – Find the number of unique products of every product that was ever offered for a discount of 25%.

SELECT COUNT(DISTINCT order_details.ProductID) AS unique_products_count
FROM order_details
WHERE discount = 0.25;

unique_products_count
-----------------------
                   64
(1 row)

Q7 – Count the customers that did not place an order in 1996.

SELECT COUNT(CustomerID) as total_customers_not_placed_order_in_1996
FROM customers S
WHERE S.CustomerID NOT IN (SELECT O.CustomerID
                         FROM orders O
                         WHERE OrderDate BETWEEN '1996-01-01' AND '1996-12-31');

total_customers_not_placed_order_in_1996
------------------------------------------
                                      24
(1 row)

Q8 – Cities with at least two companies. Order by number of companies descending, first and then by the city name ascending.

SELECT City, COUNT(DISTINCT CompanyName) as companycount
FROM customers
GROUP BY City
HAVING COUNT(DISTINCT CompanyName) >= 2
ORDER BY companycount DESC, City ASC;

     city      | companycount
----------------+--------------
London         |            6
México D.F.    |            5
Sao Paulo      |            4
Buenos Aires   |            3
Madrid         |            3
Rio de Janeiro |            3
Lisboa         |            2
Nantes         |            2
Paris          |            2
Portland       |            2
(10 rows)

Q9 – Total price for order id 10332. In the calculation of total cost, do not include freight costs just product costs. Show the results in 2 decimal points.

SELECT Sum(cast(D.UnitPrice*D.Quantity-D.UnitPrice*D.Quantity*D.Discount as decimal(10,2)))
FROM orders O, order_details D
WHERE O.OrderID = D.OrderID
AND O.OrderID = 10332;

  sum
---------
1786.88
(1 row)

Q10 – Find the city with the most companies. Show the city name and the company names.

SELECT city, companyName
FROM customers
WHERE city = (
SELECT city
FROM (
SELECT ROW_NUMBER() OVER (ORDER BY COUNT(companyName) DESC) row_num, city, COUNT(companyName)
FROM customers
GROUP BY city
ORDER BY COUNT(companyName) DESC
) x
WHERE x.row_num = 1
);

 city  |      companyname
--------+-----------------------
London | Around the Horn
London | B's Beverages
London | Consolidated Holdings
London | Eastern Connection
London | North/South
London | Seven Seas Imports
(6 rows)
