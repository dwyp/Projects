package com.jobboard.controller;

import com.jobboard.util.Copier;
import com.jobboard.dao.JobBoardDao;
import com.jobboard.dao.JobBoardDaoFeedImpl;
import com.jobboard.model.JobPost;
import java.util.Map;
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
        this.databaseDao = dao;
    }

    @RequestMapping(value = "/loadFeed", method = RequestMethod.GET)
    public String loadFeed() {

        // create an instance of the JobBoardDaoFeedImpl, which loads the feed from the site
        JobBoardDao feedDao = new JobBoardDaoFeedImpl("https://www.jobsatosu.com/all_jobs.atom");

        // copy the feed into the database
        Copier copyFeed = new Copier();
        
        currentJobs = feedDao.getJobMap();
        expiredJobs = copyFeed.copyJobMap(feedDao.getJobMap(), databaseDao);

        return "redirect:displayJobs";
    }

    @RequestMapping(value = "/displayJobs", method = RequestMethod.GET)
    public String displayJobs(Map<String, Object> model) {

        // get map from database, convert to array to pass to jsp
        model.put("jobs", currentJobs.values().toArray(new JobPost[0]));
        model.put("expiredJobs", expiredJobs.values().toArray(new JobPost[0]));
        return "displayJobsView";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String displayJobs(Map<String, Object> model,
            HttpServletRequest req,
            HttpServletResponse res) {

        String[] keywords = req.getParameter("keywords").split(" ");
        StringBuilder keywordsString = new StringBuilder();
        for (String s : keywords) {
            keywordsString.append(s);
            keywordsString.append(" ");
        }

        model.put("jobs", databaseDao.search(keywords));
        model.put("keywords", keywordsString.toString());

        return "displayJobsView";
    }
}
