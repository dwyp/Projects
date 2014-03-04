/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobboard.dao;

import com.jobboard.model.JobPost;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 *
 * @author daniel
 */
public class JobBoardDaoDbImpl implements JobBoardDao {

    // prepared statements for MySQL DB
    private static final String SQL_INSERT_JOBPOST
            = "INSERT INTO jobs (number, published, updated, title, content, authorId) VALUES (?,?,?,?,?,?)";

    private static final String SQL_REMOVE_JOBPOST
            = "DELETE FROM jobs WHERE number = ?";

    private static final String SQL_COUNT_JOBS
            = "SELECT COUNT(*) FROM jobs";

    private static final String SQL_SELECT_JOBPOST
            = "SELECT * FROM jobs INNER JOIN authors "
            + "ON jobs.authorId=authors.authorId WHERE number=?";

    private static final String SQL_LIST_JOBS
            = "SELECT * FROM jobs INNER JOIN authors "
            + "ON jobs.authorId=authors.authorId";

    private static final String SQL_UPDATE_JOBPOST
            = "UPDATE jobs SET published=?, updated=?, title=?, content=?, authorId=? "
            + "WHERE number=?";

    private static final String SQL_COUNT_AUTHORIDS
            = "SELECT COUNT(*) FROM jobs WHERE authorId=?";

    private static final String SQL_REMOVE_AUTHORID
            = "DELETE FROM authors WHERE authorId=?";

    private static final String SQL_SELECT_AUTHORID
            = "SELECT authorId FROM authors WHERE authorName=?";

    private static final String SQL_INSERT_AUTHOR
            = "INSERT into authors (authorName) VALUES (?)";

    private static final String SQL_COUNT_AUTHORS_WITH_NAME
            = "SELECT COUNT(*) FROM authors WHERE authorName=?";

    private static final String SQL_SEARCH
            = "SELECT * FROM jobs INNER JOIN authors "
            + "ON jobs.authorId=authors.authorId WHERE "
            + "title REGEXP ? OR "
            + "authorName REGEXP ? OR "
            + "content REGEXP ? OR "
            + "number REGEXP ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public JobPost add(JobPost job) {

        // if authorName count is 0, create Author and Id
        if (jdbcTemplate.queryForObject(SQL_COUNT_AUTHORS_WITH_NAME, Integer.class, job.getAuthor()) == 0) {
            jdbcTemplate.update(SQL_INSERT_AUTHOR, job.getAuthor());
        }

        // get author id (newly created or otherwise)
        int authorId = jdbcTemplate.queryForObject(SQL_SELECT_AUTHORID, Integer.class, job.getAuthor());

        // insert job into jobs table
        jdbcTemplate.update(SQL_INSERT_JOBPOST, job.getNumber(), job.getPublished(),
                job.getUpdated(), job.getTitle(), job.getContent(), authorId);

        // used if we're going to use an auto increamented number from the database
        // job.setNumber(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return job;
    }

    @Override
    public void remove(int jobNumber) {

        // get author id using job number
        int authorId = jdbcTemplate.queryForObject(SQL_SELECT_AUTHORID, Integer.class, getJob(jobNumber).getAuthor());

        // remove job post from jobs table
        jdbcTemplate.update(SQL_REMOVE_JOBPOST, jobNumber);

        // if no other jobs use that author id, delete from authors table
        if (jdbcTemplate.queryForObject(SQL_COUNT_AUTHORIDS, Integer.class, authorId) == 0) {
            jdbcTemplate.update(SQL_REMOVE_AUTHORID, authorId);
        }
    }

    @Override
    public int size() {
        // returns total number of jobs in jobs table
        return jdbcTemplate.queryForObject(SQL_COUNT_JOBS, Integer.class);
    }

    @Override
    public JobPost update(JobPost job) {
        // if authorName count is 0, create Author and Id
        if (jdbcTemplate.queryForObject(SQL_COUNT_AUTHORS_WITH_NAME, Integer.class, job.getAuthor()) == 0) {
            jdbcTemplate.update(SQL_INSERT_AUTHOR, job.getAuthor());
        }

        // get author id (newly created or otherwise)
        int authorId = jdbcTemplate.queryForObject(SQL_SELECT_AUTHORID, Integer.class, job.getAuthor());

        // update job in jobs table
        jdbcTemplate.update(SQL_UPDATE_JOBPOST, job.getPublished(), job.getUpdated(),
                job.getTitle(), job.getContent(), authorId, job.getNumber());

        return job;
    }

    @Override
    public Map<Integer, JobPost> getJobMap() {
        // returns a map of JobPosts from a joined jobs and authors table
        // returns null if an exception is thrown

        try {
            List<JobPost> jList = jdbcTemplate.query(SQL_LIST_JOBS, new JobMapper());
            Map<Integer, JobPost> map = new HashMap<>();
            for (JobPost job : jList) {
                map.put(job.getNumber(), job);
            }

            return map;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public JobPost getJob(int jobNumber) {
        // returns a single JobPost from a joined jobs and authors table
        // returns null if an exception is thrown

        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_JOBPOST, new JobMapper(), jobNumber);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public JobPost[] search(String[] keywords) {

        // checks for blank search terms
        if (keywords.length > 0 && !keywords[0].equals("")) {
            // string builder to create regexp search term
            StringBuilder sb = new StringBuilder();
            
            sb.append(keywords[0]);

            // inserts | for OR function in REGEXP
            for (int i = 1; i < keywords.length; i++) {
                sb.append("|");
                sb.append(keywords[i]);

            }
            String regexp = sb.toString();
            
            // OR query with search terms for all columns
            List<JobPost> jlist = jdbcTemplate.query(SQL_SEARCH, new JobMapper(), regexp, regexp, regexp, regexp);
            return jlist.toArray(new JobPost[0]);
        } else {
            // query that returns all database entries in response to blank search terms
            List<JobPost> jlist = jdbcTemplate.query(SQL_LIST_JOBS, new JobMapper());
            return jlist.toArray(new JobPost[0]);
        }
    }

    private static final class JobMapper implements ParameterizedRowMapper<JobPost> {
        // Mapper class for joined jobs and authors tables

        @Override
        public JobPost mapRow(ResultSet rs, int rowNum) throws SQLException {
            JobPost job = new JobPost();

            // maps columns to object
            job.setNumber(Integer.parseInt(rs.getString("number")));
            job.setPublished(rs.getString("published"));
            job.setUpdated(rs.getString("updated"));
            job.setTitle(rs.getString("title"));
            job.setContent(rs.getString("content"));
            job.setAuthor(rs.getString("authorName"));

            return job;
        }
    }
}
