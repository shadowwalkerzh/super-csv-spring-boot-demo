package com.wan.csv.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhuowan on 2017/3/15 14:20.
 * Description:
 */
@Data public class CSVBean implements Serializable{

    private Long id;
    private String username;
    private String password;
    private Date createTime;

}
