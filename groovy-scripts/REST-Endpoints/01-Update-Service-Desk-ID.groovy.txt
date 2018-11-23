// endpoint imports
import com.onresolve.scriptrunner.runner.rest.common.CustomEndpointDelegate
import groovy.json.JsonBuilder
import groovy.transform.BaseScript
import javax.servlet.http.HttpServletRequest
import javax.ws.rs.core.MultivaluedMap
import javax.ws.rs.core.Response
import org.apache.http.impl.client.HttpClientBuilder
import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.ContentType.*
import groovyx.net.http.ContentType
import static groovyx.net.http.Method.*
import groovy.json.JsonSlurper
import net.sf.json.groovy.JsonSlurper

// users
import com.atlassian.jira.user.ApplicationUser
import com.atlassian.jira.user.util.UserUtil

// issue
import com.atlassian.jira.issue.Issue

// logging
import org.apache.log4j.Logger
import org.apache.log4j.Level

// custom fields
import com.atlassian.jira.ComponentManager
import com.atlassian.jira.issue.comments.CommentManager
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.MutableIssue
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder
import com.atlassian.jira.issue.util.IssueChangeHolder
import com.atlassian.jira.issue.ModifiedValue

// request type
import com.atlassian.servicedesk.api.requesttype.RequestTypeService
import com.atlassian.servicedesk.api.requesttype.RequestType
import com.onresolve.scriptrunner.runner.customisers.PluginModule
import com.onresolve.scriptrunner.runner.customisers.WithPlugin

// jqlQuery
import com.atlassian.jira.jql.builder.JqlQueryBuilder
import com.atlassian.jira.jql.parser.JqlQueryParser
import com.atlassian.jira.issue.search.SearchException
import com.atlassian.jira.issue.search.SearchProvider
import com.atlassian.jira.issue.search.SearchResults
import com.atlassian.jira.web.bean.PagerFilter
import com.atlassian.jira.jql.builder.JqlClauseBuilder
import com.atlassian.jira.web.bean.PagerFilter
import groovy.transform.ToString


// jackson
import com.atlassian.jira.issue.fields.rest.json.beans.JiraBaseUrls
import com.atlassian.jira.issue.fields.rest.json.beans.PriorityJsonBean
import com.atlassian.jira.issue.priority.Priority
import com.onresolve.scriptrunner.runner.rest.common.CustomEndpointDelegate
import groovy.transform.BaseScript
import org.codehaus.jackson.map.ObjectMapper

// begin endpoint logic
// ---------------------------------------------------------------------------------------
@BaseScript CustomEndpointDelegate delegate

def http = new HTTPBuilder('https://jira.we.co/rest/scriptrunner/latest/custom/updateServiceDeskRequest')

updateServiceDeskRequest(
  httpMethod: "PUT", groups: ["administrators"]
) { MultivaluedMap queryParams, String body, HttpServletRequest request ->

  // set logging
  def log = Logger.getLogger("com.acme.CreateSubtask")
  log.setLevel(Level.DEBUG)

  // construct shortcuts
  def userUtil = ComponentAccessor.getUserUtil()
  def changeHolder = new DefaultIssueChangeHolder()
  def issueManager = ComponentAccessor.getIssueManager()
  def issueService = ComponentAccessor.getIssueService()
  def userManager = ComponentAccessor.getUserManager()
  def customFieldManager = ComponentAccessor.getCustomFieldManager()
  def authContext = ComponentAccessor.getJiraAuthenticationContext()

  // users
  def user = ComponentAccessor.getJiraAuthenticationContext().getUser()

  // request body
  def jsonSlurper = new JsonSlurper()
  def object = jsonSlurper.parseText(body)
  assert object instanceof Map

  // find issue
  def issueKey = request.getParameter("key")
  def issueObject = issueManager.getIssueObject(issueKey)

  // customer request fields
  def requestTypeService = RequestTypeService
  def regionField = customFieldManager.getCustomFieldObjectByName("Region")
  def customerField = customFieldManager.getCustomFieldObjectByName("Customer Request Type")
  def emailField = customFieldManager.getCustomFieldObjectByName("Email")
  def numberField = customFieldManager.getCustomFieldObjectByName("Number")
  def costCenterField = customFieldManager.getCustomFieldObjectByName("Cost Center")
  def startDateField = customFieldManager.getCustomFieldObjectByName("Start Date")
  def itemField = customFieldManager.getCustomFieldObjectByName("Item")
  def oboardingLocationField = customFieldManager.getCustomFieldObjectByName("Orientation Location")
  def onboardingRegionField = customFieldManager.getCustomFieldObjectByName("Onboarding Region")
  def nameField = customFieldManager.getCustomFieldObjectByName("Name")
  def idField = customFieldManager.getCustomFieldObjectByName("ID")
  def organizationField = customFieldManager.getCustomFieldObjectByName("Organizations")
  def locationField = customFieldManager.getCustomFieldObjectByName("Location")
  def titleField = customFieldManager.getCustomFieldObjectByName("Title")
  def hireDateField = customFieldManager.getCustomFieldObjectByName("hire_date")
  def businessUnitField = customFieldManager.getCustomFieldObjectByName("Business Unit")
  def regionEmployeeSupportsField = customFieldManager.getCustomFieldObjectByName("Region Employee Supports")
  def workdayIDField = customFieldManager.getCustomFieldObjectByName("Workday ID")
  def countryField = customFieldManager.getCustomFieldObjectByName("Country")


  requestTypeService = RequestTypeService
  //regionValue = object['requestFieldValues']["Region")
  def deskValue = object['']
  def requestValue = object["requestTypeId"]
  def summaryValue = object['summary']
  def emailValue = object['requestFieldValues']["customfield_13016"].toString()
  def titleValue = object['requestFieldValues']['customfield_13935']
  def costCenterValue = object['requestFieldValues']["Cost Center"]
  def itemValue = object['requestFieldValues']["customfield_13718"]
  def onboardingRegionValue = object['requestFieldValues']["customfield_13802"]
  def nameValue = object['requestFieldValues']["customfield_13015"]
  def organizationValue = object['requestFieldValues']["Organizations"]
  def locationValue = object['requestFieldValues']["customfield_13009"]
  def hireDateValue = object['requestFieldValues']['duedate']
  def businessUnitValue = object['requestFieldValues']["customfield_13746"]
  def regionEmployeeSupportsValue = object['requestFieldValues']["customfield_13926"]
  def workdayIDValue = object['requestFieldValues']["customfield_13009"]
  def countryValue = object['requestFieldValues']["customfield_13926"]

  // ----------------------------------------------------
  // NOTE: don't touch these fields:
  // priority'
  // customfield_13802, aka proposed_location_region
  // customfield_13755, aka new_hire_orientation_location
  // ------------------------------------------------------


  def options;

    try {
      emailField.updateValue(null, issueObject, new ModifiedValue(issueObject.getCustomFieldValue(emailField), emailValue),changeHolder);
      //emailField.updateValue(null, issueObject, new ModifiedValue(issueObject.getCustomFieldValue(emailField), summaryValue),changeHolder);
      //requestValue.updateValue(null, issueObject, new ModifiedValue(issueObject.getCustomFieldValue(requestValue), requestValue),changeHolder);
      titleField.updateValue(null, issueObject, new ModifiedValue(issueObject.getCustomFieldValue(titleField), titleValue),changeHolder);
      /*emailField.updateValue(null, issueObject, new ModifiedValue(issueObject.getCustomFieldValue(emailField), costCenterValue),changeHolder);
      emailField.updateValue(null, issueObject, new ModifiedValue(issueObject.getCustomFieldValue(emailField), itemValue),changeHolder);
      emailField.updateValue(null, issueObject, new ModifiedValue(issueObject.getCustomFieldValue(emailField), onboardingRegionValue),changeHolder);
      emailField.updateValue(null, issueObject, new ModifiedValue(issueObject.getCustomFieldValue(emailField), organizationValue),changeHolder);
      emailField.updateValue(null, issueObject, new ModifiedValue(issueObject.getCustomFieldValue(emailField), locationValue),changeHolder);
      emailField.updateValue(null, issueObject, new ModifiedValue(issueObject.getCustomFieldValue(emailField), hireDateValue),changeHolder);
      emailField.updateValue(null, issueObject, new ModifiedValue(issueObject.getCustomFieldValue(emailField), businessUnitValue),changeHolder);
      emailField.updateValue(null, issueObject, new ModifiedValue(issueObject.getCustomFieldValue(emailField), regionEmployeeSupportsValue),changeHolder);
      emailField.updateValue(null, issueObject, new ModifiedValue(issueObject.getCustomFieldValue(emailField), workdayIDValue),changeHolder);
      emailField.updateValue(null, issueObject, new ModifiedValue(issueObject.getCustomFieldValue(emailField), countryValue),changeHolder);*/
      return Response.ok(object).build()
      //return Response.ok(response_body.toString()).build()
    } catch(Exception ex) {
    return Response.noContent().build()
  }
}
