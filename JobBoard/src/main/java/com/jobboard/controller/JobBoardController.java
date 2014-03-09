package com.jobboard.controller;

import com.jobboard.util.Copier;
import com.jobboard.dao.JobBoardDao;
import com.jobboard.dao.JobBoardDaoFeedImpl;
import com.jobboard.model.JobPost;
import com.sun.syndication.io.FeedException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JobBoardController {

    private JobBoardDao databaseDao;
    private Map<Integer, JobPost> currentJobs;
    private Map<Integer, JobPost> expiredJobs;

    @Inject
    public JobBoardController(JobBoardDao dao) {
        // gets DAO bean from Spring
        this.databaseDao = dao;
    }

    @RequestMapping(value = {"/", "loadFeed"}, method = RequestMethod.GET)
    public String loadFeed() {

        // create an instance of the JobBoardDaoFeedImpl, which loads the feed from the site
        JobBoardDao feedDao;
        try {
            feedDao = new JobBoardDaoFeedImpl("https://www.jobsatosu.com/all_jobs.atom");

            // copy the feed into the database
            Copier copyFeed = new Copier();

            // gets map of the feed and places it in currentJobs
            currentJobs = feedDao.getJobMap();
            // updates database and returns map of jobs only in the database
            expiredJobs = copyFeed.copyJobMap(feedDao.getJobMap(), databaseDao);

        } catch (IOException ex) {
            Logger.getLogger(JobBoardController.class.getName()).log(Level.SEVERE, null, ex);
            if (currentJobs != null && expiredJobs != null) {
                return "displayErrorContinue";
            } else {
                return "displayOsuError";
            }
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(JobBoardController.class.getName()).log(Level.SEVERE, null, ex);
            if (currentJobs != null && expiredJobs != null) {
                return "displayErrorContinue";
            } else {
                return "displayOsuError";
            }
        } catch (FeedException ex) {
            Logger.getLogger(JobBoardController.class.getName()).log(Level.SEVERE, null, ex);
            if (currentJobs != null && expiredJobs != null) {
                return "displayErrorContinue";
            } else {
                return "displayOsuError";
            }
        }
        return "redirect:displayJobs";
    }

    @RequestMapping(value = "/displayJobs", method = RequestMethod.GET)
    public String displayJobs(Map<String, Object> model) {
        try {
            // converts currentJobs to array to pass to jsp
            model.put("jobs", currentJobs.values().toArray(new JobPost[0]));
            // converts expiredJobs to array to pass to jsp
            model.put("expiredJobs", expiredJobs.values().toArray(new JobPost[0]));
            return "displayJobsView";
        } catch (Exception ex) {
            return "displayErrorContinue";
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String displaySearchResults(Map<String, Object> model,
            HttpServletRequest req,
            HttpServletResponse res) {

        try {
            // gets keywords parameter and turns into array
            String[] keywords = req.getParameter("keywords").split(" ");

            // gets all jobs from database that match keywords
            JobPost[] searchResults = databaseDao.search(keywords);

            // will hold search results jobs that are expired
            Map<Integer, JobPost> searchedExpiredJobs = new HashMap<>();

            // will hold search results jobs that are current
            Map<Integer, JobPost> searchedCurrentJobs = new HashMap<>();

            // gets set of keys from current job to compare  against
            Set<Integer> currentKeys = currentJobs.keySet();

            // seperates search results into current and expired jobs
            for (JobPost job : searchResults) {
                if (currentKeys.contains(job.getNumber())) {
                    searchedCurrentJobs.put(job.getNumber(), job);
                } else {
                    searchedExpiredJobs.put(job.getNumber(), job);
                }
            }

            // converts to arrays and places search results in model for view
            model.put("jobs", searchedCurrentJobs.values().toArray(new JobPost[0]));
            model.put("expiredJobs", searchedExpiredJobs.values().toArray(new JobPost[0]));

            // places "keywords" string in model to refill search bar
            model.put("keywords", req.getParameter("keywords"));

            return "displayJobsView";
        } catch (Exception ex) {
            return "displayErrorContinue";
        }
    }
}
