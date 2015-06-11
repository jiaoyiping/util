package com.jiaoyiping.lucene;

import org.apache.lucene.analysis.Tokenizer;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 焦一平
 * Date: 2015/4/8
 * Time: 22:45
 * To change this template use File | Settings | File Templates.
 */
public class YPTokenizer extends Tokenizer {
    @Override
    public boolean incrementToken() throws IOException {
        clearAttributes();
        return false;
    }
}
