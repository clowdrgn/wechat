package com.blueware.util.db;

import java.util.List;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;

public class MongoConnection {
        
        private static MongoClient mongo = null;
        private static DB db = null;
        
        public static synchronized void init(List<ServerAddress> replicaSet,
                        MongoClientOptions option, String defaultDb){
                mongo = new MongoClient(replicaSet, option);
                mongo.setReadPreference(ReadPreference.nearest());
                setDb(mongo.getDB(defaultDb));
        }

        public static DB getDB() {
                return db;
        }

        public static void setDb(DB db) {
                MongoConnection.db = db;
        }
        public static void close(){
                mongo.close();
        }
}
