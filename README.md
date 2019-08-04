# Articles

* This application needs Java 8 to run
* It will use a H2 in Memory database.
    * Access: http://localhost:8080/h2-console
    * JDBC URL: jdbc:h2:mem:testdb 
* Once compiled and run, it will expose the current endpoints:
* The application will be exposed through http://localhost:8080
    * Endpoint: POST /articles
        * Creates a new Article
        * Payload: 
        ```
        {
             "header": "header",
             "shortDescription": "short description",
             "text": "text",
             "publishDate": "2019-07-31",
             "authors": [{"id":1}],
             "keywords": [{"id":1}]        
         }    
        ```
        * Is it necessary to have have the authors and keywords previously created.
        
    * Endpoint: PUT /articles/{id}
        * Update the Article with the current id
        * Payload:
        ```
       {
            "header": "header",
            "shortDescription": "short description",
            "text": "text",
            "publishDate": "2019-07-31",
            "authors": [{"id":1}],
            "keywords": [{"id":1}]        
        } 
        ```
        * Is it necessary to have have the authors and keywords previously created.
    
    * Endpoint: DELETE /articles/{id}
        * Remove the article eith the current id.
        
    * Endpoint: GET /articles/{id}
        * Get the article with the current id
        
    * Endpoint: GET /articles?fromDate=2019-07-25&toDate=2019-07-29
        * Get all the articles in the current period
    
    * Endpoint: POST /authors
        * Creates a new Author
        * Payload:
        ```
        {
            "name":"John"
        }
        ```
    
    * Endpoint: GET /authors
        * Returns all authors
    
    * Endpoint: GET /authors/{id}/articles
        * Returns all the articles that contains the author with this id
        
    * Endpoint: POST /keywords
        * Creates a new Keyword
        * Payload:
        ```
        {
            "name":"reactive"
        }
        ```
        
    * Endpoint: GET /keywords
        * Returns all Keywords
        
    * Endpoint: GET /keywords/{id}/articles
        * Returns all the articles that contains the keyword with this id 