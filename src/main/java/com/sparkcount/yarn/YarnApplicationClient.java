package com.sparkcount.yarn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YarnApplicationClient {

    private final static Logger logger = LoggerFactory.getLogger(YarnApplicationClient.class);

    public YarnApplicationClient(Configuration configuration){

        YarnClient client = YarnClient.createYarnClient();
        client.init(configuration);
        client.start();
    }
}
