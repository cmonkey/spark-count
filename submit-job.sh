#!/bin/bash

### submit wordcount 
~/gitrepo/spark/bin/spark-submit --class com.sparkcount.WordCount \
    --master spark://10.0.0.5:7077 \
    --deploy-mode cluster \
    --executor-memory 5g \
    --name wordcount \
    --conf "spark.app.id=wordcount" \
    --supervise \
    hdfs://10.0.0.3:9000/usr/spark/jars/spark-count_2.11-0.0.1.jar \
    hdfs:/10.0.0.3:9000/usr/input/core-site.xml 20

### submit TestGroupBy
~/gitrepo/spark/bin/spark-submit --class com.sparkcount.TestGroupBy \
    --master spark://10.0.0.5:7077 \
    --deploy-mode cluster \
    --executor-memory 5g \
    --name wordcount \
    --conf "spark.app.id=groupby" \
    --supervise \
    hdfs://10.0.0.3:9000/usr/spark/jars/spark-count_2.11-0.0.1.jar \

### submit GroupKeyCheckPoint 
~/gitrepo/spark/bin/spark-submit --class com.sparkcount.GroupKeyCheckPoint \
    --master spark://10.0.0.5:7077 \
    --deploy-mode cluster \
    --executor-memory 5g \
    --name wordcount \
    --conf "spark.app.id=GroupKeyCheckPoint" \
    --supervise \
    hdfs://10.0.0.3:9000/usr/spark/jars/spark-count_2.11-0.0.1.jar \

