# Event Management System - Core Classes Documentation

## Project Information
**Developer (Core Model):** Gorkem  
**Date:** December 2025  
**Course:** Object-Oriented Programming

---

## Table of Contents
- [Overview](#overview)
- [Architecture & Design Patterns](#architecture--design-patterns)
- [Class Descriptions](#class-descriptions)
- [Sample Output Scenarios](#sample-output-scenarios)
- [Integration Guide](#integration-guide)
- [Requirements Checklist](#requirements-checklist)

---

## Overview

This document covers the core model classes for the Modern Event Management System. These classes provide the foundation for managing three types of events: In-Person, Virtual, and Hybrid.

---

## Architecture & Design Patterns

### 1. Inheritance Hierarchy
- **Abstract Superclass:** `Event` (contains abstract method `getEventDetails()`)
- **Concrete Subclasses:** `InPersonEvent`, `VirtualEvent`, `HybridEvent`
- All subclasses override the abstract method and call superclass methods

### 2. Aggregation (HAS-A Relationship)
- `Event` HAS-A `Guest[]` array
- Each event maintains its own guest list
- Demonstrates object composition

### 3. Interface Implementation
- `Communicable` interface defines notification contract
- `Event` class implements the interface
- All event types inherit the notification capability

### 4. Polymorphism
- Event array can hold all event types: `Event[] events = new Event[10];`
- Method calls resolve to correct implementation at runtime
- Example: `events[i].getEventDetails()` calls the appropriate subclass method

### 5. Exception Handling
- **Custom Exceptions:** `EventFullException`, `InvalidEventDateException`
- `addGuest()` throws `EventFullException` when capacity is reached
- Trushant's main class will catch and handle these exceptions

---

## Class Descriptions

### Interface: `Communicable`

**Purpose:** Defines contract for classes that can send notifications

**Methods:**
- `void sendNotification(String message)` - Sends notification to recipients

**Implementation:** Event class implements this interface to notify all registered guests

---

### Class: `Guest`

**Purpose:** Represents an event attendee

**Attributes:**
- `name` (String) - Guest's full name
- `email` (String) - Guest's email address

**Methods:**
- `Guest(String name, String email)` - Constructor
- Getters and setters for all attributes
- `toString()` - Returns formatted guest information

**Example Usage:**
```java
Guest guest1 = new Guest("Alice Johnson", "alice@email.com");
Guest guest2 = new Guest("Bob Smith", "bob@email.com");
```

---

### Abstract Class: `Event`

**Purpose:** Base class for all event types, implements Communicable

**Attributes:**
- `title` (String) - Event title
- `date` (String) - Event date
- `guestList` (Guest[]) - Array of registered guests
- `guestCount` (int) - Current number of guests
- `MAX_GUESTS` (static final int) - Default maximum capacity (100)

**Key Methods:**
- `Event(String title, String date)` - Constructor
- `abstract String getEventDetails()` - Abstract method (must be overridden)
- `void addGuest(Guest guest) throws EventFullException` - Adds guest or throws exception
- `int getCapacity()` - Returns event capacity (can be overridden)
- `void displayGuestList()` - Prints all registered guests
- `void sendNotification(String message)` - Implements Communicable interface
- `toString()` - Returns event summary

---

### Class: `InPersonEvent` extends `Event`

**Purpose:** Represents physical venue events

**Additional Attributes:**
- `location` (String) - Physical venue address
- `physicalCapacity` (int) - Venue capacity limit

**Overridden Methods:**
- `getEventDetails()` - Returns details including location and capacity
- `getCapacity()` - Returns physical venue capacity
- `toString()` - Includes location information

**Example:**
```java
InPersonEvent conference = new InPersonEvent(
    "Tech Conference 2025",
    "March 15, 2025",
    "Convention Center, Toronto",
    50
);
```

---

### Class: `VirtualEvent` extends `Event`

**Purpose:** Represents online-only events

**Additional Attributes:**
- `meetingUrl` (String) - Virtual meeting link

**Overridden Methods:**
- `getEventDetails()` - Returns details including meeting URL
- `getCapacity()` - Returns unlimited capacity (Integer.MAX_VALUE)
- `toString()` - Includes "(Virtual)" designation

**Example:**
```java
VirtualEvent webinar = new VirtualEvent(
    "AI Workshop",
    "April 20, 2025",
    "https://zoom.us/meeting123"
);
```

---

### Class: `HybridEvent` extends `Event`

**Purpose:** Represents events with both physical and virtual components

**Additional Attributes:**
- `location` (String) - Physical venue address
- `meetingUrl` (String) - Virtual meeting link
- `physicalCapacity` (int) - Physical venue capacity

**Overridden Methods:**
- `getEventDetails()` - Returns details for both attendance modes
- `getCapacity()` - Returns physical capacity (virtual is unlimited)
- `toString()` - Includes both location and "(Hybrid)" designation

**Example:**
```java
HybridEvent symposium = new HybridEvent(
    "Medical Symposium",
    "May 10, 2025",
    "Hospital Auditorium",
    "https://meet.google.com/abc-defg-hij",
    30
);
```

---

### Exception Classes

#### `EventFullException`
**Purpose:** Thrown when attempting to add guest to full event  
**Usage:** Caught in main application to display appropriate error message

#### `InvalidEventDateException`
**Purpose:** Thrown when invalid date format is provided  
**Usage:** Can be used for date validation (optional feature)

---

## Sample Output Scenarios

### Scenario 1: Creating Events and Adding Guests

```
===============================================
    MODERN EVENT MANAGEMENT SYSTEM
===============================================

Creating In-Person Event...
✓ Event created: Tech Conference 2025

Event Details:
Type: In-Person Event
Title: Tech Conference 2025
Date: March 15, 2025
Location: Convention Center, Toronto
Capacity: 0 / 50

Adding guests...
✓ Guest added: Alice Johnson
✓ Guest added: Bob Smith
✓ Guest added: Carol White

Updated Event Details:
Type: In-Person Event
Title: Tech Conference 2025
Date: March 15, 2025
Location: Convention Center, Toronto
Capacity: 3 / 50
```

### Scenario 2: Displaying Guest List

```
=== Guest List for: Tech Conference 2025 ===
1. Guest: Alice Johnson (alice@email.com)
2. Guest: Bob Smith (bob@email.com)
3. Guest: Carol White (carol@email.com)
Total Guests: 3 / 50
```

### Scenario 3: Sending Notifications

```
--- Sending Notification for: Tech Conference 2025 ---
Message: Reminder: Event starts tomorrow at 9:00 AM!
Sending to 3 guest(s):
  -> alice@email.com
  -> bob@email.com
  -> carol@email.com
```

### Scenario 4: Exception Handling (Event Full)

```
Adding guest to full event...
✗ ERROR: Cannot add guest. Event 'Small Workshop' is full!

Event capacity: 5 / 5
Unable to add: David Lee
```

### Scenario 5: Polymorphism Demo

```
===============================================
    ALL UPCOMING EVENTS
===============================================

Event 1:
Type: In-Person Event
Title: Tech Conference 2025
Date: March 15, 2025
Location: Convention Center, Toronto
Capacity: 3 / 50

Event 2:
Type: Virtual Event
Title: AI Workshop
Date: April 20, 2025
Meeting URL: https://zoom.us/meeting123
Registered: 15 guests (No capacity limit)

Event 3:
Type: Hybrid Event (In-Person & Virtual)
Title: Medical Symposium
Date: May 10, 2025
Physical Location: Hospital Auditorium
Virtual URL: https://meet.google.com/abc-defg-hij
In-Person Capacity: 8 / 30
Note: Virtual attendance is unlimited
```

---

## Integration Guide

### How Trushant Should Use These Classes

#### 1. In EventTester (main class):

```java
// Create array of events (polymorphism)
Event[] events = new Event[10];
int eventCount = 0;

// Create different event types
events[eventCount++] = new InPersonEvent("Conference", "Jan 15", "Toronto", 50);
events[eventCount++] = new VirtualEvent("Webinar", "Feb 20", "https://zoom.us/123");
events[eventCount++] = new HybridEvent("Summit", "Mar 10", "NY", "https://meet.com", 30);
```

#### 2. Adding guests with exception handling:

```java
try {
    Guest newGuest = new Guest("John Doe", "john@email.com");
    events[0].addGuest(newGuest);
    System.out.println("✓ Guest added successfully!");
} catch (EventFullException e) {
    System.out.println("✗ ERROR: " + e.getMessage());
}
```

#### 3. Displaying event details (polymorphism):

```java
for (int i = 0; i < eventCount; i++) {
    System.out.println("\nEvent " + (i + 1) + ":");
    System.out.println(events[i].getEventDetails()); // Calls correct version
}
```

#### 4. Sending notifications:

```java
events[0].sendNotification("Event reminder: Starts at 9 AM tomorrow!");
```

---

## Requirements Checklist

| # | Requirement | Status |
|---|-------------|--------|
| 1 | Project proposal submitted | ✅ |
| 2 | Code is testable (no user input in core) | ✅ |
| 4 | Well documented with Javadoc | ✅ |
| 5 | Clear, meaningful names | ✅ |
| 6 | Responsibilities distributed | ✅ |
| 7 | No code duplication | ✅ |
| 8 | Short, focused methods | ✅ |
| 11 | Abstract superclass with abstract method | ✅ |
| 12 | Subclasses override & call superclass | ✅ |
| 13 | All classes have toString() | ✅ |
| 14 | Custom exceptions defined | ✅ |
| 15 | Custom interface implemented | ✅ |
| 16 | Demonstrates aggregation | ✅ |
| 16 | Demonstrates inheritance | ✅ |
| 16 | Demonstrates polymorphism | ✅ |
| 16 | Demonstrates exception handling | ✅ |
| 17 | UML diagram included | ✅ |
| 18 | Uses arrays only | ✅ |

---

## Main Menu Suggestions for Trushant

**Recommended Menu Options:**
1. Create Event (choose type: In-Person/Virtual/Hybrid)
2. Add Guest to Event
3. View Event Details
4. View Guest List
5. Send Notification
6. List All Events
7. Exit

---

## Testing Recommendations

- ✅ Test adding guests until capacity is reached
- ✅ Test all three event types
- ✅ Test notification system
- ✅ Test guest list display
- ✅ Demonstrate polymorphism with mixed Event array
- ✅ Test exception handling for full events

---

## Sample Event Creation Code

```java
// In-Person Event
Event event1 = new InPersonEvent(
    "Tech Conference 2025",
    "March 15, 2025",
    "Convention Center, Toronto",
    50
);

// Virtual Event
Event event2 = new VirtualEvent(
    "AI Workshop",
    "April 20, 2025",
    "https://zoom.us/meeting123"
);

// Hybrid Event
Event event3 = new HybridEvent(
    "Medical Symposium",
    "May 10, 2025",
    "Hospital Auditorium",
    "https://meet.google.com/abc-defg-hij",
    30
);
```

---

## Notes

- Virtual events have **unlimited capacity** (Integer.MAX_VALUE)
- In-person events are limited by **physical venue capacity**
- Hybrid events track physical capacity but allow unlimited virtual attendance
- All exceptions must be caught and handled in the main application
- Guest arrays are initialized with MAX_GUESTS (100) capacity by default

---

## Contact & Support

**Developer:** Gorkem  
**For Questions:** Review this documentation and the inline Javadoc comments in the code

**Last Updated:** December 2025

---

## License

This project is for educational purposes as part of an Object-Oriented Programming course.
