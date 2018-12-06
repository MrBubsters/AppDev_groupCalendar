import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.Calendar.Events.QuickAdd;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.api.services.calendar.model.Events;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CalendarAPI {
    private static final String APPLICATION_NAME = "CalAPP";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";


    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = CalendarAPI.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
    
    public static Calendar build() throws GeneralSecurityException, IOException {
    	// Build Calendar
    	final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        return service;
    }
    
    public static void addEvent(Calendar service, String summary, String desc, 
    		DateTime startTime, DateTime endTime, String[] recur, String timezone) throws IOException {
    	//Creates event based on input values
    	
    	Event event = new Event()
    		    .setSummary(summary)
//    		    .setLocation(loc)
    		    .setDescription(desc);

    		DateTime startDateTime = new DateTime("2018-11-28T09:00:00-07:00");
    		EventDateTime start = new EventDateTime()
    		    .setDateTime(startTime)
    		    .setTimeZone("America/Chicago");
    		event.setStart(start);

    		DateTime endDateTime = new DateTime("2018-11-28T17:00:00-07:00");
    		EventDateTime end = new EventDateTime()
    		    .setDateTime(endTime)
    		    .setTimeZone("America/Chicago");
    		event.setEnd(end);

//    		String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
//    		event.setRecurrence(Arrays.asList(recurrence));

//    		EventAttendee[] attendees = new EventAttendee[] {
//    		    new EventAttendee().setEmail("lpage@example.com"),
//    		    new EventAttendee().setEmail("sbrin@example.com"),
//    		};
//    		event.setAttendees(Arrays.asList(attendees));

    		EventReminder[] reminderOverrides = new EventReminder[] {
    		    new EventReminder().setMethod("email").setMinutes(24 * 60),
    		    new EventReminder().setMethod("popup").setMinutes(10),
    		};
    		Event.Reminders reminders = new Event.Reminders()
    		    .setUseDefault(false)
    		    .setOverrides(Arrays.asList(reminderOverrides));
    		event.setReminders(reminders);

    		String calendarId = "primary";
    		event = service.events().insert(calendarId, event).execute();
    		System.out.printf("Event created: %s\n", event.getHtmlLink());
    }
    
    public static void quickAdd(Calendar service, String text) throws IOException {
    	String calendarId = "primary";
    	Event event = service.events().quickAdd(calendarId, text).execute();
    	System.out.printf("Event created: %s\n", event.getHtmlLink());
    }
    
    public static ArrayList<String> getNext10(Calendar service) throws IOException {
      // List the next 10 events from the primary calendar.
      DateTime now = new DateTime(System.currentTimeMillis());
      Events events = service.events().list("primary")
              .setMaxResults(10)
              .setTimeMin(now)
              .setOrderBy("startTime")
              .setSingleEvents(true)
              .execute();
      List<Event> items = events.getItems();
      ArrayList<String> list = new ArrayList<String>();
      if (items.isEmpty()) {
          System.out.println("No upcoming events found.");
      } else {
          System.out.println("Upcoming events");
          for (Event event : items) {
              DateTime start = event.getStart().getDateTime();
              if (start == null) {
                  start = event.getStart().getDate();
              }
              System.out.printf("%s (%s)\n", event.getSummary(), start);
              list.add(event.getSummary());
          }
      }
      return list;
    }
    
    public static void DeleteEvent(String id) {
    	
    }
    
    public static void DeleteCreds() {
    	File creds = new File("tokens/StoredCredential");
    	creds.delete();
    }

    public static void main(String... args) throws IOException, GeneralSecurityException {
    	Calendar service = build();
    	//DeleteCreds();
    }
}
