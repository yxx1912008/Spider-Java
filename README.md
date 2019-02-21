# Spider-Java
>https://github.com/yxx1912008/Spider-Java.git

#### 项目说明

 >生产服务器：树莓派
	
	1. 树莓派 Raspberry-3B 配置为 ：Cpu:ARM  1.2GHz 64-bit  Ram :1gb 
	2. 服务器开启 swap:1.5g
	3. 服务器开启 nginx 1.14
	4. 数据库采用 MYSQL 5.6

>开发服务器：Win7

	 1.开发环境为 Eclipse luna + JDK1.8 +Maven + Memcached
	 2.电脑系统为Windows 7
	 


 本单机项目主要用于 微信小程序后台 微信公众号后台 前后端分离 接口调用。
 
 项目基于Springboot 2.1.1 构建，整合 阿里巴巴 druid，整合 webmagic爬虫框架。
 
 支持将运营数据后台发送至 管理员邮箱列表
 
 支持定时调度任务
 
 整合 ```mybatis.generator``` 数据库自动化映射工具 方便在日常开发中进行数据库 映射
 
 --------------------------------------------------
 
  #### 项目特点
	1.项目配置采用 application.properties 文件配置，生产配置为 application-prod.properties,开发为application-dev.properties
	2.日志系统采用 log4j2 ，生产日志与开发日志分离，自由控制日志粒度。
	3.采用 Memcached 作为业务缓存,提高部分数据访问数据
	4.预埋权限效验功能，为后期单点登录做准备
	5.支持部分静态文件访问
	6.已经配置多数据源，方便不同业务查询
 

 
 #### 项目预览 
 > 小程序 （小程序代码请参考 [点我直达](https://github.com/yxx1912008/ShoppingCat)）

小程序界面预览：
![](https://github.com/yxx1912008/Spider-Java/blob/master/spider/src/main/resources/static/cat/shopcat-about.gif?raw=true)

小程序技术点:
	
	1.小程序采用 VUE 框架进行开发 
	2.支持动态修改主api服务器域名
	3.支持 微信审核状态下的 页面关闭，以规避微信小程序审核
 --------------------------------------------------
 
 >微信公众号：购物猫
 
 公众号界面预览：
 
 ![公众号介绍图](https://github.com/yxx1912008/Spider-Java/blob/master/spider/src/main/resources/static/cat/we-media-about.gif?raw=true)

 
