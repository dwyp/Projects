/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobboard.dao;

import com.jobboard.model.JobPost;
import java.util.Map;
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
public class JobBoardDaoDbImplTest {
    
    private JobBoardDao dao;
    
    public JobBoardDaoDbImplTest() {
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
        dao = (JobBoardDao) ctx.getBean("jobBoardDao");
        
        // get and clean database
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from jobs");
        cleaner.execute("delete from authors");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class JobBoardDaoDbImpl.
     */
    @Test
    public void testAddRemoveSizeGetJob() {
        System.out.println("add, remove, size, getJob");
        
        JobPost job1 = new JobPost();
        JobPost job2 = new JobPost();
        
        job1.setNumber(53077);
        job1.setPublished("2014-03-01T01:05:41-05:00");
        job1.setUpdated("2014-03-01T01:05:41-05:00");
        job1.setTitle("Head Coach");
        job1.setContent("Develops, directs, and coaches an intercollegiate spirit squad program.  Conducts all practices and rehearsals.  Instructs athletes in the proper technique for fundamentals of dance choreography for routines and cheerleading including tumbling, stunting, etc; responsible for the development of all routines and performances in cooperation with assistant coaches and choreographers as part of a comprehensive game atmosphere initiative; coordinates participation and performances at home football games and other sports as requested; manages requests for cheerleaders and Brutus participation in department, University, community and private events; manages all administrative aspects of the spirit squad including scheduling, travel, equipment needs, budget, and staffing; manages all college-prep clinics, cheerleader recruiting and selection process, appearances, and competitions.  Monitors academic progress of spirit squad.  Participates in development activities to promote the program.");
        job1.setAuthor("Athletics");
        
        job2.setNumber(53061);
        job2.setPublished("2014-03-01T01:05:21-05:00");
        job2.setUpdated("2014-03-01T01:05:21-05:00");
        job2.setTitle("Custodial Worker");
        job2.setContent("The Custodial Worker maintains a safe and clean environment in and around assigned Student Life residence halls, offices and other properties for student residents, guests and staff; responsible for day-to-day cleaning as well as other housekeeping duties involved with special housing operations; cleans rooms, offices and public areas; cleans most flat surfaces, furniture, windows, vents, and light fixtures; may count, issue, and receive linen; operates basic electrical cleaning equipment; maintains floors and carpets, moves furniture, removes trash, clears snow, and cleans building entrances and walkways; reports any damage or theft discovered in work area; may be asked to respond to emergencies; climbs ladders and scaffolding.");
        job2.setAuthor("Housekeeping-North District");
        
        // adds jobs to DB
        dao.add(job1);
        dao.add(job2);
        
        // size test
        assertEquals(2, dao.size());
        
        // getJob test
        assertEquals(job1.getAuthor(), dao.getJob(53077).getAuthor());
        assertEquals(job1.getContent(), dao.getJob(53077).getContent());
        assertEquals(job1.getPublished(), dao.getJob(53077).getPublished());
        assertEquals(job1.getUpdated(), dao.getJob(53077).getUpdated());
        assertEquals(job1.getNumber(), dao.getJob(53077).getNumber());
        assertEquals(job1.getTitle(), dao.getJob(53077).getTitle());
        
        // remove test
        dao.remove(53077);
        
        assertEquals(1, dao.size());
        assertEquals(job2.getNumber(), dao.getJob(53061).getNumber());
    } 
    
    @Test
    public void testUpdateGetJobMap() {
        System.out.println("update, getJobMap");
        
        JobPost job1 = new JobPost();
        JobPost job2 = new JobPost();
        
        job1.setNumber(53077);
        job1.setPublished("2014-03-01T01:05:41-05:00");
        job1.setUpdated("2014-03-01T01:05:41-05:00");
        job1.setTitle("Head Coach");
        job1.setContent("Develops, directs, and coaches an intercollegiate spirit squad program.  Conducts all practices and rehearsals.  Instructs athletes in the proper technique for fundamentals of dance choreography for routines and cheerleading including tumbling, stunting, etc; responsible for the development of all routines and performances in cooperation with assistant coaches and choreographers as part of a comprehensive game atmosphere initiative; coordinates participation and performances at home football games and other sports as requested; manages requests for cheerleaders and Brutus participation in department, University, community and private events; manages all administrative aspects of the spirit squad including scheduling, travel, equipment needs, budget, and staffing; manages all college-prep clinics, cheerleader recruiting and selection process, appearances, and competitions.  Monitors academic progress of spirit squad.  Participates in development activities to promote the program.");
        job1.setAuthor("Athletics");
        
        job2.setNumber(53061);
        job2.setPublished("2014-03-01T01:05:21-05:00");
        job2.setUpdated("2014-03-01T01:05:21-05:00");
        job2.setTitle("Custodial Worker");
        job2.setContent("The Custodial Worker maintains a safe and clean environment in and around assigned Student Life residence halls, offices and other properties for student residents, guests and staff; responsible for day-to-day cleaning as well as other housekeeping duties involved with special housing operations; cleans rooms, offices and public areas; cleans most flat surfaces, furniture, windows, vents, and light fixtures; may count, issue, and receive linen; operates basic electrical cleaning equipment; maintains floors and carpets, moves furniture, removes trash, clears snow, and cleans building entrances and walkways; reports any damage or theft discovered in work area; may be asked to respond to emergencies; climbs ladders and scaffolding.");
        job2.setAuthor("Housekeeping-North District");
        
        dao.add(job1);
        dao.add(job2);
        
        // set job1 to different job for update test

        job1.setPublished("2014-02-25T10:37:17-05:00");
        job1.setUpdated("2014-02-25T10:37:17-05:00");
        job1.setTitle("Post Doctoral Researcher");
        job1.setContent("The Post-Doctoral Researcher will independently perform all aspects of an assigned research project(s) centered on questions regarding the mechanisms modulating ocular morphogenesis during embryonic development; responsible for design and execution of experiments, recording of experimental progress, collection and interpretation of data, maintenance of mouse lines, and production of research reports and manuscripts for publication; presentation of research progress at regular lab meetings and other university organizations; attendance and presentation at scientific conferences; guidance and support of laboratory research staff; monitoring and maintaining laboratory reagents, equipment and cell lines as assigned; initiating collaborative efforts with internal and external researchers as appropriate; preparation of training grant applications and undertaking any other relevant duties for laboratory research as assigned by the principal investigator.");
        job1.setAuthor("Optometry");
        
        // update test
        dao.update(job1);
        
        assertEquals(job1.getAuthor(), dao.getJob(53077).getAuthor());
        assertEquals(job1.getContent(), dao.getJob(53077).getContent());
        assertEquals(job1.getPublished(), dao.getJob(53077).getPublished());
        assertEquals(job1.getUpdated(), dao.getJob(53077).getUpdated());
        assertEquals(job1.getNumber(), dao.getJob(53077).getNumber());
        assertEquals(job1.getTitle(), dao.getJob(53077).getTitle());
        
        // getJobMap test
        Map<Integer,JobPost> jobMap = dao.getJobMap();
        
        assertEquals(2, jobMap.size());
        assertEquals(job1.getAuthor(), jobMap.get(53077).getAuthor());
        assertEquals(job1.getContent(), jobMap.get(53077).getContent());
        assertEquals(job1.getPublished(), jobMap.get(53077).getPublished());
        assertEquals(job1.getUpdated(), jobMap.get(53077).getUpdated());
        assertEquals(job1.getNumber(), jobMap.get(53077).getNumber());
        assertEquals(job1.getTitle(), jobMap.get(53077).getTitle());
        assertEquals(job2.getAuthor(), jobMap.get(53061).getAuthor());
        assertEquals(job2.getContent(), jobMap.get(53061).getContent());
        assertEquals(job2.getPublished(), jobMap.get(53061).getPublished());
        assertEquals(job2.getUpdated(), jobMap.get(53061).getUpdated());
        assertEquals(job2.getNumber(), jobMap.get(53061).getNumber());
        assertEquals(job2.getTitle(), jobMap.get(53061).getTitle());
    }
    
    @Test
    public void testSearch() {
        System.out.println("search");
        
        JobPost job1 = new JobPost();
        JobPost job2 = new JobPost();
        
        job1.setNumber(53077);
        job1.setPublished("2014-03-01T01:05:41-05:00");
        job1.setUpdated("2014-03-01T01:05:41-05:00");
        job1.setTitle("Head Coach");
        job1.setContent("Develops, directs, and coaches an intercollegiate spirit squad program.  Conducts all practices and rehearsals.  Instructs athletes in the proper technique for fundamentals of dance choreography for routines and cheerleading including tumbling, stunting, etc; responsible for the development of all routines and performances in cooperation with assistant coaches and choreographers as part of a comprehensive game atmosphere initiative; coordinates participation and performances at home football games and other sports as requested; manages requests for cheerleaders and Brutus participation in department, University, community and private events; manages all administrative aspects of the spirit squad including scheduling, travel, equipment needs, budget, and staffing; manages all college-prep clinics, cheerleader recruiting and selection process, appearances, and competitions.  Monitors academic progress of spirit squad.  Participates in development activities to promote the program.");
        job1.setAuthor("Athletics");
        
        job2.setNumber(53061);
        job2.setPublished("2014-03-01T01:05:21-05:00");
        job2.setUpdated("2014-03-01T01:05:21-05:00");
        job2.setTitle("Custodial Worker");
        job2.setContent("The Custodial Worker maintains a safe and clean environment in and around assigned Student Life residence halls, offices and other properties for student residents, guests and staff; responsible for day-to-day cleaning as well as other housekeeping duties involved with special housing operations; cleans rooms, offices and public areas; cleans most flat surfaces, furniture, windows, vents, and light fixtures; may count, issue, and receive linen; operates basic electrical cleaning equipment; maintains floors and carpets, moves furniture, removes trash, clears snow, and cleans building entrances and walkways; reports any damage or theft discovered in work area; may be asked to respond to emergencies; climbs ladders and scaffolding.");
        job2.setAuthor("Housekeeping-North District");
        
        dao.add(job1);
        dao.add(job2);
     
        String[] keywords1 = {"53077", "53061"};
        assertEquals(2,dao.search(keywords1).length);
        
        String[] keywords2 = {"53077"};
        assertEquals(1,dao.search(keywords2).length);
        
        String[] keywords3 = {};
        assertEquals(2,dao.search(keywords1).length);
        
        
    }
}
