// modified value
import com.atlassian.jira.issue.ModifiedValue

// users
import com.atlassian.jira.user.ApplicationUser
import com.atlassian.jira.user.util.UserUtil;

// issue
import com.atlassian.jira.issue.Issue

// logging
import org.apache.log4j.Logger
import org.apache.log4j.Level
  
// custom fields

import com.atlassian.jira.ComponentManager
import com.atlassian.jira.issue.comments.CommentManager
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder
import com.atlassian.jira.issue.util.IssueChangeHolder
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.IssueManager
import com.atlassian.jira.issue.MutableIssue
import com.atlassian.jira.user.ApplicationUser
// request type
import com.atlassian.servicedesk.api.requesttype.RequestTypeService
import com.atlassian.servicedesk.api.requesttype.RequestType
import com.onresolve.scriptrunner.runner.customisers.PluginModule
import com.onresolve.scriptrunner.runner.customisers.WithPlugin

// set logging
def log = Logger.getLogger("com.acme.CreateSubtask")
log.setLevel(Level.DEBUG)

// construct shortcuts
def userUtil = ComponentAccessor.getUserUtil()
def changeHolder = new DefaultIssueChangeHolder()
def issueManager = ComponentAccessor.getIssueManager()
def issueService = ComponentAccessor.getIssueService()
def userManager = ComponentAccessor.getUserManager()
def issue = event.issue as Issue 
def customFieldManager = ComponentAccessor.getCustomFieldManager()


// customer request
def requestTypeService = RequestTypeService
def currentUser = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser()
def issuePField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Issue Participants"}
def requestPField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Request participants"}
def modelPField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Reviewer/s"}


def issueP=[]
def requestP=[]
def modelP=[]

//log.debug issuePField
log.debug "issueP: " + issue.getCustomFieldValue(issuePField)

//log.debug modelPField
log.debug "modelPField: " + issue.getCustomFieldValue(modelPField)

//log.debug requestPField
log.debug "requestPField: " + issue.getCustomFieldValue(requestPField)

if (issue.getCustomFieldValue(issuePField).toString() != "" || issue.getCustomFieldValue(requestPField).toString() != "" ) {

        MutableIssue mutableIssue = issueManager.getIssueObject(issue.getKey())
		log.debug mutableIssue
    
    for (user in issue.getCustomFieldValue(issuePField)) {
		def userValue = user.toString()
		def username = userValue.substring(0, userValue.indexOf('('))
		//log.debug "username = " + username
		issueP.add(ComponentAccessor.getUserManager().getUserByName(username))
	}
	for (user in issue.getCustomFieldValue(requestPField)) {
		def userValue = user.toString()
		def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
		//log.debug "username = " + username
		requestP.add(ComponentAccessor.getUserManager().getUserByName(username))
	}
    for (user in issue.getCustomFieldValue(modelPField)) {
		def userValue = user.toString()
		def username = userValue.substring(0, userValue.indexOf('('))
		//log.debug "username = " + username
		modelP.add(ComponentAccessor.getUserManager().getUserByName(username))
	}
	log.debug "issueP = " + issueP
	log.debug "requestP = " + requestP
	log.debug "modelP = " + modelP
	if (issueP !=requestP){
		if (issueP !=modelP){			// s-a modificat in Issue Participants			
			requestP=[]
			modelP=[]
			requestPField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(requestPField), issueP),changeHolder);
			issuePField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(issuePField), issueP),changeHolder);	
			modelPField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(modelPField), issueP),changeHolder);
		}
		else if (requestP !=modelP){		// s-a modificat in Request Participants	
			issueP=[]
			modelP=[]
			requestPField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(requestPField), requestP),changeHolder);
			issuePField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(issuePField), requestP),changeHolder);	
			modelPField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(modelPField), requestP),changeHolder);
		}
	
	}
	else if (issueP == requestP){
		modelPField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(modelPField), issueP),changeHolder);
	}
}
log.debug "Done"	
