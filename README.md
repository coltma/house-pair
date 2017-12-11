# HousePair


## 1. Overview
HousePair helps you find a match home for you!

This project aims to help users to find a matched rental house(simplified as house). After crawling house ads from from websites (or social media apps and other data resource in the future work), it processes those data and visualize them on Google Map. Furthermore, based on historical rental house price and Machine Learning, it could predict the price for new posted ads which used as a reference for user to make a reasonable choice. If the user has some requirement for a house and once those kind of house was found by our crawler, the system would notify the user.


## 2. Main Use Cases
###### 1. House Searching:
  User could search for his interest cities rental house data;
###### 2. Price Prediction:
  The system is able to do some prediction, such as price trend in the future or the predicted price based on the historical data;
###### 3. Display Rental House Information:
  A basic UI to provide user a more simple and clear perspective to houses with Google Map;
###### 4. Offline Notification:
  The system is able to notify user through E-mail or social media apps like Facebook once a candidate house is found;

## 3. High Level Design
![alt text](https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png "Logo Title Text 1")


1.House Search Service
* Displays house data according to user's interest city.
* Visualize house data with Google Map.
* Provides predicted data (if available) based on Machine Learning.
* Subscribe a kind of or a specific target house.

2.House Crawler Service
* A distributed crawler system including crawler collecting web pages and parse process raw HTML file.

3.House Data Process Service
* De duplicate and validate house ads from different data resource, such as web crawler.
* If find any target data, invoke notification service.

4.Machine Learning Service
* Use historical house data to train different models based on different cities, and apply different model to give a predicted price for posted house for user reference.

5.House Subscription Service
* Accept users' subscriptions to a specific house or one kind of house.
* Once the price of user subscribed house goes down or such kind of house is found, this service will notify user who subscribed those.

## 4. Detail Design

#### 1.House Search Service
###### Java, Spring Boot, Google Map;
* Fetch house data and predicted prices data(if available) from database, visualize them on web page.
* Store users' subscription.

#### 2.House Crawler Service
[img]
###### Python, Scrapy, RabbitMQ, MongoDB;
* A feeder reads feed into a message queue, then crawlers consume those message by crawling the specific websites.
* Crawler get the web pages(HTML) and store them into database.
* Parser read in the raw web page from database and analyze the key data such as house address and price, then send them into different message queues based on house geographic location.

##### Discussion
1.Why use message queue?
* Decouple feeder and crawler

2.Why not analyze immediately after crawled a web page?
* Choosing asynchronous parse HTML data is to separate the crawling and parsing job, so crawlers focus on crawling and easy to scale up. If we want more web pages, we just add more crawlers, vice versa. Parser is the similar way to do so.
* By storing raw page data, we can choose what we want from those data based on our dynamical needs; Otherwise, we need to crawler those crawled web pages again.
* House ads data is not so real-time demanding.


#### 3.House Data Process Service
###### Java, Spring Boot, RabbitMQ;
* Consume from the message queue and do validation and de-duplicate before store them into final house data database; Also, if the new data is what the user subscribes, invoke `House Notification Service` through REST API.

##### Discussion
1.Why use message queue?
* Decouple raw data parsing and storing, and separate processed data based on house geographic location into different queues. In the future, we could add other data resource into our system apart from web page data.
* On the other hand, different house data servers consume different message queues, so duplicate house data from different data resource could be find at one data server.

2.Why store house data into different *house data server*?
* House has a strong location feature and users tend to search houses based on geographic locations, so we can store house data based on locations. If an area has a large number of houses, we can further split into several servers.

#### 4.Machine Learning Service
###### Python  
* Offline: read house data from database and extract features to train a model.
* Online: when new data comes, use trained to do prediction for house price and store them into predicted database.


#### 5.House Notification Service
###### Node.js, MySQL
* When received invoke from `House Data Process Service`, send emails to matched users.

#### General Discussion

1.Why NoSQL DB?
* SQL are Schema–Oriented i.e. the structure of the data should be known in advance ensuring that the data adheres to the schema; But HTML data varies, we need process unpredictable and unstructured information.
* SQL Databases are vertically scalable, but need hardware upgrade, which is expensive; SQL suffers from serious performance bottlenecks if we need real-time notification.
* In cluster, ACID transaction will make SQL DB slow.
* Consider data size would continuously grow, NoSQl Scalability can handle it.

## 5. Future work
* Increase data source types;
* UI improvement;
* Service scale up;
* User click log records;
