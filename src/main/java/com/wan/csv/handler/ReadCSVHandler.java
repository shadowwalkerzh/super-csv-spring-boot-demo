package com.wan.csv.handler;

import com.alibaba.fastjson.JSON;
import com.wan.csv.bean.CSVBean;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.constraint.LMinMax;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuowan on 2017/3/15 14:11.
 * Description:
 */
public class ReadCSVHandler {

    public static void main(String[] args) throws Exception {
        ReadCSVHandler readCSVHandler = new ReadCSVHandler();
        readCSVHandler.csvBeanReader();
    }

   public void  csvBeanReader() throws Exception{
       ICsvBeanReader reader = null;
       try {
            reader = new CsvBeanReader(new FileReader("test_list_common.csv"), CsvPreference.STANDARD_PREFERENCE);
           final String[] headers = reader.getHeader(true);
           final CellProcessor[] processors = getProcessors();
           List<CSVBean> dataList = new ArrayList<>();
           CSVBean csvBean;
           while ((csvBean = reader.read(CSVBean.class, headers, processors)) != null) {
               dataList.add(csvBean);
           }
           System.out.println("handler size:" + dataList.size() + " ,content: " + JSON.toJSONString(dataList));
       } finally {
           if (reader != null) {
               reader.close();
           }
       }
   }

    private CellProcessor[] getProcessors() {
       return new CellProcessor[] {
           new LMinMax(0L, LMinMax.MAX_LONG),
           new NotNull(),
           new NotNull(),
           new ParseDate("dd/MM/yyyy")
       };

   }

}
