import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.bc.issue.search.SearchService
import com.atlassian.jira.user.ApplicationUser
import com.atlassian.jira.bc.issue.search.SearchService.ParseResult
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.search.SearchException
import com.atlassian.jira.issue.search.SearchResults
import com.atlassian.jira.user.ApplicationUser
import com.atlassian.jira.web.bean.PagerFilter
 
SearchService searchService = ComponentAccessor.getComponentOfType(SearchService.class)
ApplicationUser adminUser = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser()
 
  

// Search the current project for any issue which have a similar summary


ParseResult parseResult = searchService.parseQuery(adminUser,"project = '" + issue.projectObject.key + "' and summary ~ '" + issue.summary +"'")
 
SearchResults queryResult
 

// Execute the query - taking into account that things can fail

if (parseResult.isValid()) {
    try
    {
        queryResult = searchService.search(adminUser,parseResult.getQuery(), PagerFilter.getUnlimitedFilter());
        if (queryResult == null) {
            log.debug("search - results is null - which should not happen");
            return false
        }
    }
    catch (SearchException e)
    {
        log.error("Error running search", e)
    }
} else {
    log.debug ("Some problem trying to search for duplicates ")
    return false
}
 
 

// Test the resulting array of issues if there is an issue with an identical summary

return ! (queryResult.issues.find { it.summary == issue.summary })




!issue.summary.contains("<certain summary already exists>")
