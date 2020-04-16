package flowable;

import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HolidayRequest {
    public static void main(String[] args) {
        //创建流程引擎
        ProcessEngine processEngine = createProcessEngine();

        //部署流程定义
        /*
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("holiday-request.bpmn20.xml")
                .deploy();
        System.out.println("deployment id: " + deployment.getId());

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        System.out.println("Found process definition : " + processDefinition.getName());
         */

        //启动流程实例
        RuntimeService runtimeService = processEngine.getRuntimeService();

        Map<String, Object> variables = new HashMap<String, Object>();
//        variables.put("employee", "jack");
//        variables.put("nrOfHolidays", 3);
//        variables.put("description", "回家看看");
        Student student = new Student();
        student.setAge(3);
        student.setName("sun");
        variables.put("student",student);
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("holidayRequest", variables);
        student.setReason("回家看看");

        //查询任务
        /*
        TaskService taskService = processEngine.getTaskService();

        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
        System.out.println("You have " + tasks.size() + " tasks:");
        for (int i=0; i<tasks.size(); i++) {
            System.out.println((i+1) + ") " + tasks.get(i).getName());
        }

        Task task = tasks.get(0);
        Map<String, Object> processVariables = taskService.getVariables(task.getId());
        System.out.println(processVariables.get("employee") + " wants " +
                processVariables.get("nrOfHolidays") + " of holidays. Do you approve this?");
        //完成任务
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("approved", true);
        taskService.complete(task.getId(), variables);

        List<Task> otherTasks = taskService.createTaskQuery().taskCandidateOrAssigned("jack").list();
        System.out.println("-----jack hava " + otherTasks.size() + " tasks-----");
        for (int i=0; i<otherTasks.size(); i++) {
            System.out.println((i+1) + ") " + otherTasks.get(i).getName());
        }

        Task otherTask = otherTasks.get(0);
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("approved", true);
        taskService.complete(otherTask.getId(), variables);
         */

        //查询历史数据
        /*
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricActivityInstance> activities =
                historyService.createHistoricActivityInstanceQuery()
                .processInstanceId("2501")
                .finished()
                .orderByHistoricActivityInstanceEndTime().asc()
                .list();
        for (HistoricActivityInstance activity: activities) {
            System.out.println(activity.getActivityId() + "  took  " + activity.getDurationInMillis() + "  ms");
        }
         */
    }

    public static ProcessEngine createProcessEngine(){
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://localhost:3306/flowable?useUnicode=true&zeroDateTimeBehavior=convertToNull")
                .setJdbcUsername("root")
                .setJdbcPassword("root")
                .setJdbcDriver("com.mysql.jdbc.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);


        ProcessEngine processEngine = cfg.buildProcessEngine();
        return processEngine;
    }
}

class Student implements Serializable {
    private String name;
    private Integer age;
    private String reason;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
