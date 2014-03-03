/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobboard.dao;

import com.jobboard.model.JobPost;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author daniel
 */
public class JobBoardDaoFeedImplTest {
    
    private JobBoardDaoFeedImpl dao = new JobBoardDaoFeedImpl("file:///home/daniel/Dropbox/GitHub/DanielWypiszynski/OSU%20Project/JobBoard/src/test/resources/all_jobs.atom");
    
    public JobBoardDaoFeedImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class JobBoardDaoFeedImpl.
     */
    @Test
    public void testGetJobMap() {
        System.out.println("getJobMap");
        
        JobPost job1 = new JobPost();
        JobPost job2 = new JobPost();
        
        job1.setNumber(53077);
        job1.setPublished("Sat Mar 01 01:05:41 EST 2014");
        job1.setUpdated("Sat Mar 01 01:05:41 EST 2014");
        job1.setTitle("Head Coach");
        job1.setContent("Develops, directs, and coaches an intercollegiate spirit squad program.  Conducts all practices and rehearsals.  Instructs athletes in the proper technique for fundamentals of dance choreography for routines and cheerleading including tumbling, stunting, etc; responsible for the development of all routines and performances in cooperation with assistant coaches and choreographers as part of a comprehensive game atmosphere initiative; coordinates participation and performances at home football games and other sports as requested; manages requests for cheerleaders and Brutus participation in department, University, community and private events; manages all administrative aspects of the spirit squad including scheduling, travel, equipment needs, budget, and staffing; manages all college-prep clinics, cheerleader recruiting and selection process, appearances, and competitions.  Monitors academic progress of spirit squad.  Participates in development activities to promote the program.");
        job1.setAuthor("Athletics");
        
        job2.setNumber(53061);
        job2.setPublished("Sat Mar 01 01:05:21 EST 2014");
        job2.setUpdated("Sat Mar 01 01:05:21 EST 2014");
        job2.setTitle("Custodial Worker");
        job2.setContent("The Custodial Worker maintains a safe and clean environment in and around assigned Student Life residence halls, offices and other properties for student residents, guests and staff; responsible for day-to-day cleaning as well as other housekeeping duties involved with special housing operations; cleans rooms, offices and public areas; cleans most flat surfaces, furniture, windows, vents, and light fixtures; may count, issue, and receive linen; operates basic electrical cleaning equipment; maintains floors and carpets, moves furniture, removes trash, clears snow, and cleans building entrances and walkways; reports any damage or theft discovered in work area; may be asked to respond to emergencies; climbs ladders and scaffolding.");
        job2.setAuthor("Housekeeping-North District");
        
        JobPost job3 = dao.getJobMap().get(53077);
        JobPost job4 = dao.getJobMap().get(53061);
        
        assertEquals(job1.getAuthor(), job3.getAuthor());
        assertEquals(job1.getContent(), job3.getContent());
        assertEquals(job1.getPublished(), job3.getPublished());
        assertEquals(job1.getUpdated(), job3.getUpdated());
        assertEquals(job1.getNumber(), job3.getNumber());
        assertEquals(job1.getTitle(), job3.getTitle());
        
        assertEquals(job2.getAuthor(), job4.getAuthor());
        assertEquals(job2.getContent(), job4.getContent());
        assertEquals(job2.getPublished(), job4.getPublished());
        assertEquals(job2.getUpdated(), job4.getUpdated());
        assertEquals(job2.getNumber(), job4.getNumber());
        assertEquals(job2.getTitle(), job4.getTitle());
    }    
    
    @Test
    public void testSize() {
        System.out.println("size");
        
        assertEquals(105, dao.size());
    }    
    
    @Test
    public void testGetJob() {
        System.out.println("getJob");
        
        JobPost job1 = new JobPost();
        JobPost job2 = dao.getJob(53077);
        
        job1.setNumber(53077);
        job1.setPublished("Sat Mar 01 01:05:41 EST 2014");
        job1.setUpdated("Sat Mar 01 01:05:41 EST 2014");
        job1.setTitle("Head Coach");
        job1.setContent("Develops, directs, and coaches an intercollegiate spirit squad program.  Conducts all practices and rehearsals.  Instructs athletes in the proper technique for fundamentals of dance choreography for routines and cheerleading including tumbling, stunting, etc; responsible for the development of all routines and performances in cooperation with assistant coaches and choreographers as part of a comprehensive game atmosphere initiative; coordinates participation and performances at home football games and other sports as requested; manages requests for cheerleaders and Brutus participation in department, University, community and private events; manages all administrative aspects of the spirit squad including scheduling, travel, equipment needs, budget, and staffing; manages all college-prep clinics, cheerleader recruiting and selection process, appearances, and competitions.  Monitors academic progress of spirit squad.  Participates in development activities to promote the program.");
        job1.setAuthor("Athletics");
        
        assertEquals(job1.getAuthor(), job2.getAuthor());
        assertEquals(job1.getContent(), job2.getContent());
        assertEquals(job1.getPublished(), job2.getPublished());
        assertEquals(job1.getUpdated(), job2.getUpdated());
        assertEquals(job1.getNumber(), job2.getNumber());
        assertEquals(job1.getTitle(), job2.getTitle());
    }    
}
