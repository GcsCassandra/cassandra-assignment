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
      
      create table birds (birdid uuid primary key, location text, scanday text, birdspecies text, birdtraits list<text>,) with comment = 'Scanned birds result';
   	  
   - drop tables
	  
	  drop table locations;
	  
	  drop table birds;
   	    	

## DML (if any)

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
