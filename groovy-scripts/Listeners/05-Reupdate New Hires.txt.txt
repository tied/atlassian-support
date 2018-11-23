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
import com.atlassian.jira.issue.customfields.impl.CascadingSelectCFType 

// Timestamp
import java.sql.Timestamp;
import static java.util.Calendar.*
import java.text.DateFormat;    
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.joda.time.DateTimeZone;



// request type
import com.atlassian.servicedesk.api.requesttype.RequestTypeService
import com.atlassian.servicedesk.api.requesttype.RequestType
import com.onresolve.scriptrunner.runner.customisers.PluginModule
import com.onresolve.scriptrunner.runner.customisers.WithPlugin

// construct shortcuts
def userUtil = ComponentAccessor.getUserUtil()
def changeHolder = new DefaultIssueChangeHolder()
def issueManager = ComponentAccessor.getIssueManager()
def issueService = ComponentAccessor.getIssueService()
def userManager = ComponentAccessor.getUserManager()
def issue = event.issue as Issue 
def customFieldManager = ComponentAccessor.getCustomFieldManager()
def optionsManager = ComponentAccessor.optionsManager
def watcherManager = ComponentAccessor.getWatcherManager()



// set logging
def log = Logger.getLogger("com.acme.CreateSubtask")
log.setLevel(org.apache.log4j.Level.DEBUG)

// fields
def customerRequestField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Customer Request Type"}
def nameField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Name"}
def emailField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Email"}
def dueDateField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Due Date"}
def workdayField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Workday Reference"}
def locationField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Location"}
def RegionLocationField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Region & Location"}
def requestField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Customer Request Type"}
def notesField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Notes"}
def startDateField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Start Date"}
def recruiterField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Recruiter"}
def costCenterField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Cost Center"}
def requestParticipantsField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Request Participants"}
def deviceField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Item"}
def orientationField = customFieldManager.getCustomFieldObjects(issue).find {it.name == "Orientation Location"}




def requestFieldConfig = requestField.getRelevantConfig(issue)
def requestFieldValue = ComponentAccessor.optionsManager.getOptions(requestFieldConfig)?.find { it.toString() == 'New Hire' }


def testRequestField = issue.getCustomFieldValue(requestField).toString()
def regionLocationFieldValue = issue.getCustomFieldValue(RegionLocationField).toString()


log.debug "requestFieldValue = " + requestFieldValue
//log.debug "regionLocationFieldValue = " + regionLocationFieldValue
log.debug "customerRequestField = " + issue.getCustomFieldValue(customerRequestField).toString()

// parse it
if (issue.issueTypeObject.name == "Master Data") {
    def description = issue.description
    
    if (issue.summary.contains('Fwd:') == true ) {
        description = issue.description.split('------------------------------')[1]
        description.replaceFirst(':', '')
        description.split('Click Here to view the notification details.')[0]
        log.debug "description = " + description
        }

    description = issue.description.replace('\n', ':')
    def tokenizeDescription = description.tokenize(':')
    
    log.debug "tokenizeDescription = " + tokenizeDescription
    def hiredate = tokenizeDescription[1].trim()
    def firstname = tokenizeDescription[3].replace(" ", "")
    def lastname = tokenizeDescription[5].replace(" ", "")
    def title = tokenizeDescription[7].trim()
    def costcenter = tokenizeDescription[9].trim()
    def region = tokenizeDescription[11].trim()
    def workcity = tokenizeDescription[13].trim()
    def workstate = tokenizeDescription[15].trim()
    def country = tokenizeDescription[17].trim()
    //def region = tokenizeDescription[17].trim()
    def location = tokenizeDescription[19].trim()
    //def linebreak = tokenizeDescription[20]
    def orientation = tokenizeDescription[21].trim()
    def manager = tokenizeDescription[22].trim()
	def recruiter = tokenizeDescription[25].trim()
    //def device = tokenizeDescription[27].trim().replace('[','').replace(']','')
    
    
       
    log.debug "Provided Region/Location = " + issue.getCustomFieldValue(RegionLocationField)
    log.debug "manager = " + manager
    log.debug "recruiter = " + recruiter
    //log.debug "device = " + device
    
    TimeZone.setDefault(TimeZone.getTimeZone('EST'))
    def duedateArray = hiredate.split("/")
    def duedate = duedateArray[2] + "-" + duedateArray[0] + "-" + duedateArray[1]
    def mydate = Date.parse("yyyy-MM-dd hh:mm:ss", duedate + " 0:00:00")
    log.debug "mydate = " + mydate
     
    
    def email = firstname.toLowerCase().replace(" ", "") + "." + lastname.toLowerCase().replace(" ", "") + "@wework.com"
    def recruiterName = recruiter.toLowerCase().trim().replace(" ", ".")
    def name = firstname + " " + lastname
    def regionLocation = []
    
 
    log.debug "recruiterName = " + recruiterName
    
    
   	if (recruiter) {watcherManager.startWatching(userManager.getUserByKey(recruiterName), issue)}
    emailField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(nameField), email.replace(" ", "")), changeHolder)
    nameField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(nameField), name), changeHolder);
    startDateField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(startDateField), hiredate), changeHolder);
    costCenterField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(costCenterField), costcenter), changeHolder)
    //requestParticipantsField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(requestParticipantsField), [userUtil.getUserByKey(recruiterName)]),changeHolder);
    //workdayField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(nameField), values['Hire Date']), changeHolder);
    locationField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(nameField), location), changeHolder);
    //customerRequestField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(customerRequestField), 'hqit/e927575a-b5eb-41b7-84e9-e85b112f5b0f'), changeHolder);
    deviceField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(deviceField), device), changeHolder);
    orientationField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(orientationField), orientation), changeHolder);

    /*
    if (country == 'United States of America') {
       regionLocation = ["null:US & Canada", ""]
       RegionLocationField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(RegionLocationField), regionLocation), changeHolder);
    } else if (country == 'Europe') {
       regionLocation = ["null:Europe", ""]
       RegionLocationField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(RegionLocationField), regionLocation), changeHolder);
    } else if (country == 'Brazil') {
       regionLocation = ["null:Latin America", "1:Brazil"]
       RegionLocationField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(RegionLocationField), regionLocation), changeHolder); 
    } else if (country == 'India') {
       regionLocation = ["null:India", ""]
       RegionLocationField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(RegionLocationField), regionLocation), changeHolder);    
    } else if (country == 'Japan') {
       regionLocation = ["null:Asia", "1:Japan"]
       RegionLocationField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(RegionLocationField), regionLocation), changeHolder);    
    } else if (country == 'Pacific') {
       regionLocation = ["null:Asia", "1:Pacific"]
		RegionLocationField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(RegionLocationField), regionLocation), changeHolder);    
    } else if (country == "Australia") {
        regionLocation = ["null:Australia", ""]
		RegionLocationField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(RegionLocationField), regionLocation), changeHolder); 
    } else if (country == 'China') {
        regionLocation = ["null:Asia", "1:China"]
        RegionLocationField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(RegionLocationField), regionLocation), changeHolder); 
    } else if (country == 'Israel') {
        regionLocation = ["null:Israel", ""]
        RegionLocationField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(RegionLocationField), regionLocation), changeHolder); 
    } else if (country == 'Germany') {
        regionLocation = ["null:Europe", "1:Germany"]
		 RegionLocationField.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(RegionLocationField), regionLocation), changeHolder);   
    }
*/
}


