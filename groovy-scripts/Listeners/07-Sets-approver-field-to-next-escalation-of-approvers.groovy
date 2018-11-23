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
def customerField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Customer Request Type"}
def approversField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Approvers"}

// test scripts
log.debug customerField
log.debug issue.getCustomFieldValue(customerField)
log.debug issue.getCustomFieldValue(customerField).toString()


if (issue.issueTypeObject.name == "Standalone Technical Task") {
    def apprv = []
    for (user in issue.getCustomFieldValue(approversField)) {
        def userValue = user.toString()
        def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
        log.debug "username = " + username
        apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
    }
    apprv.add(ComponentAccessor.getUserManager().getUserByName("lenore.vassil"))
    log.debug apprv
	approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
}
