package com.wugy.test.activiti;

import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.wugy.test.spring.BaseTest;

public class LeaveProcessTest extends BaseTest {

	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;

	@Test
	public void testLeaveProcess() {
		String resource = "diagrams/leave/leave.bpmn";
		Deployment deploy = repositoryService.createDeployment().addClasspathResource(resource).deploy();
		Assert.notNull(deploy);
		System.out.println("deployId=" + deploy.getId() + ",deployName=" + deploy.getName());

		// 查询流程定义
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.deploymentId(deploy.getId()).singleResult();
		Long businessKey = new Double(1000000 * Math.random()).longValue();
		// 启动流程
		String processDefinitionId = processDefinition.getId();
		runtimeService.startProcessInstanceById(processDefinitionId, businessKey.toString());
		// 查询任务实例
		List<Task> taskList = taskService.createTaskQuery().processDefinitionId(processDefinitionId).list();
		for (Task task : taskList) {
			System.out.println("Task id=" + task.getId() + ",name=" + task.getName());
		}
	}

}
