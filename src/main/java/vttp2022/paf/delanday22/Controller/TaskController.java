package vttp2022.paf.delanday22.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;
import vttp2022.paf.delanday22.Model.Task;
import vttp2022.paf.delanday22.Model.User;
import vttp2022.paf.delanday22.Repository.TaskRepository;
import vttp2022.paf.delanday22.Service.UserService;

@Controller
@RequestMapping(path="/task")
public class TaskController {
    
    @Autowired
    private UserService userSvc;

    @Autowired
    private TaskRepository taskRepo;

    @PostMapping
    public String postTask (@RequestBody MultiValueMap<String,String> form, Model model) {

        User user = new User();
        user.setUsername(form.getFirst("username"));
        user.setPassword(form.getFirst("password"));

        if (!userSvc.authenticate(user)) {
            return "fail-auth";
        }

        Task task = new Task();
        task.setTaskName(form.getFirst("taskName"));
        task.setPriority(form.getFirst("priority"));
        task.setAssignTo(user);
        task.setCompletionDate(form.getFirst("completion"));

        Integer count = taskRepo.createTask(task);

        System.out.printf(">>>> %s, count: %d\n", task.toString(), count);

        return "createdTask";

    }

}
