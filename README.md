# Digital Scroll Management System

The **Digital Scroll Management System** is a Java-based application designed to manage digital scrolls with features that cater to both regular users and administrators. This application offers functionalities such as uploading, editing, deleting, viewing, and downloading digital scrolls, with role-based access controls for added security and user experience.

## Table of Contents
- [Features](#features)
- [System Requirements](#system-requirements)
- [Setup](#setup)
- [Usage](#usage)
- [User Roles](#user-roles)
    - [Guest](#guest)
    - [Normal User](#normal-user)
    - [Admin](#admin)
- [Testing](#testing)
    - [Manual Testing](#manual-testing)
    - [Automated Testing](#automated-testing)
- [Technology Stack](#technology-stack)

## Features

### User Functionality
- **Add New Scrolls**: Upload digital scrolls with metadata for easy management.
- **Edit and Update Scrolls**: Modify scroll details, including title and file. Only scrolls uploaded by the user can be edited.
- **Remove Scrolls**: Delete scrolls uploaded by the user.
- **View and Search Scrolls**: Browse available scrolls with advanced filters (uploader ID, scroll ID, name, upload date).
- **Preview Scrolls**: Preview a scroll's metadata before downloading.
- **Download Scrolls**: Download scrolls directly from the preview interface.

### Admin Functionality
- **View Users**: Access a list of all registered users and their profiles.
- **Add Users**: Create new users with specific roles.
- **Delete Users**: Remove users from the system.
- **View Statistics**: Access detailed statistics of scroll uploads and downloads.

## System Requirements
- **Java**: JDK 11 or higher
- **JavaFX**: JavaFX SDK for GUI development
- **Maven/Gradle**: For project management and dependency resolution
- **JSON Parser**: Jackson library for handling JSON data

## Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/DigitalScrollManagementSystem.git

2. **Navigate to the project directory**:
    ```bash
   cd DigitalScrollManagementSystem

3. **Install dependencies**:
    ```bash
    gradle build

4. **Run the application**:
    ```bash
    gradle run

## Usage

### Launching the Application
After starting the application, the main interface will open. Depending on your role (Guest, User, Admin), you'll have access to different features.

### Navigating the Interface

1. User Management:
- Login: For registered users to log in.
- Registration: Create a new user account.
- Update Profile: Modify your user profile information.
- Logout: Sign out of the application.

2. Scroll Management:
- Add Scroll: Upload a new scroll.
- Edit My Scrolls: Modify the scrolls you have uploaded.
- View and Search Scrolls: Browse all available scrolls using search filters. Preview and download scrolls directly.

3. Admin Options:
- View Users: Access a detailed list of all users.
- Add User: Create a new user with admin privileges.
- Delete User: Remove a user from the system.
- View Stats: Access detailed statistics of scroll usage.

## User Roles
1. Guest
- Can only browse scrolls using search filters.
- Cannot upload, edit, or download scrolls.

2. Normal User
- Can upload, edit, and delete their own scrolls.
- Can view, search, preview, and download any scroll.

3. Admin
- Full access to all features, including managing users and viewing system statistics.

## Testing

### Manual Testing
1. User Registration and Login:
- Register a new user via the "Registration" option.
- Log in using the newly created credentials.
- Attempt to log in with incorrect credentials to verify error handling.

2. Scroll Management:
- Upload a scroll using the "Add Scroll" option.
- Edit the scroll you just uploaded and verify changes.
- Delete the scroll you uploaded and ensure it's removed from the list.
- Use the search feature to filter scrolls by uploader ID, scroll name, and upload date.
- Preview and download scrolls to check that the file download works as expected.

3. Admin Features:
- Log in as an Admin user.
- Use the "View Users" feature to browse user profiles.
- Add a new user and verify it appears in the user list.
- Delete a user and ensure it's removed from the system.
- View statistics to verify the upload/download count for each scroll.

### Automated Testing
**JUnit Tests**:
- The project includes JUnit test cases to verify core functionalities such as user registration, login, scroll uploads, and data retrieval.
- To run the tests, navigate to the project directory and execute:
    ```bash
    gradle test

## Technology Stack
- **Java**: Main programming language.
- **JavaFX**: GUI framework for creating the user interface.
- **Jackson**: JSON library for parsing and handling JSON data.
- **Maven/Gradle**: Build tools for dependency management and project configuration.
- **JUnit**: Testing framework for unit tests.

