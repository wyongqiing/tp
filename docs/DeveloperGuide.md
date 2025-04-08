Developer Guide
=======

## Table of Contents

1. [Acknowledgements](#acknowledgements)
2. [Setting up, getting started](#setting-up-getting-started)
3. [Design](#design)
    - [Architecture](#architecture)
        - [Main components of the architecture](#main-components-of-the-architecture)
        - [How the architecture components interact with each other](#how-the-architecture-components-interact-with-each-other)
    - [UI Component](#ui-component)
    - [Logic Component](#logic-component)
    - [Model Component](#model-component)
    - [Storage Component](#storage-component)
    - [Common Classes](#common-classes)
4. [Implementation](#implementation)
    - [Undo/Redo Feature](#proposed-undoredo-feature)
5. [Documentation, Logging, Testing, Configuration, DevOps](#documentation-logging-testing-configuration-dev-ops)
6. [Appendix: Requirements](#appendix-requirements)
    - [Product Scope](#product-scope)
    - [User Stories](#user-stories)
    - [Use Cases](#use-cases)
    - [Non-Functional Requirements](#non-functional-requirements)
    - [Glossary](#glossary)
7. [Appendix: Instructions for Manual Testing](#appendix-instructions-for-manual-testing)
8. [Appendix: Planned Enhancement](#appendix-planned-enhancement)

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

HRelper is built upon the AddressBook-Level3 project created by the SE-EDU initiative.
It incorporates the following third-party libraries:

* JavaFX – for building the graphical user interface (GUI)
* Jackson – for handling JSON serialization and deserialization
* JUnit 5 – for writing and running unit tests

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**
Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------
## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [
_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create
and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2425S2-CS2103T-F14-2/tp/tree/master/src/main
/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/AY2425S2-CS2103T-F14-2/tp/tree/master/src/main/java/seedu/address/MainApp.java)) is
in charge of the app launch and shut down.

* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding
  API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component
through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the
implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified
in [`Ui.java`](https://github.com/AY2425S2-CS2103T-F14-2/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts
e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`,
inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the
visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that
are in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API
** : [`Logic.java`](https://github.com/AY2425S2-CS2103T-F14-2/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API
call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates
   a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which
   is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take
   several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:

* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a
  placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse
  the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as
  a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser`
  interface so that they can be treated similarly where possible e.g, during testing.

### Model component

**API
** : [`Model.java`](https://github.com/AY2425S2-CS2103T-F14-2/tp/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the HRelper data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which
  is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to
  this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as
  a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they
  should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram-1.png" width="550" />

</div>

### Storage component

**API
** : [`Storage.java`](https://github.com/AY2425S2-CS2103T-F14-2/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,

* can save both HRelper data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only
  the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
  that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo
history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the
following operations:

* `VersionedAddressBook#commit()`— Saves the current HRelper state in its history.
* `VersionedAddressBook#undo()`— Restores the previous HRelper state from its history.
* `VersionedAddressBook#redo()`— Restores a previously undone HRelper state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()`
and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the
initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command
calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes
to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book
state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also
calls `Model#commitAddressBook()`, causing another modified address book state to be saved into
the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing
the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer`
once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

![UndoSequenceDiagram](images/UndoSequenceDiagram-Logic.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

Similarly, how an undo operation goes through the `Model` component is shown below:

![UndoSequenceDiagram](images/UndoSequenceDiagram-Model.png)

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once
to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such
as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`.
Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not
pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be
purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern
desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
    * Pros: Easy to implement.
    * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
    * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

HRelper is designed for HR teams who:

* manage a significant number of employee records and contact details.
* find traditional spreadsheets inefficient or cumbersome for day-to-day contact management.
* has a need to manage a significant number of contacts
* require a structured system to track details like joining dates, NRICs, and other personal data.
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Enables HR to quickly access and manage employee and job seeker information, streamlining
recruitment and communication within the company.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​ | I want to …​                                  | So that I can…​                                                          |
|----------|---------|-----------------------------------------------|--------------------------------------------------------------------------|
| `* * *`  | user    | I can add a profile                           | so that I can store employee details in the address book.                |
| `* * *`  | user    | I can delete the employees profile            | so that I can removed outdated or incorrect records.                     |
| `* * *`  | user    | I can view a profiles                         | so that I can get employee information.                                  |
| `* * `   | user    | I can edit the profile                        | so that i can update their information when needed.                      |
| `* *`    | user    | I can filter contacts by department           | so that I can quickly see all employees in a specific department.        |
| `* *`    | user    | I can filter contacts by employement type     | so that I can quickly see all employees with a specific employment type. |
| `* *`    | user    | I can filter contacts by job title            | so that I can quickly see all employees with a specific job title.       |
| `* *`    | user    | I can add an extra optional note to a profile | so that I am aware of additional information about an employee.          |

### Use cases
(For all use cases below, the **System** is the `HRelper` and the **Actor** is the `HR manager (HR)`, unless specified
otherwise)

**Use case: Add an Employee Profile**

**MSS **

1. HR requests to add an employee profile.

2. HRelper prompts for details of the employee.

3. HR fills in the details.

4. HRelper requests HR to confirm the details.

5. HR confirms the details.

6. HRelper adds the employee to the system.

   Use case ends.

**Extensions**

* 3a. HR enters invalid data for one or more attributes.
    * 3a1. HRelper displays an error message indicating which field is invalid and the expected format.
    * 3a2. HR corrects the invalid entry.
      Use case resumes at step 3.
* 4a. HR decides to change details.
    * 4a1. HR cancels confirmation.
      Use case resumes at step 3.

* 4b. HRelper finds an existing employee profile by tags. (Department, Employment Type or Job Title)
  Use case resumes at step 3.

* 5a. HR loses connection.

    * 5a1. HRelper saves details.

    * 5a2. HRelper prompts HR to continue where they left off.
      Use case resumes at step 3.

**Use case: Find Employees by Department**

**MSS**

1. HR requests to find employees by department.
2. HRelper prompts for the department keyword.
3. HR enters the department keyword.
4. HRelper displays a list of employees in the specified department.
   Use case ends.

**Extensions**

* 3a. HR enters invalid characters in the department keyword.
    * 3a1. HRelper shows an error message explaining valid department input format.
      Use case resumes at step 2.
* 4a. No employees found in the specified department.
    * 4a1. HRelper shows an empty list.

      Use case ends.

**Use case: Find Employees by Job Title**

**MSS**

1. HR requests to find employees by job title.
2. HRelper prompts for the job title keyword.
3. HR enters the job title keyword.
4. HRelper displays a list of employees with the specified job title.
   Use case ends.

**Extensions**

* 3a. HR enters invalid characters in the job title keyword.
    * 3a1. HRelper shows an error message explaining valid job title input format.
      Use case resumes at step 2.
* 3b. HR enters a keyword that is too short or ambiguous.
    * 3b1. HRelper suggests using more specific keywords.
      Use case resumes at step 2.
* 4a. No employees found with the specified job title.
    * 4a1. HRelper shows an empty list. </br> Use case ends.

**Use case: Find Employees by Employment Type**

**MSS**

1. HR requests to find employees by employment type.
2. HRelper prompts for the employment type keyword.
3. HR enters the employment type keyword.
4. HRelper displays a list of employees with the specified employment type.</br>
   Use case ends.

**Extensions**

* 3a. HR enters invalid characters in the employment type keyword.
    * 3a1. HRelper shows an error message explaining valid employment type input format.
      Use case resumes at step 2.
* 4a. No employees found with the specified employment type.
    * 4a1. HRelper shows an empty list.</br>
      Use case ends.

**Use case: View Employee Details by Full Name or Surname**

**MSS**

1. HR enters the employee's full name.
2. HRelper displays the comprehensive profile of the specified employee.</br>
   Use case ends.

Extensions

* 1a. The given name does not match any employee in the system.
    * 1a1. HRelper shows an error message.</br>
      Use case resumes at step 2.
* 1b. Multiple employees share the same name.
    * 1b1. HRelper displays a list of all matching employees.</br>
      Use case ends.
* 2a. The employee profile has incomplete information.
    * 2a1. HRelper displays the available information with indicators for missing fields.</br>
      Use case ends.

**Use case: Add/Edit Note to Employee Profile by Index**

**MSS**

1. HR requests to add or edit a note for an employee.
2. HR enters the employee index and new note content.
3. HRelper updates the employee's profile with the new note.

   Use case ends.

Extensions

* 3a. The given index is invalid.
    * 3a1. HRelper shows an error message.</br>
      Use case resumes at step 2.
* 3b. HR enters empty note content.
    * 3b1. HRelper removes any existing note from the employee profile.
    * 3b2. HRelper confirms the note has been removed.</br>
      Use case ends.
* 4a. HR tries to add a note that exceeds the character limit.
    * 4a1. HRelper shows an error message indicating the character limit.</br>
      Use case resumes at step 3.

**Use case: Delete an Employee Profile**

**MSS**

1. HR requests to list employees.
2. HRelper shows a list of employees.
3. HR requests to delete a specific employee in the list.
4. HRelper deletes the employee.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. HRelper shows an error message.

      Use case resumes at step 2.

**Use case: Add/Edit/Remove Note to Employee Profile by Index**

**MSS**

1. HR requests to add or edit a note for an employee.
2. HR enters the employee index and new note content.
3. HRelper updates the employee's profile with the new note.</br>
   Use case ends.

**Extensions**

* 3a. The given index is invalid.
    * 3a1. HRelper shows an error message.
      Use case resumes at step 2.
* 3b. HR enters empty note content.
    * 3b1. HRelper removes any existing note from the employee profile.</br>
      Use case ends.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `17` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
   able to accomplish most of the tasks faster using commands than using the mouse.
4. Managing contacts will remain as the core feature in all iterations.
5. The application should not crash for any input given by the user.
6. The application will remain as a CLI based application.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **HRelper**: A Human Resource management tool for managing employee profiles efficiently.
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **UI**: User Interface - the space where interactions between humans (users) and machines (software or hardware) occur
* **Sequence Diagram**: A visual representation of the sequence of interactions between different objects, components,
  or actors in a system over time
* **API**: Application Programming Interface - A set of rules, protocols, and tools that allows different software
  applications to communicate and interact with each other
* **GUI**: Graphical User Interface - A type of user interface that allows users to interact with software applications
  or devices through graphical elements rather than using text-based commands
* **JavaFx**: A Java library used for building modern GUIs
* **JSON**: An open standard file format and data interchange format that uses human-readable text to store and transmit
  data objects consisting of attribute–value pairs and arrays
* **CLI app**: Command-Line Interface application - A software application that allows users to interact with it through
  a text-based interface by typing commands in a console or terminal

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">Note: These instructions only provide a starting point for testers to work on;
testers are expected to do more exploratory testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder
    2. Open a terminal and `cd` into the folder with the jar file.
    3. Run `java -jar "[CS2103T-F14-02][HRelper]".jar`.</br>
       Expected: An empty address book will be shown.</br>
       The window size may not be optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by rerunning `java -jar "[CS2103T-F14-02][HRelper]".jar` in the terminal.<br>
       Expected: The most recent window size and location is retained.

### Deleting a person

1. Deleting a person while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    1. Test case: `delete 1`<br>
       Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.
       Timestamp in the status bar is updated.

    1. Test case: `delete 0`<br>
       Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Saving data

1. Testing data persistence across sessions

    1. Add a new person using the `add` command.

    2. Close the app.

    3. Re-launch the app. </br>
       Expected: The newly added person should still be present in the list.

You can try out other data changing commands such as `clear`,`delete`, `edit`.

2. Dealing with missing data file

    1. Exit HRelper, if it is running.
    2. delete the `addressbook.json` file inside the folder named `data`. It is located in the same folder as the jar
       file.
    3. Relaunch the app. </br>
       Expected: The app should create a new `addressbook.json` file filled with sample data.

## **Appendix: Planned Enhancement**

**Team Size** : 5

1. **Add a confirmation window before executing sensitive commands** (e.g., edit, delete).<br>
   Currently, once a command is entered, the system immediately executes it without confirmation.
   This can be risky, especially for delete operations. We plan to add a pop-up confirmation dialog
   before executing critical actions to prevent accidental edits or deletions.
2. **Implement `undo` functionality.**<br>
   There is currently no way to revert a mistake. If a user accidentally deletes or modifies a record, it cannot be
   recovered. We plan to introduce an undo feature that allows users to revert the most recent command that modified
   data.
3. **Implement `redo` functionality.**<br>
   After undoing a command, the user might want to reapply it. Currently, this requires retyping the command manually.
   We plan to add a redo feature to restore the most recently undone command.
4. **Allow multiple deletions in a single command.**<br>
   Currently, the delete command only supports deleting one contact at a time. We plan to support multiple deletions
   using comma-separated indexes, e.g., `delete 1, 3, 5` to improve user efficiency.
5. **Relax address format constraints.**<br>
   Currently, addresses must include a Singapore-style postal code with specific district prefixes. To support global
   users and remote workers, we plan to relax this validation to allow international formats or omit postal codes
   altogether.
6. **Add support for data import/export using CSV files.**<br>
   Currently, HRelper stores and reads contact data in JSON format, which may not be familiar to non-technical users. HR
   teams often maintain contact records in Excel or Google Sheets and may need to perform bulk updates, generate
   reports, or share data externally.<br>
   We plan to introduce the ability to import data from CSV files, allowing HR teams to easily migrate existing records
   into HRelper without manually entering each contact. Similarly, we will support exporting current records to CSV
   format, enabling convenient data backup, offline reporting, and integration with other HR tools.

    * This feature will:
        1. Reduce manual entry and setup time
        2. Improve compatibility with spreadsheet tools
        3. Allow HR to generate and share filtered reports easily
        4. Provide error messages when invalid or missing fields are detected during import

7. **Implement a command history tracker**.<br>
   Currently, HRelper does not store a history of previously entered commands. This can be inconvenient for users who
   wish to repeat a past command, especially if the command was long or complex (e.g., multi-field `add` or `edit`
   commands).

   We plan to implement a **command history tracker** that:

    - Maintains a chronological log of previously executed commands.
    - Allows users to **navigate the history using arrow keys** (↑ / ↓) in the command box.
    - Enables users to **reuse, modify, or re-execute** previous commands with ease.

   This enhancement improves usability by reducing the need for repetitive typing and helps users correct or retry
   failed commands quickly.
8. **Enable tag auto-suggestions when typing departments, job titles, or employment types.**
   Currently, users must remember and manually input the exact department/job title format. This can lead to errors or
   inconsistencies.
   We plan to implement autocomplete suggestions when the user begins typing tags (e.g., “Fin” suggests “Finance”) to
   improve speed, accuracy, and consistency in data entry.
9. **Add a mini HR dashboard for quick data overview.**
   Currently, HRelper only displays contact information. For HR users managing a large database, a dashboard summarizing
   key statistics would be useful.
   We plan to introduce a sidebar or pop-up dashboard that shows:

    - Total number of employees
    - Department breakdown
    - Employment type distribution
    - Gender and nationality statistics
    - This enhancement gives HR teams quick insights at a glance and supports workforce planning.

10. **Enable editing multiple entries in one command.**
    Currently, the `edit` command only supports updating one person at a time. In many HR workflows, bulk updates are
    needed (e.g., changing job titles for a group of interns or updating department names after restructuring).

    We plan to enhance the `edit` command to support **batch editing** using comma-separated indexes and a shared set of
    fields. <br>
    For example:<br>
    `edit 2, 4, 5 t/IT/Intern/Software Intern`
    This would apply the same tag update to persons at index 2, 4, and 5.

    - The command will apply the same changes to all specified records.
    - If any index is invalid, the system will display an error message and abort the operation to avoid partial
      updates. 
    - This will significantly reduce repetitive commands and improve productivity when handling common changes.
