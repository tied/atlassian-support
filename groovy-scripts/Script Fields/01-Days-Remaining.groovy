import java.sql.Timestamp
import java.text.SimpleDateFormat 
import java.util.Date

def date = new Date()

def today = date.format('yyyy/MM/dd', TimeZone.getTimeZone('EST'))
def duedate = issue.dueDate



return today
