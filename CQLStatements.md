# Cassandra CQL Statements

Use this file to include your DDL.  Also include any DML that you may have created.


## DDL

   - create the keyspace (oboe)
    
      create keyspace oboe with replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;

   - delete a keyspace (DB)
    
      drop keyspace oboe;
      
   - create tables
   
   - locations 	  	
    
      create table locations (locationid UUID primary key, location text, name text) with comment='Satellite scanned locations';
   	  	 
   - birds
      
      create table birds (birdid uuid primary key, location text, scanday text, birdspecies text, birdtraits list<text>) with comment = 'Scanned birds result';
   	  
   - drop tables
	  
	  drop table locations;
	  
	  drop table birds;
   	    	

## DML

   - populate dummy data into tables
   
   	- locations
		
		begin batch
		
			insert into locations(locationid, location, name) values(now(), '33N,84W', 'Atlanta');
			insert into locations(locationid, location, name) values(now(), '32N,96W', 'Dallas');
			insert into locations(locationid, location, name) values(now(), '29N,95W', 'Houston');
			insert into locations(locationid, location, name) values(now(), '32N,65W', 'Bermuda');
			
			insert into locations(locationid, location, name) values(now(), '40N,73W', 'New York');
			insert into locations(locationid, location, name) values(now(), '25N,80W', 'Miami');
			insert into locations(locationid, location, name) values(now(), '45N,75W', 'Ottwa');
			insert into locations(locationid, location, name) values(now(), '41N,87W', 'Chicago');
			
			insert into locations(locationid, location, name) values(now(), '38N,122W', 'San Francisco');
			insert into locations(locationid, location, name) values(now(), '49N,124W', 'Vancouver');	
			insert into locations(locationid, location, name) values(now(), '36N,140E', 'Tokyo');
			insert into locations(locationid, location, name) values(now(), '33S,151E', 'Sydney');
			
			insert into locations(locationid, location, name) values(now(), '64N,20W', 'Iceland');
			insert into locations(locationid, location, name) values(now(), '34S,23E', 'South Africa');
	
		apply batch;
		
		
   - birds
	
		begin batch
		
			insert into birds(birdid, location, scanday, birdspecies, birdtraits) values(now(), '33N,84W', '2024-09-18', 'Humming bird', ['small, agile, hooked beak']);
			insert into birds(birdid, location, scanday, birdspecies, birdtraits) values(now(), '32N,96W', '2024-03-27', 'Special loon', ['blue eyes, swim and dive, webbed feet']);
			insert into birds(birdid, location, scanday, birdspecies, birdtraits) values(now(), '29N,95W', '2025-02-27', 'Common loon', ['red eyes, swim and dive, webbed feet']);
			insert into birds(birdid, location, scanday, birdspecies, birdtraits) values(now(), '32N,65W', '2023-02-27', 'Canadian goose', ['black eyes, migration, grey feathers']);
			insert into birds(birdid, location, scanday, birdspecies, birdtraits) values(now(), '40N,73W', '2024-01-18', 'Humming bird', ['small, agile, hooked beak']);
			insert into birds(birdid, location, scanday, birdspecies, birdtraits) values(now(), '25N,80W', '2024-05-27', 'Special loon', ['blue eyes, swim and dive, webbed feet']);
			insert into birds(birdid, location, scanday, birdspecies, birdtraits) values(now(), '45N,75W', '2025-02-22', 'Common loon', ['red eyes, swim and dive, webbed feet']);
			insert into birds(birdid, location, scanday, birdspecies, birdtraits) values(now(), '41N,87W', '2025-01-27', 'Canadian goose', ['black eyes, migration, grey feathers']);
			insert into birds(birdid, location, scanday, birdspecies, birdtraits) values(now(), '38N,122W', '2022-09-18', 'Humming bird', ['small, agile, hooked beak']);
			insert into birds(birdid, location, scanday, birdspecies, birdtraits) values(now(), '49N,124W', '2021-08-27', 'Special loon', ['blue eyes, swim and dive, webbed feet']);
			insert into birds(birdid, location, scanday, birdspecies, birdtraits) values(now(), '36N,140E', '2025-07-21', 'Common loon', ['red eyes, swim and dive, webbed feet']);
			insert into birds(birdid, location, scanday, birdspecies, birdtraits) values(now(), '33S,151E', '2024-07-27', 'Canadian goose', ['black eyes, migration, grey feathers']);
			insert into birds(birdid, location, scanday, birdspecies, birdtraits) values(now(), '64N,20W', '2024-08-18', 'Humming bird', ['small, agile, hooked beak']);
			insert into birds(birdid, location, scanday, birdspecies, birdtraits) values(now(), '34S,23E', '2024-11-27', 'Special loon', ['blue eyes, swim and dive, webbed feet']);
		
		apply batch;
		
   - Read the scan results for a specific location on a given day (from database table - birds)

       select * from birds where location = '49N,124W' and scanday = '2021-08-27' allow filtering;

   - Clean up tables (remove all records from the target tables)
    
       truncate locations;
   
       truncate birds;

## Comments on End Points and UI Designed

	- Project was built with Java 17, and Spring Boot 3.2.4.
	
	- Project is Maven buildable.

    - when the application started (locally), check the following end points
       
       - home page: http://localhost:8080
        
         On the home page, there are clickable button on top for navigation between "Home", "Locations" and "Birds"
   		
			- "Home" shows the full list of tabular display of 'Locations' scanned, and birds scanned.
			
			- "Locations" shows the list of locations scanned by the satellite, and other functions for modifying and user configure and input new locations, which 
			  will be recorded in the back-end DB.
			
			- "Birds" show the similar functionalities as 'Locations".
	  - As shown and required by the unit tests, search end points are also designed to fulfill the functionalities; when the unit tests have been run, you should 	
	    be able to use the following end points to check the results (json format results will be shown on your browser):
	    
	    http://localhost:8080/scan/result/location/25N,71W/day/2025-08-17
	    http://localhost:8080/scan/location/25N,71W
	  
	  - Some thoughts:
	  	
	  	- The assignment project is just a basic functioning application with back-end database Cassandra, plus a little bit front-end HTML (static) web pages 
	  	  designed for 	convenience to some degree. However, more consideration about production level development. 
	  	    		
	  	- Immediate thoughts would be listed as follows:
   	  
		   - multi-node Cassandra DB as back-end data stores;
		   - security login;
		   - sophisticated front-end functionalities using javaScripts (e.g., React.js) leading to better data rendering with analytics for certain business 
		     requirements; 
		   - others yet not coming to my mind yet.
		
		- As for code base itself, there maybe more need on code coverage, and /or other improvements.
   		  
   		  
   
   
       
       