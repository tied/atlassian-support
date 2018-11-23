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

// set logging
def log = Logger.getLogger("com.acme.CreateSubtask")
log.setLevel(Level.DEBUG)

// logging
import org.apache.log4j.Logger
import org.apache.log4j.Level

Issue issue = event.getIssue()
Long projectId = issue.getProjectObject().getId()
CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager()

// fields to check
def regionField = customFieldManager.getCustomFieldObjectByName("Region")

// fields to change

def region = issue.getCustomFieldValue(regionField).toString()



ApplicationUser currentUser = ComponentAccessor.getJiraAuthenticationContext()?.getLoggedInUser()

// route by region
def issue_type = issue.issueTypeObject.name
log.debug issue_type
log.debug region
if (issue_type == "Support Request") {
    if(region == "China"){
		IssueManager issueManager = ComponentAccessor.getIssueManager()
		log.debug issueManager
        MutableIssue mutableIssue = issueManager.getIssueObject(issue.getKey())
		log.debug mutableIssue
        mutableIssue.setAssignee(ComponentAccessor.getUserManager().getUserByName("luke.hu@wework.com"))
		issueManager.updateIssue(currentUser, mutableIssue, EventDispatchOption.ISSUE_ASSIGNED, true)
	}
	
	else if(region=="Australia" || region=="Europe" || region=="Israel"){
        IssueManager issueManager = ComponentAccessor.getIssueManager()
		log.debug issueManager
        MutableIssue mutableIssue = issueManager.getIssueObject(issue.getKey())
		log.debug mutableIssue
		mutableIssue.setAssignee(ComponentAccessor.getUserManager().getUserByName("filip.spiers@wework.com"))
		issueManager.updateIssue(currentUser, mutableIssue, EventDispatchOption.ISSUE_ASSIGNED, true)

    }
	
	else if(region=="Japan"){
		IssueManager issueManager = ComponentAccessor.getIssueManager()
		log.debug issueManager
        MutableIssue mutableIssue = issueManager.getIssueObject(issue.getKey())
		log.debug mutableIssue
		mutableIssue.setAssignee(ComponentAccessor.getUserManager().getUserByName("kazuhiro.hosaka@wework.com"))
		issueManager.updateIssue(currentUser, mutableIssue, EventDispatchOption.ISSUE_ASSIGNED, true)
    }

	else if(region=="Latin America"){
		IssueManager issueManager = ComponentAccessor.getIssueManager()
		log.debug issueManager
        MutableIssue mutableIssue = issueManager.getIssueObject(issue.getKey())
		log.debug mutableIssue
		mutableIssue.setAssignee(ComponentAccessor.getUserManager().getUserByName("matt.vierling"))
		issueManager.updateIssue(currentUser, mutableIssue, EventDispatchOption.ISSUE_ASSIGNED, true)
	}	

	else if(region=="Pacific"){
		IssueManager issueManager = ComponentAccessor.getIssueManager()
		log.debug issueManager
        MutableIssue mutableIssue = issueManager.getIssueObject(issue.getKey())
		log.debug mutableIssue
		mutableIssue.setAssignee(ComponentAccessor.getUserManager().getUserByName("xinting.zhang"))
		issueManager.updateIssue(currentUser, mutableIssue, EventDispatchOption.ISSUE_ASSIGNED, true)
	}

	else if(region=="Tri-State" || region=="US & Canada - East" || region=="US & Canada - West"){
		IssueManager issueManager = ComponentAccessor.getIssueManager()
		log.debug issueManager
        MutableIssue mutableIssue = issueManager.getIssueObject(issue.getKey())
		log.debug mutableIssue
		mutableIssue.setAssignee(ComponentAccessor.getUserManager().getUserByName("dylain.cohen"))
		issueManager.updateIssue(currentUser, mutableIssue, EventDispatchOption.ISSUE_ASSIGNED, true)
    }
}
