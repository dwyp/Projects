/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobboard.dao;

import com.jobboard.model.JobPost;
import java.util.Map;

/**
 *
 * @author daniel
 */
public interface JobBoardDao {
    
    public JobPost add(JobPost job);
    
    public void remove(int jobNumber);
    
    public int size();
    
    public JobPost update(JobPost job);
    
    public Map<Integer,JobPost> getJobMap();
    
    public JobPost getJob(int jobNumber);
    
    public JobPost[] search(String[] keywords);
    
}
