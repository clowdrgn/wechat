package com.blueware.util.db;

import com.mongodb.DBCollection;

public class DaoImplBase<T> {

        protected DBCollection getDBCollection(String tableName){
                return MongoConnection.getDB().getCollection(tableName);
        }
}
