package com.jiaoyiping.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created with IntelliJ IDEA.
 * User: 焦一平
 * Date: 2015/6/11
 * Time: 6:28
 * To change this template use File | Settings | File Templates.
 * 本类作用:检测一个目录下的jar包是否存在冲突
 */
public class DependencyResolver {

    private static Logger logger = Logger.getLogger(DependencyResolver.class);

    public static Map<String,String> getConflictJars(String jarFilePath){
        File file = new File(jarFilePath);
        if(!file.exists()){
            logger.error("所指定的jar包路径不存在");
            return null;
        }
        Map<String,String> allEntry = new HashMap<>();
        Map<String,String> resultMap = new HashMap<>();
        File[] files = file.listFiles();
        for(File file1:files){
            if(file1.getName().endsWith(".jar")){
                try {
                    Map<String,String> entries = getJarEntry(new JarFile(file1));
                    for(String entry:entries.keySet()){
                        if (allEntry.containsKey(entry)){
                            logger.debug("检测到jar包冲突："+entry+"存在于"+allEntry.get(entry)+","+entries.get(entry)+"两个包下");
                            resultMap.put(entry, allEntry.get(entry) + " == " + entries.get(entry));
                            break;
                        }
                        allEntry.put(entry, entries.get(entry));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return resultMap;
    }
    private static Map<String,String> getJarEntry(JarFile jarFile){
        if(jarFile==null){
            return null;
        }
        Map<String,String> result = new HashMap<>();
        Enumeration<JarEntry> entryEnumeration = jarFile.entries();
        while (entryEnumeration.hasMoreElements()){
            JarEntry jarEntry = entryEnumeration.nextElement();
            if(jarEntry.isDirectory() ||!jarEntry.getName().endsWith(".class")){
                continue;
            }
            result.put((jarEntry.getName().substring(0,jarEntry.getName().length()-6)).replace("/","."),jarFile.getName());
        }
        return result;
    }

}
