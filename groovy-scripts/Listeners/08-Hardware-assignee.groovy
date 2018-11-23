import com.onresolve.jira.groovy.user.FieldBehaviours
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.user.ApplicationUser
import com.atlassian.jira.issue.ModifiedValue

import com.atlassian.jira.issue.Issue

import com.atlassian.jira.ComponentManager
import com.atlassian.jira.issue.comments.CommentManager
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.user.util.UserUtil;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder
import com.atlassian.jira.issue.util.IssueChangeHolder

// logging
import static com.atlassian.jira.issue.IssueFieldConstants.*
import org.apache.log4j.Logger
import org.apache.log4j.Level
  
def log = Logger.getLogger("com.acme.CreateSubtask")
log.setLevel(Level.DEBUG)

// construct shortcuts
def customFieldManager = ComponentAccessor.getCustomFieldManager()
def userUtil = ComponentAccessor.getUserUtil()
def changeHolder = new DefaultIssueChangeHolder()
def issueManager = ComponentAccessor.getIssueManager()
def issueService = ComponentAccessor.getIssueService()
def userManager = ComponentAccessor.getUserManager()
def issue = event.issue as Issue 

// fields to check
def regionField = customFieldManager.getCustomFieldObjectByName("Region")
def driveField = customFieldManager.getCustomFieldObjectByName("Change Type")
def emailField = customFieldManager.getCustomFieldObjectByName("Email")
def locationField = customFieldManager.getCustomFieldObjectByName("Location")
def approversField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Approvers"}
def equipField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Equipment"}

// fields to change
def newRegion
def apprv = []
def loc
def region = issue.getCustomFieldValue(regionField).toString()
def drive = issue.getCustomFieldValue(driveField).toString()
def email = issue.getReporter().getEmailAddress()
def equip = issue.getCustomFieldValue(equipField).toString()


log.debug region

// add approvers for application requests


// route by region
if (issue.issueTypeObject.name == "IT Help") {
  if (equip == "Mifi" || equip == "iPad") {
    apprv.add(ComponentAccessor.getUserManager().getUserByName("brittney.robertson"))
}  
}
if (apprv) {
    log.debug apprv
	approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
}
