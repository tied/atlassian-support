import com.atlassian.jira.issue.changehistory.ChangeHistoryManager;
import com.atlassian.jira.issue.history.ChangeItemBean;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;

//For test
Issue issue = event.getIssue()
String fieldName = 'Email'
def oldValue;

def ChangeHistoryManager= ComponentAccessor.getChangeHistoryManager();

List<ChangeItemBean> changeItems
changeItems = ChangeHistoryManager.getChangeItemsForField(issue, fieldName)
if (changeItems.size()>0) {
oldValue = changeItems.get(changeItems.size()-1).getFromString()

}

issueInputParameters.addCustomFieldValue(fieldName, oldValue)
