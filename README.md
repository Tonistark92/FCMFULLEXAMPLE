# FCM Notification Project

This project demonstrates the integration of Firebase Cloud Messaging (FCM) into an Android application. It covers the following functionalities:

- Receiving notifications from FCM using topics and from another device.
- Sending the updated token to the server.
- Handling messages received from FCM. If it is a data message, it processes it. If it is a notification message, it displays the notification both in the foreground and background.
- Can send and recive as broadcast so all the devices gets it and i can send to them all

## Getting Started

### 1. Firebase Setup

1. Create a Firebase project at [Firebase Console](https://console.firebase.google.com/).
2. Add an Android app to your Firebase project.
3. Download the `google-services.json` file and place it in the `app/` directory of your project.
4. Add Firebase SDK dependencies to your `build.gradle` files:

## Images
 - from got notification while on forground 
![img1](https://github.com/Tonistark92/FCM_Full_Example/assets/86676102/7aeec564-1d80-48d7-b32c-a913700cba82)
 - from got notification while on background 
![img2](https://github.com/Tonistark92/FCM_Full_Example/assets/86676102/532c305e-cfb5-4263-8922-c47844bf64fb)
 
