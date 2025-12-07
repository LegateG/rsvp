// ============================================
// INTERFACE
// ============================================

/**
 * Interface for classes that can send notifications
 * Implements requirement #15: custom interface
 */
interface Communicable {
    /**
     * Sends a notification message
     * @param message The notification message to send
     */
    void sendNotification(String message);
}

// ============================================
// CUSTOM EXCEPTIONS
// ============================================

/**
 * Exception thrown when attempting to add a guest to a full event
 * Implements requirement #14: exception handling
 */
class EventFullException extends Exception {
    public EventFullException(String message) {
        super(message);
    }
}

/**
 * Exception thrown when an invalid date is provided
 */
class InvalidEventDateException extends Exception {
    public InvalidEventDateException(String message) {
        super(message);
    }
}

// ============================================
// GUEST CLASS
// ============================================

/**
 * Represents a guest who can RSVP to events
 * Demonstrates proper encapsulation and documentation
 */
class Guest {
    private String name;
    private String email;
    
    /**
     * Constructor for Guest
     * @param name Guest's full name
     * @param email Guest's email address
     */
    public Guest(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    // Setters
    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Returns string representation of Guest
     * Requirement #13: toString method
     */
    @Override
    public String toString() {
        return "Guest: " + name + " (" + email + ")";
    }
}

// ============================================
// ABSTRACT EVENT CLASS
// ============================================

/**
 * Abstract base class for all event types
 * Requirement #11: Abstract superclass with abstract method
 * Demonstrates aggregation: Event HAS-A Guest array
 */
abstract class Event implements Communicable {
    protected String title;
    protected String date;
    protected Guest[] guestList;
    protected int guestCount;
    protected static final int MAX_GUESTS = 100;
    
    /**
     * Constructor for Event
     * @param title Event title
     * @param date Event date
     */
    public Event(String title, String date) {
        this.title = title;
        this.date = date;
        this.guestList = new Guest[MAX_GUESTS];
        this.guestCount = 0;
    }
    
    /**
     * Abstract method to get event-specific details
     * Requirement #11: Abstract method in superclass
     */
    public abstract String getEventDetails();
    
    /**
     * Adds a guest to the event
     * Implements requirement #14: throws custom exception
     * @param guest The guest to add
     * @throws EventFullException if event is at capacity
     */
    public void addGuest(Guest guest) throws EventFullException {
        if (guestCount >= getCapacity()) {
            throw new EventFullException("Cannot add guest. Event '" + title + "' is full!");
        }
        guestList[guestCount] = guest;
        guestCount++;
    }
    
    /**
     * Gets the current number of guests
     * @return Number of registered guests
     */
    public int getGuestCount() {
        return guestCount;
    }
    
    /**
     * Returns the capacity for this event type
     * Can be overridden by subclasses
     * @return Maximum capacity
     */
    public int getCapacity() {
        return MAX_GUESTS;
    }
    
    /**
     * Displays all guests registered for this event
     */
    public void displayGuestList() {
        System.out.println("\n=== Guest List for: " + title + " ===");
        if (guestCount == 0) {
            System.out.println("No guests registered yet.");
        } else {
            for (int i = 0; i < guestCount; i++) {
                System.out.println((i + 1) + ". " + guestList[i]);
            }
        }
        System.out.println("Total Guests: " + guestCount + " / " + getCapacity());
    }
    
    /**
     * Implementation of Communicable interface
     * Requirement #15: Interface implementation
     */
    @Override
    public void sendNotification(String message) {
        System.out.println("\n--- Sending Notification for: " + title + " ---");
        System.out.println("Message: " + message);
        System.out.println("Sending to " + guestCount + " guest(s):");
        for (int i = 0; i < guestCount; i++) {
            System.out.println("  -> " + guestList[i].getEmail());
        }
    }
    
    // Getters
    public String getTitle() {
        return title;
    }
    
    public String getDate() {
        return date;
    }
    
    /**
     * Returns string representation of Event
     * Requirement #13: toString method
     */
    @Override
    public String toString() {
        return "Event: " + title + " on " + date + " (" + guestCount + " guests)";
    }
}

// ============================================
// IN-PERSON EVENT SUBCLASS
// ============================================

/**
 * Represents an in-person event with physical location
 * Requirement #12: Overrides methods and calls superclass
 */
class InPersonEvent extends Event {
    private String location;
    private int physicalCapacity;
    
    /**
     * Constructor for InPersonEvent
     * @param title Event title
     * @param date Event date
     * @param location Physical venue location
     * @param physicalCapacity Maximum venue capacity
     */
    public InPersonEvent(String title, String date, String location, int physicalCapacity) {
        super(title, date);
        this.location = location;
        this.physicalCapacity = physicalCapacity;
    }
    
    /**
     * Overrides abstract method from Event
     * Requirement #12: Overridden method
     */
    @Override
    public String getEventDetails() {
        String details = getBasicDetails(); // Call to helper method
        details += "\nLocation: " + location;
        details += "\nCapacity: " + guestCount + " / " + physicalCapacity;
        return details;
    }
    
    /**
     * Helper method that demonstrates call to superclass
     * Requirement #12: Makes call to superclass method
     */
    private String getBasicDetails() {
        return "Type: In-Person Event" +
               "\nTitle: " + super.getTitle() +
               "\nDate: " + super.getDate();
    }
    
    /**
     * Overrides capacity to use physical venue capacity
     */
    @Override
    public int getCapacity() {
        return physicalCapacity;
    }
    
    // Getters
    public String getLocation() {
        return location;
    }
    
    /**
     * Requirement #13: toString method
     */
    @Override
    public String toString() {
        return super.toString() + " at " + location;
    }
}

// ============================================
// VIRTUAL EVENT SUBCLASS
// ============================================

/**
 * Represents a virtual online event
 * Requirement #12: Overrides methods and calls superclass
 */
class VirtualEvent extends Event {
    private String meetingUrl;
    
    /**
     * Constructor for VirtualEvent
     * @param title Event title
     * @param date Event date
     * @param meetingUrl URL for virtual meeting
     */
    public VirtualEvent(String title, String date, String meetingUrl) {
        super(title, date);
        this.meetingUrl = meetingUrl;
    }
    
    /**
     * Overrides abstract method from Event
     * Requirement #12: Overridden method
     */
    @Override
    public String getEventDetails() {
        String details = getBasicInfo(); // Calls helper that uses superclass
        details += "\nMeeting URL: " + meetingUrl;
        details += "\nRegistered: " + guestCount + " guests (No capacity limit)";
        return details;
    }
    
    /**
     * Helper method demonstrating superclass method call
     * Requirement #12: Makes call to superclass method
     */
    private String getBasicInfo() {
        return "Type: Virtual Event" +
               "\nTitle: " + super.title +
               "\nDate: " + super.date;
    }
    
    /**
     * Virtual events have unlimited capacity
     */
    @Override
    public int getCapacity() {
        return Integer.MAX_VALUE;
    }
    
    // Getters
    public String getMeetingUrl() {
        return meetingUrl;
    }
    
    /**
     * Requirement #13: toString method
     */
    @Override
    public String toString() {
        return super.toString() + " (Virtual)";
    }
}

// ============================================
// HYBRID EVENT SUBCLASS
// ============================================

/**
 * Represents a hybrid event (both in-person and virtual)
 * Requirement #12: Overrides methods and calls superclass
 */
class HybridEvent extends Event {
    private String location;
    private String meetingUrl;
    private int physicalCapacity;
    
    /**
     * Constructor for HybridEvent
     * @param title Event title
     * @param date Event date
     * @param location Physical venue location
     * @param meetingUrl URL for virtual attendance
     * @param physicalCapacity Maximum physical venue capacity
     */
    public HybridEvent(String title, String date, String location, 
                       String meetingUrl, int physicalCapacity) {
        super(title, date);
        this.location = location;
        this.meetingUrl = meetingUrl;
        this.physicalCapacity = physicalCapacity;
    }
    
    /**
     * Overrides abstract method from Event
     * Requirement #12: Overridden method
     */
    @Override
    public String getEventDetails() {
        String details = getEventBaseInfo(); // Call to helper method
        details += "\nPhysical Location: " + location;
        details += "\nVirtual URL: " + meetingUrl;
        details += "\nIn-Person Capacity: " + guestCount + " / " + physicalCapacity;
        details += "\nNote: Virtual attendance is unlimited";
        return details;
    }
    
    /**
     * Helper method showing superclass method usage
     * Requirement #12: Makes call to superclass method
     */
    private String getEventBaseInfo() {
        return "Type: Hybrid Event (In-Person & Virtual)" +
               "\nTitle: " + getTitle() +
               "\nDate: " + getDate();
    }
    
    /**
     * Hybrid events use physical capacity for in-person tracking
     */
    @Override
    public int getCapacity() {
        return physicalCapacity;
    }
    
    // Getters
    public String getLocation() {
        return location;
    }
    
    public String getMeetingUrl() {
        return meetingUrl;
    }
    
    /**
     * Requirement #13: toString method
     */
    @Override
    public String toString() {
        return super.toString() + " (Hybrid: " + location + " & Online)";
    }
}