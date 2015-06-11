package com.jiaoyiping.lucene;

import org.apache.lucene.analysis.Analyzer;

/**
 * Created with IntelliJ IDEA.
 * User: 焦一平
 * Date: 2015/4/8
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 * YP分词器
 */
public class YPAnalyzer extends Analyzer {
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        YPTokenizer ypTokenizer = new YPTokenizer();
        return new TokenStreamComponents(ypTokenizer);
    }
}
