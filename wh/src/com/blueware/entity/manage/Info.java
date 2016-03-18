package com.blueware.entity.manage;

import java.util.List;


public class Info {

        private Long id;

        private Long userId;
        
        private String userIdPaixu;

        private String name;

        private String email;

        private String company;

        private String project;

        private String phone;

        private String loginTime;

        private String createTime;

        private String language;

        private String dataTime;

        private Long support;

        private String saleName;

        private String supportName;

        private Long sale;

        private Long preSale;

        private String preSaleName;

        private int status;

        private Long adminId;

        private String kfId;

        private int role;

        private int assign;
        
        private int title;
        
        private String position;


        private int level;

        private String version;

        private String noteTime;

        private String metricTime;

        private String loudouTime;


        private int custom;


        private String qq;

        private Long customer;

        private int payLevel;

        private String payTime;

        private String comming;

        private String expireTime;
        private int daoqi;
        private String pay_level;
        
        private int gender;
        
        private Long xiaoshouyi;
        
        private Long xiaoshouyiAdmin;
        
        
        private String from;
        
        private String contectTime;
        
        private int emailstatus;
        
        private Long groupId;
        
        public Info() {
        }

        public Info(Long userId, String name, String email, String company, String phone, String loginTime, String createTime, String language, String kfId, Long adminId, Long support, Long sale, Long preSale, Long customer, int status, String qq, String project, int emailstatus) {
                setUserId(userId);
                setName(name);
                setEmail(email);
                setCompany(company);
                setPhone(phone);
                setLoginTime(loginTime);
                setCreateTime(createTime);
                setLanguage(language);
                setKfId(kfId);
                setAdminId(adminId);
                setSupport(support);
                setSale(sale);
                setPreSale(preSale);
                setStatus(status);
                setQq(qq);
                setProject(project);
                setCustomer(customer);
                setEmailstatus(emailstatus);
                if(userId != null){
                        setUserIdPaixu(userId.toString());
                }
        }
        
        public Long getUserId() {
                return userId;
        }

        public void setUserId(Long userId) {
                this.userId = userId;
        }

        public String getDataTime() {
                return dataTime;
        }

        public void setDataTime(String dataTime) {
                this.dataTime = dataTime;
        }

        public String getLoginTime() {
                return loginTime;
        }

        public void setLoginTime(String loginTime) {
                this.loginTime = loginTime;
        }

        public String getCreateTime() {
                return createTime;
        }

        public void setCreateTime(String createTime) {
                this.createTime = createTime;
        }

        public String getLanguage() {
                return language;
        }

        public void setLanguage(String language) {
                this.language = language;
        }


        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getCompany() {
                return company;
        }

        public void setCompany(String company) {
                this.company = company;
        }


        public String getKfId() {
                return kfId;
        }

        public void setKfId(String kfId) {
                this.kfId = kfId;
        }

        public int getStatus() {
                return status;
        }

        public void setStatus(int status) {
                this.status = status;
        }



        public Long getSupport() {
                return support;
        }

        public void setSupport(Long support) {
                this.support = support;
        }

        public Long getSale() {
                return sale;
        }

        public void setSale(Long sale) {
                this.sale = sale;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }


        public Long getAdminId() {
                return adminId;
        }

        public void setAdminId(Long adminId) {
                this.adminId = adminId;
        }


        public String getSaleName() {
                return saleName;
        }

        public void setSaleName(String saleName) {
                this.saleName = saleName;
        }

        public String getSupportName() {
                return supportName;
        }

        public void setSupportName(String supportName) {
                this.supportName = supportName;
        }

        public int getRole() {
                return role;
        }

        public void setRole(int role) {
                this.role = role;
        }

        public int getAssign() {
                return assign;
        }

        public void setAssign(int assign) {
                this.assign = assign;
        }

        public Long getPreSale() {
                return preSale;
        }

        public void setPreSale(Long preSale) {
                this.preSale = preSale;
        }

        public String getPreSaleName() {
                return preSaleName;
        }

        public void setPreSaleName(String preSaleName) {
                this.preSaleName = preSaleName;
        }

        public int getLevel() {
                return level;
        }

        public void setLevel(int level) {
                this.level = level;
        }

        public String getVersion() {
                return version;
        }

        public void setVersion(String version) {
                this.version = version;
        }

        public String getNoteTime() {
                return noteTime;
        }

        public void setNoteTime(String noteTime) {
                this.noteTime = noteTime;
        }

        public String getMetricTime() {
                return metricTime;
        }

        public void setMetricTime(String metricTime) {
                this.metricTime = metricTime;
        }

        public String getLoudouTime() {
                return loudouTime;
        }

        public void setLoudouTime(String loudouTime) {
                this.loudouTime = loudouTime;
        }


        public int getCustom() {
                return custom;
        }

        public void setCustom(int custom) {
                this.custom = custom;
        }


        public String getProject() {
                return project;
        }

        public void setProject(String project) {
                this.project = project;
        }

        public String getQq() {
                return qq;
        }

        public void setQq(String qq) {
                this.qq = qq;
        }

        public Long getCustomer() {
                return customer;
        }

        public void setCustomer(Long customer) {
                this.customer = customer;
        }


        public int getPayLevel() {
                return payLevel;
        }

        public void setPayLevel(int payLevel) {
                this.payLevel = payLevel;
        }

        public String getPayTime() {
                return payTime;
        }

        public void setPayTime(String payTime) {
                this.payTime = payTime;
        }

        public String getExpireTime() {
                return expireTime;
        }

        public void setExpireTime(String expireTIme) {
                this.expireTime = expireTIme;
        }

        public int getDaoqi() {
                return daoqi;
        }

        public void setDaoqi(int daoqi) {
                this.daoqi = daoqi;
        }

        public String getComming() {
                return comming;
        }

        public void setComming(String comming) {
                this.comming = comming;
        }

        public int getGender() {
                return gender;
        }

        public void setGender(int gender) {
                this.gender = gender;
        }

        public Long getXiaoshouyi() {
                return xiaoshouyi;
        }

        public void setXiaoshouyi(Long xiaoshouyi) {
                this.xiaoshouyi = xiaoshouyi;
        }

        public Long getXiaoshouyiAdmin() {
                return xiaoshouyiAdmin;
        }

        public void setXiaoshouyiAdmin(Long xiaoshouyiAdmin) {
                this.xiaoshouyiAdmin = xiaoshouyiAdmin;
        }


        public String getPay_level() {
                return pay_level;
        }

        public void setPay_level(String pay_level) {
                this.pay_level = pay_level;
        }


        public String getFrom() {
                return from;
        }

        public void setFrom(String from) {
                this.from = from;
        }

        public String getUserIdPaixu() {
                return userIdPaixu;
        }

        public void setUserIdPaixu(String userIdPaixu) {
                this.userIdPaixu = userIdPaixu;
        }

        public String getContectTime() {
                return contectTime;
        }

        public void setContectTime(String contectTime) {
                this.contectTime = contectTime;
        }


		public int getEmailstatus() {
			return emailstatus;
		}

		public void setEmailstatus(int emailstatus) {
			this.emailstatus = emailstatus;
		}

		public Long getGroupId() {
			return groupId;
		}

		public void setGroupId(Long groupId) {
			this.groupId = groupId;
		}
                public int getTitle() {
                        return title;
                }

                public void setTitle(int title) {
                        this.title = title;
                }

                public String getPosition() {
                        return position;
                }

                public void setPosition(String position) {
                        this.position = position;
                }


        
}
