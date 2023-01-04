package vttp2022.paf.delanday22.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.paf.delanday22.Model.Task;
import static vttp2022.paf.delanday22.Repository.Queries.*;

@Repository
public class TaskRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer createTask(Task task) {
        
        return jdbcTemplate.update(SQL_INSERT_TASK, task.getTaskName(), task.getPriority().toString(), 
                task.getAssignTo().getUsername(), task.getCompletionDate());

    }
    
}
