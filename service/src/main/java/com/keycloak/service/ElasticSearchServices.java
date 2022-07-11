package com.keycloak.service;


import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.keycloak.model.ELKStack.Logger;
import com.keycloak.model.ELKStack.ELKResponseData;


public class ElasticSearchServices {
    
    //================================
    //  Default constructor
    //================================
    public ElasticSearchServices() {
    }
    
    //================================
    //  Get Indices
    //================================
    public Response GetIndices(){

        Response response = ClientBuilder.newClient()
                          .target("http://localhost:9200/_cat/indices")
                          .request(MediaType.APPLICATION_JSON)
                          .get();
        return response;


    }

    //================================
    //  Send logger log data
    //================================
    public ELKResponseData SendIndex(Logger logger){
        
        //Create Json object
        Logger iLogger=new Logger();
        //Create Response  object
        ELKResponseData responseData=new ELKResponseData();

        iLogger.setName(logger.getName());
        iLogger.setEmail(logger.getEmail());

        Response response=ClientBuilder.newClient()
                          .target("http://localhost:9200/logger/user")
                          .request()
                          .post(Entity.entity(iLogger, MediaType.APPLICATION_JSON) );

        if(response.getStatus()==201){

            responseData.set_responseCode(response.getStatus());
            responseData.set_responseMessage("log data successfully created");
            
        }     
        else{
            responseData.set_responseCode(400);
            responseData.set_responseMessage("Error occured");
        }                             

        return responseData;
    }

    //=========================================
	//		S E A R C H    E L E M E N T S 
	//=========================================

    public Response GetSearchElements(String indexData){

        String url = "http://localhost:9200/" + indexData+ "/_search";
        Response response = ClientBuilder.newClient()
                          .target(url)
                          .request(MediaType.APPLICATION_JSON)
                          .get();
        return response;
    }

    
}
