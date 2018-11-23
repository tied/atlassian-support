package com.cprime.ylasman.jira.assigncomponent

import com.atlassian.jira.bc.project.component.ProjectComponent
import com.atlassian.jira.bc.project.component.ProjectComponentManager
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.servicedesk.api.requesttype.RequestType
import com.atlassian.servicedesk.api.requesttype.RequestTypeService
import com.atlassian.jira.user.ApplicationUser
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.IssueManager
import com.atlassian.jira.issue.MutableIssue
import com.atlassian.jira.event.type.EventDispatchOption
import com.atlassian.jira.issue.index.IssueIndexingService

Issue issue = event.getIssue()
Long projectId = issue.getProjectObject().getId()
CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager()
CustomField regionField = customFieldManager.getCustomFieldObject('customfield_13716')
CustomField requestTypeField = customFieldManager.getCustomFieldObjectByName("Customer Request Type")
Object regionValue = issue.getCustomFieldValue(regionField)


if (regionValue == null || requestTypeField == null) { return }

ApplicationUser currentUser = ComponentAccessor.getJiraAuthenticationContext()?.getLoggedInUser()
RequestTypeService requestTypeService = ComponentAccessor.getOSGiComponentInstanceOfType(RequestTypeService)
def sourceIssueRequestTypeQuery = requestTypeService.newQueryBuilder().issue(issue.id).build()
def requestTypeEither = ComponentAccessor.getOSGiComponentInstanceOfType(RequestTypeService).getRequestTypes(currentUser,sourceIssueRequestTypeQuery)
def cost_centerField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Default Cost Center"}


if (requestTypeEither.isLeft()) {
    log.error "${requestTypeEither.left().get()}"
    return
}
def requestType = requestTypeEither.right.results[0]
log.setLevel(org.apache.log4j.Level.DEBUG)
log.debug(requestType)
String componentName = requestType.getName() + "_" + regionValue?.get(null) + "_" + regionValue?.get("1")
log.debug("Searching for component '$componentName'.")
ProjectComponentManager projectComponentManager = ComponentAccessor.getProjectComponentManager()
ProjectComponent component = projectComponentManager.findByComponentName(projectId, componentName)
Collection <ProjectComponent> components = []
components.add(component)
if (component) {
    log.debug("Found component: '$component'")
	IssueManager issueManager = ComponentAccessor.getIssueManager()
	MutableIssue mutableIssue = issueManager.getIssueObject(issue.getKey())
	IssueIndexingService indexManager = ComponentAccessor.getComponent(IssueIndexingService.class)
    if (!issue.getAssignee()) { mutableIssue.setAssignee(component.getComponentLead()) }
	mutableIssue.setComponent(components)
//	issueManager.updateIssue(currentUser, mutableIssue, EventDispatchOption.DO_NOT_DISPATCH, false)
    issueManager.updateIssue(currentUser, mutableIssue, EventDispatchOption.ISSUE_ASSIGNED, true)
	indexManager.reIndex(issue)
}
