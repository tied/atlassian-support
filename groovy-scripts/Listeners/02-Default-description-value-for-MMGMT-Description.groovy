import com.onresolve.jira.groovy.user.FieldBehaviours
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.user.ApplicationUser
import com.atlassian.jira.issue.ModifiedValue
import com.onresolve.jira.groovy.user.FieldBehaviours
import static com.atlassian.jira.issue.IssueFieldConstants.*

import com.atlassian.jira.issue.Issue

def issue = event.issue as Issue 

if (issue.issueTypeObject.name == "New Feature") {
    //def descField = issue.getFieldById("description")
}
