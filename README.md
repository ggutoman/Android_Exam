                                                                                 # Android_Exam
Examination for mobile applicants

# **Goal of the exam**

To Effectively assess a developer's skills in Android app Development and their decision-making capabilities for solving common development task.

# **Task**
To complete this examination, follow the steps below:

### 1. Fork the Repository
- Start by forking the repository to create your own copy. This will allow you to make changes and submit your project without affecting the original repository.

### 2. Create an Android Project
- Create a new Android project using Android Studio.
- Ensure that the project is configured to use Kotlin and Jetpack Compose.

### 3. Implement the Features
You will need to implement the following features in your project:

#### a. **User Interface using Jetpack Compose:**
- Utilize Jetpack Compose to design the UI components.
- Create composable functions for the main screen, user list, and user detail screen.

#### b. **Load and Show List of Users from Remote Source:**
- Use [Random User API](https://randomuser.me/) to fetch a list of users.
- Use **Ktor** as the HTTP client to make network requests.
- Parse the response and display the users in a list.

#### c. **Show Full Details of a User:**
- When a user in the list is clicked, navigate to a new screen that shows the full details of the selected user.
- Implement navigation using **Compose Navigation**.

#### d. **Swipe to Refresh List:**
- Add functionality to allow users to refresh the list of users by swiping down.

#### e. **Load More Data when Scrolling Down:**
- Implement pagination so that more users are loaded as the user scrolls down the list.

#### f. **Data to be Displayed for Each User:**
Each user should display the following information:
- First Name
- Last Name
- Birth Date
- Email Address
- Contact Number

#### g. **Single Activity Module:**
- Implement the entire app using a single activity and different composable screens for the user list and user details.

#### h. **MVVM Design Pattern:**
- Use the MVVM (Model-View-ViewModel) design pattern to structure your code.
- The ViewModel should manage the UI-related data and business logic.

### 4. Testing and Documentation
- Test your application to ensure it functions correctly.
- Document your code and provide a README file that explains how to build and run the project.

### 5. Push the Code to Your Forked Repository
- Once the project is complete, push your code to your forked repository.
- Share the link to the repository as your submission.

### 6. Additional Recommendations:
- **Use Coroutines:** for asynchronous tasks, such as making network calls.
- **Error Handling:** Implement proper error handling for network requests and user input.
- **Dependency Injection:** Consider using Koin for dependency injection.
- **UI/UX:** Ensure that the UI is intuitive and user-friendly.
