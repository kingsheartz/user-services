package com.keycloak.model.ELKStack;

public class IndexData {
    
    //=================================
    //      Data members
    //=================================

    private String indexName;
    private int from;
    private int to;


    //=================================
    //  Default Constructors
    //=================================

    public IndexData(){}


    //=================================
    //  Parameterized Constructors
    //=================================

    public IndexData(String indexName, int from, int to) {
        this.indexName = indexName;
        this.from = from;
        this.to = to;
    }

    //=================================
    //  Properties
    //=================================
    public String getIndexName() {
        return indexName;
    }
    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public int getfrom() {
        return from;
    }
    public void setfrom(int from) {
        this.from = from;
    }
    public int getto() {
        return to;
    }
    public void setto(int to) {
        this.to = to;
    }



    //================================
    //  override toString()
    //================================
    @Override
    public String toString() {
        return "IndexData [indexName=" + indexName + ", from=" + from + ", to=" + to + "]";
    }
}
