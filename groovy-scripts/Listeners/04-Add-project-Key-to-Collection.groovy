package com.cprime.subtask.updatecomponents

import com.atlassian.jira.bc.project.component.ProjectComponent
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.event.type.EventDispatchOption
import com.atlassian.jira.issue.index.IssueIndexingService
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.IssueManager
import com.atlassian.jira.issue.MutableIssue
import com.atlassian.jira.user.ApplicationUser

log.setLevel(org.apache.log4j.Level.INFO)

Collection<String> projects = ["WWIIM", "SEC", "PPL"]

log.debug("Project key: " + event.getProject().getKey().toString())
if (!projects.contains(event.getProject().getKey())) { return }

def updateComponents(Issue _issue) {
	Collection<ProjectComponent> components = _issue.getParentObject().getComponents()
	IssueManager issueManager = ComponentAccessor.getIssueManager()
	MutableIssue mutableIssue = issueManager.getIssueObject(_issue.getKey())
	ApplicationUser currentUser = ComponentAccessor.getJiraAuthenticationContext()?.getLoggedInUser()
	IssueIndexingService indexManager = ComponentAccessor.getComponent(IssueIndexingService.class)
	mutableIssue.setComponent(components)
	issueManager.updateIssue(currentUser, mutableIssue, EventDispatchOption.DO_NOT_DISPATCH, false)
	indexManager.reIndex(_issue)
}

Issue issue = event.getIssue()
log.debug("Event issue: " + issue.toString())

if (issue.isSubTask()) {
	Collection<ProjectComponent> components = issue.getParentObject().getComponents()
	log.debug("Parent components: " + components.toString())
	if (components.isEmpty()) { return }
	updateComponents(issue)
} else {
	Collection<Issue> subTasks = issue.getSubTaskObjects()
	log.debug("My sub-tasks: " + subTasks.toString())
	if (subTasks.isEmpty()) { return }

	Collection<ProjectComponent> components = issue.getComponents()
	log.debug("My components: " + components.toString())
	if (components.isEmpty()) { return }

	subTasks.each() {
		updateComponents(it)
	}
}
