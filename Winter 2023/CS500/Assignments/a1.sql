--Q1
SELECT DISTINCT c.categoryname
FROM categories c
JOIN products p ON c.categoryid = p.categoryid
ORDER BY c.categoryname ASC;

--Q2
select customername, customertitle
from customers
where country = 'Canada'
order by customername;

--Q3
SELECT customername, address, city, country
FROM customers
WHERE country NOT IN ('Brazil', 'France', 'Germany', 'USA')
ORDER BY country, city;

--Q4
SELECT orderdate, shippeddate, customerid, freight
FROM orders
WHERE orderdate = '1997-07-25';

--Q5
SELECT productname, unitprice
FROM products
WHERE productname LIKE 'C%'
ORDER BY unitprice DESC;

--Q6
SELECT productname, unitprice, quantityperunit
FROM products
WHERE quantityperunit LIKE '%bottles%'
ORDER BY productname;

--Q7
SELECT p.productname, c.categoryname
FROM products p
JOIN categories c on p.categoryid=c.categoryid
WHERE c.categoryname LIKE 'Produce'
ORDER BY p.productname;

--Q8
SELECT o.OrderID, c.CompanyName, c. CustomerName
FROM orders o
JOIN customers c ON o.CustomerID = c.CustomerID
WHERE o.OrderDate > '1998-01-01' AND o.ShippedDate > o.RequiredDate
ORDER BY o.OrderID ASC;

--Q9
SELECT OrderID, Freight, CAST(Freight+(Freight*0.1) AS decimal(10,2)) AS Freight_with_tax
FROM orders
WHERE Freight >= 500;

--Q10
SELECT o.orderid, p.productname, od.quantity, od.unitprice, od.discount, CAST((od.quantity*od.unitprice*(1-od.discount)) as decimal(10, 2)) as product_total_price
FROM orders o
JOIN order_details od ON o.orderid = od.orderid
JOIN products p ON od.productid = p.productid
WHERE o.orderid IN (10248, 10866)
ORDER BY o.orderid;
