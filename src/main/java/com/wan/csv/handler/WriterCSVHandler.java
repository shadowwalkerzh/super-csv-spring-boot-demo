package com.wan.csv.handler;

import com.wan.csv.bean.CSVBean;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.constraint.LMinMax;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhuowan on 2017/3/15 14:14.
 * Description:
 */
public class WriterCSVHandler {

    public static void main(String[] args) throws Exception {
        WriterCSVHandler writerCSVHandler = new WriterCSVHandler();
        writerCSVHandler.csvBeanWriter(getProcessors(), getHeaders(), getData(), "test_list_common_batch.csv");
    }

    /**
     * 写入csv
     *
     * @param processors cell processors
     * @param headers    headers
     * @param data       data may be List or Map or Bean
     * @throws Exception
     */
    public void csvBeanWriter(final CellProcessor[] processors, final String[] headers, List data,
        final String fileName) throws Exception {
        ICsvBeanWriter beanWriter = null;
        try {
            //bean写入
            beanWriter = new CsvBeanWriter(new FileWriter(fileName), CsvPreference.STANDARD_PREFERENCE);
            beanWriter.writeHeader(headers);
            for (final Object o : data) {
                beanWriter.write(o, headers, getProcessors());
            }
        } finally {
            if (beanWriter != null) {
                beanWriter.close();
            }
        }
    }

    private static String[] getHeaders() {
        return new String[] {"id", "username", "password", "createTime"};
    }

    private static CellProcessor[] getProcessors() {
        return new CellProcessor[] {
            new LMinMax(0L, LMinMax.MAX_LONG),
            new NotNull(),
            new NotNull(),
            new FmtDate("dd/MM/yyyy")
        };
    }

    protected static List<CSVBean> getData() {
        List<CSVBean> dataList = new ArrayList<>();
        for (int i = 0; i < 50000; i ++) {
            CSVBean bean1 = new CSVBean();
            bean1.setId(Long.valueOf(i));
            bean1.setUsername("name" + i);
            bean1.setPassword("pwd" + i);
            bean1.setCreateTime(new Date());
            dataList.add(bean1);
        }
        return dataList;
    }

}
