## Spark Count [![Build Status](https://travis-ci.org/cmonkey/spark-count.svg?branch=master)](https://travis-ci.org/cmonkey/spark-count)

### Stopping a Running Spark Application  

bin/spark-class org.apache.spark.deploy.Client kill spark://master:7077 driver-20161224163318-0002

### Test Racket

cd src/main/rkt 

racket -t *.rkt

## DevOps Shell

   docker pull opszero/opshell
   docker run -it opszero/opshell
