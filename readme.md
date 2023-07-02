# 系统说明
会议室预约平台：
* 系统采用jsp+servlet+jdbc技术开发，数据库使用的是mysql。
* 功能包括用户登录，学生管理，教师管理，用户管理，会议室预约，学生签到，学生出席查看，预约记录查看，个人信息修改，密码修改等功能。

# 运行说明
* 导入sql文件
<br>启动MySQL  ->  打开navicat,在localhost里点击运行sql文件 ->  选择sql文件，选择UTF-8编码 ->  点击开始，信息日志里出现success证明导入成功 ->  刷新界面，会出现roombook库
* 导入源码，配置环境
<br>打开eclipse  ->  File  ->  Import  ->  General  ->  Existing Projects into Workspace  ->  选择源码文件  ->  勾选下方Copy projects into workspace  ->  Finish  ->  右键项目，点击Properties  ->  点击Java Build Path  ->  点击Edit  ->  选择JRE版本  ->  Finish  
点击Java Compiler  ->  选择jdk版本  
点击Project Facts  ->  选择Java版本  ->  点击OK
<br>如果错误还未完全消失：
点击Projects  ->  Clean...  ->  选择该项目，点击OK
* 登录
<br>系统访问路径：http://localhost:8080/roombook 
<br>管理员账号密码：admin/123456
<br>学生测试账号：1821310082/123456
<br>教师测试账号：T111/123456
<br>注意：添加教师或者添加学生的时候会添加对应的系统登录用户数据，删除同理！新增用户默认登录密码为123456

# 运行截图
* 登录界面
<br>![1.png](/WebContent/images/1.png)
* 查看个人说明
<br>![2.png](/WebContent/images/2.png)
* 管理员身份添加学生/教师新用户
<br>![3.png](/WebContent/images/3.png)
* 添加预约记录
<br>![4.png](/WebContent/images/4.png)
* 学生刷卡签到
<br>![5.png](/WebContent/images/5.png)