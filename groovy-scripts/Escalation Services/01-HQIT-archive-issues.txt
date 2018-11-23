// import packages for time methods

import java.sql.Timestamp
import java.text.SimpleDateFormat 
import java.util.Date
import java.util.concurrent.TimeUnit

// define shortcut for issue due date
def duedate = issue.dueDate

// define the current day
def currentDay = new Date()

// define a custom date for two weeks from now
def archiveDate = new Date(duedate.time + TimeUnit.DAYS.toMillis(14))

// if the issue type of the current issue is master data
if(issue.getIssueType().getName() == "Master Data"){
    
    
    // if due date is less than the custom date from line 8
    if(archiveDate.compareTo(currentDay) < 0){
       
            // set  Security Level = Archived
            issueInputParameters.setSecurityLevelId(10500L)
            issueInputParameters.setSkipScreenCheck(true)
        }
    }
