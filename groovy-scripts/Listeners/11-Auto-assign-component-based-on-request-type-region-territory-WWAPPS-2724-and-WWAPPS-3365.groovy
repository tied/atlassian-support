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
Object costValue = issue.getCustomFieldValue(cost_centerField).toString()

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

IssueManager issueManager = ComponentAccessor.getIssueManager()
MutableIssue mutableIssue = issueManager.getIssueObject(issue.getKey())
IssueIndexingService indexManager = ComponentAccessor.getComponent(IssueIndexingService.class)
def reg= ""   

if (component) {
    log.debug("Found component: '$component'")
 if (!issue.getAssignee()) { mutableIssue.setAssignee(component.getComponentLead()) 
	mutableIssue.setComponent(components)
//	issueManager.updateIssue(currentUser, mutableIssue, EventDispatchOption.DO_NOT_DISPATCH, false)
    issueManager.updateIssue(currentUser, mutableIssue, EventDispatchOption.ISSUE_ASSIGNED, true)
	indexManager.reIndex(issue)
		}
	}

	log.debug mutableIssue

if (issue.getCustomFieldValue(requestTypeField).toString() == "pod/b60d5d06-528b-481b-9e1d-ef09173e521f"){      //Create Supervisory
	log.debug "found Create Supervisory "
	log.debug "Region[0]=" + regionValue?.get(null)
	reg=regionValue?.get(null)
	log.debug "cost center=" + costValue
	if (reg.toString() == "Europe, Israel & Australia"){
		if ((costValue == "Building Operations - Building Operations Leadership") || (costValue == "Building Operations - Cleaning & Pantry") || (costValue == "Building Operations - Facilities Maintenance") || (costValue == "Building Operations - Member Technology Services")  || (costValue == "Building Operations - Security")   || (costValue == "Community - Community Leadership & Operational Support") || (costValue == "Community - Community")) {
			log.debug "found cost center"

			mutableIssue.setAssignee(ComponentAccessor.getUserManager().getUserByName("lashaw"))
			issueManager.updateIssue(currentUser, mutableIssue, EventDispatchOption.ISSUE_ASSIGNED, true)
			}
		}
	else if ((reg.toString() == "Global") || (reg.toString() == "US & Canada West") || (reg.toString() == "US & Canada East") || (reg.toString() == "Latin America") || (reg.toString() == "Pacific") || (reg.toString() == "China") || (reg.toString() == "Japan") || (reg.toString() == "India") || (reg.toString() == "Non-WeWork Business Lines")){
			log.debug "found region others"
			if ((costValue == "Building Operations - Building Operations Leadership") || (costValue == "Building Operations - Cleaning & Pantry") || (costValue == "Building Operations - Facilities Maintenance") || (costValue == "Building Operations - Member Technology Services")  || (costValue == "Building Operations - Security")   || (costValue == "Community - Community Leadership & Operational Support") || (costValue == "Community - Community")) {
				log.debug "found cost center"

				mutableIssue.setAssignee(ComponentAccessor.getUserManager().getUserByName("marissa.matarese"))
				issueManager.updateIssue(currentUser, mutableIssue, EventDispatchOption.ISSUE_ASSIGNED, true)
			}
	}		
}	

if (issue.getCustomFieldValue(requestTypeField).toString() == "pod/5ee7658e-6680-40cb-9ed2-3a9f7236c127"){      //Edit Supervisory
	log.debug "found Edit Supervisory "
	log.debug "Region[0]=" + regionValue?.get(null)
	reg=regionValue?.get(null)
	log.debug "cost center=" + costValue
	if (reg.toString() == "Europe, Israel & Australia"){
		if ((costValue == "Building Operations - Building Operations Leadership") || (costValue == "Building Operations - Cleaning & Pantry") || (costValue == "Building Operations - Facilities Maintenance") || (costValue == "Building Operations - Member Technology Services")  || (costValue == "Building Operations - Security")   || (costValue == "Community - Community Leadership & Operational Support") || (costValue == "Community - Community")) {
			log.debug "found cost center"

			mutableIssue.setAssignee(ComponentAccessor.getUserManager().getUserByName("lashaw"))
			issueManager.updateIssue(currentUser, mutableIssue, EventDispatchOption.ISSUE_ASSIGNED, true)
			}
		}
	else if ((reg.toString() == "Global") || (reg.toString() == "US & Canada West") || (reg.toString() == "US & Canada East") || (reg.toString() == "Latin America") || (reg.toString() == "Pacific") || (reg.toString() == "China") || (reg.toString() == "Japan") || (reg.toString() == "India") || (reg.toString() == "Non-WeWork Business Lines")){
			log.debug "found region others"
			if ((costValue == "Building Operations - Building Operations Leadership") || (costValue == "Building Operations - Cleaning & Pantry") || (costValue == "Building Operations - Facilities Maintenance") || (costValue == "Building Operations - Member Technology Services")  || (costValue == "Building Operations - Security")   || (costValue == "Community - Community Leadership & Operational Support") || (costValue == "Community - Community")) {
				log.debug "found cost center"

				mutableIssue.setAssignee(ComponentAccessor.getUserManager().getUserByName("marissa.matarese"))
				issueManager.updateIssue(currentUser, mutableIssue, EventDispatchOption.ISSUE_ASSIGNED, true)
			}
	}		
}	
log.debug "done"
