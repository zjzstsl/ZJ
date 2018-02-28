#!/bin/bash

# ---------------------------------------------------
# 功能：启动指定工程
# 使用：run project_name
# 前提：以run.sh脚本所在的路径为相对路径
#
# 执行逻辑(目前只考虑在开发阶段使用):
#   1. 定位到该工程主目录下;
#   2. 重新编译打包 mvn clean package;
#   3. 关闭之前启动的进程(kill -9);
#   4. 直接启动target目录下jar;
#   目前位置相当于 cd $RPOJ_HOME && mvn clean package && cd target && kill -9 $PID && java -jar $JAVA_MEM_OPTS xxxx.jar
#
# //TODO 对于一个模块启动多个实例的能力还未全面考虑.
# //TODO 可指定是否需要重新编译
# //TODO 可指定启动端口
# //TODO 目前只支持 jar 的启动,后期进行war启动方式的支持
# //TODO 增加bebug参数
# //TODO 增加JMX参数
# //TODO 根据服务器操作系统是否64位，自动调整运行内存
# author: Shiyunlai
# Date: 2018-02-24
# ---------------------------------------------------

function usage(){
    echo "INFO:必须指定工程名称！"
    echo "run.sh {PROJ_NAME}"
    echo "for example：run.sh tools-eureka-server"
}

# 工程名称（必须输入）
PROJ_NAME=""

# 运行内存, 目前只考虑开发阶段使用 //TODO 参照Dubbo的启动文件 start.sh 进行运行内存优化
JAVA_MEM_OPTS="-server -Xms256m -Xmx256m -XX:PermSize=56m"

# 控制台日志输出位置和文件
STDOUT_FILE=target/stdout.log

# ------------------ main process -------------------

if [ $# -eq 0 ]; then
    usage ;
    exit ;
else
    PROJ_NAME=$1
fi

# 启动介质 一般是: 工程名 + "-0.0.1-SNAPSHOT.jar"
RUN_TARGET="target/$PROJ_NAME-0.0.1-SNAPSHOT.jar" ;

# 定位到工程目
cd $PROJ_NAME

# 先清理之前的部署，杀掉进程，删除部署目录($DEPLOY_PATH)
PIDS=`ps -ef | grep java | grep "$PROJ_NAME" | grep -v grep |awk '{print $2}'`
if [ -z "$PIDS" ]; then
    echo "INFO: The $PROJ_NAME not started!"
else
    echo "INFO: The $PROJ_NAME is started! PID is : $PIDS , now kill it ."
    kill -9 $PIDS > /dev/null 2>&1
fi

# 重新编译
mvn clean package

# 直接运行target目录的jar
nohup java -jar $JAVA_MEM_OPTS $RUN_TARGET > $STDOUT_FILE 2>&1 &

tail -f $STDOUT_FILE


