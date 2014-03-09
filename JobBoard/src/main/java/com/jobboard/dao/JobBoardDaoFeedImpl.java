/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobboard.dao;

import com.jobboard.model.JobPost;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class JobBoardDaoFeedImpl implements JobBoardDao {

    private URL url;
    private File file;
    private XmlReader reader = null;
    private SyndFeed feed = null;

    public JobBoardDaoFeedImpl(String urlString) throws MalformedURLException, IOException, IllegalArgumentException, FeedException {

        if (urlString.contains("http")) {
            url = new URL(urlString);
            reader = new XmlReader(url);
        } else {
            file = new File(urlString);
            reader = new XmlReader(file);
        }
        feed = new SyndFeedInput().build(reader);
    }

    @Override
    public JobPost add(JobPost job) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(int jobNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        // returns size using getJobMap to read
        return getJobMap().size();
    }

    @Override
    public JobPost update(JobPost job) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Integer, JobPost> getJobMap() {

        Map<Integer, JobPost> jobMap = new HashMap<>();

        for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
            SyndEntry entry = (SyndEntry) i.next();

            JobPost job = new JobPost();

            for (SyndContentImpl content : (List<SyndContentImpl>) entry.getContents()) {
                job.setContent(content.getValue());
            }

            job.setAuthor(entry.getAuthor());
            job.setPublished(entry.getPublishedDate().toString());
            job.setUpdated(entry.getUpdatedDate().toString());
            job.setNumber(Integer.parseInt(entry.getUri()));
            job.setTitle(entry.getTitle());

            jobMap.put(job.getNumber(), job);
        }

        return jobMap;
    }

    @Override
    public JobPost getJob(int jobNumber) {
        return getJobMap().get(jobNumber);
    }

    @Override
    public JobPost[] search(String[] keywords) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
