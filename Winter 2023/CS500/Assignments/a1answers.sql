Q1 – Show all the different product categories. Order results alphabetically.

SELECT DISTINCT c.categoryname
FROM categories c
JOIN products p ON c.categoryid = p.categoryid
ORDER BY c.categoryname ASC;

categoryname
----------------
Beverages
Condiments
Confections
Dairy Products
Grains/Cereals
Meat/Poultry
Produce
Seafood
(8 rows)

Q2 – Show the name and title of all customers from Canada. Order the results by customer name.

select customername, customertitle
from customers
where country = 'Canada'
order by customername;

customername    |    customertitle
-------------------+---------------------
Elizabeth Lincoln | Accounting Manager
Jean Fresnière    | Marketing Assistant
Yoshi Tannamuri   | Marketing Assistant
(3 rows)

Q3 – Show the customer name, address, city and country of all customers not from Brazil, France, Germany or USA. Order the results by country first, then city, both ascending.

SELECT customername, address, city, country
FROM customers
WHERE country NOT IN ('Brazil', 'France', 'Germany', 'USA')
ORDER BY country, city;

customername       |                    address                     |      city       |   country
-------------------------+------------------------------------------------+-----------------+-------------
Yvonne Moncada          | Ing. Gustavo Moncada 8585 Piso 20-A            | Buenos Aires    | Argentina
Sergio Gutiérrez        | Av. del Libertador 900                         | Buenos Aires    | Argentina
Patricio Simpson        | Cerrito 333                                    | Buenos Aires    | Argentina
Roland Mendel           | Kirchgasse 6                                   | Graz            | Austria
Georg Pipps             | Geislweg 14                                    | Salzburg        | Austria
Catherine Dewey         | Rue Joseph-Bens 532                            | Bruxelles       | Belgium
Pascale Cartrain        | Boulevard Tirou, 255                           | Charleroi       | Belgium
Jean Fresnière          | 43 rue St. Laurent                             | Montréal        | Canada
Elizabeth Lincoln       | 23 Tsawassen Blvd.                             | Tsawassen       | Canada
Yoshi Tannamuri         | 1900 Oak St.                                   | Vancouver       | Canada
Jytte Petersen          | Vinbæltet 34                                   | Kobenhavn       | Denmark
Palle Ibsen             | Smagsloget 45                                  | Århus           | Denmark
Matti Karttunen         | Keskuskatu 45                                  | Helsinki        | Finland
Pirkko Koskitalo        | Torikatu 38                                    | Oulu            | Finland
Patricia McKenna        | 8 Johnstown Road                               | Cork            | Ireland
Giovanni Rovelli        | Via Ludovico il Moro 22                        | Bergamo         | Italy
Maurizio Moroni         | Strada Provinciale 124                         | Reggio Emilia   | Italy
Paolo Accorti           | Via Monte Bianco 34                            | Torino          | Italy
Ana Trujillo            | Avda. de la Constitución 2222                  | México D.F.     | Mexico
Antonio Moreno          | Mataderos  2312                                | México D.F.     | Mexico
Francisco Chang         | Sierras de Granada 9993                        | México D.F.     | Mexico
Guillermo Fernández     | Calle Dr. Jorge Cash 321                       | México D.F.     | Mexico
Miguel Angel Paolino    | Avda. Azteca 123                               | México D.F.     | Mexico
Jonas Bergulfsen        | Erling Skakkes gate 78                         | Stavern         | Norway
Zbyszek Piestrzeniewicz | ul. Filtrowa 68                                | Warszawa        | Poland
Isabel de Castro        | Estrada da saúde n. 58                         | Lisboa          | Portugal
Lino Rodriguez          | Jardim das rosas n. 32                         | Lisboa          | Portugal
Eduardo Saavedra        | Rambla de Cataluña, 23                         | Barcelona       | Spain
Diego Roel              | C/ Moralzarzal, 86                             | Madrid          | Spain
Alejandra Camino        | Gran Vía, 1                                    | Madrid          | Spain
Martín Sommer           | C/ Araquil, 67                                 | Madrid          | Spain
José Pedro Freyre       | C/ Romero, 33                                  | Sevilla         | Spain
Maria Larsson           | Åkergatan 24                                   | Bräcke          | Sweden
Christina Berglund      | Berguvsvägen  8                                | Luleå           | Sweden
Yang Wang               | Hauptstr. 29                                   | Bern            | Switzerland
Michael Holz            | Grenzacherweg 237                              | Genève          | Switzerland
Helen Bennett           | Garden House Crowther Way                      | Cowes           | UK
Elizabeth Brown         | Berkeley Gardens 12  Brewery                   | London          | UK
Simon Crowther          | South House 300 Queensbridge                   | London          | UK
Thomas Hardy            | 120 Hanover Sq.                                | London          | UK
Ann Devon               | 35 King George                                 | London          | UK
Hari Kumar              | 90 Wadhurst Rd.                                | London          | UK
Victoria Ashworth       | Fauntleroy Circus                              | London          | UK
Carlos González         | Carrera 52 con Ave. Bolívar #65-98 Llano Largo | Barquisimeto    | Venezuela
Manuel Pereira          | 5ª Ave. Los Palos Grandes                      | Caracas         | Venezuela
Felipe Izquierdo        | Ave. 5 de Mayo Porlamar                        | I. de Margarita | Venezuela
Carlos Hernández        | Carrera 22 con Ave. Carlos Soublette #8-35     | San Cristóbal   | Venezuela
(47 rows)

Q4 – Show the order date, shipped date, customer id and freight of all orders placed on July 25 1997.

SELECT orderdate, shippeddate, customerid, freight
FROM orders
WHERE orderdate = '1997-07-25';

orderdate  | shippeddate | customerid | freight
------------+-------------+------------+---------
1997-07-25 | 1997-08-06  | LAMAI      |   26.78
1997-07-25 | 1997-08-01  | WOLZA      |   80.65
(2 rows)

Q5 – Show the product name and unit price of all products whose name starts with 'C'. Order by unit price descending.

SELECT productname, unitprice
FROM products
WHERE productname LIKE 'C%'
ORDER BY unitprice DESC;

productname          | unitprice
------------------------------+-----------
Côte de Blaye                |     263.5
Carnarvon Tigers             |      62.5
Camembert Pierrot            |        34
Chef Anton's Cajun Seasoning |        22
Chef Anton's Gumbo Mix       |     21.35
Chang                        |        19
Chartreuse verte             |        18
Chai                         |        18
Chocolade                    |     12.75
(9 rows)

Q6 – Show the product name, unit price and quantity per unity of all products whose quantity comes in bottles. Hint: look at the possible distinct values in the quantityperunit attribute. Order by product name.

SELECT productname, unitprice, quantityperunit
FROM products
WHERE quantityperunit LIKE '%bottles%'
ORDER BY productname;

productname            | unitprice |   quantityperunit
----------------------------------+-----------+---------------------
Aniseed Syrup                    |        10 | 12 - 550 ml bottles
Chang                            |        19 | 24 - 12 oz bottles
Côte de Blaye                    |     263.5 | 12 - 75 cl bottles
Genen Shouyu                     |        13 | 24 - 250 ml bottles
Laughing Lumberjack Lager        |        14 | 24 - 12 oz bottles
Louisiana Fiery Hot Pepper Sauce |     21.05 | 32 - 8 oz bottles
Outback Lager                    |        15 | 24 - 355 ml bottles
Rhönbräu Klosterbier             |      7.75 | 24 - 0.5 l bottles
Sasquatch Ale                    |        14 | 24 - 12 oz bottles
Sirop d'érable                   |      28.5 | 24 - 500 ml bottles
Steeleye Stout                   |        18 | 24 - 12 oz bottles
(11 rows)

(I saw this change in query while putting bottle there are 12 results in that there is one row is with bottle result and remaining are with bottles which are 11 results.)

Q7 – Show the product name and category name for all products in the 'Produce' category. Order by product_name.

SELECT p.productname, c.categoryname
FROM products p
JOIN categories c on p.categoryid=c.categoryid
WHERE c.categoryname LIKE 'Produce'
ORDER BY p.productname;

           productname           | categoryname
---------------------------------+--------------
 Longlife Tofu                   | Produce
 Manjimup Dried Apples           | Produce
 Rössle Sauerkraut               | Produce
 Tofu                            | Produce
 Uncle Bob's Organic Dried Pears | Produce
(5 rows)

Q8 – Show the order id, the company name that placed the order, and the first and last name of the associated employee (customer name).  Only show orders placed after January 1, 1998 that shipped after they were required.  Sort by order id.

SELECT o.OrderID, c.CompanyName, c. CustomerName
FROM orders o
JOIN customers c ON o.CustomerID = c.CustomerID
WHERE o.OrderDate > '1998-01-01' AND o.ShippedDate > o.RequiredDate
ORDER BY o.OrderID ASC;

 orderid |        companyname        |    customername
---------+---------------------------+--------------------
   10816 | Great Lakes Food Market   | Howard Snyder
   10827 | Bon app'                  | Laurence Lebihan
   10828 | Rancho grande             | Sergio Gutiérrez
   10847 | Save-a-lot Markets        | Jose Pavarotti
   10924 | Berglunds snabbköp        | Christina Berglund
   10927 | La corne d'abondance      | Daniel Tonini
   10960 | HILARION-Abastos          | Carlos Hernández
   10970 | Bólido Comidas preparadas | Martín Sommer
(8 rows)

Q9 – If the cost of freight is greater than or equal to $500.00, it will be taxed by 10%. Show the order id, freight cost and freight cost plus tax (freight_with_tax) for all orders of freight $500 or more. Use 2 decimal points for the calculated number freight_with_tax. Use cast(X as decimal(10,2)) to show the value of X as a decimal number with 10 digits and 2 decimal points.

SELECT OrderID, Freight, CAST(Freight+(Freight*0.1) AS decimal(10,2)) AS Freight_with_tax
FROM orders
WHERE Freight >= 500;

orderid | freight | freight_with_tax
---------+---------+------------------
  10372 |  890.78 |           979.86
  10479 |  708.95 |           779.85
  10514 |  789.95 |           868.95
  10540 | 1007.64 |          1108.40
  10612 |  544.08 |           598.49
  10691 |  810.05 |           891.05
  10816 |  719.78 |           791.76
  10897 |  603.54 |           663.89
  10912 |  580.91 |           639.00
  10983 |  657.54 |           723.29
  11017 |  754.26 |           829.69
  11030 |  830.75 |           913.83
  11032 |  606.19 |           666.81
(13 rows)

Q10 – Show the order id, product name, product quantity, product unit price, product discount and product total price for order ids 10248 and 10866. You must calculate the product total price given the other data. To understand the meaning of the discount attribute, use this: a discount value of 0.25 represents a 25% discount. Show the total price with only 2 decimal points. Order the results by order id.

SELECT o.orderid, p.productname, od.quantity, od.unitprice, od.discount, CAST((od.quantity*od.unitprice*(1-od.discount)) as decimal(10, 2)) as product_total_price
FROM orders o
JOIN order_details od ON o.orderid = od.orderid
JOIN products p ON od.productid = p.productid
WHERE o.orderid IN (10248, 10866)
ORDER BY o.orderid;

orderid |          productname          | quantity | unitprice | discount | product_total_price
---------+-------------------------------+----------+-----------+----------+---------------------
  10248 | Queso Cabrales                |       12 |        14 |        0 |              168.00
  10248 | Singaporean Hokkien Fried Mee |       10 |       9.8 |        0 |               98.00
  10248 | Mozzarella di Giovanni        |        5 |      34.8 |        0 |              174.00
  10866 | Chang                         |       21 |        19 |     0.25 |              299.25
  10866 | Guaraná Fantástica            |        6 |       4.5 |     0.25 |               20.25
  10866 | Nord-Ost Matjeshering         |       40 |     25.89 |     0.25 |              776.70
(6 rows)
