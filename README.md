# HousePair


## 1. Overview
HousePair helps you find a match home for you!

This project aims to help users to find a matched rental house. After crawling house ads from from websites (or social media apps and other data resource in the future work), it processes those data and visualize them on Google Map. Furthermore, based on historical rental house price and Machine Learning, it could predict the price for new posted ads which used as a reference for user to make a reasonable choice. If user is interested in a particular house and once the house price changed, the system would notify the user.

<img width="1123" alt="demo" src="https://user-images.githubusercontent.com/25625245/37695359-ec3147ee-2c8b-11e8-8540-a816fd94eaa0.png">

## 2. Main Use Cases
###### 1. House Searching:
  User could search for his interest cities rental house data;
###### 2. Price Prediction:
  The system is able to do some prediction, such as the predicted price based on the historical data;
###### 3. Display Rental House Information:
  A basic UI to provide user a more simple and clear perspective to houses with Google Map;
###### 4. Subscription & Notification:
  Subscribing house and get notification when the price of this  house changes (found by crawler);

## 3. High Level Design
![capstone-high-level-diagram](https://user-images.githubusercontent.com/25625245/37694140-06cbc6c4-2c82-11e8-8457-ecb85ab3b478.png)


1.House Search Service
* Displays house data according to user's interest city.
* Visualize house data with Google Map.
* Provides predicted data (if available) based on Machine Learning.
* Subscribe a target house.

2.House Crawler Service
* A distributed crawler system including crawler collecting web pages and parse process raw HTML file.

3.House Data Process Service
* De-duplicate and validate house ads from different data resource, such as web crawler.
* If find any target data, invoke notification service.

4.Machine Learning Service
* Use historical house data to train different models based on different cities, and apply different model to give a predicted price for posted house for user reference.

5.House Subscription Service
* Accept users' subscriptions to a specific house or one kind of house.
* Once the price of user subscribed house goes down or such kind of house is found, this service will notify user who subscribed those.

> Due to the limited resource of hardware and demo, the database is included in House Data Process Service instead of a standalone database server.

> In the demo, We only crawl craglist.com and using San Francisco data.

## 4. Detail Design

#### 1.House Search Service
![capstone-search-diagram](https://user-images.githubusercontent.com/25625245/37694152-192db69c-2c82-11e8-80eb-9703120524cb.png)
###### Java, Spring Boot, MongoDB;
* Fetch house data from database, visualize them on web page.
* Fetch predicted prices data(if available) from `Machine Learning Service`.
* Store users' subscription.

#### 2.House Crawler Service
![capstone-crawler-diagram](https://user-images.githubusercontent.com/25625245/37694160-26c9b738-2c82-11e8-9dc0-228b08434d07.png)
###### Python, Scrapy, RabbitMQ, MongoDB;
* Crawlers consume those message by crawling the specific websites.
* Crawler get the web pages(HTML) and store them into database.
* Parser read in the raw web page from database and analyze the key data such as house address and price, then send them into different message queues based on house geographic location.
> For house ads which lacking detail address but provided location, the parser will complete the address information through Google Places API.

##### Discussion
1.Why use message queue?
* Decouple house data processing and crawler

2.Why not analyze immediately after crawled a web page?
* Choosing asynchronous parse HTML data is to separate the crawling and parsing job, so crawlers focus on crawling and easy to scale up. If we want more web pages, we just add more crawlers, vice versa. Parser is the similar way to do so.
* By storing raw page data, we can choose what we want from those data based on our dynamical needs; Otherwise, we need to crawler those crawled web pages again.
* House ads data is not so real-time demanding.


#### 3.House Data Process Service
![capstone-process-diagram](https://user-images.githubusercontent.com/25625245/37694169-36d63de0-2c82-11e8-93a6-da0d8ad1d3e3.png)

###### Java, Spring Boot, RabbitMQ;
* Consume from the message queue and do validation and de-duplicate before store them into final house data database.
* If the new data is what the user subscribes, invoke `House Notification Service` through REST API.

##### Discussion
1.Why use message queue?
* Decouple raw data parsing and storing, and separate processed data based on house geographic location into different queues. In the future, we could add other data resource into our system apart from web page data.
* On the other hand, different house data servers consume different message queues, so duplicate house data from different data resource could be find at one data server.

2.Why store house data into different *house data server*?
* House has a strong location feature and users tend to search houses based on geographic locations, so we can store house data based on locations. If an area has a large number of houses, we can further split into several servers.

#### 4.Machine Learning Service
![capstone-ml-diagram](https://user-images.githubusercontent.com/25625245/37694174-4b74b93e-2c82-11e8-91ca-449719699b99.png)
###### Python, Django, MongoDB
* Offline: read house data from database and extract features to train a model.
* Online: when new data comes, use trained to do prediction for house price and store them into predicted database.
> The actual training result is not so good, the reason include but not limited to:
Even within a city, the price varies for different area;
Limited house data, around 2,000 for a city in craglist.com;
Limited usable feature: number of bedrooms, number of bathroom, area size;


#### 5.House Subscription Service
![capstone-subscription-diagram](https://user-images.githubusercontent.com/25625245/37694179-56abcbf8-2c82-11e8-89fa-a50d2f57506d.png)
###### Node.js, RabbitMQ
* Consume message from `House Data Process Service`, send emails to matched users.

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
* Add cache to improve availability;
