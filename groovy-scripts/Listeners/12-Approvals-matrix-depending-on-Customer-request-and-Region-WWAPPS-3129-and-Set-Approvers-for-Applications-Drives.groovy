// modified value
import com.atlassian.jira.issue.ModifiedValue

// users
import com.atlassian.jira.user.ApplicationUser
import com.atlassian.jira.user.util.UserUtil;

// issue
import com.atlassian.jira.issue.Issue

  
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

// logging
import org.apache.log4j.Logger
import org.apache.log4j.Level

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
def regionField = customFieldManager.getCustomFieldObjectByName("Region")

// customer request
def requestTypeService = RequestTypeService
def currentUser = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser()
def customerField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Customer Request Type"}
def approversField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Approvers"}
def appField = customFieldManager.getCustomFieldObjectByName("Application")
def driveField = customFieldManager.getCustomFieldObjectByName("Change Type")
def emailField = customFieldManager.getCustomFieldObjectByName("Email")
def locationField = customFieldManager.getCustomFieldObjectByName("Location")

def newRegion
def apprv = []
def loc
def app = issue.getCustomFieldValue(appField).toString()
def region = issue.getCustomFieldValue(regionField).toString()
def drive = issue.getCustomFieldValue(driveField).toString()
def email = issue.getReporter().getEmailAddress()

// test scripts
log.debug customerField
log.debug issue.getCustomFieldValue(customerField)
log.debug issue.getCustomFieldValue(customerField).toString()
log.debug region

//1
if (issue.getCustomFieldValue(customerField).toString() == "hqit/f96686b0-0f57-4f83-be66-b4e9e078d08e") {  // custom request type = new hardware
	if (region == "USA & Canada (East)") {
	}
	else if (region == "USA & Canada (West)") {
	}
	else if (region == "Europe, Israel, Australia") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("punit.rajpara"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Latin America") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("gjausoro"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Pacific") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("john.vincent"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "India") {
		if(email.contains("@wework.com")){
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("john.vincent"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
		}
		else if(email.contains("@wework.co.in")){
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("purusothaman.satchithanandam"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
		}
	}	
	else if (region == "China") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("vanness.zhou"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Japan") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("freddy.phan"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}			
}

//2
else if (issue.getCustomFieldValue(customerField).toString() == "hqit/0a0634a0-927b-4904-b3a0-07f3d8344136") {  // custom request type = Damaged Device (HQIT)
	if (region == "USA & Canada (East)") {
	}
	else if (region == "USA & Canada (West)") {
	}
	else if (region == "Europe, Israel, Australia") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("punit.rajpara"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Latin America") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("gjausoro"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Pacific") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("john.vincent"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "India") {
		if(email.contains("@wework.com")){
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("john.vincent"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
		}
		else if(email.contains("@wework.co.in")){
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("purusothaman.satchithanandam"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
		}
	}		
	else if (region == "China") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("vanness.zhou"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Japan") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("freddy.phan"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}			
}

//3
else if (issue.getCustomFieldValue(customerField).toString() == "hqit/af676636-c3b0-4e75-a658-94f05c6ee32a") {  // custom request type = Lost/Stolen Device (HQIT)
	if (region == "USA & Canada (East)") {
	}
	else if (region == "USA & Canada (West)") {
	}
	else if (region == "Europe, Israel, Australia") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("james.allen"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Latin America") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("gjausoro"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Pacific") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("john.vincent"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "India") {
		if(email.contains("@wework.com")){
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("john.vincent"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
		}
		else if(email.contains("@wework.co.in")){
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("purusothaman.satchithanandam"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
		}
	}	
	else if (region == "China") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("vanness.zhou"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Japan") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("freddy.phan"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}			
}

//4
else if (issue.getCustomFieldValue(customerField).toString() == "hqit/0c4899d1-3474-4d57-9fde-48d0a7999598") {  // custom request type = New Accessories (HQIT)
	if (region == "USA & Canada (East)") {
	}
	else if (region == "USA & Canada (West)") {
	}
	else if (region == "Europe, Israel, Australia") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("james.allen"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Latin America") {
	}	
	else if (region == "Pacific") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("john.vincent"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "India") {
		if(email.contains("@wework.com")){
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("john.vincent"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
		}
		else if(email.contains("@wework.co.in")){
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("purusothaman.satchithanandam"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
		}
	}		
	else if (region == "China") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("tma"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Japan") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("freddy.phan"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}			
}

//5
else if (issue.getCustomFieldValue(customerField).toString() == "hqit/e3f04873-7193-4657-8f60-aaa18f6176f4") {  // custom request type = AWS Workspace (HQIT)
	if (region == "USA & Canada (East)") {
	}
	else if (region == "USA & Canada (West)") {
	}
	else if (region == "Europe, Israel, Australia") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("james.allen"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Latin America") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("Tyson.A..Cadorette"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Pacific") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("john.vincent"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "India") {
	}	
	else if (region == "China") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("azhou"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Japan") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("freddy.phan"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}			
}

//6
else if (issue.getCustomFieldValue(customerField).toString() == "hqit/e927575a-b5eb-41b7-84e9-e85b112f5b0f") {  // custom request type = Software (HQIT)
	if (region == "USA & Canada (East)") {
	}
	else if (region == "USA & Canada (West)") {
	}
	else if (region == "Europe, Israel, Australia") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("james.allen"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Latin America") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("gjausoro"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Pacific") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("john.vincent"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "India") {
		if(email.contains("@wework.com")){
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("john.vincent"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
		}
		else if(email.contains("@wework.co.in")){
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("purusothaman.satchithanandam"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
		}
	}	
	else if (region == "China") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("azhou"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Japan") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("freddy.phan"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}			
}

//7
else if (issue.getCustomFieldValue(customerField).toString() == "hqit/8174faf2-3f0a-4f89-9581-bf0b5b26fd4a") {  // custom request type = Drives (HQIT)
	if (region == "USA & Canada (East)") {
	}
	else if (region == "USA & Canada (West)") {
	}
	else if (region == "Europe, Israel, Australia") {
	}	
	else if (region == "Latin America") {
	}	
	else if (region == "Pacific") {
	}	
	else if (region == "India") {
	}	
	else if (region == "China") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("azhou"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Japan") {
	}			
}

//8
else if (issue.getCustomFieldValue(customerField).toString() == "hqit/2c2d5aee-155e-485c-a64e-0ab20719eaa3") {  // custom request type = Create Google Group (HQIT)
	if (region == "USA & Canada (East)") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("brittney.robertson"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}
	else if (region == "USA & Canada (West)") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("franco.azabache"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}
	else if (region == "Europe, Israel, Australia") {
	}	
	else if (region == "Latin America") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("gjausoro"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Pacific") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("john.vincent"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "India") {
	}	
	else if (region == "China") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("tma"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Japan") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("freddy.phan"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}			
}

//9
else if (issue.getCustomFieldValue(customerField).toString() == "hqit/91e3f60e-f9f9-437b-bc21-c3fa1aeab67a") {  // custom request type = Delete Google Group
	if (region == "USA & Canada (East)") {
	}
	else if (region == "USA & Canada (West)") {
	}
	else if (region == "Europe, Israel, Australia") {
	}	
	else if (region == "Latin America") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("gjausoro"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Pacific") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("john.vincent"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "India") {
	}	
	else if (region == "China") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("tma"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Japan") {
	}			
}

//10
else if (issue.getCustomFieldValue(customerField).toString() == "hqit/9e4bb954-0dcc-4043-a78d-bad7cf57ea8d") {  // custom request type = Modify Google Group
	if (region == "USA & Canada (East)") {
	}
	else if (region == "USA & Canada (West)") {
	}
	else if (region == "Europe, Israel, Australia") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("james.allen"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Latin America") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("gjausoro"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Pacific") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("john.vincent"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "India") {
	}	
	else if (region == "China") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("tma"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Japan") {
	}			
}

//11
else if (issue.getCustomFieldValue(customerField).toString() == "hqit/1c39e9de-7730-4ecd-9d48-2a4c56468077") {  // custom request type = Calendar Room Resource
	if (region == "USA & Canada (East)") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("brittney.robertson"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}
	else if (region == "USA & Canada (West)") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("franco.azabache"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}
	else if (region == "Europe, Israel, Australia") {
	}	
	else if (region == "Latin America") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("gjausoro"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Pacific") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("john.vincent"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "India") {
	}	
	else if (region == "China") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("tma"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Japan") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("freddy.phan"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}			
}

//12
else if (issue.getCustomFieldValue(customerField).toString() == "hqit/150da629-bbdc-4ec6-b082-fdb1bfbe8846") {  // custom request type = Shared Mailbox
	if (region == "USA & Canada (East)") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("brittney.robertson"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}
	else if (region == "USA & Canada (West)") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("franco.azabache"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}
	else if (region == "Europe, Israel, Australia") {
	}	
	else if (region == "Latin America") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("gjausoro"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Pacific") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("john.vincent"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "India") {
	}	
	else if (region == "China") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("tma"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Japan") {			
	}
}

//13
else if (issue.getCustomFieldValue(customerField).toString() == "hqit/870fde7a-d85c-4558-a1bc-b9a38c881316") {  // custom request type = Access Terminated Email/Calendars
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("stella"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}
	
//14
else if (issue.getCustomFieldValue(customerField).toString() == "hqit/f1ff5540-4788-4bf8-ba3e-293609c062e8") {  // custom request type = Access Active Emails/Calendars
	if (region == "USA & Canada (East)") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("stella"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}
	else if (region == "USA & Canada (West)") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("stella"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}
	else if (region == "Europe, Israel, Australia") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("lashaw"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Latin America") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("stella"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Pacific") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("john.vincent"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "India") {
		if(email.contains("@wework.com")){
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("john.vincent"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
		}
		else if(email.contains("@wework.co.in")){
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("purusothaman.satchithanandam"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
		}
	}		
	else if (region == "China") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("tma"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}	
	else if (region == "Japan") {	
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("freddy.phan"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);	
	}
}

//15
else if (issue.getCustomFieldValue(customerField).toString() == "hqit/fbef69db-522e-4c5f-89b2-bb92a663afc5") {  // custom request type = Unable to print.
	if (region == "China") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("azhou"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}
}
		
//16
else if (issue.getCustomFieldValue(customerField).toString() == "hqit/c3c42cac-c5b6-4105-a989-df39111a885a") {  // custom request type = Internet Issues (wired connection)
	if (region == "China") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("vanness.zhou"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}
}
	
//17
else if (issue.getCustomFieldValue(customerField).toString() == "hqit/4ef3368d-4db0-46f8-99c6-8bada8b9142b") {  // custom request type = Wifi Issues
	if (region == "China") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("vanness.zhou"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}
}
		
//18
else if (issue.getCustomFieldValue(customerField).toString() == "hqit/a2e109d7-1d51-4ba2-a7ef-bd1510889071") {  // custom request type = VPN Issues
	if (region == "China") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("vanness.zhou"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}
}
			
//19
else if (issue.getCustomFieldValue(customerField).toString() == "hqit/3458f2c5-daed-4bae-bf92-3e169a1cadcf") {  // custom request type = Computer
	if (region == "China") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("azhou"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}
}
			
//20
else if (issue.getCustomFieldValue(customerField).toString() == "hqit/74244b50-c347-4066-99ff-1a23b50e6dbf") {  // custom request type = OneLogin
	if (region == "China") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("azhou"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}
}
			
//21
else if (issue.getCustomFieldValue(customerField).toString() == "hqit/4e0680a3-6349-4d26-8b97-b87404daddab") {  // custom request type = MENA
	if (region == "China") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("azhou"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}
}
			
//22
else if (issue.getCustomFieldValue(customerField).toString() == "hqit/8b38f492-e445-4210-8651-25238039959a") {  // custom request type = General requests
	if (region == "Pacific") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("john.vincent"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}
	else if (region == "China") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("azhou"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}
	else if (region == "Japan") {
		
		for (user in issue.getCustomFieldValue(approversField)) {
			def userValue = user.toString()
			def username = userValue.substring(userValue.indexOf('(') + 1, userValue.indexOf(')'))
			log.debug "username = " + username
			apprv.add(ComponentAccessor.getUserManager().getUserByName(username))
		}
		apprv.add(ComponentAccessor.getUserManager().getUserByName("freddy.phan"))
		log.debug apprv
		approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
	}
}

// Set Approvers for Applications & Drives

if (issue.issueTypeObject.name == "Data Change") {
    log.debug issue.issueTypeObject.name
    log.debug drive

    switch (drive) {
       case 'Accounting':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("chris.sweson"))			
            loc = "\\\\10.251.70.100\\Shared\\Accounting Dept"
            break
        case 'Accounting Dept':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("patrick.kelly"))			
            loc = "\\\\10.251.70.100\\Shared\\Accounting Dept"
            break
        case 'Accounts Payable':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("patrick.kelly"))			
            loc = "\\\\10.251.70.100\\Shared\\Accounting Dept"
            break
        case 'Accounts Receivable':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("patrick.kelly"))			
            loc = "\\\\10.251.70.100\\Shared\\Accounting Dept"
            break
        case 'Brand':
            loc = "\\\\10.251.70.100\\Shared\\Brand"
            break
        case 'Business Planning':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("mike.nolan"))			
            loc = "\\\\10.251.70.100\\Shared\\Business Planning"
            break
        case 'Construction Accounting':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("patrick.kelly"))			
            loc = "\\\\10.251.70.100\\Shared\\Construction Accounting"
            break
        case 'Dalian':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("jimmy.prinsky"))			
            loc = ""
            break
        //case 'Enterprise':
		//	apprv.add(ComponentAccessor.getUserManager().getUserByName("conor.mccaffery"))
        //    loc = "\\\\10.251.70.100\\Shared\\Enterprise"
        //    break
        case 'Finance':
            log.debug "Finance Success"
			apprv.add(ComponentAccessor.getUserManager().getUserByName("michael.kopelman"))			
            loc = "\\\\10.251.70.100\\Shared\\Finance"
            break
        case 'Financial Accounting':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("patrick.kelly"))			
            loc = ""
            break
        case 'Financial Planning':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("michael.kopelman"))			
            loc = ""
            break
        case 'Financial Reporting':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("stephanie.tighe"))			
            loc = ""
            break
        case 'Fixed Asset Accounting':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("carmine.campo"))			
            loc = ""
            break
        case 'Human Resources':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("terry.wallace"))			
            loc = "\\\\10.251.70.100\\Shared\\Human Resources"
            break
        case 'Internal Controls':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("patrick.kelly"))			
            loc = "\\\\10.251.70.100\\Shared\\Accounting Dept"
            break
        case 'Legal':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("pgreenspan"))			
            loc = "\\\\10.251.70.100\\Shared\\WeWork Legal"
            break
        case 'Legal Entity':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("patrick.kelly"))			
            loc = "\\\\10.251.70.100\\Shared\\WeWork Legal"
            break
        case 'Office of the Chief Accounting Officer (OCAO)':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("edgranaghan"))			
            loc = "\\\\10.251.70.100\\Shared\\WeWork Legal"
            break
        case 'Operations':
            //apprv = [userUtil.getUserByKey('terry.wallace')]
			apprv.add(ComponentAccessor.getUserManager().getUserByName("terry.wallace"))			
            loc = "\\\\10.251.70.100\\Shared\\Operations"
            break
        case 'Payroll':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("niki.demakis"))			
            loc = "\\\\10.251.70.100\\Shared\\Shared Services\\People\\Payroll"
            break
        case 'Procurement':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("kevin.perry"))			
            loc = "\\\\10.251.70.100\\Shared\\weworkstaff\\Procurement"
            break
        case 'Product':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("Tyson.A..Cadorette"))			
            loc = ""
            break
        case 'Real Estate':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("nicole.fitzgerald"))			
            loc = "\\\\10.251.70.100\\Real Estate"
            break
        case 'Revenue Management':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("florent.cailliau"))			
            loc = "\\\\10.251.70.100\\shared\\revenue management"
            break
        case 'SAP (prodsap02)':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("haron.bitton"))			
            loc = ""
            break
        case 'Shared Services':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("kevin.perry"))			
            loc = "\\\\10.251.70.100\\Shared\\Shared Services\\People"
            break
        case 'Shared Services Management':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("greg.napolitan"))			
            loc = "\\\\10.251.70.100\\Shared\\Shared Services\\Management"
            break
        case 'SOX BPC':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("edgranaghan"))			
            loc = "\\\\10.251.70.100\\Shared\\SOX BPC"
            break
       case 'Tax':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("paul.aronson"))
            loc = "\\\\10.251.70.100\\Shared\\accounting dept\\Tax"
            break
        case 'Treasury':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("alex.agguire"))
            loc = "\\\\10.251.70.100\\Shared\\Accounting Dept\\Treasury"
            break
        case 'Physical drive':
			apprv.add(ComponentAccessor.getUserManager().getUserByName("Tyson.A..Cadorette"))
            loc = ""
            break			
        
       default:
            break
    }
}


// add approvers for application requests

if (app.contains("Wrike")) {
log.debug app
    apprv.add(ComponentAccessor.getUserManager().getUserByName("rachell.bordoy"))
	log.debug apprv
	approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
} 
if (app.contains("Webex")) {
log.debug app
    apprv.add(ComponentAccessor.getUserManager().getUserByName("laura.beucher"))
	log.debug apprv
	approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
}
if (app.contains("Autodesk 3DS Max") || app.contains("Autocad") || app.contains("Nasuni") || app.contains("Naviworks Manage") || app.contains("ReCap Pro") || app.contains("Revit") || app.contains("Rhinocerous") || app.contains("Bluebeam") || app.contains("Sketchup")) {
	log.debug app
	apprv.add(ComponentAccessor.getUserManager().getUserByName("Tyson.A..Cadorette"))
	log.debug apprv
	approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
}
//if (app.contains("Box") || app.contains("Boomerang") || app.contains("Sketchapp") || app.contains("Docusign")) {
 //   apprv.add(ComponentAccessor.getUserManager().getUserByName("brittney.robertson"))
//}
if (app.contains("Tableau - People Analytics")) {
log.debug app
    apprv.add(ComponentAccessor.getUserManager().getUserByName("gabriel.horwitz"))
	log.debug apprv
	approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
}
if (app.contains("Tableau - Data Analytics")) {
log.debug app
    apprv.add(ComponentAccessor.getUserManager().getUserByName("gabriel.horwitz"))
	log.debug apprv
	approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
}
if (app.contains("Looker")) {
log.debug app
    apprv.add(ComponentAccessor.getUserManager().getUserByName("data.help"))
	log.debug apprv
	approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
}
//if (app.contains("Airtable")) {
 //   apprv.add(ComponentAccessor.getUserManager().getUserByName("shreya.balusu"))
//	apprv.add(ComponentAccessor.getUserManager().getUserByName("rachell.bordoy"))
//	log.debug apprv
//	approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);}
if (app.contains("Salesforce")) {
    apprv.add(ComponentAccessor.getUserManager().getUserByName("Salesforcesupport"))
	log.debug apprv
	approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
}


// route by region
if (region == "West (USA & Canada)") {
    //def asignee = userUtil.getUserByKey('musab.mulla')
    //def validateAssignResult = issueService.validateAssign(asignee, issue.id, issue.reporterId)
    //issueService.assign(asignee, validateAssignResult)
} else if (region == "Latin America") {
} else if (region == "Europe, Israel, Australia") {
} else if (region == "China") {
} else if (region == "Pacific") {
} else if (region == "India") {
} else if (region == "Japan") {
}

if (issue.issueTypeObject.name == "Existing Building Task" || issue.issueTypeObject.name == "Standalone Technical Task") {
    log.debug email
	emailField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(emailField), email),changeHolder);
}

if (apprv) {
    log.debug apprv
	approversField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(approversField), apprv),changeHolder);
}

if (loc) {
    log.debug loc
	locationField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(locationField), loc),changeHolder);
}

if (newRegion) {
    log.debug newRegion
	regionField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(regionField), newRegion),changeHolder);
}

log.debug "done"
