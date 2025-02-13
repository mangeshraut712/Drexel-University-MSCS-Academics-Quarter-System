--Q1
SELECT CategoryID, count(ProductID)
FROM products
GROUP BY CategoryID
ORDER BY CategoryID ASC;

--Q2
SELECT C.CategoryName, count(ProductID)
FROM products P, categories C
WHERE P.CategoryID = C.CategoryID
GROUP BY CategoryName
ORDER BY CategoryName;

--Q3
SELECT COUNT(DISTINCT SupplierID) AS "Number of distinct suppliers"
FROM products;

--Q4
SELECT COUNT(DISTINCT CustomerID) as "Number of distinct customers"
FROM customers;

--Q5
SELECT C.CompanyName
FROM customers C, orders O
WHERE C.CustomerID = O.CustomerID
AND O.OrderDate > '1996-12-31' AND O.OrderDate < '1998-01-01'
GROUP BY C.CompanyName
HAVING Count(O.OrderID)>9
ORDER BY COUNT(O.OrderID);

--Q6
SELECT COUNT(DISTINCT order_details.ProductID) AS unique_products_count
FROM order_details
WHERE discount = 0.25;

--Q7
SELECT COUNT(CustomerID) as total_customers_not_placed_order_in_1996
FROM customers S
WHERE S.CustomerID NOT IN (SELECT O.CustomerID
                         FROM orders O
                         WHERE OrderDate BETWEEN '1996-01-01' AND '1996-12-31');

--Q8
SELECT City, COUNT(DISTINCT CompanyName) as companycount
FROM customers
GROUP BY City
HAVING COUNT(DISTINCT CompanyName) >= 2
ORDER BY companycount DESC, City ASC;

--Q9
SELECT Sum(cast(D.UnitPrice*D.Quantity-D.UnitPrice*D.Quantity*D.Discount as decimal(10,2)))
FROM orders O, order_details D
WHERE O.OrderID = D.OrderID
AND O.OrderID = 10332;

--Q10
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
