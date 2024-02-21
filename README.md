# android-clean-architecture
A postman simulation has been created, bringing the accounts and details of the movements.
## Main Picture
There are 3 layer in this app().  
| UI(Presentation Layer)  | Domain Layer          | Data Layer                         
| ----------------------- | --------------------- | ---------------------------------- |
| respositories           | entity                | data source, dto                   |
| viewmodel,factory       | usecase               | interface AccountApiService        |
| probably your extension | repository interface  | config(retrofit)                   |
| etc...                  | etc..                 |                                    |
