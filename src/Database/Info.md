Every class in Database has two or three level of permissions in the Hierarchy.
Here is a list of each permissions

Hierarchy leaderboard:
1. Admin
2. Librarian / Financial / Staff
3. Customers

Hierarchy prototype:
1. Basic permissions is only given to the customers
2. A combination of all three levels of heirarchy is given to Librarian / Financial / Staff
3. Advanced level is given to admins

Permissions per class:
1. Announcements:
    ## Basic:
    1. View announcements
    ## Intermediate:
    2. Add announcements

2. BooksDB:
    ## Basic:
    1. Get Book information
    2. Get all book information
    3. Buy a book
    4. Borrow a book
    ## Intermediate:
    1. Remove a book
    2. Add a book
    ## Advanced:
    1. Edit book information

3. Customers:
    ## Basic:
    1. Get User
    2. Validate Login
    ## Intermediate:
    1. Add user
    2. Remove user
    3. Check username
    4. Get all users
    ## Advanced:
    1. Edit User information

4. CustomerTickets:
    ## Basic:
    1. Create a ticket
    2. Request a book
    3. Extend due date
    4. Get all tickets
    5. Add messages to tickets
    ## Intermediate:
    1. Remove ticket

5. Logs:
    ## Basic:
    1. Log an action 
    ## Intermediate:
    1. Get all logs

6. Maintenance:
    ## Basic:
    1. Check status
    ## Intermediate:
    2. Set status
