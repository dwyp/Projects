/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobboard.util;

import com.jobboard.dao.JobBoardDao;
import com.jobboard.model.JobPost;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author daniel
 */
public class Copier {

    public Map<Integer,JobPost> copyJobMap(Map<Integer, JobPost> jobMap, JobBoardDao dao) {
        
        Set<Map.Entry<Integer, JobPost>> entrySet = jobMap.entrySet();
        Map<Integer,JobPost> daoMap = dao.getJobMap();
        
        for (Entry entry : entrySet) {
            int key = (int)entry.getKey();
            if (daoMap.get(key) == null) {
                dao.add((JobPost)entry.getValue());
            } else {
                // if dao's updated string does not equal jobMap's entry's udpated string, update the dao
                String daoUpdated = daoMap.get(key).getUpdated();
                String jobMapUpdated = jobMap.get(key).getUpdated();
                
                if (!daoUpdated.equals(jobMapUpdated)) dao.update((JobPost)entry.getValue());
                
                // remove job from daoMap so we can return any jobs that weren't in the feed but were in the database
                daoMap.remove(key);
            }
        }
        return daoMap;
    }
}
