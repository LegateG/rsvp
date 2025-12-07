# Modern Event Management System

A console-based Java application for managing in-person, virtual, and hybrid events with guest tracking and notification capabilities.

## Project Overview

This Object-Oriented Programming project demonstrates key OOP concepts including inheritance, polymorphism, aggregation, interface implementation, and exception handling. The system allows users to create and manage three types of events while maintaining guest lists and sending notifications.

## Team Members

- **Gorkem** - Core Model & Logic (Event classes, Guest class, Interface, Exceptions)
- **Trushant** - Application Flow & User Interaction (EventTester, Menu system, User input)

## Features

- **Multiple Event Types**: Support for In-Person, Virtual, and Hybrid events
- **Guest Management**: Add guests with RSVP tracking and capacity limits
- **Notifications**: Send reminders and updates to all registered guests
- **Exception Handling**: Graceful handling of capacity limits and invalid inputs
- **Polymorphism**: Unified management of different event types

## System Architecture

### Class Structure

```
Communicable (Interface)
    ↑
Event (Abstract Class)
    ├── InPersonEvent
    ├── VirtualEvent
    └── HybridEvent

Guest (Aggregated by Event)
EventFullException (Custom Exception)
InvalidEventDateException (Custom Exception)
```

### Key Relationships

- **Inheritance**: All event types extend the abstract Event class
- **Aggregation**: Event HAS-A Guest array
- **Interface Implementation**: Event implements Communicable
- **Polymorphism**: Event array holds all event types

## Project Structure

```
EventManagementProject/
├── EventManagementCore.java          # Core classes (Gorkem)
├── EventTester.java                  # Main application (Trushant)
├── EventManagementSystem_UML.png     # UML diagram
├── ProjectDocumentation.pdf          # Full documentation
└── README.md                         # This file
```

## How to Run

1. **Compile the project:**
   ```bash
   javac EventManagementCore.java EventTester.java
   ```

2. **Run the application:**
   ```bash
   java EventTester
   ```

3. **Follow the menu prompts** to:
   - Create new events
   - Add guests to events
   - View event details
   - Display guest lists
   - Send notifications

## Usage Example

```java
// Create an in-person event
Event conference = new InPersonEvent(
    "Tech Conference 2025",
    "March 15, 2025",
    "Convention Center, Toronto",
    50
);

// Add guests
try {
    Guest guest = new Guest("Alice Johnson", "alice@email.com");
    conference.addGuest(guest);
} catch (EventFullException e) {
    System.out.println(e.getMessage());
}

// View details
System.out.println(conference.getEventDetails());

// Send notification
conference.sendNotification("Event starts at 9 AM!");
```

## OOP Concepts Demonstrated

| Concept | Implementation |
|---------|---------------|
| **Abstraction** | Abstract Event class with abstract method |
| **Inheritance** | Three event subclasses extend Event |
| **Polymorphism** | Event array holds different event types |
| **Encapsulation** | Private fields with public getters/setters |
| **Aggregation** | Event HAS-A Guest array |
| **Interface** | Communicable interface for notifications |
| **Exception Handling** | Custom exceptions (EventFullException) |

## Course Requirements

✅ Abstract superclass with abstract method  
✅ Subclasses override methods and call superclass  
✅ All classes have toString() methods  
✅ Custom exceptions thrown and handled  
✅ Custom interface implemented  
✅ Demonstrates aggregation, inheritance, polymorphism  
✅ UML class diagram included  
✅ Uses arrays only (no Collections)  
✅ Well-documented code  
✅ No user input in core classes  

## Documentation

- **Full Documentation**: See `ProjectDocumentation.pdf` for detailed class descriptions, sample outputs, and usage guide
- **UML Diagram**: See `EventManagementSystem_UML.png` for visual class relationships
- **Code Comments**: All classes include Javadoc-style documentation

## Technical Details

- **Language**: Java
- **Data Structures**: Arrays only
- **Design Patterns**: Template Method, Strategy (via polymorphism)
- **Exception Handling**: Try-catch blocks with custom exceptions

## Notes

- No external libraries required (uses only Java standard library)
- Virtual events have unlimited capacity
- In-person and hybrid events have configurable capacity limits
- All event data is stored in memory (no database)

## Contact

For questions or issues, please contact the development team:
- **Gorkem** - Core Model Development
- **Trushant** - Application Development

---

**Course**: Object-Oriented Programming  
**Date**: December 2025  
**Version**: 1.0
