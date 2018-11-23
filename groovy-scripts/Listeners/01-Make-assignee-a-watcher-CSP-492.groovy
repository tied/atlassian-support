import com.atlassian.jira.component.ComponentAccessor

def assignee = issue.getAssigneeUser()

if (assignee) {
    ComponentAccessor.getWatcherManager().startWatching(assignee, issue)
}
