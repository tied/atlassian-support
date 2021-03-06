
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

// request type
import com.atlassian.servicedesk.api.requesttype.RequestTypeService
import com.atlassian.servicedesk.api.requesttype.RequestType
import com.onresolve.scriptrunner.runner.customisers.PluginModule
import com.onresolve.scriptrunner.runner.customisers.WithPlugin
//import org.apache.commons.math3.util.Precision;
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
//def DueDateField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Due Date" }
def DaysLeftField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Days left" }
def StartDateField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Start Date" }
def customerField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Customer Request Type"}
def email = issue.getReporter().getEmailAddress()

log.debug "Create date = " + issue.getCreated()
log.debug "Due date = " + issue.getDueDate()
log.debug "Due date time = " + issue.getDueDate().time

def String daysleft
def dif=0
if (issue.issueTypeObject.name == "Master Data"){
    if (email.contains("@test.rrr")){
     log.debug "reporteru contine rrr"   
    }
if (issue.getDueDate().time==null || issue.getCustomFieldValue(StartDateField)==null ){
	daysleft = null;
    MutableIssue mutableIssue = issueManager.getIssueObject(issue.getKey())
	log.debug "primu if" + mutableIssue
    log.debug daysleft
	DaysLeftField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(DaysLeftField), daysleft),changeHolder);
 
}
	else {
    	MutableIssue mutableIssue = issueManager.getIssueObject(issue.getKey())
		log.debug "al doilea if" + mutableIssue
        //log.debug "dif= " + (issue.getCustomFieldValue(DueDateField).getTime() - issue.getCreated().getTime())/ 1000 / 3600 / 24
   		//dif= (issue.getCustomFieldValue(DueDateField).getTime() - issue.getCreated().getTime())/ 1000 / 3600 / 24
		log.debug "dif= " + (issue.getDueDate().time - issue.getCustomFieldValue(StartDateField).getTime())/ 1000 / 3600 / 24
        daysleft=dif.toString()
        daysleft=daysleft.substring(0, daysleft.indexOf('.')+2)
        log.debug daysleft
		DaysLeftField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(DaysLeftField), daysleft),changeHolder);
    }
	}
