# Sophon User Guide

![Sophon Chatbot Screenshot](Ui.png)

Sophon is a powerful task management chatbot designed to help you organise your tasks efficiently. Whether you're a student, professional, or anyone looking to stay on top of their to-do list, Sophon is here to assist you every step of the way. With Sophon, you can easily add, view, and manage your tasks using simple commands.

## Features

### Adding a Todo Task: `todo`

Add a simple task to your list.

**Format:** `todo DESCRIPTION`

**Example:**
```
todo Watch CS2103T breifing 
```

**Expected output:**
```
Got it! I've added this task:
  [T][ ] Watch CS2103T breifing
Now you have 1 task in your list.
```

### Adding a Deadline: `deadline`

Add a task with a specific deadline.

**Format:** `deadline DESCRIPTION /by DEADLINE`

**Deadline formats supported:**
- `yyyy-MM-dd HH:mm:ss` (e.g., 2024-12-15 23:59)

**Example:**
```
deadline Submit CS2103T IP /by 2026-02-20 16:00:00
```

**Expected output:**
```
Got it! I've added this task:
  [D][ ] Submit CS2103T IP (by Feb 20 2026 16:00:00)
Now you have 2 tasks in your list.
```

### Adding an Event: `event`

Add a task that starts and ends at specific times.

**Format:** `event DESCRIPTION /from START_TIME /to END_TIME`

**Example:**
```
event Team meeting /from 2026-02-19 21:30:00 /to 2026-02-19 22:30:00
```

**Expected output:**
```
Got it! I've added this task:
  [E][ ] Team meeting (from: Feb 19 2026, 21:30:00 to Feb 19 2026 22:30:00)
Now you have 3 tasks in your list.
```

### Viewing All Tasks: `list`

Display all tasks in your list.

**Format:** `list`

**Example:**
```
list
```

**Expected output:**
```
Here are the tasks in your list:
1. [T][ ] Watch CS2103T breifing
2. [D][ ] Submit CS2103T IP (by Feb 20 2026 16:00:00)
3. [E][ ] Team meeting (from: Feb 19 2026, 21:30:00 to Feb 19 2026 22:30:00)
```

### Marking Tasks as Done: `mark`

Mark a task as completed.

**Format:** `mark TASK_INDEX`

**Example:**
```
mark 1
```

**Expected output:**
```
Great! I have marked this task as done:
  [T][X] Watch CS2103T breifing
```

### Unmarking Tasks: `unmark`

Mark a completed task as not done.

**Format:** `unmark TASK_INDEX`

**Example:**
```
unmark 1
```

**Expected output:**
```
Sure! I have marked this task as not done yet:
  [T][ ] Watch CS2103T breifing
```

### Deleting Tasks: `delete`

Remove a task from your list.

**Format:** `delete TASK_INDEX`

**Example:**
```
delete 2
```

**Expected output:**
```
Got it! I have deleted this task:
  [D][ ] Submit CS2103T IP (by Feb 20 2026 16:00:00)
Now you have 2 tasks in your list
```

### Finding Tasks: `find`

Search for tasks containing specific keywords.

**Format:** `find KEYWORD`

**Example:**
```
find meeting
```

**Expected output:**
```
Here are the matching tasks in your list:
1. [E][ ] Team meeting (from: Feb 19 2026, 21:30:00 to Feb 19 2026 22:30:00)
```

### Check command: `help`

Check a list of all available commands and their formats.

**Format:** `help`

**Expected output:**
```
Here are the available commands:
(First line: format)
(Second line: usage)

1. todo [description]
   Adds a todo task.

2. deadline [description] /by yyyy-MM-dd HH:mm:ss
   Adds a deadline task.

3. event [description] /from yyyy-MM-dd HH:mm:ss /to yyyy-MM-dd HH:mm:ss
   Adds an event task.

4. list
   Shows all tasks.

5. mark [task number]
   Marks a task as done.

6. unmark [task number]
   Marks a task as not done.

7. delete [task number]
   Deletes a task.

8. find [keyword]
   Finds tasks containing the keyword.

9. help [command]
   Get detailed help for a specific command.

10. bye
    Exits the application.                
```

### Check specific command: `help [command]`

Check the format and usage of a specific command with a detailed example.

**Format:** `help COMMAND`

**Example:**
```
help deadline
```

**Expected output:**
```
deadline [description] /by yyyy-MM-dd HH:mm:ss
Adds a deadline task.

Example:
deadline submit assignment /by 2026-03-01 23:59:00
```

### Exiting the Application: `bye`

Close Sophon and save your tasks.

**Format:** `bye`

**Example:**
```
bye
```

**Expected output:**
```
Bye bye! Sophon hopes to see you again soon! :)
```


### Acknowledgements
This README.md is adapted from "https://github.com/Duyy0406/ip/blob/master/docs/README.md" with some modifications to fit the context of Sophon. 
Artificial Intelligence (AI) has been used to assist in this project: generate user and Sophon's avatar, write the JavaDoc comments, CSS files, and assist me refactor the code.
