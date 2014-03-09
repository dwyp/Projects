/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobboard.logic;

import com.jobboard.util.Copier;
import com.jobboard.dao.JobBoardDao;
import com.jobboard.dao.JobBoardDaoFeedImpl;
import com.jobboard.model.JobPost;
import com.sun.syndication.io.FeedException;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author daniel
 */
public class CopierTest {

    private JobBoardDaoFeedImpl feedDao;
    private JobBoardDao databaseDao;

    public CopierTest() {
        try {
            this.feedDao = new JobBoardDaoFeedImpl("all_jobs.atom");
        } catch (IOException ex) {
            Logger.getLogger(CopierTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(CopierTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FeedException ex) {
            Logger.getLogger(CopierTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        // Ask Spring for my DAO
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        databaseDao = (JobBoardDao) ctx.getBean("jobBoardDao");
        
        // get and clean database
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from jobs");
        cleaner.execute("delete from authors");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of copyJobMap method, of class Copier.
     */
    @Test
    public void testCopyJobMap() {
        System.out.println("copyJobMap");
        Map<Integer, JobPost> jobMap = feedDao.getJobMap();
        JobBoardDao dao = databaseDao;
        
        Copier instance = new Copier();
        instance.copyJobMap(jobMap, dao);
        
        assertEquals(feedDao.size(), databaseDao.size());
        
        JobPost job1 = feedDao.getJob(53012);
        JobPost job2 = databaseDao.getJob(53012);
        
        assertEquals(job1.getAuthor(), job2.getAuthor());
        assertEquals(job1.getContent(), job2.getContent());
        assertEquals(job1.getPublished(), job2.getPublished());
        assertEquals(job1.getUpdated(), job2.getUpdated());
        assertEquals(job1.getNumber(), job2.getNumber());
        assertEquals(job1.getTitle(), job2.getTitle());
    }

}
