package com.blueware.dao.info;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blueware.entity.manage.Info;
import com.blueware.util.db.DaoImplBase;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class InfoDaoImpl extends DaoImplBase<Info> {
        protected static final Logger LOG = LoggerFactory.getLogger(InfoDaoImpl.class);
        protected final String TABLE_NAME = "info";

        static {
                Instance = new InfoDaoImpl();
        }

        private final static InfoDaoImpl Instance;

        public static InfoDaoImpl getInstance() {
                return Instance;
        }

        public List<Info> findTest(){
                List<Info> infos = new ArrayList<Info>();
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", new BasicDBObject("$lt", 3000L));
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while (cursor.hasNext()) {
                                infos.add(getInfoFromResult(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return infos;
        }
        public List<Info> findByLoginTime(String loginTime) {
                List<Info> infos = new ArrayList<Info>();
                try {
                        DBObject object = new BasicDBObject();
                        object.put("login_time", new BasicDBObject("$gte", loginTime));
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while (cursor.hasNext()) {
                                infos.add(getInfoFromResult(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return infos;
        }

        public List<Info> findByAdminId(Long adminId, int number, int skip) {
                List<Info> infos = new ArrayList<Info>();
                try {
                        DBObject object = new BasicDBObject();
                        BasicDBList list = new BasicDBList();
                        list.add(new BasicDBObject("admin_id", adminId));
                        list.add(new BasicDBObject("sale", adminId));
                        list.add(new BasicDBObject("support", adminId));
                        list.add(new BasicDBObject("preSale", adminId));
                        object.put("$or", list);
                        DBObject sort = new BasicDBObject();
                        sort.put("contect_time", 1);
                        DBCursor cursor = null;
                        if(number == 0){
                                cursor = getDBCollection(TABLE_NAME).find(object);
                        }else{
                                cursor = getDBCollection(TABLE_NAME).find(object).sort(sort).skip(skip).limit(number);
                        }
                        infos = new ArrayList<Info>();
                        while (cursor.hasNext()) {
                                infos.add(getInfoFromResult(cursor.next()));
                        }
                        return infos;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        
        public long countAdminId(Long adminId){
                try {
                        DBObject object = new BasicDBObject();
                        BasicDBList list = new BasicDBList();
                        list.add(new BasicDBObject("admin_id", adminId));
                        list.add(new BasicDBObject("sale", adminId));
                        list.add(new BasicDBObject("support", adminId));
                        list.add(new BasicDBObject("preSale", adminId));
                        object.put("$or", list);
                        DBObject sort = new BasicDBObject();
                        sort.put("contect_time", 1);
                        return getDBCollection(TABLE_NAME).count(object);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return 0;
        }

        public List<Info> countSign() {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("user_id", new BasicDBObject("$gte", 1L));
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        List<Info> infos = new ArrayList<Info>();
                        while (cursor.hasNext()) {
                                infos.add(getInfoFromResult(cursor.next()));
                        }
                        return infos;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        public List<Info> countSign(String start, String end) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("user_id", new BasicDBObject("$gte", 1L));
                        BasicDBList list = new BasicDBList();
                        list.add(new BasicDBObject("create_time", new BasicDBObject("$gte", start)));
                        list.add(new BasicDBObject("create_time", new BasicDBObject("$lt", end)));
                        object.put("$and", list);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        List<Info> infos = new ArrayList<Info>();
                        while (cursor.hasNext()) {
                                infos.add(getInfoFromResult(cursor.next()));
                        }
                        return infos;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        public List<Info> findAll(){
                try{
                        List<Info> infos = new ArrayList<Info>();
                        DBObject object = new BasicDBObject();
                        object.put("user_id", new BasicDBObject("$gt", 0));
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while(cursor.hasNext()){
                                infos.add(getInfoFromResult(cursor.next()));
                        }
                        return infos;
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        
        public List<Info> findEmail(int emailStatus){
                try{
                        List<Info> infos = new ArrayList<Info>();
                        DBObject object = new BasicDBObject();
                        object.put("email_status", emailStatus);
                        object.put("user_id", new BasicDBObject("$gt", 0L));
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while(cursor.hasNext()){
                                infos.add(getInfoFromResult(cursor.next()));
                        }
                        return infos;
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        public Info findEmail(Long userId, int emailStatus){
                try{
                        DBObject object = new BasicDBObject();
                        object.put("email_status", emailStatus);
                        object.put("user_id", userId);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if(cursor.hasNext()){
                                return getInfoFromResult(cursor.next());
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public Info findByEmail(String email) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("email", email);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getInfoFromResult(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        public Info findByEmailOrPhone(String email,String phone) {
            try {
                    DBObject object = new BasicDBObject();
                    BasicDBList list = new BasicDBList();
                    list.add(new BasicDBObject("email", email));
                    list.add(new BasicDBObject("phone", phone));
                    object.put("$or", list);
                    DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                    if (cursor.hasNext()) {
                            return getInfoFromResult(cursor.next());
                    }
            } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
            }
            return null;
    }

        public Info findByUserId(Long userId) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("user_id", userId);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getInfoFromResult(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        
        public Info findByUserIdStatus(Long userId) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("user_id", userId);
                        object.put("status", 1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getInfoFromResult(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public Info findById(Long id) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", id);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getInfoFromResult(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        public boolean update_contectTime(Info info) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", info.getId());
                        BasicDBObject value = new BasicDBObject();
                        value.put("$set", new BasicDBObject("contect_time", info.getContectTime()));
                        return getDBCollection(TABLE_NAME).update(object, value).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        public List<Info> findByCreateTime(String start, String end){
                List<Info> infos = new ArrayList<Info>();
                try{
                       DBObject object = new BasicDBObject();
                       BasicDBList list = new BasicDBList();
                       list.add(new BasicDBObject("create_time", new BasicDBObject("$gte", start)));
                       list.add(new BasicDBObject("create_time", new BasicDBObject("$lt", end)));
                       object.put("$and", list);
                       DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                       while(cursor.hasNext()){
                               infos.add(getInfoFromResult(cursor.next()));
                       }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return infos;
        }
        public Info findByUserIdCreate(Long userId) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("user_id", userId);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        if (cursor.hasNext()) {
                                return getInfoFromResult(cursor.next());
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public boolean exist(Long userId) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("user_id", userId);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        return cursor.hasNext();
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        
        public boolean updateshouhui(Info info){
                try{
                        DBObject object = new BasicDBObject();
                        object.put("id", info.getId());
                        DBObject value = new BasicDBObject("$set", new BasicDBObject("sale", null));
                        return getDBCollection(TABLE_NAME).update(object, value).getN() > -1;
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        public boolean update(Info info) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", info.getId());
                        DBObject value = new BasicDBObject();
                        if (info.getUserId() != null && info.getUserId() > 0) {
                                value.put("user_id", info.getUserId());
                        }
                        value.put("login_time", info.getLoginTime());
                        value.put("create_time", info.getCreateTime());
                        if (info.getDataTime() != null) {
                                value.put("data_time", info.getDataTime());
                        }
                        if (info.getSale() != null && info.getSale() > 0) {
                                value.put("sale", info.getSale());
                        }
                        if (info.getSupport() != null && info.getSupport() > 0) {
                                value.put("support", info.getSupport());
                        }
                        if (info.getPreSale() != null && info.getPreSale() > 0) {
                                value.put("preSale", info.getPreSale());
                        }
                        if (info.getName() != null && info.getName().trim().length() > 0) {
                                value.put("name", info.getName());
                        }
                        if (info.getPhone() != null && info.getPhone().trim().length() > 0) {
                                value.put("phone", info.getPhone());
                        }
                        if (info.getEmail() != null && info.getEmail().trim().length() > 0) {
                                value.put("email", info.getEmail());
                        }
                        if (info.getExpireTime() != null && info.getExpireTime().trim().length() > 0) {
                                value.put("pay_expire_time", info.getExpireTime());
                        }
                        if(info.getGender() >= 0 && info.getGender() <= 2){
                                value.put("gender", info.getGender());
                        }
                        value.put("project", info.getProject());
                        value.put("qq", info.getQq());
                        if(info.getTitle() > 0){
                                value.put("title", info.getTitle());
                        }
                        return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        public boolean updateOwner(Info info) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", info.getId());
                        DBObject value = new BasicDBObject();
                        value.put("support", info.getSupport());
                        value.put("sale", info.getSale());
                        value.put("preSale", info.getPreSale());
                        return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        public boolean updateKf(Long id, Long kfId) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", id);
                        DBObject value = new BasicDBObject();
                        value.put("$set", new BasicDBObject("kf_id", kfId));
                        return getDBCollection(TABLE_NAME).update(object, value).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        
        public boolean updateEmailStatus(String email) {
            try {
                    DBObject object = new BasicDBObject();
                    object.put("email", email);
                    DBObject value = new BasicDBObject();
                    value.put("$set", new BasicDBObject("email_status", 1));
                    return getDBCollection(TABLE_NAME).update(object, value).getN() > -1;
            } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
            }
            return false;
    }

        public Long findKFById(Long id) {
                try {
                        DBObject object = new BasicDBObject("id", id);
                        DBObject result = new BasicDBObject("kf_id", 1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object, result);
                        if (cursor.hasNext()) {
                                return Long.parseLong(cursor.next().get("kf_id").toString().trim());
                        }
                } catch (Exception e) {
                }
                return null;
        }
        
        public boolean updateUdesk(Long id, Long udeskId) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", id);
                        DBObject value = new BasicDBObject();
                        value.put("$set", new BasicDBObject("udesk_id", udeskId));
                        return getDBCollection(TABLE_NAME).update(object, value).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        public Long findUdeskById(Long id) {
                try {
                        DBObject object = new BasicDBObject("id", id);
                        DBObject result = new BasicDBObject("udesk_id", 1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object, result);
                        if (cursor.hasNext()) {
                                return Long.parseLong(cursor.next().get("udesk_id").toString().trim());
                        }
                } catch (Exception e) {
                }
                return null;
        }

        public Long findIdest() {
                Long id = null;
                try {
                        DBObject sort = new BasicDBObject();
                        sort.put("id", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find().sort(sort).limit(1);
                        if (cursor.hasNext()) {
                                id = Long.parseLong(cursor.next().get("id").toString().trim());
                        } else {
                                id = 100L;
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        id = 100L;
                }
                return id;
        }

        public Info insertAndGet(Info info) {
                try {
                        DBObject value = new BasicDBObject();
                        Long id = findIdest() + 1;
                        info.setId(id);
                        value.put("id", id);
                        value.put("user_id", info.getUserId());
                        value.put("name", info.getName());
                        value.put("email", info.getEmail());
                        value.put("company", info.getCompany());
                        value.put("phone", info.getPhone());
                        value.put("login_time", info.getLoginTime());
                        value.put("create_time", info.getCreateTime());
                        value.put("data_time", info.getDataTime());
                        value.put("admin_id", info.getAdminId());
                        value.put("project", info.getProject());
                        value.put("qq", info.getQq());
                        if (getDBCollection(TABLE_NAME).insert(value).getN() > -1) {
                                return info;
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
        
        public boolean update_xiaoshouyi(Info info, String lableId){
                try{
                        DBObject object = new BasicDBObject();
                        DBObject value = new BasicDBObject();
                        value.put("xiaoshouyi", info.getXiaoshouyi());
                        if(lableId != null && lableId.length() > 0){
                                value.put("xiaoshouyi_lable_id", lableId);
                        }
                        object.put("id", info.getId());
                        return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set",value)).getN() > -1;
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        public boolean delete(Long id) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("id", id);
                        return getDBCollection(TABLE_NAME).remove(object).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        
        public List<Info> search_from(String from, String start, String end){
                List<Info> infos = new ArrayList<Info>();
                try{
                        DBObject object = new BasicDBObject();
                        Pattern pattern = Pattern.compile("^.*" + from.trim() + ".*$", Pattern.CASE_INSENSITIVE);
                        object.put("comming", pattern);
                        BasicDBList list = new BasicDBList();
                        list.add(new BasicDBObject("create_time", new BasicDBObject("$gte", start)));
                        list.add(new BasicDBObject("create_time", new BasicDBObject("$lt", end)));
                        object.put("$and", list);
                        object.put("user_id", new BasicDBObject("$gt", 0L));
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while(cursor.hasNext()){
                                infos.add(getInfoFromResult(cursor.next()));
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return infos;
        }
        
        public List<Info> search_from_one(String from, String start, String end){
                List<Info> infos = new ArrayList<Info>();
                try{
                        DBObject object = new BasicDBObject();
                        Pattern pattern = Pattern.compile("^.*" + from.trim() + ".*$", Pattern.CASE_INSENSITIVE);
                        object.put("comming", pattern);
                        BasicDBList list = new BasicDBList();
                        list.add(new BasicDBObject("create_time", new BasicDBObject("$gte", start)));
                        list.add(new BasicDBObject("create_time", new BasicDBObject("$lt", end)));
                        object.put("$and", list);
                        object.put("user_id", new BasicDBObject("$gt", 0L));
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while(cursor.hasNext()){
                                infos.add(getInfoFromResult(cursor.next()));
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return infos;
        }
        
        public List<Info> findByFrom(String from){
                List<Info> infos = new ArrayList<Info>();
                try{
                        DBObject object = new BasicDBObject();
                        Pattern pattern = Pattern.compile("^.*" + from + ".*$", Pattern.CASE_INSENSITIVE);
                        object.put("comming", pattern);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while(cursor.hasNext()){
                                infos.add(getInfoFromResult(cursor.next()));
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return infos;
        }

        public List<Info> search(String email, String name, String phone, String qq, boolean in, String userId) {
                List<Info> infos = null;
                try {
                        DBObject object = new BasicDBObject();
                        BasicDBList list = new BasicDBList();
                        if (phone != null && !phone.trim().equals("")) {
                                Pattern pattern = Pattern.compile("^.*" + phone.trim() + ".*$", Pattern.CASE_INSENSITIVE);
                                list.add(new BasicDBObject("phone", pattern));
                        }
                        if (name != null && !name.trim().equals("")) {
                                Pattern pattern = Pattern.compile("^.*" + name.trim() + ".*$", Pattern.CASE_INSENSITIVE);
                                list.add(new BasicDBObject("name", pattern));
                        }
                        /*if (company != null && !company.trim().equals("")) {
                                Pattern pattern = Pattern.compile("^.*" + company.trim() + ".*$", Pattern.CASE_INSENSITIVE);
                                list.add(new BasicDBObject("company", pattern));
                        }
                        if (company != null && !company.trim().equals("")) {
                                Pattern pattern = Pattern.compile("^.*" + company.trim() + ".*$", Pattern.CASE_INSENSITIVE);
                                list.add(new BasicDBObject("project", pattern));
                        }*/
                        if (email != null && !email.trim().equals("")) {
                                Pattern pattern = Pattern.compile("^.*" + email.trim() + ".*$", Pattern.CASE_INSENSITIVE);
                                list.add(new BasicDBObject("email", pattern));
                        }
                        if (qq != null && !qq.trim().equals("")) {
                                list.add(new BasicDBObject("qq", qq));
                        }
                        if (userId != null && !userId.trim().equals("")) {
                            list.add(new BasicDBObject("user_id",  Long.parseLong(userId)));
                        }
                        if (list.size() <= 0) {
                                return null;
                        }
                        object.put("$or", list);
                        DBObject sort = new BasicDBObject("create_time", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                        infos = new ArrayList<Info>();
                        while (cursor.hasNext()) {
                                infos.add(getInfoFromResult(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return infos;
        }

        public Info getInfoFromResult(DBObject object) {
                Info info = null;
                try {
                        int status = 0;
                        try {
                                status = Integer.parseInt(object.get("status").toString().trim());
                        } catch (Exception e) {
                        }
                        Long id = Long.parseLong(object.get("id").toString());
                        Long userId = null;
                        try {
                                userId = Long.parseLong(object.get("user_id").toString());
                        } catch (Exception e) {
                        }
                        String name = null;
                        try {
                                name = object.get("name").toString();
                        } catch (Exception e) {
                        }
                        String email = null;
                        try {
                                email = object.get("email").toString();
                        } catch (Exception e) {
                        }
                        String dataTime = null;
                        try {
                                dataTime = object.get("data_time").toString();
                        } catch (Exception e) {
                        }
                        String loginTime = null;
                        try {
                                loginTime = object.get("login_time").toString();
                        } catch (Exception e) {
                        }
                        String createTime = null;
                        try {
                                createTime = object.get("create_time").toString();
                        } catch (Exception e) {
                        }
                        String phone = null;
                        try {
                                phone = object.get("phone").toString();
                        } catch (Exception e) {
                        }
                        String company = null;
                        try {
                                company = object.get("company").toString();
                        } catch (Exception e) {
                        }
                        String language = null;
                        try {
                                language = object.get("language").toString();
                        } catch (Exception e) {
                        }
                        String kfId = null;
                        try {
                                kfId = object.get("kf_id").toString();
                        } catch (Exception e) {
                        }
                        Long support = null;
                        try {
                                support = Long.parseLong(object.get("support").toString().trim());
                        } catch (Exception e) {
                        }
                        Long sale = null;
                        try {
                                sale = Long.parseLong(object.get("sale").toString().trim());
                        } catch (Exception e) {
                        }
                        Long adminId = null;
                        try {
                                adminId = Long.parseLong(object.get("admin_id").toString().trim());
                        } catch (Exception e) {
                        }
                        Long preSale = null;
                        try {
                                preSale = Long.parseLong(object.get("preSale").toString().trim());
                        } catch (Exception e) {
                        }
                        String project = null;
                        try {
                                project = object.get("project").toString();
                        } catch (Exception e) {
                        }
                        String qq = null;
                        try {
                                qq = object.get("qq").toString();
                        } catch (Exception e) {
                        }
                        Long customer = null;
                        try {
                                customer = Long.parseLong(object.get("customer").toString().trim());
                        } catch (Exception e) {
                        }
                        int payLevel = 0;
                        try{
                                payLevel = Integer.parseInt(object.get("pay_level").toString().trim());
                        }catch(Exception e){}
                        String expireTime = null;
                        try{
                                expireTime = object.get("pay_expire_time").toString();
                        }catch(Exception e){}
                        String payTime = null;
                        try{
                                payTime = object.get("pay_time").toString();
                        }catch(Exception e){}
                        String comming = null;
                        try{
                                 comming = object.get("comming").toString();
                        }catch(Exception e){}
                        Long xiaoshouyi = null;
                        try{
                                xiaoshouyi = Long.parseLong(object.get("xiaoshouyi").toString().trim());
                        }catch(Exception e){}
                        Long xiaoshouyiAdmin = null;
                        try{
                                xiaoshouyiAdmin = Long.parseLong(object.get("xiaoshouyiAdmin").toString().trim());
                        }catch(Exception e){}
                        int gender = 0;
                        try{
                                gender = Integer.parseInt(object.get("gender").toString().trim());
                        }catch(Exception e){}
                        String contectTime = null;
                        try {
                        		contectTime = object.get("contect_time").toString();
						} catch (Exception e) {
							// TODO: handle exception
						}
                        int emailstatus = 0;
                        try {
                        		emailstatus = Integer.parseInt(object.get("email_status").toString().trim());
						} catch (Exception e) {
							// TODO: handle exception
						}
                        int title = 0;
                        try{
                                title = Integer.parseInt(object.get("title").toString());
                        }catch(Exception e){}
                        info = new Info(userId, name, email, company, phone, loginTime, createTime, language, kfId, adminId, support, sale, preSale, customer, status, qq, project,emailstatus);
                        info.setTitle(title);
                        info.setContectTime(contectTime);
                        info.setDataTime(dataTime);
                        info.setId(id);
                        info.setPayLevel(payLevel);
                        info.setPayTime(payTime);
                        info.setExpireTime(expireTime);
                        info.setComming(comming);
                        info.setEmailstatus(emailstatus);
                        if(comming != null){
                                if(comming.equals("invite")){
                                        info.setFrom("ÑûÇë");
                                }else if(comming.equals("qingcloud")){
                                        info.setFrom("ÇàÔÆ");
                                }else {
                                        info.setFrom(comming);
                                }
                        }
                        info.setXiaoshouyi(xiaoshouyi);
                        info.setXiaoshouyiAdmin(xiaoshouyiAdmin);
                        info.setGender(gender);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return info;
        }

}
